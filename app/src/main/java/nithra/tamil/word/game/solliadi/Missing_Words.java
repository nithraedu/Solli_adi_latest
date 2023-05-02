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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
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
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Missing_Words extends AppCompatActivity implements View.OnClickListener, Download_completed {
    //*********************reward videos process 1***********************

    static final int vs = 0;
    static int rvo = 0;
    static int mCoinCount = 20;
    final SharedPreference sps = new SharedPreference();
    final String gameid = "19";
    TextView ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9;
    TextView c_button1, c_button2, c_button3, c_button4, p_coins, p_coins_red;
    TextView c_score_edit, c_word_number, c_ans;
    Chronometer focus;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    Newgame_DataBaseHelper5 newhelper5;
    Newgame_DataBaseHelper6 newhelper6;
    RelativeLayout adsicon;
    LinearLayout ads_layout;
    String question_id = "", question = "", answer = "";
    int u_id = 0;
    String isdown = "0";
    String sf_word;
    SoundPool spz1, spz2, spz3, spz4;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    long ttstop;
    Dialog openDialog_s, openDialog_p, openDialog_odd_man;
    int e2;
    int s = 0;
    TextView next_continue;
    TextView ttscores;
    Typeface tyr;
    int f_sec;
    int r = 0;
    int dia_dismiss = 0;
    int fb_reward = 0;
    int reward_status = 0;
    //reward videos process 1***********************
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    int setval_vid;
    TextView coin_value, c_settings;
    LinearLayout ads_lay;
    RelativeLayout head;
    TextView ch_watts_app, ch_facebook;
    int share_name = 0;
    LinearLayout qwt;
    TextView word1, word2, word3, word4, word5, word6, ans, dis, close;
    String retype = "s";
    int setting_access = 0;
    String answers = "";
    Handler handler;
    Runnable my_runnable;
    private RewardedInterstitialAd rewardedAd;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing__words);

        newhelper6 = new Newgame_DataBaseHelper6(Missing_Words.this);
        newhelper = new Newgame_DataBaseHelper(Missing_Words.this);
        newhelper2 = new Newgame_DataBaseHelper2(Missing_Words.this);
        newhelper3 = new Newgame_DataBaseHelper3(Missing_Words.this);
        myDbHelper = new DataBaseHelper(Missing_Words.this);
        newhelper4 = new Newgame_DataBaseHelper4(Missing_Words.this);

        myDbHelper = new DataBaseHelper(this);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        ed6 = findViewById(R.id.ed6);
        ed7 = findViewById(R.id.ed7);
        ed8 = findViewById(R.id.ed8);
        ed9 = findViewById(R.id.ed9);
        p_coins = findViewById(R.id.p_coins);
        p_coins_red = findViewById(R.id.p_coins_red);
        head = findViewById(R.id.head);
        c_button1 = findViewById(R.id.c_button1);
        c_button2 = findViewById(R.id.c_button2);
        c_button3 = findViewById(R.id.c_button3);
        c_button4 = findViewById(R.id.c_button4);
        c_settings = findViewById(R.id.c_settings);

        ch_watts_app = findViewById(R.id.ch_watts_app);
        ch_facebook = findViewById(R.id.ch_facebook);
        qwt = findViewById(R.id.qwt);


        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Missing_Words.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Missing_Words.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Missing_Words.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Missing_Words.this, R.raw.coins, 1);///


        openDialog_s = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        adsicon = openDialog_s.findViewById(R.id.adsicon);
        ads_layout = openDialog_s.findViewById(R.id.fl_adplaceholder);


        if (sps.getInt(Missing_Words.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(Missing_Words.this);
            /**/
        }

        //New_Main_Activity.fb_addload(Missing_Words.this);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");

        Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        if (sps.getInt(Missing_Words.this, "purchase_ads") == 0) {
            industrialload();
        }
        soundset();
        ads_lay = findViewById(R.id.ads_lay);
        Utills.INSTANCE.load_add_AppLovin(this, ads_lay, getResources().getString(R.string.Bottom_Banner));

        ImageView prize_logo = findViewById(R.id.prize_logo);
        if (sps.getInt(Missing_Words.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                if (sps.getString(Missing_Words.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Missing_Words.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Missing_Words.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Missing_Words.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Missing_Words.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Missing_Words.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
        c_score_edit = findViewById(R.id.c_score_edit);
        c_word_number = findViewById(R.id.c_word_number);
        c_ans = findViewById(R.id.c_ans);
        focus = findViewById(R.id.c_time_edit);

        c_button1.setOnClickListener(v -> validate("" + c_button1.getText().toString(), "b1"));
        c_button2.setOnClickListener(v -> validate("" + c_button2.getText().toString(), "b2"));
        c_button3.setOnClickListener(v -> validate("" + c_button3.getText().toString(), "b3"));
        c_button4.setOnClickListener(v -> validate("" + c_button4.getText().toString(), "b4"));
        c_ans.setOnClickListener(v -> user_verify());
        ch_watts_app.setOnClickListener(v -> {
            share_name = 2;
            final String a = "com.whatsapp";
            permission(a);
        });
        ch_facebook.setOnClickListener(v -> {
            share_name = 1;
            final String a = "com.facebook.katana";
            permission(a);
        });
        c_settings.setOnClickListener(v -> {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            String snd = sps.getString(Missing_Words.this, "snd");
            if (snd.equals("off")) {
                sps.putString(Missing_Words.this, "snd", "on");
                c_settings.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd.equals("on")) {
                sps.putString(Missing_Words.this, "snd", "off");
                c_settings.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }
        });
        qwt.setOnClickListener(v -> dialog(0));
        if (sps.getString(Missing_Words.this, "mw_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view
            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Missing_Words.this, "sequence example qz");
            sequence.setConfig(config);
            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");
            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Missing_Words.this).setTarget(ch_facebook).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener((itemView, position) -> {
                if (position == 1) {
                    sps.putString(Missing_Words.this, "mw_time_start", "yes");
                    sps.putString(Missing_Words.this, "showcase_dismiss_mw", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }
            });
            sps.putString(Missing_Words.this, "mw_intro", "no");
            sequence.start();
        }
        next();
    }

    public void validate(String ans, String dtn_name) {
        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
        if (sk > 50) {
            String dates = sps.getString(Missing_Words.this, "date");
            Cursor cs = null;
            if (dates.equals("0")) {
                cs = newhelper6.getQry("select * from newgames5 where answer LIKE'" + ans + "'and isfinish='0' and questionid=" + question_id + " and gameid=" + gameid + "");
            } else {
                cs = newhelper6.getQry("select * from newgames5 where answer LIKE'" + ans + "'and daily='0' and questionid=" + question_id + " and gameid=" + gameid + "");
            }
            cs.moveToFirst();
            if (cs.getCount() != 0) {
                spz3.play(soundId3, sv, sv, 0, 0, sv);
                String date = sps.getString(Missing_Words.this, "date");

                if (date.equals("0")) {
                    newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                } else {
                    newhelper6.executeSql("UPDATE newgames5 SET daily='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                }
                System.out.println("##################################UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                if (dtn_name.equals("b1")) {
                    // change_red();
                    c_button1.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button1.setTextColor(getResources().getColor(R.color.white));
                } else if (dtn_name.equals("b2")) {
                    // change_red();
                    c_button2.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button2.setTextColor(getResources().getColor(R.color.white));
                } else if (dtn_name.equals("b3")) {
                    // change_red();
                    c_button3.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button3.setTextColor(getResources().getColor(R.color.white));
                } else if (dtn_name.equals("b4")) {
                    // change_red();
                    c_button4.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button4.setTextColor(getResources().getColor(R.color.white));
                }
                ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                focus.stop();
                c_button1.setPadding(20, 20, 20, 20);
                c_button2.setPadding(20, 20, 20, 20);
                c_button3.setPadding(20, 20, 20, 20);
                c_button4.setPadding(20, 20, 20, 20);
                price_update();
                c_button1.setEnabled(false);
                c_button2.setEnabled(false);
                c_button3.setEnabled(false);
                c_button4.setEnabled(false);
                c_ans.setEnabled(false);
                answers = "yes";
                coinanim();

                Handler handler = new Handler(Looper.myLooper());
                handler.postDelayed(() -> adShow(), 2300);
            } else {

                String date = sps.getString(Missing_Words.this, "date");

                spz2.play(soundId2, sv, sv, 0, 0, sv);
                if (date.equals("0")) {
                    newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                } else {
                    newhelper6.executeSql("UPDATE newgames5 SET daily='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                }

                if (answer.equals(c_button1.getText().toString())) {
                    // change_red();
                    c_button1.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button1.setTextColor(getResources().getColor(R.color.white));
                } else if (answer.equals(c_button2.getText().toString())) {
                    // change_red();
                    c_button2.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button2.setTextColor(getResources().getColor(R.color.white));
                } else if (answer.equals(c_button3.getText().toString())) {
                    // change_red();
                    c_button3.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button3.setTextColor(getResources().getColor(R.color.white));
                } else if (answer.equals(c_button4.getText().toString())) {
                    // change_red();
                    c_button4.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button4.setTextColor(getResources().getColor(R.color.white));
                }
                if (dtn_name.equals("b1")) {
                    c_button1.setBackgroundResource(R.drawable.button3);
                } else if (dtn_name.equals("b2")) {
                    c_button2.setBackgroundResource(R.drawable.button3);
                } else if (dtn_name.equals("b3")) {
                    c_button3.setBackgroundResource(R.drawable.button3);
                } else if (dtn_name.equals("b4")) {
                    c_button4.setBackgroundResource(R.drawable.button3);
                }
                answers = "no";
                c_button1.setPadding(20, 20, 20, 20);
                c_button2.setPadding(20, 20, 20, 20);
                c_button3.setPadding(20, 20, 20, 20);
                c_button4.setPadding(20, 20, 20, 20);
                ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                focus.stop();
                c_button1.setEnabled(false);
                c_button2.setEnabled(false);
                c_button3.setEnabled(false);
                c_button4.setEnabled(false);
                c_ans.setEnabled(false);
                coinanim_red();
                Handler handler = new Handler(Looper.myLooper());
                handler.postDelayed(() -> adShow(), 2300);
            }
        } else {
            dialog(1);
        }

    }

    private void reset() {
        answers = "";

        head.setVisibility(View.VISIBLE);
        c_button1.setBackgroundResource(R.drawable.back_answer_list);
        c_button2.setBackgroundResource(R.drawable.back_answer_list);
        c_button3.setBackgroundResource(R.drawable.back_answer_list);
        c_button4.setBackgroundResource(R.drawable.back_answer_list);

        c_button1.setTextColor(getResources().getColor(R.color.dark_green));
        c_button2.setTextColor(getResources().getColor(R.color.dark_green));
        c_button3.setTextColor(getResources().getColor(R.color.dark_green));
        c_button4.setTextColor(getResources().getColor(R.color.dark_green));
        c_button1.setEnabled(true);
        c_button2.setEnabled(true);
        c_button3.setEnabled(true);
        c_button4.setEnabled(true);
        c_ans.setEnabled(true);
        c_button1.setPadding(20, 20, 20, 20);
        c_button2.setPadding(20, 20, 20, 20);
        c_button3.setPadding(20, 20, 20, 20);
        c_button4.setPadding(20, 20, 20, 20);
    }

    public void next() {
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = 0;
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        }
        c_score_edit.setText("" + skx);
        reset();
        String date = sps.getString(Missing_Words.this, "date");
        // myDbHelper.executeSql("DELETE FROM answertable");
        if (date.equals("0")) {
            Cursor c1 = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            c_word_number.setText(no/*+"/"+c1.getCount()*/);
        } else {
            String tfoption = date;
            String[] first = tfoption.split("-");
            c_word_number.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            c_word_number.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }


        Cursor c;
        if (date.equals("0")) {
            c = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            c = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
            c.moveToFirst();
        }

        if (c.getCount() != 0) {
            u_id = c.getInt(c.getColumnIndexOrThrow("id"));
            question = c.getString(c.getColumnIndexOrThrow("question"));
            question_id = c.getString(c.getColumnIndexOrThrow("questionid"));
            answer = c.getString(c.getColumnIndexOrThrow("answer"));
            sf_word = c.getString(c.getColumnIndexOrThrow("sf_word"));
            isdown = c.getString(c.getColumnIndexOrThrow("isdown"));
            //question_txt.setText(question);
            String tfoption = sf_word;
            String[] first = tfoption.split(",");
            int optlen = first.length;
            if (optlen == 4) {
                c_button1.setText("" + first[0]);
                c_button2.setText("" + first[1]);
                c_button3.setText("" + first[2]);
                c_button4.setText("" + first[3]);
            }
            set_question_new(question);

            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            if (playtime == 0) {
                if (sps.getString(Missing_Words.this, "resume_mwa").equals("")) {
                    sps.putString(Missing_Words.this, "resume_mwa", "yes");
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

    private void set_question_new(String qs) {
        ed1.setVisibility(View.VISIBLE);
        ed1.setText("" + qs);
    }

    private void set_question(String qs) {
        String tfoption = qs;
        String[] first = tfoption.split(",");
        int optlen = first.length;
        if (optlen == 3) {
            ed1.setVisibility(View.VISIBLE);
            if (first[0].equals("_")) {
                first[0] = "________________";
            } else if (first[1].equals("_")) {
                first[1] = "________________";
            } else if (first[2].equals("_")) {
                first[2] = "________________";
            }
            ed1.setText("" + first[0] + " " + first[1] + " " + first[2]);
        } else if (optlen == 4) {
            ed1.setVisibility(View.VISIBLE);
            if (first[0].equals("_")) {
                first[0] = "________________";
            } else if (first[1].equals("_")) {
                first[1] = "________________";
            } else if (first[2].equals("_")) {
                first[2] = "________________";
            } else if (first[3].equals("_")) {
                first[3] = "________________";
            }
            ed1.setText("" + first[0] + " " + first[1] + " " + first[2] + " " + first[3]);
        } else if (optlen == 5) {
            ed1.setVisibility(View.VISIBLE);
            if (first[0].equals("_")) {
                first[0] = "________________";
            } else if (first[1].equals("_")) {
                first[1] = "________________";
            } else if (first[2].equals("_")) {
                first[2] = "________________";
            } else if (first[3].equals("_")) {
                first[3] = "________________";
            } else if (first[4].equals("_")) {
                first[4] = "________________";
            }
            ed1.setText("" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4]);
        } else if (optlen == 6) {
            ed1.setVisibility(View.VISIBLE);
            if (first[0].equals("_")) {
                first[0] = "________________";
            } else if (first[1].equals("_")) {
                first[1] = "________________";
            } else if (first[2].equals("_")) {
                first[2] = "________________";
            } else if (first[3].equals("_")) {
                first[3] = "________________";
            } else if (first[4].equals("_")) {
                first[4] = "________________";
            } else if (first[5].equals("_")) {
                first[5] = "________________";
            }
            ed1.setText("" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5]);
        } else if (optlen == 7) {
            ed1.setVisibility(View.VISIBLE);
            if (first[0].equals("_")) {
                first[0] = "________________";
            } else if (first[1].equals("_")) {
                first[1] = "________________";
            } else if (first[2].equals("_")) {
                first[2] = "________________";
            } else if (first[3].equals("_")) {
                first[3] = "________________";
            } else if (first[4].equals("_")) {
                first[4] = "________________";
            } else if (first[5].equals("_")) {
                first[5] = "________________";
            } else if (first[6].equals("_")) {
                first[6] = "________________";
            }
            ed1.setText("" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5] + " " + first[6]);
        } else if (optlen == 8) {
            ed1.setVisibility(View.VISIBLE);
            if (first[0].equals("_")) {
                first[0] = "________________";
            } else if (first[1].equals("_")) {
                first[1] = "________________";
            } else if (first[2].equals("_")) {
                first[2] = "________________";
            } else if (first[3].equals("_")) {
                first[3] = "________________";
            } else if (first[4].equals("_")) {
                first[4] = "________________";
            } else if (first[5].equals("_")) {
                first[5] = "________________";
            } else if (first[6].equals("_")) {
                first[6] = "________________";
            } else if (first[7].equals("_")) {
                first[7] = "________________";
            }
            ed1.setText("" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5] + " " + first[6] + " " + first[7]);
        } else if (optlen == 9) {
            ed1.setVisibility(View.VISIBLE);
            // ed2.setVisibility(View.VISIBLE);
            if (first[0].equals("_")) {
                first[0] = "________________";
            } else if (first[1].equals("_")) {
                first[1] = "________________";
            } else if (first[2].equals("_")) {
                first[2] = "________________";
            } else if (first[3].equals("_")) {
                first[3] = "________________";
            } else if (first[4].equals("_")) {
                first[4] = "________________";
            } else if (first[5].equals("_")) {
                first[5] = "________________";
            } else if (first[6].equals("_")) {
                first[6] = "________________";
            } else if (first[7].equals("_")) {
                first[7] = "________________";
            } else if (first[8].equals("_")) {
                first[8] = "________________";
            }
            ed1.setText("" + first[0] + " " + first[1] + " " + first[2] + " " + first[3] + " " + first[4] + " " + first[5] + " " + first[6] + " " + first[7] + " " + first[8]);
        }

    }

    public void coinanim() {
////
        //score intial
        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        c_score_edit.setText(tr);
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
        c_score_edit.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        c_score_edit.getLocationOnScreen(locationOnScreenSecond);
        float destinationX = locationOnScreenSecond[0];
        float destinationY = locationOnScreenSecond[1];
        TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
        transAnimation.setDuration(700);
        p_coins.startAnimation(transAnimation);
        p_coins.postDelayed(() -> p_coins.setVisibility(View.INVISIBLE), transAnimation.getDuration());


        ////

        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(() -> {
            //play1.start();
            spz4.play(soundId4, sv, sv, 0, 0, sv);
            p_coins.setVisibility(View.VISIBLE);
            int[] locationInWindow1 = new int[2];
            p_coins.getLocationInWindow(locationInWindow1);
            int[] locationOnScreen1 = new int[2];
            p_coins.getLocationOnScreen(locationOnScreen1);
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
            p_coins.startAnimation(transAnimation1);
            p_coins.postDelayed(() -> p_coins.setVisibility(View.INVISIBLE), transAnimation1.getDuration());
        }, 1000);

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

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
            c_score_edit.startAnimation(levels1);
        }, 2200);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(() -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 20;
                String aStringx = Integer.toString(spx);
                c_score_edit.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            }


            Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
            ch.moveToFirst();
            if (ch.getCount() != 0) {
                int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
                int shh = sh + 50;
                myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

            }
            // setSc();
        }, 1200);

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
        TextView extracoin2 = openDialog_s.findViewById(R.id.extracoin2);
        final LinearLayout vid_earn = openDialog_s.findViewById(R.id.vid_earn);
        final LinearLayout rewardvideo = openDialog_s.findViewById(R.id.rewardvideo);
        LinearLayout ads_layout = openDialog_s.findViewById(R.id.fl_adplaceholder);

        TextView video_earn = openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Missing_Words.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");
        TextView discription = openDialog_s.findViewById(R.id.discription);
        discription.setText("கேள்விக்கான பதில்");
        discription.setVisibility(View.VISIBLE);
        ImageView prize_logo = openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Missing_Words.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                if (sps.getString(Missing_Words.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Missing_Words.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Missing_Words.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Missing_Words.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Missing_Words.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Missing_Words.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Missing_Words.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(Missing_Words.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            //New_Main_Activity.load_addFromMain_multiplayer(Missing_Words.this, ads_layout);
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Missing_Words.this, ads_layout);
            } else {
                ads_layout.setVisibility(View.GONE);
            }
        }


        discription.setText("கேள்விக்கான பதில்");
        discription.setVisibility(View.VISIBLE);
        discription.setOnClickListener(v -> dialog_dicription());
        next_continue.setVisibility(View.VISIBLE);
        word.setTypeface(tyr);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);

        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");

        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(Missing_Words.this, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        String aStringx = Integer.toString(skx);
        ttscores.setText(aStringx);


        if (sps.getString(Missing_Words.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Missing_Words.this, "date");
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
        //discription.startAnimation(myFadeInAnimation);


        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Missing_Words.this, "" + "Reward video", "Loading...");
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
                            //reward(Missing_Words.this);
                            rewarded_adnew();
                            Toast.makeText(Missing_Words.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }

        });

        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Missing_Words.this, "" + "Reward video", "Loading...");
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
                            //reward(Missing_Words.this);
                            rewarded_adnew();
                            Toast.makeText(Missing_Words.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }
        });
        wtp.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.whatsapp");

                    String msg = ("நான் சொல்லிஅடி செயலியில் வினாடி வினா நிலை " + c_word_number.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.google.android.apps.plus");

                    String msg = ("நான் சொல்லிஅடி செயலியில் வினாடி வினாி நிலை " + c_word_number.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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

        if (answers.equals("yes")) {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        } else {
            word.setText("");
            word.setText("ºòŸC ªêŒè");
        }

        next_continue.setOnClickListener(view -> {
            dia_dismiss = 1;
            openDialog_s.dismiss();
            next();


        });
        openDialog_s.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Missing_Words.this, "game_area", "on");
                if (main_act.equals("")) {
                    finish();
                    openDialog_s.dismiss();
                    Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                    openDialog_s.dismiss();
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
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    //*********************reward videos process 3***********************


    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            final Dialog openDialog = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            if (!isFinishing()) openDialog.show();
        }

    }


    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Missing_Words.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }

    @Override
    public void onClick(View v) {

    }

    public void coinanim_red() {
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
        p_coins.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        p_coins.getLocationOnScreen(locationOnScreenSecond);
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
            p_coins.getLocationInWindow(locationInWindowSecond1);
            int[] locationOnScreenSecond1 = new int[2];
            p_coins.getLocationOnScreen(locationOnScreenSecond1);
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
        final Dialog openDialog = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);

        TextView exit = openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Missing_Words.this, "date");
        if (date.equals("0")) {
            next_game.setText("விடுப்பட்ட வார்த்தையை நிரப்பவும் தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("விடுப்பட்ட வார்த்தையை நிரப்பவும் புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");
        }

        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Clue_Game_Hard.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Picture_Game_Hard.class);
            startActivity(i);
        });

        exit.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
            startActivity(i);
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

        TextView odd_man_out = openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(view -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Odd_man_out.class);
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
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Ote_to_Tamil.class);
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
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Riddle_game.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, WordError_correction.class);
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
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Fill_in_blanks.class);
            startActivity(i);
        });


        TextView quiz = openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(Missing_Words.this);
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
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Match_tha_fallows_game.class);
            startActivity(i);

        });
        find_words_from_pictures.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Missing_Words.class);
            startActivity(i);
        });
        quiz.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Missing_Words.class);
            startActivity(i);
        });

        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Missing_Words.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);

        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Missing_Words.class);
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
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Missing_Words.class);
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
            sps.putString(Missing_Words.this, "date", "0");
            Intent i = new Intent(Missing_Words.this, Find_difference_between_pictures.class);
            startActivity(i);
        });
        if (!isFinishing()) openDialog.show();

        openDialog.setOnKeyListener((dialog, keyCode, event) -> {

            if (main_act.equals("")) {

                finish();
                //     openDialog_s.dismiss();
                Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Missing_Words.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Missing_Words.this, "date", "0");


            return keyCode == KeyEvent.KEYCODE_BACK;
        });
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
        f_sec = sec + sec2 + seconds;
        //
        String date = sps.getString(Missing_Words.this, "date");
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                prize_data_update(Missing_Words.this, 75);
            } else if (timeElapsed > 91300) {
                prize_data_update(Missing_Words.this, 50);
            } else {
                prize_data_update(Missing_Words.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                prize_data_update(Missing_Words.this, 100);
            } else if (timeElapsed > 91300) {
                prize_data_update(Missing_Words.this, 75);
            } else {
                prize_data_update(Missing_Words.this, 50);
            }
        }
        ////////////////Prize//////////////////
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("#################OnStop");

        if (handler != null) handler.removeCallbacks(my_runnable);

        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.stop();

        String date = sps.getString(Missing_Words.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        System.out.println("#################OnResume");
        if (sps.getString(Missing_Words.this, "resume_mw").equals("")) {
            sps.putString(Missing_Words.this, "resume_mw", "yes");
        } else {
            String date = sps.getString(Missing_Words.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
            } else {
                pos = 2;
            }
            Cursor cs = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + question_id + "'");
            cs.moveToFirst();
            long dscore = 0;
            if (cs.getCount() != 0) {
                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }


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

                    String date = sps.getString(Missing_Words.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                    }

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Missing_Words.this, Missing_Words.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");

                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT, "Solli_adi");
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

    public void dialog(int i) {

        final Dialog openDialog_earncoin = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }
        video.setOnClickListener(v -> {
            rvo = 1;
            extra_coin_s = 0;
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Missing_Words.this, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    focus.stop();

                    String date = sps.getString(Missing_Words.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                    }
                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {
                    //reward(Missing_Words.this);
                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(Missing_Words.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);
                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        });

        wp.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
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
            if (Utils.isNetworkAvailable(Missing_Words.this)) {

                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Missing_Words.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                    } else {
                        pos = 2;
                    }
                    newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

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

    public void permission(final String a) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
            focus.stop();

            String date = sps.getString(Missing_Words.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
                newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
            } else {
                pos = 2;
                newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
            }
            helpshare(a);
        }
    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Missing_Words.this, "showcase_dismiss_mw").equals("")) {
                showcase_dismiss();
            } else {
                sps.putString(Missing_Words.this, "mw_time_start", "yes");
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();
            }

        }, 800);
    }

    private void user_verify() {
        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
        if (sk > 50) {
            if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                ans_checked();
            } else {
                final Dialog openDialog = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = openDialog.findViewById(R.id.yes);
                TextView no = openDialog.findViewById(R.id.no);
                TextView txt_ex2 = openDialog.findViewById(R.id.txt_ex2);
                TextView txt_ex = openDialog.findViewById(R.id.txt_ex);
                txt_ex.setText("விடைகளை பார்க்க வேண்டுமா ?");
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


                    ans_checked();

                    openDialog.dismiss();
                });
                no.setOnClickListener(v -> {
                    sps.putString(getApplicationContext(), "checkbox_ans", "");
                    openDialog.dismiss();
                });
                if (!isFinishing()) openDialog.show();
            }


        } else {
            dialog(1);
        }
    }

    private void ans_checked() {
        String date = sps.getString(Missing_Words.this, "date");
        Cursor c;
        if (date.equals("0")) {
            c = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            c = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
            c.moveToFirst();
        }
        if (c.getCount() != 0) {
            spz2.play(soundId2, sv, sv, 0, 0, sv);
            if (date.equals("0")) {
                newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
            } else {
                newhelper6.executeSql("UPDATE newgames5 SET daily='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
            }

            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx - 50;
            String aStringx = Integer.toString(spx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            c_score_edit.setText(aStringx);

            if (answer.equals(c_button1.getText().toString())) {
                // change_red();
                c_button1.setBackgroundResource(R.drawable.correct_ans_val);
                c_button1.setTextColor(getResources().getColor(R.color.white));
            } else if (answer.equals(c_button2.getText().toString())) {
                //change_red();
                c_button2.setBackgroundResource(R.drawable.correct_ans_val);
                c_button2.setTextColor(getResources().getColor(R.color.white));
            } else if (answer.equals(c_button3.getText().toString())) {
                // change_red();
                c_button3.setBackgroundResource(R.drawable.correct_ans_val);
                c_button3.setTextColor(getResources().getColor(R.color.white));
            } else if (answer.equals(c_button4.getText().toString())) {
                // change_red();
                c_button4.setBackgroundResource(R.drawable.correct_ans_val);
                c_button4.setTextColor(getResources().getColor(R.color.white));
            }
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
            focus.stop();
            c_button1.setEnabled(false);
            c_button2.setEnabled(false);
            c_button3.setEnabled(false);
            c_button4.setEnabled(false);
            c_ans.setEnabled(false);
            c_button1.setPadding(20, 20, 20, 20);
            c_button2.setPadding(20, 20, 20, 20);
            c_button3.setPadding(20, 20, 20, 20);
            c_button4.setPadding(20, 20, 20, 20);
            Handler handler = new Handler(Looper.myLooper());
            handler.postDelayed(() -> adShow(), 2300);
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        sps.putString(Missing_Words.this, "game_area", "on");
        openDialog_p = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        TextView yes = openDialog_p.findViewById(R.id.yes);
        TextView no = openDialog_p.findViewById(R.id.no);


        yes.setOnClickListener(v -> {

            focus.stop();
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();


            String date = sps.getString(Missing_Words.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
            } else {
                pos = 2;
            }

            newhelper6.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");

            // String date = sps.getString(Missing_Words.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            } else {
                if (sps.getString(Missing_Words.this, "Exp_list").equals("on")) {
                    finish();
                    Intent i = new Intent(Missing_Words.this, Expandable_List_View.class);
                    startActivity(i);
                } else {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
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


    }

    private void soundset() {
        String snd = sps.getString(Missing_Words.this, "snd");
        c_settings = findViewById(R.id.c_settings);
        if (snd.equals("off")) {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            // toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;
        } else if (snd.equals("on")) {
            //  toggleButton.setBackgroundResource(R.drawable.on);
            c_settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;
        }
    }

    private void download_datas() {
        Cursor cz = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("questionid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Missing_Words.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    public void downloaddata_regular() {

        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Missing_Words.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {
            //DownLoad Letters and Words

            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                download_datas();
            } else {

                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Missing_Words.this);                           /* .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog12, which) -> {
                    // continue with delete

                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Missing_Words.this, "goto_sett", 1);


                    dialog12.dismiss();
                }).setNegativeButton("பின்னர்", (dialog1, which) -> {
                    // do nothing
                    sps.putString(Missing_Words.this, "game_area", "on");

                    String date = sps.getString(Missing_Words.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
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
            sps.putString(Missing_Words.this, "game_area", "on");
            String date = sps.getString(Missing_Words.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            } else {
                finish();
                Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                startActivity(i);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
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

    private void dialog_dicription() {
        openDialog_odd_man = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_odd_man.setContentView(R.layout.answer_discription_quiz);
        word1 = openDialog_odd_man.findViewById(R.id.word1);
        word2 = openDialog_odd_man.findViewById(R.id.word2);
        word3 = openDialog_odd_man.findViewById(R.id.word3);
        word4 = openDialog_odd_man.findViewById(R.id.word4);
        word5 = openDialog_odd_man.findViewById(R.id.word5);
        word6 = openDialog_odd_man.findViewById(R.id.word6);
        ans = openDialog_odd_man.findViewById(R.id.ans);
        dis = openDialog_odd_man.findViewById(R.id.discription);
        close = openDialog_odd_man.findViewById(R.id.close);
        TextView questions = openDialog_odd_man.findViewById(R.id.questions);
        TextView diss = openDialog_odd_man.findViewById(R.id.dis);
        TextView answer = openDialog_odd_man.findViewById(R.id.answer);


        //  word1.setVisibility(View.GONE);
        word2.setVisibility(View.GONE);
        word3.setVisibility(View.GONE);
        word4.setVisibility(View.GONE);
        word5.setVisibility(View.GONE);
        word6.setVisibility(View.GONE);
        diss.setVisibility(View.GONE);
        dis.setVisibility(View.GONE);
        close.setOnClickListener(view -> openDialog_odd_man.dismiss());

        openDialog_odd_man.setOnCancelListener(dialog -> {
            // dialog dismiss without button press
            // load_addcontent2(context,ads_layout);


        });
        Cursor cs;
        cs = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + question_id + "'");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            String question = cs.getString(cs.getColumnIndexOrThrow("question"));
            String answera = cs.getString(cs.getColumnIndexOrThrow("answer"));

            questions.setText("கேள்வி");
            answer.setText("பதில்");
            word1.setText("" + question);
            ans.setText(answera);


        }
        if (sps.getInt(Missing_Words.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            //  load_addcontent(context, frame_layout2);
        }

        openDialog_odd_man.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Missing_Words.this)) {
                String date = sps.getString(Missing_Words.this, "date");
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

                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Missing_Words.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்").setPositiveButton("அமைப்பு", (dialog, which) -> {
                    // continue with delete
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Missing_Words.this, "goto_sett", 1);
                    dialog.dismiss();
                }).setNegativeButton("பின்னர்", (dialog, which) -> {
                    // do nothing

                    String date = sps.getString(Missing_Words.this, "date");
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

                if (sps.getString(Missing_Words.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Missing_Words.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Missing_Words.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Missing_Words.this, "complite_reg").equals("yes")) {
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
        final Dialog openDialog = new Dialog(Missing_Words.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

        if (!isFinishing()) openDialog.show();
    }

    public void settingpermission() {
        if (sps.getInt(Missing_Words.this, "permission") == 2) {
            AlertDialog alertDialog = new AlertDialog.Builder(Missing_Words.this).create();
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
                sps.putString(Missing_Words.this, "game_area", "on");
                String date = sps.getString(Missing_Words.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                } else {
                    finish();
                    Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                    startActivity(i);
                }
                dialog.dismiss();
            });


            alertDialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 150) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Missing_Words.this, "permission", 1);
                //  downloaddata_daily();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Missing_Words.this, "permission", 2);
                        finish();
                        Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                        startActivity(i);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Missing_Words.this, "permission", 0);
                        finish();
                        Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }
            }

        }
        if (requestCode == 151) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Missing_Words.this, "permission", 1);
                downloaddata_regular();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Missing_Words.this, "permission", 2);
                        finish();
                        Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                        startActivity(i);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Missing_Words.this, "permission", 0);
                        finish();
                        Intent i = new Intent(Missing_Words.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }
            }
        }

        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Missing_Words.this, "permission", 1);
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
                        sps.putInt(Missing_Words.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Missing_Words.this, "permission", 0);
                    }
                }
            }

        }
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
                Toast.makeText(Missing_Words.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
            }
            fb_reward = 0;

        };

        if (rewardedAd != null) {
            rewardedAd.show(this, success);
            reward_status = 1;
        } else {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
        }
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
            //Toast.makeText(this, ""+sps.getInt(this, "Game3_Stage_Close_ST"), Toast.LENGTH_SHORT).show();
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
