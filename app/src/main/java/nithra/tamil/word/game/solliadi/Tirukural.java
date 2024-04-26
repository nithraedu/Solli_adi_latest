package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.TAG;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.Utils.isNetworkAvailable;

import android.Manifest;
import android.app.Activity;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
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

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Tirukural extends AppCompatActivity {
    static final int mCoinCount = 20;
    public static FrameLayout add, add2, add3;
    public static LinearLayout add_e;
    public static LinearLayout add_sc;
    static int ry;
    static int rvo = 0;
    static SharedPreference spd = new SharedPreference();
    final SharedPreference sps = new SharedPreference();
    final int gameid = 12;
    final int minmum = 1;
    final int maximum = 3;
    final Context context = this;
    final int minmumd = 1;
    final int maximumd = 4;
    TextView c_button1, c_button2, c_button3, c_button4, c_button5, c_button6, c_button7, c_button8, c_button9, c_button10, c_button11, c_button12;
    TextView to_no, question_txt;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    String questionid, question, answer, split_word;
    String answers;
    int randomno;
    int daily_start = 0;
    String[] first, first1, start;
    TextView word1, word2, word3, word4, word5, word6, word7;
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
    TextView c_time, score;
    Chronometer focus;
    LinearLayout adds, list4,adsLay1;
    LinearLayout qtw;
    String retype = "s";
    Typeface typ, tyr;
    int fb_reward = 0;
    int reward_status = 0;
    Dialog openDialog_s;
    RelativeLayout adsicon, adsicon2;
    int share_name = 0;
    int setting_access = 0;
    int loadaddcontent = 0;
    Dialog openDialog_p, openDialog_odd_man;
    long ttstop;
    String btn_str = "";
    int s = 0;
    int f_sec;
    int r = 0;
    TextView ttscores;
    TextView next_continue;
    String sa = "";
    int e2 = 0;
    Animation myFadeInAnimation;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper4 newhelper4;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    Dialog openDialog;
    int randomnod;
    int setval_vid;
    TextView coin_value;
    FirebaseAnalytics mFirebaseAnalytics;
    int dia_dismiss = 0;
    Handler handler;
    Runnable my_runnable;
   // private MaxInterstitialAd mInterstitialAd;
   // private MaxRewardedAd rewardedAd;
   private RewardedAd rewardedAd;
   private AdManagerInterstitialAd interstitialAd ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirukural);
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);


        if (sps.getString(Tirukural.this, "new_user_db").equals("")) {

        } else {
            if (sps.getString(Tirukural.this, "new_user_db").equals("on")) {
                sps.putString(Tirukural.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(Tirukural.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }

        myDbHelper = new DataBaseHelper(context);
        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);


        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Tirukural.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Tirukural.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Tirukural.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Tirukural.this, R.raw.coins, 1);
///
        ImageView prize_logo = findViewById(R.id.prize_logo);
        if (sps.getInt(Tirukural.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        //New_Main_Activity.fb_addload(Tirukural.this);
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable(this)) {
                if (sps.getString(Tirukural.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Tirukural.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Tirukural.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Tirukural.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Tirukural.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Tirukural.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");

        //Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        if (sps.getInt(Tirukural.this, "purchase_ads") == 0) {
           // industrialload();
            if (!sps.getString(Tirukural.this, "InterstitialId").equals("")|| sps.getString(Tirukural.this, "InterstitialId") != null) {
                industrialload();
            }
        }

        find();

       // Utills.INSTANCE.load_add_AppLovin(this, adds, getResources().getString(R.string.Bottom_Banner));
        if (sps.getInt(Tirukural.this, "purchase_ads") == 0) {
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
                adsLay1.setVisibility(View.GONE);
            }
        }else adsLay1.setVisibility(View.GONE);
        openDialog_s = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        myFadeInAnimation = AnimationUtils.loadAnimation(Tirukural.this, R.anim.blink_animation);


        String snd = sps.getString(Tirukural.this, "snd");

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        c_settings = findViewById(R.id.c_settings);
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
                sps.putString(Tirukural.this, "date", message);
                next();
            } else {
                sps.putString(Tirukural.this, "date", "0");
                next();
            }
        } else {
            sps.putString(Tirukural.this, "date", "0");
            next();
        }


        if (sps.getString(Tirukural.this, "Tirukural_game_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Tirukural.this, "sequence example Tirukural");

            sequence.setConfig(config);

            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");


            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Tirukural.this).setTarget(helpshare_layout).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener((itemView, position) -> {

                if (position == 1) {
                    sps.putString(Tirukural.this, "Tirukural_time_start", "yes");
                    sps.putString(Tirukural.this, "showcase_dismiss_tir", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            });
            sequence.start();
            sps.putString(Tirukural.this, "Tirukural_game_intro", "no");

        }

        if (sps.getInt(Tirukural.this, "reward_coin_txt") == 0) {
            sps.putInt(Tirukural.this, "reward_coin_txt", 20);
        }

        c_settings.setOnClickListener(v -> {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            String snd1 = sps.getString(Tirukural.this, "snd");
            if (snd1.equals("off")) {
                sps.putString(Tirukural.this, "snd", "on");
                c_settings.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd1.equals("on")) {
                sps.putString(Tirukural.this, "snd", "off");
                c_settings.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }
        });
        c_button1.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button1.getText().toString();
                senddata(data);
                check_answer();
                c_button1.setText("");
            }
        });
        c_button2.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button2.getText().toString();
                senddata(data);
                check_answer();
                c_button2.setText("");
            }
        });

        c_button3.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button3.getText().toString();
                senddata(data);
                check_answer();
                c_button3.setText("");
            }
        });
        c_button4.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button4.getText().toString();
                senddata(data);
                check_answer();
                c_button4.setText("");
            }
        });
        c_button5.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button5.getText().toString();
                senddata(data);
                check_answer();
                c_button5.setText("");
            }
        });
        c_button6.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button6.getText().toString();
                senddata(data);
                check_answer();
                c_button6.setText("");
            }
        });
        c_button7.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button7.getText().toString();
                senddata(data);
                check_answer();
                c_button7.setText("");
            }
        });
        c_button8.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button8.getText().toString();
                senddata(data);
                check_answer();
                c_button8.setText("");
            }
        });
        c_button9.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button9.getText().toString();
                senddata(data);
                check_answer();
                c_button9.setText("");
            }
        });
        c_button10.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button10.getText().toString();
                senddata(data);
                check_answer();
                c_button10.setText("");
            }
        });
        c_button11.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button11.getText().toString();
                senddata(data);
                check_answer();
                c_button11.setText("");
            }
        });
        c_button12.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            String data1 = word1.getText().toString();
            String data2 = word2.getText().toString();
            String data3 = word3.getText().toString();
            String data4 = word4.getText().toString();
            String data5 = word5.getText().toString();
            String data6 = word6.getText().toString();
            String data7 = word7.getText().toString();
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {

            } else {
                String data = c_button12.getText().toString();
                senddata(data);
                check_answer();
                c_button12.setText("");
            }
        });

        word1.setOnClickListener(v -> {

            String data = word1.getText().toString();
            senddata_toword(data);
            word1.setText("");
            clearnew();

        });
        word2.setOnClickListener(v -> {

            String data = word2.getText().toString();
            senddata_toword(data);
            word2.setText("");

        });
        word3.setOnClickListener(v -> {
            String data = word3.getText().toString();
            senddata_toword(data);
            word3.setText("");
            clearnew();
        });
        word4.setOnClickListener(v -> {
            String data = word4.getText().toString();
            senddata_toword(data);
            word4.setText("");
            clearnew();

        });
        word5.setOnClickListener(v -> {
            String data = word5.getText().toString();
            senddata_toword(data);
            word5.setText("");
            clearnew();

        });
        word6.setOnClickListener(v -> {
            String data = word6.getText().toString();
            senddata_toword(data);
            word6.setText("");
            clearnew();

        });
        word7.setOnClickListener(v -> {
            String data = word7.getText().toString();
            senddata_toword(data);
            word7.setText("");
            clearnew();
        });


        earncoin.setOnClickListener(v -> dialog(0));
        qtw.setOnClickListener(v -> dialog(0));

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

        c_ans.setOnClickListener(v -> {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk >= 50) {
                r = 1;
                if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                    Cursor cd;
                    String date = sps.getString(Tirukural.this, "date");
                    if (date.equals("0")) {
                        cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "' and isfinish='0' and gameid='" + gameid + "'");
                        cd.moveToFirst();
                    } else {
                        cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "' and gameid='" + gameid + "'");
                        cd.moveToFirst();
                    }

                    String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                    //Toast.makeText(Clue_Game_Hard.this, "" + sa, Toast.LENGTH_SHORT).show();
                    //Score Adding
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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
                    // Toast.makeText(Tirukural.this, "Ans" + sa, Toast.LENGTH_SHORT).show();

                    setans(sa);
                    list4.setVisibility(View.INVISIBLE);

                    Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                    ans_high.startAnimation(w_game);
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(sa);
                    //Update QST
                    String datee = sps.getString(Tirukural.this, "date");
                    if (datee.equals("0")) {
                        newhelper3.executeSql("UPDATE right_order SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    } else {
                        newhelper3.executeSql("UPDATE right_order SET daily=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                    }
                    //Next Function
                    //  r = 1;


                    focus.stop();
                    // completegame();
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> adShow("1"), 3000);

                } else {
                    final Dialog openDialog = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog.setContentView(R.layout.show_ans);
                    TextView yes = openDialog.findViewById(R.id.yes);
                    TextView no = openDialog.findViewById(R.id.no);
                    TextView txt_ex2 = openDialog.findViewById(R.id.txt_ex2);
                    txt_ex2.setText("மொத்த நாணயங்களில் 50 குறைக்கப்படும்");
                    CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                    checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                        if (isChecked) {
                            sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                        } else {
                            sps.putString(getApplicationContext(), "checkbox_ans", "");
                        }
                    });

                    yes.setOnClickListener(v12 -> {
                        Cursor cd;
                        String date = sps.getString(Tirukural.this, "date");
                        if (date.equals("0")) {
                            cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "' and isfinish='0' and gameid='" + gameid + "'");

                            cd.moveToFirst();
                        } else {
                            cd = newhelper3.getQry("SELECT * FROM right_order where  questionid='" + questionid + "'  and gameid='" + gameid + "'");
                            cd.moveToFirst();
                        }
                        String sas = null;
                        if (cd.getCount() != 0) {
                            sas = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        }
                        //Toast.makeText(Clue_Game_Hard.this, "" + sa, Toast.LENGTH_SHORT).show();
                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        if (cfx.getCount() != 0) {
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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
                        setans(sas);
                        //Update QST
                        String datee = sps.getString(Tirukural.this, "date");
                        if (datee.equals("0")) {
                            newhelper3.executeSql("UPDATE right_order SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        } else {
                            newhelper3.executeSql("UPDATE right_order SET daily=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                        }
                        //Next Function
                        // r = 1;
                        openDialog.dismiss();

                        focus.stop();
                        //    completegame();

                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow("2"), 3000);


                    });
                    no.setOnClickListener(v1 -> {
                        sps.putString(getApplicationContext(), "checkbox_ans", "");
                        openDialog.dismiss();
                    });
                    if (!isFinishing()) openDialog.show();
                }


            } else {
                dialog(1);
            }
        });


    }


    private void setans(String sa) {

        String tfoption = sa;
        first = tfoption.split(",");
        word1.setText("" + first[0]);
        word2.setText("" + first[1]);
        word3.setText("" + first[2]);
        word4.setText("" + first[3]);
        word5.setText("" + first[4]);
        word6.setText("" + first[5]);
        word7.setText("" + first[6]);
        correctans();
    }

    private void check_answer() {

        String data1 = word1.getText().toString();
        String data2 = word2.getText().toString();
        String data3 = word3.getText().toString();
        String data4 = word4.getText().toString();
        String data5 = word5.getText().toString();
        String data6 = word6.getText().toString();
        String data7 = word7.getText().toString();

        String ans = data1 + "," + data2 + "," + data3 + "," + data4 + "," + data5 + "," + data6 + "," + data7;
        //  Toast.makeText(this, "ans"+ans, Toast.LENGTH_SHORT).show();

        if (ans.equals(answer)) {
            String date = sps.getString(Tirukural.this, "date");
            if (date.equals("0")) {
                newhelper3.executeSql("UPDATE right_order SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            } else {
                newhelper3.executeSql("UPDATE right_order SET daily=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            }

            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
            focus.stop();
            coinanim();
            price_update();
            // Toast.makeText(this, "Correct_Answer", Toast.LENGTH_SHORT).show();


            correctans();
            /*Handler handler = new Handler(Looper.myLooper());
            handler.postDelayed(() -> adShow("3"), 2000);*/
        } else {
            if (data1.length() != 0 && data2.length() != 0 && data3.length() != 0 && data4.length() != 0 && data5.length() != 0 && data6.length() != 0 && data7.length() != 0) {
                wrongans();
            }
            //Toast.makeText(this, "Worng_Answer", Toast.LENGTH_SHORT).show();
        }

    }

    private void senddata_toword(String data) {
        if (c_button1.getText().toString().equals("")) {
            c_button1.setText(data);
        } else if (c_button2.getText().toString().equals("")) {
            c_button2.setText(data);
        } else if (c_button3.getText().toString().equals("")) {
            c_button3.setText(data);
        } else if (c_button5.getText().toString().equals("")) {
            c_button5.setText(data);
        } else if (c_button6.getText().toString().equals("")) {
            c_button6.setText(data);
        } else if (c_button7.getText().toString().equals("")) {
            c_button7.setText(data);
        } else if (c_button9.getText().toString().equals("")) {
            c_button9.setText(data);
        }
    }

    private void senddata(String data) {
        if (word1.getText().toString().equals("")) {
            word1.setText(data);
        } else if (word2.getText().toString().equals("")) {
            word2.setText(data);
        } else if (word3.getText().toString().equals("")) {
            word3.setText(data);
        } else if (word4.getText().toString().equals("")) {
            word4.setText(data);
        } else if (word5.getText().toString().equals("")) {
            word5.setText(data);
        } else if (word6.getText().toString().equals("")) {
            word6.setText(data);
        } else if (word7.getText().toString().equals("")) {
            word7.setText(data);
        }
    }

    private void find() {

        to_no = findViewById(R.id.c_word_number);
        question_txt = findViewById(R.id.question_txt);
        focus = findViewById(R.id.c_time_edit);
        c_clear = findViewById(R.id.clue_clear);
        c_ans = findViewById(R.id.c_ans);
        c_coin = findViewById(R.id.c_coins);
        ans_high = findViewById(R.id.ans_highlite);


        c_button1 = findViewById(R.id.c_button1);
        c_button2 = findViewById(R.id.c_button2);
        c_button3 = findViewById(R.id.c_button3);
        c_button4 = findViewById(R.id.c_button4);
        c_button5 = findViewById(R.id.c_button5);
        c_button6 = findViewById(R.id.c_button6);
        c_button7 = findViewById(R.id.c_button7);
        c_button8 = findViewById(R.id.c_button8);
        c_button9 = findViewById(R.id.c_button9);
        c_button10 = findViewById(R.id.c_button10);
        c_button11 = findViewById(R.id.c_button11);
        c_button12 = findViewById(R.id.c_button12);

        word1 = findViewById(R.id.word1);
        word2 = findViewById(R.id.word2);
        word3 = findViewById(R.id.word3);
        word4 = findViewById(R.id.word4);
        word5 = findViewById(R.id.word5);
        word6 = findViewById(R.id.word6);
        word7 = findViewById(R.id.word7);


        to_no = findViewById(R.id.c_word_number);
        question_txt = findViewById(R.id.question_txt);


        earncoin = findViewById(R.id.earncoin);
        w_head = findViewById(R.id.clue_head);
        h_gplues = findViewById(R.id.ch_gplues);
        h_watts_app = findViewById(R.id.ch_watts_app);
        h_facebook = findViewById(R.id.ch_facebook);
        to_no = findViewById(R.id.c_word_number);
        score = findViewById(R.id.c_score_edit);
        focus = findViewById(R.id.c_time_edit);
        c_settings = findViewById(R.id.c_settings);
        c_edit = findViewById(R.id.clue_ans_editer);
        adds = findViewById(R.id.ads_lay);
        adsLay1 = findViewById(R.id.adsLay1);
        qtw = findViewById(R.id.qwt);

        list4 = findViewById(R.id.list4);
        helpshare_layout = findViewById(R.id.helpshare_layout);


        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        if (cfq.getCount() != 0) {
            int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
            String tr = String.valueOf(skq);
            score.setText(tr);
        }

    }

    private void next() {


        word1.setText("");
        word2.setText("");
        word3.setText("");
        word4.setText("");
        word5.setText("");
        word6.setText("");
        word7.setText("");
        clearnew();

        c_ans.setBackgroundResource(R.drawable.yellow_question);
        list4.setVisibility(View.VISIBLE);
        c_ans.setEnabled(true);


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

        if (sps.getString(Tirukural.this, str_date1).equals("")) {

            daily_bones();
            sps.putString(Tirukural.this, str_date1, "yes");

        }


        //Toast.makeText(this, "daily_start"+daily_start, Toast.LENGTH_SHORT).show();
        if (daily_start == 1) {
            Toast.makeText(Tirukural.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான திருக்குறள் விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
            sps.putString(context, "date", "0");
            daily_start = 0;
        }


        String date = sps.getString(Tirukural.this, "date");
        if (date.equals("0")) {
            Cursor c1 = newhelper3.getQry("select * from right_order where gameid='" + gameid + "'");
            c1.moveToFirst();
            Cursor c2 = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            to_no.setText(no/*+"/"+c1.getCount()*/);
            to_no.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        } else {
            if (sps.getInt(Tirukural.this, "purchase_ads") == 1) {

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
            c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            daily_start = 1;
            c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "'and daily='0'  order by random() limit 1");
            c.moveToFirst();
        }

        if (c.getCount() != 0) {
            questionid = c.getString(c.getColumnIndexOrThrow("questionid"));
            question = c.getString(c.getColumnIndexOrThrow("question"));
            answer = c.getString(c.getColumnIndexOrThrow("answer"));
            split_word = c.getString(c.getColumnIndexOrThrow("splitword"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));

            System.out.println("######kural:" + answer);

            String tfoption2 = question;
            start = tfoption2.split(",");
            question_txt.setText("" + start[3]);


            String tfoption = answer;
            first = tfoption.split(",");
            //   type = first.length;

            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;
            if (randomno == 1) {
                set_question(1);
            } else if (randomno == 2) {
                set_question(2);
            } else if (randomno == 3) {
                set_question(3);
            }

            if (playtime == 0) {

                if (sps.getString(Tirukural.this, "Tirukural_time_start").equals("yes")) {
                    focus.setBase(SystemClock.elapsedRealtime() + playtime);
                    focus.start();
                }

            } else {
                focus.setBase(SystemClock.elapsedRealtime() + playtime);
                focus.start();


            }


        } else {
            if (date.equals("0")) {
                nextgamesdialog();
            } else {
                newhelper3.executeSql("UPDATE right_order SET daily=0 WHERE gameid='" + gameid + "'");
                next();
            }
        }

    }

    private void daily_bones() {
        openDialog = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog.findViewById(R.id.ok_y);
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
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Tirukural.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Tirukural.this, "daily_bonus_date"))) {

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

        extra_coin.setOnClickListener(v -> {
            rvo = 1;
            extra_coin_s = 1;
            if (isNetworkAvailable(this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Tirukural.this, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    show_reward();
                } else {
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();
                        if (fb_reward == 1) {
                            show_reward();
                            // mShowVideoButton.setVisibility(View.VISIBLE);
                        } else {
                            //reward(Tirukural.this);
                            rewarded_adnew();
                            Toast.makeText(Tirukural.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);


                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }
        });


        if (!isFinishing()) openDialog.show();
    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);

        TextView exit = openDialog.findViewById(R.id.exit);

        String date = sps.getString(Tirukural.this, "date");
        if (date.equals("0")) {
            next_game.setText("திருக்குறள்  தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("தினசரி திருக்குறள்  புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள்  சிறப்பாக விளையாட காத்திருக்கும்  விளையாட்டுக்கள்.");
        }
        openDialog.setCancelable(false);
        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Clue_Game_Hard.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Picture_Game_Hard.class);
            startActivity(i);
        });
        exit.setOnClickListener(v -> {
            if (main_act.equals("")) {
                finish();
                Intent i = new Intent(Tirukural.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Tirukural.this, "game_area", "on");
                finish();
            }
            sps.putString(Tirukural.this, "date", "0");
        });

        Cursor ct;
        ct = myDbHelper.getQry("select * from maintable where isfinish='0' order by id limit 1");
        ct.moveToFirst();
        if (ct.getCount() != 0) {
            Cursor c;
            c = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0' order by id limit 1");
            c.moveToFirst();
            if (c.getCount() != 0) {
                p_game.setVisibility(View.VISIBLE);
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
            c4 = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='0' order by id limit 1");
            c4.moveToFirst();
            if (c4.getCount() != 0) {
                c_game.setVisibility(View.VISIBLE);
            }
        } else {
            next_game.setText("புதிய பதிவுகள் ஏதும் இல்லை.விரைவில் வினாக்கள் பதிவேற்றம் செய்யப்படும்.");
            exit.setText("சரி ");
            exit.setVisibility(View.VISIBLE);
        }

        TextView odd_man_out = openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Odd_man_out.class);
            startActivity(i);
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

        TextView opposite_word = openDialog.findViewById(R.id.opposite_word);
        TextView ote_to_tamil = openDialog.findViewById(R.id.ote_to_tamil);
        opposite_word.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Ote_to_Tamil.class);
            startActivity(i);
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


        seerpaduthu.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Riddle_game.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, WordError_correction.class);
            startActivity(i);
        });


        TextView fill_in_blanks = openDialog.findViewById(R.id.fill_in_blanks);
        TextView eng_to_tamil = openDialog.findViewById(R.id.eng_to_tamil);

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

        fill_in_blanks.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Fill_in_blanks.class);
            startActivity(i);
        });


        TextView quiz = openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = openDialog.findViewById(R.id.match_words);
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

        match_words.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Match_tha_fallows_game.class);
            startActivity(i);

        });
        find_words_from_pictures.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Find_words_from_picture.class);
            startActivity(i);
        });
        quiz.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Quiz_Game.class);
            startActivity(i);
        });

        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Tirukural.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Jamble_word_game.class);
            startActivity(i);
        });
        TextView missing_words = openDialog.findViewById(R.id.missing_words);
        Cursor jmps;
        jmps = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='0' order by id limit 1");
        jmps.moveToFirst();
        if (jmps.getCount() != 0) {
            missing_words.setVisibility(View.VISIBLE);
        }
        missing_words.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Missing_Words.class);
            startActivity(i);
        });
        TextView six_differences = openDialog.findViewById(R.id.six_differences);
        Cursor dif;
        dif = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='0' order by id limit 1");
        dif.moveToFirst();
        if (dif.getCount() != 0) {
            six_differences.setVisibility(View.VISIBLE);
        }
        six_differences.setOnClickListener(v -> {
            finish();
            sps.putString(Tirukural.this, "date", "0");
            Intent i = new Intent(Tirukural.this, Find_difference_between_pictures.class);
            startActivity(i);
        });
        if (!isFinishing()) openDialog.show();
        openDialog.setOnKeyListener((dialog, keyCode, event) -> {

            if (main_act.equals("")) {

                finish();
                //     openDialog_s.dismiss();
                Intent i = new Intent(Tirukural.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Tirukural.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Tirukural.this, "date", "0");

            return keyCode == KeyEvent.KEYCODE_BACK;
        });

    }

    private void set_question(int i) {
        Cursor c;
        c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "'and daily='0'  order by random() limit 1");
        c.moveToFirst();
        if (c.getCount() != 0) {
            answers = c.getString(c.getColumnIndexOrThrow("answer"));
        }

        String tfoption = answers;
        first1 = tfoption.split(",");

        if (i == 1) {
            c_button5.setText("" + first[0]);
            c_button3.setText("" + first[1]);
            c_button7.setText("" + first[2]);
            c_button1.setText("" + first[3]);
            c_button2.setText("" + first[4]);
            c_button9.setText("" + first[5]);
            c_button6.setText("" + first[6]);

        } else if (i == 2) {
            c_button3.setText("" + first[0]);
            c_button2.setText("" + first[1]);
            c_button5.setText("" + first[2]);
            c_button7.setText("" + first[3]);
            c_button6.setText("" + first[4]);
            c_button9.setText("" + first[5]);
            c_button1.setText("" + first[6]);

        } else if (i == 3) {
            c_button5.setText("" + first[0]);
            c_button2.setText("" + first[1]);
            c_button6.setText("" + first[2]);
            c_button9.setText("" + first[3]);
            c_button7.setText("" + first[4]);
            c_button3.setText("" + first[5]);
            c_button1.setText("" + first[6]);

        }
    }

    public void correctans() {

        word1.setEnabled(false);
        word2.setEnabled(false);
        word3.setEnabled(false);
        word4.setEnabled(false);
        word5.setEnabled(false);
        word6.setEnabled(false);
        word7.setEnabled(false);

        c_ans.setEnabled(false);

        word1.setBackgroundResource(R.drawable.right_ans);
        word2.setBackgroundResource(R.drawable.right_ans);
        word3.setBackgroundResource(R.drawable.right_ans);
        word4.setBackgroundResource(R.drawable.right_ans);
        word5.setBackgroundResource(R.drawable.right_ans);
        word6.setBackgroundResource(R.drawable.right_ans);
        word7.setBackgroundResource(R.drawable.right_ans);


        word1.setTextColor(getResources().getColor(R.color.colorAccent));
        word2.setTextColor(getResources().getColor(R.color.colorAccent));
        word3.setTextColor(getResources().getColor(R.color.colorAccent));
        word4.setTextColor(getResources().getColor(R.color.colorAccent));
        word5.setTextColor(getResources().getColor(R.color.colorAccent));
        word6.setTextColor(getResources().getColor(R.color.colorAccent));
        word7.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void wrongans() {

        word1.setBackgroundResource(R.drawable.worng_ans);
        word2.setBackgroundResource(R.drawable.worng_ans);
        word3.setBackgroundResource(R.drawable.worng_ans);
        word4.setBackgroundResource(R.drawable.worng_ans);
        word5.setBackgroundResource(R.drawable.worng_ans);
        word6.setBackgroundResource(R.drawable.worng_ans);
        word7.setBackgroundResource(R.drawable.worng_ans);

        word1.setTextColor(getResources().getColor(R.color.white));
        word2.setTextColor(getResources().getColor(R.color.white));
        word3.setTextColor(getResources().getColor(R.color.white));
        word4.setTextColor(getResources().getColor(R.color.white));
        word5.setTextColor(getResources().getColor(R.color.white));
        word6.setTextColor(getResources().getColor(R.color.white));
        word7.setTextColor(getResources().getColor(R.color.white));
    }

    public void clearnew() {


        word1.setEnabled(true);
        word2.setEnabled(true);
        word3.setEnabled(true);
        word4.setEnabled(true);
        word5.setEnabled(true);
        word6.setEnabled(true);
        word7.setEnabled(true);

        word1.setTextColor(getResources().getColor(R.color.black));
        word2.setTextColor(getResources().getColor(R.color.black));
        word3.setTextColor(getResources().getColor(R.color.black));
        word4.setTextColor(getResources().getColor(R.color.black));
        word5.setTextColor(getResources().getColor(R.color.black));
        word6.setTextColor(getResources().getColor(R.color.black));
        word7.setTextColor(getResources().getColor(R.color.black));

        word1.setBackgroundResource(R.drawable.game_buttons);
        word2.setBackgroundResource(R.drawable.game_buttons);
        word3.setBackgroundResource(R.drawable.game_buttons);
        word4.setBackgroundResource(R.drawable.game_buttons);
        word5.setBackgroundResource(R.drawable.game_buttons);
        word6.setBackgroundResource(R.drawable.game_buttons);
        word7.setBackgroundResource(R.drawable.game_buttons);

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


    //reward videos***********************//

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


    public void ins_app(final Context context, View view1, int vall) {
        TextView titt = view1.findViewById(R.id.txtlist);
        ImageView logo = view1.findViewById(R.id.imageview);
        final Button inss = view1.findViewById(R.id.btn_cont);
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

        view1.setOnClickListener(view -> {
            if (isNetworkAvailable(context)) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inss.getTag().toString())));
            } else {
                Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });

        inss.setOnClickListener(view -> {
            if (isNetworkAvailable(context)) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(view.getTag().toString())));
            } else {
                Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });
    }

