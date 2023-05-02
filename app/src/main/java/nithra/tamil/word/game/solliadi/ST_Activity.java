package nithra.tamil.word.game.solliadi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nit_app.CodetoTamilUtil;


public class ST_Activity extends Activity {


    static final SharedPreference spa = new SharedPreference();
    static LinearLayout add_fb;
    final Context context = this;
    final String tablenew = "noti_cal";
    SharedPreference sharedPreference;
    String str_title = "";
    SQLiteDatabase myDB;
    String title, message, msgType, date, time;
    int idd;
    String urls;
    AdView ads_lay;
    WebView content_view;
    AppCompatImageView noti_cancel;
    FloatingActionButton share_but;
    TextView sticky;
    int show_id, show_ads;
    Handler handler;
    LinearLayout banner_adParent;
    Runnable my_runnable;

    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.st_lay);
        System.out.println("#######St Activity");

        content_view = findViewById(R.id.noti_web);
        banner_adParent = findViewById(R.id.banner_adParent);


        content_view = findViewById(R.id.noti_web);
        share_but = findViewById(R.id.share_but);
        sticky = findViewById(R.id.sticky);
        //ImageView backdrop=(ImageView) home_about_dialog.findViewById(R.id.backdrop);
        noti_cancel = findViewById(R.id.noti_cancel);
        ads_lay = findViewById(R.id.ads_lay);
        /*getSupportActionBar().hide();*/
        sharedPreference = new SharedPreference();
        myDB = openOrCreateDatabase("myDB", 0, null);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + tablenew
                + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR);");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS share_noti (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,sahre_msg VARCHAR,date VARCHAR,time VARCHAR);");


        if (sharedPreference.getInt(this, "purchase_ads") == 0) {
            Utills.INSTANCE.initializeAdzz(this);
            if (sharedPreference.getInt(context, "Noti_Content_Close") == 0 || sharedPreference.getInt(context, "Noti_Content_Close") == Utills.notiInterstitialadCount)
                industrialload();
        }
        //Utills.INSTANCE.load_add_AppLovin(this, ads_lay, getResources().getString(R.string.Noti_Banner));
        Utills.INSTANCE.loads_ads_banner(this, ads_lay, banner_adParent, getResources().getString(R.string.Noti_Banner));
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

        TextView tit_txt = findViewById(R.id.sticky);
        tit_txt.setText(str_title);

        WebSettings ws = content_view.getSettings();
        ws.setJavaScriptEnabled(true);

        String bodyFont = "<style> body { font-size:20px; } table { font-size:20px; <font face='bamini' > }</style>"
                + "<style> @font-face { font-family:'bamini'; src: url('file:///android_asset/baamini.ttf') } </style>";

        String summary = "<!DOCTYPE html> <html><head>" + bodyFont + " </head> <body >"
                + "" + message + "</body></html>";
        String str = message.substring(0, 4);
        if (str.equals("http")) content_view.loadUrl(message);
        else content_view.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);
        // content_view.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);

        FloatingActionButton share_but = findViewById(R.id.share_but);

        share_but.setOnClickListener(v -> {
            String result = Html.fromHtml(message).toString();
            result = sharedPreference.getString(ST_Activity.this, "view_web_tit") + "\n" + CodetoTamilUtil.convertToTamil(CodetoTamilUtil.BAMINI, result);
            Share_content share_content = new Share_content();
            share_content.Share_content(ST_Activity.this, result);
            // share_txt(ST_Activity.this, "" + result);
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
        content_view.setOnLongClickListener(v -> true);
        noti_cancel.setOnClickListener(v -> onBackPressed());


    }

    private void industrialload() {
        if (mInterstitialAd != null) return;
        Log.i("TAG", "onAdLoadedCalled");
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getResources().getString(R.string.Noti_Exit_INS), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                interstiallistener();
                Log.i("TAG", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("TAG", loadAdError.toString());
                mInterstitialAd = null;
                Log.i("TAG", "onAdLoadedfailed" + loadAdError.getMessage());
            }
        });

    }

    public void interstiallistener() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d("TAG", "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
                if (show_ads == 1) {
                    finish();
                    Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                    startActivity(i);
                } else finish();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e("TAG", "Ad failed to show fullscreen content.");
                mInterstitialAd = null;


            }

        });
    }

    public void adShow() {
        if (mInterstitialAd != null) {
            if (sharedPreference.getInt(context, "Noti_Content_Close") == 0)
                sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
            else
                sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", 1);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                mInterstitialAd.show(this);
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
            if (sharedPreference.getInt(context, "Noti_Content_Close") > Utills.notiInterstitialadCount)
                sharedPreference.putInt(context, "Noti_Content_Close", 1);

            //Toast.makeText(this, "" + sharedPreference.getInt(this, "Noti_Content_Close"), Toast.LENGTH_SHORT).show();
            if (show_ads == 1) {
                finish();
                Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                startActivity(i);
            } else finish();
        }

    }

    protected void onResume() {
        super.onResume();
        //  Toast.makeText(context, "" + sharedPreference.getInt(context, "Noti_Content_Close"), Toast.LENGTH_SHORT).show();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Utills.INSTANCE.Loading_Dialog_dismiss();
        mInterstitialAd = null;
        handler = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
        if (spa.getInt(context, "purchase_ads") == 0) if (mInterstitialAd != null) back_press();
        else {
            sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
            if (sharedPreference.getInt(context, "Noti_Content_Close") > Utills.notiInterstitialadCount)
                sharedPreference.putInt(context, "Noti_Content_Close", 1);
            if (show_ads == 1) {
                finish();
                startActivity(i);
            } else finish();
        }
        else {
            sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
            if (sharedPreference.getInt(context, "Noti_Content_Close") > Utills.notiInterstitialadCount)
                sharedPreference.putInt(context, "Noti_Content_Close", 1);
            if (show_ads == 1) {
                finish();
                startActivity(i);
            } else finish();
        }


    }

    public void back_press() {
        final Dialog openDialog_p = new Dialog(ST_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess_noti);
        openDialog_p.setCancelable(false);
        TextView yes = openDialog_p.findViewById(R.id.yes);
        TextView noask = openDialog_p.findViewById(R.id.no);
        yes.setOnClickListener(v -> {
            openDialog_p.dismiss();
            adShow();
        });
        noask.setOnClickListener(v -> openDialog_p.dismiss());
        openDialog_p.show();
    }
}