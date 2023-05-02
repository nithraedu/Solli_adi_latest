package nithra.tamil.word.game.solliadi.word_search_game.Models.chellange;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import nithra.tamil.word.game.solliadi.DataBaseHelper;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper2;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper3;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utills;
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


public class challenge_WS_GridFragment extends Fragment implements challenge_WS_GridView.OnWordSelectedListener, OnLikeListener, OnAnimationEndListener {


    public static final Set<Word> mSolution = new HashSet<Word>();
    public static final Set<Word> mFoundWords = new HashSet<Word>();
    public static final ArrayList<String> hints = new ArrayList<>();
    static private final int coinearn = 1;
    static private final int mCoinCount = 20;
    public static LinearLayout lay_wordsearch;
    public static int levelId, gridcount;
    static int rvo = 0;
    private static String[] mWordList = {"வ.ள.ம்", "மு.ய.ல்", "ந.ல.ம்", "நி.ற.ம்", "மு.த.லை", "ந.ம.து", "அ.ள.வு"};
    final ArrayList<String> question_list = new ArrayList<>();
    final SharedPreference sp = new SharedPreference();
    final my_dialog myDialog_class = new my_dialog();
    final ArrayList<String> option_array = new ArrayList<>();
    final List<String> elephantList = new ArrayList<>();
    final String Dailytest_ok = "";
    private final Direction[] mDirections = Direction.values();
    // Facebook variable starts
    public CountDownTimer countDownTimer;
    challenge_WS_GridView challengeWsGridView;
    MyTableView myTableView;
    LinearLayout option_view;
    RelativeLayout toptool, hintview, coin_lay, progress_lay;
    double diagonalInches;
    String table_name = "", dont_call = "", game_type = "", select_me = "", missing_content = "", game_finish = "";
    Animation bounce_anim = null;
    Context context;
    ImageView more_timer, saval_setting;
    View view = null;
    Vibrator vibrate;
    TextView counton_timer, counts, screatch_txt, question_txt, coin_txt, option1_txt, option2_txt, title_txt;
    ProgressBar ProgressBar;
    int my_progress = 0, time_value = 2, coin_point = 0, leader_point = 0, coin_point1 = 0, goback = 0, total_word = 0, total_finded = 0;
    Dialog my_my_dialog = null;
    SQLiteDatabase mydb, Inner_mydb, exdb;
    Cursor cursor = null, coin_cursor;
    ImageView icon_ad_img;
    LinearLayout n_icon_ad;
    LinearLayout normal_baner;
    String share_content = "";
    String btn_str = "";
    Dialog Winning_dialog;
    FirebaseAnalytics mFirebaseAnalytics;
    // facebook variable ends
    int extra_coin_s = 0;
    MediaPlayer beep_mp;
    int fb_reward = 0;
    int reward_status = 0;
    String reward_video = "";
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    SQLiteDatabase dbs, dbn, dbn2;
    Handler handler;
    Runnable my_runnable;
    private int mRows;
    private int mColumns;
    private boolean[][] mLock;
    private int[] mRandomIndexes;
    private String[][] mBoard;
    private long totalTimeCountInMilliseconds, timeBlinkInMilliseconds; // total count down time in
    private boolean blink, timer_stop = false;
    private RewardedInterstitialAd rewardedAd;
    private InterstitialAd mInterstitialAd;

    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sp.getString(context, "ws_challenge_showcase_intro").equals("")) {
                showcase_dismiss();
            } else {
                startTimer();
                sp.putString(getActivity(), "ws_challenge_intro", "no");
            }

        }, 800);
    }
    //reward videos process 1***********************

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

                    }

                    counton_timer.setText(String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60));
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
                        adShow();
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
            if (coin_cursor != null) coin_cursor.close();
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
            if (cursor != null) cursor.close();

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.challenge_view, null);
        System.out.println("-----hh Dailytest_ok : " + Dailytest_ok);

        progress_lay = view.findViewById(R.id.progress_lay);

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

        context = getActivity();

        if (my_dialog.isNetworkAvailable(getActivity())) {
            Utills.INSTANCE.initializeAdzz(getActivity());
            if (sp.getInt(context, "purchase_ads") == 0) {
                industrialload();
            }
            rewarded_adnew();
        }
        normal_baner = view.findViewById(R.id.normal_baner);
        Utills.INSTANCE.load_add_AppLovin(getActivity(), normal_baner, getResources().getString(R.string.Bottom_Banner));

        coin_lay = view.findViewById(R.id.coin_lay);
        coin_txt = view.findViewById(R.id.coin_txt);
        counts = view.findViewById(R.id.counts);
        counton_timer = view.findViewById(R.id.counton_timer);
        more_timer = view.findViewById(R.id.more_timer);
        saval_setting = view.findViewById(R.id.saval_setting);
        screatch_txt = view.findViewById(R.id.screatch_txt);
        question_txt = view.findViewById(R.id.question_txt);

        option1_txt = view.findViewById(R.id.option1_txt);
        option2_txt = view.findViewById(R.id.option2_txt);
        title_txt = view.findViewById(R.id.title_txt);
        title_txt.setText(sp.getString(getActivity(), "saval_type"));


        icon_ad_img = view.findViewById(R.id.icon_ad_img);
        n_icon_ad = view.findViewById(R.id.n_icon_ad);
        n_icon_ad.setOnClickListener(view -> {
            //  myDialog_class.WST_Native_IconAd_show();
        });

        vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);


        ProgressBar = view.findViewById(R.id.ProgressBar);

        //bounce_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_anim);

        option_view = view.findViewById(R.id.option_view);

        question_txt.setOnClickListener(v -> {
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


        });

        coin_lay.setOnClickListener(v -> {
            myDialog_class.media_player(getActivity(), R.raw.click, "normal");

            more_coin();
        });


        more_timer.setOnClickListener(v -> {
            myDialog_class.media_player(getActivity(), R.raw.click, "normal");

            more_time();
        });

        saval_setting.setOnClickListener(v -> {

            String snd = sp.getString(getActivity(), "snd");
            if (snd.equals("off")) {
                sp.putString(getActivity(), "snd", "on");
                saval_setting.setBackgroundResource(R.drawable.sound_on);
            } else if (snd.equals("on")) {
                sp.putString(getActivity(), "snd", "off");
                saval_setting.setBackgroundResource(R.drawable.sound_off);
            }


            // myDialog_class.setting(getActivity()).show();
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

        lay_wordsearch = view.findViewById(R.id.lay_wordsearch);

        challengeWsGridView = view.findViewById(R.id.grd_challenge);
        myTableView = view.findViewById(R.id.mytab);


        toptool = view.findViewById(R.id.toptool);
        hintview = view.findViewById(R.id.hintview);


        if (sp.getInt(getContext(), "Grid") == 1) {

            myTableView.setVisibility(View.VISIBLE);
        }

        levelId = sp.getInt(getContext(), "LEVEL");
        gridcount = sp.getInt(getContext(), "GRID");


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

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(getActivity()).setTarget(question_txt).setDismissText("சரி").setContentText("குறிப்பு பார்க்க சொல்லை சொடுக்கவும்.").build()).setOnItemDismissedListener((itemView, position) -> {
                if (position == 2) {
                    // startTimer();
                    sp.putString(getActivity(), "ws_challenge_intro", "no");
                    sp.putString(getActivity(), "ws_challenge_showcase_intro", "yes");
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

    @Override
    public void onPause() {
        super.onPause();
        reward_video = "";

        if (handler != null) handler.removeCallbacks(my_runnable);

    }


    //reward videos***********************//

    @Override
    public void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ON Resume  " + sp.getInt(context, "Game1_Stage_Close_VV"));
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        mFirebaseAnalytics.setCurrentScreen(getActivity(), "Word Search Challenge", null);


        if (timer_stop) {
            if (game_finish.equals("")) {
                startTimer();
                timer_stop = false;
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

                if (question_list.get(mFoundWords.size()).replace(".", "").equals(forwardWord.toString().toLowerCase()) || question_list.get(mFoundWords.size()).replace(".", "").equals(reverseWord.toString().toLowerCase())) {


                    if (wordStart != firstPos && wordStart != lastPos) {
                        if (word.getWord().equalsIgnoreCase(forwardWord.toString()) || word.getWord().equalsIgnoreCase(reverseWord.toString())) {

                            Toast.makeText(getContext(), "இந்த வார்த்தை வேறு இடத்தில் உள்ளது ", Toast.LENGTH_SHORT).show();
                        }
                        continue;
                    }

                    Word found = (word.getWord().replace(".", "").equals(forwardWord.toString())) ? word : null;


                    if (found == null) {
                        found = (word.getWord().replace(".", "").equals(reverseWord.toString())) ? word : null;
                    }

                    System.out.println("=========================== word " + word.getWord());
                    System.out.println("=========================== forwardWord.toString() " + forwardWord);
                    System.out.println("=========================== reverseWord.toString() " + reverseWord);
                    System.out.println("=========================== found " + found);

                    if (found != null) {

                        myDialog_class.media_player(getActivity(), R.raw.scretch_done, "normal");

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
                            if (cursor != null) cursor.close();
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
                if (cursor != null) cursor.close();

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

        String share_content = "தமிழ் வார்த்தை விளையாட்டை விளையாட இது உங்களுக்கான சொற்களம்.\n" + "நீங்களும் இக்களத்தில் ஒருவராக இருக்க \n" + "நித்ராவின் செம்மொழி  வேட்டை செயலியை  கீழே உள்ள லிங்கை கிளிக் செய்து தரவிறக்கம் செய்யவும்.\n" + "https://goo.gl/5FhjMM";


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
        Winning_dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        Winning_dialog.setContentView(R.layout.dia_winning_report);
        //    Winning_dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
        Winning_dialog.setCancelable(false);
        if (!Winning_dialog.isShowing()) {
            Winning_dialog.show();
        }
        my_my_dialog = Winning_dialog;

        TextView video_earn = Winning_dialog.findViewById(R.id.video_earn);
        video_earn.setText("காணொளியை பார்த்து " + sp.getInt(challenge_WS_GridFragment.this.context, "reward_coin_txt") + "+ நாணயங்கள் பெற");

        LinearLayout vid_earn = Winning_dialog.findViewById(R.id.vid_earn);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(challenge_WS_GridFragment.this.context, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);

        vid_earn.setOnClickListener(v -> {
            rvo = 2;
            extra_coin_s = 0;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {

                    reward_progressBar.dismiss();
                    show_reward();


                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {
                    fb_reward = 0;
                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);


                }
            } else {

                Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }

        });

        LinearLayout fl_adplaceholder = Winning_dialog.findViewById(R.id.fl_adplaceholder);
        if (sp.getInt(challenge_WS_GridFragment.this.context, "purchase_ads") == 1) {
            fl_adplaceholder.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(context)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(context, fl_adplaceholder);
            } else {
                fl_adplaceholder.setVisibility(View.GONE);
            }
        }

        //  price_update();

        goback = 0;
        my_my_dialog.setOnDismissListener(dialog -> {
            if (goback == 0) {
                Intent intent = new Intent(challenge_WS_GridFragment.this.context, Word_search_main.class);
                challenge_WS_GridFragment.this.getActivity().finish();
                startActivity(intent);
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
            if (coin_cursor != null) coin_cursor.close();
        }


        TextView best_score = Winning_dialog.findViewById(R.id.best_score);
        TextView current_score = Winning_dialog.findViewById(R.id.current_score);
        TextView win_coin = Winning_dialog.findViewById(R.id.win_coin);
        TextView credit_coin = Winning_dialog.findViewById(R.id.credit_coin);
        TextView time_txt = Winning_dialog.findViewById(R.id.time_txt);
        TextView retry_txt = Winning_dialog.findViewById(R.id.retry_txt);
        Chronometer best_chronometer = Winning_dialog.findViewById(R.id.best_chronometer);

        TextView best_valthu_txt = Winning_dialog.findViewById(R.id.best_valthu_txt);
        ArrayList<String> best_word = new ArrayList<>(Arrays.asList("மிக சிறப்பு", "வாழ்த்துக்கள்", "அருமை", "அற்புதம்", "தமிழ் வாழ்க", "தமிழன்"));
        String bestword = best_word.get(new Random().nextInt(best_word.size()));
        best_valthu_txt.setText("" + bestword);

        RelativeLayout retry_game = Winning_dialog.findViewById(R.id.retry_game);
        RelativeLayout next_game = Winning_dialog.findViewById(R.id.next_game);

        LikeButton like_retry_game = Winning_dialog.findViewById(R.id.like_retry_game);
        LikeButton like_continue_img = Winning_dialog.findViewById(R.id.like_continue_img);


        retry_game.setOnClickListener(v -> {
            myDialog_class.media_player(challenge_WS_GridFragment.this.context, R.raw.click, "normal");
            goback = 1;

            Intent intent = new Intent(challenge_WS_GridFragment.this.context, Word_search_main.class);
            challenge_WS_GridFragment.this.getActivity().finish();
            startActivity(intent);

        });

        next_game.setOnClickListener(v -> {
            myDialog_class.media_player(challenge_WS_GridFragment.this.context, R.raw.click, "normal");
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
                if (coin_cursor != null) coin_cursor.close();
            }

            load_data();
            Winning_dialog.dismiss();

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

        if (sp.getInt(challenge_WS_GridFragment.this.context, "bestscore" + game_type) <= (best_score_value + bonus_value)) {
            sp.putInt(challenge_WS_GridFragment.this.context, "bestscore" + game_type, (best_score_value + bonus_value));
        }
        time_txt.setTextSize(12);
        time_txt.setText("வார்த்தைகள் ");
        best_chronometer.setText("" + total_finded + "/" + total_word);
        retry_txt.setText("வெளியேற");

        best_score.setText("" + (sp.getInt(challenge_WS_GridFragment.this.context, "bestscore" + game_type)));
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
            if (cursor != null) cursor.close();
        }


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


        challengeWsGridView.setVisibility(View.VISIBLE);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void option_load() {

        question_txt.setText(question_list.get(mFoundWords.size()).replace(".", ""));
        select_me = "";

        if (!sp.getString(context, "option_show").equals("")) {
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
                if (cursor != null) cursor.close();
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
                    if (cursor != null) cursor.close();
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


    /**
     * Attempts to add a word to the puzzle anywhere possible.
     *
     * @param word The word added
     */
    private void addWord(String word) {

        String[] str = word.split("\\.");


        System.out.println("Row" + mRows);
        System.out.println("Columns" + mColumns);

        if (str.length > mColumns && str.length > mRows) {
            return;
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
        }

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

            String[] str = word.split("\\.");

            StringBuilder builder = new StringBuilder();
            for (String s : str) {
                builder.append(s);
            }

            if (getAvailableSpace(direction, row, col) < builder.toString().length()) {
                return -1;
            }

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
                mBoard[i][j] = s;
            }
        }


        System.out.println("===========================================  resetBoard() - alphabet ");
    }

    public void get_time(int get_time) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView tv1 = new TextView(context);
        tv1.setLayoutParams(params);
        tv1.setPadding(10, 10, 10, 10);
        tv1.setText("+" + get_time);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setTextColor(Color.WHITE);
        tv1.setTextSize(15);
        tv1.setAllCaps(true);
        lay_wordsearch.addView(tv1);
        TranslateAnimation anims1 = new TranslateAnimation(my_dialog.getXY(more_timer)[0], my_dialog.getXY(ProgressBar)[0], my_dialog.getXY(more_timer)[1], my_dialog.getXY(ProgressBar)[1]);
        anims1.setDuration(500);
        anims1.setFillAfter(true);
        tv1.startAnimation(anims1);

        new Handler(Looper.myLooper()).postDelayed(() -> lay_wordsearch.removeView(tv1), 1000);
    }

    public void more_time() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_more_time);
        dialog.setCancelable(false);
        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }

        TextView cancel_time = dialog.findViewById(R.id.cancel_time);
        TextView done_time = dialog.findViewById(R.id.done_time);
        final CheckBox time_checkbox = dialog.findViewById(R.id.time_checkbox);

        coin_point1 = Integer.parseInt(coin_txt.getText().toString());

        if (sp.getString(context, "time_checkbox_" + game_type).equals("")) {
            dialog.show();
        } else {
            if (coin_point1 >= 100) {
                if (time_checkbox.isChecked()) {
                    sp.putString(context, "time_checkbox_" + game_type, "done");
                }
                coin_point -= 100;
                myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                coin_point1 -= 100;
                coin_txt.setText("" + coin_point1);
                // coin_txt.startAnimation(bounce_anim);

                totalTimeCountInMilliseconds += (30 * 1000);

                get_time(30);

                if ((60L * time_value * 1000) <= totalTimeCountInMilliseconds) {
                    my_progress = (int) (totalTimeCountInMilliseconds / 1000);
                }
                startTimer();

            } else {
                more_coin();
            }
        }

        cancel_time.setOnClickListener(v -> {
            myDialog_class.media_player(context, R.raw.click, "normal");

            dialog.dismiss();
            startTimer();
        });

        done_time.setOnClickListener(v -> {
            myDialog_class.media_player(context, R.raw.click, "normal");

            dialog.dismiss();

            if (coin_point1 >= 100) {

                if (time_checkbox.isChecked()) {
                    sp.putString(context, "time_checkbox_" + game_type, "done");
                }
                coin_point -= 100;
                myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                coin_point1 -= 100;
                coin_txt.setText("" + coin_point1);
                //coin_txt.startAnimation(bounce_anim);

                totalTimeCountInMilliseconds += (30 * 1000);

                if ((60L * time_value * 1000) <= totalTimeCountInMilliseconds) {
                    my_progress = (int) (totalTimeCountInMilliseconds / 1000);

                }
                get_time(30);
                startTimer();
            } else {
                more_coin();
            }


        });

    }

    public void option_hint_dia(final Word hint_word, final String option_show) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_hint_option);
        dialog.setCancelable(false);
        dialog.show();

        final RelativeLayout ans_hint_lay = dialog.findViewById(R.id.ans_hint_lay);
        final RelativeLayout option_hint_lay = dialog.findViewById(R.id.option_hint_lay);

        final ImageView option_A_tick = dialog.findViewById(R.id.option_A_tick);
        final ImageView option_B_tick = dialog.findViewById(R.id.option_B_tick);


        TextView cancel_hint_option = dialog.findViewById(R.id.cancel_hint_option);
        TextView done_hint_option = dialog.findViewById(R.id.done_hint_option);

        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }

        if (option_show.equals("hint")) {
            option_B_tick.setVisibility(View.VISIBLE);
            ans_hint_lay.setVisibility(View.GONE);
        }

        coin_point1 = Integer.parseInt(coin_txt.getText().toString());

        ans_hint_lay.setOnClickListener(v -> {
            myDialog_class.media_player(context, R.raw.click, "normal");

            option_A_tick.setVisibility(View.VISIBLE);
            option_B_tick.setVisibility(View.INVISIBLE);
            select_me = "ans";
        });
        option_hint_lay.setOnClickListener(v -> {
            myDialog_class.media_player(context, R.raw.click, "normal");

            option_A_tick.setVisibility(View.INVISIBLE);
            option_B_tick.setVisibility(View.VISIBLE);
            select_me = "opt";
        });
        cancel_hint_option.setOnClickListener(v -> {
            myDialog_class.media_player(context, R.raw.click, "normal");


            if (option_show.equals("all")) {
                select_me = "";
            }
            dialog.dismiss();
            startTimer();
        });
        done_hint_option.setOnClickListener(v -> {
            myDialog_class.media_player(context, R.raw.click, "normal");

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
                Toast.makeText(context, "மேலேயுள்ள விருப்பத்தை தேர்வு செய்யவும்..", Toast.LENGTH_SHORT).show();
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

        TextView cancel_hint = dialog.findViewById(R.id.cancel_hint);
        TextView done_hint = dialog.findViewById(R.id.done_hint);
        final CheckBox hint_checkbox = dialog.findViewById(R.id.hint_checkbox);

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

        cancel_hint.setOnClickListener(v -> {
            myDialog_class.media_player(getActivity(), R.raw.click, "normal");

            dialog.dismiss();
            startTimer();

        });
        done_hint.setOnClickListener(v -> {
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
        });
    }

    public void more_coin() {
        if (Dailytest_ok.equals("")) {
            countDownTimer.cancel();
        }
        final Dialog openDialog_earncoin;

        openDialog_earncoin = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = openDialog_earncoin.findViewById(R.id.ssss);


        TextView wpro = openDialog_earncoin.findViewById(R.id.wpro);

        cancel.setVisibility(View.VISIBLE);
        //   wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");

        ss.setOnClickListener(v -> {
            // startTimer();
            openDialog_earncoin.cancel();
        });
        cancel.setOnClickListener(v -> {
            //  startTimer();
            openDialog_earncoin.cancel();
        });


        RelativeLayout video = openDialog_earncoin.findViewById(R.id.earnvideo);

        share_content = "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh";

        video.setOnClickListener(v -> {

            rvo = 1;
            extra_coin_s = 0;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                if (fb_reward == 1) {

                    reward_progressBar.dismiss();
                    show_reward();
                    openDialog_earncoin.cancel();

                    // mShowVideoButton.setVisibility(View.VISIBLE);
                } else {
                    fb_reward = 0;
                    rewarded_adnew();
                    new Handler(Looper.myLooper()).postDelayed(() -> {
                        reward_progressBar.dismiss();

                        Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                    }, 2000);


                }
            } else {

                Toast.makeText(context, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }

        });

        wp.setOnClickListener(view -> {
            if (isNetworkAvailable()) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.whatsapp");

                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivityForResult(Intent.createChooser(i, "Share via"), 21);


                } else {
                    Toast.makeText(getActivity(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });

        openDialog_earncoin.setOnDismissListener(dialog -> startTimer());
        openDialog_earncoin.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_installed;
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
        animator.addUpdateListener(animation -> textView.setText("" + (int) animation.getAnimatedValue()));
        animator.start();
    }

    private void coin_collection(int value) {

        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.share_dialog2);
        dialog.setCancelable(false);
        dialog.show();

        TextView ok_y = dialog.findViewById(R.id.ok_y);
        final TextView b_scores = dialog.findViewById(R.id.b_scores);


        try {
            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
            }
        } finally {
            if (coin_cursor != null) coin_cursor.close();
        }
        coin_point += (value);
        coin_earned_anim(b_scores, (value));

        myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

        ok_y.setOnClickListener(v -> {
            //  myDialog_class.media_player(getActivity(), R.raw.coin, "normal");
            dialog.dismiss();
            coin_txt.setText("" + coin_point);
            //   coin_txt.startAnimation(bounce_anim);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Winning_dialog != null && Winning_dialog.isShowing()) {
            Winning_dialog.dismiss();
        }
        rewardedAd = null;
        mInterstitialAd = null;
        handler = null;
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

                coin_collection(20);
            } else {
                // Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 15) {//g plus
            if (resultCode == -1) {
                coin_collection(10);
            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 21) {
            if (resultCode == -1) {
                coin_collection(20);
            }
        }

    }

    public void price_update() {
        ////////////////Prize//////////////////
        if (hints.size() != 0) {
            prize_data_update(context, 25);
        } else {
            prize_data_update(context, 75);
        }
        ////////////////Prize//////////////////
    }

    private void industrialload() {

        Utills.INSTANCE.initializeAdzz(getActivity());
        if (mInterstitialAd != null) return;

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(getActivity(), getResources().getString(R.string.Senthamil_Thedal_Ins), adRequest, new InterstitialAdLoadCallback() {
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
                winning_report(context, "time_up");
                industrialload();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                Log.e("TAG", "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
                handler = null;
                Utills.INSTANCE.Loading_Dialog_dismiss();
                sp.putInt(context, "Game3_Stage_Close_ST", 0);
                winning_report(context, "time_up");
            }

        });
    }

    public void adShow() {
        if (sp.getInt(context, "Game3_Stage_Close_ST") == Utills.interstitialadCount && mInterstitialAd != null) {
            sp.putInt(context, "Game3_Stage_Close_ST", 0);
            Utills.INSTANCE.Loading_Dialog(requireActivity());
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                mInterstitialAd.show(getActivity());
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sp.putInt(context, "Game3_Stage_Close_ST", (sp.getInt(context, "Game3_Stage_Close_ST") + 1));
            if (sp.getInt(context, "Game3_Stage_Close_ST") > Utills.interstitialadCount)
                sp.putInt(context, "Game3_Stage_Close_ST", 0);
            winning_report(context, "time_up");
            //Toast.makeText(context, "" + sp.getInt(context, "Game3_Stage_Close_ST"), Toast.LENGTH_SHORT).show();

        }

    }

    public void rewarded_adnew() {

        RewardedInterstitialAd.load(getActivity(), getResources().getString(R.string.Reward_Ins),
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
                Toast.makeText(context, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
            }
            fb_reward = 0;

        };

        if (rewardedAd != null) {
            rewardedAd.show(getActivity(), success);
            reward_status = 1;
        } else {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
        }
    }

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = openDialog.findViewById(R.id.ok_y);
        TextView b_scores = openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);

        /*
        final String aStringx = Integer.toString(spx);*/
        b_scores.setText("" + a);
        ok_y.setOnClickListener(v -> {

            openDialog.dismiss();
            //  //mCoinCount = 0;
        });
        if (!requireActivity().isFinishing()) openDialog.show();
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
            TextView ok_y = openDialog.findViewById(R.id.ok_y);
            TextView b_scores = openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            final int spx = skx + mCoinCount;
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            final String aStringx = Integer.toString(spx);


            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(v -> {
                coin_txt.setText("" + spx);
                openDialog.dismiss();
                // //mCoinCount = 0;
            });

            if (!requireActivity().isFinishing()) openDialog.show();
        }

    }


}
