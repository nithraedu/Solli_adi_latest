package nithra.tamil.word.game.solliadi.biling_code;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;

import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.billing.BillingManager;
import nithra.tamil.word.game.solliadi.billing.BillingProvider;

import static nithra.tamil.word.game.solliadi.billing.BillingManager.BILLING_MANAGER_NOT_INITIALIZED;


public class Billing_Activity extends AppCompatActivity implements BillingProvider {


    private BillingManager mBillingManager;
    private MainViewController mViewController;

    public AcquireFragment mAcquireFragment;
    private static final String DIALOG_TAG = "dialog";
    SharedPreference sharedPreference = new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Utils.Full_Screen(getWindow());
        /*mViewController = new MainViewController(frontpage.this);*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mViewController = new MainViewController(Billing_Activity.this);
        if (savedInstanceState != null) {
            mAcquireFragment = (AcquireFragment) getSupportFragmentManager()
                    .findFragmentByTag(DIALOG_TAG);


        }
        mBillingManager = new BillingManager(this, mViewController.getUpdateListener());


        onPurchaseButtonClicked(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //onresum

        if (mBillingManager != null
                && mBillingManager.getBillingClientResponseCode() == BillingClient.BillingResponseCode.OK) {
            mBillingManager.queryPurchases();
        }
    }

    public void onPurchaseButtonClicked(int val) {

        Log.d("onPurchaseButtonClicked", "Purchase button clicked.");
        //   sp.putInt(MainActivity.this, "ad_click_val", val);
        if (mAcquireFragment == null) {
            mAcquireFragment = new AcquireFragment();
        }

        if (!isAcquireFragmentShown()) {
            FragmentManager fm = getFragmentManager();

            mAcquireFragment.show(getSupportFragmentManager(), DIALOG_TAG);
            fm.executePendingTransactions();

           /* mAcquireFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Toast.makeText(Billing_Activity.this, "dismiss listener", Toast.LENGTH_SHORT).show();
                }
            });*/

            mAcquireFragment.setDissmissListener(new AcquireFragment.OnDismissListener() {
                @Override
                public void onDismiss(AcquireFragment myDialogFragment) {
                    finish();
                    // Toast.makeText(Billing_Activity.this, "dissmiss listener", Toast.LENGTH_SHORT).show();
                }
            });

            if (mBillingManager != null
                    && mBillingManager.getBillingClientResponseCode()
                    > BILLING_MANAGER_NOT_INITIALIZED) {
                mAcquireFragment.onManagerReady(this);

            }
        }

    }


    public boolean isAcquireFragmentShown() {
        return mAcquireFragment != null && mAcquireFragment.isVisible();
    }

    public void showRefreshedUi() {
        updateUi();
        if (mAcquireFragment != null) {
            mAcquireFragment.refreshUI();
        }
    }

    /**
     * Update UI to reflect model
     */
    @UiThread
    private void updateUi() {
//        Utils.toast_center(Billing_Activity.this, "add_remove : " +
//                sharedPreference.getBoolean1(Billing_Activity.this, "add_remove"));
        //sharedPreference.putBoolean(Billing_Activity.this, "add_remove", true);
        if(sharedPreference.getInt(Billing_Activity.this,"purchase_ads")==1)
        {
            //mAcquireFragment.dismiss();

            finish();
            sharedPreference.putInt(Billing_Activity.this,"purchase_ads_completed",1);
        }

  /*      if (sharedPreference.getBoolean(Billing_Activity.this, "add_remove")) {
            //mAcquireFragment.dismiss();
            congratulations_dialog();
        }*/
    }

    public void congratulations_dialog() {
        final Dialog no_datefun = new Dialog(Billing_Activity.this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        no_datefun.setContentView(R.layout.sku_details_row);
        no_datefun.setCancelable(false);
        TextView title = no_datefun.findViewById(R.id.title);
        TextView price = no_datefun.findViewById(R.id.price);
        TextView description = no_datefun.findViewById(R.id.description);
        TextView state_button = no_datefun.findViewById(R.id.state_button);
        description.setText("Your purchase is successfull!!  Now you got ad free version of this App!!");
        state_button.setText("OK");
        title.setText("Congratulations! ");
        price.setVisibility(View.GONE);

        state_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // sharedPreference.putBoolean(Billing_Activity.this, "purchesed", true);
                sharedPreference.putInt(Billing_Activity.this, "purchase_ads", 1);
                no_datefun.dismiss();
                finish();
            }
        });
        no_datefun.show();
    }

    @Override
    public BillingManager getBillingManager() {
        return mBillingManager;
    }

    @Override
    public boolean isPremiumPurchased() {
        return mViewController.isPremiumPurchased();
    }

    /*  @Override
      public boolean isGoldMonthlySubscribed() {
          return mViewController.isGoldMonthlySubscribed();
      }

     *//* @Override
    public boolean isGoldYearlySubscribed() {
        return mViewController.isGoldYearlySubscribed();
    }*//*

    @Override
    public boolean isTankFull() {
        return mViewController.isTankFull();
    }
*/
    public void onBillingManagerSetupFinished() {
        if (mAcquireFragment != null) {
            mAcquireFragment.onManagerReady(this);
        }
    }

    /**
     * Show an alert dialog to the user
     *
     * @param messageId     String id to display inside the alert dialog
     * @param optionalParam Optional attribute for the string
     */
    @UiThread
    public void alert(@StringRes int messageId, @Nullable Object optionalParam) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new RuntimeException("Dialog could be shown only from the main thread");
        }

        AlertDialog.Builder bld = new AlertDialog.Builder(Billing_Activity.this);
        bld.setNeutralButton("OK", null);

        if (optionalParam == null) {
            bld.setMessage(messageId);
        } else {
            bld.setMessage(getResources().getString(messageId, optionalParam));
        }
        if (!isFinishing()) {
            bld.create().show();
        } else {
            Utils.toast_center(Billing_Activity.this, "" + R.string.alert_error_consuming);
        }
    }
}
