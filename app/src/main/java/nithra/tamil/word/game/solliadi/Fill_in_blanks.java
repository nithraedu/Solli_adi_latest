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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
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
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

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
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native_Senthamil_Thedal_Native_Banner;

public class Fill_in_blanks extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Download_completed {

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

    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    TextView ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8;
    TextView c_button1, c_button2, c_button3, c_button4, c_button5, c_button6, c_button7, c_button8, c_button9, c_button10, c_button11,
            c_button12, c_button13, c_button14, c_button15, c_button16;
    TextView score;
    String letters = "";
    String gameid = "13", answers;
    int levelid = 0;
    int letter_length;
    int minmum = 1;
    int maximum_s = 3;
    int maximum = 3;
    int minmum_s = 1;
    int randomno;
    int randomno_set = 0;
    int word_type_random;
    int word_maximum = 3;
    int word_minimum = 2;
    TextView c_word_number, c_coins, c_settings, c_ans;
    int maximum_show = 3, minmum_show = 1, randomno_show;
    Chronometer focus;
    DataBaseHelper myDbHelper;
    SharedPreference sps = new SharedPreference();
    int sv = 0;
    String string_arrange_value = "";
    ArrayList<String> mylist = new ArrayList<String>();
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
    static int ry;
    static int rvo = 0;
    static int mCoinCount=20;


    private MaxInterstitialAd ins_game,game_exit_ins;
    int f_sec;
    TextView to_no;
    int r = 0;
    TextView shareq, h_gplues, h_watts_app, h_facebook, ans_high;
    int share_name = 0;
    RelativeLayout w_head, helpshare_layout;
    long ttstop;
    LinearLayout adds;
    private GoogleApiClient mGoogleApiClient;
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
    int dia_dismiss=0;
    int coin_anim=0;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        newhelper = new Newgame_DataBaseHelper(Fill_in_blanks.this);
        newhelper2 = new Newgame_DataBaseHelper2(Fill_in_blanks.this);
        newhelper3 = new Newgame_DataBaseHelper3(Fill_in_blanks.this);
        newhelper4 = new Newgame_DataBaseHelper4(Fill_in_blanks.this);
        myDbHelper = new DataBaseHelper(Fill_in_blanks.this);




