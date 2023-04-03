package nithra.tamil.word.game.solliadi;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nit_app.CodetoTamilUtil;


public class Noti_Fragment extends AppCompatActivity implements Notify_del {


    final String tablenew = "noti_cal";
    SharedPreference sharedPreference;
    SQLiteDatabase myDB;
    TextView txtNoNotification, del_txt;
    ListView listView;
    int notification_counts = 0;
    String[] title, message, msgType, msgDate, msgTime, urls;
    int[] Id, isclose;

    int isvalided;
    String str_msg;
    // static SharedPreference sharedPreference = new SharedPreference();
    Button noti_delete;
    ProgressDialog mProgress;


    LinearLayout add_fb;


    com.facebook.ads.InterstitialAd interstitialAd_notid;
    int counter = 10;
    CountDownTimer countDownTimer;
    Dialog home_about_dialog;
    Dialog openDialog_p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noti_view);

        sharedPreference = new SharedPreference();

        myDB = openOrCreateDatabase("myDB", 0, null);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + tablenew
                + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR," +
                "bm VARCHAR,ntype VARCHAR,url VARCHAR);");

        LinearLayout ads_lay = findViewById(R.id.ads_lay);
        // New_Main_Activity.load_addFromMain(Noti_Fragment.this, ads_lay);
        txtNoNotification = findViewById(R.id.txtNoNotification);
        del_txt = findViewById(R.id.del_txt);
        listView = findViewById(R.id.listView1);

        // noti_delete = (Button)findViewById(R.id.noti_delete);


        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.action_noti, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        TextView action = findViewById(R.id.actionword8);
        noti_delete = findViewById(R.id.noti_delete);
        action.setText("அறிவிப்புகள்");
        TextView action_back = findViewById(R.id.action_back);
        Drawable d = getResources().getDrawable(R.drawable.actionbar_back);
        getSupportActionBar().setBackgroundDrawable(d);
        openDialog_p = new Dialog(Noti_Fragment.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess_noti);


        setada();

        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delet_fun(Id[position], 0);
                Log.i("check_jaasim", "onItemLongClick: ");
                return true;
            }
        });
        noti_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                delet_fun(0, 1);
            }
        });

    }

    @Override
    public void onLongClick(int id, int title) {
        delet_fun(id, title);
    }

    public void delet_fun(final int id, final int title) {

        final Dialog no_datefun = new Dialog(Noti_Fragment.this,
                android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        no_datefun.setContentView(R.layout.nodate_dia);
        no_datefun.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        Button btnSet = no_datefun.findViewById(R.id.btnSet);
        Button btnok = no_datefun.findViewById(R.id.btnok);
        TextView head_txt = no_datefun.findViewById(R.id.head_txt);
        TextView editText1 = no_datefun.findViewById(R.id.editText1);
        btnSet.setText("ஆம்");
        btnok.setText("இல்லை");
        head_txt.setVisibility(View.GONE);

        if (title == 0) {
            editText1.setText("இந்த பதிவை நீக்க வேண்டுமா?");
        } else {
            editText1.setText("அனைத்து அறிவிப்புகளையும் நீக்க வேண்டுமா?");
        }

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sharedPreference.putString(getApplicationContext(), "imgURL"+title, url);
                sharedPreference.removeString(Noti_Fragment.this, "imgURL" + id);

                if (title == 0) {
                    myDB.execSQL("delete from noti_cal where id = '" + id + "'");
                } else {
                    myDB.execSQL("delete from noti_cal ");
                }
                setada();
                no_datefun.dismiss();

            }
        });

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_datefun.dismiss();
            }
        });


        no_datefun.show();

    }


    public void setada() {
        if (openDialog_p != null || openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
        Cursor c = myDB.rawQuery("select * from " + tablenew + " order by id desc ", null);

        if (c.getCount() == 0) {
            txtNoNotification.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            del_txt.setVisibility(View.GONE);
            noti_delete.setVisibility(View.GONE);
            notification_counts = 1;

        } else {
            notification_counts = 0;
            txtNoNotification.setVisibility(View.GONE);
            del_txt.setVisibility(View.VISIBLE);
            noti_delete.setVisibility(View.VISIBLE);
            Id = new int[c.getCount()];
            isclose = new int[c.getCount()];
            title = new String[c.getCount()];
            message = new String[c.getCount()];
            msgType = new String[c.getCount()];
            msgDate = new String[c.getCount()];
            msgTime = new String[c.getCount()];
            urls = new String[c.getCount()];
            for (int i = 0; i < c.getCount(); i++) {
                c.moveToPosition(i);
                Id[i] = c.getInt(c.getColumnIndexOrThrow("id"));
                title[i] = c.getString(c.getColumnIndexOrThrow("bm"));
                message[i] = c.getString(c.getColumnIndexOrThrow("message"));
                msgType[i] = c.getString(c.getColumnIndexOrThrow("type"));
                isclose[i] = c.getInt(c.getColumnIndexOrThrow("isclose"));
                msgDate[i] = c.getString(c.getColumnIndexOrThrow("date"));
                msgTime[i] = c.getString(c.getColumnIndexOrThrow("time"));
                urls[i] = c.getString(c.getColumnIndexOrThrow("url"));
            }

            notify_adapter adapter = new notify_adapter(Noti_Fragment.this, isclose, title, urls, Id, msgDate, msgTime);
            listView.setAdapter(adapter);


        }

    }

    public void dia_dismiss(Dialog dialog) {
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                setada();
            }
        });
    }

    public void otfun(String title, String message) {


        final Dialog dialog_ot = new Dialog(Noti_Fragment.this,
                android.R.style.Theme_Translucent_NoTitleBar);
        dialog_ot.setContentView(R.layout.otdia);
        TextView titletxtot = dialog_ot.findViewById(R.id.title);
        titletxtot.setText(title);
        final LinearLayout addview = dialog_ot
                .findViewById(R.id.addview);
        //  New_Main_Activity.load_addFromMain(Noti_Fragment.this, addview);


        WebView notesWebView = dialog_ot
                .findViewById(R.id.webView2);

        notesWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        WebSettings ws = notesWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        notesWebView.loadUrl(message);
        notesWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //	view.loadUrl(url);
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Utils.mProgress(Noti_Fragment.this, "Loading Please Wait", true).show();
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
        dia_dismiss(dialog_ot);
        dialog_ot.show();
    }


    @Override
    public void onResume() {
        super.onResume();


        System.out.println("########################notification_counts" + notification_counts);
        System.out.println("@IN");


    }


    public void loadUrlInWebView(final String url) {
        final Dialog urlDialog = new Dialog(Noti_Fragment.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        urlDialog.setContentView(R.layout.common_web);

        final LinearLayout addView1 = urlDialog.findViewById(R.id.addview);
        Button btnTitle = urlDialog.findViewById(R.id.version2);
        final FloatingActionButton web_share = urlDialog.findViewById(R.id.web_share);
        btnTitle.setVisibility(View.GONE);
        urlDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

        NestedWebView notesWebView = urlDialog.findViewById(R.id.common_web);
        WebSettings ws = notesWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setBuiltInZoomControls(true);
        ws.setSupportZoom(true);
        notesWebView.loadUrl(url);
        notesWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
// Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgress = ProgressDialog.show(Noti_Fragment.this, null,
                        "Loading please wait");
                mProgress.setCancelable(false);
                mProgress.show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    mProgress.dismiss();
                } catch (Exception e) {

                }
                super.onPageFinished(view, url);
            }

        });

        notesWebView.setOnScrollChangedCallback(new NestedWebView.OnScrollChangedCallback() {
            @Override
            public void onScrollChange(WebView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY && scrollY > 0) {

                    web_share.hide();
                }

                if (scrollY < oldScrollY) {

                    web_share.show();
                }
            }
        });

        web_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String share_txt = CodetoTamilUtil.convertToTamil(CodetoTamilUtil.BAMINI, Html.fromHtml(url).toString());
                Share_content share_content = new Share_content();
                share_content.Share_content(Noti_Fragment.this, share_txt);
            }
        });

        urlDialog.show();

        urlDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                setada();
            }
        });
    }

    public void onBackPressed() {
        finish();
    }

}
