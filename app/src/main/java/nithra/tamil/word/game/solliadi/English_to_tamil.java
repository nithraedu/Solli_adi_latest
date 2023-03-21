package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.MainActivity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;

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
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.material.snackbar.Snackbar;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.StringTokenizer;

import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class English_to_tamil extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final String TAG = "SavedGames";
    //*********************reward videos process 1***********************
    //private final String AD_UNIT_ID = getString(R.string.rewarded);

    // The AppState slot we are editing.  For simplicity this sample only manipulates a single
    // Cloud Save slot and a corresponding Snapshot entry,  This could be changed to any integer
    // 0-3 without changing functionality (Cloud Save has four slots, numbered 0-3).
    private static final int APP_STATE_KEY = 1;
    // Request code used to invoke sign-in UI.
    private static final int RC_SIGN_IN = 9001;
    // Request code used to invoke Snapshot selection UI.
    private static final int RC_SELECT_SNAPSHOT = 9002;
    static int ry;
    //reward videos process 1***********************
    static int rvo = 0;
    static int mCoinCount = 20;
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    DataBaseHelper myDbHelper;
    TextView question_txt, c_ans;
    EditText ans_txt;
    TextView c_button1, c_button2, c_button3, c_button4, c_button5, c_button6, c_button7, c_button8, c_button9, c_button10, c_button11, c_button12, c_button13, c_button14, c_button15, c_button16;
    int gameid = 14, levelid;
    String hints, letters, answer;
    SoundPool click, win, coin, worng;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    int minmum = 1;
    int maximum = 3;
    int randomno;
    int type;
    Button clue_clear;
    TextView c_coin;
    TextView c_time, score, to_no;
    int e2;
    int score_type = 0;
    SharedPreference sps = new SharedPreference();
    long ttstop;
    Chronometer focus;
    com.facebook.ads.InterstitialAd interstitialAd_game;
    Dialog openDialog_p;
    Dialog openDialog_s;
    int s = 0;
    TextView next_continue;
    TextView ttscores;
    Typeface typ, tyr;
    int fb_reward = 0;
    RewardedVideoAd rewardedVideoAd;
    int reward_status = 0;
    int r = 0;
    TextView ans_high, c_settings;
    RelativeLayout w_head, helpshare_layout;

    /// Client used to interact with Google APIs.
    TextView c_word_number;
    TextView earncoin;
    LinearLayout qwt, adds;


    // Facebook variable starts
    int share_name = 0;
    TextView shareq, h_gplues, h_watts_app, h_facebook;
    int dia_dismiss = 0;
    private GoogleApiClient mGoogleApiClient;
    private boolean mGameOver;
    private boolean mGamePaused;
    private long mTimeRemaining;
    // True when the application is attempting to resolve a sign-in error that has a possible
    // resolution,
    private boolean mIsResolving = false;
    // True immediately after the user clicks the sign-in button/
    private boolean mSignInClicked = false;
    // True if we want to automatically attempt to sign in the user at application start.
    private boolean mAutoStartSignIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_to_tamil);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        newhelper = new Newgame_DataBaseHelper(English_to_tamil.this);
        newhelper2 = new Newgame_DataBaseHelper2(English_to_tamil.this);
        newhelper3 = new Newgame_DataBaseHelper3(English_to_tamil.this);
        newhelper4 = new Newgame_DataBaseHelper4(English_to_tamil.this);
        myDbHelper = new DataBaseHelper(English_to_tamil.this);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(English_to_tamil.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(English_to_tamil.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(English_to_tamil.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(English_to_tamil.this, R.raw.coins, 1);///


        openDialog_s = new Dialog(English_to_tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen2);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                .addScope(Drive.SCOPE_APPFOLDER) // SavedGames
                .build();


        if (sps.getInt(English_to_tamil.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(English_to_tamil.this);
            /* */
        }


        find();
        click();
        next();
        reward(English_to_tamil.this);
        ins_add();
        loads_ads_banner();
        if (sps.getString(English_to_tamil.this, "en_to_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(English_to_tamil.this, "sequence example en_to");
            sequence.setConfig(config);
            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(English_to_tamil.this)
                            .setTarget(helpshare_layout)
                            .setDismissText("சரி")
                            .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                            .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {

                            if (position == 1) {
                                sps.putString(English_to_tamil.this, "en_to_intro_time_start", "yes");
                                sps.putString(English_to_tamil.this, "showcase_dismiss_en_to_intro", "yes");

                                focus.setBase(SystemClock.elapsedRealtime());
                                focus.start();

                            }
                        }
                    });

            sps.putString(English_to_tamil.this, "en_to_intro", "no");
            sequence.start();


        }
    }

    private void loads_ads_banner() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sps.getInt(English_to_tamil.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            adds.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);

        } else {
            if (Utils.isNetworkAvailable(English_to_tamil.this)) {
                fb_native(English_to_tamil.this, native_banner_ad_container);
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }

    }

    private void next() {

        reset();

        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            score.setText("" + skx);
        }

        Cursor c;
        c = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "'and isfinish='0' order by id limit 1");
        c.moveToFirst();
        if (c.getCount() != 0) {
            letters = c.getString(c.getColumnIndexOrThrow("letters"));
            answer = c.getString(c.getColumnIndexOrThrow("answer"));
            hints = c.getString(c.getColumnIndexOrThrow("hints"));
            levelid = c.getInt(c.getColumnIndexOrThrow("levelid"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            c_word_number.setText("" + levelid);

            if (sps.getString(English_to_tamil.this, "en_to_intro_time_start").equals("yes")) {
                focus.setBase(SystemClock.elapsedRealtime() + playtime);
                focus.start();
            }

            // Toast.makeText(this, "" + c.getCount(), Toast.LENGTH_SHORT).show();

            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;

            //String r= String.valueOf(w_id);
            //lt_id.setText(r);

            String tfoption = answer;
            String[] first = tfoption.split(",");
            type = first.length;
            System.out.println("#########################type" + type);
            System.out.println("#########################randomno" + randomno);

            if (randomno == 1) {
                simple();
            } else if (randomno == 2) {
                medium();
            } else if (randomno == 3) {
                hard();
            }

            question_txt.setText("" + letters);
        } else {
            nextgamesdialog();
        }


    }

    private void reset() {
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

        ans_txt.setVisibility(View.VISIBLE);
        clue_clear.setVisibility(View.VISIBLE);
        ans_high.setVisibility(View.GONE);
        question_txt.setText("");
        ans_txt.setText("");
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        c_ans.setEnabled(true);
        score_type = 0;
        c_coin.setVisibility(View.INVISIBLE);

        c_button1.setVisibility(View.VISIBLE);
        c_button2.setVisibility(View.VISIBLE);
        c_button3.setVisibility(View.VISIBLE);
        c_button4.setVisibility(View.VISIBLE);
        c_button5.setVisibility(View.VISIBLE);
        c_button6.setVisibility(View.VISIBLE);
        c_button7.setVisibility(View.VISIBLE);
        c_button8.setVisibility(View.VISIBLE);
        c_button9.setVisibility(View.VISIBLE);
        c_button10.setVisibility(View.VISIBLE);
        c_button11.setVisibility(View.VISIBLE);
        c_button12.setVisibility(View.VISIBLE);
        c_button13.setVisibility(View.VISIBLE);
        c_button14.setVisibility(View.VISIBLE);
        c_button15.setVisibility(View.VISIBLE);
        c_button16.setVisibility(View.VISIBLE);
    }

    private void find() {
        question_txt = (TextView) findViewById(R.id.question_txt);
        c_button1 = (TextView) findViewById(R.id.c_button1);
        c_button2 = (TextView) findViewById(R.id.c_button2);
        c_button3 = (TextView) findViewById(R.id.c_button3);
        c_button4 = (TextView) findViewById(R.id.c_button4);
        c_button5 = (TextView) findViewById(R.id.c_button5);
        c_button6 = (TextView) findViewById(R.id.c_button6);
        c_button7 = (TextView) findViewById(R.id.c_button7);
        c_button8 = (TextView) findViewById(R.id.c_button8);
        c_button9 = (TextView) findViewById(R.id.c_button9);
        c_button10 = (TextView) findViewById(R.id.c_button10);
        c_button11 = (TextView) findViewById(R.id.c_button11);
        c_button12 = (TextView) findViewById(R.id.c_button12);
        c_button13 = (TextView) findViewById(R.id.c_button13);
        c_button14 = (TextView) findViewById(R.id.c_button14);
        c_button15 = (TextView) findViewById(R.id.c_button15);
        c_button16 = (TextView) findViewById(R.id.c_button16);
        ans_txt = (EditText) findViewById(R.id.ans_txt);
        clue_clear = (Button) findViewById(R.id.clue_clear);
        c_coin = (TextView) findViewById(R.id.c_coins);
        score = (TextView) findViewById(R.id.c_score_edit);
        focus = (Chronometer) findViewById(R.id.c_time_edit);
        c_ans = (TextView) findViewById(R.id.c_ans);
        ans_high = (TextView) findViewById(R.id.ans_highlite);
        c_word_number = (TextView) findViewById(R.id.c_word_number);
        qwt = (LinearLayout) findViewById(R.id.qwt);
        c_settings = (TextView) findViewById(R.id.c_settings);
        adds = (LinearLayout) findViewById(R.id.ads_lay);

        h_gplues = (TextView) findViewById(R.id.ch_gplues);
        h_watts_app = (TextView) findViewById(R.id.ch_watts_app);
        h_facebook = (TextView) findViewById(R.id.ch_facebook);
        String snd = sps.getString(English_to_tamil.this, "snd");
        if (snd.equals("off")) {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            sv = 0;
        } else if (snd.equals("on")) {
            c_settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;
        }
        helpshare_layout = (RelativeLayout) findViewById(R.id.helpshare_layout);
        w_head = (RelativeLayout) findViewById(R.id.clue_head);
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        c_ans.setEnabled(true);
        earncoin = (TextView) findViewById(R.id.earncoin);
    }

    private void click() {

        c_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                cfw.moveToFirst();
                int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                if (sk >= 50) {
                    r = 1;
                    if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                        Cursor cd;
                        String date = sps.getString(English_to_tamil.this, "date");
                        if (date.equals("0")) {
                            cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "' and isfinish='0' and gameid='" + gameid + "'");
                            cd.moveToFirst();
                        } else {
                            cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "' and gameid='" + gameid + "'");
                            cd.moveToFirst();
                        }

                        String sa = cd.getString(cd.getColumnIndexOrThrow("hints"));
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
                      /*  c_clue.clearAnimation();
                        c_clue.setVisibility(View.INVISIBLE);*/

                        //
                        ans_txt.setVisibility(View.INVISIBLE);
                        clue_clear.setVisibility(View.INVISIBLE);

                        Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                        ans_high.startAnimation(w_game);
                        ans_high.setVisibility(View.VISIBLE);
                        ans_high.setText(sa);
                        //Update QST
                        String datee = sps.getString(English_to_tamil.this, "date");
                        if (datee.equals("0")) {
                            newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
                        } else {
                            newhelper4.executeSql("UPDATE newgamesdb4 SET daily=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
                        }
                        //Next Function
                        //  r = 1;


                      /*  if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if (getApiClient().isConnected()) {
                                if (isSignedIn()) {
                                    savedGamesUpdate();
                                }
                            }
                        }*/
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
                        final Dialog openDialog = new Dialog(English_to_tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        openDialog.setContentView(R.layout.show_ans);
                        TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                        TextView no = (TextView) openDialog.findViewById(R.id.no);
                        TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);
                        txt_ex2.setText("மொத்த நாணயங்களில் 50 குறைக்கப்படும்");
                        CheckBox checkbox_ans = (CheckBox) openDialog.findViewById(R.id.checkbox_ans);
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
                                Cursor cd;
                                String date = sps.getString(English_to_tamil.this, "date");
                                if (date.equals("0")) {
                                    cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "' and isfinish='0' and gameid='" + gameid + "'");
                                    cd.moveToFirst();
                                } else {
                                    cd = newhelper4.getQry("SELECT * FROM newgamesdb4 where  levelid='" + levelid + "'  and gameid='" + gameid + "'");
                                    cd.moveToFirst();
                                }
                                String sas = null;
                                if (cd.getCount() != 0) {
                                    sas = cd.getString(cd.getColumnIndexOrThrow("hints"));
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
                                ans_txt.setVisibility(View.INVISIBLE);
                                clue_clear.setVisibility(View.INVISIBLE);

                                Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
                                ans_high.startAnimation(w_game);
                                ans_high.setVisibility(View.VISIBLE);
                                ans_high.setText(sas);
                                //Update QST
                                String datee = sps.getString(English_to_tamil.this, "date");
                                if (datee.equals("0")) {
                                    newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
                                } else {
                                    newhelper4.executeSql("UPDATE newgamesdb4 SET daily=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
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
        });

        ans_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verify("" + ans_txt.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        qwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });
        earncoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });
        clue_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                click.play(soundId1, sv, sv, 0, 0, sv);
                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });
        clue_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ans_txt.setText("");
                return false;
            }
        });
        c_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(English_to_tamil.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(English_to_tamil.this, "snd", "on");
                    c_settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(English_to_tamil.this, "snd", "off");
                    c_settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });
        c_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button1.startAnimation(shake);
                String ts = c_button1.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button2.startAnimation(shake);
                String ts = c_button2.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button3.startAnimation(shake);
                String ts = c_button3.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button4.startAnimation(shake);
                String ts = c_button4.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button5.startAnimation(shake);
                String ts = c_button5.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button6.startAnimation(shake);
                String ts = c_button6.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button7.startAnimation(shake);
                String ts = c_button7.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button8.startAnimation(shake);
                String ts = c_button8.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button9.startAnimation(shake);
                String ts = c_button9.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button10.startAnimation(shake);
                String ts = c_button10.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button11.startAnimation(shake);
                String ts = c_button11.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button12.startAnimation(shake);
                String ts = c_button12.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button13.startAnimation(shake);
                String ts = c_button13.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button14.startAnimation(shake);
                String ts = c_button14.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button15.startAnimation(shake);
                String ts = c_button15.getText().toString();
                ans_txt.append(ts);
            }
        });
        c_button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.button_shake);
                c_button16.startAnimation(shake);
                String ts = c_button16.getText().toString();
                ans_txt.append(ts);
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

    public void simple() {
        System.out.println("#######################answer" + answer);
        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";
        c_button4.setVisibility(View.GONE);
        c_button8.setVisibility(View.GONE);
        c_button12.setVisibility(View.GONE);
        c_button13.setVisibility(View.GONE);
        c_button14.setVisibility(View.GONE);
        c_button15.setVisibility(View.GONE);
        c_button16.setVisibility(View.GONE);
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
            c_button1.setText(letter1);
            c_button2.setText(letter2);
            c_button3.setText(letter3);
            c_button5.setText(answer);
            c_button6.setText(letter6);
            c_button7.setText(letter7);
            c_button9.setText(letter9);
            c_button10.setText(letter10);
            c_button11.setText(letter11);

        } else if (type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
        System.out.println("#######################answer" + answer);
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        c_button13.setVisibility(View.GONE);
        c_button14.setVisibility(View.GONE);
        c_button15.setVisibility(View.GONE);
        c_button16.setVisibility(View.GONE);
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
            c_button1.setText(letter1);
            c_button2.setText(letter2);
            c_button3.setText(answer);
            c_button4.setText(letter11);
            c_button5.setText(letter5);
            c_button6.setText(letter6);
            c_button7.setText(letter7);
            c_button8.setText(letter3);
            c_button9.setText(letter9);
            c_button10.setText(letter15);
            c_button11.setText(letter4);
            c_button12.setText(letter12);
              /*  c_button13.setText(letter10);
                c_button14.setText(letter14);
                c_button15.setText(letter12);
                c_button16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
                /*c_button13.setText(letter14);
                c_button14.setText(word2);
                c_button15.setText(letter10);
                c_button16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
               /* c_button13.setText(letter14);
                c_button14.setText(letter12);
                c_button15.setText(letter10);
                c_button16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
              /*  c_button13.setText(letter14);
                c_button14.setText(letter15);
                c_button15.setText(word1);
                c_button16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
               /* c_button13.setText(letter14);
                c_button14.setText(letter12);
                c_button15.setText(letter9);
                c_button16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
              /*  c_button13.setText(word6);
                c_button14.setText(letter11);
                c_button15.setText(letter13);
                c_button16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
               /* c_button13.setText(letter6);
                c_button14.setText(word1);
                c_button15.setText(letter13);
                c_button16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
              /*  c_button13.setText(letter6);
                c_button14.setText(letter11);
                c_button15.setText(word2);
                c_button16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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
              /*  c_button13.setText(letter6);
                c_button14.setText(letter11);
                c_button15.setText(word2);
                c_button16.setText(word8);*/

        }
    }

    public void hard() {
        String a = "கே,சே,பி,யா,தி,ரே,ஏ,வ்,ர்,கே,ஃ,ஓ,ப,ல்,இ,து,பு,மு,ரூ";
        System.out.println("#######################answer" + answer);
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
            c_button1.setText(letter1);
            c_button2.setText(letter2);
            c_button3.setText(answer);
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
        } else if (type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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


        } else if (type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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


        } else if (type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

        } else if (type == 9) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(answer, ",");
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

    private void verify(String val) {
        System.out.println("##################verify" + val);
        System.out.println("##################levelid" + levelid);
        System.out.println("##################gameid" + gameid);
        Cursor cs = newhelper4.getQry("select * from newgamesdb4 where hints LIKE'" + val + "'and isfinish='0'and levelid='" + levelid + "'and gameid='" + gameid + "'");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            win.play(soundId3, sv, sv, 0, 0, sv);
            ry = 1;
            //Toast.makeText(Clue_Game_Hard.this, "Correct Answer", Toast.LENGTH_SHORT).show();
            c_ans.setBackgroundResource(R.drawable.tick_background);
            c_ans.setEnabled(false);
            ans_high.setVisibility(View.VISIBLE);
            ans_high.setText(val);
            //bulb invisible

            //
            ans_txt.setVisibility(View.INVISIBLE);
            clue_clear.setVisibility(View.INVISIBLE);
            String date = sps.getString(English_to_tamil.this, "date");
            if (date.equals("0")) {
                newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
            } else {
                newhelper4.executeSql("UPDATE dailytest SET isfinish=1 WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
            }

            long ttstime = 0;
            long timeElapseds = SystemClock.elapsedRealtime() - focus.getBase();
            int hourss = (int) (timeElapseds / 3600000);
            int minutess = (int) (timeElapseds - hourss * 3600000) / 60000;
            int secondss = (int) (timeElapseds - hourss * 3600000 - minutess * 60000) / 1000;

            int mins = hourss * 60;
            int secs = mins * 60;
            int sec2s = minutess * 60;
            ttstime = secs + sec2s + secondss;

            System.out.println("###################Timertime" + ttstime);
            if (ttstime < 60) {
                score_type = 1;
            } else if (ttstime > 60) {
                score_type = 2;
            }

            coinanim();
        }


    }

    private void pressKey(int keycode) {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        ans_txt.onKeyDown(keycode, event);
    }

    public void coinanim() {
////
        if (score_type == 1) {
            //score intial

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
                    setSc();
                }
            }, 4000);
        } else if (score_type == 2) {
            //score intial
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
            c_coin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    c_coin.setVisibility(View.INVISIBLE);
                }
            }, transAnimation.getDuration());


            ////
            new Thread(new Runnable() {

                public void run() {
                    int es = e2 + 15;
                    while (e2 < es) {
                        try {
                            Thread.sleep(150);
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
            }, 1300);

            Handler handler21 = new Handler();
            handler21.postDelayed(new Runnable() {
                @Override
                public void run() {
//Score Setting
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx + 15;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");


                    Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                    ch.moveToFirst();
                    int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
                    int shh = sh + 40;
                    myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");
                    //
                    setSc();
                }
            }, 2500);

        } else {
            //score intial

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
            c_coin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    c_coin.setVisibility(View.INVISIBLE);
                }
            }, transAnimation.getDuration());


            new Thread(new Runnable() {

                public void run() {
                    int es = e2 + 10;
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
            }, 1200);

            Handler handler21 = new Handler();
            handler21.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Score Setting
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx + 10;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                    Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                    ch.moveToFirst();
                    int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
                    int shh = sh + 30;
                    myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");

                    //
                    setSc();
                }
            }, 2500);
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
        final LinearLayout rewardvideo = (LinearLayout) openDialog_s.findViewById(R.id.rewardvideo);
        final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);
        LinearLayout ads_layout = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);

        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sps.getInt(English_to_tamil.this, "reward_coin_txt") + "+நாணயங்கள் பெற");


        Animation myFadeInAnimation = AnimationUtils.loadAnimation(English_to_tamil.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(English_to_tamil.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
            //MainActivity.load_addFromMain_multiplayer(English_to_tamil.this, ads_layout);
            if (Utils.isNetworkAvailable(English_to_tamil.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(English_to_tamil.this, ads_layout);
            } else {
                ads_layout.setVisibility(View.GONE);
            }

            /*  if (loadaddcontent == 1) {
                if (native_adView3 != null) {
                    native_adView3.removeAllViews();
                }
                LayoutInflater inflater;
                inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.remote_config, null);
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
                View view1 = inflater.inflate(R.layout.remote_config, null);
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
        String date = sps.getString(English_to_tamil.this, "date");
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

        if (sps.getString(English_to_tamil.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(English_to_tamil.this, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }

        if (ry == 1) {

        } else {
            rewardvideo.setVisibility(View.INVISIBLE);
        }

        RelativeLayout adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);
        rewardvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(English_to_tamil.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(English_to_tamil.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedVideoAd.show();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedVideoAd.show();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    reward(English_to_tamil.this);
                                    Toast.makeText(English_to_tamil.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        vid_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(English_to_tamil.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(English_to_tamil.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedVideoAd.show();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedVideoAd.show();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    reward(English_to_tamil.this);
                                    Toast.makeText(English_to_tamil.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                        if (sps.getString(English_to_tamil.this,"watts_app_s").equals(""))
                        {
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Score Adding
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ", null);
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 20;
                                    String aStringx = Integer.toString(spx);
                                    score.setText(aStringx);
                                    ttscores.setText(aStringx);
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                                    sps.putString(English_to_tamil.this,"watts_app_s","yes");

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
                    //openFacebookSession();
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
        word.setText("ï¡Á");
        if (score_type == 0) {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        } else if (score_type == 0) {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        } else if (score_type == 0) {
            word.setText("");
            word.setText("ÜŸ¹î‹");
        }
        if (r == 1) {
            word.setText("");
            word.setText("ºòŸC ªêŒè");
            r = 0;
        }

        next_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sps.getInt(English_to_tamil.this, "purchase_ads") == 1) {

                } else {
                    //  advancads();
                    //   advancads_content();
                }

                score_type = 0;
                sps.putInt(getApplicationContext(), "cluetime", 0);
                if (sps.getInt(getApplicationContext(), "ins_ad_new") == 4) {
                    sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        if (interstitialAd_game == null || !interstitialAd_game.isAdLoaded()) {
                            dia_dismiss = 1;
                            openDialog_s.dismiss();
                            next();
                            ins_add();
                            return;
                        }
                        // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                        if (interstitialAd_game.isAdInvalidated()) {
                            dia_dismiss = 1;
                            openDialog_s.dismiss();
                            next();
                            ins_add();
                            return;
                        }
                        // Show the ad
                        interstitialAd_game.show();


                    /*    if (interstitialAd_game != null) {
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

            /*    dia_dismiss=1;
                openDialog_s.dismiss();*/
            }
        });
        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss != 1) {
                    sps.putString(English_to_tamil.this, "game_area", "on");

                    String date = sps.getString(English_to_tamil.this, "date");
                    if (date.equals("0")) {

                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(English_to_tamil.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            openDialog_s.dismiss();
                            finish();
                        }


                    } else {
                        if (sps.getString(English_to_tamil.this, "Exp_list").equals("on")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(English_to_tamil.this, Expandable_List_View.class);
                            startActivity(i);

                        } else {
                            if (main_act.equals("")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(English_to_tamil.this, MainActivity.class);
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


            }
        });

       /* openDialog_s.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });*/
        if (!isFinishing()) {
            openDialog_s.show();
        }

    }

    public void onBackPressed() {
 /*   public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if(keyCode==KeyEvent.KEYCODE_BACK) {*/

        sps.putString(English_to_tamil.this, "game_area", "on");
        sps.putInt(English_to_tamil.this, "addlodedd", 0);
        s = 1;
        openDialog_p = new Dialog(English_to_tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
        TextView no = (TextView) openDialog_p.findViewById(R.id.no);
        CheckBox checkbox_back = (CheckBox) openDialog_p.findViewById(R.id.checkbox_back);
        LinearLayout ads_lay = (LinearLayout) openDialog_p.findViewById(R.id.fl_adplaceholder);
        // load_addcontent2(context, ads_lay);


        ads_lay.setVisibility(View.GONE);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dates = sps.getString(English_to_tamil.this, "date");
                int pos;
                if (dates.equals("0")) {
                    pos = 1;
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    focus.stop();
                    newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                    //     myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                } else {
                    pos = 2;
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    focus.stop();
                    newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");

                    //    myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                }

                String date = sps.getString(English_to_tamil.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(English_to_tamil.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                } else {
                    if (sps.getString(English_to_tamil.this, "Exp_list").equals("on")) {
                        finish();
                        Intent i = new Intent(English_to_tamil.this, Expandable_List_View.class);
                        startActivity(i);
                    } else {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(English_to_tamil.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    }
                }
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

    //*********************reward videos process 3***********************


    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(English_to_tamil.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }

    //reward videos***********************//
    public void vidcoinearn() {
        final Dialog openDialog = new Dialog(English_to_tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(English_to_tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "onConnected");
        //  updateUI();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: " + i);
        mGoogleApiClient.connect();
        // updateUI();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed");
        if (mIsResolving) {
            // The application is attempting to resolve this connection failure already.
            Log.d(TAG, "onConnectionFailed: already resolving");
            return;
        }

        if (mSignInClicked || mAutoStartSignIn) {
            mSignInClicked = false;
            mAutoStartSignIn = false;

            // Attempt to resolve the connection failure.
            Log.d(TAG, "onConnectionFailed: begin resolution.");
            mIsResolving = BaseGameUtils.resolveConnectionFailure(this, mGoogleApiClient,
                    connectionResult, RC_SIGN_IN, getString(R.string.signin_other_error));
        }

        //  updateUI();
    }

    private void ins_add() {
        //10 geme once intersttial ad show

        interstitialAd_game = new com.facebook.ads.InterstitialAd(English_to_tamil.this, getString(R.string.industrial_between));
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                dia_dismiss = 1;
                openDialog_s.dismiss();
                next();
                ins_add();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed


            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback

            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback

            }
        };

        if (sps.getInt(English_to_tamil.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            interstitialAd_game.loadAd(
                    interstitialAd_game.buildLoadAdConfig()
                            .withAdListener(interstitialAdListener)
                            .build());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }

        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        focus.start();
        String date = sps.getString(English_to_tamil.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");
            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            pos = 2;
            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");
            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        }
    }

    public void dialog(int i) {
        final Dialog openDialog_earncoin = new Dialog(English_to_tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 1;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(English_to_tamil.this, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(English_to_tamil.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                            // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");

                            // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        }
                        reward_progressBar.dismiss();
                        rewardedVideoAd.show();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        reward(English_to_tamil.this);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(English_to_tamil.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
                /*rvo = 1;

                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(English_to_tamil.this, "" + "Reward video", "Loading...");

                    if (mRewardedVideoAd.isLoaded()) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(English_to_tamil.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                            // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");

                            // myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
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
                                    Toast.makeText(English_to_tamil.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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


                if (isNetworkAvailable()) {


                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(English_to_tamil.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                            //  myDbHelper.executeSql("UPDATE right_order SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        } else {
                            pos = 2;
                            newhelper3.executeSql("UPDATE right_order SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");

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

    protected void onResume() {
        super.onResume();

        if (!mGameOver && mGamePaused) {
            //resumeGame();
        }

        //uiHelper.onResume();


        if (sps.getString(English_to_tamil.this, "riddle_time_start").equals("")) {
            sps.putString(English_to_tamil.this, "riddle_time_start", "yes");
        } else {
            String date = sps.getString(English_to_tamil.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                pos = 1;
                cs = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "' and levelid='" + levelid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
                }
            } else {
                pos = 2;
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

    }

    public void permission(final String a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(English_to_tamil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                helpshare(a);
            } else {
                if (sps.getString(English_to_tamil.this, "permission_grand").equals("")) {
                    sps.putString(English_to_tamil.this, "permission_grand", "yes");
                    //  First_register("yes");
                    AlertDialog alertDialog = new AlertDialog.Builder(English_to_tamil.this).create();
                    alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர  பின்வரும் permission-யை  allow செய்யவேண்டும்");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if ((ContextCompat.checkSelfPermission(English_to_tamil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(English_to_tamil.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                                    } else {
                                        helpshare(a);
                                    }
                                }
                            });

                    alertDialog.show();

                } else {
                    if ((ContextCompat.checkSelfPermission(English_to_tamil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(English_to_tamil.this, "permission") == 2) {
                            AlertDialog alertDialog = new AlertDialog.Builder(English_to_tamil.this).create();
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
                                            dialog.dismiss();
                                        }
                                    });


                            alertDialog.show();
                        } else {
                            if ((ContextCompat.checkSelfPermission(English_to_tamil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(English_to_tamil.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                            } else {
                                helpshare(a);
                            }
                        }
                    } else {
                        if ((ContextCompat.checkSelfPermission(English_to_tamil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(English_to_tamil.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                        } else {
                            helpshare(a);
                        }
                    }

                }
            }

        } else {
            helpshare(a);
        }
    }

    private void helpshare(String a) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Bitmap bitmap = Bitmap.createBitmap(w_head.getWidth(), w_head.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        w_head.draw(canvas);


        String root = Environment.getExternalStorageDirectory().toString();
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
                    String date = sps.getString(English_to_tamil.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                        newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "'");

                        //myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    } else {
                        pos = 2;
                        newhelper4.executeSql("UPDATE newgamesdb4 SET playtime='" + ttstop + "' WHERE levelid='" + levelid + "' and gameid='" + gameid + "' and daily='0'");

                        // myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                    }

                    Uri uri = Uri.fromFile(file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் புதிருக்கு பதில் இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 152) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(English_to_tamil.this, "permission", 1);
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
                        sps.putInt(English_to_tamil.this, "permission", 2);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(English_to_tamil.this, "permission", 0);
                    }
                }
            }

        }
    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(English_to_tamil.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);

        TextView exit = (TextView) openDialog.findViewById(R.id.exit);

        String date = sps.getString(English_to_tamil.this, "date");
        if (date.equals("0")) {
            next_game.setText("புதிருக்கு பதில்  தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("தினசரி புதிருக்கு பதில்  புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள்  சிறப்பாக விளையாட காத்திருக்கும்  விளையாட்டுக்கள்.");
        }
        openDialog.setCancelable(false);
        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, English_to_tamil.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
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

        TextView odd_man_out = (TextView) openDialog.findViewById(R.id.odd_man_out);
        TextView matchword = (TextView) openDialog.findViewById(R.id.matchword);
        matchword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Odd_man_out.class);
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
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Ote_to_Tamil.class);
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
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, English_to_tamil.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, WordError_correction.class);
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
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });
        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, English_to_tamil.class);
                startActivity(i);
            }
        });

        TextView quiz = (TextView) openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = (TextView) openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = (TextView) openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(English_to_tamil.this);
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
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Find_words_from_picture.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Quiz_Game.class);
                startActivity(i);
            }
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(English_to_tamil.this);
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
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Jamble_word_game.class);
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
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Missing_Words.class);
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
                sps.putString(English_to_tamil.this, "date", "0");
                Intent i = new Intent(English_to_tamil.this, Find_difference_between_pictures.class);
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
                    Intent i = new Intent(English_to_tamil.this, MainActivity.class);
                    startActivity(i);
                } else {
                    sps.putString(English_to_tamil.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                sps.putString(English_to_tamil.this, "date", "0");

               /* finish();
                openDialog.dismiss();
                sps.putString(Riddle_game.this, "date", "0");
                Intent i = new Intent(Riddle_game.this, MainActivity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });

    }

    //*** In ad area **
    public void showcase_dismiss() {
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("##########################sps.getString" + sps.getString(English_to_tamil.this, "showcase_dismiss_en_to_intro"));
                if (sps.getString(English_to_tamil.this, "showcase_dismiss_en_to_intro").equals("")) {
                    System.out.println("######################showcase_dismiss");
                    showcase_dismiss();
                } else {
                    System.out.println("######################en_to_intro_time_start");
                    sps.putString(English_to_tamil.this, "en_to_intro_time_start", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }
            }
        }, 800);
    }

    public void reward(final Context context) {
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
                if (reward_status == 1) {
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx + mCoinCount;
                    String aStringx = Integer.toString(spx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");


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
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());
    }

}
