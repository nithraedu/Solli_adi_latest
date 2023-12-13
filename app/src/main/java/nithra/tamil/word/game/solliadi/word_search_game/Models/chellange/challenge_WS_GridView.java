package nithra.tamil.word.game.solliadi.word_search_game.Models.chellange;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Models.Direction;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Models.Word;


public class challenge_WS_GridView extends LinearLayout implements OnKeyListener {

    public static int clearSelection = 0;
    public static float[] touchPoint;
    final String[] colors = {"#F44336", "#9C27B0", "#2196F3", "#4CAF50", "#FFC107", "#00BCD4",
            "#E91E63", "#673AB7", "#009688", "#CDDC39", "#FF5722", "#607D8B", "#3F51B5",
            "#FF1744", "#0091EA", "#00C853", "#FFAB00", "#795548", "#827717", "#006064"};
    final SharedPreference sp = new SharedPreference();
    final List<Integer> listPos = new ArrayList<>();
    private final float mScale = getResources().getDisplayMetrics().density;
    private final float mMinDistance = (int) (50.0 * mScale + 0.5);
    private final int mColumns;
    private final int mRows;
    private final Matrix matrix = new Matrix();
    int i = 0, k = 0;
    Animation animShake, zoomIn, zoomOut;
    View view;
    MediaPlayer mp;
    Animation wobble;
    private int mDefaultColor;
    private int mSelectionColor;
    private int mColumnWidth;
    private float mCornerRadius;
    /**
     * Vars related to selection and selection drawing
     */
    private Integer mSelStartPosition;
    private Rect mSelStartRect;
    private Rect mSelEndRect;
    private Integer mSelectionSteps;
    private Direction mSelectionDirection;
    private Paint mPaint, mFoundPaint, mHintPaint, mMtPaint;
    private List<View> mPreviousSelection;
    private Bitmap mFoundCache;
    /**
     * State of the board
     */
    private WordsearchGameState mGameState;
    /**
     * Listeners
     */
    private OnWordSelectedListener mOnWordSelectedListener;
    private boolean mFocusSelected;
    private String mLastWordFound;

    public challenge_WS_GridView(Context context) {
        super(context);
        mColumns = sp.getInt(context, "GRID");
        mRows = sp.getInt(context, "GRID");
        init();
    }

