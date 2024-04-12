package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.Utils.isNetworkAvailable;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Word_Game_Hard extends AppCompatActivity {
    public static final String TAG = "SavedGames";
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

    static int mCoinCount = 20;
    static int rvo = 0;


    // Facebook variable starts
    static SharedPreference spd = new SharedPreference();
    final SharedPreference sps = new SharedPreference();
    final int gameid = 4;
    final Context context = this;
    final int minmum = 1;
    final int maximum = 4;
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    private final PendingAction pendingAction = PendingAction.NONE;
    int fb_reward = 0;
    int val = 0;
    int reward_status = 0;
    // facebook variable ends
    String btn_str = "";
    Button clear;
    ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14;
    TextView bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15, bt16;
    EditText word_editer;
    TextView vl1, vl2, vl3, vl4, vl5, vl6, vl7, vl8, vl9, vl10, vl11, vl12, vl13, vl14;
    TextView time, score, word_no;
    String tr;
    TextView verify;
    LinearLayout ans_set;
    //SQLiteDatabase sqLiteDatabase1;
    DataBaseHelper myDbHelper;
    Typeface typ;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    Chronometer focus;
    List<String> test = new ArrayList<String>();
    int level;
    long timeWhenStopped = 0;
    int x = 0;
    int r = 1;
    int sk;
    String letterid;
    int b_score = 0;
    int counter = 0;
    int total = 70;
    int counter2 = 0;
    int total2 = 10;
    int f_sec;
    TextView toggleButton;
    int counter3 = 0;
    int total3 = 90;
    TextView settings;
    // MediaPlayer w1=new MediaPlayer();
    TextView cancel;
    SoundPool click, win, coin, worng, cr_ans;
    int soundId1, soundId2, soundId3, soundId4, soundId5;
    int sv = 0;
    RadioButton fn1, fn2, fn3;
    LinearLayout adds;
    Dialog openDialog;
    LinearLayout addsdialog;
    TextView tx1, tx2;
    int current_sc;
    int total_sc;
    int c_counter = 0;
    int c_total = 70;
    int k = 1;
    int case2 = 0, tot2 = 50, tt_case2, tt_tot2;
    Typeface tyr;
    PopupWindow popupWindow;
    int gt = 0;
    int tans;
    int tscore;
    int ttime;
    RelativeLayout w_head, helpshare_layout;
    // Myadapter adapter;
    TextView shareq, h_gplues, h_watts_app, h_facebook;
    String email = "";
    Timer t1, th;
    int t, t2;
    TextView lt_id;
    RelativeLayout head;
    TextView feedback;
    EditText usertxt;
    LinearLayout qtw;
    String answers;
    int defTimeOut = 0;
    int u_id;
    String downok = "", downnodata = "";
    DownloadFileAsync downloadFileAsync;
    ProgressDialog mProgressDialog;
    TextView next_continue;
    TextView ttscores;
    long ttstop;
    int rdvalu;
    SeekBar progress;
    TextView ex_bones, bs_points;
    TextView earncoin;
    int ry;
    String retype = "s";
    RelativeLayout edit_buttons_layout;
    TextView skip;
    Dialog openDialog_p;
    int s = 0;
    int share_name = 0;
    int setting_access = 0;
    RelativeLayout adsicon, adsicon2;
    int loadaddcontent = 0;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    int setval_vid;
    TextView coin_value;
    Dialog openDialog_daily;
    int randomno;
    FirebaseAnalytics mFirebaseAnalytics;
    int dia_dismiss = 0;
    Handler handler;
    Runnable my_runnable;
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            sps.putString(Word_Game_Hard.this, "game_area", "on");
            if (popupWindow.isShowing()) popupWindow.dismiss();
            else {

                sps.putInt(Word_Game_Hard.this, "addlodedd", 0);
                s = 1;
                openDialog_p = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog_p.setContentView(R.layout.back_pess);
                TextView yes = openDialog_p.findViewById(R.id.yes);
                TextView no = openDialog_p.findViewById(R.id.no);

                yes.setOnClickListener(v -> {

                    focus.stop();
                    counter = 0;
                    counter3 = 0;


                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();


                    String date = sps.getString(Word_Game_Hard.this, "date");
                    int pos;
                    if (date.equals("0")) pos = 1;
                    else pos = 2;

                    myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                    myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");

                    // String date = sps.getString(Word_Game_Hard.this, "date");
                    if (date.equals("0")) if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                        startActivity(i);
                    } else finish();
                    else if (sps.getString(Word_Game_Hard.this, "Exp_list").equals("on")) {
                        finish();
                        Intent i = new Intent(Word_Game_Hard.this, Expandable_List_View.class);
                        startActivity(i);
                    } else if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                        startActivity(i);
                    } else finish();

                    openDialog_p.dismiss();
                });
                no.setOnClickListener(v -> openDialog_p.dismiss());
                openDialog_p.show();


            }

        }
    };
    // private MaxRewardedAd rewardedAd;
    // private MaxInterstitialAd mInterstitialAd;
    private RewardedAd rewardedAd;
    private AdManagerInterstitialAd interstitialAd;

    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word__game);
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");

        //uiHelper = new UiLifecycleHelper(this, callback);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);


        if (sps.getString(Word_Game_Hard.this, "new_user_db").equals("")) {

        } else if (sps.getString(Word_Game_Hard.this, "new_user_db").equals("on")) {
            sps.putString(Word_Game_Hard.this, "db_name_start", "Tamil_Game2.db");
            Commen_string.dbs_name = "Tamil_Game2.db";
        } else {
            sps.putString(Word_Game_Hard.this, "db_name_start", "Solli_Adi");
            Commen_string.dbs_name = "Solli_Adi";
        }

        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        myDbHelper = new DataBaseHelper(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);


        if (sps.getInt(Word_Game_Hard.this, "purchase_ads") == 1)
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        else {
            //fb_addload_score_screen(context);

            /**/
        }


        email = sps.getString(Word_Game_Hard.this, "email");


        //Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        if (sps.getInt(Word_Game_Hard.this, "purchase_ads") == 0) {
            // industrialload();
            if (!sps.getString(Word_Game_Hard.this, "InterstitialId").equals("") || sps.getString(Word_Game_Hard.this, "InterstitialId") != null) {
                industrialload();
            }
        }
        adds = findViewById(R.id.ads_lay);

        //Utills.INSTANCE.load_add_AppLovin(this, adds, getResources().getString(R.string.Bottom_Banner));
        if (Utils.isNetworkAvailable(context)) {
            if (!sps.getString(context, "BannerId").equals("") || sps.getString(context, "BannerId") != null) {
                System.out.println(
                        "Ads Should be not empty : " + sps.getString(context, "BannerId")
                );
                Utils.load_add_banner(context, sps.getString(context, "BannerId"), adds);
            }
        } else {
            System.out.println(
                    "Ads Should be -- empty : " + sps.getString(context, "BannerId")
            );
            adds.setVisibility(View.GONE);
        }


        if (!Utills.INSTANCE.isColumnExists(this, "answertable", "rd"))
            myDbHelper.executeSql("alter table answertable add column rd integer DEFAULT 0");

        if (!Utills.INSTANCE.isColumnExists(this, "answertable", "playtime"))
            myDbHelper.executeSql("alter table answertable add column playtime integer");

        if (!Utills.INSTANCE.isColumnExists(this, "answertable", "levelscore"))
            myDbHelper.executeSql("alter table answertable add column levelscore integer  DEFAULT 0");

        if (!Utills.INSTANCE.isColumnExists(this, "answertable", "useranswer"))
            myDbHelper.executeSql("alter table answertable add column useranswer integer");

        if (!Utills.INSTANCE.isColumnExists(this, "maintable", "playtime"))
            myDbHelper.executeSql("alter table maintable add column playtime integer DEFAULT 0");

        if (!Utills.INSTANCE.isColumnExists(this, "maintable", "noclue"))
            myDbHelper.executeSql("alter table maintable add column noclue integer DEFAULT 0");

        if (!Utills.INSTANCE.isColumnExists(this, "dailytest", "playtime"))
            myDbHelper.executeSql("alter table dailytest add column playtime integer DEFAULT 0");

        if (!Utills.INSTANCE.isColumnExists(this, "dailytest", "noclue"))
            myDbHelper.executeSql("alter table dailytest add column noclue integer DEFAULT 0");


        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Word_Game_Hard.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Word_Game_Hard.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Word_Game_Hard.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Word_Game_Hard.this, R.raw.coins, 1);
        cr_ans = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId5 = cr_ans.load(Word_Game_Hard.this, R.raw.cr_ans, 1);

        ImageView prize_logo = findViewById(R.id.prize_logo);
        if (sps.getInt(Word_Game_Hard.this, "remoteConfig_prize") == 1)
            prize_logo.setVisibility(View.VISIBLE);
        else prize_logo.setVisibility(View.GONE);
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable(this))
                if (sps.getString(Word_Game_Hard.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, Game_Status.class);
                    startActivity(i);
                } else if (sps.getString(Word_Game_Hard.this, "otp_verify").equals("yes")) {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, Price_Login.class);
                    startActivity(i);
                }
            else
                Toast.makeText(Word_Game_Hard.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
        });

