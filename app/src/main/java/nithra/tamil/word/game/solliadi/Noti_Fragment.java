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
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nit_app.CodetoTamilUtil;
import nithra.tamil.word.game.solliadi.adutils.AdUtils;


public class Noti_Fragment extends AppCompatActivity implements Notify_del {


    SharedPreference sharedPreference;

    SQLiteDatabase myDB;
    String tablenew = "noti_cal";

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
    LinearLayout layout_banner_ad;


    com.facebook.ads.InterstitialAd interstitialAd_notid;
    int counter = 10;
    CountDownTimer countDownTimer;
    Dialog home_about_dialog;
    Dialog openDialog_p;

    private MaxInterstitialAd interstitialAd;

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

        LinearLayout ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        // New_Main_Activity.load_addFromMain(Noti_Fragment.this, ads_lay);
        txtNoNotification = (TextView) findViewById(R.id.txtNoNotification);
        del_txt = (TextView) findViewById(R.id.del_txt);
        listView = (ListView) findViewById(R.id.listView1);

        // noti_delete = (Button)findViewById(R.id.noti_delete);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.action_noti, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        TextView action = (TextView) findViewById(R.id.actionword8);
        noti_delete = (Button) findViewById(R.id.noti_delete);
        action.setText("அறிவிப்புகள்");
        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d = getResources().getDrawable(R.drawable.actionbar_back);
        getSupportActionBar().setBackgroundDrawable(d);
        openDialog_p = new Dialog(Noti_Fragment.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess_noti);

        layout_banner_ad = (LinearLayout) findViewById(R.id.layout_banner_ad);

