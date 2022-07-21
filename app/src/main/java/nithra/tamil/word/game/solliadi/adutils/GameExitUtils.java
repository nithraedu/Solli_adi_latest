package nithra.tamil.word.game.solliadi.adutils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdkUtils;

import nithra.tamil.word.game.solliadi.R;

public class GameExitUtils {


/*
    public static void rectbanner(Context ctx, String ad_id, LinearLayout linearLayout) {
        MaxAdView adView = new MaxAdView(ad_id, ctx);


        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = ctx.getResources().getDimensionPixelSize(R.dimen.rect_banner_height);

        adView.setLayoutParams(new FrameLayout.LayoutParams(width, heightPx));


        linearLayout.addView(adView);

        // Load the ad
        adView.loadAd();

        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                System.out.println("print error" + error);
            }
        });

    }
*/

    public static MaxAdView rectBannerAdView(Context ctx, String ad_id) {

        MaxAdView adView = new MaxAdView(ad_id, ctx);


        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = ctx.getResources().getDimensionPixelSize(R.dimen.rect_banner_height);

        adView.setLayoutParams(new FrameLayout.LayoutParams(width, heightPx));

        // Load the ad
        adView.loadAd();

        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                System.out.println("print error1" + error);
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                System.out.println("print error" + error);
            }
        });

        return adView;
    }


}
