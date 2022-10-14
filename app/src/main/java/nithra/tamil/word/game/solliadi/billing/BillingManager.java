package nithra.tamil.word.game.solliadi.billing;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryPurchasesParams;

import java.util.ArrayList;
import java.util.List;

public class BillingManager implements PurchasesUpdatedListener {

    private final Activity mActivity;
    private final BillingUpdateListener mBillingUpdatesListener;
    private BillingClient mBillingClient;
    private boolean mIsServiceConnected;
    public static final String SKU_ID = "removeads";
    private final List<Purchase> mPurchases = new ArrayList<>();

    public BillingManager(Activity activity, final BillingUpdateListener updatesListener) {

        mActivity = activity;
        mBillingUpdatesListener = updatesListener;
        mBillingClient = BillingClient.newBuilder(activity).setListener(this).enablePendingPurchases().build();

        // Start setup. This is asynchronous and the specified listener will be called
        // once setup completes.
        // It also starts to report all the new purchases through onPurchasesUpdated() callback.
        startServiceConnection(new Runnable() {
            @Override
            public void run() {
                queryPurchases();
            }
        });
    }

    public void queryPurchases() {
        Runnable queryToExecute = new Runnable() {
            @Override
            public void run() {

                mBillingClient.queryPurchasesAsync(
                        QueryPurchasesParams.newBuilder()
                                .setProductType(BillingClient.ProductType.INAPP)
                                .build(),
                        new PurchasesResponseListener() {
                            public void onQueryPurchasesResponse(BillingResult billingResult, List purchases) {
                                // check billingResult
                                // process returned purchase list, e.g. display the plans user owns
                                onQueryPurchasesFinished(billingResult,purchases);
                            }
                        }
                );

            }
        };

        if (mIsServiceConnected){
            queryToExecute.run();
        }else{
        startServiceConnection(queryToExecute);
        }
    }

    private void onQueryPurchasesFinished(BillingResult result, List purchases) {
        // Have we been disposed of in the meantime? If so, or bad result code, then quit
        if (mBillingClient == null || result.getResponseCode() != BillingClient.BillingResponseCode.OK) {
            Log.w("Billing", "Billing client was null or result code (" + result.getResponseCode()
                    + ") was bad - quitting");
            return;
        }

        Log.d("Billing", "Query inventory was successful.");

        // Update the UI and purchases inventory with new list of purchases
        this.onPurchasesUpdated(BillingResult.newBuilder().build(), purchases);
        //mBillingUpdatesListener.onPurchasesUpdated(purchases);
    }

    public void startServiceConnection(final Runnable executeOnSuccess) {

        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    mIsServiceConnected = true;
                    if (executeOnSuccess != null) {
                        executeOnSuccess.run();
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                mIsServiceConnected = false;
            }
        });
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            for (Purchase purchase : list) {
                handlePurchase(purchase);
            }
            mBillingUpdatesListener.onPurchasesUpdated(mPurchases);
            Log.w("Billing", "purchasesupdate");
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.i("Billing", "onPurchasesUpdated() - user cancelled the purchase flow - skipping");
        } else {
            Log.w("Billing", "onPurchasesUpdated() got unknown resultCode: " + billingResult.getResponseCode());
        }
    }

    private void handlePurchase(Purchase purchase) {
        Log.w("Billing", "insidehandlepurchase");
        if (purchase.getProducts().get(0).equals(SKU_ID) && purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build();
                mBillingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
                    @Override
                    public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Toast.makeText(mActivity, "Purchase acknowledged successful...", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        }

        mPurchases.add(purchase);

    }
    public void billingclientDestory(){
        mBillingClient.endConnection();
    }
}
