package nithra.tamil.word.game.solliadi;

import android.app.Activity;
import android.app.Dialog;
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

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nit_app.CodetoTamilUtil;
import nithra.tamil.word.game.solliadi.adutils.AdUtils;


public class ST_Activity extends Activity {


    static SharedPreference spa = new SharedPreference();
    static com.facebook.ads.AdView adView_fb;
    static LinearLayout add_fb;
    SharedPreference sharedPreference;
    String str_title = "";
    Context context = this;
    SQLiteDatabase myDB;
    String tablenew = "noti_cal";
    String title, message, msgType, date, time;
    int idd;
    String urls;
    LinearLayout ads_lay;
    WebView content_view;
    AppCompatImageView noti_cancel;
    FloatingActionButton share_but;
    TextView sticky;
    int show_id, show_ads;
    LinearLayout native_banner_ad_container;
    com.facebook.ads.InterstitialAd interstitialAd_notid;

    private MaxInterstitialAd interstitialAd;

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
        System.out.println("#######St Activity");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        content_view = (WebView) findViewById(R.id.noti_web);


        content_view = (WebView) findViewById(R.id.noti_web);
        share_but = (FloatingActionButton) findViewById(R.id.share_but);
        sticky = (TextView) findViewById(R.id.sticky);
        //ImageView backdrop=(ImageView) home_about_dialog.findViewById(R.id.backdrop);
        noti_cancel = (AppCompatImageView) findViewById(R.id.noti_cancel);
        ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        /*getSupportActionBar().hide();*/
        sharedPreference = new SharedPreference();
        myDB = openOrCreateDatabase("myDB", 0, null);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + tablenew
                + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR);");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS share_noti (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,sahre_msg VARCHAR,date VARCHAR,time VARCHAR);");

        sharedPreference.putInt(context, "Content_ads_shown_neww", 2);
        // if (spa.getInt(context, "purchase_ads") == 0) {
        // Make sure to set the mediation provider value to "max" to ensure proper functionality
        AppLovinSdk.getInstance(context).setMediationProvider("max");
        AppLovinSdk.initializeSdk(context, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads
                loads_ads_banner();
                System.out.println("---Content_ads_shown_neww : " + sharedPreference.getInt(context, "Content_ads_shown_neww"));

                if (sharedPreference.getInt(context, "Content_ads_shown_neww") == 2) {
                    industrialload();
                } else {
                    sharedPreference.putInt(context, "Content_ads_shown_neww",
                            sharedPreference.getInt(context, "Content_ads_shown_neww") + 1);
                }
            }
        });
        //  }

        //industrial();


        spa.putInt(ST_Activity.this, "addlodedd", 0);
        spa.putInt(ST_Activity.this, "native_banner_ads", 0);


        Bundle extras;
        extras = getIntent().getExtras();
        idd = extras.getInt("idd");
        urls = extras.getString("urls");
        System.out.println("=============urls" + urls);

        Bundle extrass;
        extrass = getIntent().getExtras();
        if (extrass != null) {
            int idd = extrass.getInt("idd");
            int adss = extrass.getInt("Noti_add");
            title = extrass.getString("title");
            message = extrass.getString("message");
            show_id = idd;
            show_ads = adss;

            str_title = title;

        }

        myDB.execSQL("update noti_cal set isclose='1' where id='" + idd + "'");

        TextView tit_txt = (TextView) findViewById(R.id.sticky);
        tit_txt.setText(str_title);

        WebSettings ws = content_view.getSettings();
        ws.setJavaScriptEnabled(true);

        String bodyFont = "<style> body { font-size:20px; } table { font-size:20px; <font face='bamini' > }</style>"
                + "<style> @font-face { font-family:'bamini'; src: url('file:///android_asset/baamini.ttf') } </style>";

        String summary = "<!DOCTYPE html> <html><head>" + bodyFont + " </head> <body >"
                + "" + message + "</body></html>";
        String str = message.substring(0, 4);
        if (str.equals("http")) {
            content_view.loadUrl(message);
        } else {
            content_view.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);
        }
        // content_view.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);

        FloatingActionButton share_but = (FloatingActionButton) findViewById(R.id.share_but);

        share_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = Html.fromHtml(message).toString();
                result = sharedPreference.getString(ST_Activity.this, "view_web_tit") + "\n" + CodetoTamilUtil.convertToTamil(CodetoTamilUtil.BAMINI, result);
                Share_content share_content = new Share_content();
                share_content.Share_content(ST_Activity.this, result);
                // share_txt(ST_Activity.this, "" + result);
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

                Utils.mProgress(ST_Activity.this, "Loading Please Wait", true).show();
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

               /* if (interstitialAd_notid != null) {
                    if (interstitialAd_notid.isAdLoaded()) {
                       // interstitialAd_notid.show();
                        back_press();
                    } else {
                        finish();
                        Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }else {
                    finish();
                    Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                    startActivity(i);
                }*/

                if (spa.getInt(context, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        //interstitialAd.showAd();
                        back_press();
                    } else {
                        if (show_ads == 1) {
                            finish();
                            Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    }
                } else {
                    if (show_ads == 1) {
                        finish();
                        Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }

                }
             /*   if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            //Ads closed
                            finish();
                            Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                            startActivity(i);
                        }
                    });
                } else {
                    finish();
                    Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                    startActivity(i);
                }*/


             /*   interstitialAd.setAdListener(new AdListener() {
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
                        finish();
                        Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        super.onAdFailedToLoad(errorCode);
                        //Ads couldn't loaded

                            finish();
                            Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                            startActivity(i);

                    }
                });*/


            }
        });


    }

    public void loads_ads_banner() {
        System.out.println("print_Ad");

        if (sharedPreference.getInt(ST_Activity.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            ads_lay.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(ST_Activity.this)) {
                AdUtils.load_add_facebook(this, getResources().getString(R.string.Noti_Banner), ads_lay);
            } else {
                ads_lay.setVisibility(View.GONE);
            }
        }
    }

    public void industrialload() {

        interstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Noti_Exit_INS), this);
        interstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                sharedPreference.putInt(context, "Content_ads_shown_neww", 0);
                if (show_ads == 1) {
                    finish();
                    Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });
        interstitialAd.loadAd();

    }

    @Override
    public void onBackPressed() {

        if (spa.getInt(context, "purchase_ads") == 0) {
            if (interstitialAd != null && interstitialAd.isReady()) {
                back_press();
            } else {
                if (show_ads == 1) {
                    finish();
                    Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            }
        } else {
            if (show_ads == 1) {
                finish();
                Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                finish();
            }

        }


    }

    protected void onResume() {
        super.onResume();

    }

    public void back_press() {
        final Dialog openDialog_p = new Dialog(ST_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess_noti);
        openDialog_p.setCancelable(false);
        TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
        TextView noask = (TextView) openDialog_p.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_p.dismiss();
                if (spa.getInt(context, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        finish();
                    }

                }
            }
        });
        noask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_p.dismiss();
            }
        });
        openDialog_p.show();
    }
}