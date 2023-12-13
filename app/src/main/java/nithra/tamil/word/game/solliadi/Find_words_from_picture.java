package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.data_download_url;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.img_down_url;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
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
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.android.material.snackbar.Snackbar;

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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Find_words_from_picture extends AppCompatActivity implements Download_completed {
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    static final int mCoinCount = 20;
    public static int chr = 0;
    static int rvo = 0;
    final SharedPreference sps = new SharedPreference();
    final String gameid = "16";
    int fb_reward = 0;
    int reward_status = 0;
    CustomKeyboard mCustomKeyboard;
    Newgame_DataBaseHelper5 newhelper5;
    Chronometer focus;
    TextView score, ans1, ans2, ans3, ans4, ans5, ans6, ans7, clear, questionid, p_facebook, p_watts_app;
    ImageView image_1, value_ans1, value_ans2, value_ans3, value_ans4, value_ans5, value_ans6, value_ans7;
    AppCompatEditText ans_editer;
    String question_id = "", question = "", answer = "";
    int u_id = 0;
    int rdvalu;
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
    TextView tx1, tx2;
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
    String email = "";
    TextView p_coins;
    int e2;
    int setting_access = 0;

    Handler handler;
    Runnable my_runnable;
    private MaxRewardedAd rewardedAd;
    private MaxInterstitialAd mInterstitialAd;

    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void download_completed(String status) {
        System.out.println("#############################status" + status);
        if (status.equals("nodata")) {
            nextgamesdialog();
        } else {
            Handler handler = new Handler(Looper.myLooper());
            handler.postDelayed(() -> {
                next();
                System.out.println("p1");

            }, 500);

        }
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_words_from_picture);
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);
        mCustomKeyboard = new CustomKeyboard(this, R.id.keyboardview, R.xml.hexkbd);
        mCustomKeyboard.registerEditText(R.id.ans_editer);
        newhelper5 = new Newgame_DataBaseHelper5(this);
        myDbHelper = new DataBaseHelper(this);

        myDbHelper = new DataBaseHelper(Find_words_from_picture.this);
        newhelper5 = new Newgame_DataBaseHelper5(Find_words_from_picture.this);
        newhelper = new Newgame_DataBaseHelper(Find_words_from_picture.this);
        newhelper2 = new Newgame_DataBaseHelper2(Find_words_from_picture.this);
        newhelper3 = new Newgame_DataBaseHelper3(Find_words_from_picture.this);
        newhelper4 = new Newgame_DataBaseHelper4(Find_words_from_picture.this);

        if (sps.getString(Find_words_from_picture.this, "new_user_db").equals("")) {

        } else {
            if (sps.getString(Find_words_from_picture.this, "new_user_db").equals("on")) {
                sps.putString(Find_words_from_picture.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(Find_words_from_picture.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        Utills.INSTANCE.initializeAdzz(this);
        rewarded_adnew();
        if (sps.getInt(Find_words_from_picture.this, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            industrialload();
        }

        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(Find_words_from_picture.this, R.raw.click, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Find_words_from_picture.this, R.raw.wrong, 1);
        win = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = win.load(Find_words_from_picture.this, R.raw.win, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Find_words_from_picture.this, R.raw.coins, 1);
        cr_ans = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId5 = cr_ans.load(Find_words_from_picture.this, R.raw.cr_ans, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Find_words_from_picture.this, R.raw.coins, 1);///
        openDialog_s = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen);
        ads_layout_bottom = openDialog_s.findViewById(R.id.fl_adplaceholder);

        //loadRewardedVideoAd();
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");


        soundset();
        find();
        click();
        next();
        System.out.println("p2");


        if (sps.getInt(Find_words_from_picture.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //fb_addload_score_screen(Find_words_from_picture.this);

        }


        if (sps.getString(Find_words_from_picture.this, "fn_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Find_words_from_picture.this, "sequence example fn3_fn");

            sequence.setConfig(config);

            sequence.addSequenceItem(value_ans1, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            sequence.addSequenceItem(verify, "சரிபார்க்க பொத்தானை அழுத்தி விடையை சரிபார்த்துக்கொள்ளவும்.", "அடுத்து");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Find_words_from_picture.this).setTarget(p_facebook).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener((itemView, position) -> {

                if (position == 2) {
                    sps.putString(Find_words_from_picture.this, "time_start_fn", "yes");
                    sps.putString(Find_words_from_picture.this, "showcase_dismiss_fn_intro", "yes");
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();

                }
            });
            sequence.start();
            sps.putString(Find_words_from_picture.this, "fn_intro", "no");

        }

    }


    private void industrialload() {
        //AppLovinSdk.getInstance( this ).showMediationDebugger();
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(AppLovinSdkConfiguration config) {
                // AppLovin SDK is initialized, start loading ads
                if (mInterstitialAd != null && mInterstitialAd.isReady()) return;
                System.out.println("ad shown  showAdWithDelay initialize done ");
                mInterstitialAd = new MaxInterstitialAd(getResources().getString(R.string.Viliyodu_Vilaiyadu_Ins), Find_words_from_picture.this);
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

    }

    public void adShow() {
        if (sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV") == Utills.interstitialadCount && mInterstitialAd != null) {
            sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", 0);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                mInterstitialAd.showAd("Viliyodu Vilaiyadu Ins");
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game1_Stage_Close_VV", (sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV") + 1));
            if (sps.getInt(this, "Game1_Stage_Close_VV") > Utills.interstitialadCount)
                sps.putInt(this, "Game1_Stage_Close_VV", 0);

            setSc();
            //Toast.makeText(this, ""+sps.getInt(this, "Game1_Stage_Close_VV"), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rewardedAd = null;
        mInterstitialAd = null;
        handler = null;
    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Find_words_from_picture.this, "showcase_dismiss_fn_intro").equals("")) {
                showcase_dismiss();
            } else {
                sps.putString(Find_words_from_picture.this, "time_start_fn", "yes");
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

            }

        }, 800);
    }

    private void soundset() {
        String snd = sps.getString(Find_words_from_picture.this, "snd");
        p_setting = findViewById(R.id.p_settings);
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
            skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        }
        score.setText("" + skx);
        reset();

        head.setVisibility(View.VISIBLE);
        ans_editer.requestFocus();
        String date = sps.getString(Find_words_from_picture.this, "date");
        // myDbHelper.executeSql("DELETE FROM answertable");
        if (date.equals("0")) {
            Cursor c1 = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "'");
            c1.moveToFirst();

            Cursor c2 = newhelper5.getQry("select * from newgames5 where gameid='" + gameid + "' and isfinish='1'");
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
            isdown = c.getString(c.getColumnIndexOrThrow("isdown"));
            String tfoption = answer;
            String[] first = tfoption.split(",");
            int optlen = first.length;

            int letter_type = first.length;
            if (letter_type > 7) {
                answerlength = 7;
            } else {
                answerlength = letter_type;
            }

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


            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(question.replace(".webp", ""), "drawable", getPackageName());
                image_1.setVisibility(View.VISIBLE);
                image_1.setImageResource(im1);
            } else {
                String fullPath = getFilesDir() + "/Nithra/solliadi/";
                File file = new File(fullPath + question + "");
                System.out.println("printqus1" + file);
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + question + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    image_1.setImageDrawable(bd);
                    System.out.println("printqus2" + question);
                } else {
                    missingimage();
                    System.out.println("printqus3" + question);
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
                    String ansn = csk.getString(csk.getColumnIndexOrThrow("answer"));
                    int dscore = csk.getInt(csk.getColumnIndexOrThrow("levelscore"));
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
                    String ansn = csk1.getString(csk1.getColumnIndexOrThrow("answer"));
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
                if (sps.getString(Find_words_from_picture.this, "time_start_fn").equals("")) {
                    sps.putString(Find_words_from_picture.this, "time_start_fn", "yes");

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
            if (ans_count >= final_ans_count) {
                if (date.equals("0")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid='" + gameid + "'");
                } else {
                    myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + question_id + "'and gameid='" + gameid + "'");
                }
                adShow();
            }
        } else {
            downloaddata_regular();

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


    }


    public void set_val(int val) {
        // Toast.makeText(this, "set_val"+val, Toast.LENGTH_SHORT).show();
        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        int sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));

        if (sk > 50) {

            if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1 ");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (ans_count <= final_ans_count) {
                        String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                        //Score Adding
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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
                    if (ans_count >= final_ans_count) {
                        verify.setVisibility(View.INVISIBLE);
                        focus.stop();
                        // update_price();
                        String date = sps.getString(Find_words_from_picture.this, "date");
                        if (date.equals("0")) {
                            newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid='" + gameid + "'");
                        } else {
                            myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + question_id + "'and gameid='" + gameid + "'");
                        }
                        update_price();
                        // completegame();
                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(() -> adShow(), 2000);
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

        final Dialog openDialog = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.show_ans);
        TextView yes = openDialog.findViewById(R.id.yes);
        TextView no = openDialog.findViewById(R.id.no);
        CheckBox checkbox_ans = openDialog.findViewById(R.id.checkbox_ans);
        checkbox_ans.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                sps.putString(getApplicationContext(), "checkbox_ans", "yes");
            } else {
                sps.putString(getApplicationContext(), "checkbox_ans", "");
            }
        });

        yes.setOnClickListener(v -> {
            Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1 ");
            cd.moveToFirst();
            if (cd.getCount() != 0) {
                if (ans_count <= final_ans_count) {
                    String sa = cd.getString(cd.getColumnIndexOrThrow("answer"));
                    myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + sa + "'and levelid='" + question_id + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                    myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + sa + "' and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");

                    //Score Adding
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
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
                if (ans_count >= final_ans_count) {
                    verify.setVisibility(View.INVISIBLE);
                    focus.stop();
                    // update_price();
                    String date = sps.getString(Find_words_from_picture.this, "date");
                    if (date.equals("0")) {
                        newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid='" + gameid + "'");
                    } else {
                        myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + question_id + "'and gameid='" + gameid + "'");
                    }
                    // completegame();
                    update_price();
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> adShow(), 2000);
                }
            }
        });
        no.setOnClickListener(v -> {
            sps.putString(getApplicationContext(), "checkbox_ans", "");
            openDialog.dismiss();
        });
        if (!isFinishing()) openDialog.show();
    }

    private void click() {
        clear.setOnClickListener(v -> {
            chr = 1;
            mCustomKeyboard.letter_del_change("1");
            mCustomKeyboard.letter_del_change("1");
            pressKey();
        });
        clear.setOnLongClickListener(v -> {
            chr = 1;
            mCustomKeyboard.letter_del_change("1");
            ans_editer.setText("");
            return false;
        });
        verify.setOnClickListener(v -> verify_data());
        p_setting.setOnClickListener(v -> {
            p_setting.setBackgroundResource(R.drawable.sound_off);
            String snd = sps.getString(Find_words_from_picture.this, "snd");
            if (snd.equals("off")) {
                sps.putString(Find_words_from_picture.this, "snd", "on");
                p_setting.setBackgroundResource(R.drawable.sound_on);
                sv = 1;
            } else if (snd.equals("on")) {
                sps.putString(Find_words_from_picture.this, "snd", "off");
                p_setting.setBackgroundResource(R.drawable.sound_off);
                sv = 0;
            }
        });
        value_ans1.setOnClickListener(v -> set_val(1));
        value_ans2.setOnClickListener(v -> set_val(2));
        value_ans3.setOnClickListener(v -> set_val(3));
        value_ans4.setOnClickListener(v -> set_val(4));
        value_ans5.setOnClickListener(v -> set_val(5));
        value_ans6.setOnClickListener(v -> set_val(6));
        value_ans7.setOnClickListener(v -> set_val(7));
        image_1.setOnClickListener(v -> pic_show(1));

        p_facebook.setOnClickListener(v -> {
            share_name = 1;
            final String a = "com.facebook.katana";
            permission(a);
        });
        p_watts_app.setOnClickListener(v -> {
            share_name = 2;
            String a = "com.whatsapp";
            permission(a);
        });
        qwt.setOnClickListener(v -> dialog(0));


        ans_editer.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(verify.getWindowToken(), 0);
        });
        ans_editer.setOnTouchListener((v, event) -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(verify.getWindowToken(), 0);

            return true;
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
                CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "பதிவு செய்துவிட்டீர்கள்", Snackbar.LENGTH_SHORT);
                final View view = snackbar.getView();
                TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
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

                        coinanim();
                        Cursor ch = myDbHelper.getQry("SELECT * FROM score ");
                        ch.moveToFirst();
                        int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
                        int shh = sh + 10;
                        myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");
                        setans(ans);
                        ans_editer.setText("");
                        ans_count++;
                        if (ans_count >= final_ans_count) {
                            verify.setVisibility(View.INVISIBLE);

                            focus.stop();
                            String date = sps.getString(Find_words_from_picture.this, "date");
                            if (date.equals("0")) {
                                newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE questionid='" + question_id + "'and gameid='" + gameid + "'");
                            } else {
                                myDbHelper.executeSql("UPDATE dailytest SET isfinish='1' WHERE levelid='" + question_id + "'and gameid='" + gameid + "'");
                            }
                            update_price();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(() -> adShow(), 2000);
                        }

                    }
                } else {
                    worng.play(soundId2, sv, sv, 0, 0, sv);
                    ans_editer.setText("");
                    CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "சரியான விடையாக இருக்கலாம் .\n ஆனால் எங்கள் தொகுப்பில் இல்லை ", Snackbar.LENGTH_SHORT);
                    final View view = snackbar.getView();
                    TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
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
            CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "எழுத்துக்களை நிரப்பவும்", Snackbar.LENGTH_SHORT);
            final View view = snackbar.getView();
            TextView textView = view.findViewById(com.google.android.material.R.id.snackbar_text);
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
        p_coins = findViewById(R.id.p_coins);
        focus = findViewById(R.id.p_time_edit);
        score = findViewById(R.id.p_score_edit);
        anslist2 = findViewById(R.id.anslist2);
        list2_pic = findViewById(R.id.list2_pic);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        ans5 = findViewById(R.id.ans5);
        ans6 = findViewById(R.id.ans6);
        ans7 = findViewById(R.id.ans7);
        verify = findViewById(R.id.verify);
        clear = findViewById(R.id.clear);
        questionid = findViewById(R.id.questionid);
        p_facebook = findViewById(R.id.p_facebook);
        p_watts_app = findViewById(R.id.p_watts_app);
        value_ans1 = findViewById(R.id.value_ans1);
        value_ans2 = findViewById(R.id.value_ans2);
        value_ans3 = findViewById(R.id.value_ans3);
        value_ans4 = findViewById(R.id.value_ans4);
        value_ans5 = findViewById(R.id.value_ans5);
        value_ans6 = findViewById(R.id.value_ans6);
        value_ans7 = findViewById(R.id.value_ans7);
        image_1 = findViewById(R.id.image_1);
        ans_editer = findViewById(R.id.ans_editer);
        head = findViewById(R.id.head);
        ads_lay = findViewById(R.id.ads_lay);
        qwt = findViewById(R.id.qwt);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = 0;
        if (cfx.getCount() != 0) {
            skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        }
        score.setText("" + skx);

        ImageView prize_logo = findViewById(R.id.prize_logo);
        if (sps.getInt(Find_words_from_picture.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                if (sps.getString(Find_words_from_picture.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Find_words_from_picture.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Find_words_from_picture.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Find_words_from_picture.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Find_words_from_picture.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Find_words_from_picture.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pressKey() {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL);
        ans_editer.onKeyDown(KeyEvent.KEYCODE_DEL, event);
    }

    public void dialog(int i) {

        final Dialog openDialog_earncoin = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Find_words_from_picture.this, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();

                    String date = sps.getString(Find_words_from_picture.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                    } else {
                        pos = 2;
                    }
                    myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                    myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {
                    fb_reward = 0;
                    //reward(Find_words_from_picture.this);
                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(Find_words_from_picture.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);
                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        });

        wp.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
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
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {

                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
                    focus.stop();
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    String date = sps.getString(Find_words_from_picture.this, "date");
                    int pos;
                    if (date.equals("0")) {
                        pos = 1;
                    } else {
                        pos = 2;
                    }
                    myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
                    myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

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

        TextView video_earn = openDialog_s.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Find_words_from_picture.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");

        ImageView prize_logo = openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Find_words_from_picture.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                if (sps.getString(Find_words_from_picture.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(Find_words_from_picture.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sps.getString(Find_words_from_picture.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(Find_words_from_picture.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(Find_words_from_picture.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(Find_words_from_picture.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Find_words_from_picture.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);


        if (sps.getInt(Find_words_from_picture.this, "purchase_ads") == 1) {
            ads_layout_bottom.setVisibility(View.GONE);
        } else {
            // New_Main_Activity.load_addFromMain_multiplayer(Find_words_from_picture.this, ads_layout_bottom);
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(Find_words_from_picture.this, ads_layout_bottom);
            } else {
                ads_layout_bottom.setVisibility(View.GONE);
            }
        }


        if (sps.getString(Find_words_from_picture.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Find_words_from_picture.this, "date");
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


        RelativeLayout adsicon = openDialog_s.findViewById(R.id.adsicon);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam);
        adsicon.startAnimation(shake);

        // final LinearLayout vid_earn = (LinearLayout) openDialog_s.findViewById(R.id.vid_earn);

        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Find_words_from_picture.this, "" + "Reward video", "Loading...");
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
                            //reward(Find_words_from_picture.this);
                            rewarded_adnew();
                            Toast.makeText(Find_words_from_picture.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }

        });

        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(Find_words_from_picture.this, "" + "Reward video", "Loading...");
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
                            //reward(Find_words_from_picture.this);
                            rewarded_adnew();
                            Toast.makeText(Find_words_from_picture.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }
        });
        wtp.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
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
        });
        fbs.setOnClickListener(view -> {

        });
        gplus.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
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

        });
        System.out.println("#############################" + " SELECT * FROM answertable where useranswer='1'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        int uans = 0;
        Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where useranswer='0'and levelid='" + question_id + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        cd.moveToFirst();
        if (cd.getCount() != 0) {
            uans = cd.getCount();
        }

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

            String date = sps.getString(Find_words_from_picture.this, "date");
            if (!date.equals("0")) {
                next_continue.setText("சரி");
            }
            Handler handler1 = new Handler(Looper.myLooper());
            handler1.postDelayed(() -> {
                coin.play(soundId4, sv, sv, 0, 0, sv);
                //play1.start();
                cns3.setVisibility(View.VISIBLE);
            }, 500);
            Handler handler2 = new Handler(Looper.myLooper());
            handler2.postDelayed(() -> {
                // play2.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns1.setVisibility(View.VISIBLE);
            }, 1000);
            Handler handler3 = new Handler(Looper.myLooper());
            handler3.postDelayed(() -> {
                //  play3.start();
                coin.play(soundId4, sv, sv, 0, 0, sv);
                cns2.setVisibility(View.VISIBLE);
            }, 1500);


            Handler handler6 = new Handler(Looper.myLooper());
            handler6.postDelayed(() -> {
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
                cns3.postDelayed(() -> cns3.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 1500);
            Handler handler7 = new Handler(Looper.myLooper());
            handler7.postDelayed(() -> {
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
                cns1.postDelayed(() -> cns1.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 1900);
            Handler handler8 = new Handler(Looper.myLooper());
            handler8.postDelayed(() -> {
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
                cns2.postDelayed(() -> cns2.setVisibility(View.INVISIBLE), transAnimation.getDuration());

            }, 2300);

            case2 = 0;
            tot2 = 30;
            new Thread(() -> {
                while (case2 < tot2) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    bsscores.post(() -> bsscores.setText("" + case2));
                    case2++;
                }

            }).start();


            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                tt_tot2 = tt_case2 + 30;
                String aStringx = Integer.toString(tt_case2);
                ttscores.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + tt_tot2 + "'");
            }


            Handler handler11 = new Handler(Looper.myLooper());
            handler11.postDelayed(() -> new Thread(() -> {

                while (tt_case2 < tt_tot2) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ttscores.post(() -> ttscores.setText("" + tt_case2));
                    tt_case2++;
                }

            }).start(), 1500);

            Handler hand = new Handler(Looper.myLooper());
            hand.postDelayed(() -> next_continue.setVisibility(View.VISIBLE), 2500);

            next_continue.setOnClickListener(view -> {
                y = 0;
                case2 = 0;
                tot2 = 0;
                tt_case2 = 0;
                tt_tot2 = 0;

                dia_dismiss = 1;
                openDialog_s.dismiss();
                next();
                System.out.println("p3");

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
            String date = sps.getString(Find_words_from_picture.this, "date");
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


            next_continue.setOnClickListener(view -> {
                y = 0;
                case2 = 0;
                tot2 = 0;
                tt_case2 = 0;
                tt_tot2 = 0;
                dia_dismiss = 1;
                openDialog_s.dismiss();
                next();
                System.out.println("p7");

            });
        }

        openDialog_s.setOnDismissListener(dialog -> {
            if (dia_dismiss != 1) {
                sps.putString(Find_words_from_picture.this, "game_area", "on");
                tot2 = 0;
                tt_tot2 = 0;
                case2 = 0;
                y = 0;
                tt_case2 = 0;


                String date = sps.getString(Find_words_from_picture.this, "date");
                if (date.equals("0")) {


                    if (main_act.equals("")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                        openDialog_s.dismiss();

                    }
                } else {
                    if (sps.getString(Find_words_from_picture.this, "Exp_list").equals("on")) {
                        finish();
                        openDialog_s.dismiss();
                        Intent i = new Intent(Find_words_from_picture.this, Expandable_List_View.class);
                        startActivity(i);

                    } else {
                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
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

    //reward videos***********************//
    public void share_earn2(int a) {
        int skx = 0;
        final Dialog openDialog = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        ok_y.setOnClickListener(v -> {
            ttscores.setText("" + finalSkx);
            score.setText("" + finalSkx);
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
            final Dialog openDialog = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            ok_y.setOnClickListener(v -> {
                score.setText("" + spxdr);
                openDialog.dismiss();
                //mCoinCount = 0;
            });

            if (!isFinishing()) openDialog.show();
        }

    }

    public void pic_show(int a) {
        openDialogk = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialogk.setContentView(R.layout.show_pic);
        TouchImageView pic_show = openDialogk.findViewById(R.id.pic_show);
        Button cancel = openDialogk.findViewById(R.id.p_cancel);

        if (a == 1) {
            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(question.replace(".webp", ""), "drawable", getPackageName());
                pic_show.setImageResource(im1);
            } else {
                String fullPath = getFilesDir() + "/Nithra/solliadi/";
                File file = new File(fullPath + question + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + question + "");
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    pic_show.setImageDrawable(bd);
                }
            }
        }
        TextView wq1 = openDialogk.findViewById(R.id.wq1);
        TextView wq2 = openDialogk.findViewById(R.id.wq2);
        RelativeLayout rts = openDialogk.findViewById(R.id.rts);
        wq1.setOnClickListener(view -> openDialogk.dismiss());
        wq2.setOnClickListener(view -> openDialogk.dismiss());
        cancel.setOnClickListener(view -> openDialogk.dismiss());
        rts.setOnClickListener(v -> openDialogk.dismiss());
        openDialogk.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (handler != null) handler.postDelayed(my_runnable, 1000);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ON Resume  " + sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV"));


        if (sps.getString(Find_words_from_picture.this, "resume_fp").equals("")) {
            sps.putString(Find_words_from_picture.this, "resume_fp", "yes");

        } else {
            String date = sps.getString(Find_words_from_picture.this, "date");
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
                dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
            }
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

        // }

        if (setting_access == 1 || setting_access == 2) {
            setting_access = 0;
            downloaddata_regular();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Find_words_from_picture.this, "date");
        int pos;
        if (date.equals("0")) pos = 1;
        else pos = 2;

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

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Find_words_from_picture.this, Find_words_from_picture.this.getPackageName(), file);
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

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Find_words_from_picture.this, "date");
        int pos;
        if (date.equals("0")) {
            pos = 1;
        } else {
            pos = 2;
        }

        myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
        myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");

        helpshare(a);
    }
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            back();
        }
    };

    private void back() {
        openDialog_p = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        TextView yes = openDialog_p.findViewById(R.id.yes);
        TextView no = openDialog_p.findViewById(R.id.no);

        yes.setOnClickListener(v -> {
            sps.putString(Find_words_from_picture.this, "game_area", "on");


            focus.stop();
            ttstop = focus.getBase() - SystemClock.elapsedRealtime();


            String date = sps.getString(Find_words_from_picture.this, "date");
            int pos;
            if (date.equals("0")) {
                pos = 1;
            } else {
                pos = 2;
            }

            myDbHelper.executeSql("UPDATE answertable SET playtime='" + ttstop + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
            myDbHelper.executeSql("UPDATE answertable SET levelscore='" + b_score + "' WHERE levelid='" + question_id + "' and gameid='" + gameid + "' and rd='" + pos + "'");
            //String date = sps.getString(Find_words_from_picture.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            } else {
                if (sps.getString(Find_words_from_picture.this, "Exp_list").equals("on")) {
                    finish();
                    Intent i = new Intent(Find_words_from_picture.this, Expandable_List_View.class);
                    startActivity(i);
                } else {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
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

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = openDialog.findViewById(R.id.next_game);
        TextView p_game = openDialog.findViewById(R.id.picgame);
        TextView c_game = openDialog.findViewById(R.id.hintgame);
        TextView s_game = openDialog.findViewById(R.id.solgame);
        TextView w_game = openDialog.findViewById(R.id.wordgame);

        TextView exit = openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Find_words_from_picture.this, "date");
        if (date.equals("0")) {
            next_game.setText("படத்திற்குள் கண்டுபிடி தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("படத்திற்குள் கண்டுபிடி புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");
        }

        c_game.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Clue_Game_Hard.class);
            startActivity(i);
        });
        s_game.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Solukul_Sol.class);
            startActivity(i);
        });
        w_game.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Word_Game_Hard.class);
            startActivity(i);
        });
        p_game.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Picture_Game_Hard.class);
            startActivity(i);
        });

        exit.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
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
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Match_Word.class);
            startActivity(i);
        });
        odd_man_out.setOnClickListener(view -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Odd_man_out.class);
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
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Opposite_word.class);
            startActivity(i);
        });
        ote_to_tamil.setOnClickListener(view -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Ote_to_Tamil.class);
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
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Makeword_Rightorder.class);
            startActivity(i);
        });
        puthir.setOnClickListener(view -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Riddle_game.class);
            startActivity(i);
        });
        tirukural.setOnClickListener(view -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Tirukural.class);
            startActivity(i);
        });
        pilaithiruthu.setOnClickListener(view -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, WordError_correction.class);
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
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Fill_in_blanks.class);
            startActivity(i);
        });

        TextView quiz = openDialog.findViewById(R.id.quiz);
        TextView find_words_from_pictures = openDialog.findViewById(R.id.find_words_from_pictures);
        TextView match_words = openDialog.findViewById(R.id.match_words);
        Newgame_DataBaseHelper5 newhelper5 = new Newgame_DataBaseHelper5(Find_words_from_picture.this);
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
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Match_tha_fallows_game.class);
            startActivity(i);

        });
        find_words_from_pictures.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Find_words_from_picture.class);
            startActivity(i);
        });
        quiz.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Quiz_Game.class);
            startActivity(i);
        });
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Find_words_from_picture.this);
        TextView jamble_words = openDialog.findViewById(R.id.jamble_words);
        Cursor jmp;
        jmp = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0' order by id limit 1");
        jmp.moveToFirst();
        if (jmp.getCount() != 0) {
            jamble_words.setVisibility(View.VISIBLE);
        }

        jamble_words.setOnClickListener(v -> {
            finish();
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Jamble_word_game.class);
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
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Missing_Words.class);
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
            sps.putString(Find_words_from_picture.this, "date", "0");
            Intent i = new Intent(Find_words_from_picture.this, Find_difference_between_pictures.class);
            startActivity(i);
        });
        if (!isFinishing()) openDialog.show();

        openDialog.setOnKeyListener((dialog, keyCode, event) -> {

            if (main_act.equals("")) {

                finish();
                //     openDialog_s.dismiss();
                Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
                startActivity(i);
            } else {
                sps.putString(Find_words_from_picture.this, "game_area", "on");
                finish();
            }
            openDialog.dismiss();
            sps.putString(Find_words_from_picture.this, "date", "0");


            return keyCode == KeyEvent.KEYCODE_BACK;
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
        String date = sps.getString(Find_words_from_picture.this, "date");
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
                    prize_data_update(Find_words_from_picture.this, 75);
                } else if (b_score != 0) {
                    prize_data_update(Find_words_from_picture.this, 25);
                }
            } else if (timeElapsed > 91300) {
                if (uans == answerlength) {
                    prize_data_update(Find_words_from_picture.this, 50);
                } else if (b_score != 0) {
                    prize_data_update(Find_words_from_picture.this, 25);
                }
            } else {
                if (uans != 0) {
                    prize_data_update(Find_words_from_picture.this, 25);
                }
            }
        } else {
            if (timeElapsed <= 91300) {
                if (uans == answerlength) {
                    prize_data_update(Find_words_from_picture.this, 100);
                } else if (b_score != 0) {
                    prize_data_update(Find_words_from_picture.this, 50);
                }
            } else if (timeElapsed > 91300) {
                if (uans == answerlength) {
                    prize_data_update(Find_words_from_picture.this, 75);
                } else if (uans != 0) {
                    prize_data_update(Find_words_from_picture.this, 50);
                }
            } else {
                if (uans != 0) {
                    prize_data_update(Find_words_from_picture.this, 25);
                }
            }
        }
        ////////////////Prize//////////////////
    }

    public void downloaddata_regular() {

        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_words_from_picture.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", (dialog, id) -> {
            //DownLoad Letters and Words

            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                download_datas();
            } else {

                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Find_words_from_picture.this);                           /* .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog12, which) -> {
                    // continue with delete

                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Find_words_from_picture.this, "goto_sett", 1);


                    dialog12.dismiss();
                }).setNegativeButton("பின்னர்", (dialog1, which) -> {
                    // do nothing
                    sps.putString(Find_words_from_picture.this, "game_area", "on");
                    String date = sps.getString(Find_words_from_picture.this, "date");
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
            sps.putString(Find_words_from_picture.this, "game_area", "on");
            finish();
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
        Download_data_server download_data_server = new Download_data_server(Find_words_from_picture.this, questionid_d, "" + gameid);
        download_data_server.execute();
    }

    public void missingimage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_words_from_picture.this);                            /*.setTitle("Delete entry")*/
        alertDialogBuilder.setMessage("படங்கள் இல்லை பதிவிறக்கம் செய்யவேண்டுமா? ").setPositiveButton("ஆம்", (dialog, which) -> {
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                String date = sps.getString(Find_words_from_picture.this, "date");
                if (date.equals("0")) {
                    Cursor cursor1 = newhelper5.getQry("SELECT * FROM newgames5 where gameid=16 order by questionid desc");
                    cursor1.moveToFirst();
                    String lastid = null;
                    if (cursor1.getCount() != 0) {
                        lastid = String.valueOf(cursor1.getInt(cursor1.getColumnIndexOrThrow("questionid")));
                    }
                    downpic(question_id, lastid);
                }
                dialog.dismiss();
                System.out.println("checkdismiss");
            } else {
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(Find_words_from_picture.this);                         /*   .setTitle("Delete entry")*/
                alertDialogBuilder1.setCancelable(false);
                alertDialogBuilder1.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", (dialog12, which12) -> {
                    // continue with delete
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 2);
                    dialog12.dismiss();
                }).setNegativeButton("பின்னர்", (dialog1, which1) -> {
                    // do nothing
                    sps.putString(Find_words_from_picture.this, "game_area", "on");
                    String date = sps.getString(Find_words_from_picture.this, "date");
                    if (date.equals("0")) {
                        backexitnet();
                    } else {
                        backexitnet();
                    }
                    dialog1.dismiss();
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 150) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Find_words_from_picture.this, "permission", 1);
                downloaddata_regular();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sps.putInt(Find_words_from_picture.this, "permission", 2);
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                        }
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Find_words_from_picture.this, "permission", 0);
                        if (main_act.equals("")) {
                            finish();
                            Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
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
                sps.putInt(Find_words_from_picture.this, "permission", 1);
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
                        sps.putInt(Find_words_from_picture.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Find_words_from_picture.this, "permission", 0);
                    }
                }
            }
        }
    }

    public void downpic(final String first, final String last) {

        head.setVisibility(View.INVISIBLE);
        Utils.mProgress(Find_words_from_picture.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {


                String result;

                InputStream is = null;
                StringBuilder sb;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("firstqid", first));
                nameValuePairs.add(new BasicNameValuePair("action", "get_gamedata"));
                nameValuePairs.add(new BasicNameValuePair("gameid", gameid));
                nameValuePairs.add(new BasicNameValuePair("lastqid", last));
                nameValuePairs.add(new BasicNameValuePair("android_id", sps.getString(Find_words_from_picture.this, "email")));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line;
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

        String str_url = img_down_url + sps.getString(Find_words_from_picture.this, "email") + "-filename.zip";

        //     new DownloadFileAsync().execute(str_url);
        downloadFileAsync = new DownloadFileAsync();
        downloadFileAsync.execute(str_url);
    }

    protected Dialog onCreateDialog(int id) {

        if (id == DIALOG_DOWNLOAD_PROGRESS) {
            mProgressDialog = new ProgressDialog(Find_words_from_picture.this);
            mProgressDialog.setMessage("படங்கள் பதிவிறக்கம் செய்யப்படுகிறது காத்திருக்கவும்.... ");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

            // playy();

            return mProgressDialog;
        }
        return null;
    }

    public void unpackZip(String ZIP_FILE_NAME) {

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
                FileOutputStream fout = new FileOutputStream(fullPath + filename);

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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Find_words_from_picture.this)) {
                download_datas();
            } else {

                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_words_from_picture.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்").setPositiveButton("அமைப்பு", (dialog, which) -> {
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    sps.putInt(Find_words_from_picture.this, "goto_sett", 1);
                    dialog.dismiss();
                }).setNegativeButton("பின்னர்", (dialog, which) -> {
                    String date = sps.getString(Find_words_from_picture.this, "date");
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
        final Dialog openDialog = new Dialog(Find_words_from_picture.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        p_coins.postDelayed(() -> p_coins.setVisibility(View.INVISIBLE), transAnimation.getDuration());


        ////


        new Thread(() -> {
            int es = e2 + 10;
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

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
            score.startAnimation(levels1);
        }, 2200);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(() -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 10;
                String aStringx = Integer.toString(spx);
                score.setText(aStringx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            }
        }, 700);

    }

    public void settingpermission() {
        if (sps.getInt(Find_words_from_picture.this, "permission") == 2) {
            AlertDialog alertDialog = new AlertDialog.Builder(Find_words_from_picture.this).create();
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
                sps.putString(Find_words_from_picture.this, "game_area", "on");
                String date = sps.getString(Find_words_from_picture.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                } else {
                    finish();
                    Intent i = new Intent(Find_words_from_picture.this, New_Main_Activity.class);
                    startActivity(i);
                }
                dialog.dismiss();
            });


            alertDialog.show();
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
                    Toast.makeText(Find_words_from_picture.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
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
                    return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                final int fileLength = connection.getContentLength();

                File SDCardRoot = getFilesDir();

                File fol = new File(SDCardRoot + "/Nithra/solliadi/");
                if (!fol.exists()) {
                    fol.mkdirs();
                }
                email = sps.getString(Find_words_from_picture.this, "email");
                File file = new File(SDCardRoot + "/Nithra/solliadi/", email + "-filename.zip");

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(file);

                byte[] data = new byte[4096];
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
                    if (output != null) output.close();
                    if (input != null) input.close();
                } catch (IOException ignored) {
                }

                if (connection != null) connection.disconnect();


            }
            return null;

        }


        @Override
        protected void onPostExecute(String unused) {
            mProgressDialog.dismiss();
            next();
            System.out.println("p12");
        }


        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }
    }

}