/*    private void industrialload() {
        //AppLovinSdk.getInstance( this ).showMediationDebugger();
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(AppLovinSdkConfiguration config) {
                // AppLovin SDK is initialized, start loading ads
                if (mInterstitialAd != null && mInterstitialAd.isReady()) return;
                System.out.println("ad shown  showAdWithDelay initialize done ");
                mInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Viliyodu_Vilaiyadu_Ins), Tirukural.this);
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
                        sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", 0);
                        setSc();
                    }
                });

                // Load the first ad
                mInterstitialAd.loadAd();

            }
        });

    }*/

    public void industrialload() {
        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        AdManagerInterstitialAd.load(this,sps.getString(this, "InterstitialId"), adRequest,
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
                                sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", 0);
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

    /*public void adShow(String c) {
        //Toast.makeText(this, "$"+c, Toast.LENGTH_SHORT).show();
        if (sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV") ==*//* Utills.interstitialadCount*//* Integer.parseInt( sps.getString(this, "showCountOther")) && interstitialAd != null) {
            sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", 0);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                if (interstitialAd == null) setSc();
                else
                    interstitialAd.show(this);
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", (sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV") + 1));
            if (sps.getInt(this, "Game1_Stage_Close_VV") > *//*Utills.interstitialadCount*//* Integer.parseInt( sps.getString(this, "showCountOther")))
                sps.putInt(this, "Game1_Stage_Close_VV", 0);
            setSc();
            //Toast.makeText(this, ""+sps.getInt(this, "Game1_Stage_Close_VV"), Toast.LENGTH_SHORT).show();

        }

    }*/

    private int safeParseInt(String value, int defaultValue) {
        if (value != null && !value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue; // Return the default value if parsing fails
            }
        }
        return defaultValue; // Also return default if the input is null or empty
    }

    public void adShow(String c) {
        // Toast.makeText(this, "$" + c, Toast.LENGTH_SHORT).show();
        int showCountOther = safeParseInt(sps.getString(this, "showCountOther"), 0);
        int currentStageCloseVV = sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV");

        if (!sps.getString(this, "showCountOther").equals("0")) {
            if (currentStageCloseVV == showCountOther && interstitialAd != null) {
                sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", 0);
                Utills.INSTANCE.Loading_Dialog(this);
                Handler handler = new Handler(Looper.myLooper());
                Runnable my_runnable = () -> {
                    if (interstitialAd == null) {
                        setSc();
                    } else {
                        interstitialAd.show(this);
                    }
                };
                handler.postDelayed(my_runnable, 2500);
            } else {
                currentStageCloseVV++;
                sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", currentStageCloseVV);
                if (currentStageCloseVV > showCountOther) {
                    sps.putInt(this, "Game1_Stage_Close_VV", 0);
                }
                setSc();
            }
        }else{
            currentStageCloseVV++;
            sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", currentStageCloseVV);
            if (currentStageCloseVV > showCountOther) {
                sps.putInt(this, "Game1_Stage_Close_VV", 0);
            }
            setSc();

        }

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
            final Dialog openDialog = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    public void dialog(int i) {
        final Dialog openDialog_earncoin = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                    String date = sps.getString(Tirukural.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                        // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");

                        // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
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
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }


        });

        wp.setOnClickListener(view -> {
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


                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });
        fb.setOnClickListener(view -> {
        });
        gplus.setOnClickListener(view -> {


            if (isNetworkAvailable(this)) {


                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Tirukural.this, "date");
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
                    Intent i1 = new Intent(Intent.ACTION_SEND);
                    i1.setType("text/plain");
                    i1.setPackage("com.google.android.apps.plus");
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i1.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i1, "Share via"), 15);


                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }

        });
        openDialog_earncoin.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);

        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Tirukural.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");

            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        }
    }

    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "" + sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV"), Toast.LENGTH_SHORT).show();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ON Resume  " + sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV"));

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Tirukural.this);
        Bundle params = new Bundle();
        params.putString("screen_name", "Tirukural Gam");
        params.putString("screen_class", "Tirukural");
        mFirebaseAnalytics.logEvent( "screen_view", params);
        //uiHelper.onResume();


        if (sps.getString(Tirukural.this, "Tirukural_time_start").equals("")) {
            sps.putString(Tirukural.this, "Tirukural_time_start", "yes");
        } else {
            String date = sps.getString(Tirukural.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                pos = 1;
                cs = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + questionid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                }
            } else {
                pos = 2;
                cs = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + questionid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                }
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

    }
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            sps.putString(Tirukural.this, "game_area", "on");
            sps.putInt(Tirukural.this, "addlodedd", 0);
            s = 1;
            openDialog_p = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.back_pess);
            TextView yes = openDialog_p.findViewById(R.id.yes);
            TextView no = openDialog_p.findViewById(R.id.no);


            yes.setOnClickListener(v -> {

                String dates = sps.getString(Tirukural.this, "date");
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
                    newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");

                    //    myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                }

                String date = sps.getString(Tirukural.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Tirukural.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                } else {
                    if (sps.getString(Tirukural.this, "Exp_list").equals("on")) {
                        finish();
                        Intent i = new Intent(Tirukural.this, Expandable_List_View.class);
                        startActivity(i);
                    } else {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Tirukural.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    }

                }


                //ad
                openDialog_p.dismiss();
                //ad


            });
            no.setOnClickListener(v -> openDialog_p.dismiss());
            openDialog_p.show();


        }
    };

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Tirukural.this, "date");
        if (date.equals("0")) {
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

        } else {
            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");

        }
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
                    String date = sps.getString(Tirukural.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                        //myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");

                        // myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    }

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Tirukural.this, Tirukural.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் திருக்குறள் இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT, "Solli_Adi");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);


        if (requestCode == 15) {
            if (resultCode == -1) {
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
                if (str_month1.length() == 1) {
                    str_month1 = "0" + str_month1;
                }

                String str_day1 = "" + cur_day1;
                if (str_day1.length() == 1) {
                    str_day1 = "0" + str_day1;
                }
                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

                if (sps.getString(Tirukural.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Tirukural.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Tirukural.this, "complite_reg").equals("yes")) {
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
        if (requestCode == 16) {
            if (resultCode == -1) {
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
                if (str_month1.length() == 1) {
                    str_month1 = "0" + str_month1;
                }

                String str_day1 = "" + cur_day1;
                if (str_day1.length() == 1) {
                    str_day1 = "0" + str_day1;
                }
                final String str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;

                if (sps.getString(Tirukural.this, "complite_reg").equals("yes")) {
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

    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        final Dialog openDialog = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        // uiHelper.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
        rewardedAd = null;
        interstitialAd = null;
        handler = null;
    }


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
        final TextView discription = openDialog_s.findViewById(R.id.discription);
        //discription.startAnimation(myFadeInAnimation);
        final LinearLayout rewardvideo = openDialog_s.findViewById(R.id.rewardvideo);
        final LinearLayout vid_earn = openDialog_s.findViewById(R.id.vid_earn);

        LinearLayout ads_layout = openDialog_s.findViewById(R.id.fl_adplaceholder);
        ImageView prize_logo = openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Tirukural.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable(this)) {
                if (sps.getString(Tirukural.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Tirukural.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Tirukural.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Tirukural.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Tirukural.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Tirukural.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });

        TextView video_earn = openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Tirukural.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Tirukural.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(Tirukural.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            //New_Main_Activity.load_addFromMain_multiplayer(Tirukural.this, ads_layout);
            if (isNetworkAvailable(context)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(context, ads_layout);
            } else {
                ads_layout.setVisibility(View.GONE);
            }
        }


        word.setTypeface(tyr);
        next_continue.setVisibility(View.VISIBLE);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);
        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
        next_continue.setText("ªî£ì˜è");
        discription.setVisibility(View.VISIBLE);
        String date = sps.getString(Tirukural.this, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            String aStringx = Integer.toString(skx);
            ttscores.setText(aStringx);
        }


        if (sps.getString(Tirukural.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Tirukural.this, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }

        if (ry == 1) {

        } else {
            rewardvideo.setVisibility(View.INVISIBLE);
        }

        RelativeLayout adsicon = openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);
        discription.setOnClickListener(v -> dialog_dicription());
        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            if (isNetworkAvailable(this)) {
                rvo = 2;
                if (isNetworkAvailable(context)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        show_reward();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler(Looper.myLooper()).postDelayed(() -> {
                            reward_progressBar.dismiss();
                            if (fb_reward == 1) {
                                show_reward();
                                // mShowVideoButton.setVisibility(View.VISIBLE);
                            } else {

                                rewarded_adnew();
                                Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                            }
                        }, 2000);
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }

        });

        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (isNetworkAvailable(context)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    show_reward();
                    rewardvideo.setVisibility(View.INVISIBLE);
                } else {
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();
                        if (fb_reward == 1) {
                            show_reward();
                            // mShowVideoButton.setVisibility(View.VISIBLE);
                        } else {
                            rewarded_adnew();
                            Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }
        });

        wtp.setOnClickListener(view -> {
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


                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        });
        fbs.setOnClickListener(view -> {

        });
        gplus.setOnClickListener(view -> {
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

                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }

        });

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


        next_continue.setOnClickListener(view -> {
            dia_dismiss = 1;
            openDialog_s.dismiss();
            next();

        });
        openDialog_s.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Tirukural.this, "game_area", "on");
                String date1 = sps.getString(Tirukural.this, "date");
                if (date1.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Tirukural.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                        openDialog_s.dismiss();
                    }
                } else {
                    if (sps.getString(Tirukural.this, "Exp_list").equals("on")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Tirukural.this, Expandable_List_View.class);
                        startActivity(i);

                    } else {
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Tirukural.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                            openDialog_s.dismiss();
                        }


                    }
                }

            } else {
                dia_dismiss = 0;
            }

        });
        if (!isFinishing()) {
            openDialog_s.show();
        }

    }

    private void dialog_dicription() {
        openDialog_odd_man = new Dialog(Tirukural.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_odd_man.setContentView(R.layout.discription_tricurral);

        TextView trikural = openDialog_odd_man.findViewById(R.id.trikural);
        TextView dis = openDialog_odd_man.findViewById(R.id.dis);
        TextView tittle = openDialog_odd_man.findViewById(R.id.tittle);
        TextView tittle1 = openDialog_odd_man.findViewById(R.id.tittle1);
        TextView tittle3 = openDialog_odd_man.findViewById(R.id.tittle3);
        TextView tittle5 = openDialog_odd_man.findViewById(R.id.tittle5);
        TextView kural_no_txt = openDialog_odd_man.findViewById(R.id.kural_no_txt);
        final LinearLayout ads_lay = openDialog_odd_man.findViewById(R.id.ads_lay);

        TextView close = openDialog_odd_man.findViewById(R.id.close);

        dis.setText("" + split_word);


        String tfoptions = question;
        String[] first2 = tfoptions.split(",");

        //  tittle.setText("குறள் பால் : " + first2[1] + "\n" + "குறள் இயல் : " + first2[2] + "\n" + "அதிகாரம் : " + first2[3]);

        tittle1.setText("" + first2[1]);
        tittle3.setText("" + first2[2]);
        tittle5.setText("" + first2[3]);

        String tfoption = answer;
        first = tfoption.split(",");

        close.setOnClickListener(v -> openDialog_odd_man.dismiss());
        kural_no_txt.setText("" + first2[0] + "");
        trikural.setText("" + first[0] + " " + first[1] + "" + " " + first[2] + "" + " " + first[3] + "" + "\n" + first[4] + " " + first[5] + " " + first[6]);

        openDialog_odd_man.show();
    }

    public void coinanim() {
////

        //score intial

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
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
        c_coin.postDelayed(() -> c_coin.setVisibility(View.INVISIBLE), transAnimation.getDuration());


        ////

        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(() -> {
            coin.play(soundId4, sv, sv, 0, 0, sv);
            c_coin.setVisibility(View.VISIBLE);
            int[] locationInWindow1 = new int[2];
            c_coin.getLocationInWindow(locationInWindow1);
            int[] locationOnScreen1 = new int[2];
            c_coin.getLocationOnScreen(locationOnScreen1);
            float sourceX1 = locationOnScreen1[0];
            float sourceY1 = locationOnScreen1[1];
            int[] locationInWindowSecond1 = new int[2];
            score.getLocationInWindow(locationInWindowSecond1);
            int[] locationOnScreenSecond1 = new int[2];
            score.getLocationOnScreen(locationOnScreenSecond1);
            float destinationX1 = locationOnScreenSecond1[0];
            float destinationY1 = locationOnScreenSecond1[1];
            TranslateAnimation transAnimation1 = new TranslateAnimation(0f, (destinationX1 - sourceX1), 0f, (destinationY1 - sourceY1));
            transAnimation1.setDuration(1000);
            c_coin.startAnimation(transAnimation1);
            c_coin.postDelayed(() -> c_coin.setVisibility(View.INVISIBLE), transAnimation1.getDuration());
        }, 1000);

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
            score.startAnimation(levels1);
        }, 2200);

        new Thread(() -> {
            int es = e2 + 20;
            while (e2 < es) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                score.post(() -> score.setText("" + e2));
                e2++;
            }

        }).start();

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(() -> {
            //Score Setting
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 20;
            String aStringx = Integer.toString(spx);
            score.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

            Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
            ch.moveToFirst();
            int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
            int shh = sh + 50;
            myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");


            adShow("4");
        }, 2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Tirukural.this, "permission", 1);
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
                        sps.putInt(Tirukural.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Tirukural.this, "permission", 0);
                    }
                }
            }

        }
    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Tirukural.this, "showcase_dismiss_tir").equals("")) {
                showcase_dismiss();
            } else {
                sps.putString(context, "Tirukural_game_intro", "yes");
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
        String date = sps.getString(Tirukural.this, "date");

        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                prize_data_update(Tirukural.this, 75);
            } else if (timeElapsed > 91300) {
                prize_data_update(Tirukural.this, 50);
            } else {
                prize_data_update(Tirukural.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                prize_data_update(Tirukural.this, 100);
            } else if (timeElapsed > 91300) {
                prize_data_update(Tirukural.this, 75);
            } else {
                prize_data_update(Tirukural.this, 50);
            }
        }
        ////////////////Prize//////////////////
    }


/*    public void rewarded_adnew() {
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

        RewardedAd.load(this, sps.getString(Tirukural.this, "RewardedId"),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.e("LoadAdError=========", loadAdError.toString());
                        rewardedAd = null;
                        reward_status=0;
                        //isfaild = 2;

                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        //  isfaild = 1;
                        fb_reward = 1;
                        reward_status=0;
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
                                reward_status=0;
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
   /* public void show_reward() {
        if (rewardedAd != null && rewardedAd.isReady()) {
            rewardedAd.showAd();
            reward_status = 1;
        } else {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
        }
    }*/


    public void show_reward() {
        if (rewardedAd != null) {
            rewardedAd.show(Tirukural.this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
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
}
