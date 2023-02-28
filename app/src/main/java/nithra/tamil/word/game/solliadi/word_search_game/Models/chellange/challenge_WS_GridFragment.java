package nithra.tamil.word.game.solliadi.word_search_game.Models.chellange;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdLayout;


import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import nithra.tamil.word.game.solliadi.Clue_Game_Hard;
import nithra.tamil.word.game.solliadi.DataBaseHelper;
import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.New_Main_Gamelist;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper2;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper3;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Models.Direction;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Models.Word;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Word_search_main;
import nithra.tamil.word.game.solliadi.word_search_game.Models.extra.MyTableView;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.LikeButton;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnAnimationEndListener;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnLikeListener;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.fb_addload_score_screen;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native_Senthamil_Thedal_Native_Banner;

/*import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;*/


public class challenge_WS_GridFragment extends Fragment implements challenge_WS_GridView.OnWordSelectedListener, OnLikeListener, OnAnimationEndListener, RewardedVideoAdListener {

    public static RelativeLayout lay_wordsearch;

    private int mRows;
    private int mColumns;

    private static String[] mWordList = {"வ.ள.ம்", "மு.ய.ல்", "ந.ல.ம்", "நி.ற.ம்", "மு.த.லை", "ந.ம.து", "அ.ள.வு"};

    private final Direction[] mDirections = Direction.values();
    private boolean[][] mLock;
    private int[] mRandomIndexes;

    private String[][] mBoard;
    public static Set<Word> mSolution = new HashSet<Word>();
    public static Set<Word> mFoundWords = new HashSet<Word>();
    ArrayList<String> question_list = new ArrayList<>();


    challenge_WS_GridView challengeWsGridView;

    MyTableView myTableView;

    SharedPreference sp = new SharedPreference();

    public static int levelId, gridcount;

    LinearLayout option_view;

    RelativeLayout toptool, hintview, coin_lay, progress_lay;

    public static ArrayList<String> hints = new ArrayList<>();

    double diagonalInches;


    String table_name = "", dont_call = "", game_type = "", select_me = "", missing_content = "", game_finish = "";

    Animation bounce_anim = null;

    Context context;

    ImageView more_timer, saval_setting;
    View view = null;

    Vibrator vibrate;


    my_dialog myDialog_class = new my_dialog();
    String showview = "";
    ArrayList<String> showcase_shap;
    ArrayList<View> showcase_id = new ArrayList<View>();
    ArrayList<String> showcase_content = new ArrayList<String>();


    TextView counton_timer, counts, screatch_txt, question_txt, coin_txt, option1_txt, option2_txt, title_txt;

    ArrayList<String> option_array = new ArrayList<>();

    ProgressBar ProgressBar;

    public CountDownTimer countDownTimer;

    private long totalTimeCountInMilliseconds, timeBlinkInMilliseconds; // total count down time in

    private boolean blink, timer_stop = false;

    int my_progress = 0, time_value = 2, coin_point = 0, leader_point = 0, coin_point1 = 0, goback = 0, total_word = 0, total_finded = 0;
    ;

    Dialog my_my_dialog = null;

    List<String> elephantList = new ArrayList<>();

    SQLiteDatabase mydb, Inner_mydb, exdb;
    Cursor cursor = null, coin_cursor;

    ImageView icon_ad_img;
    LinearLayout n_icon_ad;


    FrameLayout Baner_frame;
    LinearLayout normal_baner;
    String Dailytest_ok = "";
    String share_content = "";
    String btn_str = "";
    Dialog Winning_dialog;
    FirebaseAnalytics mFirebaseAnalytics;
    static int rvo = 0;
    // Facebook variable starts
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    public void onError(Ad ad, AdError adError) {

    }

    @Override
    public void onAdLoaded(Ad ad) {

    }

    @Override
    public void onAdClicked(Ad ad) {

    }

    @Override
    public void onLoggingImpression(Ad ad) {

    }

    @Override
    public void onRewardedVideoClosed() {

    }

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sp.getString(context, "ws_challenge_showcase_intro").equals("")) {
                    showcase_dismiss();
                } else {
                    startTimer();
                    sp.putString(getActivity(), "ws_challenge_intro", "no");
                }

            }
        }, 800);
    }

    /* private UiLifecycleHelper uiHelper;
     private Session.StatusCallback callback = new Session.StatusCallback() {
         @Override
         public void call(Session session, SessionState state,
                          Exception exception) {
             // onSessionStateChange(session, state, exception);
         }
     };
     private FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
         @Override
         public void onError(FacebookDialog.PendingCall pendingCall,
                             Exception error, Bundle data) {
             Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
         }

         @Override
         public void onComplete(FacebookDialog.PendingCall pendingCall,
                                Bundle data) {
             Log.d("HelloFacebook", "Success!");
         }
     };*/
    // facebook variable ends
    int extra_coin_s = 0;

    public static String getCountryName(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                return addresses.get(0).getCountryName();
            }
        } catch (IOException ioe) {
        }
        return null;
    }

    MediaPlayer beep_mp;

    public void startTimer() {

        if (Dailytest_ok.equals("")) {

            dont_call = "";

            counton_timer.setTextColor(Color.parseColor("#000000"));
            countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
                // 500 means, onTick function will be called at every 500
                // milliseconds

                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    long seconds = leftTimeInMilliseconds / 1000;

                    totalTimeCountInMilliseconds = leftTimeInMilliseconds;

                    if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {

                        if (counton_timer != null) {
                            //  counton_timer.setTextAppearance(getActivity(), R.style.blinkText);
                        }

                        // change the style of the textview .. giving a red
                        // alert style

                        if (blink) {
                            counton_timer.setVisibility(View.VISIBLE);
                            // if blink is true, textview will be visible
                        } else {
                            // counton_timer.setVisibility(View.INVISIBLE);
                        }

                        blink = !blink; // toggle the value of blink

                        //  beep_mp = MediaPlayer.create(getActivity(), R.raw.clock);

                       /* beep_mp.setWakeMode(getActivity(), PowerManager.PARTIAL_WAKE_LOCK);
                        beep_mp.start();

                        beep_mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {

                                beep_mp.release();
                            }
                        });*/

                    }

                    counton_timer.setText(String.format("%02d", seconds / 60)
                            + ":" + String.format("%02d", seconds % 60));
                    // format the textview to show the easily readable format

                    int progress = (int) (leftTimeInMilliseconds / 1000);

                    float s = (float) progress / my_progress * 100;
                    progress = (int) s;

                    ProgressBar.setProgress(progress);

                }

                @Override
                public void onFinish() {
                    // this function will be called when the timecount is finished

                    counton_timer.setText("நேரம் முடிவடைந்தது !");
                    counton_timer.setVisibility(View.VISIBLE);

                    if (!game_finish.equals("")) {
                        counton_timer.setText("வெற்றி பெற்றுவிட்டீர் !");
                    } else {
                        game_finish = "winning_report";
                        winning_report(context, "time_up");
                    }


                }

            }.start();
        }

    }


    public void load_data() {

        total_word = 0;
        total_finded = 0;

        try {
            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
                leader_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("l_points"));
                coin_txt.setText("" + coin_point);
//                coin_txt.startAnimation(bounce_anim);
            }
        } finally {
            if (coin_cursor != null)
                coin_cursor.close();
        }

        try {

            cursor = mydb.rawQuery("select * from '" + table_name + "' where is_finish='" + 0 + "' ORDER BY RANDOM() LIMIT 20", null);

            System.out.println("88888888888888888888 cursor.getCount() : select * from '" + table_name + "' where is_finish='" + 0 + "' ORDER BY RANDOM() LIMIT 20");
            System.out.println("88888888888888888888 cursor.getCount() : " + cursor.getCount());

            if (cursor.getCount() != 0) {

                elephantList.clear();

                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);

                    String value = cursor.getString(cursor.getColumnIndexOrThrow("ch_words"));
                    elephantList.add(value);

                }

                System.out.println("88888888888888888888 elephantList : " + elephantList);

                mWordList = new String[elephantList.size()];

                for (int i = 0; i < elephantList.size(); i++) {
                    mWordList[i] = elephantList.get(i);
                }
            }

        } finally {
            if (cursor != null)
                cursor.close();

        }

        resetBoard();
        generateBoard();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initBoard();
        }


        if (table_name.equals("challenge")) {
            time_value = 2;
        } else if (table_name.equals("missing_word")) {
            time_value = 5;
        } else {
            time_value = 3;
        }

        totalTimeCountInMilliseconds = 60 * time_value * 1000;

        my_progress = (int) (totalTimeCountInMilliseconds / 1000);

        timeBlinkInMilliseconds = 30 * 1000;

        if (!sp.getString(getActivity(), "ws_challenge_intro").equals("")) {
            startTimer();
        }
    }

    public void no_tool() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.getString(getActivity(), "ws_challenge_intro").equals("")) {
                    System.out.println("no_tool ------------no_tool -------");
                    getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    startTimer();

                    //  Native_icon_ad.load_add(getActivity(), normal_baner);
                    //myDialog_class.WST_Native_IconAd(getActivity(), icon_ad_img, n_icon_ad);
                    //  myDialog_class.WST_Native_BannerAd(getActivity(), Baner_frame);

                } else {
                    no_tool();
                }


                System.out.println("no_tool ------------" + sp.getString(getActivity(), "ws_challenge_intro"));

            }
        }, 1000);
    }


    public void no_setting() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getString(getActivity(), "no_setting").equals("")) {
                    if (sp.getInt(getContext(), "Grid") == 1) {

                        myTableView.setVisibility(View.VISIBLE);
                    } else {
                        myTableView.setVisibility(View.GONE);
                    }
                    startTimer();
                } else {
                    no_setting();
                }
            }
        }, 1000);
    }

    int fb_reward = 0;
    com.facebook.ads.RewardedVideoAd rewardedVideoAd;
    int reward_status = 0;
    //*********************reward videos process 1***********************

    String reward_video = "";


    //private final String AD_UNIT_ID = getString(R.string.rewarded);
    private static final String APP_ID = "ca-app-pub-4267540560263635~9441478701";
    private static final long COUNTER_TIME = 10;
    private static final int GAME_OVER_REWARD = 1;

    static private int mCoinCount = 20, coinearn = 1;
    private boolean mGameOver;
    private boolean mGamePaused;
    private RewardedVideoAd mRewardedVideoAd;
    private long mTimeRemaining;
    //reward videos process 1***********************

    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    SQLiteDatabase dbs, dbn, dbn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.challenge_view, null);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

      /*  if (sp.getString(getActivity(), "ws_challenge_intro").equals("")) {
        } else {
            no_tool();
        }*/

        //*********************reward videos process 2***********************