///
        skip = findViewById(R.id.skip);
        progress = findViewById(R.id.progress);
        ex_bones = findViewById(R.id.ex_bones);
        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
        progress.setOnTouchListener((view, motionEvent) -> true);
        progress.setMax(100);
        ex_bones.setText("" + sps.getInt(Word_Game_Hard.this, "bones_prog"));
        //Sound ON OFF

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toggleButton = popupView.findViewById(R.id.toggle);
        String snd = sps.getString(Word_Game_Hard.this, "snd");
        settings = findViewById(R.id.settings);
        if (snd.equals("off")) {

            settings.setBackgroundResource(R.drawable.sound_off);
            //toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;

        } else if (snd.equals("on")) {

            settings.setBackgroundResource(R.drawable.sound_on);
            // toggleButton.setBackgroundResource(R.drawable.on);
            sv = 1;

        }


        // Toast.makeText(Word_Game_Hard.this, ""+f_sec, Toast.LENGTH_SHORT).show();
        openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.score_screen);
        adsicon = openDialog.findViewById(R.id.adsicon);

        /////////

        if (sps.getInt(Word_Game_Hard.this, "purchase_ads") == 1) {
        } else {
            //advancads();
        }

        ////////

        /////////

        if (sps.getInt(Word_Game_Hard.this, "purchase_ads") == 1) {
        } else {
            //advancads_content();
        }

        adsicon2 = findViewById(R.id.adsicon2);
        edit_buttons_layout = findViewById(R.id.edit_buttons_layout);
        head = findViewById(R.id.w_head);
        verify = findViewById(R.id.verify);
        clear = findViewById(R.id.clear);
        q1 = findViewById(R.id.value_ans1);
        q2 = findViewById(R.id.value_ans2);
        q3 = findViewById(R.id.value_ans3);
        q4 = findViewById(R.id.value_ans4);
        q5 = findViewById(R.id.value_ans5);
        q6 = findViewById(R.id.value_ans6);
        q7 = findViewById(R.id.value_ans7);
        q8 = findViewById(R.id.value_ans8);
        q9 = findViewById(R.id.value_ans9);
        q10 = findViewById(R.id.value_ans10);
        q11 = findViewById(R.id.value_ans11);
        q12 = findViewById(R.id.value_ans12);
        q13 = findViewById(R.id.value_ans13);
        q14 = findViewById(R.id.value_ans14);
        ans_set = findViewById(R.id.list1_pic);
        settings = findViewById(R.id.settings);
        word_editer = findViewById(R.id.ans_editer);
        bt1 = findViewById(R.id.button1);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
        bt4 = findViewById(R.id.button4);
        bt5 = findViewById(R.id.button5);
        bt6 = findViewById(R.id.button6);
        bt7 = findViewById(R.id.button7);
        bt8 = findViewById(R.id.button8);
        bt9 = findViewById(R.id.button9);
        bt10 = findViewById(R.id.button10);
        bt11 = findViewById(R.id.button11);
        bt12 = findViewById(R.id.button12);
        bt13 = findViewById(R.id.button13);
        bt14 = findViewById(R.id.button14);
        bt15 = findViewById(R.id.button15);
        bt16 = findViewById(R.id.button16);
        time = findViewById(R.id.word_time_edit);
        word_no = findViewById(R.id.word_number);
        score = findViewById(R.id.word_score_edit);
        feedback = findViewById(R.id.feedback);
        focus = findViewById(R.id.word_time_edit);

        w_head = findViewById(R.id.w_head);
        h_gplues = findViewById(R.id.h_gplues);
        h_watts_app = findViewById(R.id.h_watts_app);
        h_facebook = findViewById(R.id.h_facebook);
        qtw = findViewById(R.id.qtw);
        earncoin = findViewById(R.id.earncoin);

        bs_points = findViewById(R.id.bs_points);
        helpshare_layout = findViewById(R.id.helpshare_layout);


        if (sps.getString(Word_Game_Hard.this, "wn_intro").equals("yes")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Word_Game_Hard.this, "sequence example wn3");

            sequence.setConfig(config);

            sequence.addSequenceItem(q1, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            sequence.addSequenceItem(verify, "சரிபார்க்க பொத்தானை அழுத்தி விடையை சரிபார்த்துக்கொள்ளவும்.", "அடுத்து");

            sequence.addSequenceItem(ex_bones, "தொடர்ந்து சரியான  10 விடைகளை கண்டுபிடித்தால், கூடுதல் விடைகளை நாணயங்கள் குறையாமல் அறிந்து கொள்ளலாம்.", "அடுத்து");

            sequence.addSequenceItem(feedback, "கருத்துக்கள்  பொத்தானை அழுத்தி மேலும் உங்களுக்கு தெரிந்த விடைகளை எங்களுக்கு அனுப்பவும் .", "அடுத்து");
            //   sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Word_Game_Hard.this).setTarget(helpshare_layout).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener((itemView, position) -> {

                if (position == 4) {
                    sps.putString(Word_Game_Hard.this, "time_start", "yes");
                    sps.putString(Word_Game_Hard.this, "showcase_dismiss_wd", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            });
            sequence.start();
            sps.putString(Word_Game_Hard.this, "wn_intro", "no");

        }

        if (sps.getInt(Word_Game_Hard.this, "reward_coin_txt") == 0)
            sps.putInt(Word_Game_Hard.this, "reward_coin_txt", 20);

        vl1 = findViewById(R.id.ans1);
        vl2 = findViewById(R.id.ans2);
        vl3 = findViewById(R.id.ans3);
        vl4 = findViewById(R.id.ans4);
        vl5 = findViewById(R.id.ans5);
        vl6 = findViewById(R.id.ans6);
        vl7 = findViewById(R.id.ans7);
        vl8 = findViewById(R.id.ans8);
        vl9 = findViewById(R.id.ans9);
        vl10 = findViewById(R.id.ans10);
        vl11 = findViewById(R.id.ans11);
        vl12 = findViewById(R.id.ans12);
        vl13 = findViewById(R.id.ans13);
        vl14 = findViewById(R.id.ans14);


        Bundle extras;
        extras = getIntent().getExtras();
        if (extras != null) {

            String message = extras.getString("datee");


            try {
                if (message.equals("d")) {

                }
            } catch (Exception e) {
                e.printStackTrace();
                message = "no";
            }


            if (message.length() > 6) {
                sps.putString(Word_Game_Hard.this, "date", message);
                Cursor c = myDbHelper.getQry("select * from dailytest where  isfinish='0' and gameid='" + gameid + "' and date='" + message + "'");
                if (c.getCount() != 0) next();
                else {
                    Cursor c2 = myDbHelper.getQry("select * from dailytest where date=" + message + "");
                    c2.moveToFirst();
                    ///User Premission Showing
                    if (c2.getCount() != 0) next();
                    else downloaddata_daily();

                }
            } else {
                sps.putString(Word_Game_Hard.this, "date", "0");
                next();
            }


        } else {
            sps.putString(Word_Game_Hard.this, "date", "0");
            next();
        }


        settings.setOnClickListener(v -> {

            // settings.setBackgroundResource(R.drawable.sound_off);
            String snd1 = sps.getString(Word_Game_Hard.this, "snd");
            if (snd1.equals("off")) {
                sps.putString(Word_Game_Hard.this, "snd", "on");
                settings.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd1.equals("on")) {
                sps.putString(Word_Game_Hard.this, "snd", "off");
                settings.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }

            // settings();
        });
        earncoin.setOnClickListener(v -> dialog(0));
        bt1.setOnClickListener(v -> {
            // c1.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt1.startAnimation(shake);
            String ts = bt1.getText().toString();
            word_editer.append(ts);

        });
        bt2.setOnClickListener(v -> {
            // c2.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt2.startAnimation(shake);
            String ts = bt2.getText().toString();
            word_editer.append(ts);
        });
        bt3.setOnClickListener(v -> {
            // c3.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt3.startAnimation(shake);
            String ts = bt3.getText().toString();
            word_editer.append(ts);
        });
        bt5.setOnClickListener(v -> {
            // c4.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt5.startAnimation(shake);
            String ts = bt5.getText().toString();
            word_editer.append(ts);
        });
        bt6.setOnClickListener(v -> {
            //c5.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt6.startAnimation(shake);
            String ts = bt6.getText().toString();
            word_editer.append(ts);
        });
        bt7.setOnClickListener(v -> {
            //c6.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt7.startAnimation(shake);
            String ts = bt7.getText().toString();
            word_editer.append(ts);
        });
        bt9.setOnClickListener(v -> {
            // c7.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt9.startAnimation(shake);
            String ts = bt9.getText().toString();
            word_editer.append(ts);
        });
        bt10.setOnClickListener(v -> {
            //  c8.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt10.startAnimation(shake);
            String ts = bt10.getText().toString();
            word_editer.append(ts);
        });
        bt11.setOnClickListener(v -> {
            //c9.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt11.startAnimation(shake);
            String ts = bt11.getText().toString();
            word_editer.append(ts);
        });
        bt4.setOnClickListener(v -> {
            // c10.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt4.startAnimation(shake);
            String ts = bt4.getText().toString();
            word_editer.append(ts);
        });
        bt8.setOnClickListener(v -> {
            //c11.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt8.startAnimation(shake);
            String ts = bt8.getText().toString();
            word_editer.append(ts);
        });
        bt12.setOnClickListener(v -> {
            // c12.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt12.startAnimation(shake);
            String ts = bt12.getText().toString();
            word_editer.append(ts);

        });
        bt13.setOnClickListener(v -> {
            // c13.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt13.startAnimation(shake);
            String ts = bt13.getText().toString();
            word_editer.append(ts);
        });
        bt14.setOnClickListener(v -> {
            //c14.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt14.startAnimation(shake);
            String ts = bt14.getText().toString();
            word_editer.append(ts);
        });
        bt15.setOnClickListener(v -> {
            //c15.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt15.startAnimation(shake);
            String ts = bt15.getText().toString();
            word_editer.append(ts);
        });
        bt16.setOnClickListener(v -> {
            // c16.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.button_shake);
            bt16.startAnimation(shake);
            String ts = bt16.getText().toString();
            word_editer.append(ts);
        });
        feedback.setOnClickListener(v -> feedbackdialog());

        h_gplues.setOnClickListener(view -> {
            share_name = 3;
            String a = "com.google.android.apps.plus";
            permission(a);
        });
        h_watts_app.setOnClickListener(view -> {
            share_name = 2;
            String a = "com.whatsapp";
            permission(a);
        });
        h_facebook.setOnClickListener(view -> {
            share_name = 1;
            final String a = "com.facebook.katana";
            permission(a);
        });
        edit_buttons_layout.setOnClickListener(v -> {
            if (k == 2) {
                popupWindow.dismiss();
                k = 1;
            }
        });
        head.setOnClickListener(v -> {
            if (k == 2) {
                popupWindow.dismiss();
                k = 1;
            }
        });
        qtw.setOnClickListener(v -> dialog(0));

        word_editer.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(verify.getWindowToken(), 0);
        });
        word_editer.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(verify.getWindowToken(), 0);

            return true;
        });

        skip.setOnClickListener(v -> {
            focus.stop();
            String date = sps.getString(Word_Game_Hard.this, "date");
            if (date.equals("0")) {
                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "' and gameid='" + gameid + "'");
                next();
            } else Toast.makeText(Word_Game_Hard.this, "Not Available", Toast.LENGTH_SHORT).show();

        });


        verify.setOnClickListener(v -> {
            String ans = word_editer.getText().toString();
            if (ans.length() != 0) {
                Cursor cs = myDbHelper.getQry("select * from answertable where answer LIKE'" + ans + "'and isfinish='1'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                cs.moveToFirst();
                if (cs.getCount() != 0) {

                    cr_ans.play(soundId5, sv, sv, 0, 0, sv);
                    word_editer.setText("");

                    CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "பதிவு செய்துவிட்டீர்கள்", Snackbar.LENGTH_SHORT);
                    final View view = snackbar.getView();
                    TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
                    view.setBackgroundResource(R.drawable.answershow_green);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    //textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                    textView.setTextSize(17);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else textView.setGravity(Gravity.CENTER_HORIZONTAL);

                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    snackbar.show();

                } else {

                    Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where answer ='" + ans + "' and isfinish='0' and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");


                    if (cd != null && cd.getCount() != 0) {
                        cd.moveToFirst();
                        if (x <= tans) {


                            win.play(soundId3, sv, sv, 0, 0, sv);

                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + ans + "' and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=0 WHERE answer='" + ans + "' and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");

                            b_score = b_score + 10;
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx + 10;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                            ch.moveToFirst();
                            int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
                            int shh = sh + 10;
                            myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

                            sps.putInt(getApplicationContext(), "p_bar", sps.getInt(Word_Game_Hard.this, "p_bar") + 10);
                            System.out.println("%%%%pogress" + sps.getInt(Word_Game_Hard.this, "p_bar"));
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            if (sps.getInt(Word_Game_Hard.this, "p_bar") > 90) {
                                sps.putInt(getApplicationContext(), "bones_prog", sps.getInt(Word_Game_Hard.this, "bones_prog") + 1);
                                sps.putInt(getApplicationContext(), "p_bar", 0);
                                progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                                pluesanim();
                            }


                            // ans_selection(x,ans);

                            word_editer.setText("");
                            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);

                            if (vl1.length() == 0) {
                                vl1.setText(ans);
                                vl1.startAnimation(levels1);
                                q1.setBackgroundResource(R.drawable.tick_background);
                                q1.setClickable(false);
                                q2.setVisibility(View.VISIBLE);
                            } else if (vl2.length() == 0) {
                                vl2.setText(ans);
                                vl2.startAnimation(levels1);
                                q2.setBackgroundResource(R.drawable.tick_background);
                                q2.setClickable(false);
                                q3.setVisibility(View.VISIBLE);
                            } else if (vl3.length() == 0) {
                                vl3.setText(ans);
                                vl3.startAnimation(levels1);
                                q3.setBackgroundResource(R.drawable.tick_background);
                                q3.setClickable(false);
                                q4.setVisibility(View.VISIBLE);
                            } else if (vl4.length() == 0) {
                                vl4.setText(ans);
                                vl4.startAnimation(levels1);
                                q4.setBackgroundResource(R.drawable.tick_background);
                                q4.setClickable(false);
                                q5.setVisibility(View.VISIBLE);
                            } else if (vl5.length() == 0) {
                                vl5.setText(ans);
                                vl5.startAnimation(levels1);
                                q5.setBackgroundResource(R.drawable.tick_background);
                                q5.setClickable(false);
                                q6.setVisibility(View.VISIBLE);
                            } else if (vl6.length() == 0) {
                                vl6.setText(ans);
                                vl6.startAnimation(levels1);
                                q6.setBackgroundResource(R.drawable.tick_background);
                                q6.setClickable(false);
                                q7.setVisibility(View.VISIBLE);
                            } else if (vl7.length() == 0) {
                                vl7.setText(ans);
                                vl7.startAnimation(levels1);
                                q7.setBackgroundResource(R.drawable.tick_background);
                                q7.setClickable(false);

                                if (tans == 11) q8.setVisibility(View.VISIBLE);
                                if (tans == 15) q8.setVisibility(View.VISIBLE);

                            } else if (vl8.length() == 0) {
                                vl8.setText(ans);
                                vl8.startAnimation(levels1);
                                q8.setBackgroundResource(R.drawable.tick_background);
                                q8.setClickable(false);
                                q9.setVisibility(View.VISIBLE);
                            } else if (vl9.length() == 0) {
                                vl9.setText(ans);
                                vl9.startAnimation(levels1);
                                q9.setBackgroundResource(R.drawable.tick_background);
                                q9.setClickable(false);
                                q10.setVisibility(View.VISIBLE);
                            } else if (vl10.length() == 0) {
                                vl10.setText(ans);
                                vl10.startAnimation(levels1);
                                q10.setBackgroundResource(R.drawable.tick_background);
                                q10.setClickable(false);

                                if (tans == 15) q11.setVisibility(View.VISIBLE);

                            } else if (vl11.length() == 0) {
                                vl11.setText(ans);
                                vl11.startAnimation(levels1);
                                q11.setBackgroundResource(R.drawable.tick_background);
                                q11.setClickable(false);
                                q12.setVisibility(View.VISIBLE);
                            } else if (vl12.length() == 0) {
                                vl12.setText(ans);
                                vl12.startAnimation(levels1);
                                q12.setBackgroundResource(R.drawable.tick_background);
                                q12.setClickable(false);
                                q13.setVisibility(View.VISIBLE);
                            } else if (vl13.length() == 0) {
                                vl13.setText(ans);
                                vl13.startAnimation(levels1);
                                q13.setBackgroundResource(R.drawable.tick_background);
                                q13.setClickable(false);
                                q14.setVisibility(View.VISIBLE);
                            } else if (vl14.length() == 0) {
                                vl14.setText(ans);
                                vl14.startAnimation(levels1);
                                q14.setBackgroundResource(R.drawable.tick_background);
                                q14.setClickable(false);

                            }


                            x++;


                        }
                        if (x >= tans) {
                            verify.setVisibility(View.INVISIBLE);
                            ex_bones.setVisibility(View.INVISIBLE);

                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "' and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "' and gameid='" + gameid + "'");
                            completegame();

                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }
                    } else {

                        worng.play(soundId2, sv, sv, 0, 0, sv);
                        word_editer.setText("");
                        CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
                        Snackbar snackbar = Snackbar.make(coordinatorLayout, "சரியான விடையாக இருக்கலாம் .\n ஆனால் எங்கள் தொகுப்பில் இல்லை ", Snackbar.LENGTH_SHORT);
                        final View view = snackbar.getView();
                        TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
                        view.setBackgroundResource(R.drawable.answershow);
                        textView.setTextColor(Color.parseColor("#FFFFFF"));
                        //  textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                        textView.setTextSize(17);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        else textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        snackbar.show();
                    }
                }
            } else {

                worng.play(soundId2, sv, sv, 0, 0, sv);
                CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "எழுத்துக்களை நிரப்பவும்", Snackbar.LENGTH_SHORT);
                final View view = snackbar.getView();
                TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
                view.setBackgroundResource(R.drawable.answershow);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                //  textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                textView.setTextSize(19);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                else textView.setGravity(Gravity.CENTER_HORIZONTAL);
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                params.gravity = Gravity.TOP;
                view.setLayoutParams(params);
                snackbar.show();
            }
        });


        q1.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {

                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");


                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl1.setText(sa);
                        vl1.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q1.setBackgroundResource(R.drawable.tick_background);
                        q1.setClickable(false);

                        q2.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }
                }

            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });

                yes.setOnClickListener(v128 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1 ");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");


                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///
                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl1.setText(sa);
                            vl1.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q1.setBackgroundResource(R.drawable.tick_background);
                            q1.setClickable(false);

                            q2.setVisibility(View.VISIBLE);
                            x++;
                            openDialog.dismiss();


                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);

                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }
                    }
                });
                no.setOnClickListener(v127 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q2.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl2.setText(sa);
                        vl2.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q2.setBackgroundResource(R.drawable.tick_background);
                        q2.setClickable(false);

                        q3.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v126 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl2.setText(sa);
                            vl2.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q2.setBackgroundResource(R.drawable.tick_background);
                            q2.setClickable(false);

                            q3.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v125 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q3.setOnClickListener(v -> {

            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);


                        vl3.setText(sa);
                        vl3.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q3.setBackgroundResource(R.drawable.tick_background);
                        q3.setClickable(false);

                        q4.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v124 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");


                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl3.setText(sa);
                            vl3.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q3.setBackgroundResource(R.drawable.tick_background);
                            q3.setClickable(false);

                            q4.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> {
                                sps.putString(getApplicationContext(), "checkbox_ans", "");
                                adShow();

                            }, 2000);
                        }

                    }
                });
                no.setOnClickListener(v123 -> openDialog.dismiss());
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q4.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "'");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);
                        vl4.setText(sa);
                        vl4.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q4.setBackgroundResource(R.drawable.tick_background);
                        q4.setClickable(false);

                        q5.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v122 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);
                            vl4.setText(sa);
                            vl4.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q4.setBackgroundResource(R.drawable.tick_background);
                            q4.setClickable(false);

                            q5.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v121 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q5.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl5.setText(sa);
                        vl5.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q5.setBackgroundResource(R.drawable.tick_background);
                        q5.setClickable(false);

                        q6.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v120 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl5.setText(sa);
                            vl5.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q5.setBackgroundResource(R.drawable.tick_background);
                            q5.setClickable(false);

                            q6.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v119 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q6.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);

                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);
                        vl6.setText(sa);
                        vl6.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q6.setBackgroundResource(R.drawable.tick_background);
                        q6.setClickable(false);

                        q7.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v118 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);

                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);
                            vl6.setText(sa);
                            vl6.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q6.setBackgroundResource(R.drawable.tick_background);
                            q6.setClickable(false);

                            q7.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);

                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v117 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q7.setOnClickListener(v -> {

            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl7.setText(sa);
                        vl7.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q7.setBackgroundResource(R.drawable.tick_background);
                        q7.setClickable(false);

                        if (tans == 11) q8.setVisibility(View.VISIBLE);
                        if (tans == 15) q8.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v116 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl7.setText(sa);
                            vl7.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q7.setBackgroundResource(R.drawable.tick_background);
                            q7.setClickable(false);

                            if (tans == 11) q8.setVisibility(View.VISIBLE);
                            if (tans == 15) q8.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            update_price();
                            focus.stop();

                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v115 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q8.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl8.setText(sa);
                        vl8.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q8.setBackgroundResource(R.drawable.tick_background);
                        q8.setClickable(false);

                        q9.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();

                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v114 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl8.setText(sa);
                            vl8.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q8.setBackgroundResource(R.drawable.tick_background);
                            q8.setClickable(false);

                            q9.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();

                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v113 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });


        q9.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl9.setText(sa);
                        vl9.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q9.setBackgroundResource(R.drawable.tick_background);
                        q9.setClickable(false);

                        q10.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        update_price();

                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v112 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl9.setText(sa);
                            vl9.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q9.setBackgroundResource(R.drawable.tick_background);
                            q9.setClickable(false);

                            q10.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);

                            focus.stop();
                            update_price();

                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v111 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q10.setOnClickListener(v -> {

            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");


                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl10.setText(sa);
                        vl10.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q10.setBackgroundResource(R.drawable.tick_background);
                        q10.setClickable(false);
                        if (tans == 15) q11.setVisibility(View.VISIBLE);

                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        update_price();

                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v110 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl10.setText(sa);
                            vl10.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q10.setBackgroundResource(R.drawable.tick_background);
                            q10.setClickable(false);

                            if (tans == 15) q11.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();

                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v19 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q11.setOnClickListener(v -> {

            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl11.setText(sa);
                        vl11.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q11.setBackgroundResource(R.drawable.tick_background);
                        q11.setClickable(false);


                        q12.setVisibility(View.VISIBLE);


                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v18 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///
                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl11.setText(sa);
                            vl11.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q11.setBackgroundResource(R.drawable.tick_background);
                            q11.setClickable(false);

                            q12.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);

                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v17 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q12.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl12.setText(sa);
                        vl12.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q12.setBackgroundResource(R.drawable.tick_background);
                        q12.setClickable(false);

                        q13.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v16 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///
                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl12.setText(sa);
                            vl12.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q12.setBackgroundResource(R.drawable.tick_background);
                            q12.setClickable(false);

                            q13.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v15 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q13.setOnClickListener(v -> {

            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl13.setText(sa);
                        vl13.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q13.setBackgroundResource(R.drawable.tick_background);
                        q13.setClickable(false);

                        q14.setVisibility(View.VISIBLE);
                        x++;


                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();

                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v14 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl13.setText(sa);
                            vl13.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q13.setBackgroundResource(R.drawable.tick_background);
                            q13.setClickable(false);

                            q14.setVisibility(View.VISIBLE);
                            x++;

                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);

                            focus.stop();
                            update_price();

                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v13 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);
        });
        q14.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk > 50) if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        ///progress bar
                        sps.putInt(getApplicationContext(), "p_bar", 0);
                        progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                        ///

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);

                        vl14.setText(sa);
                        vl14.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q14.setBackgroundResource(R.drawable.tick_background);
                        q14.setClickable(false);
                        x++;

                    }
                    if (x == tans) {
                        verify.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();
                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }

                }
            } else {
                final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    if (isChecked) sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                    else sps.putString(getApplicationContext(), "checkbox_ans", "");
                });
                yes.setOnClickListener(v12 -> {
                    Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                    cd.moveToFirst();
                    if (cd.getCount() != 0) {
                        if (x <= tans) {
                            String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                            myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                            myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");


                            ///progress bar
                            sps.putInt(getApplicationContext(), "p_bar", 0);
                            progress.setProgress(sps.getInt(Word_Game_Hard.this, "p_bar"));
                            ///

                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            vl14.setText(sa);
                            vl14.setTextColor(getResources().getColor(R.color.rippelColor1));
                            q14.setBackgroundResource(R.drawable.tick_background);
                            q14.setClickable(false);
                            x++;
                            openDialog.dismiss();
                        }
                        if (x == tans) {
                            verify.setVisibility(View.INVISIBLE);
                            focus.stop();
                            update_price();
                            String date = sps.getString(Word_Game_Hard.this, "date");
                            if (date.equals("0"))
                                myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            else
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                            completegame();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                });
                no.setOnClickListener(v1 -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }
            else dialog(1);


        });

        // sps.putInt(Word_Game_Hard.this,"bones_prog",300);
        ex_bones.setOnClickListener(v -> {

            if (sps.getInt(Word_Game_Hard.this, "bones_prog") != 0) {
                sps.putInt(getApplicationContext(), "bones_prog", sps.getInt(Word_Game_Hard.this, "bones_prog") - 1);
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {

                    if (x <= tans) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=0 WHERE answer='" + sa + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        bones_ans(sa);
                        ex_bones.setText("" + sps.getInt(Word_Game_Hard.this, "bones_prog"));
                        b_score = b_score + 10;
                        x++;
                    }
                    if (x >= tans) {
                        verify.setVisibility(View.INVISIBLE);
                        ex_bones.setVisibility(View.INVISIBLE);

                        focus.stop();
                        update_price();

                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0"))
                            myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        else
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                        completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
                    }
                }
            } else
                Toast.makeText(Word_Game_Hard.this, "தொடர்ந்து சரியான  10 விடைகளை கண்டுபிடித்தால், கூடுதல் விடைகளை நாணயங்கள் குறையாமல் அறிந்து கொள்ளலாம்.", Toast.LENGTH_SHORT).show();

        });

        clear.setOnClickListener(v -> pressKey());
        clear.setOnLongClickListener(v -> {
            word_editer.setText("");
            return false;
        });
    }

    private void pluesanim() {

        bs_points.setVisibility(View.VISIBLE);
        int[] locationInWindow = new int[2];
        bs_points.getLocationInWindow(locationInWindow);
        int[] locationOnScreen = new int[2];
        bs_points.getLocationOnScreen(locationOnScreen);
        float sourceX = locationOnScreen[0];
        float sourceY = locationOnScreen[1];
        int[] locationInWindowSecond = new int[2];
        ex_bones.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        ex_bones.getLocationOnScreen(locationOnScreenSecond);
        float destinationX = locationOnScreenSecond[0];
        float destinationY = locationOnScreenSecond[1];
        TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
        transAnimation.setDuration(800);
        bs_points.startAnimation(transAnimation);
        bs_points.postDelayed(() -> {
            ex_bones.setText("" + sps.getInt(Word_Game_Hard.this, "bones_prog"));
            bs_points.setVisibility(View.INVISIBLE);
        }, transAnimation.getDuration());

    }

    private void bones_ans(String ans) {

        word_editer.setText("");
        Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
        if (vl1.length() == 0) {
            vl1.setText(ans);
            vl1.startAnimation(levels1);
            q1.setBackgroundResource(R.drawable.tick_background);
            q1.setClickable(false);
            q2.setVisibility(View.VISIBLE);
        } else if (vl2.length() == 0) {
            vl2.setText(ans);
            vl2.startAnimation(levels1);
            q2.setBackgroundResource(R.drawable.tick_background);
            q2.setClickable(false);
            q3.setVisibility(View.VISIBLE);
        } else if (vl3.length() == 0) {
            vl3.setText(ans);
            vl3.startAnimation(levels1);
            q3.setBackgroundResource(R.drawable.tick_background);
            q3.setClickable(false);
            q4.setVisibility(View.VISIBLE);
        } else if (vl4.length() == 0) {
            vl4.setText(ans);
            vl4.startAnimation(levels1);
            q4.setBackgroundResource(R.drawable.tick_background);
            q4.setClickable(false);
            q5.setVisibility(View.VISIBLE);
        } else if (vl5.length() == 0) {
            vl5.setText(ans);
            vl5.startAnimation(levels1);
            q5.setBackgroundResource(R.drawable.tick_background);
            q5.setClickable(false);
            q6.setVisibility(View.VISIBLE);
        } else if (vl6.length() == 0) {
            vl6.setText(ans);
            vl6.startAnimation(levels1);
            q6.setBackgroundResource(R.drawable.tick_background);
            q6.setClickable(false);
            q7.setVisibility(View.VISIBLE);
        } else if (vl7.length() == 0) {
            vl7.setText(ans);
            vl7.startAnimation(levels1);
            q7.setBackgroundResource(R.drawable.tick_background);
            q7.setClickable(false);

            if (tans == 11) q8.setVisibility(View.VISIBLE);
            if (tans == 15) q8.setVisibility(View.VISIBLE);

        } else if (vl8.length() == 0) {
            vl8.setText(ans);
            vl8.startAnimation(levels1);
            q8.setBackgroundResource(R.drawable.tick_background);
            q8.setClickable(false);
            q9.setVisibility(View.VISIBLE);
        } else if (vl9.length() == 0) {
            vl9.setText(ans);
            vl9.startAnimation(levels1);
            q9.setBackgroundResource(R.drawable.tick_background);
            q9.setClickable(false);
            q10.setVisibility(View.VISIBLE);
        } else if (vl10.length() == 0) {
            vl10.setText(ans);
            vl10.startAnimation(levels1);
            q10.setBackgroundResource(R.drawable.tick_background);
            q10.setClickable(false);

            if (tans == 15) q11.setVisibility(View.VISIBLE);

        } else if (vl11.length() == 0) {
            vl11.setText(ans);
            vl11.startAnimation(levels1);
            q11.setBackgroundResource(R.drawable.tick_background);
            q11.setClickable(false);
            q12.setVisibility(View.VISIBLE);
        } else if (vl12.length() == 0) {
            vl12.setText(ans);
            vl12.startAnimation(levels1);
            q12.setBackgroundResource(R.drawable.tick_background);
            q12.setClickable(false);
            q13.setVisibility(View.VISIBLE);
        } else if (vl13.length() == 0) {
            vl13.setText(ans);
            vl13.startAnimation(levels1);
            q13.setBackgroundResource(R.drawable.tick_background);
            q13.setClickable(false);
            q14.setVisibility(View.VISIBLE);
        } else if (vl14.length() == 0) {
            vl14.setText(ans);
            vl14.startAnimation(levels1);
            q14.setBackgroundResource(R.drawable.tick_background);
            q14.setClickable(false);

        }
    }

    private void feedbackdialog() {


        final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.userfeedback);
        usertxt = openDialog.findViewById(R.id.usertxt);
        final TextView u_ans = openDialog.findViewById(R.id.u_ans);
        final TextView bt1 = openDialog.findViewById(R.id.button1);
        final TextView bt2 = openDialog.findViewById(R.id.button2);
        final TextView bt3 = openDialog.findViewById(R.id.button3);
        final TextView bt4 = openDialog.findViewById(R.id.button4);
        final TextView bt5 = openDialog.findViewById(R.id.button5);
        final TextView bt6 = openDialog.findViewById(R.id.button6);
        final TextView bt7 = openDialog.findViewById(R.id.button7);
        final TextView bt8 = openDialog.findViewById(R.id.button8);
        final TextView bt9 = openDialog.findViewById(R.id.button9);
        final TextView bt10 = openDialog.findViewById(R.id.button10);
        final TextView bt11 = openDialog.findViewById(R.id.button11);
        final TextView bt12 = openDialog.findViewById(R.id.button12);
        final TextView nextspace = openDialog.findViewById(R.id.nextspace);
        final TextView bt14 = openDialog.findViewById(R.id.button14);
        final TextView clear = openDialog.findViewById(R.id.clear);
        final TextView sends = openDialog.findViewById(R.id.sends);
        final TextView cancel = openDialog.findViewById(R.id.feed_close);

        openDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        String ans1 = vl1.getText().toString();
        if (ans1.length() != 0) u_ans.append(ans1);
        String ans2 = vl2.getText().toString();
        if (ans2.length() != 0) u_ans.append("," + ans2);

        String ans3 = vl3.getText().toString();
        if (ans3.length() != 0) u_ans.append("," + ans3);

        String ans4 = vl4.getText().toString();
        if (ans4.length() != 0) u_ans.append("," + ans4);

        String ans5 = vl5.getText().toString();
        if (ans5.length() != 0) u_ans.append("," + ans5);

        String ans6 = vl6.getText().toString();
        if (ans6.length() != 0) u_ans.append("," + ans6);

        String ans7 = vl7.getText().toString();
        if (ans7.length() != 0) u_ans.append("," + ans7);

        String ans8 = vl8.getText().toString();
        if (ans8.length() != 0) u_ans.append("," + ans8);

        String ans9 = vl9.getText().toString();
        if (ans9.length() != 0) u_ans.append("," + ans9);

        String ans10 = vl10.getText().toString();
        if (ans10.length() != 0) u_ans.append("," + ans10);

        String ans11 = vl11.getText().toString();
        if (ans11.length() != 0) u_ans.append("," + ans11);

        String ans12 = vl12.getText().toString();
        if (ans12.length() != 0) u_ans.append("," + ans12);

        String ans13 = vl13.getText().toString();
        if (ans13.length() != 0) u_ans.append("," + ans13);

        String ans14 = vl14.getText().toString();
        if (ans14.length() != 0) u_ans.append("," + ans14);


        Cursor c;
        String date = sps.getString(Word_Game_Hard.this, "date");
        if (date.equals("0")) {
            c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "' and levelid='" + letterid + "'");
            c.moveToFirst();//random() limit
        } else {
            c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and levelid='" + letterid + "'");
            c.moveToFirst();
        }

        if (c.getCount() != 0) {
            String sa = c.getString(c.getColumnIndexOrThrow("letters"));
            if (c.getCount() != 0) {

                StringTokenizer tokenizer = new StringTokenizer(sa, ",");
                final String letter1 = tokenizer.nextToken();
                String letter2 = tokenizer.nextToken().trim();
                String letter3 = tokenizer.nextToken().trim();
                String letter4 = tokenizer.nextToken().trim();
                String letter5 = tokenizer.nextToken().trim();
                String letter6 = tokenizer.nextToken().trim();
                String letter7 = tokenizer.nextToken().trim();
                String letter8 = tokenizer.nextToken().trim();
                String letter9 = tokenizer.nextToken().trim();

                bt1.setText(letter1);
                bt2.setText(letter2);
                bt3.setText(letter3);
                bt5.setText(letter4);
                bt6.setText(letter5);
                bt7.setText(letter6);
                bt9.setText(letter7);
                bt10.setText(letter8);
                bt11.setText(letter9);
            }

        }
        bt1.setOnClickListener(v -> {

            String ts = bt1.getText().toString();
            usertxt.append(ts);

        });
        bt2.setOnClickListener(v -> {

            String ts = bt2.getText().toString();
            usertxt.append(ts);
        });
        bt3.setOnClickListener(v -> {

            String ts = bt3.getText().toString();
            usertxt.append(ts);
        });
        bt5.setOnClickListener(v -> {

            String ts = bt5.getText().toString();
            usertxt.append(ts);
        });
        bt6.setOnClickListener(v -> {

            String ts = bt6.getText().toString();
            usertxt.append(ts);
        });
        bt7.setOnClickListener(v -> {

            String ts = bt7.getText().toString();
            usertxt.append(ts);
        });
        bt9.setOnClickListener(v -> {

            String ts = bt9.getText().toString();
            usertxt.append(ts);
        });
        bt10.setOnClickListener(v -> {

            String ts = bt10.getText().toString();
            usertxt.append(ts);
        });
        bt11.setOnClickListener(v -> {

            String ts = bt11.getText().toString();
            usertxt.append(ts);
        });

        bt4.setOnClickListener(v -> {

            String ts = bt4.getText().toString();
            usertxt.append(ts);
        });
        bt8.setOnClickListener(v -> {

            String ts = bt8.getText().toString();
            usertxt.append(ts);
        });
        bt12.setOnClickListener(v -> {

            String ts = bt12.getText().toString();
            usertxt.append(ts);

        });
        bt13.setOnClickListener(v -> {

            String ts = bt13.getText().toString();
            usertxt.append(ts);
        });
        bt14.setOnClickListener(v -> {

            String ts = bt14.getText().toString();
            usertxt.append(ts);
        });
        bt15.setOnClickListener(v -> {

            String ts = bt15.getText().toString();
            usertxt.append(ts);
        });
        bt16.setOnClickListener(v -> {

            String ts = bt16.getText().toString();
            usertxt.append(ts);
        });

        cancel.setOnClickListener(v -> openDialog.dismiss());
        sends.setOnClickListener(v -> {

            if (usertxt.length() != 0) {
                Cursor cs = myDbHelper.getQry("select * from answertable where answer LIKE'" + usertxt.getText().toString() + "' and levelid='" + letterid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    usertxt.setText("");
                    Utils.toast_normal(Word_Game_Hard.this, "இந்த வார்த்தை எங்களது  தொகுப்பில் உள்ளது.வேறு வார்த்தையை பதிவிடவும்.");
                } else if (usertxt.getText().toString().length() == 0)
                    Utils.toast_normal(Word_Game_Hard.this, "புதிய வார்த்தைகளை பதிவு செய்து அனுப்பவும். ");
                else if (isNetworkAvailable(Word_Game_Hard.this)) {

                    final Handler handler = new Handler(Looper.myLooper()) {
                        public void handleMessage(Message msg) {
                            Runnable runnable = () -> {

                            };
                            runOnUiThread(runnable);
                        }
                    };
                    Thread checkUpdate = new Thread() {
                        public void run() {
                            try {
                                send_extraword(usertxt.getText().toString());
                            } catch (Exception e) {

                            }
                            handler.sendEmptyMessage(0);
                        }
                    };
                    checkUpdate.start();

                    Utils.toast_normal(Word_Game_Hard.this, "கருத்துக்கள்  அனுப்பப்பட்டது. நன்றி.");
                    openDialog.dismiss();

                } else Utils.toast_normal(Word_Game_Hard.this, "இணையதள சேவையை சரிபார்க்கவும்.");
            }


        });
        nextspace.setOnClickListener(v -> usertxt.append(","));
        clear.setOnClickListener(v -> dialogkey());
        clear.setOnLongClickListener(v -> {
            usertxt.setText("");
            return false;
        });

        u_ans.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(u_ans.getWindowToken(), 0);
        });
        u_ans.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(u_ans.getWindowToken(), 0);

            return true;
        });

        if (!isFinishing()) openDialog.show();
    }

    private void pressKey() {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL);
        word_editer.onKeyDown(KeyEvent.KEYCODE_DEL, event);


    }

    private void dialogkey() {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL);
        usertxt.onKeyDown(KeyEvent.KEYCODE_DEL, event);
    }

    private void showPopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        String snd = sps.getString(Word_Game_Hard.this, "snd");
        toggleButton = popupView.findViewById(R.id.toggle);
        if (snd.equals("off")) toggleButton.setBackgroundResource(R.drawable.off);
        else if (snd.equals("on")) toggleButton.setBackgroundResource(R.drawable.on);
        Button cancel = popupView.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> openDialog.dismiss());

        toggleButton.setOnClickListener(v -> {
            String snd1 = sps.getString(Word_Game_Hard.this, "snd");

            if (snd1.equals("off")) {

                System.out.println("*****on");
                sps.putString(Word_Game_Hard.this, "snd", "on");
                toggleButton.setBackgroundResource(R.drawable.on);
                sv = 1;


            }
            if (snd1.equals("on")) {
                System.out.println("*****off");
                sps.putString(Word_Game_Hard.this, "snd", "off");
                toggleButton.setBackgroundResource(R.drawable.off);
                sv = 0;


            }


        });

        popupWindow.showAsDropDown(settings, 50, -10);


    }

    //Help_Share
    private void helpshare(String a) {


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Bitmap bitmap = Bitmap.createBitmap(w_head.getWidth(), w_head.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        w_head.draw(canvas);


        String root = getFilesDir().toString();
        File mydir = new File(root + "/Nithra/Tamil_Odu_Viliyadu");
        mydir.mkdirs();
        String fname = "Tamil_Odu_Viliyadu.jpg";
        final File file = new File(mydir, fname);

        if (file.exists()) file.delete();

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            if (file.exists()) {
                final boolean appinstalled = appInstalledOrNot(a);
                if (appinstalled) {

                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();


                    String date = sps.getString(Word_Game_Hard.this, "date");
                    int pos;
                    if (date.equals("0")) pos = 1;
                    else pos = 2;

                    myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                    myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");


                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Word_Game_Hard.this, Word_Game_Hard.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT, "சொல்லிஅடி");
                    //  share.putExtra(android.content.Intent.EXTRA_TEXT,"Shared via Tamil Calendar Offline.\nClick here to download"+ "\nhttps://goo.gl/ITvWGu");
                    startActivity(Intent.createChooser(share, "Share Card Using"));
                } else {

                    CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "இந்த செயலி தங்களிடம் இல்லை", Snackbar.LENGTH_SHORT);
                    final View view = snackbar.getView();
                    TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
                    view.setBackgroundResource(R.drawable.answershow);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                    textView.setTextSize(17);
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    snackbar.show();
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextfont() {

        //Font Setting

        bt1.setTypeface(typ);
        bt2.setTypeface(typ);
        bt3.setTypeface(typ);
        bt4.setTypeface(typ);
        bt5.setTypeface(typ);
        bt6.setTypeface(typ);
        bt7.setTypeface(typ);
        bt8.setTypeface(typ);
        bt9.setTypeface(typ);
        bt10.setTypeface(typ);
        bt11.setTypeface(typ);
        bt12.setTypeface(typ);
        vl1.setTypeface(typ);
        vl2.setTypeface(typ);
        vl3.setTypeface(typ);
        vl4.setTypeface(typ);
        vl5.setTypeface(typ);
        vl6.setTypeface(typ);
        vl7.setTypeface(typ);
        vl8.setTypeface(typ);
        vl9.setTypeface(typ);
        vl10.setTypeface(typ);
        vl11.setTypeface(typ);
        vl12.setTypeface(typ);
        vl13.setTypeface(typ);
        vl14.setTypeface(typ);
        word_editer.setTypeface(typ);
        score.setTypeface(typ);
        verify.setTypeface(typ);
        focus.setTypeface(typ);
    }

    public void next() {


        Calendar calendar3 = Calendar.getInstance();
        int cur_year1 = calendar3.get(Calendar.YEAR);
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        String str_month1 = "" + (cur_month1 + 1);
        if (str_month1.length() == 1) str_month1 = "0" + str_month1;

        String str_day1 = "" + cur_day1;
        if (str_day1.length() == 1) str_day1 = "0" + str_day1;
        final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;


        sps.putString(Word_Game_Hard.this, "watts_app", "");
        sps.putString(Word_Game_Hard.this, "watts_app_s", "");
        sps.putString(Word_Game_Hard.this, "gplues", "yes");
        sps.putString(Word_Game_Hard.this, "face_share", "");

        w_head.setVisibility(View.VISIBLE);
        verify.setVisibility(View.VISIBLE);
        ex_bones.setVisibility(View.VISIBLE);
        //second game layout visibility
        head.setVisibility(View.VISIBLE);
        bs_points.setVisibility(View.INVISIBLE);
        LinearLayout linearLayout;
        linearLayout = findViewById(R.id.anslist2);
        linearLayout.setVisibility(View.VISIBLE);
        LinearLayout linearLayout1;
        linearLayout1 = findViewById(R.id.list2_pic);
        linearLayout1.setVisibility(View.VISIBLE);

        //
        vl1.setVisibility(View.INVISIBLE);
        vl2.setVisibility(View.INVISIBLE);
        vl3.setVisibility(View.INVISIBLE);
        vl4.setVisibility(View.INVISIBLE);
        vl5.setVisibility(View.INVISIBLE);
        vl6.setVisibility(View.INVISIBLE);
        vl7.setVisibility(View.INVISIBLE);
        vl8.setVisibility(View.INVISIBLE);
        vl9.setVisibility(View.INVISIBLE);
        vl10.setVisibility(View.INVISIBLE);
        vl11.setVisibility(View.INVISIBLE);
        vl12.setVisibility(View.INVISIBLE);
        vl13.setVisibility(View.INVISIBLE);
        vl14.setVisibility(View.INVISIBLE);


        q1.setVisibility(View.VISIBLE);
        q2.setVisibility(View.INVISIBLE);
        q3.setVisibility(View.INVISIBLE);
        q4.setVisibility(View.INVISIBLE);
        q5.setVisibility(View.INVISIBLE);
        q6.setVisibility(View.INVISIBLE);
        q7.setVisibility(View.INVISIBLE);
        q8.setVisibility(View.INVISIBLE);
        q9.setVisibility(View.INVISIBLE);
        q10.setVisibility(View.INVISIBLE);
        q11.setVisibility(View.INVISIBLE);
        q12.setVisibility(View.INVISIBLE);
        q13.setVisibility(View.INVISIBLE);
        q14.setVisibility(View.INVISIBLE);


    /*    if (sps.getString(Word_Game_Hard.this, "time_start").equals(""))
        {
            sps.putString(Word_Game_Hard.this, "time_start", "yes");

        }else
        {

            *//*
         *//*
        }
*/

        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;

        x = 1;
        String date = sps.getString(Word_Game_Hard.this, "date");


        // myDbHelper.executeSql("DELETE FROM answertable");

        if (date.equals("0")) {
            Cursor c1 = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'");
            if (c1 != null) {
                c1.moveToFirst();

                Cursor c2 = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "' and isfinish='1'");
                c2.moveToFirst();
                int count1 = c2.getCount() + 1;
                String no = String.valueOf(count1);
                word_no.setText(no/*+"/"+c1.getCount()*/);
            } else return;
        } else {
            if (sps.getInt(Word_Game_Hard.this, "purchase_ads") != 1) {
                sps.putInt(context, "addloded_rect_bck", 0);
                sps.putInt(context, "addloded_rect_mul", 0);

            }


            String tfoption = date;
            String[] first = tfoption.split("-");
            word_no.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            word_no.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }

        LinearLayout bl1 = findViewById(R.id.buttonlist1_layout);
        LinearLayout bl2 = findViewById(R.id.buttonlist2_layout);
        LinearLayout bl3 = findViewById(R.id.buttonlist3_layout);
        LinearLayout bl4 = findViewById(R.id.buttonlist4_layout);


        word_editer.setText("");
        bt1.setText("");
        bt2.setText("");
        bt3.setText("");
        bt5.setText("");
        bt6.setText("");
        bt7.setText("");
        bt9.setText("");
        bt10.setText("");
        bt11.setText("");
        bt4.setText("");
        bt8.setText("");
        bt12.setText("");
        bt13.setText("");
        bt14.setText("");
        bt15.setText("");
        bt16.setText("");
        vl1.setText("");
        q1.setBackgroundResource(R.drawable.yellow_question);
        vl2.setText("");
        q2.setBackgroundResource(R.drawable.yellow_question);
        vl3.setText("");
        q3.setBackgroundResource(R.drawable.yellow_question);
        vl4.setText("");
        q4.setBackgroundResource(R.drawable.yellow_question);
        vl5.setText("");
        q5.setBackgroundResource(R.drawable.yellow_question);
        vl6.setText("");
        q6.setBackgroundResource(R.drawable.yellow_question);
        vl7.setText("");
        q7.setBackgroundResource(R.drawable.yellow_question);
        vl8.setText("");
        q8.setBackgroundResource(R.drawable.yellow_question);
        vl9.setText("");
        q9.setBackgroundResource(R.drawable.yellow_question);
        vl10.setText("");
        q10.setBackgroundResource(R.drawable.yellow_question);
        vl11.setText("");
        q11.setBackgroundResource(R.drawable.yellow_question);
        vl12.setText("");
        q12.setBackgroundResource(R.drawable.yellow_question);
        vl13.setText("");
        q13.setBackgroundResource(R.drawable.yellow_question);
        vl14.setText("");
        q14.setBackgroundResource(R.drawable.yellow_question);

        vl1.setTextColor(getResources().getColor(R.color.white));
        vl2.setTextColor(getResources().getColor(R.color.white));
        vl3.setTextColor(getResources().getColor(R.color.white));
        vl4.setTextColor(getResources().getColor(R.color.white));
        vl5.setTextColor(getResources().getColor(R.color.white));
        vl6.setTextColor(getResources().getColor(R.color.white));
        vl7.setTextColor(getResources().getColor(R.color.white));
        vl8.setTextColor(getResources().getColor(R.color.white));
        vl9.setTextColor(getResources().getColor(R.color.white));
        vl10.setTextColor(getResources().getColor(R.color.white));
        vl11.setTextColor(getResources().getColor(R.color.white));
        vl12.setTextColor(getResources().getColor(R.color.white));
        vl13.setTextColor(getResources().getColor(R.color.white));
        vl14.setTextColor(getResources().getColor(R.color.white));

        q1.setClickable(true);
        q2.setClickable(true);
        q3.setClickable(true);
        q4.setClickable(true);
        q5.setClickable(true);
        q6.setClickable(true);
        q7.setClickable(true);
        q8.setClickable(true);
        q9.setClickable(true);
        q10.setClickable(true);
        q11.setClickable(true);
        q12.setClickable(true);
        q13.setClickable(true);
        q14.setClickable(true);


        Cursor c;
        if (date.equals("0")) {

            c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {

            c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
            c.moveToFirst();

        }

        if (c.getCount() != 0) {
            u_id = c.getInt(c.getColumnIndexOrThrow("id"));
            String sa = c.getString(c.getColumnIndexOrThrow("letters"));
            letterid = c.getString(c.getColumnIndexOrThrow("levelid"));
            answers = c.getString(c.getColumnIndexOrThrow("answer"));

            //splitting answer from main table and inserting into answer table

            //daily bonus
            if (sps.getString(Word_Game_Hard.this, str_date1).equals("")) {
                daily_bones();
                sps.putString(Word_Game_Hard.this, str_date1, "yes");

            }

            String tfoption = answers;
            String[] first = tfoption.split(",");
            int optlen = first.length;

            if (date.equals("0")) rdvalu = 1;
            else rdvalu = 2;

            Cursor answ = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "'");
            if (answ.getCount() == 0) for (int i = 0; i < optlen; i++) {
                ContentValues cv = new ContentValues();
                cv.put("gameid", gameid);
                cv.put("levelid", letterid);
                cv.put("answer", first[i].trim());
                if (date.equals("0")) cv.put("rd", 1);
                else cv.put("rd", 2);
                cv.put("isfinish", "0");
                myDbHelper.insert_data("answertable", null, cv);
            }


            Cursor cs = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "' and isfinish='1'");
            cs.moveToFirst();
            if (cs.getCount() != 0) {

                int dscore = cs.getInt(cs.getColumnIndexOrThrow("levelscore"));
                x = 1 + cs.getCount();
                //  b_score=sps.getInt(Word_Game_Hard.this,"old_score_continue");
                b_score = dscore;
                // Toast.makeText(Word_Game_Hard.this, ""+x, Toast.LENGTH_SHORT).show();

                Cursor csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='0'");
                csk.moveToFirst();
                for (int i = 0; i < csk.getCount(); i++) {
                    csk.moveToPosition(i);
                    String ansn = csk.getString(csk.getColumnIndexOrThrow("answer"));

                    if (vl1.length() == 0) {
                        vl1.setText(ansn);
                        vl1.setTextColor(getResources().getColor(R.color.white));
                        q1.setBackgroundResource(R.drawable.tick_background);
                        q1.setClickable(false);
                        q2.setVisibility(View.VISIBLE);
                    } else if (vl2.length() == 0) {
                        vl2.setText(ansn);
                        vl2.setTextColor(getResources().getColor(R.color.white));
                        q2.setBackgroundResource(R.drawable.tick_background);
                        q2.setClickable(false);
                        q3.setVisibility(View.VISIBLE);
                    } else if (vl3.length() == 0) {
                        vl3.setText(ansn);
                        vl3.setTextColor(getResources().getColor(R.color.white));
                        q3.setBackgroundResource(R.drawable.tick_background);
                        q3.setClickable(false);
                        q4.setVisibility(View.VISIBLE);
                    } else if (vl4.length() == 0) {
                        vl4.setText(ansn);
                        vl4.setTextColor(getResources().getColor(R.color.white));
                        q4.setBackgroundResource(R.drawable.tick_background);
                        q4.setClickable(false);
                        q5.setVisibility(View.VISIBLE);
                    } else if (vl5.length() == 0) {
                        vl5.setText(ansn);
                        vl5.setTextColor(getResources().getColor(R.color.white));
                        q5.setBackgroundResource(R.drawable.tick_background);
                        q5.setClickable(false);
                        q6.setVisibility(View.VISIBLE);
                    } else if (vl6.length() == 0) {
                        vl6.setText(ansn);
                        vl6.setTextColor(getResources().getColor(R.color.white));
                        q6.setBackgroundResource(R.drawable.tick_background);
                        q6.setClickable(false);
                        q7.setVisibility(View.VISIBLE);
                    } else if (vl7.length() == 0) {
                        vl7.setText(ansn);
                        vl7.setTextColor(getResources().getColor(R.color.white));
                        q7.setBackgroundResource(R.drawable.tick_background);
                        q7.setClickable(false);
                        q8.setVisibility(View.VISIBLE);
                    } else if (vl8.length() == 0) {
                        vl8.setText(ansn);
                        vl8.setTextColor(getResources().getColor(R.color.white));
                        q8.setBackgroundResource(R.drawable.tick_background);
                        q7.setClickable(false);
                        q9.setVisibility(View.VISIBLE);
                    } else if (vl9.length() == 0) {
                        vl9.setText(ansn);
                        vl9.setTextColor(getResources().getColor(R.color.white));
                        q9.setBackgroundResource(R.drawable.tick_background);
                        q9.setClickable(false);
                        q10.setVisibility(View.VISIBLE);
                    } else if (vl10.length() == 0) {
                        vl10.setText(ansn);
                        vl10.setTextColor(getResources().getColor(R.color.white));
                        q10.setBackgroundResource(R.drawable.tick_background);
                        q10.setClickable(false);
                        q11.setVisibility(View.VISIBLE);
                    } else if (vl11.length() == 0) {
                        vl11.setText(ansn);
                        vl11.setTextColor(getResources().getColor(R.color.white));
                        q11.setBackgroundResource(R.drawable.tick_background);
                        q11.setClickable(false);
                        q12.setVisibility(View.VISIBLE);
                    } else if (vl12.length() == 0) {
                        vl12.setText(ansn);
                        vl12.setTextColor(getResources().getColor(R.color.white));
                        q12.setBackgroundResource(R.drawable.tick_background);
                        q12.setClickable(false);
                        q13.setVisibility(View.VISIBLE);
                    } else if (vl13.length() == 0) {
                        vl13.setText(ansn);
                        vl13.setTextColor(getResources().getColor(R.color.white));
                        q13.setBackgroundResource(R.drawable.tick_background);
                        q13.setClickable(false);
                        q14.setVisibility(View.VISIBLE);
                    } else if (vl14.length() == 0) {
                        vl14.setText(ansn);
                        vl14.setTextColor(getResources().getColor(R.color.white));
                        q14.setBackgroundResource(R.drawable.tick_background);
                        q14.setClickable(false);
                    }
                }

                Cursor csk1 = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='1'");
                csk1.moveToFirst();
                for (int i = 0; i < csk1.getCount(); i++) {
                    csk1.moveToPosition(i);
                    String ansn = csk1.getString(csk1.getColumnIndexOrThrow("answer"));

                    if (vl1.length() == 0) {
                        vl1.setText(ansn);
                        vl1.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q1.setBackgroundResource(R.drawable.tick_background);
                        q1.setClickable(false);

                        q2.setVisibility(View.VISIBLE);
                    } else if (vl2.length() == 0) {
                        vl2.setText(ansn);
                        vl2.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q2.setBackgroundResource(R.drawable.tick_background);
                        q2.setClickable(false);

                        q3.setVisibility(View.VISIBLE);
                    } else if (vl3.length() == 0) {
                        vl3.setText(ansn);
                        vl3.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q3.setBackgroundResource(R.drawable.tick_background);
                        q3.setClickable(false);

                        q4.setVisibility(View.VISIBLE);
                    } else if (vl4.length() == 0) {
                        vl4.setText(ansn);
                        vl4.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q4.setBackgroundResource(R.drawable.tick_background);
                        q4.setClickable(false);

                        q5.setVisibility(View.VISIBLE);
                    } else if (vl5.length() == 0) {
                        vl5.setText(ansn);
                        vl5.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q5.setBackgroundResource(R.drawable.tick_background);
                        q5.setClickable(false);

                        q6.setVisibility(View.VISIBLE);
                    } else if (vl6.length() == 0) {
                        vl6.setText(ansn);
                        vl6.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q6.setBackgroundResource(R.drawable.tick_background);
                        q6.setClickable(false);

                        q7.setVisibility(View.VISIBLE);

                    } else if (vl7.length() == 0) {
                        vl7.setText(ansn);
                        vl7.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q7.setBackgroundResource(R.drawable.tick_background);
                        q7.setClickable(false);

                        q8.setVisibility(View.VISIBLE);

                    } else if (vl8.length() == 0) {
                        vl8.setText(ansn);
                        vl8.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q8.setBackgroundResource(R.drawable.tick_background);
                        q8.setClickable(false);

                        q9.setVisibility(View.VISIBLE);
                    } else if (vl9.length() == 0) {
                        vl9.setText(ansn);
                        vl9.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q9.setBackgroundResource(R.drawable.tick_background);
                        q9.setClickable(false);

                        q10.setVisibility(View.VISIBLE);
                    } else if (vl10.length() == 0) {
                        vl10.setText(ansn);
                        vl10.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q10.setBackgroundResource(R.drawable.tick_background);
                        q10.setClickable(false);

                        q11.setVisibility(View.VISIBLE);
                    } else if (vl11.length() == 0) {
                        vl11.setText(ansn);
                        vl11.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q11.setBackgroundResource(R.drawable.tick_background);
                        q11.setClickable(false);

                        q12.setVisibility(View.VISIBLE);
                    } else if (vl12.length() == 0) {
                        vl12.setText(ansn);
                        vl12.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q12.setBackgroundResource(R.drawable.tick_background);
                        q12.setClickable(false);

                        q13.setVisibility(View.VISIBLE);
                    } else if (vl13.length() == 0) {
                        vl13.setText(ansn);
                        vl13.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q13.setBackgroundResource(R.drawable.tick_background);
                        q13.setClickable(false);

                        q14.setVisibility(View.VISIBLE);
                    } else if (vl14.length() == 0) {
                        vl14.setText(ansn);
                        vl14.setTextColor(getResources().getColor(R.color.rippelColor1));
                        q14.setBackgroundResource(R.drawable.tick_background);
                        q14.setClickable(false);
                    }
                }


            } else {
                //  Toast.makeText(Word_Game_Hard.this, "Time Reseting", Toast.LENGTH_SHORT).show();
                sps.putInt(Word_Game_Hard.this, "old_score_continue", 0);
                sps.putInt(Word_Game_Hard.this, "old_time_start", 0);


                if (sps.getString(Word_Game_Hard.this, "time_start").equals(""))
                    sps.putString(Word_Game_Hard.this, "time_start", "yes");
                else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }


            }


            if (optlen >= 7 && optlen <= 10) {
                tans = 8;
                tscore = 70;
                ttime = 63;
                simple();

            } else if (optlen >= 11 && optlen <= 15) {
                tans = 11;
                tscore = 100;
                ttime = 183;
                medium();
            } else if (optlen >= 16) {
                tans = 15;
                tscore = 140;
                ttime = 243;
                hard();
            }
            if (c.getCount() != 0) {
                if (sa.length() > 100) {
                    bt4.setVisibility(View.VISIBLE);
                    StringTokenizer tokenizer = new StringTokenizer(sa, ",");
                    final String letter1 = tokenizer.nextToken();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();
                    String letter16 = tokenizer.nextToken().trim();


                    bt1.setText(letter1);
                    bt2.setText(letter2);
                    bt3.setText(letter3);
                    bt5.setText(letter4);
                    bt6.setText(letter5);
                    bt7.setText(letter6);
                    bt9.setText(letter7);
                    bt10.setText(letter8);
                    bt11.setText(letter9);
                    bt4.setText(letter10);
                    bt8.setText(letter11);
                    bt12.setText(letter12);
                    bt13.setText(letter13);
                    bt14.setText(letter14);
                    bt15.setText(letter15);
                    bt16.setText(letter16);

                } else {
                    StringTokenizer tokenizer = new StringTokenizer(sa, ",");
                    final String letter1 = tokenizer.nextToken();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();


                    bt1.setText(letter1);
                    bt2.setText(letter2);
                    bt3.setText(letter3);
                    bt5.setText(letter4);
                    bt6.setText(letter5);
                    bt7.setText(letter6);
                    bt9.setText(letter7);
                    bt10.setText(letter8);
                    bt11.setText(letter9);
                }
                Cursor ces = myDbHelper.getQry("SELECT * FROM score");
                ces.moveToFirst();
                sk = ces.getInt(ces.getColumnIndexOrThrow("coins"));
                score.setText(Integer.toString(sk));
            }
            System.out.println("#######################xxx" + x);
            System.out.println("#######################tans" + tans);
            if (tans == 11) if (x >= tans) {
                if (date.equals("0"))
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                else
                    myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");
                adShow();
            }

        } else if (date.equals("0")) downloaddata_regular();
        else if (sps.getString(Word_Game_Hard.this, "Exp_list").equals("on")) {
            finish();
            Intent i = new Intent(Word_Game_Hard.this, Expandable_List_View.class);
            startActivity(i);
        } else {
            Toast.makeText(Word_Game_Hard.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான சொல் விளையாட்டு விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Word_Game_Hard.class);
            startActivity(i);
        }
    }

    private void daily_bones() {
        openDialog_daily = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_daily.setContentView(R.layout.daily_bones_newd2);
        openDialog_daily.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog_daily.findViewById(R.id.ok_y);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        ea = 100;
        Calendar calendar3 = Calendar.getInstance();
        int cur_year1 = calendar3.get(Calendar.YEAR);
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        String str_month1 = "" + (cur_month1 + 1);
        if (str_month1.length() == 1) str_month1 = "0" + str_month1;

        String str_day1 = "" + cur_day1;
        if (str_day1.length() == 1) str_day1 = "0" + str_day1;
        final String str_date1 = str_day1 + "-" + str_month1 + "-" + cur_year1;

        Date date1 = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final String date = sdf.format(date1);

        TextView tomarrow_coin_earn = openDialog_daily.findViewById(R.id.tomarrow_coin_earn);

        ok_y.setOnClickListener(v -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + ea;
            String aStringx = Integer.toString(spx);
            score.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            openDialog_daily.dismiss();
            sps.putString(context, "daily_bonus_date", date);


        });
        coin_value = openDialog_daily.findViewById(R.id.coin_value);
        ea = 100;
        final int vals = reward_play_count * 100;
        ea = ea + vals;
        coin_value.setText("" + ea);

        LinearLayout extra_coin = openDialog_daily.findViewById(R.id.extra_coin);
        System.out.println("############################^^^^^^^^^^^^^^currentdate" + str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Word_Game_Hard.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Word_Game_Hard.this, "daily_bonus_date"))) {

        } else sps.putInt(context, "daily_bonus_count", 0);
        if (sps.getInt(context, "daily_bonus_count") == 0) ea = 100;
        else if (sps.getInt(context, "daily_bonus_count") == 1) ea = 150;
        else if (sps.getInt(context, "daily_bonus_count") == 2) ea = 200;
        else if (sps.getInt(context, "daily_bonus_count") == 3) ea = 250;
        else if (sps.getInt(context, "daily_bonus_count") == 4) ea = 300;
        prize_data_update(context, ea);
        coin_value = openDialog_daily.findViewById(R.id.coin_value);
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomno = rn.nextInt(maximum - minmum + 1) + minmum;

        String ran_score = "";
        if (randomno == 1) {
            sps.putInt(context, "daily_bonus_count", 1);
            ran_score = "150";
        } else if (randomno == 2) {
            sps.putInt(context, "daily_bonus_count", 2);
            ran_score = "200";
        } else if (randomno == 3) {
            sps.putInt(context, "daily_bonus_count", 3);
            ran_score = "250";
        } else if (randomno == 4) {
            sps.putInt(context, "daily_bonus_count", 4);
            ran_score = "300";
        }

        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);

        extra_coin.setOnClickListener(v -> {
            extra_coin_s = 1;
            if (isNetworkAvailable(this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Word_Game_Hard.this, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    show_reward();
                } else new Handler(Looper.myLooper()).postDelayed(() -> {
                    reward_progressBar.dismiss();
                    // mShowVideoButton.setVisibility(View.VISIBLE);
                    if (fb_reward == 1) show_reward();
                    else {
                        //reward(Word_Game_Hard.this);
                        rewarded_adnew();
                        Toast.makeText(Word_Game_Hard.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });


        openDialog_daily.show();
    }

    private void medium() {

        vl1.setVisibility(View.VISIBLE);
        vl2.setVisibility(View.VISIBLE);
        vl3.setVisibility(View.VISIBLE);
        vl4.setVisibility(View.VISIBLE);
        vl5.setVisibility(View.VISIBLE);
        vl6.setVisibility(View.VISIBLE);
        vl7.setVisibility(View.VISIBLE);
        vl8.setVisibility(View.VISIBLE);
        vl9.setVisibility(View.VISIBLE);
        vl10.setVisibility(View.VISIBLE);

    }

    private void simple() {
//second game layout visibility
        LinearLayout linearLayout;
        linearLayout = findViewById(R.id.anslist2);
        linearLayout.setVisibility(View.GONE);
        LinearLayout linearLayout1;
        linearLayout1 = findViewById(R.id.list2_pic);
        linearLayout1.setVisibility(View.GONE);
        //

        vl1.setVisibility(View.VISIBLE);
        vl2.setVisibility(View.VISIBLE);
        vl3.setVisibility(View.VISIBLE);
        vl4.setVisibility(View.VISIBLE);
        vl5.setVisibility(View.VISIBLE);
        vl6.setVisibility(View.VISIBLE);
        vl7.setVisibility(View.VISIBLE);


    }

    public void hard() {

        vl1.setVisibility(View.VISIBLE);
        vl2.setVisibility(View.VISIBLE);
        vl3.setVisibility(View.VISIBLE);
        vl4.setVisibility(View.VISIBLE);
        vl5.setVisibility(View.VISIBLE);
        vl6.setVisibility(View.VISIBLE);
        vl7.setVisibility(View.VISIBLE);
        vl8.setVisibility(View.VISIBLE);
        vl9.setVisibility(View.VISIBLE);
        vl10.setVisibility(View.VISIBLE);
        vl11.setVisibility(View.VISIBLE);
        vl12.setVisibility(View.VISIBLE);
        vl13.setVisibility(View.VISIBLE);
        vl14.setVisibility(View.VISIBLE);

    }

    public void dialog(int i) {

        final Dialog openDialog_earncoin = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = openDialog_earncoin.findViewById(R.id.ssss);

        ss.setOnClickListener(v -> openDialog_earncoin.cancel());
        cancel.setOnClickListener(v -> openDialog_earncoin.cancel());

        TextView wpro = openDialog_earncoin.findViewById(R.id.wpro);
        if (i == 1) {
            cancel.setVisibility(View.INVISIBLE);
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }
        RelativeLayout video = openDialog_earncoin.findViewById(R.id.earnvideo);
        video.setOnClickListener(v -> {
            rvo = 1;
            extra_coin_s = 0;
            if (isNetworkAvailable(this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Word_Game_Hard.this, "date");
                    int pos;
                    if (date.equals("0")) pos = 1;
                    else pos = 2;
                    myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                    myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");

                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {

                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);
                }
            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });

        wp.setOnClickListener(view -> {
            // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            if (isNetworkAvailable(this)) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    openDialog_earncoin.cancel();
                    Intent i12 = new Intent(Intent.ACTION_SEND);
                    i12.setType("text/plain");
                    i12.setPackage("com.whatsapp");
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i12.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i12, "Share via"), 12);
                } else
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
        });
        fb.setOnClickListener(view -> {

        });
        gplus.setOnClickListener(view -> {
            // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            if (isNetworkAvailable(this)) {

                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Word_Game_Hard.this, "date");
                    int pos;
                    if (date.equals("0")) pos = 1;
                    else pos = 2;
                    myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                    myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");

                    openDialog_earncoin.cancel();
                    Intent i1 = new Intent(Intent.ACTION_SEND);
                    i1.setType("text/plain");
                    i1.setPackage("com.google.android.apps.plus");
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i1.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i1, "Share via"), 15);
                } else
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });
        openDialog_earncoin.show();
    }

    public boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void setSc() {


        if (s == 1) {
            openDialog_p.dismiss();
            s = 0;
        }
        sps.putString(Word_Game_Hard.this, "answer_tb", "");


        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;


        TextView arputham = openDialog.findViewById(R.id.arputham);
        TextView extracoin = openDialog.findViewById(R.id.extracoin);
        next_continue = openDialog.findViewById(R.id.continues);
        ttscores = openDialog.findViewById(R.id.tts_score);
        final TextView bsscores = openDialog.findViewById(R.id.bs_score);
        final TextView dumy = openDialog.findViewById(R.id.bs_score_dum);
        final TextView cns1 = openDialog.findViewById(R.id.cnse1);
        final TextView cns2 = openDialog.findViewById(R.id.cnse2);
        final TextView cns3 = openDialog.findViewById(R.id.cnse3);
        final TextView cns4 = openDialog.findViewById(R.id.cnse4);
        final TextView cns5 = openDialog.findViewById(R.id.cnse5);
        final TextView cns6 = openDialog.findViewById(R.id.cnse6);
        final TextView cns7 = openDialog.findViewById(R.id.cnse7);
        addsdialog = openDialog.findViewById(R.id.ads_lay);
        tx2 = openDialog.findViewById(R.id.tt2);
        final TextView wtp = openDialog.findViewById(R.id.wtp);
        final TextView fbs = openDialog.findViewById(R.id.fbp);
        final TextView gplus = openDialog.findViewById(R.id.gplus);
        final LinearLayout vid_earn = openDialog.findViewById(R.id.vid_earn);
        final LinearLayout rewardvideo = openDialog.findViewById(R.id.rewardvideo);

        ImageView prize_logo = openDialog.findViewById(R.id.prize_logo);
        if (sps.getInt(Word_Game_Hard.this, "remoteConfig_prize") == 1)
            prize_logo.setVisibility(View.VISIBLE);
        else prize_logo.setVisibility(View.GONE);
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable(this))
                if (sps.getString(Word_Game_Hard.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, Game_Status.class);
                    startActivity(i);
                } else if (sps.getString(Word_Game_Hard.this, "otp_verify").equals("yes")) {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, Price_Login.class);
                    startActivity(i);
                }
            else
                Toast.makeText(Word_Game_Hard.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
        });

        LinearLayout ads_layout = openDialog.findViewById(R.id.fl_adplaceholder);

        TextView video_earn = openDialog.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Word_Game_Hard.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Word_Game_Hard.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);


        //  New_Main_Activity.load_addFromMain_multiplayer(Word_Game_Hard.this,ads_layout);
        if (sps.getInt(Word_Game_Hard.this, "purchase_ads") == 1)
            ads_layout.setVisibility(View.GONE);
        else if (isNetworkAvailable(context)) {
            //New_Main_Activity.load_add_fb_rect_score_screen(context, ads_layout);
        } else ads_layout.setVisibility(View.GONE);


        next_continue.setVisibility(View.INVISIBLE);


        if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Word_Game_Hard.this, "date");
            if (dates.equals("0")) rewardvideo.setVisibility(View.VISIBLE);
        }


        Cursor csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='0'");
        csk.moveToFirst();
        if (csk.getCount() == 0) rewardvideo.setVisibility(View.INVISIBLE);


        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            if (isNetworkAvailable(context)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    show_reward();
                    rewardvideo.setVisibility(View.INVISIBLE);
                } else new Handler(Looper.myLooper()).postDelayed(() -> {
                    reward_progressBar.dismiss();
                    // mShowVideoButton.setVisibility(View.VISIBLE);
                    if (fb_reward == 1) show_reward();
                    else {

                        rewarded_adnew();
                        Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });


        c_counter = 0;
        current_sc = 0;
        case2 = 0;
        tt_case2 = 0;
        tt_tot2 = 0;
        total_sc = 0;
        c_total = 0;
        ttscores.setText("");

        final RelativeLayout adsicon = openDialog.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);


        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (isNetworkAvailable(context)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    show_reward();
                    rewardvideo.setVisibility(View.INVISIBLE);
                } else new Handler(Looper.myLooper()).postDelayed(() -> {
                    reward_progressBar.dismiss();
                    // mShowVideoButton.setVisibility(View.VISIBLE);
                    if (fb_reward == 1) show_reward();
                    else {

                        rewarded_adnew();
                        Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
        });
        wtp.setOnClickListener(view -> {
            // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            if (isNetworkAvailable(this)) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.whatsapp");

                    String msg = ("நான் சொல்லிஅடி செயலியில் சொல் விளையாட்டில் நிலை " + word_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivity(Intent.createChooser(i, "Share via"));
                    startActivityForResult(Intent.createChooser(i, "Share via"), 21);


                } else
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
        });
        fbs.setOnClickListener(view -> {

        });
        gplus.setOnClickListener(view -> {

            // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            if (isNetworkAvailable(this)) {
                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.google.android.apps.plus");

                    String msg = ("நான் சொல்லிஅடி செயலியில் சொல் விளையாட்டில்  நிலை " + word_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i, "Share via"), 16);


                } else
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });


        if (b_score >= tscore && f_sec <= ttime) {
            //condition=1
            b_score = 0;
            arputham.setTypeface(tyr);
            arputham.setText("Iè ÜŸ¹î‹");
            extracoin.setTypeface(tyr);
            extracoin.setText("Ã´î™ ï£íòƒèœ");
            tx2.setTypeface(tyr);
            tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
            next_continue.setTypeface(tyr);
            next_continue.setText("ªî£ì˜è");
            String date = sps.getString(Word_Game_Hard.this, "date");
            if (!date.equals("0")) next_continue.setText("சரி");
            Handler handler1 = new Handler(Looper.myLooper());
            handler1.postDelayed(() -> {
                coin.play(soundId4, sv, sv, 0, 0, sv);
                //play1.start();
                cns6.setVisibility(View.VISIBLE);
            }, 500);
            Handler handler2 = new Handler(Looper.myLooper());
            handler2.postDelayed(() -> {
                //play2.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns4.setVisibility(View.VISIBLE);
            }, 1000);
            Handler handler3 = new Handler(Looper.myLooper());
            handler3.postDelayed(() -> {
                //play3.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns3.setVisibility(View.VISIBLE);
            }, 1500);
            Handler handler4 = new Handler(Looper.myLooper());
            handler4.postDelayed(() -> {
                //play4.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns1.setVisibility(View.VISIBLE);
            }, 2000);
            Handler handler5 = new Handler(Looper.myLooper());
            handler5.postDelayed(() -> {
                //play5.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns2.setVisibility(View.VISIBLE);
            }, 2500);
            Handler handler6 = new Handler(Looper.myLooper());
            handler6.postDelayed(() -> {
                // play6.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns5.setVisibility(View.VISIBLE);
            }, 3000);
            Handler handler7 = new Handler(Looper.myLooper());
            handler7.postDelayed(() -> {
                //play7.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns7.setVisibility(View.VISIBLE);
            }, 3500);

            Handler handler8 = new Handler(Looper.myLooper());
            handler8.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns7.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns7.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns7.startAnimation(transAnimation);
                cns7.postDelayed(() -> cns7.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 3900);
            Handler handler9 = new Handler(Looper.myLooper());
            handler9.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns5.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns5.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns5.startAnimation(transAnimation);
                cns5.postDelayed(() -> cns5.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 4300);
            Handler handler10 = new Handler(Looper.myLooper());
            handler10.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns2.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns2.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns2.startAnimation(transAnimation);
                cns2.postDelayed(() -> cns2.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 4700);
            Handler handler11 = new Handler(Looper.myLooper());
            handler11.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns1.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns1.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns1.startAnimation(transAnimation);
                cns1.postDelayed(() -> cns1.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 5100);
            Handler handler12 = new Handler(Looper.myLooper());
            handler12.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns3.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns3.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns3.startAnimation(transAnimation);
                cns3.postDelayed(() -> cns3.setVisibility(View.INVISIBLE), transAnimation.getDuration());
            }, 5500);
            Handler handler13 = new Handler(Looper.myLooper());
            handler13.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns4.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns4.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns4.startAnimation(transAnimation);
                cns4.postDelayed(() -> cns4.setVisibility(View.INVISIBLE), transAnimation.getDuration());
            }, 5900);
            Handler handler14 = new Handler(Looper.myLooper());
            handler14.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns6.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns6.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns6.startAnimation(transAnimation);
                cns6.postDelayed(() -> cns6.setVisibility(View.INVISIBLE), transAnimation.getDuration());
            }, 6300);

            c_counter = 0;
            c_total = 70;

            new Thread(() -> {
                while (c_counter < c_total) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    bsscores.post(() -> bsscores.setText("" + c_counter));
                    c_counter++;
                }

            }).start();


            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            current_sc = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            total_sc = current_sc + 70;
            String aStringx = Integer.toString(current_sc);
            ttscores.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + total_sc + "'");

            Handler handler15 = new Handler(Looper.myLooper());
            handler15.postDelayed(() -> new Thread(() -> {

                while (current_sc < total_sc) {
                    try {
                        Thread.sleep(45);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ttscores.post(() -> ttscores.setText("" + current_sc));
                    current_sc++;
                }

            }).start(), 3900);
            Handler hand = new Handler(Looper.myLooper());
            hand.postDelayed(() -> next_continue.setVisibility(View.VISIBLE), 6200);

            next_continue.setOnClickListener(view -> {
                c_counter = 0;
                current_sc = 0;
                case2 = 0;
                tt_case2 = 0;
                tt_tot2 = 0;
                total_sc = 0;
                c_total = 0;


                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

                dia_dismiss = 1;
                openDialog.dismiss();
                next();
                sps.putInt(getApplicationContext(), "ins_ad_new", (sps.getInt(getApplicationContext(), "ins_ad_new") + 1));


            });
        } else if (b_score >= tscore && f_sec > ttime) {
            //Condition 2
            b_score = 0;
            arputham.setTypeface(tyr);
            arputham.setText("ÜŸ¹î‹");
            extracoin.setTypeface(tyr);
            extracoin.setText("Ã´î™ ï£íòƒèœ");
            tx2.setTypeface(tyr);
            tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
            next_continue.setTypeface(tyr);
            next_continue.setText("ªî£ì˜è");
            String date = sps.getString(Word_Game_Hard.this, "date");
            if (!date.equals("0")) next_continue.setText("சரி");
            Handler handler1 = new Handler(Looper.myLooper());
            handler1.postDelayed(() -> {
                coin.play(soundId4, sv, sv, 0, 0, sv);
                //play1.start();
                cns5.setVisibility(View.VISIBLE);
            }, 500);
            Handler handler2 = new Handler(Looper.myLooper());
            handler2.postDelayed(() -> {
                // play2.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns2.setVisibility(View.VISIBLE);
            }, 1000);
            Handler handler3 = new Handler(Looper.myLooper());
            handler3.postDelayed(() -> {
                //play3.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns1.setVisibility(View.VISIBLE);
            }, 1500);
            Handler handler4 = new Handler(Looper.myLooper());
            handler4.postDelayed(() -> {
                // play4.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns3.setVisibility(View.VISIBLE);
            }, 2000);
            Handler handler5 = new Handler(Looper.myLooper());
            handler5.postDelayed(() -> {
                //play5.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns4.setVisibility(View.VISIBLE);
            }, 2500);


            Handler handler8 = new Handler(Looper.myLooper());
            handler8.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns5.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns5.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns5.startAnimation(transAnimation);
                cns5.postDelayed(() -> cns5.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 2500);
            Handler handler9 = new Handler(Looper.myLooper());
            handler9.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns2.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns2.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns2.startAnimation(transAnimation);
                cns2.postDelayed(() -> cns2.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 2900);
            Handler handler10 = new Handler(Looper.myLooper());
            handler10.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns1.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns1.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns1.startAnimation(transAnimation);
                cns1.postDelayed(() -> cns1.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 3300);
            Handler handler11 = new Handler(Looper.myLooper());
            handler11.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns3.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns3.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns3.startAnimation(transAnimation);
                cns3.postDelayed(() -> cns3.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 3700);
            Handler handler12 = new Handler(Looper.myLooper());
            handler12.postDelayed(() -> {
                int[] locationInWindow = new int[2];
                cns4.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                cns4.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                dumy.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                dumy.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 2f, (destinationY - sourceY));
                transAnimation.setDuration(400);
                cns4.startAnimation(transAnimation);
                cns4.postDelayed(() -> cns4.setVisibility(View.INVISIBLE), transAnimation.getDuration());
            }, 4100);

            case2 = 0;
            tot2 = 50;
            new Thread(() -> {
                while (case2 < tot2) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    bsscores.post(() -> bsscores.setText("" + case2));
                    case2++;
                }

            }).start();


            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            tt_tot2 = tt_case2 + 50;
            String aStringx = Integer.toString(tt_case2);
            ttscores.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + tt_tot2 + "'");


            Handler handler13 = new Handler(Looper.myLooper());
            handler13.postDelayed(() -> new Thread(() -> {

                while (tt_case2 < tt_tot2) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ttscores.post(() -> ttscores.setText("" + tt_case2));
                    tt_case2++;
                }

            }).start(), 2500);


            Handler hand = new Handler(Looper.myLooper());
            hand.postDelayed(() -> next_continue.setVisibility(View.VISIBLE), 3500);

            next_continue.setOnClickListener(view -> {
                c_counter = 0;
                current_sc = 0;
                case2 = 0;
                tt_case2 = 0;
                tt_tot2 = 0;
                c_counter = 0;
                current_sc = 0;
                case2 = 0;
                tt_case2 = 0;
                tt_tot2 = 0;
                total_sc = 0;
                c_total = 0;


                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

                dia_dismiss = 1;
                openDialog.dismiss();
                next();
                sps.putInt(getApplicationContext(), "ins_ad_new", (sps.getInt(getApplicationContext(), "ins_ad_new") + 1));


            });
        } else if (b_score < tscore) {
            b_score = 0;
            arputham.setTypeface(tyr);
            arputham.setText("Iè ÜŸ¹î‹");
            extracoin.setTypeface(tyr);
            extracoin.setText("Ã´î™ ï£íòƒèœ");
            tx2.setTypeface(tyr);
            tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
            next_continue.setTypeface(tyr);
            next_continue.setText("ªî£ì˜è");
            String date = sps.getString(Word_Game_Hard.this, "date");
            if (!date.equals("0")) next_continue.setText("சரி");
            arputham.setTypeface(tyr);
            arputham.setText("ï¡Á");
            extracoin.setTypeface(tyr);
            extracoin.setText("Ã´î™ ï£íòƒèœ");
            tx2.setTypeface(tyr);
            tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
            next_continue.setTypeface(tyr);
            next_continue.setText("ªî£ì˜è");
            String dates = sps.getString(Word_Game_Hard.this, "date");
            if (!dates.equals("0")) next_continue.setText("சரி");
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            tt_tot2 = tt_case2;
            String aStringx = Integer.toString(tt_case2);
            ttscores.setText(aStringx);
            bsscores.setText("0");

            next_continue.setVisibility(View.VISIBLE);
            next_continue.setOnClickListener(view -> {
                c_counter = 0;
                current_sc = 0;
                case2 = 0;
                tt_case2 = 0;
                tt_tot2 = 0;
                tt_tot2 = 0;
                total_sc = 0;
                c_total = 0;


                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

                dia_dismiss = 1;
                openDialog.dismiss();
                next();
                sps.putInt(getApplicationContext(), "ins_ad_new", (sps.getInt(getApplicationContext(), "ins_ad_new") + 1));

            });
        }

        openDialog.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Word_Game_Hard.this, "game_area", "on");
                c_counter = 0;
                current_sc = 0;
                tt_tot2 = 0;
                c_counter = 0;
                current_sc = 0;
                tt_tot2 = 0;
                total_sc = 0;
                c_total = 0;
                case2 = 0;
                tt_case2 = 0;


                String date = sps.getString(Word_Game_Hard.this, "date");
                if (date.equals("0")) if (main_act.equals("")) {
                    finish();
                    openDialog.dismiss();
                    Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    openDialog.dismiss();
                    finish();
                }
                else if (sps.getString(Word_Game_Hard.this, "Exp_list").equals("on")) {
                    finish();
                    openDialog.dismiss();
                    Intent i = new Intent(Word_Game_Hard.this, Expandable_List_View.class);
                    startActivity(i);

                } else if (main_act.equals("")) {
                    finish();
                    openDialog.dismiss();
                    Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    openDialog.dismiss();
                    finish();
                }


            } else dia_dismiss = 0;

        });
        openDialog.setOnKeyListener((dialog, keyCode, event) -> {
            sps.putString(Word_Game_Hard.this, "game_area", "on");
            focus.stop();
            counter = 0;
            counter3 = 0;
            focus.stop();
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();


            String date = sps.getString(Word_Game_Hard.this, "date");
            int pos;
            if (date.equals("0")) pos = 1;
            else pos = 2;

            myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");
            myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");

            // String date = sps.getString(Word_Game_Hard.this, "date");
            if (date.equals("0")) if (main_act.equals("")) {
                finish();
                Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                startActivity(i);
            } else finish();
            else if (sps.getString(Word_Game_Hard.this, "Exp_list").equals("on")) {
                finish();
                Intent i = new Intent(Word_Game_Hard.this, Expandable_List_View.class);
                startActivity(i);
            } else if (main_act.equals("")) {
                finish();
                Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                startActivity(i);
            } else finish();


            //  Toast.makeText(context, "back", Toast.LENGTH_SHORT).show();

            // Prevent dialog close on back press button
            return keyCode == KeyEvent.KEYCODE_BACK;
        });

        if (!isFinishing()) openDialog.show();
    }

    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Word_Game_Hard.this);
        Bundle params = new Bundle();
        params.putString("screen_name", "Sol Game");
        params.putString("screen_class", "Word_Game_Hard");
        mFirebaseAnalytics.logEvent("screen_view", params);
        if (setting_access == 1) {
            setting_access = 0;
            //if ((ContextCompat.checkSelfPermission(Word_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            downloaddata_daily();
            /*} else {
                settingpermission();
            }*/
        } else if (setting_access == 2) {
            setting_access = 0;
            //if ((ContextCompat.checkSelfPermission(Word_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            downloaddata_regular();
            /*} else {
                settingpermission();
            }*/
        }

        if (sps.getString(Word_Game_Hard.this, "resume_w").equals(""))
            sps.putString(Word_Game_Hard.this, "resume_w", "yes");
        else {
            String date = sps.getString(Word_Game_Hard.this, "date");
            int pos;
            if (date.equals("0")) pos = 1;
            else pos = 2;

            Cursor cs = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + pos + "'");
            cs.moveToFirst();
            long dscore = 0;
            if (cs.getCount() != 0) dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            //  long wt=sps.getInt(Word_Game_Hard.this,"old_time_start");
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //   uiHelper.onSaveInstanceState(outState);
        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);


        if (requestCode == 0) if (isNetworkAvailable(Word_Game_Hard.this)) {


            String date = sps.getString(Word_Game_Hard.this, "date");
            if (date.equals("0")) {
                Cursor c1 = myDbHelper.getQry("select id from maintable order by id DESC");
                c1.moveToFirst();


                System.out.print("Count====" + c1.getCount());


                if (c1.getCount() != 0) {


                    //c1.getString(c1.getColumnIndexOrThrow("id"));

                    System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));

                    downloadcheck("" + c1.getString(c1.getColumnIndexOrThrow("id")), "ord");

                } else downloadcheck("0", "ord");
            } else {
                Cursor c1 = myDbHelper.getQry("select id from dailytest order by id DESC");
                c1.moveToFirst();


                System.out.print("Count====" + c1.getCount());


                if (c1.getCount() != 0) {
                    //c1.getString(c1.getColumnIndexOrThrow("id"));
                    System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));
                    downloadcheck("" + c1.getString(c1.getColumnIndexOrThrow("id")), "daily");


                } else {

                    System.out.print("else====");
                    downloadcheck("0", "daily");
                }
            }


        } else {

            head.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Word_Game_Hard.this);                            /*.setTitle("Delete entry")*/
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog, which) -> {
                // continue with delete
                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                sps.putInt(Word_Game_Hard.this, "goto_sett", 1);
                dialog.dismiss();
            }).setNegativeButton("பின்னர்", (dialog, which) -> {
                // do nothing

                String date = sps.getString(Word_Game_Hard.this, "date");
                if (date.equals("0")) backexitnet();
                else backexitnet();
                dialog.dismiss();
            }).setIcon(android.R.drawable.ic_dialog_alert).show();

        }
        if (requestCode == 15) if (resultCode == -1) {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 10;
            String aStringx = Integer.toString(spx);
            // score.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            share_earn(10);
            //setcoin(1);


            ///Reward Share
            retype = "s";
            Calendar calendar3 = Calendar.getInstance();
            int cur_year1 = calendar3.get(Calendar.YEAR);
            int cur_month1 = calendar3.get(Calendar.MONTH);
            int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

            String str_month1 = "" + (cur_month1 + 1);
            if (str_month1.length() == 1) str_month1 = "0" + str_month1;

            String str_day1 = "" + cur_day1;
            if (str_day1.length() == 1) str_day1 = "0" + str_day1;
            final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

            if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
                Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                cn.moveToFirst();
                int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                int gm1s = gm1 + 1;
                myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
            }
            ///Reward Share

        } else {
            //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 12) if (resultCode == -1) {

            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 20;
            String aStringx = Integer.toString(spx);
            // score.setText(aStringx);

            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            share_earn(20);

            if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
                ///Reward Share
                retype = "s";
                Calendar calendar3 = Calendar.getInstance();
                int cur_year1 = calendar3.get(Calendar.YEAR);
                int cur_month1 = calendar3.get(Calendar.MONTH);
                int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                String str_month1 = "" + (cur_month1 + 1);
                if (str_month1.length() == 1) str_month1 = "0" + str_month1;

                String str_day1 = "" + cur_day1;
                if (str_day1.length() == 1) str_day1 = "0" + str_day1;
                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;
                ///
                Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                cn.moveToFirst();
                int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                int gm1s = gm1 + 1;
                myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                ///Reward Share
            }
        } else {
        }

        if (requestCode == 21) if (resultCode == -1) {

            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 20;
            String aStringx = Integer.toString(spx);
            // score.setText(aStringx);

            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            share_earn2(20);

            if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
                ///Reward Share
                retype = "s";
                Calendar calendar3 = Calendar.getInstance();
                int cur_year1 = calendar3.get(Calendar.YEAR);
                int cur_month1 = calendar3.get(Calendar.MONTH);
                int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                String str_month1 = "" + (cur_month1 + 1);
                if (str_month1.length() == 1) str_month1 = "0" + str_month1;

                String str_day1 = "" + cur_day1;
                if (str_day1.length() == 1) str_day1 = "0" + str_day1;
                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;
                ///
                Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                cn.moveToFirst();
                int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                int gm1s = gm1 + 1;
                myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                ///Reward Share
            }
        } else {
        }
        if (requestCode == 16) if (resultCode == -1) {

            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 10;
            String aStringx = Integer.toString(spx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            share_earn2(10);
            ///Reward Share
            retype = "s";
            Calendar calendar3 = Calendar.getInstance();
            int cur_year1 = calendar3.get(Calendar.YEAR);
            int cur_month1 = calendar3.get(Calendar.MONTH);
            int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

            String str_month1 = "" + (cur_month1 + 1);
            if (str_month1.length() == 1) str_month1 = "0" + str_month1;

            String str_day1 = "" + cur_day1;
            if (str_day1.length() == 1) str_day1 = "0" + str_day1;
            final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

            if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
                Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                cn.moveToFirst();
                int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                int gm1s = gm1 + 1;
                myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
            }
            ///Reward Share

        } else {
            //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Word_Game_Hard.this, "date");
        int pos;
        if (date.equals("0")) pos = 1;
        else pos = 2;
        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");
        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");


        try {
            t1.cancel();
            th.cancel();
        } catch (Exception e) {

        }

        //AppEventsLogger.deactivateApp(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        rewardedAd = null;
        interstitialAd = null;
        handler = null;
    }


    public void timee() {
        t1 = new Timer();
        t1.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                t = sps.getInt(getApplicationContext(), "randomtime");
                if (t > 0) {
                    t--;
                    System.out.println("times---" + t);
                    sps.putInt(getApplicationContext(), "randomtime", t);
                    System.out.println("time " + t);
                } else t1.cancel();

            }
        }, 1000, 1000);

    }

    public void send_extraword(String feedback) throws UnsupportedEncodingException {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RetofitClient retrofit = new RetofitClient();
        Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

        email = Utils.android_id(context);
        // String letter= URLDecoder.decode(feedback,"UTF-8");
        String finalString = URLEncoder.encode(feedback, "UTF-8");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("gameid", "4");
        map.put("rowid", "" + letterid);
        map.put("extraword", finalString);
        map.put("tableid", "" + u_id);

        String date = sps.getString(Word_Game_Hard.this, "date");
        if (date.equals("0"))
            map.put("mode", "regular");//nameValuePairs.add(new BasicNameValuePair("mode", "regular"));
        else map.put("mode", "daily");//nameValuePairs.add(new BasicNameValuePair("mode", "daily"));

        Call<List<HashMap<String, String>>> call = api.getWordGameHard_send_extraworddata(map);

        call.enqueue(new Callback<List<HashMap<String, String>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String, String>>> call, Response<List<HashMap<String, String>>> response) {
                if (response.isSuccessful()) {
                    /*String date = sps.getString(New_Main_Activity.this, "date");
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    String line = "";
                    while ((line = rd.readLine()) != null) Log.e("HttpResponse", line);*/

                    Gson gson = new Gson();
                    String result = gson.toJson(response.body());

                    System.out.print("Result============123" + result);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<HashMap<String, String>>> call, Throwable t) {
                // Handle network failures
            }
        });


    }

