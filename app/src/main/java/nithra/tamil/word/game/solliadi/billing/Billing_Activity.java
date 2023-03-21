package nithra.tamil.word.game.solliadi.billing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;


public class Billing_Activity extends AppCompatActivity implements PurchasesUpdatedListener {

    private static final String TAG = "Billing";
    SharedPreference sharedPreference = new SharedPreference();
    List<ProductDetails> productDetailsList;
    Handler handler;
    Activity activity;
    RecyclerView recyclerView;
    MyListAdapter adapter;
    ProgressBar mLoadingView;
    private BillingClient billingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        productDetailsList = new ArrayList<>();
        activity = Billing_Activity.this;

        initialDialog();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @UiThread
    private void updateUi() {
        if (sharedPreference.getBoolean(Billing_Activity.this, "add_remove")) {
            congratulations_dialog();
        }
    }

    public void initialDialog() {
        final Dialog initialloading = new Dialog(Billing_Activity.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        initialloading.setContentView(R.layout.acquire_fragment);
        initialloading.setCanceledOnTouchOutside(false);

        mLoadingView = initialloading.findViewById(R.id.screen_wait);
        recyclerView = initialloading.findViewById(R.id.list);
        mLoadingView.setVisibility(View.VISIBLE);

        initialBilling();
        establishConnection();

        initialloading.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });

        initialloading.show();

    }

    public void initialBilling() {
        billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener(
                        (billingResult, list) -> {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                                for (Purchase purchase : list) {
                                    handlePurchase(purchase);
                                }
                            }
                        }
                ).build();
    }

    void establishConnection() {

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    showProducts();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                establishConnection();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    void showProducts() {

        ImmutableList<QueryProductDetailsParams.Product> productList = ImmutableList.of(
                //Product 1
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(BillingManager.SKU_ID)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
        );

        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();

        billingClient.queryProductDetailsAsync(
                params,
                (billingResult, prodDetailsList) -> {
                    // Process the result
                    productDetailsList.clear();
                    handler.postDelayed(() -> {
                        //loadProducts.setVisibility(View.INVISIBLE);
                        productDetailsList.addAll(prodDetailsList);
                        Log.d(TAG, productDetailsList.size() + " number of products");
                        mLoadingView.setVisibility(View.GONE);
                        adapter = new MyListAdapter(productDetailsList);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Billing_Activity.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(adapter);
                    }, 2000);

                }
        );

    }

    void handlePurchase(Purchase purchases) {

        if (!purchases.isAcknowledged()) {
            billingClient.acknowledgePurchase(AcknowledgePurchaseParams
                    .newBuilder()
                    .setPurchaseToken(purchases.getPurchaseToken())
                    .build(), billingResult -> {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //Setting setIsRemoveAd to true
                    // true - No ads
                    // false - showing ads.

                    Log.d(TAG, "Purchased Successfully");
                    sharedPreference.putBoolean(Billing_Activity.this, "add_remove", true);
                    updateUi();
                    //  goBack();
                }
            });
            Log.d(TAG, "Purchase Token: " + purchases.getPurchaseToken());
            Log.d(TAG, "Purchase Time: " + purchases.getPurchaseTime());
            Log.d(TAG, "Purchase OrderID: " + purchases.getOrderId());
            sharedPreference.putBoolean(Billing_Activity.this, "add_remove", true);
            updateUi();
        }
    }

    void launchPurchaseFlow(ProductDetails productDetails) {
        ImmutableList<BillingFlowParams.ProductDetailsParams> productDetailsParamsList =
                ImmutableList.of(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                                .setProductDetails(productDetails)
                                .build()
                );
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build();

        billingClient.launchBillingFlow(activity, billingFlowParams);
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {

        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            for (Purchase purchase : list) {
                handlePurchase(purchase);
            }
            //mBillingUpdatesListener.onPurchasesUpdated(mPurchases);
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.i(TAG, "onPurchasesUpdated() - user cancelled the purchase flow - skipping");
        } else {
            Log.w(TAG, "onPurchasesUpdated() got unknown resultCode: " + billingResult.getResponseCode());
        }

    }

    public void congratulations_dialog() {
        final Dialog no_datefun = new Dialog(Billing_Activity.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        no_datefun.setContentView(R.layout.permission_dialog_layout);
        no_datefun.setCancelable(false);

        TextView ok = no_datefun.findViewById(R.id.permission_ok);
        TextView txt = no_datefun.findViewById(R.id.txt);
        TextView title = no_datefun.findViewById(R.id.txtfd);

        ok.setBackgroundColor(getResources().getColor(R.color.green));
        ok.setTextColor(getResources().getColor(R.color.white));

        txt.setText("Your purchase is successful!!  Now you got ads free version of this App!!");
        ok.setText("OK");
        title.setText("Congratulations! ");

        ok.setOnClickListener(v -> {
            if (!isFinishing()) {
                no_datefun.dismiss();
            }
            finish();
        });

        if (!isFinishing()) {
            no_datefun.show();
        }


    }

    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
        List<ProductDetails> listdata;

        // RecyclerView recyclerView;
        public MyListAdapter(List<ProductDetails> listdata) {
            this.listdata = listdata;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.sku_details_row, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final ProductDetails myListData = listdata.get(position);
            holder.headd.setText("" + myListData.getProductType());
            holder.title.setText("" + myListData.getTitle());
            holder.price.setText("" + myListData.getOneTimePurchaseOfferDetails().getFormattedPrice());
            holder.description.setText("" + myListData.getDescription());
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchPurchaseFlow(productDetailsList.get(position));
                    //Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
                }
            });
        }


        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView headd, title, price, description;
            public Button button;

            public ViewHolder(View itemView) {
                super(itemView);
                headd = itemView.findViewById(R.id.headd);
                title = itemView.findViewById(R.id.title);
                price = itemView.findViewById(R.id.price);
                description = itemView.findViewById(R.id.description);
                button = itemView.findViewById(R.id.state_button);

            }
        }
    }

}
