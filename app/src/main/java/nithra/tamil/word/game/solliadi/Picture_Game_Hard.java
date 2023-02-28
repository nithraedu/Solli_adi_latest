package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.fb_addload_score_screen;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.material.snackbar.Snackbar;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.adutils.Ad_NativieUtils;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Picture_Game_Hard extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    int fb_reward = 0;
    //com.facebook.ads.RewardedVideoAd rewardedVideoAd;

    private MaxRewardedAd rewardedAd;
    int val=0;
    int reward_status = 0;
    //*********************reward videos process 1***********************
    //private final String AD_UNIT_ID = getString(R.string.rewarded);
    private static final String APP_ID = "ca-app-pub-4267540560263635~9441478701";
    private static final long COUNTER_TIME = 10;
    private static final int GAME_OVER_REWARD = 1;


    private boolean mGameOver;
    private boolean mGamePaused;

    private long mTimeRemaining;
    //reward videos process 1***********************


    public static final String TAG = "SavedGames";

    // The AppState slot we are editing.  For simplicity this sample only manipulates a single
    // Cloud Save slot and a corresponding Snapshot entry,  This could be changed to any integer
    // 0-3 without changing functionality (Cloud Save has four slots, numbered 0-3).
    private static final int APP_STATE_KEY = 1;

    // Request code used to invoke sign-in UI.
    private static final int RC_SIGN_IN = 9001;

    // Request code used to invoke Snapshot selection UI.
    private static final int RC_SELECT_SNAPSHOT = 9002;

    /// Client used to interact with Google APIs.
    private GoogleApiClient mGoogleApiClient;


    // True when the application is attempting to resolve a sign-in error that has a possible
    // resolution,
    private boolean mIsResolving = false;

    // True immediately after the user clicks the sign-in button/
    private boolean mSignInClicked = false;

    // True if we want to automatically attempt to sign in the user at application start.
    private boolean mAutoStartSignIn = true;


    // Facebook variable starts

    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";

    private PendingAction pendingAction = PendingAction.NONE;

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }


    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

    /*   private UiLifecycleHelper uiHelper;

       private Session.StatusCallback callback = new Session.StatusCallback() {
           @Override
           public void call(Session session, SessionState state,
                            Exception exception) {
               // onSessionStateChange(session, state, exception);
           }
       };

       private FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
           @Override
           public void onError(FacebookDialog.PendingCall pendingCall,
                               Exception error, Bundle data) {
               Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
           }

           @Override
           public void onComplete(FacebookDialog.PendingCall pendingCall,
                                  Bundle data) {
               Log.d("HelloFacebook", "Success!");
           }
       };*/
    String btn_str = "";
    // facebook variable ends


    DataBaseHelper myDbHelper;
    Typeface typ, tyr;
    TextView p_time, score, to_no;
    Chronometer focus;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    TextView bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15, bt16;
    ImageView img1, img2, img3, img4;
    TextView intro, p_clear;
    SharedPreference sps = new SharedPreference();
    static SharedPreference sp = new SharedPreference();
    EditText p_edit;
    // MediaPlayer c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20;
    // MediaPlayer w1;


    int level;
    String isdown = "";
    int gameid = 1;
    int wordid;
    TextView ans_show;
    int word_type, image_type;
    String answer;
    TextView p_setting;
    TextView toggleButton;
    TextView u_verify;
    String imid;
    RadioButton fn1, fn2, fn3;
    int f_sec;
    //MediaPlayer r1, play1;
    TextView p_coin;
    int e2;
    LinearLayout adds, list4;
    TouchImageView pic_show;
    int im11, im12, im13, im14;
    int r = 0;
    static int f;
    String clue;
    TextView pic_clue, clue_ans;
    PopupWindow popupWindow;
    int k = 1;
    RelativeLayout w_head, helpshare_layout;
    TextView shareq, h_gplues, h_watts_app, h_facebook;
    String sa;
    //Randem Number
    Random q = new Random();
    int minNumber = 1;
    int maxNumber = 3;
    int rnum;
    JSONArray warray, warray2, carray, sarray, sarray2;
    String str_vpcont;
    String email = "";

    TextView earncoin;

    Timer t1, th;
    int t, t2;



    LinearLayout qtw;
    int min = 1;
    int max = 3;
    int random;


    String result = null;
    TextView next_continue;

    String downok = "", downnodata = "";
    DownloadFileAsync downloadFileAsync;
    ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    TextView ttscores;
    long ttstop;
    int noclue = 0;
    String id;
    int ry;
    SoundPool spz1, spz2, spz3, spz4;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    static int vs = 0;
    String retype = "s";
    //
    int setting_access = 0;

    int picdig = 0;
    Dialog openDialogk;
    static int mCoinCount = 20;
    static int rvo = 0;
    RelativeLayout edit_buttons_layout;
    Dialog openDialog_earncoin, openDialog_p;
    int s = 0;

    /////////native advance////////////
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-4267540560263635/9323490091";
    private static final String ADMOB_APP_ID = "ca-app-pub-4267540560263635~3166935503";
    /////////native advance////////////
    /////////Native_Top_Advanced////////////
    private static final String ADMOB_AD_UNIT_ID_Top = "ca-app-pub-4267540560263635/2303543680";
    /////////Native_Top_Advanced////////////
    /////////Native_BackPress_Advanced////////////
    private static final String ADMOB_AD_UNIT_ID_back = "ca-app-pub-4267540560263635/3321111884";
    /////////Native_BackPress_Advanced////////////


    Dialog openDialog_s;
    int share_name = 0;
    public static FrameLayout add, add2, add3;
    static SharedPreference spd = new SharedPreference();
    Context context = this;
    RelativeLayout adsicon, adsicon2;
    CircleImageView ads_logo, ads_logo2;
    int loadaddcontent = 0;

    public static LinearLayout add_e;
    public static LinearLayout add_sc;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    /////////native advance////////////
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    int setval_vid;
    TextView coin_value;
    int minmumd = 1;
    int maximumd = 4;
    int randomnod;
    Dialog openDialog;
    FirebaseAnalytics mFirebaseAnalytics;
    int dia_dismiss = 0;

    private MaxInterstitialAd ins_game, game_exit_ins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic__game);


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");


        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);






        if (spd.getString(Picture_Game_Hard.this, "new_user_db").equals("")) {

        } else {
            if (spd.getString(Picture_Game_Hard.this, "new_user_db").equals("on")) {
                spd.putString(Picture_Game_Hard.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                spd.putString(Picture_Game_Hard.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        rewarded_ad();
        if (sps.getInt(context, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(context).setMediationProvider("max");
            AppLovinSdk.initializeSdk(context, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    industrialload_game();
                    game_exit_ins_ad();

                }
            });
        }



        adds = (LinearLayout) findViewById(R.id.ads_lay);
        if (sps.getInt(context, "purchase_ads") == 0) {
        if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {
            Ad_NativieUtils.load_add_facebook(this, getResources().getString(R.string.Viliyodu_Vilaiyadu_Native_Banner_new), adds);
        } else {
            adds.setVisibility(View.GONE);
        }}else{
            adds.setVisibility(View.GONE);
        }



        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        myDbHelper = new DataBaseHelper(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);

      /*  exdb=myDbHelper.getReadableDatabase();
        dbs=newhelper.getReadableDatabase();
        dbn=newhelper2.getReadableDatabase();
        dbn2=newhelper3.getReadableDatabase();*/


        /*String gid = "1";
        String qid = "";
        for (int i = 0; i<=199; i++){
            if (qid.equals("")){
                qid = "" +i;
            } else {
                qid = qid + "," + i;
            }
        }
        System.out.println("---qid : " +qid);
        System.out.println("---qid : " + "UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='16'");
        myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE levelid in (" + qid + ") and gameid='1'");
*/
        email = sps.getString(Picture_Game_Hard.this, "email");

        System.out.println("mail======pic==" + email);
        //uiHelper = new UiLifecycleHelper(this, callback);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                .addScope(Drive.SCOPE_APPFOLDER) // SavedGames
                .build();


        //loadRewardedVideoAd();
        //New_Main_Activity.fb_addload(Picture_Game_Hard.this);

        if (sps.getString(Picture_Game_Hard.this, "signinagain").equals("yes")) {
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                try {
                    if (!getApiClient().isConnected()) {
                        if (!isSignedIn()) {
                            beginUserInitiatedSignIn();
                            mGoogleApiClient.connect();

                        }
                    }

                } catch (Exception ex) {
                    //ex.printStackTrace();
                }
            }

        }

      /*  Start s = new BStart();
        Toast.makeText(this, ""+s.saal(50), Toast.LENGTH_SHORT).show();*/


        if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(context);

        }



        ImageView prize_logo = (ImageView) findViewById(R.id.prize_logo);

        if (sp.getInt(Picture_Game_Hard.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sp.getString(Picture_Game_Hard.this, "price_registration").equals("com")) {
                        System.out.println("############################## S1");
                        finish();
                        Intent i = new Intent(Picture_Game_Hard.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sp.getString(Picture_Game_Hard.this, "otp_verify").equals("yes")) {
                            System.out.println("############################## S2");
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            System.out.println("############################## S3");
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Picture_Game_Hard.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });



      /*  if(Utils.isNetworkAvailable(Picture_Game_Hard.this)){

            New_Main_Activity.load_add(New_Main_Activity.add,Picture_Game_Hard.this);
        }*/

        ///Alter Answer table

        if (sps.getString(Picture_Game_Hard.this, "alter_answer_table").equals("")) {


            myDbHelper.executeSql("alter table answertable add column rd integer DEFAULT 0");
            myDbHelper.executeSql("alter table answertable add column playtime integer");
            myDbHelper.executeSql("alter table answertable add column levelscore integer  DEFAULT 0");
            myDbHelper.executeSql("alter table answertable add column useranswer integer");

            myDbHelper.executeSql("alter table maintable add column playtime integer DEFAULT 0");
            myDbHelper.executeSql("alter table maintable add column noclue integer DEFAULT 0");
            myDbHelper.executeSql("alter table dailytest add column playtime integer DEFAULT 0");
            myDbHelper.executeSql("alter table dailytest add column noclue integer DEFAULT 0");
            sps.putString(Picture_Game_Hard.this, "alter_answer_table", "yes");
        }

//sounds for game


        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Picture_Game_Hard.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Picture_Game_Hard.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Picture_Game_Hard.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Picture_Game_Hard.this, R.raw.coins, 1);

        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        toggleButton = (TextView) popupView.findViewById(R.id.toggle);
        //Sound ON OFF
        String snd = sps.getString(Picture_Game_Hard.this, "snd");
        p_setting = (TextView) findViewById(R.id.p_settings);
        if (snd.equals("off")) {
            p_setting.setBackgroundResource(R.drawable.sound_off);
            // toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;

        } else if (snd.equals("on")) {
            //  toggleButton.setBackgroundResource(R.drawable.on);
            p_setting.setBackgroundResource(R.drawable.sound_on);
            sv = 1;

        }

        p_edit = (EditText) findViewById(R.id.p_ans_editer);
        p_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(p_edit.getWindowToken(), 0);
            }
        });
        p_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(p_edit.getWindowToken(), 0);

                return true;
            }
        });

        u_verify = (TextView) findViewById(R.id.pic__verifi);
        pic_clue = (TextView) findViewById(R.id.pic_clue);
        h_watts_app = (TextView) findViewById(R.id.p_watts_app);
        helpshare_layout = (RelativeLayout) findViewById(R.id.helpshare_layout);


        if (sps.getString(Picture_Game_Hard.this, "pn_intro").equals("yes")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view
            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Picture_Game_Hard.this, "sequence example pn2");
            sequence.setConfig(config);
            sequence.addSequenceItem(u_verify, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");
            sequence.addSequenceItem(pic_clue, "குறிப்பை பார்க்க பச்சை நிற பொத்தானை அழுத்தவும் .", "அடுத்து");
            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Picture_Game_Hard.this)
                    .setTarget(helpshare_layout)
                    .setDismissText("சரி")
                    .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {
                            if (position == 2) {
                                sps.putString(context, "pic_time_start", "yes");
                                sps.putString(context, "showcase_dismiss_p", "yes");
                                focus.setBase(SystemClock.elapsedRealtime());
                                focus.start();
                            }
                        }
                    });
            sps.putString(Picture_Game_Hard.this, "pn_intro", "no");
            sequence.start();
        }

        if (sps.getInt(Picture_Game_Hard.this, "reward_coin_txt") == 0) {
            sps.putInt(Picture_Game_Hard.this, "reward_coin_txt", 20);
        }

        find();
        click1();
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
            /*Utils.toast_center(Picture_Game_Hard.this,message);*/
            if (message.length() > 6) {
                sps.putString(Picture_Game_Hard.this, "date", message);
                Cursor c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + message + "'");
                if (c.getCount() != 0) {
                    next();
                } else {
                    Cursor c2 = myDbHelper.getQry("select * from dailytest where date=" + message + "");
                    c2.moveToFirst();
                    if (c2.getCount() != 0) {
                        next();
                    } else {
                        ///User Premission Showing
                        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (sps.getString(Picture_Game_Hard.this, "permission_grand").equals("")) {
                                sps.putString(Picture_Game_Hard.this, "permission_grand", "yes");
                                //  First_register("yes");
                                AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
                                alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய பின்வரும் permission-யை allow செய்யவேண்டும்");
                                alertDialog.setCancelable(false);
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                                    ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 150);
                                                } else {
                                                    downloaddata_daily();
                                                }
                                            }
                                        });

                                alertDialog.show();

                            } else {
                                if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                    if (sps.getInt(Picture_Game_Hard.this, "permission") == 2) {
                                        AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
                                        alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய Settings-ல் உள்ள permission-யை allow செய்யவேண்டும்");
                                        alertDialog.setCancelable(false);
                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings ",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        Intent intent = new Intent();
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                        Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                                        intent.setData(uri);
                                                        getApplicationContext().startActivity(intent);
                                                        setting_access = 1;

                                                    }
                                                });

                                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        sps.putString(Picture_Game_Hard.this, "game_area", "on");
                                                        String date = sps.getString(Picture_Game_Hard.this, "date");
                                                        if (date.equals("0")) {
                                                            if (main_act.equals("")) {
                                                                finish();
                                                                Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                                                startActivity(i);
                                                            } else {
                                                                finish();
                                                            }
                                                        } else {
                                                            finish();
                                                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                                            startActivity(i);
                                                        }
                                                        dialog.dismiss();
                                                    }
                                                });


                                        alertDialog.show();
                                    } else {
                                        if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                            ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 150);
                                        } else {
                                            downloaddata_daily();
                                        }
                                    }
                                } else {
                                    if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 150);
                                    } else {
                                        downloaddata_daily();
                                    }
                                }

                            }
                        } else {
                            downloaddata_daily();
                        }*/
                        downloaddata_daily();
                    }
                }

            } else {
                sps.putString(Picture_Game_Hard.this, "date", "0");
                next();
            }


        } else {
            sps.putString(Picture_Game_Hard.this, "date", "0");
            next();
        }


    }

    private void loads_ads_banner() {
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            adds.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {
                fb_native(Picture_Game_Hard.this, native_banner_ad_container);

            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }
    }


    public void find() {
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        bt1 = (TextView) findViewById(R.id.p_button1);
        bt2 = (TextView) findViewById(R.id.p_button2);
        bt3 = (TextView) findViewById(R.id.p_button3);
        bt4 = (TextView) findViewById(R.id.p_button4);
        bt5 = (TextView) findViewById(R.id.p_button5);
        bt6 = (TextView) findViewById(R.id.p_button6);
        bt7 = (TextView) findViewById(R.id.p_button7);
        bt8 = (TextView) findViewById(R.id.p_button8);
        bt9 = (TextView) findViewById(R.id.p_button9);
        bt10 = (TextView) findViewById(R.id.p_button10);
        bt11 = (TextView) findViewById(R.id.p_button11);
        bt12 = (TextView) findViewById(R.id.p_button12);
        bt13 = (TextView) findViewById(R.id.p_button13);
        bt14 = (TextView) findViewById(R.id.p_button14);
        bt15 = (TextView) findViewById(R.id.p_button15);
        bt16 = (TextView) findViewById(R.id.p_button16);
        img1 = (ImageView) findViewById(R.id.image_1);
        img2 = (ImageView) findViewById(R.id.image_2);
        img3 = (ImageView) findViewById(R.id.image_3);
        img4 = (ImageView) findViewById(R.id.image_4);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        list4 = (LinearLayout) findViewById(R.id.list4);
        //Time Score Making
        to_no = (TextView) findViewById(R.id.p_word_number);
        score = (TextView) findViewById(R.id.p_score_edit);
        focus = (Chronometer) findViewById(R.id.p_time_edit);
        p_edit = (EditText) findViewById(R.id.p_ans_editer);
        u_verify = (TextView) findViewById(R.id.pic__verifi);
        ans_show = (TextView) findViewById(R.id.ans_highlite);
        p_setting = (TextView) findViewById(R.id.p_settings);
        //
        p_coin = (TextView) findViewById(R.id.p_coins);
        //intro=(TextView)findViewById(R.id.introduction);
        qtw = (LinearLayout) findViewById(R.id.qwt);
        //Help Share
        w_head = (RelativeLayout) findViewById(R.id.pict_head);
        h_gplues = (TextView) findViewById(R.id.p_gplues);
        h_watts_app = (TextView) findViewById(R.id.p_watts_app);
        h_facebook = (TextView) findViewById(R.id.p_facebook);
        //
        earncoin = (TextView) findViewById(R.id.earncoin);
        edit_buttons_layout = (RelativeLayout) findViewById(R.id.edit_buttons_layout);

        pic_clue = (TextView) findViewById(R.id.pic_clue);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.blink_animation);
        pic_clue.startAnimation(myFadeInAnimation);
        clue_ans = (TextView) findViewById(R.id.clue_word);

        p_clear = (TextView) findViewById(R.id.p_clear);

        //focus.start();
        //score intial

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndex("coins"));
        String tr = String.valueOf(skq);
        score.setText(tr);
        //
        openDialog_s = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        ads_logo = (CircleImageView) openDialog_s.findViewById(R.id.ads_logo);
        adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        /////////

        if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {

        } else {
            // advancads();
        }

        ////////
        /////////

        if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {

        } else {
            //  advancads_content();
        }

        //builder_dialog = new AdLoader.Builder(this, ADMOB_AD_UNIT_ID_Top);

        //install_ads_doalug();

        ////////
    }


    public void click1() {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(1);
                picdig = 1;
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(2);
                picdig = 1;
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(3);
                picdig = 1;
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(4);
                picdig = 1;
            }
        });
        p_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p_setting.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(Picture_Game_Hard.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(Picture_Game_Hard.this, "snd", "on");
                    p_setting.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(Picture_Game_Hard.this, "snd", "off");
                    p_setting.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
              /*  if (k == 1) {
                    showpopup();
                    k = 2;
                } else {
                    popupWindow.dismiss();
                    k = 1;
                }*/
            }
        });
        w_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k == 2) {
                    popupWindow.dismiss();
                    k = 1;
                }
            }
        });
        earncoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });
        h_gplues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 3;
                String a = "com.google.android.apps.plus";
                permission(a);
            }
        });
        h_watts_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 2;
                String a = "com.whatsapp";
                permission(a);
            }
        });
        h_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 1;
                final String a = "com.facebook.katana";
                permission(a);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt1.startAnimation(shake);
                String ts = bt1.getText().toString();
                p_edit.append(ts);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt2.startAnimation(shake);
                String ts = bt2.getText().toString();
                p_edit.append(ts);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt3.startAnimation(shake);
                String ts = bt3.getText().toString();
                p_edit.append(ts);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt5.startAnimation(shake);
                String ts = bt5.getText().toString();
                p_edit.append(ts);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt6.startAnimation(shake);
                String ts = bt6.getText().toString();
                p_edit.append(ts);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt7.startAnimation(shake);
                String ts = bt7.getText().toString();
                p_edit.append(ts);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt9.startAnimation(shake);
                String ts = bt9.getText().toString();
                p_edit.append(ts);
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt10.startAnimation(shake);
                String ts = bt10.getText().toString();
                p_edit.append(ts);

            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt11.startAnimation(shake);
                String ts = bt11.getText().toString();
                p_edit.append(ts);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt4.startAnimation(shake);
                String ts = bt4.getText().toString();
                p_edit.append(ts);
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt8.startAnimation(shake);
                String ts = bt8.getText().toString();
                p_edit.append(ts);
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt12.startAnimation(shake);
                String ts = bt12.getText().toString();
                p_edit.append(ts);

            }
        });
        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt13.startAnimation(shake);
                String ts = bt13.getText().toString();
                p_edit.append(ts);
            }
        });

        bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt14.startAnimation(shake);
                String ts = bt14.getText().toString();
                p_edit.append(ts);
            }
        });
        bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt15.startAnimation(shake);
                String ts = bt15.getText().toString();
                p_edit.append(ts);

            }
        });
        bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.button_shake);
                bt16.startAnimation(shake);
                String ts = bt16.getText().toString();
                p_edit.append(ts);

            }
        });
        p_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });

        p_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                p_edit.setText("");
                return false;
            }
        });

        final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);
        ads_logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsicon2.setVisibility(View.INVISIBLE);

            }
        });
        edit_buttons_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        pic_clue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                cfw.moveToFirst();
                int sk = cfw.getInt(cfw.getColumnIndex("coins"));
                if (sk > 25) {
                    if (sps.getString(getApplicationContext(), "checkbox_clue").equals("yes")) {
                        clue();
                    } else {
                        if (f == 0) {
                            final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                            openDialog.setContentView(R.layout.clue_ans_show);
                            TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                            TextView no = (TextView) openDialog.findViewById(R.id.no);
                            TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);

                            CheckBox checkbox_ans = (CheckBox) openDialog.findViewById(R.id.checkbox_ans);
                            checkbox_ans.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                    if (isChecked == true) {
                                        sps.putString(getApplicationContext(), "checkbox_clue", "yes");
                                    } else {
                                        sps.putString(getApplicationContext(), "checkbox_clue", "");
                                    }
                                }
                            });
                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    clue();
                                    openDialog.dismiss();
                                }
                            });
                            no.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    sps.putString(getApplicationContext(), "checkbox_clue", "");
                                    openDialog.dismiss();
                                }
                            });
                            openDialog.show();
                        } else {
                            clue();
                        }


                    }

                } else {
                    dialog(1);
                }


            }
        });

        qtw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });

        //User Verifing Answer
        u_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                cfw.moveToFirst();
                int sk = cfw.getInt(cfw.getColumnIndex("coins"));
                if (sk >= 100) {
                    if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                        p_edit.setText("");


                        String date = sps.getString(Picture_Game_Hard.this, "date");
                        Cursor cd;
                        if (date.equals("0")) {
                            cd = myDbHelper.getQry("SELECT * FROM maintable where  levelid='" + wordid + "' and isfinish='0' and gameid='" + gameid + "'");
                            cd.moveToFirst();
                        } else {
                            cd = myDbHelper.getQry("SELECT * FROM dailytest where  levelid='" + wordid + "' and isfinish='0' and gameid='" + gameid + "'");
                            cd.moveToFirst();
                        }
                        String sa = "";
                        if (cd.getCount() != 0) {
                            sa = cd.getString(cd.getColumnIndex("answer"));

                        }
                        //Toast.makeText(Picture_Game_Hard.this, "" + sa, Toast.LENGTH_SHORT).show();
                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = 0;
                        if (cfx.getCount() != 0) {
                            skx = cfx.getInt(cfx.getColumnIndex("coins"));

                        }
                        int spx = 0;

                        if (f == 0) {

                            spx = skx - 100;
                        } else {
                            spx = skx - 75;
                        }

                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                        u_verify.setBackgroundResource(R.drawable.tick_background);
                        u_verify.setEnabled(false);

                        sps.putInt(getApplicationContext(), "ach6_a1", 0);
                        //
                        Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                        ans_show.startAnimation(w_game);
                        ans_show.setVisibility(View.VISIBLE);
                        ans_show.setText(sa);
                        //Update QST

                        if (date.equals("0")) {
                            myDbHelper.executeSql("UPDATE maintable SET isfinish=1 WHERE levelid='" + wordid + "'and gameid='" + gameid + "'");

                        } else {
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish=1 WHERE levelid='" + wordid + "'and gameid='" + gameid + "'");

                        }
                        //Next Function
                        r = 1;


                        pic_clue.clearAnimation();
                        pic_clue.setVisibility(View.INVISIBLE);
                        list4.setVisibility(View.INVISIBLE);

                       /* if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {
                                    savedGamesUpdate();
                                }
                            }
                        }*/
                        focus.stop();
                        completegame();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                setSc();
                            }
                        }, 4000);
                    } else {
                        final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        openDialog.setContentView(R.layout.show_ans);
                        TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                        TextView no = (TextView) openDialog.findViewById(R.id.no);
                        TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);

                        if (f == 0) {
                            txt_ex2.setText("மொத்த நாணயங்களில் 100 குறைக்கப்படும்");
                        } else {
                            txt_ex2.setText("மொத்த நாணயங்களில் 75 குறைக்கப்படும்");
                        }

                        CheckBox checkbox_ans = (CheckBox) openDialog.findViewById(R.id.checkbox_ans);
                        checkbox_ans.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                if (isChecked == true) {
                                    sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                                } else {
                                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                                }
                            }
                        });

                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                p_edit.setText("");


                                String date = sps.getString(Picture_Game_Hard.this, "date");
                                Cursor cd;
                                if (date.equals("0")) {
                                    cd = myDbHelper.getQry("SELECT * FROM maintable where  levelid='" + wordid + "' and isfinish='0' and gameid='" + gameid + "'");
                                    cd.moveToFirst();
                                } else {
                                    cd = myDbHelper.getQry("SELECT * FROM dailytest where  levelid='" + wordid + "' and isfinish='0' and gameid='" + gameid + "'");
                                    cd.moveToFirst();
                                }
                                String sa = "";
                                if (cd.getCount() != 0) {
                                    sa = cd.getString(cd.getColumnIndex("answer"));
                                }
                                //Toast.makeText(Picture_Game_Hard.this, "" + sa, Toast.LENGTH_SHORT).show();
                                //Score Adding
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                if (cfx.getCount() != 0) {
                                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                    int spx;
                                    if (f == 0) {
                                        spx = skx - 100;
                                    } else {
                                        spx = skx - 75;
                                    }
                                    String aStringx = Integer.toString(spx);
                                    score.setText(aStringx);
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                }


                                u_verify.setBackgroundResource(R.drawable.tick_background);
                                u_verify.setEnabled(false);

                                sps.putInt(getApplicationContext(), "ach6_a1", 0);
                                //
                                Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                                ans_show.startAnimation(w_game);
                                ans_show.setVisibility(View.VISIBLE);
                                ans_show.setText(sa);
                                //Update QST

                                if (date.equals("0")) {
                                    myDbHelper.executeSql("UPDATE maintable SET isfinish=1 WHERE levelid='" + wordid + "'and gameid='" + gameid + "'");

                                } else {
                                    myDbHelper.executeSql("UPDATE dailytest SET isfinish=1 WHERE levelid='" + wordid + "'and gameid='" + gameid + "'");

                                }
                                //Next Function
                                r = 1;
                                openDialog.dismiss();

                                pic_clue.clearAnimation();
                                pic_clue.setVisibility(View.INVISIBLE);
                                list4.setVisibility(View.INVISIBLE);

                              /*  if (Utils.isNetworkAvailable(getApplicationContext())) {
                                    if (getApiClient().isConnected()) {
                                        if (isSignedIn()) {
                                            savedGamesUpdate();
                                        }
                                    }
                                }*/
                                completegame();
                                focus.stop();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        setSc();
                                    }
                                }, 4000);
                            }
                        });
                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sps.putString(getApplicationContext(), "checkbox_ans", "");
                                openDialog.dismiss();
                            }
                        });
                        openDialog.show();
                    }
                } else {
                    dialog(1);
                }
            }
        });

        //Verifing Answer
        p_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = p_edit.getText().toString();
                String date = sps.getString(Picture_Game_Hard.this, "date");
                Cursor cd;
                if (date.equals("0")) {

                    cd = myDbHelper.getQry("SELECT * FROM maintable where answer ='" + answer + "' and isfinish='0' and levelid='" + wordid + "'and gameid='" + gameid + "'");
                    cd.moveToFirst();
                } else {

                    cd = myDbHelper.getQry("SELECT * FROM dailytest where answer ='" + answer + "' and isfinish='0' and levelid='" + wordid + "'and gameid='" + gameid + "'");
                    cd.moveToFirst();
                }
                if (cd.getCount() != 0) {

                    //reward yes
                    vs = 1;
                    //reward yes

                    spz3.play(soundId3, sv, sv, 0, 0, sv);
                    //Toast.makeText(Picture_Game_Hard.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    u_verify.setBackgroundResource(R.drawable.tick_background);
                    u_verify.setEnabled(false);
                    ans_show.setVisibility(View.VISIBLE);
                    ans_show.setText(answer);
                    if (date.equals("0")) {
                        myDbHelper.executeSql("UPDATE maintable SET isfinish=1 WHERE levelid='" + wordid + "'and gameid='" + gameid + "'");

                    } else {
                        myDbHelper.executeSql("UPDATE dailytest SET isfinish=1 WHERE levelid='" + wordid + "'and gameid='" + gameid + "'");

                    }
                    //Time Setting For Clue Game
                    focus.stop();
                    long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
                    int hours = (int) (timeElapsed / 3600000);
                    int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
                    int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

                    int min = hours * 60;
                    int sec = min * 60;
                    int sec2 = minutes * 60;
                    f_sec = sec + sec2 + seconds;


                    price_update();


                    pic_clue.clearAnimation();
                    pic_clue.setVisibility(View.INVISIBLE);
                    list4.setVisibility(View.INVISIBLE);
                    if (sps.getString(getApplicationContext(), "ach6").equals("")) {
                        if (sps.getInt(getApplicationContext(), "ach6_a1") >= 5) {
                            if (Utils.isNetworkAvailable(getApplicationContext())) {
                                if (getApiClient().isConnected()) {
                                    if (isSignedIn()) {
                                        Games.Achievements.unlock(getApiClient(), getString(R.string.achievement__6));
                                        sps.putString(getApplicationContext(), "ach6", "yes");
                                    } else {
                                        sps.putString(getApplicationContext(), "ach6", "yes");
                                    }
                                } else {
                                    sps.putString(getApplicationContext(), "ach6", "yes");
                                }
                            } else {
                                sps.putString(getApplicationContext(), "ach6", "yes");
                            }
                            sps.putInt(getApplicationContext(), "ach6_a1", (sps.getInt(getApplicationContext(), "ach6_a1") + 1));
                        } else {
                            sps.putInt(getApplicationContext(), "ach6_a1", (sps.getInt(getApplicationContext(), "ach6_a1") + 1));
                        }
                    }

                  /*  if (Utils.isNetworkAvailable(getApplicationContext())) {
                        if (getApiClient().isConnected()) {
                            if (isSignedIn()) {
                                savedGamesUpdate();
                            }
                        }
                    }*/


                    ////


                   /* Calendar calendar3 = Calendar.getInstance();
                    int cur_year1 = calendar3.get(Calendar.YEAR);
                    int cur_month1 = calendar3.get(Calendar.MONTH);
                    int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                    String str_month1 = "" + (cur_month1 + 1);
                    if (str_month1.length() == 1) {
                        str_month1 = "0" + str_month1;
                    }

                    String str_day1 = ""+cur_day1;
                    if(str_day1.length()==1){
                        str_day1 = "0"+str_day1;
                    }
                    final String str_date1 = cur_year1+"-"+str_month1+"-"+str_day1;

                    if(sps.getString(Picture_Game_Hard.this,"complite_reg").equals("yes")) {
                        Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'", null);
                        cn.moveToFirst();
                        int cns = cn.getInt(cn.getColumnIndex("score"));
                        int time = cn.getInt(cn.getColumnIndex("playtime"));
                        int gm1 = cn.getInt(cn.getColumnIndex("gm1"));
                        int cnse=0;
                        long ptime;
                        ;
                        if (f==0)
                        {
                            cnse = cns+50;
                        }else {
                            cnse = cns+25;
                        }
                        long ttstime = 0;
                        long timeElapseds = SystemClock.elapsedRealtime() - focus.getBase();
                        int hourss = (int) (timeElapseds / 3600000);
                        int minutess = (int) (timeElapseds - hourss * 3600000) / 60000;
                        int secondss = (int) (timeElapseds - hourss * 3600000 - minutess * 60000) / 1000;

                        int mins = hourss * 60;
                        int secs = mins * 60;
                        int sec2s = minutess * 60;
                        ttstime = secs + sec2s + secondss;

                        ptime=time+ttstime;
                        int gm1s=gm1+1;



                        myDbHelper.executeSql("UPDATE userdata_r SET score='" + cnse + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                        myDbHelper.executeSql("UPDATE userdata_r SET playtime='" + ptime + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                        myDbHelper.executeSql("UPDATE userdata_r SET gm1='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                    }*/


                    coinanim();
                    p_edit.setText("");


                }
            }
        });

    }

    private void helpshare(String a) {


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Bitmap bitmap = Bitmap.createBitmap(w_head.getWidth(), w_head.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        w_head.draw(canvas);

        String root = getFilesDir().toString();
        File mydir = new File(root + "/Nithra/Solli_adi");
        mydir.mkdirs();
        String fname = "Solli_adi.jpg";
        final File file = new File(mydir, fname);

        if (file.exists()) {
            file.delete();
        }

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

                    String date = sps.getString(Picture_Game_Hard.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                        myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                        myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                    }

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Picture_Game_Hard.this, Picture_Game_Hard.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");

                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT,
                            "Solli_adi");
                    //  share.putExtra(android.content.Intent.EXTRA_TEXT,"Shared via Tamil Calendar Offline.\nClick here to download"+ "\nhttps://goo.gl/ITvWGu");
                    startActivity(Intent.createChooser(share, "Share Card Using"));

                } else {

                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "இந்த செயலி தங்களிடம் இல்லை", Snackbar.LENGTH_SHORT);
                    final View view = snackbar.getView();
                    TextView textView = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
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

    private void clue() {


        pic_clue.setText("");
        Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clueshow_pic);
        clue_ans.startAnimation(w_game);
        clue_ans.setVisibility(View.VISIBLE);
        clue_ans.setText(clue);
        u_verify.setVisibility(View.INVISIBLE);
        Handler handler8 = new Handler();
        handler8.postDelayed(new Runnable() {
            @Override
            public void run() {

                u_verify.setVisibility(View.VISIBLE);
                clue_ans.setVisibility(View.INVISIBLE);
            }
        }, 5000);
        if (f == 0) {
            //Score Adding
            f = 1;
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                int spx = skx - 25;
                String aStringx = Integer.toString(spx);
                score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            }
         /*   pic_clue.setText("");
            Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clueshow_pic);
            clue_ans.startAnimation(w_game);
            clue_ans.setVisibility(View.VISIBLE);
            clue_ans.setText(clue);
            u_verify.setVisibility(View.INVISIBLE);
            Handler handler8 = new Handler();
            handler8.postDelayed(new Runnable() {
                @Override
                public void run() {

                    u_verify.setVisibility(View.VISIBLE);
                    clue_ans.setVisibility(View.INVISIBLE);
                }
            }, 5000);
            pic_clue.clearAnimation();
            pic_clue.setBackgroundResource(R.drawable.bulb2);*/
        }

    }

    public void medium() {
        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";
        if (sps.getString(Picture_Game_Hard.this, "pic_time_start").equals("")) {
            sps.putString(Picture_Game_Hard.this, "pic_time_start", "yes");

        } else {

            focus.setBase(SystemClock.elapsedRealtime());
            focus.start();
        }

        pic_clue.setVisibility(View.VISIBLE);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.blink_animation);
        pic_clue.startAnimation(myFadeInAnimation);
        pic_clue.setBackgroundResource(R.drawable.bulbs);
        pic_clue.setText("1");


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

        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
        //Button Anim
        LinearLayout bl1 = (LinearLayout) findViewById(R.id.p_buttonlist1_layout);
        LinearLayout bl2 = (LinearLayout) findViewById(R.id.p_buttonlist2_layout);
        LinearLayout bl3 = (LinearLayout) findViewById(R.id.p_buttonlist3_layout);
        LinearLayout bl4 = (LinearLayout) findViewById(R.id.p_buttonlist4_layout);
       /* Animation h_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button1);
        Animation h_game2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button2);
        bl1.startAnimation(h_game);
        bl2.startAnimation(h_game2);
        bl3.startAnimation(h_game);
        bl4.startAnimation(h_game2);*/
        //
        ans_show.setVisibility(View.GONE);
        img1.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);
        img4.setVisibility(View.GONE);
        p_coin.setVisibility(View.INVISIBLE);
        list4.setVisibility(View.VISIBLE);
        u_verify.setBackgroundResource(R.drawable.yellow_question);
        if (word_type == 1) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            String letter1 = tokenizer.nextToken().trim();
            String letter2 = tokenizer.nextToken().trim();
            String letter3 = tokenizer.nextToken().trim();
            String letter4 = tokenizer.nextToken().trim();
            String letter5 = tokenizer.nextToken().trim();
            String letter6 = tokenizer.nextToken().trim();
            String letter7 = tokenizer.nextToken().trim();
            String letter9 = tokenizer.nextToken().trim();
            String letter10 = tokenizer.nextToken().trim();
            String letter11 = tokenizer.nextToken().trim();
            String letter12 = tokenizer.nextToken().trim();
            String letter13 = tokenizer.nextToken().trim();
            String letter14 = tokenizer.nextToken().trim();
            String letter15 = tokenizer.nextToken().trim();
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(sa);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);


        } else if (word_type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(letter6);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter13);
            bt11.setText(letter7);
            bt12.setText(word2);


        } else if (word_type == 3) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word2);
            bt2.setText(letter15);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word2);
            bt11.setText(letter2);
            bt12.setText(letter1);


        } else if (word_type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
            String letter2 = tokenizer.nextToken().trim();
            String letter3 = tokenizer.nextToken().trim();
            String letter4 = tokenizer.nextToken().trim();
            String letter5 = tokenizer.nextToken().trim();
            String letter6 = tokenizer.nextToken().trim();
            String letter7 = tokenizer.nextToken().trim();
            String letter9 = tokenizer.nextToken().trim();
            String letter10 = tokenizer.nextToken().trim();
            String letter11 = tokenizer.nextToken().trim();
            String letter12 = tokenizer.nextToken().trim();
            String letter13 = tokenizer.nextToken().trim();
            String letter14 = tokenizer.nextToken().trim();
            String letter15 = tokenizer.nextToken().trim();
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(word1);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter10);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);


        } else if (word_type == 5) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter13);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter8);
            bt12.setText(word2);


        } else if (word_type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word2);
            bt2.setText(letter9);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(letter6);
            bt6.setText(letter1);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(letter8);
            bt10.setText(word1);
            bt11.setText(word3);
            bt12.setText(letter4);


        } else if (word_type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter8);
            bt2.setText(letter4);
            bt3.setText(word5);
            bt4.setText(word2);
            bt5.setText(word7);
            bt6.setText(word3);
            bt7.setText(word4);
            bt8.setText(letter11);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);


        } else if (word_type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(letter6);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);


        } else if (word_type == 9) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter6);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);


        }


        if (image_type == 1) {

            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(imid.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img1.setImageResource(im1);
            } else {
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + imid + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
                    // Toast.makeText(Picture_Game_Hard.this, ""+imid, Toast.LENGTH_SHORT).show();
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    img1.setImageDrawable(bd);
                    img1.setVisibility(View.VISIBLE);
                } else {
                    missingimage();

                }

            }


        } else if (image_type == 2) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);

            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");


                if (file.exists() && file2.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);
                } else {
                    missingimage();
                }

            }

        } else if (image_type == 3) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);
                img3.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");

                if (file.exists() && file2.exists() && file3.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    img3.setImageDrawable(bd3);
                } else {
                    missingimage();

                }

            }
        } else if (image_type == 4) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());
                im14 = getResources().getIdentifier(word4.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);
                img3.setImageResource(im13);
                img4.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                File file4 = new File(fullPath + word4 + "");
                if (file.exists() && file2.exists() && file3.exists() && file4.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    img3.setImageDrawable(bd3);

                    Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
                    Resources res4 = getResources();
                    BitmapDrawable bd4 = new BitmapDrawable(res4, bitimg4);
                    img4.setImageDrawable(bd4);
                } else {
                    missingimage();
                }

            }
        }
    }

    public void simple() {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";


        if (sps.getString(Picture_Game_Hard.this, "pic_time_start").equals("")) {
            sps.putString(Picture_Game_Hard.this, "pic_time_start", "yes");

        } else {

            focus.setBase(SystemClock.elapsedRealtime());
            focus.start();
        }


        pic_clue.setVisibility(View.VISIBLE);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.blink_animation);
        pic_clue.startAnimation(myFadeInAnimation);
        pic_clue.setBackgroundResource(R.drawable.bulbs);
        pic_clue.setText("1");

        bt4.setVisibility(View.GONE);
        bt8.setVisibility(View.GONE);
        bt12.setVisibility(View.GONE);
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);

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
        p_edit.setText("");
        //Animation in buttons
        LinearLayout bl1 = (LinearLayout) findViewById(R.id.p_buttonlist1_layout);
        LinearLayout bl2 = (LinearLayout) findViewById(R.id.p_buttonlist2_layout);
        LinearLayout bl3 = (LinearLayout) findViewById(R.id.p_buttonlist3_layout);
        LinearLayout bl4 = (LinearLayout) findViewById(R.id.p_buttonlist4_layout);
       /* Animation h_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button1);
        Animation h_game2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button2);
        bl1.startAnimation(h_game);
        bl2.startAnimation(h_game2);
        bl3.startAnimation(h_game);
        bl4.startAnimation(h_game2);*/


        list4.setVisibility(View.VISIBLE);
        ans_show.setVisibility(View.GONE);
        img1.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);
        img4.setVisibility(View.GONE);
        p_coin.setVisibility(View.INVISIBLE);
        u_verify.setBackgroundResource(R.drawable.yellow_question);


        if (word_type == 1) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            String letter1 = tokenizer.nextToken().trim();
            String letter2 = tokenizer.nextToken().trim();
            String letter3 = tokenizer.nextToken().trim();
            String letter4 = tokenizer.nextToken().trim();
            String letter5 = tokenizer.nextToken().trim();
            String letter6 = tokenizer.nextToken().trim();
            String letter7 = tokenizer.nextToken().trim();
            String letter9 = tokenizer.nextToken().trim();
            String letter10 = tokenizer.nextToken().trim();
            String letter11 = tokenizer.nextToken().trim();
            String letter12 = tokenizer.nextToken().trim();
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(letter3);
            bt5.setText(sa);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt9.setText(letter9);
            bt10.setText(letter10);
            bt11.setText(letter11);

        } else if (word_type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt9.setText(letter9);
            bt10.setText(word2);
            bt11.setText(letter11);

        } else if (word_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word2);
            bt2.setText(letter5);
            bt3.setText(letter2);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt9.setText(letter6);
            bt10.setText(letter14);
            bt11.setText(word3);

        } else if (word_type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
            String letter2 = tokenizer.nextToken().trim();
            String letter3 = tokenizer.nextToken().trim();
            String letter4 = tokenizer.nextToken().trim();
            String letter5 = tokenizer.nextToken().trim();
            String letter6 = tokenizer.nextToken().trim();
            String letter7 = tokenizer.nextToken().trim();
            String letter9 = tokenizer.nextToken().trim();
            String letter10 = tokenizer.nextToken().trim();
            String letter11 = tokenizer.nextToken().trim();
            String letter12 = tokenizer.nextToken().trim();
            String letter13 = tokenizer.nextToken().trim();
            String letter14 = tokenizer.nextToken().trim();
            String letter15 = tokenizer.nextToken().trim();
            bt1.setText(letter7);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt5.setText(letter6);
            bt6.setText(word2);
            bt7.setText(word4);
            bt9.setText(letter12);
            bt10.setText(letter7);
            bt11.setText(word1);

        } else if (word_type == 5) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(letter14);
            bt5.setText(letter2);
            bt6.setText(word4);
            bt7.setText(word3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(word2);
        } else if (word_type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word5);
            bt2.setText(word2);
            bt3.setText(letter4);
            bt5.setText(word6);
            bt6.setText(letter1);
            bt7.setText(word4);
            bt9.setText(letter8);
            bt10.setText(word1);
            bt11.setText(word3);

        } else if (word_type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word3);
            bt2.setText(word5);
            bt3.setText(word2);
            bt5.setText(word7);
            bt6.setText(word6);
            bt7.setText(word4);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);

        } else if (word_type == 8) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(letter6);
            bt3.setText(word8);
            bt5.setText(word5);
            bt6.setText(word7);
            bt7.setText(word4);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
        } else if (word_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word7);
            bt9.setText(word4);
            bt10.setText(word3);
            bt11.setText(word2);

        }

        if (image_type == 1) {

            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(imid.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img1.setImageResource(im1);
            } else {
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";


                File file = new File(fullPath + imid + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    img1.setImageDrawable(bd);
                } else {
                    missingimage();
                }


            }


        } else if (image_type == 2) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);

            } else {

                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                if (file.exists() && file2.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);
                } else {
                    missingimage();
                }

            }

        } else if (image_type == 3) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);
                img3.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                if (file.exists() && file2.exists() && file3.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    img3.setImageDrawable(bd3);
                } else {
                    missingimage();
                }

            }
        } else if (image_type == 4) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());
                im14 = getResources().getIdentifier(word4.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);
                img3.setImageResource(im13);
                img4.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                File file4 = new File(fullPath + word4 + "");
                if (file.exists() && file2.exists() && file3.exists() && file4.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    img3.setImageDrawable(bd3);

                    Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
                    Resources res4 = getResources();
                    BitmapDrawable bd4 = new BitmapDrawable(res4, bitimg4);
                    img4.setImageDrawable(bd4);
                } else {
                    missingimage();

                }


            }
        }


    }

    public void hard() {

        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";

        pic_clue.setVisibility(View.VISIBLE);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.blink_animation);
        pic_clue.startAnimation(myFadeInAnimation);
        pic_clue.setBackgroundResource(R.drawable.bulbs);
        pic_clue.setText("1");


        if (sps.getString(Picture_Game_Hard.this, "pic_time_start").equals("")) {
            sps.putString(Picture_Game_Hard.this, "pic_time_start", "yes");

        } else {

            focus.setBase(SystemClock.elapsedRealtime());
            focus.start();
        }

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
        ans_show.setVisibility(View.GONE);
        img1.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);
        img4.setVisibility(View.GONE);
        p_coin.setVisibility(View.INVISIBLE);
        list4.setVisibility(View.VISIBLE);
        //Button Anim
        LinearLayout bl1 = (LinearLayout) findViewById(R.id.p_buttonlist1_layout);
        LinearLayout bl2 = (LinearLayout) findViewById(R.id.p_buttonlist2_layout);
        LinearLayout bl3 = (LinearLayout) findViewById(R.id.p_buttonlist3_layout);
        LinearLayout bl4 = (LinearLayout) findViewById(R.id.p_buttonlist4_layout);
      /*  Animation h_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button1);
        Animation h_game2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button2);
        bl1.startAnimation(h_game);
        bl2.startAnimation(h_game2);
        bl3.startAnimation(h_game);
        bl4.startAnimation(h_game2);*/
        //
        u_verify.setBackgroundResource(R.drawable.yellow_question);

        if (word_type == 1) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            String letter1 = tokenizer.nextToken().trim();
            String letter2 = tokenizer.nextToken().trim();
            String letter3 = tokenizer.nextToken().trim();
            String letter4 = tokenizer.nextToken().trim();
            String letter5 = tokenizer.nextToken().trim();
            String letter6 = tokenizer.nextToken().trim();
            String letter7 = tokenizer.nextToken().trim();
            String letter9 = tokenizer.nextToken().trim();
            String letter10 = tokenizer.nextToken().trim();
            String letter11 = tokenizer.nextToken().trim();
            String letter12 = tokenizer.nextToken().trim();
            String letter13 = tokenizer.nextToken().trim();
            String letter14 = tokenizer.nextToken().trim();
            String letter15 = tokenizer.nextToken().trim();
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(sa);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
            bt13.setText(letter10);
            bt14.setText(letter14);
            bt15.setText(letter12);
            bt16.setText(letter13);


        } else if (word_type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(letter6);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter13);
            bt11.setText(letter7);
            bt12.setText(letter12);
            bt13.setText(letter14);
            bt14.setText(word2);
            bt15.setText(letter10);
            bt16.setText(letter8);


        } else if (word_type == 3) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word2);
            bt2.setText(letter15);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word2);
            bt11.setText(letter2);
            bt12.setText(letter1);
            bt13.setText(letter14);
            bt14.setText(letter12);
            bt15.setText(letter10);
            bt16.setText(letter13);

        } else if (word_type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
            String letter2 = tokenizer.nextToken().trim();
            String letter3 = tokenizer.nextToken().trim();
            String letter4 = tokenizer.nextToken().trim();
            String letter5 = tokenizer.nextToken().trim();
            String letter6 = tokenizer.nextToken().trim();
            String letter7 = tokenizer.nextToken().trim();
            String letter9 = tokenizer.nextToken().trim();
            String letter10 = tokenizer.nextToken().trim();
            String letter11 = tokenizer.nextToken().trim();
            String letter12 = tokenizer.nextToken().trim();
            String letter13 = tokenizer.nextToken().trim();
            String letter14 = tokenizer.nextToken().trim();
            String letter15 = tokenizer.nextToken().trim();
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(letter11);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter10);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(letter9);
            bt13.setText(letter14);
            bt14.setText(letter15);
            bt15.setText(word1);
            bt16.setText(word2);

        } else if (word_type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter13);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter8);
            bt12.setText(word2);
            bt13.setText(letter14);
            bt14.setText(letter12);
            bt15.setText(letter9);
            bt16.setText(letter10);


        } else if (word_type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter14);
            bt2.setText(letter9);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(letter13);
            bt6.setText(letter8);
            bt7.setText(word4);
            bt8.setText(letter7);
            bt9.setText(letter1);
            bt10.setText(word1);
            bt11.setText(word3);
            bt12.setText(letter4);
            bt13.setText(word6);
            bt14.setText(letter11);
            bt15.setText(letter6);
            bt16.setText(word2);


        } else if (word_type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(letter2);
            bt2.setText(letter4);
            bt3.setText(word5);
            bt4.setText(word2);
            bt5.setText(word7);
            bt6.setText(letter13);
            bt7.setText(word4);
            bt8.setText(letter8);
            bt9.setText(letter10);
            bt10.setText(letter15);
            bt11.setText(letter12);
            bt12.setText(word6);
            bt13.setText(letter6);
            bt14.setText(word1);
            bt15.setText(letter11);
            bt16.setText(word3);


        } else if (word_type == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(letter15);
            bt3.setText(word8);
            bt4.setText(letter13);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter12);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(letter14);
            bt12.setText(letter8);
            bt13.setText(letter6);
            bt14.setText(letter7);
            bt15.setText(word2);
            bt16.setText(letter10);

        } else if (word_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String letter1 = tokenizer.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(letter2);
            bt4.setText(letter13);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter6);
            bt8.setText(word4);
            bt9.setText(letter3);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
            bt13.setText(letter6);
            bt14.setText(letter2);
            bt15.setText(word2);
            bt16.setText(word8);

        }


        if (image_type == 1) {

            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(imid.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img1.setImageResource(im1);
            } else {
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                File file = new File(fullPath + imid + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    img1.setImageDrawable(bd);
                } else {
                    missingimage();
                }

            }


        } else if (image_type == 2) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);

            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                if (file.exists() && file2.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);
                } else {
                    missingimage();
                }
            }

        } else if (image_type == 3) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);
                img3.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                if (file.exists() && file2.exists() && file3.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    img3.setImageDrawable(bd3);
                } else {
                    missingimage();
                }

            }
        } else if (image_type == 4) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());
                im14 = getResources().getIdentifier(word4.replace(".webp", ""), "drawable", getPackageName());
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
                img1.setImageResource(im11);
                img2.setImageResource(im12);
                img3.setImageResource(im13);
                img4.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                File file4 = new File(fullPath + word4 + "");
                if (file.exists() && file2.exists() && file3.exists() && file4.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    img3.setImageDrawable(bd3);

                    Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
                    Resources res4 = getResources();
                    BitmapDrawable bd4 = new BitmapDrawable(res4, bitimg4);
                    img4.setImageDrawable(bd4);
                } else {
                    missingimage();
                }

            }
        }
    }


    public void next() {
        //reward correcr ans
        //reward correcr ans
        if (picdig == 1) {
            openDialogk.dismiss();
            picdig = 0;
        }
        Calendar calendar3 = Calendar.getInstance();
        int cur_year1 = calendar3.get(Calendar.YEAR);
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        String str_month1 = "" + (cur_month1 + 1);
        if (str_month1.length() == 1) {
            str_month1 = "0" + str_month1;
        }

        String str_day1 = "" + cur_day1;
        if (str_day1.length() == 1) {
            str_day1 = "0" + str_day1;
        }
        final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;
        //daily bonus
        f = 0;
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {
                native_banner_ad_container.setVisibility(View.VISIBLE);
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }
        w_head.setVisibility(View.VISIBLE);
        bt4.setVisibility(View.VISIBLE);
        bt8.setVisibility(View.VISIBLE);
        bt12.setVisibility(View.VISIBLE);
        bt13.setVisibility(View.VISIBLE);
        bt14.setVisibility(View.VISIBLE);
        bt15.setVisibility(View.VISIBLE);
        bt16.setVisibility(View.VISIBLE);
        u_verify.setEnabled(true);
        String date = sps.getString(Picture_Game_Hard.this, "date");
        sps.putString(Picture_Game_Hard.this, "watts_app", "");
        sps.putString(Picture_Game_Hard.this, "watts_app_s", "");
        sps.putString(Picture_Game_Hard.this, "gplues", "yes");
        sps.putString(Picture_Game_Hard.this, "face_share", "");
        if (date.equals("0")) {
            Cursor c1 = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "' and isfinish='1'");
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            to_no.setText(no/*+"/"+c1.getCount()*/);
        } else {
            if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {

            } else {
                sps.putInt(context, "addloded_rect_bck", 0);
                sps.putInt(context, "addloded_rect_mul", 0);

            }

            String tfoption = date;
            String[] first = tfoption.split("-");
            to_no.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            to_no.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        }

        Cursor c;

        if (date.equals("0")) {
            c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
            c.moveToFirst();
        }


        if (c.getCount() != 0) {
            sa = c.getString(c.getColumnIndex("letters"));
            imid = c.getString(c.getColumnIndex("imagename"));
            wordid = Integer.parseInt(c.getString(c.getColumnIndex("levelid")));
            id = c.getString(c.getColumnIndex("id"));

            /*if (sps.getString(Picture_Game_Hard.this, "error_correction_dis_2").equals("")) {
                myDbHelper.executeSql("UPDATE maintable SET isfinish=1 WHERE  id='" + id + "' and gameid='" + gameid + "'");
                sps.putString(Picture_Game_Hard.this, "error_correction_dis_2", "yes");
            }*/

            if (sps.getString(Picture_Game_Hard.this, str_date1).equals("")) {
                daily_bones();
                System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeee daily_bones()");
                sps.putString(Picture_Game_Hard.this, str_date1, "yes");
            }

            int playtime = 0;

            try {
                playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (playtime == 0) {
                if (sps.getString(Picture_Game_Hard.this, "resume_ptr").equals("")) {
                    sps.putString(Picture_Game_Hard.this, "resume_ptr", "yes");
                } else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }
            }
            String tfoption = sa;
            String[] first = tfoption.split(",");
            int w_type = first.length;
            word_type = w_type;

            String tfoptiona = imid;
            String[] firsta = tfoptiona.split(",");
            int i_type = firsta.length;
            image_type = i_type;

            answer = c.getString(c.getColumnIndex("answer"));
            clue = c.getString(c.getColumnIndex("hints"));

            if (date.equals("0")) {
                isdown = c.getString(c.getColumnIndex("isdownload"));
            } else {
                isdown = String.valueOf(1);
            }


            //String r = String.valueOf(wordid);
            //lt_id.setText(r);

            Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;

            if (random == 1) {
                simple();
            } else if (random == 2) {
                medium();
            } else if (random == 3) {
                hard();
            }
        } else {

            if (date.equals("0")) {

///User Premission Showing

               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (sps.getString(Picture_Game_Hard.this, "permission_grand").equals("")) {
                        sps.putString(Picture_Game_Hard.this, "permission_grand", "yes");
                        //  First_register("yes");
                        AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
                        alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய பின்வரும் permission-யை allow செய்யவேண்டும்");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                            ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
                                        } else {
                                            downloaddata_regular();
                                        }
                                    }
                                });

                        alertDialog.show();

                    } else {
                        if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            if (sps.getInt(Picture_Game_Hard.this, "permission") == 2) {
                                AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
                                alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய  permission-யை allow செய்யவேண்டும்");
                                alertDialog.setCancelable(false);
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings ",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                Intent intent = new Intent();
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                                intent.setData(uri);
                                                getApplicationContext().startActivity(intent);
                                                setting_access = 2;
                                            }
                                        });

                                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                sps.putString(Picture_Game_Hard.this, "game_area", "on");
                                                finish();
                                                dialog.dismiss();
                                            }
                                        });


                                alertDialog.show();
                            } else {
                                if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                    ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
                                } else {
                                    downloaddata_regular();
                                }
                            }
                        } else {
                            if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151
                                );
                            } else {
                                downloaddata_regular();
                            }
                        }

                    }
                } else {
                    downloaddata_regular();
                }*/
                if (isNetworkAvailable())
                    downloaddata_regular();
                else
                    Toast.makeText(this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();

            } else {


                // Toast.makeText(Picture_Game_Hard.this, "தினசரி படம் பார்த்து கண்டுபிடி விளையாட்டுகள் முடிந்தது.வழக்கமான படம் பார்த்து கண்டுபிடி விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();

                if (sps.getString(Picture_Game_Hard.this, "Exp_list").equals("on")) {
                    finish();
                    Intent i = new Intent(Picture_Game_Hard.this, Expandable_List_View.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Picture_Game_Hard.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான படம் பார்த்து கண்டுபிடி விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
                    finish();
                    sps.putString(Picture_Game_Hard.this, "date", "0");
                    Intent i = new Intent(Picture_Game_Hard.this, Picture_Game_Hard.class);
                    startActivity(i);
                }



              /*  w_head.setVisibility(View.INVISIBLE);
                final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.dailytomaingame);
                TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                TextView nos = (TextView) openDialog.findViewById(R.id.no);
                openDialog.setCancelable(false);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sps.putString(Picture_Game_Hard.this, "date", "0");
                        Intent i = new Intent(Picture_Game_Hard.this, Picture_Game_Hard.class);
                        startActivity(i);
                        finish();
                        openDialog.dismiss();
                    }
                });
                nos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        openDialog.dismiss();
                    }
                });
                openDialog.show();

                openDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        finish();
                        openDialog.dismiss();
                        return keyCode == KeyEvent.KEYCODE_BACK;
                    }
                });*/

            }
        }
    }

    private void daily_bones() {
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeee daily_bones_openDialog");
        openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView tomarrow_coin_earn = (TextView) openDialog.findViewById(R.id.tomarrow_coin_earn);


        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        openDialog.setCancelable(false);
        focus.setBase(SystemClock.elapsedRealtime());
        focus.stop();

        Calendar calendar3 = Calendar.getInstance();
        int cur_year1 = calendar3.get(Calendar.YEAR);
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        String str_month1 = "" + (cur_month1 + 1);
        if (str_month1.length() == 1) {
            str_month1 = "0" + str_month1;
        }

        String str_day1 = "" + cur_day1;
        if (str_day1.length() == 1) {
            str_day1 = "0" + str_day1;
        }
        final String str_date1 = str_day1 + "-" + str_month1 + "-" + cur_year1;

        Date date1 = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final String date = sdf.format(date1);

        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                int spx = skx + ea;
                String aStringx = Integer.toString(spx);
                score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                sps.putString(context, "daily_bonus_date", date);
                openDialog.dismiss();
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

            }
        });

        System.out.println("############################^^^^^^^^^^^^^^currentdate" + str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Picture_Game_Hard.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Picture_Game_Hard.this, "daily_bonus_date"))) {

        } else {
            sps.putInt(context, "daily_bonus_count", 0);
        }
        if (sps.getInt(context, "daily_bonus_count") == 0) {
            ea = 100;
        } else if (sps.getInt(context, "daily_bonus_count") == 1) {
            ea = 150;
        } else if (sps.getInt(context, "daily_bonus_count") == 2) {
            ea = 200;
        } else if (sps.getInt(context, "daily_bonus_count") == 3) {
            ea = 250;
        } else if (sps.getInt(context, "daily_bonus_count") == 4) {
            ea = 300;
        }
        prize_data_update(Picture_Game_Hard.this, ea);
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
      /*  final int vals = reward_play_count * 100;
        ea = ea + vals;*/
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomnod = rn.nextInt(maximumd - minmumd + 1) + minmumd;

        //String r= String.valueOf(w_id);
        //lt_id.setText(r);
        String ran_score = "";
        if (randomnod == 1) {
            sps.putInt(context, "daily_bonus_count", 1);
            ran_score = "150";
        } else if (randomnod == 2) {
            sps.putInt(context, "daily_bonus_count", 2);
            ran_score = "200";
        } else if (randomnod == 3) {
            sps.putInt(context, "daily_bonus_count", 3);
            ran_score = "250";
        } else if (randomnod == 4) {
            sps.putInt(context, "daily_bonus_count", 4);
            ran_score = "300";
        }

        LinearLayout extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);

        extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        extra_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 1;
                extra_coin_s = 1;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Picture_Game_Hard.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedAd.showAd();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(Picture_Game_Hard.this);
                                    rewarded_ad();
                                    Toast.makeText(Picture_Game_Hard.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);


                    }
                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }
            }
        });
                     /*   b_close.setOnClickListener(new View.OnClickListener() {

                       /* b_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openDialog.dismiss();
                            }
                        });*/
        openDialog.show();
    }

    private void pressKey(int keycode) {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        p_edit.onKeyDown(keycode, event);
    }


    public void showpopup() {

        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        final String snd = sps.getString(Picture_Game_Hard.this, "snd");
        toggleButton = (TextView) popupView.findViewById(R.id.toggle);

        if (snd.equals("off")) {
            toggleButton.setBackgroundResource(R.drawable.off);
        } else if (snd.equals("on")) {
            toggleButton.setBackgroundResource(R.drawable.on);
        }

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snd = sps.getString(Picture_Game_Hard.this, "snd");
                System.out.println("*****click");
                if (snd.equals("off")) {

                    System.out.println("*****on");
                    sps.putString(Picture_Game_Hard.this, "snd", "on");
                    toggleButton.setBackgroundResource(R.drawable.on);
                    sv = 1;
         /*           c1.setVolume(1, 1);
                    c2.setVolume(1, 1);
                    c3.setVolume(1, 1);
                    c4.setVolume(1, 1);
                    c5.setVolume(1, 1);
                    c6.setVolume(1, 1);
                    c7.setVolume(1, 1);
                    c8.setVolume(1, 1);
                    c9.setVolume(1, 1);
                    c10.setVolume(1, 1);
                    c11.setVolume(1, 1);
                    c12.setVolume(1, 1);
                    c13.setVolume(1, 1);
                    c14.setVolume(1, 1);
                    c15.setVolume(1, 1);
                    c16.setVolume(1, 1);
                    c17.setVolume(1, 1);
                    c18.setVolume(1, 1);
                    c19.setVolume(1, 1);
                    c20.setVolume(1, 1);
                    r1.setVolume(1, 1);
                    w1.setVolume(1, 1);
                    play1.setVolume(1, 1);*/

                    // popupWindow.dismiss();

                }
                if (snd.equals("on")) {
                    System.out.println("*****off");

                    sps.putString(Picture_Game_Hard.this, "snd", "off");
                    toggleButton.setBackgroundResource(R.drawable.off);
                    sv = 0;
                 /*   c1.setVolume(0, 0);
                    c2.setVolume(0, 0);
                    c3.setVolume(0, 0);
                    c4.setVolume(0, 0);
                    c5.setVolume(0, 0);
                    c6.setVolume(0, 0);
                    c7.setVolume(0, 0);
                    c8.setVolume(0, 0);
                    c9.setVolume(0, 0);
                    c10.setVolume(0, 0);
                    c11.setVolume(0, 0);
                    c12.setVolume(0, 0);
                    c13.setVolume(0, 0);
                    c14.setVolume(0, 0);
                    c15.setVolume(0, 0);
                    c16.setVolume(0, 0);
                    c17.setVolume(0, 0);
                    c18.setVolume(0, 0);
                    c19.setVolume(0, 0);
                    c20.setVolume(0, 0);
                    r1.setVolume(0, 0);
                    w1.setVolume(0, 0);
                    play1.setVolume(0, 0);*/
                    // popupWindow.dismiss();
                }
            }
        });

        popupWindow.showAsDropDown(p_setting, 50, -10);

    }

