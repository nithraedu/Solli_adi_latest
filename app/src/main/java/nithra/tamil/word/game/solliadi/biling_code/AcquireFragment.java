/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nithra.tamil.word.game.solliadi.biling_code;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClient.SkuType;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.biling_code.row.SkusAdapter1;
import nithra.tamil.word.game.solliadi.biling_code.row.UiDelegatesFactory;
import nithra.tamil.word.game.solliadi.biling_code.row.UiManager;
import nithra.tamil.word.game.solliadi.billing.BillingProvider;


/**
 * Displays a screen with various in-app purchase and subscription options
 */
public class AcquireFragment extends DialogFragment implements DialogInterface.OnDismissListener {
    private static final String TAG = "AcquireFragment";

    private RecyclerView mRecyclerView;
    private SkusAdapter1 mAdapter;
    private View mLoadingView;
    private TextView mErrorTextView;
    private BillingProvider mBillingProvider;

    SharedPreference sharedPreference;
    Context context;

    int colors;
    /*@Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }*/

    //Create interface in your DialogFragment (or a new file)
    public interface OnDismissListener {
        void onDismiss(AcquireFragment myDialogFragment);
    }

    //create Pointer and setter to it
    private OnDismissListener onDismissListener;

    public void setDissmissListener(OnDismissListener dissmissListener) {
        this.onDismissListener = dissmissListener;
    }

    //Call it on the dialogFragment onDissmiss
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (onDismissListener != null) {
            onDismissListener.onDismiss(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.acquire_fragment, container, false);
        mErrorTextView = root.findViewById(R.id.error_textview);
        mRecyclerView = root.findViewById(R.id.list);
        mLoadingView = root.findViewById(R.id.screen_wait);
        if (mBillingProvider != null) {
            handleManagerAndUiReady();
        }

        return root;
    }