        if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(this).setMediationProvider("max");
            AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    loads_ads_banner();

                }
            });

        }

        setada();

        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
               /* Intent i = new Intent(Noti_Fragment.this, New_Main_Activity.class);
                startActivity(i);*/

            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("##########Noti_Fragment");


                myDB.execSQL("update noti_cal set isclose='1' where id='" + Id[position] + "'");

                Cursor cv = myDB.rawQuery("select * from noti_cal where id='" + Id[position] + "'", null);
                cv.moveToFirst();

                String type = cv.getString(cv.getColumnIndexOrThrow("type"));
                String urls = cv.getString(cv.getColumnIndexOrThrow("url"));

                if (type.equals("s")) {

                    // gcmfun_shown(List.get(position).getTitle(), List.get(position).getMessage());
                    notificationsTamil(title[position], message[position], urls, 0);

                } else if (type.equals("st")) {

                    notificationsTamil(title[position], message[position], urls, 0);

                } else if (type.equals("w")) {

                    notificationsOnLink(title[position], message[position]);

                } else if (type.equals("ot")) {

                    notificationsOnLink(title[position], message[position]);
                }

                //myDB.execSQL("update " + tablenew + " set isclose='1' where id='" + Id[position] + "'");
                // gcmfun(message[position], title[position], msgType[position], msgDate[position], msgTime[position],Id[position]);
            }
        });*/

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
        Button btnSet = (Button) no_datefun.findViewById(R.id.btnSet);
        Button btnok = (Button) no_datefun.findViewById(R.id.btnok);
        TextView head_txt = (TextView) no_datefun.findViewById(R.id.head_txt);
        TextView editText1 = (TextView) no_datefun.findViewById(R.id.editText1);
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

            layout_banner_ad.setVisibility(View.GONE);
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

            if (Utils.isNetworkAvailable(Noti_Fragment.this)) {
                layout_banner_ad.setVisibility(View.VISIBLE);
            } else {
                layout_banner_ad.setVisibility(View.GONE);
            }
        }

    }

    public void gcmfun(final String message, final String title, String type, final String date, final String time, final int id) {


        /* dialog for show message */
        final Dialog dialog_s = new Dialog(Noti_Fragment.this,
                android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog_s.setContentView(R.layout.cross_app);

        Button ok = (Button) dialog_s.findViewById(R.id.button1);
        ok.setText("Ok");
        TextView textView1 = (TextView) dialog_s.findViewById(R.id.version2);
        TextView textView2 = (TextView) dialog_s.findViewById(R.id.textView2);
        textView1.setText("" + title);
        textView2.setText("" + message);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dialog_s.dismiss();
            }
        });

        /* dialog for show for tamil message */


        /* dialog for show for web link message */

        final Dialog dialog_w = new Dialog(Noti_Fragment.this,
                android.R.style.Theme_Translucent_NoTitleBar);
        dialog_w.setContentView(R.layout.notification_url);
        TextView txtHeading = (TextView) dialog_w.findViewById(R.id.txtHeading);
        Button btnClkHere = (Button) dialog_w.findViewById(R.id.btnClkHere);

        txtHeading.setText(title);
        btnClkHere.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Utils.isNetworkAvailable(Noti_Fragment.this)) {

                    otfun(title, message);

                } else {
                    Utils.toast_normal(Noti_Fragment.this, "Hey buddy, connect to the network");
                }
                dialog_w.dismiss();
            }
        });


        if (type.equals("s") || type.equals("ns")) {
            dia_dismiss(dialog_s);
            dialog_s.show();
        } else if (type.equals("st")) {
            Intent i = new Intent(Noti_Fragment.this, ST_Activity.class);
            sharedPreference.putInt(Noti_Fragment.this, "Noti_id", id);
            startActivity(i);
        } else if (type.equals("w")) {
            dia_dismiss(dialog_w);
            dialog_w.show();
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
        TextView titletxtot = (TextView) dialog_ot.findViewById(R.id.title);
        titletxtot.setText(title);
        final LinearLayout addview = (LinearLayout) dialog_ot
                .findViewById(R.id.addview);
        //  New_Main_Activity.load_addFromMain(Noti_Fragment.this, addview);


        WebView notesWebView = (WebView) dialog_ot
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


        if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 1) {
            layout_banner_ad.setVisibility(View.GONE);
        }
    }

    public void notificationsTamil(String title, final String message, String url, int val) {
        //industrial();
        home_about_dialog = new Dialog(Noti_Fragment.this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        home_about_dialog.setContentView(R.layout.st_lay);
        System.out.println("##########dialog");
        home_about_dialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

        WebView webView1 = (WebView) home_about_dialog.findViewById(R.id.noti_web);
        final FloatingActionButton web_share = (FloatingActionButton) home_about_dialog.findViewById(R.id.share_but);
        TextView sticky = (TextView) home_about_dialog.findViewById(R.id.sticky);
        //ImageView backdrop=(ImageView) home_about_dialog.findViewById(R.id.backdrop);
        AppCompatImageView noti_cancel = (AppCompatImageView) home_about_dialog.findViewById(R.id.noti_cancel);
        final LinearLayout ads_lay = (LinearLayout) home_about_dialog.findViewById(R.id.ads_lay);
        System.out.println("Url==" + url);

        //btnTitle.setText(title);
        sticky.setText(title);
        final LinearLayout addview = (LinearLayout) home_about_dialog.findViewById(R.id.ad_lay);


        if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            /* */
            System.out.println("#####################request send");
        }


        //New_Main_Activity.adds(Noti_Fragment.this, addview);
        LinearLayout native_banner_ad_container = (LinearLayout) home_about_dialog.findViewById(R.id.native_banner_ad_container);

        if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Noti_Fragment.this)) {
                // load_addFromMain_fb(Noti_Fragment.this, native_banner_ad_container);
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }


       /* if (sharedPreference.getInt(context, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(context).setMediationProvider("max");
            AppLovinSdk.initializeSdk(context, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    industrialload();
                }
            });
        }*/

        noti_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        //interstitialAd.showAd();
                        back_press();
                    } else {
                        setada();
                        home_about_dialog.dismiss();
                    }
                }
            }
        });

        WebSettings ws = webView1.getSettings();
        ws.setJavaScriptEnabled(true);
        String bodyFont = "<style> body { font-size:20px; } table { font-size:20px; <font face='bamini' > }</style>"
                + "<style> @font-face { font-family:'bamini'; src: url('file:///android_asset/baamini.ttf') } </style>";
        final String summary = "<!DOCTYPE html> <html><head>" + bodyFont + " </head> <body >"
                + message + "</body></html>";

        String str = message.substring(0, 4);
        if (str.equals("http")) {
            webView1.loadUrl(message);
        } else {
            webView1.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);
        }
        // webView1.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);

        webView1.setWebViewClient(new WebViewClient() {

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
                mProgress = ProgressDialog.show(Noti_Fragment.this, null,
                        "Loading Please Wait");
                mProgress.setCancelable(true);
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

            /*webView1.setOnScrollChangedCallback(new NestedWebView.OnScrollChangedCallback() {
                @Override
                public void onScrollChange(WebView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > oldScrollY && scrollY > 0) {

                        web_share.hide();
                    }

                    if (scrollY < oldScrollY) {

                        web_share.show();
                    }
                }
            });*/
        webView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        web_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String share_txt = CodetoTamilUtil.convertToTamil(CodetoTamilUtil.BAMINI, Html.fromHtml(message).toString());
              /*  Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "தமிழில் புதிர்விளையாட்டு விளையாட ஆர்வமா?\n" +
                        "உங்களின் எதிர்ப்பார்ப்புகளை பூர்த்தி செய்யும் சொல்லிஅடி செயலி பல்வேறு சிறப்பு அம்சங்களுடன் உங்களுக்காக! உடனே தரவிறக்கம்  செய்யுங்கள். https://goo.gl/EUGjDh" + "\n\n" + share_txt);
                //shareIntent.putExtra(Intent.EXTRA_SUBJECT,"நித்ரா தமிழ் செயலி:"+" "+"https://goo.gl/FJln8A");
                shareIntent.setType("text/*");
                startActivity(Intent.createChooser(shareIntent, "Share text using"));*/
                Share_content share_content = new Share_content();
                share_content.Share_content(Noti_Fragment.this, share_txt);
            }
        });

        home_about_dialog.show();
        home_about_dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        //interstitialAd.showAd();
                        back_press();
                    } else {
                        setada();
                    }
                }

                return false;
            }
        });
       /* home_about_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {


                if (interstitialAd_notid != null) {
                    if (interstitialAd_notid.isAdLoaded()) {
                        //interstitialAd_notid.show();
                        back_press();
                    }else {
                        setada();
                        Noti_Fragment.load_addFromMain_fb(Noti_Fragment.this, native_banner_ad_container_mn);

                        home_about_dialog.dismiss();
                    }
                }else {
                    setada();
                    Noti_Fragment.load_addFromMain_fb(Noti_Fragment.this, native_banner_ad_container_mn);

                    home_about_dialog.dismiss();
                }
            }
        });*/
    }


    public void notificationsOnLink(String title, final String message) {

        final Dialog notifcationUrlDialog = new Dialog(Noti_Fragment.this,
                android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        notifcationUrlDialog.setContentView(R.layout.notification_url);

        notifcationUrlDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);

        TextView txtHeading = (TextView) notifcationUrlDialog
                .findViewById(R.id.txtHeading);
        Button btnClickHere = (Button) notifcationUrlDialog
                .findViewById(R.id.btnClkHere);

        txtHeading.setText(title);
        btnClickHere.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ConnectionDetector cd = new ConnectionDetector(Noti_Fragment.this);
                final Boolean status = cd.connectiontointernet();
                if (status) {
                    loadUrlInWebView(message);
                    notifcationUrlDialog.dismiss();
                } else {
                    //toast("Hey buddy, connect to the network", getApplicationContext());
                    Toast.makeText(Noti_Fragment.this, "Hey buddy, connect to the network", Toast.LENGTH_SHORT).show();
                }
            }
        });


        notifcationUrlDialog.show();

        notifcationUrlDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                setada();
            }
        });
    }

