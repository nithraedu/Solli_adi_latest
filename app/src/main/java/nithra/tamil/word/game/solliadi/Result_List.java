package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.facebook.ads.NativeAdLayout;





import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;

public class Result_List extends AppCompatActivity {
TextView daily_result,weekly_result,monthly_result,share_result;
    LinearLayout adds;
    static SharedPreference sps=new SharedPreference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result__list);
        daily_result=(TextView)findViewById(R.id.daily_result);
        weekly_result=(TextView)findViewById(R.id.weekly_result);
        monthly_result=(TextView)findViewById(R.id.monthly_result);
        share_result=(TextView)findViewById(R.id.share_result);
        adds=(LinearLayout)findViewById(R.id.ads_lay);

        daily_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1",1);
                startActivity(i);
            }
        });
        weekly_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1",2);
                startActivity(i);
            }
        });
        monthly_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1",3);
                startActivity(i);
            }
        });
        share_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1",4);
                startActivity(i);
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if(keyCode==KeyEvent.KEYCODE_BACK) {

            finish();
            Intent i = new Intent(Result_List.this, Game_States.class);
            startActivity(i);

        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onResume() {
        super.onResume();

      /*  if (sps.getInt(Result_List.this, "addlodedd") == 1) {
            MainActivity.load_addFromMain(Result_List.this, adds);
        } else {
            if (Utils.isNetworkAvailable(Result_List.this)) {
                sps.putInt(Result_List.this, "addlodedd", 2);
                System.out.println("@IMG");
                final AdView adView = new AdView(Result_List.this);
                adView.setAdUnitId(getString(R.string.main_banner_ori));

                adView.setAdSize(AdSize.SMART_BANNER);
                AdRequest request = new AdRequest.Builder().build();
                adView.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        System.out.println("@@@loaded");
                        adds.removeAllViews();
                        adds.addView(adView);
                        adds.setVisibility(View.VISIBLE);
                        super.onAdLoaded();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        System.out.println("@@@NOt loaded");
                        super.onAdFailedToLoad(i);
                    }
                });
                adView.loadAd(request);

            }
        }*/
      NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sps.getInt(Result_List.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            adds.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);

        } else {
            if (Utils.isNetworkAvailable(Result_List.this)) {
                fb_native(Result_List.this, native_banner_ad_container);
              /*  if (sps.getInt(Result_List.this, "native_banner_ads") == 1) {
                    New_Main_Gamelist.inflateAd(Result_List.this, native_banner_ad_container);
                } else {
                    fb_native(Result_List.this, native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }
    }
}