    /**
     * Refreshes this fragment's UI
     */
    public void refreshUI() {
        Log.d(TAG, "Looks like purchases list might have been updated - refreshing the UI");
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Notifies the fragment that billing manager is ready and provides a BillingProviders
     * instance to access it
     */
    public void onManagerReady(BillingProvider billingProvider) {
        mBillingProvider = billingProvider;
        if (mRecyclerView != null) {
            handleManagerAndUiReady();
        }
    }

    /**
     * Enables or disables "please wait" screen.
     */
    private void setWaitScreen(boolean set) {
        mRecyclerView.setVisibility(set ? View.GONE : View.VISIBLE);
        mLoadingView.setVisibility(set ? View.VISIBLE : View.GONE);
    }

    /**
     * Executes query for SKU details at the background thread
     */
    private void handleManagerAndUiReady() {
        // If Billing Manager was successfully initialized - start querying for SKUs
        setWaitScreen(true);

        sharedPreference = new SharedPreference();


        querySkuDetails();
    }

    private void displayAnErrorIfNeeded() {
        if (getActivity() == null || getActivity().isFinishing()) {
            Log.i(TAG, "No need to show an error - activity is finishing already");
            return;
        }

        mLoadingView.setVisibility(View.GONE);
        mErrorTextView.setVisibility(View.VISIBLE);
        int billingResponseCode = mBillingProvider.getBillingManager()
                .getBillingClientResponseCode();

        switch (billingResponseCode) {
            case BillingClient.BillingResponseCode.OK:
                // If manager was connected successfully, then show no SKUs error
                mErrorTextView.setText(getText(R.string.error_no_skus));
                break;
            case BillingClient.BillingResponseCode.BILLING_UNAVAILABLE:
                mErrorTextView.setText(getText(R.string.error_billing_unavailable));
                break;
            default:
                mErrorTextView.setText(getText(R.string.error_billing_default));
        }

    }

    /**
     * Queries for in-app and subscriptions SKU details and updates an adapter with new data
     */
    private void querySkuDetails() {
        long startTime = System.currentTimeMillis();

        Log.d(TAG, "querySkuDetails() got subscriptions and inApp SKU details lists for: "
                + (System.currentTimeMillis() - startTime) + "ms");

        if (getActivity() != null && !getActivity().isFinishing()) {
            final List<SkuDetails> dataList = new ArrayList<>();
            mAdapter = new SkusAdapter1(getActivity(), mBillingProvider);
          /*  final UiManager uiManager = createUiManager(val,mAdapter, mBillingProvider);
            mAdapter.setUiManager(uiManager);*/
            // Filling the list with all the data to render subscription rows
            /*  */
           /* UiDelegatesFactory    mDelegatesFactory = new UiDelegatesFactory(val,mBillingProvider);
            List<String> subscriptionsSkus = mDelegatesFactory
                    .getSkuList(SkuType.SUBS);
            addSkuRows(color,dataList, subscriptionsSkus, SkuType.SUBS, new Runnable() {
                @Override
                public void run() {
                    // Once we added all the subscription items, fill the in-app items rows below
                   *//* UiDelegatesFactory    mDelegatesFactory = new UiDelegatesFactory(val,mBillingProvider);
                    List<String> inAppSkus = mDelegatesFactory
                            .getSkuList(SkuType.INAPP);
                    addSkuRows(color,dataList, inAppSkus, SkuType.INAPP, null);*//*
                }
            });*/
            /* sharedPreference = new SharedPreference();*/
            /*   if (sharedPreference.getInt(getActivity(),"ad_click_val")==0){*/
            UiDelegatesFactory mDelegatesFactory = new UiDelegatesFactory(mBillingProvider);
            List<String> inAppSkus = mDelegatesFactory
                    .getSkuList(SkuType.INAPP);
            addSkuRows(dataList, inAppSkus, SkuType.INAPP, null);
           /* }else {
                UiDelegatesFactory    mDelegatesFactory = new UiDelegatesFactory(val,mBillingProvider);
                List<String> inAppSkus = mDelegatesFactory
                        .getSkuList(SkuType.SUBS);
                addSkuRows(dataList, inAppSkus, SkuType.SUBS, null);
            }*/


        }
    }

    private void addSkuRows(final List<SkuDetails> inList, List<String> skusList,
                            final @SkuType String billingType, final Runnable executeWhenFinished) {

        mBillingProvider.getBillingManager().querySkuDetailsAsync(billingType, skusList,
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> skuDetailsList) {
                        Log.d("Billing", "onSkuDetailsResponse - List Size: " + skuDetailsList.size());

                        if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                            Log.w(TAG, "Unsuccessful query for type: " + billingType
                                    + ". Error code: " + billingResult.getResponseCode());
                        } else if (skuDetailsList != null
                                && skuDetailsList.size() > 0) {
                            // If we successfully got SKUs, add a header in front of the row
                            @StringRes int stringRes = (billingType == SkuType.INAPP)
                                    ? R.string.header_inapp : R.string.header_subscriptions;
                            /*inList.add(new SkuRowData(getString(stringRes)));*/
                            // Then fill all the other rows
                            for (SkuDetails details : skuDetailsList) {
                                Log.i(TAG, "Adding sku: " + details);
                                //inList.add(new SkuDetails(details, SkusAdapter.TYPE_NORMAL, billingType));
                                inList.add(details);
                            }

                            if (inList.size() == 0) {
                                displayAnErrorIfNeeded();
                            } else {
                                if (mRecyclerView.getAdapter() == null) {
                                    mRecyclerView.setAdapter(mAdapter);
                                   /*  Resources res = getContext().getResources();
                                   mRecyclerView.addItemDecoration(new CardsWithHeadersDecoration(
                                            mAdapter, (int) res.getDimension(R.dimen.lrtbp_5),
                                            (int) res.getDimension(R.dimen.wh_1)));*/
                                    mRecyclerView.setLayoutManager(
                                            new LinearLayoutManager(getContext()));
                                }

                                mAdapter.updateData(inList);
                                setWaitScreen(false);
                            }

                        }

                        if (executeWhenFinished != null) {
                            executeWhenFinished.run();
                        }
                    }

                });
    }

/*    protected UiManager createUiManager(int val, SkusAdapter adapter, BillingProvider provider) {
        return new UiManager(val, adapter, provider);
    }*/
}