package nithra.tamil.word.game.solliadi.word_search_game.Models.general;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native_Senthamil_Thedal_Native_Banner;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.facebook.ads.NativeAdLayout;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import nithra.tamil.word.game.solliadi.DataBaseHelper;
import nithra.tamil.word.game.solliadi.LoginActivity;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper2;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper3;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Models.Direction;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Models.Word;
import nithra.tamil.word.game.solliadi.word_search_game.Models.extra.MyTableView;
import nithra.tamil.word.game.solliadi.word_search_game.Models.extra.TinyDB;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.game_sub_level_page;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.LikeButton;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnAnimationEndListener;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnLikeListener;


public class WordsearchGridFragment extends Fragment implements WordsearchGridView.OnWordSelectedListener, OnLikeListener, OnAnimationEndListener {

    public static RelativeLayout lay_wordsearch;

    public static Set<Word> mSolution = new HashSet<Word>();
    //private static String[] mWordList={"கு.ர.ங்.கு","மு.ய.ல்","ஒ.ட்.ட.க.ச்.சி.வி.ங்.கி","சி.று.த்.தை.ப்.பு.லி","மு.த.லை","வ.ரி.க்.கு.தி.ரை","கு.ள்.ள.ந.ரி"};
    public static Set<Word> mFoundWords = new HashSet<Word>();
    // வ,ள ,ம்$ந,ல,ம்$நி,ற,ம்$த,ர, ம்$உ,த்,த,ர,வு$வ,ழ,க்,க,ம்$எ,ளி,தா,க$அ,ள,வு$ந,ம,து$நா,டு
    public static Set<Word> mFoundWords_alter = new HashSet<Word>();
    public static int levelId, gridcount;
    public static ArrayList<String> hints = new ArrayList<>();
    public static ArrayList<String> find_word = new ArrayList<>();
    public static Chronometer chronometer;
    public static long timeWhenStopped = 0;
    static int rvo = 0;
    private static String[] mWordList = {"வ.ள.ம்", "மு.ய.ல்", "ந.ல.ம்", "நி.ற.ம்", "மு.த.லை", "ந.ம.து", "அ.ள.வு"};
    static private int mCoinCount = 20;
    private final Direction[] mDirections = Direction.values();
    // Facebook variable starts
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    Dialog my_my_dialog = null;
    Dialog openDialog_earncoin;
    String table_name = "", level_category = "", level_id = "", dont_call = "";
    String btn_str = "";
    Word hint_word = null;
    boolean onstop = false;
    ArrayList<String> my_solution = new ArrayList<>();
    WordsearchGridView wordsearchGridView;
    Animation bounce_anim = null;
    MyTableView myTableView;
    RecyclerView recyclerView;
    SharedPreference sp = new SharedPreference();
    RelativeLayout toptool, hintview, coin_lay;
    double diagonalInches;
    Context context;
    Vibrator vibrate;
    ImageView general_setting;
    TextView counts, coin_txt, screatch_txt, level_txt, level_name_txt;
    String meanning_words = "";
    int credits_coin = 0, coin_point = 0, goback = 0;
    List<String> elephantList = new ArrayList<>();
    SQLiteDatabase mydb, Inner_mydb, exdb;
    Cursor cursor = null, coin_cursor = null;
    int extra_coin_s = 0;
    ImageView icon_ad_img;
    LinearLayout n_icon_ad;
    // CallbackManager callbackManager;
    // AppInviteDialog mInvititeDialog;
    FrameLayout Baner_frame;
    LinearLayout normal_baner;
    my_dialog myDialog_class = new my_dialog();
    String showview = "";
    ArrayList<String> showcase_shap;
    ArrayList<View> showcase_id = new ArrayList<View>();
    ArrayList<String> showcase_content = new ArrayList<String>();
    String share_content = "";
    FirebaseAnalytics mFirebaseAnalytics;
    String call_onstop = "";
    int fb_reward = 0;
    int reward_status = 0;
    String reward_video = "";
    /*
        private UiLifecycleHelper uiHelper;
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
        };
        // facebook variable ends

    */
    //reward videos process 1***********************
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    SQLiteDatabase dbs, dbn, dbn2;
    private int mRows;
    private int mColumns;
    private boolean[][] mLock;
    private int[] mRandomIndexes;
    //*********************reward videos process 1***********************
    private String[][] mBoard;
    private TinyDB tinyDB;
    private RecyclerAdapter mWordAdapter;
    //com.facebook.ads.RewardedVideoAd rewardedVideoAd;
    private MaxRewardedAd rewardedAd;
    private boolean mGameOver;
    private boolean mGamePaused;
    private long mTimeRemaining;

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

    public void no_tool() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getString(getActivity(), "ws_general_intro").equals("")) {
                    System.out.println("no_tool ------------no_tool -------");
                    getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    //   Native_icon_ad.load_add(getActivity(), normal_baner);
                    //myDialog_class.WST_Native_IconAd(getActivity(), icon_ad_img, n_icon_ad);
                    //   myDialog_class.WST_Native_BannerAd(getActivity(), Baner_frame);
                } else {
                    no_tool();
                }


                System.out.println("no_tool ------------");

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
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                } else {
                    no_setting();
                }
            }
        }, 1000);
    }

    public void showcase_dismiss() {
        Handler handler30 = new Handler();
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sp.getString(context, "ws_general_showcase_intro").equals("")) {
                    showcase_dismiss();
                } else {
                    startTimer();
                    sp.putString(getActivity(), "ws_general_intro", "no");
                }

            }
        }, 800);
    }

    public void startTimer() {
        sp.putString(getActivity(), "ws_general_intro", "no");
        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        chronometer.start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wordsearch_view, null);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*if (!sp.getString(getActivity(), "ws_general_intro").equals("")) {
        } else {
            no_tool();
        }*/

        context = getActivity();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                call_onstop = "call_onstop";
            }
        }, 4000);

        if (my_dialog.isNetworkAvailable(getActivity())) {
            if (sp.getInt(getActivity(), "purchase_ads") == 1) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            } else {
                //fb_addload_score_screen(getActivity());

            }

            //reward();
            rewarded_ad();
        }

       /* getCountryName(getActivity(), 36.82199703, 5.76600356);

        System.out.println("locale========= " + getCountryName(getActivity(), 36.82199703, 5.76600356));
*/


//reward videos process 2***********************

        mydb = getActivity().openOrCreateDatabase("WS_tamil.db", getActivity().MODE_PRIVATE, null);
        Inner_mydb = getActivity().openOrCreateDatabase("Inner_DB", 0, null);

        exdb = getActivity().openOrCreateDatabase("Solli_Adi", getActivity().MODE_PRIVATE, null);

        ImageView prize_logo = (ImageView) view.findViewById(R.id.prize_logo);
        if (sp.getInt(getActivity(), "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getActivity())) {
                    if (sp.getString(getActivity(), "price_registration").equals("com")) {
                        getActivity().finish();
                        Intent i = new Intent(getActivity(), Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sp.getString(getActivity(), "otp_verify").equals("yes")) {
                            getActivity().finish();
                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            startActivity(i);
                        } else {
                            getActivity().finish();
                            Intent i = new Intent(getActivity(), Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myDbHelper = new DataBaseHelper(getActivity());
        newhelper = new Newgame_DataBaseHelper(getActivity());
        newhelper2 = new Newgame_DataBaseHelper2(getActivity());
        newhelper3 = new Newgame_DataBaseHelper3(getActivity());

       /* exdb = myDbHelper.getReadableDatabase();
        dbs = newhelper.getReadableDatabase();
        dbn = newhelper2.getReadableDatabase();
        dbn2 = newhelper3.getReadableDatabase();*/

        tinyDB = new TinyDB(getActivity());


        //uiHelper = new UiLifecycleHelper(getActivity(), callback);

        // bounce_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_anim);

        general_setting = (ImageView) view.findViewById(R.id.general_setting);
        general_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String snd = sp.getString(getActivity(), "snd");
                if (snd.equals("off")) {
                    sp.putString(getActivity(), "snd", "on");
                    general_setting.setBackgroundResource(R.drawable.sound_on);
                } else if (snd.equals("on")) {
                    sp.putString(getActivity(), "snd", "off");
                    general_setting.setBackgroundResource(R.drawable.sound_off);
                }
               /* myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                sp.putString(getActivity(), "no_setting", "setting");
                no_setting();*/
                //  myDialog_class.setting(getActivity()).show();
            }
        });

        normal_baner = (LinearLayout) view.findViewById(R.id.normal_baner);
        Baner_frame = (FrameLayout) view.findViewById(R.id.Baner_frame);
        icon_ad_img = (ImageView) view.findViewById(R.id.icon_ad_img);
        n_icon_ad = (LinearLayout) view.findViewById(R.id.n_icon_ad);
        n_icon_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //   myDialog_class.WST_Native_IconAd_show();
            }
        });


        //  cursor = mydb.rawQuery("select * from ws_tamil where size='" + sp.getInt(getActivity(), "WORD") + "' and gameid='"+sp.getInt(getActivity(),"LevelID")+"'", null);

        table_name = sp.getString(getActivity(), "table_name");
        level_category = sp.getString(getActivity(), "level_category");
        level_id = sp.getString(getActivity(), "sub_level_category");

        System.out.println("===========" + sp.getString(getActivity(), "" + level_category + "_" + level_id));

        System.out.println("333333333 : " + level_category);
