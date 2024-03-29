package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.Utils.isNetworkAvailable;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Ote_to_Tamil extends AppCompatActivity implements Download_completed {

    public static final String TAG = "SavedGames";
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    static int ry;
    static int mCoinCount = 20;


    // Facebook variable starts
    static int rvo = 0;
    static SharedPreference spd = new SharedPreference();
    final int gameid = 8;
    final SharedPreference sps = new SharedPreference();
    final int minmum = 1;
    final int maximum = 3;
    final Context context = this;
    /////////native advance////////////
    final int minmumd = 1;
    final int maximumd = 4;
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    private final PendingAction pendingAction = PendingAction.NONE;
    int fb_reward = 0;
    int reward_status = 0;
    String btn_str = "";
    DataBaseHelper myDbHelper;
    Typeface typ, tyr;
    // facebook variable ends
    TextView c_time, score, to_no;
    Chronometer focus;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    EditText c_edit;
    int level;
    int w_id;
    TextView c_verify, c_clear, ans_high;
    int f_sec;
    TextView c_ans;
    TextView bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15, bt16;
    SoundPool click, win, coin, worng;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    int e2;
    TextView c_coin;
    RadioButton fn1, fn2, fn3;
    TextView c_settings;
    TextView toggleButton;
    LinearLayout adds, list4;
    PopupWindow popupWindow;
    int kx = 1;
    RelativeLayout w_head, helpshare_layout;
    TextView shareq, h_gplues, h_watts_app, h_facebook;
    int r = 0;
    String sa;
    int type;
    JSONArray warray, warray2, carray, sarray, sarray2;
    String str_vpcont;
    String email = "";
    Timer t1, th;
    int t, t2;
    LinearLayout qtw;
    TextView earncoin;
    int randomno;
    TextView next_continue;
    String downok = "", downnodata = "";
    ProgressDialog mProgressDialog;
    TextView ttscores;
    int tim = 0;
    long ttstop;
    int noclue = 0;
    String retype = "s";
    RelativeLayout edit_buttons_layout;
    Dialog openDialog_p;
    Dialog openDialog_s;
    int s = 0;
    TextView question;
    int share_name = 0;
    int setting_access = 0;
    RelativeLayout adsicon, adsicon2;
    int loadaddcontent = 0;
    /////////Native_BackPress_Advanced////////////
    int daily_start = 0;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    Dialog openDialog;
    int setval_vid;
    TextView coin_value;
    int randomnod;
    FirebaseAnalytics mFirebaseAnalytics;
    int dia_dismiss = 0;
    //RewardedVideoAd rewardedVideoAd;
    Handler handler;
    Runnable my_runnable;
    private MaxRewardedAd rewardedAd;
    private MaxInterstitialAd mInterstitialAd;

    @Override
    public void download_completed(String status) {
        System.out.println("#############################status" + status);
        if (status.equals("nodata")) nextgamesdialog();
        else next();
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
            startActivity(i);
        } else finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ote_to_tamil_game);
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.blink_animation);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");

        //uiHelper = new UiLifecycleHelper(this, callback);
        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);


        if (sps.getString(Ote_to_Tamil.this, "new_user_db").equals("")) {

        } else if (sps.getString(Ote_to_Tamil.this, "new_user_db").equals("on")) {
            sps.putString(Ote_to_Tamil.this, "db_name_start", "Tamil_Game2.db");
            Commen_string.dbs_name = "Tamil_Game2.db";
        } else {
            sps.putString(Ote_to_Tamil.this, "db_name_start", "Solli_Adi");
            Commen_string.dbs_name = "Solli_Adi";
        }

        myDbHelper = new DataBaseHelper(context);
        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);


        email = sps.getString(Ote_to_Tamil.this, "email");


        Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        if (sps.getInt(Ote_to_Tamil.this, "purchase_ads") == 0) {
            industrialload();
        }


        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Ote_to_Tamil.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Ote_to_Tamil.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Ote_to_Tamil.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Ote_to_Tamil.this, R.raw.coins, 1);

        ImageView prize_logo = findViewById(R.id.prize_logo);
        if (sps.getInt(Ote_to_Tamil.this, "remoteConfig_prize") == 1)
            prize_logo.setVisibility(View.VISIBLE);
        else prize_logo.setVisibility(View.GONE);
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable(this))
                if (sps.getString(Ote_to_Tamil.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Ote_to_Tamil.this, Game_Status.class);
                    startActivity(i);
                } else if (sps.getString(Ote_to_Tamil.this, "otp_verify").equals("yes")) {
                    finish();
                    Intent i = new Intent(Ote_to_Tamil.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    finish();
                    Intent i = new Intent(Ote_to_Tamil.this, Price_Login.class);
                    startActivity(i);
                }
            else
                Toast.makeText(Ote_to_Tamil.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
        });

        openDialog_s = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        adsicon = openDialog_s.findViewById(R.id.adsicon);

        String snd = sps.getString(Ote_to_Tamil.this, "snd");

        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        toggleButton = popupView.findViewById(R.id.toggle);
        c_settings = findViewById(R.id.c_settings);
        if (snd.equals("off")) {
            //  toggleButton.setBackgroundResource(R.drawable.off);
            c_settings.setBackgroundResource(R.drawable.sound_off);
            sv = 0;
            //sounds
        } else if (snd.equals("on")) {
            c_settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;
            //
        }
        c_edit = findViewById(R.id.clue_ans_editer);
        c_edit.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);
        });
        c_edit.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);

            return true;
        });


        //finding and next
        find();
        clicklistner();
        adds = findViewById(R.id.ads_lay);
        Utills.INSTANCE.load_add_AppLovin(this, adds, getResources().getString(R.string.Bottom_Banner));

        c_ans = findViewById(R.id.c_ans);
        h_watts_app = findViewById(R.id.ch_watts_app);
        helpshare_layout = findViewById(R.id.helpshare_layout);
        //Dialog for intro

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
                sps.putString(Ote_to_Tamil.this, "date", message);
                next();
            } else {
                sps.putString(Ote_to_Tamil.this, "date", "0");
                next();
            }
        } else {
            sps.putString(Ote_to_Tamil.this, "date", "0");
            next();
        }


        if (sps.getString(Ote_to_Tamil.this, "ote_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Ote_to_Tamil.this, "ote_sequence example cn3");

            sequence.setConfig(config);

            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");
            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Ote_to_Tamil.this)
                            .setTarget(helpshare_layout)
                            .setDismissText("சரி")
                            .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                            .build())
                    .setOnItemDismissedListener((itemView, position) -> {

                        if (position == 1) {
                            sps.putString(Ote_to_Tamil.this, "ote_sequence example cn3", "yes");
                            sps.putString(Ote_to_Tamil.this, "showcase_dismiss_ote", "yes");
                            focus.setBase(SystemClock.elapsedRealtime());
                            focus.start();

                        }
                    });


            sps.putString(Ote_to_Tamil.this, "ote_intro", "no");
            sequence.start();

        }

        if (sps.getInt(Ote_to_Tamil.this, "reward_coin_txt") == 0)
            sps.putInt(Ote_to_Tamil.this, "reward_coin_txt", 20);

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


    }

    public void rewarded_adnew() {
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
    }

    public void show_reward() {
        if (rewardedAd != null && rewardedAd.isReady()) {
            rewardedAd.showAd();
            reward_status = 1;
        } else {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
        }
    }

    private void industrialload() {
        //AppLovinSdk.getInstance( this ).showMediationDebugger();
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, config -> {
            // AppLovin SDK is initialized, start loading ads
            if (mInterstitialAd != null && mInterstitialAd.isReady()) return;
            System.out.println("ad shown  showAdWithDelay initialize done ");
            mInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Ragasiya_sorgal_ins), Ote_to_Tamil.this);
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

    }

    public void adShow() {
        if (sps.getInt(getApplicationContext(), "Game4_Stage_Close_RS") == Utills.interstitialadCount && mInterstitialAd != null) {
            sps.putInt(getApplicationContext(), "Game4_Stage_Close_RS", 0);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                if (mInterstitialAd == null) setSc();
                else
                    mInterstitialAd.showAd("Ragasiya sorgal ins");
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game4_Stage_Close_RS", (sps.getInt(getApplicationContext(), "Game4_Stage_Close_RS") + 1));
            if (sps.getInt(context, "Game4_Stage_Close_RS") > Utills.interstitialadCount)
                sps.putInt(context, "Game4_Stage_Close_RS", 0);

            setSc();
            //Toast.makeText(this, ""+sps.getInt(this, "Game4_Stage_Close_RS"), Toast.LENGTH_SHORT).show();
        }

    }

    public void clicklistner() {
        c_settings.setOnClickListener(v -> {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            String snd = sps.getString(Ote_to_Tamil.this, "snd");
            if (snd.equals("off")) {
                sps.putString(Ote_to_Tamil.this, "snd", "on");
                c_settings.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd.equals("on")) {
                sps.putString(Ote_to_Tamil.this, "snd", "off");
                c_settings.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }
            // settings();
        });
        earncoin.setOnClickListener(v -> dialog(0));
        w_head.setOnClickListener(view -> {
            if (kx == 2) {
                popupWindow.dismiss();
                kx = 1;
            }
        });


        bt1.setOnClickListener(v -> {
            //c1.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt1.startAnimation(shake);
            String ts = bt1.getText().toString();
            c_edit.append(ts);
        });
        bt2.setOnClickListener(v -> {
            // c2.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt2.startAnimation(shake);
            String ts = bt2.getText().toString();
            c_edit.append(ts);
        });
        bt3.setOnClickListener(v -> {
            // c3.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt3.startAnimation(shake);
            String ts = bt3.getText().toString();
            c_edit.append(ts);
        });
        bt5.setOnClickListener(v -> {
            //  c4.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt5.startAnimation(shake);
            String ts = bt5.getText().toString();
            c_edit.append(ts);
        });
        bt6.setOnClickListener(v -> {
            // c5.start();
            click.play(soundId1, sv, sv, 0, 0, sv);

            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt6.startAnimation(shake);
            String ts = bt6.getText().toString();
            c_edit.append(ts);
        });
        bt7.setOnClickListener(v -> {
            // c6.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt7.startAnimation(shake);
            String ts = bt7.getText().toString();
            c_edit.append(ts);
        });
        bt9.setOnClickListener(v -> {
            // c7.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt9.startAnimation(shake);
            String ts = bt9.getText().toString();
            c_edit.append(ts);
        });
        bt10.setOnClickListener(v -> {
            // c8.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt10.startAnimation(shake);
            String ts = bt10.getText().toString();
            c_edit.append(ts);
        });
        bt11.setOnClickListener(v -> {
            //  c9.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt11.startAnimation(shake);
            String ts = bt11.getText().toString();
            c_edit.append(ts);
        });

        bt4.setOnClickListener(v -> {
            // c10.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt4.startAnimation(shake);
            String ts = bt4.getText().toString();
            c_edit.append(ts);
        });

        bt8.setOnClickListener(v -> {
            // c11.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt8.startAnimation(shake);
            String ts = bt8.getText().toString();
            c_edit.append(ts);
        });
        bt12.setOnClickListener(v -> {
            // c12.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt12.startAnimation(shake);
            String ts = bt12.getText().toString();
            c_edit.append(ts);

        });
        bt13.setOnClickListener(v -> {
            // c13.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt13.startAnimation(shake);
            String ts = bt13.getText().toString();
            c_edit.append(ts);
        });

        bt14.setOnClickListener(v -> {
            // c14.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt14.startAnimation(shake);
            String ts = bt14.getText().toString();
            c_edit.append(ts);
        });
        bt15.setOnClickListener(v -> {
            // c15.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt15.startAnimation(shake);
            String ts = bt15.getText().toString();
            c_edit.append(ts);

        });
        bt16.setOnClickListener(v -> {
            //c16.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            Animation shake = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.button_shake);
            bt16.startAnimation(shake);
            String ts = bt16.getText().toString();
            c_edit.append(ts);

        });

        qtw.setOnClickListener(v -> dialog(0));

        c_clear.setOnClickListener(v -> {
            //c17.start();
            click.play(soundId1, sv, sv, 0, 0, sv);
            pressKey();
        });

        c_clear.setOnLongClickListener(v -> {
            c_edit.setText("");
            return false;
        });

        focus.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


                sps.putInt(getApplicationContext(), "cluetime", (sps.getInt(getApplicationContext(), "cluetime") + 1));

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Time Setting For Clue Game
                long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
                int hours = (int) (timeElapsed / 3600000);
                int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
                int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

                int min = hours * 60;
                int sec = min * 60;
                int sec2 = minutes * 60;
                f_sec = sec + sec2 + seconds;
                //

            }


        });
        final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);

        edit_buttons_layout.setOnClickListener(v -> {
            if (kx == 2) {
                popupWindow.dismiss();
                kx = 1;
            }
        });

        //User Verifing Answer
        c_ans.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk >= 100)
                if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                    Cursor cd;
                    String date = sps.getString(Ote_to_Tamil.this, "date");
                    if (date.equals("0")) {
                        cd = newhelper2.getQry("SELECT * FROM newmaintable2 where  questionid='" + w_id + "' and isfinish='0' and gameid='" + gameid + "'");
                        cd.moveToFirst();
                    } else {
                        cd = newhelper2.getQry("SELECT * FROM newmaintable2 where  questionid='" + w_id + "' and daily='0' and gameid='" + gameid + "'");
                        cd.moveToFirst();
                    }

                    String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                    //Toast.makeText(Ote_to_Tamil.this, "" + sa, Toast.LENGTH_SHORT).show();
                    //Score Adding
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx - 100;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
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
                    ans_high.setText(sa);
                    //Update QST
                    String datee = sps.getString(Ote_to_Tamil.this, "date");
                    if (datee.equals("0"))
                        newhelper2.executeSql("UPDATE newmaintable2 SET isfinish=1 WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    else
                        newhelper2.executeSql("UPDATE newmaintable2 SET daily=1 WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    //Next Function
                    r = 1;


                    focus.stop();

                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> adShow(), 5000);

                } else {
                    final Dialog openDialog = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog.setContentView(R.layout.show_ans);
                    TextView yes = openDialog.findViewById(R.id.yes);
                    TextView no = openDialog.findViewById(R.id.no);
                    TextView txt_ex2 = openDialog.findViewById(R.id.txt_ex2);
                    txt_ex2.setText("மொத்த நாணயங்களில் 100 குறைக்கப்படும்");
                    CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                    checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                        if (isChecked)
                            sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                        else sps.putString(getApplicationContext(), "checkbox_ans", "");
                    });

                    yes.setOnClickListener(v12 -> {
                        Cursor cd;
                        String date = sps.getString(Ote_to_Tamil.this, "date");
                        if (date.equals("0")) {
                            cd = newhelper2.getQry("SELECT * FROM newmaintable2 where  questionid='" + w_id + "' and isfinish='0' and gameid='" + gameid + "'");
                            cd.moveToFirst();
                        } else {
                            cd = newhelper2.getQry("SELECT * FROM newmaintable2 where  questionid='" + w_id + "' and gameid='" + gameid + "' and daily='0'");
                            cd.moveToFirst();
                        }

                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        //Toast.makeText(Ote_to_Tamil.this, "" + sa, Toast.LENGTH_SHORT).show();
                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 100;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
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
                        ans_high.setText(sa);
                        //Update QST
                        String datee = sps.getString(Ote_to_Tamil.this, "date");
                        if (datee.equals("0"))
                            newhelper2.executeSql("UPDATE newmaintable2 SET isfinish=1 WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                        else
                            newhelper2.executeSql("UPDATE newmaintable2 SET daily=1 WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                        //Next Function
                        r = 1;
                        openDialog.dismiss();

                        focus.stop();


                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 5000);


                    });
                    no.setOnClickListener(v1 -> {
                        sps.putString(getApplicationContext(), "checkbox_ans", "");
                        openDialog.dismiss();
                    });
                    if (!isFinishing()) openDialog.show();
                }
            else dialog(1);
        });
        //Verifing Answer
        c_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = c_edit.getText().toString();
                String dates = sps.getString(Ote_to_Tamil.this, "date");
                Cursor cd;
                if (dates.equals("0")) {
                    cd = newhelper2.getQry("SELECT * FROM newmaintable2 where answer ='" + answer + "' and isfinish='0' and questionid='" + w_id + "' and gameid='" + gameid + "'");
                    cd.moveToFirst();
                } else {
                    cd = newhelper2.getQry("SELECT * FROM newmaintable2 where answer ='" + answer + "' and daily='0' and questionid='" + w_id + "' and gameid='" + gameid + "'");
                    cd.moveToFirst();
                }

                if (cd.getCount() != 0) {
                    win.play(soundId3, sv, sv, 0, 0, sv);
                    ry = 1;
                    //Toast.makeText(Ote_to_Tamil.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    c_ans.setBackgroundResource(R.drawable.tick_background);
                    c_ans.setEnabled(false);
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(answer);
                    //bulb invisible

                    //
                    list4.setVisibility(View.INVISIBLE);
                    String date = sps.getString(Ote_to_Tamil.this, "date");
                    if (date.equals("0"))
                        newhelper2.executeSql("UPDATE newmaintable2 SET isfinish=1 WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    else
                        newhelper2.executeSql("UPDATE newmaintable2 SET daily=1 WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");


                    focus.stop();

                    price_update();
                    coinanim();

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
        File mydir = new File(root + "/Nithra/Solli_Adi");
        mydir.mkdirs();
        String fname = "Solli_Adi.jpg";
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
                    String date = sps.getString(Ote_to_Tamil.this, "date");
                    int pos;

                    if (date.equals("0")) {
                        pos = 1;
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");

//            newhelper2.executeSql("UPDATE newmaintable2 SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "' and daily='0'");

                        //      newhelper2.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    }

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Ote_to_Tamil.this, Ote_to_Tamil.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT,
                            "Solli_Adi");
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

    public void clue() {
        noclue = noclue + 1;
        Cursor c;
        String date = sps.getString(Ote_to_Tamil.this, "date");
        if (date.equals("0"))
            c = newhelper2.getQry("select * from newmaintable2 where questionid='" + w_id + "' and gameid='" + gameid + "' and isfinish='0'");
        else
            c = newhelper2.getQry("select * from newmaintable2 where questionid='" + w_id + "' and gameid='" + gameid + "'  and daily='0'");
        c.moveToFirst();
        if (c.getCount() != 0) {
            String sb = c.getString(c.getColumnIndexOrThrow("hints"));
            StringTokenizer tok = new StringTokenizer(sb, ",");
            String cs1 = tok.nextToken();
            String cs2 = tok.nextToken();
            String cs3 = tok.nextToken();
            Animation clueanim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);


        }
    }

    private void pressKey() {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL);
        c_edit.onKeyDown(KeyEvent.KEYCODE_DEL, event);
    }

    //find view
    public void find() {
        c_edit = findViewById(R.id.clue_ans_editer);
        c_clear = findViewById(R.id.clue_clear);
        ans_high = findViewById(R.id.ans_highlite);
        edit_buttons_layout = findViewById(R.id.edit_buttons_layout);
        adsicon2 = findViewById(R.id.adsicon2);
        // c_verify = (Button) findViewById(R.id.clue_verify);
        c_clear = findViewById(R.id.clue_clear);
        c_ans = findViewById(R.id.c_ans);
        question = findViewById(R.id.question);
        list4 = findViewById(R.id.list4);

        bt1 = findViewById(R.id.c_button1);
        bt2 = findViewById(R.id.c_button2);
        bt3 = findViewById(R.id.c_button3);
        bt4 = findViewById(R.id.c_button4);
        bt5 = findViewById(R.id.c_button5);
        bt6 = findViewById(R.id.c_button6);
        bt7 = findViewById(R.id.c_button7);
        bt8 = findViewById(R.id.c_button8);
        bt9 = findViewById(R.id.c_button9);
        bt10 = findViewById(R.id.c_button10);
        bt11 = findViewById(R.id.c_button11);
        bt12 = findViewById(R.id.c_button12);
        bt13 = findViewById(R.id.c_button13);
        bt14 = findViewById(R.id.c_button14);
        bt15 = findViewById(R.id.c_button15);
        bt16 = findViewById(R.id.c_button16);
        qtw = findViewById(R.id.qwt);
        earncoin = findViewById(R.id.earncoin);

        //help share
        w_head = findViewById(R.id.clue_head);
        h_gplues = findViewById(R.id.ch_gplues);
        h_watts_app = findViewById(R.id.ch_watts_app);
        h_facebook = findViewById(R.id.ch_facebook);
        //
        //Time Score Making
        to_no = findViewById(R.id.c_word_number);
        score = findViewById(R.id.c_score_edit);
        focus = findViewById(R.id.c_time_edit);
        c_settings = findViewById(R.id.c_settings);
        //
        c_coin = findViewById(R.id.c_coins);
        //score intial

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        score.setText(tr);
        //


    }

    //
    public void simple() {
        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";
        bt4.setVisibility(View.GONE);
        bt8.setVisibility(View.GONE);
        bt12.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(letter3);
            bt5.setText(sa);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt9.setText(letter9);
            bt10.setText(letter10);
            bt11.setText(letter11);

        } else if (type == 2) {

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

        } else if (type == 3) {

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

        } else if (type == 4) {

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
            bt1.setText(word2);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt5.setText(letter6);
            bt6.setText(letter13);
            bt7.setText(word4);
            bt9.setText(word1);
            bt10.setText(letter7);
            bt11.setText(letter12);

        } else if (type == 5) {

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

        } else if (type == 6) {

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

        } else if (type == 7) {

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

        } else if (type == 8) {

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

        } else if (type == 9) {

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


        } else if (type == 2) {


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


        } else if (type == 3) {

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


        } else if (type == 4) {


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


        } else if (type == 5) {

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


        } else if (type == 6) {


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


        } else if (type == 7) {


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


        } else if (type == 8) {


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

        } else if (type == 9) {

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
    }

    public void hard() {
        String a = "கே,சே,பி,யா,தி,ரே,ஏ,வ்,ர்,கே,ஃ,ஓ,ப,ல்,இ,து,பு,மு,ரூ";

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
        } else if (type == 2) {

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


        } else if (type == 3) {

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

        } else if (type == 4) {

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

        } else if (type == 5) {

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


        } else if (type == 6) {

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


        } else if (type == 7) {

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

        } else if (type == 8) {

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

        } else if (type == 9) {
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
    }

    //next Game Start
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


        //daily bonus

        if (sps.getString(Ote_to_Tamil.this, str_date1).equals("")) {

            daily_bones();
            sps.putString(Ote_to_Tamil.this, str_date1, "yes");

        }


        tim = 0;

        w_head.setVisibility(View.VISIBLE);
        bt4.setVisibility(View.VISIBLE);
        bt8.setVisibility(View.VISIBLE);
        bt12.setVisibility(View.VISIBLE);
        bt13.setVisibility(View.VISIBLE);
        bt14.setVisibility(View.VISIBLE);
        bt15.setVisibility(View.VISIBLE);
        bt16.setVisibility(View.VISIBLE);
        c_ans.setEnabled(true);
        sps.putString(Ote_to_Tamil.this, "watts_app", "");
        sps.putString(Ote_to_Tamil.this, "watts_app_s", "");
        sps.putString(Ote_to_Tamil.this, "gplues", "yes");
        sps.putString(Ote_to_Tamil.this, "face_share", "");

      /*  if (sps.getString(Ote_to_Tamil.this, "Ote_time_start").equals(""))
        {
            sps.putString(Ote_to_Tamil.this, "Ote_time_start", "yes");

        }else
        {

           *//*
        }
*/

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

        ans_high.setVisibility(View.GONE);
        c_coin.setVisibility(View.INVISIBLE);
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        list4.setVisibility(View.VISIBLE);


        LinearLayout bl1 = findViewById(R.id.c_buttonlist1_layout);
        LinearLayout bl2 = findViewById(R.id.c_buttonlist2_layout);
        LinearLayout bl3 = findViewById(R.id.c_buttonlist3_layout);
        LinearLayout bl4 = findViewById(R.id.c_buttonlist4_layout);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.blink_animation);


        if (daily_start == 1) {
            Toast.makeText(Ote_to_Tamil.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான பிறமொழி சொற்கள் விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
            sps.putString(context, "date", "0");
            daily_start = 0;
        }


        String date = sps.getString(Ote_to_Tamil.this, "date");


        if (date.equals("0")) {
            Cursor c1 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            to_no.setText(no/*+"/"+c1.getCount()*/);
            to_no.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        } else {
            if (sps.getInt(Ote_to_Tamil.this, "purchase_ads") == 1) {

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
            c = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            daily_start = 1;
            c = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and daily='0' order by random() limit 1");
            c.moveToFirst();
        }

        //  Toast.makeText(Ote_to_Tamil.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான குறிப்பு மூலம் கண்டுபிடி விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
        // nextgamesdialog();
        if (c.getCount() != 0) {
            sa = c.getString(c.getColumnIndexOrThrow("sf_words"));
            w_id = c.getInt(c.getColumnIndexOrThrow("questionid"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            question.setText(c.getString(c.getColumnIndexOrThrow("question")));
            if (playtime == 0) if (sps.getString(Ote_to_Tamil.this, "Ote_time_start").equals(""))
                sps.putString(Ote_to_Tamil.this, "Ote_time_start", "yes");
            else {

                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

            }
            else {

                focus.setBase(SystemClock.elapsedRealtime() + playtime);
                focus.start();

            }

            String tfoption = sa;
            String[] first = tfoption.split(",");
            type = first.length;

            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;

            if (randomno == 1) simple();
            else if (randomno == 2) medium();
            else if (randomno == 3) hard();
        } else if (date.equals("0")) downloaddata_regular2();
        else if (sps.getString(Ote_to_Tamil.this, "Exp_list").equals("on")) {
            finish();
            Intent i = new Intent(Ote_to_Tamil.this, Expandable_List_View.class);
            startActivity(i);
        } else {
            Toast.makeText(Ote_to_Tamil.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான பிறமொழி சொற்கள் விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Ote_to_Tamil.class);
            startActivity(i);
        }
    }

    private void daily_bones() {
        openDialog = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        //  TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog.findViewById(R.id.ok_y);
        //   TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
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

        TextView tomarrow_coin_earn = openDialog.findViewById(R.id.tomarrow_coin_earn);


        ok_y.setOnClickListener(v -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + ea;
            String aStringx = Integer.toString(spx);
            score.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            sps.putString(context, "daily_bonus_date", date);
            openDialog.dismiss();

        });
        coin_value = openDialog.findViewById(R.id.coin_value);
        ea = 100;
        final int vals = reward_play_count * 100;
        ea = ea + vals;
        coin_value.setText("" + ea);

        LinearLayout extra_coin = openDialog.findViewById(R.id.extra_coin);
        System.out.println("############################^^^^^^^^^^^^^^currentdate" + str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Ote_to_Tamil.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Ote_to_Tamil.this, "daily_bonus_date"))) {

        } else sps.putInt(context, "daily_bonus_count", 0);
        if (sps.getInt(context, "daily_bonus_count") == 0) ea = 100;
        else if (sps.getInt(context, "daily_bonus_count") == 1) ea = 150;
        else if (sps.getInt(context, "daily_bonus_count") == 2) ea = 200;
        else if (sps.getInt(context, "daily_bonus_count") == 3) ea = 250;
        else if (sps.getInt(context, "daily_bonus_count") == 4) ea = 300;
        prize_data_update(context, ea);
        coin_value = openDialog.findViewById(R.id.coin_value);
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomnod = rn.nextInt(maximumd - minmumd + 1) + minmumd;

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


        extra_coin = openDialog.findViewById(R.id.extra_coin);
        extra_coin.setOnClickListener(v -> {
            extra_coin_s = 1;
            if (isNetworkAvailable(this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Ote_to_Tamil.this, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    show_reward();
                } else new Handler(Looper.myLooper()).postDelayed(() -> {
                    reward_progressBar.dismiss();
                    // mShowVideoButton.setVisibility(View.VISIBLE);
                    if (fb_reward == 1) show_reward();
                    else {
                        //reward(Ote_to_Tamil.this);
                        rewarded_adnew();
                        Toast.makeText(Ote_to_Tamil.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });
        if (!isFinishing()) openDialog.show();
    }
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            sps.putString(Ote_to_Tamil.this, "game_area", "on");

            if (popupWindow.isShowing()) popupWindow.dismiss();
            else {
                sps.putInt(Ote_to_Tamil.this, "addlodedd", 0);
                s = 1;
                openDialog_p = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog_p.setContentView(R.layout.back_pess);
                TextView yes = openDialog_p.findViewById(R.id.yes);
                TextView no = openDialog_p.findViewById(R.id.no);

                yes.setOnClickListener(v -> {

                    String dates = sps.getString(Ote_to_Tamil.this, "date");
                    int pos;
                    if (dates.equals("0")) {
                        pos = 1;
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");

                        //    newhelper2.executeSql("UPDATE newmaintable2 SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "' and daily='0'");

                        //   newhelper2.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    }


                    String date = sps.getString(Ote_to_Tamil.this, "date");
                    if (date.equals("0")) if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                        startActivity(i);
                    } else finish();
                    else if (sps.getString(Ote_to_Tamil.this, "Exp_list").equals("on")) {
                        finish();
                        Intent i = new Intent(Ote_to_Tamil.this, Expandable_List_View.class);
                        startActivity(i);
                    } else if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                        startActivity(i);
                    } else finish();

                    openDialog_p.dismiss();
                });
                no.setOnClickListener(v -> openDialog_p.dismiss());
                openDialog_p.show();


            }


            // return super.onKeyDown(keyCode, event);
        }
    };

    public void setSc() {
        if (s == 1) {
            openDialog_p.dismiss();
            s = 0;
        }

        next_continue = openDialog_s.findViewById(R.id.continues2);
        ttscores = openDialog_s.findViewById(R.id.tts_score2);
        final TextView wtp = openDialog_s.findViewById(R.id.wtp);
        final TextView fbs = openDialog_s.findViewById(R.id.fbp);
        final TextView kuduthal = openDialog_s.findViewById(R.id.tt22);
        final TextView gplus = openDialog_s.findViewById(R.id.gplus2);
        final TextView word = openDialog_s.findViewById(R.id.arputham2);
        final LinearLayout rewardvideo = openDialog_s.findViewById(R.id.rewardvideo);
        LinearLayout ads_layout = openDialog_s.findViewById(R.id.fl_adplaceholder);
        final LinearLayout vid_earn = openDialog_s.findViewById(R.id.vid_earn);

        ImageView prize_logo = openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Ote_to_Tamil.this, "remoteConfig_prize") == 1)
            prize_logo.setVisibility(View.VISIBLE);
        else prize_logo.setVisibility(View.GONE);
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable(this))
                if (sps.getString(Ote_to_Tamil.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Ote_to_Tamil.this, Game_Status.class);
                    startActivity(i);
                } else if (sps.getString(Ote_to_Tamil.this, "otp_verify").equals("yes")) {
                    finish();
                    Intent i = new Intent(Ote_to_Tamil.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    finish();
                    Intent i = new Intent(Ote_to_Tamil.this, Price_Login.class);
                    startActivity(i);
                }
            else
                Toast.makeText(Ote_to_Tamil.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
        });

        TextView video_earn = openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Ote_to_Tamil.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Ote_to_Tamil.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        //New_Main_Activity.load_addFromMain_multiplayer(Ote_to_Tamil.this, ads_layout);
        if (sps.getInt(Ote_to_Tamil.this, "purchase_ads") == 1) ads_layout.setVisibility(View.GONE);
        else if (isNetworkAvailable(Ote_to_Tamil.this)) {
            //New_Main_Activity.load_add_fb_rect_score_screen(Ote_to_Tamil.this, ads_layout);
        } else ads_layout.setVisibility(View.GONE);


        word.setTypeface(tyr);
        next_continue.setVisibility(View.VISIBLE);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);
        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(Ote_to_Tamil.this, "date");
        if (!date.equals("0")) next_continue.setText("சரி");
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        String aStringx = Integer.toString(skx);
        ttscores.setText(aStringx);


        if (sps.getString(Ote_to_Tamil.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Ote_to_Tamil.this, "date");
            if (dates.equals("0")) rewardvideo.setVisibility(View.VISIBLE);
        }

        if (ry == 1) {

        } else rewardvideo.setVisibility(View.INVISIBLE);

        RelativeLayout adsicon = openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);


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

                    String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                    i.putExtra(Intent.EXTRA_TEXT, msg);
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

                    String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + to_no.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i, "Share via"), 16);

                } else
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });
        word.setText("ï¡Á");

        if (r == 1) {
            word.setText("");
            word.setText("ºòŸC ªêŒè");
            r = 0;
        }

        next_continue.setOnClickListener(view -> {
            noclue = 0;
            dia_dismiss = 1;
            openDialog_s.dismiss();
            next();


        });

        openDialog_s.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Ote_to_Tamil.this, "game_area", "on");
                String date1 = sps.getString(Ote_to_Tamil.this, "date");
                if (date1.equals("0")) if (main_act.equals("")) {
                    finish();
                    openDialog_s.dismiss();
                    Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    openDialog_s.dismiss();
                    finish();
                }
                else if (sps.getString(Ote_to_Tamil.this, "Exp_list").equals("on")) {
                    finish();
                    openDialog_s.dismiss();
                    Intent i = new Intent(Ote_to_Tamil.this, Expandable_List_View.class);
                    startActivity(i);

                } else if (main_act.equals("")) {
                    finish();
                    openDialog_s.dismiss();
                    Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    openDialog_s.dismiss();
                    finish();
                }

            } else dia_dismiss = 0;

        });
        if (!isFinishing()) openDialog_s.show();

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



    public void coinanim() {
////
        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        score.setText(tr);
        //
        e2 = skq;
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
        transAnimation.setDuration(600);
        c_coin.startAnimation(transAnimation);
        c_coin.postDelayed(() -> c_coin.setVisibility(View.INVISIBLE), transAnimation.getDuration());


        new Thread(() -> {
            int es = e2 + 50;
            while (e2 < es) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                score.post(() -> score.setText("" + e2));
                e2++;
            }

        }).start();

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
            score.startAnimation(levels1);
        }, 1200);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(() -> {
            //Score Setting
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 50;
            String aStringx = Integer.toString(spx);
            score.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

            Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
            ch.moveToFirst();
            int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
            int shh = sh + 30;
            myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");
            adShow();
        }, 2500);

    }

    public void dialog(int i) {
        final Dialog openDialog_earncoin = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 100 நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
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
                    String date = sps.getString(Ote_to_Tamil.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");

                        //myDbHelper.executeSql("UPDATE newmaintable2 SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "' and daily='0'");

                        //myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    }
                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {
                    fb_reward = 0;

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
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                            "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
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
                    String date = sps.getString(Ote_to_Tamil.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");

                        // newhelper2.executeSql("UPDATE newmaintable2 SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "' and daily='0'");

                        // newhelper2.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
                    }
                    openDialog_earncoin.cancel();
                    Intent i1 = new Intent(Intent.ACTION_SEND);
                    i1.setType("text/plain");
                    i1.setPackage("com.google.android.apps.plus");
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                            "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i1.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i1, "Share via"), 15);


                } else
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

        });
        openDialog_earncoin.show();
    }

    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Ote_to_Tamil.this);
        Bundle params = new Bundle();
        params.putString("screen_name", "Other language to tamil");
        params.putString("screen_class", "Ote_to_Tamil");
        mFirebaseAnalytics.logEvent( "screen_view", params);
        System.out.println("addloded" + sps.getInt(Ote_to_Tamil.this, "addloded"));

        if (setting_access == 1) {
            setting_access = 0;
            // if ((ContextCompat.checkSelfPermission(Ote_to_Tamil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            downloaddata_daily();
            /*} else {
                Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                finish();
                startActivity(i);
            }*/
        } else if (setting_access == 2) {
            setting_access = 0;
            //if ((ContextCompat.checkSelfPermission(Ote_to_Tamil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            downloaddata_regular();
           /* } else {
                Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                finish();
                startActivity(i);
            }*/
        }


        if (sps.getInt(Ote_to_Tamil.this, "goto_sett") == 1) {
            sps.putInt(Ote_to_Tamil.this, "goto_sett", 0);

            if (isNetworkAvailable(Ote_to_Tamil.this)) {

            }
        }

        if (sps.getString(Ote_to_Tamil.this, "resume_c_ote").equals(""))
            sps.putString(Ote_to_Tamil.this, "resume_c_ote", "yes");
        else {
            String date = sps.getString(Ote_to_Tamil.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                pos = 1;
                cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + w_id + "'");
                cs.moveToFirst();
                //                    noofclue=cs.getInt(cs.getColumnIndexOrThrow("noclue"));
                if (cs.getCount() != 0) dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            } else {
                pos = 2;
                cs = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + w_id + "' and daily='0'");
                cs.moveToFirst();
                //                   noofclue=cs.getInt(cs.getColumnIndexOrThrow("noclue"));
                if (cs.getCount() != 0) dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            }
            //  long wt=sps.getInt(Word_Game_Hard.this,"old_time_start");

            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // uiHelper.onSaveInstanceState(outState);
        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);

        if (requestCode == 0) if (isNetworkAvailable(Ote_to_Tamil.this)) download_datas();
        else {


            w_head.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ote_to_Tamil.this);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்")
                    .setPositiveButton("அமைப்பு", (dialog, which) -> {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        sps.putInt(Ote_to_Tamil.this, "goto_sett", 1);
                        dialog.dismiss();
                    })
                    .setNegativeButton("பின்னர்", (dialog, which) -> {
                        String date = sps.getString(Ote_to_Tamil.this, "date");
                        if (date.equals("0")) backexitnet();
                        else backexitnet();
                        dialog.dismiss();
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }


        if (requestCode == 15) if (resultCode == -1) {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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
            if (str_month1.length() == 1) str_month1 = "0" + str_month1;

            String str_day1 = "" + cur_day1;
            if (str_day1.length() == 1) str_day1 = "0" + str_day1;
            final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

            if (sps.getString(Ote_to_Tamil.this, "complite_reg").equals("yes")) {
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
            //score.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

            share_earn(20);

            if (sps.getString(Ote_to_Tamil.this, "complite_reg").equals("yes")) {
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
            //score.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

            share_earn2(20);


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


        String date = sps.getString(Ote_to_Tamil.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");

//            newhelper2.executeSql("UPDATE newmaintable2 SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "' and daily='0'");

            //      newhelper2.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
        }


        try {
            t1.cancel();
            th.cancel();
        } catch (Exception e) {

        }
        //  AppEventsLogger.deactivateApp(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) openDialog_p.dismiss();
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.dismiss();
        rewardedAd = null;
        mInterstitialAd = null;
        handler = null;
    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);

        TextView exit = openDialog.findViewById(R.id.exit);

        String date = sps.getString(Ote_to_Tamil.this, "date");
        if (date.equals("0"))
            next_game.setText("பிறமொழி சொற்கள்  தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        else
            next_game.setText("தினசரி பிறமொழி சொற்கள்  புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள்  சிறப்பாக விளையாட காத்திருக்கும்  விளையாட்டுக்கள்.");
        openDialog.setCancelable(false);
        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Ote_to_Tamil.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Picture_Game_Hard.class);
            startActivity(i);
        });
        exit.setOnClickListener(v -> {
            if (main_act.equals("")) {
                finish();
                Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Ote_to_Tamil.this, "game_area", "on");
                finish();
            }
            sps.putString(Ote_to_Tamil.this, "date", "0");
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
            c2 = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='0' order by id limit 1");
            c2.moveToFirst();
            if (c2.getCount() != 0) s_game.setVisibility(View.VISIBLE);
            Cursor c3;
            c3 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0' order by id limit 1");
            c3.moveToFirst();
            if (c3.getCount() != 0) w_game.setVisibility(View.VISIBLE);
            Cursor c4;
            c4 = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='0' order by id limit 1");
            c4.moveToFirst();
            if (c4.getCount() != 0) c_game.setVisibility(View.VISIBLE);
        } else {
            next_game.setText("புதிய பதிவுகள் ஏதும் இல்லை.விரைவில் வினாக்கள் பதிவேற்றம் செய்யப்படும்.");
            exit.setText("சரி ");
            exit.setVisibility(View.VISIBLE);
        }

        TextView odd_man_out = openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(view -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Odd_man_out.class);
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
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Ote_to_Tamil.class);
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


        seerpaduthu.setOnClickListener(view -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Riddle_game.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, WordError_correction.class);
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
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Fill_in_blanks.class);
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
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Match_tha_fallows_game.class);
            startActivity(i);

        });
        find_words_from_pictures.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Find_words_from_picture.class);
            startActivity(i);
        });
        quiz.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Quiz_Game.class);
            startActivity(i);
        });

        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Ote_to_Tamil.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) jamble_words.setVisibility(View.VISIBLE);

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Jamble_word_game.class);
            startActivity(i);
        });
        TextView missing_words = openDialog.findViewById(R.id.missing_words);
        Cursor jmps;
        jmps = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='0' order by id limit 1");
        jmps.moveToFirst();
        if (jmps.getCount() != 0) missing_words.setVisibility(View.VISIBLE);
        missing_words.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Missing_Words.class);
            startActivity(i);
        });
        TextView six_differences = openDialog.findViewById(R.id.six_differences);
        Cursor dif;
        dif = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='0' order by id limit 1");
        dif.moveToFirst();
        if (dif.getCount() != 0) six_differences.setVisibility(View.VISIBLE);
        six_differences.setOnClickListener(v -> {
            finish();
            sps.putString(Ote_to_Tamil.this, "date", "0");
            Intent i = new Intent(Ote_to_Tamil.this, Find_difference_between_pictures.class);
            startActivity(i);
        });
        if (!isFinishing()) openDialog.show();
        openDialog.setOnKeyListener((dialog, keyCode, event) -> {

            if (main_act.equals("")) {

                finish();
                //openDialog_s.dismiss();
                Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Ote_to_Tamil.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Ote_to_Tamil.this, "date", "0");


            return keyCode == KeyEvent.KEYCODE_BACK;
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 152)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Ote_to_Tamil.this, "permission", 1);
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

    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Ote_to_Tamil.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }


    //*********************reward videos process 3***********************

    public void vidcoinearn() {

        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea = ea + setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

            if (!isFinishing()) openDialog.show();
        }

    }


    //reward videos***********************//

    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Ote_to_Tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

        if (isNetworkAvailable(Ote_to_Tamil.this)) {
            Cursor c1 = myDbHelper.getQry("select id from dailytest order by id DESC");
            c1.moveToFirst();


            System.out.print("Count====" + c1.getCount());


            //c1.getString(c1.getColumnIndexOrThrow("id"));
            if (c1.getCount() != 0)
                System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));
            else System.out.print("else====");


        } else {

            w_head.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ote_to_Tamil.this);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                    .setPositiveButton("அமைப்பு", (dialog, which) -> {
                        // continue with delete
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        sps.putInt(Ote_to_Tamil.this, "goto_sett", 1);
                        dialog.dismiss();
                    })
                    .setNegativeButton("பின்னர்", (dialog, which) -> {
                        // do nothing

                        String date = sps.getString(Ote_to_Tamil.this, "date");
                        if (date.equals("0")) backexitnet();
                        else backexitnet();
                        dialog.dismiss();
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }

    public void downloaddata_regular() {

        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ote_to_Tamil.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {
            //DownLoad Letters and Words


            if (isNetworkAvailable(Ote_to_Tamil.this)) {
                Cursor c1 = newhelper2.getQry("select id from newmaintable2 order by id DESC");
                c1.moveToFirst();


                System.out.print("Count====" + c1.getCount());


                //c1.getString(c1.getColumnIndexOrThrow("id"));
                if (c1.getCount() != 0)
                    System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));
                else {

                }
            } else {

                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Ote_to_Tamil.this);
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                        .setPositiveButton("அமைப்பு", (dialog12, which) -> {
                            // continue with delete
                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                            sps.putInt(Ote_to_Tamil.this, "goto_sett", 1);
                            dialog12.dismiss();
                        })
                        .setNegativeButton("பின்னர்", (dialog1, which) -> {
                            // do nothing
                            sps.putString(Ote_to_Tamil.this, "game_area", "on");
                            String date = sps.getString(Ote_to_Tamil.this, "date");
                            if (date.equals("0")) backexitnet();
                            else backexitnet();
                            dialog1.dismiss();
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        });
        alertDialogBuilder.setPositiveButton("இல்லை ", (dialog, id) -> {
            sps.putString(Ote_to_Tamil.this, "game_area", "on");
            Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
            startActivity(i);
            finish();
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Ote_to_Tamil.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");

//            newhelper2.executeSql("UPDATE newmaintable2 SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper2.executeSql("UPDATE newmaintable2 SET playtime='" + ttstop + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "' and daily='0'");

            //      newhelper2.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE questionid='" + w_id + "' and gameid='" + gameid + "'");
        }
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

//*** In Adapter **

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
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Ote_to_Tamil.this, "showcase_dismiss_ote").equals(""))
                showcase_dismiss();
            else {
                sps.putString(Ote_to_Tamil.this, "ote_sequence example cn3", "yes");
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

            }

        }, 800);
    }

    public void price_update() {
        ////////////////Prize//////////////////
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;
        String date = sps.getString(Ote_to_Tamil.this, "date");
        if (date.equals("0")) if (timeElapsed <= 91300) prize_data_update(Ote_to_Tamil.this, 75);
        else if (timeElapsed > 91300) prize_data_update(Ote_to_Tamil.this, 50);
        else prize_data_update(Ote_to_Tamil.this, 25);
        else if (timeElapsed <= 91300) prize_data_update(Ote_to_Tamil.this, 100);
        else if (timeElapsed > 91300) prize_data_update(Ote_to_Tamil.this, 75);
        else prize_data_update(Ote_to_Tamil.this, 50);
        ////////////////Prize//////////////////
    }

    public void downloaddata_regular2() {

        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ote_to_Tamil.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {
            //DownLoad Letters and Words

            if (isNetworkAvailable(Ote_to_Tamil.this)) download_datas();
            else {

                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Ote_to_Tamil.this);                           /* .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                        .setPositiveButton("அமைப்பு", (dialog12, which) -> {
                            // continue with delete

                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                            sps.putInt(Ote_to_Tamil.this, "goto_sett", 1);


                            dialog12.dismiss();
                        })
                        .setNegativeButton("பின்னர்", (dialog1, which) -> {
                            // do nothing
                            sps.putString(Ote_to_Tamil.this, "game_area", "on");
                            String date = sps.getString(Ote_to_Tamil.this, "date");
                            if (date.equals("0")) if (main_act.equals("")) {
                                finish();
                                Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                                startActivity(i);
                            } else finish();
                            else if (date.equals("0")) backexitnet();
                            else backexitnet();
                            dialog1.dismiss();
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        });
        alertDialogBuilder.setPositiveButton("இல்லை ", (dialog, id) -> {
            sps.putString(Ote_to_Tamil.this, "game_area", "on");
            String date = sps.getString(Ote_to_Tamil.this, "date");
            if (date.equals("0")) if (main_act.equals("")) {
                finish();
                Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                startActivity(i);
            } else finish();
            else {
                finish();
                Intent i = new Intent(Ote_to_Tamil.this, New_Main_Activity.class);
                startActivity(i);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    private void download_datas() {
        Cursor cz = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0)
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("questionid")));
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Ote_to_Tamil.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

}

