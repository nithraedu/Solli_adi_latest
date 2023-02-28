package nithra.tamil.word.game.solliadi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

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


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.material.snackbar.Snackbar;
import com.google.example.games.basegameutils.BaseGameActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.adutils.GameExitUtils;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.fb_addload_score_screen;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.data_download_url;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.img_down_url;

public class Find_difference_between_pictures extends BaseGameActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Download_completed {
    int fb_reward = 0;
   // RewardedVideoAd rewardedVideoAd;
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
    // Facebook variable starts

    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void download_completed(String status) {
        System.out.println("#############################status" + status);
        if (status.equals("nodata")) {
            nextgamesdialog();
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    next();
                    System.out.println("jas1");
                }
            }, 500);

        }
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

    CustomKeyboard mCustomKeyboard;
    Newgame_DataBaseHelper5 newhelper5;
    Newgame_DataBaseHelper6 newhelper6;
    Chronometer focus;
    TextView score, ans1, ans2, ans3, ans4, ans5, ans6, ans7, clear, questionid, p_facebook, p_watts_app;
    ImageView image_1, image_2, value_ans1, value_ans2, value_ans3, value_ans4, value_ans5, value_ans6, value_ans7;
    AppCompatEditText ans_editer;
    SharedPreference sps = new SharedPreference();
    String gameid = "20";
    String question_id = "", question = "", answer = "";
    int u_id = 0;
    int rdvalu;
    String qs1 = "", qs2 = "";
    DataBaseHelper myDbHelper;
    LinearLayout anslist2, list2_pic;
    TextView verify;

    SoundPool click, win, coin, worng, cr_ans, spz4;
    int soundId1, soundId2, soundId3, soundId4, soundId5;
    int sv = 0;
    int ans_count = 0, final_ans_count = 0;
    String isdown = "0";
    TextView p_setting;
    Long ttstop;
    int b_score = 0;
    Dialog openDialog_s;
    TextView next_continue;
    TextView ttscores;
    LinearLayout ads_layout_bottom;
    static int rvo = 0;
    TextView tx1, tx2;
    static int mCoinCount = 20;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    int setval_vid;
    Dialog openDialog;
    TextView coin_value;
    int minmumd = 1;
    int maximumd = 4;
    int randomnod;
    int case2 = 0, tot2 = 30, tt_case2, tt_tot2;
    int y;
    int answer_types = 0;
    Typeface tyr;
    int dia_dismiss = 0;

    private MaxInterstitialAd ins_game,game_exit_ins;
    private GoogleApiClient mGoogleApiClient;
    int spxdr = 0;
    int answerlength;
    Dialog openDialogk;
    RelativeLayout head;
    int share_name = 0;
    Dialog openDialog_p;
    LinearLayout ads_lay, qwt;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    DownloadFileAsync downloadFileAsync;
    ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    String email = "";
    public static int chr = 0;
    TextView p_coins;
    int e2;
    int setting_access = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_difference_between_pictures);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mCustomKeyboard = new CustomKeyboard(this, R.id.keyboardview, R.xml.hexkbd);
        mCustomKeyboard.registerEditText(R.id.ans_editer);
        newhelper5 = new Newgame_DataBaseHelper5(this);
        newhelper6 = new Newgame_DataBaseHelper6(this);
        myDbHelper = new DataBaseHelper(this);

        myDbHelper = new DataBaseHelper(Find_difference_between_pictures.this);
        newhelper5 = new Newgame_DataBaseHelper5(Find_difference_between_pictures.this);
        newhelper = new Newgame_DataBaseHelper(Find_difference_between_pictures.this);
        newhelper2 = new Newgame_DataBaseHelper2(Find_difference_between_pictures.this);
        newhelper3 = new Newgame_DataBaseHelper3(Find_difference_between_pictures.this);
        newhelper4 = new Newgame_DataBaseHelper4(Find_difference_between_pictures.this);




        /*String gid = "20";
        String qid = "";
        for (int i = 1; i<=29; i++){
            if (qid.equals("")){
                qid = "" +i;
            } else {
                qid = qid + "," + i;
            }
        }
        newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid in (" + qid + ") and gameid='20'");
*/
        if (sps.getString(Find_difference_between_pictures.this, "new_user_db").equals("")) {

        } else {
            if (sps.getString(Find_difference_between_pictures.this, "new_user_db").equals("on")) {
                sps.putString(Find_difference_between_pictures.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(Find_difference_between_pictures.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Find_difference_between_pictures.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Find_difference_between_pictures.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Find_difference_between_pictures.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Find_difference_between_pictures.this, R.raw.coins, 1);
        cr_ans = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId5 = cr_ans.load(Find_difference_between_pictures.this, R.raw.cr_ans, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Find_difference_between_pictures.this, R.raw.coins, 1);///
        openDialog_s = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen);
        ads_layout_bottom = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);

        //loadRewardedVideoAd();
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                .addScope(Drive.SCOPE_APPFOLDER) // SavedGames
                .build();


        soundset();
        find();
        click();
        next();
        System.out.println("jas2");
        rewarded_ad();
        if (sps.getInt(Find_difference_between_pictures.this, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(Find_difference_between_pictures.this).setMediationProvider("max");
            AppLovinSdk.initializeSdk(Find_difference_between_pictures.this, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    //sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                    industrialload_game();
                    game_exit_ins_ad();

                }
            });
        }

        if (sps.getInt(Find_difference_between_pictures.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(Find_difference_between_pictures.this);
        }

        if (sps.getString(Find_difference_between_pictures.this, "6f_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Find_difference_between_pictures.this, "sequence example fn3_6f");

            sequence.setConfig(config);

            sequence.addSequenceItem(value_ans1, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            sequence.addSequenceItem(verify, "சரிபார்க்க பொத்தானை அழுத்தி விடையை சரிபார்த்துக்கொள்ளவும்.", "அடுத்து");

            // sequence.addSequenceItem(ex_bones, "தொடர்ந்து சரியான  10 விடைகளை கண்டுபிடித்தால், கூடுதல் விடைகளை நாணயங்கள் குறையாமல் அறிந்து கொள்ளலாம்.", "அடுத்து");

            // sequence.addSequenceItem(feedback, "கருத்துக்கள்  பொத்தானை அழுத்தி மேலும் உங்களுக்கு தெரிந்த விடைகளை எங்களுக்கு அனுப்பவும் .", "அடுத்து");
            //   sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Find_difference_between_pictures.this)
                    .setTarget(p_facebook)
                    .setDismissText("சரி")
                    .setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {

                            if (position == 2) {
                                sps.putString(Find_difference_between_pictures.this, "time_start_6f", "yes");
                                sps.putString(Find_difference_between_pictures.this, "showcase_dismiss_6f_intro", "yes");
                                focus.setBase(SystemClock.elapsedRealtime());
                                focus.start();

                            }
                        }
                    });
            sequence.start();
            sps.putString(Find_difference_between_pictures.this, "6f_intro", "no");

        }

    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sps.getString(Find_difference_between_pictures.this, "showcase_dismiss_6f_intro").equals("")) {
                    showcase_dismiss();
                } else {
                    sps.putString(Find_difference_between_pictures.this, "time_start_6f", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }

            }
        }, 800);
    }

    private void soundset() {
        String snd = sps.getString(Find_difference_between_pictures.this, "snd");
        p_setting = (TextView) findViewById(R.id.p_settings);
        if (snd.equals("off")) {
            p_setting.setBackgroundResource(R.drawable.sound_off);
            // toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;
        } else if (snd.equals("on")) {
            //  toggleButton.setBackgroundResource(R.drawable.on);
            p_setting.setBackgroundResource(R.drawable.sound_on);
            sv = 1;
        }
    }

    private void next() {
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = 0;
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndex("coins"));
        }
        score.setText("" + skx);
        reset();
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        if (sps.getInt(Find_difference_between_pictures.this, "purchase_ads") == 1) {
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            native_banner_ad_container.setVisibility(View.VISIBLE);
        }
        head.setVisibility(View.VISIBLE);
        ans_editer.requestFocus();
        String date = sps.getString(Find_difference_between_pictures.this, "date");
        // myDbHelper.executeSql("DELETE FROM answertable");
        if (date.equals("0")) {
            Cursor c1 = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            int count1 = c2.getCount() + 1;
            String no = String.valueOf(count1);
            questionid.setText(no/*+"/"+c1.getCount()*/);
        } else {
            String tfoption = date;
            String[] first = tfoption.split("-");
            questionid.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            questionid.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
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
            u_id = c.getInt(c.getColumnIndex("id"));
            question = c.getString(c.getColumnIndex("question"));
            question_id = c.getString(c.getColumnIndex("questionid"));
            answer = c.getString(c.getColumnIndex("answer"));
            isdown = c.getString(c.getColumnIndex("isdown"));
            String tfoption = answer;
            String[] first = tfoption.split(",");
            int optlen = first.length;


            int letter_type = first.length;
            if (letter_type > 7) {
                answerlength = 7;
            } else {
                answerlength = letter_type;
            }
            StringTokenizer word = new StringTokenizer(question, ",");
            String word1 = word.nextToken();
            String word2 = word.nextToken();

            qs1 = word1;
            qs2 = word2;

            if (date.equals("0")) {
                rdvalu = 1;
            } else {
                rdvalu = 2;
            }
            if (optlen == 1) {
                view_ans(1);
            } else if (optlen == 2) {
                view_ans(2);
            } else if (optlen == 3) {
                view_ans(3);
            } else if (optlen == 4) {
                view_ans(4);
            } else if (optlen == 5) {
                view_ans(5);
            } else if (optlen == 6) {
                view_ans(6);
            } else if (optlen >= 7) {
                view_ans(7);
            }






           /* if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                image_1.setVisibility(View.VISIBLE);
                image_1.setImageResource(im1);

                int im2 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                image_2.setVisibility(View.VISIBLE);
                image_2.setImageResource(im2);

                System.out.println("@@@@@@@@@@@@@@@@@oi" + word1);
            } else {
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                File file1 = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");

                if (file1.exists() && file2.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    image_1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    BitmapDrawable bd2 = new BitmapDrawable(res, bitimg2);
                    image_2.setImageDrawable(bd2);
                } else {
                    missingimage();
                    System.out.println("missimg1");
                }
            }*/


            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                image_1.setVisibility(View.VISIBLE);
                image_1.setImageResource(im1);
                System.out.println("@@@@@@@@@@@@@@@@@oi" + word1);
            } else {
                System.out.println("---image 1 : " + word1);

                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + word1 + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    image_1.setImageDrawable(bd);
                } else {
                    missingimage();
                    System.out.println("missimg1");
                }
            }
            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                image_2.setVisibility(View.VISIBLE);
                image_2.setImageResource(im1);
            } else {
                System.out.println("---image 2 : " + word2);

                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + word2 + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    image_2.setImageDrawable(bd);
                } else {
                    missingimage();
                    System.out.println("missimg2");
                }
            }

            Cursor answ = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + question_id + "' and rd='" + rdvalu + "'");
            answ.moveToFirst();
            if (answ.getCount() == 0) {
                for (int i = 0; i < optlen; i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("gameid", gameid);
                    cv.put("levelid", question_id);
                    cv.put("answer", first[i].trim());
                    if (date.equals("0")) {
                        cv.put("rd", 1);
                    } else {
                        cv.put("rd", 2);
                    }
                    cv.put("isfinish", "0");
                    myDbHelper.insert_data("answertable", null, cv);
                }
            }
            System.out.println("######################" + answ.getCount());
            if (answ.getCount() != 0) {

                Cursor csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + question_id + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='0'");
                csk.moveToFirst();
                System.out.println("######################" + csk.getCount());
                for (int i = 0; i < csk.getCount(); i++) {
                    csk.moveToPosition(i);
                    String ansn = csk.getString(csk.getColumnIndex("answer"));
                    int dscore = csk.getInt(csk.getColumnIndex("levelscore"));
                    b_score = dscore;
                    if (ans1.length() == 0) {
                        ans1.setText(ansn);
                        ans1.setTextColor(getResources().getColor(R.color.white));
                        value_ans1.setBackgroundResource(R.drawable.tick_background);
                        value_ans1.setClickable(false);
                        value_ans2.setVisibility(View.VISIBLE);
                    } else if (ans2.length() == 0) {
                        ans2.setText(ansn);
                        ans2.setTextColor(getResources().getColor(R.color.white));
                        value_ans2.setBackgroundResource(R.drawable.tick_background);
                        value_ans2.setClickable(false);
                        value_ans3.setVisibility(View.VISIBLE);
                    } else if (ans3.length() == 0) {
                        ans3.setText(ansn);
                        ans3.setTextColor(getResources().getColor(R.color.white));
                        value_ans3.setBackgroundResource(R.drawable.tick_background);
                        value_ans3.setClickable(false);
                        value_ans4.setVisibility(View.VISIBLE);
                    } else if (ans4.length() == 0) {
                        ans4.setText(ansn);
                        ans4.setTextColor(getResources().getColor(R.color.white));
                        value_ans4.setBackgroundResource(R.drawable.tick_background);
                        value_ans4.setClickable(false);
                        value_ans5.setVisibility(View.VISIBLE);
                    } else if (ans5.length() == 0) {
                        ans5.setText(ansn);
                        ans5.setTextColor(getResources().getColor(R.color.white));
                        value_ans5.setBackgroundResource(R.drawable.tick_background);
                        value_ans5.setClickable(false);
                        value_ans6.setVisibility(View.VISIBLE);
                    } else if (ans6.length() == 0) {
                        ans6.setText(ansn);
                        ans6.setTextColor(getResources().getColor(R.color.white));
                        value_ans6.setBackgroundResource(R.drawable.tick_background);
                        value_ans6.setClickable(false);
                        value_ans7.setVisibility(View.VISIBLE);
                    } else if (ans7.length() == 0) {
                        ans7.setText(ansn);
                        ans7.setTextColor(getResources().getColor(R.color.white));
                        value_ans7.setBackgroundResource(R.drawable.tick_background);
                        value_ans7.setClickable(false);
                    }
                }

                Cursor csk1 = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + question_id + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='1'");
                csk1.moveToFirst();
                for (int i = 0; i < csk1.getCount(); i++) {
                    csk1.moveToPosition(i);
                    String ansn = csk1.getString(csk1.getColumnIndex("answer"));
                    if (ans1.length() == 0) {
                        ans1.setText(ansn);
                        ans1.setTextColor(getResources().getColor(R.color.rippelColor1));
                        value_ans1.setBackgroundResource(R.drawable.tick_background);
                        value_ans1.setClickable(false);
                        value_ans2.setVisibility(View.VISIBLE);
                    } else if (ans2.length() == 0) {
                        ans2.setText(ansn);
                        ans2.setTextColor(getResources().getColor(R.color.rippelColor1));
                        value_ans2.setBackgroundResource(R.drawable.tick_background);
                        value_ans2.setClickable(false);
                        value_ans3.setVisibility(View.VISIBLE);
                    } else if (ans3.length() == 0) {
                        ans3.setText(ansn);
                        ans3.setTextColor(getResources().getColor(R.color.rippelColor1));
                        value_ans3.setBackgroundResource(R.drawable.tick_background);
                        value_ans3.setClickable(false);
                        value_ans4.setVisibility(View.VISIBLE);
                    } else if (ans4.length() == 0) {
                        ans4.setText(ansn);
                        ans4.setTextColor(getResources().getColor(R.color.rippelColor1));
                        value_ans4.setBackgroundResource(R.drawable.tick_background);
                        value_ans4.setClickable(false);
                        value_ans5.setVisibility(View.VISIBLE);
                    } else if (ans5.length() == 0) {
                        ans5.setText(ansn);
                        ans5.setTextColor(getResources().getColor(R.color.rippelColor1));
                        value_ans5.setBackgroundResource(R.drawable.tick_background);
                        value_ans5.setClickable(false);
                        value_ans6.setVisibility(View.VISIBLE);
                    } else if (ans6.length() == 0) {
                        ans6.setText(ansn);
                        ans6.setTextColor(getResources().getColor(R.color.rippelColor1));
                        value_ans6.setBackgroundResource(R.drawable.tick_background);
                        value_ans6.setClickable(false);
                        value_ans7.setVisibility(View.VISIBLE);

                    }
                }
            } else {
                if (sps.getString(Find_difference_between_pictures.this, "time_start_fn").equals("")) {
                    sps.putString(Find_difference_between_pictures.this, "time_start_fn", "yes");

                } else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                }
            }
            Cursor cs = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + question_id + "' and rd='" + rdvalu + "' and isfinish='1'");
            cs.moveToFirst();
            if (cs.getCount() != 0) {
                ans_count = cs.getCount();
            }

        } else {
            data_download_game();
            //downloaddata_regular();
            //nextgamesdialog();
        }
    }

    private void view_ans(int i) {
        final_ans_count = i;
        if (i == 1) {
            value_ans1.setVisibility(View.VISIBLE);
            ans1.setVisibility(View.VISIBLE);
        } else if (i == 2) {
            value_ans1.setVisibility(View.VISIBLE);
            ans1.setVisibility(View.VISIBLE);
            //value_ans2.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
        } else if (i == 3) {
            value_ans1.setVisibility(View.VISIBLE);
            ans1.setVisibility(View.VISIBLE);
            //value_ans2.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            // value_ans3.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
        } else if (i == 4) {
            value_ans1.setVisibility(View.VISIBLE);
            ans1.setVisibility(View.VISIBLE);
            //value_ans2.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            // value_ans3.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            //value_ans4.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
        } else if (i == 5) {
            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);
            ans1.setVisibility(View.VISIBLE);
            // value_ans2.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            // value_ans3.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            // value_ans4.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            // value_ans5.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
        } else if (i == 6) {
            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);
            ans1.setVisibility(View.VISIBLE);
            // value_ans2.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            // value_ans3.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            // value_ans4.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            // value_ans5.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            //value_ans6.setVisibility(View.VISIBLE);
            ans6.setVisibility(View.VISIBLE);
        } else if (i >= 7) {
            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);
            ans1.setVisibility(View.VISIBLE);
            //value_ans2.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            //value_ans3.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            //value_ans4.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            //value_ans5.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            //value_ans6.setVisibility(View.VISIBLE);
            ans6.setVisibility(View.VISIBLE);
            //value_ans7.setVisibility(View.VISIBLE);
            ans7.setVisibility(View.VISIBLE);
        }
    }

    private void reset() {
        ans_count = 0;
        value_ans1.setVisibility(View.GONE);
        value_ans2.setVisibility(View.GONE);
        value_ans3.setVisibility(View.GONE);
        value_ans4.setVisibility(View.GONE);
        value_ans5.setVisibility(View.GONE);
        value_ans6.setVisibility(View.GONE);
        value_ans7.setVisibility(View.GONE);

        value_ans1.setBackgroundResource(R.drawable.yellow_question);
        value_ans2.setBackgroundResource(R.drawable.yellow_question);
        value_ans3.setBackgroundResource(R.drawable.yellow_question);
        value_ans4.setBackgroundResource(R.drawable.yellow_question);
        value_ans5.setBackgroundResource(R.drawable.yellow_question);
        value_ans6.setBackgroundResource(R.drawable.yellow_question);
        value_ans7.setBackgroundResource(R.drawable.yellow_question);

        ans1.setVisibility(View.GONE);
        ans2.setVisibility(View.GONE);
        ans3.setVisibility(View.GONE);
        ans4.setVisibility(View.GONE);
        ans5.setVisibility(View.GONE);
        ans6.setVisibility(View.GONE);
        ans7.setVisibility(View.GONE);
        anslist2.setVisibility(View.GONE);
        list2_pic.setVisibility(View.GONE);

        ans1.setTextColor(getResources().getColor(R.color.white));
        ans2.setTextColor(getResources().getColor(R.color.white));
        ans3.setTextColor(getResources().getColor(R.color.white));
        ans4.setTextColor(getResources().getColor(R.color.white));
        ans5.setTextColor(getResources().getColor(R.color.white));
        ans6.setTextColor(getResources().getColor(R.color.white));
        ans7.setTextColor(getResources().getColor(R.color.white));

        value_ans1.setClickable(true);
        value_ans2.setClickable(true);
        value_ans3.setClickable(true);
        value_ans4.setClickable(true);
        value_ans5.setClickable(true);
        value_ans6.setClickable(true);
        value_ans7.setClickable(true);
        ans1.setText("");
        ans2.setText("");
        ans3.setText("");
        ans4.setText("");
        ans5.setText("");
        ans6.setText("");
        ans7.setText("");
        verify.setVisibility(View.VISIBLE);
        ans_editer.setText("");

    }