//-47029

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

        vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds

        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        coin_txt = (TextView) view.findViewById(R.id.coin_txt);
        counts = (TextView) view.findViewById(R.id.counts);
        screatch_txt = (TextView) view.findViewById(R.id.screatch_txt);
        level_txt = (TextView) view.findViewById(R.id.level_txt);
        level_name_txt = (TextView) view.findViewById(R.id.level_name_txt);
        coin_lay = (RelativeLayout) view.findViewById(R.id.coin_lay);

        coin_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                more_coin("no");
            }
        });


        level_txt.setText(level_id);
        level_name_txt.setText(level_category);

    /*    try {
            coin_cursor = Inner_mydb.rawQuery("select * from coin_table", null);
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coin_point"));
                coin_txt.setText("" + coin_point);
//                coin_txt.startAnimation(bounce_anim);
            }
        } finally {
            if (coin_cursor != null)
                coin_cursor.close();
        }   */
        ////////////////////////////////////////////////////////////////////////////

        try {
            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
                coin_txt.setText("" + coin_point);
//                coin_txt.startAnimation(bounce_anim);
            }
        } finally {
            if (coin_cursor != null) coin_cursor.close();
        }


        //return LayoutInflater.from(getActivity()).inflate(R.layout.wordsearch_view, null);
