package nithra.tamil.word.game.solliadi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;








import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.example.games.basegameutils.BaseGameActivity;

import java.io.File;
import java.io.FileOutputStream;

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

public class Quiz_Game extends BaseGameActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,  Download_completed {
    TextView question_txt, c_ans, c_button1, c_button2, c_button3, c_button4, score, c_word_number;
    Chronometer focus;

    SharedPreference sps = new SharedPreference();
    String gameid = "17";
    String question_id = "", question = "", answer = "";
    int u_id = 0;
    String isdown = "0";
    String sf_word;
    DataBaseHelper myDbHelper;
    SoundPool spz1, spz2, spz3, spz4;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    long ttstop;
    Dialog openDialog_s, openDialog_p, openDialog_odd_man;
    RelativeLayout adsicon, adsicon2;
    CircleImageView ads_logo, ads_logo2;
    LinearLayout ads_layout;
    int s = 0;
    TextView next_continue;
    TextView ttscores;
    Typeface typ, tyr;
    static int vs = 0;
    static int rvo = 0;
    static int mCoinCount = 20;
    int f_sec;
    int r = 0;
    int dia_dismiss = 0;

    private MaxInterstitialAd ins_game,game_exit_ins;


    int extra_coin_s = 0;
    TextView s_word_number, s_score_edit, hint, p_coins, p_coins_red, earncoin, to_no, discription, c_settings, ch_facebook, ch_watts_app;
    int reward_play_count = 0;
    int ea = 0;
    int setval_vid;
    TextView coin_value;
    private GoogleApiClient mGoogleApiClient;
    RelativeLayout head;
    int share_name = 0;
    int e2;
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
    LinearLayout qwt;

    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    Newgame_DataBaseHelper5 newhelper5;
    TextView word1, word2, word3, word4, word5, word6, ans, dis, close;
    LinearLayout ads_lay;
    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        newhelper5 = new Newgame_DataBaseHelper5(this);
        myDbHelper = new DataBaseHelper(this);