/*
    public void setans(View v) {
        switch (v.getId()) {
            case R.id.value_ans1:
                set_val(1);
                break;
            case R.id.value_ans2:
                set_val(2);
                break;
            case R.id.value_ans3:
                set_val(3);
                break;
            case R.id.value_ans4:
                set_val(4);
                break;
            case R.id.value_ans5:
                set_val(5);
                break;
            case R.id.value_ans6:
                set_val(6);
                break;
            case R.id.value_ans7:
                set_val(7);
                break;
        }
    }
*/

    public void set_val(int val) {
        // Toast.makeText(this, "set_val"+val, Toast.LENGTH_SHORT).show();
        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        int sk = cfw.getInt(cfw.getColumnIndex("coins"));

        if (sk > 50) {

            if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1 ");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (ans_count <= final_ans_count) {
                        String sa = cd.getString(cd.getColumnIndex("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        if (val == 1) {
                            ans1.setText(sa);
                            ans1.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans1.setBackgroundResource(R.drawable.tick_background);
                            value_ans1.setClickable(false);
                            if (answerlength >= 2) {
                                value_ans2.setVisibility(View.VISIBLE);
                            }

                        } else if (val == 2) {
                            ans2.setText(sa);
                            ans2.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans2.setBackgroundResource(R.drawable.tick_background);
                            value_ans2.setClickable(false);
                            if (answerlength >= 3) {
                                value_ans3.setVisibility(View.VISIBLE);

                            }
                        } else if (val == 3) {
                            ans3.setText(sa);
                            ans3.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans3.setBackgroundResource(R.drawable.tick_background);
                            value_ans3.setClickable(false);
                            if (answerlength >= 4) {
                                value_ans4.setVisibility(View.VISIBLE);
                            }

                        } else if (val == 4) {
                            ans4.setText(sa);
                            ans4.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans4.setBackgroundResource(R.drawable.tick_background);
                            value_ans4.setClickable(false);
                            if (answerlength >= 5) {
                                value_ans5.setVisibility(View.VISIBLE);
                            }

                        } else if (val == 5) {
                            ans5.setText(sa);
                            ans5.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans5.setBackgroundResource(R.drawable.tick_background);
                            value_ans5.setClickable(false);
                            if (answerlength >= 6) {
                                value_ans6.setVisibility(View.VISIBLE);
                            }

                        } else if (val == 6) {
                            ans6.setText(sa);
                            ans6.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans6.setBackgroundResource(R.drawable.tick_background);
                            value_ans6.setClickable(false);
                            if (answerlength >= 7) {
                                value_ans7.setVisibility(View.VISIBLE);
                            }

                        } else if (val == 7) {
                            ans7.setText(sa);
                            ans7.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans7.setBackgroundResource(R.drawable.tick_background);
                            value_ans7.setClickable(false);

                        }
                        ans_count++;
                    }
                    if (ans_count == final_ans_count) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        // update_price();
                        String date = sps.getString(Find_difference_between_pictures.this, "date");
                        if (date.equals("0")) {
                            newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid='" + gameid + "'");
                        } else {
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + question_id + "'and gameid='" + gameid + "'");
                        }
                        update_price();
                        // completegame();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setSc();

                            }
                        }, 2000);
                    }
                }
            } else {
                checkbox_dialog(val);
            }
        } else {
            dialog(1);
        }

    }

    public void checkbox_dialog(final int vals) {

        final Dialog openDialog = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.show_ans);
        TextView yes = (TextView) openDialog.findViewById(R.id.yes);
        TextView no = (TextView) openDialog.findViewById(R.id.no);
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
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1 ");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (ans_count <= final_ans_count) {
                        String sa = cd.getString(cd.getColumnIndex("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                        int spx = skx - 50;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                        if (vals == 1) {
                            ans1.setText(sa);
                            ans1.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans1.setBackgroundResource(R.drawable.tick_background);
                            value_ans1.setClickable(false);
                            if (answerlength >= 2) {
                                value_ans2.setVisibility(View.VISIBLE);
                            }

                        } else if (vals == 2) {
                            ans2.setText(sa);
                            ans2.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans2.setBackgroundResource(R.drawable.tick_background);
                            value_ans2.setClickable(false);
                            if (answerlength >= 3) {
                                value_ans3.setVisibility(View.VISIBLE);

                            }
                        } else if (vals == 3) {
                            ans3.setText(sa);
                            ans3.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans3.setBackgroundResource(R.drawable.tick_background);
                            value_ans3.setClickable(false);
                            if (answerlength >= 4) {
                                value_ans4.setVisibility(View.VISIBLE);
                            }

                        } else if (vals == 4) {
                            ans4.setText(sa);
                            ans4.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans4.setBackgroundResource(R.drawable.tick_background);
                            value_ans4.setClickable(false);
                            if (answerlength >= 5) {
                                value_ans5.setVisibility(View.VISIBLE);
                            }

                        } else if (vals == 5) {
                            ans5.setText(sa);
                            ans5.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans5.setBackgroundResource(R.drawable.tick_background);
                            value_ans5.setClickable(false);
                            if (answerlength >= 6) {
                                value_ans6.setVisibility(View.VISIBLE);
                            }

                        } else if (vals == 6) {
                            ans6.setText(sa);
                            ans6.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans6.setBackgroundResource(R.drawable.tick_background);
                            value_ans6.setClickable(false);
                            if (answerlength >= 7) {
                                value_ans7.setVisibility(View.VISIBLE);
                            }

                        } else if (vals == 7) {
                            ans7.setText(sa);
                            ans7.setTextColor(getResources().getColor(R.color.rippelColor1));
                            value_ans7.setBackgroundResource(R.drawable.tick_background);
                            value_ans7.setClickable(false);

                        }
                        ans_count++;
                        openDialog.dismiss();
                    }
                    if (ans_count == final_ans_count) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        // update_price();
                        String date = sps.getString(Find_difference_between_pictures.this, "date");
                        if (date.equals("0")) {
                            newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid='" + gameid + "'");
                        } else {
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + question_id + "'and gameid='" + gameid + "'");
                        }
                        // completegame();
                        update_price();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setSc();

                            }
                        }, 2000);
                    }
                }
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

    private void click() {
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chr = 1;
                mCustomKeyboard.letter_del_change("1");
                mCustomKeyboard.letter_del_change("1");
                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });
        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chr = 1;
                mCustomKeyboard.letter_del_change("1");
                ans_editer.setText("");
                return false;
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verify_data();
            }
        });
        p_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_setting.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(Find_difference_between_pictures.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(Find_difference_between_pictures.this, "snd", "on");
                    p_setting.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(Find_difference_between_pictures.this, "snd", "off");
                    p_setting.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });
        value_ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_val(1);
            }
        });
        value_ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_val(2);
            }
        });
        value_ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_val(3);
            }
        });
        value_ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_val(4);
            }
        });
        value_ans5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_val(5);
            }
        });
        value_ans6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_val(6);
            }
        });
        value_ans7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_val(7);
            }
        });
        image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pic_show(1, qs1, qs2);
            }
        });
        image_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pic_show(1, qs1, qs2);
            }
        });

        p_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_name = 1;
                final String a = "com.facebook.katana";
                permission(a);
            }
        });
        p_watts_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_name = 2;
                String a = "com.whatsapp";
                permission(a);
            }
        });
        qwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(0);
            }
        });


        ans_editer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(verify.getWindowToken(), 0);
            }
        });
        ans_editer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(verify.getWindowToken(), 0);

                return true;
            }
        });
    }

    private void verify_data() {
        chr = 1;
        mCustomKeyboard.letter_del_change("1");
        mCustomKeyboard.letter_del_change("1");
        String ans = ans_editer.getText().toString();
        if (ans.length() != 0) {
            Cursor cs = myDbHelper.getQry("select * from answertable where answer LIKE'" + ans + "'and isfinish='1'and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
            cs.moveToFirst();
            if (cs.getCount() != 0) {
                cr_ans.play(soundId5, sv, sv, 0, 0, sv);
                ans_editer.setText("");
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "பதிவு செய்துவிட்டீர்கள்", Snackbar.LENGTH_SHORT);
                final View view = snackbar.getView();
                TextView textView = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                view.setBackgroundResource(R.drawable.answershow_green);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                //textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                textView.setTextSize(17);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                } else {
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                }

                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                params.gravity = Gravity.TOP;
                view.setLayoutParams(params);
                snackbar.show();
            } else {
                Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where answer ='" + ans + "' and isfinish='0' and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (ans_count <= final_ans_count) {
                        win.play(soundId3, sv, sv, 0, 0, sv);

                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + ans + "' and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=0 WHERE answer='" + ans + "' and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");

                        b_score = b_score + 10;

                   /*     Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                        int spx = skx + 10;
                        String aStringx = Integer.toString(spx);
                        score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/
                        coinanim();
                        Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                        ch.moveToFirst();
                        int sh = ch.getInt(ch.getColumnIndex("l_points"));
                        int shh = sh + 10;
                        myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");
                        setans(ans);
                        ans_editer.setText("");
                        ans_count++;
                        if (ans_count == final_ans_count) {
                            verify.setVisibility(View.INVISIBLE);

                            focus.stop();
                            String date = sps.getString(Find_difference_between_pictures.this, "date");
                            if (date.equals("0")) {
                                newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid='" + gameid + "'");
                            } else {
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + question_id + "'and gameid='" + gameid + "'");
                            }
                            update_price();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setSc();

                                }
                            }, 2000);
                        }

                    }
                } else {
                    worng.play(soundId2, sv, sv, 0, 0, sv);
                    ans_editer.setText("");
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "சரியான விடையாக இருக்கலாம் .\n ஆனால் எங்கள் தொகுப்பில் இல்லை ", Snackbar.LENGTH_SHORT);
                    final View view = snackbar.getView();
                    TextView textView = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                    view.setBackgroundResource(R.drawable.answershow);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    //  textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                    textView.setTextSize(17);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    snackbar.show();
                }
            }
        } else {

            worng.play(soundId2, sv, sv, 0, 0, sv);
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "எழுத்துக்களை நிரப்பவும்", Snackbar.LENGTH_SHORT);
            final View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
            view.setBackgroundResource(R.drawable.answershow);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            //  textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            textView.setTextSize(19);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            } else {
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
            params.gravity = Gravity.TOP;
            view.setLayoutParams(params);
            snackbar.show();
        }

    }

    private void setans(String ans) {
        Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
        if (ans1.length() == 0) {
            ans1.setText(ans);
            ans1.startAnimation(levels1);
            value_ans1.setBackgroundResource(R.drawable.tick_background);
            value_ans1.setClickable(false);
            if (answerlength >= 2) {
                value_ans2.setVisibility(View.VISIBLE);
            }

        } else if (ans2.length() == 0) {
            ans2.setText(ans);
            ans2.startAnimation(levels1);
            value_ans2.setBackgroundResource(R.drawable.tick_background);
            value_ans2.setClickable(false);
            if (answerlength >= 3) {
                value_ans3.setVisibility(View.VISIBLE);
            }

        } else if (ans3.length() == 0) {
            ans3.setText(ans);
            ans3.startAnimation(levels1);
            value_ans3.setBackgroundResource(R.drawable.tick_background);
            value_ans3.setClickable(false);
            if (answerlength >= 4) {
                value_ans4.setVisibility(View.VISIBLE);
            }

        } else if (ans4.length() == 0) {
            ans4.setText(ans);
            ans4.startAnimation(levels1);
            value_ans4.setBackgroundResource(R.drawable.tick_background);
            value_ans4.setClickable(false);
            if (answerlength >= 5) {
                value_ans5.setVisibility(View.VISIBLE);
            }

        } else if (ans5.length() == 0) {
            ans5.setText(ans);
            ans5.startAnimation(levels1);
            value_ans5.setBackgroundResource(R.drawable.tick_background);
            value_ans5.setClickable(false);
            if (answerlength >= 6) {
                value_ans6.setVisibility(View.VISIBLE);
            }

        } else if (ans6.length() == 0) {
            ans6.setText(ans);
            ans6.startAnimation(levels1);
            value_ans6.setBackgroundResource(R.drawable.tick_background);
            value_ans6.setClickable(false);
            if (answerlength >= 7) {
                value_ans7.setVisibility(View.VISIBLE);
            }

        } else if (ans7.length() == 0) {
            ans7.setText(ans);
            ans7.startAnimation(levels1);
            value_ans7.setBackgroundResource(R.drawable.tick_background);
            value_ans7.setClickable(false);
        }
    }

    private void find() {
        p_coins = (TextView) findViewById(R.id.p_coins);
        focus = (Chronometer) findViewById(R.id.p_time_edit);
        score = (TextView) findViewById(R.id.p_score_edit);
        anslist2 = (LinearLayout) findViewById(R.id.anslist2);
        list2_pic = (LinearLayout) findViewById(R.id.list2_pic);
        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        ans4 = (TextView) findViewById(R.id.ans4);
        ans5 = (TextView) findViewById(R.id.ans5);
        ans6 = (TextView) findViewById(R.id.ans6);
        ans7 = (TextView) findViewById(R.id.ans7);
        verify = (TextView) findViewById(R.id.verify);
        clear = (TextView) findViewById(R.id.clear);
        questionid = (TextView) findViewById(R.id.questionid);
        p_facebook = (TextView) findViewById(R.id.p_facebook);
        p_watts_app = (TextView) findViewById(R.id.p_watts_app);
        value_ans1 = (ImageView) findViewById(R.id.value_ans1);
        value_ans2 = (ImageView) findViewById(R.id.value_ans2);
        value_ans3 = (ImageView) findViewById(R.id.value_ans3);
        value_ans4 = (ImageView) findViewById(R.id.value_ans4);
        value_ans5 = (ImageView) findViewById(R.id.value_ans5);
        value_ans6 = (ImageView) findViewById(R.id.value_ans6);
        value_ans7 = (ImageView) findViewById(R.id.value_ans7);
        image_1 = (ImageView) findViewById(R.id.image_1);
        image_2 = (ImageView) findViewById(R.id.image_2);
        ans_editer = (AppCompatEditText) findViewById(R.id.ans_editer);
        head = (RelativeLayout) findViewById(R.id.head);
        ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        qwt = (LinearLayout) findViewById(R.id.qwt);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = 0;
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndex("coins"));
        }
        score.setText("" + skx);

        ImageView prize_logo = (ImageView) findViewById(R.id.prize_logo);
        /*final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        prize_logo.startAnimation(pendulam);*/
        if (sps.getInt(Find_difference_between_pictures.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    if (sps.getString(Find_difference_between_pictures.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Find_difference_between_pictures.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Find_difference_between_pictures.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Find_difference_between_pictures.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void pressKey(int keycode) {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        ans_editer.onKeyDown(keycode, event);
    }

    public void dialog(int i) {

        final Dialog openDialog_earncoin = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Find_difference_between_pictures.this, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Find_difference_between_pictures.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                        } else {
                            pos = 2;
                        }
                        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                        //reward(Find_difference_between_pictures.this);
                        rewarded_ad();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(Find_difference_between_pictures.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
             /*   rvo = 1;
                extra_coin_s = 0;
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Find_difference_between_pictures.this, "" + "Reward video", "Loading...");

                    if (mRewardedVideoAd.isLoaded()) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Find_difference_between_pictures.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                        } else {
                            pos = 2;
                        }
                        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

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

                                    Toast.makeText(Find_difference_between_pictures.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
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
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {

                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Find_difference_between_pictures.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            pos = 1;
                        } else {
                            pos = 2;
                        }
                        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

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

    public void setSc() {

       /* if (s == 1) {
            openDialog_p.dismiss();
            s = 0;
        }
*/
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        int f_sec = sec + sec2 + seconds;


        TextView arputham = (TextView) openDialog_s.findViewById(R.id.arputham);
        TextView extracoin = (TextView) openDialog_s.findViewById(R.id.extracoin);
        next_continue = (TextView) openDialog_s.findViewById(R.id.continues);

        ttscores = (TextView) openDialog_s.findViewById(R.id.tts_score);
        final TextView bsscores = (TextView) openDialog_s.findViewById(R.id.bs_score);
        final TextView dumy = (TextView) openDialog_s.findViewById(R.id.bs_score_dum);
        final TextView cns1 = (TextView) openDialog_s.findViewById(R.id.cnse1);
        final TextView cns2 = (TextView) openDialog_s.findViewById(R.id.cnse2);
        final TextView cns3 = (TextView) openDialog_s.findViewById(R.id.cnse3);
        final TextView cns4 = (TextView) openDialog_s.findViewById(R.id.cnse4);
        final TextView cns5 = (TextView) openDialog_s.findViewById(R.id.cnse5);
        tx2 = (TextView) openDialog_s.findViewById(R.id.tt2);
        final TextView wtp = (TextView) openDialog_s.findViewById(R.id.wtp);
        final TextView fbs = (TextView) openDialog_s.findViewById(R.id.fbp);
        final TextView gplus = (TextView) openDialog_s.findViewById(R.id.gplus);
        final LinearLayout rewardvideo = (LinearLayout) openDialog_s.findViewById(R.id.rewardvideo);
        final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);

        TextView video_earn = (TextView) openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sps.getInt(Find_difference_between_pictures.this, "reward_coin_txt") + "+நாணயங்கள் பெற");

        ImageView prize_logo = (ImageView) openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Find_difference_between_pictures.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    if (sps.getString(Find_difference_between_pictures.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Find_difference_between_pictures.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Find_difference_between_pictures.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Find_difference_between_pictures.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Find_difference_between_pictures.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);


        if (sps.getInt(Find_difference_between_pictures.this, "purchase_ads") == 1) {
            ads_layout_bottom.setVisibility(View.GONE);
        } else {
            // New_Main_Activity.load_addFromMain_multiplayer(Find_difference_between_pictures.this, ads_layout_bottom);
            if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Find_difference_between_pictures.this, ads_layout_bottom);
            } else {
                ads_layout_bottom.setVisibility(View.GONE);
            }
        }


        if (sps.getString(Find_difference_between_pictures.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Find_difference_between_pictures.this, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }

        Cursor csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + question_id + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='0'");
        csk.moveToFirst();
        if (csk.getCount() == 0) {
            rewardvideo.setVisibility(View.INVISIBLE);
        }
        next_continue.setVisibility(View.INVISIBLE);


        RelativeLayout adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);

        // final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);

        vid_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Find_difference_between_pictures.this, "" + "Reward video", "Loading...");
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
                                    //reward(Find_difference_between_pictures.this);
                                    rewarded_ad();
                                    Toast.makeText(Find_difference_between_pictures.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Find_difference_between_pictures.this, "" + "Reward video", "Loading...");
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
                                    //reward(Find_difference_between_pictures.this);
                                    rewarded_ad();
                                    Toast.makeText(Find_difference_between_pictures.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
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
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");
                        String msg = ("நான் சொல்லிஅடி செயலியில் படத்திற்குள் கண்டுபிடி நிலை " + questionid.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");

                        String msg = ("நான் சொல்லிஅடி செயலியில் படத்திற்குள் கண்டுபிடி நிலை" + questionid.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
        System.out.println("#############################" + " SELECT * FROM answertable where useranswer='1'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        int uans = 0;
        Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where useranswer='0'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        cd.moveToFirst();
        if (cd.getCount() != 0) {
            uans = cd.getCount();
        }

        // Toast.makeText(Find_words_from_picture.this, "u_ans"+uans, Toast.LENGTH_SHORT).show();
        // Toast.makeText(Find_words_from_picture.this, "answerlength"+answerlength, Toast.LENGTH_SHORT).show();
        int tt_time = 122;


        System.out.println("########################uans" + uans);
        System.out.println("########################answerlength" + answerlength);
        if (uans == answerlength & f_sec <= tt_time) {
            //Condition 2
            y = 0;
            arputham.setTypeface(tyr);
            arputham.setText("ÜŸ¹î‹");
            extracoin.setTypeface(tyr);
            extracoin.setText("Ã´î™ ï£íòƒèœ");
            tx2.setTypeface(tyr);
            tx2.setText("Ã´î™ ï£íò‹ ªðø ðAó¾‹");
            next_continue.setTypeface(tyr);
            next_continue.setText("ªî£ì˜è");

            String date = sps.getString(Find_difference_between_pictures.this, "date");
            if (!date.equals("0")) {
                next_continue.setText("சரி");
            }
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    coin.play(soundId4, sv, sv, 0, 0, sv);
                    //play1.start();
                    cns3.setVisibility(View.VISIBLE);
                }
            }, 500);
            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // play2.start();
                    coin.play(soundId4, sv, sv, 0, 0, sv);
                    cns1.setVisibility(View.VISIBLE);
                }
            }, 1000);
            Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //  play3.start();
                    coin.play(soundId4, sv, sv, 0, 0, sv);
                    cns2.setVisibility(View.VISIBLE);
                }
            }, 1500);


            Handler handler6 = new Handler();
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
            Handler handler7 = new Handler();
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
            Handler handler8 = new Handler();
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
                tt_case2 = cfx.getInt(cfx.getColumnIndex("coins"));
                tt_tot2 = tt_case2 + 30;
                String aStringx = Integer.toString(tt_case2);
                ttscores.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + tt_tot2 + "'");
            }


            Handler handler11 = new Handler();
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

            Handler hand = new Handler();
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
                    if (sps.getInt(Find_difference_between_pictures.this, "purchase_ads") == 1) {
                        dia_dismiss = 1;
                        openDialog_s.dismiss();
                        next();
                        System.out.println("jas3");
                    } else {
                        if (sps.getInt(getApplicationContext(), "ins_ad_new") == 4) {
                            sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                            if (Utils.isNetworkAvailable(getApplicationContext())) {
                                if (ins_game == null || !ins_game.isReady()) {
                                    dia_dismiss = 1;
                                    openDialog_s.dismiss();
                                    next();
                                    System.out.println("jas4");
                                    //industrialload_game();
                                    return;
                                }
                                else {
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
                            } else {
                                next();
                            }*/
                            } else {
                                dia_dismiss = 1;
                                openDialog_s.dismiss();
                                next();
                                System.out.println("jas5");
                            }

                        } else {
                            dia_dismiss = 1;
                            openDialog_s.dismiss();
                            next();
                            System.out.println("jas6");
                            sps.putInt(getApplicationContext(), "ins_ad_new", (sps.getInt(getApplicationContext(), "ins_ad_new") + 1));
                        }
                        //  advancads();
                        // advancads_content();
                    }

                    /*play1.stop();
                    play2.stop();
                    play3.stop();*/
                    // advancads_content();





                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        if (getApiClient().isConnected()) {
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
                    }
                   /* dia_dismiss = 1;
                    openDialog_s.dismiss();*/
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
            String date = sps.getString(Find_difference_between_pictures.this, "date");
            if (!date.equals("0")) {
                next_continue.setText("சரி");
            }
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                tt_case2 = cfx.getInt(cfx.getColumnIndex("coins"));
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
                    if (sps.getInt(Find_difference_between_pictures.this, "purchase_ads") == 1) {
                        dia_dismiss = 1;
                        openDialog_s.dismiss();
                        next();
                        System.out.println("jas7");
                    }else{
                        if (sps.getInt(getApplicationContext(), "ins_ad_new") == 4) {
                            sps.putInt(getApplicationContext(), "ins_ad_new", 0);
                            if (Utils.isNetworkAvailable(getApplicationContext())) {
                                if (ins_game == null || !ins_game.isReady()) {
                                    dia_dismiss = 1;
                                    openDialog_s.dismiss();
                                    next();
                                    System.out.println("jas8");
                                    //industrialload_game();
                                    return;
                                }
                                else{
                                    ins_game.showAd();
                                }
                            } else {
                                dia_dismiss = 1;
                                openDialog_s.dismiss();
                                next();
                                System.out.println("jas9");
                            }

                        } else {
                            dia_dismiss = 1;
                            openDialog_s.dismiss();
                            next();
                            System.out.println("jas10");
                            sps.putInt(getApplicationContext(), "ins_ad_new", (sps.getInt(getApplicationContext(), "ins_ad_new") + 1));
                        }

                    }

             /*       dia_dismiss = 1;
                    openDialog_s.dismiss();*/
                }
            });
        }

        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss != 1) {
                    sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                    tot2 = 0;
                    tt_tot2 = 0;
                    case2 = 0;
                    y = 0;
                    tt_case2 = 0;


                    String date = sps.getString(Find_difference_between_pictures.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                            openDialog_s.dismiss();

                        }
                    } else {
                        if (sps.getString(Find_difference_between_pictures.this, "Exp_list").equals("on")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Find_difference_between_pictures.this, Expandable_List_View.class);
                            startActivity(i);

                        } else {
                            if (main_act.equals("")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
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
        sps.putInt(Find_difference_between_pictures.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }


    //reward videos***********************//
    public void share_earn2(int a) {
        int skx = 0;
        final Dialog openDialog = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndex("coins"));
/*        int spx = skx + a;
        final String aStringx = Integer.toString(spx);*/
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
            final Dialog openDialog = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
            TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);


            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                final int skx = cfx.getInt(cfx.getColumnIndex("coins"));
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

    private void daily_bones() {
        openDialog = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        //  TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        //   TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
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

        TextView tomarrow_coin_earn = (TextView) openDialog.findViewById(R.id.tomarrow_coin_earn);

        //TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);

        //TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);


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
                    sps.putString(Find_difference_between_pictures.this, "daily_bonus_date", date);
                }
                openDialog.dismiss();
            }
        });

        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
        ea = 100;
        final int vals = reward_play_count * 100;
        ea = ea + vals;
        coin_value.setText("" + ea);

        LinearLayout extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        System.out.println("############################^^^^^^^^^^^^^^currentdate" + str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Find_difference_between_pictures.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Find_difference_between_pictures.this, "daily_bonus_date"))) {

        } else {
            sps.putInt(Find_difference_between_pictures.this, "daily_bonus_count", 0);
        }
        if (sps.getInt(Find_difference_between_pictures.this, "daily_bonus_count") == 0) {
            ea = 100;
        } else if (sps.getInt(Find_difference_between_pictures.this, "daily_bonus_count") == 1) {
            ea = 150;
        } else if (sps.getInt(Find_difference_between_pictures.this, "daily_bonus_count") == 2) {
            ea = 200;
        } else if (sps.getInt(Find_difference_between_pictures.this, "daily_bonus_count") == 3) {
            ea = 250;
        } else if (sps.getInt(Find_difference_between_pictures.this, "daily_bonus_count") == 4) {
            ea = 300;
        }
        prize_data_update(Find_difference_between_pictures.this, ea);
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
      /*  final int vals = reward_play_count * 100;
        ea = ea + vals;*/
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomnod = rn.nextInt(maximumd - minmumd + 1) + minmumd;

        //String r= String.valueOf(w_id);
        //lt_id.setText(r);
        String ran_score = "";
        if (randomnod == 1) {
            sps.putInt(Find_difference_between_pictures.this, "daily_bonus_count", 1);
            ran_score = "150";
        } else if (randomnod == 2) {
            sps.putInt(Find_difference_between_pictures.this, "daily_bonus_count", 2);
            ran_score = "200";
        } else if (randomnod == 3) {
            sps.putInt(Find_difference_between_pictures.this, "daily_bonus_count", 3);
            ran_score = "250";
        } else if (randomnod == 4) {
            sps.putInt(Find_difference_between_pictures.this, "daily_bonus_count", 4);
            ran_score = "300";
        }

        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);

        extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);

        extra_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra_coin_s = 1;
                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Find_difference_between_pictures.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedAd.showAd();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(Find_difference_between_pictures.this);
                                    rewarded_ad();
                                    Toast.makeText(Find_difference_between_pictures.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
                       /* b_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openDialog.dismiss();
                            }
                        });*/
        openDialog.show();
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
                System.out.println("jas11");
                sps.putInt(getApplicationContext(), "ins_ad_new", 0);
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

    public void pic_show(int a, String qs1, String qs2) {
        openDialogk = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialogk.setContentView(R.layout.show_pic2);
        TouchImageView pic_show = (TouchImageView) openDialogk.findViewById(R.id.pic_show);
        TouchImageView pic_show2 = (TouchImageView) openDialogk.findViewById(R.id.pic_show2);
        Button cancel = (Button) openDialogk.findViewById(R.id.p_cancel);
        pic_show2.setVisibility(View.VISIBLE);
        if (a == 1) {
            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(qs1.replace(".webp", ""), "drawable", getPackageName());
                pic_show.setImageResource(im1);
            } else {
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + qs1 + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + qs1 + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    pic_show.setImageDrawable(bd);
                }
            }
        }

        if (a == 1) {
            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(qs2.replace(".webp", ""), "drawable", getPackageName());
                pic_show2.setImageResource(im1);
            } else {
                String fullPath = getFilesDir()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + qs2 + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + qs2 + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    pic_show2.setImageDrawable(bd);
                }
            }
        }
        TextView wq1 = (TextView) openDialogk.findViewById(R.id.wq1);
        TextView wq2 = (TextView) openDialogk.findViewById(R.id.wq2);
        RelativeLayout rts = (RelativeLayout) openDialogk.findViewById(R.id.rts);
        wq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogk.dismiss();
            }
        });
        wq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogk.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogk.dismiss();
            }
        });
        rts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogk.dismiss();
            }
        });
        openDialogk.show();
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (sps.getString(Find_difference_between_pictures.this, "resume_fp").equals("")) {
            sps.putString(Find_difference_between_pictures.this, "resume_fp", "yes");

        } else {
            String date = sps.getString(Find_difference_between_pictures.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
            } else {
                pos = 2;
            }
            Cursor cs = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + question_id + "' and rd='" + pos + "'");
            cs.moveToFirst();
            long dscore = 0;
            if (cs.getCount() != 0) {
                dscore = cs.getInt(cs.getColumnIndex("playtime"));
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

       /* if (sps.getInt(Find_words_from_picture.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            ads_lay.setVisibility(View.GONE);
        } else {
            if (sps.getInt(Find_words_from_picture.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(Find_words_from_picture.this, ads_lay);
            } else {
                if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                    sps.putInt(Find_words_from_picture.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(Find_words_from_picture.this);
                    adView.setAdUnitId(getString(R.string.main_banner_ori));

                    adView.setAdSize(AdSize.SMART_BANNER);
                    AdRequest request = new AdRequest.Builder().build();
                    adView.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            System.out.println("@@@loaded");
                            ads_lay.removeAllViews();
                            ads_lay.addView(adView);
                            ads_lay.setVisibility(View.VISIBLE);
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
        // }

        if (setting_access == 1) {
            setting_access = 0;
            //if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                data_download_game();
            /*} else {
                settingpermission();
            }*/
        } else if (setting_access == 2) {
            setting_access = 0;
           // if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                data_download_game();
            /*} else {
                settingpermission();
            }*/
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Find_difference_between_pictures.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
        } else {
            pos = 2;
        }

        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

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
                    Uri uri = FileProvider.getUriForFile(Find_difference_between_pictures.this, Find_difference_between_pictures.this.getPackageName(), file);
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

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Find_difference_between_pictures.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
        } else {
            pos = 2;
        }

        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                helpshare(a);
            } else {
                if (sps.getString(Find_difference_between_pictures.this, "permission_grand").equals("")) {
                    sps.putString(Find_difference_between_pictures.this, "permission_grand", "yes");
                    //  First_register("yes");
                    AlertDialog alertDialog = new AlertDialog.Builder(Find_difference_between_pictures.this).create();
                    alertDialog.setMessage("இந்த நிலையை உங்களது நண்பருக்கு பகிர  பின்வரும் permission-யை  allow செய்யவேண்டும்");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                        ActivityCompat.requestPermissions(Find_difference_between_pictures.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                                    } else {
                                        helpshare(a);
                                    }
                                }
                            });

                    alertDialog.show();

                } else {
                    if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(Find_difference_between_pictures.this, "permission") == 2) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Find_difference_between_pictures.this).create();
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
                                            String date = sps.getString(Find_difference_between_pictures.this, "date");
                                            int pos;
                                            if (date.equals("0")) {
                                                pos = 1;
                                            } else {
                                                pos = 2;
                                            }
                                            Cursor cs = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + question_id + "' and rd='" + pos + "'");
                                            cs.moveToFirst();
                                            long dscore = 0;
                                            if (cs.getCount() != 0) {
                                                dscore = cs.getInt(cs.getColumnIndex("playtime"));
                                            }
                                            focus.setBase(SystemClock.elapsedRealtime() + dscore);
                                            focus.start();
                                            dialog.dismiss();
                                            // finish();
                                        }
                                    });


                            alertDialog.show();
                        } else {
                            if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(Find_difference_between_pictures.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 152);
                            } else {
                                helpshare(a);
                            }
                        }
                    } else {
                        if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(Find_difference_between_pictures.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
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

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
            openDialog_p = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.back_pess);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();


                    String date = sps.getString(Find_difference_between_pictures.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                    } else {
                        pos = 2;
                    }

                    myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                    myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

                    // String date = sps.getString(Find_words_from_picture.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    } else {
                        if (sps.getString(Find_difference_between_pictures.this, "Exp_list").equals("on")) {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, Expandable_List_View.class);
                            startActivity(i);
                        } else {
                            if (main_act.equals("")) {
                                finish();
                                Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                finish();
                            }
                        }

                    }

                    //ad
                    if (sps.getInt(Find_difference_between_pictures.this, "purchase_ads") == 0) {
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

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);

        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Find_difference_between_pictures.this, "date");
        if (date.equals("0")) {
            next_game.setText("படத்திற்குள் கண்டுபிடி தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("படத்திற்குள் கண்டுபிடி புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");
        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Clue_Game_Hard.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Odd_man_out.class);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Ote_to_Tamil.class);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Riddle_game.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, WordError_correction.class);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });
        eng_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, English_to_tamil.class);
                startActivity(i);
            }
        });

        TextView quiz = (TextView) openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = (TextView) openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = (TextView) openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(Find_difference_between_pictures.this);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });
        find_words_from_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Find_difference_between_pictures.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Quiz_Game.class);
                startActivity(i);
            }
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Find_difference_between_pictures.this);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Jamble_word_game.class);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Missing_Words.class);
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
                sps.putString(Find_difference_between_pictures.this, "date", "0");
                Intent i = new Intent(Find_difference_between_pictures.this, Find_difference_between_pictures.class);
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
                    Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                sps.putString(Find_difference_between_pictures.this, "date", "0");


              /*  finish();
                openDialog.dismiss();
                //sps.putString(Odd_man_out.this, "date", "0");
                Intent i = new Intent(Odd_man_out.this, New_Main_Activity.class);
                startActivity(i);*/
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
    }

    private void update_price() {
        ////////////////Prize//////////////////
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        int f_sec = sec + sec2 + seconds;
        String date = sps.getString(Find_difference_between_pictures.this, "date");
        int uans = 0;

        Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where useranswer='0'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        cd.moveToFirst();
        if (cd.getCount() != 0) {
            uans = cd.getCount();
        }
        System.out.println("################################date" + date);
        System.out.println("################################timeElapsed" + timeElapsed);
        System.out.println("################################uans" + uans);
        System.out.println("################################answerlength" + answerlength);
        System.out.println("################################b_score" + b_score);
        if (date.equals("0")) {
            if (timeElapsed <= 91300) {
                if (uans == answerlength) {
                    prize_data_update(Find_difference_between_pictures.this, 75);
                } else if (b_score != 0) {
                    prize_data_update(Find_difference_between_pictures.this, 25);
                }
            } else if (timeElapsed > 91300) {
                if (uans == answerlength) {
                    prize_data_update(Find_difference_between_pictures.this, 50);
                } else if (b_score != 0) {
                    prize_data_update(Find_difference_between_pictures.this, 25);
                }
            } else {
                if (uans != 0) {
                    prize_data_update(Find_difference_between_pictures.this, 25);
                }
            }
        } else {
            if (timeElapsed <= 91300) {
                if (uans == answerlength) {
                    prize_data_update(Find_difference_between_pictures.this, 100);
                } else if (b_score != 0) {
                    prize_data_update(Find_difference_between_pictures.this, 50);
                }
            } else if (timeElapsed > 91300) {
                if (uans == answerlength) {
                    prize_data_update(Find_difference_between_pictures.this, 75);
                } else if (uans != 0) {
                    prize_data_update(Find_difference_between_pictures.this, 50);
                }
            } else {
                if (uans != 0) {
                    prize_data_update(Find_difference_between_pictures.this, 25);
                }
            }
        }
        ////////////////Prize//////////////////
    }

    public void data_download_game() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (sps.getString(Find_difference_between_pictures.this, "permission_grand").equals("")) {
                sps.putString(Find_difference_between_pictures.this, "permission_grand", "yes");
                AlertDialog alertDialog = new AlertDialog.Builder(Find_difference_between_pictures.this).create();
                alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய பின்வரும் permission-யை allow செய்யவேண்டும்");
                alertDialog.setCancelable(false);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                    ActivityCompat.requestPermissions(Find_difference_between_pictures.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 150);
                                } else {
                                    downloaddata_regular();
                                }
                            }
                        });

                alertDialog.show();
            } else {
                if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if (sps.getInt(Find_difference_between_pictures.this, "permission") == 2) {
                        AlertDialog alertDialog = new AlertDialog.Builder(Find_difference_between_pictures.this).create();
                        alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய  permission-யை allow செய்யவேண்டும்");
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
                                        setting_access = 2;
                                    }
                                });

                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                                        finish();
                                        dialog.dismiss();
                                    }
                                });


                        alertDialog.show();
                    } else {
                        if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(Find_difference_between_pictures.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 150);
                        } else {
                            downloaddata_regular();
                        }
                    }
                } else {
                    if ((ContextCompat.checkSelfPermission(Find_difference_between_pictures.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        ActivityCompat.requestPermissions(Find_difference_between_pictures.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 150);
                    } else {
                        downloaddata_regular();
                    }
                }

            }
        } else {
            downloaddata_regular();
        }*/

        downloaddata_regular();
    }

    public void downloaddata_regular() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_difference_between_pictures.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                    download_datas();
                } else {
                    NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                    native_banner_ad_container.setVisibility(View.INVISIBLE);
                    head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_difference_between_pictures.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                            .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                    sps.putInt(Find_difference_between_pictures.this, "goto_sett", 1);


                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                                    String date = sps.getString(Find_difference_between_pictures.this, "date");
                                    if (date.equals("0")) {
                                        backexitnet();
                                    } else {
                                        backexitnet();
                                    }
                                   /* Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
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
                /*Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
                startActivity(i);*/
                sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void download_datas() {
        Cursor cz = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndex("questionid")));
        }
        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Find_difference_between_pictures.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    public void missingimage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_difference_between_pictures.this);                            /*.setTitle("Delete entry")*/
        alertDialogBuilder.setMessage("படங்கள் இல்லை பதிவிறக்கம் செய்யவேண்டுமா? ")
                .setPositiveButton("ஆம்", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            String date = sps.getString(Find_difference_between_pictures.this, "date");
                            if (date.equals("0")) {
//                                Cursor cursor1 = newhelper5.getQry("SELECT * FROM newgames5 where gameid=16 order by questionid desc");
                                Cursor c2 = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='1'");
                                c2.moveToFirst();
                                int count1 = c2.getCount();
                                String lastid = String.valueOf(count1);

                               /* Cursor cursor1 = newhelper6.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='1'");
                                cursor1.moveToFirst();
                                String lastid = null;
                                if (cursor1.getCount() != 0) {
                                    lastid = String.valueOf(cursor1.getInt(cursor1.getColumnIndex("questionid")));
                                }*/
                                System.out.println("--last q id : " + lastid);
                                downpic(question_id, lastid);
                            }
                            dialog.dismiss();
                            System.out.println("checkdismiss2");
                        } else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_difference_between_pictures.this);                         /*   .setTitle("Delete entry")*/
                            alertDialogBuilder.setCancelable(false);
                            alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                                    .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 2);
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // do nothing
                                            sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                                            String date = sps.getString(Find_difference_between_pictures.this, "date");
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
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == 150) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Find_difference_between_pictures.this, "permission", 1);
                downloaddata_regular();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Find_difference_between_pictures.this, "permission", 2);
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Find_difference_between_pictures.this, "permission", 0);
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    }
                }
            }

        }

        if (requestCode == 152) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Find_difference_between_pictures.this, "permission", 1);
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
                        sps.putInt(Find_difference_between_pictures.this, "permission", 2);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Find_difference_between_pictures.this, "permission", 0);
                    }
                }
            }
        }
    }

    public void downpic(final String first, final String last) {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
        native_banner_ad_container.setVisibility(View.INVISIBLE);
        head.setVisibility(View.INVISIBLE);
        Utils.mProgress(Find_difference_between_pictures.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {


                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("firstqid", first));
                nameValuePairs.add(new BasicNameValuePair("action", "get_gamedata"));
                nameValuePairs.add(new BasicNameValuePair("gameid", gameid));
                nameValuePairs.add(new BasicNameValuePair("lastqid", last));
                nameValuePairs.add(new BasicNameValuePair("android_id", sps.getString(Find_difference_between_pictures.this, "email")));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));

                System.out.println("--- send data : " + nameValuePairs);
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(data_download_url);
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in https connection" + e.toString());
                }
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                    System.out.print("Result============" + result);
                } catch (Exception e) {

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Utils.mProgress.dismiss();
                startDownload();

            }
        }.execute();
    }

    public void startDownload() {


        String str_url = img_down_url + sps.getString(Find_difference_between_pictures.this, "email") + "-filename.zip";

        //     new DownloadFileAsync().execute(str_url);


        downloadFileAsync = new DownloadFileAsync();
        downloadFileAsync.execute(str_url);
    }

    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(Find_difference_between_pictures.this);
                mProgressDialog.setMessage("படங்கள் பதிவிறக்கம் செய்யப்படுகிறது காத்திருக்கவும்.... ");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                if (!mProgressDialog.isShowing()) {
                    mProgressDialog.show();
                }

                // playy();

                return mProgressDialog;

            default:
                return null;
        }
    }


    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(aurl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                System.out.print("========siva ");
                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                final int fileLength = connection.getContentLength();

                File SDCardRoot = getFilesDir();

                File fol = new File(SDCardRoot + "/Nithra/solliadi/");
                if (!fol.exists()) {
                    fol.mkdirs();
                }
                email = sps.getString(Find_difference_between_pictures.this, "email");
                File file = new File(SDCardRoot + "/Nithra/solliadi/", email + "-filename.zip");

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(file);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    publishProgress("" + (int) ((total * 100) / fileLength));
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        output.write(data, 0, count);
                }

                unpackZip(email + "-filename.zip");

            } catch (Exception e) {


                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();


            }
            return null;

        }


        @Override
        protected void onPostExecute(String unused) {
            mProgressDialog.dismiss();
            next();
            System.out.println("jas12");
        }


        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
            /*if(!isNetworkAvailable()){
				downloadFileAsync.isCancelled();
				//downloadFileAsync.cancel(true);

			}*/
        }
    }


    public int unpackZip(String ZIP_FILE_NAME) {

        InputStream is;
        ZipInputStream zis;
        try {

            String fullPath = getFilesDir() + "/Nithra/solliadi/";
            is = new FileInputStream(fullPath + ZIP_FILE_NAME);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;

            while ((ze = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;

                // zapis do souboru
                String filename = ze.getName();
                FileOutputStream fout = new FileOutputStream(fullPath
                        + filename);

                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
            File file = new File(fullPath + ZIP_FILE_NAME);
            file.delete();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Find_difference_between_pictures.this)) {
                download_datas();
            } else {
                NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);
                native_banner_ad_container.setVisibility(View.INVISIBLE);
                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_difference_between_pictures.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                sps.putInt(Find_difference_between_pictures.this, "goto_sett", 1);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String date = sps.getString(Find_difference_between_pictures.this, "date");
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
        final Dialog openDialog = new Dialog(Find_difference_between_pictures.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        }, 2200);

        Handler handler21 = new Handler();
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    int skx = cfx.getInt(cfx.getColumnIndex("coins"));
                    int spx = skx + 10;
                    String aStringx = Integer.toString(spx);
                    score.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                }
                // setSc();
            }
        }, 700);

    }

    public void settingpermission() {
        if (sps.getInt(Find_difference_between_pictures.this, "permission") == 2) {
            AlertDialog alertDialog = new AlertDialog.Builder(Find_difference_between_pictures.this).create();
            alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய Settings-ல் உள்ள permission-யை allow செய்யவேண்டும்");
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
                            setting_access = 1;

                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sps.putString(Find_difference_between_pictures.this, "game_area", "on");
                            String date = sps.getString(Find_difference_between_pictures.this, "date");
                            if (date.equals("0")) {
                                if (main_act.equals("")) {
                                    finish();
                                    Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else {
                                    finish();
                                }
                            } else {
                                finish();
                                Intent i = new Intent(Find_difference_between_pictures.this, New_Main_Activity.class);
                                startActivity(i);
                            }
                            dialog.dismiss();
                        }
                    });


            alertDialog.show();
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
                if (reward_status == 1) {
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
                } else {
                    Toast.makeText(Find_difference_between_pictures.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
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
                if (reward_status == 1) {
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
        *//*      rewardedVideoAd.setAdListener(new RewardedVideoAdListener() {

            @Override
            public void onRewardedVideoCompleted() {
                reward_status = 1;
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onRewardedVideoClosed() {
                reward(context);
                if (reward_status == 1) {
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
                } else {
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
    }*/

}