////////////////////////////////////////////////////////////////////////////////FB//////////////////////////////////////////////////////////////////
        /*loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");*/

      /*  callbackManager = CallbackManager.Factory.create();

        mInvititeDialog = new AppInviteDialog(getActivity());

        mInvititeDialog = new AppInviteDialog(this);
        // Other app specific specialization
        // Callback registration

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code

                        LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile"));
                        String appLinkUrl, previewImageUrl;
                        //   Toast.makeText(New_Main_Activity.this, "Success2", Toast.LENGTH_SHORT).show();
                        appLinkUrl = "https://www.mydomain.com/myapplink";
                        //  previewImageUrl = "https://www.mydomain.com/my_invite_image.jpg";

                        if (AppInviteDialog.canShow()) {
                            AppInviteContent content = new AppInviteContent.Builder()
                                    .setApplinkUrl(appLinkUrl)
                                    //  .setPreviewImageUrl(previewImageUrl)
                                    .build();
                            AppInviteDialog.show(getActivity(), content);
                        }
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });


        LoginManager.getInstance().retrieveLoginStatus(getActivity(), new LoginStatusCallback() {
            @Override
            public void onCompleted(AccessToken accessToken) {
                // User was previously logged in, can log them in directly here.
                // If this callback is called, a notification is shown that says
                // "Logged in as <User Name>"

                String appLinkUrl, previewImageUrl;
                // Toast.makeText(New_Main_Activity.this, "Success", Toast.LENGTH_SHORT).show();
                appLinkUrl = "https://www.mydomain.com/myapplink";
                // previewImageUrl = "https://www.mydomain.com/my_invite_image.jpg";

                if (AppInviteDialog.canShow()) {
                    AppInviteContent content = new AppInviteContent.Builder()
                            .setApplinkUrl(appLinkUrl)
                            // .setPreviewImageUrl(previewImageUrl)
                            .build();
                    AppInviteDialog.show(getActivity(), content);
                }
            }

            @Override
            public void onFailure() {
                // No access token could be retrieved for the user
            }

            @Override
            public void onError(Exception exception) {
                // An error occurred
            }
        });

        FacebookSdk.sdkInitialize(getActivity());
        Uri targetUrl =
                AppLinks.getTargetUrlFromInboundIntent(getActivity(), getActivity().getIntent());
        if (targetUrl != null) {
            Log.i("Activity", "App Link Target URL: " + targetUrl.toString());
        } else {
        *//*    AppLinkData.fetchDeferredAppLinkData(getActivity(),
                    new AppLinkData.CompletionHandler() {
                        @Override
                        public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                            //process applink data
                        }
                    });*//*
        }


        mInvititeDialog.registerCallback(callbackManager,
                new FacebookCallback<AppInviteDialog.Result>() {

                    @Override
                    public void onSuccess(AppInviteDialog.Result result) {
                        System.out.println("#########success" + result);
                        Toast.makeText(getActivity(), "success" + result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getActivity(), "canceled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getActivity(), "error" + error, Toast.LENGTH_SHORT).show();
                    }
                });
*/
////////////////////////////////////////////////////////////////////////////////FB//////////////////////////////////////////////////////////////////

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("me");
        context = getActivity();

        lay_wordsearch = (RelativeLayout) view.findViewById(R.id.lay_wordsearch);

        wordsearchGridView = (WordsearchGridView) view.findViewById(R.id.grd_wordsearch);
        myTableView = (MyTableView) view.findViewById(R.id.mytab);
        recyclerView = (RecyclerView) view.findViewById(R.id.grd_word_list);


        toptool = (RelativeLayout) view.findViewById(R.id.toptool);
        hintview = (RelativeLayout) view.findViewById(R.id.hintview);


        if (sp.getInt(getContext(), "Grid") == 1) {

            myTableView.setVisibility(View.VISIBLE);
        }

        levelId = sp.getInt(getContext(), "LEVEL");
        gridcount = sp.getInt(getContext(), "GRID");


        String snd = sp.getString(getActivity(), "snd");

        if (snd.equals("off")) {
            general_setting.setBackgroundResource(R.drawable.sound_off);
        } else {
            general_setting.setBackgroundResource(R.drawable.sound_on);
        }

     /*   if (sp.getString(getActivity(), "ws_general_intro").equals("")) {
            //showcase_view(mp_img);

            showview = "ws_general_intro";

            showcase_shap = new ArrayList<>(Arrays.asList("rect", "circle", "rect"));

            showcase_content.add("செயலியை பகிர்ந்து கூடுதல் நாணயம் பெறலாம் ");
            showcase_content.add("விளையாட்டின் செயல்களை உங்கள் கட்டுப்பாட்டில் அமைக்க ");

            if (table_name.equals("general")) {
                showcase_content.add("குறிப்பு பார்க்க மற்றும் இந்த சொல்லிற்கான வாக்கியத்தைப்  பார்க்க , சொல்லை சொடுக்கவும்");
            } else {
                showcase_content.add("குறிப்பு பார்க்க இந்த சொல்லை சொடுக்கவும்");

            }


            showcase_id.add(coin_lay);
            showcase_id.add(general_setting);
            showcase_id.add(recyclerView);


            myDialog_class.showcase_view(getActivity(), showview, showcase_id, showcase_shap, showcase_content);

        }*/


        if (sp.getString(getActivity(), "ws_general_intro").equals("")) {
            //Toast.makeText(context, "ws_challenge_intro", Toast.LENGTH_SHORT).show();
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity(), "sequence example ws_general_intro");

            sequence.setConfig(config);

            sequence.addSequenceItem(coin_lay, "செயலியை பகிர்ந்து கூடுதல் நாணயம் பெறலாம்.", "அடுத்து");

            // sequence.addSequenceItem(general_setting, "விளையாட்டின் செயல்களை உங்கள் கட்டுப்பாட்டில் அமைக்க ", "அடுத்து");
            String data = "";
            if (table_name.equals("general")) {
                data = "குறிப்பு பார்க்க மற்றும் இந்த சொல்லிற்கான வாக்கியத்தைப்  பார்க்க , சொல்லை சொடுக்கவும்";
            } else {
                data = "குறிப்பு பார்க்க இந்த சொல்லை சொடுக்கவும்";


            }
            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");
            sequence.addSequenceItem(new MaterialShowcaseView.Builder(getActivity()).setTarget(recyclerView).setDismissText("சரி").setContentText(data).build()).setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                @Override
                public void onDismiss(MaterialShowcaseView itemView, int position) {
                    if (position == 1) {
                        sp.putString(getActivity(), "ws_general_intro", "no");
                        sp.putString(getActivity(), "ws_general_showcase_intro", "yes");
                        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                        chronometer.start();

                    }
                }
            });


            sequence.start();

        }
    }

    public void reward_fun() {
        //mCoinCount = 0;
        if (Utils.isNetworkAvailable(getActivity())) {


        } else {

            Toast.makeText(getActivity(), "இணையதள சேவையை சரிபார்க்கவும்..", Toast.LENGTH_SHORT).show();
        }
    }





    /*  @Override
    public void onStop() {
        super.onStop();

        myDialog_class.media_player(getActivity(), R.raw.coin, "stop");

        System.out.println("======check onStop() dont_call : " + dont_call);

        if (isNetworkAvailable())
        {
            if (!call_onstop.equals(""))
            {
                WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                WordsearchGridFragment.chronometer.stop();
                sp.putString(getActivity(), "" + level_category + "_" + level_id, "" + timeWhenStopped);
            }

        }else {
            WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
            WordsearchGridFragment.chronometer.stop();
            sp.putString(getActivity(), "" + level_category + "_" + level_id, "" + timeWhenStopped);
        }

    }*/


    //*********************reward videos process 3***********************

    private void addCoins(int coins) {
        mCoinCount = coins;
        sp.putInt(getActivity(), "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }

    @Override
    public void onPause() {
        super.onPause();

        reward_video = "";

        //******reward video pocess :4

        //reward video pocess :4 ************//

        //myDialog_class.media_player(getActivity(), R.raw.coin, "stop");

        onstop = true;

        System.out.println("======check onStop() dont_call : " + dont_call);

        if (isNetworkAvailable()) {
            if (!call_onstop.equals("")) {
                WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                WordsearchGridFragment.chronometer.stop();
                sp.putString(getActivity(), "" + level_category + "_" + level_id, "" + timeWhenStopped);
            }

        } else {
            WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
            WordsearchGridFragment.chronometer.stop();
            sp.putString(getActivity(), "" + level_category + "_" + level_id, "" + timeWhenStopped);
        }
    }


    //reward videos***********************//

    @Override
    public void onResume() {
        super.onResume();

        //******reward video pocess :4
        //loadRewardedVideoAd();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        mFirebaseAnalytics.setCurrentScreen(getActivity(), "Word Search General", null);

        if (!mGameOver && mGamePaused) {

        }

        //reward video pocess :4 ************//

        if (!sp.getString(getActivity(), "ws_general_intro").equals("")) {
            // Native_icon_ad.load_add(getActivity(), normal_baner);
            //myDialog_class.WST_Native_IconAd(getActivity(), icon_ad_img, n_icon_ad);
            //    myDialog_class.WST_Native_BannerAd(getActivity(), Baner_frame);
        }

        timeWhenStopped = 0;

        if (!sp.getString(getActivity(), "" + level_category + "_" + level_id).equals("")) {
            System.out.println("===========" + sp.getString(getActivity(), "" + level_category + "_" + level_id));
            timeWhenStopped = Long.parseLong(sp.getString(getActivity(), "" + level_category + "_" + level_id));

            if (!onstop) {
                if (mFoundWords.size() <= 0) {
                    timeWhenStopped = 0;
                }
            }

        }

        System.out.println("======check dont_call : " + dont_call);
        System.out.println("======check ws_general_intro : " + sp.getString(getActivity(), "ws_general_intro"));
        if (dont_call.equals("")) {
            if (!sp.getString(getActivity(), "ws_general_intro").equals("")) {
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
            }
        }


        if (sp.getInt(getActivity(), "purchase_ads") == 1) {
            normal_baner.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            NativeAdLayout native_banner_ad_container = (NativeAdLayout) getActivity().findViewById(R.id.native_banner_ad_container);
            if (Utils.isNetworkAvailable(getActivity())) {
                fb_native_Senthamil_Thedal_Native_Banner(getActivity(), native_banner_ad_container);
                /* if (sp.getInt(getActivity(),"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(getActivity(),native_banner_ad_container);
                }else {
                    fb_native(getActivity(),native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

         /*   if (sp.getInt(getActivity(), "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(getActivity(), normal_baner);
            } else {
                if (Utils.isNetworkAvailable(getActivity())) {
                    sp.putInt(getActivity(), "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(getActivity());
                    adView.setAdUnitId(getString(R.string.main_banner_ori));

                    adView.setAdSize(AdSize.SMART_BANNER);
                    AdRequest request = new AdRequest.Builder().build();
                    adView.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            System.out.println("@@@loaded");
                            normal_baner.removeAllViews();
                            normal_baner.addView(adView);
                            normal_baner.setVisibility(View.VISIBLE);
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
        }

    }

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
            screatch_txt.setVisibility(View.VISIBLE);
            StringBuilder myscreatch = new StringBuilder();
            for (int i = 0; i < positions.size(); i++) {
                int row = positions.get(i) / mColumns;
                int col = positions.get(i) % mColumns;
                String c = mBoard[row][col];
                myscreatch.append(c);
            }

            screatch_txt.setText(myscreatch.toString().toUpperCase());
        }

        if (WordsearchGridView.clearSelection == 1) {

            WordsearchGridView.clearSelection = 0;
            screatch_txt.setVisibility(View.INVISIBLE);

            for (Word word : mSolution) {

                int wordStart = (word.getRow() * mColumns) + word.getCol();

              /*  if (wordStart != firstPos && wordStart != lastPos) {
                    if (word.getWord().toLowerCase().equals(forwardWord.toString().toLowerCase())

                            || word.getWord().toLowerCase().equals(reverseWord.toString().toLowerCase())) {

                        Toast.makeText(getContext(), "Word is located in another position", Toast.LENGTH_SHORT).show();
                    }
                    continue;
                } */

                if (wordStart != firstPos && wordStart != lastPos) {
                    if (word.getWord().replace(".", "").equals(forwardWord.toString())

                            || word.getWord().replace(".", "").equals(reverseWord.toString())) {

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
                    if (!find_word.contains(found.getWord())) {
                        myDialog_class.media_player(getActivity(), R.raw.scretch_done, "normal");
                    }
                    if (sp.getInt(getActivity(), "Vibrate") == 1) {
                        // vibrate.vibrate(100);
                    }

                    screatch_txt.setVisibility(View.VISIBLE);
                    screatch_txt.setText(found.getWord().replace(".", ""));

                    mFoundWords.add(found);
                    find_word.add(found.getWord());
                    wordsearchGridView.clearHint();
                    wordsearchGridView.wordFound(found);
                    mWordAdapter.setWordFound(found);
                    counts.setText("" + mFoundWords.size() + "/" + mWordAdapter.getItemCount());

                    tinyDB.putListObject("solutions" + level_category + level_id, new ArrayList<>(mSolution));
                    tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));

                    break;
                } else {
                    myDialog_class.media_player(getActivity(), R.raw.scretch_fail, "normal");
                }
            }
        }

        //Flag to avoid multi drag for last word

        if (mFoundWords.size() == mSolution.size()) {
            winning_report();
        }
    }

    private void onPuzzleComplete() {
        System.out.println("generate== ");
        resetBoard();
        generateBoard();
        initBoard();

        if (sp.getInt(context, "Grid") == 1) {

            myTableView.setVisibility(View.VISIBLE);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                recyclerView.setVisibility(View.VISIBLE);
                wordsearchGridView.setVisibility(View.VISIBLE);
               /* AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500);
                wordsearchGridView.startAnimation(anim);*/
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

        mColumns = wordsearchGridView.getNumColumns();
        mRows = wordsearchGridView.getNumRows();

        mRows = sp.getInt(getContext(), "GRID");
        mColumns = sp.getInt(getContext(), "GRID");

        System.out.println("88888888888888888888 GRID : " + sp.getInt(getContext(), "GRID"));

        mBoard = new String[mRows][mColumns];
        mLock = new boolean[mRows][mColumns];

        int version_code = 0;

        try {
            PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            String version = pInfo.versionName;
            version_code = pInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("----fff sp==app_version " + sp.getInt(getActivity(), "app_version"));
        System.out.println("----fff version_code " + version_code);
        System.out.println("----fff sp======alter " + sp.getString(getActivity(), "alter"));

        if (sp.getString(getActivity(), "alter").equals("done")) {
            if (sp.getInt(getActivity(), "app_version") == version_code) {

                System.out.println("----fff tinyDB==found.size() " + tinyDB.getListObject("found" + level_category + level_id, Word.class).size());

                if (tinyDB.getListObject("found" + level_category + level_id, Word.class).size() != 0) {
                    {
                        List<Word> words = tinyDB.getListObject("found" + level_category + level_id, Word.class);
                        mFoundWords = new HashSet<Word>(words);
                        tinyDB.putListObject("mFoundWords_alter", new ArrayList<>(mFoundWords));

                        List<Word> alter_words = tinyDB.getListObject("mFoundWords_alter", Word.class);
                        mFoundWords_alter = new HashSet<Word>(alter_words);

                        mFoundWords.clear();
                        tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));
                        System.out.println("----fff mFoundWords_alter.size() " + mFoundWords_alter.size());
                    }
                }
            }
        }

        resetBoard();

        System.out.println("----fff start ");

        try {
            System.out.println("----fff start1 " + tinyDB.getListObject("found" + level_category + level_id, Word.class).size());
        } catch (Exception e) {
            System.out.println("catch_print== " + e.getMessage());
        }

        if (tinyDB.getListObject("found" + level_category + level_id, Word.class).size() == 0) {
            System.out.println("----fff sp======if generateBoard() ");
            generateBoard();
        } else {

            System.out.println("----fff sp======else found avilable");


            List<Word> words = tinyDB.getListObject("solutions" + level_category + level_id, Word.class);

            for (Word word : words) {
                placeWord(word);
                System.out.println("Size word:" + word);
            }

            words = tinyDB.getListObject("found" + level_category + level_id, Word.class);
            mFoundWords = new HashSet<Word>(words);
            wordsearchGridView.wordMaintain(mFoundWords);
        }


        wordsearchGridView.setOnWordSelectedListener(this);

        initBoard();

        recyclerView.setVisibility(View.VISIBLE);
        wordsearchGridView.setVisibility(View.VISIBLE);

        if (mFoundWords.size() > 0) {

            for (Word found : mFoundWords)
                mWordAdapter.setWordFound(found);
        }
    }

    private void initBoard() {
        wordsearchGridView.setBoard(mBoard);


        List<Word> sortedWords = new ArrayList<Word>(mSolution);
        //Collections.sort(sortedWords);
        mWordAdapter = new RecyclerAdapter(getActivity(), sortedWords);
        mWordAdapter.setWordsFound(mFoundWords);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mWordAdapter);
        recyclerView.setEnabled(false);
        recyclerView.setFocusable(false);
        counts.setText(mFoundWords.size() + "/" + mWordAdapter.getItemCount());

        if (sp.getString(getActivity(), "alter").equals("done")) {

            for (Word word1 : mSolution) {

                String current_sollution = word1.getWord();

                System.out.println("----fff sp====== current_sollution " + current_sollution);
                System.out.println("----fff sp====== mFoundWords_alter.size()" + mFoundWords_alter.size());

                if (mFoundWords_alter.size() != 0) {
                    for (Word word2 : mFoundWords_alter) {

                        System.out.println("----fff sp====== alter word2.getWord() " + word2.getWord());
                        if (current_sollution.equals(word2.getWord())) {
                            mFoundWords.add(word1);
                            break;
                        }
                    }
                }
            }

            wordsearchGridView.wordMaintain(mFoundWords);
            counts.setText("" + mFoundWords.size() + "/" + mWordAdapter.getItemCount());

            if (mFoundWords.size() == mSolution.size()) {
                winning_report();
            }
        }
        System.out.println("----fff===== mFoundWords mFoundWords.size() " + mFoundWords.size());


           /* if (mFoundWords_alter.contains(word)) {
                mWordAdapter.setWordFound(word);
                mFoundWords.add(word);

                System.out.println("----fff word " + word);
            }*/

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

       /* outState.putParcelableArrayList("solution", new ArrayList<Word>(mSolution));
        outState.putParcelableArrayList("found", new ArrayList<Word>(mFoundWords));*/
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

        if (table_name.equals("")) {
            table_name = sp.getString(getActivity(), "table_name");
            level_category = sp.getString(getActivity(), "level_category");
            level_id = sp.getString(getActivity(), "sub_level_category");
        }


        cursor = mydb.rawQuery("select * from '" + table_name + "' where Category='" + level_category + "' and Level='" + level_id + "'", null);


        System.out.println("333333333 : " + "select * from category where Category='" + level_category + "' and Catid='" + level_id + "'");
        System.out.println("cur_cnt== " + cursor.getCount());

        if (cursor.getCount() != 0) {
            System.out.println("aaaa###");
            mSolution.clear();
            my_solution.clear();

            cursor.moveToFirst();
            String value = cursor.getString(cursor.getColumnIndexOrThrow("Words"));

            elephantList = Arrays.asList(value.split(","));

            System.out.println("88888888888888888888 elephantList : " + elephantList);

            mWordList = new String[elephantList.size()];

            for (int i = 0; i < elephantList.size(); i++) {
                mWordList[i] = elephantList.get(i);
            }
        }


        for (String word : mWordList) {

            System.out.println("88888888888888888888 word : " + word);
            addWord(word);
        }

    }

    private RecyclerView getWordListGridView() {
        return (RecyclerView) getView().findViewById(R.id.grd_word_list);
    }

    private WordsearchGridView getGridView() {
        return (WordsearchGridView) getView().findViewById(R.id.grd_wordsearch);
    }

    /**
     * Attempts to add a word to the puzzle anywhere possible.
     *
     * @param word The word added
     * @return The Word object representing this words position in the puzzle.
     * Null if the word could not be fit into the puzzle
     */
    private Word addWord(String word) {

        String[] str = word.split("\\.");




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

            my_solution.add(word.getWord().replace(".", ""));


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

        screatch_txt.setText("");
        mFoundWords.clear();
        mSolution.clear();
        my_solution.clear();
        find_word.clear();
        hints.clear();
        wordsearchGridView.reset();

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


       /* Random rand = new Random(System.currentTimeMillis());
        for (int i = mWordList.length - 1; i >= 1; i--) {
            int randIndex = rand.nextInt(i);
            String word = mWordList[i];
            mWordList[i] = mWordList[randIndex];
            mWordList[randIndex] = word;
        }*/

    }

    public void dia_dismiss(Dialog dialog) {
        dialog.dismiss();
    }

    public void winning_report() {
        //   myDialog_class.media_player(getActivity(), R.raw.winning, "normal");
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_winning_report);
        //   dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
        dialog.show();

        TextView video_earn = (TextView) dialog.findViewById(R.id.video_earn);
        video_earn.setText("மேலும் " + sp.getInt(getActivity(), "reward_coin_txt") + "+நாணயங்கள் பெற");

        LinearLayout vid_earn = (LinearLayout) dialog.findViewById(R.id.vid_earn);

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
                        rewardedAd.showAd();


                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                        //reward();
                        rewarded_ad();
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

        if (hints.size() != mSolution.size()) {
            price_update();
        }


        my_my_dialog = dialog;
        goback = 0;
        credits_coin = 0;

        my_my_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (goback == 0) {
                    Intent intent = new Intent(getActivity(), game_sub_level_page.class);
                    getActivity().finish();
                    startActivity(intent);
                }
            }
        });

        TextView stage_txt = (TextView) dialog.findViewById(R.id.stage_txt);
        TextView continue_txt = (TextView) dialog.findViewById(R.id.continue_txt);
        TextView best_score = (TextView) dialog.findViewById(R.id.best_score);
        TextView current_score = (TextView) dialog.findViewById(R.id.current_score);
        TextView win_coin = (TextView) dialog.findViewById(R.id.win_coin);
        TextView credit_coin = (TextView) dialog.findViewById(R.id.credit_coin);
        RelativeLayout retry_game = (RelativeLayout) dialog.findViewById(R.id.retry_game);
        final RelativeLayout next_game = (RelativeLayout) dialog.findViewById(R.id.next_game);

        final LikeButton like_retry_game = (LikeButton) dialog.findViewById(R.id.like_retry_game);
        final LikeButton like_continue_img = (LikeButton) dialog.findViewById(R.id.like_continue_img);

        TextView best_valthu_txt = (TextView) dialog.findViewById(R.id.best_valthu_txt);
        ArrayList<String> best_word = new ArrayList<>(Arrays.asList("மிக சிறப்பு", "வாழ்த்துக்கள்", "அருமை", "அற்புதம்", "தமிழ் வாழ்க", "தமிழன்"));
        String bestword = best_word.get(new Random().nextInt(best_word.size()));
        best_valthu_txt.setText("" + bestword);


        LinearLayout fl_adplaceholder = (LinearLayout) dialog.findViewById(R.id.fl_adplaceholder);
        if (sp.getInt(getActivity(), "purchase_ads") == 1) {
            fl_adplaceholder.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(context)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(context, fl_adplaceholder);
            } else {
                fl_adplaceholder.setVisibility(View.GONE);
            }
            // load_addinstall(getActivity(), fl_adplaceholder);
            // New_Main_Activity.load_addFromMain_multiplayer(getActivity(), fl_adplaceholder);
          /*  if (Utils.isNetworkAvailable(getActivity())){
                //New_Main_Activity.load_add_fb_rect_score_screen(getActivity(), fl_adplaceholder);
            }else {
                fl_adplaceholder.setVisibility(View.GONE);
            }*/
           /* NativeAdLayout native_banner_ad_container = (NativeAdLayout)getActivity().findViewById(R.id.native_banner_ad_container);
            if (Utils.isNetworkAvailable(getActivity())){
                if (sp.getInt(getActivity(),"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(getActivity(),native_banner_ad_container);
                }else {
                    fb_native(getActivity(),native_banner_ad_container);
                }
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }*/
        }

        if (table_name.equals("general")) {


            Cursor meanning_word_cur = null;

            meanning_words = "";
            TextView win_meaning_txt = (TextView) dialog.findViewById(R.id.win_meaning_txt);
            win_meaning_txt.setVisibility(View.VISIBLE);


            win_meaning_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   myDialog_class.media_player(getActivity(), R.raw.winning, "normal");
                    final Dialog meaning_dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    meaning_dialog.setContentView(R.layout.dia_meaning);

                    TextView meaning_cancel_txt = (TextView) meaning_dialog.findViewById(R.id.meaning_cancel_txt);
                    TextView meaning_txt = (TextView) meaning_dialog.findViewById(R.id.meaning_txt);
                    FrameLayout Baner_frame_mean = (FrameLayout) meaning_dialog.findViewById(R.id.Baner_frame_mean);

                    //    myDialog_class.WST_Native_BannerAd(getActivity(), Baner_frame_mean);

                    System.out.println("------ii : " + meanning_words);

                    meaning_txt.setText(Html.fromHtml(meanning_words));
                    meaning_txt.setMovementMethod(new ScrollingMovementMethod());

                    meaning_cancel_txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            meaning_dialog.dismiss();
                        }
                    });
                    meaning_dialog.show();
                }
            });

            try {
                for (int i = 0; i < my_solution.size(); i++) {
                    meanning_word_cur = mydb.rawQuery("select * from general_meaning where Word='" + my_solution.get(i) + "'", null);
                    if (meanning_word_cur.getCount() != 0) {
                        meanning_word_cur.moveToFirst();

                        // String sentence=my_solution.get(i)+" - " +meanning_word_cur.getString(meanning_word_cur.getColumnIndexOrThrow("Sentence"));
                        String sentence = "" + (i + 1) + ". " + meanning_word_cur.getString(meanning_word_cur.getColumnIndexOrThrow("Sentence"));

                        sentence = sentence.replace(my_solution.get(i), "<font color='#ffea00'>" + my_solution.get(i) + "</font>") + "<br><br>";

                        meanning_words += sentence;

                    }
                }
            } finally {
                if (meanning_word_cur != null) meanning_word_cur.close();
            }


        }

        retry_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                goback = 1;

                mFoundWords.clear();
                mSolution.clear();
                my_solution.clear();
                find_word.clear();
                hints.clear();


                mFoundWords.clear();
                tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));

                // Inner_mydb.execSQL("UPDATE '"+table_name+"' SET game_finish='" + 2 + "' where id='" + level_id + "' and game_cate='" + level_category + "'");


                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("game_finish", 2);
                Inner_mydb.update(table_name, contentValues12, "id='" + level_id + "' and game_cate='" + level_category + "'", null);

                tinyDB.putListObject("solutions" + level_category + level_id, new ArrayList<>(mSolution));
                tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));

                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime());
                WordsearchGridFragment.chronometer.start();
                coin_txt.setText("" + coin_point);
                //coin_txt.startAnimation(bounce_anim);
                onPuzzleComplete();

                dialog.dismiss();
            }
        });

        next_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                try {
                    coin_cursor = myDbHelper.getQry("select * from score");
                    if (coin_cursor.getCount() != 0) {
                        coin_cursor.moveToFirst();
                        coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
                        coin_txt.setText("" + coin_point);
//                coin_txt.startAnimation(bounce_anim);
                    }
                } finally {
                    if (coin_cursor != null) coin_cursor.close();
                }

                if (goback == 2) {
                    congreate_finish();
                    my_my_dialog.dismiss();
                } else {
                    goback = 1;

                    int level_id1 = Integer.parseInt(level_id);

                    if (game_sub_level_page.game_stage > level_id1) {
                        level_id1 += 1;
                        level_id = "" + level_id1;
                        level_txt.setText(level_id);

                        sp.putString(getActivity(), "sub_level_category", "" + level_id1);
                        onPuzzleComplete();
                    }
                    tinyDB.putListObject("solutions" + level_category + level_id, new ArrayList<>(mSolution));
                    tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));

                    WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime());
                    WordsearchGridFragment.chronometer.start();
                    coin_txt.setText("" + coin_point);


