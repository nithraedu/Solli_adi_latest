package nithra.tamil.word.game.solliadi.adutils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;

import nithra.tamil.word.game.solliadi.R;

public class Ad_NativieUtils {

    private static MaxNativeAdLoader nativeAdLoader;
    private static ViewGroup nativeAdContainerView;
    private static MaxAd loadedNativeAd;
    private static Context c;

    public static void load_add_facebook(Context ctx, String ad_id, LinearLayout linearLayout) {
        c = ctx;
        nativeAdLoader = new MaxNativeAdLoader(ad_id, ctx);
        // nativeAdLoader.setRevenueListener((MaxAdRevenueListener) ctx);

        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(@Nullable MaxNativeAdView maxNativeAdView, MaxAd maxAd) {
                super.onNativeAdLoaded(maxNativeAdView, maxAd);
                System.out.println("--load--" + maxAd);
                // Clean up any pre-existing native ad to prevent memory leaks.
                if (loadedNativeAd != null) {
                    nativeAdLoader.destroy(loadedNativeAd);
                }

                // Save ad for cleanup.
                loadedNativeAd = maxAd;

                linearLayout.removeAllViews();
                linearLayout.addView(maxNativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(String s, MaxError maxError) {
                super.onNativeAdLoadFailed(s, maxError);
                System.out.println("--Faild--" + maxError);
            }

            @Override
            public void onNativeAdClicked(MaxAd maxAd) {
                super.onNativeAdClicked(maxAd);
            }
        });
        nativeAdLoader.loadAd(createNativeAdView());

    }

    public static MaxNativeAdView createNativeAdView() {
        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.aaaaaa)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setAdvertiserTextViewId(R.id.advertiser_textView)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(R.id.ad_options_view)
                .setCallToActionButtonId(R.id.cta_button)
                .build();
        return new MaxNativeAdView(binder, c);
    }
}