    public challenge_WS_GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.wordsearch);
        mColumns = a.getInt(R.styleable.wordsearch_android_numColumns, sp.getInt(context, "GRID"));
        mRows = mColumns;
        init();
    }

    public void clearHint() {
        mGameState.mHint = null;
    }

    public void clearSelection() {
        if (mSelectionDirection != null && mSelectionSteps != null && mSelStartPosition != null) {
            for (View view : getSelectionViews()) {

                //((TextView) view.findViewById(R.id.lbl_char)).setTextColor(mDefaultColor);

                if (sp.getInt(getContext(), "THEME") == 0) {

                    ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                } else if (sp.getInt(getContext(), "THEME") == 1) {

                    ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);

                } else if (sp.getInt(getContext(), "THEME") == 3) {

                    ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                } else {

                    ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.WHITE);
                }
            }
            listPos.clear();

            clearSelection = 1;

            mOnWordSelectedListener.onWordSelected(getPositionsOnPath(mSelectionDirection, mSelStartPosition,
                    mSelectionSteps));
        }
        mSelStartPosition = null;
        mSelectionSteps = null;
        mSelectionDirection = null;
        postInvalidate();
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED && mLastWordFound != null) {
            event.getText().add("Word Found: " + mLastWordFound);
            mLastWordFound = null;
            return true;
        }
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    /**
     * @return the cellSize
     */
    public int getCellSize() {
        return mColumnWidth;
    }

    public View getChildAtPosition(int index) {
        int row = (int) Math.floor((double) index / (double) mColumns);
        int col = index % mColumns;
        LinearLayout rowView = (LinearLayout) getChildAt(row);
        return rowView.getChildAt(col);
    }

    public int getEndPosition(Direction direction, int startPosition, int steps) {
        int startRow = startPosition / mColumns;
        int startCol = startPosition % mColumns;
        int curRow = startRow;
        int curCol = startCol;

        for (int i = steps; i >= 0; i--) {
            curCol = startCol;
            curRow = startRow;

            if (direction.isUp()) {
                curRow -= steps;
            } else if (direction.isDown()) {
                curRow += steps;
            }

            if (direction.isLeft()) {
                curCol -= steps;
            } else if (direction.isRight()) {
                curCol += steps;
            }

            if (curRow < 0 || curCol < 0 || curRow >= mRows || curCol >= mColumns) {
                break;
            }
        }

        return (curRow * mColumns) + curCol;
    }

    public int getNumColumns() {
        return mColumns;
    }

    public int getNumRows() {
        return mRows;
    }

    public List<Integer> getPositionsOnPath(Direction direction, int startPosition, int steps) {
        List<Integer> positions = new ArrayList<Integer>();
        int curRow = startPosition / mColumns;
        int curCol = startPosition % mColumns;

        for (int i = 0; i <= steps; i++) {
            positions.add((curRow * mColumns) + curCol);

            if (direction.isUp()) {
                curRow -= 1;
            } else if (direction.isDown()) {
                curRow += 1;
            }

            if (direction.isLeft()) {
                curCol -= 1;
            } else if (direction.isRight()) {
                curCol += 1;
            }

            if (curRow < 0 || curCol < 0 || curRow >= mRows || curCol >= mColumns) {
                break;
            }

        }
        return positions;
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
            Rect rect = getViewRect(v);
            selectionChanged(rect.centerX(), rect.centerY());
            if (mFocusSelected) {
                clearSelection();
            }

            TextView tv = (TextView) ((ViewGroup) getFocusedChild()).getFocusedChild().findViewById(R.id.lbl_char);
            if (tv != null) {


                if (sp.getInt(getContext(), "THEME") == 0) {

                    tv.setTextColor(mFocusSelected ? 0xFF3f51b5 : Color.BLACK);
                } else if (sp.getInt(getContext(), "THEME") == 1) {

                    tv.setTextColor(mFocusSelected ? 0xFF3f51b5 : Color.BLACK);

                } else if (sp.getInt(getContext(), "THEME") == 3) {

                    tv.setTextColor(mFocusSelected ? 0xFF3f51b5 : Color.BLACK);
                } else {

                    tv.setTextColor(mFocusSelected ? 0xFF3f51b5 : Color.WHITE);
                }
            }
            Toast.makeText(getContext(), "" + tv.getText(), Toast.LENGTH_SHORT).show();
            mFocusSelected = !mFocusSelected;
        }

        return false;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof WordsearchGameState) {
            super.onRestoreInstanceState(((WordsearchGameState) state).getSuperState());
            mGameState = ((WordsearchGameState) state);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.AbsListView#onSaveInstanceState()
     */
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable state = super.onSaveInstanceState();
        WordsearchGameState saveState = new WordsearchGameState(state);
        saveState.mHint = mGameState.mHint;
        saveState.mFoundWords = mGameState.mFoundWords;
        return saveState;
    }

    public void reset() {
        if (mPreviousSelection != null) {
            mPreviousSelection.clear();
        }
        mGameState = new WordsearchGameState();
        mSelStartPosition = null;
        mSelStartRect = null;
        mSelEndRect = null;
        mSelectionDirection = null;
        mFoundCache = null;
        mSelectionSteps = null;
    }

    public void selectionChanged(float xPos, float yPos) {
        if (mSelStartPosition == null) {
            int position = pointToPosition((int) xPos, (int) yPos);
            if (position >= 0) {
                View item = getChildAtPosition(position);
                mSelStartRect = getViewRect(item);
                mSelStartPosition = position;
            }
            postInvalidate();
        } else {
            float xDelta = xPos - mSelStartRect.centerX();
            float yDelta = (yPos - mSelStartRect.centerY()) * -1;
            double distance = Math.hypot(xDelta, yDelta);
            Log.d("Angle", "DIST: " + (int) distance + ", MIN: " + mMinDistance);
            if (isInTouchMode() && distance < mMinDistance) {
                return;
            }

            Direction previousDirection = mSelectionDirection;
            Integer previousSteps = mSelectionSteps;
            mSelectionDirection = Direction.getDirection((float) Math.atan2(yDelta, xDelta));

            float stepSize = mSelectionDirection.isAngle() ? (float) Math.hypot(mColumnWidth, mColumnWidth) : mColumnWidth;
            mSelectionSteps = Math.round((float) distance / stepSize);

            if (mSelectionSteps == 0) {
                mSelectionSteps = null;
            }

            if (mSelectionDirection != previousDirection || mSelectionSteps != previousSteps) {
                List<View> selectedViews = getSelectionViews();
                if (selectedViews == null) {
                    return;
                }

                // Selection no longer includes these characters so
                // restore their default color
                if (mPreviousSelection != null && !mPreviousSelection.isEmpty()) {
                    List<View> oldViews = new ArrayList<View>(mPreviousSelection);
                    oldViews.removeAll(selectedViews);
                    for (View view : oldViews) {

                        if (sp.getInt(getContext(), "THEME") == 0) {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                        } else if (sp.getInt(getContext(), "THEME") == 1) {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);

                        } else if (sp.getInt(getContext(), "THEME") == 3) {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                        } else {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.WHITE);
                        }
                    }
                }

                mPreviousSelection = selectedViews;

                // Selection now includes these characters so
                // highlight them

                if (mSelectionDirection != null && mSelectionSteps != null && mSelStartPosition != null) {

                    /* if (!selectedViews.isEmpty()) {*/

                    //mp = MediaPlayer.create(getContext(), R.raw.btn);

                    for (View view : selectedViews) {


                        if (sp.getInt(getContext(), "THEME") == 0) {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                        } else if (sp.getInt(getContext(), "THEME") == 1) {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);

                        } else if (sp.getInt(getContext(), "THEME") == 3) {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                        } else {

                            ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.WHITE);
                        }

                        ((TextView) view.findViewById(R.id.lbl_char)).clearAnimation();

                    }

                    View endView = selectedViews.get(selectedViews.size() - 1);
                    mSelEndRect = getViewRect(endView);

                    listPos.clear();
                    mOnWordSelectedListener.onWordSelected(getPositionsOnPath(mSelectionDirection, mSelStartPosition,
                            mSelectionSteps));
                }
                postInvalidate();
            }
        }
    }

    public void setBoard(String[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                View v = getChildAtPosition(i * mColumns + j);
                ((TextView) v.findViewById(R.id.lbl_char)).setText("" + board[i][j]);


                if (sp.getInt(getContext(), "THEME") == 0) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                } else if (sp.getInt(getContext(), "THEME") == 1) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);

                } else if (sp.getInt(getContext(), "THEME") == 3) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                } else {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.WHITE);
                }


                if (sp.getInt(getContext(), "GRID") == 5) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(22);
                } else if (sp.getInt(getContext(), "GRID") == 6) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(20);
                } else if (sp.getInt(getContext(), "GRID") == 7) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(18);
                } else if (sp.getInt(getContext(), "GRID") == 8) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(17);
                } else if (sp.getInt(getContext(), "GRID") == 9) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(16);
                } else if (sp.getInt(getContext(), "GRID") == 10) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(15);
                } else if (sp.getInt(getContext(), "GRID") == 11) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(12);
                } else if (sp.getInt(getContext(), "GRID") == 12) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextSize(10);
                }

            }
        }
    }

    /**
     * @param onWordSelectedListener the onWordSelectedListener to set
     */
    public void setOnWordSelectedListener(OnWordSelectedListener onWordSelectedListener) {
        mOnWordSelectedListener = onWordSelectedListener;
    }

    public void showHint(Word word) {
        mGameState.mHint = word;
        final View v = getChildAtPosition((word.getRow() * mColumns) + word.getCol());
        animShake = AnimationUtils.loadAnimation(getContext(), R.anim.blink);
        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        zoomOut = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_out);
        ((TextView) v.findViewById(R.id.lbl_char)).startAnimation(animShake);
        ((TextView) v.findViewById(R.id.lbl_char)).startAnimation(zoomIn);
        ((TextView) v.findViewById(R.id.lbl_char)).startAnimation(zoomOut);
        ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.parseColor("#d50000"));

        view = v;

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {


                if (sp.getInt(getContext(), "THEME") == 0) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                } else if (sp.getInt(getContext(), "THEME") == 1) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);

                } else if (sp.getInt(getContext(), "THEME") == 3) {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                } else {

                    ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(Color.WHITE);
                }

            }
        }, 4000);

        // postInvalidate();
    }

    public void wordMaintain(Set<Word> words) {
        mGameState.foundWords = words;
        postInvalidate();
    }

    public void wordFound(Word word) {
        if (!mGameState.mFoundWords.contains(word)) {

            //To clear if word found
            listPos.clear();

            k = k + 1;
            if (k == 10) {

                k = 0;
            }

            final TextView tv = new TextView(getContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(params);
            //tv.setBackgroundResource(R.drawable.oval_button);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(word.getWord());
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextColor(Color.parseColor(colors[i]));
            tv.setTextSize(20);
            tv.setAllCaps(true);
            challenge_WS_GridFragment.lay_wordsearch.addView(tv);

            final Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setInterpolator(new AccelerateInterpolator());
            fadeOut.setFillAfter(true);
            fadeOut.setStartOffset(100);
            fadeOut.setDuration(1500);
            //tv.startAnimation(fadeOut);

            DisplayMetrics metrics = new DisplayMetrics();
            ((challenge) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int widthPixels = metrics.widthPixels;
            int heightPixels = metrics.heightPixels;
            float widthDpi = metrics.xdpi;
            float heightDpi = metrics.ydpi;
            float widthInches = widthPixels / widthDpi;
            float heightInches = heightPixels / heightDpi;
            float scaleFactor = metrics.density;
            float widthDp = widthPixels / scaleFactor;
            float heightDp = heightPixels / scaleFactor;

            float smallestWidth = Math.min(widthDp, heightDp);

            TranslateAnimation anims = new TranslateAnimation(touchPoint[0], 100, touchPoint[1], 100);
            //TranslateAnimation anims = new TranslateAnimation(550, 450, 850, 100);
            anims.setDuration(1500);
            anims.setFillAfter(true);
            //tv.startAnimation(anims);

            AnimationSet animation = new AnimationSet(true);
            animation.addAnimation(anims);
            animation.addAnimation(fadeOut);
            tv.setAnimation(animation);

            new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    challenge_WS_GridFragment.lay_wordsearch.removeView(tv);
                }
            }, 1000);


            mLastWordFound = word.getWord();
            sendAccessibilityEvent(AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED);
            mGameState.mFoundWords.add(word);

            if (mFoundCache == null) {
                mFoundCache = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas foundCanvas = new Canvas(mFoundCache);
            drawSelection(word, foundCanvas, mFoundPaint);
            postInvalidate();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {

        if (mGameState.mHint != null) {
            //drawSelection(mGameState.mHint, canvas, mHintPaint);
        }

        if (mGameState.foundWords.size() > 0) {

            for (Word mword : mGameState.foundWords) {

                mMtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mMtPaint.setColor(Color.parseColor("#8bc34a"));

                drawSelection(mword, canvas, mMtPaint);
            }
        }

        if (mFoundCache == null) {
            mFoundCache = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas foundCanvas = new Canvas(mFoundCache);
            for (Word word : mGameState.mFoundWords) {
                drawSelection(word, foundCanvas, mFoundPaint);
            }
        }

        canvas.drawBitmap(mFoundCache, 0f, 0f, mFoundPaint);

        if (mSelectionDirection != null && mSelectionSteps != null && mSelStartPosition != null) {
            drawCurrentSelection(canvas);
        } else if (!isInTouchMode() && mSelStartPosition != null && mFocusSelected) {
            float pad = mColumnWidth / 3.2f;
            RectF superRect = new RectF(-pad, -pad, pad, pad);
            canvas.save();
            canvas.translate(mSelStartRect.centerX(), mSelStartRect.centerY());
            // canvas.rotate(mSelectionDirection.getAngleDegree());
            canvas.drawRoundRect(superRect, mCornerRadius, mCornerRadius, mPaint);
            canvas.restore();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.GridView#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isInEditMode()) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
            mColumnWidth = (int) ((float) getMeasuredWidth() / (float) mColumns);
        } else {
            if (getResources().getDisplayMetrics().widthPixels > getResources().getDisplayMetrics().heightPixels) {
                super.onMeasure(heightMeasureSpec, heightMeasureSpec);
                setMeasuredDimension(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
            } else {
                super.onMeasure(widthMeasureSpec, widthMeasureSpec);
                setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(widthMeasureSpec));
            }
            mColumnWidth = (int) ((float) getMeasuredWidth() / (float) mColumns);
        }

        mCornerRadius = getMeasuredWidth() / 28.0f;

    }

    private void drawCurrentSelection(Canvas canvas) {
        float pad = mColumnWidth / 3.2f;
        RectF superRect = new RectF(-pad, -pad, pad, pad);
        float xDelta = mSelEndRect.centerX() - mSelStartRect.centerX();
        float yDelta = (mSelEndRect.centerY() - mSelStartRect.centerY()) * -1;
        double distance = Math.hypot(xDelta, yDelta);
        superRect.right += distance;

        canvas.save();
        canvas.translate(mSelStartRect.centerX(), mSelStartRect.centerY());
        canvas.rotate(mSelectionDirection.getAngleDegree());
        canvas.drawRoundRect(superRect, mCornerRadius, mCornerRadius, mPaint);
        //canvas.drawCircle(mSelStartRect.centerX(),mSelStartRect.centerX(),20,mPaint);
        canvas.restore();
    }

    private void drawSelection(Word word, Canvas canvas, Paint paint) {
        float angleStep = (float) Math.hypot(mColumnWidth, mColumnWidth);
        float pad = mColumnWidth / 3.2f;
        if (word.getWord() == null) return;
        String[] str = word.getWord().split("\\.");
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(s);
        }

        // float distance = (word.getDirection().isAngle() ? angleStep : mColumnWidth) * (word.getWord().length() - 1);

        float distance = (word.getDirection().isAngle() ? angleStep : mColumnWidth) * (str.length - 1);

        RectF superRect = new RectF(-pad, -pad, pad, pad);
        superRect.right += distance;

        View v = getChildAtPosition((word.getRow() * mColumns) + word.getCol());
        Rect viewRect = getViewRect(v);

        canvas.save();
        canvas.translate(viewRect.centerX(), viewRect.centerY());
        canvas.rotate(word.getDirection().getAngleDegree());
        canvas.drawRoundRect(superRect, mCornerRadius, mCornerRadius, paint);
        canvas.restore();
    }

    private int getInverseColor(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = Color.alpha(color);
        return Color.argb(alpha, 255 - red, 255 - green, 255 - blue);
    }

    private List<View> getSelectionViews() {
        if (mSelStartPosition == null || mSelectionSteps == null || mSelectionDirection == null) {
            return null;
        }

        List<View> views = new ArrayList<View>();
        for (Integer position : getPositionsOnPath(mSelectionDirection, mSelStartPosition, mSelectionSteps)) {

            if (!listPos.contains(position)) {
                listPos.add(position);
            }
        }

        for (Integer position : listPos) {

            views.add(getChildAtPosition(position));
        }

        return views;
    }

    private Rect getViewRect(View v) {
        Rect viewRect = new Rect();
        v.getDrawingRect(viewRect);
        viewRect.offset(v.getLeft(), ((ViewGroup) v.getParent()).getTop());
        return viewRect;
    }

    private void init() {
        setWillNotDraw(false);
        setOrientation(LinearLayout.VERTICAL);

        mDefaultColor = Color.WHITE;
        mSelectionColor = getInverseColor(mDefaultColor);

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        lp.weight = 1.0f;
        for (int i = 0; i < mColumns; i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < mColumns; j++) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.wordsearch_grid_cell, null);
                view.setFocusable(true);
                view.setOnFocusChangeListener(new OnFocusChangeListener() {
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus && mFocusSelected) {
                            Rect rect = getViewRect(v);
                            selectionChanged(rect.centerX(), rect.centerY());
                        }

                        if (mFocusSelected) {
                            List<View> selectedViews = getSelectionViews();
                            if (selectedViews == null) {
                                return;
                            }

                            for (View view : selectedViews) {
                                if (view.equals(v)) {
                                    return;
                                }
                            }
                        }


                        //((TextView) v.findViewById(R.id.lbl_char)).setTextColor(hasFocus ? 0xFF3f51b5 : mDefaultColor);

                        if (sp.getInt(getContext(), "THEME") == 0) {

                            ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(hasFocus ? 0xFF3f51b5 : Color.BLACK);
                        } else if (sp.getInt(getContext(), "THEME") == 1) {

                            ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(hasFocus ? 0xFF3f51b5 : Color.BLACK);

                        } else if (sp.getInt(getContext(), "THEME") == 3) {

                            ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(hasFocus ? 0xFF3f51b5 : Color.BLACK);
                        } else {

                            ((TextView) v.findViewById(R.id.lbl_char)).setTextColor(hasFocus ? 0xFF3f51b5 : Color.WHITE);
                        }

                    }
                });
                view.setOnKeyListener(this);

                row.addView(view, lp);
            }
            addView(row, lp);
        }

        mGameState = new WordsearchGameState();
        mHintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mHintPaint.setARGB(0xA0, 0x94, 0x47, 0x47);
        mHintPaint.setColor(Color.parseColor("#80d50000"));


        setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent e) {
                // Log.d("Stuff", "TOUCH DETECTED");

                Matrix inverse = new Matrix();
                matrix.invert(inverse);
                touchPoint = new float[]{e.getRawX(), e.getRawY()};
                inverse.mapPoints(touchPoint);

                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (view != null) {

                            ((TextView) view.findViewById(R.id.lbl_char)).clearAnimation();

                            if (sp.getInt(getContext(), "THEME") == 0) {

                                ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                            } else if (sp.getInt(getContext(), "THEME") == 1) {

                                ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);

                            } else if (sp.getInt(getContext(), "THEME") == 3) {

                                ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.BLACK);
                            } else {

                                ((TextView) view.findViewById(R.id.lbl_char)).setTextColor(Color.WHITE);
                            }
                        }

                        Random r = new Random();
                        //int random = r.nextInt((16) + 1);

                        i = i + 1;
                        if (i == 20) {

                            i = 0;
                        }

                        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                        //mPaint.setARGB(0xFF, 0x3f, 0x51, 0xb5);
                        mPaint.setColor(Color.parseColor(colors[i]));

                        mFoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                        //mFoundPaint.setARGB(0xA0, 0x3f, 0x51, 0xb5);
                        mFoundPaint.setColor(Color.parseColor(colors[i]));

                        break;

                    case MotionEvent.ACTION_MOVE:
                        selectionChanged(e.getX(), e.getY());

                        break;

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        clearSelection();
                        break;
                }
                return true;
            }
        });

    }

    private boolean isPointInsideView(float x, float y, View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        rect.offset(view.getLeft(), ((ViewGroup) view.getParent()).getTop());
        return rect.contains((int) x, (int) y);
    }

    private int pointToPosition(float x, float y) {
        for (int i = 0; i < mColumns * mColumns; i++) {
            if (isPointInsideView(x, y, getChildAtPosition(i))) {
                return i;
            }
        }
        return -1;
    }

    public interface OnWordSelectedListener {
        void onWordSelected(List<Integer> positions);
    }

    private static final class WordsearchGameState extends BaseSavedState {

        @SuppressWarnings("unused")
        public static final Creator<WordsearchGameState> CREATOR =
                new Creator<WordsearchGameState>() {
                    /*
                     * (non-Javadoc)
                     *
                     * @see
                     * android.os.Parcelable.Creator#createFromParcel(android
                     * .os.Parcel)
                     */
                    public WordsearchGameState createFromParcel(Parcel source) {
                        return new WordsearchGameState(source);
                    }/*
                     * (non-Javadoc)
                     *
                     * @see android.os.Parcelable.Creator#newArray(int)
                     */

                    public WordsearchGameState[] newArray(int size) {
                        return new WordsearchGameState[size];
                    }
                };
        private Set<Word> mFoundWords;
        private Set<Word> foundWords = new HashSet<>();
        private Word mHint;
        private Word mMaintain;

        private WordsearchGameState() {
            super(Parcel.obtain());
            mFoundWords = new HashSet<Word>();
        }

        private WordsearchGameState(Parcel in) {
            super(in);
            mFoundWords = new HashSet<Word>();
            Parcelable[] parcels = in.readParcelableArray(Word.class.getClassLoader());
            for (Parcelable parcel : parcels) {
                mFoundWords.add((Word) parcel);
            }
            mHint = in.readParcelable(Word.class.getClassLoader());
        }

        private WordsearchGameState(Parcelable parcelable) {
            super(parcelable);
        }

        /*
         * (non-Javadoc)
         *
         * @see android.view.AbsSavedState#writeToParcel(android.os.Parcel, int)
         */
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelableArray(mFoundWords.toArray(new Word[]{}), flags);
            dest.writeParcelable(mHint, flags);
        }

    }
}
