package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nit_app.CodetoTamilUtil;


public class ST_Activity1 extends Activity {
    SharedPreference sharedPreference;
    String str_title = "";
    WebView content_view;
    Context context;


    SQLiteDatabase myDB;
    String tablenew = "noti_cal";
    String title, message, msgType, date, time;

    SharedPreference spa = new SharedPreference();

    LinearLayout adds, ads_lay;

    AppCompatImageView noti_cancel;
    FloatingActionButton share_but;
    TextView sticky;
    com.facebook.ads.InterstitialAd interstitialAd_notid;

    public static void share_txt(Context context, String str) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,
                "சொல்லிஅடி");
        i.putExtra(
                Intent.EXTRA_TEXT,
                str + "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                        "விளையாட இங்கே கிளிக் செய்யவும் \n https://goo.gl/6hFhIy");
        context.startActivity(Intent.createChooser(i, "Share via"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.st_lay);
        System.out.println("#######St Activity1");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*getSupportActionBar().hide();*/
        sharedPreference = new SharedPreference();
        myDB = openOrCreateDatabase("myDB", 0, null);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + tablenew
                + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR);");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS share_noti (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,sahre_msg VARCHAR,date VARCHAR,time VARCHAR);");


      /*  if((spa.getInt(ST_Activity1.this,"loadins_ads")==0)){
            spa.putInt(ST_Activity1.this, "loadins_ads", 1);

        }else if (spa.getInt(ST_Activity1.this,"loadins_ads")==1){
            spa.putInt(ST_Activity1.this, "loadins_ads", 0);

        }*/
        if (spa.getInt(ST_Activity1.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {

            /*AdRequest notadRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(notadRequest);*/
        }

        System.out.println("##########ST Activity1");


        content_view = (WebView) findViewById(R.id.noti_web);
        share_but = (FloatingActionButton) findViewById(R.id.share_but);
        sticky = (TextView) findViewById(R.id.sticky);
        //ImageView backdrop=(ImageView) home_about_dialog.findViewById(R.id.backdrop);
        noti_cancel = (AppCompatImageView) findViewById(R.id.noti_cancel);
        ads_lay = (LinearLayout) findViewById(R.id.ads_lay);

     /*   adds=(LinearLayout)findViewById(R.id.ads_lay);
        content_view = (WebView) findViewById(R.id.web);
        noti_cancel =(TextView)findViewById(R.id.noti_cancel);*/
        /*Cursor c = myDB.rawQuery("select * from " + tablenew + " where id =" + sharedPreference.getInt(getApplicationContext(), "Noti_id") + " ", null);
        c.moveToFirst();*/

      /*  ImageView backdrop = (ImageView)findViewById(R.id.backdrop);

        Glide
                .with(ST_Activity1.this)
                .load(sharedPreference.getString(getApplicationContext(), "imgURL"+sharedPreference.getInt(getApplicationContext(), "Noti_id")))
                .centerCrop()
                .placeholder(R.drawable.logo2)
                .error(R.drawable.logo2)
                .crossFade()
                .into(backdrop);*/

        Bundle extras;
        extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("bm");
            message = extras.getString("message");
            msgType = extras.getString("type");
            date = extras.getString("str_date");
            time = extras.getString("str_time");
            str_title = title;
        }

        sticky = (TextView) findViewById(R.id.sticky);
        sticky.setText(str_title);

        WebSettings ws = content_view.getSettings();
        ws.setJavaScriptEnabled(true);

        String bodyFont = "<style> body { font-size:20px; } table { font-size:20px; <font face='bamini' > }</style>"
                + "<style> @font-face { font-family:'bamini'; src: url('file:///android_asset/baamini.ttf') } </style>";

        String summary = "<!DOCTYPE html> <html><head>" + bodyFont + " </head> <body >"
                + "<br><br><br>" + message + "</body></html>";

        content_view.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);

        FloatingActionButton share_but = (FloatingActionButton) findViewById(R.id.share_but);

        share_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = Html.fromHtml(message).toString();
                result = sharedPreference.getString(ST_Activity1.this, "view_web_tit") + "\n" + CodetoTamilUtil.convertToTamil(CodetoTamilUtil.BAMINI, result);
                //share_txt(ST_Activity1.this, "" + result);
                Share_content share_content = new Share_content();
                share_content.Share_content(ST_Activity1.this, result);
            }
        });

        content_view.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);

                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse(url));
                startActivity(i);

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                Utils.mProgress(ST_Activity1.this, "Loading Please Wait", true).show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    Utils.mProgress.dismiss();
                } catch (Exception e) {

                }
                super.onPageFinished(view, url);
            }
        });
        content_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        noti_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd_notid != null) {
                    if (interstitialAd_notid.isAdLoaded()) {
                        interstitialAd_notid.show();
                    } else {
                        finish();
                        Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                } else {
                    finish();
                    Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                    startActivity(i);
                }

/*
                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        //Ads loaded
                        interstitialAd.show();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        //Ads closed
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        super.onAdFailedToLoad(errorCode);
                        //Ads couldn't loaded


                            finish();
                            Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                            startActivity(i);

                    }
                });
*/




             /*   if ((spa.getInt(ST_Activity1.this, "loadins_ads") == 0)) {
                    if (Ads_fb.quesfbinterstitialAd != null) {
                        if (Ads_fb.quesfbinterstitialAd.isAdLoaded()) {
                            Ads_fb.quesfbinterstitialAd.show();
                            Ads_fb.quesfbinterstitialAd.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onError(Ad arg0, AdError arg1) {
                                    // TODO Auto-generated method stub
                                    //HomeScreen.sharedPrefAddInt("2031", 1, HomeScreen.mPreferences);
                                    //	Toast.makeText(context, "load fb 1", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onAdLoaded(Ad arg0) {
                                    // TODO Auto-generated method stub
                                    //HomeScreen.sharedPrefAddInt("add_fb_in_1", 1, HomeScreen.mPreferences);
                                    //Toast.makeText(context, "load fb 1", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onAdClicked(Ad arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDisplayed(Ad arg0) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onInterstitialDismissed(Ad arg0) {
                                    // TODO Auto-generated method stub
                                    finish();
                                    Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                                    startActivity(i);
                                }
                            });
                        } else {
                            finish();
                            Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                            startActivity(i);
                        }
                    } else {
                        finish();
                        Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                } else if (spa.getInt(ST_Activity1.this, "loadins_ads") == 1) {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                        interstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                finish();
                                Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                                startActivity(i);
                            }
                        });
                    }

                }*/
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (interstitialAd_notid != null) {
            if (interstitialAd_notid.isAdLoaded()) {
                interstitialAd_notid.show();
            } else {
                finish();
                Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                startActivity(i);
            }
        } else {
            finish();
            Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
            startActivity(i);
        }
/*
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                //Ads loaded
                interstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                //Ads closed
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                //Ads couldn't loaded
                    finish();
                    Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                    startActivity(i);
            }
        });
*/


    /*
        if ((spa.getInt(ST_Activity1.this, "loadins_ads") == 0)) {
            if (Ads_fb.quesfbinterstitialAd != null) {
                if (Ads_fb.quesfbinterstitialAd.isAdLoaded()) {
                    Ads_fb.quesfbinterstitialAd.show();
                    Ads_fb.quesfbinterstitialAd.setAdListener(new InterstitialAdListener() {
                        @Override
                        public void onError(Ad arg0, AdError arg1) {
                            // TODO Auto-generated method stub
                            //HomeScreen.sharedPrefAddInt("2031", 1, HomeScreen.mPreferences);
                            //	Toast.makeText(context, "load fb 1", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdLoaded(Ad arg0) {
                            // TODO Auto-generated method stub
                            //HomeScreen.sharedPrefAddInt("add_fb_in_1", 1, HomeScreen.mPreferences);
                            //Toast.makeText(context, "load fb 1", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdClicked(Ad arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDisplayed(Ad arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onInterstitialDismissed(Ad arg0) {
                            // TODO Auto-generated method stub
                            finish();
                            Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                            startActivity(i);
                        }
                    });
                } else {
                    finish();
                    Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                    startActivity(i);
                }
            } else {
                finish();
                Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                startActivity(i);
            }
        } else if (spa.getInt(ST_Activity1.this, "loadins_ads") == 1) {
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        finish();
                        Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                });
            }else {
                finish();
                Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                startActivity(i);
            }

        }*/


    }

    protected void onResume() {
        super.onResume();
      /*  if (spa.getInt(ST_Activity1.this, "addloded") == 1) {
            New_Main_Activity.load_addFromMain(ST_Activity1.this, adds);
        }else {
            if (Utils.isNetworkAvailable(ST_Activity1.this)) {
                spa.putInt(ST_Activity1.this, "addloded", 2);
                System.out.println("@IMG");
                final AdView adView = new AdView(ST_Activity1.this);
                adView.setAdUnitId("ca-app-pub-4267540560263635/6120401901");

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
         *//*   spa.putInt(Levels.this, "addloded", 2);
            com.facebook.ads.AdView adVieww = new com.facebook.ads.AdView(Levels.this, "1746769098928603_1797515200520659", com.facebook.ads.AdSize.BANNER_320_50);
            adVieww.loadAd();
            adds.addView(adVieww);
            adds.setVisibility(View.VISIBLE);*//*
        }*/

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (spa.getInt(ST_Activity1.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(ST_Activity1.this)) {
                fb_native(ST_Activity1.this, native_banner_ad_container);
              /*  if (spa.getInt(ST_Activity1.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(ST_Activity1.this,native_banner_ad_container);
                }else {
                    fb_native(ST_Activity1.this,native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
           /* if (spa.getInt(ST_Activity1.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(ST_Activity1.this, adds);
            } else {
                if (Utils.isNetworkAvailable(ST_Activity1.this)) {
                    spa.putInt(ST_Activity1.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(ST_Activity1.this);
                    adView.setAdUnitId(getString(R.string.main_banner_ori));

                    adView.setAdSize(AdSize.SMART_BANNER);
                    AdRequest request = new AdRequest.Builder().build();
                    adView.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            System.out.println("@@@loaded");
                            ads_lay.removeAllViews();
                            ads_lay.addView(adView);
                            ads_lay.setVisibility(View.VISIBLE);
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
        }

    }

    public void industrial() {
        System.out.println("###############&&&&&&&&&" + sharedPreference.getInt(ST_Activity1.this, "Content_ads_shown_new"));
        interstitialAd_notid = new com.facebook.ads.InterstitialAd(ST_Activity1.this, getString(R.string.fb_noti_industrial));
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                finish();
                Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                startActivity(i);
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed


            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback

            }
        };

        if (sharedPreference.getInt(ST_Activity1.this, "Content_ads_shown_new") == 5 || sharedPreference.getInt(ST_Activity1.this, "Content_ads_shown_new") == 0) {
            sharedPreference.putInt(ST_Activity1.this, "Content_ads_shown_new", 1);
            if (spa.getInt(ST_Activity1.this, "purchase_ads") == 1) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            } else {
                // interstitialAd_notid.loadAd();
                interstitialAd_notid.loadAd(
                        interstitialAd_notid.buildLoadAdConfig()
                                .withAdListener(interstitialAdListener)
                                .build());
            }

/*
            interstitialAd_notid.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    System.out.println("###############&&&&&&&&&onError");
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    finish();
                    Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                    startActivity(i);
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    System.out.println("###############&&&&&&&&&onError");
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    System.out.println("###############&&&&&&&&&onAdLoaded");
                    */
/*countDownTimer = new CountDownTimer(10000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (counter == 3) {
                                if (!isFinishing()) {
                                    Utils.mProgress(Noti_Fragment.this, "ADS BREAK", false).show();
                                }
                            }
                            counter--;
                        }

                        @Override
                        public void onFinish() {
                            if (!isFinishing()) {
                                if (Utils.mProgress != null) {
                                    Utils.mProgress.dismiss();
                                }
                                if (interstitialAd_notid != null) {
                                    if (interstitialAd_notid.isAdLoaded()) {
                                        interstitialAd_notid.show();
                                    }
                                }
                            }

                        }
                    }.start();*//*

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
*/

        } else {
            sharedPreference.putInt(ST_Activity1.this, "Content_ads_shown_new",
                    sharedPreference.getInt(ST_Activity1.this, "Content_ads_shown_new") + 1);
        }

    }

}