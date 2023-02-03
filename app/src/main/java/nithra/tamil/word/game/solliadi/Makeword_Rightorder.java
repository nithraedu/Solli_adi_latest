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
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

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
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.os.Bundle;
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








import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;


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
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native_Ragasiya_sorgal_Native_Banner;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native_Senthamil_Thedal_Native_Banner;

public class Makeword_Rightorder extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Download_completed {

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

    private Makeword_Rightorder.PendingAction pendingAction = Makeword_Rightorder.PendingAction.NONE;

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void download_completed(String status) {
        System.out.println("#############################status" + status);
        if (status.equals("nodata")) {
            nextgamesdialog();
        } else {
            next();
        }
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

  /*  private UiLifecycleHelper uiHelper;

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
    };
*/
    String btn_str = "";


    TextView c_time, score, to_no, question_txt;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    SharedPreference sps = new SharedPreference();
    String gameid = "9";
    String questionid, question, answer, split_word;
    Chronometer focus;
    TextView bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15, bt16;
    RelativeLayout w_head, helpshare_layout;
    TextView shareq, h_gplues, h_watts_app, h_facebook;
    TextView earncoin;
    TextView c_settings;
    EditText c_edit;
    int type = 0;
    SoundPool click, win, coin, worng;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    TextView c_verify, c_clear, ans_high, c_clue;
    TextView c_ans;
    TextView c_coin;
    int e2 = 0;
    Dialog openDialog_s;


    int share_name = 0;
    int setting_access = 0;
    public static FrameLayout add, add2, add3;
    static SharedPreference spd = new SharedPreference();
    Context context = this;
    RelativeLayout adsicon, adsicon2;
    CircleImageView ads_logo, ads_logo2;
    int loadaddcontent = 0;
    TextView next_continue;
    TextView ttscores;
    Typeface typ, tyr;



    private MaxInterstitialAd ins_game,game_exit_ins;
    static int ry;

    static int rvo = 0;
    static int mCoinCount=20;
    String retype = "s";

    long ttstop;

    LinearLayout adds, list4;
    LinearLayout qtw;
    Dialog openDialog_p;
    int s = 0;
    int f_sec;
    int r = 0;
    int daily_start = 0;

    int minmum = 1;
    int maximum = 3;
    int randomno;
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

    TextView coin_value;
    int minmumd = 1;
    int maximumd = 4;
    int randomnod;
    Dialog openDialog;
    int setval_vid;
    FirebaseAnalytics mFirebaseAnalytics;
    int dia_dismiss=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeword__rightorder);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);

        if (sps.getString(Makeword_Rightorder.this,"new_user_db").equals("")){

        }else {
            if (sps.getString(Makeword_Rightorder.this,"new_user_db").equals("on")){
                sps.putString(Makeword_Rightorder.this,"db_name_start","Tamil_Game2.db");
                Commen_string.dbs_name="Tamil_Game2.db";
            }else {
                sps.putString(Makeword_Rightorder.this,"db_name_start","Solli_Adi");
                Commen_string.dbs_name="Solli_Adi";
            }

        }

        myDbHelper = new DataBaseHelper(context);
        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);


        String gid = "9";
        String qid = "";
        for (int i = 1; i<=2499; i++){
            if (qid.equals("")){
                qid = "" +i;
            } else {
                qid = qid + "," + i;
            }
        }
        newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='9'");


     /*   exdb = myDbHelper.getReadableDatabase();
        dbs = newhelper.getReadableDatabase();
        dbn = newhelper2.getReadableDatabase();
        dbn2 = newhelper3.getReadableDatabase();
*/

        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Makeword_Rightorder.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Makeword_Rightorder.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Makeword_Rightorder.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Makeword_Rightorder.this, R.raw.coins, 1);
