package nithra.tamil.word.game.solliadi.match_tha_fallows;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.RequiresApi;
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

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import nithra.tamil.word.game.solliadi.Clue_Game_Hard;
import nithra.tamil.word.game.solliadi.Commen_string;
import nithra.tamil.word.game.solliadi.DataBaseHelper;
import nithra.tamil.word.game.solliadi.Download_completed;
import nithra.tamil.word.game.solliadi.Download_data_server;
import nithra.tamil.word.game.solliadi.Expandable_List_View;
import nithra.tamil.word.game.solliadi.Fill_in_blanks;
import nithra.tamil.word.game.solliadi.Find_difference_between_pictures;
import nithra.tamil.word.game.solliadi.Find_words_from_picture;
import nithra.tamil.word.game.solliadi.Jamble_word_game;
import nithra.tamil.word.game.solliadi.LoginActivity;
import nithra.tamil.word.game.solliadi.Makeword_Rightorder;
import nithra.tamil.word.game.solliadi.Match_Word;
import nithra.tamil.word.game.solliadi.Missing_Words;
import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper2;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper3;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper4;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper5;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper6;
import nithra.tamil.word.game.solliadi.Odd_man_out;
import nithra.tamil.word.game.solliadi.Opposite_word;
import nithra.tamil.word.game.solliadi.Ote_to_Tamil;
import nithra.tamil.word.game.solliadi.Picture_Game_Hard;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.Quiz_Game;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.Riddle_game;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Solukul_Sol;
import nithra.tamil.word.game.solliadi.Tirukural;
import nithra.tamil.word.game.solliadi.Utills;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.WordError_correction;
import nithra.tamil.word.game.solliadi.Word_Game_Hard;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;


public class Match_tha_fallows_game extends AppCompatActivity implements View.OnClickListener, Download_completed {

    //SQLiteDatabase database;

    static final int mCoinCount = 20;
    static int rvo = 0;
    final SharedPreference sp = new SharedPreference();
    final ArrayList<MyData> data_list = new ArrayList<>();
    final ArrayList<String> maintain_ans = new ArrayList<>();
    final ArrayList<Integer> find_qus_list = new ArrayList<>();
    final ArrayList<Integer> find_ans_list = new ArrayList<>();
    final int gameid = 15;
    final SharedPreference sps = new SharedPreference();
    int fb_reward = 0;
    int reward_status = 0;
    //reward videos process 1***********************
    TextView qus_txt1, qus_txt2, qus_txt3, qus_txt4, qus_txt5, qus_txt6, qus_txt7, qus_txt8, qus_txt9, qus_txt10, ans_txt1, ans_txt2, ans_txt3, ans_txt4, ans_txt5, ans_txt6, ans_txt7, ans_txt8, ans_txt9, ans_txt10, ans_num_txt1, ans_num_txt2, ans_num_txt3, ans_num_txt4, ans_num_txt5, ans_num_txt6, ans_num_txt7, ans_num_txt8, ans_num_txt9, ans_num_txt10, qus_num_txt1, qus_num_txt2, qus_num_txt3, qus_num_txt4, qus_num_txt5, qus_num_txt6, qus_num_txt7, qus_num_txt8, qus_num_txt9, qus_num_txt10;
    ImageView show_all_ans_img;
    ArrowLayout arrow_layout;
    TextView nodata_txt, moveans_txt, FROMVIEW, TOVIEW, QUSNUMVIEW;
    ScrollView qus_scrool, ans_scrool;
    int FROM_POS = 0, TO_POS = 0;
    Boolean scroll_act = false, hint_act = false, arrow_move;
    Toast toastMessage;
    Newgame_DataBaseHelper5 newhelper5;
    String question = "";
    String sf_word = "";
    String Answer = "";
    Chronometer focus;
    TextView question_txt, score;
    DataBaseHelper myDbHelper;
    Dialog openDialog_s;
    LinearLayout ads_layout_bottom;
    TextView next_continue;
    TextView ttscores;
    TextView tx1, tx2, p_facebook, p_settings, p_watts_app, p_coins_red, p_coins;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    Typeface tyr;
    int dia_dismiss = 0;
    int spxdr = 0;
    int answer_types = 0;
    String questionid = "";
    SoundPool click, win, coin, worng, cr_ans, spz4;
    int soundId1, soundId2, soundId3, soundId4, soundId5;
    int sv = 0;
    int rdvalu;
    int answerlength;
    int case2 = 0, tot2 = 30, tt_case2, tt_tot2;
    int y;
    int ea = 0;
    int setval_vid;
    TextView coin_value;
    Long ttstop;
    Dialog openDialog_p;
    int share_name = 0;
    RelativeLayout head;
    LinearLayout qwt, ads_lay;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    int e2;
    TextView c_coin, p_coins_2;
    boolean flip_boolean, flip_animcancel;
    Handler vali_handler = null;
    Handler handler;
    Runnable my_runnable;
    private MaxRewardedAd rewardedAd;
    private MaxInterstitialAd mInterstitialAd;

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    public void btn_dissable() {
        qus_txt1.setEnabled(false);
        qus_txt2.setEnabled(false);
        qus_txt3.setEnabled(false);
        qus_txt4.setEnabled(false);
        qus_txt5.setEnabled(false);

        qus_num_txt1.setEnabled(false);
        qus_num_txt2.setEnabled(false);
        qus_num_txt3.setEnabled(false);
        qus_num_txt4.setEnabled(false);
        qus_num_txt5.setEnabled(false);

        ans_txt1.setEnabled(false);
        ans_txt2.setEnabled(false);
        ans_txt3.setEnabled(false);
        ans_txt4.setEnabled(false);
        ans_txt5.setEnabled(false);

    }

    public void btn_enable() {
        qus_txt1.setEnabled(true);
        qus_txt2.setEnabled(true);
        qus_txt3.setEnabled(true);
        qus_txt4.setEnabled(true);
        qus_txt5.setEnabled(true);

        qus_num_txt1.setEnabled(true);
        qus_num_txt2.setEnabled(true);
        qus_num_txt3.setEnabled(true);
        qus_num_txt4.setEnabled(true);
        qus_num_txt5.setEnabled(true);

        ans_txt1.setEnabled(true);
        ans_txt2.setEnabled(true);
        ans_txt3.setEnabled(true);
        ans_txt4.setEnabled(true);
        ans_txt5.setEnabled(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.match_the_fallows);
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);
        myDbHelper = new DataBaseHelper(Match_tha_fallows_game.this);
        newhelper5 = new Newgame_DataBaseHelper5(Match_tha_fallows_game.this);
        newhelper = new Newgame_DataBaseHelper(Match_tha_fallows_game.this);
        newhelper2 = new Newgame_DataBaseHelper2(Match_tha_fallows_game.this);
        newhelper3 = new Newgame_DataBaseHelper3(Match_tha_fallows_game.this);
        newhelper4 = new Newgame_DataBaseHelper4(Match_tha_fallows_game.this);

        qus_scrool = findViewById(R.id.qus_scrool);
        ans_scrool = findViewById(R.id.ans_scrool);