//reward videos process 2***********************


        System.out.println("-----hh Dailytest_ok : " + Dailytest_ok);

        progress_lay = (RelativeLayout) view.findViewById(R.id.progress_lay);

        if (Dailytest_ok.equals("")) {
            table_name = sp.getString(getActivity(), "table_name");
            game_type = sp.getString(getActivity(), "game_type");
        } else {
            progress_lay.setVisibility(View.GONE);

            sp.putString(getActivity(), "show_challenge", "");
            sp.putString(getActivity(), "option_show", "");

            int min = 0;
            int max = 3;
            Random r = new Random();
            int D = r.nextInt(max - min + 1) + min;

            System.out.println("-----hh D : " + D);

            if (D == 0) {
                sp.putString(getActivity(), "saval_type", "வார்த்தை தேடல்");
                sp.putString(getActivity(), "game_type", "challenge_word");
                sp.putString(getActivity(), "table_name", "challenge");
            }
            if (D == 1) {
                sp.putString(getActivity(), "option_show", "show");
                sp.putString(getActivity(), "game_type", "oposit_word");
                sp.putString(getActivity(), "table_name", "yethirsoll");
                sp.putString(getActivity(), "saval_type", "எதிர்சொல்");
            }
            if (D == 2) {
                sp.putString(getActivity(), "option_show", "show");
                sp.putString(getActivity(), "game_type", "Q_A_word");
                sp.putString(getActivity(), "table_name", "Q_A");
                sp.putString(getActivity(), "saval_type", "கேள்வி பதில்கள்");
            }
            if (D == 3) {
                sp.putString(getActivity(), "game_type", "missing_word");
                sp.putString(getActivity(), "table_name", "missing_word");
                sp.putString(getActivity(), "saval_type", "விடுபட்ட வார்த்தை");
            }

            table_name = sp.getString(getActivity(), "table_name");
            game_type = sp.getString(getActivity(), "game_type");
        }


        mydb = getActivity().openOrCreateDatabase("WS_tamil.db", getActivity().MODE_PRIVATE, null);
        Inner_mydb = getActivity().openOrCreateDatabase("Inner_DB", 0, null);

        exdb = getActivity().openOrCreateDatabase("Solli_Adi", getActivity().MODE_PRIVATE, null);

        myDbHelper = new DataBaseHelper(getActivity());
        newhelper = new Newgame_DataBaseHelper(getActivity());
        newhelper2 = new Newgame_DataBaseHelper2(getActivity());
        newhelper3 = new Newgame_DataBaseHelper3(getActivity());

     /*   exdb = myDbHelper.getReadableDatabase();
        dbs = newhelper.getReadableDatabase();
        dbn = newhelper2.getReadableDatabase();
        dbn2 = newhelper3.getReadableDatabase();*/

        context = getActivity();

        if (myDialog_class.isNetworkAvailable(getActivity())) {
            if (sp.getInt(getActivity(), "purchase_ads") == 1) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            } else {
                //fb_addload_score_screen(getActivity());

                /**/
            }
            reward();
        }


        coin_lay = (RelativeLayout) view.findViewById(R.id.coin_lay);
        coin_txt = (TextView) view.findViewById(R.id.coin_txt);
        counts = (TextView) view.findViewById(R.id.counts);
        counton_timer = (TextView) view.findViewById(R.id.counton_timer);
        more_timer = (ImageView) view.findViewById(R.id.more_timer);
        saval_setting = (ImageView) view.findViewById(R.id.saval_setting);
        screatch_txt = (TextView) view.findViewById(R.id.screatch_txt);
        question_txt = (TextView) view.findViewById(R.id.question_txt);

        option1_txt = (TextView) view.findViewById(R.id.option1_txt);
        option2_txt = (TextView) view.findViewById(R.id.option2_txt);
        title_txt = (TextView) view.findViewById(R.id.title_txt);
        title_txt.setText(sp.getString(getActivity(), "saval_type"));


        normal_baner = (LinearLayout) view.findViewById(R.id.normal_baner);
        Baner_frame = (FrameLayout) view.findViewById(R.id.Baner_frame);
        icon_ad_img = (ImageView) view.findViewById(R.id.icon_ad_img);
        n_icon_ad = (LinearLayout) view.findViewById(R.id.n_icon_ad);
        n_icon_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  myDialog_class.WST_Native_IconAd_show();
            }
        });

        vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);


      /*  option1_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");


                Word mWords = null;

                for (Word word : mSolution) {
                    System.out.println("-------- word  " + word.getWord());

                    if (word.getWord().replace(".", "").equals(question_list.get(mFoundWords.size()).replace(".", ""))) {
                        mWords = word;
                        System.out.println("-------- word mWords " + mWords);
                        break;
                    }

                }


                coin_point1 = Integer.parseInt(coin_txt.getText().toString());

                if (!mFoundWords.contains(mWords)) {

                    if (hints.contains(mWords.getWord())) {
                        challengeWsGridView.showHint(mWords);
                    } else {

                        if (select_me.equals("")) {
                            if (coin_point1 >= 20) {
                                option_hint_dia(mWords, "all");
                            } else {
                                more_coin();
                            }
                        } else {
                            if (coin_point1 >= 10) {

                                select_me = "opt";
                                option_hint_dia(mWords, "hint");

                            } else {
                                more_coin();
                            }
                        }
                    }
                }


            }
        });

        option2_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                Word mWords = null;

                for (Word word : mSolution) {
                    System.out.println("-------- word  " + word.getWord());

                    if (word.getWord().replace(".", "").equals(question_list.get(mFoundWords.size()).replace(".", ""))) {
                        mWords = word;
                        System.out.println("-------- word mWords " + mWords);
                        break;
                    }

                }


                coin_point1 = Integer.parseInt(coin_txt.getText().toString());

                if (!mFoundWords.contains(mWords)) {

                    if (hints.contains(mWords.getWord())) {
                        challengeWsGridView.showHint(mWords);
                    } else {

                        if (select_me.equals("")) {
                            if (coin_point1 >= 20) {
                                option_hint_dia(mWords, "all");
                            } else {
                                more_coin();
                            }
                        } else {
                            if (coin_point1 >= 10) {

                                select_me = "opt";
                                option_hint_dia(mWords, "hint");

                            } else {
                                more_coin();
                            }
                        }
                    }
                }
            }
        });*/


        ProgressBar = (ProgressBar) view.findViewById(R.id.ProgressBar);

        //bounce_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_anim);

        option_view = (LinearLayout) view.findViewById(R.id.option_view);

        question_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                Word mWords = null;

                for (Word word : mSolution) {
                    System.out.println("-------- word  " + word.getWord());

                    if (word.getWord().replace(".", "").equals(question_list.get(mFoundWords.size()).replace(".", ""))) {
                        mWords = word;
                        System.out.println("-------- word mWords " + mWords);
                        break;
                    }

                }


                coin_point1 = Integer.parseInt(coin_txt.getText().toString());
                if (!mFoundWords.contains(mWords)) {
                    if (hints.contains(mWords.getWord())) {
                        challengeWsGridView.showHint(mWords);
                    } else {
                        if (coin_point1 >= 20) {

                            if (!game_type.equals("challenge_word")) {

                                if (hints.contains(mWords.getWord())) {
                                    challengeWsGridView.showHint(mWords);
                                } else {
                                    if (select_me.equals("")) {
                                        option_hint_dia(mWords, "all");
                                    } else {
                                        select_me = "opt";
                                        option_hint_dia(mWords, "hint");
                                    }
                                }

                            } else if (sp.getString(getActivity(), "option_show").equals("")) {
                                hint_dia(mWords);
                            } /*else {
                                if (select_me.equals("")) {
                                    option_hint_dia(mWords, "all");
                                }
                            }*/
                        } else {
                            more_coin();
                        }
                    }

                }

              /*  if (sp.getString(getActivity(), "option_show").equals("")) {

                    if (!mFoundWords.contains(mWords)) {
                        if (hints.contains(mWords.getWord())) {
                            challengeWsGridView.showHint(mWords);
                        } else {
                            hint_dia(mWords);
                        }
                    }
                    option_hint_dia(mWords);
                } else {
                    if (!mFoundWords.contains(mWords)) {
                        if (hints.contains(mWords.getWord())) {
                            challengeWsGridView.showHint(mWords);
                        } else {
                            hint_dia(mWords);
                        }
                    }
                }*/


            }
        });

        coin_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                more_coin();
            }
        });


        more_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                more_time();
            }
        });

        saval_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String snd = sp.getString(getActivity(), "snd");
                if (snd.equals("off")) {
                    sp.putString(getActivity(), "snd", "on");
                    saval_setting.setBackgroundResource(R.drawable.sound_on);
                } else if (snd.equals("on")) {
                    sp.putString(getActivity(), "snd", "off");
                    saval_setting.setBackgroundResource(R.drawable.sound_off);
                }


              /*  myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                if (Dailytest_ok.equals("")) {
                    countDownTimer.cancel();
                }

                sp.putString(getActivity(), "no_setting", "setting");
                no_setting();*/
                // myDialog_class.setting(getActivity()).show();
            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        float widthDpi = metrics.xdpi;
        float heightDpi = metrics.ydpi;

        float widthInches = widthPixels / widthDpi;
        float heightInches = heightPixels / heightDpi;

        float scaleFactor = metrics.density;
        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;

        float smallestWidth = Math.min(widthDp, heightDp);

        diagonalInches = Math.sqrt((widthInches * widthInches) + (heightInches * heightInches));


        if (sp.getInt(getContext(), "Notimer") == 0) {

            sp.putInt(getContext(), "Start", 1);
            sp.putInt(getContext(), "Notimer", 1);
        } else {

            sp.putInt(getContext(), "Start", 0);
        }


        //return LayoutInflater.from(getActivity()).inflate(R.layout.wordsearch_view, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity();

        lay_wordsearch = (RelativeLayout) view.findViewById(R.id.lay_wordsearch);

        challengeWsGridView = (challenge_WS_GridView) view.findViewById(R.id.grd_challenge);
        myTableView = (MyTableView) view.findViewById(R.id.mytab);


        toptool = (RelativeLayout) view.findViewById(R.id.toptool);
        hintview = (RelativeLayout) view.findViewById(R.id.hintview);


        if (sp.getInt(getContext(), "Grid") == 1) {

            myTableView.setVisibility(View.VISIBLE);
        }

        levelId = sp.getInt(getContext(), "LEVEL");
        gridcount = sp.getInt(getContext(), "GRID");


 /*       if (sp.getString(getActivity(), "saval_showcase").equals("")) {
            //showcase_view(mp_img);

            showview = "saval_showcase";

            showcase_shap = new ArrayList<>(Arrays.asList("rect", "circle", "circle", "rect"));

            showcase_content.add("செயலியை பகிர்ந்து கூடுதல் நாணயம் பெறலாம் ");
            showcase_content.add("நேரத்தை அதிகரிக்க இந்த பொத்தானை சொடுக்கவும்");
            showcase_content.add("விளையாட்டின் செயல்களை உங்கள் கட்டுப்பாட்டில் அமைக்க ");
            showcase_content.add("குறிப்பு பார்க்க சொல்லை சொடுக்கவும்");

            showcase_id.add(coin_lay);
            showcase_id.add(more_timer);
            showcase_id.add(saval_setting);
            showcase_id.add(question_txt);

            myDialog_class.showcase_view(getActivity(), showview, showcase_id, showcase_shap, showcase_content);

        }*/

        String snd = sp.getString(getActivity(), "snd");

        if (snd.equals("off")) {
            saval_setting.setBackgroundResource(R.drawable.sound_off);
        } else {
            saval_setting.setBackgroundResource(R.drawable.sound_on);
        }


        if (sp.getString(getActivity(), "ws_challenge_intro").equals("")) {
            //Toast.makeText(context, "ws_challenge_intro", Toast.LENGTH_SHORT).show();

            showcase_dismiss();

            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity(), "sequence example ws_challenge_intro");

            sequence.setConfig(config);

            sequence.addSequenceItem(coin_lay, "செயலியை பகிர்ந்து கூடுதல் நாணயம் பெறலாம்.", "அடுத்து");

            sequence.addSequenceItem(more_timer, "நேரத்தை அதிகரிக்க இந்த பொத்தானை சொடுக்கவும்.", "அடுத்து");
            // sequence.addSequenceItem(saval_setting, "விளையாட்டின் செயல்களை உங்கள் கட்டுப்பாட்டில் அமைக்க.", "அடுத்து");

            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(getActivity())
                    .setTarget(question_txt)
                    .setDismissText("சரி")
                    .setContentText("குறிப்பு பார்க்க சொல்லை சொடுக்கவும்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {
                            if (position == 2) {
                                // startTimer();
                                sp.putString(getActivity(), "ws_challenge_intro", "no");
                                sp.putString(getActivity(), "ws_challenge_showcase_intro", "yes");
                            }
                        }
                    });


            sequence.start();

        }
        //uiHelper = new UiLifecycleHelper(getActivity(), callback);
    }

    @Override
    public void onStop() {
        super.onStop();

        myDialog_class.media_player(getActivity(), R.raw.scretch_fail, "stop");
        if (!timer_stop) {
            if (!sp.getString(getActivity(), "ws_challenge_intro").equals("")) {
                if (Dailytest_ok.equals("")) {
                    countDownTimer.cancel();
                }
            }

            if (dont_call.equals("")) {
                timer_stop = true;
            }

        }

    }

    //*********************reward videos process 3***********************


    private void addCoins(int coins) {
        mCoinCount = coins;
        sp.putInt(getActivity(), "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }


    //reward videos***********************//

    @Override
    public void onPause() {
        super.onPause();
        reward_video = "";

        //******reward video pocess :4

        //reward video pocess :4 ************//
    }

    @Override
    public void onResume() {
        super.onResume();
        //******reward video pocess :4
        //loadRewardedVideoAd();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        mFirebaseAnalytics.setCurrentScreen(getActivity(), "Word Search Challenge", null);

        if (!mGameOver && mGamePaused) {

        }

        //reward video pocess :4 ************//

        if (!sp.getString(getActivity(), "saval_showcase").equals("")) {
            //  Native_icon_ad.load_add(getActivity(), normal_baner);
            //myDialog_class.WST_Native_IconAd(getActivity(), icon_ad_img, n_icon_ad);
            //   myDialog_class.WST_Native_BannerAd(getActivity(), Baner_frame);

        }
        if (timer_stop) {
            if (game_finish.equals("")) {
                startTimer();
                timer_stop = false;
            }

        }

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) view.findViewById(R.id.native_banner_ad_container_1);
        System.out.println("=====--check purchase_ads : " + sp.getInt(getActivity(), "purchase_ads"));
        if (sp.getInt(getActivity(), "purchase_ads") == 1) {
            normal_baner.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            if (Utils.isNetworkAvailable(getActivity())) {
                native_banner_ad_container.setVisibility(View.GONE);
                fb_native_Senthamil_Thedal_Native_Banner(getActivity(), native_banner_ad_container);
                //fb_native(getActivity(),native_banner_ad_container);
               /* if (sp.getInt(getActivity(), "native_banner_ads") == 1) {
                    New_Main_Gamelist.inflateAd(getActivity(), native_banner_ad_container);
                } else {
                    fb_native(getActivity(), native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onWordSelected(List<Integer> positions) {

        int firstPos = positions.get(0);
        int lastPos = positions.get(positions.size() - 1);


        StringBuilder forwardWord = new StringBuilder();
        StringBuilder reverseWord = new StringBuilder();

        for (Integer position : positions) {
            int row = position / mColumns;
            int col = position % mColumns;

            String c = mBoard[row][col];

            forwardWord.append(c);
            reverseWord.insert(0, c);
        }

        if (positions.size() > 0) {
            // screatch_txt.setVisibility(View.VISIBLE);
            StringBuilder myscreatch = new StringBuilder();
            for (int i = 0; i < positions.size(); i++) {
                int row = positions.get(i) / mColumns;
                int col = positions.get(i) % mColumns;
                String c = mBoard[row][col];
                myscreatch.append(c);
            }

            // screatch_txt.setText(myscreatch.toString().toUpperCase());
        }

        if (challenge_WS_GridView.clearSelection == 1) {


            challenge_WS_GridView.clearSelection = 0;
            //screatch_txt.setVisibility(View.INVISIBLE);

            for (Word word : mSolution) {

                int wordStart = (word.getRow() * mColumns) + word.getCol();

                if (question_list.get(mFoundWords.size()).replace(".", "").equals(forwardWord.toString().toLowerCase()) ||
                        question_list.get(mFoundWords.size()).replace(".", "").equals(reverseWord.toString().toLowerCase())) {


                    if (wordStart != firstPos && wordStart != lastPos) {
                        if (word.getWord().toLowerCase().equals(forwardWord.toString().toLowerCase())
                                || word.getWord().toLowerCase().equals(reverseWord.toString().toLowerCase())) {

                            Toast.makeText(getContext(), "இந்த வார்த்தை வேறு இடத்தில் உள்ளது ", Toast.LENGTH_SHORT).show();
                        }
                        continue;
                    }

                    Word found = (word.getWord().replace(".", "").equals(forwardWord.toString())) ? word : null;


                    if (found == null) {
                        found = (word.getWord().replace(".", "").equals(reverseWord.toString())) ? word : null;
                    }

                    System.out.println("=========================== word " + word.getWord());
                    System.out.println("=========================== forwardWord.toString() " + forwardWord.toString());
                    System.out.println("=========================== reverseWord.toString() " + reverseWord.toString());
                    System.out.println("=========================== found " + found);

                    if (found != null) {

                    /*screatch_txt.setVisibility(View.VISIBLE);
                    screatch_txt.setText(found.getWord().replace(".", ""));*/

                        myDialog_class.media_player(getActivity(), R.raw.scretch_done, "normal");

                      /*  if (sp.getInt(getActivity(), "Vibrate") == 1) {
                            vibrate.vibrate(100);
                        }*/

                        mFoundWords.add(found);
                        challengeWsGridView.clearHint();
                        challengeWsGridView.wordFound(found);

                        counts.setText("" + mFoundWords.size() + "/" + mSolution.size());
                        coin_point += 5;
                        leader_point += 5;

                        myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "',l_points='" + leader_point + "'");

                        try {
                            cursor = mydb.rawQuery("select * from '" + table_name + "' where ch_words='" + found.getWord() + "'", null);
                            if (cursor.getCount() != 0) {
                                cursor.moveToFirst();

                                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                                if (Dailytest_ok.equals("")) {
                                    mydb.execSQL("UPDATE '" + table_name + "' SET is_finish='" + 1 + "' where id='" + id + "'");
                                }


                                cursor = Inner_mydb.rawQuery("select * from challenge where id='" + id + "'", null);
                                if (cursor.getCount() == 0) {
                                    ContentValues contentValues12 = new ContentValues();
                                    contentValues12.put("id", id);
                                    contentValues12.put(game_type, 1);
                                    Inner_mydb.insert("challenge", null, contentValues12);
                                } else {
                                    if (Dailytest_ok.equals("")) {
                                        Inner_mydb.execSQL("UPDATE challenge SET '" + game_type + "'='" + 1 + "' where id='" + id + "'");
                                    }

                                }
                            }

                        } finally {
                            if (cursor != null)
                                cursor.close();
                        }

                        if (mSolution.size() > mFoundWords.size()) {
                            option_load();
                        }


                        if (Dailytest_ok.equals("")) {
                            countDownTimer.cancel();
                        }
                        if (table_name.equals("challenge")) {
                            totalTimeCountInMilliseconds += (5 * 1000);
                            get_time(5);

                        } else if (table_name.equals("missing_word")) {
                            totalTimeCountInMilliseconds += (10 * 1000);
                            get_time(10);
                        } else {
                            totalTimeCountInMilliseconds += (7 * 1000);
                            get_time(7);
                        }
                        startTimer();

                        total_finded += 1;

                        break;
                    }
                } /*else {
                    myDialog_class.media_player(getActivity(), R.raw.scretch_fail, "normal");
                }*/
            }


        }

        //Flag to avoid multi drag for last word

        if (mFoundWords.size() == mSolution.size()) {

            try {

                cursor = mydb.rawQuery("select * from '" + table_name + "' where is_finish='" + 0 + "' ORDER BY RANDOM() LIMIT 20", null);

                System.out.println("88888888888888888888 cursor.getCount() : select * from '" + table_name + "' where is_finish='" + 0 + "' ORDER BY RANDOM() LIMIT 20");
                System.out.println("88888888888888888888 cursor.getCount() : " + cursor.getCount());

                if (cursor.getCount() != 0) {

                    elephantList.clear();

                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToPosition(i);

                        String value = cursor.getString(cursor.getColumnIndexOrThrow("ch_words"));
                        elephantList.add(value);

                    }

                    System.out.println("88888888888888888888 elephantList : " + elephantList);

                    mWordList = new String[elephantList.size()];

                    for (int i = 0; i < elephantList.size(); i++) {
                        mWordList[i] = elephantList.get(i);
                    }
                }

            } finally {
                if (cursor != null)
                    cursor.close();

            }

            resetBoard();
            generateBoard();
            initBoard();


        }
    }

    public void dia_dismiss(Dialog dialog) {

        System.out.println("dialog : " + dialog);
        dialog.dismiss();
    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {


        switch (likeButton.getId()) {

            case R.id.like_retry_game: {
                //dia_dismiss(my_my_dialog);

            }
            break;

            case R.id.like_continue_img: {
                dia_dismiss(my_my_dialog);

            }
            break;
        }

        likeButton.setLiked(false);

    }

    @Override
    public void liked(LikeButton likeButton) {

        String share_content = "தமிழ் வார்த்தை விளையாட்டை விளையாட இது உங்களுக்கான சொற்களம்.\n" +
                "நீங்களும் இக்களத்தில் ஒருவராக இருக்க \n" +
                "நித்ராவின் செம்மொழி  வேட்டை செயலியை  கீழே உள்ள லிங்கை கிளிக் செய்து தரவிறக்கம் செய்யவும்.\n" + "https://goo.gl/5FhjMM";


        String root = Environment.getExternalStorageDirectory().toString();
        File mydir = new File(root + "/.nithra/TWS");
        mydir.mkdirs();
        String fname = "Image-appshare.jpg";
        final File file = new File(mydir, fname);


        switch (likeButton.getId()) {

            case R.id.like_retry_game: {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                goback = 1;

                Intent intent = new Intent(getActivity(), Word_search_main.class);
                getActivity().finish();
                startActivity(intent);
            }
            break;

            case R.id.like_continue_img: {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                goback = 1;
                load_data();
            }
            break;

        }
    }

    @Override
    public void unLiked(LikeButton likeButton) {

    }


    public void winning_report(final Context context, String get_value) {
        //  myDialog_class.media_player(getActivity(), R.raw.winning, "normal");
        Winning_dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        Winning_dialog.setContentView(R.layout.dia_winning_report);
        //    Winning_dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
        Winning_dialog.setCancelable(false);
        if (!Winning_dialog.isShowing()) {
            Winning_dialog.show();
        }
        my_my_dialog = Winning_dialog;

   /*    if (mFoundWords.size() == mSolution.size()){
           price_update();
       }
*/
        TextView video_earn = (TextView) Winning_dialog.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sp.getInt(getActivity(), "reward_coin_txt") + "+நாணயங்கள் பெற");

        LinearLayout vid_earn = (LinearLayout) Winning_dialog.findViewById(R.id.vid_earn);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        vid_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvo = 2;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {

                        reward_progressBar.dismiss();
                        rewardedVideoAd.show();


                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                        reward();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);


                    }
                } else {

                    Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

            }
        });

        LinearLayout fl_adplaceholder = (LinearLayout) Winning_dialog.findViewById(R.id.fl_adplaceholder);
        if (sp.getInt(getActivity(), "purchase_ads") == 1) {
            fl_adplaceholder.setVisibility(View.GONE);
        } else {
            //  load_addinstall(getActivity(), fl_adplaceholder);
            //  New_Main_Activity.load_addFromMain_multiplayer(getActivity(), fl_adplaceholder);
           /* if (Utils.isNetworkAvailable(getActivity())){
                //New_Main_Activity.load_add_fb_rect_score_screen(getActivity(), fl_adplaceholder);
            }else {
                fl_adplaceholder.setVisibility(View.GONE);
            }*/
            if (Utils.isNetworkAvailable(context)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(context, fl_adplaceholder);
            } else {
                fl_adplaceholder.setVisibility(View.GONE);
            }
        }

        //  price_update();

        goback = 0;
        my_my_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (goback == 0) {
                    Intent intent = new Intent(getActivity(), Word_search_main.class);
                    getActivity().finish();
                    startActivity(intent);
                }
            }
        });

        // int leader_point = 0;
        try {
            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                leader_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("l_points"));
            }
        } finally {
            if (coin_cursor != null)
                coin_cursor.close();
        }


        TextView best_score = (TextView) Winning_dialog.findViewById(R.id.best_score);
        TextView current_score = (TextView) Winning_dialog.findViewById(R.id.current_score);
        TextView win_coin = (TextView) Winning_dialog.findViewById(R.id.win_coin);
        TextView credit_coin = (TextView) Winning_dialog.findViewById(R.id.credit_coin);
        TextView time_txt = (TextView) Winning_dialog.findViewById(R.id.time_txt);
        TextView retry_txt = (TextView) Winning_dialog.findViewById(R.id.retry_txt);
        Chronometer best_chronometer = (Chronometer) Winning_dialog.findViewById(R.id.best_chronometer);

        TextView best_valthu_txt = (TextView) Winning_dialog.findViewById(R.id.best_valthu_txt);
        ArrayList<String> best_word = new ArrayList<>(Arrays.asList("மிக சிறப்பு", "வாழ்த்துக்கள்", "அருமை", "அற்புதம்", "தமிழ் வாழ்க", "தமிழன்"));
        String bestword = best_word.get(new Random().nextInt(best_word.size()));
        best_valthu_txt.setText("" + bestword);

        RelativeLayout retry_game = (RelativeLayout) Winning_dialog.findViewById(R.id.retry_game);
        RelativeLayout next_game = (RelativeLayout) Winning_dialog.findViewById(R.id.next_game);

        LikeButton like_retry_game = (LikeButton) Winning_dialog.findViewById(R.id.like_retry_game);
        LikeButton like_continue_img = (LikeButton) Winning_dialog.findViewById(R.id.like_continue_img);

        /*like_retry_game.setOnLikeListener(this);
        like_retry_game.setOnAnimationEndListener(this);
        like_continue_img.setOnLikeListener(this);
        like_continue_img.setOnAnimationEndListener(this);*/


        retry_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                goback = 1;

                Intent intent = new Intent(getActivity(), Word_search_main.class);
                getActivity().finish();
                startActivity(intent);

            }
        });

        next_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                goback = 1;

                try {
                    coin_cursor = myDbHelper.getQry("select * from score");
                    if (coin_cursor.getCount() != 0) {
                        coin_cursor.moveToFirst();
                        coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
                        leader_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("l_points"));
                        coin_txt.setText("" + coin_point);
//                coin_txt.startAnimation(bounce_anim);
                    }
                } finally {
                    if (coin_cursor != null)
                        coin_cursor.close();
                }

                load_data();
                Winning_dialog.dismiss();

            }
        });


        int best_score_value = total_finded * 5;
        int bonus_value = 0;

        if (hints.size() == 0) {
            leader_point += 10;
            bonus_value = total_finded;
            coin_point = coin_point + bonus_value;

            myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "',l_points='" + leader_point + "'");
            credit_coin.setText("" + bonus_value);


        }

        if (sp.getInt(getActivity(), "bestscore" + game_type) <= (best_score_value + bonus_value)) {
            sp.putInt(getActivity(), "bestscore" + game_type, (best_score_value + bonus_value));
        }
        time_txt.setTextSize(12);
        time_txt.setText("வார்த்தைகள் ");
        best_chronometer.setText("" + total_finded + "/" + total_word);
        retry_txt.setText("வெளியேற");

        best_score.setText("" + (sp.getInt(getActivity(), "bestscore" + game_type)));
        win_coin.setText("" + (best_score_value));
        current_score.setText("" + (best_score_value + bonus_value));

        if (get_value.equals("exit")) {
            next_game.setVisibility(View.GONE);
            like_continue_img.setVisibility(View.GONE);
        }


        try {
            cursor = mydb.rawQuery("select * from '" + table_name + "' where is_finish='" + 0 + "' ORDER BY RANDOM() LIMIT 20", null);
            if (cursor.getCount() == 0) {
                next_game.setVisibility(View.GONE);
                like_continue_img.setVisibility(View.GONE);
            }

        } finally {
            if (cursor != null)
                cursor.close();
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onPuzzleComplete() {
        resetBoard();
        generateBoard();
        initBoard();

        if (sp.getInt(context, "Grid") == 1) {

            myTableView.setVisibility(View.VISIBLE);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                challengeWsGridView.setVisibility(View.VISIBLE);
                AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500);
                challengeWsGridView.startAnimation(anim);
            }

        }, 500);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mColumns = challengeWsGridView.getNumColumns();
        mRows = challengeWsGridView.getNumRows();

        mRows = sp.getInt(getContext(), "GRID");
        mColumns = sp.getInt(getContext(), "GRID");

        System.out.println("88888888888888888888 GRID : " + sp.getInt(getContext(), "GRID"));

        mBoard = new String[mRows][mColumns];
        mLock = new boolean[mRows][mColumns];

        challengeWsGridView.setOnWordSelectedListener(this);
        load_data();





       /* resetBoard();
        generateBoard();
        initBoard();*/


        challengeWsGridView.setVisibility(View.VISIBLE);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void option_load() {

        question_txt.setText(question_list.get(mFoundWords.size()).replace(".", ""));
        select_me = "";

        if (!sp.getString(getActivity(), "option_show").equals("")) {
            option_view.setVisibility(View.VISIBLE);
            option1_txt.setBackgroundColor(Color.parseColor("#ffe900"));
            option2_txt.setBackgroundColor(Color.parseColor("#ffe900"));

            int opt1 = 0, opt2 = 1;

            int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);


            if (opt1 != randomNum) {
                opt1 = randomNum;
                opt2 = 0;
            }

            option_array.clear();
            try {
                cursor = mydb.rawQuery("select * from '" + table_name + "' where ch_words='" + question_list.get(mFoundWords.size()) + "'", null);

                System.out.println("------------------------------question_list select * from '" + table_name + "' where ch_words='" + question_list.get(mFoundWords.size()) + "'");


                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    option_array.add(question_list.get(mFoundWords.size()));
                    option_array.add(cursor.getString(cursor.getColumnIndexOrThrow("opt_word")));
                    option_array.add(cursor.getString(cursor.getColumnIndexOrThrow("qus_word")));

                }
            } finally {
                if (cursor != null)
                    cursor.close();
            }


            System.out.println("------------------------------question_list-option_array" + option_array);

            System.out.println("------------------------------question_list.get(opt1) " + option_array.get(opt1));
            option1_txt.setText(option_array.get(opt1).replace(".", ""));
            option2_txt.setText(option_array.get(opt2).replace(".", ""));
            question_txt.setText(option_array.get(2));

        } else {

            if (game_type.equals("missing_word")) {

                try {
                    cursor = mydb.rawQuery("select * from '" + table_name + "' where ch_words='" + question_list.get(mFoundWords.size()) + "'", null);

                    System.out.println("------------------------------question_list select * from '" + table_name + "' where ch_words='" + question_list.get(mFoundWords.size()) + "'");


                    if (cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        missing_content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

                    }
                } finally {
                    if (cursor != null)
                        cursor.close();
                }
                System.out.println("---------------------content : " + missing_content);

                String htmlText = missing_content.replace(question_list.get(mFoundWords.size()).replace(".", ""), "<font color='#ffffff'>_________</font>");
                question_txt.setText(Html.fromHtml(htmlText));

            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initBoard() {
        challengeWsGridView.setBoard(mBoard);


        List<Word> sortedWords = new ArrayList<Word>(mSolution);
        //Collections.sort(sortedWords);


        counts.setText(mFoundWords.size() + "/" + mSolution.size());

        total_word += mSolution.size();

        option_load();


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("solution", new ArrayList<Word>(mSolution));
        outState.putParcelableArrayList("found", new ArrayList<Word>(mFoundWords));
    }

    public void generateBoard() {

        mRandomIndexes = new int[mRows * mColumns];

        System.out.println("===========================================  generateBoard() - mRandomIndexes.length " + mRandomIndexes.length);


        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < mRandomIndexes.length; i++) {
            mRandomIndexes[i] = i;
        }

        for (int i = mRandomIndexes.length - 1; i >= 1; i--) {
            int randIndex = rand.nextInt(i);
            int realIndex = mRandomIndexes[i];
            mRandomIndexes[i] = mRandomIndexes[randIndex];
            mRandomIndexes[randIndex] = realIndex;
        }


        List<String> elephantList = new ArrayList<>();

        for (String word : mWordList) {

            System.out.println("88888888888888888888 word : " + word);
            addWord(word);
        }

    }


    private challenge_WS_GridView getGridView() {
        return (challenge_WS_GridView) getView().findViewById(R.id.grd_challenge);
    }


    /**
     * Attempts to add a word to the puzzle anywhere possible.
     *
     * @param word The word added
     * @return The Word object representing this words position in the puzzle.
     * Null if the word could not be fit into the puzzle
     */
    private Word addWord(String word) {

        String str[] = word.split("\\.");




       /* StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(s);
        }*/

        System.out.println("Row" + mRows);
        System.out.println("Columns" + mColumns);

        if (str.length > mColumns && str.length > mRows) {
            return null;
        }

        Random rand = new Random();
        for (int i = mDirections.length - 1; i >= 1; i--) {
            int randIndex = rand.nextInt(i);
            Direction direction = mDirections[i];
            mDirections[i] = mDirections[randIndex];
            mDirections[randIndex] = direction;
        }

        Direction bestDirection = null;
        int bestRow = -1;
        int bestCol = -1;
        int bestScore = -1;


        System.out.println("===========================================  mRandomIndexes " + mRandomIndexes);

        for (int index : mRandomIndexes) {
            System.out.println("===========================================  index " + index);
            int row = index / mColumns;
            int col = index % mColumns;

            System.out.println("===========================================  mDirections " + mDirections);

            for (Direction direction : mDirections) {

                System.out.println("===========================================  direction " + direction);

                int score = canFit(word, direction, row, col);

                System.out.println("===========================================  score " + score);
                System.out.println("===========================================  bestScore " + bestScore);


                if (score > bestScore) {
                    bestRow = row;
                    bestCol = col;
                    bestDirection = direction;
                    bestScore = score;
                }
            }
        }

        System.out.println("===========================================  bestScore " + bestScore);
        if (bestScore >= 0) {
            Word result = new Word(word, bestRow, bestCol, bestDirection);
            placeWord(result);
            return result;
        }

        return null;
    }

    /**
     * Places the provided word in the puzzle at the given location. This
     * assumes that tests have already been performed to check if the word will
     * fit. Returns a representation of the word in the puzzle at the
     * appropriate location.
     */
    private void placeWord(Word word) {

        try {
            int curRow = word.getRow();
            int curCol = word.getCol();
            final String wordStr = word.getWord();
            final Direction direction = word.getDirection();

            String[] letter = wordStr.split("\\.");


            for (int i = 0; i < letter.length; i++) {
                String c = letter[i];

                mBoard[curRow][curCol] = c;
                mLock[curRow][curCol] = true;

                if (direction.isUp()) {
                    curRow -= 1;
                } else if (direction.isDown()) {
                    curRow += 1;
                }

                if (direction.isLeft()) {
                    curCol -= 1;
                } else if (direction.isRight()) {
                    curCol += 1;
                }
            }

            mSolution.add(word);
            question_list.add(word.getWord());


            System.out.println("mSolution :" + mSolution);

        } catch (Exception e) {

            System.out.println("Exception5" + e.getMessage());
        }
    }


    /**
     * Determines if a given word can be placed at a given location in a
     * particular direction. This returns a count of the number of characters
     * shared with other words on the board. This is used for difficulty
     * weighting
     */
    private int canFit(String word, Direction direction, int row, int col) {

        int score = 0;

        try {

            String str[] = word.split("\\.");

            StringBuilder builder = new StringBuilder();
            for (String s : str) {
                builder.append(s);
            }

            if (getAvailableSpace(direction, row, col) < builder.toString().length()) {
                return -1;
            }

           /* if (getAvailableSpace(direction, row, col) < word.length()) {
                return -1;
            }*/

            int curRow = row;
            int curCol = col;

            String[] letter = word.split("\\.");

            System.out.println("----------------- word " + word);
            System.out.println("----------------- letter.length " + letter.length);


            for (int i = 0; i < letter.length; i++) {

                String c = letter[i];
                System.out.println("----------------- c " + c);

                if (mLock[curRow][curCol] && mBoard[curRow][curCol] != c) {
                    return -1;
                } else if (mLock[curRow][curCol]) {
                    score++;
                }

                if (direction.isUp()) {
                    curRow -= 1;
                } else if (direction.isDown()) {
                    curRow += 1;
                }

                if (direction.isLeft()) {
                    curCol -= 1;
                } else if (direction.isRight()) {
                    curCol += 1;
                }
            }

        } catch (Exception e) {

            System.out.println("Exception6" + e.getMessage());
        }

        return score;
    }

    /**
     * @return The maximum possible length of a word placed at the given
     * location in a given direction.
     */
    private int getAvailableSpace(Direction direction, int row, int col) {
        switch (direction) {
            case DOWN:
                return mRows - row;
            case DOWN_LEFT:
                return Math.min(mRows - row, col);
            case DOWN_RIGHT:
                return Math.min(mRows - row, mColumns - col);
            case LEFT:
                return col;
            case RIGHT:
                return mColumns - col;
            case UP:
                return row;
            case UP_LEFT:
                return Math.min(row, col);
            case UP_RIGHT:
                return Math.min(row, mColumns - col);
        }

        return 0;
    }

    private void resetBoard() {
        mFoundWords.clear();
        mSolution.clear();
        hints.clear();
        question_list.clear();
        select_me = "";
        game_finish = "";

        challengeWsGridView.reset();

        for (int i = 0; i < mLock.length; i++) {
            for (int j = 0; j < mLock[i].length; j++) {
                mLock[i][j] = false;
            }
        }

        System.out.println("===========================================  resetBoard() - mBoard.length " + mBoard.length);

        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard[i].length; j++) {
                //final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                final String[] alphabet = {"அ", "ஆ", "இ", "ஈ", "உ", "ஊ", "எ", "ஏ", "ஐ", "ஒ", "ஓ", "ஔ"};
                final int N = alphabet.length;

                Random r = new Random();
                String s = alphabet[r.nextInt(N)];
                // char s = alphabet.charAt(r.nextInt(N));
                //mBoard[i][j] = 'Z';
                mBoard[i][j] = s;
            }
        }


        System.out.println("===========================================  resetBoard() - alphabet ");
    }

    public void get_time(int get_time) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView tv1 = new TextView(getActivity());
        tv1.setLayoutParams(params);
        tv1.setPadding(10, 10, 10, 10);
        tv1.setText("+" + get_time);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setTextColor(Color.WHITE);
        tv1.setTextSize(15);
        tv1.setAllCaps(true);
        lay_wordsearch.addView(tv1);
        TranslateAnimation anims1 = new TranslateAnimation(myDialog_class.getXY(more_timer)[0], myDialog_class.getXY(ProgressBar)[0], myDialog_class.getXY(more_timer)[1], myDialog_class.getXY(ProgressBar)[1]);
        anims1.setDuration(500);
        anims1.setFillAfter(true);
        tv1.startAnimation(anims1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lay_wordsearch.removeView(tv1);
            }
        }, 1000);
    }


    public void more_time() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_more_time);
        dialog.setCancelable(false);
        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }

        TextView cancel_time = (TextView) dialog.findViewById(R.id.cancel_time);
        TextView done_time = (TextView) dialog.findViewById(R.id.done_time);
        final CheckBox time_checkbox = (CheckBox) dialog.findViewById(R.id.time_checkbox);

        coin_point1 = Integer.parseInt(coin_txt.getText().toString());

        if (sp.getString(getActivity(), "time_checkbox_" + game_type).equals("")) {
            dialog.show();
        } else {
            if (coin_point1 >= 100) {
                if (time_checkbox.isChecked()) {
                    sp.putString(getActivity(), "time_checkbox_" + game_type, "done");
                }
                coin_point -= 100;
                myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                coin_point1 -= 100;
                coin_txt.setText("" + coin_point1);
                // coin_txt.startAnimation(bounce_anim);

                totalTimeCountInMilliseconds += (30 * 1000);

                get_time(30);

                if ((60 * time_value * 1000) <= totalTimeCountInMilliseconds) {
                    my_progress = (int) (totalTimeCountInMilliseconds / 1000);
                }
                startTimer();

            } else {
                more_coin();
            }
        }

        cancel_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                dialog.dismiss();
                startTimer();
            }
        });

        done_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                dialog.dismiss();

                if (coin_point1 >= 100) {

                    if (time_checkbox.isChecked()) {
                        sp.putString(getActivity(), "time_checkbox_" + game_type, "done");
                    }
                    coin_point -= 100;
                    myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                    coin_point1 -= 100;
                    coin_txt.setText("" + coin_point1);
                    //coin_txt.startAnimation(bounce_anim);

                    totalTimeCountInMilliseconds += (30 * 1000);

                    if ((60 * time_value * 1000) <= totalTimeCountInMilliseconds) {
                        my_progress = (int) (totalTimeCountInMilliseconds / 1000);

                    }
                    get_time(30);
                    startTimer();
                } else {
                    more_coin();
                }


            }
        });

    }

    public void option_hint_dia(final Word hint_word, final String option_show) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_hint_option);
        dialog.setCancelable(false);
        dialog.show();

        final RelativeLayout ans_hint_lay = (RelativeLayout) dialog.findViewById(R.id.ans_hint_lay);
        final RelativeLayout option_hint_lay = (RelativeLayout) dialog.findViewById(R.id.option_hint_lay);

        final ImageView option_A_tick = (ImageView) dialog.findViewById(R.id.option_A_tick);
        final ImageView option_B_tick = (ImageView) dialog.findViewById(R.id.option_B_tick);


        TextView cancel_hint_option = (TextView) dialog.findViewById(R.id.cancel_hint_option);
        TextView done_hint_option = (TextView) dialog.findViewById(R.id.done_hint_option);

        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }

        if (option_show.equals("hint")) {
            option_B_tick.setVisibility(View.VISIBLE);
            ans_hint_lay.setVisibility(View.GONE);
        }

        coin_point1 = Integer.parseInt(coin_txt.getText().toString());

        ans_hint_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                option_A_tick.setVisibility(View.VISIBLE);
                option_B_tick.setVisibility(View.INVISIBLE);
                select_me = "ans";
            }
        });
        option_hint_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                option_A_tick.setVisibility(View.INVISIBLE);
                option_B_tick.setVisibility(View.VISIBLE);
                select_me = "opt";
            }
        });
        cancel_hint_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");


                if (option_show.equals("all")) {
                    select_me = "";
                }
                dialog.dismiss();
                startTimer();
            }
        });
        done_hint_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                if (select_me.equals("ans")) {
                    coin_point -= 10;
                    myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                    coin_point1 -= 10;
                    coin_txt.setText("" + coin_point1);
                    // coin_txt.startAnimation(bounce_anim);


                    if (game_type.equals("missing_word")) {
                        String htmlText = missing_content.replace(question_list.get(mFoundWords.size()).replace(".", ""), "<font color='#faff72'>" + question_list.get(mFoundWords.size()).replace(".", "") + "</font>");
                        question_txt.setText(Html.fromHtml(htmlText));
                    } else {
                        if (question_list.get(mFoundWords.size()).replace(".", "").equals(option1_txt.getText().toString())) {
                            option1_txt.setBackgroundColor(Color.parseColor("#45ea3d"));
                        } else {
                            option2_txt.setBackgroundColor(Color.parseColor("#45ea3d"));
                        }
                    }
                    dialog.dismiss();
                    startTimer();

                } else if (select_me.equals("opt")) {

                    if (option_show.equals("hint")) {
                        coin_point -= 10;
                        myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");
                        coin_point1 -= 10;
                    } else {
                        coin_point -= 20;
                        myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");
                        coin_point1 -= 20;
                    }


                    coin_txt.setText("" + coin_point1);
//                    coin_txt.startAnimation(bounce_anim);


                    if (game_type.equals("missing_word")) {
                        String htmlText = missing_content.replace(question_list.get(mFoundWords.size()).replace(".", ""), "<font color='#faff72'>" + question_list.get(mFoundWords.size()).replace(".", "") + "</font>");
                        question_txt.setText(Html.fromHtml(htmlText));
                    } else {
                        if (question_list.get(mFoundWords.size()).replace(".", "").equals(option1_txt.getText().toString())) {
                            option1_txt.setBackgroundColor(Color.parseColor("#45ea3d"));
                        } else {
                            option2_txt.setBackgroundColor(Color.parseColor("#45ea3d"));
                        }
                    }
                    if (!hints.contains(hint_word.getWord())) {
                        hints.add(hint_word.getWord());
                    }

                    challengeWsGridView.showHint(hint_word);

                    dialog.dismiss();
                    startTimer();
                } else {
                    Toast.makeText(getActivity(), "மேலேயுள்ள விருப்பத்தை தேர்வு செய்யவும்..", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void hint_dia(final Word hint_word) {

        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_hint_show);
        dialog.setCancelable(false);


        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }

        TextView cancel_hint = (TextView) dialog.findViewById(R.id.cancel_hint);
        TextView done_hint = (TextView) dialog.findViewById(R.id.done_hint);
        final CheckBox hint_checkbox = (CheckBox) dialog.findViewById(R.id.hint_checkbox);

        coin_point1 = Integer.parseInt(coin_txt.getText().toString());


        if (sp.getString(getActivity(), "hint_checkbox_" + game_type).equals("")) {
            if (!hints.contains(hint_word.getWord())) {
                dialog.show();
            } else {
                challengeWsGridView.showHint(hint_word);
            }

        } else {

            if (coin_point1 >= 20) {
                if (!hints.contains(hint_word.getWord())) {
                    hints.add(hint_word.getWord());
                }

                coin_point -= 20;
                myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                coin_point1 -= 20;
                coin_txt.setText("" + coin_point1);

                //    coin_txt.startAnimation(bounce_anim);

                challengeWsGridView.showHint(hint_word);

                startTimer();

            } else {
                more_coin();
            }
        }

        cancel_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                dialog.dismiss();
                startTimer();

            }
        });
        done_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");


                if (coin_point >= 20) {
                    if (hint_checkbox.isChecked()) {
                        sp.putString(getActivity(), "hint_checkbox_" + game_type, "done");
                    }

                    coin_point -= 20;
                    myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                    coin_point1 -= 20;
                    coin_txt.setText("" + coin_point1);

//                    coin_txt.startAnimation(bounce_anim);


                    if (!hints.contains(hint_word.getWord())) {
                        hints.add(hint_word.getWord());
                    }

                    challengeWsGridView.showHint(hint_word);

                    startTimer();
                } else {
                    more_coin();
                }

                dialog.dismiss();
            }
        });
    }


    public void more_coin() {
        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }
        final Dialog openDialog_earncoin;

        openDialog_earncoin = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = (TextView) openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = (TextView) openDialog_earncoin.findViewById(R.id.ssss);


        TextView wpro = (TextView) openDialog_earncoin.findViewById(R.id.wpro);

        cancel.setVisibility(View.INVISIBLE);
        //   wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startTimer();
                openDialog_earncoin.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startTimer();
                openDialog_earncoin.cancel();
            }
        });


        RelativeLayout video = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnvideo);

        share_content = "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh";
      /*  String root = Environment.getExternalStorageDirectory().toString();
        File mydir = new File(root + "/.nithra/TWS");
        mydir.mkdirs();
        String fname = "Image-appshare.jpg";
        final File file = new File(mydir, fname);*/

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rvo = 1;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {

                        reward_progressBar.dismiss();
                        rewardedVideoAd.show();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                        reward();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);


                    }
                } else {

                    Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

            }
        });

        wp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");

                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 21);