///
        ImageView prize_logo=(ImageView)findViewById(R.id.prize_logo);
        /*final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        prize_logo.startAnimation(pendulam);*/
        if (sps.getInt(Makeword_Rightorder.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sps.getString(Makeword_Rightorder.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Makeword_Rightorder.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Makeword_Rightorder.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Makeword_Rightorder.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Makeword_Rightorder.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Makeword_Rightorder.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        //reward(context);
        rewarded_ad();
        if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(Makeword_Rightorder.this).setMediationProvider("max");
            AppLovinSdk.initializeSdk(Makeword_Rightorder.this, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    industrialload_game();
                    game_exit_ins_ad();
                }
            });
        }

        find();
        //loads_ads_banner();

        adds = (LinearLayout) findViewById(R.id.ads_lay);
        if (sps.getInt(context, "purchase_ads") == 0) {
        if (Utils.isNetworkAvailable(Makeword_Rightorder.this)) {

            Ad_NativieUtils.load_add_facebook(this,getResources().getString(R.string.Ragasiya_sorgal_Native_Banner_new),adds);
        }else {
            adds.setVisibility(View.GONE);
        }
        } else {
            adds.setVisibility(View.GONE);
        }

        // next();


        if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(context);
           /**/
        }


        openDialog_s = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        ads_logo = (CircleImageView) openDialog_s.findViewById(R.id.ads_logo);
        adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);

        /////////


        if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {
        } else {
           // advancads();
        }

        ////////

        /////////

        if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {
        } else {
            //advancads_content();
        }

        ////////
        if (sps.getInt(Makeword_Rightorder.this,"reward_coin_txt")==0){
            sps.putInt(Makeword_Rightorder.this,"reward_coin_txt",20);
        }


        //loadRewardedVideoAd();

       // uiHelper = new UiLifecycleHelper(this, callback);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                .addScope(Drive.SCOPE_APPFOLDER) // SavedGames
                .build();


        String snd = sps.getString(Makeword_Rightorder.this, "snd");

        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings,null);
        c_settings = (TextView) findViewById(R.id.c_settings);
        if (snd.equals("off")) {
            //  toggleButton.setBackgroundResource(R.drawable.off);
            c_settings.setBackgroundResource(R.drawable.sound_off);
            sv = 0;
            //
            //sounds
        } else if (snd.equals("on")) {
            c_settings.setBackgroundResource(R.drawable.sound_on);

            sv = 1;
            //
        }

        helpshare_layout = (RelativeLayout) findViewById(R.id.helpshare_layout);


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
                sps.putString(Makeword_Rightorder.this, "date", message);
                next();
            } else {
                sps.putString(Makeword_Rightorder.this, "date", "0");
                next();
            }
        } else {
            sps.putString(Makeword_Rightorder.this, "date", "0");
            next();
        }


        if (sps.getString(Makeword_Rightorder.this, "mk_word_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Makeword_Rightorder.this, "sequence example mkword");

            sequence.setConfig(config);

            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            // sequence.addSequenceItem(verify, "சரிபார்க்க பொத்தானை அழுத்தி விடையை சரிபார்த்துக்கொள்ளவும்.", "அடுத்து");

            //  sequence.addSequenceItem(ex_bones, "தொடர்ந்து சரியான  10 விடைகளை கண்டுபிடித்தால், கூடுதல் விடைகளை நாணயங்கள் குறையாமல் அறிந்து கொள்ளலாம்.", "அடுத்து");


            //   sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");


            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Makeword_Rightorder.this)
                    .setTarget(helpshare_layout)
                    .setDismissText("சரி")
                    .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {

                            if (position == 1) {
                                sps.putString(Makeword_Rightorder.this, "mak_time_start", "yes");
                                sps.putString(Makeword_Rightorder.this, "showcase_dismiss_ro", "yes");

                                focus.setBase(SystemClock.elapsedRealtime());
                                focus.start();

                            }
                        }
                    });
            sequence.start();
            sps.putString(Makeword_Rightorder.this, "mk_word_intro", "no");

        }

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c1.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt1.startAnimation(shake);
                String ts = bt1.getText().toString();
                c_edit.append(ts);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c2.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt2.startAnimation(shake);
                String ts = bt2.getText().toString();
                c_edit.append(ts);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt3.startAnimation(shake);
                String ts = bt3.getText().toString();
                c_edit.append(ts);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c4.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt5.startAnimation(shake);
                String ts = bt5.getText().toString();
                c_edit.append(ts);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt6.startAnimation(shake);
                String ts = bt6.getText().toString();
                c_edit.append(ts);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt7.startAnimation(shake);
                String ts = bt7.getText().toString();
                c_edit.append(ts);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c7.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt9.startAnimation(shake);
                String ts = bt9.getText().toString();
                c_edit.append(ts);
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c8.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt10.startAnimation(shake);
                String ts = bt10.getText().toString();
                c_edit.append(ts);
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c9.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt11.startAnimation(shake);
                String ts = bt11.getText().toString();
                c_edit.append(ts);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c10.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt4.startAnimation(shake);
                String ts = bt4.getText().toString();
                c_edit.append(ts);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c11.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt8.startAnimation(shake);
                String ts = bt8.getText().toString();
                c_edit.append(ts);
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c12.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt12.startAnimation(shake);
                String ts = bt12.getText().toString();
                c_edit.append(ts);
            }
        });
        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c13.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt13.startAnimation(shake);
                String ts = bt13.getText().toString();
                c_edit.append(ts);
            }
        });
        bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c14.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt14.startAnimation(shake);
                String ts = bt14.getText().toString();
                c_edit.append(ts);
            }
        });
        bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c15.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt15.startAnimation(shake);
                String ts = bt15.getText().toString();
                c_edit.append(ts);
            }
        });
        bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.button_shake);
                bt16.startAnimation(shake);
                String ts = bt16.getText().toString();
                c_edit.append(ts);
            }
        });

        c_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(Makeword_Rightorder.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(Makeword_Rightorder.this, "snd", "on");
                    c_settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(Makeword_Rightorder.this, "snd", "off");
                    c_settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        c_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                cfw.moveToFirst();
                if (cfw.getCount() != 0) {
                    int sk = cfw.getInt(cfw.getColumnIndex("coins"));
                    r = 0;
                    if (sk >= 50) {
                        if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                            Cursor cd;
                            String date = sps.getString(Makeword_Rightorder.this, "date");
                            if (date.equals("0")) {
                                cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "' and isfinish='0' and gameid='" + gameid + "'");
                                cd.moveToFirst();
                            } else {
                                cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "' and gameid='" + gameid + "'  and daily='0'");
                                cd.moveToFirst();
                            }

                            String sa = cd.getString(cd.getColumnIndex("answer"));
                            //Toast.makeText(Clue_Game_Hard.this, "" + sa, Toast.LENGTH_SHORT).show();
                            //Score Adding
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            if (cfx.getCount() != 0) {
                                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                int spx = skx - 50;
                                String aStringx = Integer.toString(spx);
                                score.setText(aStringx);
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            }

                            c_ans.setBackgroundResource(R.drawable.tick_background);
                            c_ans.setEnabled(false);
                            //
                            sps.putInt(getApplicationContext(), "ach6_a1", 0);

                            //bulb invisible
                      /*  c_clue.clearAnimation();
                        c_clue.setVisibility(View.INVISIBLE);*/

                            //
                            list4.setVisibility(View.INVISIBLE);

                            Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                            ans_high.startAnimation(w_game);
                            ans_high.setVisibility(View.VISIBLE);
                            ans_high.setText(sa);
                            //Update QST
                            String datee = sps.getString(Makeword_Rightorder.this, "date");
                            if (datee.equals("0")) {
                                newhelper3.executeSql("UPDATE right_order SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                            } else {
                                newhelper3.executeSql("UPDATE right_order SET daily=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                            }
                            //Next Function
                            //  r = 1;


                            focus.stop();
                            // completegame();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    setSc();
                                }
                            }, 3000);

                        } else {
                            final Dialog openDialog = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                            openDialog.setContentView(R.layout.show_ans);
                            TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                            TextView no = (TextView) openDialog.findViewById(R.id.no);
                            TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);
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
                                    Cursor cd;
                                    String date = sps.getString(Makeword_Rightorder.this, "date");
                                    if (date.equals("0")) {
                                        cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "' and isfinish='0' and gameid='" + gameid + "'");

                                        cd.moveToFirst();
                                    } else {
                                        cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "' and gameid='" + gameid + "'  and daily='0'");
                                        cd.moveToFirst();
                                    }
                                    String sas = null;
                                    if (cd.getCount() != 0) {
                                        sas = cd.getString(cd.getColumnIndex("answer"));
                                    }
                                    //Toast.makeText(Clue_Game_Hard.this, "" + sa, Toast.LENGTH_SHORT).show();
                                    //Score Adding
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    if (cfx.getCount() != 0) {
                                        int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                        int spx = skx - 50;
                                        String aStringx = Integer.toString(spx);
                                        score.setText(aStringx);
                                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    }

                                    c_ans.setBackgroundResource(R.drawable.tick_background);
                                    c_ans.setEnabled(false);
                                    //
                                    sps.putInt(getApplicationContext(), "ach6_a1", 0);

                                    //bulb invisible


                                    //
                                    list4.setVisibility(View.INVISIBLE);

                                    Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                                    ans_high.startAnimation(w_game);
                                    ans_high.setVisibility(View.VISIBLE);
                                    ans_high.setText(sas);
                                    //Update QST
                                    String datee = sps.getString(Makeword_Rightorder.this, "date");
                                    if (datee.equals("0")) {
                                        newhelper3.executeSql("UPDATE right_order SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                                    } else {
                                        newhelper3.executeSql("UPDATE right_order SET daily=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                                    }
                                    //Next Function
                                    // r = 1;
                                    openDialog.dismiss();

                                    focus.stop();
                                    //    completegame();

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            setSc();
                                        }
                                    }, 3000);


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

            }
        });

        c_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = c_edit.getText().toString();
                Log.e("to value", answer);
                System.out.println("==========value" + answer);
                String dates = sps.getString(Makeword_Rightorder.this, "date");
                Cursor cd;
                if (dates.equals("0")) {
                    cd = newhelper3.getQry("SELECT * FROM right_order where answer ='" + answer + "' and isfinish='0' and questionid='" + questionid + "' and gameid='" + gameid + "'");
                    System.out.println("============results" + "SELECT * FROM right_order where answer ='" + answer + "' and isfinish='0' and questionid='" + questionid + "' and gameid='" + gameid + "'");
                    cd.moveToFirst();
                } else {
                    cd = newhelper3.getQry("SELECT * FROM right_order where answer ='" + answer + "' and daily='0' and questionid='" + questionid + "' and gameid='" + gameid + "'");
                    cd.moveToFirst();
                }

                if (cd.getCount() != 0) {
                    win.play(soundId3, sv, sv, 0, 0, sv);
                    ry = 1;
                    //Toast.makeText(Clue_Game_Hard.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    c_ans.setBackgroundResource(R.drawable.tick_background);
                    c_ans.setEnabled(false);
                    list4.setVisibility(View.INVISIBLE);
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(answer);

                    String date = sps.getString(Makeword_Rightorder.this, "date");
                    if (date.equals("0")) {
                        newhelper3.executeSql("UPDATE right_order SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    } else {
                        newhelper3.executeSql("UPDATE right_order SET daily=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    }

                    focus.stop();
                    coinanim();
                    price_update();
                }
            }
        });
        c_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });

        c_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c_edit.setText("");
                return false;
            }
        });

        earncoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });
        qtw.setOnClickListener(new View.OnClickListener() {
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


    }

    private void loads_ads_banner() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            adds.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Makeword_Rightorder.this)){
                fb_native_Ragasiya_sorgal_Native_Banner(Makeword_Rightorder.this,native_banner_ad_container);
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

        }
    }

    private void next() {

        c_edit.setText("");
        ans_high.setVisibility(View.GONE);
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        list4.setVisibility(View.VISIBLE);
        c_ans.setEnabled(true);
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        }else {
            if (Utils.isNetworkAvailable(Makeword_Rightorder.this)){
                native_banner_ad_container.setVisibility(View.VISIBLE);
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }
        w_head.setVisibility(View.VISIBLE);


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

        if (sps.getString(Makeword_Rightorder.this, str_date1).equals("")) {

           daily_bones();
            sps.putString(Makeword_Rightorder.this, str_date1, "yes");

        }

        if (daily_start == 1) {
            Toast.makeText(Makeword_Rightorder.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான சீர்படுத்து விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
            sps.putString(context, "date", "0");
            daily_start = 0;
        }

        String date = sps.getString(Makeword_Rightorder.this, "date");
        if (date.equals("0")) {
            Cursor c1 = newhelper3.getQry("select * from right_order where gameid='" + gameid + "'");
            c1.moveToFirst();
            Cursor c2 = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            to_no.setText(no/*+"/"+c1.getCount()*/);
        } else {
            if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {

            }else {
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
            c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            daily_start = 1;
            c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and daily='0'  order by random() limit 1");
            c.moveToFirst();
        }

        if (c.getCount() != 0) {
            questionid = c.getString(c.getColumnIndex("questionid"));
            question = c.getString(c.getColumnIndex("question"));
            answer = c.getString(c.getColumnIndex("answer"));
            split_word = c.getString(c.getColumnIndex("splitword"));
            int playtime = c.getInt(c.getColumnIndex("playtime"));

            question_txt.setText(question);
            ans_high.setText(answer);


            System.out.println("##############split_waord" + split_word);
            System.out.println("##############question" + question);
            System.out.println("##############questionid" + questionid);
            System.out.println("##############answer" + answer);

            String tfoption = split_word;
            String[] first = tfoption.split(",");
            type = first.length;

            //  Toast.makeText(context, "!!!!!!!!!!randomno"+randomno, Toast.LENGTH_SHORT).show();

            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;

            if (randomno == 1) {
                setgame();

                // simple();
            } else if (randomno == 2) {
                medium();
            } else if (randomno == 3) {
                medium1();
            }

            System.out.println("%%%%%%%%%%%%%% " + playtime);
            if (playtime == 0) {

                if (sps.getString(Makeword_Rightorder.this, "mak_time_start").equals("yes")) {
                    focus.setBase(SystemClock.elapsedRealtime() + playtime);
                    focus.start();
                }

            } else {
                focus.setBase(SystemClock.elapsedRealtime() + playtime);
                focus.start();


            }

        } else {
            downloaddata_regular2();
           // nextgamesdialog();
        }


    }

    private void daily_bones() {
         openDialog = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
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

        //TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);

        //TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);


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

            }
        });
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
        ea = 100;
        final int vals=reward_play_count*100;
        ea=ea+vals;
        coin_value.setText("" + ea);

        LinearLayout extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        System.out.println("############################^^^^^^^^^^^^^^currentdate"+str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate"+sps.getString(Makeword_Rightorder.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Makeword_Rightorder.this, "daily_bonus_date"))) {

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
                extra_coin_s = 1;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Makeword_Rightorder.this, "" + "Reward video", "Loading...");
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
                                    //reward(Makeword_Rightorder.this);
                                    rewarded_ad();
                                    Toast.makeText(Makeword_Rightorder.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }

            }
        });

                       /* b_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openDialog.dismiss();
                            }
                        });*/
        openDialog.show();
    }

    public void setgame() {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
        if (type == 1) {

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
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }
    }

    public void medium() {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
        if (type == 1) {

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
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            bt3.setText(word12);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }


    }

    public void medium1() {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
        if (type == 1) {

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
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt3.setText(letter6);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(word1);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(word2);
            bt11.setText(letter7);
            bt12.setText(letter13);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(letter15);
            bt3.setText(letter1);
            bt4.setText(letter4);
            bt5.setText(word3);
            bt6.setText(letter9);
            bt7.setText(word2);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word1);
            bt11.setText(word2);
            bt12.setText(letter2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt2.setText(word3);
            bt3.setText(letter10);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(letter7);
            bt7.setText(letter10);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);



              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt2.setText(letter4);
            bt3.setText(letter13);
            bt4.setText(word5);
            bt5.setText(word3);
            bt6.setText(letter3);
            bt7.setText(word4);
            bt8.setText(word1);
            bt9.setText(letter3);
            bt10.setText(letter6);
            bt11.setText(word2);
            bt12.setText(letter8);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter6);
            bt2.setText(word5);
            bt3.setText(letter4);
            bt4.setText(letter1);
            bt5.setText(word2);
            bt6.setText(letter9);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(word1);
            bt10.setText(letter8);
            bt11.setText(letter4);
            bt12.setText(word3);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word5);
            bt2.setText(letter8);
            bt3.setText(letter4);
            bt4.setText(letter11);
            bt5.setText(word7);
            bt6.setText(word2);
            bt7.setText(word4);
            bt8.setText(word3);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt2.setText(word8);
            bt3.setText(letter6);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt4.setText(word9);
            bt5.setText(word5);
            bt6.setText(letter3);
            bt7.setText(word3);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word8);
            bt11.setText(letter6);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            bt2.setText(word10);
            bt3.setText(word8);
            bt4.setText(word1);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter3);
            bt8.setText(word4);
            bt9.setText(word7);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word2);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word4);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word7);
            bt8.setText(word11);
            bt9.setText(word6);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word10);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            bt3.setText(word12);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }

    }

    private void find() {
        to_no = (TextView) findViewById(R.id.c_word_number);
        question_txt = (TextView) findViewById(R.id.question_txt);
        focus = (Chronometer) findViewById(R.id.c_time_edit);
        c_clear = (TextView) findViewById(R.id.clue_clear);
        c_ans = (TextView) findViewById(R.id.c_ans);
        c_coin = (TextView) findViewById(R.id.c_coins);
        ans_high = (TextView) findViewById(R.id.ans_highlite);

        bt1 = (TextView) findViewById(R.id.c_button1);
        bt2 = (TextView) findViewById(R.id.c_button2);
        bt3 = (TextView) findViewById(R.id.c_button3);
        bt4 = (TextView) findViewById(R.id.c_button4);
        bt5 = (TextView) findViewById(R.id.c_button5);
        bt6 = (TextView) findViewById(R.id.c_button6);
        bt7 = (TextView) findViewById(R.id.c_button7);
        bt8 = (TextView) findViewById(R.id.c_button8);
        bt9 = (TextView) findViewById(R.id.c_button9);
        bt10 = (TextView) findViewById(R.id.c_button10);
        bt11 = (TextView) findViewById(R.id.c_button11);
        bt12 = (TextView) findViewById(R.id.c_button12);
        bt13 = (TextView) findViewById(R.id.c_button13);
        bt14 = (TextView) findViewById(R.id.c_button14);
        bt15 = (TextView) findViewById(R.id.c_button15);
        bt16 = (TextView) findViewById(R.id.c_button16);

        earncoin = (TextView) findViewById(R.id.earncoin);
        w_head = (RelativeLayout) findViewById(R.id.clue_head);
        h_gplues = (TextView) findViewById(R.id.ch_gplues);
        h_watts_app = (TextView) findViewById(R.id.ch_watts_app);
        h_facebook = (TextView) findViewById(R.id.ch_facebook);
        to_no = (TextView) findViewById(R.id.c_word_number);
        score = (TextView) findViewById(R.id.c_score_edit);
        focus = (Chronometer) findViewById(R.id.c_time_edit);
        c_settings = (TextView) findViewById(R.id.c_settings);
        c_edit = (EditText) findViewById(R.id.clue_ans_editer);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        qtw = (LinearLayout) findViewById(R.id.qwt);


        list4 = (LinearLayout) findViewById(R.id.list4);


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
        c_edit.setText("");


        c_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);
            }
        });
        c_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);

                return true;
            }
        });


        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        if (cfq.getCount() != 0) {
            int skq = cfq.getInt(cfq.getColumnIndex("coins"));
            String tr = String.valueOf(skq);
            score.setText(tr);
        }


    }

    private void pressKey(int keycode) {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        c_edit.onKeyDown(keycode, event);
    }

    public void coinanim() {
////

        //score intial

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndex("coins"));
        String tr = String.valueOf(skq);
        score.setText(tr);
        //
        e2 = skq;
        //

        coin.play(soundId4, sv, sv, 0, 0, sv);
        c_coin.setVisibility(View.VISIBLE);
        int[] locationInWindow = new int[2];
        c_coin.getLocationInWindow(locationInWindow);
        int[] locationOnScreen = new int[2];
        c_coin.getLocationOnScreen(locationOnScreen);
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
        c_coin.startAnimation(transAnimation);
        c_coin.postDelayed(new Runnable() {
            @Override
            public void run() {
                c_coin.setVisibility(View.INVISIBLE);
            }
        }, transAnimation.getDuration());


        ////

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                coin.play(soundId4, sv, sv, 0, 0, sv);
                c_coin.setVisibility(View.VISIBLE);
                int[] locationInWindow = new int[2];
                c_coin.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                c_coin.getLocationOnScreen(locationOnScreen);
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
                c_coin.startAnimation(transAnimation);
                c_coin.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        c_coin.setVisibility(View.INVISIBLE);
                    }
                }, transAnimation.getDuration());
            }
        }, 1000);

        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                score.startAnimation(levels1);
            }
        }, 2200);

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

        Handler handler21 = new Handler();
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Score Setting
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
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
        }, 3000);
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
        final LinearLayout rewardvideo = (LinearLayout) openDialog_s.findViewById(R.id.rewardvideo);
        LinearLayout ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);
        LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);
        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sps.getInt(Makeword_Rightorder.this, "reward_coin_txt") + "+நாணயங்கள் பெற");
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Makeword_Rightorder.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        ImageView prize_logo=(ImageView)openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Makeword_Rightorder.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sps.getString(Makeword_Rightorder.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Makeword_Rightorder.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Makeword_Rightorder.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Makeword_Rightorder.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Makeword_Rightorder.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Makeword_Rightorder.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
           // New_Main_Activity.load_addFromMain_multiplayer(Makeword_Rightorder.this,ads_layout);
            if (Utils.isNetworkAvailable(Makeword_Rightorder.this)){
                //New_Main_Activity.load_add_fb_rect_score_screen(Makeword_Rightorder.this, ads_layout);
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


        word.setTypeface(tyr);
        next_continue.setVisibility(View.VISIBLE);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);
        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(Makeword_Rightorder.this, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndex("coins"));
            String aStringx = Integer.toString(skx);
            ttscores.setText(aStringx);
        }