/*    public void settings() {
        String snd = sps.getString(Picture_Game_Hard.this, "snd");

        final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.settings);

        toggleButton = (ToggleButton) openDialog.findViewById(R.id.toggle);
        if (snd.equals("off")) {
            toggleButton.setChecked(true);
        } else if (snd.equals("on")) {

            toggleButton.setChecked(false);
        }
        Button cancel = (Button) openDialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog.dismiss();

            }
        });
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    sps.putString(Picture_Game_Hard.this, "snd", "off");

                    c1.setVolume(0, 0);
                    c2.setVolume(0, 0);
                    c3.setVolume(0, 0);
                    c4.setVolume(0, 0);
                    c5.setVolume(0, 0);
                    c6.setVolume(0, 0);
                    c7.setVolume(0, 0);
                    c8.setVolume(0, 0);
                    c9.setVolume(0, 0);
                    c10.setVolume(0, 0);
                    c11.setVolume(0, 0);
                    c12.setVolume(0, 0);
                    c13.setVolume(0, 0);
                    c14.setVolume(0, 0);
                    c15.setVolume(0, 0);
                    c16.setVolume(0, 0);
                    c17.setVolume(0, 0);
                    c18.setVolume(0, 0);
                    c19.setVolume(0, 0);
                    c20.setVolume(0, 0);
                    r1.setVolume(0, 0);
                    w1.setVolume(0, 0);
                    play1.setVolume(0, 0);

                    //

                    //sounds

                }
                if (isChecked == false) {
                    c1.setVolume(1, 1);
                    c2.setVolume(1, 1);
                    c3.setVolume(1, 1);
                    c4.setVolume(1, 1);
                    c5.setVolume(1, 1);
                    c6.setVolume(1, 1);
                    c7.setVolume(1, 1);
                    c8.setVolume(1, 1);
                    c9.setVolume(1, 1);
                    c10.setVolume(1, 1);
                    c11.setVolume(1, 1);
                    c12.setVolume(1, 1);
                    c13.setVolume(1, 1);
                    c14.setVolume(1, 1);
                    c15.setVolume(1, 1);
                    c16.setVolume(1, 1);
                    c17.setVolume(1, 1);
                    c18.setVolume(1, 1);
                    c19.setVolume(1, 1);
                    c20.setVolume(1, 1);
                    r1.setVolume(1, 1);
                    w1.setVolume(1, 1);
                    play1.setVolume(1, 1);
                    //
                    //sounds
                    sps.putString(Picture_Game_Hard.this, "snd", "on");
                }

            }
        });
        openDialog.show();
    }*/


    public void setSc() {
        if (s == 1) {
            openDialog_p.dismiss();
            s = 0;
        }

        next_continue = (TextView) openDialog_s.findViewById(R.id.continues2);
        ttscores = (TextView) openDialog_s.findViewById(R.id.tts_score2);
        final TextView wtp = (TextView) openDialog_s.findViewById(R.id.wtp);
        final TextView fbs = (TextView) openDialog_s.findViewById(R.id.fbp);
        final TextView kuduthal = (TextView) openDialog_s.findViewById(R.id.tt22);
        final TextView gplus = (TextView) openDialog_s.findViewById(R.id.gplus2);
        final TextView word = (TextView) openDialog_s.findViewById(R.id.arputham2);
        LinearLayout ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);
        final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);
        final LinearLayout rewardvideo = (LinearLayout) openDialog_s.findViewById(R.id.rewardvideo);

        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sps.getInt(Picture_Game_Hard.this, "reward_coin_txt") + "+நாணயங்கள் பெற");

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        ImageView prize_logo = (ImageView) openDialog_s.findViewById(R.id.prize_logo);
        if (sp.getInt(Picture_Game_Hard.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sp.getString(Picture_Game_Hard.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Picture_Game_Hard.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sp.getString(Picture_Game_Hard.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Picture_Game_Hard.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            //New_Main_Activity.load_addFromMain_multiplayer(Picture_Game_Hard.this, ads_layout);
            if (Utils.isNetworkAvailable(context)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(context, ads_layout);
            } else {
                ads_layout.setVisibility(View.GONE);
            }

            if (isNetworkAvailable()) {
                //load_addcontent2(context,ads_layout);
                // load_addinstall(context, ads_layout);
            } else {
           /*     if (native_adView3 != null) {
                    native_adView3.removeAllViews();
                }
                LayoutInflater inflater;
                inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.remote_config, null);
                ins_app(context, view1, sps.getInt(context, "remoteConfig"));
                ads_layout.addView(view1);*/
            }
        }


        next_continue.setVisibility(View.VISIBLE);
        word.setTypeface(tyr);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);
        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(Picture_Game_Hard.this, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndex("coins"));
        String aStringx = Integer.toString(skx);
        ttscores.setText(aStringx);


        if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Picture_Game_Hard.this, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }

        if (vs == 1) {

        } else {
            rewardvideo.setVisibility(View.INVISIBLE);
        }
        //Time Setting For Clue Game
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;

        f_sec = sec + sec2 + seconds;

        RelativeLayout adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);
        ads_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        vid_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(context)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }

                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedAd.showAd();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(context);
                                    rewarded_ad();
                                    Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

            }
        });


        rewardvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(context)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedAd.showAd();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(context);
                                    rewarded_ad();
                                    Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        wtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");

                        String msg = ("நான் சொல்லிஅடி செயலியில் படம் பார்த்து கண்டுபிடி நிலை " + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivity(Intent.createChooser(i, "Share via"));

                        startActivityForResult(Intent.createChooser(i, "Share via"), 21);


                    /*    if (sps.getString(Picture_Game_Hard.this, "watts_app_s").equals("")) {
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Score Adding
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ", null);
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                    int spx = skx + 20;
                                    String aStringx = Integer.toString(spx);
                                    score.setText(aStringx);
                                    ttscores.setText(aStringx);
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                                    sps.putString(Picture_Game_Hard.this, "watts_app_s", "yes");


                                }
                            }, 3000);
                        }*/


                    } else {
                        Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    // toast("இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
        });
        fbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
                btn_str = "share";
                if (isLoggedIn()) {


                    publishFeedDialog();
                    // toast("yes");
                } else {
                    openFacebookSession();
                    // toast("no");
                }*/

            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");

                        String msg = ("நான் சொல்லிஅடி செயலியில் படம் பார்த்து கண்டுபிடி நிலை " + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 16);
                    } else {
                        Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    // toast("இணையதள சேவையை சரிபார்க்கவும் ");
                }

            }

        });
        word.setText("ï¡Á");
        if (f_sec < 30) {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        } else if (f_sec > 30 && f_sec < 60) {
            word.setText("");
            word.setText("ÜŸ¹î‹");

        } else if (f_sec > 60) {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        }
        if (r == 1) {
            word.setText("");
            word.setText("ºòŸC ªêŒè");
            r = 0;
        }

        next_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();
                } else {
                    if (sps.getInt(getApplicationContext(), "ins_ad_new") == 4) {
                        sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (ins_game == null || !ins_game.isReady()) {
                                dia_dismiss = 1;
                                openDialog_s.dismiss();
                                next();
                                industrialload_game();
                                System.out.println("##################################FB INDUSTRIAL onLoggingImpression2");
                                return;
                            } else {
                                ins_game.showAd();
                            }

                        } else {
                            dia_dismiss = 1;
                            openDialog_s.dismiss();
                            next();
                        }

                    } else {
                        next();
                        dia_dismiss = 1;
                        openDialog_s.dismiss();
                        sps.putInt(getApplicationContext(), "ins_ad_new", (sps.getInt(getApplicationContext(), "ins_ad_new") + 1));
                    }

                    //  advancads();
                    //advancads_content();
                }



                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    if (getApiClient().isConnected()) {
                        if (isSignedIn()) {
                            int k1 = 0;
                            Cursor sc2 = myDbHelper.getQry("select * from score ");
                            sc2.moveToFirst();
                            if (sc2.getCount() != 0) {
                                k1 = sc2.getInt(sc2.getColumnIndex("l_points"));
                            }
                            Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard), k1);
                        }
                    }
                }

                if (sps.getString(getApplicationContext(), "1st_achiv").equals("")) {
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        if (getApiClient().isConnected()) {
                            if (isSignedIn()) {

                                Games.Achievements.unlock(getApiClient(), getString(R.string.achievement___1));
                                sps.putString(getApplicationContext(), "1st_achiv", "yes");
                            } else {
                                sps.putString(getApplicationContext(), "1st_achiv", "yes");
                            }
                        } else {
                            sps.putString(getApplicationContext(), "1st_achiv", "yes");
                        }
                    } else {
                        sps.putString(getApplicationContext(), "1st_achiv", "yes");
                    }

                }

                if (sps.getString(getApplicationContext(), "pic_a1").equals("")) {
                    if (sps.getInt(getApplicationContext(), "pic") >= 5) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {

                                    Games.Achievements.unlock(getApiClient(), getString(R.string.achievement__4));
                                    sps.putString(getApplicationContext(), "pic_a1", "yes");
                                } else {
                                    sps.putString(getApplicationContext(), "pic_a1", "yes");
                                }
                            } else {
                                sps.putString(getApplicationContext(), "pic_a1", "yes");
                            }
                        } else {
                            sps.putString(getApplicationContext(), "pic_a1", "yes");
                        }
                        sps.putInt(getApplicationContext(), "pic", (sps.getInt(getApplicationContext(), "pic") + 1));
                    } else {

                        sps.putInt(getApplicationContext(), "pic", (sps.getInt(getApplicationContext(), "pic") + 1));
                    }


                }
                if (sps.getString(getApplicationContext(), "ach9").equals("")) {
                    if (sps.getInt(getApplicationContext(), "ach9_a1") >= 25) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {

                                    Games.Achievements.unlock(getApiClient(), getString(R.string.achievement______9));
                                    sps.putString(getApplicationContext(), "ach9", "yes");
                                } else {
                                    sps.putString(getApplicationContext(), "ach9", "yes");
                                }
                            } else {
                                sps.putString(getApplicationContext(), "ach9", "yes");
                            }
                        } else {
                            sps.putString(getApplicationContext(), "ach9", "yes");
                        }
                        sps.putInt(getApplicationContext(), "ach9_a1", (sps.getInt(getApplicationContext(), "ach9_a1") + 1));
                    } else {

                        sps.putInt(getApplicationContext(), "ach9_a1", (sps.getInt(getApplicationContext(), "ach9_a1") + 1));
                    }


                }

                if (sps.getString(getApplicationContext(), "ach13").equals("")) {
                    if (sps.getInt(getApplicationContext(), "ach13_a1") >= 50) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {

                                    Games.Achievements.unlock(getApiClient(), getString(R.string.achievement___13));
                                    sps.putString(getApplicationContext(), "ach13", "yes");
                                } else {
                                    sps.putString(getApplicationContext(), "ach13", "yes");
                                }
                            } else {
                                sps.putString(getApplicationContext(), "ach13", "yes");
                            }
                        } else {
                            sps.putString(getApplicationContext(), "ach13", "yes");
                        }
                        sps.putInt(getApplicationContext(), "ach13_a1", (sps.getInt(getApplicationContext(), "ach13_a1") + 1));
                    } else {

                        sps.putInt(getApplicationContext(), "ach13_a1", (sps.getInt(getApplicationContext(), "ach13_a1") + 1));
                    }


                }

                if (sps.getString(getApplicationContext(), "ach16").equals("")) {
                    if (sps.getInt(getApplicationContext(), "ach16_a1") >= 50) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {

                                    Games.Achievements.unlock(getApiClient(), getString(R.string.achievement__16));
                                    sps.putString(getApplicationContext(), "ach16", "yes");
                                } else {
                                    sps.putString(getApplicationContext(), "ach16", "yes");
                                }
                            } else {
                                sps.putString(getApplicationContext(), "ach16", "yes");
                            }
                        } else {
                            sps.putString(getApplicationContext(), "ach16", "yes");
                        }
                        sps.putInt(getApplicationContext(), "ach16_a1", (sps.getInt(getApplicationContext(), "ach16_a1") + 1));
                    } else {

                        sps.putInt(getApplicationContext(), "ach16_a1", (sps.getInt(getApplicationContext(), "ach16_a1") + 1));
                    }


                }

                if (sps.getString(getApplicationContext(), "ach18").equals("")) {
                    if (sps.getInt(getApplicationContext(), "ach18_a1") >= 100) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {

                                    Games.Achievements.unlock(getApiClient(), getString(R.string.achievement____18));
                                    sps.putString(getApplicationContext(), "ach18", "yes");
                                } else {
                                    sps.putString(getApplicationContext(), "ach18", "yes");
                                }
                            } else {
                                sps.putString(getApplicationContext(), "ach18", "yes");
                            }
                        } else {
                            sps.putString(getApplicationContext(), "ach18", "yes");
                        }
                        sps.putInt(getApplicationContext(), "ach18_a1", (sps.getInt(getApplicationContext(), "ach18_a1") + 1));
                    } else {

                        sps.putInt(getApplicationContext(), "ach18_a1", (sps.getInt(getApplicationContext(), "ach18_a1") + 1));
                    }


                }

                if (sps.getString(getApplicationContext(), "ach19").equals("")) {
                    if (sps.getInt(getApplicationContext(), "ach19_a1") >= 250) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {

                                    Games.Achievements.unlock(getApiClient(), getString(R.string.achievement___19));
                                    sps.putString(getApplicationContext(), "ach19", "yes");
                                } else {
                                    sps.putString(getApplicationContext(), "ach19", "yes");
                                }
                            } else {
                                sps.putString(getApplicationContext(), "ach19", "yes");
                            }
                        } else {
                            sps.putString(getApplicationContext(), "ach19", "yes");
                        }
                        sps.putInt(getApplicationContext(), "ach19_a1", (sps.getInt(getApplicationContext(), "ach19_a1") + 1));
                    } else {

                        sps.putInt(getApplicationContext(), "ach19_a1", (sps.getInt(getApplicationContext(), "ach19_a1") + 1));
                    }


                }

              /*  dia_dismiss = 1;
                openDialog_s.dismiss();*/
            }
        });
        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss != 1) {

                    System.out.println("###############################openDialog_s");
                    sps.putString(Picture_Game_Hard.this, "game_area", "on");
                    String date = sps.getString(Picture_Game_Hard.this, "date");
                    if (date.equals("0")) {

                        if (main_act.equals("")) {
                            openDialog_s.dismiss();
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            openDialog_s.dismiss();
                            finish();
                        }

                    } else {
                        if (sps.getString(Picture_Game_Hard.this, "Exp_list").equals("on")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Picture_Game_Hard.this, Expandable_List_View.class);
                            startActivity(i);

                        } else {
                            if (main_act.equals("")) {
                                openDialog_s.dismiss();
                                finish();
                                Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                openDialog_s.dismiss();
                                finish();
                            }
                        }

                    }
                } else {
                    dia_dismiss = 0;
                }


            }
        });

        if (!isFinishing()) {
            openDialog_s.show();
        }

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

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void dialog(int i) {
        openDialog_earncoin = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = (TextView) openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = (TextView) openDialog_earncoin.findViewById(R.id.ssss);

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_earncoin.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_earncoin.cancel();
            }
        });

        TextView wpro = (TextView) openDialog_earncoin.findViewById(R.id.wpro);
        if (i == 1) {
            cancel.setVisibility(View.INVISIBLE);
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 100  நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }

        RelativeLayout video = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnvideo);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 1;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Picture_Game_Hard.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        }
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                        //reward(context);
                        rewarded_ad();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
               /* extra_coin_s = 0;
                rvo = 1;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Picture_Game_Hard.this, "" + "Reward video", "Loading...");


                    if (mRewardedVideoAd.isLoaded()) {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        String date = sps.getString(Picture_Game_Hard.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        }
                        reward_progressBar.dismiss();
                        showRewardedVideo();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {

                        startGame();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (mRewardedVideoAd.isLoaded()) {
                                    showRewardedVideo();
                                    openDialog_earncoin.cancel();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    startGame();
                                    Toast.makeText(Picture_Game_Hard.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        wp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        openDialog_earncoin.dismiss();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");
                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 12);
                   /*     if (sps.getString(Picture_Game_Hard.this, "watts_app").equals("")) {
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Score Adding
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ", null);
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                    int spx = skx + 20;
                                    String aStringx = Integer.toString(spx);
                                    score.setText(aStringx);
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                                    sps.putString(Picture_Game_Hard.this, "watts_app", "yes");

                                }
                            }, 3000);
                        }*/

                        ///shareing reward
                    } else {
                        Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    // toast("இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (isNetworkAvailable()) {
                    openDialog_earncoin.dismiss();

                    btn_str = "invite";
                    if (isLoggedIn()) {
                        Bundle params = new Bundle();
                        params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        showDialogWithoutNotificationBarInvite("apprequests",
                                params);
                        // toast("yes");
                    } else {
                        openFacebookSession();
                        // toast("no");
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }   // toast("இணையதள சேவையை சரிபார்க்கவும் ");
*/
            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isNetworkAvailable()) {

                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        String date = sps.getString(Picture_Game_Hard.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        }
                        openDialog_earncoin.dismiss();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");
                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 15);

                    } else {
                        Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    // toast("இணையதள சேவையை சரிபார்க்கவும் ");
                }

            }

        });
        openDialog_earncoin.show();
    }

    public void onBackPressed() {


        // public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        //  if (keyCode == KeyEvent.KEYCODE_BACK) {
        spd.putString(Picture_Game_Hard.this, "game_area", "on");

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {


            sps.putInt(Picture_Game_Hard.this, "addlodedd", 0);
            s = 1;
            openDialog_p = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.back_pess);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String dates = sps.getString(Picture_Game_Hard.this, "date");
                    int pos;

                    if (dates.equals("0")) {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        pos = 1;
                        myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                    } else {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        pos = 2;
                        myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                        myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                    }

                    String date = sps.getString(Picture_Game_Hard.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    } else {
                        if (sps.getString(Picture_Game_Hard.this, "Exp_list").equals("on")) {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, Expandable_List_View.class);
                            startActivity(i);
                        } else {
                            if (main_act.equals("")) {
                                finish();
                                Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                finish();
                            }


                        }
                    }


                    //ad
                    if (sps.getInt(context, "purchase_ads") == 0) {
                        if (sps.getInt(getApplicationContext(), "game_exit_ins") == 4) {
                            sps.putInt(getApplicationContext(), "game_exit_ins", 0);
                            if (Utils.isNetworkAvailable(getApplicationContext())) {
                                if (game_exit_ins != null && game_exit_ins.isReady()) {
                                    openDialog_p.dismiss();
                                    game_exit_ins.showAd();
                                }
                            }
                        } else {
                            openDialog_p.dismiss();
                            sps.putInt(getApplicationContext(), "game_exit_ins", (sps.getInt(getApplicationContext(), "game_exit_ins") + 1));
                        }
                    }else{
                        openDialog_p.dismiss();
                    }
                    //ad

                       /* if (interstitialAd.isLoaded()) {
                            interstitialAd.show();
                            interstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {

                                    String date = sps.getString(Picture_Game_Hard.this, "date");
                                    if (date.equals("0")) {
                                        if (main_act.equals("")){
                                            openDialog_p.dismiss();
                                            finish();
                                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                            startActivity(i);
                                        }else {
                                            openDialog_p.dismiss();
                                            finish();
                                        }

                                    } else {
                                        if (sps.getString(Picture_Game_Hard.this, "Exp_list").equals("on")) {
                                            finish();
                                            Intent i = new Intent(Picture_Game_Hard.this, Expandable_List_View.class);
                                            startActivity(i);
                                        } else {
                                            if (main_act.equals("")){
                                                openDialog_p.dismiss();
                                                finish();
                                                Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                                startActivity(i);
                                            }else {
                                                openDialog_p.dismiss();
                                                finish();
                                            }
                                        }
                                    }

                                }
                            });
                        } else {
                            String date = sps.getString(Picture_Game_Hard.this, "date");
                            if (date.equals("0")) {
                                if (main_act.equals("")){
                                    openDialog_p.dismiss();
                                    finish();
                                    Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                    startActivity(i);
                                }else {
                                    openDialog_p.dismiss();
                                    finish();
                                }

                            } else {
                                if (sps.getString(Picture_Game_Hard.this, "Exp_list").equals("on")) {
                                    finish();
                                    Intent i = new Intent(Picture_Game_Hard.this, Expandable_List_View.class);
                                    startActivity(i);
                                } else {
                                    if (main_act.equals("")){
                                        openDialog_p.dismiss();
                                        finish();
                                        Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                        startActivity(i);
                                    }else {
                                        openDialog_p.dismiss();
                                        finish();
                                    }
                                }
                            }
                        }*/
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog_p.dismiss();
                }
            });
            openDialog_p.show();


        }
    }
   /* return super.onKeyDown(keyCode, event);
    }*/

    public void coinanim() {
////
        completegame();

        if (f == 0) {
            //score intial
            int skq = 0;
            Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
            cfq.moveToFirst();
            if (cfq.getCount() != 0) {
                skq = cfq.getInt(cfq.getColumnIndex("coins"));
            }
            String tr = String.valueOf(skq);
            score.setText(tr);
            //
            e2 = skq;
            //play1.start();
            spz4.play(soundId4, sv, sv, 0, 0, sv);
            p_coin.setVisibility(View.VISIBLE);
            int[] locationInWindow = new int[2];
            p_coin.getLocationInWindow(locationInWindow);
            int[] locationOnScreen = new int[2];
            p_coin.getLocationOnScreen(locationOnScreen);
            float sourceX = locationOnScreen[0];
            float sourceY = locationOnScreen[1];
            int[] locationInWindowSecond = new int[2];
            score.getLocationInWindow(locationInWindowSecond);
            int[] locationOnScreenSecond = new int[2];
            score.getLocationOnScreen(locationOnScreenSecond);
            float destinationX = locationOnScreenSecond[0];
            float destinationY = locationOnScreenSecond[1];
            TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
            transAnimation.setDuration(500);
            p_coin.startAnimation(transAnimation);
            p_coin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    p_coin.setVisibility(View.INVISIBLE);
                }
            }, transAnimation.getDuration());


            ////

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //play1.start();
                    spz4.play(soundId4, sv, sv, 0, 0, sv);
                    p_coin.setVisibility(View.VISIBLE);
                    int[] locationInWindow = new int[2];
                    p_coin.getLocationInWindow(locationInWindow);
                    int[] locationOnScreen = new int[2];
                    p_coin.getLocationOnScreen(locationOnScreen);
                    float sourceX = locationOnScreen[0];
                    float sourceY = locationOnScreen[1];
                    int[] locationInWindowSecond = new int[2];
                    score.getLocationInWindow(locationInWindowSecond);
                    int[] locationOnScreenSecond = new int[2];
                    score.getLocationOnScreen(locationOnScreenSecond);
                    float destinationX = locationOnScreenSecond[0];
                    float destinationY = locationOnScreenSecond[1];
                    TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
                    transAnimation.setDuration(1000);
                    p_coin.startAnimation(transAnimation);
                    p_coin.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            p_coin.setVisibility(View.INVISIBLE);
                        }
                    }, transAnimation.getDuration());
                }
            }, 1000);

            new Thread(new Runnable() {

                public void run() {
                    int es = e2 + 20;
                    while (e2 < es) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        score.post(new Runnable() {

                            public void run() {

                                score.setText("" + e2);

                            }

                        });
                        e2++;
                    }

                }

            }).start();

            Handler handler30 = new Handler();
            handler30.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                    score.startAnimation(levels1);
                }
            }, 2200);

            Handler handler21 = new Handler();
            handler21.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int skx = 0;
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        skx = cfx.getInt(cfx.getColumnIndex("coins"));

                    }
                    int spx = skx + 20;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                    Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                    ch.moveToFirst();
                    int sh = ch.getInt(ch.getColumnIndex("l_points"));
                    int shh = sh + 50;
                    myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

                    setSc();
                }
            }, 4000);
        } else if (f == 2) {
            //score intial
            int skq = 0;
            Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
            cfq.moveToFirst();
            if (cfq.getCount() != 0) {
                skq = cfq.getInt(cfq.getColumnIndex("coins"));

            }
            String tr = String.valueOf(skq);
            score.setText(tr);
            //
            e2 = skq;
            //play1.start();
            spz4.play(soundId4, sv, sv, 0, 0, sv);
            p_coin.setVisibility(View.VISIBLE);
            int[] locationInWindow = new int[2];
            p_coin.getLocationInWindow(locationInWindow);
            int[] locationOnScreen = new int[2];
            p_coin.getLocationOnScreen(locationOnScreen);
            float sourceX = locationOnScreen[0];
            float sourceY = locationOnScreen[1];
            int[] locationInWindowSecond = new int[2];
            score.getLocationInWindow(locationInWindowSecond);
            int[] locationOnScreenSecond = new int[2];
            score.getLocationOnScreen(locationOnScreenSecond);
            float destinationX = locationOnScreenSecond[0];
            float destinationY = locationOnScreenSecond[1];
            TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
            transAnimation.setDuration(600);
            p_coin.startAnimation(transAnimation);
            p_coin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    p_coin.setVisibility(View.INVISIBLE);
                }
            }, transAnimation.getDuration());


            ////


            new Thread(new Runnable() {

                public void run() {
                    int es = e2 + 15;
                    while (e2 < es) {
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        score.post(new Runnable() {

                            public void run() {

                                score.setText("" + e2);

                            }

                        });
                        e2++;
                    }

                }

            }).start();

            Handler handler21 = new Handler();
            handler21.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int skx = 0;
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        skx = cfx.getInt(cfx.getColumnIndex("coins"));

                    }
                    int spx = skx + 15;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                    Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                    ch.moveToFirst();
                    if (ch.getCount() != 0) {
                        int sh = ch.getInt(ch.getColumnIndex("l_points"));
                        int shh = sh + 30;
                        myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

                    }

                    setSc();
                }
            }, 2500);

            Handler handler30 = new Handler();
            handler30.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                    score.startAnimation(levels1);
                }
            }, 1300);

        } else if (f == 1) {
            //score intial
            int skq = 0;
            Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
            cfq.moveToFirst();
            if (cfq.getCount() != 0) {
                skq = cfq.getInt(cfq.getColumnIndex("coins"));

            }
            String tr = String.valueOf(skq);
            score.setText(tr);
            //
            e2 = skq;
            //play1.start();
            spz4.play(soundId4, sv, sv, 0, 0, sv);
            p_coin.setVisibility(View.VISIBLE);
            int[] locationInWindow = new int[2];
            p_coin.getLocationInWindow(locationInWindow);
            int[] locationOnScreen = new int[2];
            p_coin.getLocationOnScreen(locationOnScreen);
            float sourceX = locationOnScreen[0];
            float sourceY = locationOnScreen[1];
            int[] locationInWindowSecond = new int[2];
            score.getLocationInWindow(locationInWindowSecond);
            int[] locationOnScreenSecond = new int[2];
            score.getLocationOnScreen(locationOnScreenSecond);
            float destinationX = locationOnScreenSecond[0];
            float destinationY = locationOnScreenSecond[1];
            TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
            transAnimation.setDuration(600);
            p_coin.startAnimation(transAnimation);
            p_coin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    p_coin.setVisibility(View.INVISIBLE);
                }
            }, transAnimation.getDuration());


            ////


            new Thread(new Runnable() {

                public void run() {
                    int es = e2 + 10;
                    while (e2 < es) {
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        score.post(new Runnable() {

                            public void run() {

                                score.setText("" + e2);

                            }

                        });
                        e2++;
                    }

                }

            }).start();

            Handler handler30 = new Handler();
            handler30.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                    score.startAnimation(levels1);
                }
            }, 1200);
            Handler handler21 = new Handler();
            handler21.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                        int spx = skx + 10;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    }


                    Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                    ch.moveToFirst();
                    if (ch.getCount() != 0) {
                        int sh = ch.getInt(ch.getColumnIndex("l_points"));
                        int shh = sh + 10;
                        myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

                    }

                    setSc();
                }
            }, 2500);
        }
    }

    public void pic_show(int a) {
        openDialogk = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialogk.setContentView(R.layout.show_pic);
        pic_show = (TouchImageView) openDialogk.findViewById(R.id.pic_show);
        Button cancel = (Button) openDialogk.findViewById(R.id.p_cancel);

        if (a == 1) {
            if (isdown.equals("0")) {

                pic_show.setImageResource(im11);

            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                Resources res1 = getResources();
                BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                pic_show.setImageDrawable(bd);


            }
        } else if (a == 2) {
            if (isdown.equals("0")) {
                pic_show.setImageResource(im12);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();

                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                Resources res2 = getResources();
                BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                pic_show.setImageDrawable(bd2);


            }

        } else if (a == 3) {

            if (isdown.equals("0")) {
                pic_show.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word3 + "");
                Resources res2 = getResources();
                BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                pic_show.setImageDrawable(bd2);


            }

        } else if (a == 4) {
            if (isdown.equals("0")) {
                pic_show.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word4 + "");
                Resources res2 = getResources();
                BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                pic_show.setImageDrawable(bd2);
            }
        }

        TextView wq1 = (TextView) openDialogk.findViewById(R.id.wq1);
        TextView wq2 = (TextView) openDialogk.findViewById(R.id.wq2);
        RelativeLayout rts = (RelativeLayout) openDialogk.findViewById(R.id.rts);
        wq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogk.dismiss();
            }
        });
        wq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogk.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogk.dismiss();
            }
        });
        rts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogk.dismiss();
            }
        });
        openDialogk.show();
    }

    protected void onResume() {
        super.onResume();

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ON Resume");


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Picture_Game_Hard.this);
        mFirebaseAnalytics.setCurrentScreen(this, "Picture Game", null);
        //uiHelper.onResume();
        //AppEventsLogger.activateApp(this);

        if (setting_access == 1) {
            setting_access = 0;
            //if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                downloaddata_daily();
           /* } else {
                settingpermission();
            }*/
        } else if (setting_access == 2) {
            setting_access = 0;
            //if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                downloaddata_regular();
           /* } else {
                settingpermission();
            }*/
        }
     /*   NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sps.getInt(Picture_Game_Hard.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            adds.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);

        } else {
            if (Utils.isNetworkAvailable(Picture_Game_Hard.this)){
                fb_native(Picture_Game_Hard.this,native_banner_ad_container);
                *//*  if (sps.getInt(Picture_Game_Hard.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Picture_Game_Hard.this,native_banner_ad_container);
                }else {
                    fb_native(Picture_Game_Hard.this,native_banner_ad_container);
                }*//*
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

           *//* if (sps.getInt(Picture_Game_Hard.this, "addlodedd") == 1) {
                System.out.println("####Native");
                New_Main_Activity.load_addFromMain(Picture_Game_Hard.this, adds);
            } else {
                if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {
                    sps.putInt(Picture_Game_Hard.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(Picture_Game_Hard.this);
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

            }
*//*
        }
*/

        if (sps.getString(getApplicationContext(), "ach11").equals("")) {
            timee();
        }
        if (sps.getString(getApplicationContext(), "ach12").equals("")) {
            timeehr();
        }

        if (sps.getInt(Picture_Game_Hard.this, "goto_sett") == 1) {
            sps.putInt(Picture_Game_Hard.this, "goto_sett", 0);

            if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {
               /* Cursor c4 = myDbHelper.getQry("select gameid,wordid from pic_game order by wordid", null);
                if (c4.getCount() != 0) {
                    c4.moveToLast();
                }*/
            }
        }

        if (sps.getString(Picture_Game_Hard.this, "resume_p").equals("")) {
            sps.putString(Picture_Game_Hard.this, "resume_p", "yes");

        } else {
            String date = sps.getString(Picture_Game_Hard.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                pos = 1;
                cs = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "' and levelid='" + wordid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                    noofclue = cs.getInt(cs.getColumnIndex("noclue"));
                }
            } else {
                pos = 2;
                cs = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and levelid='" + wordid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {

                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                    noofclue = cs.getInt(cs.getColumnIndex("noclue"));
                }
            }

            if (noofclue == 1) {
                f = 1;
            }
            //  long wt=sps.getInt(Word_Game_Hard.this,"old_time_start");
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
            System.out.println("##################################dscore" + dscore);
        }
    }


   /* public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());
    }

    private void publishFeedDialog() {
        Bundle params = new Bundle();


        params.putString("name", "சொல்லிஅடி");
        // params.putString("message", "my_message");
        params.putString("link", "https://goo.gl/CcA9a8");
        params.putString("description", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் +\n" +
                "                             விளையாட இங்கே கிளிக் செய்யவும் ");
        params.putString("caption", "நான் சொல்லிஅடி செயலியில்  படம் பார்த்துகண்டுபிடி   நிலை " + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.");
        // params.putString("picture","https://s3-ap-southeast-1.amazonaws.com/nithra-tamil/solliadi_logo.webp");
        WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(this,
                Session.getActiveSession(), params)).setOnCompleteListener(
                new OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the success
                            // and the post Id.
                            final String postId = values.getString("post_id");
                            if (postId != null) {

                              *//*  if (sps.getString(Picture_Game_Hard.this, "face_share").equals("")) {

                                    sps.putString(Picture_Game_Hard.this, "face_share", "yes");


                                }*//*

                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ", null);
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                int spx = skx + 10;
                                String aStringx = Integer.toString(spx);
                                //score.setText(aStringx);
                                // ttscores.setText(aStringx);
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                share_earn2(10);
                                ///Reward Share
                                retype = "s";
                                Calendar calendar3 = Calendar.getInstance();
                                int cur_year1 = calendar3.get(Calendar.YEAR);
                                int cur_month1 = calendar3.get(Calendar.MONTH);
                                int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                                String str_month1 = "" + (cur_month1 + 1);
                                if (str_month1.length() == 1) {
                                    str_month1 = "0" + str_month1;
                                }

                                String str_day1 = "" + cur_day1;
                                if (str_day1.length() == 1) {
                                    str_day1 = "0" + str_day1;
                                }
                                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

                                if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
                                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'", null);
                                    cn.moveToFirst();
                                    int gm1 = cn.getInt(cn.getColumnIndex("score"));
                                    int gm1s = gm1 + 1;
                                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                                }
                                ///Reward Share


                            } else {
                                // User clicked the Cancel button
                                Toast.makeText(getApplicationContext(),
                                        "Publish cancelled", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                            // User clicked the "x" button
                            Toast.makeText(getApplicationContext(),
                                    "Publish cancelled", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            // Generic, ex: network error
                            Toast.makeText(getApplicationContext(),
                                    "Error posting story", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                }).build();
        feedDialog.show();
    }


    private void showDialogWithoutNotificationBarInvite(String action, Bundle params) {
        final WebDialog dialog = new WebDialog.Builder(context,
                Session.getActiveSession(), action, params)
                .setOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error != null
                                && !(error instanceof FacebookOperationCanceledException)) {

                        }

                        try {
                            System.out.println("Invitation was sent to "
                                    + values.toString());

                            for (int i = 0; values.containsKey("to[" + i + "]"); i++) {
                                String curId = values
                                        .getString("to[" + i + "]");

                            }

                            // lastearn("invaite friends", (values.size() - 1));
                            if ((values.size() - 1) >= 1) {
                                //setcoin(values.size() - 1);

                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ", null);
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                int spx = (values.size() - 1) * 10;
                                String aStringx = Integer.toString(spx + skx);
                                // score.setText(aStringx);
                                myDbHelper.executeSql("UPDATE score SET coins='" + (spx + skx) + "'");

                                share_earn(spx);

                                // Toast.makeText(Picture_Game_Hard.this, "கூடுதல் நாணயங்கள்  " + spx + "  வழங்கப்பட்டது.தற்போது உங்களது மொத்த நாணயங்கள்" + (spx + skx) + "", Toast.LENGTH_SHORT).show();
                                // share_earn(10);

                                ///Reward Share
                                retype = "s";
                                Calendar calendar3 = Calendar.getInstance();
                                int cur_year1 = calendar3.get(Calendar.YEAR);
                                int cur_month1 = calendar3.get(Calendar.MONTH);
                                int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                                String str_month1 = "" + (cur_month1 + 1);
                                if (str_month1.length() == 1) {
                                    str_month1 = "0" + str_month1;
                                }

                                String str_day1 = "" + cur_day1;
                                if (str_day1.length() == 1) {
                                    str_day1 = "0" + str_day1;
                                }
                                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

                                if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
                                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'", null);
                                    cn.moveToFirst();
                                    int gm1 = cn.getInt(cn.getColumnIndex("score"));
                                    int spxx = (values.size() - 1);
                                    int gm1s = gm1 + spxx;
                                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                                }
                                ///Reward Share

                            }

                        } catch (Exception e) {

                        }
                    }
                }).build();

        Window dialog_window = dialog.getWindow();
        dialog_window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // dialogAction = action;
        // dialogParams = params;

        dialog.show();
    }

    private void openFacebookSession() {
        Session.openActiveSession(this, true, Arrays.asList("email",
                "user_birthday", "user_hometown", "user_location"),
                new Session.StatusCallback() {
                    @Override
                    public void call(Session session, SessionState state,
                                     Exception exception) {

                        if (session != null && session.isOpened()) {
                            // toast("open");

                            if (btn_str.equals("share")) {

                                publishFeedDialog();
                            } else if (btn_str.equals("invite")) {

                                Bundle params = new Bundle();
                                params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                        "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                                showDialogWithoutNotificationBarInvite(
                                        "apprequests", params);
                            }
                        }
                    }
                });
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //uiHelper.onSaveInstanceState(outState);
        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {

            if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {


                String date = sps.getString(Picture_Game_Hard.this, "date");
                if (date.equals("0")) {

                    Cursor c1 = myDbHelper.getQry("select id from maintable order by id DESC");
                    c1.moveToFirst();
                    System.out.print("Count====" + c1.getCount());

                    if (c1.getCount() != 0) {

                        //c1.getString(c1.getColumnIndex("id"));
                        System.out.print("Last ID====" + c1.getString(c1.getColumnIndex("id")));

                        downloadcheck("" + c1.getString(c1.getColumnIndex("id")), "ord");

                    } else {
                        downloadcheck("0", "ord");
                    }

                } else {
                    Cursor c1 = myDbHelper.getQry("select id from dailytest order by id DESC");
                    c1.moveToFirst();
                    System.out.print("Count====" + c1.getCount());
                    if (c1.getCount() != 0) {
                        //c1.getString(c1.getColumnIndex("id"));
                        System.out.print("Last ID====" + c1.getString(c1.getColumnIndex("id")));

                        downloadcheck("" + c1.getString(c1.getColumnIndex("id")), "daily");

                    } else {
                        System.out.print("else====");

                        downloadcheck("0", "daily");
                    }
                }


            } else {
                NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                native_banner_ad_container.setVisibility(View.INVISIBLE);
                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                            /*.setTitle("Delete entry")*/
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                          /*  try {
                                                startActivityForResult(new Intent(
                                                        Settings.ACTION_WIRELESS_SETTINGS), 0);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                startActivityForResult(new Intent(
                                                        Settings.ACTION_SETTINGS), 0);
                                            }*/
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                sps.putInt(Picture_Game_Hard.this, "goto_sett", 1);


                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                                String date = sps.getString(Picture_Game_Hard.this, "date");
                                if (date.equals("0")) {
                                    backexitnet();
                                } else {
                                    backexitnet();
                                }
                               /* Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                startActivity(i);*/
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        }


        if (requestCode == 2) {
            if (Utils.isNetworkAvailable(getApplicationContext())) {

                String date = sps.getString(Picture_Game_Hard.this, "date");
                if (date.equals("0")) {
                    Cursor cursor1 = myDbHelper.getQry("SELECT id FROM maintable order by id desc");
                    cursor1.moveToFirst();
                    String lastid = null;
                    if (cursor1.getCount() != 0) {
                        lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                    }

                    downpic(id, lastid);
                } else {
                    Cursor cursor1 = myDbHelper.getQry("SELECT id FROM dailytest order by id desc");
                    cursor1.moveToFirst();
                    String lastid = null;
                    if (cursor1.getCount() != 0) {
                        lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                    }

                    downpic(id, lastid);
                }
            } else {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                         /*   .setTitle("Delete entry")*/
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 2);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                                String date = sps.getString(Picture_Game_Hard.this, "date");
                                if (date.equals("0")) {
                                    backexitnet();
                                } else {
                                    backexitnet();
                                }
                               /* Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                startActivity(i);*/
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        }


        if (requestCode == 15) {
            if (resultCode == -1) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                int spx = skx + 10;
                String aStringx = Integer.toString(spx);
                // score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                //setcoin(1);
                share_earn(10);

                ///Reward Share
                retype = "s";
                Calendar calendar3 = Calendar.getInstance();
                int cur_year1 = calendar3.get(Calendar.YEAR);
                int cur_month1 = calendar3.get(Calendar.MONTH);
                int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                String str_month1 = "" + (cur_month1 + 1);
                if (str_month1.length() == 1) {
                    str_month1 = "0" + str_month1;
                }

                String str_day1 = "" + cur_day1;
                if (str_day1.length() == 1) {
                    str_day1 = "0" + str_day1;
                }
                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

                if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                    cn.moveToFirst();
                    int gm1 = cn.getInt(cn.getColumnIndex("score"));
                    int gm1s = gm1 + 1;
                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                }
                ///Reward Share

            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 16) {
            if (resultCode == -1) {
              /*  if (sps.getString(Picture_Game_Hard.this, "gplues").equals("yes")) {

                }*/

                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                int spx = skx + 10;
                String aStringx = Integer.toString(spx);
                //score.setText(aStringx);
                // ttscores.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                sps.putString(Picture_Game_Hard.this, "gplues", "no");
                share_earn2(10);

                ///Reward Share
                retype = "s";
                Calendar calendar3 = Calendar.getInstance();
                int cur_year1 = calendar3.get(Calendar.YEAR);
                int cur_month1 = calendar3.get(Calendar.MONTH);
                int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                String str_month1 = "" + (cur_month1 + 1);
                if (str_month1.length() == 1) {
                    str_month1 = "0" + str_month1;
                }

                String str_day1 = "" + cur_day1;
                if (str_day1.length() == 1) {
                    str_day1 = "0" + str_day1;
                }
                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

                if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                    cn.moveToFirst();
                    int gm1 = cn.getInt(cn.getColumnIndex("score"));
                    int gm1s = gm1 + 1;
                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                }
                ///Reward Share
            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == 12) {
            if (resultCode == -1) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                int spx = skx + 20;
                String aStringx = Integer.toString(spx);
                //score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                share_earn(20);

                if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
                    ///Reward Share
                    retype = "s";
                    Calendar calendar3 = Calendar.getInstance();
                    int cur_year1 = calendar3.get(Calendar.YEAR);
                    int cur_month1 = calendar3.get(Calendar.MONTH);
                    int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                    String str_month1 = "" + (cur_month1 + 1);
                    if (str_month1.length() == 1) {
                        str_month1 = "0" + str_month1;
                    }

                    String str_day1 = "" + cur_day1;
                    if (str_day1.length() == 1) {
                        str_day1 = "0" + str_day1;
                    }
                    final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;
                    ///
                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                    cn.moveToFirst();
                    int gm1 = cn.getInt(cn.getColumnIndex("score"));
                    int gm1s = gm1 + 1;
                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                    ///Reward Share
                }
            } else {
            }
        }

        if (requestCode == 21) {
            if (resultCode == -1) {


                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                int spx = skx + 20;
                String aStringx = Integer.toString(spx);
                //score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                share_earn2(20);

                if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
                    ///Reward Share
                    retype = "s";
                    Calendar calendar3 = Calendar.getInstance();
                    int cur_year1 = calendar3.get(Calendar.YEAR);
                    int cur_month1 = calendar3.get(Calendar.MONTH);
                    int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

                    String str_month1 = "" + (cur_month1 + 1);
                    if (str_month1.length() == 1) {
                        str_month1 = "0" + str_month1;
                    }

                    String str_day1 = "" + cur_day1;
                    if (str_day1.length() == 1) {
                        str_day1 = "0" + str_day1;
                    }
                    final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;
                    ///
                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                    cn.moveToFirst();
                    int gm1 = cn.getInt(cn.getColumnIndex("score"));
                    int gm1s = gm1 + 1;
                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                    ///Reward Share
                }
            } else {
            }
        }
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
                    //  sharedPrefAddInt("randomtime", t);
