package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.Makeword_Rightorder.TAG;

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
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nit_app.CodetoTamilUtil;


public class ST_Activity extends AppCompatActivity {


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
    LinearLayout ads_lay;
    WebView content_view;
    AppCompatImageView noti_cancel;
    FloatingActionButton share_but;
    TextView sticky;
    int show_id, show_ads;
    Handler handler;
    LinearLayout banner_adParent;
    Runnable my_runnable;

 //   private MaxInterstitialAd mInterstitialAd;
 private AdManagerInterstitialAd interstitialAd ;


    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
            if (spa.getInt(context, "purchase_ads") == 0)
                if (interstitialAd != null /*&& interstitialAd.show(this)*/) back_press();
                else {
                    sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
                   /* if (sharedPreference.getInt(context, "Noti_Content_Close") > *//*Utills.notiInterstitialadCount*//* Integer.parseInt( sharedPreference.getString(getApplicationContext(), "showCountNoti")))
                        sharedPreference.putInt(context, "Noti_Content_Close", 1);*/
                    if (!sharedPreference.getString(getApplicationContext(), "showCountNoti").isEmpty()) {
                        int showCountNoti = Integer.parseInt(sharedPreference.getString(getApplicationContext(), "showCountNoti"));
                        if (sharedPreference.getInt(context, "Noti_Content_Close") > showCountNoti)
                            sharedPreference.putInt(context, "Noti_Content_Close", 1);
                    }

                    if (show_ads == 1) {
                        finish();
                        startActivity(i);
                    } else finish();
                }
            else {
                System.out.println("hnrdfd "+sharedPreference.getString(getApplicationContext(), "showCountNoti"));
                sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
              /*  if (sharedPreference.getInt(context, "Noti_Content_Close") > *//*Utills.notiInterstitialadCount*//*  Integer.parseInt( sharedPreference.getString(getApplicationContext(), "showCountNoti")))
                    sharedPreference.putInt(context, "Noti_Content_Close", 1);*/
                if (!sharedPreference.getString(getApplicationContext(), "showCountNoti").isEmpty()) {
                    int showCountNoti = Integer.parseInt(sharedPreference.getString(getApplicationContext(), "showCountNoti"));
                    if (sharedPreference.getInt(context, "Noti_Content_Close") > showCountNoti)
                        sharedPreference.putInt(context, "Noti_Content_Close", 1);
                }

                if (show_ads == 1) {
                    finish();
                    startActivity(i);
                } else finish();
            }


        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.st_lay);
        System.out.println("#######St Activity");
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);
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
            String showCountNotiString = sharedPreference.getString(getApplicationContext(), "showCountNoti");
            System.out.println("tjnhhjs "+showCountNotiString);
            int showCountNotiValue = 0; // Default value if parsing fails
            if (showCountNotiString != null && !showCountNotiString.isEmpty()) {
                try {
                    showCountNotiValue = Integer.parseInt(showCountNotiString);
                } catch (NumberFormatException e) {
                    // Handle the case where the string cannot be parsed as an integer
                    e.printStackTrace(); // Or log the error
                }
            }
           // Utills.INSTANCE.initializeAdzz(this);
            //if (sharedPreference.getInt(context, "Noti_Content_Close") == 0 || sharedPreference.getInt(context, "Noti_Content_Close") == /*Utills.notiInterstitialadCount*/   Integer.parseInt( sharedPreference.getString(getApplicationContext(), "showCountNoti")))
            if (sharedPreference.getInt(context, "Noti_Content_Close") == 0 || sharedPreference.getInt(context, "Noti_Content_Close") == showCountNotiValue)
                //industrialload();
                if (!sharedPreference.getString(this, "InterstitialId").equals("")|| sharedPreference.getString(this, "InterstitialId") != null) {
                    industrialload();
                }
           // Utills.INSTANCE.load_add_AppLovin(this, ads_lay, getResources().getString(R.string.Noti_Banner));

            if (Utils.isNetworkAvailable(context)) {
                if (!sharedPreference.getString(context, "BannerId").equals("") || sharedPreference .getString(context, "BannerId") != null) {
                    System.out.println(
                            "Ads Should be not empty : " + sharedPreference.getString(context, "BannerId")
                    );
                    Utils.load_add_banner(context, sharedPreference.getString(context, "BannerId"), ads_lay);
                }
            } else {
                System.out.println(
                        "Ads Should be -- empty : " + sharedPreference.getString(context, "BannerId")
                );
                ads_lay.setVisibility(View.GONE);
            }

            if (!Utills.INSTANCE.isNetworkAvailable(this)) {
                banner_adParent.setVisibility(View.GONE);
            }
        }
        else banner_adParent.setVisibility(View.GONE);
        Bundle extras;
        extras = getIntent().getExtras();
        idd = extras.getInt("idd");


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
                super.onPageStarted(view, url, favicon);
                try {
                    Utils.mProgress(ST_Activity.this, "Loading Please Wait", true).show();
                } catch (Exception ignored) {

                }

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
        noti_cancel.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());


    }

    public void industrialload() {
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        AdManagerInterstitialAd.load(this,sharedPreference.getString(this, "InterstitialId"), adRequest,
                new AdManagerInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitial) {
                        interstitialAd = interstitial;
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d("TAG", "Ad dismissed fullscreen content.");
                               interstitialAd = null;
                                if (show_ads == 1) {
                                    finish();
                                    Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else finish();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Log.e("TAG", "Ad failed to show fullscreen content.");
                               interstitialAd = null;
                                handler = null;
                                Utills.INSTANCE.Loading_Dialog_dismiss();
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded for an ad.
                                Log.d(TAG, "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad showed fullscreen content.");
                            }
                        });

                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("TAG", loadAdError.toString());
                       interstitialAd = null;
                        handler = null;
                        Log.i("TAG", "onAdLoadedfailed" + loadAdError.getMessage());
                    }

                });

    }

    public void adShow() {

        System.out.println(" result "+ sharedPreference.getString(this, "showCountNoti"));
        if (!sharedPreference.getString(getApplicationContext(), "showCountNoti").isEmpty()) {
            if (interstitialAd != null) {
                if (sharedPreference.getInt(context, "Noti_Content_Close") == 0)
                    sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
                else
                    sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", 1);
                Utills.INSTANCE.Loading_Dialog(this);
                handler = new Handler(Looper.myLooper());
                my_runnable = () -> {
                    if (interstitialAd == null) if (show_ads == 1) {
                        finish();
                        Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                        startActivity(i);
                    } else finish();
                    else
                        interstitialAd.show(this);
                };
                handler.postDelayed(my_runnable, 2500);
            }
            else {
                sharedPreference.putInt(getApplicationContext(), "Noti_Content_Close", (sharedPreference.getInt(getApplicationContext(), "Noti_Content_Close") + 1));
                /*if (sharedPreference.getInt(context, "Noti_Content_Close") > *//*Utills.notiInterstitialadCount*//*  Integer.parseInt( sharedPreference.getString(getApplicationContext(), "showCountNoti")))
                sharedPreference.putInt(context, "Noti_Content_Close", 1);*/

                if (!sharedPreference.getString(getApplicationContext(), "showCountNoti").isEmpty()) {
                    int showCountNoti = Integer.parseInt(sharedPreference.getString(getApplicationContext(), "showCountNoti"));
                    if (sharedPreference.getInt(context, "Noti_Content_Close") > showCountNoti)
                        sharedPreference.putInt(context, "Noti_Content_Close", 1);
                }


                //Toast.makeText(this, "" + sharedPreference.getInt(this, "Noti_Content_Close"), Toast.LENGTH_SHORT).show();
                if (show_ads == 1) {
                    finish();
                    Intent i = new Intent(ST_Activity.this, New_Main_Activity.class);
                    startActivity(i);
                } else finish();
            }
        }else{
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
        interstitialAd = null;
        handler = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
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