/*    public void get_noti() {

        db = dbadapter.getReadableDatabase();

        Cursor cv = db.rawQuery("select * from notify", null);
        cv.moveToFirst();

        if (cv.getCount() == 0) {

            no_noti.setVisibility(View.VISIBLE);

        } else {

            ladapter = new LoginDataBaseAdapter(getApplicationContext());

            db = ladapter.getReadableDatabase();

            notes = new ArrayList<>();

            Cursor cv1 = db.rawQuery("select * from notify order by id desc", null);

            if (cv1.getCount() == 0) {


            } else {
                cv1.moveToFirst();

                do {
                    NotifyItem item = new NotifyItem();
                    item.setTitle(cv1.getString(cv1.getColumnIndexOrThrow("title")));
                    item.setMessage(cv1.getString(cv1.getColumnIndexOrThrow("message")));
                    item.setType(cv1.getString(cv1.getColumnIndexOrThrow("type")));
                    item.setId(cv1.getInt(cv1.getColumnIndexOrThrow("id")));
                    item.setDate(cv1.getString(cv1.getColumnIndexOrThrow("date")));
                    item.setTime(cv1.getString(cv1.getColumnIndexOrThrow("time")));
                    item.setIsclose(cv1.getInt(cv1.getColumnIndexOrThrow("isclose")));
                    notes.add(item);

                } while (cv1.moveToNext());
            }

            notify = (RecyclerView) findViewById(R.id.r_notify);
            adapter = new NotificationAdapter(getApplicationContext(), notes);
            notify.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            notify.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }*/

    public void loadUrlInWebView(final String url) {
        final Dialog urlDialog = new Dialog(Noti_Fragment.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        urlDialog.setContentView(R.layout.common_web);

        final LinearLayout addView1 = (LinearLayout) urlDialog.findViewById(R.id.addview);
        Button btnTitle = (Button) urlDialog.findViewById(R.id.version2);
        final FloatingActionButton web_share = (FloatingActionButton) urlDialog.findViewById(R.id.web_share);
        btnTitle.setVisibility(View.GONE);
        urlDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

        NestedWebView notesWebView = (NestedWebView) urlDialog.findViewById(R.id.common_web);
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
/*
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh" + "\n\n" + share_txt);
                //shareIntent.putExtra(Intent.EXTRA_SUBJECT,"நித்ரா தமிழ் செயலி:"+" "+"https://goo.gl/FJln8A");
                shareIntent.setType("text/*");
                startActivity(Intent.createChooser(shareIntent, "Share text using"));*/
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
        /*Intent i = new Intent(Noti_Fragment.this, New_Main_Activity.class);
        startActivity(i);*/
    }


    public void industrial() {
        interstitialAd_notid = new com.facebook.ads.InterstitialAd(Noti_Fragment.this, getString(R.string.fb_noti_industrial));
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                setada();
                if (home_about_dialog.isShowing()) {
                    home_about_dialog.dismiss();
                }
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
                sharedPreference.putInt(Noti_Fragment.this, "Content_ads_shown_neww", 0);
                if (home_about_dialog.isShowing()) {
                    home_about_dialog.dismiss();
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

    public void loads_ads_banner() {
        System.out.println("#####=====notification_counts" + notification_counts);
        if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            layout_banner_ad.setVisibility(View.GONE);
        } else {
            //notification_counts = 0;
            if (notification_counts == 0) {
                if (Utils.isNetworkAvailable(Noti_Fragment.this)) {
                    AdUtils.load_add_facebook(this, getResources().getString(R.string.Noti_Banner), layout_banner_ad);
                } else {
                    layout_banner_ad.setVisibility(View.GONE);
                }
            } else {
                layout_banner_ad.setVisibility(View.GONE);
            }

        }
        /*if (sharedPreference.getInt(Noti_Fragment.this, "addlodedd") == 1) {

            New_Main_Activity.load_addFromMain(Noti_Fragment.this, adds);
            System.out.println("@Native");

        }else {
            if (Utils.isNetworkAvailable(Noti_Fragment.this)) {
                sharedPreference.putInt(Noti_Fragment.this, "addlodedd", 2);
                System.out.println("@IMG");
                final AdView adView = new AdView(Noti_Fragment.this);
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
    }

    public void back_press() {

        openDialog_p.setCancelable(false);
        TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
        TextView noask = (TextView) openDialog_p.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog_p.dismiss();
                /*if (interstitialAd_notid.isAdLoaded()) {
                    interstitialAd_notid.show();
                } else {
                    setada();
                }*/
                if (sharedPreference.getInt(Noti_Fragment.this, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        setada();
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