        if (sps.getString(Match_tha_fallows_game.this, "new_user_db").equals("")) {

        } else {
            if (sps.getString(Match_tha_fallows_game.this, "new_user_db").equals("on")) {
                sps.putString(Match_tha_fallows_game.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(Match_tha_fallows_game.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        //New_Main_Activity.fb_addload(Match_tha_fallows_game.this);
        arrow_layout = findViewById(R.id.arrow_layout);
        show_all_ans_img = findViewById(R.id.show_all_ans_img);
        nodata_txt = findViewById(R.id.nodata_txt);
        moveans_txt = findViewById(R.id.moveans_txt);
        qus_txt1 = findViewById(R.id.qus_txt1);
        qus_txt2 = findViewById(R.id.qus_txt2);
        qus_txt3 = findViewById(R.id.qus_txt3);
        qus_txt4 = findViewById(R.id.qus_txt4);
        qus_txt5 = findViewById(R.id.qus_txt5);
        qus_txt6 = findViewById(R.id.qus_txt6);
        qus_txt7 = findViewById(R.id.qus_txt7);
        qus_txt8 = findViewById(R.id.qus_txt8);
        qus_txt9 = findViewById(R.id.qus_txt9);
        qus_txt10 = findViewById(R.id.qus_txt10);
        p_coins_red = findViewById(R.id.qus_txt10);
        p_coins = findViewById(R.id.p_coins);

        ans_txt1 = findViewById(R.id.ans_txt1);
        ans_txt2 = findViewById(R.id.ans_txt2);
        ans_txt3 = findViewById(R.id.ans_txt3);
        ans_txt4 = findViewById(R.id.ans_txt4);
        ans_txt5 = findViewById(R.id.ans_txt5);
        ans_txt6 = findViewById(R.id.ans_txt6);
        ans_txt7 = findViewById(R.id.ans_txt7);
        ans_txt8 = findViewById(R.id.ans_txt8);
        ans_txt9 = findViewById(R.id.ans_txt9);
        ans_txt10 = findViewById(R.id.ans_txt10);

        ans_num_txt1 = findViewById(R.id.ans_num_txt1);
        ans_num_txt2 = findViewById(R.id.ans_num_txt2);
        ans_num_txt3 = findViewById(R.id.ans_num_txt3);
        ans_num_txt4 = findViewById(R.id.ans_num_txt4);
        ans_num_txt5 = findViewById(R.id.ans_num_txt5);
        ans_num_txt6 = findViewById(R.id.ans_num_txt6);
        ans_num_txt7 = findViewById(R.id.ans_num_txt7);
        ans_num_txt8 = findViewById(R.id.ans_num_txt8);
        ans_num_txt9 = findViewById(R.id.ans_num_txt9);
        ans_num_txt10 = findViewById(R.id.ans_num_txt10);

        qus_num_txt1 = findViewById(R.id.qus_num_txt1);
        qus_num_txt2 = findViewById(R.id.qus_num_txt2);
        qus_num_txt3 = findViewById(R.id.qus_num_txt3);
        qus_num_txt4 = findViewById(R.id.qus_num_txt4);
        qus_num_txt5 = findViewById(R.id.qus_num_txt5);
        qus_num_txt6 = findViewById(R.id.qus_num_txt6);
        qus_num_txt7 = findViewById(R.id.qus_num_txt7);
        qus_num_txt8 = findViewById(R.id.qus_num_txt8);
        qus_num_txt9 = findViewById(R.id.qus_num_txt9);
        qus_num_txt10 = findViewById(R.id.qus_num_txt10);
        p_facebook = findViewById(R.id.p_facebook);
        p_watts_app = findViewById(R.id.p_watts_app);

        focus = findViewById(R.id.p_time_edit);
        question_txt = findViewById(R.id.questionid);
        score = findViewById(R.id.p_score_edit);
        p_settings = findViewById(R.id.p_settings);
        head = findViewById(R.id.head);
        qwt = findViewById(R.id.qwt);
        ads_lay = findViewById(R.id.ads_lay);

        c_coin = findViewById(R.id.c_coins);
        p_coins_2 = findViewById(R.id.p_coins_2);

        show_all_ans_img.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt1.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt2.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt3.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt4.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt5.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt6.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt7.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt8.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt9.setOnClickListener(Match_tha_fallows_game.this);
        qus_txt10.setOnClickListener(Match_tha_fallows_game.this);

        qus_num_txt1.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt2.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt3.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt4.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt5.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt6.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt7.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt8.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt9.setOnClickListener(Match_tha_fallows_game.this);
        qus_num_txt10.setOnClickListener(Match_tha_fallows_game.this);

        ans_txt1.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt2.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt3.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt4.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt5.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt6.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt7.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt8.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt9.setOnClickListener(Match_tha_fallows_game.this);
        ans_txt10.setOnClickListener(Match_tha_fallows_game.this);
        p_settings.setOnClickListener(Match_tha_fallows_game.this);
        p_facebook.setOnClickListener(Match_tha_fallows_game.this);
        p_watts_app.setOnClickListener(Match_tha_fallows_game.this);
        qwt.setOnClickListener(Match_tha_fallows_game.this);


        Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        if (sps.getInt(Match_tha_fallows_game.this, "purchase_ads") == 0) {
            industrialload();
        }

        ImageView prize_logo = findViewById(R.id.prize_logo);


        if (sps.getInt(Match_tha_fallows_game.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    if (sps.getString(Match_tha_fallows_game.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Match_tha_fallows_game.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Match_tha_fallows_game.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Match_tha_fallows_game.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Match_tha_fallows_game.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Match_tha_fallows_game.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });

        soundset();
        arrow_layout = findViewById(R.id.arrow_layout);

        openDialog_s = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen);
        ads_layout_bottom = openDialog_s.findViewById(R.id.fl_adplaceholder);

        //loadRewardedVideoAd();
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");

        //Sound Pool Sounds

        if (sps.getString(Match_tha_fallows_game.this, "mf_intro").equals("")) {
            showcase_dismiss();
            qus_num_txt1.setBackgroundResource(R.drawable.yellow_question);
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Match_tha_fallows_game.this, "sequence example pnm");
            sequence.setConfig(config);
            sequence.addSequenceItem(show_all_ans_img, "பொருந்திய வினாக்களை பார்க்க இந்த பொத்தானை அழுத்தி  காணலாம்.", "அடுத்து");
            sequence.addSequenceItem(qus_txt1, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");
            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Match_tha_fallows_game.this).setTarget(p_facebook).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                @Override
                public void onDismiss(MaterialShowcaseView itemView, int position) {

                    if (position == 2) {
                        qus_num_txt1.setBackgroundResource(R.drawable.circle_shap);
                        sps.putString(Match_tha_fallows_game.this, "mf_time_start", "yes");
                        sps.putString(Match_tha_fallows_game.this, "showcase_dismiss_mf", "yes");
                        focus.setBase(SystemClock.elapsedRealtime());
                        focus.start();
                    }
                }
            });

            sps.putString(Match_tha_fallows_game.this, "mf_intro", "no");
            sequence.start();

        }


        if (sps.getInt(Match_tha_fallows_game.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(Match_tha_fallows_game.this);
            /**/
        }

        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Match_tha_fallows_game.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Match_tha_fallows_game.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Match_tha_fallows_game.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Match_tha_fallows_game.this, R.raw.coins, 1);
        cr_ans = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId5 = cr_ans.load(Match_tha_fallows_game.this, R.raw.cr_ans, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Match_tha_fallows_game.this, R.raw.coins, 1);

        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = 0;
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        }
        score.setText("" + skx);
        //reward(Match_tha_fallows_game.this);

        next();


    }