        myDbHelper = new DataBaseHelper(Quiz_Game.this);
        newhelper = new Newgame_DataBaseHelper(Quiz_Game.this);
        newhelper2 = new Newgame_DataBaseHelper2(Quiz_Game.this);
        newhelper3 = new Newgame_DataBaseHelper3(Quiz_Game.this);
        newhelper4 = new Newgame_DataBaseHelper4(Quiz_Game.this);

        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Quiz_Game.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Quiz_Game.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Quiz_Game.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Quiz_Game.this, R.raw.coins, 1);///



        /*String gid = "17";
        String qid = "";
        for (int i = 0; i<=499; i++){
            if (qid.equals("")){
                qid = "" +i;
            } else {
                qid = qid + "," + i;
            }
        }
        System.out.println("---qid : " +qid);
        System.out.println("---qid : " + "UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='17'");
        newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='17'");
*/


        if (sps.getString(Quiz_Game.this, "new_user_db").equals("")) {

        } else {
            if (sps.getString(Quiz_Game.this, "new_user_db").equals("on")) {
                sps.putString(Quiz_Game.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(Quiz_Game.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        openDialog_s = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        ads_logo = (CircleImageView) openDialog_s.findViewById(R.id.ads_logo);
        adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);




        if (sps.getInt(Quiz_Game.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(Quiz_Game.this);
           /**/
        }


        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");


        //reward(Quiz_Game.this);
        rewarded_ad();
        if (sps.getInt(Quiz_Game.this, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(Quiz_Game.this).setMediationProvider("max");
            AppLovinSdk.initializeSdk(Quiz_Game.this, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    industrialload_game();
                    game_exit_ins_ad();

                }
            });
        }

        find();
        next();
        soundset();
       // loads_ads_banner();
        ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        if (sps.getInt(Quiz_Game.this, "purchase_ads") == 0) {
        if (Utils.isNetworkAvailable(Quiz_Game.this)) {

        Ad_NativieUtils.load_add_facebook(this,getResources().getString(R.string.Viliyodu_Vilaiyadu_Native_Banner_new),ads_lay);
        }
        else {
            ads_lay.setVisibility(View.GONE);
        }
        }else{
            ads_lay.setVisibility(View.GONE);
        }

        if (sps.getString(Quiz_Game.this, "qz_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view
            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Quiz_Game.this, "sequence example qz");
            sequence.setConfig(config);
            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");
            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Quiz_Game.this)
                    .setTarget(ch_facebook)
                    .setDismissText("சரி")
                    .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {
                            if (position == 1) {
                                sps.putString(Quiz_Game.this, "qz_time_start", "yes");
                                sps.putString(Quiz_Game.this, "showcase_dismiss_qz", "yes");
                                focus.setBase(SystemClock.elapsedRealtime());
                                focus.start();
                            }
                        }
                    });
            sps.putString(Quiz_Game.this, "qz_intro", "no");
            sequence.start();
        }

    }

    private void loads_ads_banner() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        if (sps.getInt(Quiz_Game.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            ads_lay.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);

        } else {
            if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                fb_native(Quiz_Game.this, native_banner_ad_container);
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }

    }

    private void find() {
        question_txt = (TextView) findViewById(R.id.question_txt);
        c_ans = (TextView) findViewById(R.id.c_ans);
        c_button1 = (TextView) findViewById(R.id.c_button1);
        c_button2 = (TextView) findViewById(R.id.c_button2);
        c_button3 = (TextView) findViewById(R.id.c_button3);
        c_button4 = (TextView) findViewById(R.id.c_button4);
        c_settings = (TextView) findViewById(R.id.c_settings);
        score = (TextView) findViewById(R.id.c_score_edit);
        p_coins = (TextView) findViewById(R.id.p_coins);
        c_word_number = (TextView) findViewById(R.id.c_word_number);
        ch_facebook = (TextView) findViewById(R.id.ch_facebook);
        ch_watts_app = (TextView) findViewById(R.id.ch_watts_app);
        focus = (Chronometer) findViewById(R.id.c_time_edit);
        head = (RelativeLayout) findViewById(R.id.head);
        qwt = (LinearLayout) findViewById(R.id.qwt);
        p_coins_red = (TextView) findViewById(R.id.p_coins_red);

        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = 0;
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        }
        score.setText("" + skx);
        c_button1.setOnClickListener(Quiz_Game.this);
        c_button2.setOnClickListener(Quiz_Game.this);
        c_button3.setOnClickListener(Quiz_Game.this);
        c_button4.setOnClickListener(Quiz_Game.this);
        ch_facebook.setOnClickListener(Quiz_Game.this);
        ch_watts_app.setOnClickListener(Quiz_Game.this);
        qwt.setOnClickListener(Quiz_Game.this);
        c_ans.setOnClickListener(Quiz_Game.this);
        c_settings.setOnClickListener(Quiz_Game.this);
        ImageView prize_logo = (ImageView) findViewById(R.id.prize_logo);

        if (sps.getInt(Quiz_Game.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                    if (sps.getString(Quiz_Game.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Quiz_Game.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Quiz_Game.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Quiz_Game.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Quiz_Game.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Quiz_Game.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void next() {
        reset();
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        if (sps.getInt(Quiz_Game.this, "purchase_ads") == 1) {
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        }else {
            if (Utils.isNetworkAvailable(Quiz_Game.this)){
                native_banner_ad_container.setVisibility(View.VISIBLE);
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

        }
        head.setVisibility(View.VISIBLE);
        String date = sps.getString(Quiz_Game.this, "date");
        // myDbHelper.executeSql("DELETE FROM answertable");
        if (date.equals("0")) {
            Cursor c1 = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='1'");
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
            c = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            c = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
            c.moveToFirst();
        }
        if (c.getCount() != 0) {
            u_id = c.getInt(c.getColumnIndexOrThrow("id"));
            question = c.getString(c.getColumnIndexOrThrow("question"));
            question_id = c.getString(c.getColumnIndexOrThrow("questionid"));
            answer = c.getString(c.getColumnIndexOrThrow("answer"));
            sf_word = c.getString(c.getColumnIndexOrThrow("sf_word"));
            isdown = c.getString(c.getColumnIndexOrThrow("isdown"));
            question_txt.setText(question);
            String tfoption = sf_word;
            String[] first = tfoption.split(",");
            int optlen = first.length;
            if (optlen == 4) {
                c_button1.setText("" + first[0]);
                c_button2.setText("" + first[1]);
                c_button3.setText("" + first[2]);
                c_button4.setText("" + first[3]);
            }

            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            if (playtime == 0) {
                if (sps.getString(Quiz_Game.this, "resume_qza").equals("")) {
                    sps.putString(Quiz_Game.this, "resume_qza", "yes");
                } else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }
            }
        } else {
            downloaddata_regular2();
            // nextgamesdialog();
        }
    }

    private void reset() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.c_button1: {
                verify(c_button1.getText().toString(), "b1");
            }
            break;
            case R.id.c_button2: {
                verify(c_button2.getText().toString(), "b2");

            }
            break;
            case R.id.c_button3: {
                verify(c_button3.getText().toString(), "b3");

            }
            break;
            case R.id.c_button4: {
                verify(c_button4.getText().toString(), "b4");
            }
            break;
            case R.id.ch_facebook: {
                share_name = 1;
                final String a = "com.facebook.katana";
                permission(a);
            }
            break;
            case R.id.ch_watts_app: {
                share_name = 1;
                final String a = "com.whatsapp";
                permission(a);
            }
            break;
            case R.id.c_ans: {
                user_verify();
            }
            break;
            case R.id.c_settings: {
                c_settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(Quiz_Game.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(Quiz_Game.this, "snd", "on");
                    c_settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(Quiz_Game.this, "snd", "off");
                    c_settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }

            }
            break;
            case R.id.qwt: {
                dialog(0);
            }
            break;
        }
    }

    private void user_verify() {
        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
        if (sk > 50) {
            if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                ans_checked();
            } else {
                final Dialog openDialog = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                TextView no = (TextView) openDialog.findViewById(R.id.no);
                TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);
                TextView txt_ex = (TextView) openDialog.findViewById(R.id.txt_ex);
                txt_ex.setText("விடைகளை பார்க்க வேண்டுமா ?");
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


                        ans_checked();

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

    private void ans_checked() {
        String date = sps.getString(Quiz_Game.this, "date");
        Cursor c;
        if (date.equals("0")) {
            c = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' order by id limit 1");
            c.moveToFirst();
        } else {
            c = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='0' and date='" + date + "'");
            c.moveToFirst();
        }
        if (c.getCount() != 0) {
            spz2.play(soundId2, sv, sv, 0, 0, sv);
            if (date.equals("0")) {
                newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
            } else {
                newhelper5.executeSql("UPDATE newgames5 SET daily='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
            }

            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx - 50;
            String aStringx = Integer.toString(spx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            score.setText(aStringx);

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
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setSc();
                }
            }, 2300);
        }
    }

    public void verify(String ans, String b_val) {
        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
        if (sk > 50) {
            String dates = sps.getString(Quiz_Game.this, "date");
            Cursor cs = null;
            if (dates.equals("0")) {
                cs = newhelper5.getQry("select * from newgames5 where answer LIKE'" + ans + "'and isfinish='0' and questionid=" + question_id + " and gameid=" + gameid + "");
            } else {
                cs = newhelper5.getQry("select * from newgames5 where answer LIKE'" + ans + "'and daily='0' and questionid=" + question_id + " and gameid=" + gameid + "");
            }
            cs.moveToFirst();
            if (cs.getCount() != 0) {
                spz3.play(soundId3, sv, sv, 0, 0, sv);
                String date = sps.getString(Quiz_Game.this, "date");

                if (date.equals("0")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                } else {
                    newhelper5.executeSql("UPDATE newgames5 SET daily='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                }
                if (b_val.equals("b1")) {
                    // change_red();
                    c_button1.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button1.setTextColor(getResources().getColor(R.color.white));
                } else if (b_val.equals("b2")) {
                    // change_red();
                    c_button2.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button2.setTextColor(getResources().getColor(R.color.white));
                } else if (b_val.equals("b3")) {
                    // change_red();
                    c_button3.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button3.setTextColor(getResources().getColor(R.color.white));
                } else if (b_val.equals("b4")) {
                    // change_red();
                    c_button4.setBackgroundResource(R.drawable.correct_ans_val);
                    c_button4.setTextColor(getResources().getColor(R.color.white));
                }
                c_button1.setPadding(20, 20, 20, 20);
                c_button2.setPadding(20, 20, 20, 20);
                c_button3.setPadding(20, 20, 20, 20);
                c_button4.setPadding(20, 20, 20, 20);
                ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                focus.stop();
                price_update();
                c_button1.setEnabled(false);
                c_button2.setEnabled(false);
                c_button3.setEnabled(false);
                c_button4.setEnabled(false);
                c_ans.setEnabled(false);
                coinanim();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSc();
                    }
                }, 2300);
            } else {

              /*  Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx - 50;
                String aStringx = Integer.toString(spx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                score.setText(aStringx);*/

                String date = sps.getString(Quiz_Game.this, "date");
                spz2.play(soundId2, sv, sv, 0, 0, sv);
                if (date.equals("0")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
                } else {
                    newhelper5.executeSql("UPDATE newgames5 SET daily='1' WHERE questionid='" + question_id + "'and gameid=" + gameid + "");
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
                if (b_val.equals("b1")) {
                    c_button1.setBackgroundResource(R.drawable.button3);
                } else if (b_val.equals("b2")) {
                    c_button2.setBackgroundResource(R.drawable.button3);
                } else if (b_val.equals("b3")) {
                    c_button3.setBackgroundResource(R.drawable.button3);
                } else if (b_val.equals("b4")) {
                    c_button4.setBackgroundResource(R.drawable.button3);
                }
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
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setSc();
                    }
                }, 2300);
            }
        } else {
            dialog(1);
        }

    }

    public void change_red() {
        c_button1.setBackgroundResource(R.drawable.button3);
        c_button2.setBackgroundResource(R.drawable.button3);
        c_button3.setBackgroundResource(R.drawable.button3);
        c_button4.setBackgroundResource(R.drawable.button3);
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
        video_earn.setText("மேலும் " + sps.getInt(Quiz_Game.this, "reward_coin_txt") + "+நாணயங்கள் பெற");

        ImageView prize_logo = (ImageView) openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Quiz_Game.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sps.getString(Quiz_Game.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Quiz_Game.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Quiz_Game.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Quiz_Game.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Quiz_Game.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Quiz_Game.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Quiz_Game.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(Quiz_Game.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Quiz_Game.this, ads_layout);
            } else {
                ads_layout.setVisibility(View.GONE);
            }

            //New_Main_Activity.load_addFromMain_multiplayer(Quiz_Game.this, ads_layout);
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
        discription.setText("கேள்விக்கான பதில்");
        discription.setVisibility(View.VISIBLE);

        next_continue.setVisibility(View.VISIBLE);
        word.setTypeface(tyr);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);
        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(Quiz_Game.this, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        String aStringx = Integer.toString(skx);
        ttscores.setText(aStringx);


        if (sps.getString(Quiz_Game.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Quiz_Game.this, "date");
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
                //
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
                if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Quiz_Game.this, "" + "Reward video", "Loading...");
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
                                    //reward(Quiz_Game.this);
                                    rewarded_ad();
                                    Toast.makeText(Quiz_Game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Quiz_Game.this, "" + "Reward video", "Loading...");
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
                                    //reward(Quiz_Game.this);
                                    rewarded_ad();
                                    Toast.makeText(Quiz_Game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (isNetworkAvailable()) {
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
                if (sps.getInt(Quiz_Game.this, "purchase_ads") == 1) {
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();
                }else {
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

                if (sps.getInt(Quiz_Game.this, "purchase_ads") == 1) {

                } else {
                    // advancads();
                    //advancads_content();
                }
               /* dia_dismiss = 1;
                openDialog_s.dismiss();*/
            }
        });
        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss != 1) {
                    sps.putString(Quiz_Game.this, "game_area", "on");
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                            openDialog_s.dismiss();
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //*********************reward videos process 3***********************










    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Quiz_Game.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }




    //reward videos***********************//



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
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                score.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
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
            final Dialog openDialog = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                    score.setText("" + skx);
                    openDialog.dismiss();
                    //mCoinCount = 0;
                }
            });
            openDialog.show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("#################OnStop");
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }


        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.stop();

        String date = sps.getString(Quiz_Game.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("#################OnResume");
        if (sps.getString(Quiz_Game.this, "resume_qz").equals("")) {
            sps.putString(Quiz_Game.this, "resume_qz", "yes");
        } else {
            String date = sps.getString(Quiz_Game.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
            } else {
                pos = 2;
            }
            Cursor cs = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + question_id + "'");
            cs.moveToFirst();
            long dscore = 0;
            if (cs.getCount() != 0) {
                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sps.getString(Quiz_Game.this, "showcase_dismiss_qz").equals("")) {
                    showcase_dismiss();
                } else {
                    sps.putString(Quiz_Game.this, "qz_time_start", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }

            }
        }, 800);
    }


    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        sps.putString(Quiz_Game.this, "game_area", "on");
            openDialog_p = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.back_pess);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    focus.stop();

                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


                        String date = sps.getString(Quiz_Game.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                        } else {
                            pos = 2;
                        }

                        newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");

                        // String date = sps.getString(Quiz_Game.this, "date");
                        if (date.equals("0")) {
                            if (main_act.equals("")) {
                                finish();
                                Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                finish();
                            }
                        } else {
                            if (sps.getString(Quiz_Game.this, "Exp_list").equals("on")) {
                                finish();
                                Intent i = new Intent(Quiz_Game.this, Expandable_List_View.class);
                                startActivity(i);
                            } else {
                                if (main_act.equals("")) {
                                    finish();
                                    Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else {
                                    finish();
                                }
                            }

                    }




                    //ad
                    if (sps.getInt(Quiz_Game.this, "purchase_ads") == 0) {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.cancel();
        }
    }

    private void soundset() {
        String snd = sps.getString(Quiz_Game.this, "snd");
        c_settings = (TextView) findViewById(R.id.c_settings);
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

    public void permission(final String a) {

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
            focus.stop();

            String date = sps.getString(Quiz_Game.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
                newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
            } else {
                pos = 2;
                newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
            }
            if ((ContextCompat.checkSelfPermission(Quiz_Game.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                helpshare(a);
            } else {
                if (sps.getString(Quiz_Game.this, "permission_grand").equals("")) {
                    sps.putString(Quiz_Game.this, "permission_grand", "yes");
                    //  First_register("yes");
                    AlertDialog alertDialog = new AlertDialog.Builder(Quiz_Game.this).create();
                    alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர  பின்வரும் permission-யை  allow செய்யவேண்டும்");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if ((ContextCompat.checkSelfPermission(Quiz_Game.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(Quiz_Game.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                                    } else {
                                        helpshare(a);
                                    }
                                }
                            });

                    alertDialog.show();

                } else {
                    if ((ContextCompat.checkSelfPermission(Quiz_Game.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(Quiz_Game.this, "permission") == 2) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Quiz_Game.this).create();
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
                                            String date = sps.getString(Quiz_Game.this, "date");
                                            int pos;
                                            if (date.equals("0")) {
                                                pos = 1;
                                            } else {
                                                pos = 2;
                                            }
                                            Cursor cs = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + question_id + "'");
                                            cs.moveToFirst();
                                            long dscore = 0;
                                            if (cs.getCount() != 0) {
                                                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                                            }
                                            focus.setBase(SystemClock.elapsedRealtime() + dscore);
                                            focus.start();
                                            dialog.dismiss();
                                        }
                                    });


                            alertDialog.show();
                        } else {
                            if ((ContextCompat.checkSelfPermission(Quiz_Game.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(Quiz_Game.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                            } else {
                                helpshare(a);
                            }
                        }
                    } else {
                        if ((ContextCompat.checkSelfPermission(Quiz_Game.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(Quiz_Game.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
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

                    String date = sps.getString(Quiz_Game.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                    }
                 /*   String date = sps.getString(Find_words_from_picture.this, "date");
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
                    Uri uri = FileProvider.getUriForFile(Quiz_Game.this, Quiz_Game.this.getPackageName(), file);
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

    public void dialog(int i) {

        final Dialog openDialog_earncoin = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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


        RelativeLayout video = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnvideo);
        TextView wpro = (TextView) openDialog_earncoin.findViewById(R.id.wpro);
        if (i == 1) {
            cancel.setVisibility(View.INVISIBLE);
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");
        }
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 1;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Quiz_Game.this, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();

                        String date = sps.getString(Quiz_Game.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                        }
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                       // reward(Quiz_Game.this);
                        rewarded_ad();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(Quiz_Game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
                /*rvo = 1;
                extra_coin_s = 0;
                if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Quiz_Game.this, "" + "Reward video", "Loading...");

                    if (mRewardedVideoAd.isLoaded()) {
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();

                        String date = sps.getString(Quiz_Game.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "'");
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
                                    Toast.makeText(Quiz_Game.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Quiz_Game.this)) {
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
                if (Utils.isNetworkAvailable(Quiz_Game.this)) {

                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Quiz_Game.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                        } else {
                            pos = 2;
                        }
                        newhelper5.executeSql("UPDATE newgames5 SET playtime='" + ttstop + "' WHERE questionid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

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
        score.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        score.getLocationOnScreen(locationOnScreenSecond);
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
                score.getLocationInWindow(locationInWindowSecond);
                int[] locationOnScreenSecond = new int[2];
                score.getLocationOnScreen(locationOnScreenSecond);
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
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx + 20;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
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
            }
        }, 1200);

    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);

        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Quiz_Game.this, "date");
        if (date.equals("0")) {
            next_game.setText("வினாடி வினா தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("வினாடி வினா புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");
        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Clue_Game_Hard.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
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

        TextView odd_man_out = (TextView) openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = (TextView) openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Odd_man_out.class);
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
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Ote_to_Tamil.class);
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
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Riddle_game.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, WordError_correction.class);
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
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });
        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, English_to_tamil.class);
                startActivity(i);
            }
        });

        TextView quiz = (TextView) openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = (TextView) openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = (TextView) openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(Quiz_Game.this);
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
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Quiz_Game.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Quiz_Game.class);
                startActivity(i);
            }
        });

        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Quiz_Game.this);
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
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Jamble_word_game.class);
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
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Missing_Words.class);
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
                sps.putString(Quiz_Game.this, "date", "0");
                Intent i = new Intent(Quiz_Game.this, Find_difference_between_pictures.class);
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
                    Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Quiz_Game.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                sps.putString(Quiz_Game.this, "date", "0");


              /*  finish();
                openDialog.dismiss();
                //sps.putString(Odd_man_out.this, "date", "0");
                Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
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
        String date = sps.getString(Quiz_Game.this, "date");
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                prize_data_update(Quiz_Game.this, 75);
            } else if (timeElapsed > 91300) {
                prize_data_update(Quiz_Game.this, 50);
            } else {
                prize_data_update(Quiz_Game.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                prize_data_update(Quiz_Game.this, 100);
            } else if (timeElapsed > 91300) {
                prize_data_update(Quiz_Game.this, 75);
            } else {
                prize_data_update(Quiz_Game.this, 50);
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
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Quiz_Game.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                    download_datas();
                } else {
                    NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                    native_banner_ad_container.setVisibility(View.INVISIBLE);
                    head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Quiz_Game.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                            .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                    sps.putInt(Quiz_Game.this, "goto_sett", 1);


                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    sps.putString(Quiz_Game.this, "game_area", "on");
                                    String date = sps.getString(Quiz_Game.this, "date");
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
        });
        alertDialogBuilder.setPositiveButton("இல்லை ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               /* Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
                startActivity(i);*/
                sps.putString(Quiz_Game.this, "game_area", "on");
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
        Download_data_server download_data_server = new Download_data_server(Quiz_Game.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Quiz_Game.this)) {
                download_datas();
            } else {
                NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                native_banner_ad_container.setVisibility(View.INVISIBLE);
                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Quiz_Game.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                sps.putInt(Quiz_Game.this, "goto_sett", 1);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
        final Dialog openDialog = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                score.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });
        openDialog.show();
    }

    private void dialog_dicription() {
        openDialog_odd_man = new Dialog(Quiz_Game.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_odd_man.setContentView(R.layout.answer_discription_quiz);
        word1 = (TextView) openDialog_odd_man.findViewById(R.id.word1);
        word2 = (TextView) openDialog_odd_man.findViewById(R.id.word2);
        word3 = (TextView) openDialog_odd_man.findViewById(R.id.word3);
        word4 = (TextView) openDialog_odd_man.findViewById(R.id.word4);
        word5 = (TextView) openDialog_odd_man.findViewById(R.id.word5);
        word6 = (TextView) openDialog_odd_man.findViewById(R.id.word6);
        ans = (TextView) openDialog_odd_man.findViewById(R.id.ans);
        dis = (TextView) openDialog_odd_man.findViewById(R.id.discription);
        close = (TextView) openDialog_odd_man.findViewById(R.id.close);
        TextView questions = (TextView) openDialog_odd_man.findViewById(R.id.questions);
        TextView diss = (TextView) openDialog_odd_man.findViewById(R.id.dis);
        TextView answer = (TextView) openDialog_odd_man.findViewById(R.id.answer);


        //  word1.setVisibility(View.GONE);
        word2.setVisibility(View.GONE);
        word3.setVisibility(View.GONE);
        word4.setVisibility(View.GONE);
        word5.setVisibility(View.GONE);
        word6.setVisibility(View.GONE);
        diss.setVisibility(View.GONE);
        dis.setVisibility(View.GONE);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openDialog_odd_man.dismiss();

            }
        });

        openDialog_odd_man.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // dialog dismiss without button press
                // load_addcontent2(context,ads_layout);


            }
        });
        Cursor cs;
        cs = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and questionid='" + question_id + "'");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            String question = cs.getString(cs.getColumnIndexOrThrow("question"));
            String answera = cs.getString(cs.getColumnIndexOrThrow("answer"));

            questions.setText("கேள்வி");
            answer.setText("பதில்");
            word1.setText("" + question);
            ans.setText(answera);


        }
        if (sps.getInt(Quiz_Game.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            //  load_addcontent(context, frame_layout2);
        }

        openDialog_odd_man.show();
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
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx - 50;
                String aStringx = Integer.toString(spx);
                score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                // setSc();
            }
        }, 1300);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 151) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Quiz_Game.this, "permission", 1);
                download_datas();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Quiz_Game.this, "permission", 2);
                        finish();
                        Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
                        startActivity(i);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Quiz_Game.this, "permission", 0);
                        finish();
                        Intent i = new Intent(Quiz_Game.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }
            }
        }

        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Quiz_Game.this, "permission", 1);
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
                        sps.putInt(Quiz_Game.this, "permission", 2);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Quiz_Game.this, "permission", 0);
                    }
                }
            }

        }
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
                    Toast.makeText(Quiz_Game.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
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