//                    coin_txt.startAnimation(bounce_anim);
                }

                dialog.dismiss();
            }
        });

       /* like_retry_game.setOnLikeListener(this);
        like_retry_game.setOnAnimationEndListener(this);

        like_continue_img.setOnLikeListener(this);
        like_continue_img.setOnAnimationEndListener(this);*/


        Chronometer best_chronometer = (Chronometer) dialog.findViewById(R.id.best_chronometer);

        level_id = sp.getString(getActivity(), "sub_level_category");
        stage_txt.setVisibility(View.GONE);
        stage_txt.setText("நிலை " + level_id);

        if (game_sub_level_page.game_stage < Integer.parseInt(level_id) + 1) {
            goback = 2;
            continue_txt.setText("வெளியேற");
            /*next_game.setVisibility(View.GONE);
            like_continue_img.setVisibility(View.GONE);*/
        }

        System.out.println("------che game_stage : " + game_sub_level_page.game_stage);
        System.out.println("------che Integer.parseInt(level_id) + 1) : " + (Integer.parseInt(level_id) + 1));


        timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        chronometer.stop();

        int hours = (int) (timeWhenStopped / 3600000);
        int minutes = (int) (timeWhenStopped - hours * 3600000) / 60000;
        int seconds = (int) (timeWhenStopped - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        int game_time = Math.abs(sec + sec2 + seconds);

        Cursor credit_cursor = null;

        System.out.println("-------------------------game_time : " + game_time);

        int leader_point = 0;
      /*  try {
            coin_cursor = Inner_mydb.rawQuery("select * from coin_table", null);
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                leader_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("leader_point"));
            }
        } finally {
            if (coin_cursor != null)
                coin_cursor.close();
        } */
        try {
            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                leader_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("l_points"));
            }
        } finally {
            if (coin_cursor != null) coin_cursor.close();
        }


        try {


            if (hints.size() == 0) {

                leader_point += 10;

                if (game_time <= 30) {
                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time1"));
                    System.out.println("-------------------------Time1 : " + credits_coin);
                } else if (game_time <= 60) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time2"));
                    System.out.println("-------------------------Time2 : " + credits_coin);
                } else if (game_time <= 90) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time3"));
                    System.out.println("-------------------------Time3 : " + credits_coin);
                } else if (game_time <= 120) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time4"));
                    System.out.println("-------------------------Time4 : " + credits_coin);
                } else if (game_time <= 150) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time5"));
                    System.out.println("-------------------------Time5 : " + credits_coin);
                } else if (game_time <= 180) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time6"));
                    System.out.println("-------------------------Time6 : " + credits_coin);
                } else if (game_time <= 210) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time7"));
                    System.out.println("-------------------------Time7 : " + credits_coin);
                } else if (game_time <= 240) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time8"));
                    System.out.println("-------------------------Time8 : " + credits_coin);
                } else if (game_time <= 270) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time9"));
                    System.out.println("-------------------------Time9 : " + credits_coin);
                } else if (game_time <= 300) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time10"));
                    System.out.println("-------------------------Time10 : " + credits_coin);
                } else if (game_time <= 330) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time11"));
                    System.out.println("-------------------------Time11 : " + credits_coin);
                } else if (game_time <= 360) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time12"));
                    System.out.println("-------------------------Time12 : " + credits_coin);
                } else if (game_time <= 390) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time13"));
                    System.out.println("-------------------------Time13 : " + credits_coin);
                } else if (game_time <= 420) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time14"));
                    System.out.println("-------------------------Time14 : " + credits_coin);
                } else if (game_time <= 450) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time15"));
                    System.out.println("-------------------------Time15 : " + credits_coin);
                } else if (game_time <= 480) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time16"));
                    System.out.println("-------------------------Time16 : " + credits_coin);
                } else if (game_time <= 510) {

                    credit_cursor = mydb.rawQuery("select * from credit where words='" + mWordAdapter.getItemCount() + "'", null);
                    credit_cursor.moveToFirst();
                    credits_coin = credit_cursor.getInt(credit_cursor.getColumnIndexOrThrow("Time17"));
                    System.out.println("-------------------------Time17 : " + credits_coin);
                }

                credit_coin.setText("" + credits_coin);

            }
        } finally {
            if (credit_cursor != null) credit_cursor.close();
        }

        int score_vale = (mSolution.size() * 3) + credits_coin;

        leader_point += (mSolution.size() * 3);

        coin_point = coin_point + score_vale;
        myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "',l_points='" + leader_point + "'");


        int best_score_value = 0;


        Cursor win_cursor = null;
        try {

            win_cursor = Inner_mydb.rawQuery("select * from '" + table_name + "' where id='" + level_id + "' and game_cate='" + level_category + "'", null);

            if (win_cursor.getCount() == 0) {
                best_score_value = score_vale;

                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("id", level_id);
                contentValues12.put("game_finish", 1);
                contentValues12.put("game_cate", level_category);
                contentValues12.put("best_score", best_score_value);
                contentValues12.put("time", timeWhenStopped);
                contentValues12.put("win_coin", (score_vale - credits_coin));
                contentValues12.put("bonus_coin", credits_coin);
                Inner_mydb.insert(table_name, null, contentValues12);
            } else {

                if (win_cursor.getCount() != 0) {
                    win_cursor.moveToFirst();
                    best_score_value = win_cursor.getInt(win_cursor.getColumnIndexOrThrow("best_score"));
                }
                System.out.println("-------------------------best_score_value : " + best_score_value);


                if (best_score_value <= score_vale) {
                    best_score_value = score_vale;
                }
                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("game_finish", 1);
                contentValues12.put("best_score", best_score_value);
                contentValues12.put("time", timeWhenStopped);
                contentValues12.put("win_coin", (score_vale - credits_coin));
                contentValues12.put("bonus_coin", credits_coin);
                Inner_mydb.update(table_name, contentValues12, "id='" + level_id + "' and game_cate='" + level_category + "'", null);
            }

            int unlock_level_id = (Integer.parseInt(level_id) + 1);
            win_cursor = Inner_mydb.rawQuery("select * from '" + table_name + "' where id='" + unlock_level_id + "' and game_cate='" + level_category + "'", null);
            if (win_cursor.getCount() == 0) {
                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("id", unlock_level_id);
                contentValues12.put("game_finish", 0);
                contentValues12.put("game_cate", level_category);
                contentValues12.put("best_score", 0);
                contentValues12.put("time", 0);
                contentValues12.put("win_coin", 0);
                contentValues12.put("bonus_coin", 0);
                Inner_mydb.insert(table_name, null, contentValues12);
            }
        } finally {
            if (win_cursor != null) win_cursor.close();
        }


        if (sp.getString(getActivity(), "game_type").equals("பொதுப்பிரிவு")) {
            try {
                win_cursor = Inner_mydb.rawQuery("select * from '" + table_name + "' where game_cate='" + level_category + "'", null);

                if (sp.getString(getActivity(), "level_unlock_" + sp.getString(getActivity(), "next_level")).equals("")) {

                    // if (win_cursor.getCount() == (level_lenth / 2)) {
                    if (win_cursor.getCount() == (26)) {
                        sp.putString(getActivity(), "level_unlock_" + sp.getString(getActivity(), "next_level"), "level_unlock_" + sp.getString(getActivity(), "next_level"));

                        final Dialog unlock_dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        unlock_dialog.setContentView(R.layout.dia_unlock_sucess);
                        // unlock_dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
                        unlock_dialog.show();

                        TextView cancel_unlock = (TextView) unlock_dialog.findViewById(R.id.cancel_unlock);
                        TextView unlock_content_txt = (TextView) unlock_dialog.findViewById(R.id.unlock_content_txt);

                        unlock_content_txt.setText("வெற்றிகரமாக " + sp.getString(getActivity(), "currrent_level") + " இல் 25 நிலைகள் முடிக்கப்பட்டு " + sp.getString(getActivity(), "next_level") + " விடுவிக்கப்பட்டது.");


                        cancel_unlock.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                unlock_dialog.dismiss();
                            }
                        });

                        // Toast.makeText(getActivity(), "" + sp.getString(getActivity(), "next_level") + " தொகுப்பு விடுவிக்கப்பட்டது ", Toast.LENGTH_SHORT).show();

                    }
                }


            } finally {
                if (win_cursor != null) win_cursor.close();
            }
        }


        best_score.setText("" + best_score_value);
        best_chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        win_coin.setText("" + (score_vale - credits_coin));
        current_score.setText("" + score_vale);
    }

    public void congreate_finish() {
        final Dialog unlock_dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        unlock_dialog.setContentView(R.layout.dia_unlock_sucess);
        //  unlock_dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
        unlock_dialog.setCancelable(false);
        unlock_dialog.show();

        TextView cancel_unlock = (TextView) unlock_dialog.findViewById(R.id.cancel_unlock);
        TextView unlock_content_txt = (TextView) unlock_dialog.findViewById(R.id.unlock_content_txt);
        TextView title_success = (TextView) unlock_dialog.findViewById(R.id.title_success);

        unlock_content_txt.setText("வெற்றிகரமாக " + sp.getString(getActivity(), "currrent_level") + " அனைத்து நிலைகளும் முடிக்கப்பட்டது மேலும் " + sp.getString(getActivity(), "next_level") + "  நிலையை விளையாட தொடங்குங்கள்.");
        title_success.setText("வாழ்த்துக்கள் !!");

        cancel_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlock_dialog.dismiss();

                Intent intent = new Intent(getActivity(), game_sub_level_page.class);
                getActivity().finish();
                startActivity(intent);

            }
        });

    }

    @Override
    public void liked(LikeButton likeButton) {


        switch (likeButton.getId()) {
            case R.id.like_retry_game: {

                myDialog_class.media_player(getActivity(), R.raw.click, "normal");


                goback = 1;

                mFoundWords.clear();
                mSolution.clear();
                my_solution.clear();
                find_word.clear();
                hints.clear();


                mFoundWords.clear();
                tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));

                // Inner_mydb.execSQL("UPDATE '"+table_name+"' SET game_finish='" + 2 + "' where id='" + level_id + "' and game_cate='" + level_category + "'");


                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("game_finish", 2);
                Inner_mydb.update(table_name, contentValues12, "id='" + level_id + "' and game_cate='" + level_category + "'", null);

                tinyDB.putListObject("solutions" + level_category + level_id, new ArrayList<>(mSolution));
                tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));

                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime());
                WordsearchGridFragment.chronometer.start();
                coin_txt.setText("" + coin_point);
                coin_txt.startAnimation(bounce_anim);
                onPuzzleComplete();

            }
            break;

            case R.id.like_continue_img: {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                if (goback == 2) {
                    congreate_finish();
                    my_my_dialog.dismiss();
                } else {
                    goback = 1;

                    int level_id1 = Integer.parseInt(level_id);

                    if (game_sub_level_page.game_stage > level_id1) {
                        level_id1 += 1;
                        level_id = "" + level_id1;
                        level_txt.setText(level_id);

                        sp.putString(getActivity(), "sub_level_category", "" + level_id1);
                        onPuzzleComplete();
                    }
                    tinyDB.putListObject("solutions" + level_category + level_id, new ArrayList<>(mSolution));
                    tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(mFoundWords));

                    WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime());
                    WordsearchGridFragment.chronometer.start();
                    coin_txt.setText("" + coin_point);