/*
    public void send_extrawordnew(String feedback) {
        //   Utils.mProgress(Word_Game_Hard.this, ".....", true).show();

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://nithra.mobi/solliadi/extrawords.php");
        try {
            // i=i+5;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
            // Get the deviceID

            email = Utils.android_id(context);
            // String letter= URLDecoder.decode(feedback,"UTF-8");
            String finalString = URLEncoder.encode(feedback, "UTF-8");
            nameValuePairs.add(new BasicNameValuePair("gameid", "4"));
            nameValuePairs.add(new BasicNameValuePair("rowid", "" + letterid));
            nameValuePairs.add(new BasicNameValuePair("extraword", finalString));
            nameValuePairs.add(new BasicNameValuePair("tableid", "" + u_id));
            String date = sps.getString(Word_Game_Hard.this, "date");
            if (date.equals("0")) nameValuePairs.add(new BasicNameValuePair("mode", "regular"));
            else nameValuePairs.add(new BasicNameValuePair("mode", "daily"));


            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) Log.e("HttpResponse", line);

        } catch (IOException e) {

        }
        //  Utils.mProgress.dismiss();
    }
*/


    public void downloadcheck(final String lastid, final String daily) {
        w_head.setVisibility(View.INVISIBLE);
        Utils.mProgress(Word_Game_Hard.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", false).show();
        Utils.mProgress.setCancelable(false);

        RetofitClient retrofit = new RetofitClient();
        Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("lastid", lastid);
        if (daily.equals("ord"))   map.put("mode",  "regular");
        else map.put("mode",  "daily");
        map.put("email", email);

        Call<List<HashMap<String, String>>> call = api.getWordGameHard_downloadcheckdata(map);

        call.enqueue(new Callback<List<HashMap<String, String>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String, String>>> call, Response<List<HashMap<String, String>>> response) {
                if (response.isSuccessful()) {
                    /*String date = sps.getString(New_Main_Activity.this, "date");
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    String line = "";
                    while ((line = rd.readLine()) != null) Log.e("HttpResponse", line);*/

                    Gson gson = new Gson();
                    String result = gson.toJson(response.body());

                    System.out.print("Result============123" + result);
                    try {
                        if (result != null) {
                            JSONArray jArray = new JSONArray(result);
                            System.err.println("Update===" + result);
                            System.out.println("===  " + jArray.length());
                            JSONObject json_data = null;
                            //isvalid=""+jArray.length();
                            downok = "" + jArray.length();
                            System.out.print("insert daily ============" + downok);


                            if (jArray.length() > 0) {
                                json_data = jArray.getJSONObject(0);
                                if (json_data.getString("NoData").equals("NoData"))
                                    downnodata = "NoData";
                                else {
                                    downnodata = "YesData";
                                    for (int i = 0; i < jArray.length(); i++) {
                                        json_data = jArray.getJSONObject(i);
                                        ContentValues cv = new ContentValues();
                                        cv.put("id", json_data.getString("id"));
                                        cv.put("gameid", json_data.getString("gameid"));
                                        cv.put("levelid", json_data.getString("levelid"));
                                        cv.put("letters", json_data.getString("letters"));

                                        String newName = json_data.getString("answer").replaceAll(" ", "");
                                        cv.put("answer", newName);

                                        cv.put("hints", json_data.getString("hints"));
                                        cv.put("imagename", json_data.getString("imagename"));
                                        cv.put("isfinish", "0");

                                        if (daily.equals("ord")) {
                                            cv.put("isdownload", "1");
                                            myDbHelper.insert_data("maintable", null, cv);

                                        } else {

                                            cv.put("date", json_data.getString("date"));
                                            myDbHelper.insert_data("dailytest", null, cv);


                                        }


                                    }
                                }
                            }
                        }

                    } catch (JSONException e1) {
                    }

                } else {

                }
                if (downnodata.equals("NoData")) {
                    Utils.mProgress.dismiss();
                    head.setVisibility(View.INVISIBLE);
                    nextgamesdialog();

                }
                else {
                    downok = "";
                    downnodata = "";
                    if (exists("https://nithra.mobi/solliadi/" + email + "-filename.zip"))
                        checkmemory();
                    else {
                        Utils.mProgress.dismiss();

                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0")) {
                            Cursor c;
                            c = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0' order by id limit 1");
                            c.moveToFirst();
                            if (c.getCount() != 0) next();
                            else nextgamesdialog();
                        } else {
                            Cursor c;
                            c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
                            c.moveToFirst();
                            if (c.getCount() != 0) next();
                            else nextgamesdialog();
                        }


                    }

                }
            }

            @Override
            public void onFailure(Call<List<HashMap<String, String>>> call, Throwable t) {
                // Handle network failures
            }
        });


    }