//                    Toast.makeText(New_Main_Activity.this, ""+t, Toast.LENGTH_SHORT).show();
                    System.out.println("time " + t);
                } else {


                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        if (getApiClient().isConnected()) {
                            sps.putString(getApplicationContext(), "ach11", "yes");

                            Games.Achievements.unlock(getApiClient(), getString(R.string.achievement___11));

                        } else {
                            sps.putString(getApplicationContext(), "ach11", "yes");
                        }
                    }
                    t1.cancel();

                }

            }
        }, 1000, 1000);

    }

    public void timeehr() {
        th = new Timer();
        th.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                t2 = sps.getInt(getApplicationContext(), "hr");
                if (t2 > 0) {
                    t2--;
                    System.out.println("times---" + t2);
                    sps.putInt(getApplicationContext(), "hr", t2);
                    //  sharedPrefAddInt("randomtime", t);
//                    Toast.makeText(New_Main_Activity.this, ""+t, Toast.LENGTH_SHORT).show();
                    System.out.println("time " + t2);
                } else {


                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        if (getApiClient().isConnected()) {
                            sps.putString(getApplicationContext(), "ach12", "yes");

                            Games.Achievements.unlock(getApiClient(), getString(R.string.achievement___12));
                        } else {
                            sps.putString(getApplicationContext(), "ach12", "yes");
                        }
                    }
                    th.cancel();

                }

            }
        }, 1000, 1000);

    }


    @Override
    public void onPause() {
        super.onPause();

        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.stop();

        String date = sps.getString(Picture_Game_Hard.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
        }
        System.out.println("##################################ttstop" + ttstop);


        //uiHelper.onPause();
        try {
            t1.cancel();
            th.cancel();
        } catch (Exception e) {

        }
        // AppEventsLogger.deactivateApp(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // uiHelper.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    public void downloadcheck(final String lastid, final String daily) {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        w_head.setVisibility(View.INVISIBLE);
        Utils.mProgress(Picture_Game_Hard.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();
        Utils.mProgress.setCancelable(false);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {


                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("lastid", lastid));

                if (daily.equals("ord")) {
                    nameValuePairs.add(new BasicNameValuePair("mode", "regular"));
                } else {
                    nameValuePairs.add(new BasicNameValuePair("mode", "daily"));
                }
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


                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
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
                            if (json_data.getString("NoData").equals("NoData")) {
                                downnodata = "NoData";
                            } else {
                                downnodata = "YesData";

                                for (int i = 0; i < jArray.length(); i++) {
                                    json_data = jArray.getJSONObject(i);
                                    ContentValues cv = new ContentValues();
                                    cv.put("id", json_data.getString("id"));
                                    cv.put("gameid", json_data.getString("gameid"));
                                    cv.put("levelid", json_data.getString("levelid"));
                                    cv.put("letters", json_data.getString("letters"));

                                    String newName = json_data.getString("answer").toString().replaceAll(" ", "");
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
                    NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                    native_banner_ad_container.setVisibility(View.INVISIBLE);
                    w_head.setVisibility(View.INVISIBLE);
                    nextgamesdialog();
                } else {
                    downok = "";
                    downnodata = "";
                    System.out.print("========###" + email);

                    if (exists("https://nithra.mobi/solliadi/" + email + "-filename.zip") == true) {
                        System.out.print("========zip ok");
                        checkmemory();
                    } else {
                        System.out.print("========not  ok");

                        Utils.mProgress.dismiss();
                        String date = sps.getString(Picture_Game_Hard.this, "date");
                        if (date.equals("0")) {
                            Cursor c;
                            c = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0' order by id limit 1");
                            c.moveToFirst();
                            if (c.getCount() != 0) {
                                next();
                            } else {
                                nextgamesdialog();
                            }
                        } else {
                            Cursor c;
                            c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
                            c.moveToFirst();
                            if (c.getCount() != 0) {
                                next();
                            } else {
                                nextgamesdialog();
                            }


                        }


                    }

                }

            }
        }.execute();
    }


    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


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
        double filesize = (length * 3) / 1204;


        if (length > 0) {

            hrSize = dec.format(filesize).concat(" KB");
            System.out.println("KB:--" + hrSize);

        }


        StatFs stat = new StatFs(getFilesDir().getPath());
        double sdAvailSize = (double) stat.getAvailableBlocks()
                * (double) stat.getBlockSize();

        double gigaAvailable = sdAvailSize / 1073741824;
        double megaaAvailable = sdAvailSize / (1024 * 1024);
        double kiloAvailable = sdAvailSize / 1024;
        if (sdAvailSize > 0) {
            hrSize = dec.format(kiloAvailable).concat(" KB");
            System.out.println("KB:--" + hrSize);
        }
        if (sdAvailSize > 0) {
            hrSize = dec.format(megaaAvailable).concat(" MB");
            System.out.println("MB:--" + hrSize);

        }

        if (sdAvailSize > 0) {

            hrSize = dec.format(gigaAvailable).concat(" GB");
            System.out.println("GB:--" + hrSize);

        }

        if (filesize <= kiloAvailable) {
            System.out.print("========downconform");
            //startdownload();
            startDownload();
            //newdown();
        } else {

            goappmanager();
        }


    }

    public void newdown() {

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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
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

    }


    public void goappmanager() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext(), android.R.style.Theme_Dialog);
        builder1.setMessage("No free space clean your storage");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(Intent.ACTION_MANAGE_PACKAGE_STORAGE);
                        startActivity(i);
                    }
                });
        builder1.setNegativeButton("Later",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // dialog.cancel();
                    }
                });
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

        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(Picture_Game_Hard.this);
                mProgressDialog.setMessage("படங்கள் பதிவிறக்கம் செய்யப்படுகிறது காத்திருக்கவும்.... ");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                if (!mProgressDialog.isShowing()) {
                    mProgressDialog.show();
                }

                // playy();

                return mProgressDialog;

            default:
                return null;
        }
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
                System.out.print("========siva ");
                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                final int fileLength = connection.getContentLength();

                File SDCardRoot = getFilesDir();

                File fol = new File(SDCardRoot + "/Nithra/solliadi/");
                if (!fol.exists()) {
                    fol.mkdirs();
                }

                File file = new File(SDCardRoot + "/Nithra/solliadi/", email + "-filename.zip");

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(file);

                byte data[] = new byte[4096];
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
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();


            }
            return null;

        }


        @Override
        protected void onPostExecute(String unused) {
            mProgressDialog.dismiss();

                if (unused != null && unused.equals("ERROR_DOW")) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setTitle("Network connection not available, please check it!");
                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            downloadFileAsync.isCancelled();
                            downloadFileAsync.cancel(true);
                            if (exists("https://nithra.mobi/solliadi/" + email + "-filename.zip") == true) {
                                System.out.print("========zip ok");
                                checkmemory();
                            }

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                } else {
                    Utils.mProgress.dismiss();
                    newdown();
                    String date = sps.getString(Picture_Game_Hard.this, "date");
                    if (date.equals("0")) {
                        Cursor c;
                        c = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0' order by id limit 1");
                        c.moveToFirst();
                        if (c.getCount() != 0) {
                            next();
                        } else {
                            nextgamesdialog();
                        }
                    } else {
                        Cursor c;
                        c = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
                        c.moveToFirst();
                        if (c.getCount() != 0) {
                            next();
                        } else {
                            nextgamesdialog();
                        }
                    }
                }
        }


        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
            /*if(!isNetworkAvailable()){
				downloadFileAsync.isCancelled();
				//downloadFileAsync.cancel(true);

			}*/
        }
    }


    public int unpackZip(String ZIP_FILE_NAME) throws IOException {

        /*InputStream is;
        ZipInputStream zis;
        try {

            String fullPath = getFilesDir() + "/Nithra/solliadi/";
            is = new FileInputStream(fullPath + ZIP_FILE_NAME);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;

            while ((ze = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;

                // zapis do souboru
                String filename = ze.getName();
                File f = new File(fullPath,filename);
                String canonicalPath = f.getCanonicalPath();
                System.out.println("can_prit"+canonicalPath);
                System.out.println("can_prit1"+fullPath);


                if (!canonicalPath.contains("/Nithra/solliadi/")) {
                    // SecurityException
                    Log.e("error","inside cannon");
                    return 0;
                }
                Log.e("error","inside cannon1");
                FileOutputStream fout = new FileOutputStream(fullPath
                        + filename);


                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
            File file = new File(fullPath + ZIP_FILE_NAME);
            file.delete();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        */

        File destDir = new File(getFilesDir() + "/Nithra/solliadi/");
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(getFilesDir() + "/Nithra/solliadi/"+ZIP_FILE_NAME));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = getFilesDir() + "/Nithra/solliadi/" + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        return 1;
    }
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[1024];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public void game_exit_ins_ad() {

        game_exit_ins = new MaxInterstitialAd(getResources().getString(R.string.Cat_Exit_Ins), this);
        game_exit_ins.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                openDialog_p.dismiss();
                game_exit_ins_ad();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                System.out.println("check error"+error);
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                System.out.println("check error2"+error);
            }
        });
        game_exit_ins.loadAd();

    }



    public void industrialload_game() {

        ins_game = new MaxInterstitialAd(getResources().getString(R.string.Viliyodu_Vilaiyadu_Ins), this);
        ins_game.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                dia_dismiss = 1;
                openDialog_s.dismiss();
                next();
                industrialload_game();
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
        ins_game.loadAd();

    }


    @Override
    protected void onStart() {
        super.onStart();
        //mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }


    }


    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "onConnected");
        //  updateUI();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: " + i);
        mGoogleApiClient.connect();
        // updateUI();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed");
        if (mIsResolving) {
            // The application is attempting to resolve this connection failure already.
            Log.d(TAG, "onConnectionFailed: already resolving");
            return;
        }

        if (mSignInClicked || mAutoStartSignIn) {
            mSignInClicked = false;
            mAutoStartSignIn = false;

            // Attempt to resolve the connection failure.
            Log.d(TAG, "onConnectionFailed: begin resolution.");
            mIsResolving = BaseGameUtils.resolveConnectionFailure(this, mGoogleApiClient,
                    connectionResult, RC_SIGN_IN, getString(R.string.signin_other_error));
        }

        //  updateUI();
    }

    /**
     * Update the Snapshot in the Saved Games service with new data.  Metadata is not affected,
     * however for your own application you will likely want to update metadata such as cover image,
     * played time, and description with each Snapshot update.  After update, the UI will
     * be cleared.
     */
    private void savedGamesUpdate() {
        final String snapshotName = "Snapshot-" + String.valueOf(APP_STATE_KEY);
        final boolean createIfMissing = true;

        // Use the data from the EditText as the new Snapshot data.
        final byte[] data = getData().getBytes();


        //   final long playedTimeMillis = 60 * 60 * 1000;
        //final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        AsyncTask<Void, Void, Boolean> updateTask = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                // showProgressDialog("Updating Saved Game");
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                Snapshots.OpenSnapshotResult open = Games.Snapshots.open(
                        mGoogleApiClient, snapshotName, createIfMissing).await();

                if (!open.getStatus().isSuccess()) {
                    Log.w(TAG, "Could not open Snapshot for update.");
                    return false;
                }

                // Change data but leave existing metadata
                Snapshot snapshot = open.getSnapshot();
                snapshot.getSnapshotContents().writeBytes(data);

             /*   SnapshotMetadataChange metadataChange = new SnapshotMetadataChange.Builder()
                        .fromMetadata(snapshot.getMetadata())
                        .setDescription("Testing Saved Games")
                        .setPlayedTimeMillis(playedTimeMillis)
                        .build();*/

                Snapshots.CommitSnapshotResult commit = Games.Snapshots.commitAndClose(
                        mGoogleApiClient, snapshot, SnapshotMetadataChange.EMPTY_CHANGE).await();

                if (!commit.getStatus().isSuccess()) {
                    Log.w(TAG, "Failed to commit Snapshot.");
                    return false;
                }

                // No failures
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    // displayMessage(getString(R.string.saved_games_update_success), false);
                } else {
                    // displayMessage(getString(R.string.saved_games_update_failure), true);
                }
                btn_str = "invite";
                // dismissProgressDialog();
                //clearDataUI();
            }
        };
        updateTask.execute();
    }

    /**
     * Get the data from the EditText.
     *
     * @return the String in the EditText, or "" if empty.
     */
    private String getData() {


        Cursor g2 = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='1' order by id desc limit 1");
        Cursor g3 = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='1' order by id desc limit 1");
        Cursor g4 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='1' order by id desc limit 1");

        g2.moveToFirst();
        g3.moveToFirst();
        g4.moveToFirst();

        Cursor c1 = myDbHelper.getQry("select * from score");
        c1.moveToFirst();


        int a2, a3, a4;
        String b2, b3, b4;
        if (g2.getCount() == 0) {
            // a2=00;
            b2 = "no";
        } else {
            // a2 = g2.getInt(g2.getColumnIndex("levelid"));
            b2 = "" + String.valueOf(g2.getInt(g2.getColumnIndex("levelid")));
        }
        if (g3.getCount() == 0) {
            //  a3=00;
            b3 = "no";
        } else {
            b3 = "" + String.valueOf(g3.getInt(g3.getColumnIndex("levelid")));
            //  a3 = g3.getInt(g3.getColumnIndex("levelid"));

        }
        if (g4.getCount() == 0) {
            // a4=00;
            b4 = "no";
        } else {
            b4 = "" + String.valueOf(g4.getInt(g4.getColumnIndex("levelid")));
            //  a4 = g4.getInt(g4.getColumnIndex("levelid"));

        }


        String upload = String.valueOf(wordid) + "#" + String.valueOf(b2) + "#" + String.valueOf(b3) + "#" + String.valueOf(b4) + "#" + String.valueOf(c1.getInt(c1.getColumnIndex("coins")) + "#" + String.valueOf(c1.getInt(c1.getColumnIndex("l_points"))));


        return upload;
    }


    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);
        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Picture_Game_Hard.this, "date");
        if (date.equals("0")) {
            next_game.setText("படம் பார்த்து கண்டுபிடி தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("தினசரி  படம் பார்த்து கண்டுபிடி புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");

        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Clue_Game_Hard.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Picture_Game_Hard.this, "game_area", "on");
                    finish();
                }
                sps.putString(Picture_Game_Hard.this, "date", "0");
            }
        });


        Cursor ct;
        ct = myDbHelper.getQry("select * from maintable where isfinish='0' order by id limit 1");
        ct.moveToFirst();
        if (ct.getCount() != 0) {
            Cursor c;
            c = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='0' order by id limit 1");
            c.moveToFirst();
            if (c.getCount() != 0) {
                c_game.setVisibility(View.VISIBLE);
            }
            Cursor c2;
            c2 = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='0' order by id limit 1");
            c2.moveToFirst();
            if (c2.getCount() != 0) {
                s_game.setVisibility(View.VISIBLE);
            }
            Cursor c3;
            c3 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0' order by id limit 1");
            c3.moveToFirst();
            if (c3.getCount() != 0) {
                w_game.setVisibility(View.VISIBLE);
            }
            Cursor c4;
            c4 = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0' order by id limit 1");
            c4.moveToFirst();
            if (c4.getCount() != 0) {
                p_game.setVisibility(View.VISIBLE);
            }
        } else {

            next_game.setText("புதிய பதிவுகள் ஏதும் இல்லை.விரைவில் வினாக்கள் பதிவேற்றம் செய்யப்படும்.");
            exit.setText("சரி ");
            exit.setVisibility(View.VISIBLE);
        }

        TextView odd_man_out = (TextView) openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = (TextView) openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Odd_man_out.class);
                startActivity(i);
            }
        });
        Cursor cts;
        cts = newhelper.getQry("select * from newmaintable where isfinish='0' order by id limit 1");
        cts.moveToFirst();
        if (cts.getCount() != 0) {
            Cursor sc;
            sc = newhelper.getQry("select * from newmaintable where gameid='5' and isfinish='0' order by id limit 1");
            sc.moveToFirst();
            if (sc.getCount() != 0) {
                odd_man_out.setVisibility(View.VISIBLE);
            }
            Cursor scs;
            scs = newhelper.getQry("select * from newmaintable where gameid='6' and isfinish='0' order by id limit 1");
            scs.moveToFirst();
            if (scs.getCount() != 0) {
                matchword.setVisibility(View.VISIBLE);
            }
        }


        TextView opposite_word = (TextView) openDialog.findViewById(R.id.opposite_word);
        TextView ote_to_tamil = (TextView) openDialog.findViewById(R.id.ote_to_tamil);
        opposite_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Ote_to_Tamil.class);
                startActivity(i);
            }
        });

        Cursor ctd;
        ctd = newhelper2.getQry("select * from newmaintable2 where isfinish='0' order by id limit 1");
        ctd.moveToFirst();
        if (ctd.getCount() != 0) {
            Cursor scd;
            scd = newhelper2.getQry("select * from newmaintable2 where gameid='7' and isfinish='0' order by id limit 1");
            scd.moveToFirst();
            if (scd.getCount() != 0) {
                opposite_word.setVisibility(View.VISIBLE);
            }
            Cursor scdd;
            scdd = newhelper2.getQry("select * from newmaintable2 where gameid='8' and isfinish='0' order by id limit 1");
            scdd.moveToFirst();
            if (scdd.getCount() != 0) {
                ote_to_tamil.setVisibility(View.VISIBLE);
            }
        }

        TextView seerpaduthu = (TextView) openDialog.findViewById(R.id.seerpaduthu);
        TextView puthir = (TextView) openDialog.findViewById(R.id.puthir);
        TextView tirukural = (TextView) openDialog.findViewById(R.id.tirukural);
        TextView pilaithiruthu = (TextView) openDialog.findViewById(R.id.pilaithiruthu);


        if (sps.getString(context, "newgame_notification").equals("start")) {

            Cursor ctds;
            ctds = newhelper3.getQry("select * from right_order where isfinish='0' order by id limit 1");
            ctds.moveToFirst();
            if (ctds.getCount() != 0) {
                Cursor scds;
                scds = newhelper3.getQry("select * from right_order where gameid='9' and isfinish='0' order by id limit 1");
                scds.moveToFirst();
                if (scds.getCount() != 0) {
                    seerpaduthu.setVisibility(View.VISIBLE);
                }
                Cursor scdds;
                scdds = newhelper3.getQry("select * from right_order where gameid='10' and isfinish='0' order by id limit 1");
                scdds.moveToFirst();
                if (scdds.getCount() != 0) {
                    puthir.setVisibility(View.VISIBLE);
                }

                Cursor c3s;
                c3s = newhelper3.getQry("select * from right_order where gameid='11' and isfinish='0' order by id limit 1");
                c3s.moveToFirst();
                if (c3s.getCount() != 0) {
                    pilaithiruthu.setVisibility(View.VISIBLE);
                }
                Cursor c4s;
                c4s = newhelper3.getQry("select * from right_order where gameid='12' and isfinish='0' order by id limit 1");
                c4s.moveToFirst();
                if (c4s.getCount() != 0) {
                    tirukural.setVisibility(View.VISIBLE);
                }
            }

        }


        seerpaduthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Riddle_game.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, WordError_correction.class);
                startActivity(i);
            }
        });

        TextView fill_in_blanks = (TextView) openDialog.findViewById(R.id.fill_in_blanks);
        TextView eng_to_tamil = (TextView) openDialog.findViewById(R.id.eng_to_tamil);

        Cursor scds;
        scds = newhelper4.getQry("select * from newgamesdb4 where gameid='13' and isfinish='0' order by id limit 1");
        scds.moveToFirst();
        if (scds.getCount() != 0) {
            fill_in_blanks.setVisibility(View.VISIBLE);
        }

        Cursor scdss;
        scdss = newhelper4.getQry("select * from newgamesdb4 where gameid='14' and isfinish='0' order by id limit 1");
        scdss.moveToFirst();
        if (scdss.getCount() != 0) {
            eng_to_tamil.setVisibility(View.VISIBLE);
        }

        fill_in_blanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });
        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });

        TextView quiz = (TextView) openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = (TextView) openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = (TextView) openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(context);
        Cursor cn28ws = newhelper5.getQry("select * from newgames5 where gameid='15' and isfinish='0'");
        cn28ws.moveToFirst();
        if (cn28ws.getCount() != 0) {
            match_words.setVisibility(View.VISIBLE);
        }
        Cursor cn27ws = newhelper5.getQry("select * from newgames5 where gameid='16' and isfinish='0'");
        cn27ws.moveToFirst();
        if (cn27ws.getCount() != 0) {
            find_words_from_pictures.setVisibility(View.VISIBLE);
        }

        Cursor cn22 = newhelper5.getQry("select * from newgames5 where gameid='17' and isfinish='0'");
        cn22.moveToFirst();
        if (cn22.getCount() != 0) {
            quiz.setVisibility(View.VISIBLE);
        }

        match_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Find_words_from_picture.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Quiz_Game.class);
                startActivity(i);
            }
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Picture_Game_Hard.this);
        TextView jamble_words = (TextView) openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        if (jmp != null && jmp.moveToFirst()) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Jamble_word_game.class);
                startActivity(i);
            }
        });
        TextView missing_words = (TextView) openDialog.findViewById(R.id.missing_words);
        Cursor jmps;
        jmps = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='0' order by id limit 1");
        if (jmps != null && jmps.moveToFirst()) {
            missing_words.setVisibility(View.VISIBLE);
        }

        missing_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Missing_Words.class);
                startActivity(i);
            }
        });
        TextView six_differences = (TextView) openDialog.findViewById(R.id.six_differences);
        Cursor dif;
        dif = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='0' order by id limit 1");
        if (dif != null && dif.moveToFirst()) {
            six_differences.setVisibility(View.VISIBLE);
        }
        six_differences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Picture_Game_Hard.this, "date", "0");
                Intent i = new Intent(Picture_Game_Hard.this, Find_difference_between_pictures.class);
                startActivity(i);
            }
        });
        openDialog.show();

        openDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (main_act.equals("")) {

                    finish();
                    //     openDialog_s.dismiss();
                    Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Picture_Game_Hard.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                sps.putString(Picture_Game_Hard.this, "date", "0");

              /*  finish();
                openDialog.dismiss();
                Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
    }

    public void missingimage() {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                            /*.setTitle("Delete entry")*/

        alertDialogBuilder.setMessage("படங்கள் இல்லை பதிவிறக்கம் செய்யவேண்டுமா? ")
                .setPositiveButton("ஆம்", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (Utils.isNetworkAvailable(getApplicationContext())) {

                            String date = sps.getString(Picture_Game_Hard.this, "date");
                            if (date.equals("0")) {
                                Cursor cursor1 = myDbHelper.getQry("SELECT id FROM maintable order by id desc");
                                cursor1.moveToFirst();
                                String lastid = null;
                                if (cursor1.getCount() != 0) {
                                    lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                                }

                                downpic(id, lastid);
                            } else {
                                Cursor cursor1 = myDbHelper.getQry("SELECT id FROM dailytest order by id desc");
                                cursor1.moveToFirst();
                                String lastid = null;
                                if (cursor1.getCount() != 0) {
                                    lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                                }

                                downpic(id, lastid);
                            }

                            dialog.dismiss();
                        } else {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                         /*   .setTitle("Delete entry")*/
                            alertDialogBuilder.setCancelable(false);
                            alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                                    .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 2);
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // do nothing

                                            String date = sps.getString(Picture_Game_Hard.this, "date");
                                            if (date.equals("0")) {
                                                if (main_act.equals("")) {
                                                    finish();
                                                    Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                                    startActivity(i);
                                                } else {
                                                    finish();
                                                }
                                            } else {
                                                finish();
                                                Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                                startActivity(i);
                                            }
                                            /*Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                            startActivity(i);*/
                                            dialog.dismiss();
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }


                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();



       /* ///User Premission Showing

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {

            if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                    (ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED)) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                            *//*.setTitle("Delete entry")*//*
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("படங்கள் இல்லை பதிவிறக்கம் செய்யவேண்டுமா? ")
                        .setPositiveButton("ஆம்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                if (Utils.isNetworkAvailable(getApplicationContext())) {

                                    String date=sps.getString(Picture_Game_Hard.this,"date");
                                    if(date.equals("0")){
                                        Cursor cursor1 = myDbHelper.getQry("SELECT id FROM maintable order by id desc", null);
                                        cursor1.moveToFirst();
                                        String lastid = null;
                                        if (cursor1.getCount() != 0) {
                                            lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                                        }

                                        downpic(id,lastid);
                                    }else
                                    {
                                        Cursor cursor1 = myDbHelper.getQry("SELECT id FROM dailytest order by id desc", null);
                                        cursor1.moveToFirst();
                                        String lastid = null;
                                        if (cursor1.getCount() != 0) {
                                            lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                                        }

                                        downpic(id,lastid);
                                    }

                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(Picture_Game_Hard.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                                }






                            }
                        })
                        .setNegativeButton("இல்லை ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }else {
                //  First_register("yes");
                w_head.setVisibility(View.INVISIBLE);
                AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
                alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய பின்வரும் permission-களை allow செய்யவேண்டும்");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "சரி ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                                        (ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED)) {
                                    ActivityCompat.requestPermissions(Picture_Game_Hard.this,
                                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.GET_ACCOUNTS}, 152);


                                }
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }





        }else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                            *//*.setTitle("Delete entry")*//*
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("படங்கள் இல்லை பதிவிறக்கம் செய்யவேண்டுமா? ")
                    .setPositiveButton("ஆம்", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (Utils.isNetworkAvailable(getApplicationContext())) {

                                String date=sps.getString(Picture_Game_Hard.this,"date");
                                if(date.equals("0")){
                                    Cursor cursor1 = myDbHelper.getQry("SELECT id FROM maintable order by id desc", null);
                                    cursor1.moveToFirst();
                                    String lastid = null;
                                    if (cursor1.getCount() != 0) {
                                        lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                                    }

                                    downpic(id,lastid);
                                }else
                                {
                                    Cursor cursor1 = myDbHelper.getQry("SELECT id FROM dailytest order by id desc", null);
                                    cursor1.moveToFirst();
                                    String lastid = null;
                                    if (cursor1.getCount() != 0) {
                                        lastid = cursor1.getString(cursor1.getColumnIndex("id"));
                                    }

                                    downpic(id,lastid);
                                }

                                dialog.dismiss();
                            }else {
                                Toast.makeText(Picture_Game_Hard.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            }






                        }
                    })
                    .setNegativeButton("இல்லை ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();*/


    }


    public void downpic(final String first, final String last) {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        w_head.setVisibility(View.INVISIBLE);
        Utils.mProgress(Picture_Game_Hard.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {


                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("firstid", first));
                nameValuePairs.add(new BasicNameValuePair("lastid", last));
                String date = sps.getString(Picture_Game_Hard.this, "date");
                if (date.equals("0")) {
                    nameValuePairs.add(new BasicNameValuePair("mode", "regular"));
                } else {
                    nameValuePairs.add(new BasicNameValuePair("mode", "daily"));
                }
                nameValuePairs.add(new BasicNameValuePair("email", email));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/missingdata.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in https connection" + e.toString());
                }
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();

                    System.out.print("Result============" + result);

                } catch (Exception e) {
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                checkmemory();

            }
        }.execute();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 150) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Picture_Game_Hard.this, "permission", 1);
                downloaddata_daily();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Picture_Game_Hard.this, "permission", 2);
                        String date = sps.getString(Picture_Game_Hard.this, "date");

                        if (date.equals("0")) {
                            finish();
                        } else {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Gamelist.class);
                            startActivity(i);
                        }

                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Picture_Game_Hard.this, "permission", 0);
                        String date = sps.getString(Picture_Game_Hard.this, "date");

                        if (date.equals("0")) {
                            finish();
                        } else {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Gamelist.class);
                            startActivity(i);
                        }
                    }
                }
            }

        }
        if (requestCode == 151) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Picture_Game_Hard.this, "permission", 1);
                downloaddata_regular();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Picture_Game_Hard.this, "permission", 2);
                        String date = sps.getString(Picture_Game_Hard.this, "date");

                        if (date.equals("0")) {
                            finish();
                        } else {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Gamelist.class);
                            startActivity(i);
                        }
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Picture_Game_Hard.this, "permission", 0);
                        String date = sps.getString(Picture_Game_Hard.this, "date");

                        if (date.equals("0")) {
                            finish();
                        } else {
                            finish();
                            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Gamelist.class);
                            startActivity(i);
                        }
                    }
                }
            }
        }
        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Picture_Game_Hard.this, "permission", 1);
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
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Picture_Game_Hard.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Picture_Game_Hard.this, "permission", 0);
                    }
                }
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
        if (str_month1.length() == 1) {
            str_month1 = "0" + str_month1;
        }

        String str_day1 = "" + cur_day1;
        if (str_day1.length() == 1) {
            str_day1 = "0" + str_day1;
        }
        final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

        myDbHelper.executeSql("create table if not exists userdetail(id integer,name varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");

        if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {
            System.out.println("=======inside");
            ///Game states for reward
            //  db1.execSQL("create table if not exists userdata_d(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
            myDbHelper.executeSql("create table if not exists userdata_r(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,type varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
            ///Game states for reward

            String mobileno = null;
            Cursor sc3 = myDbHelper.getQry("select * from userdetail");
            sc3.moveToFirst();
            if (sc3.getCount() != 0) {
                mobileno = sc3.getString(sc3.getColumnIndex("phno"));

            }

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
                System.out.println("=======inserting2" + str_date1);
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

        String dates = sps.getString(Picture_Game_Hard.this, "date");
        if (dates.equals("0")) {
            retype = "r";
        } else {
            retype = "d";
        }
 /*       Calendar calendar3 = Calendar.getInstance();
        int cur_year1 = calendar3.get(Calendar.YEAR);
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        String str_month1 = "" + (cur_month1 + 1);
        if (str_month1.length() == 1) {
            str_month1 = "0" + str_month1;
        }

        String str_day1 = ""+cur_day1;
        if(str_day1.length()==1){
            str_day1 = "0"+str_day1;
        }
        final String str_date1 = cur_year1+"-"+str_month1+"-"+str_day1;*/


        if (retype.equals("r")) {
            if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {

                System.out.println("################regular on" + retype);
                Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                cn.moveToFirst();
                int cns = cn.getInt(cn.getColumnIndex("score"));
                int time = cn.getInt(cn.getColumnIndex("playtime"));
                int gm1 = cn.getInt(cn.getColumnIndex("gm1"));
                int cnse = 0;
                long ptime;
                if (vs == 1) {
                    if (f == 0) {
                        cnse = cns + 50;
                    } else {
                        cnse = cns + 25;
                    }
                } else {
                    cnse = cns;
                }

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


                myDbHelper.executeSql("UPDATE userdata_r SET score='" + cnse + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                myDbHelper.executeSql("UPDATE userdata_r SET playtime='" + ptime + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                myDbHelper.executeSql("UPDATE userdata_r SET gm1='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");

            }
        } else if (retype.equals("d")) {
            if (sps.getString(Picture_Game_Hard.this, "complite_reg").equals("yes")) {

                if (sps.getString(Picture_Game_Hard.this, "yes_reward").equals("yes")) {


                    System.out.println("################daily on" + retype);
                    System.out.println("=====print ok");
                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + dates + "'");
                    cn.moveToFirst();
                    int cns = cn.getInt(cn.getColumnIndex("score"));
                    int time = cn.getInt(cn.getColumnIndex("playtime"));
                    int gm1 = cn.getInt(cn.getColumnIndex("gm1"));
                    int cnse = 0;
                    long ptime;
                    if (vs == 1) {
                        if (f == 0) {
                            cnse = cns + 50;
                        } else {
                            cnse = cns + 25;
                        }
                    } else {
                        cnse = cns;
                    }
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


                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + cnse + "' where type ='" + retype + "'and date='" + dates + "'");
                    myDbHelper.executeSql("UPDATE userdata_r SET playtime='" + ptime + "' where type ='" + retype + "'and date='" + dates + "'");
                    myDbHelper.executeSql("UPDATE userdata_r SET gm1='" + gm1s + "' where type ='" + retype + "'and date='" + dates + "'");


                } else {
                    System.out.println("=====print not ok");
                }


            }


        }


    }

    //*********************reward videos process 3***********************


    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Picture_Game_Hard.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }


    //reward videos***********************//

    public void vidcoinearn() {
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea = ea + setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
            TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            final int skx = cfx.getInt(cfx.getColumnIndex("coins"));
            int spx = skx + mCoinCount;
            final String aStringx = Integer.toString(spx);


            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score.setText("" + skx);
                    openDialog.dismiss();
                    //mCoinCount = 0;
                }
            });

            openDialog.show();
        }

    }

    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndex("coins"));
     /*   int spx = skx + a;
        final String aStringx = Integer.toString(spx);*/
        b_scores.setText("" + a);


        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndex("coins"));
     /*   int spx = skx + a;
        final String aStringx = Integer.toString(spx);*/
        b_scores.setText("" + a);


        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttscores.setText("" + skx);
                score.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }

    public void downloaddata_daily() {
        if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {
            Cursor c1 = myDbHelper.getQry("select id from dailytest order by id DESC");
            c1.moveToFirst();


            System.out.print("Count====" + c1.getCount());


            if (c1.getCount() != 0) {


                //c1.getString(c1.getColumnIndex("id"));

                System.out.print("Last ID====" + c1.getString(c1.getColumnIndex("id")));

                downloadcheck("" + c1.getString(c1.getColumnIndex("id")), "daily");

            } else {
                System.out.print("else====");

                downloadcheck("0", "daily");
            }


        } else {
            NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
            native_banner_ad_container.setVisibility(View.INVISIBLE);
            w_head.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                         /*   .setTitle("Delete entry")*/
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                    .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                                          /*  try {
                                                startActivityForResult(new Intent(
                                                        Settings.ACTION_WIRELESS_SETTINGS), 0);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                startActivityForResult(new Intent(
                                                        Settings.ACTION_SETTINGS), 0);
                                            }*/
                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                            sps.putInt(Picture_Game_Hard.this, "goto_sett", 1);


                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing

                            String date = sps.getString(Picture_Game_Hard.this, "date");
                            if (date.equals("0")) {
                                backexitnet();
                            } else {
                                backexitnet();
                            }
                           /* Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                            startActivity(i);*/
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    public void downloaddata_regular() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Picture_Game_Hard.this)) {
                    Cursor c1 = myDbHelper.getQry("select id from maintable order by id DESC");
                    c1.moveToFirst();
                    System.out.print("Count====" + c1.getCount());

                    if (c1.getCount() != 0) {


                        //c1.getString(c1.getColumnIndex("id"));

                        System.out.print("Last ID====" + c1.getString(c1.getColumnIndex("id")));

                        downloadcheck("" + c1.getString(c1.getColumnIndex("id")), "ord");

                    } else {
                        downloadcheck("0", "ord");
                    }
                } else {
                    NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                    native_banner_ad_container.setVisibility(View.INVISIBLE);
                    w_head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Picture_Game_Hard.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                            .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                    sps.putInt(Picture_Game_Hard.this, "goto_sett", 1);


                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    sps.putString(Picture_Game_Hard.this, "game_area", "on");
                                    String date = sps.getString(Picture_Game_Hard.this, "date");
                                    if (date.equals("0")) {
                                        backexitnet();
                                    } else {
                                        backexitnet();
                                    }
                                    /*Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                    startActivity(i);*/
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });
        alertDialogBuilder.setPositiveButton("இல்லை ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                sps.putString(Picture_Game_Hard.this, "game_area", "on");
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void permission(final String a) {
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.stop();
        String date = sps.getString(Picture_Game_Hard.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

            myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                helpshare(a);
            } else {
                if (sps.getString(Picture_Game_Hard.this, "permission_grand").equals("")) {
                    sps.putString(Picture_Game_Hard.this, "permission_grand", "yes");
                    //  First_register("yes");
                    AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
                    alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர  பின்வரும் permission-யை  allow செய்யவேண்டும்");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                                    } else {
                                        helpshare(a);
                                    }
                                }
                            });

                    alertDialog.show();

                } else {
                    if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(Picture_Game_Hard.this, "permission") == 2) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
                            alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர settingsல் உள்ள permission-யை allow செய்யவேண்டும்");
                            alertDialog.setCancelable(false);
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            Intent intent = new Intent();
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                            intent.setData(uri);
                                            getApplicationContext().startActivity(intent);
                                        }
                                    });

                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            String date = sps.getString(Picture_Game_Hard.this, "date");
                                            int pos;
                                            Cursor cs;
                                            long dscore = 0;
                                            int noofclue = 0;
                                            if (date.equals("0")) {
                                                pos = 1;
                                                cs = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "' and levelid='" + wordid + "'");
                                                cs.moveToFirst();
                                                if (cs.getCount() != 0) {
                                                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                                                    noofclue = cs.getInt(cs.getColumnIndex("noclue"));
                                                }
                                            } else {
                                                pos = 2;
                                                cs = myDbHelper.getQry("select * from dailytest where gameid='" + gameid + "' and levelid='" + wordid + "'");
                                                cs.moveToFirst();
                                                if (cs.getCount() != 0) {

                                                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                                                    noofclue = cs.getInt(cs.getColumnIndex("noclue"));
                                                }
                                            }

                                            if (noofclue == 1) {
                                                f = 1;
                                            }
                                            //  long wt=sps.getInt(Word_Game_Hard.this,"old_time_start");
                                            focus.setBase(SystemClock.elapsedRealtime() + dscore);
                                            focus.start();
                                            dialog.dismiss();
                                        }
                                    });


                            alertDialog.show();
                        } else {
                            if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                            } else {
                                helpshare(a);
                            }
                        }
                    } else {
                        if ((ContextCompat.checkSelfPermission(Picture_Game_Hard.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(Picture_Game_Hard.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
                        } else {
                            helpshare(a);
                        }
                    }
                }
            }

        } else {
            helpshare(a);
        }*/
        helpshare(a);
    }


    //*** In Adapter **

    public void ins_app(final Context context, View view1, int vall) {
        TextView titt = (TextView) view1.findViewById(R.id.txtlist);
        ImageView logo = (ImageView) view1.findViewById(R.id.imageview);
        final Button inss = (Button) view1.findViewById(R.id.btn_cont);
        if (vall == 1 || vall == 0) {
            if (!appInstalledOrNot(context, "nithra.tamilcalender")) {
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
                titt.setText("Nithra Apps");
                inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
                logo.setImageResource(R.drawable.nithralogo);
            }
        } else if (vall == 2) {
            if (!appInstalledOrNot(context, "com.bharathdictionary")) {
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
        } else if (vall == 3) {
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
        } else if (vall == 4) {
            if (!appInstalledOrNot(context, "nithra.samayalkurippu")) {
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
        } else if (vall == 5) {
            if (!appInstalledOrNot(context, "nithra.jobs.career.placement")) {
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
        } else if (vall == 6) {
            if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
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
        } else {
            if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
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
        }

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(context)) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inss.getTag().toString())));
                } else {
                    Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
        });

        inss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(context)) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(view.getTag().toString())));
                } else {
                    Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
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

    //*** In ad area **

    public void showcase_dismiss() {
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sp.getString(Picture_Game_Hard.this, "showcase_dismiss_p").equals("")) {
                    showcase_dismiss();
                } else {
                    sps.putString(context, "pic_time_start", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }

            }
        }, 800);
    }

    private void price_update() {
        ////////////////Prize//////////////////
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;
        long mill_sec = TimeUnit.NANOSECONDS.toSeconds(timeElapsed);
        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;



   /*     Toast.makeText(this, "minutes"+minutes, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "seconds"+seconds, Toast.LENGTH_SHORT).show();*/
        System.out.println("########################-------minutes" + minutes);
        System.out.println("########################-------seconds" + seconds);
        System.out.println("########################-------String" + timeElapsed);

       /* String.format("%02d min, %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
                TimeUnit.MILLISECONDS.toSeconds(timeElapsed) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))
        );*/
        String date = sps.getString(Picture_Game_Hard.this, "date");

        if (date.equals("0")) {
            //  Toast.makeText(this, ""+f_sec, Toast.LENGTH_SHORT).show();
            if (timeElapsed <= 91300) {
                if (f == 0) {
                    prize_data_update(Picture_Game_Hard.this, 75);
                }
                if (f == 1) {
                    prize_data_update(Picture_Game_Hard.this, 25);
                }
            } else if (timeElapsed > 91300) {
                if (f == 0) {
                    prize_data_update(Picture_Game_Hard.this, 50);
                }
                if (f == 1) {
                    prize_data_update(Picture_Game_Hard.this, 25);
                }
            }
        } else {
            //Toast.makeText(this, ""+f_sec, Toast.LENGTH_SHORT).show();
            if (timeElapsed <= 91300) {
                if (f == 0) {
                    prize_data_update(Picture_Game_Hard.this, 100);
                }
                if (f == 1) {
                    prize_data_update(Picture_Game_Hard.this, 50);
                }
            } else if (timeElapsed > 91300) {
                if (f == 0) {
                    prize_data_update(Picture_Game_Hard.this, 75);
                }
                if (f == 1) {
                    prize_data_update(Picture_Game_Hard.this, 50);
                }
            }
        }
        ////////////////Prize//////////////////
    }

    public void settingpermission() {
        if (sps.getInt(Picture_Game_Hard.this, "permission") == 2) {
            AlertDialog alertDialog = new AlertDialog.Builder(Picture_Game_Hard.this).create();
            alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய Settings-ல் உள்ள permission-யை allow செய்யவேண்டும்");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings ",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                            intent.setData(uri);
                            getApplicationContext().startActivity(intent);
                            setting_access = 1;

                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sps.putString(Picture_Game_Hard.this, "game_area", "on");
                            String date = sps.getString(Picture_Game_Hard.this, "date");
                            if (date.equals("0")) {
                                if (main_act.equals("")) {
                                    finish();
                                    Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else {
                                    finish();
                                }
                            } else {
                                finish();
                                Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                                startActivity(i);
                            }
                            dialog.dismiss();
                        }
                    });


            alertDialog.show();
        }
    }


    public void rewarded_ad() {
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
                rewarded_ad();
                if (reward_status == 1) {
                    if (extra_coin_s == 0) {
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndex("coins"));
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
                rewardedAd.loadAd();


            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                /*retryAttempt++;
                long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

                new Handler().postDelayed( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        rewardedAd.loadAd();
                    }
                }, delayMillis );*/
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                rewardedAd.loadAd();
            }
        });
        rewardedAd.loadAd();
    }


}






