//                    coin_txt.startAnimation(bounce_anim);
                }
            }
            break;

        }

        //Toast.makeText(getActivity(), "liked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        // Toast.makeText(getActivity(), "Disliked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {

        String share_content = "தமிழ் வார்த்தை விளையாட்டை விளையாட இது உங்களுக்கான சொற்களம்.\n" + "நீங்களும் இக்களத்தில் ஒருவராக இருக்க \n" + "நித்ராவின் செம்மொழி  வேட்டை செயலியை  கீழே உள்ள லிங்கை கிளிக் செய்து தரவிறக்கம் செய்யவும்.\n" + "https://goo.gl/5FhjMM";
        String root = Environment.getExternalStorageDirectory().toString();
        File mydir = new File(root + "/.nithra/TWS");
        mydir.mkdirs();
        String fname = "Image-appshare.jpg";
        final File file = new File(mydir, fname);

        switch (likeButton.getId()) {
            case R.id.like_retry_game: {

                dia_dismiss(my_my_dialog);

            }
            break;

            case R.id.like_continue_img: {

                dia_dismiss(my_my_dialog);

            }
            break;


        }

        likeButton.setLiked(false);


        //Log.d(TAG, "Animation End for %s" + likeButton);
    }

    public void more_coin(String val) {

        WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        WordsearchGridFragment.chronometer.stop();


        openDialog_earncoin = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = (TextView) openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = (TextView) openDialog_earncoin.findViewById(R.id.ssss);


        TextView wpro = (TextView) openDialog_earncoin.findViewById(R.id.wpro);
        cancel.setVisibility(View.INVISIBLE);
        if (val.equals("yes")) {
            wpro.setText("இந்த விளையாட்டை தொடர குறைந்தபட்சம் 50  - க்கும் மேற்பட்ட நாணயங்கள் தேவை. எனவே கூடுதல் நாணயங்கள் பெற பகிரவும்.");

        }

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

        share_content = "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh";
        String root = Environment.getExternalStorageDirectory().toString();
        File mydir = new File(root + "/.nithra/TWS");
        mydir.mkdirs();
        String fname = "Image-appshare.jpg";
        final File file = new File(mydir, fname);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rvo = 1;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {

                        reward_progressBar.dismiss();
                        rewardedAd.showAd();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {
                        fb_reward = 0;
                        //reward();
                        rewarded_ad();
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

                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
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

             /*   if (isNetworkAvailable()) {
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


            /*    if (isNetworkAvailable()) {

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
        openDialog_earncoin.show();
        openDialog_earncoin.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
            }
        });


    }

    /*   public boolean isLoggedIn() {
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
       }

       private void publishFeedDialog() {
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
       }
   */
    public void hint_dia(final Word hint_word) {

        WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        WordsearchGridFragment.chronometer.stop();


        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_hint_show);
        dialog.setCancelable(false);

        coin_point = Integer.parseInt(coin_txt.getText().toString());

        LinearLayout meaning_lay = (LinearLayout) dialog.findViewById(R.id.meaning_lay);
        TextView meaning_txt = (TextView) dialog.findViewById(R.id.meaning_txt);
        TextView word_txt = (TextView) dialog.findViewById(R.id.word_txt);
        TextView cancel_hint = (TextView) dialog.findViewById(R.id.cancel_hint);
        TextView done_hint = (TextView) dialog.findViewById(R.id.done_hint);
        final CheckBox hint_checkbox = (CheckBox) dialog.findViewById(R.id.hint_checkbox);


        if (table_name.equals("general")) {
            meaning_lay.setVisibility(View.VISIBLE);

            Cursor cursor = null;

            String my_hint = hint_word.getWord().replace(".", "");
            try {
                cursor = mydb.rawQuery("select * from general_meaning where Word='" + my_hint + "'", null);
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    String meaning = cursor.getString(cursor.getColumnIndexOrThrow("Sentence"));

                    word_txt.setText("" + (hint_word.getWord().replace(".", "")));

                    /*  String htmlText1 = "<marquee direction='left'>***</marquee>"+hint_word.getWord().replace(".", "");
                    word_txt.setText(Html.fromHtml(htmlText1));*/


                    String htmlText = meaning.replace(hint_word.getWord().replace(".", ""), "<font color='#ffffff'>" + hint_word.getWord().replace(".", "") + "</font>");
                    meaning_txt.setText(Html.fromHtml(htmlText));
                }
            } finally {
                if (cursor != null) cursor.close();
            }

        }

        if (sp.getString(getActivity(), "hint_checkbox_" + table_name).equals("")) {
            if (!hints.contains(hint_word.getWord())) {
                dialog.show();
            } else {
                wordsearchGridView.showHint(hint_word);
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
            }

        } else {

            if (hints.contains(hint_word.getWord())) {

                wordsearchGridView.showHint(hint_word);
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
            } else {
                if (coin_point >= 20) {
                    if (!hints.contains(hint_word.getWord())) {
                        hints.add(hint_word.getWord());
                    }

                    coin_point -= 20;
                    myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");
                    coin_txt.setText("" + coin_point);
                    //    coin_txt.startAnimation(bounce_anim);

                    wordsearchGridView.showHint(hint_word);

                    WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    WordsearchGridFragment.chronometer.start();

                } else {
                    more_coin("yes");
                }
            }
        }

        cancel_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");
                dialog.dismiss();
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                WordsearchGridFragment.chronometer.start();

            }
        });
        done_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                if (coin_point >= 20) {
                    if (hint_checkbox.isChecked()) {
                        sp.putString(getActivity(), "hint_checkbox_" + table_name, "done");
                    }

                    coin_point -= 20;

                    myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");
                    coin_txt.setText("" + coin_point);
                    //   coin_txt.startAnimation(bounce_anim);


                    if (!hints.contains(hint_word.getWord())) {
                        hints.add(hint_word.getWord());
                    }
                    wordsearchGridView.showHint(hint_word);

                    WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    WordsearchGridFragment.chronometer.start();
                } else {
                    more_coin("yes");
                }

                dialog.dismiss();
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*public void more_coin(String need_coin) {
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

        dont_call = "dont_call";

        WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        WordsearchGridFragment.chronometer.stop();

        TextView cancel_morecoin = (TextView) dialog.findViewById(R.id.cancel_morecoin);
        TextView share_content = (TextView) dialog.findViewById(R.id.share_content);
        RelativeLayout credit_reward = (RelativeLayout) dialog.findViewById(R.id.credit_reward);
        RelativeLayout credit_WApp = (RelativeLayout) dialog.findViewById(R.id.credit_WApp);
        RelativeLayout credit_fb = (RelativeLayout) dialog.findViewById(R.id.credit_fb);
        RelativeLayout credit_gm = (RelativeLayout) dialog.findViewById(R.id.credit_gm);
        RelativeLayout credit_more = (RelativeLayout) dialog.findViewById(R.id.credit_more);

        if (need_coin.equals("yes")) {
            share_content.setText("உங்களிடம் போதுமான நாணயங்கள் இல்லை செயலியை பகிர்ந்து நாணயங்கள் பெறவும் .");
        }


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
                //media1.setLiked(true);
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
                dont_call = "";
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
            }
        });

        dialog.show();

    }*/

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
        animator.setObjectValues(0, (count));
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
            if (coin_cursor != null) coin_cursor.close();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);

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
                    action, params)
                    .setOnCompleteListener(new WebDialog.OnCompleteListener() {
                        @Override
                        public void onComplete(Bundle values, FacebookException error) {

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
        long timeElapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        int f_sec = sec + sec2 + seconds;
        if (timeElapsed <= 181500) {
            if (hints.size() == 0) {
                prize_data_update(context, 75);
            } else {
                prize_data_update(context, 25);
            }

        } else if (timeElapsed > 181500) {
            if (hints.size() == 0) {
                prize_data_update(context, 50);
            } else {
                prize_data_update(context, 25);
            }
        } else {
            prize_data_update(context, 25);
        }
        ////////////////Prize//////////////////
    }

    public void rewarded_ad() {
        rewardedAd = MaxRewardedAd.getInstance(getResources().getString(R.string.Reward_Ins), getActivity());
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
                rewarded_ad();
                if (reward_status == 1) {
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

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                rewardedAd.loadAd();
            }
        });
        rewardedAd.loadAd();
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

    public void share_earn2(int a) {
        final Dialog openDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
       /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
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

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

    /**
     * Represents a word placed at an arbitrary location in a grid. Row and
     * Column point to the position of the first character in the word,
     * regardless of direction or reversal.
     *
     * @author wes
     */


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

        List<Word> mWords;

        Context context;

        public RecyclerAdapter(Context context, List<Word> words) {
            this.context = context;
            this.mWords = words;
        }

        public void setWordsFound(Collection<Word> words) {
            mFoundWords.addAll(words);
        }

        public void setWordFound(Word word) {
            mFoundWords.add(word);
//            mWords.remove(word);
//            mWords.add(word);
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordsearch_word, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            Word word = mWords.get(position);
            System.out.println("Size wordStr mWords :" + mWords);
            //v.setFocusable(false);
            holder.tv.setFocusable(false);


            if (word.getWord() != null) {
                String wordStr = word.getWord();
                //wordStr = wordStr.substring(0, 1).toUpperCase() + wordStr.substring(1);

               /* System.out.println("Size wordStr mWords_sub_0,1 :" + wordStr.substring(0, 1).toUpperCase());
                System.out.println("Size wordStr mWords_sub_1 :" + wordStr.substring(1));
                System.out.println("Size wordStr mWords_sub_0,1+sub_1 :" + wordStr);
*/
                wordStr = wordStr.replace(".", "");
                holder.tv.setText(wordStr);

            }

            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myDialog_class.media_player(getActivity(), R.raw.click, "normal");

                    hint_word = mWords.get(position);

                    if (mFoundWords.contains(mWords.get(position))) {

                        if (table_name.equals("general")) {

                            WordsearchGridFragment.timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                            WordsearchGridFragment.chronometer.stop();

                            final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                            dialog.setContentView(R.layout.dia_word_meaning);
                            dialog.show();

                            RelativeLayout meaning_lay = (RelativeLayout) dialog.findViewById(R.id.meaning_lay);
                            meaning_lay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            TextView word_content = (TextView) dialog.findViewById(R.id.word_content);
                            TextView word_title = (TextView) dialog.findViewById(R.id.word_title);
                            TextView meaning_cancel = (TextView) dialog.findViewById(R.id.meaning_cancel);
                            meaning_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            Cursor cursor = null;

                            String my_hint = hint_word.getWord().replace(".", "");
                            try {
                                cursor = mydb.rawQuery("select * from general_meaning where Word='" + my_hint + "'", null);
                                if (cursor.getCount() != 0) {
                                    cursor.moveToFirst();
                                    String meaning = cursor.getString(cursor.getColumnIndexOrThrow("Sentence"));

                                    word_title.setText("" + (hint_word.getWord().replace(".", "")));

                                    String htmlText = meaning.replace(hint_word.getWord().replace(".", ""), "<font color='#ffffff'>" + hint_word.getWord().replace(".", "") + "</font>");
                                    word_content.setText(Html.fromHtml(htmlText));
                                }
                            } finally {
                                if (cursor != null) cursor.close();
                            }

                            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                                    WordsearchGridFragment.chronometer.start();
                                }
                            });
                        }

                    } else {
                        hint_dia(mWords.get(position));
                    }

                   /* if (hints.contains(mWords.get(position).getWord())) {

                        if (mFoundWords.contains(mWords.get(position))) {

                        } else {

                            //Toast.makeText(getContext(), "Hint for this word already shown", Toast.LENGTH_SHORT).show();
                            wordsearchGridView.showHint(mWords.get(position));
                        }

                    } else {

                        if (!mFoundWords.contains(mWords.get(position))) {

                            if (!hints.contains(mWords.get(position).getWord())) {

                                hints.add(mWords.get(position).getWord());

                                wordsearchGridView.showHint(mWords.get(position));

                            }


                        }
                    }
*/

                }
            });

            if (!mFoundWords.contains(word)) {
                holder.tv.setTextColor(0xFFFFFFFF);

                holder.tv.setPaintFlags(holder.tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            } else {

                holder.tv.setTextColor(Color.parseColor("#9e9e9e"));

                holder.tv.setPaintFlags(holder.tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            MyViewHolder(View view) {
                super(view);

                tv = (TextView) view.findViewById(R.id.lbl_word);
            }
        }
    }
}
