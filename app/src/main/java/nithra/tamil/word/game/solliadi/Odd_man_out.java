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
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Odd_man_out extends AppCompatActivity implements Download_completed {

    public static final String TAG = "SavedGames";
    static final int vs = 0;
    static int f;
    static int rvo = 0;
    static int mCoinCount = 20;
    static SharedPreference spd = new SharedPreference();
    final String gameid = "5";
    final SharedPreference sps = new SharedPreference();
    final int min = 1;
    final int max = 3;
    final Context context = this;
    final int minmum = 1;
    final int maximum = 4;
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    private final PendingAction pendingAction = PendingAction.NONE;
    // True when the application is attempting to resolve a sign-in error that has a possible
    // resolution,
    private final boolean mIsResolving = false;
    // True immediately after the user clicks the sign-in button/
    private final boolean mSignInClicked = false;
    // True if we want to automatically attempt to sign in the user at application start.
    private final boolean mAutoStartSignIn = true;
    int fb_reward = 0;
    int reward_status = 0;
    String btn_str = "";
    // facebook variable ends
    TextView s_word_number, s_score_edit, hint, p_coins, p_coins_red, earncoin, to_no, discription;
    TextView bt1, bt2, bt3, bt4, bt5, bt6;
    TextView bts1, bts2, bts3, bts4;
    Chronometer focus;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    int u_id, questionid;
    String question, answer;
    RelativeLayout sixcat, fourcat, below, bottom;
    Animation clickzoom, clickzoom2;
    int ans_position;
    int q_type = 0;
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
    TextView next_continue;
    TextView ttscores;
    Typeface typ, tyr;
    int f_sec;
    int r = 0;
    int s = 0;
    PopupWindow popupWindow;
    TextView toggleButton;
    TextView p_setting;
    LinearLayout qwt;
    FrameLayout frame_layout2;
    Dialog openDialog_s, openDialog_p, openDialog_odd_man;
    RelativeLayout adsicon, adsicon2;
    LinearLayout helpshare_layout;
    /////////Native_BackPress_Advanced////////////
    TextView word1, word2, word3, word4, word5, word6, ans, dis, close;
    Animation myFadeInAnimation;
    int share_name = 0;
    LinearLayout ads_layout;
    int loadaddcontent = 0;
    int daily_start = 0;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper4 newhelper4;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    int randomno;
    int setval_vid;
    TextView coin_value;
    Dialog openDialog;
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
        if (status.equals("nodata")) {
            nextgamesdialog();
        } else {
            next();
        }

    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odd_man_out);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);


        if (sps.getString(Odd_man_out.this, "new_user_db").equals("")) {

        } else {
            if (sps.getString(Odd_man_out.this, "new_user_db").equals("on")) {
                sps.putString(Odd_man_out.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(Odd_man_out.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }

        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        myDbHelper = new DataBaseHelper(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);


        //uiHelper = new UiLifecycleHelper(this, callback);


        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        //reward(Odd_man_out.this);

        Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        if (sps.getInt(context, "purchase_ads") == 0) {
            industrialload();
        }


        find();

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
                sps.putString(Odd_man_out.this, "date", message);
                next();
            } else {
                sps.putString(Odd_man_out.this, "date", "0");
                next();
            }
        } else {
            sps.putString(Odd_man_out.this, "date", "0");
            next();
        }


        if (sps.getString(Odd_man_out.this, "odd_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Odd_man_out.this, "sequence example odd2");
            sequence.setConfig(config);
            sequence.addSequenceItem(hint, "இந்த பொத்தானை பயன்படுத்தி விடைகளின் எண்ணிக்கையை பாதியாக குறைத்துக்கொள்ளலாம்.", "அடுத்து");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Odd_man_out.this).setTarget(helpshare_layout).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener((itemView, position) -> {

                if (position == 1) {
                    sps.putString(Odd_man_out.this, "odd_time_start", "yes");
                    sps.putString(Odd_man_out.this, "showcase_dismiss_odd", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            });

            sps.putString(Odd_man_out.this, "odd_intro", "no");
            sequence.start();


        }

        if (sps.getInt(Odd_man_out.this, "reward_coin_txt") == 0) {
            sps.putInt(Odd_man_out.this, "reward_coin_txt", 20);
        }
        openDialog_s = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);
        adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);


        /////////

        if (sps.getInt(Odd_man_out.this, "purchase_ads") == 1) {
        } else {
            //   advancads();
        }

        ////////
        /////////

        if (sps.getInt(Odd_man_out.this, "purchase_ads") == 1) {
        } else {
            //  advancads_content();
        }

        ImageView prize_logo = (ImageView) findViewById(R.id.prize_logo);
        if (sps.getInt(Odd_man_out.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                if (sps.getString(Odd_man_out.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Odd_man_out.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Odd_man_out.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Odd_man_out.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Odd_man_out.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Odd_man_out.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });

        openDialog_odd_man = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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


        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toggleButton = (TextView) popupView.findViewById(R.id.toggle);
        //Sound ON OFF
        String snd = sps.getString(Odd_man_out.this, "snd");
        p_setting = (TextView) findViewById(R.id.s_settings);
        if (snd.equals("off")) {
            p_setting.setBackgroundResource(R.drawable.sound_off);
            sv = 0;

        } else if (snd.equals("on")) {
            p_setting.setBackgroundResource(R.drawable.sound_on);
            sv = 1;

        }

        bt1.setOnTouchListener((v, event) -> {
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

        });
        bt2.setOnTouchListener((v, event) -> {
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

        });
        bt3.setOnTouchListener((v, event) -> {
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

        });
        bt4.setOnTouchListener((v, event) -> {
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

        });
        bt5.setOnTouchListener((v, event) -> {
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

        });
        bt6.setOnTouchListener((v, event) -> {
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

        });
        bts1.setOnTouchListener((v, event) -> {
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

        });

        bts2.setOnTouchListener((v, event) -> {
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

        });

        bts3.setOnTouchListener((v, event) -> {
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

        });

        bts4.setOnTouchListener((v, event) -> {
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

        });

        p_setting.setOnClickListener(v -> {

            p_setting.setBackgroundResource(R.drawable.sound_off);
            String snd1 = sps.getString(Odd_man_out.this, "snd");
            if (snd1.equals("off")) {
                sps.putString(Odd_man_out.this, "snd", "on");
                p_setting.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd1.equals("on")) {
                sps.putString(Odd_man_out.this, "snd", "off");
                p_setting.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }
        });
        below.setOnClickListener(view -> {
            if (k == 2) {
                popupWindow.dismiss();
                k = 1;
            }
        });

        bottom.setOnClickListener(view -> {
            if (k == 2) {
                popupWindow.dismiss();
                k = 1;
            }
        });
        qwt.setOnClickListener(view -> dialog(0));


        hint.setOnClickListener(view -> {

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
                    newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    Random rn = new Random();
                    random = rn.nextInt(max - min + 1) + min;
                    hint.clearAnimation();

                    hintshow();


                } else {
                    final Dialog openDialog = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog.setContentView(R.layout.show_ans);
                    TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                    TextView no = (TextView) openDialog.findViewById(R.id.no);
                    TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);
                    TextView txt_ex = (TextView) openDialog.findViewById(R.id.txt_ex);
                    txt_ex.setText("வினாக்களை பாதியாக குறைக்க வேண்டுமா?");
                    txt_ex2.setText("மொத்த நாணயங்களில் 50 குறைக்கப்படும்");
                    CheckBox checkbox_ans = (CheckBox) openDialog.findViewById(R.id.checkbox_ans);
                    checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

                        if (isChecked) {
                            sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                        } else {
                            sps.putString(getApplicationContext(), "checkbox_ans", "");
                        }
                    });

                    yes.setOnClickListener(v -> {
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                        s_score_edit.setText(aStringx);

                        //  hint.setEnabled(false);
                        hint.setVisibility(View.INVISIBLE);
                        newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        Random rn = new Random();
                        random = rn.nextInt(max - min + 1) + min;
                        hint.clearAnimation();

                        hintshow();

                        openDialog.dismiss();
                    });
                    no.setOnClickListener(v -> {
                        sps.putString(getApplicationContext(), "checkbox_ans", "");
                        openDialog.dismiss();
                    });
                    if (!isFinishing())openDialog.show();

                }
            } else {
                dialog(1);
            }

        });

        earncoin.setOnClickListener(v -> dialog(0));
        p_gplues.setOnClickListener(view -> {
            share_name = 3;
            String a = "com.google.android.apps.plus";
            permission(a);
        });
        p_watts_app.setOnClickListener(view -> {
            share_name = 2;
            String a = "com.whatsapp";
            permission(a);
        });
        p_facebook.setOnClickListener(view -> {
            share_name = 1;
            final String a = "com.facebook.katana";
            permission(a);
        });


    }

    private void next() {

        myFadeInAnimation = AnimationUtils.loadAnimation(Odd_man_out.this, R.anim.blink_animation);
        hint.startAnimation(myFadeInAnimation);


        discription.setVisibility(View.INVISIBLE);

        bts1.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bts2.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bts3.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bts4.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bt1.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bt2.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bt3.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bt4.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bt5.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        bt6.setBackgroundResource(R.drawable.curve_for_odd_man_out);

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

        if (sps.getString(Odd_man_out.this, str_date1).equals("")) {
            daily_bones();

            sps.putString(Odd_man_out.this, str_date1, "yes");

        }

        if (daily_start == 1) {
            Toast.makeText(Odd_man_out.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான வேறுபாடுகளை கண்டுபிடி விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();

            sps.putString(context, "date", "0");
            daily_start = 0;
        }

        String date = sps.getString(Odd_man_out.this, "date");

        if (date.equals("0")) {
            Cursor c2 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            s_word_number.setText(no/*+"/"+c1.getCount()*/);
        } else {
            if (sps.getInt(Odd_man_out.this, "purchase_ads") == 1) {

            } else {
                sps.putInt(context, "addloded_rect_bck", 0);
                sps.putInt(context, "addloded_rect_mul", 0);

            }
            String tfoption = date;
            String[] first = tfoption.split("-");
            to_no.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            to_no.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }

        Cursor c1 = null;

        if (date.equals("0")) {

            c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and isfinish='0'");

        } else {
            daily_start = 1;
            c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and daily='0' order by random() limit 1");
        }
        c1.moveToFirst();
        if (c1.getCount() != 0) {

            head.setVisibility(View.VISIBLE);
            u_id = c1.getInt(c1.getColumnIndexOrThrow("id"));
            questionid = c1.getInt(c1.getColumnIndexOrThrow("questionid"));
            question = c1.getString(c1.getColumnIndexOrThrow("question"));
            answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
            int clue = c1.getInt(c1.getColumnIndexOrThrow("clue"));
            int playtime = c1.getInt(c1.getColumnIndexOrThrow("playtime"));
            String tfoption = question;
            String[] first = tfoption.split(",");
            int letter_type = first.length;
            // Toast.makeText(Odd_man_out.this, ""+clue, Toast.LENGTH_SHORT).show();


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
                if (sps.getString(Odd_man_out.this, "odd_time_start").equals("")) {

                } else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            }
        } else {
            downloaddata_regular();
        }

    }

    private void download_datas() {
        Cursor cz = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("questionid")));
        }

        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Odd_man_out.this, questionid_d, "5");
        download_data_server.execute();
    }

    private void daily_bones() {
        openDialog = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        //  TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
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

        TextView tomarrow_coin_earn = (TextView) openDialog.findViewById(R.id.tomarrow_coin_earn);

        //TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        //TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);

        ok_y.setOnClickListener(v -> {
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

        });
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
        ea = 100;
        final int vals = reward_play_count * 100;
        ea = ea + vals;
        coin_value.setText("" + ea);

        LinearLayout extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        System.out.println("############################^^^^^^^^^^^^^^currentdate" + str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Odd_man_out.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Odd_man_out.this, "daily_bonus_date"))) {

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
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomno = rn.nextInt(maximum - minmum + 1) + minmum;

        String ran_score = "";
        if (randomno == 1) {
            sps.putInt(context, "daily_bonus_count", 1);
            ran_score = "150";
        } else if (randomno == 2) {
            sps.putInt(context, "daily_bonus_count", 2);
            ran_score = "200";
        } else if (randomno == 3) {
            sps.putInt(context, "daily_bonus_count", 3);
            ran_score = "250";
        } else if (randomno == 4) {
            sps.putInt(context, "daily_bonus_count", 4);
            ran_score = "300";
        }

        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);

        extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        extra_coin.setOnClickListener(v -> {
            extra_coin_s = 1;
            extra_coin_s = 1;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Odd_man_out.this, "" + "Reward video", "Loading...");
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
                            //reward(Odd_man_out.this);
                            rewarded_adnew();
                            Toast.makeText(Odd_man_out.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);


                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        });
        if (!isFinishing())openDialog.show();
    }

    private void find() {
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
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
        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Odd_man_out.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Odd_man_out.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Odd_man_out.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Odd_man_out.this, R.raw.coins, 1);