/*
    public void downloadchecknew(final String lastid, final String daily) {

        w_head.setVisibility(View.INVISIBLE);
        Utils.mProgress(Word_Game_Hard.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", false).show();
        Utils.mProgress.setCancelable(false);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {


                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("lastid", lastid));

                if (daily.equals("ord"))
                    nameValuePairs.add(new BasicNameValuePair("mode", "regular"));
                else nameValuePairs.add(new BasicNameValuePair("mode", "daily"));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(New_Main_Activity.data_check);
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in https connection" + e.toString());
                }
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line = "0";
                    while ((line = reader.readLine()) != null) sb.append(line + "\n");
                    is.close();
                    result = sb.toString();

                    System.out.print("Result============" + result);

                } catch (Exception e) {
                }

                try {
                    if (result != null) {
                        JSONArray jArray = new JSONArray(result);
                        System.err.println("Update===" + result);
                        System.out.println("===  " + jArray.length());
                        JSONObject json_data = null;
                        //isvalid=""+jArray.length();
                        downok = "" + jArray.length();
                        System.out.print("insert daily ============" + downok);


                        if (jArray.length() > 0) {
                            json_data = jArray.getJSONObject(0);
                            if (json_data.getString("NoData").equals("NoData"))
                                downnodata = "NoData";
                            else {
                                downnodata = "YesData";
                                for (int i = 0; i < jArray.length(); i++) {
                                    json_data = jArray.getJSONObject(i);
                                    ContentValues cv = new ContentValues();
                                    cv.put("id", json_data.getString("id"));
                                    cv.put("gameid", json_data.getString("gameid"));
                                    cv.put("levelid", json_data.getString("levelid"));
                                    cv.put("letters", json_data.getString("letters"));

                                    String newName = json_data.getString("answer").replaceAll(" ", "");
                                    cv.put("answer", newName);

                                    cv.put("hints", json_data.getString("hints"));
                                    cv.put("imagename", json_data.getString("imagename"));
                                    cv.put("isfinish", "0");

                                    if (daily.equals("ord")) {
                                        cv.put("isdownload", "1");
                                        myDbHelper.insert_data("maintable", null, cv);

                                    } else {

                                        cv.put("date", json_data.getString("date"));
                                        myDbHelper.insert_data("dailytest", null, cv);


                                    }


                                }
                            }
                        }
                    }

                } catch (JSONException e1) {
                } catch (ParseException e1) {
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                System.out.print("down ok!!!============" + downok + "===");

                if (downnodata.equals("NoData")) {
                    Utils.mProgress.dismiss();
                    head.setVisibility(View.INVISIBLE);
                    nextgamesdialog();

                }
                else {
                    downok = "";
                    downnodata = "";
                    if (exists("https://nithra.mobi/solliadi/" + email + "-filename.zip"))
                        checkmemory();
                    else {
                        Utils.mProgress.dismiss();

                        String date = sps.getString(Word_Game_Hard.this, "date");
                        if (date.equals("0")) {
                            Cursor c;
                            c = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0' order by id limit 1");
                            c.moveToFirst();
                            if (c.getCount() != 0) next();
                            else nextgamesdialog();
                        } else {
                            Cursor c;
                            c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
                            c.moveToFirst();
                            if (c.getCount() != 0) next();
                            else nextgamesdialog();
                        }


                    }

                }

*/
/*
if(downok.equals("")){
        head.setVisibility(View.INVISIBLE);
        new AlertDialog.Builder(Word_Game_Hard.this)
                            *//*
*/
/*.setTitle("Delete entry")*//*
*/
/*
                .setMessage("பதிவுகள் ஏதும் இல்லை .பிறகு முயற்சிக்கவும் ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
}else{
}*//*


            }
        }.execute();
    }
*/

    public void checkmemory() {
        String url = "";
        url = "https://nithra.mobi/solliadi/" + email + "-filename.zip";


        URL uri = null;
        try {
            uri = new URL(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        URLConnection connection = null;
        try {
            connection = uri.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int length = connection.getContentLength();

        String hrSize = "";
        DecimalFormat dec = new DecimalFormat("0.00");
        double filesize = (length) * 3 / 1204;


        if (length > 0) {

            hrSize = dec.format(filesize).concat(" KB");
            System.out.println("KB:--" + hrSize);

        }


        StatFs stat = new StatFs(getFilesDir().getPath());
        double sdAvailSize = (double) stat.getAvailableBlocks() * (double) stat.getBlockSize();

        double gigaAvailable = sdAvailSize / 1073741824;
        double megaaAvailable = sdAvailSize / (1024 * 1024);
        double kiloAvailable = sdAvailSize / 1024;
        if (sdAvailSize > 0) {
            hrSize = dec.format(kiloAvailable).concat(" KB");
            System.out.println("KB:--123" + hrSize);
        }
        if (sdAvailSize > 0) {
            hrSize = dec.format(megaaAvailable).concat(" MB");
            System.out.println("MB:--123" + hrSize);

        }

        if (sdAvailSize > 0) {

            hrSize = dec.format(gigaAvailable).concat(" GB");
            System.out.println("GB:--123" + hrSize);

        }

        //startdownload();
        if (filesize <= kiloAvailable) startDownload();
        else goappmanager();


    }

    public void goappmanager() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext(), android.R.style.Theme_Dialog);

        builder1.setMessage("No free space clean your storage");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Ok", (dialog, id) -> {

            Intent i = new Intent(Intent.ACTION_MANAGE_PACKAGE_STORAGE);

            startActivity(i);

        });
        builder1.setNegativeButton("Later", (dialog, id) -> dialog.cancel());


        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void startDownload() {


        String str_url = "https://nithra.mobi/solliadi/" + email + "-filename.zip";

        //     new DownloadFileAsync().execute(str_url);


        downloadFileAsync = new DownloadFileAsync();
        downloadFileAsync.execute(str_url);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DOWNLOAD_PROGRESS) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("படங்கள் பதிவிறக்கம் செய்யப்படுகிறது காத்திருக்கவும்.... ");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            // playy();
            return mProgressDialog;
        }
        return null;
    }

    public void newdown() {
        RetofitClient retrofit = new RetofitClient();
        Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

        HashMap<String,String> map = new  HashMap<String,String>();
        map.put("filename",email + "-filename.zip");

        Call<List<HashMap<String,String>>> call = api.getWordGameHard_newdowndata(map);

        call.enqueue(new Callback<List<HashMap<String,String>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String,String>>> call, Response<List<HashMap<String,String>>> response) {
                if (response.isSuccessful()) {
                    /*String date = sps.getString(New_Main_Activity.this, "date");
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    String line = "";
                    while ((line = rd.readLine()) != null) Log.e("HttpResponse", line);*/

                    Gson gson= new Gson();
                    String result =gson.toJson(response.body());

                    System.out.print("Result============123" + result);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<HashMap<String,String>>> call, Throwable t) {
                // Handle network failures
            }
        });


    }

   /* public void newdownnew() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("filename", email + "-filename.zip"));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/solliadi1.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in https connection" + e.toString());
                }
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line = "0";
                    while ((line = reader.readLine()) != null) sb.append(line + "\n");
                    is.close();
                    result = sb.toString();

                    System.out.print("Result============123" + result);

                } catch (Exception e) {
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }

        }.execute();

    }*/

    public void unpackZip(String ZIP_FILE_NAME) throws IOException {

        File destDir = new File(getFilesDir() + "/Nithra/solliadi/");
        if (!destDir.exists()) destDir.mkdir();
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(getFilesDir() + "/Nithra/solliadi/" + ZIP_FILE_NAME));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = getFilesDir() + "/Nithra/solliadi/" + File.separator + entry.getName();
            String canonicalPath = destDir.getCanonicalPath();
            if (!canonicalPath.startsWith(filePath)) {
                Log.e("Errorrr", canonicalPath);
            }
            if (!entry.isDirectory()) extractFile(zipIn, filePath);
            else {
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[1024];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) bos.write(bytesIn, 0, read);
        bos.close();
    }

  /*  public void rewarded_adnew() {
        rewardedAd = MaxRewardedAd.getInstance(getResources().getString(R.string.Reward_Ins), this);
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {
                reward_status = 1;
            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                fb_reward = 1;
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
            }

            @Override
            public void onAdHidden(MaxAd ad) {
                rewarded_adnew();
                if (reward_status == 1) {
                    if (extra_coin_s == 0) {
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + mCoinCount;
                        String aStringx = Integer.toString(spx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (rvo == 2) {
                                share_earn2(mCoinCount);
                            } else {
                                vidcoinearn();
                            }
                        }
                    }, 500);
                } else {
                    Toast.makeText(context, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
                }

                fb_reward = 0;


            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                rewardedAd = null;
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                rewardedAd.loadAd();
            }
        });
        rewardedAd.loadAd();
    }*/

    private void rewarded_adnew() {

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        RewardedAd.load(this, sps.getString(Word_Game_Hard.this, "RewardedId"),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.e("LoadAdError=========", loadAdError.toString());
                        rewardedAd = null;
                        //isfaild = 2;

                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        //  isfaild = 1;
                        fb_reward = 1;
                        Log.e(TAG, "Ad was Called.=========");
                        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.e(TAG, "Ad was clicked.=========");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                rewarded_adnew();
                                if (reward_status == 1) {
                                    if (extra_coin_s == 0) {
                                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                        cfx.moveToFirst();
                                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                        int spx = skx + mCoinCount;
                                        String aStringx = Integer.toString(spx);
                                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (rvo == 2) {
                                                share_earn2(mCoinCount);
                                            } else {
                                                vidcoinearn();
                                            }
                                        }
                                    }, 500);
                                } else {
                                    Toast.makeText(context, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();

                                }

                                fb_reward = 0;
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.e(TAG, "Ad dismissed fullscreen content.=========");

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e(TAG, "Ad failed to show fullscreen content.=========");
                                rewardedAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded for an ad.
                                Log.e(TAG, "Ad recorded an impression.=========");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.e(TAG, "Ad showed fullscreen content.=========");
                            }
                        });

                    }
                });
    }

    /*public void show_reward() {
        if (rewardedAd != null && rewardedAd.isReady()) {
            rewardedAd.showAd();
            reward_status = 1;
        } else {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
        }
    }*/

    public void show_reward() {
        if (rewardedAd != null) {
            rewardedAd.show(Word_Game_Hard.this, new OnUserEarnedRewardListener() {

                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d(TAG, "The user earned the reward.");

                    rewardedAd = null;
                    reward_status = 1;
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                }

            });

        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }

    }

   /* private void industrialload() {
        //AppLovinSdk.getInstance( this ).showMediationDebugger();
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, config -> {
            // AppLovin SDK is initialized, start loading ads
            if (mInterstitialAd != null && mInterstitialAd.isReady()) return;
            System.out.println("ad shown  showAdWithDelay initialize done ");
            mInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Ragasiya_sorgal_ins), Word_Game_Hard.this);
            mInterstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    System.out.println("ad shown loaded : " + ad.getWaterfall());
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    handler = null;
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    Log.d("TAG", "Ad dismissed fullscreen content.");
                    mInterstitialAd = null;
                    handler = null;
                    Utills.INSTANCE.Loading_Dialog_dismiss();
                    setSc();
                    industrialload();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    Log.d("TAG", error.toString());
                    mInterstitialAd = null;
                    handler = null;
                    Log.i("TAG", "onAdLoadedfailed" + error.getMessage());
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    Log.e("TAG", "Ad failed to show fullscreen content.");
                    mInterstitialAd = null;
                    handler = null;
                    Utills.INSTANCE.Loading_Dialog_dismiss();
                    sps.putInt(getApplicationContext(), "Game4_Stage_Close_RS", 0);
                    setSc();
                }
            });

            // Load the first ad
            mInterstitialAd.loadAd();

        });

    }*/

    public void industrialload() {
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        AdManagerInterstitialAd.load(this, sps.getString(this, "InterstitialId"), adRequest,
                new AdManagerInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitial) {
                        interstitialAd = interstitial;
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d("TAG", "Ad dismissed fullscreen content.");
                                interstitialAd = null;
                                handler = null;
                                Utills.INSTANCE.Loading_Dialog_dismiss();
                                setSc();
                                industrialload();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Log.e("TAG", "Ad failed to show fullscreen content.");
                                interstitialAd = null;
                                handler = null;
                                Utills.INSTANCE.Loading_Dialog_dismiss();
                                sps.putInt(getApplicationContext(), "Game4_Stage_Close_RS", 0);
                                setSc();
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
        if (sps.getInt(getApplicationContext(), "Game4_Stage_Close_RS") == /*Utills.interstitialadCount*/ Integer.parseInt( sps.getString(this, "showCountOther")) && interstitialAd != null) {
            sps.putInt(getApplicationContext(), "Game4_Stage_Close_RS", 0);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                if (interstitialAd == null) setSc();
                else
                    interstitialAd.show(this);
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game4_Stage_Close_RS", (sps.getInt(getApplicationContext(), "Game4_Stage_Close_RS") + 1));
            if (sps.getInt(context, "Game4_Stage_Close_RS") > /*Utills.interstitialadCount*/ Integer.parseInt( sps.getString(this, "showCountOther")))
                sps.putInt(context, "Game4_Stage_Close_RS", 0);

            setSc();
            //Toast.makeText(this, ""+sps.getInt(this, "Game4_Stage_Close_RS"), Toast.LENGTH_SHORT).show();
        }

    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);
        TextView exit = openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);
        String date = sps.getString(Word_Game_Hard.this, "date");
        if (date.equals("0"))
            next_game.setText("சொல் விளையாட்டு தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        else
            next_game.setText("தினசரி  சொல் விளையாட்டு புதிய பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக  விளையாட காத்திருக்கும் விளையாட்டுக்கள்.");

        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Clue_Game_Hard.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Picture_Game_Hard.class);
            startActivity(i);
        });
        exit.setOnClickListener(v -> {
            if (main_act.equals("")) {
                finish();
                Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Word_Game_Hard.this, "game_area", "on");
                finish();
            }
            sps.putString(Word_Game_Hard.this, "date", "0");
        });

        Cursor ct;
        ct = myDbHelper.getQry("select * from maintable where isfinish='0' order by id limit 1");
        ct.moveToFirst();
        if (ct.getCount() != 0) {
            Cursor c;
            c = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0' order by id limit 1");
            c.moveToFirst();
            if (c.getCount() != 0) p_game.setVisibility(View.VISIBLE);
            Cursor c2;
            c2 = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='0' order by id limit 1");
            c2.moveToFirst();
            if (c2.getCount() != 0) c_game.setVisibility(View.VISIBLE);
            Cursor c3;
            c3 = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='0' order by id limit 1");
            c3.moveToFirst();
            if (c3.getCount() != 0) s_game.setVisibility(View.VISIBLE);
            Cursor c4;
            c4 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0' order by id limit 1");
            c4.moveToFirst();
            if (c4.getCount() != 0) w_game.setVisibility(View.VISIBLE);

        } else {
            next_game.setText("புதிய பதிவுகள் ஏதும் இல்லை.விரைவில் வினாக்கள் பதிவேற்றம் செய்யப்படும்.");
            exit.setText("சரி ");
            exit.setVisibility(View.VISIBLE);
        }

        TextView odd_man_out = openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Odd_man_out.class);
            startActivity(i);
        });
        Cursor cts;
        cts = newhelper.getQry("select * from newmaintable where isfinish='0' order by id limit 1");
        cts.moveToFirst();
        if (cts.getCount() != 0) {
            Cursor sc;
            sc = newhelper.getQry("select * from newmaintable where gameid='5' and isfinish='0' order by id limit 1");
            sc.moveToFirst();
            if (sc.getCount() != 0) odd_man_out.setVisibility(View.VISIBLE);
            Cursor scs;
            scs = newhelper.getQry("select * from newmaintable where gameid='6' and isfinish='0' order by id limit 1");
            scs.moveToFirst();
            if (scs.getCount() != 0) matchword.setVisibility(View.VISIBLE);
        }

        TextView opposite_word = openDialog.findViewById(R.id.opposite_word);
        TextView ote_to_tamil = openDialog.findViewById(R.id.ote_to_tamil);
        opposite_word.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Ote_to_Tamil.class);
            startActivity(i);
        });

        Cursor ctd;
        ctd = newhelper2.getQry("select * from newmaintable2 where isfinish='0' order by id limit 1");
        ctd.moveToFirst();
        if (ctd.getCount() != 0) {
            Cursor scd;
            scd = newhelper2.getQry("select * from newmaintable2 where gameid='7' and isfinish='0' order by id limit 1");
            scd.moveToFirst();
            if (scd.getCount() != 0) opposite_word.setVisibility(View.VISIBLE);
            Cursor scdd;
            scdd = newhelper2.getQry("select * from newmaintable2 where gameid='8' and isfinish='0' order by id limit 1");
            scdd.moveToFirst();
            if (scdd.getCount() != 0) ote_to_tamil.setVisibility(View.VISIBLE);


        }


        TextView seerpaduthu = openDialog.findViewById(R.id.seerpaduthu);
        TextView puthir = openDialog.findViewById(R.id.puthir);
        TextView tirukural = openDialog.findViewById(R.id.tirukural);
        TextView pilaithiruthu = openDialog.findViewById(R.id.pilaithiruthu);


        if (sps.getString(context, "newgame_notification").equals("start")) {
            Cursor ctds;
            ctds = newhelper3.getQry("select * from right_order where isfinish='0' order by id limit 1");
            ctds.moveToFirst();
            if (ctds.getCount() != 0) {
                Cursor scds;
                scds = newhelper3.getQry("select * from right_order where gameid='9' and isfinish='0' order by id limit 1");
                scds.moveToFirst();
                if (scds.getCount() != 0) seerpaduthu.setVisibility(View.VISIBLE);
                Cursor scdds;
                scdds = newhelper3.getQry("select * from right_order where gameid='10' and isfinish='0' order by id limit 1");
                scdds.moveToFirst();
                if (scdds.getCount() != 0) puthir.setVisibility(View.VISIBLE);

                Cursor c3s;
                c3s = newhelper3.getQry("select * from right_order where gameid='11' and isfinish='0' order by id limit 1");
                c3s.moveToFirst();
                if (c3s.getCount() != 0) pilaithiruthu.setVisibility(View.VISIBLE);
                Cursor c4s;
                c4s = newhelper3.getQry("select * from right_order where gameid='12' and isfinish='0' order by id limit 1");
                c4s.moveToFirst();
                if (c4s.getCount() != 0) tirukural.setVisibility(View.VISIBLE);
            }
        }


        seerpaduthu.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Riddle_game.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, WordError_correction.class);
            startActivity(i);
        });

        TextView fill_in_blanks = openDialog.findViewById(R.id.fill_in_blanks);
        TextView eng_to_tamil = openDialog.findViewById(R.id.eng_to_tamil);

        Cursor scds;
        scds = newhelper4.getQry("select * from newgamesdb4 where gameid='13' and isfinish='0' order by id limit 1");
        scds.moveToFirst();
        if (scds.getCount() != 0) fill_in_blanks.setVisibility(View.VISIBLE);

        Cursor scdss;
        scdss = newhelper4.getQry("select * from newgamesdb4 where gameid='14' and isfinish='0' order by id limit 1");
        scdss.moveToFirst();
        if (scdss.getCount() != 0) eng_to_tamil.setVisibility(View.VISIBLE);

        fill_in_blanks.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Fill_in_blanks.class);
            startActivity(i);
        });


        TextView quiz = openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(context);
        Cursor cn28ws = newhelper5.getQry("select * from newgames5 where gameid='15' and isfinish='0'");
        cn28ws.moveToFirst();
        if (cn28ws.getCount() != 0) match_words.setVisibility(View.VISIBLE);
        Cursor cn27ws = newhelper5.getQry("select * from newgames5 where gameid='16' and isfinish='0'");
        cn27ws.moveToFirst();
        if (cn27ws.getCount() != 0) find_words_from_pictures.setVisibility(View.VISIBLE);

        Cursor cn22 = newhelper5.getQry("select * from newgames5 where gameid='17' and isfinish='0'");
        cn22.moveToFirst();
        if (cn22.getCount() != 0) quiz.setVisibility(View.VISIBLE);

        match_words.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Match_tha_fallows_game.class);
            startActivity(i);

        });
        find_words_from_pictures.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Find_words_from_picture.class);
            startActivity(i);
        });
        quiz.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Quiz_Game.class);
            startActivity(i);
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Word_Game_Hard.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        if (jmp != null && jmp.moveToFirst()) jamble_words.setVisibility(View.VISIBLE);

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Jamble_word_game.class);
            startActivity(i);
        });
        TextView missing_words = openDialog.findViewById(R.id.missing_words);
        Cursor jmps;
        jmps = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='0' order by id limit 1");
        if (jmps != null && jmps.moveToFirst()) missing_words.setVisibility(View.VISIBLE);
        missing_words.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Missing_Words.class);
            startActivity(i);
        });
        TextView six_differences = openDialog.findViewById(R.id.six_differences);
        Cursor dif;
        dif = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='0' order by id limit 1");
        if (dif != null && dif.moveToFirst()) six_differences.setVisibility(View.VISIBLE);
        six_differences.setOnClickListener(v -> {
            finish();
            sps.putString(Word_Game_Hard.this, "date", "0");
            Intent i = new Intent(Word_Game_Hard.this, Find_difference_between_pictures.class);
            startActivity(i);
        });

        if (!isFinishing()) openDialog.show();
        openDialog.setOnKeyListener((dialog, keyCode, event) -> {

            if (main_act.equals("")) {

                finish();
                //     openDialog_s.dismiss();
                Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Word_Game_Hard.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Word_Game_Hard.this, "date", "0");

            return keyCode == KeyEvent.KEYCODE_BACK;
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 152)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Word_Game_Hard.this, "permission", 1);
                if (share_name == 1) {
                    final String a = "com.facebook.katana";
                    helpshare(a);
                } else if (share_name == 2) {
                    String a = "com.whatsapp";
                    helpshare(a);
                } else if (share_name == 3) {
                    String a = "com.google.android.apps.plus";
                    helpshare(a);
                }
            }
    }

    public void completegame() {


        //table for reward
        Calendar calendar3 = Calendar.getInstance();
        int cur_year1 = calendar3.get(Calendar.YEAR);
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        String str_month1 = "" + (cur_month1 + 1);
        if (str_month1.length() == 1) str_month1 = "0" + str_month1;

        String str_day1 = "" + cur_day1;
        if (str_day1.length() == 1) str_day1 = "0" + str_day1;
        final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

        myDbHelper.executeSql("create table if not exists userdetail(id integer,name varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");

        if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
            System.out.println("=======inside");
            ///Game states for reward
            //  db1.execSQL("create table if not exists userdata_d(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
            myDbHelper.executeSql("create table if not exists userdata_r(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,type varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
            ///Game states for reward

            String mobileno = null;
            Cursor sc3 = myDbHelper.getQry("select * from userdetail");
            sc3.moveToFirst();
            if (sc3.getCount() != 0) mobileno = sc3.getString(sc3.getColumnIndexOrThrow("phno"));

            Cursor sc2 = myDbHelper.getQry("select * from userdata_r where date ='" + str_date1 + "' and type='d'");
            sc2.moveToFirst();
            if (sc2.getCount() == 0) {
                System.out.println("=======inserting1" + mobileno);
                ContentValues cv = new ContentValues();
                cv.put("phno", "" + mobileno);
                cv.put("date", "" + str_date1);
                cv.put("type", "d");
                cv.put("gm1", "0");
                cv.put("gm2", "0");
                cv.put("gm3", "0");
                cv.put("gm4", "0");
                cv.put("score", "0");
                cv.put("playtime", "0");
                cv.put("isfinish", "0");
                myDbHelper.insert_data("userdata_r", null, cv);
            }

            Cursor sc4 = myDbHelper.getQry("select * from userdata_r where date ='" + str_date1 + "' and type='r'");
            sc4.moveToFirst();
            if (sc4.getCount() == 0) {
                System.out.println("=======inserting2" + str_date1);
                ContentValues cv = new ContentValues();
                cv.put("phno", "" + mobileno);
                cv.put("date", "" + str_date1);
                cv.put("type", "r");
                cv.put("gm1", "0");
                cv.put("gm2", "0");
                cv.put("gm3", "0");
                cv.put("gm4", "0");
                cv.put("score", "0");
                cv.put("playtime", "0");
                cv.put("isfinish", "0");
                myDbHelper.insert_data("userdata_r", null, cv);
            }


            Cursor sc5 = myDbHelper.getQry("select * from userdata_r where date ='" + str_date1 + "' and type='s'");
            sc5.moveToFirst();
            if (sc5.getCount() == 0) {
                System.out.println("=======inserting3" + str_date1);
                ContentValues cv = new ContentValues();
                cv.put("phno", "" + mobileno);
                cv.put("date", "" + str_date1);
                cv.put("type", "s");
                cv.put("gm1", "0");
                cv.put("gm2", "0");
                cv.put("gm3", "0");
                cv.put("gm4", "0");
                cv.put("score", "0");
                cv.put("playtime", "0");
                cv.put("isfinish", "0");
                myDbHelper.insert_data("userdata_r", null, cv);
            }
        }
        //table for reward

        String dates = sps.getString(Word_Game_Hard.this, "date");
        if (dates.equals("0")) retype = "r";
        else retype = "d";
        if (dates.equals("0")) rdvalu = 1;
        else rdvalu = 2;
        if (retype.equals("r")) {
            if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
                Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                cn.moveToFirst();
                int cns = cn.getInt(cn.getColumnIndexOrThrow("score"));
                int time = cn.getInt(cn.getColumnIndexOrThrow("playtime"));
                int gm1 = cn.getInt(cn.getColumnIndexOrThrow("gm4"));
                int cnse = 0;
                long ptime;


                long ttstime = 0;
                long timeElapseds = SystemClock.elapsedRealtime() - focus.getBase();
                int hourss = (int) (timeElapseds / 3600000);
                int minutess = (int) (timeElapseds - hourss * 3600000) / 60000;
                int secondss = (int) (timeElapseds - hourss * 3600000 - minutess * 60000) / 1000;

                int mins = hourss * 60;
                int secs = mins * 60;
                int sec2s = minutess * 60;
                ttstime = secs + sec2s + secondss;

                ptime = time + ttstime;
                int gm1s = gm1 + 1;

                Cursor csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='0'");
                csk.moveToFirst();
                if (csk.getCount() != 0) {
                    int r = csk.getCount();
                    int y = r * 10;
                    cns = cns + y;
                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + cns + "' where type ='" + retype + "'and date='" + str_date1 + "'");

                }

                //  myDbHelper.executeSql("UPDATE userdata_r SET score='" + cnse + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                myDbHelper.executeSql("UPDATE userdata_r SET playtime='" + ptime + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                //  myDbHelper.executeSql("UPDATE userdata_r SET gm2='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
            }


            if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
                Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                cn.moveToFirst();
                int gm1 = cn.getInt(cn.getColumnIndexOrThrow("gm4"));
                int gm1s = gm1 + 1;
                myDbHelper.executeSql("UPDATE userdata_r SET gm4='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
            }

        } else if (retype.equals("d"))
            if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes"))
                if (sps.getString(Word_Game_Hard.this, "yes_reward").equals("yes")) {


                    System.out.println("=====print ok");
                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + dates + "'");
                    cn.moveToFirst();
                    int cns = cn.getInt(cn.getColumnIndexOrThrow("score"));
                    int time = cn.getInt(cn.getColumnIndexOrThrow("playtime"));
                    int gm1 = cn.getInt(cn.getColumnIndexOrThrow("gm4"));
                    int cnse = 0;
                    long ptime;


                    long ttstime = 0;
                    long timeElapseds = SystemClock.elapsedRealtime() - focus.getBase();
                    int hourss = (int) (timeElapseds / 3600000);
                    int minutess = (int) (timeElapseds - hourss * 3600000) / 60000;
                    int secondss = (int) (timeElapseds - hourss * 3600000 - minutess * 60000) / 1000;

                    int mins = hourss * 60;
                    int secs = mins * 60;
                    int sec2s = minutess * 60;
                    ttstime = secs + sec2s + secondss;

                    ptime = time + ttstime;
                    int gm1s = gm1 + 1;

                    Cursor csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='0'");
                    csk.moveToFirst();
                    if (csk.getCount() != 0) {
                        int r = csk.getCount();
                        int y = r * 10;
                        cns = cns + y;
                        myDbHelper.executeSql("UPDATE userdata_r SET score='" + cns + "' where type ='" + retype + "'and date='" + dates + "'");

                    }

                    //  myDbHelper.executeSql("UPDATE userdata_r SET score='" + cnse + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                    myDbHelper.executeSql("UPDATE userdata_r SET playtime='" + ptime + "' where type ='" + retype + "'and date='" + dates + "'");
                    //  myDbHelper.executeSql("UPDATE userdata_r SET gm2='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");

                    if (sps.getString(Word_Game_Hard.this, "complite_reg").equals("yes")) {
                        Cursor cnw = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + dates + "'");
                        cnw.moveToFirst();
                        int gm1w = cnw.getInt(cnw.getColumnIndexOrThrow("gm4"));
                        int gm1sw = gm1w + 1;
                        myDbHelper.executeSql("UPDATE userdata_r SET gm4='" + gm1sw + "' where type ='" + retype + "'and date='" + dates + "'");
                    }
                } else System.out.println("=====print not ok");


    }

    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Word_Game_Hard.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }

    public void vidcoinearn() {
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea = ea + setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = openDialog.findViewById(R.id.ok_y);
            TextView b_scores = openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + mCoinCount;
            final String aStringx = Integer.toString(spx);


            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(v -> {
                score.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            });

            openDialog.show();
        }

    }


    //*********************reward videos process 3***********************

    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog.findViewById(R.id.ok_y);
        TextView b_scores = openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        b_scores.setText("" + a);


        ok_y.setOnClickListener(v -> {
            score.setText("" + skx);
            openDialog.dismiss();
            //mCoinCount = 0;
        });

        if (!isFinishing()) openDialog.show();
    }


    //reward videos***********************//

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Word_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog.findViewById(R.id.ok_y);
        TextView b_scores = openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        b_scores.setText("" + a);


        ok_y.setOnClickListener(v -> {
            ttscores.setText("" + skx);
            score.setText("" + skx);
            openDialog.dismiss();
            //mCoinCount = 0;
        });

        if (!isFinishing()) openDialog.show();
    }

    public void downloaddata_daily() {

        if (isNetworkAvailable(Word_Game_Hard.this)) {
            Cursor c1 = myDbHelper.getQry("select id from dailytest order by id DESC");
            c1.moveToFirst();


            System.out.print("Count====" + c1.getCount());


            if (c1.getCount() != 0) {
                //c1.getString(c1.getColumnIndexOrThrow("id"));
                System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));
                downloadcheck("" + c1.getString(c1.getColumnIndexOrThrow("id")), "daily");


            } else {

                System.out.print("else====");
                downloadcheck("0", "daily");
            }


        } else {

            head.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Word_Game_Hard.this);                          /*  .setTitle("Delete entry")*/
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog, which) -> {
                // continue with delete
                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                sps.putInt(Word_Game_Hard.this, "goto_sett", 1);
                dialog.dismiss();
            }).setNegativeButton("பின்னர்", (dialog, which) -> {
                // do nothing

                String date = sps.getString(Word_Game_Hard.this, "date");
                if (date.equals("0")) backexitnet();
                else backexitnet();
                dialog.dismiss();
            }).setIcon(android.R.drawable.ic_dialog_alert).show();

        }
    }

    public void downloaddata_regular() {

        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Word_Game_Hard.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {

            //DownLoad Letters and Words

            if (isNetworkAvailable(Word_Game_Hard.this)) {
                Cursor c1 = myDbHelper.getQry("select id from maintable order by id DESC");
                c1.moveToFirst();


                System.out.print("Count====" + c1.getCount());


                if (c1.getCount() != 0) {


                    //c1.getString(c1.getColumnIndexOrThrow("id"));

                    System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));

                    downloadcheck("" + c1.getString(c1.getColumnIndexOrThrow("id")), "ord");

                } else downloadcheck("0", "ord");
            } else {

                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Word_Game_Hard.this);                           /* .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog12, which) -> {
                    // continue with delete

                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Word_Game_Hard.this, "goto_sett", 1);
                    dialog12.dismiss();
                }).setNegativeButton("பின்னர்", (dialog1, which) -> {
                    // do nothing
                    sps.putString(Word_Game_Hard.this, "game_area", "on");

                    String date = sps.getString(Word_Game_Hard.this, "date");
                    if (date.equals("0")) backexitnet();
                    else backexitnet();
                    dialog1.dismiss();
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
            }


        });
        alertDialogBuilder.setPositiveButton("இல்லை ", (dialog, id) -> {
            sps.putString(Word_Game_Hard.this, "game_area", "on");
            finish();
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Word_Game_Hard.this, "date");
        int pos;
        if (date.equals("0")) pos = 1;
        else pos = 2;
        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");
        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + letterid + "' and gameid='" + gameid + "' and rd='" + pos + "'");

        helpshare(a);
    }

    public void ins_app(final Context context, View view1, int vall) {
        TextView titt = view1.findViewById(R.id.txtlist);
        ImageView logo = view1.findViewById(R.id.imageview);
        final Button inss = view1.findViewById(R.id.btn_cont);
        if (vall == 1 || vall == 0) if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
            titt.setText("நித்ரா தமிழ் நாட்காட்டி");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcalender&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.calender_logo);
        } else if (!appInstalledOrNot(context, "com.bharathdictionary")) {
            titt.setText("English to Tamil Dictionary Offline - தமிழ் அகராதி");
            inss.setTag("https://play.google.com/store/apps/details?id=com.bharathdictionary&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.dic_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamil.vivasayam.agriculture.market")) {
            titt.setText("நித்ரா விவசாயம், மாடித்தோட்டம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamil.vivasayam.agriculture.market&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.vivasayam_logo);
        } else if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
            titt.setText("நித்ரா சமையல் - சைவம் மற்றும் அசைவம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.samayalkurippu&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.samayal_logo);
        } else if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
            titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.jobs_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
            titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.crossword_logo);
        } else {
            titt.setText("நித்ரா  செயலிகள்");
            inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
            logo.setImageResource(R.drawable.nithralogo);
        }
        else if (vall == 2) if (!appInstalledOrNot(context, "com.bharathdictionary")) {
            titt.setText("English to Tamil Dictionary Offline - தமிழ் அகராதி");
            inss.setTag("https://play.google.com/store/apps/details?id=com.bharathdictionary&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.dic_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamil.vivasayam.agriculture.market")) {
            titt.setText("நித்ரா விவசாயம், மாடித்தோட்டம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamil.vivasayam.agriculture.market&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.vivasayam_logo);
        } else if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
            titt.setText("நித்ரா சமையல் - சைவம் மற்றும் அசைவம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.samayalkurippu&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.samayal_logo);
        } else if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
            titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.jobs_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
            titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.crossword_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
            titt.setText("நித்ரா தமிழ் நாட்காட்டி");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcalender&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.calender_logo);
        } else {
            titt.setText("நித்ரா  செயலிகள்");
            inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
            logo.setImageResource(R.drawable.nithralogo);
        }
        else if (vall == 3)
            if (!appInstalledOrNot(context, "nithra.tamil.vivasayam.agriculture.market")) {
                titt.setText("நித்ரா விவசாயம், மாடித்தோட்டம்");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamil.vivasayam.agriculture.market&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.vivasayam_logo);
            } else if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
                titt.setText("நித்ரா சமையல் - சைவம் மற்றும் அசைவம்");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.samayalkurippu&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.samayal_logo);
            } else if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.jobs_logo);
            } else if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
                titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.crossword_logo);
            } else if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
                titt.setText("நித்ரா தமிழ் நாட்காட்டி");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcalender&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.calender_logo);
            } else if (!appInstalledOrNot(context, "com.bharathdictionary")) {
                titt.setText("English to Tamil Dictionary Offline - தமிழ் அகராதி");
                inss.setTag("https://play.google.com/store/apps/details?id=com.bharathdictionary&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.dic_logo);
            } else {
                titt.setText("நித்ரா  செயலிகள்");
                inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
                logo.setImageResource(R.drawable.nithralogo);
            }
        else if (vall == 4) if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
            titt.setText("நித்ரா சமையல் - சைவம் மற்றும் அசைவம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.samayalkurippu&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.samayal_logo);
        } else if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
            titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.jobs_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
            titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.crossword_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
            titt.setText("நித்ரா தமிழ் நாட்காட்டி");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcalender&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.calender_logo);
        } else if (!appInstalledOrNot(context, "com.bharathdictionary")) {
            titt.setText("English to Tamil Dictionary Offline - தமிழ் அகராதி");
            inss.setTag("https://play.google.com/store/apps/details?id=com.bharathdictionary&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.dic_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamil.vivasayam.agriculture.market")) {
            titt.setText("நித்ரா விவசாயம், மாடித்தோட்டம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamil.vivasayam.agriculture.market&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.vivasayam_logo);
        } else {
            titt.setText("நித்ரா  செயலிகள்");
            inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
            logo.setImageResource(R.drawable.nithralogo);
        }
        else if (vall == 5) if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
            titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.jobs_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
            titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.crossword_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
            titt.setText("நித்ரா தமிழ் நாட்காட்டி");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcalender&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.calender_logo);
        } else if (!appInstalledOrNot(context, "com.bharathdictionary")) {
            titt.setText("English to Tamil Dictionary Offline - தமிழ் அகராதி");
            inss.setTag("https://play.google.com/store/apps/details?id=com.bharathdictionary&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.dic_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamil.vivasayam.agriculture.market")) {
            titt.setText("நித்ரா விவசாயம், மாடித்தோட்டம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamil.vivasayam.agriculture.market&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.vivasayam_logo);
        } else if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
            titt.setText("நித்ரா சமையல் - சைவம் மற்றும் அசைவம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.samayalkurippu&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.samayal_logo);
        } else {
            titt.setText("நித்ரா  செயலிகள்");
            inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
            logo.setImageResource(R.drawable.nithralogo);
        }
        else if (vall == 6) if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
            titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.crossword_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
            titt.setText("நித்ரா தமிழ் நாட்காட்டி");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcalender&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.calender_logo);
        } else if (!appInstalledOrNot(context, "com.bharathdictionary")) {
            titt.setText("English to Tamil Dictionary Offline - தமிழ் அகராதி");
            inss.setTag("https://play.google.com/store/apps/details?id=com.bharathdictionary&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.dic_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamil.vivasayam.agriculture.market")) {
            titt.setText("நித்ரா விவசாயம், மாடித்தோட்டம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamil.vivasayam.agriculture.market&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.vivasayam_logo);
        } else if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
            titt.setText("நித்ரா சமையல் - சைவம் மற்றும் அசைவம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.samayalkurippu&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.samayal_logo);
        } else if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
            titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.jobs_logo);
        } else {
            titt.setText("நித்ரா  செயலிகள்");
            inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
            logo.setImageResource(R.drawable.nithralogo);
        }
        else if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
            titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.crossword_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
            titt.setText("நித்ரா தமிழ் நாட்காட்டி");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcalender&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.calender_logo);
        } else if (!appInstalledOrNot(context, "com.bharathdictionary")) {
            titt.setText("English to Tamil Dictionary Offline - தமிழ் அகராதி");
            inss.setTag("https://play.google.com/store/apps/details?id=com.bharathdictionary&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.dic_logo);
        } else if (!appInstalledOrNot(context, "nithra.tamil.vivasayam.agriculture.market")) {
            titt.setText("நித்ரா விவசாயம், மாடித்தோட்டம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamil.vivasayam.agriculture.market&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.vivasayam_logo);
        } else if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
            titt.setText("நித்ரா சமையல் - சைவம் மற்றும் அசைவம்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.samayalkurippu&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.samayal_logo);
        } else if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
            titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
            inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
            logo.setImageResource(R.drawable.jobs_logo);
        } else {
            titt.setText("நித்ரா  செயலிகள்");
            inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
            logo.setImageResource(R.drawable.nithralogo);
        }

        view1.setOnClickListener(view -> {
            if (isNetworkAvailable(context))
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inss.getTag().toString())));
            else Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
        });

        inss.setOnClickListener(view -> {
            if (isNetworkAvailable(context))
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(view.getTag().toString())));
            else Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
        });
    }

    private boolean appInstalledOrNot(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    //*** In Adapter **

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Word_Game_Hard.this, "showcase_dismiss_wd").equals(""))
                showcase_dismiss();
            else {
                sps.putString(Word_Game_Hard.this, "time_start", "yes");
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

            }

        }, 800);
    }

    private void update_price() {
        ////////////////Prize//////////////////
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        int f_sec = sec + sec2 + seconds;
        String date = sps.getString(Word_Game_Hard.this, "date");
        if (date.equals("0")) if (timeElapsed <= 91300) {
            if (b_score == tscore) prize_data_update(Word_Game_Hard.this, 75);
            else if (b_score != 0) prize_data_update(Word_Game_Hard.this, 25);
        } else if (timeElapsed > 91300) {
            if (b_score == tscore) prize_data_update(Word_Game_Hard.this, 50);
            else if (b_score != 0) prize_data_update(Word_Game_Hard.this, 25);
        } else if (b_score != 0) prize_data_update(Word_Game_Hard.this, 25);
        else if (timeElapsed <= 91300) {
            if (b_score == tscore) prize_data_update(Word_Game_Hard.this, 100);
            else if (b_score != 0) prize_data_update(Word_Game_Hard.this, 50);
        } else if (timeElapsed > 91300) {
            if (b_score == tscore) prize_data_update(Word_Game_Hard.this, 75);
            else if (b_score != 0) prize_data_update(Word_Game_Hard.this, 50);
        } else if (b_score != 0) prize_data_update(Word_Game_Hard.this, 25);
        ////////////////Prize//////////////////
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
            startActivity(i);
        } else finish();
    }

    public void settingpermission() {
        if (sps.getInt(Word_Game_Hard.this, "permission") == 2) {
            AlertDialog alertDialog = new AlertDialog.Builder(Word_Game_Hard.this).create();
            alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய Settings-ல் உள்ள permission-யை allow செய்யவேண்டும்");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings ", (dialog, which) -> {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                intent.setData(uri);
                getApplicationContext().startActivity(intent);
                setting_access = 1;
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ", (dialog, which) -> {
                sps.putString(Word_Game_Hard.this, "game_area", "on");
                String date = sps.getString(Word_Game_Hard.this, "date");
                if (date.equals("0")) if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                    startActivity(i);
                } else finish();
                else {
                    finish();
                    Intent i = new Intent(Word_Game_Hard.this, New_Main_Activity.class);
                    startActivity(i);
                }
                dialog.dismiss();
            });


            alertDialog.show();
        }
    }

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(aurl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                    return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                final int fileLength = connection.getContentLength();

                File SDCardRoot = getFilesDir();

                File fol = new File(SDCardRoot + "/Nithra/solliadi/");
                if (!fol.exists()) fol.mkdirs();

                File file = new File(SDCardRoot + "/Nithra/solliadi/", email + "-filename.zip");

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(file);

                byte[] data = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    publishProgress("" + (int) ((total * 100) / fileLength));
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        output.write(data, 0, count);
                }
                unpackZip(email + "-filename.zip");


            } catch (Exception e) {


                return e.toString();
            } finally {
                try {
                    if (output != null) output.close();
                    if (input != null) input.close();
                } catch (IOException ignored) {
                }

                if (connection != null) connection.disconnect();


            }
            return null;

        }


        @Override
        protected void onPostExecute(String unused) {
            mProgressDialog.dismiss();

            if (unused != null && unused.equals("ERROR_DOW")) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Word_Game_Hard.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setTitle("Network connection not available, please check it!");
                alertDialogBuilder.setPositiveButton("Ok", (dialog, id) -> {
                    dialog.dismiss();
                    downloadFileAsync.isCancelled();
                    downloadFileAsync.cancel(true);


                    if (exists("https://nithra.mobi/solliadi/" + email + "-filename.zip")) {
                        System.out.print("========zip ok");
                        checkmemory();
                    }

                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            } else {
                Utils.mProgress.dismiss();
                newdown();
                String date = sps.getString(Word_Game_Hard.this, "date");
                if (date.equals("0")) {
                    Cursor c;
                    c = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0' order by id limit 1");
                    c.moveToFirst();
                    if (c.getCount() != 0) next();
                    else nextgamesdialog();
                } else {
                    Cursor c;
                    c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
                    c.moveToFirst();
                    if (c.getCount() != 0) next();
                    else nextgamesdialog();
                }
            }

        }

        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }
    }
}


