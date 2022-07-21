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


import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;

import java.util.List;

import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.biling_code.row.GasDelegate;
import nithra.tamil.word.game.solliadi.billing.BillingManager;


/**
 * Handles control logic of the BaseGamePlayActivity
 */
public class MainViewController {
    private static final String TAG = "MainViewController";


    // How many units (1/4 tank is our unit) fill in the tank.
    private static final int TANK_MAX = 4;

    private final UpdateListener mUpdateListener;
    private Billing_Activity mActivity;

    // Tracks if we currently own subscriptions SKUs
    private boolean mGoldMonthly;
    private boolean mGoldYearly;

    // Tracks if we currently own a premium car
    private boolean mIsPremium;

    // Current amount of gas in tank, in units
    private int mTank;

    public MainViewController(Billing_Activity activity) {
        mUpdateListener = new UpdateListener();
        mActivity = activity;

    }


    public UpdateListener getUpdateListener() {
        return mUpdateListener;
    }

    public boolean isTankEmpty() {
        return mTank <= 0;
    }

    public boolean isTankFull() {
        return mTank >= TANK_MAX;
    }

    public boolean isPremiumPurchased() {
        return mIsPremium;
    }

    public boolean isGoldMonthlySubscribed() {
        return mGoldMonthly;
    }

    public boolean isGoldYearlySubscribed() {
        return mGoldYearly;
    }


    /**
     * Handler to billing updates
     */
    private class UpdateListener implements BillingManager.BillingUpdatesListener {
        @Override
        public void onBillingClientSetupFinished() {
            mActivity.onBillingManagerSetupFinished();
        }

        @Override
        public void onConsumeFinished(String token,int result) {
            // Log.d(TAG, "Consumption finished. Purchase token: " + token + ", result: " + result);
            System.out.println("Consumption finished. Purchase token: " + token + ", result: " + result);

            // Note: We know this is the SKU_GAS, because it's the only one we consume, so we don't
            // check if token corresponding to the expected sku was consumed.
            // If you have more than one sku, you probably need to validate that the token matches
            // the SKU you expect.
            // It could be done by maintaining a map (updating it every time you call consumeAsync)
            // of all tokens into SKUs which were scheduled to be consumed and then looking through
            // it here to check which SKU corresponds to a consumed token.
            if (result == BillingClient.BillingResponseCode.OK) {
                // Successfully consumed, so we apply the effects of the item in our
                // game world's logic, which in our case means filling the gas tank a bit
                // Log.d(TAG, "Consumption successful. Provisioning.Done");

               /* System.out.println("Consumption successful. Provisioning.Done");
                SharedPreference sharedPreference = new SharedPreference();
                sharedPreference.putInt(mActivity,"pur_type",2);
                saveData(true);*/
            } else {
                // Log.d(TAG, "Consumption successful. Provisioning." + result);
              /*  System.out.println("Consumption successful. Provisioning." + result);
                saveData(false);
                mActivity.alert(R.string.alert_error_consuming, result);*/
            }

//                 mActivity.showRefreshedUi();
            Log.d(TAG, "End consumption flow.");
        }

        @Override
        public void onPurchasesUpdated(List<Purchase> purchaseList) {


            Boolean vall = false;


            for (Purchase purchase : purchaseList) {
                switch (purchase.getSku()) {

                    case GasDelegate.SKU_ID:
                        // Log.d(TAG, "We have gas. Consuming it.");
                        vall = true;
                        mIsPremium = true;
                        // We should consume the purchase and fill up the tank once it was consumed
                        //     mActivity.getBillingManager().consumeAsync(purchase.getPurchaseToken());
                        SharedPreference sharedPreference = new SharedPreference();
                       // sharedPreference.putInt(mActivity,"pur_type",2);
                        sharedPreference.putInt(mActivity,"pur_type",2);


                        break;
                   /* case GoldMonthlyDelegate.SKU_ID:
                        mGoldMonthly = false;
                        mGoldYearly = false;

                        SharedPreference sharedPreference1 = new SharedPreference();
                        sharedPreference1.putInt(mActivity,"pur_type",1);
                        mGoldMonthly = true;
                        vall = true;

                        break;*/

                }
            }
            saveData(vall);
            mActivity.showRefreshedUi();

        }
    }

    /**
     * Save current tank level to disc
     * <p>
     * Note: In a real application, we recommend you save data in a secure way to
     * prevent tampering.
     * For simplicity in this sample, we simply store the data using a
     * SharedPreferences.
     */
    public  void saveData(boolean vall) {
        SharedPreference sharedPreference = new SharedPreference();
        sharedPreference.putBoolean(mActivity, "purchase", vall);

    }

}