/////////////////////////////////////////Reward tittle////////////////////////////////////////////
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;
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

/////////////////////////////////////////Reward tittle////////////////////////////////////////////

        if (sps.getString(Makeword_Rightorder.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Makeword_Rightorder.this, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }

        if (ry == 1) {

        } else {
            rewardvideo.setVisibility(View.INVISIBLE);
        }
        ads_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        RelativeLayout adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);
        //final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);

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

                        String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivity(Intent.createChooser(i, "Share via"));
                        startActivityForResult(Intent.createChooser(i, "Share via"), 21);

/*

                        if (sps.getString(Makeword_Rightorder.this,"watts_app_s").equals(""))
                        {
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Score Adding
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                    int spx = skx + 20;
                                    String aStringx = Integer.toString(spx);
                                    score.setText(aStringx);
                                    ttscores.setText(aStringx);
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                                    sps.putString(Makeword_Rightorder.this,"watts_app_s","yes");

                                }
                            }, 3000);
                        }
*/


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

                        String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
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


        next_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sps.getInt(Makeword_Rightorder.this, "purchase_ads") == 1) {
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();
                } else {

                    sps.putInt(getApplicationContext(), "cluetime", 0);
                    if (sps.getInt(getApplicationContext(), "ins_ad_new") == 4) {
                        sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if(ins_game == null || !ins_game.isReady()) {
                                dia_dismiss = 1;
                                openDialog_s.dismiss();
                                next();
                                industrialload_game();
                                return;
                            }
                            else{
                                ins_game.showAd();
                            }


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
                    //advancads_content();
                  //  advancads();
                }

                //noclue=0;

            }
        });


        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss!=1){
                    sps.putString(Makeword_Rightorder.this,"game_area","on");
                        String date = sps.getString(Makeword_Rightorder.this, "date");
                        if (date.equals("0")) {
                            if (main_act.equals("")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                finish();
                                openDialog_s.dismiss();
                            }
                        } else {
                            if (sps.getString(Makeword_Rightorder.this, "Exp_list").equals("on")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(Makeword_Rightorder.this, Expandable_List_View.class);
                                startActivity(i);

                            } else {
                                if (main_act.equals("")) {
                                    finish();
                                    openDialog_s.dismiss();
                                    Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else {
                                    finish();
                                    openDialog_s.dismiss();
                                }
                            }


                    }

                }else {
                    dia_dismiss=0;
                }

            }
        });

        if (!isFinishing()) {
            openDialog_s.show();
        }

    }




    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }







    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Makeword_Rightorder.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }




    //reward videos***********************//



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

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
                titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
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

        ins_game = new MaxInterstitialAd(getResources().getString(R.string.Ragasiya_sorgal_ins), this);
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

    public void vidcoinearn() {

        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea=ea+setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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


    public void dialog(int i) {
        final Dialog openDialog_earncoin = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }
        RelativeLayout video = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnvideo);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 1;
                extra_coin_s = 0;
                if (Utils.isNetworkAvailable(Makeword_Rightorder.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Makeword_Rightorder.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        if (rewardedAd.isReady()){
                            rewardedAd.showAd();
                        }

                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                        //reward(Makeword_Rightorder.this);
                        rewarded_ad();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(Makeword_Rightorder.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(Makeword_Rightorder.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }


             /*   if (Utils.isNetworkAvailable(getApplicationContext())) {
                    if (interstitialAd_game != null) {
                        if (interstitialAd_game.isLoaded()) {
                            interstitialAd_game.show();
                            interstitialAd_game.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {


                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                    int spx = skx + 50;
                                    String aStringx = Integer.toString(spx);
                                    score.setText(aStringx);
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                                    ins_video();

                                }


                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "பிறகு முயற்ச்சிக்கவும் .", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

*/


            }
        });

        wp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        openDialog_earncoin.cancel();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");
                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 12);

                      /*  if (sps.getString(Clue_Game_Hard.this,"watts_app").equals(""))
                        {
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Score Adding
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                    int spx = skx + 20;
                                    String aStringx = Integer.toString(spx);
                                    score.setText(aStringx);
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                                    sps.putString(Clue_Game_Hard.this, "watts_app", "yes");

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
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  if (isNetworkAvailable()) {

                    openDialog_earncoin.cancel();
                    btn_str = "invite";
                    if (isLoggedIn()) {
                        Bundle params = new Bundle();
                        params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        showDialogWithoutNotificationBarInvite("apprequests", params);
                        // toast("yes");
                    } else {
                        openFacebookSession();
                        // toast("no");
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                } */  // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isNetworkAvailable()) {


                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Makeword_Rightorder.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                            //  myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");

                            // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        }
                        openDialog_earncoin.cancel();
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

    @Override
    protected void onStart() {
        super.onStart();
        //mGoogleApiClient.connect();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Makeword_Rightorder.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'  and daily='0'");
            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        }
    }


    protected void onResume() {
        super.onResume();

        if (!mGameOver && mGamePaused) {
            //resumeGame();
        }

        //uiHelper.onResume();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Makeword_Rightorder.this);
        mFirebaseAnalytics.setCurrentScreen(this, "Make Right order game", null);

        /*if (sps.getInt(Makeword_Rightorder.this, "addlodedd") == 1) {
            New_Main_Activity.load_addFromMain(Makeword_Rightorder.this, adds);
        }else {
            if (Utils.isNetworkAvailable(Makeword_Rightorder.this)) {
                adds = (LinearLayout) findViewById(R.id.ads_lay);
                sps.putInt(Makeword_Rightorder.this, "addlodedd", 2);
                System.out.println("@IMG");
                final AdView adView = new AdView(Makeword_Rightorder.this);
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



        if (sps.getString(Makeword_Rightorder.this, "mak_time_start").equals("")) {
            sps.putString(Makeword_Rightorder.this, "mak_time_start", "yes");
        } else {
            String date = sps.getString(Makeword_Rightorder.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                pos = 1;
                cs = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + questionid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                }
            } else {
                pos = 2;
                cs = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + questionid + "'  and daily='0'");
                cs.moveToFirst();
                if(cs.getCount()!=0) {
                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                }
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

    }


    public void onBackPressed() {
 /*   public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if(keyCode==KeyEvent.KEYCODE_BACK) {*/

        sps.putString(Makeword_Rightorder.this,"game_area","on");
        sps.putInt(Makeword_Rightorder.this, "addlodedd", 0);

            s = 1;
            openDialog_p = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.back_pess);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String dates = sps.getString(Makeword_Rightorder.this, "date");
                    int pos;
                    if (dates.equals("0")) {
                        pos = 1;
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                        //     myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                        //    myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    }

                        String date = sps.getString(Makeword_Rightorder.this, "date");
                        if (date.equals("0")) {
                            if (main_act.equals("")) {
                                finish();
                                Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                finish();
                            }
                        } else {
                            if (sps.getString(Makeword_Rightorder.this, "Exp_list").equals("on")) {
                                finish();
                                Intent i = new Intent(Makeword_Rightorder.this, Expandable_List_View.class);
                                startActivity(i);
                            } else {
                                if (main_act.equals("")) {
                                    finish();
                                    Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
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
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    openDialog_p.dismiss();
                }
            });
            openDialog_p.show();


        // return super.onKeyDown(keyCode, event);
    }


    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Makeword_Rightorder.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'  and daily='0'");

            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(Makeword_Rightorder.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                helpshare(a);
            }else {
                if (sps.getString(Makeword_Rightorder.this, "permission_grand").equals("")) {
                    sps.putString(Makeword_Rightorder.this, "permission_grand", "yes");
                    //  First_register("yes");
                    AlertDialog alertDialog = new AlertDialog.Builder(Makeword_Rightorder.this).create();
                    alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர  பின்வரும் permission-யை  allow செய்யவேண்டும்");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if ((ContextCompat.checkSelfPermission(Makeword_Rightorder.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(Makeword_Rightorder.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                                    } else {
                                        helpshare(a);
                                    }
                                }
                            });

                    alertDialog.show();

                } else {
                    if ((ContextCompat.checkSelfPermission(Makeword_Rightorder.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(Makeword_Rightorder.this, "permission") == 2) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Makeword_Rightorder.this).create();
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

                                            if (sps.getString(Makeword_Rightorder.this, "mak_time_start").equals("")) {
                                                sps.putString(Makeword_Rightorder.this, "mak_time_start", "yes");
                                            } else {
                                                String date = sps.getString(Makeword_Rightorder.this, "date");
                                                int pos;
                                                Cursor cs;
                                                long dscore = 0;
                                                int noofclue = 0;
                                                if (date.equals("0")) {
                                                    pos = 1;
                                                    cs = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + questionid + "'");
                                                    cs.moveToFirst();
                                                    if (cs.getCount() != 0) {
                                                        dscore = cs.getInt(cs.getColumnIndex("playtime"));
                                                    }
                                                } else {
                                                    pos = 2;
                                                    cs = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + questionid + "'  and daily='0'");
                                                    cs.moveToFirst();
                                                    if(cs.getCount()!=0) {
                                                        dscore = cs.getInt(cs.getColumnIndex("playtime"));
                                                    }
                                                }
                                                focus.setBase(SystemClock.elapsedRealtime() + dscore);
                                                focus.start();
                                            }
                                            dialog.dismiss();
                                        }
                                    });


                            alertDialog.show();
                        } else {
                            if ((ContextCompat.checkSelfPermission(Makeword_Rightorder.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(Makeword_Rightorder.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                            } else {
                                helpshare(a);
                            }
                        }
                    } else {
                        if ((ContextCompat.checkSelfPermission(Makeword_Rightorder.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(Makeword_Rightorder.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
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

    private void helpshare(String a) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Bitmap bitmap = Bitmap.createBitmap(w_head.getWidth(), w_head.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        w_head.draw(canvas);


        String root = getFilesDir().toString();
        File mydir = new File(root + "/Nithra/Solli_Adi");
        mydir.mkdirs();
        String fname = "Solli_Adi.jpg";
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
                    String date = sps.getString(Makeword_Rightorder.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                        //myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                        // myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    }

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Makeword_Rightorder.this, Makeword_Rightorder.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் சீர்படுத்து இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT,
                            "Solli_Adi");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);

        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Makeword_Rightorder.this)) {
                download_datas();
            } else {
                NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                native_banner_ad_container.setVisibility(View.INVISIBLE);
                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Makeword_Rightorder.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                sps.putInt(Makeword_Rightorder.this, "goto_sett", 1);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String date = sps.getString(Makeword_Rightorder.this, "date");
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

        if (requestCode == 15) {
            if (resultCode == -1) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                int spx = skx + 10;
                String aStringx = Integer.toString(spx);
                //  score.setText(aStringx);
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

                if (sps.getString(Makeword_Rightorder.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Makeword_Rightorder.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Makeword_Rightorder.this, "complite_reg").equals("yes")) {
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
        if (requestCode == 16) {
            if (resultCode == -1) {
            /*    if (sps.getString(Clue_Game_Hard.this, "gplues").equals("yes")) {

                    sps.putString(Clue_Game_Hard.this, "gplues", "no");

                }*/
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
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

                if (sps.getString(Makeword_Rightorder.this, "complite_reg").equals("yes")) {
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


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Makeword_Rightorder.this, "permission", 1);
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
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Makeword_Rightorder.this, "permission", 2);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Makeword_Rightorder.this, "permission", 0);
                    }
                }
            }

        }
    }


    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndex("coins"));
/*        int spx = skx + a;
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
        final Dialog openDialog = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndex("coins"));
/*        int spx = skx + a;
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

/*
    public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());
    }

    private void showDialogWithoutNotificationBarInvite(String action, Bundle params) {
        final WebDialog dialog = new WebDialog.Builder(Makeword_Rightorder.this,
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
                                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                int spx = (values.size() - 1) * 10;
                                String aStringx = Integer.toString(spx + skx);
                                // score.setText(aStringx);
                                myDbHelper.executeSql("UPDATE score SET coins='" + (spx + skx) + "'");
                                share_earn(spx);
                                //Toast.makeText(Clue_Game_Hard.this, "கூடுதல் நாணயங்கள்  "+spx+"  வழங்கப்பட்டது.தற்போது உங்களது மொத்த நாணயங்கள்"+ (spx + skx)+"", Toast.LENGTH_SHORT).show();

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

                                if (sps.getString(Makeword_Rightorder.this, "complite_reg").equals("yes")) {
                                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
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
    }

    private void publishFeedDialog() {
        Bundle params = new Bundle();
        params.putString("name", "சொல்லிஅடி");
        // params.putString("caption", "");


        params.putString("name", "சொல்லிஅடி");
        // params.putString("message", "my_message");
        params.putString("link", "https://goo.gl/CcA9a8");
        params.putString("description", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் +\n" +
                "                             விளையாட இங்கே கிளிக் செய்யவும் ");
        params.putString("caption", "நான் சொல்லிஅடி செயலியில் குறிப்பு மூலம் கண்டுபிடி  நிலை " + to_no.getText().toString() + " ஐ முடித்துள்ளேன்");


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



                             */
/*   if (sps.getString(Clue_Game_Hard.this, "face_share").equals("")) {

                                    sps.putString(Clue_Game_Hard.this, "face_share", "yes");

                                }*//*


                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                                int spx = skx + 10;
                                String aStringx = Integer.toString(spx);
                                // score.setText(aStringx);
                                ttscores.setText(aStringx);
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

                                if (sps.getString(Makeword_Rightorder.this, "complite_reg").equals("yes")) {
                                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
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
*/

    @Override
    public void onDestroy() {
        super.onDestroy();
       // uiHelper.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Makeword_Rightorder.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);
        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Makeword_Rightorder.this, "date");
        if (date.equals("0")) {
            next_game.setText("சீர்படுத்து தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("சீர்படுத்து புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");

        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Clue_Game_Hard.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Makeword_Rightorder.this, "game_area", "on");
                    finish();
                }
                sps.putString(Makeword_Rightorder.this, "date", "0");
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Odd_man_out.class);
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Ote_to_Tamil.class);
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Riddle_game.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, WordError_correction.class);
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });

        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, English_to_tamil.class);
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Find_words_from_picture.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Quiz_Game.class);
                startActivity(i);
            }
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Makeword_Rightorder.this);
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Jamble_word_game.class);
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Missing_Words.class);
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
                sps.putString(Makeword_Rightorder.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, Find_difference_between_pictures.class);
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
                    Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Makeword_Rightorder.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();

                /*finish();
                openDialog.dismiss();
                //sps.putString(Odd_man_out.this, "date", "0");
                Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
    }


    public void showcase_dismiss(){
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sps.getString(Makeword_Rightorder.this,"showcase_dismiss_ro").equals(""))
                {
                    showcase_dismiss();
                }else {
                    sps.putString(context, "mak_time_start", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }

            }
        }, 800);
    }

    public void price_update(){
        ////////////////Prize//////////////////
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;
        String date = sps.getString(Makeword_Rightorder.this, "date");
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                prize_data_update(Makeword_Rightorder.this, 75);
            } else if (timeElapsed > 91300) {
                prize_data_update(Makeword_Rightorder.this, 50);
            } else {
                prize_data_update(Makeword_Rightorder.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                prize_data_update(Makeword_Rightorder.this, 100);
            } else if (timeElapsed > 91300) {
                prize_data_update(Makeword_Rightorder.this, 75);
            } else {
                prize_data_update(Makeword_Rightorder.this, 50);
            }
        }
        ////////////////Prize//////////////////
    }
    public void downloaddata_regular2() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Makeword_Rightorder.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Makeword_Rightorder.this)) {
                    download_datas();
                } else {
                    NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                    native_banner_ad_container.setVisibility(View.INVISIBLE);
                    w_head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Makeword_Rightorder.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                            .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                    sps.putInt(Makeword_Rightorder.this, "goto_sett", 1);


                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    sps.putString(Makeword_Rightorder.this, "game_area", "on");
                                    String date = sps.getString(Makeword_Rightorder.this, "date");
                                    if (date.equals("0")) {
                                        if (main_act.equals("")) {
                                            finish();
                                            Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
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
                                   /* Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
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
               /* Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                startActivity(i);*/
                sps.putString(Makeword_Rightorder.this, "game_area", "on");
                String date = sps.getString(Makeword_Rightorder.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                }else {
                    finish();
                    Intent i = new Intent(Makeword_Rightorder.this, New_Main_Activity.class);
                    startActivity(i);
                }
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    private void download_datas() {
        Cursor cz = newhelper3.getQry("select * from right_order where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndex("questionid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Makeword_Rightorder.this, questionid_d, "" + gameid);
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


    /*public void reward(final Context context) {
        rewardedVideoAd = new RewardedVideoAd(context, getString(R.string.fb_rewarded_ins));
      *//*  rewardedVideoAd.setAdListener(new RewardedVideoAdListener() {

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
