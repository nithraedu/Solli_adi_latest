package nithra.tamil.word.game.solliadi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/*import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;*/
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;








import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import de.hdodenhof.circleimageview.CircleImageView;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.adutils.Ad_NativieUtils;
import nithra.tamil.word.game.solliadi.adutils.GameExitUtils;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.fb_addload_score_screen;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native_Puthayal_Sorkal_Native_Banner;

public class Opposite_word extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Download_completed {

    int fb_reward = 0;
    //RewardedVideoAd rewardedVideoAd;

    private MaxRewardedAd rewardedAd;
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

    // Facebook variable starts
    static int vs = 0;
    static int f;
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";

    private PendingAction pendingAction = PendingAction.NONE;

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void download_completed(String status) {
        System.out.println("#############################status"+status);
        if (status.equals("nodata")){
            nextgamesdialog();
        }else {
            next();
        }
    }


    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }
    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }
    String btn_str = "";
    // facebook variable ends
    TextView s_word_number, s_score_edit, hint, p_coins, p_coins_red, earncoin, to_no, discription;
    TextView bt1, bt2, bt3, bt4, bt5, bt6;
    TextView bts1, bts2, bts3, bts4;
    Chronometer focus;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    String gameid = "7";
    int u_id, questionid;
    String question, answer;
    RelativeLayout sixcat, fourcat, below, bottom;
    SharedPreference sps = new SharedPreference();

    Animation clickzoom, clickzoom2;
    int ans_position;

    int q_type = 0;
    int min = 1;
    int max = 3;
    int random = 0;
    int k = 1;
    long ttstop;
    SoundPool spz1, spz2, spz3, spz4;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    int e2;
    RelativeLayout head;
    TextView p_facebook, p_watts_app, p_gplues;
    Dialog openDialog_earncoin;
    String retype = "s";
    Context context = this;
    static int rvo = 0;
    static int mCoinCount=20;
    TextView next_continue;
    TextView ttscores;
    public static final String TAG = "SavedGames";
    Typeface typ, tyr;
    int f_sec;
    int r = 0;
    int s = 0;
    PopupWindow popupWindow;
    TextView toggleButton;
    TextView p_setting;
    LinearLayout qwt;
    FrameLayout frame_layout2;
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


    private MaxInterstitialAd ins_game,game_exit_ins;

    /////////native advance////////////
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-4267540560263635/9323490091";
    private static final String ADMOB_APP_ID = "ca-app-pub-4267540560263635~3166935503";

    Dialog openDialog_s, openDialog_p, openDialog_odd_man;
    ;
    RelativeLayout adsicon, adsicon2;
    CircleImageView ads_logo, ads_logo2;
    LinearLayout helpshare_layout;
    /////////native advance////////////
    /////////Native_Top_Advanced////////////
    private static final String ADMOB_AD_UNIT_ID_Top = "ca-app-pub-4267540560263635/2303543680";
    /////////Native_Top_Advanced////////////
    /////////Native_BackPress_Advanced////////////
    private static final String ADMOB_AD_UNIT_ID_back = "ca-app-pub-4267540560263635/3321111884";
    /////////Native_BackPress_Advanced////////////
    TextView word1, word2, word3, word4, word5, word6, ans, dis, close;
    Animation myFadeInAnimation;
    int share_name = 0;
    LinearLayout ads_layout;
    public static FrameLayout add, add2, add3;
    static SharedPreference spd = new SharedPreference();
    int loadaddcontent = 0;
    FrameLayout ads_layout_bottom;
    TextView question_txt;
    int rdvalu = 2;
    int daily_start = 0;


    public static LinearLayout add_e;
    public static LinearLayout add_sc;

    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper4 newhelper4;

    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea=0;
    Dialog openDialog;
    int minmumd = 1;
    int maximumd = 4;
    int randomnod;
    int setval_vid;
    TextView coin_value;
    FirebaseAnalytics mFirebaseAnalytics;
    int dia_dismiss=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opposite_word);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);


        if (sps.getString(Opposite_word.this,"new_user_db").equals("")){

        }else {
            if (sps.getString(Opposite_word.this,"new_user_db").equals("on")){
                sps.putString(Opposite_word.this,"db_name_start","Tamil_Game2.db");
                Commen_string.dbs_name="Tamil_Game2.db";
            }else {
                sps.putString(Opposite_word.this,"db_name_start","Solli_Adi");
                Commen_string.dbs_name="Solli_Adi";
            }

        }

        myDbHelper = new DataBaseHelper(context);
        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);


        /*String gid = "7";
        String qid = "";
        for (int i = 0; i<=999; i++){
            if (qid.equals("")){
                qid = "" +i;
            } else {
                qid = qid + "," + i;
            }
        }
        System.out.println("---qid : " +qid);
        System.out.println("---qid : " + "UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='16'");
        newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='7'");
*/
    /*    exdb = myDbHelper.getReadableDatabase();
        dbn = newhelper.getReadableDatabase();
        dbs = newhelper2.getReadableDatabase();
        dbn2 = newhelper3.getReadableDatabase();*/

        //uiHelper = new UiLifecycleHelper(this, callback);




        if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(context);
          /* */
        }


        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        //reward(Opposite_word.this);
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

        find();
        // next();
        //loads_ads_banner();
        /*adds = (LinearLayout) findViewById(R.id.ads_lay);
        if (sps.getInt(context, "purchase_ads") == 0) {
        if (Utils.isNetworkAvailable(Opposite_word.this)) {

        Ad_NativieUtils.load_add_facebook(this,getResources().getString(R.string.Puthayal_Sorkal_Native_Banner_new),adds);
        }else {
            adds.setVisibility(View.GONE);
        }
        }else{
            adds.setVisibility(View.GONE);
        }*/


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
                sps.putString(Opposite_word.this, "date", message);
                next();
            } else {
                sps.putString(Opposite_word.this, "date", "0");
                next();
            }
        } else {
            sps.putString(Opposite_word.this, "date", "0");
            next();
        }


        if (sps.getString(Opposite_word.this, "opp_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Opposite_word.this, "sequence example opp2");
            sequence.setConfig(config);
            sequence.addSequenceItem(hint, "இந்த பொத்தானை பயன்படுத்தி விடைகளின் எண்ணிக்கையை பாதியாக குறைத்துக்கொள்ளலாம்.", "அடுத்து");
            // sequence.addSequenceItem(pic_clue, "குறிப்பை பார்க்க பச்சை நிற பொத்தானை அழுத்தவும் .", "அடுத்து");
            // sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Opposite_word.this)
                    .setTarget(helpshare_layout)
                    .setDismissText("சரி")
                    .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {

                            if (position == 1) {
                                sps.putString(Opposite_word.this, "odd_time_start", "yes");
                                sps.putString(Opposite_word.this, "showcase_dismiss_opp", "yes");
                                focus.setBase(SystemClock.elapsedRealtime());
                                focus.start();

                            }
                        }
                    });

            sps.putString(Opposite_word.this, "opp_intro", "no");
            sequence.start();


        }

        if (sps.getInt(Opposite_word.this,"reward_coin_txt")==0){
            sps.putInt(Opposite_word.this,"reward_coin_txt",20);
        }
        openDialog_s = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        ads_logo = (CircleImageView) openDialog_s.findViewById(R.id.ads_logo);
        adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);


        /////////

        ////////

        /////////


        if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {

        } else {
            // advancads();
            // advancads_content();
        }


        ////////


        // install_ads_doalug();


        ImageView prize_logo=(ImageView)findViewById(R.id.prize_logo);
        /*final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        prize_logo.startAnimation(pendulam);*/
        if (sps.getInt(Opposite_word.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sps.getString(Opposite_word.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Opposite_word.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Opposite_word.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Opposite_word.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Opposite_word.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Opposite_word.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        openDialog_odd_man = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_odd_man.setContentView(R.layout.answer_discription);
        word1 = (TextView) openDialog_odd_man.findViewById(R.id.word1);
        word2 = (TextView) openDialog_odd_man.findViewById(R.id.word2);
        word3 = (TextView) openDialog_odd_man.findViewById(R.id.word3);
        word4 = (TextView) openDialog_odd_man.findViewById(R.id.word4);
        word5 = (TextView) openDialog_odd_man.findViewById(R.id.word5);
        word6 = (TextView) openDialog_odd_man.findViewById(R.id.word6);
        ans = (TextView) openDialog_odd_man.findViewById(R.id.ans);
        dis = (TextView) openDialog_odd_man.findViewById(R.id.discription);
        close = (TextView) openDialog_odd_man.findViewById(R.id.close);
        frame_layout2 = (FrameLayout) openDialog_odd_man.findViewById(R.id.frame_layout2);


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
        String snd = sps.getString(Opposite_word.this, "snd");
        p_setting = (TextView) findViewById(R.id.s_settings);
        if (snd.equals("off")) {
            p_setting.setBackgroundResource(R.drawable.sound_off);
            // settings.setBackgroundResource(R.drawable.sound_off);
            //  toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;

        } else if (snd.equals("on")) {
            p_setting.setBackgroundResource(R.drawable.sound_on);
            // settings.setBackgroundResource(R.drawable.sound_off);
            // toggleButton.setBackgroundResource(R.drawable.on);
            sv = 1;

        }

     /*   final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);*/
        ads_logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsicon2.setVisibility(View.INVISIBLE);

            }
        });

        bt1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bt1.startAnimation(clickzoom);
                        String ts = bt1.getText().toString();
                        verify(ts, "bt1");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bt2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bt2.startAnimation(clickzoom);
                        String ts = bt2.getText().toString();
                        verify(ts, "bt2");
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });
        bt3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bt3.startAnimation(clickzoom);
                        String ts = bt3.getText().toString();
                        verify(ts, "bt3");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bt4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bt4.startAnimation(clickzoom);
                        String ts = bt4.getText().toString();
                        verify(ts, "bt4");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bt5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bt5.startAnimation(clickzoom);
                        String ts = bt5.getText().toString();
                        verify(ts, "bt5");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bt6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bt6.startAnimation(clickzoom);
                        String ts = bt6.getText().toString();
                        verify(ts, "bt6");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bts1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bts1.startAnimation(clickzoom);
                        String ts = bts1.getText().toString();
                        verify(ts, "bts1");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bts2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bts2.startAnimation(clickzoom);
                        String ts = bts2.getText().toString();
                        verify(ts, "bts2");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bts3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bts3.startAnimation(clickzoom);
                        String ts = bts3.getText().toString();
                        verify(ts, "bts3");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });
        bts4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    if (sk > 50) {
                        bts4.startAnimation(clickzoom);
                        String ts = bts4.getText().toString();
                        verify(ts, "bts4");
                    } else {
                        dialog(1);
                    }

                }

                return true;

            }
        });

        p_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p_setting.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(Opposite_word.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(Opposite_word.this, "snd", "on");
                    p_setting.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(Opposite_word.this, "snd", "off");
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
        below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k == 2) {
                    popupWindow.dismiss();
                    k = 1;
                }
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k == 2) {
                    popupWindow.dismiss();
                    k = 1;
                }
            }
        });
        qwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(0);
            }
        });


        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                cfw.moveToFirst();
                int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                if (sk > 50) {
                    if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                        s_score_edit.setText(aStringx);

                        //hint.setEnabled(false);
                        hint.setVisibility(View.INVISIBLE);
                        newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        Random rn = new Random();
                        random = rn.nextInt(max - min + 1) + min;
                        hint.clearAnimation();

                        hintshow();


                    } else {
                        final Dialog openDialog = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        openDialog.setContentView(R.layout.show_ans);
                        TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                        TextView no = (TextView) openDialog.findViewById(R.id.no);
                        TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);
                        TextView txt_ex = (TextView) openDialog.findViewById(R.id.txt_ex);
                        txt_ex.setText("வினாக்களை பாதியாக குறைக்க வேண்டுமா?");
                        txt_ex2.setText("மொத்த நாணயங்களில் 50 குறைக்கப்படும்");
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
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx - 50;
                                String aStringx = Integer.toString(spx);
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                s_score_edit.setText(aStringx);

                                //  hint.setEnabled(false);
                                hint.setVisibility(View.INVISIBLE);
                                newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                                Random rn = new Random();
                                random = rn.nextInt(max - min + 1) + min;
                                hint.clearAnimation();

                                hintshow();

                                openDialog.dismiss();
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

        earncoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });

        p_gplues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 3;
                String a = "com.google.android.apps.plus";
                permission(a);
            }
        });

        p_watts_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 2;
                String a = "com.whatsapp";
                permission(a);
            }
        });

        p_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 1;
                final String a = "com.facebook.katana";
                permission(a);
            }
        });


    }

   /* private void loads_ads_banner() {

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {
            adds.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            if (Utils.isNetworkAvailable(Opposite_word.this)){
                fb_native_Puthayal_Sorkal_Native_Banner(Opposite_word.this,native_banner_ad_container);
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }
    }*/

    private void next() {

        myFadeInAnimation = AnimationUtils.loadAnimation(Opposite_word.this, R.anim.blink_animation);
        hint.startAnimation(myFadeInAnimation);


        discription.setVisibility(View.INVISIBLE);

        bts1.setBackgroundResource(R.drawable.curve_opposite_word);
        bts2.setBackgroundResource(R.drawable.curve_opposite_word);
        bts3.setBackgroundResource(R.drawable.curve_opposite_word);
        bts4.setBackgroundResource(R.drawable.curve_opposite_word);
        bt1.setBackgroundResource(R.drawable.curve_opposite_word);
        bt2.setBackgroundResource(R.drawable.curve_opposite_word);
        bt3.setBackgroundResource(R.drawable.curve_opposite_word);
        bt4.setBackgroundResource(R.drawable.curve_opposite_word);
        bt5.setBackgroundResource(R.drawable.curve_opposite_word);
        bt6.setBackgroundResource(R.drawable.curve_opposite_word);

        bts1.setEnabled(true);
        bts2.setEnabled(true);
        bts3.setEnabled(true);
        bts4.setEnabled(true);
        bt1.setEnabled(true);
        bt2.setEnabled(true);
        bt3.setEnabled(true);
        bt4.setEnabled(true);
        bt5.setEnabled(true);
        bt6.setEnabled(true);



/*
        Calendar calendar2 = Calendar.getInstance();
        int cur_year = calendar2.get(Calendar.YEAR);
        int cur_month = calendar2.get(Calendar.MONTH);
        int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

        String str_month = "" + (cur_month + 1);
        if (str_month.length() == 1) {
            str_month = "0" + str_month;
        }

        String str_day = "" + cur_day;
        if (str_day.length() == 1) {
            str_day = "0" + str_day;
        }
        String str_date = cur_year + "-" + str_month + "-" + str_day;
        sps.putString(context,"date",""+str_date);*/


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

        if (sps.getString(Opposite_word.this, str_date1).equals("")) {

          daily_bones();
            sps.putString(Opposite_word.this, str_date1, "yes");

        }

        if (daily_start == 1) {
            Toast.makeText(Opposite_word.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான எதிர்சொல்லை  கண்டுபிடி விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
            sps.putString(context, "date", "0");
            daily_start = 0;
        }


        String date = sps.getString(Opposite_word.this, "date");
        if (date.equals("0")) {
            rdvalu = 2;
            Cursor c2 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            s_word_number.setText(no/*+"/"+c1.getCount()*/);
        } else {
            Newgame_DataBaseHelper2 newhelper2 = new Newgame_DataBaseHelper2(context);
            Cursor c1 = newhelper2.getQry("select * from newmaintable2 where gameid='7' and isfinish='0'  and daily='0' order by random() limit 1");
            c1.moveToFirst();
            if (c1.getCount() == 0) {
                newhelper2.executeSql("UPDATE newmaintable2 SET daily='0' WHERE gameid=7");
            }
            if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {

            }else {
                sps.putInt(context, "addloded_rect_bck", 0);
                sps.putInt(context, "addloded_rect_mul", 0);

            }
            rdvalu = 1;
            String tfoption = date;
            String[] first = tfoption.split("-");
            s_word_number.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            s_word_number.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }


        Cursor c1 = null;

        if (date.equals("0")) {
            c1 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and isfinish='0'");
        } else {
            daily_start = 1;
            c1 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and  daily='0' order by random() limit 1");
        }
        c1.moveToFirst();
        if (c1.getCount() != 0) {
            NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
            if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {
                native_banner_ad_container.setVisibility(View.GONE);
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            }else {
                if (Utils.isNetworkAvailable(Opposite_word.this)){
                   // native_banner_ad_container.setVisibility(View.VISIBLE);
                }else {
                    native_banner_ad_container.setVisibility(View.GONE);
                }
            }
            head.setVisibility(View.VISIBLE);
            u_id = c1.getInt(c1.getColumnIndexOrThrow("id"));
            questionid = c1.getInt(c1.getColumnIndexOrThrow("questionid"));
            question = c1.getString(c1.getColumnIndexOrThrow("question"));
            answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
            int clue = c1.getInt(c1.getColumnIndexOrThrow("clue"));
            String question_tt = c1.getString(c1.getColumnIndexOrThrow("sf_words"));
            int playtime = c1.getInt(c1.getColumnIndexOrThrow("playtime"));
            question_txt.setText(question_tt);
            String tfoption = question;
            String[] first = tfoption.split(",");
            int letter_type = first.length;
            // Toast.makeText(Odd_man_out.this, ""+clue, Toast.LENGTH_SHORT).show();



          /*  long ptime=0;
            int clue=0;
            Cursor cs;
            cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "'");
            cs.moveToFirst();
            if(cs.getCount()!=0) {
                clue=cs.getInt(cs.getColumnIndexOrThrow("clue"));
            }
            if (clue!=0){
                random=clue;
                hintshow();
                hint.setEnabled(false);
            }
*/

            if (letter_type == 1) {
                bt1.setVisibility(View.VISIBLE);
                bt1.setText(question);

            } else if (letter_type == 2) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
            } else if (letter_type == 3) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
                bt3.setText(letters3);
            } else if (letter_type == 4) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                sixcat.setVisibility(View.GONE);
                fourcat.setVisibility(View.VISIBLE);
                bts1.setVisibility(View.VISIBLE);
                bts2.setVisibility(View.VISIBLE);
                bts3.setVisibility(View.VISIBLE);
                bts4.setVisibility(View.VISIBLE);
                bts1.setText(letters1);
                bts2.setText(letters2);
                bts3.setText(letters3);
                bts4.setText(letters4);
                q_type = 4;
                if (letters1.equals(answer)) {
                    ans_position = 1;
                } else if (letters2.equals(answer)) {
                    ans_position = 2;
                } else if (letters3.equals(answer)) {
                    ans_position = 3;
                } else if (letters4.equals(answer)) {
                    ans_position = 4;
                }
            } else if (letter_type == 5) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt4.setVisibility(View.VISIBLE);
                bt5.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
                bt3.setText(letters3);
                bt4.setText(letters4);
                bt5.setText(letters5);
            } else if (letter_type == 6) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                final String letters6 = tokenizerw.nextToken().trim();
                sixcat.setVisibility(View.VISIBLE);
                fourcat.setVisibility(View.GONE);
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt4.setVisibility(View.VISIBLE);
                bt5.setVisibility(View.VISIBLE);
                bt6.setVisibility(View.VISIBLE);
                bt1.setText(letters1);
                bt2.setText(letters2);
                bt3.setText(letters3);
                bt4.setText(letters4);
                bt5.setText(letters5);
                bt6.setText(letters6);
                q_type = 6;
                if (letters1.equals(answer)) {
                    ans_position = 1;
                } else if (letters2.equals(answer)) {
                    ans_position = 2;
                } else if (letters3.equals(answer)) {
                    ans_position = 3;
                } else if (letters4.equals(answer)) {
                    ans_position = 4;
                } else if (letters5.equals(answer)) {
                    ans_position = 5;
                } else if (letters6.equals(answer)) {
                    ans_position = 6;
                }
            }


            if (clue == 0) {
                //hint.setEnabled(true);
                random = 0;
                hint.setVisibility(View.VISIBLE);
            } else {
                random = clue;
                hint.clearAnimation();
                hintshow();
                // hint.setEnabled(false);
                hint.setVisibility(View.INVISIBLE);
            }
            if (playtime == 0) {
                if (sps.getString(Opposite_word.this, "odd_time_start").equals("")) {

                } else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            }
        } else {
            downloaddata_regular();
           // nextgamesdialog();
        }

    }

    private void find() {
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        clickzoom = AnimationUtils.loadAnimation(this, R.anim.click_zoom);
        clickzoom2 = AnimationUtils.loadAnimation(this, R.anim.click_zoom2);
        s_word_number = (TextView) findViewById(R.id.s_word_number);
        s_score_edit = (TextView) findViewById(R.id.s_score_edit);
        focus = (Chronometer) findViewById(R.id.s_time_edit);
        hint = (TextView) findViewById(R.id.hint);
        bt1 = (TextView) findViewById(R.id.bt1);
        bt2 = (TextView) findViewById(R.id.bt2);
        bt3 = (TextView) findViewById(R.id.bt3);
        bt4 = (TextView) findViewById(R.id.bt4);
        bt5 = (TextView) findViewById(R.id.bt5);
        bt6 = (TextView) findViewById(R.id.bt6);
        sixcat = (RelativeLayout) findViewById(R.id.sixcat);
        fourcat = (RelativeLayout) findViewById(R.id.fourcat);
        bts1 = (TextView) findViewById(R.id.bts1);
        bts2 = (TextView) findViewById(R.id.bts2);
        bts3 = (TextView) findViewById(R.id.bts3);
        bts4 = (TextView) findViewById(R.id.bts4);
        //adds = (LinearLayout) findViewById(R.id.ads_lay);
        s_word_number = (TextView) findViewById(R.id.s_word_number);
        p_coins = (TextView) findViewById(R.id.p_coins);
        head = (RelativeLayout) findViewById(R.id.head);
        p_coins_red = (TextView) findViewById(R.id.p_coins_red);
        p_facebook = (TextView) findViewById(R.id.p_facebook);
        p_watts_app = (TextView) findViewById(R.id.p_watts_app);
        p_gplues = (TextView) findViewById(R.id.p_gplues);
        earncoin = (TextView) findViewById(R.id.earncoin);
        to_no = (TextView) findViewById(R.id.s_word_number);
        p_setting = (TextView) findViewById(R.id.s_settings);
        below = (RelativeLayout) findViewById(R.id.below);
        bottom = (RelativeLayout) findViewById(R.id.bottom);
        qwt = (LinearLayout) findViewById(R.id.qwt);
        helpshare_layout = (LinearLayout) findViewById(R.id.helpshare_layout);
        discription = (TextView) findViewById(R.id.discription);
        question_txt = (TextView) findViewById(R.id.question);
        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Opposite_word.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Opposite_word.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Opposite_word.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Opposite_word.this, R.raw.coins, 1);