/*
                        if (sps.getString(Clue_Game_Hard.this,"watts_app_s").equals(""))
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

                                    sps.putString(Clue_Game_Hard.this,"watts_app_s","yes");

                                }
                            }, 3000);
                        }
*/


                    } else {
                        Toast.makeText(getActivity(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    // toast("இணையதள சேவையை சரிபார்க்கவும் ");
                }
            /*    if (isNetworkAvailable()) {
                    if (appInstalledOrNot("com.whatsapp")) {
                        Uri uri = Uri.fromFile(file);
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setPackage("com.whatsapp");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if ((ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {

                                shareIntent.setType("image/*");
                                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            } else {
                                shareIntent.setType("text/plain");
                            }
                        } else {
                            shareIntent.setType("image/*");
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                        }

                        shareIntent.putExtra(Intent.EXTRA_TEXT, share_content);
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "சொல்லிஅடி !!");
                        startActivityForResult(Intent.createChooser(shareIntent, "Share via"), 12);
                    } else {
                        Toast.makeText(getActivity(), "இந்த செயலி தங்களிடம் தற்போது இல்லை பிறகு முயற்சிக்கவும் . ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "இணையத்தள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  if (isNetworkAvailable()) {

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
                    Toast.makeText(getActivity(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }*/

/*
                if (isNetworkAvailable()) {

                    System.out.println("-------gg share_content.length() "+share_content.length());

                    share_content="நித்ராவின் செம்மொழி  வேட்டை \n" + "https://goo.gl/ub45C1";

                    Bundle params = new Bundle();
                    //params.putString("message", "https://play.google.com/store/apps/details?id=nithra.tamil.letter.crossword.search");
                    params.putString("message", share_content);
                    showDialogWithoutNotificationBarInvite("apprequests", params);
                } else {
                    Toast.makeText(getActivity(), "இணையத்தள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {

                    if (appInstalledOrNot("com.google.android.apps.plus")) {

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");
                        i.putExtra(Intent.EXTRA_TEXT, share_content);
                        i.putExtra(Intent.EXTRA_SUBJECT, "சொல்லிஅடி !!");
                        startActivityForResult(Intent.createChooser(i, "Share via"), 15);

                    } else {
                        Toast.makeText(getActivity(), "இந்த செயலி தங்களிடம் தற்போது இல்லை பிறகு முயற்சிக்கவும் . ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "இணையத்தள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }

        });

        openDialog_earncoin.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                startTimer();
            }
        });
        openDialog_earncoin.show();
    }


   /* public void more_coin() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_more_coin);
        dialog.setCancelable(false);


        Bitmap imgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.loading_page);
        // String imgBitmapPath = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), imgBitmap, "title", null);
        String root = Environment.getExternalStorageDirectory().toString();
        File mydir = new File(root + "/.nithra/TWS");
        mydir.mkdirs();
        String fname = "Image-appshare.jpg";
        final File file = new File(mydir, fname);

        if (file.exists()) {
            file.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            imgBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("errorg" + e);
        }

        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }
        dont_call = "dont_call";
        TextView cancel_morecoin = (TextView) dialog.findViewById(R.id.cancel_morecoin);
        RelativeLayout credit_reward = (RelativeLayout) dialog.findViewById(R.id.credit_reward);
        RelativeLayout credit_WApp = (RelativeLayout) dialog.findViewById(R.id.credit_WApp);
        RelativeLayout credit_fb = (RelativeLayout) dialog.findViewById(R.id.credit_fb);
        RelativeLayout credit_gm = (RelativeLayout) dialog.findViewById(R.id.credit_gm);
        RelativeLayout credit_more = (RelativeLayout) dialog.findViewById(R.id.credit_more);

        final LikeButton media_reward = (LikeButton) dialog.findViewById(R.id.media_reward);
        final LikeButton media1 = (LikeButton) dialog.findViewById(R.id.media1);
        final LikeButton media2 = (LikeButton) dialog.findViewById(R.id.media2);
        final LikeButton media3 = (LikeButton) dialog.findViewById(R.id.media3);
        final LikeButton media4 = (LikeButton) dialog.findViewById(R.id.media4);

        media_reward.setOnLikeListener(this);
        media_reward.setOnAnimationEndListener(this);

        media1.setOnLikeListener(this);
        media1.setOnAnimationEndListener(this);

        media2.setOnLikeListener(this);
        media2.setOnAnimationEndListener(this);

        media3.setOnLikeListener(this);
        media3.setOnAnimationEndListener(this);

        media4.setOnLikeListener(this);
        media4.setOnAnimationEndListener(this);

        credit_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                //media1.setLiked(true);
                media_reward.onClick(v);
            }
        });

        credit_WApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                media1.onClick(v);
            }
        });

        credit_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                media2.onClick(v);
            }
        });

        credit_gm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                media3.onClick(v);
            }
        });
        credit_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                media4.onClick(v);
            }
        });


        cancel_morecoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                dialog.dismiss();
                startTimer();

                if (timer_stop) {
                    //timer_stop = false;

                }
            }
        });

        dialog.show();

    }*/

  /*  public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());
    }

    private void openFacebookSession() {
        Session.openActiveSession(getActivity(), true, Arrays.asList("email",
                "user_birthday", "user_hometown", "user_location"),
                new Session.StatusCallback() {
                    @Override
                    public void call(Session session, SessionState state,
                                     Exception exception) {

                        if (session != null && session.isOpened()) {
                            // toast("open");

                            if (btn_str.equals("share")) {

                                publishFeedDialog();
                            } else if (btn_str.equals("invite")) {

                                Bundle params = new Bundle();
                                params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                        "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                                showDialogWithoutNotificationBarInvite(
                                        "apprequests", params);
                            }
                        }
                    }
                });
    }*/

/*    private void publishFeedDialog() {
        Bundle params = new Bundle();
        params.putString("name", "சொல்லிஅடி");
        // params.putString("caption", "");


        params.putString("name", "சொல்லிஅடி");
        // params.putString("message", "my_message");
        params.putString("link", "https://goo.gl/CcA9a8");
        params.putString("description", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் +\n" +
                "                             விளையாட இங்கே கிளிக் செய்யவும் ");
        params.putString("caption", "நான் சொல்லிஅடி விளையாட இங்கே கிளிக் செய்யவும்  ");


        WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(getActivity(),
                Session.getActiveSession(), params)).setOnCompleteListener(
                new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the success
                            // and the post Id.
                            final String postId = values.getString("post_id");
                            if (postId != null) {


                                try {
                                    System.out.println("Invitation was sent to "
                                            + values.toString());

                                    for (int i = 0; values.containsKey("to[" + i + "]"); i++) {
                                        String curId = values
                                                .getString("to[" + i + "]");

                                    }

                                    // lastearn("invaite friends", (values.size() - 1));
                                    if ((values.size() - 1) >= 1) {
                                        //setcoin(values.size() - 1);

                                        coin_collection((values.size() - 1), 10);
                                    }

                                } catch (Exception e) {

                                }


                            } else {
                                // User clicked the Cancel button
                                Toast.makeText(getActivity(),
                                        "Publish cancelled", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                            // User clicked the "x" button
                            Toast.makeText(getActivity(),
                                    "Publish cancelled", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            // Generic, ex: network error
                            Toast.makeText(getActivity(),
                                    "Error posting story", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                }).build();
        feedDialog.show();
    }*/

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void coin_earned_anim(final TextView textView, int count) {
        ValueAnimator animator = new ValueAnimator();
        //animator.setObjectValues(0, (coinearn * 10));
        animator.setObjectValues(0, count);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText("" + (int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    private void coin_collection(int share_member, int value) {

        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.share_dialog2);
        dialog.setCancelable(false);
        dialog.show();

        TextView ok_y = (TextView) dialog.findViewById(R.id.ok_y);
        final TextView b_scores = (TextView) dialog.findViewById(R.id.b_scores);


        try {
            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
            }
        } finally {
            if (coin_cursor != null)
                coin_cursor.close();
        }
        coin_point += (share_member * value);
        coin_earned_anim(b_scores, (share_member * value));

        myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  myDialog_class.media_player(getActivity(), R.raw.coin, "normal");
                dialog.dismiss();
                coin_txt.setText("" + coin_point);
                //   coin_txt.startAnimation(bounce_anim);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Winning_dialog != null && Winning_dialog.isShowing()) {
            Winning_dialog.dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);

        if (requestCode == 11) {//sms
            if (resultCode == 1) {


            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();

            }
        }
        if (requestCode == 12) {//whats app
            if (resultCode == -1) {

                coin_collection(1, 20);
            } else {
                // Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 15) {//g plus
            if (resultCode == -1) {
                coin_collection(1, 10);
            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 21) {
            if (resultCode == -1) {
                coin_collection(1, 20);
            }
        }

    }

    /*
        private void showDialogWithoutNotificationBarInvite(String action, Bundle params) {
            final WebDialog dialog = new WebDialog.Builder(getActivity(),
                    Session.getActiveSession(), action, params)
                    .setOnCompleteListener(new WebDialog.OnCompleteListener() {
                        @Override
                        public void onComplete(Bundle values,
                                               FacebookException error) {
                            System.out.println("fb error : " + error);
                            if (error != null && !(error instanceof FacebookOperationCanceledException)) {
                                Toast.makeText(getActivity(), "Request Not Send", Toast.LENGTH_SHORT).show();
                            }

                            try {
                                System.out.println("Invitation was sent to "
                                        + values.toString());

                                for (int i = 0; values.containsKey("to[" + i + "]"); i++) {
                                    String curId = values
                                            .getString("to[" + i + "]");

                                }

                                // lastearn("invaite friends", (values.size() - 1));
                                if ((values.size() - 1) >= 1) {
                                    //setcoin(values.size() - 1);

                                    coin_collection((values.size() - 1), 10);
                                }

                            } catch (Exception e) {

                            }
                        }
                    }).build();

            Window dialog_window = dialog.getWindow();
            dialog_window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            // dialogAction = action;
            // dialogParams = params;

            dialog.show();
        }
    */
    public void price_update() {
        ////////////////Prize//////////////////
        if (hints.size() != 0) {
            prize_data_update(context, 25);
        } else {
            prize_data_update(context, 75);
        }
        ////////////////Prize//////////////////
    }

/*
    public void reward(final Context context) {
        rewardedVideoAd = new com.facebook.ads.RewardedVideoAd(context, getString(R.string.fb_rewarded_ins));
        rewardedVideoAd.setAdListener(new com.facebook.ads.RewardedVideoAdListener() {

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
        rewardedVideoAd.loadAd();
    }
*/

    public void reward() {
        rewardedVideoAd = new RewardedVideoAd(context, getString(R.string.fb_rewarded_ins));
      /*  rewardedVideoAd.setAdListener(new RewardedVideoAdListener() {

            @Override
            public void onRewardedVideoCompleted() {
                reward_status=1;
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onRewardedVideoClosed() {
                reward();
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
        rewardedVideoAd.loadAd();*/
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
                reward();
                if (reward_status == 1) {
                  /*  if (extra_coin_s == 0) {
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + mCoinCount;
                        String aStringx = Integer.toString(spx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                    }*/
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

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);

        /*   Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        int spx = skx + a;
        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/

/*
        final String aStringx = Integer.toString(spx);*/
        b_scores.setText("" + a);
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog.dismiss();
                //  //mCoinCount = 0;
            }
        });
        openDialog.show();
    }

    public void vidcoinearn() {
        if (extra_coin_s == 1) {
            extra_coin_s = 0;

            //   //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
            TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            final int spx = skx + mCoinCount;
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            final String aStringx = Integer.toString(spx);


            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    coin_txt.setText("" + spx);
                    openDialog.dismiss();
                    // //mCoinCount = 0;
                }
            });

            openDialog.show();
        }

    }


}
