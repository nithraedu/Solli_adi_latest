package nithra.tamil.word.game.solliadi.billing;

import com.android.billingclient.api.Purchase;

import java.util.List;

public interface BillingUpdateListener {
    void onPurchasesUpdated(List<Purchase> purchases);
}
