package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
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
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Fill_in_blanks extends AppCompatActivity implements Download_completed {

    //*********************reward videos process 1***********************
    //private final String AD_UNIT_ID = getString(R.string.rewarded);

    static int ry;
    static int rvo = 0;
    static int mCoinCount = 20;
    final String gameid = "13";
    final int maximum_s = 3;
    final int minmum_s = 1;
    final int maximum_show = 3;
    final int minmum_show = 1;
    final SharedPreference sps = new SharedPreference();
    final ArrayList<String> mylist = new ArrayList<String>();
    int fb_reward = 0;
    int reward_status = 0;
    Newgame_DataBaseHelper newhelper;
    //reward videos process 1***********************
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    TextView ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8;
    TextView c_button1, c_button2, c_button3, c_button4, c_button5, c_button6, c_button7, c_button8, c_button9, c_button10, c_button11, c_button12, c_button13, c_button14, c_button15, c_button16;
    TextView score;
    String letters = "";
    String answers;
    int levelid = 0;
    int letter_length;
    int minmum = 1;
    int maximum = 3;
    int randomno;
    int randomno_set = 0;
    int word_type_random;
    int word_maximum = 3;
    int word_minimum = 2;
    TextView c_word_number, c_coins, c_settings, c_ans;
    int randomno_show;
    Chronometer focus;
    DataBaseHelper myDbHelper;
    int sv = 0;
    String string_arrange_value = "";
    int e2 = 0;
    SoundPool click, win, coin, worng;
    int soundId1, soundId2, soundId3, soundId4;
    TextView c_coin;
    Dialog openDialog_p;
    Dialog openDialog_s;
    int s = 0;
    TextView next_continue;
    TextView ttscores;
    Typeface typ, tyr;
    int f_sec;
    TextView to_no;
    int r = 0;
    TextView shareq, h_gplues, h_watts_app, h_facebook, ans_high;
    int share_name = 0;
    RelativeLayout w_head, helpshare_layout;
    long ttstop;
    LinearLayout adds;
    TextView clear_value;
    LinearLayout list4;
    TextView earncoin;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    int clear_data = 0;
    LinearLayout qwt;
    Dialog openDialog;
    LinearLayout extra_coin;
    TextView coin_value;
    int setval_vid;
    int dia_dismiss = 0;
    int coin_anim = 0;
    Handler handler;
    Runnable my_runnable;
    private RewardedInterstitialAd rewardedAd;
    private InterstitialAd mInterstitialAd;

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_blanks);

        newhelper = new Newgame_DataBaseHelper(Fill_in_blanks.this);
        newhelper2 = new Newgame_DataBaseHelper2(Fill_in_blanks.this);
        newhelper3 = new Newgame_DataBaseHelper3(Fill_in_blanks.this);
        newhelper4 = new Newgame_DataBaseHelper4(Fill_in_blanks.this);
        myDbHelper = new DataBaseHelper(Fill_in_blanks.this);


        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Fill_in_blanks.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Fill_in_blanks.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Fill_in_blanks.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Fill_in_blanks.this, R.raw.coins, 1);

        ImageView prize_logo = findViewById(R.id.prize_logo);
        if (sps.getInt(Fill_in_blanks.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                if (sps.getString(Fill_in_blanks.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Fill_in_blanks.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Fill_in_blanks.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Fill_in_blanks.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Fill_in_blanks.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Fill_in_blanks.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });

        openDialog_s = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);


        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");


        if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(Fill_in_blanks.this);
            /**/
        }


        find();
        click();
        next();
        Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        // Make sure to set the mediation provider value to "max" to ensure proper functionality

        if (sps.getInt(this, "purchase_ads") == 0) {
            industrialload();
        }


        adds = findViewById(R.id.ads_lay);

        Utills.INSTANCE.load_add_AppLovin(this, adds, getResources().getString(R.string.Bottom_Banner));


        if (sps.getString(Fill_in_blanks.this, "fill_to_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Fill_in_blanks.this, "sequence example fill");

            sequence.setConfig(config);

            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Fill_in_blanks.this).setTarget(helpshare_layout).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener((itemView, position) -> {

                if (position == 1) {
                    sps.putString(Fill_in_blanks.this, "fill_intro_time_start", "yes");
                    sps.putString(Fill_in_blanks.this, "showcase_dismiss_fill_intro", "yes");

                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            });

            sps.putString(Fill_in_blanks.this, "fill_to_intro", "no");
            sequence.start();


        }

    }

    private void click_txt_change() {

        earncoin.setOnClickListener(v -> dialog(0));

        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);

            }
        });
        ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);
            }
        });
        ed3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);
            }
        });
        ed4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);
            }
        });
        ed5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);
            }
        });
        ed6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);
            }
        });
        ed7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);
            }
        });
        ed8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ans_verify = "";
                if (letter_length == 4) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString();
                } else if (letter_length == 5) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString();
                } else if (letter_length == 6) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString() + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
                }
                System.out.println("################ED1" + ans_verify);
                verifyed(ans_verify);
            }
        });


    }

    private void verifyed(String ans_verify) {

        System.out.println("#################verifyed value" + ans_verify);
        System.out.println("#################verifyed answers" + answers);
        if (answers.equals("" + ans_verify)) {
            sps.putString(Fill_in_blanks.this, "value_data_blanks", "");
            System.out.println("################" + "UPDATE newgamesdb4 SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
            newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
            c_button1.setEnabled(false);
            c_button2.setEnabled(false);
            c_button3.setEnabled(false);
            c_button4.setEnabled(false);
            c_button5.setEnabled(false);
            c_button6.setEnabled(false);
            c_button7.setEnabled(false);
            c_button8.setEnabled(false);
            c_button9.setEnabled(false);
            c_button10.setEnabled(false);
            c_button11.setEnabled(false);
            c_button12.setEnabled(false);
            c_button13.setEnabled(false);
            c_button14.setEnabled(false);
            c_button15.setEnabled(false);
            c_button16.setEnabled(false);
            clear_value.setEnabled(false);
            c_ans.setBackgroundResource(R.drawable.tick_background);
            c_ans.setEnabled(false);
            r = 0;

            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
            focus.stop();
            if (coin_anim == 0) {
                coinanim();
                price_update();
                coin_anim = 1;
            }


        } else {
            if (letter_length == 4) {
                if (ed1.getText().toString().length() != 0 && ed2.getText().toString().length() != 0 && ed3.getText().toString().length() != 0 && ed4.getText().toString().length() != 0) {
                    wrongans();
                }
            } else if (letter_length == 5) {
                if (ed1.getText().toString().length() != 0 && ed2.getText().toString().length() != 0 && ed3.getText().toString().length() != 0 && ed4.getText().toString().length() != 0 && ed5.getText().toString().length() != 0) {
                    wrongans();
                }
            } else if (letter_length == 6) {
                if (ed1.getText().toString().length() != 0 && ed2.getText().toString().length() != 0 && ed3.getText().toString().length() != 0 && ed4.getText().toString().length() != 0 && ed5.getText().toString().length() != 0 && ed6.getText().toString().length() != 0) {
                    wrongans();
                }
            } else if (letter_length == 7) {
                if (ed1.getText().toString().length() != 0 && ed2.getText().toString().length() != 0 && ed3.getText().toString().length() != 0 && ed4.getText().toString().length() != 0 && ed5.getText().toString().length() != 0 && ed6.getText().toString().length() != 0 && ed7.getText().toString().length() != 0) {
                    wrongans();
                }
            } else if (letter_length == 8) {
                if (ed1.getText().toString().length() != 0 && ed2.getText().toString().length() != 0 && ed3.getText().toString().length() != 0 && ed4.getText().toString().length() != 0 && ed5.getText().toString().length() != 0 && ed6.getText().toString().length() != 0 && ed7.getText().toString().length() != 0 && ed8.getText().toString().length() != 0) {
                    wrongans();
                }
            }

        }
    }

    public void wrongans() {
        worng.play(soundId2, sv, sv, 0, 0, sv);
        CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "தவறான பதில்", Snackbar.LENGTH_SHORT);
        final View view = snackbar.getView();
        TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
        view.setBackgroundResource(R.drawable.answershow);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        //textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);

        textView.setTextSize(17);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        textView.setTextSize(19);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snackbar.show();
    }

    private void reset_values() {

        ed1.setVisibility(View.GONE);
        ed2.setVisibility(View.GONE);
        ed3.setVisibility(View.GONE);
        ed4.setVisibility(View.GONE);
        ed5.setVisibility(View.GONE);
        ed6.setVisibility(View.GONE);
        ed7.setVisibility(View.GONE);
        ed8.setVisibility(View.GONE);
        c_coins.setVisibility(View.INVISIBLE);

        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
        ed7.setText("");
        ed8.setText("");

        ed1.setEnabled(false);
        ed2.setEnabled(false);
        ed3.setEnabled(false);
        ed4.setEnabled(false);
        ed5.setEnabled(false);
        ed6.setEnabled(false);
        ed7.setEnabled(false);
        ed8.setEnabled(false);
        clear_value.setEnabled(true);
        c_button1.setText("");
        c_button2.setText("");
        c_button3.setText("");
        c_button4.setText("");
        c_button5.setText("");
        c_button6.setText("");
        c_button7.setText("");
        c_button8.setText("");
        c_button9.setText("");
        c_button10.setText("");
        c_button11.setText("");
        c_button12.setText("");
        c_button13.setText("");
        c_button14.setText("");
        c_button15.setText("");
        c_button16.setText("");


        c_button1.setEnabled(true);
        c_button2.setEnabled(true);
        c_button3.setEnabled(true);
        c_button4.setEnabled(true);
        c_button5.setEnabled(true);
        c_button6.setEnabled(true);
        c_button7.setEnabled(true);
        c_button8.setEnabled(true);
        c_button9.setEnabled(true);
        c_button10.setEnabled(true);
        c_button11.setEnabled(true);
        c_button12.setEnabled(true);
        c_button13.setEnabled(true);
        c_button14.setEnabled(true);
        c_button15.setEnabled(true);
        c_button16.setEnabled(true);

        ans_high.setVisibility(View.GONE);
        c_coin.setVisibility(View.INVISIBLE);
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        list4.setVisibility(View.VISIBLE);
        c_ans.setEnabled(true);
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


            adShow();
        }, 3000);
    }

    private void next() {

        coin_anim = 0;
        Random rns = new Random();
        randomno_set = rns.nextInt(maximum_s - minmum_s + 1) + minmum_s;

        w_head.setVisibility(View.VISIBLE);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            score.setText("" + skx);
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
        String date = sps.getString(Fill_in_blanks.this, "date");
        if (date.equals("0")) {
            Cursor c1 = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            to_no.setText(no/*+"/"+c1.getCount()*/);
            to_no.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        } else {

            String tfoption = date;
            String[] first = tfoption.split("-");
            to_no.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            to_no.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }

        Cursor c;
        c = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "'and isfinish='0' order by id limit 1");
        c.moveToFirst();
        if (c.getCount() != 0) {
            reset_values();
            levelid = c.getInt(c.getColumnIndexOrThrow("levelid"));
            letters = c.getString(c.getColumnIndexOrThrow("letters"));
            answers = c.getString(c.getColumnIndexOrThrow("answer"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            if (sps.getString(Fill_in_blanks.this, "fill_intro_time_start").equals("yes")) {

                focus.setBase(SystemClock.elapsedRealtime() + playtime);
                focus.start();
            }
            // Toast.makeText(this, "answers" + answers, Toast.LENGTH_SHORT).show();
            String tfoption = letters;
            String[] first = tfoption.split(",");
            letter_length = first.length;
            System.out.println("letter_length" + letter_length);

            if (sps.getString(Fill_in_blanks.this, str_date1).equals("")) {
                daily_bones();
                sps.putString(Fill_in_blanks.this, str_date1, "yes");
            }

            if (sps.getString(Fill_in_blanks.this, "value_data_blanks").length() != 0) {
                System.out.println("##################value_data_blanks" + sps.getString(Fill_in_blanks.this, "value_data_blanks"));
                System.out.println("##################myliststart" + mylist);
                // mylist.add(sps.getString(Fill_in_blanks.this, "value_data_blanks"));
                String tfoptionde = sps.getString(Fill_in_blanks.this, "value_data_blanks");
                String[] firstde = tfoptionde.split(",");
                int letter_lengthde = firstde.length;

                for (int j = 0; j < letter_lengthde; j++) {
                    mylist.add("" + firstde[j]);
                }
                System.out.println("##################mylist" + mylist);

                for (int i = 0; i < mylist.size(); i++) {
                    System.out.println("##################mylist.size()" + mylist.get(i));
                    if (mylist.get(i).equals("1")) {
                        ed1.setText("");
                    }
                    if (mylist.get(i).equals("2")) {
                        ed2.setText("");
                    }
                    if (mylist.get(i).equals("3")) {
                        ed3.setText("");
                    }
                    if (mylist.get(i).equals("4")) {
                        ed4.setText("");
                    }
                    if (mylist.get(i).equals("5")) {
                        ed5.setText("");
                    }
                    if (mylist.get(i).equals("6")) {
                        ed6.setText("");
                    }
                    if (mylist.get(i).equals("7")) {
                        ed7.setText("");
                    }
                    if (mylist.get(i).equals("8")) {
                        ed8.setText("");
                    }
                }
            } else {
                if (letter_length == 4) {
                    word_maximum = 3;
                    word_minimum = 2;
                    Random rn = new Random();
                    word_type_random = rn.nextInt(word_maximum - word_minimum + 1) + word_minimum;
                    System.out.println("word_type_random" + word_type_random);
                } else if (letter_length == 5) {
                    word_maximum = 4;
                    word_minimum = 2;
                    Random rn = new Random();
                    word_type_random = rn.nextInt(word_maximum - word_minimum + 1) + word_minimum;
                    System.out.println("word_type_random" + word_type_random);
                } else if (letter_length == 6) {
                    word_maximum = 5;
                    word_minimum = 2;
                    Random rn = new Random();
                    word_type_random = rn.nextInt(word_maximum - word_minimum + 1) + word_minimum;
                    System.out.println("word_type_random" + word_type_random);
                } else if (letter_length == 7) {
                    word_maximum = 6;
                    word_minimum = 2;
                    Random rn = new Random();
                    word_type_random = rn.nextInt(word_maximum - word_minimum + 1) + word_minimum;
                    System.out.println("word_type_random" + word_type_random);
                } else if (letter_length == 8) {
                    word_maximum = 7;
                    word_minimum = 2;
                    Random rn = new Random();
                    word_type_random = rn.nextInt(word_maximum - word_minimum + 1) + word_minimum;
                    System.out.println("word_type_random" + word_type_random);
                }


                mylist.clear();
                System.out.println("#########################blank clear" + mylist);

                if (word_type_random == 2) {
                    random_create();
                    random_create();
                }
                if (word_type_random == 3) {
                    random_create();
                    random_create();
                    random_create();
                }
                if (word_type_random == 4) {
                    random_create();
                    random_create();
                    random_create();
                    //random_create();
                }
                if (word_type_random == 5) {
                    random_create();
                    random_create();
                    random_create();
                    random_create();
                    // random_create();
                }
                if (word_type_random == 6) {
                    random_create();
                    random_create();
                    random_create();
                    random_create();
                    random_create();
                    // random_create();
                }
                if (word_type_random == 7) {
                    random_create();
                    random_create();
                    random_create();
                    random_create();
                    random_create();
                    random_create();
                    // random_create();
                }

            }


            if (letter_length == 4) {

                StringTokenizer word = new StringTokenizer(letters, ",");
                String word1 = word.nextToken().trim();
                String word2 = word.nextToken().trim();
                String word3 = word.nextToken().trim();
                String word4 = word.nextToken().trim();
                ed1.setVisibility(View.VISIBLE);
                ed2.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.VISIBLE);
                ed4.setVisibility(View.VISIBLE);

                ed1.setEnabled(true);
                ed2.setEnabled(true);
                ed3.setEnabled(true);
                ed4.setEnabled(true);

                for (int i = 0; i < mylist.size(); i++) {
                    if (mylist.get(i).equals("1")) {
                        word1 = "";
                    }
                    if (mylist.get(i).equals("2")) {
                        word2 = "";
                    }
                    if (mylist.get(i).equals("3")) {
                        word3 = "";
                    }
                    if (mylist.get(i).equals("4")) {
                        word4 = "";
                    }
                }
                ed1.setText("" + word1);
                ed2.setText("" + word2);
                ed3.setText("" + word3);
                ed4.setText("" + word4);


            }
            if (letter_length == 5) {

                StringTokenizer word = new StringTokenizer(letters, ",");
                String word1 = word.nextToken().trim();
                String word2 = word.nextToken().trim();
                String word3 = word.nextToken().trim();
                String word4 = word.nextToken().trim();
                String word5 = word.nextToken().trim();
                ed1.setVisibility(View.VISIBLE);
                ed2.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.VISIBLE);
                ed4.setVisibility(View.VISIBLE);
                ed5.setVisibility(View.VISIBLE);

                ed1.setEnabled(true);
                ed2.setEnabled(true);
                ed3.setEnabled(true);
                ed4.setEnabled(true);
                ed5.setEnabled(true);

                for (int i = 0; i < mylist.size(); i++) {
                    if (mylist.get(i).equals("1")) {
                        word1 = "";
                    }
                    if (mylist.get(i).equals("2")) {
                        word2 = "";
                    }
                    if (mylist.get(i).equals("3")) {
                        word3 = "";
                    }
                    if (mylist.get(i).equals("4")) {
                        word4 = "";
                    }
                    if (mylist.get(i).equals("5")) {
                        word5 = "";
                    }
                }
                ed1.setText("" + word1);
                ed2.setText("" + word2);
                ed3.setText("" + word3);
                ed4.setText("" + word4);
                ed5.setText("" + word5);

            }
            if (letter_length == 6) {

                StringTokenizer word = new StringTokenizer(letters, ",");
                String word1 = word.nextToken().trim();
                String word2 = word.nextToken().trim();
                String word3 = word.nextToken().trim();
                String word4 = word.nextToken().trim();
                String word5 = word.nextToken().trim();
                String word6 = word.nextToken().trim();
                ed1.setVisibility(View.VISIBLE);
                ed2.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.VISIBLE);
                ed4.setVisibility(View.VISIBLE);
                ed5.setVisibility(View.VISIBLE);
                ed6.setVisibility(View.VISIBLE);

                ed1.setEnabled(true);
                ed2.setEnabled(true);
                ed3.setEnabled(true);
                ed4.setEnabled(true);
                ed5.setEnabled(true);
                ed6.setEnabled(true);

                for (int i = 0; i < mylist.size(); i++) {
                    if (mylist.get(i).equals("1")) {
                        word1 = "";
                    }
                    if (mylist.get(i).equals("2")) {
                        word2 = "";
                    }
                    if (mylist.get(i).equals("3")) {
                        word3 = "";
                    }
                    if (mylist.get(i).equals("4")) {
                        word4 = "";
                    }
                    if (mylist.get(i).equals("5")) {
                        word5 = "";
                    }
                    if (mylist.get(i).equals("6")) {
                        word6 = "";
                    }
                }
                ed1.setText("" + word1);
                ed2.setText("" + word2);
                ed3.setText("" + word3);
                ed4.setText("" + word4);
                ed5.setText("" + word5);
                ed6.setText("" + word6);
            }
            if (letter_length == 7) {

                StringTokenizer word = new StringTokenizer(letters, ",");
                String word1 = word.nextToken().trim();
                String word2 = word.nextToken().trim();
                String word3 = word.nextToken().trim();
                String word4 = word.nextToken().trim();
                String word5 = word.nextToken().trim();
                String word6 = word.nextToken().trim();
                String word7 = word.nextToken().trim();
                ed1.setVisibility(View.VISIBLE);
                ed2.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.VISIBLE);
                ed4.setVisibility(View.VISIBLE);
                ed5.setVisibility(View.VISIBLE);
                ed6.setVisibility(View.VISIBLE);
                ed7.setVisibility(View.VISIBLE);

                ed1.setEnabled(true);
                ed2.setEnabled(true);
                ed3.setEnabled(true);
                ed4.setEnabled(true);
                ed5.setEnabled(true);
                ed6.setEnabled(true);
                ed7.setEnabled(true);

                for (int i = 0; i < mylist.size(); i++) {
                    if (mylist.get(i).equals("1")) {
                        word1 = "";
                    }
                    if (mylist.get(i).equals("2")) {
                        word2 = "";
                    }
                    if (mylist.get(i).equals("3")) {
                        word3 = "";
                    }
                    if (mylist.get(i).equals("4")) {
                        word4 = "";
                    }
                    if (mylist.get(i).equals("5")) {
                        word5 = "";
                    }
                    if (mylist.get(i).equals("6")) {
                        word6 = "";
                    }
                    if (mylist.get(i).equals("7")) {
                        word7 = "";
                    }
                }

                ed1.setText("" + word1);
                ed2.setText("" + word2);
                ed3.setText("" + word3);
                ed4.setText("" + word4);
                ed5.setText("" + word5);
                ed6.setText("" + word6);
                ed7.setText("" + word7);
            }
            if (letter_length == 8) {

                StringTokenizer word = new StringTokenizer(letters, ",");
                String word1 = word.nextToken().trim();
                String word2 = word.nextToken().trim();
                String word3 = word.nextToken().trim();
                String word4 = word.nextToken().trim();
                String word5 = word.nextToken().trim();
                String word6 = word.nextToken().trim();
                String word7 = word.nextToken().trim();
                String word8 = word.nextToken().trim();
                ed1.setVisibility(View.VISIBLE);
                ed2.setVisibility(View.VISIBLE);
                ed3.setVisibility(View.VISIBLE);
                ed4.setVisibility(View.VISIBLE);
                ed5.setVisibility(View.VISIBLE);
                ed6.setVisibility(View.VISIBLE);
                ed7.setVisibility(View.VISIBLE);
                ed8.setVisibility(View.VISIBLE);

                ed1.setEnabled(true);
                ed2.setEnabled(true);
                ed3.setEnabled(true);
                ed4.setEnabled(true);
                ed5.setEnabled(true);
                ed6.setEnabled(true);
                ed7.setEnabled(true);
                ed8.setEnabled(true);

                for (int i = 0; i < mylist.size(); i++) {
                    if (mylist.get(i).equals("1")) {
                        word1 = "";
                    }
                    if (mylist.get(i).equals("2")) {
                        word2 = "";
                    }
                    if (mylist.get(i).equals("3")) {
                        word3 = "";
                    }
                    if (mylist.get(i).equals("4")) {
                        word4 = "";
                    }
                    if (mylist.get(i).equals("5")) {
                        word5 = "";
                    }
                    if (mylist.get(i).equals("6")) {
                        word6 = "";
                    }
                    if (mylist.get(i).equals("7")) {
                        word7 = "";
                    }
                    if (mylist.get(i).equals("8")) {
                        word8 = "";
                    }
                }
                ed1.setText("" + word1);
                ed2.setText("" + word2);
                ed3.setText("" + word3);
                ed4.setText("" + word4);
                ed5.setText("" + word5);
                ed6.setText("" + word6);
                ed7.setText("" + word7);
                ed8.setText("" + word8);
            }

            System.out.println("############randomno" + randomno);
            System.out.println("############letters" + letters);
            System.out.println("############randomno_set" + randomno_set);
            c_button4.setVisibility(View.VISIBLE);
            c_button8.setVisibility(View.VISIBLE);
            c_button12.setVisibility(View.VISIBLE);
            c_button13.setVisibility(View.VISIBLE);
            c_button14.setVisibility(View.VISIBLE);
            c_button15.setVisibility(View.VISIBLE);
            c_button16.setVisibility(View.VISIBLE);
            if (randomno_set == 1) {
                simple();
            } else if (randomno_set == 2) {
                medium();
            } else if (randomno_set == 3) {
                hard();
            }
            Random rn = new Random();
            randomno_show = rn.nextInt(maximum_show - minmum_show + 1) + minmum_show;
            //random_create();
            click_txt_change();
        } else {
            downloaddata_regular2();
            // nextgamesdialog();
        }
    }

    private void random_create() {
        if (letter_length == 4) {
            minmum = 1;
            maximum = 4;
            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;
        }
        if (letter_length == 5) {
            minmum = 1;
            maximum = 5;
            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;
        }
        if (letter_length == 6) {
            minmum = 1;
            maximum = 6;
            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;
        }
        if (letter_length == 7) {
            minmum = 1;
            maximum = 7;
            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;
        }
        if (letter_length == 8) {
            minmum = 1;
            maximum = 8;
            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;
        }

        if (randomno == 1) {
            ed1.setText("");
        } else if (randomno == 2) {
            ed2.setText("");
        } else if (randomno == 3) {
            ed3.setText("");
        } else if (randomno == 4) {
            ed4.setText("");
        } else if (randomno == 5) {
            ed5.setText("");
        } else if (randomno == 6) {
            ed6.setText("");
        } else if (randomno == 7) {
            ed7.setText("");
        } else if (randomno == 8) {
            ed8.setText("");
        }

        mylist.add("" + randomno);
        String str = "" + mylist;
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(" ", "");


        sps.putString(Fill_in_blanks.this, "value_data_blanks", "" + str);
        System.out.println("mylist" + mylist);
    }

    private void click() {
        qwt.setOnClickListener(v -> dialog(0));
        c_ans.setOnClickListener(v -> {

            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk >= 50) {
                c_ans.setEnabled(false);
                r = 1;
                if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {


                    Cursor cd;
                    String date = sps.getString(Fill_in_blanks.this, "date");
                    if (date.equals("0")) {
                        cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "' and isfinish='0' and gameid='" + gameid + "'");
                        cd.moveToFirst();
                    } else {
                        cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "' and gameid='" + gameid + "'");
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
                    // list4.setVisibility(View.INVISIBLE);
                    ed1.setVisibility(View.INVISIBLE);
                    ed2.setVisibility(View.INVISIBLE);
                    ed3.setVisibility(View.INVISIBLE);
                    ed4.setVisibility(View.INVISIBLE);
                    ed5.setVisibility(View.INVISIBLE);
                    ed6.setVisibility(View.INVISIBLE);
                    ed7.setVisibility(View.INVISIBLE);
                    ed8.setVisibility(View.INVISIBLE);
                    Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                    ans_high.startAnimation(w_game);
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(sa);
                    //Update QST
                    String datee = sps.getString(Fill_in_blanks.this, "date");
                    if (datee.equals("0")) {
                        newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
                    } else {
                        newhelper4.executeSql("UPDATE newgamesdb4 SET daily=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
                    }
                    sps.putString(Fill_in_blanks.this, "value_data_blanks", "");
                    //Next Function
                    //  r = 1;


                    focus.stop();
                    // completegame();
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> adShow(), 3000);

                } else {
                    final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                        c_ans.setEnabled(false);
                        Cursor cd;
                        String date = sps.getString(Fill_in_blanks.this, "date");
                        if (date.equals("0")) {
                            cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "' and isfinish='0' and gameid='" + gameid + "'");

                            cd.moveToFirst();
                        } else {
                            cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "'  and gameid='" + gameid + "'");
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
                        // list4.setVisibility(View.INVISIBLE);
                        ed1.setVisibility(View.INVISIBLE);
                        ed2.setVisibility(View.INVISIBLE);
                        ed3.setVisibility(View.INVISIBLE);
                        ed4.setVisibility(View.INVISIBLE);
                        ed5.setVisibility(View.INVISIBLE);
                        ed6.setVisibility(View.INVISIBLE);
                        ed7.setVisibility(View.INVISIBLE);
                        ed8.setVisibility(View.INVISIBLE);

                        Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                        ans_high.startAnimation(w_game);
                        ans_high.setVisibility(View.VISIBLE);
                        ans_high.setText(sas);
                        //Update QST
                        String datee = sps.getString(Fill_in_blanks.this, "date");
                        if (datee.equals("0")) {
                            newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
                        } else {
                            newhelper4.executeSql("UPDATE newgamesdb4 SET daily=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
                        }
                        //Next Function
                        // r = 1;
                        sps.putString(Fill_in_blanks.this, "value_data_blanks", "");
                        openDialog.dismiss();

                        focus.stop();
                        //    completegame();

                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 3000);
                    });
                    no.setOnClickListener(v1 -> {
                        c_ans.setEnabled(true);
                        sps.putString(getApplicationContext(), "checkbox_ans", "");
                        openDialog.dismiss();
                    });
                    if (!isFinishing()) openDialog.show();
                }

            } else {
                dialog(1);
            }
        });
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
        clear_value.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            clear_data = 0;
            System.out.println("==================Collections old" + mylist);
            Collections.sort(mylist, Collections.reverseOrder());
            System.out.println("==================Collections" + mylist);
            for (int i = 0; i < mylist.size(); i++) {

                if (clear_data == 0) {
                    if (ed8.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("8")) {
                            ed8.setText("");
                            clear_data = 1;
                        }
                    }
                }
                if (clear_data == 0) {
                    if (ed7.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("7")) {
                            ed7.setText("");
                            clear_data = 1;
                        }
                    }
                }
                if (clear_data == 0) {
                    if (ed6.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("6")) {
                            ed6.setText("");
                            clear_data = 1;
                        }
                    }
                }

                if (clear_data == 0) {
                    if (ed5.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("5")) {
                            ed5.setText("");
                            clear_data = 1;
                        }
                    }
                }

                if (clear_data == 0) {
                    if (ed4.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("4")) {
                            ed4.setText("");
                            clear_data = 1;
                        }
                    }
                }
                if (clear_data == 0) {
                    if (ed3.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("3")) {
                            ed3.setText("");
                            clear_data = 1;
                        }
                    }
                }
                if (clear_data == 0) {
                    if (ed2.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("2")) {
                            ed2.setText("");
                            clear_data = 1;
                        }
                    }
                }
                if (clear_data == 0) {
                    if (ed1.getText().toString().equals("")) {

                    } else {
                        if (mylist.get(i).equals("1")) {
                            ed1.setText("");
                            clear_data = 1;
                        }
                    }
                }

            }
        });
        c_button1.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button1.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button1.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button1.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button1.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button1.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button1.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button1.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button1.getText().toString());
            }
        });
        c_button2.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button2.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button2.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button2.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button2.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button2.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button2.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button2.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button2.getText().toString());
            }
        });

        c_button3.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button3.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button3.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button3.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button3.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button3.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button3.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button3.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button3.getText().toString());
            }
        });
        c_button4.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button4.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button4.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button4.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button4.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button4.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button4.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button4.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button4.getText().toString());
            }
        });
        c_button5.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button5.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button5.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button5.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button5.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button5.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button5.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button5.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button5.getText().toString());
            }
        });
        c_button6.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button6.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button6.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button6.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button6.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button6.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button6.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button6.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button6.getText().toString());
            }
        });
        c_button7.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button7.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button7.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button7.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button7.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button7.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button7.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button7.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button7.getText().toString());
            }
        });
        c_button8.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button8.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button8.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button8.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button8.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button8.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button8.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button8.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button8.getText().toString());
            }
        });
        c_button9.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button9.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button9.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button9.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button9.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button9.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button9.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button9.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button9.getText().toString());
            }
        });
        c_button10.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button10.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button10.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button10.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button10.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button10.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button10.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button10.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button10.getText().toString());
            }
        });
        c_button11.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button11.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button11.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button11.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button11.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button11.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button11.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button11.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button11.getText().toString());
            }
        });
        c_button12.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button12.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button12.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button12.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button12.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button12.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button12.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button12.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button12.getText().toString());
            }
        });
        c_button13.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button13.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button13.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button13.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button13.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button13.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button13.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button13.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button13.getText().toString());
            }
        });
        c_button14.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button14.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button14.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button14.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button14.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button14.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button14.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button14.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button14.getText().toString());
            }
        });
        c_button15.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button15.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button15.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button15.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button15.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button15.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button15.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button15.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button15.getText().toString());
            }
        });
        c_button16.setOnClickListener(v -> {
            click.play(soundId1, sv, sv, 0, 0, sv);
            if (ed1.getText().toString().equals("")) {
                ed1.setText("" + c_button16.getText().toString());
            } else if (ed2.getText().toString().equals("")) {
                ed2.setText("" + c_button16.getText().toString());
            } else if (ed3.getText().toString().equals("")) {
                ed3.setText("" + c_button16.getText().toString());
            } else if (ed4.getText().toString().equals("")) {
                ed4.setText("" + c_button16.getText().toString());
            } else if (ed5.getText().toString().equals("")) {
                ed5.setText("" + c_button16.getText().toString());
            } else if (ed6.getText().toString().equals("")) {
                ed6.setText("" + c_button16.getText().toString());
            } else if (ed7.getText().toString().equals("")) {
                ed7.setText("" + c_button16.getText().toString());
            } else if (ed8.getText().toString().equals("")) {
                ed8.setText("" + c_button16.getText().toString());
            }
        });

        c_settings.setOnClickListener(v -> {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            String snd = sps.getString(Fill_in_blanks.this, "snd");
            if (snd.equals("off")) {
                sps.putString(Fill_in_blanks.this, "snd", "on");
                c_settings.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd.equals("on")) {
                sps.putString(Fill_in_blanks.this, "snd", "off");
                c_settings.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }
        });

    }

    private void find() {
        c_settings = findViewById(R.id.c_settings);
        String snd = sps.getString(Fill_in_blanks.this, "snd");
        if (snd.equals("off")) {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            sv = 0;
        } else if (snd.equals("on")) {
            c_settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;
        }
        helpshare_layout = findViewById(R.id.helpshare_layout);
        w_head = findViewById(R.id.clue_head);

        to_no = findViewById(R.id.c_word_number);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        ed6 = findViewById(R.id.ed6);
        ed7 = findViewById(R.id.ed7);
        ed8 = findViewById(R.id.ed8);
        clear_value = findViewById(R.id.clear_value);
        ans_high = findViewById(R.id.ans_highlite);
        list4 = findViewById(R.id.list4);

        to_no = findViewById(R.id.c_word_number);
        c_coin = findViewById(R.id.c_coins);
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
        c_button13 = findViewById(R.id.c_button13);
        c_button14 = findViewById(R.id.c_button14);
        c_button15 = findViewById(R.id.c_button15);
        c_button16 = findViewById(R.id.c_button16);
        score = findViewById(R.id.c_score_edit);
        c_coins = findViewById(R.id.c_coins);
        c_ans = findViewById(R.id.c_ans);
        c_word_number = findViewById(R.id.c_word_number);
        focus = findViewById(R.id.c_time_edit);

        h_gplues = findViewById(R.id.ch_gplues);
        h_watts_app = findViewById(R.id.ch_watts_app);
        h_facebook = findViewById(R.id.ch_facebook);
        earncoin = findViewById(R.id.earncoin);
        qwt = findViewById(R.id.qwt);
        ed1.setVisibility(View.GONE);
        ed2.setVisibility(View.GONE);
        ed3.setVisibility(View.GONE);
        ed4.setVisibility(View.GONE);
        ed5.setVisibility(View.GONE);
        ed6.setVisibility(View.GONE);
        ed7.setVisibility(View.GONE);
        ed8.setVisibility(View.GONE);
        c_coins.setVisibility(View.INVISIBLE);

        ed1.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed1.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });
        ed2.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed2.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });
        ed3.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed3.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });
        ed4.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed4.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });
        ed5.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed5.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });
        ed6.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed6.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });
        ed7.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed7.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });
        ed8.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
        });
        ed8.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            return true;
        });

    }

    //
    public void simple() {
        System.out.println("###############letter_length" + letter_length);
        System.out.println("###############letters" + letters);

        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";
        c_button4.setVisibility(View.GONE);
        c_button8.setVisibility(View.GONE);
        c_button12.setVisibility(View.GONE);
        c_button13.setVisibility(View.GONE);
        c_button14.setVisibility(View.GONE);
        c_button15.setVisibility(View.GONE);
        c_button16.setVisibility(View.GONE);
        if (letter_length == 1) {
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
            c_button1.setText(letter1);
            c_button2.setText(letter2);
            c_button3.setText(letter3);
            c_button5.setText(letters);
            c_button6.setText(letter6);
            c_button7.setText(letter7);
            c_button9.setText(letter9);
            c_button10.setText(letter10);
            c_button11.setText(letter11);

        } else if (letter_length == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter1);
            c_button2.setText(letter2);
            c_button3.setText(word1);
            c_button5.setText(letter5);
            c_button6.setText(letter6);
            c_button7.setText(letter7);
            c_button9.setText(letter9);
            c_button10.setText(word2);
            c_button11.setText(letter11);

        } else if (letter_length == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word2);
            c_button2.setText(letter5);
            c_button3.setText(letter2);
            c_button5.setText(word1);
            c_button6.setText(letter9);
            c_button7.setText(letter7);
            c_button9.setText(letter6);
            c_button10.setText(letter14);
            c_button11.setText(word3);

        } else if (letter_length == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word2);
            c_button2.setText(letter10);
            c_button3.setText(word3);
            c_button5.setText(letter6);
            c_button6.setText(letter13);
            c_button7.setText(word4);
            c_button9.setText(word1);
            c_button10.setText(letter7);
            c_button11.setText(letter12);

        } else if (letter_length == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter7);
            c_button2.setText(word5);
            c_button3.setText(letter14);
            c_button5.setText(letter2);
            c_button6.setText(word4);
            c_button7.setText(word3);
            c_button9.setText(word1);
            c_button10.setText(letter6);
            c_button11.setText(word2);

        } else if (letter_length == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word5);
            c_button2.setText(word2);
            c_button3.setText(letter4);
            c_button5.setText(word6);
            c_button6.setText(letter1);
            c_button7.setText(word4);
            c_button9.setText(letter8);
            c_button10.setText(word1);
            c_button11.setText(word3);

        } else if (letter_length == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word3);
            c_button2.setText(word5);
            c_button3.setText(word2);
            c_button5.setText(word7);
            c_button6.setText(word6);
            c_button7.setText(word4);
            c_button9.setText(letter10);
            c_button10.setText(word1);
            c_button11.setText(letter12);

        } else if (letter_length == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word6);
            c_button2.setText(letter6);
            c_button3.setText(word8);
            c_button5.setText(word5);
            c_button6.setText(word7);
            c_button7.setText(word4);
            c_button9.setText(word1);
            c_button10.setText(word3);
            c_button11.setText(word2);

        } else if (letter_length == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word6);
            c_button2.setText(word1);
            c_button3.setText(word8);
            c_button5.setText(word5);
            c_button6.setText(word9);
            c_button7.setText(word7);
            c_button9.setText(word4);
            c_button10.setText(word3);
            c_button11.setText(word2);

        }
    }

    public void medium() {
        System.out.println("###############letter_length" + letter_length);
        System.out.println("###############letters" + letters);
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        c_button13.setVisibility(View.GONE);
        c_button14.setVisibility(View.GONE);
        c_button15.setVisibility(View.GONE);
        c_button16.setVisibility(View.GONE);
        if (letter_length == 1) {

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
            c_button1.setText(letter1);
            c_button2.setText(letter2);
            c_button3.setText(letters);
            c_button4.setText(letter11);
            c_button5.setText(letter5);
            c_button6.setText(letter6);
            c_button7.setText(letter7);
            c_button8.setText(letter3);
            c_button9.setText(letter9);
            c_button10.setText(letter15);
            c_button11.setText(letter4);
            c_button12.setText(letter12);


        } else if (letter_length == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter5);
            c_button2.setText(letter2);
            c_button3.setText(word1);
            c_button4.setText(letter4);
            c_button5.setText(letter1);
            c_button6.setText(letter6);
            c_button7.setText(letter11);
            c_button8.setText(letter3);
            c_button9.setText(letter9);
            c_button10.setText(letter13);
            c_button11.setText(letter7);
            c_button12.setText(word2);


        } else if (letter_length == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word2);
            c_button2.setText(letter15);
            c_button3.setText(word3);
            c_button4.setText(letter4);
            c_button5.setText(word1);
            c_button6.setText(letter9);
            c_button7.setText(letter7);
            c_button8.setText(letter3);
            c_button9.setText(letter6);
            c_button10.setText(word2);
            c_button11.setText(letter2);
            c_button12.setText(letter1);


        } else if (letter_length == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter13);
            c_button2.setText(letter10);
            c_button3.setText(word3);
            c_button4.setText(letter4);
            c_button5.setText(letter6);
            c_button6.setText(word1);
            c_button7.setText(letter7);
            c_button8.setText(letter3);
            c_button9.setText(letter10);
            c_button10.setText(word4);
            c_button11.setText(letter12);
            c_button12.setText(word2);


        } else if (letter_length == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter7);
            c_button2.setText(word5);
            c_button3.setText(word3);
            c_button4.setText(letter4);
            c_button5.setText(letter13);
            c_button6.setText(word4);
            c_button7.setText(letter3);
            c_button8.setText(letter3);
            c_button9.setText(word1);
            c_button10.setText(letter6);
            c_button11.setText(letter8);
            c_button12.setText(word2);


        } else if (letter_length == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word2);
            c_button2.setText(letter9);
            c_button3.setText(letter4);
            c_button4.setText(word5);
            c_button5.setText(letter6);
            c_button6.setText(letter1);
            c_button7.setText(word4);
            c_button8.setText(word6);
            c_button9.setText(letter8);
            c_button10.setText(word1);
            c_button11.setText(word3);
            c_button12.setText(letter4);


        } else if (letter_length == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter8);
            c_button2.setText(letter4);
            c_button3.setText(word5);
            c_button4.setText(word2);
            c_button5.setText(word7);
            c_button6.setText(word3);
            c_button7.setText(word4);
            c_button8.setText(letter11);
            c_button9.setText(letter10);
            c_button10.setText(word1);
            c_button11.setText(letter12);
            c_button12.setText(word6);


        } else if (letter_length == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word6);
            c_button2.setText(letter6);
            c_button3.setText(word8);
            c_button4.setText(letter3);
            c_button5.setText(word5);
            c_button6.setText(word4);
            c_button7.setText(letter7);
            c_button8.setText(word7);
            c_button9.setText(word1);
            c_button10.setText(word3);
            c_button11.setText(word2);
            c_button12.setText(letter8);

        } else if (letter_length == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word6);
            c_button2.setText(word1);
            c_button3.setText(word8);
            c_button4.setText(letter3);
            c_button5.setText(word5);
            c_button6.setText(word9);
            c_button7.setText(letter6);
            c_button8.setText(word4);
            c_button9.setText(word2);
            c_button10.setText(word3);
            c_button11.setText(word8);
            c_button12.setText(word7);

        }
    }

    public void hard() {
        System.out.println("###############letter_length" + letter_length);
        System.out.println("###############letters" + letters);
        String a = "கே,சே,பி,யா,தி,ரே,ஏ,வ்,ர்,கே,ஃ,ஓ,ப,ல்,இ,து,பு,மு,ரூ";

        if (letter_length == 1) {
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
            c_button1.setText(letter1);
            c_button2.setText(letter2);
            c_button3.setText(letters);
            c_button4.setText(letter11);
            c_button5.setText(letter5);
            c_button6.setText(letter6);
            c_button7.setText(letter7);
            c_button8.setText(letter3);
            c_button9.setText(letter9);
            c_button10.setText(letter15);
            c_button11.setText(letter4);
            c_button12.setText(letter12);
            c_button13.setText(letter10);
            c_button14.setText(letter14);
            c_button15.setText(letter12);
            c_button16.setText(letter13);
        } else if (letter_length == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter5);
            c_button2.setText(letter2);
            c_button3.setText(word1);
            c_button4.setText(letter4);
            c_button5.setText(letter1);
            c_button6.setText(letter6);
            c_button7.setText(letter11);
            c_button8.setText(letter3);
            c_button9.setText(letter9);
            c_button10.setText(letter13);
            c_button11.setText(letter7);
            c_button12.setText(letter12);
            c_button13.setText(letter14);
            c_button14.setText(word2);
            c_button15.setText(letter10);
            c_button16.setText(letter8);


        } else if (letter_length == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word2);
            c_button2.setText(letter15);
            c_button3.setText(word3);
            c_button4.setText(letter4);
            c_button5.setText(word1);
            c_button6.setText(letter9);
            c_button7.setText(letter7);
            c_button8.setText(letter3);
            c_button9.setText(letter6);
            c_button10.setText(word2);
            c_button11.setText(letter2);
            c_button12.setText(letter1);
            c_button13.setText(letter14);
            c_button14.setText(letter12);
            c_button15.setText(letter10);
            c_button16.setText(letter13);

        } else if (letter_length == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter13);
            c_button2.setText(letter10);
            c_button3.setText(word3);
            c_button4.setText(letter4);
            c_button5.setText(letter6);
            c_button6.setText(letter11);
            c_button7.setText(letter7);
            c_button8.setText(letter3);
            c_button9.setText(letter10);
            c_button10.setText(word4);
            c_button11.setText(letter12);
            c_button12.setText(letter9);
            c_button13.setText(letter14);
            c_button14.setText(letter15);
            c_button15.setText(word1);
            c_button16.setText(word2);

        } else if (letter_length == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter7);
            c_button2.setText(word5);
            c_button3.setText(word3);
            c_button4.setText(letter4);
            c_button5.setText(letter13);
            c_button6.setText(word4);
            c_button7.setText(letter3);
            c_button8.setText(letter3);
            c_button9.setText(word1);
            c_button10.setText(letter6);
            c_button11.setText(letter8);
            c_button12.setText(word2);
            c_button13.setText(letter14);
            c_button14.setText(letter12);
            c_button15.setText(letter9);
            c_button16.setText(letter10);


        } else if (letter_length == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter14);
            c_button2.setText(letter9);
            c_button3.setText(letter4);
            c_button4.setText(word5);
            c_button5.setText(letter13);
            c_button6.setText(letter8);
            c_button7.setText(word4);
            c_button8.setText(letter7);
            c_button9.setText(letter1);
            c_button10.setText(word1);
            c_button11.setText(word3);
            c_button12.setText(letter4);
            c_button13.setText(word6);
            c_button14.setText(letter11);
            c_button15.setText(letter6);
            c_button16.setText(word2);


        } else if (letter_length == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(letter2);
            c_button2.setText(letter4);
            c_button3.setText(word5);
            c_button4.setText(word2);
            c_button5.setText(word7);
            c_button6.setText(letter13);
            c_button7.setText(word4);
            c_button8.setText(letter8);
            c_button9.setText(letter10);
            c_button10.setText(letter15);
            c_button11.setText(letter12);
            c_button12.setText(word6);
            c_button13.setText(letter6);
            c_button14.setText(word1);
            c_button15.setText(letter11);
            c_button16.setText(word3);

        } else if (letter_length == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word6);
            c_button2.setText(letter15);
            c_button3.setText(word8);
            c_button4.setText(letter13);
            c_button5.setText(word5);
            c_button6.setText(word4);
            c_button7.setText(letter12);
            c_button8.setText(word7);
            c_button9.setText(word1);
            c_button10.setText(word3);
            c_button11.setText(letter14);
            c_button12.setText(letter8);
            c_button13.setText(letter6);
            c_button14.setText(letter7);
            c_button15.setText(word2);
            c_button16.setText(letter10);

        } else if (letter_length == 9) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(letters, ",");
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
            c_button1.setText(word6);
            c_button2.setText(word1);
            c_button3.setText(letter2);
            c_button4.setText(letter13);
            c_button5.setText(word5);
            c_button6.setText(word9);
            c_button7.setText(letter6);
            c_button8.setText(word4);
            c_button9.setText(letter3);
            c_button10.setText(word3);
            c_button11.setText(word8);
            c_button12.setText(word7);
            c_button13.setText(letter6);
            c_button14.setText(letter2);
            c_button15.setText(word2);
            c_button16.setText(word8);
        }
    }

    public void setSc() {
        if (s == 1) {
            if (openDialog_p != null) {
                if (openDialog_p.isShowing()) {
                    openDialog_p.dismiss();
                }
            }
            //  openDialog_p.dismiss();
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

        ImageView prize_logo = openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Fill_in_blanks.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                if (sps.getString(Fill_in_blanks.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Fill_in_blanks.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Fill_in_blanks.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Fill_in_blanks.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Fill_in_blanks.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Fill_in_blanks.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
        TextView video_earn = openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Fill_in_blanks.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Fill_in_blanks.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            // New_Main_Activity.load_addFromMain_multiplayer(Fill_in_blanks.this, ads_layout);
            if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Fill_in_blanks.this, ads_layout);
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
        String date = sps.getString(Fill_in_blanks.this, "date");
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


        if (sps.getString(Fill_in_blanks.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Fill_in_blanks.this, "date");
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
        //  final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);

        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");
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
                            //reward(Fill_in_blanks.this);
                            rewarded_adnew();
                            Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }

        });

        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");
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
                            //reward(Fill_in_blanks.this);
                            rewarded_adnew();
                            Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        });

        wtp.setOnClickListener(view -> {
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


                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });
        fbs.setOnClickListener(view -> {

        });
        gplus.setOnClickListener(view -> {
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
        } else {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        }
        /////////////////////////////////////////Reward tittle////////////////////////////////////////////


        next_continue.setOnClickListener(view -> {
            dia_dismiss = 1;
            openDialog_s.dismiss();
            next();


        });

        openDialog_s.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Fill_in_blanks.this, "game_area", "on");
                String date1 = sps.getString(Fill_in_blanks.this, "date");
                if (date1.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                        openDialog_s.dismiss();
                    }
                } else {
                    if (sps.getString(Fill_in_blanks.this, "Exp_list").equals("on")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Fill_in_blanks.this, Expandable_List_View.class);
                        startActivity(i);

                    } else {
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
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


    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void industrialload() {

        Utills.INSTANCE.initializeAdzz(this);
        if (mInterstitialAd != null) return;

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getResources().getString(R.string.Senthamil_Thedal_Ins), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                interstiallistener();
                Log.i("TAG", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("TAG", loadAdError.toString());
                mInterstitialAd = null;
                Log.i("TAG", "onAdLoadedfailed" + loadAdError.getMessage());
                handler = null;

            }
        });

    }

    public void interstiallistener() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                Log.d("TAG", "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
                handler = null;
                Utills.INSTANCE.Loading_Dialog_dismiss();
                setSc();
                industrialload();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                Log.e("TAG", "Ad failed to show fullscreen content.");
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
            //Toast.makeText(this, "" + sps.getInt(this, "Game3_Stage_Close_ST"), Toast.LENGTH_SHORT).show();

        }

    }

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    public void vidcoinearn() {
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea = ea + setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Fill_in_blanks.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
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
                    String date = sps.getString(Fill_in_blanks.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
                        //myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
                        // myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    }

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Fill_in_blanks.this, Fill_in_blanks.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் விடுபட்ட எழுத்துக்களை கண்டுபிடி இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Fill_in_blanks.this, "permission", 1);
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
                        sps.putInt(Fill_in_blanks.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Fill_in_blanks.this, "permission", 0);
                    }
                }
            }

        }
    }

    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);

        if (sps.getString(Fill_in_blanks.this, "riddle_time_start").equals("")) {
            sps.putString(Fill_in_blanks.this, "riddle_time_start", "yes");
        } else {
            String date = sps.getString(Fill_in_blanks.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                cs = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "' and levelid='" + levelid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                }
            } else {
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

    }

    //reward videos***********************//

    public void onBackPressed() {
 /*   public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if(keyCode==KeyEvent.KEYCODE_BACK) {*/
        sps.putString(Fill_in_blanks.this, "fill_intro_time_start", "yes");
        sps.putString(Fill_in_blanks.this, "game_area", "on");
        sps.putInt(Fill_in_blanks.this, "addlodedd", 0);
        s = 1;
        openDialog_p = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        TextView yes = openDialog_p.findViewById(R.id.yes);
        TextView no = openDialog_p.findViewById(R.id.no);


        yes.setOnClickListener(v -> {

            String dates = sps.getString(Fill_in_blanks.this, "date");
            int pos;
            if (dates.equals("0")) {
                ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                focus.stop();
                newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                //     myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
            } else {
                ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                focus.stop();
                newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                //    myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
            }

            String date = sps.getString(Fill_in_blanks.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            } else {
                if (sps.getString(Fill_in_blanks.this, "Exp_list").equals("on")) {
                    finish();
                    Intent i = new Intent(Fill_in_blanks.this, Expandable_List_View.class);
                    startActivity(i);
                } else {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                }
            }

            openDialog_p.dismiss();
        });
        no.setOnClickListener(v -> openDialog_p.dismiss());
        openDialog_p.show();


        // return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (handler != null) handler.removeCallbacks(my_runnable);
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Fill_in_blanks.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        }
    }

    public void dialog(int i) {
        final Dialog openDialog_earncoin = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Fill_in_blanks.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                        // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");

                        // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    }
                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {
                    //reward(Fill_in_blanks.this);
                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);
                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }


        });

        wp.setOnClickListener(view -> {
            if (isNetworkAvailable()) {
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


            if (isNetworkAvailable()) {


                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Fill_in_blanks.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                        //  myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");

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

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);

        TextView exit = openDialog.findViewById(R.id.exit);

        String date = sps.getString(Fill_in_blanks.this, "date");
        if (date.equals("0")) {
            next_game.setText("விடுபட்ட எழுத்துக்களை கண்டுபிடி  தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("தினசரி விடுபட்ட எழுத்துக்களை கண்டுபிடி  புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள்  சிறப்பாக விளையாட காத்திருக்கும்  விளையாட்டுக்கள்.");
        }
        openDialog.setCancelable(false);
        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Picture_Game_Hard.class);
            startActivity(i);
        });
        exit.setOnClickListener(v -> finish());

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
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Odd_man_out.class);
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
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Ote_to_Tamil.class);
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
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, WordError_correction.class);
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
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
            startActivity(i);
        });
        eng_to_tamil.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
            startActivity(i);
        });

        TextView quiz = openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(Fill_in_blanks.this);
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
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Match_tha_fallows_game.class);
            startActivity(i);

        });
        find_words_from_pictures.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Find_words_from_picture.class);
            startActivity(i);
        });
        quiz.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Quiz_Game.class);
            startActivity(i);
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Fill_in_blanks.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Jamble_word_game.class);
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
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Missing_Words.class);
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
            sps.putString(Fill_in_blanks.this, "date", "0");
            Intent i = new Intent(Fill_in_blanks.this, Find_difference_between_pictures.class);
            startActivity(i);
        });
        if (!isFinishing()) openDialog.show();
        openDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (main_act.equals("")) {

                finish();
                //     openDialog_s.dismiss();
                Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Fill_in_blanks.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Fill_in_blanks.this, "date", "0");

            return keyCode == KeyEvent.KEYCODE_BACK;
        });

    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            System.out.println("##########################sps.getString" + sps.getString(Fill_in_blanks.this, "showcase_dismiss_fill_intro"));
            if (sps.getString(Fill_in_blanks.this, "showcase_dismiss_fill_intro").equals("")) {
                System.out.println("######################showcase_dismiss");
                showcase_dismiss();
            } else {
                System.out.println("######################en_to_intro_time_start");
                sps.putString(Fill_in_blanks.this, "fill_intro_time_start", "yes");
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();
            }
        }, 800);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                download_datas();
            } else {

                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fill_in_blanks.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்").setPositiveButton("அமைப்பு", (dialog, which) -> {
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Fill_in_blanks.this, "goto_sett", 1);
                    dialog.dismiss();
                }).setNegativeButton("பின்னர்", (dialog, which) -> {
                    String date = sps.getString(Fill_in_blanks.this, "date");
                    if (date.equals("0")) {
                        backexitnet();
                    } else {
                        backexitnet();
                    }
                    dialog.dismiss();
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
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


            } else {
            }
        }

    }

    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    public void daily_bones() {
        openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
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


        //  TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog.findViewById(R.id.ok_y);
        TextView tomarrow_coin_earn = openDialog.findViewById(R.id.tomarrow_coin_earn);
        //   TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        ea = 100;
        ok_y.setOnClickListener(v -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + ea;
                String aStringx = Integer.toString(spx);
                score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                sps.putString(Fill_in_blanks.this, "daily_bonus_date", date);
            }

            openDialog.dismiss();

        });

        System.out.println("############################^^^^^^^^^^^^^^currentdate" + str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Fill_in_blanks.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Fill_in_blanks.this, "daily_bonus_date"))) {

        } else {
            sps.putInt(Fill_in_blanks.this, "daily_bonus_count", 0);
        }
        if (sps.getInt(Fill_in_blanks.this, "daily_bonus_count") == 0) {
            ea = 100;
        } else if (sps.getInt(Fill_in_blanks.this, "daily_bonus_count") == 1) {
            ea = 150;
        } else if (sps.getInt(Fill_in_blanks.this, "daily_bonus_count") == 2) {
            ea = 200;
        } else if (sps.getInt(Fill_in_blanks.this, "daily_bonus_count") == 3) {
            ea = 250;
        } else if (sps.getInt(Fill_in_blanks.this, "daily_bonus_count") == 4) {
            ea = 300;
        }
        prize_data_update(Fill_in_blanks.this, ea);
        coin_value = openDialog.findViewById(R.id.coin_value);
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomno = rn.nextInt(maximum - minmum + 1) + minmum;

        String ran_score = "";
        if (randomno == 1) {
            sps.putInt(Fill_in_blanks.this, "daily_bonus_count", 1);
            ran_score = "150";
        } else if (randomno == 2) {
            sps.putInt(Fill_in_blanks.this, "daily_bonus_count", 2);
            ran_score = "200";
        } else if (randomno == 3) {
            sps.putInt(Fill_in_blanks.this, "daily_bonus_count", 3);
            ran_score = "250";
        } else if (randomno == 4) {
            sps.putInt(Fill_in_blanks.this, "daily_bonus_count", 4);
            ran_score = "300";
        }

        extra_coin = openDialog.findViewById(R.id.extra_coin);
        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);

        TextView coin_value = openDialog.findViewById(R.id.coin_value);
        ea = 100;
        final int vals = reward_play_count * 100;
        ea = ea + vals;
        coin_value.setText("" + ea);

        extra_coin = openDialog.findViewById(R.id.extra_coin);
        extra_coin.setOnClickListener(v -> {
            rvo = 1;
            extra_coin_s = 1;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");
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
                            //reward(Fill_in_blanks.this);
                            rewarded_adnew();
                            Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);


                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        });
        if (!isFinishing()) openDialog.show();
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
        String date = sps.getString(Fill_in_blanks.this, "date");
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                prize_data_update(Fill_in_blanks.this, 75);
            } else if (timeElapsed > 91300) {
                prize_data_update(Fill_in_blanks.this, 50);
            } else {
                prize_data_update(Fill_in_blanks.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                prize_data_update(Fill_in_blanks.this, 100);
            } else if (timeElapsed > 91300) {
                prize_data_update(Fill_in_blanks.this, 75);
            } else {
                prize_data_update(Fill_in_blanks.this, 50);
            }
        }
        ////////////////Prize//////////////////
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

    public void downloaddata_regular2() {

        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fill_in_blanks.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {
            //DownLoad Letters and Words

            if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                download_datas();
            } else {

                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Fill_in_blanks.this);                           /* .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog12, which) -> {
                    // continue with delete

                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Fill_in_blanks.this, "goto_sett", 1);


                    dialog12.dismiss();
                }).setNegativeButton("பின்னர்", (dialog1, which) -> {
                    // do nothing
                    sps.putString(Fill_in_blanks.this, "game_area", "on");
                    String date = sps.getString(Fill_in_blanks.this, "date");
                    if (date.equals("0")) {
                        backexitnet();
                    } else {
                        backexitnet();
                    }
                    dialog1.dismiss();
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
            }

        });
        alertDialogBuilder.setPositiveButton("இல்லை ", (dialog, id) -> {
            sps.putString(Fill_in_blanks.this, "game_area", "on");
            finish();
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    private void download_datas() {
        Cursor cz = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "'order by levelid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("levelid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Fill_in_blanks.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    public void rewarded_adnew() {


        RewardedInterstitialAd.load(this, getResources().getString(R.string.Reward_Ins),
                new AdRequest.Builder().build(), new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedInterstitialAd ad) {
                        rewardedAd = ad;
                        fb_reward = 1;

                        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.d("FindWFP", "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d("FindWFP", "Ad dismissed fullscreen content.");
                                rewardedAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e("FindWFP", "Ad failed to show fullscreen content.");
                                rewardedAd = null;
                                rewarded_adnew();
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded for an ad.
                                Log.d("FindWFP", "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d("FindWFP", "Ad showed fullscreen content.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        Log.d("FindWFP", loadAdError.toString());
                        rewardedAd = null;
                    }
                });

    }

    public void show_reward() {
        OnUserEarnedRewardListener success = rewardItem -> {
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
                Toast.makeText(Fill_in_blanks.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
            }
            fb_reward = 0;

        };
        if (rewardedAd != null) {
            rewardedAd.show(this, success);
            reward_status = 1;
        } else {
            Toast.makeText(this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
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