///

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
        String tr = String.valueOf(skq);
        s_score_edit.setText(tr);
        // focus.start();
    }

    public void verify(String ans, String button) {
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.stop();
        String date = sps.getString(Odd_man_out.this, "date");
        Cursor cs = null;
        if (date.equals("0")) {
            cs = newhelper.getQry("select * from newmaintable where answer LIKE'" + ans + "'and isfinish='0'and questionid=" + questionid + " and gameid=" + gameid + "");
        } else {
            cs = newhelper.getQry("select * from newmaintable where answer LIKE'" + ans + "'and daily='0'and questionid=" + questionid + " and gameid=" + gameid + "");
        }
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            String dis = cs.getString(cs.getColumnIndexOrThrow("sf_words"));
            coinanim();
            //   Toast.makeText(Odd_man_out.this, "Correct Answer", Toast.LENGTH_SHORT).show();
            spz3.play(soundId3, sv, sv, 0, 0, sv);
            changecolour_r(button);
            hint.clearAnimation();
            hint.setVisibility(View.INVISIBLE);
            if (date.equals("0")) {
                newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            } else {
                newhelper.executeSql("UPDATE newmaintable SET daily='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            }

            price_update();
            Handler handler = new Handler(Looper.myLooper());
            handler.postDelayed(() -> adShow(), 2300);
        } else {
            //  Toast.makeText(Odd_man_out.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            hint.clearAnimation();
            hint.setVisibility(View.INVISIBLE);
            Cursor c1 = null;
            if (date.equals("0")) {
                c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "' and isfinish='0'");
            } else {
                c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "' and daily='0'");
            }
            c1.moveToFirst();
            if (c1.getCount() != 0) {
                String answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
                show_answer(answer);
                String dis = c1.getString(c1.getColumnIndexOrThrow("sf_words"));
            }

            spz2.play(soundId2, sv, sv, 0, 0, sv);
            coinanim_red();
            changecolour_w(button);


            if (date.equals("0")) {
                newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            } else {
                newhelper.executeSql("UPDATE newmaintable SET daily='1' WHERE questionid='" + questionid + "'and gameid=" + gameid + "");
            }
            Handler handler = new Handler(Looper.myLooper());
            handler.postDelayed(() -> adShow(), 2300);
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
        sps.putString(Odd_man_out.this, "game_area", "on");
        sps.putString(Odd_man_out.this, "odd_time_start", "yes");
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            sps.putInt(Odd_man_out.this, "addlodedd", 0);
            s = 1;
            openDialog_p = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.back_pess);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);

            yes.setOnClickListener(v -> {
                ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                focus.stop();
                newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                if (main_act.equals("")) {
                    finish();
                    openDialog_s.dismiss();
                    Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                    openDialog_s.dismiss();
                }

                openDialog_p.dismiss();
            });
            no.setOnClickListener(v -> openDialog_p.dismiss());
            openDialog_p.show();


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Odd_man_out.this, "date");
        System.out.println("######################Timer ttstop" + ttstop);
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
            newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
        }

    }

    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ON Resume  " + sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV"));

        long ptime = 0;
        int clue = 0;
        Cursor cs;
        cs = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "'");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            ptime = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            clue = cs.getInt(cs.getColumnIndexOrThrow("clue"));
        }
        // Toast.makeText(Odd_man_out.this, "random"+clue, Toast.LENGTH_SHORT).show();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Odd_man_out.this);
        mFirebaseAnalytics.setCurrentScreen(this, "Odd man out Game", null);

        if (sps.getString(Odd_man_out.this, "odd_time_start").equals("")) {


        } else {

            focus.setBase(SystemClock.elapsedRealtime() + ptime);
            focus.start();

        }


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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts2.setVisibility(View.INVISIBLE);
                        bts4.setVisibility(View.INVISIBLE);
                    }, 1000);

                } else if (random == 2) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts2.startAnimation(clickzoom2);
                    bts3.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts3.setVisibility(View.INVISIBLE);
                        bts2.setVisibility(View.INVISIBLE);
                    }, 1000);


                } else if (random == 3) {
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts2.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    bts2.setEnabled(false);
                    bts4.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts2.setVisibility(View.INVISIBLE);
                        bts4.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts1.setVisibility(View.INVISIBLE);
                        bts4.setVisibility(View.INVISIBLE);
                    }, 1000);


                } else if (random == 2) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    bts3.setEnabled(false);
                    bts4.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts3.setVisibility(View.INVISIBLE);
                        bts4.setVisibility(View.INVISIBLE);
                    }, 1000);


                } else if (random == 3) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts3.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts3.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts1.setVisibility(View.INVISIBLE);
                        bts3.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts1.setVisibility(View.INVISIBLE);
                        bts4.setVisibility(View.INVISIBLE);
                    }, 1000);


                } else if (random == 2) {
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts2.startAnimation(clickzoom2);
                    bts1.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts2.setVisibility(View.INVISIBLE);
                        bts1.setVisibility(View.INVISIBLE);
                    }, 1000);


                } else if (random == 3) {
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts4.startAnimation(clickzoom2);
                    bts2.startAnimation(clickzoom2);
                    bts4.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts4.setVisibility(View.INVISIBLE);
                        bts2.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts1.setVisibility(View.INVISIBLE);
                        bts2.setVisibility(View.INVISIBLE);
                    }, 1000);


                } else if (random == 2) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts3.startAnimation(clickzoom2);
                    bts1.setEnabled(false);
                    bts3.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts1.setVisibility(View.INVISIBLE);
                        bts3.setVisibility(View.INVISIBLE);
                    }, 1000);

                } else if (random == 3) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts2.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts2.startAnimation(clickzoom2);
                    bts3.setEnabled(false);
                    bts2.setEnabled(false);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bts3.setVisibility(View.INVISIBLE);
                        bts2.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt2.setVisibility(View.INVISIBLE);
                        bt4.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt3.setVisibility(View.INVISIBLE);
                        bt2.setVisibility(View.INVISIBLE);
                        bt5.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt3.setVisibility(View.INVISIBLE);
                        bt4.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt3.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt3.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
                        bt5.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt5.setVisibility(View.INVISIBLE);
                        bt4.setVisibility(View.INVISIBLE);
                        bt3.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt2.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt2.setVisibility(View.INVISIBLE);
                        bt1.setVisibility(View.INVISIBLE);
                        bt5.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt4.setVisibility(View.INVISIBLE);
                        bt2.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt2.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt3.setVisibility(View.INVISIBLE);
                        bt5.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt2.setVisibility(View.INVISIBLE);
                        bt5.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt2.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt3.setVisibility(View.INVISIBLE);
                        bt4.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt2.setVisibility(View.INVISIBLE);
                        bt4.setVisibility(View.INVISIBLE);
                        bt6.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt2.setVisibility(View.INVISIBLE);
                        bt5.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt1.setVisibility(View.INVISIBLE);
                        bt3.setVisibility(View.INVISIBLE);
                        bt4.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        bt2.setVisibility(View.INVISIBLE);
                        bt4.setVisibility(View.INVISIBLE);
                        bt5.setVisibility(View.INVISIBLE);
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> bts1.setVisibility(View.INVISIBLE), 1000);
                    Handler handler2 = new Handler(Looper.myLooper());
                    handler2.postDelayed(() -> bts4.setVisibility(View.INVISIBLE), 1000);

                } else if (random == 2) {
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts4.setBackgroundResource(R.color.eliminate);
                    bts3.startAnimation(clickzoom2);
                    bts4.startAnimation(clickzoom2);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> bts3.setVisibility(View.INVISIBLE), 1000);
                    Handler handler2 = new Handler(Looper.myLooper());
                    handler2.postDelayed(() -> bts4.setVisibility(View.INVISIBLE), 1000);


                } else if (random == 3) {
                    bts1.setBackgroundResource(R.color.eliminate);
                    bts3.setBackgroundResource(R.color.eliminate);
                    bts1.startAnimation(clickzoom2);
                    bts3.startAnimation(clickzoom2);
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> bts1.setVisibility(View.INVISIBLE), 1000);
                    Handler handler2 = new Handler(Looper.myLooper());
                    handler2.postDelayed(() -> bts3.setVisibility(View.INVISIBLE), 1000);
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
            s_score_edit.getLocationInWindow(locationInWindowSecond1);
            int[] locationOnScreenSecond1 = new int[2];
            s_score_edit.getLocationOnScreen(locationOnScreenSecond1);
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
                s_score_edit.post(() -> s_score_edit.setText("" + e2));
                e2++;
            }

        }).start();

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
            s_score_edit.startAnimation(levels1);
        }, 2200);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(() -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 20;
            String aStringx = Integer.toString(spx);
            s_score_edit.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

            Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
            ch.moveToFirst();
            int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
            int shh = sh + 50;
            myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

            // setSc();
        }, 1200);

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
                s_score_edit.post(() -> s_score_edit.setText("" + e2));
                e2++;
            }

        }).start();

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
            s_score_edit.startAnimation(levels1);
        }, 2200);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(() -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx - 50;
            String aStringx = Integer.toString(spx);
            s_score_edit.setText(aStringx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            // setSc();
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
                    newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

//Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Odd_man_out.this, Odd_man_out.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இந்த வேறுபாடுகளை கண்டுபிடி விளையாட்டின் விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்.   https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT, "Solli_adi");
                    //  share.putExtra(android.content.Intent.EXTRA_TEXT,"Shared via Tamil Calendar Offline.\nClick here to download"+ "\nhttps://goo.gl/ITvWGu");
                    startActivity(Intent.createChooser(share, "Share Card Using"));
                } else {
                    Toast.makeText(Odd_man_out.this, "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
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
        ConnectivityManager connec = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void dialog(int i) {
        openDialog_earncoin = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        ss.setOnClickListener(v -> openDialog_earncoin.cancel());
        cancel.setOnClickListener(v -> openDialog_earncoin.cancel());


        RelativeLayout video = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnvideo);
        final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);


        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            extra_coin_s = 0;
            if (isNetworkAvailable()) {

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }

        });

        video.setOnClickListener(v -> {
            rvo = 1;
            extra_coin_s = 0;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Odd_man_out.this, "date");
                    System.out.println("######################Timer ttstop" + ttstop);
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                        newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                    }
                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {

                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);
                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
