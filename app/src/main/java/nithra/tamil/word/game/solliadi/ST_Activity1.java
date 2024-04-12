package nithra.tamil.word.game.solliadi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nit_app.CodetoTamilUtil;


public class ST_Activity1 extends AppCompatActivity {
    final String tablenew = "noti_cal";
    final SharedPreference spa = new SharedPreference();
    SharedPreference sharedPreference;
    String str_title = "";
    WebView content_view;
    Context context;
    SQLiteDatabase myDB;
    String title, message, msgType, date, time;
    LinearLayout adds, ads_lay;

    AppCompatImageView noti_cancel;
    FloatingActionButton share_but;
    TextView sticky;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.st_lay);
        System.out.println("#######St Activity1");

        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);
        /*getSupportActionBar().hide();*/
        sharedPreference = new SharedPreference();
        myDB = openOrCreateDatabase("myDB", 0, null);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + tablenew
                + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR);");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS share_noti (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,sahre_msg VARCHAR,date VARCHAR,time VARCHAR);");


        if (spa.getInt(ST_Activity1.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {

        }

        System.out.println("##########ST Activity1");


        content_view = (WebView) findViewById(R.id.noti_web);
        share_but = (FloatingActionButton) findViewById(R.id.share_but);
        sticky = (TextView) findViewById(R.id.sticky);
        //ImageView backdrop=(ImageView) home_about_dialog.findViewById(R.id.backdrop);
        noti_cancel = (AppCompatImageView) findViewById(R.id.noti_cancel);

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

                finish();
                Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
                startActivity(i);


            }
        });


    }
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {

            finish();
            Intent i = new Intent(ST_Activity1.this, New_Main_Activity.class);
            startActivity(i);

        }
    };
}