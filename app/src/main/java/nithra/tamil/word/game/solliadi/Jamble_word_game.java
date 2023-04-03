package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Random;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Jamble_word_game extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, Download_completed {
    public static final String TAG = "SavedGames";
    //*********************reward videos process 1***********************
    //private final String AD_UNIT_ID = getString(R.string.rewarded);
    //reward videos process 1***********************
    static final int mCoinCount = 20;
    // The AppState slot we are editing.  For simplicity this sample only manipulates a single
    // Cloud Save slot and a corresponding Snapshot entry,  This could be changed to any integer
    // 0-3 without changing functionality (Cloud Save has four slots, numbered 0-3).
    private static final int APP_STATE_KEY = 1;
    // Request code used to invoke sign-in UI.
    private static final int RC_SIGN_IN = 9001;
    // Request code used to invoke Snapshot selection UI.
    private static final int RC_SELECT_SNAPSHOT = 9002;
    static int ry;
    static int rvo = 0;
    final SharedPreference sps = new SharedPreference();
    final String gameid = "18";
    final int minmum = 1;
    final int maximum = 3;
    int fb_reward = 0;
    int reward_status = 0;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    TextView wd_txt1, wd_txt2, wd_txt3, wd_txt4, wd_txt5;
    // Facebook variable starts
    TextView wd_txt6, wd_txt7, wd_txt8, wd_txt9, wd_txt10;
    TextView ask_ans, c_word_number, c_settings, ch_watts_app, ch_facebook;
    String tuch_val = "";
    Newgame_DataBaseHelper6 newhelper6;
    String questionid = "", question = "", answer = "", split_word = "";
    int daily_start = 0;
    int randomno;
    String[] first;
    RelativeLayout head;
    Dialog openDialog_p;
    TextView c_score_edit;
    Chronometer focus;
    Typeface typ, tyr;
    DataBaseHelper myDbHelper;
    Dialog openDialog_s;
    TextView next_continue;
    TextView ttscores;
    int s = 0;
    int dia_dismiss = 0;
    int setval_vid;
    TextView coin_value, p_coins_red;
    int e2;
    SoundPool click, win, coin, worng, spz4;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    TextView c_coin;
    long ttstop;
    int setting_access = 0;
    FirebaseAnalytics mFirebaseAnalytics;
    LinearLayout adds;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    RelativeLayout w_head;
    String retype = "s";
    int share_name = 0;
    LinearLayout earncoin;
    String answer_shows = "";
    Handler handler;
    Runnable my_runnable;
    private RewardedAd rewardedAd;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jamble_word_game);

        newhelper6 = new Newgame_DataBaseHelper6(Jamble_word_game.this);
        myDbHelper = new DataBaseHelper(Jamble_word_game.this);
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");

        openDialog_s = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);

        newhelper = new Newgame_DataBaseHelper(Jamble_word_game.this);
        newhelper2 = new Newgame_DataBaseHelper2(Jamble_word_game.this);
        newhelper3 = new Newgame_DataBaseHelper3(Jamble_word_game.this);
        myDbHelper = new DataBaseHelper(Jamble_word_game.this);
        newhelper4 = new Newgame_DataBaseHelper4(Jamble_word_game.this);

        //loadRewardedVideoAd();


        wd_txt1 = findViewById(R.id.wd_txt1);
        wd_txt2 = findViewById(R.id.wd_txt2);
        wd_txt3 = findViewById(R.id.wd_txt3);
        wd_txt4 = findViewById(R.id.wd_txt4);
        wd_txt5 = findViewById(R.id.wd_txt5);

        wd_txt6 = findViewById(R.id.wd_txt6);
        wd_txt7 = findViewById(R.id.wd_txt7);
        wd_txt8 = findViewById(R.id.wd_txt8);
        wd_txt9 = findViewById(R.id.wd_txt9);
        wd_txt10 = findViewById(R.id.wd_txt10);
        ask_ans = findViewById(R.id.ask_ans);
        p_coins_red = findViewById(R.id.p_coins_red);
        c_word_number = findViewById(R.id.c_word_number);
        c_settings = findViewById(R.id.c_settings);
        c_score_edit = findViewById(R.id.c_score_edit);
        ch_watts_app = findViewById(R.id.ch_watts_app);
        ch_facebook = findViewById(R.id.ch_facebook);
        focus = findViewById(R.id.c_time_edit);
        adds = findViewById(R.id.ads_lay);
        w_head = findViewById(R.id.w_head);
        earncoin = findViewById(R.id.qwt);
        //Setting touch and drag listeners
        wd_txt1.setOnTouchListener(this);
        wd_txt2.setOnTouchListener(this);
        wd_txt3.setOnTouchListener(this);
        wd_txt4.setOnTouchListener(this);
        wd_txt5.setOnTouchListener(this);

        wd_txt6.setOnTouchListener(this);
        wd_txt7.setOnTouchListener(this);
        wd_txt8.setOnTouchListener(this);
        wd_txt9.setOnTouchListener(this);
        wd_txt10.setOnTouchListener(this);

        c_coin = findViewById(R.id.c_coins);
        wd_txt1.setOnDragListener(this);
        wd_txt2.setOnDragListener(this);
        wd_txt3.setOnDragListener(this);
        wd_txt4.setOnDragListener(this);
        wd_txt5.setOnDragListener(this);

        wd_txt6.setOnDragListener(this);
        wd_txt7.setOnDragListener(this);
        wd_txt8.setOnDragListener(this);
        wd_txt9.setOnDragListener(this);
        wd_txt10.setOnDragListener(this);
        ImageView prize_logo = findViewById(R.id.prize_logo);
        if (sps.getInt(Jamble_word_game.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                if (sps.getString(Jamble_word_game.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Jamble_word_game.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Jamble_word_game.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Jamble_word_game.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
        //reward(Jamble_word_game.this);
        MobileAds.initialize(this);
        rewarded_adnew();
        if (sps.getInt(Jamble_word_game.this, "purchase_ads") == 0) {
            Utills.INSTANCE.initializeAdzz(this);
            industrialload();
        }

        Utills.INSTANCE.load_add_AppLovin(this, adds);

        if (sps.getString(Jamble_word_game.this, "jam_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Jamble_word_game.this, "sequence example fn3_jam");

            sequence.setConfig(config);

            sequence.addSequenceItem(ask_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Jamble_word_game.this).setTarget(ch_facebook).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener((itemView, position) -> {

                if (position == 1) {
                    sps.putString(Jamble_word_game.this, "time_start_jam", "yes");
                    sps.putString(Jamble_word_game.this, "showcase_dismiss_jam_intro", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            });
            sequence.start();
            sps.putString(Jamble_word_game.this, "jam_intro", "no");

        }


        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Jamble_word_game.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Jamble_word_game.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Jamble_word_game.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Jamble_word_game.this, R.raw.coins, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Jamble_word_game.this, R.raw.coins, 1);
        ask_ans.setOnClickListener(v -> answer_show());
        String snd = sps.getString(Jamble_word_game.this, "snd");
        c_settings = findViewById(R.id.c_settings);
        if (snd.equals("off")) {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            sv = 0;

        } else if (snd.equals("on")) {
            c_settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;

        }
        c_settings.setOnClickListener(v -> {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            String snd1 = sps.getString(Jamble_word_game.this, "snd");
            if (snd1.equals("off")) {
                sps.putString(Jamble_word_game.this, "snd", "on");
                c_settings.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd1.equals("on")) {
                sps.putString(Jamble_word_game.this, "snd", "off");
                c_settings.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }
        });
        ch_watts_app.setOnClickListener(v -> {
            share_name = 2;
            String a = "com.whatsapp";
            permission(a);
        });
        ch_facebook.setOnClickListener(v -> {
            share_name = 1;
            final String a = "com.facebook.katana";
            permission(a);
        });
        earncoin.setOnClickListener(v -> dialog(0));
        next();
    }

    private void answer_show() {
        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        if (cfw.getCount() != 0) {
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk >= 50) {
                if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                    showanswer(first);

                } else {
                    final Dialog openDialog = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

                    yes.setOnClickListener(v -> {
                        openDialog.dismiss();
                        showanswer(first);
                    });
                    no.setOnClickListener(v -> {
                        sps.putString(getApplicationContext(), "checkbox_ans", "");
                        openDialog.dismiss();
                    });
                    openDialog.show();

                }
            } else {
                dialog(1);
            }
        }

    }

    private void next() {
        c_score_edit.setText("" + Jamble_word_game_functions.score(Jamble_word_game.this, 0));
        String date = sps.getString(Jamble_word_game.this, "date");

        Cursor c;
        if (date.equals("0")) {
            c = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            daily_start = 1;
            c = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        }
        resetvalues();
        if (c.getCount() != 0) {
            questionid = c.getString(c.getColumnIndexOrThrow("questionid"));
            question = c.getString(c.getColumnIndexOrThrow("question"));
            answer = c.getString(c.getColumnIndexOrThrow("answer"));
            split_word = c.getString(c.getColumnIndexOrThrow("sf_word"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            String tfoption = answer;
            first = tfoption.split(",");
            System.out.println("###################fl" + first.length);
            c_word_number.setText("" + questionid);
            if (playtime == 0) {
                if (sps.getString(Jamble_word_game.this, "time_start_jam").equals("")) {
                    sps.putString(Jamble_word_game.this, "time_start_jam", "yes");
                } else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }
            }
            textviews_show(first.length);
            random_arrange(first.length, first);
        } else {
            downloaddata_regular();
            //  nextgamesdialog();
        }

    }

    private void resetvalues() {
        w_head.setVisibility(View.VISIBLE);
        ask_ans.setEnabled(true);
        c_coin.setVisibility(View.INVISIBLE);
        wd_txt1.setEnabled(true);
        wd_txt2.setEnabled(true);
        wd_txt3.setEnabled(true);
        wd_txt4.setEnabled(true);
        wd_txt5.setEnabled(true);
        wd_txt6.setEnabled(true);
        wd_txt7.setEnabled(true);
        wd_txt8.setEnabled(true);
        wd_txt9.setEnabled(true);
        wd_txt10.setEnabled(true);
        ask_ans.setEnabled(true);
        wd_txt1.setText("");
        wd_txt2.setText("");
        wd_txt3.setText("");
        wd_txt4.setText("");
        wd_txt5.setText("");
        wd_txt6.setText("");
        wd_txt7.setText("");
        wd_txt8.setText("");
        wd_txt9.setText("");
        wd_txt10.setText("");
        wd_txt1.setVisibility(View.INVISIBLE);
        wd_txt2.setVisibility(View.INVISIBLE);
        wd_txt3.setVisibility(View.INVISIBLE);
        wd_txt4.setVisibility(View.INVISIBLE);
        wd_txt5.setVisibility(View.INVISIBLE);
        wd_txt6.setVisibility(View.INVISIBLE);
        wd_txt7.setVisibility(View.INVISIBLE);
        wd_txt8.setVisibility(View.INVISIBLE);
        wd_txt9.setVisibility(View.INVISIBLE);
        wd_txt10.setVisibility(View.INVISIBLE);
        wd_txt1.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt2.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt3.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt4.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt5.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt6.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt7.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt8.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt9.setBackgroundResource(R.drawable.jummble_qs_button);
        wd_txt10.setBackgroundResource(R.drawable.jummble_qs_button);
    }

    private void random_arrange(int val, String[] first) {
        Random rn = new Random();
        randomno = rn.nextInt(maximum - minmum + 1) + minmum;
        if (val == 3) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[0]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[2]);
                wd_txt3.setText(first[1]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[0]);
                wd_txt3.setText(first[1]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2];
        } else if (val == 4) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[0]);
                wd_txt4.setText(first[3]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[3]);
                wd_txt3.setText(first[1]);
                wd_txt4.setText(first[2]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[3]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[2]);
                wd_txt4.setText(first[0]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2] + " " + first[3];
        } else if (val == 5) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[0]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[3]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[3]);
                wd_txt3.setText(first[1]);
                wd_txt4.setText(first[2]);
                wd_txt5.setText(first[4]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[3]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[2]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[0]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4];
        } else if (val == 6) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[0]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[3]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[3]);
                wd_txt3.setText(first[1]);
                wd_txt4.setText(first[5]);
                wd_txt5.setText(first[2]);
                wd_txt6.setText(first[4]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[3]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[2]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[0]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5];
        } else if (val == 7) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[0]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[3]);
                wd_txt7.setText(first[6]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[3]);
                wd_txt3.setText(first[1]);
                wd_txt4.setText(first[5]);
                wd_txt5.setText(first[2]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[4]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[3]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[2]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[0]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5] + " " + first[6];
        } else if (val == 8) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[0]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[3]);
                wd_txt7.setText(first[7]);
                wd_txt8.setText(first[6]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[3]);
                wd_txt3.setText(first[1]);
                wd_txt4.setText(first[5]);
                wd_txt5.setText(first[7]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[4]);
                wd_txt8.setText(first[2]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[3]);
                wd_txt2.setText(first[7]);
                wd_txt3.setText(first[2]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[1]);
                wd_txt8.setText(first[0]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5] + " " + first[6] + " " + first[7];
        } else if (val == 9) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[8]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[3]);
                wd_txt7.setText(first[7]);
                wd_txt8.setText(first[6]);
                wd_txt9.setText(first[0]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[3]);
                wd_txt3.setText(first[1]);
                wd_txt4.setText(first[5]);
                wd_txt5.setText(first[8]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[4]);
                wd_txt8.setText(first[2]);
                wd_txt9.setText(first[7]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[3]);
                wd_txt2.setText(first[7]);
                wd_txt3.setText(first[2]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[1]);
                wd_txt8.setText(first[8]);
                wd_txt9.setText(first[0]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5] + " " + first[6] + " " + first[7] + " " + first[8];
        } else if (val == 10) {
            if (randomno == 1) {
                wd_txt1.setText(first[2]);
                wd_txt2.setText(first[1]);
                wd_txt3.setText(first[8]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[3]);
                wd_txt7.setText(first[9]);
                wd_txt8.setText(first[6]);
                wd_txt9.setText(first[0]);
                wd_txt10.setText(first[7]);
            } else if (randomno == 2) {
                wd_txt1.setText(first[0]);
                wd_txt2.setText(first[3]);
                wd_txt3.setText(first[1]);
                wd_txt4.setText(first[5]);
                wd_txt5.setText(first[8]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[4]);
                wd_txt8.setText(first[2]);
                wd_txt9.setText(first[9]);
                wd_txt10.setText(first[7]);
            } else if (randomno == 3) {
                wd_txt1.setText(first[3]);
                wd_txt2.setText(first[7]);
                wd_txt3.setText(first[2]);
                wd_txt4.setText(first[4]);
                wd_txt5.setText(first[5]);
                wd_txt6.setText(first[6]);
                wd_txt7.setText(first[9]);
                wd_txt8.setText(first[8]);
                wd_txt9.setText(first[0]);
                wd_txt10.setText(first[1]);
            }
            answer_shows = "" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5] + " " + first[6] + " " + first[7] + " " + first[8] + " " + first[9];

        }
    }

    private void textviews_show(int val) {
        if (val == 3) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
        } else if (val == 4) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
            wd_txt4.setVisibility(View.VISIBLE);
        } else if (val == 5) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
            wd_txt4.setVisibility(View.VISIBLE);
            wd_txt5.setVisibility(View.VISIBLE);
        } else if (val == 6) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
            wd_txt4.setVisibility(View.VISIBLE);
            wd_txt5.setVisibility(View.VISIBLE);
            wd_txt6.setVisibility(View.VISIBLE);
        } else if (val == 7) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
            wd_txt4.setVisibility(View.VISIBLE);
            wd_txt5.setVisibility(View.VISIBLE);
            wd_txt6.setVisibility(View.VISIBLE);
            wd_txt7.setVisibility(View.VISIBLE);
        } else if (val == 8) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
            wd_txt4.setVisibility(View.VISIBLE);
            wd_txt5.setVisibility(View.VISIBLE);
            wd_txt6.setVisibility(View.VISIBLE);
            wd_txt7.setVisibility(View.VISIBLE);
            wd_txt8.setVisibility(View.VISIBLE);
        } else if (val == 9) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
            wd_txt4.setVisibility(View.VISIBLE);
            wd_txt5.setVisibility(View.VISIBLE);
            wd_txt6.setVisibility(View.VISIBLE);
            wd_txt7.setVisibility(View.VISIBLE);
            wd_txt8.setVisibility(View.VISIBLE);
            wd_txt9.setVisibility(View.VISIBLE);
        } else if (val == 10) {
            wd_txt1.setVisibility(View.VISIBLE);
            wd_txt2.setVisibility(View.VISIBLE);
            wd_txt3.setVisibility(View.VISIBLE);
            wd_txt4.setVisibility(View.VISIBLE);
            wd_txt5.setVisibility(View.VISIBLE);
            wd_txt6.setVisibility(View.VISIBLE);
            wd_txt7.setVisibility(View.VISIBLE);
            wd_txt8.setVisibility(View.VISIBLE);
            wd_txt9.setVisibility(View.VISIBLE);
            wd_txt10.setVisibility(View.VISIBLE);
        }
    }

    private void showanswer(String[] first) {
        ask_ans.setEnabled(false);
        String datee = sps.getString(Jamble_word_game.this, "date");
        if (datee.equals("0")) {
            newhelper6.executeSql("UPDATE newgames5 SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            newhelper6.executeSql("UPDATE newgames5 SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        }
        if (first.length == 3) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
        } else if (first.length == 4) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
            wd_txt4.setText(first[3]);
        } else if (first.length == 5) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
            wd_txt4.setText(first[3]);
            wd_txt5.setText(first[4]);
        } else if (first.length == 6) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
            wd_txt4.setText(first[3]);
            wd_txt5.setText(first[4]);
            wd_txt6.setText(first[5]);
        } else if (first.length == 7) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
            wd_txt4.setText(first[3]);
            wd_txt5.setText(first[4]);
            wd_txt6.setText(first[5]);
            wd_txt7.setText(first[6]);
        } else if (first.length == 8) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
            wd_txt4.setText(first[3]);
            wd_txt5.setText(first[4]);
            wd_txt6.setText(first[5]);
            wd_txt7.setText(first[6]);
            wd_txt8.setText(first[7]);
        } else if (first.length == 9) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
            wd_txt4.setText(first[3]);
            wd_txt5.setText(first[4]);
            wd_txt6.setText(first[5]);
            wd_txt7.setText(first[6]);
            wd_txt8.setText(first[7]);
            wd_txt9.setText(first[8]);
        } else if (first.length == 10) {
            wd_txt1.setText(first[0]);
            wd_txt2.setText(first[1]);
            wd_txt3.setText(first[2]);
            wd_txt4.setText(first[3]);
            wd_txt5.setText(first[4]);
            wd_txt6.setText(first[5]);
            wd_txt7.setText(first[6]);
            wd_txt8.setText(first[7]);
            wd_txt9.setText(first[8]);
            wd_txt10.setText(first[9]);
        }
        right_indicate();
        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(() -> coinanim_red(), 100);

        Handler handler22 = new Handler(Looper.myLooper());
        handler22.postDelayed(() -> adShow(), 3000);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            //handle the dragged view being dropped over a target view
            TextView dropped = (TextView) event.getLocalState();
            TextView dropTarget = (TextView) v;
            //stop displaying the view where it was before it was dragged
            // dropped.setVisibility(View.INVISIBLE);

            //if an item has already been dropped here, there will be different string
            String text = dropTarget.getText().toString();
            //if there is already an item here, set it back visible in its original place
            //update the text and color in the target view to reflect the data being dropped
            System.out.println("#########ttdropTarget" + dropTarget.getText().toString());
            tuch_val = dropTarget.getText().toString();
            System.out.println("#########ttuch_val" + dropped.getText().toString());
            dropTarget.setText(dropped.getText().toString());
            dropped.setText(tuch_val);
            validate();
        }
        return true;
    }

    private void validate() {
        if (first.length == 3) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString())) {
                valid_yes();
            }
        } else if (first.length == 4) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString()) && first[3].equals(wd_txt4.getText().toString())) {
                valid_yes();
            }
        } else if (first.length == 5) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString()) && first[3].equals(wd_txt4.getText().toString()) && first[4].equals(wd_txt5.getText().toString())) {
                valid_yes();
            }
        } else if (first.length == 6) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString()) && first[3].equals(wd_txt4.getText().toString()) && first[4].equals(wd_txt5.getText().toString()) && first[5].equals(wd_txt6.getText().toString())) {
                valid_yes();
            }
        } else if (first.length == 7) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString()) && first[3].equals(wd_txt4.getText().toString()) && first[4].equals(wd_txt5.getText().toString()) && first[5].equals(wd_txt6.getText().toString()) && first[6].equals(wd_txt7.getText().toString())) {
                valid_yes();
            }
        } else if (first.length == 8) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString()) && first[3].equals(wd_txt4.getText().toString()) && first[4].equals(wd_txt5.getText().toString()) && first[5].equals(wd_txt6.getText().toString()) && first[6].equals(wd_txt7.getText().toString()) && first[7].equals(wd_txt8.getText().toString())) {
                valid_yes();
            }
        } else if (first.length == 9) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString()) && first[3].equals(wd_txt4.getText().toString()) && first[4].equals(wd_txt5.getText().toString()) && first[5].equals(wd_txt6.getText().toString()) && first[6].equals(wd_txt7.getText().toString()) && first[7].equals(wd_txt8.getText().toString()) && first[8].equals(wd_txt9.getText().toString())) {
                valid_yes();
            }
        } else if (first.length == 10) {
            if (first[0].equals(wd_txt1.getText().toString()) && first[1].equals(wd_txt2.getText().toString()) && first[2].equals(wd_txt3.getText().toString()) && first[3].equals(wd_txt4.getText().toString()) && first[4].equals(wd_txt5.getText().toString()) && first[5].equals(wd_txt6.getText().toString()) && first[6].equals(wd_txt7.getText().toString()) && first[7].equals(wd_txt8.getText().toString()) && first[8].equals(wd_txt9.getText().toString()) && first[9].equals(wd_txt10.getText().toString())) {
                valid_yes();
            }
        }
    }

    private void valid_yes() {
        //Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        String datee = sps.getString(Jamble_word_game.this, "date");
        if (datee.equals("0")) {
            newhelper6.executeSql("UPDATE newgames5 SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            newhelper6.executeSql("UPDATE newgames5 SET isfinish=1 WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        }
        right_indicate();
        // prize_data_update(Jamble_word_game.this, 75);
        price_update();
        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(() -> {
            //setSc();
            coinanim();
        }, 1500);
    }

    private void right_indicate() {
        ask_ans.setEnabled(false);
        focus.stop();
        wd_txt1.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt2.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt3.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt4.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt5.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt6.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt7.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt8.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt9.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt10.setBackgroundResource(R.drawable.jummble_cr_button);
        wd_txt1.setEnabled(false);
        wd_txt2.setEnabled(false);
        wd_txt3.setEnabled(false);
        wd_txt4.setEnabled(false);
        wd_txt5.setEnabled(false);
        wd_txt6.setEnabled(false);
        wd_txt7.setEnabled(false);
        wd_txt8.setEnabled(false);
        wd_txt9.setEnabled(false);
        wd_txt10.setEnabled(false);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        } else return false;
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
        final LinearLayout rewardvideo = openDialog_s.findViewById(R.id.rewardvideo);
        final LinearLayout vid_earn = openDialog_s.findViewById(R.id.vid_earn);
        LinearLayout ads_layout = openDialog_s.findViewById(R.id.fl_adplaceholder);
        final TextView discription = openDialog_s.findViewById(R.id.discription);
        discription.setVisibility(View.VISIBLE);
        discription.setText("" + answer_shows);
        discription.setTextSize(15);
        ImageView prize_logo = openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Jamble_word_game.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                if (sps.getString(Jamble_word_game.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Jamble_word_game.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Jamble_word_game.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Jamble_word_game.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
        TextView video_earn = openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Jamble_word_game.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");


        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Jamble_word_game.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(Jamble_word_game.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            // New_Main_Activity.load_addFromMain_multiplayer(Jamble_word_game.this, ads_layout);
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Jamble_word_game.this, ads_layout);
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
        String date = sps.getString(Jamble_word_game.this, "date");
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


        if (sps.getString(Jamble_word_game.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Jamble_word_game.this, "date");
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
        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Jamble_word_game.this, "" + "Reward video", "Loading...");
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
                            //reward(Jamble_word_game.this);
                            rewarded_adnew();
                            Toast.makeText(Jamble_word_game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }
        });


        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                rvo = 2;
                if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Jamble_word_game.this, "" + "Reward video", "Loading...");
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
                                //reward(Jamble_word_game.this);
                                rewarded_adnew();
                                Toast.makeText(Jamble_word_game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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

        wtp.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.whatsapp");

                    String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + c_word_number.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivity(Intent.createChooser(i, "Share via"));
                    startActivityForResult(Intent.createChooser(i, "Share via"), 21);
                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });
        gplus.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.google.android.apps.plus");

                    String msg = ("நான் சொல்லிஅடி செயலியில் குறிப்புகள் மூலம் கண்டுபிடி நிலை" + c_word_number.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
        word.setText("ï¡Á");

        next_continue.setOnClickListener(view -> {
            dia_dismiss = 1;
            openDialog_s.dismiss();
            next();
        });

        openDialog_s.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Jamble_word_game.this, "game_area", "on");


                String date1 = sps.getString(Jamble_word_game.this, "date");
                if (date1.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        openDialog_s.dismiss();
                        finish();
                    }
                } else {
                    if (sps.getString(Jamble_word_game.this, "Exp_list").equals("on")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Jamble_word_game.this, Expandable_List_View.class);
                        startActivity(i);

                    } else {
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
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

        });

       /* openDialog_s.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {



              *//*
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });*/
        if (!isFinishing()) {
            openDialog_s.show();
        }

    }

    public boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    //reward videos***********************//
    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            c_score_edit.setText("" + skx);
            openDialog.dismiss();
            //mCoinCount = 0;
        });
        openDialog.show();
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
            final Dialog openDialog = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                c_score_edit.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            });

            openDialog.show();
        }

    }

    public void industrialload() {
        if (mInterstitialAd != null) return;
        Log.i(TAG, "onAdLoadedCalled");
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getResources().getString(R.string.Game3_Stage_Close_ST), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                interstiallistener();
                Log.i(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d(TAG, loadAdError.toString());
                mInterstitialAd = null;
                handler = null;
                Log.i(TAG, "onAdLoadedfailed" + loadAdError.getMessage());
            }
        });

    }

    public void interstiallistener() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
                handler = null;
                Utills.INSTANCE.Loading_Dialog_dismiss();
                setSc();
                industrialload();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
                handler = null;
                Utills.INSTANCE.Loading_Dialog_dismiss();
                sps.putInt(getApplicationContext(), "Game3_Stage_Close_ST", 0);
                setSc();
            }

        });
    }

    public void adShow() {
        if (sps.getInt(getApplicationContext(), "Game3_Stage_Close_ST") == Utills.interstitialadCount && mInterstitialAd != null) {
            sps.putInt(getApplicationContext(), "Game3_Stage_Close_ST", 0);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                mInterstitialAd.show(this);
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game3_Stage_Close_ST", (sps.getInt(getApplicationContext(), "Game3_Stage_Close_ST") + 1));
            if (sps.getInt(this, "Game3_Stage_Close_ST") > Utills.interstitialadCount)
                sps.putInt(this, "Game3_Stage_Close_ST", 0);

            setSc();
            //Toast.makeText(this, ""+sps.getInt(this, "Game3_Stage_Close_ST"), Toast.LENGTH_SHORT).show();
        }

    }

    public void coinanim() {
        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        c_score_edit.setText(tr);
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
        c_score_edit.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        c_score_edit.getLocationOnScreen(locationOnScreenSecond);
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
            c_score_edit.getLocationInWindow(locationInWindowSecond1);
            int[] locationOnScreenSecond1 = new int[2];
            c_score_edit.getLocationOnScreen(locationOnScreenSecond1);
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
            c_score_edit.startAnimation(levels1);
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
                c_score_edit.post(() -> c_score_edit.setText("" + e2));
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
            c_score_edit.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

            Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
            ch.moveToFirst();
            int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
            int shh = sh + 50;
            myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");


            adShow();
        }, 4000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Jamble_word_game.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

            //  newhelper6.executeSql("UPDATE newgames5 SET noclue='" + noclue + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

            // newhelper6.executeSql("UPDATE newgames5 SET noclue='" + noclue + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        }
    }

    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Jamble_word_game.this);
        mFirebaseAnalytics.setCurrentScreen(this, "Jamble_words", null);

        System.out.println("addloded" + sps.getInt(Jamble_word_game.this, "addloded"));

        if (setting_access == 1) {
            setting_access = 0;
        } else if (setting_access == 2) {
            setting_access = 0;
        }


        if (sps.getInt(Jamble_word_game.this, "goto_sett") == 1) {
            sps.putInt(Jamble_word_game.this, "goto_sett", 0);

            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {

            }
        }
        if (sps.getString(Jamble_word_game.this, "resume_jam").equals("")) {
            sps.putString(Jamble_word_game.this, "resume_jam", "yes");

        } else {
            String date = sps.getString(Jamble_word_game.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
            } else {
                pos = 2;
            }
            Cursor cs = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + questionid + "' and rd='" + pos + "'");
            cs.moveToFirst();
            long dscore = 0;
            if (cs.getCount() != 0) {
                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

        if (sps.getString(Jamble_word_game.this, "resume_c").equals("")) {

            sps.putString(Jamble_word_game.this, "resume_c", "yes");

        } else {

            String date = sps.getString(Jamble_word_game.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                cs = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + questionid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                    //                  noofclue = cs.getInt(cs.getColumnIndexOrThrow("noclue"));
                }
            } else {
                cs = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + questionid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {

                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
//                    noofclue = cs.getInt(cs.getColumnIndexOrThrow("noclue"));
                }
            }
            //  long wt=sps.getInt(Word_Game_Hard.this,"old_time_start");
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }
    }

    public void settingpermission() {
        if (sps.getInt(Jamble_word_game.this, "permission") == 2) {
            AlertDialog alertDialog = new AlertDialog.Builder(Jamble_word_game.this).create();
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
                sps.putString(Jamble_word_game.this, "game_area", "on");
                String date = sps.getString(Jamble_word_game.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                } else {
                    finish();
                    Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                    startActivity(i);
                }
                dialog.dismiss();
            });


            alertDialog.show();
        }
    }

    private void coinanim_red() {

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        int skq = 0;
        if (cfq.getCount() != 0) {
            cfq.moveToFirst();
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
        c_coin.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        c_coin.getLocationOnScreen(locationOnScreenSecond);
        float destinationX = locationOnScreenSecond[0];
        float destinationY = locationOnScreenSecond[1];
        TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
        transAnimation.setDuration(700);
        p_coins_red.startAnimation(transAnimation);
        p_coins_red.postDelayed(() -> p_coins_red.setVisibility(View.INVISIBLE), transAnimation.getDuration());


        ////

        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(() -> {
            //play1.start();
            spz4.play(soundId4, sv, sv, 0, 0, sv);
            p_coins_red.setVisibility(View.VISIBLE);
            int[] locationInWindow1 = new int[2];
            p_coins_red.getLocationInWindow(locationInWindow1);
            int[] locationOnScreen1 = new int[2];
            p_coins_red.getLocationOnScreen(locationOnScreen1);
            float sourceX1 = locationOnScreen1[0];
            float sourceY1 = locationOnScreen1[1];
            int[] locationInWindowSecond1 = new int[2];
            c_coin.getLocationInWindow(locationInWindowSecond1);
            int[] locationOnScreenSecond1 = new int[2];
            c_coin.getLocationOnScreen(locationOnScreenSecond1);
            float destinationX1 = locationOnScreenSecond1[0];
            float destinationY1 = locationOnScreenSecond1[1];
            TranslateAnimation transAnimation1 = new TranslateAnimation(0f, (destinationX1 - sourceX1), 0f, (destinationY1 - sourceY1));
            transAnimation1.setDuration(1000);
            p_coins_red.startAnimation(transAnimation1);
            p_coins_red.postDelayed(() -> p_coins_red.setVisibility(View.INVISIBLE), transAnimation1.getDuration());
        }, 1000);

        new Thread(() -> {
            int es = e2 - 50;
            while (e2 < es) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                c_score_edit.post(() -> c_score_edit.setText("" + e2));
                e2++;
            }

        }).start();

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
            c_score_edit.startAnimation(levels1);
        }, 2200);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(() -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx - 50;
            String aStringx = Integer.toString(spx);
            c_score_edit.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            // setSc();
        }, 1300);

    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);
        TextView exit = openDialog.findViewById(R.id.exit);

        String date = sps.getString(Jamble_word_game.this, "date");
        if (date.equals("0")) {
            next_game.setText("தடம் மாறிய வார்த்தைகளை இணைக்க  தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("தினசரி தடம் மாறிய வார்த்தைகளை இணைக்க  புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள்  சிறப்பாக விளையாட காத்திருக்கும்  விளையாட்டுக்கள்.");
        }
        openDialog.setCancelable(false);
        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Jamble_word_game.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Picture_Game_Hard.class);
            startActivity(i);
        });
        exit.setOnClickListener(v -> {
            if (main_act.equals("")) {
                finish();
                Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Jamble_word_game.this, "game_area", "on");
                finish();
            }
            sps.putString(Jamble_word_game.this, "date", "0");
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
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Odd_man_out.class);
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
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Ote_to_Tamil.class);
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


        if (sps.getString(Jamble_word_game.this, "newgame_notification").equals("start")) {
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


        seerpaduthu.setOnClickListener(view -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Riddle_game.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, WordError_correction.class);
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
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Fill_in_blanks.class);
            startActivity(i);
        });

        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Jamble_word_game.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Jamble_word_game.class);
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
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Missing_Words.class);
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
            sps.putString(Jamble_word_game.this, "date", "0");
            Intent i = new Intent(Jamble_word_game.this, Find_difference_between_pictures.class);
            startActivity(i);
        });
        openDialog.show();
        openDialog.setOnKeyListener((dialog, keyCode, event) -> {

            if (main_act.equals("")) {

                finish();
                //     openDialog_s.dismiss();
                Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Jamble_word_game.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Jamble_word_game.this, "date", "0");

            return keyCode == KeyEvent.KEYCODE_BACK;
        });

    }

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Jamble_word_game.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
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
                    String date = sps.getString(Jamble_word_game.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    } else {
                        newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    }

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Jamble_word_game.this, Jamble_word_game.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
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
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                String date = sps.getString(Jamble_word_game.this, "date");
                if (date.equals("0")) {
                    Cursor c1 = myDbHelper.getQry("select id from maintable order by id DESC");
                    c1.moveToFirst();


                    System.out.print("Count====" + c1.getCount());


                    if (c1.getCount() != 0) {


                        //c1.getString(c1.getColumnIndexOrThrow("id"));

                        System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));

                        //downloadcheck("" + c1.getString(c1.getColumnIndexOrThrow("id")), "ord");

                    } else {
                        //downloadcheck("0", "ord");
                    }
                } else {
                    Cursor c1 = myDbHelper.getQry("select id from dailytest order by id DESC");
                    c1.moveToFirst();
                    System.out.print("Count====" + c1.getCount());
                    if (c1.getCount() != 0) {
                        //c1.getString(c1.getColumnIndexOrThrow("id"));
                        System.out.print("Last ID====" + c1.getString(c1.getColumnIndexOrThrow("id")));
                        //downloadcheck("" + c1.getString(c1.getColumnIndexOrThrow("id")), "daily");

                    } else {
                        System.out.print("else====");

                        // downloadcheck("0", "daily");
                    }
                }
            } else {


                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Jamble_word_game.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்").setPositiveButton("அமைப்பு", (dialog, which) -> {
                    // continue with delete
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Jamble_word_game.this, "goto_sett", 1);
                    dialog.dismiss();
                }).setNegativeButton("பின்னர்", (dialog, which) -> {
                    // do nothing

                    String date = sps.getString(Jamble_word_game.this, "date");
                    if (date.equals("0")) {
                        backexitnet();
                    } else {
                        backexitnet();
                    }
                    dialog.dismiss();
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        }
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

                if (sps.getString(Jamble_word_game.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Jamble_word_game.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Jamble_word_game.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Jamble_word_game.this, "complite_reg").equals("yes")) {
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
        final Dialog openDialog = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            c_score_edit.setText("" + skx);
            openDialog.dismiss();
            //mCoinCount = 0;
        });

        openDialog.show();
    }

    public void dialog(int i) {
        final Dialog openDialog_earncoin = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = openDialog_earncoin.findViewById(R.id.earngplus);


        TextView cancel = openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = openDialog_earncoin.findViewById(R.id.ssss);

        ss.setOnClickListener(v -> openDialog_earncoin.cancel());
        cancel.setOnClickListener(v -> openDialog_earncoin.cancel());

        RelativeLayout video = openDialog_earncoin.findViewById(R.id.earnvideo);
        TextView wpro = openDialog_earncoin.findViewById(R.id.wpro);
        if (i == 1) {
            cancel.setVisibility(View.INVISIBLE);
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }
        video.setOnClickListener(v -> {

            rvo = 1;
            extra_coin_s = 0;
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Jamble_word_game.this, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {

                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {
                    fb_reward = 0;
                    //reward(Jamble_word_game.this);
                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(Jamble_word_game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);


                }
            } else {

                Toast.makeText(Jamble_word_game.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }


        });

        wp.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    openDialog_earncoin.cancel();
                    Intent i1 = new Intent(Intent.ACTION_SEND);
                    i1.setType("text/plain");
                    i1.setPackage("com.whatsapp");
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i1.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i1, "Share via"), 12);


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
    public void onBackPressed() {
        sps.putString(Jamble_word_game.this, "game_area", "on");
        sps.putString(Jamble_word_game.this, "odd_time_start", "yes");
        sps.putInt(Jamble_word_game.this, "addlodedd", 0);
        s = 1;
        openDialog_p = new Dialog(Jamble_word_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        TextView yes = openDialog_p.findViewById(R.id.yes);
        TextView no = openDialog_p.findViewById(R.id.no);


        yes.setOnClickListener(v -> {
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
            focus.stop();
            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            if (main_act.equals("")) {
                finish();
                openDialog_s.dismiss();
                Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                finish();
                openDialog_s.dismiss();
            }

            //ad

            openDialog_p.dismiss();
        });
        no.setOnClickListener(v -> openDialog_p.dismiss());
        openDialog_p.show();

    }

    private void download_datas() {
        Cursor cz = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("questionid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Jamble_word_game.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    public void downloaddata_regular() {

        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Jamble_word_game.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {
            //DownLoad Letters and Words

            if (Utils.isNetworkAvailable(Jamble_word_game.this)) {
                download_datas();
            } else {

                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Jamble_word_game.this);                           /* .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog12, which) -> {
                    // continue with delete

                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Jamble_word_game.this, "goto_sett", 1);


                    dialog12.dismiss();
                }).setNegativeButton("பின்னர்", (dialog1, which) -> {
                    // do nothing
                    sps.putString(Jamble_word_game.this, "game_area", "on");

                    String date = sps.getString(Jamble_word_game.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    } else {
                        if (date.equals("0")) {
                            backexitnet();
                        } else {
                            backexitnet();
                        }
                    }
                    dialog1.dismiss();
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
            }

        });
        alertDialogBuilder.setPositiveButton("இல்லை ", (dialog, id) -> {
            sps.putString(Jamble_word_game.this, "game_area", "on");
            String date = sps.getString(Jamble_word_game.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            } else {
                finish();
                Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                startActivity(i);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
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

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Jamble_word_game.this, "showcase_dismiss_jam_intro").equals("")) {
                showcase_dismiss();
            } else {
                sps.putString(Jamble_word_game.this, "time_start_jam", "yes");
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

            }

        }, 800);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 150) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Jamble_word_game.this, "permission", 1);
                //  downloaddata_daily();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Jamble_word_game.this, "permission", 2);
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                        startActivity(i);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Jamble_word_game.this, "permission", 0);
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }
            }

        }
        if (requestCode == 151) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Jamble_word_game.this, "permission", 1);
                downloaddata_regular();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Jamble_word_game.this, "permission", 2);
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                        startActivity(i);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Jamble_word_game.this, "permission", 0);
                        finish();
                        Intent i = new Intent(Jamble_word_game.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }
            }
        }

        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Jamble_word_game.this, "permission", 1);
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
                        sps.putInt(Jamble_word_game.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Jamble_word_game.this, "permission", 0);
                    }
                }
            }

        }
    }

    private void price_update() {
        ////////////////Prize//////////////////
        //Time Setting For Clue Game
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        int f_sec = sec + sec2 + seconds;
        //
        String date = sps.getString(Jamble_word_game.this, "date");
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                prize_data_update(Jamble_word_game.this, 75);
            } else if (timeElapsed > 91300) {
                prize_data_update(Jamble_word_game.this, 50);
            } else {
                prize_data_update(Jamble_word_game.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                prize_data_update(Jamble_word_game.this, 100);
            } else if (timeElapsed > 91300) {
                prize_data_update(Jamble_word_game.this, 75);
            } else {
                prize_data_update(Jamble_word_game.this, 50);
            }
        }
        ////////////////Prize//////////////////
    }

    public void rewarded_adnew() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, getResources().getString(R.string.Reward), adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error.
                Log.d(TAG, loadAdError.toString());
                rewardedAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                rewardedAd = ad;
                fb_reward = 1;
                adslisner();
                Log.d(TAG, "Ad was loaded.");
            }
        });


    }

    public void adslisner() {
        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {

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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        if (rvo == 2) {
                            share_earn2(mCoinCount);
                        } else {
                            vidcoinearn();
                        }
                    }, 500);
                } else {
                    Toast.makeText(Jamble_word_game.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
                }

                fb_reward = 0;

            }

        });
    }

    public void show_reward() {
        if (rewardedAd != null) {
            rewardedAd.show(this, rewardItem -> {
                // Handle the reward.
                Log.d(TAG, "The user earned the reward.");
                int rewardAmount = rewardItem.getAmount();
                String rewardType = rewardItem.getType();
                reward_status = 1;
            });
        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
        rewardedAd = null;
        mInterstitialAd = null;
        handler = null;
    }
}