///

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        s_score_edit.setText(tr);
        // focus.start();
    }

    public void verify(String ans, String button) {
        String dates = sps.getString(Opposite_word.this, "date");
        Cursor cs = null;
        if (dates.equals("0")) {
             cs = newhelper2.getQry("select * from newmaintable2 where answer LIKE'" + ans + "'and isfinish='0' and questionid=" + questionid + " and gameid=" + gameid + "");
        }else {
            cs = newhelper2.getQry("select * from newmaintable2 where answer LIKE'" + ans + "'and daily='0' and questionid=" + questionid + " and gameid=" + gameid + "");
        }
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            String dis = cs.getString(cs.getColumnIndexOrThrow("sf_words"));
           /* discription.setVisibility(View.VISIBLE);
            discription.setText(dis);*/
            coinanim();
            //   Toast.makeText(Odd_man_out.this, "Correct Answer", Toast.LENGTH_SHORT).show();
            spz3.play(soundId3, sv, sv, 0, 0, sv);
            changecolour_r(button);
            hint.clearAnimation();
            hint.setVisibility(View.INVISIBLE);
            String date = sps.getString(Opposite_word.this, "date");

            if (date.equals("0")) {
                newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            } else {
                newhelper2.executeSql("UPDATE newmaintable2 SET daily='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            }
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
            focus.stop();
            price_update();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setSc();
                }
            }, 2300);
        } else {
            //  Toast.makeText(Odd_man_out.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            hint.clearAnimation();
            hint.setVisibility(View.INVISIBLE);

            Cursor c1 = null;
            if (dates.equals("0")) {
                c1 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "' and isfinish='0'");
            }else {
                c1 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "' and daily='0'");

            }
            c1.moveToFirst();
            if (c1.getCount() != 0) {
                String answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
                show_answer(answer);
                String dis = c1.getString(c1.getColumnIndexOrThrow("sf_words"));
              /*  discription.setVisibility(View.VISIBLE);
                discription.setText(dis);*/
            }

            spz2.play(soundId2, sv, sv, 0, 0, sv);
            coinanim_red();
            changecolour_w(button);
            String date = sps.getString(Opposite_word.this, "date");
            if (date.equals("0")) {
                newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            } else {
                newhelper2.executeSql("UPDATE newmaintable2 SET daily='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setSc();
                }
            }, 2300);
        }
    }

    private void show_answer(String answer) {

        if (q_type == 4) {
            if (answer.equals(bts1.getText().toString())) {
                bts1.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bts2.getText().toString())) {
                bts2.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bts3.getText().toString())) {
                bts3.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bts4.getText().toString())) {
                bts4.setBackgroundResource(R.color.rightans);
            }
        } else if (q_type == 6) {
            if (answer.equals(bt1.getText().toString())) {
                bt1.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt2.getText().toString())) {
                bt2.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt3.getText().toString())) {
                bt3.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt4.getText().toString())) {
                bt4.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt5.getText().toString())) {
                bt5.setBackgroundResource(R.color.rightans);
            } else if (answer.equals(bt6.getText().toString())) {
                bt6.setBackgroundResource(R.color.rightans);
            }
        }
    }

    private void changecolour_w(String button) {

        if (button.equals("bts1")) {
            bts1.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts2")) {
            bts2.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts3")) {
            bts3.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts4")) {
            bts4.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt1")) {
            bt1.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt2")) {
            bt2.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt3")) {
            bt3.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt4")) {
            bt4.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt5")) {
            bt5.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt6")) {
            bt6.setBackgroundResource(R.color.worngans);
        }
        bts1.setEnabled(false);
        bts2.setEnabled(false);
        bts3.setEnabled(false);
        bts4.setEnabled(false);
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
        bt5.setEnabled(false);
        bt6.setEnabled(false);
    }

    private void changecolour_r(String button) {

        if (button.equals("bts1")) {
            bts1.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts2")) {
            bts2.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts3")) {
            bts3.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts4")) {
            bts4.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt1")) {
            bt1.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt2")) {
            bt2.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt3")) {
            bt3.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt4")) {
            bt4.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt5")) {
            bt5.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt6")) {
            bt6.setBackgroundResource(R.color.rightans);
        }
        bts1.setEnabled(false);
        bts2.setEnabled(false);
        bts3.setEnabled(false);
        bts4.setEnabled(false);
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
        bt5.setEnabled(false);
        bt6.setEnabled(false);
    }

    public void onBackPressed() {
        sps.putString(Opposite_word.this,"game_area","on");
        sps.putString(Opposite_word.this, "odd_time_start", "yes");
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            sps.putInt(Opposite_word.this, "addlodedd", 0);

                s = 1;
                openDialog_p = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog_p.setContentView(R.layout.back_pess);
                TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
                TextView no = (TextView) openDialog_p.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                            if (main_act.equals("")) {
                                finish();
                                openDialog_s.dismiss();

                                Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                finish();
                                openDialog_s.dismiss();
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


    @Override
    protected void onPause() {
        super.onPause();
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Opposite_word.this, "date");
        System.out.println("######################Timer ttstop"+ttstop);
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
        }


    }

    protected void onResume() {
        super.onResume();
        long ptime = 0;
        int clue = 0;

        String date = sps.getString(Opposite_word.this, "date");
        int pos;
        Cursor cs;
        long dscore = 0;
        int noofclue = 0;
        if (date.equals("0")) {
            pos = 1;
            cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "'");
            cs.moveToFirst();
            if (cs.getCount() != 0) {
                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                clue = cs.getInt(cs.getColumnIndexOrThrow("clue"));
            }
        } else {
            pos = 2;
            cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "'");
            cs.moveToFirst();
            if (cs.getCount() != 0) {
                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                clue = cs.getInt(cs.getColumnIndexOrThrow("clue"));
            }
        }


      /*  Cursor cs;
        cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "'");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            ptime = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            clue = cs.getInt(cs.getColumnIndexOrThrow("clue"));
        }*/

        // Toast.makeText(Odd_man_out.this, "random"+clue, Toast.LENGTH_SHORT).show();
       /* if (clue!=0){
            random=clue;
            hintshow();
            hint.setEnabled(false);
        }*/
        if (sps.getString(Opposite_word.this, "odd_time_start").equals("")) {

            System.out.println("######################Timer not");
        } else {
            System.out.println("######################Timer start"+dscore);
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Opposite_word.this);
        mFirebaseAnalytics.setCurrentScreen(this, "Find the Opposite Words", null);



    }

    public void hintshow() {

        if (q_type == 4) {
            if (ans_position == 1) {
                if (random == 1) {
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts2.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    bts2.setEnabled(false);
                    bts4.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts2.setVisibility(View.INVISIBLE);
                            bts4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 2) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts2.startAnimation(clickzoom2);
                    bts3.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts3.setVisibility(View.INVISIBLE);
                            bts2.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 3) {
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts2.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    bts2.setEnabled(false);
                    bts4.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts2.setVisibility(View.INVISIBLE);
                            bts4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }
            } else if (ans_position == 2) {
                if (random == 1) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts4.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts1.setVisibility(View.INVISIBLE);
                            bts4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 2) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    bts3.setEnabled(false);
                    bts4.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts3.setVisibility(View.INVISIBLE);
                            bts4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 3) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts3.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts3.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts1.setVisibility(View.INVISIBLE);
                            bts3.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                }

            } else if (ans_position == 3) {
                if (random == 1) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts4.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts1.setVisibility(View.INVISIBLE);
                            bts4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 2) {
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts2.startAnimation(clickzoom2);
                    bts1.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts2.setVisibility(View.INVISIBLE);
                            bts1.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 3) {
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts4.startAnimation(clickzoom2);
                    bts2.startAnimation(clickzoom2);
                    bts4.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts4.setVisibility(View.INVISIBLE);
                            bts2.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                }

            } else if (ans_position == 4) {
                if (random == 1) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts2.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts1.setVisibility(View.INVISIBLE);
                            bts2.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 2) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts3.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts3.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts1.setVisibility(View.INVISIBLE);
                            bts3.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 3) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts2.startAnimation(clickzoom2);
                    bts3.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts3.setVisibility(View.INVISIBLE);
                            bts2.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                }

            }
        } else if (q_type == 6) {
            if (ans_position == 1) {
                if (random == 1) {
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt4.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt2.startAnimation(clickzoom2);
                    bt4.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);
                    bt2.setEnabled(false);
                    bt4.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt2.setVisibility(View.INVISIBLE);
                            bt4.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 2) {

                    bt3.setBackgroundResource(R.color.eliminate);
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt5.setBackgroundResource(R.color.eliminate);
                    bt3.startAnimation(clickzoom2);
                    bt2.startAnimation(clickzoom2);
                    bt5.startAnimation(clickzoom2);

                    bt3.setEnabled(false);
                    bt2.setEnabled(false);
                    bt5.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt3.setVisibility(View.INVISIBLE);
                            bt2.setVisibility(View.INVISIBLE);
                            bt5.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 3) {

                    bt3.setBackgroundResource(R.color.eliminate);
                    bt4.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt3.startAnimation(clickzoom2);
                    bt4.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);
                    bt3.setEnabled(false);
                    bt4.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt3.setVisibility(View.INVISIBLE);
                            bt4.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }

            } else if (ans_position == 2) {
                if (random == 1) {

                    bt1.setBackgroundResource(R.color.eliminate);
                    bt3.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt3.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);
                    bt1.setEnabled(false);
                    bt3.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt3.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                } else if (random == 2) {
                    bt3.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt5.setBackgroundResource(R.color.eliminate);
                    bt3.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);
                    bt5.startAnimation(clickzoom2);
                    bt3.setEnabled(false);
                    bt6.setEnabled(false);
                    bt5.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt3.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                            bt5.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 3) {

                    bt5.setBackgroundResource(R.color.eliminate);
                    bt4.setBackgroundResource(R.color.eliminate);
                    bt3.setBackgroundResource(R.color.eliminate);
                    bt5.startAnimation(clickzoom2);
                    bt4.startAnimation(clickzoom2);
                    bt3.startAnimation(clickzoom2);

                    bt5.setEnabled(false);
                    bt4.setEnabled(false);
                    bt3.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt5.setVisibility(View.INVISIBLE);
                            bt4.setVisibility(View.INVISIBLE);
                            bt3.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }

            } else if (ans_position == 3) {
                if (random == 1) {

                    bt1.setBackgroundResource(R.color.eliminate);
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt2.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);

                    bt1.setEnabled(false);
                    bt2.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt2.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 2) {

                    bt2.setBackgroundResource(R.color.eliminate);
                    bt1.setBackgroundResource(R.color.eliminate);
                    bt5.setBackgroundResource(R.color.eliminate);
                    bt2.startAnimation(clickzoom2);
                    bt1.startAnimation(clickzoom2);
                    bt5.startAnimation(clickzoom2);

                    bt2.setEnabled(false);
                    bt1.setEnabled(false);
                    bt5.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt2.setVisibility(View.INVISIBLE);
                            bt1.setVisibility(View.INVISIBLE);
                            bt5.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                } else if (random == 3) {

                    bt4.setBackgroundResource(R.color.eliminate);
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt4.startAnimation(clickzoom2);
                    bt2.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);

                    bt4.setEnabled(false);
                    bt2.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt4.setVisibility(View.INVISIBLE);
                            bt2.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }

            } else if (ans_position == 4) {
                if (random == 1) {


                    bt1.setBackgroundResource(R.color.eliminate);
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt2.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);
                    bt1.setEnabled(false);
                    bt2.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt2.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                } else if (random == 2) {


                    bt1.setBackgroundResource(R.color.eliminate);
                    bt3.setBackgroundResource(R.color.eliminate);
                    bt5.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt3.startAnimation(clickzoom2);
                    bt5.startAnimation(clickzoom2);

                    bt1.setEnabled(false);
                    bt3.setEnabled(false);
                    bt5.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt3.setVisibility(View.INVISIBLE);
                            bt5.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 3) {

                    bt2.setBackgroundResource(R.color.eliminate);
                    bt5.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt2.startAnimation(clickzoom2);
                    bt5.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);

                    bt2.setEnabled(false);
                    bt5.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt2.setVisibility(View.INVISIBLE);
                            bt5.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }

            } else if (ans_position == 5) {
                if (random == 1) {

                    bt1.setBackgroundResource(R.color.eliminate);
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt2.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);

                    bt1.setEnabled(false);
                    bt2.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt2.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                } else if (random == 2) {

                    bt1.setBackgroundResource(R.color.eliminate);
                    bt3.setBackgroundResource(R.color.eliminate);
                    bt4.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt3.startAnimation(clickzoom2);
                    bt4.startAnimation(clickzoom2);

                    bt1.setEnabled(false);
                    bt3.setEnabled(false);
                    bt4.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt3.setVisibility(View.INVISIBLE);
                            bt4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                } else if (random == 3) {


                    bt2.setBackgroundResource(R.color.eliminate);
                    bt4.setBackgroundResource(R.color.eliminate);
                    bt6.setBackgroundResource(R.color.eliminate);
                    bt2.startAnimation(clickzoom2);
                    bt4.startAnimation(clickzoom2);
                    bt6.startAnimation(clickzoom2);

                    bt2.setEnabled(false);
                    bt4.setEnabled(false);
                    bt6.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt2.setVisibility(View.INVISIBLE);
                            bt4.setVisibility(View.INVISIBLE);
                            bt6.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }

            } else if (ans_position == 6) {
                if (random == 1) {

                    bt1.setBackgroundResource(R.color.eliminate);
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt5.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt2.startAnimation(clickzoom2);
                    bt5.startAnimation(clickzoom2);

                    bt1.setEnabled(false);
                    bt2.setEnabled(false);
                    bt5.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt2.setVisibility(View.INVISIBLE);
                            bt5.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                } else if (random == 2) {
                    bt1.setBackgroundResource(R.color.eliminate);
                    bt3.setBackgroundResource(R.color.eliminate);
                    bt4.setBackgroundResource(R.color.eliminate);
                    bt1.startAnimation(clickzoom2);
                    bt3.startAnimation(clickzoom2);
                    bt4.startAnimation(clickzoom2);

                    bt1.setEnabled(false);
                    bt3.setEnabled(false);
                    bt4.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt1.setVisibility(View.INVISIBLE);
                            bt3.setVisibility(View.INVISIBLE);
                            bt4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                } else if (random == 3) {
                    bt2.setBackgroundResource(R.color.eliminate);
                    bt4.setBackgroundResource(R.color.eliminate);
                    bt5.setBackgroundResource(R.color.eliminate);
                    bt2.startAnimation(clickzoom2);
                    bt4.startAnimation(clickzoom2);
                    bt5.startAnimation(clickzoom2);

                    bt2.setEnabled(false);
                    bt4.setEnabled(false);
                    bt5.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bt2.setVisibility(View.INVISIBLE);
                            bt4.setVisibility(View.INVISIBLE);
                            bt5.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }
            }
        }
    }

    public void hintshow2() {

        if (q_type == 4) {
            if (ans_position == 1) {
                if (random == 1) {
                    bts2.setVisibility(View.INVISIBLE);
                    bts4.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bts3.setVisibility(View.INVISIBLE);
                    bts2.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bts2.setVisibility(View.INVISIBLE);
                    bts4.setVisibility(View.INVISIBLE);
                }

            } else if (ans_position == 2) {
                if (random == 1) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts1.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 2) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts3.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts4.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);


                } else if (random == 3) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts3.startAnimation(clickzoom2);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts1.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bts3.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }

            } else if (ans_position == 3) {
                if (random == 1) {
                    bts1.setVisibility(View.INVISIBLE);
                    bts4.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bts2.setVisibility(View.INVISIBLE);
                    bts1.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bts4.setVisibility(View.INVISIBLE);
                    bts2.setVisibility(View.INVISIBLE);
                }

            } else if (ans_position == 4) {
                if (random == 1) {
                    bts1.setVisibility(View.INVISIBLE);
                    bts2.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bts1.setVisibility(View.INVISIBLE);
                    bts3.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bts2.setVisibility(View.INVISIBLE);
                    bts3.setVisibility(View.INVISIBLE);
                }

            }
        } else if (q_type == 6) {
            if (ans_position == 1) {
                if (random == 1) {
                    bt2.setVisibility(View.INVISIBLE);
                    bt4.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bt3.setVisibility(View.INVISIBLE);
                    bt2.setVisibility(View.INVISIBLE);
                    bt5.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bt3.setVisibility(View.INVISIBLE);
                    bt4.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                }

            } else if (ans_position == 2) {
                if (random == 1) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt3.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bt3.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                    bt5.setVisibility(View.INVISIBLE);

                } else if (random == 3) {
                    bt5.setVisibility(View.INVISIBLE);
                    bt4.setVisibility(View.INVISIBLE);
                    bt3.setVisibility(View.INVISIBLE);
                }

            } else if (ans_position == 3) {
                if (random == 1) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt2.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bt2.setVisibility(View.INVISIBLE);
                    bt1.setVisibility(View.INVISIBLE);
                    bt5.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bt4.setVisibility(View.INVISIBLE);
                    bt2.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                }

            } else if (ans_position == 4) {
                if (random == 1) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt2.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt3.setVisibility(View.INVISIBLE);
                    bt5.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bt2.setVisibility(View.INVISIBLE);
                    bt5.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                }

            } else if (ans_position == 5) {
                if (random == 1) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt2.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt3.setVisibility(View.INVISIBLE);
                    bt4.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bt2.setVisibility(View.INVISIBLE);
                    bt4.setVisibility(View.INVISIBLE);
                    bt6.setVisibility(View.INVISIBLE);
                }

            } else if (ans_position == 6) {
                if (random == 1) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt2.setVisibility(View.INVISIBLE);
                    bt5.setVisibility(View.INVISIBLE);
                } else if (random == 2) {
                    bt1.setVisibility(View.INVISIBLE);
                    bt3.setVisibility(View.INVISIBLE);
                    bt4.setVisibility(View.INVISIBLE);
                } else if (random == 3) {
                    bt2.setVisibility(View.INVISIBLE);
                    bt4.setVisibility(View.INVISIBLE);
                    bt5.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void coinanim() {
////


        //score intial

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        s_score_edit.setText(tr);
        //
        e2 = skq;
        //play1.start();
        spz4.play(soundId4, sv, sv, 0, 0, sv);
        p_coins.setVisibility(View.VISIBLE);
        int[] locationInWindow = new int[2];
        p_coins.getLocationInWindow(locationInWindow);
        int[] locationOnScreen = new int[2];
        p_coins.getLocationOnScreen(locationOnScreen);
        float sourceX = locationOnScreen[0];
        float sourceY = locationOnScreen[1];
        int[] locationInWindowSecond = new int[2];
        s_score_edit.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        s_score_edit.getLocationOnScreen(locationOnScreenSecond);
        float destinationX = locationOnScreenSecond[0];
        float destinationY = locationOnScreenSecond[1];
        TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
        transAnimation.setDuration(700);
        p_coins.startAnimation(transAnimation);
        p_coins.postDelayed(new Runnable() {
            @Override
            public void run() {
                p_coins.setVisibility(View.INVISIBLE);
            }
        }, transAnimation.getDuration());


        ////

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //play1.start();
                spz4.play(soundId4, sv, sv, 0, 0, sv);
                p_coins.setVisibility(View.VISIBLE);
                int[] locationInWindow = new int[2];
                p_coins.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                p_coins.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                s_score_edit.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                s_score_edit.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
                transAnimation.setDuration(1000);
                p_coins.startAnimation(transAnimation);
                p_coins.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        p_coins.setVisibility(View.INVISIBLE);
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
                    s_score_edit.post(new Runnable() {

                        public void run() {

                            s_score_edit.setText("" + e2);

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
                s_score_edit.startAnimation(levels1);
            }
        }, 2200);

        Handler handler21 = new Handler();
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount()!=0){
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx + 20;
                    String aStringx = Integer.toString(spx);
                    s_score_edit.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                }


                Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                ch.moveToFirst();
                if (ch.getCount()!=0){
                    int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
                    int shh = sh + 50;
                    myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

                }


                // setSc();
            }
        }, 1200);

    }

    public void coinanim_red() {
////


        //score intial

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq=0;
        if (cfq.getCount()!=0){
            skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
            String tr = String.valueOf(skq);
        }

        //  s_score_edit.setText(tr);
        //
        e2 = skq;
        //play1.start();
        spz4.play(soundId4, sv, sv, 0, 0, sv);
        p_coins_red.setVisibility(View.VISIBLE);
        int[] locationInWindow = new int[2];
        p_coins_red.getLocationInWindow(locationInWindow);
        int[] locationOnScreen = new int[2];
        p_coins_red.getLocationOnScreen(locationOnScreen);
        float sourceX = locationOnScreen[0];
        float sourceY = locationOnScreen[1];
        int[] locationInWindowSecond = new int[2];
        p_coins.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        p_coins.getLocationOnScreen(locationOnScreenSecond);
        float destinationX = locationOnScreenSecond[0];
        float destinationY = locationOnScreenSecond[1];
        TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
        transAnimation.setDuration(700);
        p_coins_red.startAnimation(transAnimation);
        p_coins_red.postDelayed(new Runnable() {
            @Override
            public void run() {
                p_coins_red.setVisibility(View.INVISIBLE);
            }
        }, transAnimation.getDuration());


        ////

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //play1.start();
                spz4.play(soundId4, sv, sv, 0, 0, sv);
                p_coins_red.setVisibility(View.VISIBLE);
                int[] locationInWindow = new int[2];
                p_coins_red.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                p_coins_red.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                p_coins.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                p_coins.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
                transAnimation.setDuration(1000);
                p_coins_red.startAnimation(transAnimation);
                p_coins_red.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        p_coins_red.setVisibility(View.INVISIBLE);
                    }
                }, transAnimation.getDuration());
            }
        }, 1000);

        new Thread(new Runnable() {

            public void run() {
                int es = e2 - 50;
                while (e2 < es) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    s_score_edit.post(new Runnable() {

                        public void run() {

                            s_score_edit.setText("" + e2);

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
                s_score_edit.startAnimation(levels1);
            }
        }, 2200);

        Handler handler21 = new Handler();
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx - 50;
                String aStringx = Integer.toString(spx);
                s_score_edit.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                // setSc();
            }
        }, 1300);

    }


    private void helpshare(String a) {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Bitmap bitmap = Bitmap.createBitmap(head.getWidth(), head.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        head.draw(canvas);

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
                    newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

/*
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
                    }*/

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Opposite_word.this, Opposite_word.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இந்த எதிர்சொல்லை கண்டுபிடி விளையாட்டின் விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்.   https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT,
                            "Solli_adi");
                    //  share.putExtra(android.content.Intent.EXTRA_TEXT,"Shared via Tamil Calendar Offline.\nClick here to download"+ "\nhttps://goo.gl/ITvWGu");
                    startActivity(Intent.createChooser(share, "Share Card Using"));
                } else {
                    Toast.makeText(Opposite_word.this, "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                  /*  CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "இந்த செயலி தங்களிடம் இல்லை", Snackbar.LENGTH_SHORT);
                    final View view = snackbar.getView();
                    TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    view.setBackgroundResource(R.drawable.answershow);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                    textView.setTextSize(17);
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    snackbar.show();*/
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
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
        openDialog_earncoin = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = (TextView) openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = (TextView) openDialog_earncoin.findViewById(R.id.ssss);


        TextView wpro = (TextView) openDialog_earncoin.findViewById(R.id.wpro);
        if (i == 1) {
            cancel.setVisibility(View.INVISIBLE);
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }
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
                        String date = sps.getString(Opposite_word.this, "date");
                        System.out.println("######################Timer ttstop"+ttstop);
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                        }
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
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
             /*   rvo = 1;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Opposite_word.this, "" + "Reward video", "Loading...");


                   *//* ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    focus.stop();
                    String date = sps.getString(Odd_man_out.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        myDbHelper.executeSql("UPDATE maintable SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                        myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        myDbHelper.executeSql("UPDATE dailytest SET playtime='" + ttstop + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");

                        myDbHelper.executeSql("UPDATE maintable SET noclue='" + f + "' WHERE levelid='" + wordid + "' and gameid='" + gameid + "'");
                    }*//*
                    if (mRewardedVideoAd.isLoaded()) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Opposite_word.this, "date");
                        System.out.println("######################Timer ttstop"+ttstop);
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
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
                                    Toast.makeText(Opposite_word.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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
                       // openFacebookSession();
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
                       /* ttstop = focus.getBase() - SystemClock.elapsedRealtime();
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
                        }*/
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
/*
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
    }

    public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());
    }*/

/*    private void publishFeedDialog() {
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
                new WebDialog.OnCompleteListener() {

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

                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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

                                if (sps.getString(Opposite_word.this, "complite_reg").equals("yes")) {
                                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                                    cn.moveToFirst();
                                    int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
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
    }*/

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
     /*   int spx = skx + a;
        final String aStringx = Integer.toString(spx);*/
        b_scores.setText("" + a);


        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttscores.setText("" + skx);
                s_score_edit.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }

   /* private void showDialogWithoutNotificationBarInvite(String action, Bundle params) {
        final WebDialog dialog = new WebDialog.Builder(context,
                Session.getActiveSession(), action, params)
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {
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

                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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

                                if (sps.getString(Opposite_word.this, "complite_reg").equals("yes")) {
                                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                                    cn.moveToFirst();
                                    int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
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
    }*/


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //uiHelper.onSaveInstanceState(outState);
        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
     /*   int spx = skx + a;
        final String aStringx = Integer.toString(spx);*/
        b_scores.setText("" + a);


        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_score_edit.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }


    //*********************reward videos process 3***********************
















    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Opposite_word.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }




    //reward videos***********************//


    public void vidcoinearn() {
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea=ea+setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
            TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + mCoinCount;
            final String aStringx = Integer.toString(spx);


            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s_score_edit.setText("" + skx);
                    openDialog.dismiss();
                    //mCoinCount = 0;
                }
            });
            openDialog.show();
        }

    }

    private void daily_bones() {

    openDialog = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        //  TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);

        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
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
        final String str_date1 =  str_day1 + "-" + str_month1 + "-" + cur_year1;

        Date date1=new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        final String date=sdf.format(date1);

        TextView tomarrow_coin_earn = (TextView) openDialog.findViewById(R.id.tomarrow_coin_earn);

        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx + ea;
                    String aStringx = Integer.toString(spx);
                    s_score_edit.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    sps.putString(context, "daily_bonus_date", date);
                }

                openDialog.dismiss();

            }
        });
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
        ea = 100;
        final int vals=reward_play_count*100;
        ea=ea+vals;
        coin_value.setText("" + ea);

        LinearLayout extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        System.out.println("############################^^^^^^^^^^^^^^currentdate"+str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate"+sps.getString(Opposite_word.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Opposite_word.this, "daily_bonus_date"))) {

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
        prize_data_update(context,ea);
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
      /*  final int vals = reward_play_count * 100;
        ea = ea + vals;*/
        coin_value.setText("" + ea);
        setval_vid=ea;
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

        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);


        extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        extra_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extra_coin_s = 1;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Opposite_word.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                    }else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedAd.showAd();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(Opposite_word.this);
                                    rewarded_ad();
                                    Toast.makeText(Opposite_word.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                            @Override
                            public void onClick(View v) {
                                openDialog.dismiss();
                            }
                        });*/
        openDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Opposite_word.this)) {
                download_datas();
            } else {
                NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                native_banner_ad_container.setVisibility(View.INVISIBLE);
                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Opposite_word.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                sps.putInt(Opposite_word.this, "goto_sett", 1);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String date = sps.getString(Opposite_word.this, "date");
                                if (date.equals("0")) {
                                    backexitnet();
                                } else {
                                    backexitnet();
                                }
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }


        if (requestCode == 16) {
            if (resultCode == -1) {
              /*  if (sps.getString(Picture_Game_Hard.this, "gplues").equals("yes")) {

                }*/

                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 10;
                String aStringx = Integer.toString(spx);
                //score.setText(aStringx);
                // ttscores.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                sps.putString(Opposite_word.this, "gplues", "no");
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

                if (sps.getString(Opposite_word.this, "complite_reg").equals("yes")) {
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
        if (requestCode == 12) {
            if (resultCode == -1) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 20;
                String aStringx = Integer.toString(spx);
                //score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                share_earn(20);

                if (sps.getString(Opposite_word.this, "complite_reg").equals("yes")) {
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
                    int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
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
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 20;
                String aStringx = Integer.toString(spx);
                //score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                share_earn2(20);

                if (sps.getString(Opposite_word.this, "complite_reg").equals("yes")) {
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
                    int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                    int gm1s = gm1 + 1;
                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                    ///Reward Share
                }
            } else {
            }
        }

        if (requestCode == 15) {
            if (resultCode == -1) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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

                if (sps.getString(Opposite_word.this, "complite_reg").equals("yes")) {
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

    }


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
        TextView extracoin2 = (TextView) openDialog_s.findViewById(R.id.extracoin2);
        final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);
        final LinearLayout rewardvideo = (LinearLayout) openDialog_s.findViewById(R.id.rewardvideo);
        LinearLayout ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);

        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் "+sps.getInt(Opposite_word.this,"reward_coin_txt")+"+நாணயங்கள் பெற");

        ImageView prize_logo=(ImageView)openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Opposite_word.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sps.getString(Opposite_word.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Opposite_word.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Opposite_word.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Opposite_word.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Opposite_word.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Opposite_word.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Opposite_word.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            //New_Main_Activity.load_addFromMain_multiplayer(Opposite_word.this,ads_layout);
            if (Utils.isNetworkAvailable(Opposite_word.this)){
                //New_Main_Activity.load_add_fb_rect_score_screen(Opposite_word.this, ads_layout);
            }else {
                ads_layout.setVisibility(View.GONE);
            }
            /*  if (loadaddcontent == 1) {
                if (native_adView3 != null) {
                    native_adView3.removeAllViews();
                }
                LayoutInflater inflater;
                inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.remote_config);
                ins_app(context, view1, sps.getInt(context, "remoteConfig"));
                ads_layout.addView(view1);
            }

            if (isNetworkAvailable()) {
                load_addinstall(context, ads_layout);
            } else {
                if (native_adView3 != null) {
                    native_adView3.removeAllViews();
                }
                LayoutInflater inflater;
                inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.remote_config);
                ins_app(context, view1, sps.getInt(context, "remoteConfig"));
                ads_layout.addView(view1);
            }*/
        }


        TextView discription = (TextView) openDialog_s.findViewById(R.id.discription);
        discription.setVisibility(View.INVISIBLE);

        next_continue.setVisibility(View.VISIBLE);
        word.setTypeface(tyr);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);
        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(Opposite_word.this, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        String aStringx = Integer.toString(skx);
        ttscores.setText(aStringx);


        if (sps.getString(Opposite_word.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Opposite_word.this, "date");
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
        // final RelativeLayout adsicon=(RelativeLayout)openDialog_s.findViewById(R.id.adsicon);
        final Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);
        ads_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //discription.startAnimation(myFadeInAnimation);
        discription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_dicription();
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
                        rewardedAd.showAd();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    }else {
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
                    }else {
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

                       /* if (sps.getString(Picture_Game_Hard.this, "watts_app_s").equals("")) {
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Score Adding
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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


          /*      btn_str = "share";
                if (isLoggedIn()) {


                    publishFeedDialog();
                    // toast("yes");
                } else {
                   // openFacebookSession();
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

                if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();
                }else {
                    System.out.println("#############@@@@@@@@@@@@SP" + sps.getInt(getApplicationContext(), "ins_ad_new"));
                    if (sps.getInt(getApplicationContext(), "ins_ad_new") == 4) {
                        sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (ins_game == null || !ins_game.isReady()) {
                                dia_dismiss = 1;
                                openDialog_s.dismiss();
                                next();
                                industrialload_game();
                                return;
                            } else {
                                ins_game.showAd();
                            }



                     /*   if (interstitialAd_game != null) {
                            if (interstitialAd_game.isLoaded()) {
                                interstitialAd_game.show();
                                interstitialAd_game.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdClosed() {

                                        next();

                                        ins_add();

                                    }

                                });
                            } else {
                                next();
                            }
                        }*/
                        } else {
                            dia_dismiss = 1;
                            openDialog_s.dismiss();
                            next();
                        }

                    } else {
                        dia_dismiss = 1;
                        openDialog_s.dismiss();
                        next();

                        sps.putInt(getApplicationContext(), "ins_ad_new", (sps.getInt(getApplicationContext(), "ins_ad_new") + 1));
                    }
                }
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    if (getApiClient().isConnected()) {
                        if (isSignedIn()) {
                            int k1 = 0;
                            Cursor sc2 = myDbHelper.getQry("select * from score ");
                            sc2.moveToFirst();
                            if (sc2.getCount() != 0) {
                                k1 = sc2.getInt(sc2.getColumnIndexOrThrow("l_points"));
                            }
                            //Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard), k1);
                        }
                    }
                }