/*

            rvo = 1;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Odd_man_out.this, "" + "Reward video", "Loading...");

               */
/*

                if (mRewardedVideoAd.isLoaded()) {

                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Odd_man_out.this, "date");
                    System.out.println("######################Timer ttstop"+ttstop);
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                        newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                        newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                    }
                    reward_progressBar.dismiss();
                    showRewardedVideo();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {


                    startGame();

                    new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reward_progressBar.dismiss();
                            if (mRewardedVideoAd.isLoaded()) {
                                showRewardedVideo();
                                openDialog_earncoin.cancel();
                                // mShowVideoButton.setVisibility(View.VISIBLE);
                            } else {
                                startGame();
                                Toast.makeText(Odd_man_out.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000);


                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }
*/

        });

        wp.setOnClickListener(view -> {
            if (isNetworkAvailable()) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    openDialog_earncoin.dismiss();
                    Intent i12 = new Intent(Intent.ACTION_SEND);
                    i12.setType("text/plain");
                    i12.setPackage("com.whatsapp");
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i12.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i12, "Share via"), 12);

                    ///shareing reward
                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });
        fb.setOnClickListener(view -> {
            // toast("இணையதள சேவையை சரிபார்க்கவும் ");

        });
        gplus.setOnClickListener(view -> {
            if (isNetworkAvailable()) {
                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    openDialog_earncoin.dismiss();
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

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        b_scores.setText("" + a);


        ok_y.setOnClickListener(v -> {
            ttscores.setText("" + skx);
            s_score_edit.setText("" + skx);
            openDialog.dismiss();
            //mCoinCount = 0;
        });

        if (!isFinishing())openDialog.show();
    }

    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        b_scores.setText("" + a);


        ok_y.setOnClickListener(v -> {
            s_score_edit.setText("" + skx);
            openDialog.dismiss();
            //mCoinCount = 0;
        });

        if (!isFinishing())openDialog.show();
    }

    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Odd_man_out.this, "reward_coin_txt", coins);
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
            final Dialog openDialog = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            ok_y.setOnClickListener(v -> {
                s_score_edit.setText("" + skx);
                openDialog.dismiss();
                //mCoinCount = 0;
            });

            if (!isFinishing())openDialog.show();
        }

    }


    //reward videos***********************//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Odd_man_out.this)) {
                download_datas();
            } else {

                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Odd_man_out.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்").setPositiveButton("அமைப்பு", (dialog, which) -> {
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Odd_man_out.this, "goto_sett", 1);
                    dialog.dismiss();
                }).setNegativeButton("பின்னர்", (dialog, which) -> {
                    String date = sps.getString(Odd_man_out.this, "date");
                    if (date.equals("0")) {
                        backexitnet();
                    } else {
                        backexitnet();
                    }
                    dialog.dismiss();
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
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
                sps.putString(Odd_man_out.this, "gplues", "no");
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

                if (sps.getString(Odd_man_out.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Odd_man_out.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Odd_man_out.this, "complite_reg").equals("yes")) {
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

                if (sps.getString(Odd_man_out.this, "complite_reg").equals("yes")) {
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
        if (requestCode == 0) {
            downloaddata_regular();
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
        extracoin2.setVisibility(View.INVISIBLE);
        LinearLayout ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);
        final LinearLayout rewardvideo = (LinearLayout) openDialog_s.findViewById(R.id.rewardvideo);
        final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);
        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Odd_man_out.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Odd_man_out.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        ImageView prize_logo = (ImageView) openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Odd_man_out.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                if (sps.getString(Odd_man_out.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Odd_man_out.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Odd_man_out.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Odd_man_out.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Odd_man_out.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Odd_man_out.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
        if (sps.getInt(Odd_man_out.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Odd_man_out.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Odd_man_out.this, ads_layout);
            } else {
                ads_layout.setVisibility(View.GONE);
            }
            // New_Main_Activity.load_addFromMain_multiplayer(Odd_man_out.this, ads_layout);
        }


        TextView discription = (TextView) openDialog_s.findViewById(R.id.discription);
        discription.setVisibility(View.VISIBLE);


        next_continue.setVisibility(View.VISIBLE);
        word.setTypeface(tyr);
        next_continue.setTypeface(tyr);
        kuduthal.setTypeface(tyr);
        kuduthal.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
        next_continue.setText("ªî£ì˜è");
        String date = sps.getString(Odd_man_out.this, "date");
        if (!date.equals("0")) {
            next_continue.setText("சரி");
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        String aStringx = Integer.toString(skx);
        ttscores.setText(aStringx);


        if (sps.getString(Odd_man_out.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Odd_man_out.this, "date");
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

        discription.setOnClickListener(view -> dialog_dicription());
        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(context)) {
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

        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(context)) {
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

        next_continue.setOnClickListener(view -> {
            dia_dismiss = 1;
            openDialog_s.dismiss();
            next();
        });
        openDialog_s.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Odd_man_out.this, "game_area", "on");


                if (main_act.equals("")) {
                    finish();
                    openDialog_s.dismiss();
                    Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                    openDialog_s.dismiss();
                }


            } else {
                dia_dismiss = 0;
            }


        });
      /*  openDialog_s.setOnKeyListener(new DialogInterface.OnKeyListener() {
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

    private void industrialload() {
        //AppLovinSdk.getInstance( this ).showMediationDebugger();
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(AppLovinSdkConfiguration config) {
                // AppLovin SDK is initialized, start loading ads
                if (mInterstitialAd != null) return;
                System.out.println("ad shown  showAdWithDelay initialize done ");
                mInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Puthayal_Sorkal_Ins), Odd_man_out.this);
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
                        sps.putInt(getApplicationContext(), "Game2_Stage_Close_PS", 0);
                        setSc();
                    }
                });

                // Load the first ad
                mInterstitialAd.loadAd();

            }
        });

    }

    public void adShow() {
        if (sps.getInt(getApplicationContext(), "Game2_Stage_Close_PS") == Utills.interstitialadCount && mInterstitialAd != null) {
            sps.putInt(getApplicationContext(), "Game2_Stage_Close_PS", 0);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                mInterstitialAd.showAd("Puthayal Sorkal Ins");
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game2_Stage_Close_PS", (sps.getInt(getApplicationContext(), "Game2_Stage_Close_PS") + 1));
            if (sps.getInt(this, "Game2_Stage_Close_PS") > Utills.interstitialadCount)
                sps.putInt(this, "Game2_Stage_Close_PS", 0);
            setSc();
            //Toast.makeText(this, ""+sps.getInt(this, "Game2_Stage_Close_PS"), Toast.LENGTH_SHORT).show();
        }

    }


    private void dialog_dicription() {

        word1.setVisibility(View.GONE);
        word2.setVisibility(View.GONE);
        word3.setVisibility(View.GONE);
        word4.setVisibility(View.GONE);
        word5.setVisibility(View.GONE);
        word6.setVisibility(View.GONE);

        close.setOnClickListener(view -> {

            //load_addcontent2(context,ads_layout);

            openDialog_odd_man.dismiss();

        });

        openDialog_odd_man.setOnCancelListener(dialog -> {
            // dialog dismiss without button press
            // load_addcontent2(context,ads_layout);

        });
        Cursor cs;
        cs = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "'");
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
        if (sps.getInt(Odd_man_out.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {

        }

        openDialog_odd_man.show();
    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Odd_man_out.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);

        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Odd_man_out.this, "date");
        if (date.equals("0")) {
            next_game.setText("வேறுபாடுகளை கண்டுபிடி தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("வேறுபாடுகளை கண்டுபிடி புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");
        }

        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Clue_Game_Hard.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Picture_Game_Hard.class);
            startActivity(i);
        });

        exit.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
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

        TextView odd_man_out = (TextView) openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = (TextView) openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Odd_man_out.class);
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


        TextView opposite_word = (TextView) openDialog.findViewById(R.id.opposite_word);
        TextView ote_to_tamil = (TextView) openDialog.findViewById(R.id.ote_to_tamil);
        opposite_word.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Ote_to_Tamil.class);
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


        seerpaduthu.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Riddle_game.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, WordError_correction.class);
            startActivity(i);
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

        fill_in_blanks.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Fill_in_blanks.class);
            startActivity(i);
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

        match_words.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Match_tha_fallows_game.class);
            startActivity(i);

        });
        find_words_from_pictures.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Find_words_from_picture.class);
            startActivity(i);
        });
        quiz.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Quiz_Game.class);
            startActivity(i);
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Odd_man_out.this);
        TextView jamble_words = (TextView) openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Jamble_word_game.class);
            startActivity(i);
        });
        TextView missing_words = (TextView) openDialog.findViewById(R.id.missing_words);
        Cursor jmps;
        jmps = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='0' order by id limit 1");
        jmps.moveToFirst();
        if (jmps.getCount() != 0) {
            missing_words.setVisibility(View.VISIBLE);
        }
        missing_words.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Missing_Words.class);
            startActivity(i);
        });
        TextView six_differences = (TextView) openDialog.findViewById(R.id.six_differences);
        Cursor dif;
        dif = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='0' order by id limit 1");
        dif.moveToFirst();
        if (dif.getCount() != 0) {
            six_differences.setVisibility(View.VISIBLE);
        }
        six_differences.setOnClickListener(v -> {
            finish();
            sps.putString(Odd_man_out.this, "date", "0");
            Intent i = new Intent(Odd_man_out.this, Find_difference_between_pictures.class);
            startActivity(i);
        });
        if (!isFinishing())openDialog.show();

        openDialog.setOnKeyListener((dialog, keyCode, event) -> {

            if (main_act.equals("")) {

                finish();
                //     openDialog_s.dismiss();
                Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Odd_man_out.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Odd_man_out.this, "date", "0");


            return keyCode == KeyEvent.KEYCODE_BACK;
        });
    }

    public void showpopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        final String snd = sps.getString(Odd_man_out.this, "snd");
        toggleButton = (TextView) popupView.findViewById(R.id.toggle);

        if (snd.equals("off")) {

            toggleButton.setBackgroundResource(R.drawable.off);

        } else if (snd.equals("on")) {

            toggleButton.setBackgroundResource(R.drawable.on);

        }

        toggleButton.setOnClickListener(v -> {
            String snd1 = sps.getString(Odd_man_out.this, "snd");
            System.out.println("*****click");
            if (snd1.equals("off")) {

                System.out.println("*****on");
                sps.putString(Odd_man_out.this, "snd", "on");
                toggleButton.setBackgroundResource(R.drawable.on);
                sv = 1;

            }
            if (snd1.equals("on")) {
                System.out.println("*****off");

                sps.putString(Odd_man_out.this, "snd", "off");
                toggleButton.setBackgroundResource(R.drawable.off);
                sv = 0;

            }
        });

        popupWindow.showAsDropDown(p_setting, 50, -10);

    }

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Odd_man_out.this, "date");
        System.out.println("######################Timer ttstop" + ttstop);
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
            newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
        }
        helpshare(a);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 152) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Odd_man_out.this, "permission", 1);
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
                        sps.putInt(Odd_man_out.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Odd_man_out.this, "permission", 0);
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

        view1.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(context)) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inss.getTag().toString())));
            } else {
                Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });

        inss.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(context)) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(view.getTag().toString())));
            } else {
                Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
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
        //uiHelper.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
        rewardedAd = null;
        mInterstitialAd = null;
        handler = null;
    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Odd_man_out.this, "showcase_dismiss_odd").equals("")) {
                showcase_dismiss();
            } else {
                sps.putString(Odd_man_out.this, "odd_time_start", "yes");

                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

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

        String date = sps.getString(Odd_man_out.this, "date");
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                System.out.println("###################random" + random);
                if (random == 0) {
                    prize_data_update(Odd_man_out.this, 75);
                } else {
                    prize_data_update(Odd_man_out.this, 25);
                }
            } else if (timeElapsed > 91300) {
                if (random == 0) {
                    prize_data_update(Odd_man_out.this, 50);
                } else {
                    prize_data_update(Odd_man_out.this, 25);
                }
            } else {
                prize_data_update(Odd_man_out.this, 25);
            }
        } else {
            if (timeElapsed <= 91300) {
                if (random == 0) {
                    prize_data_update(Odd_man_out.this, 100);
                } else {
                    prize_data_update(Odd_man_out.this, 50);
                }
            } else if (timeElapsed > 91300) {
                if (random == 0) {
                    prize_data_update(Odd_man_out.this, 75);
                } else {
                    prize_data_update(Odd_man_out.this, 50);
                }
            } else {
                prize_data_update(Odd_man_out.this, 50);
            }
        }
        ////////////////Prize//////////////////
    }

    public void downloaddata_regular() {
        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Odd_man_out.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {
            //DownLoad Letters and Words

            if (Utils.isNetworkAvailable(Odd_man_out.this)) {
                download_datas();
            } else {
                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Odd_man_out.this);                           /* .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog12, which) -> {
                    // continue with delete

                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Odd_man_out.this, "goto_sett", 1);


                    dialog12.dismiss();
                }).setNegativeButton("பின்னர்", (dialog1, which) -> {
                    // do nothing
                    sps.putString(Odd_man_out.this, "game_area", "on");
                    String date = sps.getString(Odd_man_out.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
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
            sps.putString(Odd_man_out.this, "game_area", "on");
            String date = sps.getString(Odd_man_out.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            } else {
                finish();
                Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
                startActivity(i);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
                rewardedAd.loadAd();


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
    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

}