    public Animation zoomAnim() {

        ScaleAnimation animation = new ScaleAnimation((float) 0.9, (float) 0.83, (float) 0.9, (float) 0.83, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(500);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }

    public void anim_move(String ans_value) {
        moveans_txt.setText(ans_value);

        moveans_txt.setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(0, show_all_ans_img.getX() - moveans_txt.getX(), 0, show_all_ans_img.getY() - moveans_txt.getY());
        // animation.setRepeatMode(0);
        animation.setDuration(2000);
        // animation.setFillAfter(true);
        moveans_txt.startAnimation(animation);

        moveans_txt.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveans_txt.setVisibility(View.INVISIBLE);
            }
        }, animation.getDuration());

    }

    public void next() {
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = 0;
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        }
        score.setText("" + skx);

        head.setVisibility(View.VISIBLE);
        String date = sps.getString(Match_tha_fallows_game.this, "date");
        // myDbHelper.executeSql("DELETE FROM answertable");
        if (date.equals("0")) {
            Cursor c1 = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            question_txt.setText(no/*+"/"+c1.getCount()*/);
        } else {
            String tfoption = date;
            String[] first = tfoption.split("-");
            question_txt.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            question_txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }


        Cursor cursor;
        data_list.clear();
        maintain_ans.clear();
        find_qus_list.clear();
        find_ans_list.clear();

        qus_scrool.post(new Runnable() {
            @Override
            public void run() {
                qus_scrool.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        ans_scrool.post(new Runnable() {
            @Override
            public void run() {
                ans_scrool.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        Cursor d = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish=1");
        System.out.println("#######################cursor.getCount()" + d.getCount());

        cursor = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish=0 order by id limit 1");
        cursor.moveToFirst();

        if (cursor.getCount() != 0) {
            arrow_layout.setVisibility(View.VISIBLE);
            nodata_txt.setVisibility(View.GONE);


            questionid = cursor.getString(cursor.getColumnIndexOrThrow("questionid")).trim();
            question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
            sf_word = cursor.getString(cursor.getColumnIndexOrThrow("sf_word"));
            Answer = cursor.getString(cursor.getColumnIndexOrThrow("answer"));
            String my_maintain = cursor.getString(cursor.getColumnIndexOrThrow("my_maintain"));

            if (my_maintain.equals("0")) {
                sp.putString(Match_tha_fallows_game.this, "mt_hint", "");
                sp.putInt(Match_tha_fallows_game.this, "mt_hint_count", 0);
                sp.putString(Match_tha_fallows_game.this, "all_corret", "");
            }

            String[] split_qus = question.split(",");
            String[] split_suf = sf_word.split(",");
            String[] split_ans = Answer.split(",");
            for (int i = 0; i < 5; i++) {
                MyData myData = new MyData(split_qus[i], split_suf[i], split_ans[i]);

                data_list.add(myData);
            }
            int playtime = cursor.getInt(cursor.getColumnIndexOrThrow("playtime"));
            if (playtime == 0) {
                if (sps.getString(Match_tha_fallows_game.this, "resume_mtf").equals("")) {
                    sps.putString(Match_tha_fallows_game.this, "resume_mtf", "yes");
                } else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }
            }

            set_value_fun(my_maintain);


        } else {
            downloaddata_regular();
            //nextgamesdialog();
        }


    }

    public void set_value_fun(String my_maintain) {
        qus_txt1.setText(data_list.get(0).get_qus());
        qus_txt2.setText(data_list.get(1).get_qus());
        qus_txt3.setText(data_list.get(2).get_qus());
        qus_txt4.setText(data_list.get(3).get_qus());
        qus_txt5.setText(data_list.get(4).get_qus());

        ans_txt1.setText(data_list.get(0).get_Suf());
        ans_txt2.setText(data_list.get(1).get_Suf());
        ans_txt3.setText(data_list.get(2).get_Suf());
        ans_txt4.setText(data_list.get(3).get_Suf());
        ans_txt5.setText(data_list.get(4).get_Suf());


        qus_txt1.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt2.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt3.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt4.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt5.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt6.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt7.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt8.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt9.setBackgroundResource(R.drawable.selectun_rect);
        qus_txt10.setBackgroundResource(R.drawable.selectun_rect);

        ans_txt1.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt2.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt3.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt4.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt5.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt6.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt7.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt8.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt9.setBackgroundResource(R.drawable.selectun_rect);
        ans_txt10.setBackgroundResource(R.drawable.selectun_rect);


        ans_num_txt1.setVisibility(View.GONE);
        ans_num_txt2.setVisibility(View.GONE);
        ans_num_txt3.setVisibility(View.GONE);
        ans_num_txt4.setVisibility(View.GONE);
        ans_num_txt5.setVisibility(View.GONE);
        ans_num_txt6.setVisibility(View.GONE);
        ans_num_txt7.setVisibility(View.GONE);
        ans_num_txt8.setVisibility(View.GONE);
        ans_num_txt9.setVisibility(View.GONE);
        ans_num_txt10.setVisibility(View.GONE);

        if (my_maintain != null && !my_maintain.equals("") && !my_maintain.equals("0")) {
            String[] split_my_maintain = my_maintain.split(",");

            for (int i = 0; i < split_my_maintain.length; i++) {
                String[] split_get_maintain = split_my_maintain[i].split("_");

                TO_POS = Integer.parseInt(split_get_maintain[0].trim());
                FROM_POS = Integer.parseInt(split_get_maintain[1].trim());

                maintain_ans.add("" + TO_POS + "_" + FROM_POS);
                find_qus_list.add(FROM_POS);
                find_ans_list.add(TO_POS);

                show_ans_num(TO_POS, FROM_POS, "");

            }
        }


        qus_scrool.startAnimation(AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.button2_animation));
        ans_scrool.startAnimation(AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.button1and3_animation));


    }

    private void flipIt(final View viewToFlip) {
        ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 0f, 360f);
        flip.setDuration(3000);
        flip.start();

    }

    @SuppressLint("WrongConstant")
    private void flipItY(final View viewToFlip) {
        ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationY", 0f, 360f);
        flip.setDuration(3000);
        flip.setRepeatMode(ValueAnimator.INFINITE);
        flip.start();

    }

    private void rotateStart(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        // set animator instance as view's tag
        view.setTag(animator);
        animator.setDuration(800);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }

    private void rotateCancel(View view) {
        // get view's tag, which is its animator instance
        ObjectAnimator animator = (ObjectAnimator) view.getTag();
        if (animator != null) {
            animator.cancel();
        }
    }

    public void flip_anim(final TextView view, final boolean b, final boolean animcancel) {

        flip_animcancel = animcancel;

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);

        oa1.setDuration(1000);
        oa2.setDuration(1000);

        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (b) {
                    QUSNUMVIEW.setBackgroundResource(R.drawable.circle_shap);
                    flip_boolean = false;
                } else {
                    QUSNUMVIEW.setBackgroundResource(R.drawable.yellow_question);
                    flip_boolean = true;
                }

                Handler handler = new Handler(Looper.myLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (animcancel) {
                            flip_anim(QUSNUMVIEW, flip_boolean, flip_animcancel);
                        }

                    }
                }, 1500);

                oa2.start();
            }
        });
        oa1.start();
    }

    public void myflip(TextView textView) {
        textView.startAnimation(AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.rotate_out));

    }

    @Override
    public void onClick(View v) {
        scroll_act = false;
        switch (v.getId()) {
            case R.id.show_all_ans_img: {
                show_Allans_fun();

                // custom_toast("மன்னிக்கவும் ! தற்போது நிலையில் எந்தவொரு சொல்லும் பொருத்தவில்லை", "normal");
            }
            break;
            case R.id.qus_txt1: {
                qus_bef_check();
                FROMVIEW = qus_txt1;
                FROM_POS = 1;
                QUSNUMVIEW = qus_num_txt1;
                qus_click_action();
            }
            break;
            case R.id.qus_txt2: {
                qus_bef_check();
                FROMVIEW = qus_txt2;
                FROM_POS = 2;
                QUSNUMVIEW = qus_num_txt2;
                qus_click_action();


            }
            break;
            case R.id.qus_txt3: {
                qus_bef_check();
                FROMVIEW = qus_txt3;
                FROM_POS = 3;
                QUSNUMVIEW = qus_num_txt3;
                qus_click_action();

            }
            break;
            case R.id.qus_txt4: {
                qus_bef_check();
                FROMVIEW = qus_txt4;
                FROM_POS = 4;
                QUSNUMVIEW = qus_num_txt4;
                qus_click_action();


            }
            break;
            case R.id.qus_txt5: {
                qus_bef_check();
                FROMVIEW = qus_txt5;
                FROM_POS = 5;
                QUSNUMVIEW = qus_num_txt5;
                qus_click_action();


            }
            break;
            case R.id.qus_txt6: {
                qus_bef_check();
                FROMVIEW = qus_txt6;
                FROM_POS = 6;
                QUSNUMVIEW = qus_num_txt6;
                qus_click_action();
            }
            break;
            case R.id.qus_txt7: {
                qus_bef_check();
                FROMVIEW = qus_txt7;
                FROM_POS = 7;
                QUSNUMVIEW = qus_num_txt7;
                qus_click_action();
            }
            break;
            case R.id.qus_txt8: {
                qus_bef_check();
                FROMVIEW = qus_txt8;
                FROM_POS = 8;
                QUSNUMVIEW = qus_num_txt8;
                qus_click_action();
            }
            break;
            case R.id.qus_txt9: {
                qus_bef_check();
                FROMVIEW = qus_txt9;
                FROM_POS = 9;
                QUSNUMVIEW = qus_num_txt9;
                qus_click_action();
            }
            break;
            case R.id.qus_txt10: {
                qus_bef_check();
                FROMVIEW = qus_txt10;
                FROM_POS = 10;
                QUSNUMVIEW = qus_num_txt10;
                qus_click_action();
            }
            break;

            case R.id.ans_txt1: {
                ans_bef_check();

                TOVIEW = ans_txt1;
                TO_POS = 1;
                ans_click_action();


            }
            break;
            case R.id.ans_txt2: {
                ans_bef_check();

                TOVIEW = ans_txt2;
                TO_POS = 2;
                ans_click_action();

            }
            break;
            case R.id.ans_txt3: {
                ans_bef_check();

                TOVIEW = ans_txt3;
                TO_POS = 3;
                ans_click_action();

            }
            break;
            case R.id.ans_txt4: {
                ans_bef_check();
                TOVIEW = ans_txt4;
                TO_POS = 4;
                ans_click_action();

            }
            break;
            case R.id.ans_txt5: {
                ans_bef_check();
                TOVIEW = ans_txt5;
                TO_POS = 5;
                ans_click_action();
            }
            break;
            case R.id.ans_txt6: {
                TOVIEW = ans_txt6;
                TO_POS = 6;
                // validate();
            }
            break;
            case R.id.ans_txt7: {
                TOVIEW = ans_txt7;
                TO_POS = 7;
                // validate();
            }
            break;
            case R.id.ans_txt8: {
                TOVIEW = ans_txt8;
                TO_POS = 8;
                // validate();

            }
            break;
            case R.id.ans_txt9: {
                TOVIEW = ans_txt9;
                TO_POS = 9;
                // validate();
            }
            break;
            case R.id.ans_txt10: {
                TOVIEW = ans_txt10;
                TO_POS = 10;
                //validate();
            }
            break;
            case R.id.p_settings: {
                p_settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(Match_tha_fallows_game.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(Match_tha_fallows_game.this, "snd", "on");
                    p_settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(Match_tha_fallows_game.this, "snd", "off");
                    p_settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
            break;
            case R.id.p_facebook: {
                share_name = 1;
                final String a = "com.facebook.katana";
                permission(a);
            }
            break;
            case R.id.p_watts_app: {
                share_name = 2;
                final String a = "com.whatsapp";
                permission(a);
            }
            break;
            case R.id.qwt: {
                dialog(0);
            }
            break;

            case R.id.qus_num_txt1: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt2: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt3: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt4: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt5: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt6: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt7: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt8: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt9: {
                hint_fun();
            }
            break;
            case R.id.qus_num_txt10: {
                hint_fun();
            }
            break;
        }
    }

    public void qus_bef_check() {

        hint_act = false;

        System.out.println("-----check qus_click_action qus_bef_check : " + vali_handler);

        // if (vali_handler == null) {

        if (FROMVIEW != null) {

            // if (QUSNUMVIEW != null && QUSNUMVIEW.getAnimation() != null) {
            QUSNUMVIEW.setText("" + FROM_POS);
            QUSNUMVIEW.setBackgroundResource(R.drawable.circle_shap);
            // }

            FROMVIEW.setBackgroundResource(R.drawable.selectun_rect);

            if (FROMVIEW.getAnimation() != null) {
                FROMVIEW.getAnimation().cancel();
                FROMVIEW.clearAnimation();
                // zoomAnim().setAnimationListener(null);
            }

            if (find_qus_list.contains(FROM_POS)) {
                FROMVIEW.setBackgroundResource(R.drawable.correct_ans_rect);
                if (FROM_POS != 0 && TO_POS != 0) {
                    show_ans_num(TO_POS, FROM_POS, "qus_bef_check");

                    arrow_layout.animateArrows(100, ans_txt1, ans_txt1, false, false, arrow_move);
                }
            }

        }
        //}

    }

    public void qus_click_action() {

        System.out.println("-----check qus_click_action qus_bef_check : " + vali_handler);

        // if (vali_handler == null) {

        if (!find_qus_list.contains(FROM_POS)) {
            FROMVIEW.startAnimation(zoomAnim());
            FROMVIEW.setBackgroundResource(R.drawable.select_rect);
            //arrow_layout.animateArrows(100, FROMVIEW, FROMVIEW, false, false,arrow_move);


            QUSNUMVIEW.setBackgroundResource(R.drawable.yellow_question);
            //  QUSNUMVIEW.startAnimation(AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.blink));
            QUSNUMVIEW.setText("");

            if (TOVIEW == null) {
                arrow_move = false;
            }

            validate();

        } else {

            System.out.println("-----check qus_click_action : ");
            FROMVIEW = null;

            custom_toast("சரியாக சொல்லைப் பொருத்தி விட்டீர்கள் மேலும் விளையாடுங்கள்", "normal");
        }
        // }
    }

    public void ans_bef_check() {
        //if (vali_handler == null) {

        if (TOVIEW != null) {
            TOVIEW.setBackgroundResource(R.drawable.selectun_rect);

            if (TOVIEW.getAnimation() != null) {
                TOVIEW.getAnimation().cancel();
                TOVIEW.clearAnimation();
                // zoomAnim().setAnimationListener(null);
            }

            if (find_ans_list.contains(TO_POS)) {
                TOVIEW.setBackgroundResource(R.drawable.correct_ans_rect);
                if (FROM_POS != 0 && TO_POS != 0) {
                    show_ans_num(TO_POS, FROM_POS, "ans_bef_check");
                    arrow_layout.animateArrows(100, ans_txt1, ans_txt1, false, false, arrow_move);
                }
            }


        }
        // }
    }

    public void ans_click_action() {
        // if (vali_handler == null) {
        if (!find_ans_list.contains(TO_POS)) {
            TOVIEW.startAnimation(zoomAnim());
            TOVIEW.setBackgroundResource(R.drawable.select_rect);
            //arrow_layout.animateArrows(100, TOVIEW, TOVIEW, false, false);
            if (FROMVIEW == null) {
                arrow_move = true;
            }
            validate();
        } else {
            // FROMVIEW = null;
            TOVIEW = null;
            // QUSNUMVIEW = null;
            custom_toast("சரியான சொல்லைப் பொருத்தி விட்டீர்கள் மேலும் விளையாடுங்கள்", "normal");
        }
        // }

    }

    public void validate() {

        Cursor cf = myDbHelper.getQry("SELECT * FROM score ");
        cf.moveToFirst();
        int sk = cf.getInt(cf.getColumnIndexOrThrow("coins"));
        if (sk > 50) {
            if (vali_handler != null) {
                vali_handler.removeCallbacksAndMessages(null);
            }

            if (FROMVIEW != null && TOVIEW != null) {

                btn_dissable();
                if (TOVIEW.getText().toString().equals(data_list.get(FROM_POS - 1).get_ans())) {

                    // if (!find_qus_list.contains(FROM_POS)) {
                    scroll_act = true;

                    arrow_layout.animateArrows(1000, FROMVIEW, TOVIEW, true, true, arrow_move);

                    vali_handler = new Handler(Looper.myLooper());
                    vali_handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Animation zoom_in_m = AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.zoom_in_m);

                            System.out.println("-----check FROMVIEW : " + FROMVIEW);
                            System.out.println("-----check TOVIEW : " + TOVIEW);
                            if (FROMVIEW != null) {
                                FROMVIEW.setAnimation(zoom_in_m);
                            }

                            btn_enable();
                            TOVIEW.setAnimation(zoom_in_m);

                            arrow_layout.animateArrows(0, FROMVIEW, TOVIEW, true, true, arrow_move);

                            //anim_move(FROMVIEW.getText().toString() + " = " + TOVIEW.getText().toString());
                            show_ans_num(TO_POS, FROM_POS, "validate");


                        }
                    }, 800);

                    if (!hint_act) {
                        coinanim();

                    }


                    maintain_ans.add("" + TO_POS + "_" + FROM_POS);
                    find_qus_list.add(FROM_POS);
                    find_ans_list.add(TO_POS);

                    String my_maintain = TextUtils.join(", ", maintain_ans);

                    newhelper5.executeSql("update newgames5 set my_maintain='" + my_maintain + "' where questionid='" + questionid + "' and gameid='" + gameid + "'");

                    System.out.println("##########################tts" + sp.getInt(Match_tha_fallows_game.this, "mt_hint_count"));

                    if (maintain_ans.size() == 5) {
                        System.out.println("##########################tts" + sp.getInt(Match_tha_fallows_game.this, "mt_hint_count"));
                        if (sp.getInt(Match_tha_fallows_game.this, "mt_hint_count") < 5) {
                            price_update();
                        }
                        newhelper5.executeSql("update newgames5 set isfinish='" + 1 + "' where questionid='" + questionid + "' and gameid='" + gameid + "'");
                        System.out.println("----gg  update newgames5 set isfinish='" + 1 + "' where questionid='" + questionid + "' and gameid='" + gameid + "'");
                        vali_handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //winning_fun();
                                adShow();
                            }
                        }, 2100);


                    }
                    System.out.println("--------hhh my_maintain :" + my_maintain);

                } else {

                    if (!hint_act) {
                        scroll_act = true;
                        arrow_layout.animateArrows(1000, FROMVIEW, TOVIEW, true, false, arrow_move);

                        vali_handler = new Handler(Looper.myLooper());
                        vali_handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Animation shake = AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.shake);
                                FROMVIEW.setAnimation(shake);
                                TOVIEW.setAnimation(shake);

                                arrow_layout.animateArrows(0, FROMVIEW, TOVIEW, true, false, arrow_move);
                                btn_enable();

                                custom_toast("தவறான பதில் மீண்டும் முயற்சிக்கவும்", "custom_toast");
                                sp.putString(Match_tha_fallows_game.this, "all_corret", "all_corret");

                                FROMVIEW.setBackgroundResource(R.drawable.selectun_rect);
                                TOVIEW.setBackgroundResource(R.drawable.selectun_rect);
                                FROMVIEW = null;
                                TOVIEW = null;
                                FROM_POS = 0;
                                TO_POS = 0;

                            }
                        }, 800);

                        coinanim_reds();


                    } else {
                        custom_toast("முதலில் வினா சொல்லை தேர்வு செய்யுங்கள்", "normal");
                    }

                }

                if (FROMVIEW != null && FROMVIEW.getAnimation() != null) {
                    FROMVIEW.getAnimation().cancel();
                    FROMVIEW.clearAnimation();
                }
                if (TOVIEW != null && TOVIEW.getAnimation() != null) {
                    TOVIEW.getAnimation().cancel();
                    TOVIEW.clearAnimation();
                }

                // if (QUSNUMVIEW != null && QUSNUMVIEW.getAnimation() != null) {
                if (QUSNUMVIEW != null) {
                    QUSNUMVIEW.setText("" + FROM_POS);
                    QUSNUMVIEW.setBackgroundResource(R.drawable.circle_shap);
                }

                Handler handler = new Handler(Looper.myLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // if (!hint_act) {
                        arrow_layout.animateArrows(100, ans_txt1, ans_txt1, false, false, arrow_move);

                        // }
                    }
                }, 2000);


            }
        } else {
            dialog(1);
        }


    }


    public void show_ans_num(int view_ans, int set_ans, String commm) {

        System.out.println("-------vvvv commm---- : " + commm);
        System.out.println("-------vvvv view_ans : " + view_ans);
        System.out.println("-------vvvv set_ans : " + set_ans);


        if (view_ans == 1 && view_ans != 0) {
            ans_num_txt1.setText("" + set_ans);
            ans_num_txt1.setVisibility(View.VISIBLE);
            ans_txt1.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 2 && view_ans != 0) {
            ans_num_txt2.setText("" + set_ans);
            ans_num_txt2.setVisibility(View.VISIBLE);
            ans_txt2.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 3 && view_ans != 0) {
            ans_num_txt3.setText("" + set_ans);
            ans_num_txt3.setVisibility(View.VISIBLE);
            ans_txt3.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 4 && view_ans != 0) {
            ans_num_txt4.setText("" + set_ans);
            ans_num_txt4.setVisibility(View.VISIBLE);
            ans_txt4.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 5 && view_ans != 0) {
            ans_num_txt5.setText("" + set_ans);
            ans_num_txt5.setVisibility(View.VISIBLE);
            ans_txt5.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 6) {
            ans_num_txt6.setText("" + set_ans);
            ans_num_txt6.setVisibility(View.VISIBLE);
            ans_txt6.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 7) {
            ans_num_txt7.setText("" + set_ans);
            ans_num_txt7.setVisibility(View.VISIBLE);
            ans_txt7.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 8) {
            ans_num_txt8.setText("" + set_ans);
            ans_num_txt8.setVisibility(View.VISIBLE);
            ans_txt8.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 9) {
            ans_num_txt9.setText("" + set_ans);
            ans_num_txt9.setVisibility(View.VISIBLE);
            ans_txt9.setBackgroundResource(R.drawable.correct_ans_rect);

        } else if (view_ans == 10) {
            ans_num_txt10.setText("" + set_ans);
            ans_num_txt10.setVisibility(View.VISIBLE);
            ans_txt10.setBackgroundResource(R.drawable.correct_ans_rect);

        }

        if (set_ans == 1) {
            qus_txt1.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 2) {
            qus_txt2.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 3) {
            qus_txt3.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 4) {
            qus_txt4.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 5) {
            qus_txt5.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 6) {
            qus_txt6.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 7) {
            qus_txt7.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 8) {
            qus_txt8.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 9) {
            qus_txt9.setBackgroundResource(R.drawable.correct_ans_rect);
        } else if (set_ans == 10) {
            qus_txt10.setBackgroundResource(R.drawable.correct_ans_rect);
        }

        FROMVIEW = null;
        TOVIEW = null;
        FROM_POS = 0;
        TO_POS = 0;

        System.out.println("-----check show_ans_num() vali_handler : " + vali_handler);

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

    //reward videos***********************//

    public void custom_toast(String validation, String type) {

        if (toastMessage != null) {
            toastMessage.cancel();
        }

        if (type.equals("normal")) {
            toastMessage = Toast.makeText(this, validation, Toast.LENGTH_SHORT);
            toastMessage.show();
        } else {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout, findViewById(R.id.toast_layout_root));


            RelativeLayout content_lay = layout.findViewById(R.id.content_lay);
            ImageView image = layout.findViewById(R.id.image);
            image.setImageResource(R.drawable.wrong_icon);
            TextView text = layout.findViewById(R.id.text);
            text.setText(validation);

            content_lay.startAnimation(AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.left_right));


            toastMessage = new Toast(getApplicationContext());
            toastMessage.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toastMessage.setDuration(Toast.LENGTH_SHORT);
            toastMessage.setView(layout);
            toastMessage.show();
        }


    }

    public void hint_fun() {
        if (FROMVIEW != null) {
            Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
            cfw.moveToFirst();
            int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
            if (sk >= 50) {
                if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                    int hint_count = sp.getInt(Match_tha_fallows_game.this, "mt_hint_count");
                    hint_count = hint_count + 1;
                    sp.putInt(Match_tha_fallows_game.this, "mt_hint_count", hint_count);
                    String ans_value = data_list.get(FROM_POS - 1).get_ans();

                    for (int i = 0; i < data_list.size(); i++) {
                        if (ans_value.equals(data_list.get(i).get_Suf())) {
                            TO_POS = i + 1;
                            break;
                        }
                    }
                    if (TO_POS == 1) {
                        TOVIEW = ans_txt1;
                    } else if (TO_POS == 2) {
                        TOVIEW = ans_txt2;
                    } else if (TO_POS == 3) {
                        TOVIEW = ans_txt3;
                    } else if (TO_POS == 4) {
                        TOVIEW = ans_txt4;
                    } else if (TO_POS == 5) {
                        TOVIEW = ans_txt5;
                    } else if (TO_POS == 6) {
                        TOVIEW = ans_txt6;
                    } else if (TO_POS == 7) {
                        TOVIEW = ans_txt7;
                    } else if (TO_POS == 8) {
                        TOVIEW = ans_txt8;
                    } else if (TO_POS == 9) {
                        TOVIEW = ans_txt9;
                    } else if (TO_POS == 10) {
                        TOVIEW = ans_txt10;
                    }
                    hint_act = true;
                    sp.putString(Match_tha_fallows_game.this, "mt_hint", "yes");
                    validate();
                    scroll_act = false;
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx - 50;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                } else {
                    final Dialog openDialog = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog.setContentView(R.layout.show_ans);
                    TextView yes = openDialog.findViewById(R.id.yes);
                    TextView no = openDialog.findViewById(R.id.no);
                    TextView txt_ex2 = openDialog.findViewById(R.id.txt_ex2);
                    txt_ex2.setText("மொத்த நாணயங்களில் 50 குறைக்கப்படும்");
                    CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
                    checkbox_ans.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if (isChecked) {
                                sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                            } else {
                                sps.putString(getApplicationContext(), "checkbox_ans", "");
                            }
                        }
                    });

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            int hint_count = sp.getInt(Match_tha_fallows_game.this, "mt_hint_count");
                            hint_count = hint_count + 1;
                            sp.putInt(Match_tha_fallows_game.this, "mt_hint_count", hint_count);
                            openDialog.dismiss();

                            String ans_value = data_list.get(FROM_POS - 1).get_ans();

                            for (int i = 0; i < data_list.size(); i++) {
                                if (ans_value.equals(data_list.get(i).get_Suf())) {
                                    TO_POS = i + 1;
                                    break;
                                }
                            }
                            if (TO_POS == 1) {
                                TOVIEW = ans_txt1;
                            } else if (TO_POS == 2) {
                                TOVIEW = ans_txt2;
                            } else if (TO_POS == 3) {
                                TOVIEW = ans_txt3;
                            } else if (TO_POS == 4) {
                                TOVIEW = ans_txt4;
                            } else if (TO_POS == 5) {
                                TOVIEW = ans_txt5;
                            } else if (TO_POS == 6) {
                                TOVIEW = ans_txt6;
                            } else if (TO_POS == 7) {
                                TOVIEW = ans_txt7;
                            } else if (TO_POS == 8) {
                                TOVIEW = ans_txt8;
                            } else if (TO_POS == 9) {
                                TOVIEW = ans_txt9;
                            } else if (TO_POS == 10) {
                                TOVIEW = ans_txt10;
                            }
                            hint_act = true;
                            sp.putString(Match_tha_fallows_game.this, "mt_hint", "yes");
                            validate();

                            scroll_act = false;
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 50;
                            String aStringx = Integer.toString(spx);
                            score.setText(aStringx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openDialog.dismiss();
                        }
                    });
                    if (!isFinishing()) openDialog.show();
                }


            } else {
                dialog(1);
            }


        }


    }

    public void winning_fun() {
        final Dialog dialog = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.winning_dia);
        dialog.setCancelable(false);
        dialog.show();
        ImageView image = dialog.findViewById(R.id.image);
        TextView hint_text = dialog.findViewById(R.id.hint_text);
        TextView no_need_txt = dialog.findViewById(R.id.no_need_txt);
        TextView yes_need_txt = dialog.findViewById(R.id.yes_need_txt);
        hint_text.setText("சரியாக அனைத்து சொல்களையும் பொருத்தி வெற்றி பெற்றுவிட்டீர்கள் மேலும் விளையாடுங்கள்");


        no_need_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        yes_need_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                next();

            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                arrow_layout.animateArrows(1000, qus_txt1, qus_txt1, false, false, arrow_move);
                FROMVIEW = null;
                TOVIEW = null;
                FROM_POS = 0;
                TO_POS = 0;
                hint_act = false;
                scroll_act = false;
            }
        });
    }

    public void show_Allans_fun() {
        final Dialog dialog = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.show_allans_dia);
        dialog.show();

        TextView exit_text = dialog.findViewById(R.id.exit_text);
        exit_text.startAnimation(zoomAnim());
        exit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ListView all_ans_list = dialog.findViewById(R.id.all_ans_list);
        ans_adapter ans_adapter = new ans_adapter();
        all_ans_list.setAdapter(ans_adapter);

    }

    public void setSc() {

        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        int f_sec = sec + sec2 + seconds;


        TextView arputham = openDialog_s.findViewById(R.id.arputham);
        TextView extracoin = openDialog_s.findViewById(R.id.extracoin);
        next_continue = openDialog_s.findViewById(R.id.continues);

        ttscores = openDialog_s.findViewById(R.id.tts_score);
        final TextView bsscores = openDialog_s.findViewById(R.id.bs_score);
        final TextView dumy = openDialog_s.findViewById(R.id.bs_score_dum);
        final TextView cns1 = openDialog_s.findViewById(R.id.cnse1);
        final TextView cns2 = openDialog_s.findViewById(R.id.cnse2);
        final TextView cns3 = openDialog_s.findViewById(R.id.cnse3);
        final TextView cns4 = openDialog_s.findViewById(R.id.cnse4);
        final TextView cns5 = openDialog_s.findViewById(R.id.cnse5);
        tx2 = openDialog_s.findViewById(R.id.tt2);
        final TextView wtp = openDialog_s.findViewById(R.id.wtp);
        final TextView fbs = openDialog_s.findViewById(R.id.fbp);
        final TextView gplus = openDialog_s.findViewById(R.id.gplus);
        final LinearLayout rewardvideo = openDialog_s.findViewById(R.id.rewardvideo);
        final LinearLayout vid_earn = openDialog_s.findViewById(R.id.vid_earn);
        final TextView definition = openDialog_s.findViewById(R.id.definition);

        TextView video_earn = openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Match_tha_fallows_game.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");

        ImageView prize_logo = openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Match_tha_fallows_game.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    if (sps.getString(Match_tha_fallows_game.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Match_tha_fallows_game.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Match_tha_fallows_game.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Match_tha_fallows_game.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Match_tha_fallows_game.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Match_tha_fallows_game.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Match_tha_fallows_game.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);


        if (sps.getInt(Match_tha_fallows_game.this, "purchase_ads") == 1) {
            ads_layout_bottom.setVisibility(View.GONE);
        } else {
            // New_Main_Activity.load_addFromMain_multiplayer(Match_tha_fallows_game.this, ads_layout_bottom);
            if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Match_tha_fallows_game.this, ads_layout_bottom);
            } else {
                ads_layout_bottom.setVisibility(View.GONE);
            }
        }


        if (sps.getString(Match_tha_fallows_game.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Match_tha_fallows_game.this, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }


        next_continue.setVisibility(View.INVISIBLE);


        RelativeLayout adsicon = openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);

        // final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);
        definition.setVisibility(View.VISIBLE);
        definition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_Allans_fun();
            }
        });
        vid_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Match_tha_fallows_game.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        show_reward();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    show_reward();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(Match_tha_fallows_game.this);
                                    rewarded_adnew();
                                    Toast.makeText(Match_tha_fallows_game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Match_tha_fallows_game.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        show_reward();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    show_reward();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(Match_tha_fallows_game.this);
                                    rewarded_adnew();
                                    Toast.makeText(Match_tha_fallows_game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");
                        String msg = ("நான் சொல்லிஅடி செயலியில் பொருத்துக நிலை " + question_txt.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
            }
        });
        fbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");

                        String msg = ("நான் சொல்லிஅடி செயலியில் பொருத்துக நிலை" + question_txt.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
        System.out.println("#############################" + " SELECT * FROM answertable where useranswer='1'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        int uans = 0;
        Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where useranswer='0'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        cd.moveToFirst();
        if (cd.getCount() != 0) {
            uans = cd.getCount();
        }

        // Toast.makeText(Match_tha_fallows_game.this, "u_ans"+uans, Toast.LENGTH_SHORT).show();
        // Toast.makeText(Match_    tha_fallows_game.this, "answerlength"+answerlength, Toast.LENGTH_SHORT).show();

        System.out.println("########################uans" + uans);
        System.out.println("########################answerlength" + answerlength);
        System.out.println("#############################mt_hint" + sp.getString(Match_tha_fallows_game.this, "mt_hint"));

        int tt_time = 120;

        if (sp.getString(Match_tha_fallows_game.this, "mt_hint").equals("") & f_sec <= tt_time) {
            //Condition 2
            if (sp.getString(Match_tha_fallows_game.this, "all_corret").equals("")) {
                y = 0;
                arputham.setTypeface(tyr);
                arputham.setText("ÜŸ¹î‹");
                extracoin.setTypeface(tyr);
                extracoin.setText("Ã´î™ ï£íòƒèœ");
                tx2.setTypeface(tyr);
                tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
                next_continue.setTypeface(tyr);
                next_continue.setText("ªî£ì˜è");

                String date = sps.getString(Match_tha_fallows_game.this, "date");
                if (!date.equals("0")) {
                    next_continue.setText("சரி");
                }
                Handler handler1 = new Handler(Looper.myLooper());
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        coin.play(soundId4, sv, sv, 0, 0, sv);
                        //play1.start();
                        cns3.setVisibility(View.VISIBLE);
                    }
                }, 500);
                Handler handler2 = new Handler(Looper.myLooper());
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // play2.start();
                        coin.play(soundId4, sv, sv, 0, 0, sv);
                        cns1.setVisibility(View.VISIBLE);
                    }
                }, 1000);
                Handler handler3 = new Handler(Looper.myLooper());
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //  play3.start();
                        coin.play(soundId4, sv, sv, 0, 0, sv);
                        cns2.setVisibility(View.VISIBLE);
                    }
                }, 1500);


                Handler handler6 = new Handler(Looper.myLooper());
                handler6.postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
                        cns3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cns3.setVisibility(View.INVISIBLE);
                            }
                        }, transAnimation.getDuration());

                    }
                }, 1500);
                Handler handler7 = new Handler(Looper.myLooper());
                handler7.postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
                        cns1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cns1.setVisibility(View.INVISIBLE);
                            }
                        }, transAnimation.getDuration());

                    }
                }, 1900);
                Handler handler8 = new Handler(Looper.myLooper());
                handler8.postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
                        cns2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cns2.setVisibility(View.INVISIBLE);
                            }
                        }, transAnimation.getDuration());

                    }
                }, 2300);

                case2 = 0;
                tot2 = 30;
                new Thread(new Runnable() {

                    public void run() {
                        while (case2 < tot2) {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            bsscores.post(new Runnable() {

                                public void run() {
                                    bsscores.setText("" + case2);

                                }

                            });
                            case2++;
                        }

                    }

                }).start();


                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    tt_tot2 = tt_case2 + 30;
                    String aStringx = Integer.toString(tt_case2);
                    ttscores.setText(aStringx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + tt_tot2 + "'");
                }


                Handler handler11 = new Handler(Looper.myLooper());
                handler11.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        new Thread(new Runnable() {

                            public void run() {

                                while (tt_case2 < tt_tot2) {
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    ttscores.post(new Runnable() {

                                        public void run() {
                                            ttscores.setText("" + tt_case2);

                                        }

                                    });
                                    tt_case2++;
                                }

                            }

                        }).start();
                    }
                }, 1500);

                Handler hand = new Handler(Looper.myLooper());
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        next_continue.setVisibility(View.VISIBLE);
                    }
                }, 2500);

                next_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        y = 0;
                        case2 = 0;
                        tot2 = 0;
                        tt_case2 = 0;
                        tt_tot2 = 0;
                        dia_dismiss = 1;
                        openDialog_s.dismiss();
                        next();

                        // advancads_content();


                    }
                });
            } else {
                next_continue.setVisibility(View.VISIBLE);

                y = 0;

                arputham.setTypeface(tyr);
                arputham.setText("Iè ÜŸ¹î‹");
                extracoin.setTypeface(tyr);
                extracoin.setText("Ã´î™ ï£íòƒèœ");
                tx2.setTypeface(tyr);
                tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
                next_continue.setTypeface(tyr);
                next_continue.setText("ªî£ì˜è");
                arputham.setTypeface(tyr);
                arputham.setText("ï¡Á");
                extracoin.setTypeface(tyr);
                extracoin.setText("Ã´î™ ï£íòƒèœ");
                tx2.setTypeface(tyr);
                tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
                next_continue.setTypeface(tyr);

                next_continue.setText("ªî£ì˜è");
                String date = sps.getString(Match_tha_fallows_game.this, "date");
                if (!date.equals("0")) {
                    next_continue.setText("சரி");
                }
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    tt_tot2 = tt_case2;
                    String aStringx = Integer.toString(tt_case2);
                    ttscores.setText(aStringx);
                }

                bsscores.setText("0");
                //Score Adding


                next_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        y = 0;
                        case2 = 0;
                        tot2 = 0;
                        tt_case2 = 0;
                        tt_tot2 = 0;


                        dia_dismiss = 1;
                        openDialog_s.dismiss();
                        next();

                    }
                });
            }

        } else {


            next_continue.setVisibility(View.VISIBLE);

            y = 0;

            arputham.setTypeface(tyr);
            arputham.setText("Iè ÜŸ¹î‹");
            extracoin.setTypeface(tyr);
            extracoin.setText("Ã´î™ ï£íòƒèœ");
            tx2.setTypeface(tyr);
            tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
            next_continue.setTypeface(tyr);
            next_continue.setText("ªî£ì˜è");
            arputham.setTypeface(tyr);
            arputham.setText("ï¡Á");
            extracoin.setTypeface(tyr);
            extracoin.setText("Ã´î™ ï£íòƒèœ");
            tx2.setTypeface(tyr);
            tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
            next_continue.setTypeface(tyr);

            next_continue.setText("ªî£ì˜è");
            String date = sps.getString(Match_tha_fallows_game.this, "date");
            if (!date.equals("0")) {
                next_continue.setText("சரி");
            }
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                tt_tot2 = tt_case2;
                String aStringx = Integer.toString(tt_case2);
                ttscores.setText(aStringx);
            }

            bsscores.setText("0");
            //Score Adding


            next_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    y = 0;
                    case2 = 0;
                    tot2 = 0;
                    tt_case2 = 0;
                    tt_tot2 = 0;
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();
                }
            });
        }

        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss != 1) {
                    sps.putString(Match_tha_fallows_game.this, "game_area", "on");
                    tot2 = 0;
                    tt_tot2 = 0;
                    case2 = 0;
                    y = 0;
                    tt_case2 = 0;


                    String date = sps.getString(Match_tha_fallows_game.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                            openDialog_s.dismiss();

                        }
                    } else {
                        if (sps.getString(Match_tha_fallows_game.this, "Exp_list").equals("on")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Match_tha_fallows_game.this, Expandable_List_View.class);
                            startActivity(i);

                        } else {
                            if (main_act.equals("")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
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

    public void share_earn2(int a) {
        int skx = 0;
        final Dialog openDialog = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog.findViewById(R.id.ok_y);
        TextView b_scores = openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            b_scores.setText("" + a);


        }


        final int finalSkx = skx;
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttscores.setText("" + finalSkx);
                score.setText("" + finalSkx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
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
            final Dialog openDialog = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = openDialog.findViewById(R.id.ok_y);
            TextView b_scores = openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);


            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                spxdr = skx;

            }


            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score.setText("" + spxdr);
                    openDialog.dismiss();
                    //mCoinCount = 0;
                }
            });

            openDialog.show();
        }

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
                    Toast.makeText(Match_tha_fallows_game.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
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
            mInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Ragasiya_sorgal_ins), Match_tha_fallows_game.this);
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
                mInterstitialAd.showAd("Ragasiya sorgal ins");
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game4_Stage_Close_RS", (sps.getInt(getApplicationContext(), "Game4_Stage_Close_RS") + 1));
            if (sps.getInt(Match_tha_fallows_game.this, "Game4_Stage_Close_RS") > Utills.interstitialadCount)
                sps.putInt(Match_tha_fallows_game.this, "Game4_Stage_Close_RS", 0);

            setSc();
            //Toast.makeText(this, ""+sps.getInt(this, "Game4_Stage_Close_RS"), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.stop();

        String date = sps.getString(Match_tha_fallows_game.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        if (sps.getString(Match_tha_fallows_game.this, "resume_mf").equals("")) {
            sps.putString(Match_tha_fallows_game.this, "resume_mf", "yes");

        } else {
            String date = sps.getString(Match_tha_fallows_game.this, "date");
            int pos;
            if (date.equals("0")) {
            } else {
            }
            Cursor cs = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + questionid + "'");
            cs.moveToFirst();
            long dscore = 0;
            if (cs.getCount() != 0) {
                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

        if (sps.getInt(Match_tha_fallows_game.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            ads_lay.setVisibility(View.GONE);
        } else {
        }
    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sp.getString(Match_tha_fallows_game.this, "showcase_dismiss_mf").equals("")) {
                    showcase_dismiss();
                } else {
                    qus_num_txt1.setBackgroundResource(R.drawable.circle_shap);
                    sps.putString(Match_tha_fallows_game.this, "mf_time_start", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }

            }
        }, 800);
    }
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            back();
        }
    };


    private void back() {

        openDialog_p = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        TextView yes = openDialog_p.findViewById(R.id.yes);
        TextView no = openDialog_p.findViewById(R.id.no);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sps.putString(Match_tha_fallows_game.this, "game_area", "on");

                focus.stop();
                ttstop = focus.getBase() - SystemClock.elapsedRealtime();


                String date = sps.getString(Match_tha_fallows_game.this, "date");
                int pos;
                if (date.equals("0")) {
                } else {
                }

                newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                // String date = sps.getString(Match_tha_fallows_game.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                } else {
                    if (sps.getString(Match_tha_fallows_game.this, "Exp_list").equals("on")) {
                        finish();
                        Intent i = new Intent(Match_tha_fallows_game.this, Expandable_List_View.class);
                        startActivity(i);
                    } else {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    }

                }


                openDialog_p.dismiss();


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

    private void soundset() {
        String snd = sps.getString(Match_tha_fallows_game.this, "snd");
        p_settings = findViewById(R.id.p_settings);
        if (snd.equals("off")) {
            p_settings.setBackgroundResource(R.drawable.sound_off);
            // toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;
        } else if (snd.equals("on")) {
            //  toggleButton.setBackgroundResource(R.drawable.on);
            p_settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;
        }
    }

    public void permission(final String a) {
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.stop();

        String date = sps.getString(Match_tha_fallows_game.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        }
        helpshare(a);
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

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Match_tha_fallows_game.this, Match_tha_fallows_game.this.getPackageName(), file);
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

        final Dialog openDialog_earncoin = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = openDialog_earncoin.findViewById(R.id.ssss);

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

        TextView wpro = openDialog_earncoin.findViewById(R.id.wpro);
        if (i == 1) {
            cancel.setVisibility(View.INVISIBLE);
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }

        RelativeLayout video = openDialog_earncoin.findViewById(R.id.earnvideo);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 1;
                extra_coin_s = 0;
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Match_tha_fallows_game.this, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();

                        String date = sps.getString(Match_tha_fallows_game.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        } else {
                            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        }
                        reward_progressBar.dismiss();
                        show_reward();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        //reward(Match_tha_fallows_game.this);
                        rewarded_adnew();
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                Toast.makeText(Match_tha_fallows_game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        wp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        openDialog_earncoin.cancel();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");
                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 12);
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

            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {

                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Match_tha_fallows_game.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                        } else {
                            pos = 2;
                        }
                        newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and rd='" + pos + "'");

                        openDialog_earncoin.cancel();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");
                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
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

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);

        TextView exit = openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Match_tha_fallows_game.this, "date");
        if (date.equals("0")) {
            next_game.setText("பொருத்துக தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("பொருத்துக புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");
        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Clue_Game_Hard.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
                startActivity(i);
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

        TextView odd_man_out = openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Odd_man_out.class);
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


        TextView opposite_word = openDialog.findViewById(R.id.opposite_word);
        TextView ote_to_tamil = openDialog.findViewById(R.id.ote_to_tamil);
        opposite_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Ote_to_Tamil.class);
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


        seerpaduthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Riddle_game.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, WordError_correction.class);
                startActivity(i);
            }
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

        fill_in_blanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });


        TextView quiz = openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(Match_tha_fallows_game.this);
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
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Match_Word.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Find_words_from_picture.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Quiz_Game.class);
                startActivity(i);
            }
        });

        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Match_tha_fallows_game.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);
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
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Jamble_word_game.class);
                startActivity(i);
            }
        });
        TextView missing_words = openDialog.findViewById(R.id.missing_words);
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
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Missing_Words.class);
                startActivity(i);
            }
        });
        TextView six_differences = openDialog.findViewById(R.id.six_differences);
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
                sps.putString(Match_tha_fallows_game.this, "date", "0");
                Intent i = new Intent(Match_tha_fallows_game.this, Find_difference_between_pictures.class);
                startActivity(i);
            }
        });
        if (!isFinishing()) openDialog.show();

        openDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (main_act.equals("")) {
                    finish();
                    //openDialog_s.dismiss();
                    Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Match_tha_fallows_game.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                sps.putString(Match_tha_fallows_game.this, "date", "0");


                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
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
        long f_sec = sec + sec2 + seconds;
        //
        System.out.println("##############################all_corret" + sp.getString(Match_tha_fallows_game.this, "all_corret"));
        System.out.println("##############################mt_hint" + sp.getString(Match_tha_fallows_game.this, "mt_hint"));
        String date = sps.getString(Match_tha_fallows_game.this, "date");
        if (date.equals("0")) {
            if (sp.getString(Match_tha_fallows_game.this, "all_corret").equals("")) {
                if (timeElapsed <= 91300) {
                    if (sp.getString(Match_tha_fallows_game.this, "mt_hint").equals("")) {
                        prize_data_update(Match_tha_fallows_game.this, 75);
                    } else {
                        prize_data_update(Match_tha_fallows_game.this, 25);
                    }
                } else if (timeElapsed > 91300) {
                    if (sp.getString(Match_tha_fallows_game.this, "mt_hint").equals("")) {
                        prize_data_update(Match_tha_fallows_game.this, 50);
                    } else {
                        prize_data_update(Match_tha_fallows_game.this, 25);
                    }
                } else {
                    prize_data_update(Match_tha_fallows_game.this, 25);
                }
            } else {
                prize_data_update(Match_tha_fallows_game.this, 25);
            }

        } else {
            if (sp.getString(Match_tha_fallows_game.this, "all_corret").equals("")) {
                if (timeElapsed <= 91300) {
                    if (sp.getString(Match_tha_fallows_game.this, "mt_hint").equals("")) {
                        prize_data_update(Match_tha_fallows_game.this, 100);
                    } else {
                        prize_data_update(Match_tha_fallows_game.this, 25);
                    }
                } else if (timeElapsed > 91300) {
                    if (sp.getString(Match_tha_fallows_game.this, "mt_hint").equals("")) {
                        prize_data_update(Match_tha_fallows_game.this, 75);
                    } else {
                        prize_data_update(Match_tha_fallows_game.this, 25);
                    }
                } else {
                    prize_data_update(Match_tha_fallows_game.this, 25);
                }
            } else {
                prize_data_update(Match_tha_fallows_game.this, 25);
            }
        }
        ////////////////Prize//////////////////
    }

    public void coinanim() {
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
        transAnimation.setDuration(500);
        c_coin.startAnimation(transAnimation);
        c_coin.postDelayed(new Runnable() {
            @Override
            public void run() {
                c_coin.setVisibility(View.INVISIBLE);
            }
        }, transAnimation.getDuration());


        ////

        Handler handler = new Handler(Looper.myLooper());
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

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                score.startAnimation(levels1);
            }
        }, 1300);

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

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
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


            }
        }, 1500);
    }

    public void coinanim_reds() {
        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        score.setText(tr);
        //
        e2 = skq;
        coin.play(soundId4, sv, sv, 0, 0, sv);
        p_coins_2.setVisibility(View.VISIBLE);
        int[] locationInWindow = new int[2];
        p_coins_2.getLocationInWindow(locationInWindow);
        int[] locationOnScreen = new int[2];
        p_coins_2.getLocationOnScreen(locationOnScreen);
        float sourceX = locationOnScreen[0];
        float sourceY = locationOnScreen[1];
        int[] locationInWindowSecond = new int[2];
        c_coin.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        c_coin.getLocationOnScreen(locationOnScreenSecond);
        float destinationX = locationOnScreenSecond[0];
        float destinationY = locationOnScreenSecond[1];
        TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
        transAnimation.setDuration(500);
        p_coins_2.startAnimation(transAnimation);
        p_coins_2.postDelayed(new Runnable() {
            @Override
            public void run() {
                p_coins_2.setVisibility(View.INVISIBLE);
            }
        }, transAnimation.getDuration());


        ////

        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                coin.play(soundId4, sv, sv, 0, 0, sv);
                p_coins_2.setVisibility(View.VISIBLE);
                int[] locationInWindow = new int[2];
                p_coins_2.getLocationInWindow(locationInWindow);
                int[] locationOnScreen = new int[2];
                p_coins_2.getLocationOnScreen(locationOnScreen);
                float sourceX = locationOnScreen[0];
                float sourceY = locationOnScreen[1];
                int[] locationInWindowSecond = new int[2];
                c_coin.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                c_coin.getLocationOnScreen(locationOnScreenSecond);
                float destinationX = locationOnScreenSecond[0];
                float destinationY = locationOnScreenSecond[1];
                TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
                transAnimation.setDuration(1000);
                p_coins_2.startAnimation(transAnimation);
                p_coins_2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        p_coins_2.setVisibility(View.INVISIBLE);
                    }
                }, transAnimation.getDuration());
            }
        }, 1000);

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                score.startAnimation(levels1);
            }
        }, 1200);

        new Thread(new Runnable() {

            public void run() {
                int es = e2 - 20;
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

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Score Setting
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx - 20;
                String aStringx = Integer.toString(spx);
                score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

            }
        }, 1600);
    }

    public void downloaddata_regular() {

        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Match_tha_fallows_game.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                    download_datas();
                } else {
                    head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Match_tha_fallows_game.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete

                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                            sps.putInt(Match_tha_fallows_game.this, "goto_sett", 1);


                            dialog.dismiss();
                        }
                    }).setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            sps.putString(Match_tha_fallows_game.this, "game_area", "on");

                            String date = sps.getString(Match_tha_fallows_game.this, "date");
                            if (date.equals("0")) {
                                backexitnet();
                            } else {
                                backexitnet();
                            }
                            dialog.dismiss();
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
                }

            }
        });
        alertDialogBuilder.setPositiveButton("இல்லை ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                sps.putString(Match_tha_fallows_game.this, "game_area", "on");
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void download_datas() {
        Cursor cz = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("questionid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Match_tha_fallows_game.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Match_tha_fallows_game.this)) {
                download_datas();
            } else {

                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Match_tha_fallows_game.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்").setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        sps.putInt(Match_tha_fallows_game.this, "goto_sett", 1);
                        dialog.dismiss();
                    }
                }).setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String date = sps.getString(Match_tha_fallows_game.this, "date");
                        if (date.equals("0")) {
                            backexitnet();
                        } else {
                            backexitnet();
                        }
                        dialog.dismiss();
                    }
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
        final Dialog openDialog = new Dialog(Match_tha_fallows_game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });
        if (!isFinishing()) openDialog.show();
    }

    public void coinanim_red() {
////


        //score intial

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
        p_coins_red.postDelayed(new Runnable() {
            @Override
            public void run() {
                p_coins_red.setVisibility(View.INVISIBLE);
            }
        }, transAnimation.getDuration());


        ////

        Handler handler = new Handler(Looper.myLooper());
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
                int es = e2 - 20;
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

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                score.startAnimation(levels1);
            }
        }, 2200);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx - 20;
                String aStringx = Integer.toString(spx);
                score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                // setSc();
            }
        }, 1300);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 150) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Match_tha_fallows_game.this, "permission", 1);
                downloaddata_regular();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Match_tha_fallows_game.this, "permission", 2);
                        finish();
                        Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
                        startActivity(i);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Match_tha_fallows_game.this, "permission", 0);
                        finish();
                        Intent i = new Intent(Match_tha_fallows_game.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }
            }

        }

        if (requestCode == 152) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Match_tha_fallows_game.this, "permission", 1);
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
                        sps.putInt(Match_tha_fallows_game.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Match_tha_fallows_game.this, "permission", 0);
                    }
                }
            }
        }
    }

    public static class MyData {

        private final String split_qus;
        private final String suf;
        private final String split_ans;

        public MyData(String split_qus, String suf, String split_ans) {
            this.split_qus = split_qus;
            this.suf = suf;
            this.split_ans = split_ans;
        }

        public String get_qus() {
            return split_qus;
        }

        public String get_Suf() {
            return suf;
        }

        public String get_ans() {
            return split_ans;
        }
    }

    public class ans_adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data_list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.list_design, null);

            TextView allqus_txt = view.findViewById(R.id.allqus_txt);
            TextView allans_txt = view.findViewById(R.id.allans_txt);
            allqus_txt.setText(data_list.get(position).get_qus());

            System.out.println("------find_qus_list " + find_qus_list);

            if (find_qus_list.contains(position + 1)) {
                allans_txt.setText(data_list.get(position).get_ans());
            } else {
                allans_txt.setText("?");
            }


            return view;
        }
    }
}