/*
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
                }*/

                if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {

                } else {
                    // advancads();
                    //advancads_content();
                }
              /*  dia_dismiss=1;
                openDialog_s.dismiss();*/
            }
        });
        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss!=1){
                    sps.putString(Opposite_word.this,"game_area","on");
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                            openDialog_s.dismiss();
                        }


                }else {
                    dia_dismiss=0;
                }

            }
        });

        /*openDialog_s.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {




             *//*   final Dialog openDialog1 = new Dialog(Picture_Game_Hard.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog1.setContentView(R.layout.back_pess);
                TextView yes = (TextView) openDialog1.findViewById(R.id.yes);
                TextView no = (TextView) openDialog1.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c1.reset();
                        c2.reset();
                        c3.reset();
                        c4.reset();
                        c5.reset();
                        c6.reset();
                        c7.reset();
                        c8.reset();
                        c9.reset();
                        c10.reset();
                        c11.reset();
                        c12.reset();
                        c13.reset();
                        c14.reset();
                        c15.reset();
                        c16.reset();
                        c17.reset();
                        c18.reset();
                        c19.reset();
                        c20.reset();
                        r1.reset();
                        w1.reset();
                        finish();
                        openDialog1.dismiss();
                        Intent i = new Intent(Picture_Game_Hard.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog1.dismiss();
                    }
                });
                openDialog1.show();*//*
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });*/
        if (!isFinishing()) {
            openDialog_s.show();
        }

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

        ins_game = new MaxInterstitialAd(getResources().getString(R.string.Puthayal_Sorkal_Ins_new), this);
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


    private void dialog_dicription() {

        word1.setVisibility(View.GONE);
        word2.setVisibility(View.GONE);
        word3.setVisibility(View.GONE);
        word4.setVisibility(View.GONE);
        word5.setVisibility(View.GONE);
        word6.setVisibility(View.GONE);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //load_addcontent2(context,ads_layout);

              /*  if (isNetworkAvailable()) {
                    load_addinstall(context, ads_layout);
                } else {
                    if (native_adView3 != null) {
                        native_adView3.removeAllViews();
                    }
                    LayoutInflater inflater;
                    inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    View view1 = inflater.inflate(R.layout.remote_config);
                    ins_app(context, view1, sps.getInt(context, "remoteConfig"));
                    ads_layout.addView(view1);
                }
                if (loadaddcontent == 1) {
                    if (native_adView3 != null) {
                        native_adView3.removeAllViews();
                    }
                    LayoutInflater inflater;
                    inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    View view1 = inflater.inflate(R.layout.remote_config);
                    ins_app(context, view1, sps.getInt(context, "remoteConfig"));
                    ads_layout.addView(view1);
                }*/
                openDialog_odd_man.dismiss();

            }
        });

        openDialog_odd_man.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // dialog dismiss without button press
                // load_addcontent2(context,ads_layout);
              /*  if (isNetworkAvailable()) {
                    load_addinstall(context, ads_layout);
                } else {
                    if (native_adView3 != null) {
                        native_adView3.removeAllViews();
                    }
                    LayoutInflater inflater;
                    inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    View view1 = inflater.inflate(R.layout.remote_config);
                    ins_app(context, view1, sps.getInt(context, "remoteConfig"));
                    ads_layout.addView(view1);
                }
                if (loadaddcontent == 1) {
                    if (native_adView3 != null) {
                        native_adView3.removeAllViews();
                    }
                    LayoutInflater inflater;
                    inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    View view1 = inflater.inflate(R.layout.remote_config);
                    ins_app(context, view1, sps.getInt(context, "remoteConfig"));
                    ads_layout.addView(view1);
                }*/

            }
        });
        Cursor cs;
        cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "'");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            String question = cs.getString(cs.getColumnIndexOrThrow("question"));
            String answer = cs.getString(cs.getColumnIndexOrThrow("answer"));
            String discription = cs.getString(cs.getColumnIndexOrThrow("sf_words"));

            if (q_type == 4) {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                word1.setVisibility(View.VISIBLE);
                word2.setVisibility(View.VISIBLE);
                word3.setVisibility(View.VISIBLE);
                word4.setVisibility(View.VISIBLE);
                word1.setText(letters1);
                word2.setText(letters2);
                word3.setText(letters3);
                word4.setText(letters4);
            } else {
                StringTokenizer tokenizerw = new StringTokenizer(question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                final String letters6 = tokenizerw.nextToken().trim();
                word1.setVisibility(View.VISIBLE);
                word2.setVisibility(View.VISIBLE);
                word3.setVisibility(View.VISIBLE);
                word4.setVisibility(View.VISIBLE);
                word5.setVisibility(View.VISIBLE);
                word6.setVisibility(View.VISIBLE);
                word1.setText(letters1);
                word2.setText(letters2);
                word3.setText(letters3);
                word4.setText(letters4);
                word5.setText(letters5);
                word6.setText(letters6);
            }
            ans.setText(answer);
            dis.setText(discription);

        }
        if (sps.getInt(Opposite_word.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            //load_addcontent(context, frame_layout2);
        }

        openDialog_odd_man.show();
    }


    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Opposite_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);

        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Opposite_word.this, "date");
        if (date.equals("0")) {
            next_game.setText("எதிர்சொல்லை கன்டுபிடி தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("எதிர்சொல்லை கன்டுபிடி புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");
        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Clue_Game_Hard.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Opposite_word.this, "game_area", "on");
                    finish();
                }
                sps.putString(Opposite_word.this, "date", "0");
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
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Opposite_word.class);
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
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Ote_to_Tamil.class);
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


        seerpaduthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Riddle_game.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, WordError_correction.class);
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
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });

        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, English_to_tamil.class);
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
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Find_words_from_picture.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Quiz_Game.class);
                startActivity(i);
            }
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Opposite_word.this);
        TextView jamble_words = (TextView) openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Jamble_word_game.class);
                startActivity(i);
            }
        });
        TextView missing_words = (TextView) openDialog.findViewById(R.id.missing_words);
        Cursor jmps;
        jmps = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='0' order by id limit 1");
        jmps.moveToFirst();
        if (jmps.getCount() != 0) {
            missing_words.setVisibility(View.VISIBLE);
        }
        missing_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Missing_Words.class);
                startActivity(i);
            }
        });
        TextView six_differences = (TextView) openDialog.findViewById(R.id.six_differences);
        Cursor dif;
        dif = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='0' order by id limit 1");
        dif.moveToFirst();
        if (dif.getCount() != 0) {
            six_differences.setVisibility(View.VISIBLE);
        }
        six_differences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Opposite_word.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, Find_difference_between_pictures.class);
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
                    Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Opposite_word.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                sps.putString(Opposite_word.this, "date", "0");

             /*   finish();
                openDialog.dismiss();
                //sps.putString(Odd_man_out.this, "date", "0");
                Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
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


        final String snd = sps.getString(Opposite_word.this, "snd");
        toggleButton = (TextView) popupView.findViewById(R.id.toggle);

        if (snd.equals("off")) {

            toggleButton.setBackgroundResource(R.drawable.off);

        } else if (snd.equals("on")) {

            toggleButton.setBackgroundResource(R.drawable.on);

        }

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snd = sps.getString(Opposite_word.this, "snd");
                System.out.println("*****click");
                if (snd.equals("off")) {
                    System.out.println("*****on");
                    sps.putString(Opposite_word.this, "snd", "on");
                    toggleButton.setBackgroundResource(R.drawable.on);
                    sv = 1;
                }
                if (snd.equals("on")) {
                    System.out.println("*****off");
                    sps.putString(Opposite_word.this, "snd", "off");
                    toggleButton.setBackgroundResource(R.drawable.off);
                    sv = 0;
                }
            }
        });
        popupWindow.showAsDropDown(p_setting, 50, -10);
    }

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Opposite_word.this, "date");
        System.out.println("######################Timer ttstop"+ttstop);
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
            newhelper2.executeSql("UPDATE newmaintable2 SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(Opposite_word.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                helpshare(a);
            }else {
                if (sps.getString(Opposite_word.this, "permission_grand").equals("")) {
                    sps.putString(Opposite_word.this, "permission_grand", "yes");
                    //  First_register("yes");
                    AlertDialog alertDialog = new AlertDialog.Builder(Opposite_word.this).create();
                    alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர பின்வரும் permission-யை allow செய்யவேண்டும்");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if ((ContextCompat.checkSelfPermission(Opposite_word.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(Opposite_word.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                                    } else {
                                        helpshare(a);
                                    }
                                }
                            });

                    alertDialog.show();

                } else {
                    if ((ContextCompat.checkSelfPermission(Opposite_word.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(Opposite_word.this, "permission") == 2) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Opposite_word.this).create();
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
                                            String date = sps.getString(Opposite_word.this, "date");
                                            int pos;
                                            Cursor cs;
                                            long dscore = 0;
                                            int noofclue = 0;
                                            if (date.equals("0")) {
                                                pos = 1;
                                                cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "'");
                                                cs.moveToFirst();
                                                if (cs.getCount() != 0) {
                                                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                                                }
                                            } else {
                                                pos = 2;
                                                cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + questionid + "'");
                                                cs.moveToFirst();
                                                if (cs.getCount() != 0) {
                                                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                                                }
                                            }
                                            if (sps.getString(Opposite_word.this, "odd_time_start").equals("")) {

                                                System.out.println("######################Timer not");
                                            } else {
                                                System.out.println("######################Timer start"+dscore);
                                                focus.setBase(SystemClock.elapsedRealtime() + dscore);
                                                focus.start();
                                            }
                                            dialog.dismiss();
                                        }
                                    });


                            alertDialog.show();
                        } else {
                            if ((ContextCompat.checkSelfPermission(Opposite_word.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(Opposite_word.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                            } else {
                                helpshare(a);
                            }
                        }
                    } else {
                        if ((ContextCompat.checkSelfPermission(Opposite_word.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(Opposite_word.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 152) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Opposite_word.this, "permission", 1);
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
                    boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!showRationale) {
                        sps.putInt(Opposite_word.this, "permission", 2);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Opposite_word.this, "permission", 0);
                    }
                }
            }
        }
    }




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
                titt.setText(" தமிழ் குறுக்கெழுத்துப்போட்டி ");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.crossword_logo);
            } else {
                titt.setText("நித்ரா  செயலிகள்");
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
                titt.setText(" தமிழ் குறுக்கெழுத்துப்போட்டி ");
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
                titt.setText(" தமிழ் குறுக்கெழுத்துப்போட்டி ");
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
    @Override
    public void onDestroy() {
        super.onDestroy();
       // uiHelper.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
    }

    public void showcase_dismiss(){
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sps.getString(Opposite_word.this,"showcase_dismiss_opp").equals(""))
                {
                    showcase_dismiss();
                }else {
                    sps.putString(Opposite_word.this, "odd_time_start", "yes");
                    sps.putString(Opposite_word.this, "opp_intro", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }

            }
        }, 800);
    }
    public void price_update() {
        ////////////////Prize//////////////////
        //Time Setting For Clue Game
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;

        String date = sps.getString(Opposite_word.this, "date");
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                System.out.println("###################random"+random);
                if (random == 0) {
                    prize_data_update(Opposite_word.this, 75);
                } else {
                    prize_data_update(Opposite_word.this, 25);
                }
            } else if (timeElapsed > 91300) {
                if (random == 0) {
                    prize_data_update(Opposite_word.this, 50);
                } else {
                    prize_data_update(Opposite_word.this, 25);
                }
            } else {
                prize_data_update(Opposite_word.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                if (random == 0) {
                    prize_data_update(Opposite_word.this, 100);
                } else {
                    prize_data_update(Opposite_word.this, 50);
                }
            } else if (timeElapsed > 91300) {
                if (random == 0) {
                    prize_data_update(Opposite_word.this, 75);
                } else {
                    prize_data_update(Opposite_word.this, 50);
                }
            } else {
                prize_data_update(Opposite_word.this, 50);
            }
        }
        ////////////////Prize//////////////////
    }
    public void downloaddata_regular() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Opposite_word.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Opposite_word.this)) {
                    download_datas();
                } else {
                    NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                    native_banner_ad_container.setVisibility(View.INVISIBLE);
                    head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Opposite_word.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                            .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                    sps.putInt(Opposite_word.this, "goto_sett", 1);


                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    sps.putString(Opposite_word.this, "game_area", "on");
                                    String date = sps.getString(Opposite_word.this, "date");
                                    if (date.equals("0")) {
                                        if (main_act.equals("")) {
                                            finish();
                                            Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                                            startActivity(i);
                                        } else {
                                            finish();
                                        }
                                    }else {
                                        if (date.equals("0")) {
                                            backexitnet();
                                        } else {
                                            backexitnet();
                                        }
                                    }
                                   /* Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
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
               /* Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                startActivity(i);*/
                sps.putString(Opposite_word.this, "game_area", "on");
                String date = sps.getString(Opposite_word.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                }else {
                    finish();
                    Intent i = new Intent(Opposite_word.this, New_Main_Activity.class);
                    startActivity(i);
                }
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    private void download_datas() {
        Cursor cz = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d="";
        cz.moveToFirst();
        if (cz.getCount()!=0){
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("questionid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Opposite_word.this,questionid_d,gameid);
        download_data_server.execute();
    }



    public void rewarded_ad(){
        rewardedAd = MaxRewardedAd.getInstance( getResources().getString(R.string.Reward_Ins), this );
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
                fb_reward=1;
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                rewarded_ad();
                if (reward_status==1){
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
                }else {
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
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                rewardedAd.loadAd();
            }
        });
        rewardedAd.loadAd();
    }


   /* public void reward(final Context context) {
        rewardedVideoAd = new RewardedVideoAd(context, getString(R.string.fb_rewarded_ins));
       *//* rewardedVideoAd.setAdListener(new RewardedVideoAdListener() {

            @Override
            public void onRewardedVideoCompleted() {
                reward_status=1;
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onRewardedVideoClosed() {
                reward(context);
                if (reward_status==1){
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
                }else {
                    Toast.makeText(context, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
                }

                fb_reward = 0;
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                //Toast.makeText(context, ""+adError.getErrorCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                fb_reward = 1;
            }

            @Override
            public void onAdClicked(Ad ad) {

            }
        });
        rewardedVideoAd.loadAd();*//*
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                // Rewarded video ad failed to load

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                fb_reward = 1;


            }

            @Override
            public void onAdClicked(Ad ad) {
                // Rewarded video ad clicked

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Rewarded Video ad impression - the event will fire when the
                // video starts playing

            }

            @Override
            public void onRewardedVideoCompleted() {
                reward_status = 1;

                // Rewarded Video View Complete - the video has been played to the end.
                // You can use this event to initialize your reward


                // Call method to give reward
                // giveReward();
            }

            @Override
            public void onRewardedVideoClosed() {
                reward(context);
                if (reward_status==1){
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
                }else {
                    Toast.makeText(context, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
                }

                fb_reward = 0;
            }
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());
    }*/

}