        /*String gid = "13";
        String qid = "";
        for (int i = 0; i<=999; i++){
            if (qid.equals("")){
                qid = "" +i;
            } else {
                qid = qid + "," + i;
            }
        }
        System.out.println("---qid : " +qid);
        System.out.println("---qid : " + "UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='16'");
        newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE levelid in (" + qid + ") and gameid='13'");
*/

        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Fill_in_blanks.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Fill_in_blanks.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Fill_in_blanks.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Fill_in_blanks.this, R.raw.coins, 1);

        ImageView prize_logo=(ImageView)findViewById(R.id.prize_logo);
        /*final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        prize_logo.startAnimation(pendulam);*/
        if (sps.getInt(Fill_in_blanks.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                .addScope(Drive.SCOPE_APPFOLDER) // SavedGames
                .build();

        find();
        click();
        next();
        //reward(Fill_in_blanks.this);
        rewarded_ad();
        if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(Fill_in_blanks.this).setMediationProvider("max");
            AppLovinSdk.initializeSdk(Fill_in_blanks.this, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    industrialload_game();
                    game_exit_ins_ad();
                }
            });
        }

        //loads_ads_banner();
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 0) {
        if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {

        Ad_NativieUtils.load_add_facebook(this,getResources().getString(R.string.Senthamil_Thedal_Native_Banner_new),adds);
        }else {
            adds.setVisibility(View.GONE);
        }
        }else{
                adds.setVisibility(View.GONE);
            }


        if (sps.getString(Fill_in_blanks.this, "fill_to_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Fill_in_blanks.this, "sequence example fill");

            sequence.setConfig(config);

            sequence.addSequenceItem(c_ans, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Fill_in_blanks.this)
                    .setTarget(helpshare_layout)
                    .setDismissText("சரி")
                    .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {

                            if (position == 1) {
                                sps.putString(Fill_in_blanks.this, "fill_intro_time_start", "yes");
                                sps.putString(Fill_in_blanks.this, "showcase_dismiss_fill_intro", "yes");

                                focus.setBase(SystemClock.elapsedRealtime());
                                focus.start();

                            }
                        }
                    });

            sps.putString(Fill_in_blanks.this, "fill_to_intro", "no");
            sequence.start();


        }

    }

    private void loads_ads_banner() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            adds.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);

        } else {
            if (Utils.isNetworkAvailable(Fill_in_blanks.this)){
                fb_native_Senthamil_Thedal_Native_Banner(Fill_in_blanks.this,native_banner_ad_container);

                /*  if (sps.getInt(Fill_in_blanks.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Fill_in_blanks.this,native_banner_ad_container);
                }else {
                    fb_native(Fill_in_blanks.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }
    }

    private void click_txt_change() {

        earncoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });

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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString();
                } else if (letter_length == 7) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString();
                } else if (letter_length == 8) {
                    ans_verify = ed1.getText().toString() + "" + ed2.getText().toString() + "" + ed3.getText().toString() + "" + ed4.getText().toString() + "" + ed5.getText().toString()
                            + "" + ed6.getText().toString() + "" + ed7.getText().toString() + "" + ed8.getText().toString();
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
            if (coin_anim==0){
                coinanim();
                price_update();
                coin_anim=1;
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
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "தவறான பதில்", Snackbar.LENGTH_SHORT);
        final View view = snackbar.getView();
        TextView textView = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
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

    private void next() {

        coin_anim=0;
        Random rns = new Random();
        randomno_set = rns.nextInt(maximum_s - minmum_s + 1) + minmum_s;
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 1) {
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        }else {
            if (Utils.isNetworkAvailable(Fill_in_blanks.this)){
                native_banner_ad_container.setVisibility(View.VISIBLE);
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }
        w_head.setVisibility(View.VISIBLE);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndex("coins"));
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
            levelid = c.getInt(c.getColumnIndex("levelid"));
            letters = c.getString(c.getColumnIndex("letters"));
            answers = c.getString(c.getColumnIndex("answer"));
            int playtime = c.getInt(c.getColumnIndex("playtime"));
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

            //String r= String.valueOf(w_id);
            //lt_id.setText(r);
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
        String str = ""+mylist;
        str = str.replace("[","");
        str = str.replace("]","");
        str = str.replace(" ","");


        sps.putString(Fill_in_blanks.this, "value_data_blanks", "" + str);
        System.out.println("mylist" + mylist);
    }

    private void click() {
        qwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });
        c_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                cfw.moveToFirst();
                int sk = cfw.getInt(cfw.getColumnIndex("coins"));
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
                        final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                                c_ans.setEnabled(true);
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
        clear_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        c_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        c_button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        c_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

    }

    private void find() {
        c_settings = (TextView) findViewById(R.id.c_settings);
        String snd = sps.getString(Fill_in_blanks.this, "snd");
        if (snd.equals("off")) {
            c_settings.setBackgroundResource(R.drawable.sound_off);
            sv = 0;
        } else if (snd.equals("on")) {
            c_settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;
        }
        helpshare_layout = (RelativeLayout) findViewById(R.id.helpshare_layout);
        w_head = (RelativeLayout) findViewById(R.id.clue_head);

        to_no = (TextView) findViewById(R.id.c_word_number);
        ed1 = (TextView) findViewById(R.id.ed1);
        ed2 = (TextView) findViewById(R.id.ed2);
        ed3 = (TextView) findViewById(R.id.ed3);
        ed4 = (TextView) findViewById(R.id.ed4);
        ed5 = (TextView) findViewById(R.id.ed5);
        ed6 = (TextView) findViewById(R.id.ed6);
        ed7 = (TextView) findViewById(R.id.ed7);
        ed8 = (TextView) findViewById(R.id.ed8);
        clear_value = (TextView) findViewById(R.id.clear_value);
        ans_high = (TextView) findViewById(R.id.ans_highlite);
        list4 = (LinearLayout) findViewById(R.id.list4);

        to_no = (TextView) findViewById(R.id.c_word_number);
        c_coin = (TextView) findViewById(R.id.c_coins);
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
        score = (TextView) findViewById(R.id.c_score_edit);
        c_coins = (TextView) findViewById(R.id.c_coins);
        c_ans = (TextView) findViewById(R.id.c_ans);
        c_word_number = (TextView) findViewById(R.id.c_word_number);
        focus = (Chronometer) findViewById(R.id.c_time_edit);

        h_gplues = (TextView) findViewById(R.id.ch_gplues);
        h_watts_app = (TextView) findViewById(R.id.ch_watts_app);
        h_facebook = (TextView) findViewById(R.id.ch_facebook);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        earncoin = (TextView) findViewById(R.id.earncoin);
        qwt = (LinearLayout) findViewById(R.id.qwt);
        ed1.setVisibility(View.GONE);
        ed2.setVisibility(View.GONE);
        ed3.setVisibility(View.GONE);
        ed4.setVisibility(View.GONE);
        ed5.setVisibility(View.GONE);
        ed6.setVisibility(View.GONE);
        ed7.setVisibility(View.GONE);
        ed8.setVisibility(View.GONE);
        c_coins.setVisibility(View.INVISIBLE);

        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
        });
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
        });
        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
        });
        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
        });
        ed5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
        });
        ed6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
        });
        ed7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
        });
        ed8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
            }
        });
        ed8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return true;
            }
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
              /*  c_button13.setText(letter10);
                c_button14.setText(letter14);
                c_button15.setText(letter12);
                c_button16.setText(letter13);*/


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
                /*c_button13.setText(letter14);
                c_button14.setText(word2);
                c_button15.setText(letter10);
                c_button16.setText(letter8);*/


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
               /* c_button13.setText(letter14);
                c_button14.setText(letter12);
                c_button15.setText(letter10);
                c_button16.setText(letter13);*/


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
              /*  c_button13.setText(letter14);
                c_button14.setText(letter15);
                c_button15.setText(word1);
                c_button16.setText(word2);*/


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
               /* c_button13.setText(letter14);
                c_button14.setText(letter12);
                c_button15.setText(letter9);
                c_button16.setText(letter10);*/


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
              /*  c_button13.setText(word6);
                c_button14.setText(letter11);
                c_button15.setText(letter13);
                c_button16.setText(word2);
*/


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
               /* c_button13.setText(letter6);
                c_button14.setText(word1);
                c_button15.setText(letter13);
                c_button16.setText(word3);*/


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
              /*  c_button13.setText(letter6);
                c_button14.setText(letter11);
                c_button15.setText(word2);
                c_button16.setText(letter10);*/

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
              /*  c_button13.setText(letter6);
                c_button14.setText(letter11);
                c_button15.setText(word2);
                c_button16.setText(word8);*/

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
        } else if (letter_length == 2)

        {

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
            if (openDialog_p!=null)
            {
                if (openDialog_p.isShowing())
                {
                    openDialog_p.dismiss();
                }
            }
          //  openDialog_p.dismiss();
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

        ImageView prize_logo=(ImageView)openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Fill_in_blanks.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sps.getInt(Fill_in_blanks.this, "reward_coin_txt") + "+நாணயங்கள் பெற");

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Fill_in_blanks.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 1) {
            ads_layout.setVisibility(View.GONE);
        } else {
           // New_Main_Activity.load_addFromMain_multiplayer(Fill_in_blanks.this, ads_layout);
            if (Utils.isNetworkAvailable(Fill_in_blanks.this)){
                //New_Main_Activity.load_add_fb_rect_score_screen(Fill_in_blanks.this, ads_layout);
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
        String date = sps.getString(Fill_in_blanks.this, "date");
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

        RelativeLayout adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);
        //  final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);

        vid_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");
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
                                    //reward(Fill_in_blanks.this);
                                    rewarded_ad();
                                    Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");
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
                                    //reward(Fill_in_blanks.this);
                                    rewarded_ad();
                                    Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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

                        if (sps.getString(Fill_in_blanks.this,"watts_app_s").equals(""))
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

                                    sps.putString(Fill_in_blanks.this,"watts_app_s","yes");

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
        }else {
            word.setText("");
            word.setText("Iè ÜŸ¹î‹");
        }
        /////////////////////////////////////////Reward tittle////////////////////////////////////////////


        next_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 1) {
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();
                } else {

                    sps.putInt(getApplicationContext(), "cluetime", 0);
                    if (sps.getInt(getApplicationContext(), "ins_ad_new") == 4) {
                        sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            if(ins_game == null || !ins_game.isReady()) {
                                next();
                                industrialload_game();
                                dia_dismiss=1;
                                openDialog_s.dismiss();
                                return;
                            }
                            else{
                                ins_game.showAd();
                            }



                      /*  if (interstitialAd_game != null) {
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
                    // advancads_content();
                    //  advancads();
                }
                //noclue=0;


                /*if (Utils.isNetworkAvailable(getApplicationContext())) {
                    if(getApiClient().isConnected()) {
                        if (isSignedIn()) {
                            int k1 = 0;
                            Cursor sc2 = myDbHelper.getQry("select * from score ");
                            sc2.moveToFirst();
                            if (sc2.getCount() != 0) {
                                k1 = sc2.getInt(sc2.getColumnIndex("l_points"));
                            }
                            Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard), k1);
                        }
                    }
                }*/

              /*  dia_dismiss=1;
                openDialog_s.dismiss();*/
            }
        });

        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss!=1){
                    sps.putString(Fill_in_blanks.this, "game_area", "on");
                        String date = sps.getString(Fill_in_blanks.this, "date");
                        if (date.equals("0")) {
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


                }else {
                    dia_dismiss=0;
                }

            }
        });

      /*  openDialog_s.setOnKeyListener(new DialogInterface.OnKeyListener() {
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

        ins_game = new MaxInterstitialAd(getResources().getString(R.string.Senthamil_Thedal_Ins_new), this);
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









    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Fill_in_blanks.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    public void vidcoinearn() {
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea=ea+setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Fill_in_blanks.this, "date");
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
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(Fill_in_blanks.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                helpshare(a);
            }else {
                if (sps.getString(Fill_in_blanks.this, "permission_grand").equals("")) {
                    sps.putString(Fill_in_blanks.this, "permission_grand", "yes");
                    //  First_register("yes");
                    AlertDialog alertDialog = new AlertDialog.Builder(Fill_in_blanks.this).create();
                    alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர  பின்வரும் permission-யை  allow செய்யவேண்டும்");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if ((ContextCompat.checkSelfPermission(Fill_in_blanks.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(Fill_in_blanks.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                                    } else {
                                        helpshare(a);
                                    }
                                }
                            });

                    alertDialog.show();

                } else {
                    if ((ContextCompat.checkSelfPermission(Fill_in_blanks.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(Fill_in_blanks.this, "permission") == 2) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Fill_in_blanks.this).create();
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
                                            if (sps.getString(Fill_in_blanks.this, "riddle_time_start").equals("")) {
                                                sps.putString(Fill_in_blanks.this, "riddle_time_start", "yes");
                                            } else {
                                                String date = sps.getString(Fill_in_blanks.this, "date");
                                                int pos;
                                                Cursor cs;
                                                long dscore = 0;
                                                int noofclue = 0;
                                                if (date.equals("0")) {
                                                    pos = 1;
                                                    cs = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "' and levelid='" + levelid + "'");
                                                    cs.moveToFirst();
                                                    if (cs.getCount() != 0) {
                                                        dscore = cs.getInt(cs.getColumnIndex("playtime"));
                                                    }
                                                } else {
                                                    pos = 2;
                                                }
                                                focus.setBase(SystemClock.elapsedRealtime() + dscore);
                                                focus.start();
                                            }
                                            dialog.dismiss();
                                        }
                                    });


                            alertDialog.show();
                        } else {
                            if ((ContextCompat.checkSelfPermission(Fill_in_blanks.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(Fill_in_blanks.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                            } else {
                                helpshare(a);
                            }
                        }
                    } else {
                        if ((ContextCompat.checkSelfPermission(Fill_in_blanks.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(Fill_in_blanks.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
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
                    String date = sps.getString(Fill_in_blanks.this, "date");
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

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Fill_in_blanks.this, Fill_in_blanks.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் விடுபட்ட எழுத்துக்களை கண்டுபிடி இதற்கான விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்  https://goo.gl/bRqmah");
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

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
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Fill_in_blanks.this, "permission", 2);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Fill_in_blanks.this, "permission", 0);
                    }
                }
            }

        }
    }

    protected void onResume() {
        super.onResume();

        if (!mGameOver && mGamePaused) {
            //resumeGame();
        }

        //uiHelper.onResume();




      /*  if (sps.getInt(Riddle_game.this, "addlodedd") == 1) {
            New_Main_Activity.load_addFromMain(Riddle_game.this, adds);
        } else {
            if (Utils.isNetworkAvailable(Riddle_game.this)) {
                adds = (LinearLayout) findViewById(R.id.ads_lay);
                sps.putInt(Riddle_game.this, "addlodedd", 2);
                System.out.println("@IMG");
                final AdView adView = new AdView(Riddle_game.this);
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

        if (sps.getString(Fill_in_blanks.this, "riddle_time_start").equals("")) {
            sps.putString(Fill_in_blanks.this, "riddle_time_start", "yes");
        } else {
            String date = sps.getString(Fill_in_blanks.this, "date");
            int pos;
            Cursor cs;
            long dscore = 0;
            int noofclue = 0;
            if (date.equals("0")) {
                pos = 1;
                cs = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "' and levelid='" + levelid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                }
            } else {
                pos = 2;
               /* cs = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + questionid + "'");
                cs.moveToFirst();
                if (cs.getCount() != 0) {
                    dscore = cs.getInt(cs.getColumnIndex("playtime"));
                }*/
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
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);


            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String dates = sps.getString(Fill_in_blanks.this, "date");
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

                    //ad
                    if (sps.getInt(Fill_in_blanks.this, "purchase_ads") == 0) {
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

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Fill_in_blanks.this, "date");
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
        final Dialog openDialog_earncoin = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Fill_in_blanks.this, "date");
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
                        rewardedAd.showAd();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        //reward(Fill_in_blanks.this);
                        rewarded_ad();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
               /* rvo = 1;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");

                    if (mRewardedVideoAd.isLoaded()) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Fill_in_blanks.this, "date");
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
                                    Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }*/
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

               /* if (isNetworkAvailable()) {

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
                }   // toast("இணையதள சேவையை சரிபார்க்கவும் ");*/
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
                        String date = sps.getString(Fill_in_blanks.this, "date");
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

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);

        TextView exit = (TextView) openDialog.findViewById(R.id.exit);

        String date = sps.getString(Fill_in_blanks.this, "date");
        if (date.equals("0")) {
            next_game.setText("விடுபட்ட எழுத்துக்களை கண்டுபிடி  தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("தினசரி விடுபட்ட எழுத்துக்களை கண்டுபிடி  புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள்  சிறப்பாக விளையாட காத்திருக்கும்  விளையாட்டுக்கள்.");
        }
        openDialog.setCancelable(false);
        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Picture_Game_Hard.class);
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
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Odd_man_out.class);
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
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Ote_to_Tamil.class);
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
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, WordError_correction.class);
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
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });
        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });

        TextView quiz = (TextView) openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = (TextView) openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = (TextView) openDialog.findViewById(R.id.match_words);
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

        match_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Find_words_from_picture.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Quiz_Game.class);
                startActivity(i);
            }
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Fill_in_blanks.this);
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
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Jamble_word_game.class);
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
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Missing_Words.class);
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
                sps.putString(Fill_in_blanks.this, "date", "0");
                Intent i = new Intent(Fill_in_blanks.this, Find_difference_between_pictures.class);
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
                    Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Fill_in_blanks.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                sps.putString(Fill_in_blanks.this, "date", "0");

               /* finish();
                openDialog.dismiss();
                sps.putString(Riddle_game.this, "date", "0");
                Intent i = new Intent(Riddle_game.this, New_Main_Activity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });

    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
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
                NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                native_banner_ad_container.setVisibility(View.INVISIBLE);
                w_head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fill_in_blanks.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                sps.putInt(Fill_in_blanks.this, "goto_sett", 1);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String date = sps.getString(Fill_in_blanks.this, "date");
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


            } else {
            }
        }

    }
    public void share_earn(int a) {
        final Dialog openDialog = new Dialog(Fill_in_blanks.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndex("coins"));
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
        final String str_date1 =  str_day1 + "-" + str_month1 + "-" + cur_year1;

        Date date1=new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        final String date=sdf.format(date1);


        //  TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView tomarrow_coin_earn = (TextView) openDialog.findViewById(R.id.tomarrow_coin_earn);
        //   TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        ea = 100;
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                    int spx = skx + ea;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    sps.putString(Fill_in_blanks.this, "daily_bonus_date", date);
                }

                openDialog.dismiss();

            }
        });

        System.out.println("############################^^^^^^^^^^^^^^currentdate"+str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate"+sps.getString(Fill_in_blanks.this, "daily_bonus_date"));

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
        prize_data_update(Fill_in_blanks.this,ea);
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
      /*  final int vals = reward_play_count * 100;
        ea = ea + vals;*/
        coin_value.setText("" + ea);
        setval_vid=ea;
        Random rn = new Random();
        randomno = rn.nextInt(maximum - minmum + 1) + minmum;

        //String r= String.valueOf(w_id);
        //lt_id.setText(r);
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

        extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);

        TextView coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
        ea = 100;
        final int vals=reward_play_count*100;
        ea=ea+vals;
        coin_value.setText("" + ea);

        extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        extra_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 1;
                extra_coin_s = 1;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Fill_in_blanks.this, "" + "Reward video", "Loading...");
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
                                    //reward(Fill_in_blanks.this);
                                    rewarded_ad();
                                    Toast.makeText(Fill_in_blanks.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
                     /*   b_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openDialog.dismiss();
                            }
                        });*/
        openDialog.show();
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
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        w_head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fill_in_blanks.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Fill_in_blanks.this)) {
                    download_datas();
                } else {
                    NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                    native_banner_ad_container.setVisibility(View.INVISIBLE);
                    w_head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fill_in_blanks.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                            .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                    sps.putInt(Fill_in_blanks.this, "goto_sett", 1);


                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    sps.putString(Fill_in_blanks.this, "game_area", "on");
                                    String date = sps.getString(Fill_in_blanks.this, "date");
                                    if (date.equals("0")) {
                                        backexitnet();
                                    } else {
                                        backexitnet();
                                    }
                                   /* Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
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
               /* Intent i = new Intent(Fill_in_blanks.this, New_Main_Activity.class);
                startActivity(i);*/
                sps.putString(Fill_in_blanks.this, "game_area", "on");
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    private void download_datas() {
        Cursor cz = newhelper4.getQry("select * from newgamesdb4 where gameid='" + gameid + "'order by levelid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndex("levelid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Fill_in_blanks.this, questionid_d, "" + gameid);
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
                    Toast.makeText(Fill_in_blanks.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        // uiHelper.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
    }
}
