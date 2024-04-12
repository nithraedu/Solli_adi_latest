package nithra.tamil.word.game.solliadi.showcase;

import android.app.Activity;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;


public class MaterialShowcaseSequence implements IDetachedListener {

    final Queue<MaterialShowcaseView> mShowcaseQueue;
    final Activity mActivity;
    PrefsManager mPrefsManager;
    private boolean mSingleUse = false;
    private ShowcaseConfig mConfig;
    private int mSequencePosition = 0;

    private OnSequenceItemShownListener mOnItemShownListener = null;
    private OnSequenceItemDismissedListener mOnItemDismissedListener = null;

    public MaterialShowcaseSequence(Activity activity) {
        mActivity = activity;
        mShowcaseQueue = new LinkedList<>();
    }

    public MaterialShowcaseSequence(Activity activity, String sequenceID) {
        this(activity);
        this.singleUse(sequenceID);
    }

    public void addSequenceItem(View targetView, String content, String dismissText) {

        MaterialShowcaseView sequenceItem = new MaterialShowcaseView.Builder(mActivity)
                .setTarget(targetView)
                .setDismissText(dismissText)
                .setContentText(content)
                .build();

        if (mConfig != null) {
            sequenceItem.setConfig(mConfig);
        }

        mShowcaseQueue.add(sequenceItem);
    }

    public MaterialShowcaseSequence addSequenceItem(MaterialShowcaseView sequenceItem) {
        mShowcaseQueue.add(sequenceItem);
        return this;
    }

    public void singleUse(String sequenceID) {
        mSingleUse = true;
        mPrefsManager = new PrefsManager(mActivity, sequenceID);
    }

    public void setOnItemShownListener(OnSequenceItemShownListener listener) {
        this.mOnItemShownListener = listener;
    }

    public void setOnItemDismissedListener(OnSequenceItemDismissedListener listener) {
        this.mOnItemDismissedListener = listener;
    }

    public boolean hasFired() {

        return mPrefsManager.getSequenceStatus() == PrefsManager.SEQUENCE_FINISHED;
    }

    public void start() {

        /**
         * Check if we've already shot our bolt and bail out if so         *
         */
        if (mSingleUse) {
            if (hasFired()) {
                return;
            }

            /**
             * See if we have started this sequence before, if so then skip to the point we reached before
             * instead of showing the user everything from the start
             */
            mSequencePosition = mPrefsManager.getSequenceStatus();

            if (mSequencePosition > 0) {
                for (int i = 0; i < mSequencePosition; i++) {
                    mShowcaseQueue.poll();
                }
            }
        }


        // do start
        if (mShowcaseQueue.size() > 0)
            showNextItem();
        System.out.println("mshowview " + mShowcaseQueue.size());
    }

    private void showNextItem() {

        if (mShowcaseQueue.size() > 0 && !mActivity.isFinishing()) {
            MaterialShowcaseView sequenceItem = mShowcaseQueue.remove();
            sequenceItem.setDetachedListener(this);
            System.out.println("mshowview1 " + mShowcaseQueue.size());
            sequenceItem.show(mActivity, mShowcaseQueue.size() == 0);
            if (mOnItemShownListener != null) {
                mOnItemShownListener.onShow(sequenceItem, mSequencePosition);
            }
        } else {
            /**
             * We've reached the end of the sequence, save the fired state
             */
            if (mSingleUse) {
                mPrefsManager.setFired();
            }
        }
    }


    @Override
    public void onShowcaseDetached(MaterialShowcaseView showcaseView, boolean wasDismissed) {

        showcaseView.setDetachedListener(null);

        /**
         * We're only interested if the showcase was purposefully dismissed
         */
        if (wasDismissed) {

            if (mOnItemDismissedListener != null) {
                mOnItemDismissedListener.onDismiss(showcaseView, mSequencePosition);
            }

            /**
             * If so, update the prefsManager so we can potentially resume this sequence in the future
             */
            if (mPrefsManager != null) {
                mSequencePosition++;
                mPrefsManager.setSequenceStatus(mSequencePosition);
            }

            showNextItem();
        } else {
            if (mSingleUse) {
                mPrefsManager.setFired();
            }
        }
    }

    public void setConfig(ShowcaseConfig config) {
        this.mConfig = config;
    }

    public interface OnSequenceItemShownListener {
        void onShow(MaterialShowcaseView itemView, int position);
    }

    public interface OnSequenceItemDismissedListener {
        void onDismiss(MaterialShowcaseView itemView, int position);
    }

}
