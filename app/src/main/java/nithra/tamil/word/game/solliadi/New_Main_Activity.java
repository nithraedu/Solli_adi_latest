package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.price_url;
import static nithra.tamil.word.game.solliadi.Utils.date_put;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.StatFs;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.billingclient.api.Purchase;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nit_app.DataBaseHelper1;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.billing.BillingManager;
import nithra.tamil.word.game.solliadi.billing.BillingUpdateListener;
import nithra.tamil.word.game.solliadi.billing.Billing_Activity;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.word_search_game.Models.DataBaseHelper_wordsearch;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Word_search_main;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.Word_search_levels;


public class New_Main_Activity extends AppCompatActivity implements RippleView.OnRippleCompleteListener, Download_completed, InstallReferrerStateListener {

    public static final String TAG = "SavedGames";
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    public static final String data_check = "https://nithra.mobi/solliadi/solliadi.php";
    static final int PLUS_ONE_REQUEST_CODE = 0;
    static final String TAG2 = "TrivialDrive";
    static final int CAMERA_CAPTURE = 5;
    private static final String LOADING_PHRASE_CONFIG_KEY = "app_sort_order";
    private static final String LOADING_PHRASE_CONFIG_KEY2 = "SolliadiPrize";
    /*public static LinearLayout add;
    public static LinearLayout add2;
    public static AdView adView2;
    public static AdView adView;
    public static AdRequest request;
    public static AdRequest request2;*/
    private static final int RESULT_LOAD_IMG = 1;
    private static final int REQUEST_CROP_ICON = 2;
    public static SharedPreferences mPreferences;
    ///
    public static LinearLayout add;
    public static LinearLayout add_rect;
    public static LinearLayout add_rect_mul;
    public static LinearLayout add_rect_backpress;
    public static com.facebook.ads.AdView advieww;
    public static com.facebook.ads.AdView advieww_scoresc;
    public static com.facebook.ads.AdView advieww_multi;
    public static boolean ad_load;
    public static FrameLayout add2, add3;
    public static FrameLayout add_p;
    public static String main_act = "";
    //////////////////////////////////////////////////////////New Activity variables////////////////////////////////
    public static LinearLayout fb_rect;
    static int mCoinCount = 20;
    static SharedPreference sps = new SharedPreference();
    static LinearLayout ads_lay;
    static LinearLayout ads_lay_rectangle;
    static LinearLayout ads_lay_new;
    static LinearLayout fb_rectangle;
    static LinearLayout fb_rectangle_scoresc;
    static LinearLayout fb_rectangle_mutiplayer;
    static NativeAdLayout nativeAdLayout;
    static SharedPreference spd = new SharedPreference();
    static String price_date = "";
    static String price_date_d = "";
    static String price_month_date = "";
    static String price_year_date = "";
    static String prize_u_id = "";
    static int score_ed = 0;
    static int old_score_ed = 0;
    static String price_pre_month_date = "";
    static String price_date_dm = "";
    static int status_act = 0, multi_val = 0;
    final int PIC_CROP = 3;
    final int PICK_IMAGE_REQUEST = 8;
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    private final int c_total = 1050;
    private final PendingAction pendingAction = PendingAction.NONE;
    /// Client used to interact with Google APIs
    // True when the application is attempting to resolve a sign-in error that has a possible
    // resolution,
    private final boolean mIsResolving = false;
    // True immediately after the user clicks the sign-in button/
    private final boolean mSignInClicked = false;
    // True if we want to automatically attempt to sign in the user at application start.
    private final boolean mAutoStartSignIn = true;
    ///ad file
    private final int exit = 0;
    private final String mCurrentSaveName = "snapshotTemp";
    Context context = this;
    SharedPreference wee = new SharedPreference();
    RippleView wordgame1, cluegame, picgame, solukulsol, oddmanout, matchword, opposite_word, english_to_tamil, right_order, riddle, tirukural_s, error_correction, fill_in_blanks, eng_to_tamil, pic_to_words;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    Newgame_DataBaseHelper5 newhelper5;
    Newgame_DataBaseHelper6 newhelper6;
    int level = 1;
    TextView action;
    SQLiteDatabase db1, dbs, dbn, dbns;
    Typeface tyr;
    Typeface typ;
    RadioButton fn1, fn2, fn3;
    //MediaPlayer play1;
    SoundPool click, win, coin, worng;
    int soundId1, soundId2, soundId3, soundId4;
    int sv = 0;
    //
    LinearLayout pic_lay;
    TextView toggleButton;
    TextView m_settings;
    TextView wordgame, hintgame, solegame, pictgame;
    TranslateAnimation translateAnimation1, translateAnimation2, translateAnimation3, translateAnimation4, translateAnimation5;
    TranslateAnimation translateAnimation6, translateAnimation7, translateAnimation8, translateAnimation9, translateAnimation10;
    SharedPreference spa = new SharedPreference();
    TextView name_main, noti;
    int back_flag = 0;
    int feedcheck;
    SQLiteDatabase myDB = null;
    DrawerLayout drawer;
    int vercode = 0;
    String vername;
    TextView ver_name, version_code;
    String version_name;
    int versions_code;
    Toolbar toolbar;
    int fornt = 0;
    JSONArray warray, warray2, carray, sarray, sarray2;
    String str_vpcont;
    // Facebook variable starts
    ///Db Variaables
    String w_gameid;
    String w_letterid;
    String email = "";
    Timer t1, th;
    int t, t2;
    DataBaseHelper1 db;
    WebView webView;
    TextView counter, time2, notis, noti_lenear, noti_img, nl_coins_show, user_name, arrow_click;
    LinearLayout new_earncoin;
    Thread thread;
    TextView checkupdate;
    int ry;
    String downok = "", downnodata = "";
    String ddddd = "";
    DownloadFileAsync downloadFileAsync;
    ProgressDialog mProgressDialog;
    ProgressDialog nProgressDialog;
    String saved_data;
    TextView p_id, c_id, ss_id, s_id, p_login_txt, oddmanout_ss_id, matchwords_no;
    LinearLayout ads_lay2, ads_lay3;
    int downcheck = 0;
    int rk = 0;
    String retype = "s";
    String btn_str = "";
    int min = 1;
    int max = 4;
    int random;
    // facebook variable ends
    //nithra.mobi
    int fb_reward = 0;
    int reward_status = 0;
    //RewardedVideoAd rewardedVideoAd;
    /*private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-4267540560263635/4032646722";
    private static final String ADMOB_APP_ID = "ca-app-pub-4267540560263635~9746757509";*/ String strr_html;
    RelativeLayout adsicon2;
    FrameLayout ads_layout_bottom;
    TextView opposite_word_id, english_to_tamil_id;
    TextView right_order_id, riddle_id, tirukuralid, error_correction_id, fill_in_blanks_id, eng_to_tamil_no, pic_to_words_no, match_words_no;
    int mutiplayer_siginin = 0;
    int r = 0;
    LinearLayout r_ads;
    ////Add
    // The helper object
    int RC_REQUEST = 10001;
    RelativeLayout re_ads;
    int ads_request = 0;
    TextView rm_name;
    ////////////////////////////////////////////////////////Word Search/////////////////////////////////////////////////////////
    SQLiteDatabase Inner_mydb, mydbd;
    /////////Native_BackPress_Advanced////////////
    DataBaseHelper_wordsearch myDbHelperd;
    Cursor update_cursor = null;
    ArrayList<String> column = new ArrayList<>(Arrays.asList("challenge_word", "oposit_word", "Q_A_word", "missing_word"));
    ArrayList<String> table = new ArrayList<>(Arrays.asList("challenge", "yethirsoll", "Q_A", "missing_word"));
    SharedPreference sp = new SharedPreference();
    int version_code_n = 0, update = 0;
    String current_date = "";
    RippleView word_search_main, match_the_fallows;
    LinearLayout leader_bd, achivements_d, word_search_d, multi_d;
    TextView word_search_game;
    LinearLayout ads_layd;
    TextView ex_name;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    int minmum = 1;
    int maximum = 4;
    int randomno;
    Dialog openDialog;
    TextView coin_value;
    int setval_vid;
    Button prices;
    RelativeLayout prize_lay;
    AppUpdateManager appUpdateManager;
    Dialog openDialogterm;
    int mCurScreen = -1;
    Dialog openDialog1;
    Uri picUri;
    String img_str = "";
    byte[] b = null;
    String temp = "";
    ///////////////////////////////////////////////////////////Word Search/////////////////////////////////////////////////////////
    String path = "";
    File myDir = null;
    TextView play_word, tamila_word, noti_count;
    int onresume_start = 0;
    //////////////////////////////////////////////////////////New Activity variables////////////////////////////////
    LinearLayout nl_removeads, nl_rateus, nl_share, nl_howtoplay, nl_feedback, nl_notification;
    InstallReferrerClient mReferrerClient;
    String source = "", medium = "", comp = "";
    Dialog intro;
    private boolean mGameOver;
    private boolean mGamePaused;
    private long mTimeRemaining;
    private int c_counter = 0;
    private byte[] mSaveGameData;
    private MaxRewardedAd rewardedAd;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private BillingManager mBillingManager;
    private MaxInterstitialAd interstitialAd;

    public static void sharedPrefAdd(String one, String two, final SharedPreferences mPreferences) {
        // TODO Auto-generated method stub
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(one, two);
        editor.commit();
    }

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


    public static Boolean clr_chace(Context context) {
        Boolean aBoolean = false;
        SharedPreference sharedPreference = new SharedPreference();
        Calendar calendar = Calendar.getInstance();
        long next_hour = calendar.getTimeInMillis();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/M/yyyy");
        Date result = new Date(next_hour);
        String formatted = sdf1.format(result);

        StringTokenizer st2 = new StringTokenizer(formatted, "/");
        int rep_day = Integer.parseInt(st2.nextToken());
        int rep_month = Integer.parseInt(st2.nextToken());
        int rep_year = Integer.parseInt(st2.nextToken());

        rep_month = rep_month - 1;

        String today_date = rep_day + "/" + rep_month + "/" + rep_year;

        Date date_today = null, date_app_update = null;

        try {
            date_today = sdf1.parse(today_date);
            if (!sharedPreference.getString(context, "clr_chace").equals("")) {
                date_app_update = sdf1.parse(sharedPreference.getString(context, "clr_chace"));
            } else {
                date_app_update = sdf1.parse(today_date);
            }

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("clr_chace : error");
        }


        if (sharedPreference.getString(context, "clr_chace").equals("")) {

            aBoolean = true;
            System.out.println("clr_chace : " + aBoolean);
        } else {
            if (date_today.compareTo(date_app_update) >= 0) {
                aBoolean = true;
                System.out.println("clr_chace : " + aBoolean);
            }
        }
        return aBoolean;
    }

    public static boolean determineConnectivity(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.getState() == NetworkInfo.State.CONNECTED;
    }

    public static void prize_data_update(Context context, int score) {
        Newgame_DataBaseHelper myDbHelper = new Newgame_DataBaseHelper(context);
        myDbHelper.executeSql("create table if not exists prize_data(id INTEGER PRIMARY KEY AUTOINCREMENT,date varchar,score integer,isfinish integer DEFAULT 0);");

        if (sps.getString(context, "price_registration").equals("com")) {
            int score_ed = 0;
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
            price_date = str_month1 + "-" + cur_year1;
            System.out.println("#################PS_price_date_in" + price_date);
//////////////////////////////////////////////////////////////////////////////////////PRIZE DATA TABLE///////////////////////////////////////////////////////////////////////////
            Cursor sc2d = myDbHelper.getQry("select * from prize_data where date ='" + price_date + "'");
            sc2d.moveToFirst();
            if (sc2d.getCount() == 0) {
                System.out.println("#################new insert");
                ContentValues cv = new ContentValues();
                cv.put("date", price_date);
                cv.put("score", score);
                myDbHelper.insert_data("prize_data", null, cv);
            } else if (sc2d.getCount() != 0) {
                Cursor up = myDbHelper.getQry("select * from prize_data where date ='" + price_date + "'");
                up.moveToFirst();
                if (up.getCount() != 0) {
                    score_ed = up.getInt(up.getColumnIndexOrThrow("score"));
                    score_ed = score_ed + score;
                    myDbHelper.executeSql("UPDATE prize_data SET score='" + score_ed + "' where date ='" + price_date + "'");
                    System.out.println("#################old insert");
                }
            }

            System.out.println("###################score_ed" + score_ed);
        }

//////////////////////////////////////////////////////////////////////////////////////PRIZE DATA TABLE///////////////////////////////////////////////////////////////////////////
    }

    public static void send_prize_data(final Context context) {

        Calendar calendar3 = Calendar.getInstance();
        price_year_date = String.valueOf(calendar3.get(Calendar.YEAR));
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        price_month_date = "" + (cur_month1 + 1);
        if (price_month_date.length() == 1) {
            price_month_date = "0" + price_month_date;
        }


        price_date_d = price_month_date + "-" + price_year_date;
        System.out.println("#################PS_date" + price_date_d);

        price_pre_month_date = "" + (cur_month1);
        if (price_pre_month_date.length() == 1) {
            price_pre_month_date = "0" + price_pre_month_date;
        }

        price_date_dm = price_pre_month_date + "-" + price_year_date;
        System.out.println("#################PSV_date" + price_date_dm);

        if (sps.getString(context, "price_registration").equals("com")) {
            Newgame_DataBaseHelper myDbHelper = new Newgame_DataBaseHelper(context);
            Cursor ol = myDbHelper.getQry("select * from prize_data where date ='" + price_date_dm + "'");
            ol.moveToFirst();
            System.out.println("#################PS_count" + ol.getCount());
            if (ol.getCount() != 0) {
                old_score_ed = ol.getInt(ol.getColumnIndexOrThrow("score"));
            }

            Cursor up = myDbHelper.getQry("select * from prize_data where date ='" + price_date_d + "'");
            up.moveToFirst();
            System.out.println("#################PS_count" + up.getCount());
            if (up.getCount() != 0) {

                score_ed = up.getInt(up.getColumnIndexOrThrow("score"));
                prize_u_id = sps.getString(context, "p_user_id");
                System.out.println("#################PS" + score_ed);
                System.out.println("#################PS_id" + prize_u_id);
                // Showing progress dialog at user registration time.
                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, price_url, ServerResponse -> {
                    JSONArray jArray, jArray2;
                    JSONObject json_data = null;
                    JSONObject json_data2 = null;
                    String data = "";
                    try {
                        jArray = new JSONArray(ServerResponse);
                        json_data = jArray.getJSONObject(0);
                        if (json_data.getString("status").equals("success")) {
                            for (int i = 0; i < jArray.length(); i++) {
                                String urls = json_data.getString("web");
                                String result = json_data.getString("result");
                                System.out.println("##############result###########" + result);
                                jArray2 = new JSONArray(result);
                                json_data2 = jArray2.getJSONObject(0);
                                for (int j = 0; j < jArray2.length(); j++) {
                                    String pos = json_data2.getString("position");
                                    System.out.println("##############pos###########" + pos);
                                }

                                System.out.println("##############urls###########" + urls);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Hiding the progress dialog after all task complete.
                    // Showing response message coming from server.
                    System.out.println("Game Status ServerResponse" + ServerResponse);
                }, volleyError -> {
                    // Hiding the progress dialog after all task complete.
                    // Showing error message if something goes wrong.
                    Toast.makeText(context, volleyError.toString(), Toast.LENGTH_LONG).show();
                }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();
                        // Adding All values to Params.
                        params.put("mode", "GameStatus");
                        params.put("user_id", prize_u_id);
                        params.put("month", price_month_date);
                        params.put("year", price_year_date);
                        params.put("score", String.valueOf(score_ed));
                        params.put("pre_month", String.valueOf(price_pre_month_date));
                        params.put("pre_year", String.valueOf(price_year_date));
                        params.put("old_score", String.valueOf(old_score_ed));
                        System.out.println("####Tuser_id" + prize_u_id);
                        System.out.println("####Tmonth" + price_month_date);
                        System.out.println("####Tyear" + price_year_date);
                        System.out.println("####Tscore" + score_ed);
                        return params;
                    }

                };
                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }
        }

    }


    @Override
    public void download_completed(String status) {
        Toast.makeText(context, "" + status, Toast.LENGTH_SHORT).show();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro__sc);
        activity_start_screen();
        sps.putString(getApplicationContext(), "ach11", "yees");
        sps.putString(getApplicationContext(), "ach12", "yees");
        sps.putString(getApplicationContext(), "signinagain", "yees");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && sp.getInt(this, "permission") == 0) {

            sp.putInt(this, "permission", 1);

            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 113);


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && sp.getInt(this, "permission") == 10) {

            sp.putInt(this, "permission", 1);
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 113);

        } else {
            System.out.println("_____________print  con't apply check value  :" + sp.getInt(this, "permission"));
            sp.putInt(this, "permission", sp.getInt(this, "permission") + 1);
        }


//in_app message(Nithra ad)
        if (sp.getInt(New_Main_Activity.this, "inappmessage") == 0) {
            sp.putInt(New_Main_Activity.this, "inappmessage", 1);
            FirebaseInAppMessaging.getInstance().setMessagesSuppressed(true);
        } else {
            FirebaseInAppMessaging.getInstance().setMessagesSuppressed(false);
        }


        // successdialog();
        System.out.println("=============Androidid" + Utils.android_id(context));

        //

        /////////

        //advancads();
        ////////
        main_act = "m_on";

        if (spa.getInt(context, "purchase_ads") == 0) {
            // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance(context).setMediationProvider("max");
            AppLovinSdk.initializeSdk(context, configuration -> {
                // AppLovin SDK is initialized, start loading ads
                industrialload();
            });


            MobileAds.initialize(this);
            RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(List.of("ABCDEF012345")).build();
            MobileAds.setRequestConfiguration(configuration);
        }


        onresume_start = 0;
        status_act = 1;
        //reward(context);
        rewarded_ad();
        intro_scn();


        db = new DataBaseHelper1(this);
        RippleView leader = (RippleView) findViewById(R.id.leader);
        RippleView multipalyer = (RippleView) findViewById(R.id.multipalyer);

        re_ads = (RelativeLayout) findViewById(R.id.re_ads);
        r_ads = (LinearLayout) findViewById(R.id.r_ads);
        email = Utils.android_id(context);
        sps.putString(New_Main_Activity.this, "newgame_notification", "start");

        sps.putString(New_Main_Activity.this, "email", email);
        System.out.println("mail======****==" + sps.getString(New_Main_Activity.this, "email"));

        smallestWidth();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                }
            }
        };

        getFcmToken();

        if (clr_chace(New_Main_Activity.this)) {
            System.out.println("==============================remote=====");
            date_put(New_Main_Activity.this, "clr_chace", 7);
            remoteConfig();
        }


        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        rm_name = (TextView) findViewById(R.id.rm_name);
        ex_name = (TextView) findViewById(R.id.ex_name);
        final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pendulam2);
        adsicon2.startAnimation(pendulam);

//FB ADS//
        AudienceNetworkAds.initialize(this);


        /* if (sps.getInt(New_Main_Activity.this, "purchase_ads") != 1) {
            rm_name.setText("சேவை மையம்");
        }*/
        if (sps.getInt(New_Main_Activity.this, "reward_coin_txt") == 0) {
            sps.putInt(New_Main_Activity.this, "reward_coin_txt", 20);
        }

        r_ads.setOnClickListener(v -> {
            //Toast.makeText(New_Main_Activity.this, "purchase", Toast.LENGTH_SHORT).show();

            if (Utils.isNetworkAvailable(getApplicationContext())) {
                purchasedialog();
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }

        });

        if (!sps.getString(New_Main_Activity.this, "ads_dialog_oi").equals("on")) {
            if (sps.getString(New_Main_Activity.this, "ads_dialog").equals("on")) {
                purchasedialog();
                sps.putString(New_Main_Activity.this, "ads_dialog", "");
            }
        } else {
            sps.putString(New_Main_Activity.this, "ads_dialog_oi", "");
        }


        if (sp.getString(New_Main_Activity.this, "review_time").equals("")) {
            Calendar current_date = Calendar.getInstance();
            long currentdate_mills = current_date.getTimeInMillis();
            sp.putString(New_Main_Activity.this, "review_time", "" + currentdate_mills);
        }
        app_update_manager();

        // appUpdateManager = AppUpdateManagerFactory.create(New_Main_Activity.this);

        inapp_messaging();
        word_search_main = (RippleView) findViewById(R.id.word_search_main);
        leader_bd = (LinearLayout) findViewById(R.id.leader_bd);
        achivements_d = (LinearLayout) findViewById(R.id.achivements_d);
        word_search_d = (LinearLayout) findViewById(R.id.word_search_d);
        multi_d = (LinearLayout) findViewById(R.id.multi_d);
        //didTapButton(word_search_main);
        leader_bd.setOnClickListener(v -> {
            //leader_bd();
        });
        achivements_d.setOnClickListener(v -> {
            //achivemnt_st();
        });
        word_search_d.setOnClickListener(v -> {
            if (sps.getString(New_Main_Activity.this, "Word_search_mainintro").equals("")) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Intent main = new Intent(getApplicationContext(), Word_search_levels.class);
                //  finish();
                startActivity(main);
                sps.putString(New_Main_Activity.this, "Word_search_mainintro", "yes");
            } else {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Intent main = new Intent(getApplicationContext(), Word_search_main.class);
                // finish();
                startActivity(main);
            }
        });

        word_search_main.setOnClickListener(v -> {
          /*  Intent main = new Intent(getApplicationContext(), Test_Activity.class);
            finish();
            startActivity(main);*/
            if (sps.getString(New_Main_Activity.this, "Word_search_mainintro").equals("")) {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Intent main = new Intent(getApplicationContext(), Word_search_levels.class);
                // finish();
                startActivity(main);
                sps.putString(New_Main_Activity.this, "Word_search_mainintro", "yes");
            } else {
                click.play(soundId1, sv, sv, 0, 0, sv);
                Intent main = new Intent(getApplicationContext(), Word_search_main.class);
                // finish();
                startActivity(main);
            }
        });

        if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
            if (sp.getInt(New_Main_Activity.this, "referrerCheck") == 0) {

                mReferrerClient = InstallReferrerClient.newBuilder(this).build();
                System.out.println("Referrer check" + mReferrerClient.isReady());
                mReferrerClient.startConnection(this);
                System.out.println("Referrer check" + mReferrerClient.isReady());

            } else {
                System.out.println("=== ReferrerDetails Referrer Already Detected");
            }
        }


        Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
        ex_name.startAnimation(w_game);

        //multipalyer.startAnimation(zoomAnim());

        multipalyer.setOnClickListener(view -> {
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                purchasedialog();
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }


        });
        leader.setOnClickListener(v -> {
            //leader_bd();
        });

        RippleView achive = (RippleView) findViewById(R.id.achivements);
        achive.setOnClickListener(v -> {

            //achivemnt_st();

        });

        File SDCardRoot = getFilesDir();
        File fol = new File(SDCardRoot + "/Nithra/solliadi/");
        if (!fol.exists()) {
            fol.mkdirs();
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


        if (sps.getString(New_Main_Activity.this, "install_date").equals("")) {

            sps.putString(New_Main_Activity.this, "install_date", "" + str_date1);
        }

        if (sps.getString(New_Main_Activity.this, "no_dialog").equals("")) {
            sps.putString(New_Main_Activity.this, str_date1, "yes");
            sps.putString(New_Main_Activity.this, "no_dialog", "yes");
        }

        noti_img = (TextView) findViewById(R.id.noti_img);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        final RelativeLayout navigationView = (RelativeLayout) findViewById(R.id.nav_view);

        ver_name = (TextView) findViewById(R.id.version_name);
        version_code = (TextView) findViewById(R.id.version_code);


        sps.putInt(context, "addloded_rect_bck", 0);
        sps.putInt(context, "addloded_rect_mul", 0);
        PackageManager packageManager = this.getPackageManager();
        if (packageManager != null) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
                version_name = packageInfo.versionName;
                versions_code = packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                version_name = null;
                versions_code = 0;
            }
            ver_name.setText("VN." + version_name);
            version_code.setText("VC." + versions_code);
        }

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);

//animations();
        callactivity();

        ///sound initialize
        if (sps.getString(New_Main_Activity.this, "inside_snd").equals("")) {
            sps.putString(New_Main_Activity.this, "snd", "on");
            sps.putString(New_Main_Activity.this, "inside_snd", "yes");
        }


        //Share
        if (sps.getString(New_Main_Activity.this, "wt").equals("")) {
            sps.putString(New_Main_Activity.this, "wt", "yes");
        } else {
            sps.putString(New_Main_Activity.this, "wt", "no");
        }
        if (sps.getString(New_Main_Activity.this, "fbs").equals("")) {
            sps.putString(New_Main_Activity.this, "fbs", "yes");
        } else {
            sps.putString(New_Main_Activity.this, "fbs", "no");
        }
        if (sps.getString(New_Main_Activity.this, "gplues").equals("")) {
            sps.putString(New_Main_Activity.this, "gplues", "yes");
        } else {
            sps.putString(New_Main_Activity.this, "gplues", "no");
        }

//// Share

        if (sps.getString(New_Main_Activity.this, "wn_intro").equals("")) {
            sps.putString(New_Main_Activity.this, "wn_intro", "yes");
        }
        if (sps.getString(New_Main_Activity.this, "cn_intro").equals("")) {
            sps.putString(New_Main_Activity.this, "cn_intro", "yes");
        }
        if (sps.getString(New_Main_Activity.this, "sn_intro").equals("")) {
            sps.putString(New_Main_Activity.this, "sn_intro", "yes");
        }
        if (sps.getString(New_Main_Activity.this, "pn_intro").equals("")) {
            sps.putString(New_Main_Activity.this, "pn_intro", "yes");
        }

////Db Copy


        if (sps.getString(New_Main_Activity.this, "dbcopy_n").equals("")) {
            sps.putString(New_Main_Activity.this, "dbcopy_n", "no");
        }

/////introduction activity

        if (sps.getString(New_Main_Activity.this, "w_lintro").equals("")) {
            sps.putString(New_Main_Activity.this, "w_lintro", "yes");
        }
        if (sps.getString(New_Main_Activity.this, "c_lintro").equals("")) {
            sps.putString(New_Main_Activity.this, "c_lintro", "yes");
        }
        if (sps.getString(New_Main_Activity.this, "s_lintro").equals("")) {
            sps.putString(New_Main_Activity.this, "s_lintro", "yes");
        }
        if (sps.getString(New_Main_Activity.this, "p_lintro").equals("")) {
            sps.putString(New_Main_Activity.this, "p_lintro", "yes");
        }


//////Date Request
        if (sps.getString(New_Main_Activity.this, "daily_date_request").equals("")) {
            Calendar calendar2 = Calendar.getInstance();
            int cur_year = calendar2.get(Calendar.YEAR);
            int cur_month = calendar2.get(Calendar.MONTH);
            int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

            String str_month = "" + (cur_month + 1);
            if (str_month.length() == 1) {
                str_month = "0" + str_month;
            }

            String str_day = "" + cur_day;
            if (str_day.length() == 1) {
                str_day = "0" + str_day;
            }
            String str_date = cur_year + "-" + str_month + "-" + str_day;
            sps.putString(New_Main_Activity.this, "daily_date_request", "" + str_date);
        }
        if (sp.getString(New_Main_Activity.this, "new_user_db").equals("")) {
            System.out.println("**************************new_user_db");
        } else {
            if (sp.getString(New_Main_Activity.this, "new_user_db").equals("on")) {
                sp.putString(New_Main_Activity.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
                System.out.println("**************************Tamil_Game2.db");
            } else {
                sp.putString(New_Main_Activity.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
                System.out.println("**************************Solli_Adi");
            }

        }

        if (sps.getString(New_Main_Activity.this, "bending_total3").equals("yes")) {

            db1 = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
            dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
            dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
            dbns = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);
            myDbHelper = new DataBaseHelper(context);
            newhelper = new Newgame_DataBaseHelper(context);
            newhelper2 = new Newgame_DataBaseHelper2(context);
            newhelper3 = new Newgame_DataBaseHelper3(context);
            newhelper4 = new Newgame_DataBaseHelper4(context);
            newhelper5 = new Newgame_DataBaseHelper5(context);

        }
        //////////////////
        db1 = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        myDB = this.openOrCreateDatabase("myDB", MODE_PRIVATE, null);

        tablescreated();

        ///Prgress Bar Running:
        new AsyncTask<String, String, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (sps.getString(New_Main_Activity.this, "alter_answer_table").equals("")) {
                    Utils.mProgress(New_Main_Activity.this, "முதல் தடவை தரவுகளை ஏற்றுகிறது. சில நிமிடங்கள் வரை ஆகலாம், காத்திருக்கவும்.....", false).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
              /*  if (sps.getInt(New_Main_Activity.this, "dbcopy" + Utils.versioncode_get(New_Main_Activity.this)) == 0) {
                    TestAdapter mDbHelper = new TestAdapter(New_Main_Activity.this);
                    mDbHelper.createDatabase();
                    mDbHelper.open();
                    mDbHelper.close();
                }*/


                mPreferences = getSharedPreferences("", MODE_PRIVATE);

                if (sps.getString(New_Main_Activity.this, "dbcopied_n").equals("yes")) {

                } else {
                    dbcreate();

                }
                if (mPreferences.getString("newdbcopied_n", "").equals("yes")) {

                } else {
                    newgamecreate_db();
                }

                if (mPreferences.getString("newdbcopied_n2", "").equals("yes")) {

                } else {
                    newgamecreate_db2();
                }

                if (mPreferences.getString("newdbcopied_n3", "").equals("yes")) {

                } else {
                    newgamecreate_db3();
                }

                try {
                    PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                    String version = pInfo.versionName;
                    version_code_n = pInfo.versionCode;

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }


                if (mPreferences.getString("newdbcopied_n4", "").equals("yes")) {

                } else {
                    newgamecreate_db4();
                }
                if (mPreferences.getString("newdbcopied_n5", "").equals("yes")) {

                } else {
                    newgamecreate_db5();
                }
                if (mPreferences.getString("newdbcopied_n6", "").equals("yes")) {

                } else {
                    newgamecreate_db6();
                }

                /*if (Utils.versioncode_get(New_Main_Activity.this)>=39)
                {
                    sp.putString(New_Main_Activity.this,"db_name_start","Tamil_Game2.db");
                }
                if (Utils.versioncode_get(New_Main_Activity.this)=39)
                {
                    sp.putString(New_Main_Activity.this,"db_name_start","Tamil_Game2.db");
                }
                else {
                    sp.putString(New_Main_Activity.this,"db_name_start","Solli_Adi");
                }*/

                if (sp.getString(New_Main_Activity.this, "new_user_db").equals("on")) {
                    sp.putString(New_Main_Activity.this, "db_name_start", "Tamil_Game2.db");
                    Commen_string.dbs_name = "Tamil_Game2.db";
                } else {
                    sp.putString(New_Main_Activity.this, "db_name_start", "Solli_Adi");
                    Commen_string.dbs_name = "Solli_Adi";
                }

                dbadd_wordsearch();
               /* if (sp.getInt(New_Main_Activity.this, "app_version") < version_code_n) {

                    sp.putInt(New_Main_Activity.this, "app_version", version_code_n);
                    sp.putString(New_Main_Activity.this, "newdbcopied_n4", "");
                }*/
                // dbmovefirst();
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                myDbHelper = new DataBaseHelper(context);
                newhelper = new Newgame_DataBaseHelper(context);
                newhelper2 = new Newgame_DataBaseHelper2(context);
                newhelper3 = new Newgame_DataBaseHelper3(context);

               /* newhelper = new Newgame_DataBaseHelper(context);
                newhelper2 = new Newgame_DataBaseHelper2(context);
                newhelper3 = new Newgame_DataBaseHelper3(context);*/

               /* dbs = openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
                dbn = openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
                dbns = openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);*/

               /* db1 = myDbHelper.getReadableDatabase();
                dbs = newhelper.getReadableDatabase();
                dbn = newhelper2.getReadableDatabase();
                dbns = newhelper3.getReadableDatabase();
*/

                // Utils.mProgress.dismiss();
            /*    if (sps.getString(New_Main_Activity.this,"signinagain").equals("yes"))
                {
                    if(Utils.isNetworkAvailable(getApplicationContext())){
                        if(!getApiClient().isConnected()){
                            if(!isSignedIn()) {
                                signinagain();
                            }
                            //Toast.makeText(getApplication(),"bbbbbb",Toast.LENGTH_SHORT).show();
                        }}
                }
*/

                sps.putInt(New_Main_Activity.this, "dbcopy" + Utils.versioncode_get(New_Main_Activity.this), 1);
//                app_install_check(New_Main_Activity.this, db);

                //newupdate();

                ///game status sending///
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    Calendar calendar2 = Calendar.getInstance();
                    int cur_year = calendar2.get(Calendar.YEAR);
                    int cur_month = calendar2.get(Calendar.MONTH);
                    int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

                    String str_month = "" + (cur_month + 1);
                    if (str_month.length() == 1) {

                        str_month = "0" + str_month;
                    }

                    String str_day = "" + cur_day;
                    if (str_day.length() == 1) {
                        str_day = "0" + str_day;
                    }
                    final String str_date = cur_year + "-" + str_month + "-" + str_day;
                    if (sps.getString(New_Main_Activity.this, "s1" + str_date).equals("")) {
                        ///Prgress Bar Running:
                        new AsyncTask<String, String, String>() {
                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                //   Utils.mProgress(New_Main_Activity.this,"முதல் தடவை தரவுகளை ஏற்றுகிறது. சில நிமிடங்கள் வரை ஆகலாம், காத்திருக்கவும்.....",false).show();
                            }

                            @Override
                            protected String doInBackground(String... params) {
                                if (mPreferences.getString("newdbcopied_n3", "").equals("yes")) {
                                    gamestatus();
                                }
                                //userstates_send();
                                return null;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                            }
                        }.execute();

                        sps.putString(New_Main_Activity.this, "s1" + str_date, "yes");
                    }

                    prize_data_update(New_Main_Activity.this, 0);
                    if (sps.getString(New_Main_Activity.this, "s2" + str_date).equals("")) {
                        System.out.println("==================userstates_send");
                        ///Prgress Bar Running:
                        new AsyncTask<String, String, String>() {
                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                //   Utils.mProgress(New_Main_Activity.this,"முதல் தடவை தரவுகளை ஏற்றுகிறது. சில நிமிடங்கள் வரை ஆகலாம், காத்திருக்கவும்.....",false).show();
                            }

                            @Override
                            protected String doInBackground(String... params) {
                                //userstates_send();
                                System.out.println("==================send_prize_data");
                                if (sps.getString(context, "price_registration").equals("com")) {
                                    // send_prize_data(New_Main_Activity.this);
                                    System.out.println("==================send_prize_data com");
                                    if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
                                        send_prize_data(New_Main_Activity.this);
                                    }

                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
                                    sps.putString(New_Main_Activity.this, "s2" + str_date, "yes");
                                }

                            }
                        }.execute();
                    }
                }


                myDbHelper.executeSql("create table if not exists userdetail(id integer,name varchar,upic varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");
                myDbHelper.executeSql("create table if not exists festival_data(id integer,gameid integer,levelid integer,letters varchar,answer varchar,hints varchar,imagename varchar,isfinish integer,isdownload integer,date varchar,playtime integer,noclue integer);");
                myDbHelper.executeSql("create table if not exists answer_table(id integer,gameid integer,levelid integer,answer varchar,isfinish integer,uid integer,rd integer DEFAULT 0,playtime integer,levelscore integer  DEFAULT 0,useranswer integer);");


                if (sps.getString(New_Main_Activity.this, "complite_reg").equals("yes")) {
                    System.out.println("=======inside");
                    ///Game states for reward
                    //  myDbHelper.executeSql("create table if not exists userdata_d(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
                    myDbHelper.executeSql("create table if not exists userdata_r(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,type varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
                    ///Game states for reward

                    String mobileno = null;
                    Cursor sc3 = myDbHelper.getQry("select * from userdetail");
                    sc3.moveToFirst();
                    if (sc3.getCount() != 0) {
                        mobileno = sc3.getString(sc3.getColumnIndexOrThrow("phno"));

                    }

                    Cursor sc2 = myDbHelper.getQry("select * from userdata_r where date ='" + str_date1 + "' and type='d'");
                    sc2.moveToFirst();
                    if (sc2.getCount() == 0) {
                        System.out.println("=======inserting1" + mobileno);
                        ContentValues cv = new ContentValues();
                        cv.put("phno", "" + mobileno);
                        cv.put("date", "" + str_date1);
                        cv.put("type", "d");
                        cv.put("gm1", "0");
                        cv.put("gm2", "0");
                        cv.put("gm3", "0");
                        cv.put("gm4", "0");
                        cv.put("score", "0");
                        cv.put("playtime", "0");
                        cv.put("isfinish", "0");
                        myDbHelper.insert_data("userdata_r", null, cv);
                    }

                    Cursor sc4 = myDbHelper.getQry("select * from userdata_r where date ='" + str_date1 + "' and type='r'");
                    sc4.moveToFirst();
                    if (sc4.getCount() == 0) {
                        System.out.println("=======inserting2" + str_date1);
                        ContentValues cv = new ContentValues();
                        cv.put("phno", "" + mobileno);
                        cv.put("date", "" + str_date1);
                        cv.put("type", "r");
                        cv.put("gm1", "0");
                        cv.put("gm2", "0");
                        cv.put("gm3", "0");
                        cv.put("gm4", "0");
                        cv.put("score", "0");
                        cv.put("playtime", "0");
                        cv.put("isfinish", "0");
                        myDbHelper.insert_data("userdata_r", null, cv);
                    }


                    Cursor sc5 = myDbHelper.getQry("select * from userdata_r where date ='" + str_date1 + "' and type='s'");
                    sc5.moveToFirst();
                    if (sc5.getCount() == 0) {
                        System.out.println("=======inserting2" + str_date1);
                        ContentValues cv = new ContentValues();
                        cv.put("phno", "" + mobileno);
                        cv.put("date", "" + str_date1);
                        cv.put("type", "s");
                        cv.put("gm1", "0");
                        cv.put("gm2", "0");
                        cv.put("gm3", "0");
                        cv.put("gm4", "0");
                        cv.put("score", "0");
                        cv.put("playtime", "0");
                        cv.put("isfinish", "0");
                        myDbHelper.insert_data("userdata_r", null, cv);
                    }
                }

                Cursor cv = myDB.rawQuery("select * from noti_cal where isclose='0'", null);
                System.out.println("============cv.getcount" + cv.getCount());
                if (cv.getCount() <= 9) {
                    noti_lenear.setText("" + cv.getCount());
                } else if (cv.getCount() > 9) {
                    noti_lenear.setText("9+");
                } else {
                    noti_lenear.setText("0");
                }

                myDbHelper = new DataBaseHelper(context);
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                if (cfx != null && cfx.moveToFirst()) {
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    nl_coins_show.setText("" + skx);
                }

                Cursor bb1 = myDbHelper.getQry("select * from score ");
                if (bb1 != null && bb1.moveToFirst()) {
                    int k1 = bb1.getInt(bb1.getColumnIndexOrThrow("coins"));
                    nl_coins_show.setText("" + k1);
                }

                if (sps.getString(New_Main_Activity.this, "bending_total3").equals("")) {
                    sps.putString(New_Main_Activity.this, "bending_total3", "yes");
                }
                if (sps.getString(New_Main_Activity.this, "bending_total4").equals("")) {
                    sps.putString(New_Main_Activity.this, "bending_total4", "yes");
                    sp.putString(New_Main_Activity.this, "game_area", "");
                }

                if (sps.getString(New_Main_Activity.this, "bending_total5").equals("")) {
                    sps.putString(New_Main_Activity.this, "bending_total5", "yes");
                }
                if (sps.getString(New_Main_Activity.this, "bending_total6").equals("")) {
                }

                ///Alter Answer table
                if (sps.getString(New_Main_Activity.this, "alter_answer_table").equals("")) {
                    Utils.mProgress.dismiss();
                }
                if (sps.getString(New_Main_Activity.this, "alter_answer_table").equals("")) {


                    myDbHelper.executeSql("alter table answertable add column rd integer DEFAULT 0");
                    myDbHelper.executeSql("alter table answertable add column playtime integer");
                    myDbHelper.executeSql("alter table answertable add column levelscore integer  DEFAULT 0");
                    myDbHelper.executeSql("alter table answertable add column useranswer integer");

                    myDbHelper.executeSql("alter table maintable add column playtime integer DEFAULT 0");
                    myDbHelper.executeSql("alter table maintable add column noclue integer DEFAULT 0");
                    myDbHelper.executeSql("alter table dailytest add column playtime integer DEFAULT 0");
                    myDbHelper.executeSql("alter table dailytest add column noclue integer DEFAULT 0");
                    sps.putString(New_Main_Activity.this, "alter_answer_table", "yes");
                }
                if (sps.getString(New_Main_Activity.this, "alter_answer_table_2").equals("")) {
                    myDbHelper.executeSql("alter table answertable add column afinish integer DEFAULT 0");
                    myDbHelper.executeSql("alter table maintable add column rtm integer DEFAULT 0");

                    sps.putString(New_Main_Activity.this, "alter_answer_table_2", "yes");
                }

                dbs = openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
                dbn = openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
                dbns = openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);
                if (sps.getString(New_Main_Activity.this, "new_maintables_daily").equals("")) {
                    newhelper.executeSql("alter table newmaintable add column daily integer DEFAULT 0");
                    newhelper2.executeSql("alter table newmaintable2 add column daily integer DEFAULT 0");

                    sps.putString(New_Main_Activity.this, "new_maintables_daily", "yes");

                }
                if (sps.getString(New_Main_Activity.this, "alter_table_rtm").equals("")) {
                    newhelper.executeSql("alter table newmaintable add column rtm integer DEFAULT 0");
                    newhelper2.executeSql("alter table newmaintable2 add column rtm integer DEFAULT 0");
                    newhelper3.executeSql("alter table right_order add column rtm integer DEFAULT 0");

                    sps.putString(New_Main_Activity.this, "alter_table_rtm", "yes");
                    System.out.println("#################alter_table_rtm############");
                }
            }
        }.execute();


        if (sps.getString(New_Main_Activity.this, "game_type").equals("")) {
            sps.putString(New_Main_Activity.this, "game_type", "1");
        }
        if (sps.getString(New_Main_Activity.this, "daily_game").equals("")) {
            sps.putString(New_Main_Activity.this, "game_type", "1");
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        noti_lenear = (TextView) findViewById(R.id.noti_linear);
        noti_lenear.setText("0");
        noti_lenear.setOnClickListener(view -> {
            //finish();
            Intent i = new Intent(New_Main_Activity.this, Noti_Fragment.class);
            startActivity(i);

        });

        LinearLayout home = (LinearLayout) findViewById(R.id.home);
        LinearLayout dailytestlist = (LinearLayout) findViewById(R.id.dailytestlist);

        home.setOnClickListener(v -> drawer.closeDrawer(navigationView));
        LinearLayout privacypolicy = (LinearLayout) findViewById(R.id.privacy_pl);
        privacypolicy.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                // finish();
                Intent i = new Intent(context, Main_policy.class);
                startActivity(i);
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }

        });
        dailytestlist.setOnClickListener(v -> {
            finish();
            drawer.closeDrawer(navigationView);
            Intent i = new Intent(New_Main_Activity.this, Expandable_List_View.class);
            startActivity(i);
        });
        LinearLayout notifications = (LinearLayout) findViewById(R.id.notifications);
        notifications.setOnClickListener(v -> {
            finish();
            Intent i = new Intent(New_Main_Activity.this, Expandable_List_View.class);
            startActivity(i);
            drawer.closeDrawer(navigationView);
        });
        LinearLayout karuthu = (LinearLayout) findViewById(R.id.karuthu);
        karuthu.setOnClickListener(v -> {
            send_feed();
            drawer.closeDrawer(navigationView);
        });
        LinearLayout ern = (LinearLayout) findViewById(R.id.ern);
        ern.setOnClickListener(v -> {
            dialog();
            drawer.closeDrawer(navigationView);
        });
        LinearLayout rating = (LinearLayout) findViewById(R.id.rating);
        rating.setOnClickListener(v -> {
            rate_fun();
            drawer.closeDrawer(navigationView);
        });
        LinearLayout nithra = (LinearLayout) findViewById(R.id.nithra);
        nithra.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Nithra")));
            } else {
                Utils.toast_center(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });

        LinearLayout share = (LinearLayout) findViewById(R.id.share);
        share.setOnClickListener(v -> {
            share_fun();
            drawer.closeDrawer(navigationView);
        });

        if (sps.getString(New_Main_Activity.this, "Daily_notifications").equals("")) {
            sps.putString(New_Main_Activity.this, "Daily_notifications", "yes");
        }


        toggleButton = (TextView) findViewById(R.id.daily_noti);
    /*    String sds = sps.getString(New_Main_Activity.this, "Daily_notifications");
        if (sds.equals("yes")) {
            toggleButton.setBackgroundResource(R.drawable.on);
        } else if (sds.equals("no")) {
            toggleButton.setBackgroundResource(R.drawable.off);
        }


        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sds = sps.getString(New_Main_Activity.this, "Daily_notifications");
                System.out.println("***In");
                if (sds.equals("yes")) {
                    System.out.println("***no");
                    sps.putString(New_Main_Activity.this, "Daily_notifications", "no");
                    toggleButton.setBackgroundResource(R.drawable.off);
                } else if (sds.equals("no")) {
                    System.out.println("***yes");
                    sps.putString(New_Main_Activity.this, "Daily_notifications", "yes");
                    toggleButton.setBackgroundResource(R.drawable.on);
                }
            }
        });*/


        ads_lay2 = (LinearLayout) findViewById(R.id.ads_lay2);

        ads_lay2.setOnClickListener(v -> {

        });

        createFolder();

        ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        ads_lay.setVisibility(View.VISIBLE);
        ads_lay3 = (LinearLayout) findViewById(R.id.ads_lay3);


        prices = (Button) findViewById(R.id.price);
        prize_lay = (RelativeLayout) findViewById(R.id.prize_lay);

        prize_enable();

        sp.putString(New_Main_Activity.this, "sd_prize_st", "");
        if (sp.getInt(New_Main_Activity.this, "remoteConfig_prize") == 1) {
            //prices.setVisibility(View.VISIBLE);
            prize_lay.setVisibility(View.VISIBLE);
        } else {
            // prices.setVisibility(View.GONE);
            prize_lay.setVisibility(View.GONE);
        }
        sp.putString(New_Main_Activity.this, "activity_call", "");
        prize_lay.setOnClickListener(v -> {
            if (determineConnectivity(New_Main_Activity.this)) {
                if (sp.getString(New_Main_Activity.this, "price_registration").equals("com")) {
                    //finish();
                    Intent i = new Intent(New_Main_Activity.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sp.getString(New_Main_Activity.this, "otp_verify").equals("yes")) {
                        // finish();
                        Intent i = new Intent(New_Main_Activity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        //finish();
                        sp.putString(New_Main_Activity.this, "activity_call", "main");
                        Intent i = new Intent(New_Main_Activity.this, Price_Login.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }
        });
        prices.setOnClickListener(v -> {
            if (determineConnectivity(New_Main_Activity.this)) {
                if (sp.getString(New_Main_Activity.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(New_Main_Activity.this, Game_Status.class);
                    startActivity(i);
                } else {
                    if (sp.getString(New_Main_Activity.this, "otp_verify").equals("yes")) {
                        finish();
                        Intent i = new Intent(New_Main_Activity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(New_Main_Activity.this, Price_Login.class);
                        startActivity(i);
                    }

                }
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }


        });

        p_login_txt = (TextView) findViewById(R.id.p_login_txt);
    }

    private void getFcmToken() {
        final String[] refreshedToken = {""};
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                return;
            }

            // Get new FCM registration token
            refreshedToken[0] = task.getResult();
            String token = refreshedToken[0];

            SharedPreference sharedPreference = new SharedPreference();
            if (sharedPreference.getInt(New_Main_Activity.this, "isvalid") == 0) {
                if (sharedPreference.getString(New_Main_Activity.this, "token").length() > 0) {
                    new gcmpost_update2().execute(token);
                }

            } else {
                if (sharedPreference.getInt(New_Main_Activity.this, "fcm_update") < Utils.getversioncode(New_Main_Activity.this)) {
                    new gcmpost_update1().execute(token);
                }
            }
            System.out.println("---fcm token : " + token);
        });

    }

    private void inapp_messaging() {
        Bundle extras = getIntent().getExtras();
        String action = getIntent().getAction();
        Uri data = getIntent().getData();
        if (Intent.ACTION_VIEW.equals(action)) {
            System.out.println("first");
            CharSequence text = getIntent().getCharSequenceExtra(Intent.ACTION_VIEW);
            String Word = "" + text;
            System.out.println("first  " + Word);
            if (data.toString().contains("prize")) {
                if (determineConnectivity(New_Main_Activity.this)) {
                    if (sp.getString(New_Main_Activity.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(New_Main_Activity.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sp.getString(New_Main_Activity.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(New_Main_Activity.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            sp.putString(New_Main_Activity.this, "activity_call", "main");
                            Intent i = new Intent(New_Main_Activity.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    //method
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      /*  getMenuInflater().inflate(R.menu.main, menu);
        return true;*/
      /*  MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return true;
    }

    public void callactivity() {
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        wordgame1 = (RippleView) findViewById(R.id.word_game);
        cluegame = (RippleView) findViewById(R.id.hint_game);
        solukulsol = (RippleView) findViewById(R.id.solukulsol);
        oddmanout = (RippleView) findViewById(R.id.oddmanout);
        matchword = (RippleView) findViewById(R.id.matchwords);
        opposite_word = (RippleView) findViewById(R.id.opposite_word);
        english_to_tamil = (RippleView) findViewById(R.id.english_to_tamil);
        right_order = (RippleView) findViewById(R.id.right_order);
        riddle = (RippleView) findViewById(R.id.riddle);
        tirukural_s = (RippleView) findViewById(R.id.tirukural_s);
        error_correction = (RippleView) findViewById(R.id.error_correction);

        fill_in_blanks = (RippleView) findViewById(R.id.fill_in_blanks);
        eng_to_tamil = (RippleView) findViewById(R.id.eng_to_tamil);
        pic_to_words = (RippleView) findViewById(R.id.pic_to_words);
        match_the_fallows = (RippleView) findViewById(R.id.match_the_fallows);


        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);

        picgame = (RippleView) findViewById(R.id.pic_game);
        wordgame = (TextView) findViewById(R.id.wordgame);
        hintgame = (TextView) findViewById(R.id.hintgame);
        solegame = (TextView) findViewById(R.id.solgame);
        pictgame = (TextView) findViewById(R.id.picgame);
        m_settings = (TextView) findViewById(R.id.m_settings);
        name_main = (TextView) findViewById(R.id.name_main);

        p_id = (TextView) findViewById(R.id.p_id);
        c_id = (TextView) findViewById(R.id.c_id);
        ss_id = (TextView) findViewById(R.id.ss_id);
        s_id = (TextView) findViewById(R.id.s_id);
        oddmanout_ss_id = (TextView) findViewById(R.id.oddmanout_ss_id);
        matchwords_no = (TextView) findViewById(R.id.matchwords_no);

        opposite_word_id = (TextView) findViewById(R.id.opposite_word_id);
        english_to_tamil_id = (TextView) findViewById(R.id.english_to_tamil_id);

        right_order_id = (TextView) findViewById(R.id.right_order_id);
        riddle_id = (TextView) findViewById(R.id.riddle_id);
        error_correction_id = (TextView) findViewById(R.id.error_correction_id);
        fill_in_blanks_id = (TextView) findViewById(R.id.fill_in_blanks_id);
        eng_to_tamil_no = (TextView) findViewById(R.id.eng_to_tamil_no);
        pic_to_words_no = (TextView) findViewById(R.id.pic_to_words_no);
        match_words_no = (TextView) findViewById(R.id.match_words_no);
        tirukuralid = (TextView) findViewById(R.id.tirukuralid);


        noti_lenear = (TextView) findViewById(R.id.noti_linear);
        action = (TextView) findViewById(R.id.actionword8);
        noti_lenear = (TextView) findViewById(R.id.noti_linear);


        View view = getLayoutInflater().inflate(R.layout.action_sole, null);
        action = (TextView) findViewById(R.id.actionword8);
        Drawable d = getResources().getDrawable(R.drawable.actionbar_back);

        name_main.setText("சொல்லிஅடி");

        //sounds for game
        //play1=  MediaPlayer.create(this,R.raw.click);

        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(New_Main_Activity.this, R.raw.click, 1);

///
        String snd = sps.getString(New_Main_Activity.this, "snd");
        if (snd.equals("off")) {
            sv = 0;
            //play1.setVolume(0,0);
        } else if (snd.equals("on")) {
            sv = 1;
            //play1.setVolume(1,1);
        }
        //
        wordgame1.setOnRippleCompleteListener(this);
        cluegame.setOnRippleCompleteListener(this);
        picgame.setOnRippleCompleteListener(this);
        solukulsol.setOnRippleCompleteListener(this);
        oddmanout.setOnRippleCompleteListener(this);
        matchword.setOnRippleCompleteListener(this);
        opposite_word.setOnRippleCompleteListener(this);
        english_to_tamil.setOnRippleCompleteListener(this);
        right_order.setOnRippleCompleteListener(this);
        riddle.setOnRippleCompleteListener(this);
        tirukural_s.setOnRippleCompleteListener(this);
        error_correction.setOnRippleCompleteListener(this);
        fill_in_blanks.setOnRippleCompleteListener(this);
        eng_to_tamil.setOnRippleCompleteListener(this);
        pic_to_words.setOnRippleCompleteListener(this);
        match_the_fallows.setOnRippleCompleteListener(this);

        Typeface typ = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        //wordgame.setTypeface(typ);

        m_settings.setOnClickListener(v -> {
            // settings();
        });


    }

    private void dbcreate() {
        Commen_string.dbs_name = "Tamil_Game2.db";
        sp.putString(New_Main_Activity.this, "new_user_db", "on");
        sp.putString(New_Main_Activity.this, "db_name_start", "Tamil_Game2.db");

        if (sps.getString(New_Main_Activity.this, "dbcopy_n").equals("no")) {
            mPreferences = getSharedPreferences("", MODE_PRIVATE);
            if (mPreferences.getString("dbcopied_n", "").equals("yes")) {
                //Toast.makeText(getBaseContext(),"second",Toast.LENGTH_SHORT).show();

                myDbHelper = new DataBaseHelper(this);
                try {
                    myDbHelper.createDataBase();
                    System.out.println("*******createDataBase");
                } catch (IOException ioe) {
                    System.out.println("" + ioe + "*******Unable to create database");
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }


            } else {
                //Toast.makeText(getBaseContext(),"first",Toast.LENGTH_SHORT).show();
                sharedPrefAdd("dbcopy_n", "yes", mPreferences);
                sharedPrefAdd("dbcopied_n", "yes", mPreferences);

                myDbHelper = new DataBaseHelper(this);
                try {
                    try {
                        myDbHelper.copyDataBase();
                    } catch (IOException ew) {
                        // TODO Auto-generated catch block
                        ew.printStackTrace();
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                try {
                    try {
                        myDbHelper.createDataBase();
                        System.out.println("*******createDataBase");
                    } catch (IOException ioe) {
                        System.out.println("" + ioe + "*******Unable to create database");
                        throw new Error("*******Unable to create database");
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                try {
                    try {
                        myDbHelper.openDataBase();
                        System.out.println("*******openDataBase");

                    } catch (SQLException sqle) {
                        throw sqle;
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                dbmovefirst();


            }

        }

    }

    public void animations() {
        final TextView a = (TextView) findViewById(R.id.a);
        final TextView a_para = (TextView) findViewById(R.id.a_para);

        final TextView ee = (TextView) findViewById(R.id.ee);
        final TextView ee_para = (TextView) findViewById(R.id.ee_para);
        final TextView e = (TextView) findViewById(R.id.e);
        final TextView e_para = (TextView) findViewById(R.id.e_para);

        final TextView u = (TextView) findViewById(R.id.u);
        final TextView u_para = (TextView) findViewById(R.id.u_para);

        a.setVisibility(View.VISIBLE);
        translateAnimation1 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation1.setDuration(3000);
        a.startAnimation(translateAnimation1);
        // a.setVisibility(View.INVISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            a.setVisibility(View.INVISIBLE);
            a_para.setVisibility(View.VISIBLE);
            translateAnimation2 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f);
            translateAnimation2.setDuration(3000);
            a_para.startAnimation(translateAnimation2);
            a_para.setVisibility(View.INVISIBLE);
            translateAnimation1.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation2.setRepeatCount(ValueAnimator.INFINITE);


            translateAnimation1.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation2.setRepeatCount(ValueAnimator.INFINITE);

        }, 3000);

        translateAnimation3 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation3.setDuration(4000);
        u.startAnimation(translateAnimation3);
        u.setVisibility(View.INVISIBLE);
        u_para.setVisibility(View.INVISIBLE);
        Handler handler2 = new Handler();
        handler2.postDelayed(() -> {
            u.setVisibility(View.INVISIBLE);
            u_para.setVisibility(View.VISIBLE);
            translateAnimation4 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f);
            translateAnimation4.setDuration(4000);
            u_para.startAnimation(translateAnimation4);
            a_para.setVisibility(View.INVISIBLE);
            translateAnimation3.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation4.setRepeatCount(ValueAnimator.INFINITE);

            translateAnimation3.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation4.setRepeatCount(ValueAnimator.INFINITE);

        }, 4000);

        translateAnimation5 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation5.setDuration(3500);
        ee.startAnimation(translateAnimation5);
        ee.setVisibility(View.INVISIBLE);
        ee_para.setVisibility(View.INVISIBLE);
        Handler handler3 = new Handler();
        handler3.postDelayed(() -> {
            ee_para.setVisibility(View.VISIBLE);
            translateAnimation6 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f);
            translateAnimation6.setDuration(3500);
            ee_para.startAnimation(translateAnimation6);

            translateAnimation5.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation6.setRepeatCount(ValueAnimator.INFINITE);


            translateAnimation5.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation6.setRepeatCount(ValueAnimator.INFINITE);

        }, 3500);
        translateAnimation7 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation7.setDuration(5000);
        e.startAnimation(translateAnimation7);
        e.setVisibility(View.INVISIBLE);
        e_para.setVisibility(View.INVISIBLE);
        Handler handler4 = new Handler();
        handler4.postDelayed(() -> {
            ee_para.setVisibility(View.VISIBLE);
            translateAnimation8 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.0f, TranslateAnimation.RELATIVE_TO_PARENT, 1.1f);
            translateAnimation8.setDuration(5000);
            e_para.startAnimation(translateAnimation8);

            translateAnimation7.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation8.setRepeatCount(ValueAnimator.INFINITE);


            translateAnimation7.setRepeatCount(ValueAnimator.INFINITE);
            translateAnimation8.setRepeatCount(ValueAnimator.INFINITE);

        }, 5000);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.pic_game: {
                if (sps.getString(New_Main_Activity.this, "picintro_one").equals("")) {
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    //  finish();
                    spa.putString(New_Main_Activity.this, "date", "0");
                    wee.putString(New_Main_Activity.this, "pic1", "p1");
                    Intent i = new Intent(New_Main_Activity.this, Picture_Levels.class);
                    startActivity(i);
                    sps.putString(New_Main_Activity.this, "picintro_one", "yes");
                } else {
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    //play1.start();
                    // finish();
                    spa.putString(New_Main_Activity.this, "date", "0");
                    wee.putString(New_Main_Activity.this, "pic1", "p1");
                    Intent i = new Intent(New_Main_Activity.this, Picture_Game_Hard.class);
                    startActivity(i);
                }


            }
            break;
            case R.id.hint_game: {
///clue_game
                if (sps.getString(New_Main_Activity.this, "hintintro_one").equals("")) {
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    //   finish();
                    spa.putString(New_Main_Activity.this, "date", "0");
                    wee.putString(New_Main_Activity.this, "hint", "h1");
                    Intent i = new Intent(New_Main_Activity.this, Clue_Levels.class);
                    startActivity(i);
                    sps.putString(New_Main_Activity.this, "hintintro_one", "yes");
                } else {
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    //play1.start();
                    //   finish();
                    spa.putString(New_Main_Activity.this, "date", "0");
                    wee.putString(New_Main_Activity.this, "hint", "h1");
                    Intent i = new Intent(New_Main_Activity.this, Clue_Game_Hard.class);
                    startActivity(i);
                }

            }
            break;
            case R.id.word_game: {
                if (sps.getString(New_Main_Activity.this, "wordintro_one").equals("")) {
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    //  finish();
                    spa.putString(New_Main_Activity.this, "date", "0");
                    wee.putString(New_Main_Activity.this, "open1", "l1");
                    Intent i = new Intent(New_Main_Activity.this, Levels.class);
                    startActivity(i);
                    sps.putString(New_Main_Activity.this, "wordintro_one", "yes");

                } else {
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    //play1.start();
                    // finish();
                    spa.putString(New_Main_Activity.this, "date", "0");
                    wee.putString(New_Main_Activity.this, "open1", "l1");
                    Intent i = new Intent(New_Main_Activity.this, Word_Game_Hard.class);
                    startActivity(i);
                }
            }
            break;
            case R.id.solukulsol: {
                if (sps.getString(New_Main_Activity.this, "sosintro_one").equals("")) {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, SolukulSol_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "sosintro_one", "yes");

                } else {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Solukul_Sol.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }

            }
            break;
            case R.id.oddmanout: {

                if (sps.getString(New_Main_Activity.this, "oddman_intro_one").equals("")) {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Odd_man_out_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "oddman_intro_one", "yes");

                } else {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Odd_man_out.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }

            }
            break;
            case R.id.matchwords: {
                // newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid=53 and gameid=6");
                if (sps.getString(New_Main_Activity.this, "matchword_intro_one").equals("")) {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Match_words_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "matchword_intro_one", "yes");

                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Match_Word.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);

                }
            }
            break;
            case R.id.opposite_word: {

                if (sps.getString(New_Main_Activity.this, "opposite_word_intro_one").equals("")) {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Opposite_word_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();

                    sps.putString(New_Main_Activity.this, "opposite_word_intro_one", "yes");

                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Opposite_word.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
            case R.id.english_to_tamil: {

                if (sps.getString(New_Main_Activity.this, "english_to_tamil_intro_one").equals("")) {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Ote_to_tamil_Levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "english_to_tamil_intro_one", "yes");

                } else {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Ote_to_Tamil.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
            case R.id.right_order: {

                if (sps.getString(New_Main_Activity.this, "Makeword_Rightorder").equals("")) {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Make_wordrightorder_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "Makeword_Rightorder", "yes");

                } else {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Makeword_Rightorder.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;

            case R.id.riddle: {

                if (sps.getString(New_Main_Activity.this, "riddle_intro").equals("")) {
                    //  finish();
                    Intent i = new Intent(New_Main_Activity.this, Riddle_game_level.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "riddle_intro", "yes");

                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Riddle_game.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
            case R.id.tirukural_s: {

                if (sps.getString(New_Main_Activity.this, "tirukural_s_intro").equals("")) {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Tirukural_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "tirukural_s_intro", "yes");

                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Tirukural.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
            case R.id.error_correction: {

                if (sps.getString(New_Main_Activity.this, "error_correction_s_intro").equals("")) {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, WordError_correction_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "error_correction_s_intro", "yes");
                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, WordError_correction.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;

            case R.id.fill_in_blanks: {
                if (sps.getString(New_Main_Activity.this, "fill_in_blanks_intro").equals("")) {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Fill_in_blanks_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "fill_in_blanks_intro", "yes");
                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Fill_in_blanks.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
            case R.id.eng_to_tamil: {
                // rate_fun();
                if (sps.getString(New_Main_Activity.this, "Quiz_game_intro").equals("")) {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Quiz_game_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "Quiz_game_intro", "yes");
                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Quiz_Game.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
            case R.id.pic_to_words: {
                if (sps.getString(New_Main_Activity.this, "pic_to_words_intro").equals("")) {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, pic_to_words_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "pic_to_words_intro", "yes");
                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Find_words_from_picture.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
            case R.id.match_the_fallows: {
                if (sps.getString(New_Main_Activity.this, "match_the_fallows_intro").equals("")) {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, match_the_words_levels.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    click.play(soundId1, sv, sv, 0, 0, sv);
                    // play1.start();
                    sps.putString(New_Main_Activity.this, "match_the_fallows_intro", "yes");
                } else {
                    // finish();
                    Intent i = new Intent(New_Main_Activity.this, Match_tha_fallows_game.class);
                    spa.putString(New_Main_Activity.this, "date", "0");
                    startActivity(i);
                    //play1.start();
                    click.play(soundId1, sv, sv, 0, 0, sv);
                }
            }
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  visible();

        //purchase();

        if (spa.getInt(New_Main_Activity.this, "purchase_ads_completed") == 1) {
            spa.putInt(New_Main_Activity.this, "purchase_ads_completed", 2);
            successdialog();
        }

        UpdateListener updateListener = new UpdateListener();
        mBillingManager = new BillingManager(this, updateListener);


        if (sps.getInt(New_Main_Activity.this, "purchase_ads") == 1) {
            ads_lay.setVisibility(View.GONE);
        } else {

        }


        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));

        if (!mGameOver && mGamePaused) {

        }


        // uiHelper.onResume();




       /* if (sps.getInt(New_Main_Activity.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase not done");
                load_add(add);
            }
        }*/

        System.out.println("###############---------------bnd");
        if (sps.getString(New_Main_Activity.this, "bending_total4").equals("yes")) {
            System.out.println("###############---------------bnd---------yes");
            if (sp.getString(New_Main_Activity.this, "game_area").equals("on")) {
                System.out.println("###############---------------bnd---------on");
                settext();
                sp.putString(New_Main_Activity.this, "game_area", "");
            }
        }

       /* Toast.makeText(this, "Onresume", Toast.LENGTH_SHORT).show();
        if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
            New_Main_Activity.load_addFromMain(New_Main_Activity.this, ads_lay);
        }*/

        myDB = this.openOrCreateDatabase("myDB", MODE_PRIVATE, null);
        tablescreated();
        Cursor cv = myDB.rawQuery("select * from noti_cal where isclose='0'", null);
        System.out.println("============cv.getcount" + cv.getCount());
        if (cv.getCount() <= 9) {
            noti_count.setText("" + cv.getCount());
        } else if (cv.getCount() > 9) {
            noti_count.setText("9+");
        } else {
            noti_count.setText("0");
        }
        if (onresume_start == 0) {
            Handler handel = new Handler();
            handel.postDelayed(() -> score_update(), 500);

        }

    }

    private void score_update() {
        myDbHelper = new DataBaseHelper(context);
        Cursor sc2 = myDbHelper.getQry("select * from score ");
        if (sc2 != null && sc2.moveToFirst()) {
            int k1 = sc2.getInt(sc2.getColumnIndexOrThrow("coins"));
            nl_coins_show.setText("" + k1);
        }
    }

    //add
    public void smallestWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        float scaleFactor = metrics.density;

        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;

        float smallestWidth = Math.min(widthDp, heightDp);

        System.out.println("Width Pixels : " + widthPixels);
        System.out.println("Height Pixels : " + heightPixels);
        System.out.println("Dots per inch : " + metrics.densityDpi);
        System.out.println("Scale Factor : " + scaleFactor);
        System.out.println("Smallest Width : " + smallestWidth);

        sps.putString(getApplicationContext(), "smallestWidth", smallestWidth + "");
        sps.putString(getApplicationContext(), "widthPixels", widthPixels + "");
        sps.putString(getApplicationContext(), "heightPixels", heightPixels + "");
        sps.putString(getApplicationContext(), "density", metrics.densityDpi + "");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            sps.putInt(New_Main_Activity.this, "addloded", 0);
            sps.putInt(context, "addloded2", 0);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (mCurScreen == R.layout.activity_new__main_) {
                    activity_start_screen();
                } else {
                    if (back_flag == 0) {
                        backpressed();
                    } else {
                    }

                }

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backpressed() {
        final Dialog openDialog_p = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess_mainacivity);
        // openDialog_p.setCancelable(false);
        TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
        TextView noask = (TextView) openDialog_p.findViewById(R.id.noask);
        TextView no = (TextView) openDialog_p.findViewById(R.id.no);
        CheckBox checkbox_back = (CheckBox) openDialog_p.findViewById(R.id.checkbox_back);
        checkbox_back.setVisibility(View.GONE);
        noask.setVisibility(View.GONE);
        final LinearLayout ads_layd = (LinearLayout) openDialog_p.findViewById(R.id.ads_lay);

       /* if (sps.getInt(New_Main_Activity.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            ads_layd.setVisibility(View.GONE);
        } else {
            // New_Main_Activity.load_addFromMain_rect(New_Main_Activity.this, ads_layd);
            if (Utils.isNetworkAvailable(context)) {
                New_Main_Activity.load_add_fb_rect(New_Main_Activity.this, ads_layd);
            } else {
                ads_layd.setVisibility(View.GONE);
            }

        }*/

        yes.setOnClickListener(view -> {

            main_act = "";
            if (sps.getString(New_Main_Activity.this, "ratefun_shown").equals("")) {
                ratefun();
                sps.putString(New_Main_Activity.this, "ratefun_shown", "yes");
            } else {
                //ins ad exit
                if (spa.getInt(context, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        finish();
                    }
                } else {
                    finish();
                }


            }
            openDialog_p.dismiss();
        });
        no.setOnClickListener(view -> openDialog_p.dismiss());

        if (sps.getString(getApplicationContext(), "ach11").equals("")) {
            sps.putInt(New_Main_Activity.this, "randomtime1", 0);

        }

        if (sps.getString(getApplicationContext(), "ach12").equals("")) {
            sps.putInt(New_Main_Activity.this, "hr1", 0);

        }
        openDialog_p.show();

    }

    public void industrialload() {

        interstitialAd = new MaxInterstitialAd(getResources().getString(R.string.App_Exit_Ins), this);
        interstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                exit_dia();

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
        interstitialAd.loadAd();

    }

    @Override
    public void onInstallReferrerSetupFinished(int responseCode) {
        System.out.println("onInstallReferrerSetupFinished");
        System.out.println("InstallReferrerClient responseCode " + responseCode);

        switch (responseCode) {
            case InstallReferrerClient.InstallReferrerResponse.OK:

                sp.putInt(New_Main_Activity.this, "referrerCheck", 1);
                System.out.println("Install Referrer conneceted... Successfully");
                System.out.println("Install Referrer conneceted..." + mReferrerClient.isReady());

                try {
                    ReferrerDetails response = mReferrerClient.getInstallReferrer();
                    if (response != null) {
                        String referrerUrl = response.getInstallReferrer();
                        long referrerClickTime = response.getReferrerClickTimestampSeconds();
                        long appInstallTime = response.getInstallBeginTimestampSeconds();
                        boolean instantExperienceLaunched = response.getGooglePlayInstantParam();

                        System.out.println("=== ReferrerDetails_url " + referrerUrl);
                        System.out.println("=== ReferrerDetails_click_time " + referrerClickTime);
                        System.out.println("=== ReferrerDetails_ins_time " + appInstallTime);
                        System.out.println("=== ReferrerDetails_launch " + instantExperienceLaunched);


                        if (referrerUrl != null) {
                            if (referrerUrl.length() > 0) {
                                String[] referrerList = referrerUrl.split("&");
                                for (int i = 0; i < referrerList.length; i++) {
                                    if (i == 0) {
                                        source = referrerList[i].substring(referrerList[i].indexOf("=") + 1);
                                        System.out.println("Referrer_source===" + source);
                                    } else if (i == 1) {
                                        medium = referrerList[i].substring(referrerList[i].indexOf("=") + 1);
                                        System.out.println("Referrer_medium===" + medium);
                                    } else if (i == 2) {
                                        comp = referrerList[i].substring(referrerList[i].indexOf("=") + 1);
                                        System.out.println("Referrer_comp===" + comp);
                                    }
                                }
                            }
                            /*sent data to server*/
                            new AsyncTask<String, String, String>() {
                                @Override
                                protected String doInBackground(String... params) {

                                    send(New_Main_Activity.this, source, medium, comp);
                                    return null;
                                }
                            }.execute();
                        } else {
                            System.out.println("=== Referrer URL NULL");
                        }

                    } else {
                        System.out.println("=== Referrer Details NULL");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                    System.out.println("=== error " + e.getMessage());
                }
                break;
            case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                System.out.println("FEATURE_NOT_SUPPORTED");
                break;
            case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                // Connection could not be established
                System.out.println("SERVICE_UNAVAILABLE");
                break;
            case InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED:
                System.out.println("SERVICE_DISCONNECTED");
                break;
            case InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR:
                System.out.println("DEVELOPER_ERROR");
                break;
            default:
                System.out.println("RESPONSE CODE NOT FOUND");

        }

        mReferrerClient.endConnection();
    }

    @Override
    public void onInstallReferrerServiceDisconnected() {
        System.out.println("onInstallReferrerServiceDisconnected");
        mReferrerClient.startConnection(this);
    }

    public void send(Context context, String utm, String utm1, String utm2) {
        HttpHandler sh = new HttpHandler();
        String url = "https://nithra.mobi/apps/referrer.php";
        JSONObject postDataParams = new JSONObject();
        try {
            postDataParams.put("app", "TAV");
            postDataParams.put("ref", utm);
            postDataParams.put("mm", utm1);
            postDataParams.put("cn", utm2);
            postDataParams.put("email", Utils.android_id(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = sh.makeServiceCall(url, postDataParams);

    }

    public void ratefun() {

        final Dialog ratedia = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        ratedia.setContentView(R.layout.rate);
        Button yesbut = (Button) ratedia.findViewById(R.id.button2);
        Button nobut = (Button) ratedia.findViewById(R.id.button1);

        final Dialog yesratedialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        yesratedialog.setContentView(R.layout.rate1);
        Button yesbut1 = (Button) yesratedialog.findViewById(R.id.button2);
        Button nobut1 = (Button) yesratedialog.findViewById(R.id.button1);

        final Dialog feedratedialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        feedratedialog.setContentView(R.layout.send_feedback);
        feedratedialog.setCanceledOnTouchOutside(false);
        Button sentbut1 = (Button) feedratedialog.findViewById(R.id.btnSend);
        final EditText editText1 = (EditText) feedratedialog.findViewById(R.id.editText1);

        final EditText ph_no = (EditText) feedratedialog.findViewById(R.id.ph_no);
        final EditText name = (EditText) feedratedialog.findViewById(R.id.name);
        final EditText emails = (EditText) feedratedialog.findViewById(R.id.emails);
        final TextView privacy_policy = (TextView) feedratedialog.findViewById(R.id.privacy_policy);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        privacy_policy.setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(editText1.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(emails.getWindowToken(), 0);
            if (isNetworkAvailable()) {

                showPrivacy();
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();

            }
        });
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                editText1.setError(null);
            }

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        sentbut1.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            if (editText1.getText().toString().trim().length() == 0) {
                Utils.toast_center(New_Main_Activity.this, "உங்களது கருத்துக்களை பதிவு செய்யவும். ");
            } else {
                if (Utils.isNetworkAvailable(New_Main_Activity.this)) {

                    Utills.INSTANCE.sendFeed(New_Main_Activity.this, name.getText().toString(), emails.getText().toString(), ph_no.getText().toString(), editText1.getText().toString());

                    imm.hideSoftInputFromWindow(editText1.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(emails.getWindowToken(), 0);
                    feedratedialog.dismiss();

                } else {
                    Utils.toast_normal(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }


        });

        yesbut1.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            try {
                String appPackageName = getPackageName();
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                startActivity(marketIntent);
            } catch (Exception e) {
                System.out.println();
            }
            yesratedialog.dismiss();
            if (spa.getInt(context, "purchase_ads") == 0) {
                if (interstitialAd != null && interstitialAd.isReady()) {
                    interstitialAd.showAd();
                } else {
                    finish();
                }
            }
        });

        nobut1.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            feedcheck = 1;
///
            InputMethodManager imm12 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm12.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            feedratedialog.show();
            yesratedialog.dismiss();
        });

        yesbut.setOnClickListener(arg0 -> {
            // TODO Auto-generated method stub
            feedcheck = 1;
            sps.putInt(getApplicationContext(), "ratecheckval", 1);
            date_put(New_Main_Activity.this, "rate_date", 90);
            ratedia.dismiss();
            yesratedialog.show();
        });
        nobut.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            sps.putInt(getApplicationContext(), "ratecheckval", 1);
            date_put(New_Main_Activity.this, "rate_date", 90);
            feedcheck = 1;
            ratedia.dismiss();
////
            InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm1.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            feedratedialog.show();
        });
        yesratedialog.setOnDismissListener(dialog -> {
            // TODO Auto-generated method stub
            if (feedcheck == 0) {
                //ins ad exit

                if (spa.getInt(context, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        finish();
                    }
                }
            }
        });

        ratedia.setOnDismissListener(dialog -> {
            // TODO Auto-generated method stub
            if (feedcheck == 0) {
                //ins ad exit
                if (spa.getInt(context, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        finish();
                    }
                }
            }
        });
        feedratedialog.setOnDismissListener(dialog -> {
            // TODO Auto-generated method stub
            if (feedcheck == 1) {
                //ins ad exit
                if (spa.getInt(context, "purchase_ads") == 0) {
                    if (interstitialAd != null && interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    } else {
                        finish();
                    }
                } else {
                    finish();
                }
            }
        });

        if (sps.getInt(getApplicationContext(), "ratecheckval") == 0) {
            ratedia.show();
        } else {
            //ins ad exit
            if (spa.getInt(context, "purchase_ads") == 0) {
                if (interstitialAd != null && interstitialAd.isReady()) {
                    interstitialAd.showAd();
                } else {
                    finish();
                }
            }

        }
    }

    public void exit_dia() {
        final Dialog exit_dia = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        exit_dia.setContentView(R.layout.exit_lay);
        exit_dia.setCanceledOnTouchOutside(false);
        exit_dia.setCancelable(false);
        Handler handel = new Handler();
        handel.postDelayed(() -> {
            // TODO Auto-generated method stub
            finish();
        }, 1200);

        exit_dia.show();
    }

    public void rate_fun() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
    }

    public void share_fun() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/*");
        i.putExtra(Intent.EXTRA_SUBJECT, "சொல்லிஅடி");
        i.putExtra(Intent.EXTRA_TEXT, "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh \n");
        startActivity(Intent.createChooser(i, "Share via"));
    }

    public void send_feed() {

        final Dialog rating_dialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        rating_dialog.setContentView(R.layout.send_feedback);
       /* rating_dialog.getWindow().setLayout(android.app.ActionBar.LayoutParams.MATCH_PARENT,
                android.app.ActionBar.LayoutParams.MATCH_PARENT);*/
        Button btnSent = (Button) rating_dialog.findViewById(R.id.btnSend);
        rating_dialog.setCanceledOnTouchOutside(false);
        final EditText txtFeedBack = (EditText) rating_dialog.findViewById(R.id.editText1);
        final EditText ph_no = (EditText) rating_dialog.findViewById(R.id.ph_no);
        final EditText name = (EditText) rating_dialog.findViewById(R.id.name);
        final EditText emails = (EditText) rating_dialog.findViewById(R.id.emails);
        final TextView privacy_policy = (TextView) rating_dialog.findViewById(R.id.privacy_policy);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        privacy_policy.setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(txtFeedBack.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(emails.getWindowToken(), 0);
            if (isNetworkAvailable()) {
                showPrivacy();
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();

            }
        });
        btnSent.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            if (txtFeedBack.getText().toString().trim().length() == 0) {
                Utils.toast_center(New_Main_Activity.this, "உங்களது கருத்துக்களை பதிவு செய்யவும். ");
            } else {
                if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
                    Utills.INSTANCE.sendFeed(New_Main_Activity.this, name.getText().toString(), emails.getText().toString(), ph_no.getText().toString(), txtFeedBack.getText().toString());
                    imm.hideSoftInputFromWindow(txtFeedBack.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(emails.getWindowToken(), 0);
                    rating_dialog.dismiss();
                } else {
                    Utils.toast_normal(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
            /*InputMethodManager imms = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imms.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);*/
        });


        rating_dialog.show();
    }

    public void tablescreated() {
        String tablenew = "noti_cal";
        int isclose = 0;
        myDB.execSQL("CREATE TABLE IF NOT EXISTS " + tablenew + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR," + "bm VARCHAR,ntype VARCHAR,url VARCHAR);");


    }


    public void createFolder() {

        // Create Folder
        File direct = new File(Environment.getExternalStorageDirectory() + "/Nithra");

        if (!direct.exists()) {
            if (direct.mkdir()) {
                // directory is created;
            }
        }

        direct.getAbsolutePath();

        File direct1 = new File(direct + "/solliadi");
        if (!direct1.exists()) {
            if (direct1.mkdir()) {
                // directory is created;
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);

    }

    public void dbmovefirst() {

        System.out.println("Database Coppy ");
        try {
            File data = Environment.getDataDirectory();

            String currentDBPath = "/data/" + context.getPackageName() + "/databases/Solli_Adi";
            String backupDBPath = "/data/" + context.getPackageName() + "/databases/Tamil_Game2.db";
            File currentDB = new File(data, currentDBPath);
            File backupDB = new File(data, backupDBPath);
            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(backupDB).getChannel();
                FileChannel dst = new FileOutputStream(currentDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                System.out.println("*******Database Restored successfully");
            } else {
                System.out.println("*******Restored error");
            }

        } catch (Exception e) {
            System.out.println("*******Database Coppy error");
        }
    }

    public void free_roll() {
        final Dialog openDialog;
        openDialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.free_roll);
        counter = (TextView) openDialog.findViewById(R.id.timer);
        time2 = (TextView) openDialog.findViewById(R.id.timer2);
        final TextView start_thread = (TextView) openDialog.findViewById(R.id.start);
        final TextView stop_thread = (TextView) openDialog.findViewById(R.id.stop);
        final TextView cancel = (TextView) openDialog.findViewById(R.id.close);
        final TextView t_time = (TextView) openDialog.findViewById(R.id.t_time);
        c_counter = 0;
        counter.setVisibility(View.INVISIBLE);
        start_thread.setVisibility(View.VISIBLE);
        stop_thread.setVisibility(View.INVISIBLE);

        final String FORMAT = "%02d:%02d:%02d";
        int seconds, minutes;

        new CountDownTimer(16000000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                t_time.setText("" + String.format(FORMAT, TimeUnit.MILLISECONDS.toHours(millisUntilFinished), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                t_time.setText("done!");
            }
        }.start();


        start_thread.setOnClickListener(v -> {
            stop_thread.setEnabled(true);
            start_thread.setEnabled(false);
            time2.setVisibility(View.INVISIBLE);
            counter.setVisibility(View.VISIBLE);
            start_thread.setVisibility(View.INVISIBLE);
            stop_thread.setVisibility(View.VISIBLE);
            thread = new Thread(() -> {
                while (c_counter < c_total) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    counter.post(() -> counter.setText("" + c_counter));
                    c_counter++;
                }
            });
            thread.start();
        });

        stop_thread.setOnClickListener(v -> {
            stop_thread.setEnabled(false);
            start_thread.setEnabled(true);
            counter.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.VISIBLE);
            String r = counter.getText().toString();
            time2.setText(r);
            c_counter = 0;
            int d = Integer.parseInt(r);
            if (d == 1000) {
                Toast.makeText(New_Main_Activity.this, "YOU WIN JACKPOT", Toast.LENGTH_SHORT).show();

            } else if (d > 990 && d < 1000) {
                Toast.makeText(New_Main_Activity.this, "YOU 20 COIN", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(New_Main_Activity.this, "NO COINS", Toast.LENGTH_SHORT).show();

            }
        });

        cancel.setOnClickListener(v -> openDialog.dismiss());
        openDialog.show();

    }


    /*@Override
    protected void onStart() {
        super.onStart();
       // mGoogleApiClient.connect();

    }*/

    public void rate() {
        if ((sps.getString(New_Main_Activity.this, "show_rate").equals(""))) {
            sps.putString(New_Main_Activity.this, "show_rate", "yes");
            if ((sps.getInt(New_Main_Activity.this, "day_day") == 0)) {
                sps.putInt(getApplicationContext(), "day_day", 1);
                Calendar c = Calendar.getInstance();
                String day1 = (c.get(Calendar.DAY_OF_MONTH) + 15) + "";
                String month1 = (c.get(Calendar.MONTH) + 1) + "";
                String year1 = c.get(Calendar.YEAR) + "";
                if (day1.length() == 1) day1 = "0" + day1;
                if (month1.length() == 1) month1 = "0" + month1;

                sps.putString(New_Main_Activity.this, "dat", day1 + ":" + month1 + ":" + year1);

            } else {
                Calendar c = Calendar.getInstance();
                String day1 = (c.get(Calendar.DAY_OF_MONTH) + 30) + "";
                String month1 = (c.get(Calendar.MONTH) + 1) + "";
                String year1 = c.get(Calendar.YEAR) + "";
                if (day1.length() == 1) day1 = "0" + day1;
                if (month1.length() == 1) month1 = "0" + month1;

                sps.putString(New_Main_Activity.this, "dat", day1 + ":" + month1 + ":" + year1);
            }
        }

    }

    public void downloadcheck(final String lastid, final String daily) {
        Utils.mProgress(New_Main_Activity.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", false).show();
        Utils.mProgress.setCancelable(false);
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
                nameValuePairs.add(new BasicNameValuePair("lastid", lastid));
                nameValuePairs.add(new BasicNameValuePair("mode", "regular"));
                nameValuePairs.add(new BasicNameValuePair("email", sps.getString(New_Main_Activity.this, "email")));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(New_Main_Activity.data_check);
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
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                    System.out.print("ord Result============" + result);
                } catch (Exception e) {
                }
                try {
                    if (result != null) {
                        JSONArray jArray = new JSONArray(result);
                        System.err.println("Update===" + result);
                        System.out.println("===  " + jArray.length());
                        JSONObject json_data = null;
                        //isvalid=""+jArray.length();
                        downok = "" + jArray.length();
                        System.out.print("insert ord ============" + downok);
                        if (jArray.length() > 0) {
                            json_data = jArray.getJSONObject(0);
                            if (json_data.getString("NoData").equals("NoData")) {
                                downnodata = "NoData";
                                System.out.print("Insert No=======");
                                downcheck = downcheck + 1;
                            } else {
                                System.out.print("Insert Yes=======");

                                downcheck = 0;
                                downnodata = "YesData";
                                for (int i = 0; i < jArray.length(); i++) {
                                    System.out.print("Insert for=======");
                                    json_data = jArray.getJSONObject(i);
                                    ContentValues cv = new ContentValues();
                                    cv.put("id", json_data.getString("id"));
                                    cv.put("gameid", json_data.getString("gameid"));
                                    cv.put("levelid", json_data.getString("levelid"));
                                    cv.put("letters", json_data.getString("letters"));

                                    String newName = json_data.getString("answer").replaceAll(" ", "");

                                    cv.put("answer", newName);
                                    cv.put("hints", json_data.getString("hints"));
                                    cv.put("imagename", json_data.getString("imagename"));
                                    cv.put("isfinish", "0");

                                    if (daily.equals("ord")) {
                                        cv.put("isdownload", "1");
                                        myDbHelper.insert_data("maintable", null, cv);

                                    } else {

                                        cv.put("date", json_data.getString("date"));
                                        myDbHelper.insert_data("dailytest", null, cv);

                                    }

                                    if (i == (jArray.length() - 1)) {
                                        if (exists("https://nithra.mobi/solliadi/" + sps.getString(New_Main_Activity.this, "email") + "-filename.zip")) {
                                            checkmemory();
                                        } else {
                                            System.out.print("ord image no============");
                                        }
                                    }


                                }
                            }
                        }
                    }

                } catch (JSONException e1) {
                } catch (android.net.ParseException e1) {
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Cursor c1 = myDbHelper.getQry("select id from dailytest order by id DESC");
                c1.moveToFirst();
                System.out.print("Count====" + c1.getCount());
                if (c1.getCount() != 0) {
                    //c1.getString(c1.getColumnIndexOrThrow("id"));
                    System.out.print("Last ID===ord=" + c1.getString(c1.getColumnIndexOrThrow("id")));
                    downloadcheck1("" + c1.getString(c1.getColumnIndexOrThrow("id")), "daily");
                } else {
                    System.out.print("else====");
                    downloadcheck1("0", "daily");
                }
            }
        }.execute();
    }

    public void downloadcheck1(final String lastid, final String daily) {
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
                nameValuePairs.add(new BasicNameValuePair("lastid", lastid));
                nameValuePairs.add(new BasicNameValuePair("mode", "daily"));
                //   nameValuePairs.add(new BasicNameValuePair("date", ""));
                nameValuePairs.add(new BasicNameValuePair("email", sps.getString(New_Main_Activity.this, "email")));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/solliadi.php");
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
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                    System.out.print("daily Result============" + result);
                } catch (Exception e) {
                }
                try {
                    if (result != null) {
                        JSONArray jArray = new JSONArray(result);
                        System.err.println("Update===" + result);
                        System.out.println("===  " + jArray.length());
                        JSONObject json_data = null;
                        //isvalid=""+jArray.length();
                        downok = "" + jArray.length();
                        System.out.print("insert daily ============" + downok);
                        if (jArray.length() > 0) {
                            json_data = jArray.getJSONObject(0);
                            if (json_data.getString("NoData").equals("NoData")) {
                                downnodata = "NoData";
                                System.out.print("Insert Daily NO=======");
                                downcheck = downcheck + 1;
                            } else {
                                downcheck = 0;
                                downnodata = "YesData";
                                System.out.print("Insert Daily Yes=======");
                                for (int i = 0; i < jArray.length(); i++) {
                                    System.out.print("Insert Daily For=======");
                                    json_data = jArray.getJSONObject(i);
                                    ContentValues cv = new ContentValues();
                                    cv.put("id", json_data.getString("id"));
                                    cv.put("gameid", json_data.getString("gameid"));
                                    cv.put("levelid", json_data.getString("levelid"));
                                    cv.put("letters", json_data.getString("letters"));
                                    String newName = json_data.getString("answer").replaceAll(" ", "");
                                    cv.put("answer", newName);
                                    cv.put("hints", json_data.getString("hints"));
                                    cv.put("imagename", json_data.getString("imagename"));
                                    cv.put("isfinish", "0");
                                    if (daily.equals("ord")) {
                                        cv.put("isdownload", "1");
                                        myDbHelper.insert_data("maintable", null, cv);
                                    } else {
                                        cv.put("date", json_data.getString("date"));
                                        myDbHelper.insert_data("dailytest", null, cv);
                                    }
                                    if (i == (jArray.length() - 1)) {
                                        if (exists("https://nithra.mobi/solliadi/" + sps.getString(New_Main_Activity.this, "email") + "-filename.zip")) {
                                            checkmemory();
                                        } else {
                                            System.out.print("daily image no============");
                                        }
                                    }

                                }
                            }
                        }
                    } else {
                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%null null");
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                } catch (android.net.ParseException e1) {
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Utils.mProgress.dismiss();
                if (downcheck == 2) {
                    new AlertDialog.Builder(New_Main_Activity.this)
                            /*.setTitle("Delete entry")*/.setMessage("பதிவுகள் ஏதும் இல்லை .பிறகு முயற்சிக்கவும் ").setPositiveButton("சரி", (dialog, which) -> {

                    }).setIcon(android.R.drawable.ic_dialog_alert).show();

                    downcheck = 0;

                } else {

                    new AlertDialog.Builder(New_Main_Activity.this)
                            /*.setTitle("Delete entry")*/.setMessage("புதிய பதிவுகள் ஏற்றப்பட்டது. விளையாடி மகிழவும்.   ").setPositiveButton("சரி", (dialog, which) -> {

                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
                    downcheck = 0;
                    downok = "";
                    downnodata = "";


                }


            }
        }.execute();
    }

    public void checkmemory() {
        String url = "";
        url = "https://nithra.mobi/solliadi/" + sps.getString(New_Main_Activity.this, "email") + "-filename.zip";


        URL uri = null;
        try {
            uri = new URL(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        URLConnection connection = null;
        try {
            connection = uri.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int length = connection.getContentLength();

        String hrSize = "";
        DecimalFormat dec = new DecimalFormat("0.00");
        double filesize = (length) * 3 / 1204;


        if (length > 0) {

            hrSize = dec.format(filesize).concat(" KB");
            System.out.println("KB:--" + hrSize);

        }


        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdAvailSize = (double) stat.getAvailableBlocks() * (double) stat.getBlockSize();

        double gigaAvailable = sdAvailSize / 1073741824;
        double megaaAvailable = sdAvailSize / (1024 * 1024);
        double kiloAvailable = sdAvailSize / 1024;
        if (sdAvailSize > 0) {
            hrSize = dec.format(kiloAvailable).concat(" KB");
            System.out.println("KB:--" + hrSize);
        }
        if (sdAvailSize > 0) {
            hrSize = dec.format(megaaAvailable).concat(" MB");
            System.out.println("MB:--" + hrSize);

        }

        if (sdAvailSize > 0) {

            hrSize = dec.format(gigaAvailable).concat(" GB");
            System.out.println("GB:--" + hrSize);

        }

        if (filesize <= kiloAvailable) {

            //startdownload();
            startDownload();
        } else {

            goappmanager();
        }


    }

    public void goappmanager() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext(), android.R.style.Theme_Dialog);
        builder1.setMessage("No free space clean your storage");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Ok", (dialog, id) -> {

            Intent i = new Intent(Intent.ACTION_MANAGE_PACKAGE_STORAGE);
            startActivity(i);

        });
        builder1.setNegativeButton("Later", (dialog, id) -> dialog.cancel());
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void startDownload() {


        String str_url = "https://nithra.mobi/solliadi/" + sps.getString(New_Main_Activity.this, "email") + "-filename.zip";

        //     new DownloadFileAsync().execute(str_url);


        downloadFileAsync = new DownloadFileAsync();
        downloadFileAsync.execute(str_url);
    }

    protected Dialog onCreateDialog(int id) {

        if (id == DIALOG_DOWNLOAD_PROGRESS) {
            nProgressDialog = new ProgressDialog(this);
            nProgressDialog.setMessage("படங்கள் பதிவிறக்கம் செய்யப்படுகிறது காத்திருக்கவும்.... ");
            nProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            nProgressDialog.setCancelable(false);
            nProgressDialog.show();
            // playy();

            return nProgressDialog;
        }
        return null;
    }

    public void deletezip() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("filename", sps.getString(New_Main_Activity.this, "email") + "-filename.zip"));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/solliadi1.php");
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
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();

                    System.out.print("Result============123" + result);

                } catch (Exception e) {
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }

        }.execute();

    }

    public int unpackZip(String ZIP_FILE_NAME) {
        InputStream is;
        ZipInputStream zis;
        try {

            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Nithra/solliadi/";
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
            return 0;
        }
        return 1;
    }


    public void gamestatus() {
        String level1, level2, level3, level4, coins = null, l_points = null;
        Cursor lastid1 = myDbHelper.getQry("select * from maintable where gameid=1 and isfinish='1'order by levelid desc");

        if (lastid1 != null && lastid1.moveToFirst()) {
            level1 = String.valueOf(lastid1.getInt(lastid1.getColumnIndexOrThrow("levelid")));
        } else {
            level1 = "-1";
        }

        Cursor lastid2 = myDbHelper.getQry("select * from maintable where gameid=2 and isfinish='1'order by levelid desc");
        if (lastid2 != null && lastid2.moveToFirst()) {
            level2 = String.valueOf(lastid2.getInt(lastid2.getColumnIndexOrThrow("levelid")));
        } else {
            level2 = "-1";
        }

        Cursor lastid3 = myDbHelper.getQry("select * from maintable where gameid=3 and isfinish='1'order by levelid desc");
        if (lastid3 != null && lastid3.moveToFirst()) {
            level3 = String.valueOf(lastid3.getInt(lastid3.getColumnIndexOrThrow("levelid")));
        } else {
            level3 = "-1";
        }
        Cursor lastid4 = myDbHelper.getQry("select * from maintable where gameid=4 and isfinish='1'order by levelid desc");
        if (lastid4 != null && lastid4.moveToFirst()) {
            level4 = String.valueOf(lastid4.getInt(lastid4.getColumnIndexOrThrow("levelid")));
        } else {
            level4 = "-1";
        }
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        if (cfx != null && cfx.moveToFirst()) {
            coins = String.valueOf(cfx.getInt(cfx.getColumnIndexOrThrow("coins")));
            l_points = String.valueOf(cfx.getInt(cfx.getColumnIndexOrThrow("l_points")));
        }


        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://www.nithra.mobi/solliadi/gamedata.php");
        try {
            // i=i+5;
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
            // Get the deviceID

            String email = sps.getString(New_Main_Activity.this, "email");
    /*        if (email.equals("")) {

                AccountManager am = AccountManager.get(New_Main_Activity.this);
                // Account[] accounts =
                // AccountManager.get(getBaseContext()).getAccounts();
                Account[] accounts = am.getAccountsByType("com.google");

                if (accounts.length > 0) {
                    email = accounts[0].name;
                }

                System.out.println("email ==============" + email);
            }*/

            String vcode = String.valueOf(vercode);
            // String letter= URLDecoder.decode(feedback,"UTF-8");
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("uid", Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID)));
            nameValuePairs.add(new BasicNameValuePair("lastid1", level1));
            nameValuePairs.add(new BasicNameValuePair("lastid2", level2));
            nameValuePairs.add(new BasicNameValuePair("lastid3", level3));
            nameValuePairs.add(new BasicNameValuePair("lastid4", level4));
            nameValuePairs.add(new BasicNameValuePair("vcode", vcode));
            nameValuePairs.add(new BasicNameValuePair("coin", coins));
            nameValuePairs.add(new BasicNameValuePair("score", l_points));
            String date = sps.getString(New_Main_Activity.this, "date");


            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                Log.e("HttpResponse", line);
            }

        } catch (IOException e) {

        }


    }

    public void dialog() {
        final Dialog openDialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.earncoin);


        RelativeLayout wp = (RelativeLayout) openDialog.findViewById(R.id.earnwa);
        RelativeLayout fb = (RelativeLayout) openDialog.findViewById(R.id.earnfb);
        RelativeLayout gplus = (RelativeLayout) openDialog.findViewById(R.id.earngplus);

        TextView cancel = (TextView) openDialog.findViewById(R.id.cancel);
        TextView ss = (TextView) openDialog.findViewById(R.id.ssss);

        ss.setOnClickListener(v -> openDialog.cancel());
        cancel.setOnClickListener(v -> openDialog.cancel());


        RelativeLayout video = (RelativeLayout) openDialog.findViewById(R.id.earnvideo);
        video.setOnClickListener(v -> {
            extra_coin_s = 0;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    rewardedAd.showAd();

                    openDialog.dismiss();
                } else {
                    new Handler().postDelayed(() -> {
                        reward_progressBar.dismiss();
                        if (fb_reward == 1) {
                            rewardedAd.showAd();
                            // mShowVideoButton.setVisibility(View.VISIBLE);
                        } else {
                            //reward(context);
                            rewarded_ad();
                            Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);


                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }


        });

        wp.setOnClickListener(view -> {
            onresume_start = 1;
            if (isNetworkAvailable()) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {

                    openDialog.dismiss();
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.whatsapp");
                    String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    i.putExtra(Intent.EXTRA_TEXT, msg);

                    startActivityForResult(i, 12);
                } else {
                    Toast.makeText(getApplicationContext(), "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // toast("இணையதள சேவையை சரிபார்க்கவும் ");
            }
        });
        fb.setOnClickListener(view -> {
          /*  if (isNetworkAvailable()) {


                btn_str = "invite";
                if (isLoggedIn()) {
                    Bundle params = new Bundle();
                    params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                            "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                    showDialogWithoutNotificationBarInvite("apprequests",
                            params);
                    // toast("yes");
                } else {
                   // openFacebookSession();
                    // toast("no");
                }


            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }   // toast("இணையதள சேவையை சரிபார்க்கவும் ");*/
        });
        gplus.setOnClickListener(view -> {
            if (isNetworkAvailable()) {
                final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                if (appinstalled) {
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

        });
        openDialog.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    //*********************reward videos process 3***********************

    @Override
    public void onDestroy() {
        super.onDestroy();
        //uiHelper.onDestroy();
        if (openDialogterm != null) {
            openDialogterm.dismiss();
            openDialogterm = null;
        }
        if (rewardedAd != null) {
            rewardedAd.destroy();
            rewardedAd = null;
        }
        if (advieww != null) {
            advieww.destroy();
        }

    }


    //reward videos***********************//

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


    public void userstates_send() {


        if (sps.getString(New_Main_Activity.this, "complite_reg").equals("yes")) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            String date = null;
            String mobileno = null;
            String reg_id = null, email = null, android_id = null;
            String dgame1 = null, dgame2 = null, dgame3 = null, dgame4 = null, dscore = null, dplaytime = null;
            String rgame1 = null, rgame2 = null, rgame3 = null, rgame4 = null, rscore = null, rplaytime = null;
            String share_count = null;


            String dates = "";
            String dgame1s = "", dgame2s = "", dgame3s = "", dgame4s = "", dscores = "", dplaytimes = "";
            String rgame1s = "", rgame2s = "", rgame3s = "", rgame4s = "", rscores = "", rplaytimes = "";
            String share_counts = "";
            String up_date = "";

            Cursor sc3 = myDbHelper.getQry("select * from userdetail");
            sc3.moveToFirst();
            if (sc3.getCount() != 0) {
                mobileno = sc3.getString(sc3.getColumnIndexOrThrow("phno"));
                email = sc3.getString(sc3.getColumnIndexOrThrow("email"));
                reg_id = sc3.getString(sc3.getColumnIndexOrThrow("regid"));
                android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            }


            Calendar calendar2 = Calendar.getInstance();
            int cur_year = calendar2.get(Calendar.YEAR);
            int cur_month = calendar2.get(Calendar.MONTH);
            int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

            String str_month = "" + (cur_month + 1);
            if (str_month.length() == 1) {
                str_month = "0" + str_month;
            }

            String str_day = "" + cur_day;
            if (str_day.length() == 1) {
                str_day = "0" + str_day;
            }
            String std = cur_year + "-" + str_month + "-" + str_day;


            Cursor sc2 = myDbHelper.getQry("select distinct (date) from userdata_r where isfinish=0 and date<'" + std + "' ");

            if (sc2.getCount() != 0) {
                for (int i = 0; i < sc2.getCount(); i++) {
                    sc2.moveToPosition(i);
                    if (sc2.getCount() != 0) {
                        date = sc2.getString(sc2.getColumnIndexOrThrow("date"));
                        dates = date + "," + dates;

                        up_date = up_date + "or date='" + date + "'";
                        // Toast.makeText(New_Main_Activity.this, "dates"+dates, Toast.LENGTH_SHORT).show();

                        Cursor r = myDbHelper.getQry("select * from userdata_r where date ='" + date + "' and type='d'");
                        r.moveToFirst();
                        if (r.getCount() != 0) {
                            for (int j = 0; j < r.getCount(); j++) {

                                dgame1 = r.getString(r.getColumnIndexOrThrow("gm1"));
                                dgame2 = r.getString(r.getColumnIndexOrThrow("gm2"));
                                dgame3 = r.getString(r.getColumnIndexOrThrow("gm3"));
                                dgame4 = r.getString(r.getColumnIndexOrThrow("gm4"));
                                dscore = r.getString(r.getColumnIndexOrThrow("score"));
                                dplaytime = r.getString(r.getColumnIndexOrThrow("playtime"));

                            }

                            dgame1s = dgame1 + "," + dgame1s;
                            dgame2s = dgame2 + "," + dgame2s;
                            dgame3s = dgame3 + "," + dgame3s;
                            dgame4s = dgame4 + "," + dgame4s;
                            dscores = dscore + "," + dscores;
                            dplaytimes = dplaytime + "," + dplaytimes;


                        }

                        Cursor d = myDbHelper.getQry("select * from userdata_r where date ='" + date + "' and type='r'");
                        d.moveToFirst();
                        if (d.getCount() != 0) {
                            for (int j = 0; j < d.getCount(); j++) {

                                rgame1 = d.getString(d.getColumnIndexOrThrow("gm1"));
                                rgame2 = d.getString(d.getColumnIndexOrThrow("gm2"));
                                rgame3 = d.getString(d.getColumnIndexOrThrow("gm3"));
                                rgame4 = d.getString(d.getColumnIndexOrThrow("gm4"));
                                rscore = d.getString(d.getColumnIndexOrThrow("score"));
                                rplaytime = d.getString(d.getColumnIndexOrThrow("playtime"));

                                rgame1s = rgame1 + "," + rgame1s;
                                rgame2s = rgame2 + "," + rgame2s;
                                rgame3s = rgame3 + "," + rgame3s;
                                rgame4s = rgame4 + "," + rgame4s;
                                rscores = rscore + "," + rscores;
                                rplaytimes = rplaytime + "," + rplaytimes;
                            }

                        }

                        Cursor s = myDbHelper.getQry("select * from userdata_r where date ='" + date + "' and type='s'");
                        s.moveToFirst();
                        if (s.getCount() != 0) {
                            for (int j = 0; j < s.getCount(); j++) {
                                share_count = s.getString(s.getColumnIndexOrThrow("score"));

                                share_counts = share_count + "," + share_counts;

                            }
                        }

                    }

                }

                up_date = up_date.substring(3);
                // Toast.makeText(New_Main_Activity.this, ""+up_date, Toast.LENGTH_SHORT).show();
                System.out.println("date==========" + up_date);


                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("androidid", android_id));
                nameValuePairs.add(new BasicNameValuePair("registrationid", reg_id));
                nameValuePairs.add(new BasicNameValuePair("mobileno", mobileno));

                nameValuePairs.add(new BasicNameValuePair("date", dates));
                nameValuePairs.add(new BasicNameValuePair("dgame1", dgame1s));
                nameValuePairs.add(new BasicNameValuePair("dgame2", dgame2s));
                nameValuePairs.add(new BasicNameValuePair("dgame3", dgame3s));
                nameValuePairs.add(new BasicNameValuePair("dgame4", dgame4s));
                nameValuePairs.add(new BasicNameValuePair("dplaytime", dplaytimes));
                nameValuePairs.add(new BasicNameValuePair("dscore", dscores));

                nameValuePairs.add(new BasicNameValuePair("rgame1", rgame1s));
                nameValuePairs.add(new BasicNameValuePair("rgame2", rgame2s));
                nameValuePairs.add(new BasicNameValuePair("rgame3", rgame3s));
                nameValuePairs.add(new BasicNameValuePair("rgame4", rgame4s));
                nameValuePairs.add(new BasicNameValuePair("rplaytime", rplaytimes));
                nameValuePairs.add(new BasicNameValuePair("rscore", rscores));

                nameValuePairs.add(new BasicNameValuePair("share", share_counts));
                String result = null;

                InputStream is = null;
                StringBuilder sb = null;
                System.out.println("date###==========" + up_date);
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/userstatus_prize.php");
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
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();

                    System.out.print("ord Result============" + result);


                } catch (Exception e) {
                }

                try {
                    JSONArray jArray = new JSONArray(result);
                    System.err.println("#######result===" + result);
                    System.out.println("#######===  " + jArray.length());
                    JSONObject json_data = null;
                    //isvalid=""+jArray.length();
                    downok = "" + jArray.length();
                    System.out.print("########insert ord ============" + downok);
                    if (jArray.length() > 0) {
                        json_data = jArray.getJSONObject(0);
                        for (int k = 0; k < jArray.length(); k++) {
                            String results = json_data.getString("Status");
                            System.out.println("=============Status" + results);
                            if (results.equals("success")) {
                                System.out.println("================Updated" + up_date);
                                myDbHelper.executeSql("UPDATE userdata_r SET isfinish=1 WHERE" + up_date + "");
                            }
                            System.out.print("Insert for=======" + up_date);
                            json_data = jArray.getJSONObject(k);

                        }
                    }
                } catch (JSONException e1) {
                } catch (android.net.ParseException e1) {
                }

            }
        }

    }

    public void nextapp(int rm) {

        if (rm == 1) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=nithra.tamilcalender&hl=en")));
        } else if (rm == 2) {


            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.bharathdictionary")));
        } else if (rm == 3) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=nithra.tamilcrosswordpuzzle")));
        } else if (rm == 4) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=nithra.tnpsc")));
        }

    }

    public void alert(int val, String str) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(New_Main_Activity.this);
        alertDialogBuilder.setTitle("சொல்லிஅடி");
        if (val == 0) {
            alertDialogBuilder.setMessage("உங்கள் App பழைய Version-ல் உள்ளது.\n" + "தங்கள் புதிய Version-க்கு மறுக்கிறீர்களா?" + "\n\n" + str).setCancelable(true).setPositiveButton("இல்லை", (dialog, id) -> dialog.cancel()).setNegativeButton("ஆம்", (dialog, id) -> {
                dialog.cancel();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

            });
        } else {
            alertDialogBuilder.setMessage("உங்களது App Latest Version-ல் உள்ளது.\n" + "நீங்கள் தொடர்ந்து பயன்படுத்துங்கள்").setCancelable(true).setPositiveButton("சரி", (dialog, id) -> dialog.cancel());

        }


        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    public void vidcoinearn() {
        onresume_start = 0;
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea = ea + setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                Cursor cfx1 = myDbHelper.getQry("SELECT * FROM score ");
                cfx1.moveToFirst();
                if (cfx1.getCount() != 0) {
                    int kks = cfx1.getInt(cfx1.getColumnIndexOrThrow("coins"));
                    nl_coins_show.setText("" + kks);
                }
                openDialog.dismiss();
                //mCoinCount = 0;
            });
            openDialog.show();
        }
    }

    public void share_earn(int a) {

        final Dialog openDialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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


        ok_y.setOnClickListener(v -> {
            onresume_start = 0;
            openDialog.dismiss();
            nl_coins_show.setText("" + skx);
            //mCoinCount = 0;
        });

        openDialog.show();
    }

    public void newupdate() {

        if (sps.getInt(getApplicationContext(), "vcode") != 0) {
            if (sps.getInt(getApplicationContext(), "update" + Utils.versioncode_get(New_Main_Activity.this)) == 0) {
                if (sps.getInt(getApplicationContext(), "vcode") < Utils.versioncode_get(New_Main_Activity.this)) {

                    final Dialog dialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);

                    dialog.setContentView(R.layout.update);
                    dialog.setCancelable(true);

                    TextView dia_close = (TextView) dialog.findViewById(R.id.dia_close);
                    dia_close.setVisibility(View.VISIBLE);
                    dia_close.setOnClickListener(v -> dialog.dismiss());
                    WebView webview = (WebView) dialog.findViewById(R.id.poruppu_thurapu);

                    webview.loadUrl("file:///android_asset/update.html");

                    webview.setOnLongClickListener(v -> true);
                    dialog.show();

                    dialog.setOnDismissListener(dialog1 -> {

                    });
                    sps.putInt(getApplicationContext(), "update" + Utils.versioncode_get(New_Main_Activity.this), Utils.versioncode_get(New_Main_Activity.this));
                }
            }
        }

    }

    public void newgamecreate_db() {
        System.out.println("*******inside");
        if (sps.getString(New_Main_Activity.this, "newdb_dbcopy_n").equals("")) {
            mPreferences = getSharedPreferences("", MODE_PRIVATE);
            if (mPreferences.getString("newdbcopied_n", "").equals("yes")) {
                //Toast.makeText(getBaseContext(),"second",Toast.LENGTH_SHORT).show();

                newhelper = new Newgame_DataBaseHelper(this);
                try {
                    newhelper.createDataBase();
                    System.out.println("*******createDataBase");
                } catch (IOException ioe) {
                    System.out.println("" + ioe + "*******Unable to create database");
                    throw new Error("Unable to create database");
                }
                try {
                    newhelper.openDataBase();
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }


            } else {
                //Toast.makeText(getBaseContext(),"first",Toast.LENGTH_SHORT).show();

                sharedPrefAdd("newdbcopied_n", "yes", mPreferences);
                newhelper = new Newgame_DataBaseHelper(this);
                try {
                    try {
                        newhelper.copyDataBase();
                    } catch (IOException ew) {
                        // TODO Auto-generated catch block
                        ew.printStackTrace();
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                try {
                    try {
                        newhelper.createDataBase();
                        System.out.println("*******createDataBase");
                    } catch (IOException ioe) {
                        System.out.println("" + ioe + "*******Unable to create database");
                        throw new Error("*******Unable to create database");
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                try {
                    try {
                        newhelper.openDataBase();
                        System.out.println("*******openDataBase");

                    } catch (SQLException sqle) {
                        throw sqle;
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                //  dbmovefirst();


            }

        }

    }

    public void newgamecreate_db2() {
        System.out.println("*******inside");
        if (sps.getString(New_Main_Activity.this, "newdb_dbcopy_n2").equals("")) {
            mPreferences = getSharedPreferences("", MODE_PRIVATE);
            if (mPreferences.getString("newdbcopied_n2", "").equals("yes")) {
                //Toast.makeText(getBaseContext(),"second",Toast.LENGTH_SHORT).show();

                newhelper2 = new Newgame_DataBaseHelper2(this);
                try {
                    newhelper2.createDataBase();
                    System.out.println("*******createDataBase");
                } catch (IOException ioe) {
                    System.out.println("" + ioe + "*******Unable to create database");
                    throw new Error("Unable to create database");
                }
                try {
                    newhelper2.openDataBase();
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }


            } else {
                //Toast.makeText(getBaseContext(),"first",Toast.LENGTH_SHORT).show();

                sharedPrefAdd("newdbcopied_n2", "yes", mPreferences);
                newhelper2 = new Newgame_DataBaseHelper2(this);
                try {
                    try {
                        newhelper2.copyDataBase();
                    } catch (IOException ew) {
                        // TODO Auto-generated catch block
                        ew.printStackTrace();
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }

                try {
                    try {
                        newhelper2.createDataBase();
                        System.out.println("*******createDataBase");
                    } catch (IOException ioe) {
                        System.out.println("" + ioe + "*******Unable to create database");
                        throw new Error("*******Unable to create database");
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                try {
                    try {
                        newhelper2.openDataBase();
                        System.out.println("*******openDataBase");

                    } catch (SQLException sqle) {
                        throw sqle;
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                //  dbmovefirst();
            }

        }

    }

    public void newgamecreate_db3() {
        System.out.println("*******inside");
        if (sps.getString(New_Main_Activity.this, "newdb_dbcopy_n3").equals("")) {
            mPreferences = getSharedPreferences("", MODE_PRIVATE);
            if (mPreferences.getString("newdbcopied_n3", "").equals("yes")) {
                //Toast.makeText(getBaseContext(),"second",Toast.LENGTH_SHORT).show();


                newhelper3 = new Newgame_DataBaseHelper3(this);
                try {
                    newhelper3.createDataBase();
                    System.out.println("*******createDataBase");
                } catch (IOException ioe) {
                    System.out.println("" + ioe + "*******Unable to create database");
                    throw new Error("Unable to create database");
                }
                try {
                    newhelper3.openDataBase();
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }


            } else {
                //Toast.makeText(getBaseContext(),"first",Toast.LENGTH_SHORT).show();

                sharedPrefAdd("newdbcopied_n3", "yes", mPreferences);
                newhelper3 = new Newgame_DataBaseHelper3(this);
                try {
                    try {
                        newhelper3.copyDataBase();
                    } catch (IOException ew) {
                        // TODO Auto-generated catch block
                        ew.printStackTrace();
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                try {
                    try {
                        newhelper3.createDataBase();
                        System.out.println("*******createDataBase");
                    } catch (IOException ioe) {
                        System.out.println("" + ioe + "*******Unable to create database");
                        throw new Error("*******Unable to create database");
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                try {
                    try {
                        newhelper3.openDataBase();
                        System.out.println("*******openDataBase");

                    } catch (SQLException sqle) {
                        throw sqle;
                    }
                    System.out.println("*******openDataBase");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                //  dbmovefirst();


            }

        }

    }

    public void newgamecreate_db4() {
        System.out.println("*******inside");
        if (sps.getString(New_Main_Activity.this, "newdb_dbcopy_n4").equals("")) {
            mPreferences = getSharedPreferences("", MODE_PRIVATE);
            if (mPreferences.getString("newdbcopied_n4", "").equals("")) {
                //Toast.makeText(getBaseContext(),"second",Toast.LENGTH_SHORT).show();
                newhelper4 = new Newgame_DataBaseHelper4(this);
                try {
                    newhelper4.createDataBase();
                    System.out.println("*******createDataBase4");
                } catch (IOException ioe) {
                    System.out.println("" + ioe + "*******Unable to create database4");
                    throw new Error("Unable to create database4");
                }
                try {
                    newhelper4.openDataBase();
                    System.out.println("*******openDataBase4");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                sharedPrefAdd("newdbcopied_n4", "yes", mPreferences);

            }

        }

    }
//** In launcher Activity **

    public void newgamecreate_db5() {
        System.out.println("*******inside");
        if (sps.getString(New_Main_Activity.this, "newdb_dbcopy_n5").equals("")) {
            mPreferences = getSharedPreferences("", MODE_PRIVATE);
            if (mPreferences.getString("newdbcopied_n5", "").equals("")) {
                //Toast.makeText(getBaseContext(),"second",Toast.LENGTH_SHORT).show();
                newhelper5 = new Newgame_DataBaseHelper5(this);
                try {
                    newhelper5.createDataBase();
                    System.out.println("*******createDataBase4");
                } catch (IOException ioe) {
                    System.out.println("" + ioe + "*******Unable to create database4");
                    throw new Error("Unable to create database4");
                }
                try {
                    newhelper5.openDataBase();
                    System.out.println("*******openDataBase4");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                sharedPrefAdd("newdbcopied_n5", "yes", mPreferences);

            }

        }

    }

    public void newgamecreate_db6() {
        System.out.println("*******inside");
        if (sps.getString(New_Main_Activity.this, "newdb_dbcopy_n6").equals("")) {
            mPreferences = getSharedPreferences("", MODE_PRIVATE);
            if (mPreferences.getString("newdbcopied_n6", "").equals("")) {
                //Toast.makeText(getBaseContext(),"second",Toast.LENGTH_SHORT).show();
                newhelper6 = new Newgame_DataBaseHelper6(this);
                try {
                    newhelper6.createDataBase();
                    System.out.println("*******createDataBase6");
                } catch (IOException ioe) {
                    System.out.println("" + ioe + "*******Unable to create database6");
                    throw new Error("Unable to create database6");
                }
                try {
                    newhelper6.openDataBase();
                    System.out.println("*******openDataBase6");

                } catch (SQLException sqle) {
                    throw sqle;
                }
                sharedPrefAdd("newdbcopied_n6", "yes", mPreferences);

            }

        }

    }


    //*** In Adapter **

    private void showPrivacy() {
        String url = "https://www.nithra.mobi/privacy.php";

        Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.privacy);
        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
        final WebView webView = (WebView) dialog.findViewById(R.id.webView);
        //webView.setVisibility(View.VISIBLE);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                    //webView.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.loadUrl(url);
        webView.setOnLongClickListener(view -> true);
        dialog.show();
    }

    public void downloaddata() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(New_Main_Activity.this);                            /*.setTitle("Delete entry")*/
        alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய வேண்டுமா ?").setPositiveButton("ஆம்", (dialog, which) -> {
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                //DownLoad Letters and Words
                Cursor c1 = myDbHelper.getQry("select id from maintable order by id DESC");
                c1.moveToFirst();
                if (c1.getCount() != 0) {
                    System.out.print("Last ID===ord=" + c1.getString(c1.getColumnIndexOrThrow("id")));
                    downloadcheck("" + c1.getString(c1.getColumnIndexOrThrow("id")), "ord");
                    dialog.dismiss();
                } else {
                    downloadcheck("0", "ord");
                    dialog.dismiss();
                }
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }

        }).setNegativeButton("இல்லை", (dialog, which) -> dialog.dismiss()).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public void prize_enable() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(3600).build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        String adVal = "";
        try {
            adVal = mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY2);
        } catch (Exception e) {
            adVal = "1";
        }

        final String finalAdVal = adVal;
        System.out.println("################====Remoteee" + adVal);
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                boolean updated = task.getResult();
                if (!finalAdVal.equals("")) {
                    sps.putInt(getApplicationContext(), "remoteConfig_prize", Integer.parseInt(finalAdVal));
                }
                if (sp.getInt(New_Main_Activity.this, "remoteConfig_prize") == 1) {
                    // prices.setVisibility(View.VISIBLE);
                    prize_lay.setVisibility(View.VISIBLE);
                } else {
                    //  prices.setVisibility(View.GONE);
                    prize_lay.setVisibility(View.GONE);
                }

            } else {

            }

        });

    }

    private void remoteConfig() {
        System.out.println("==============================remote");
        sps.putInt(New_Main_Activity.this, "remote_config_first", 1);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 7);
        Date laterDate = c.getTime();
        sps.putString(New_Main_Activity.this, "remote_config_date", dateFormat.format(laterDate));
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(3600).build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        String adVal = "";
        try {
            adVal = mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY);
        } catch (Exception e) {
            adVal = "1";
        }
        // System.out.println("====================Remote Config" + mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));
        System.out.println("################====Remote" + adVal);
        //sps.putInt(this, "remoteConfig", Integer.parseInt(adVal));

    /*    long cacheExpiration = 3600; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }*/
        final String finalAdVal = adVal;
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                boolean updated = task.getResult();
                if (!finalAdVal.equals("")) {
                    sps.putInt(getApplicationContext(), "remoteConfig", Integer.parseInt(finalAdVal));
                } else {
                    sps.putInt(getApplicationContext(), "remoteConfig", 1);
                }

            } else {
                sps.putInt(getApplicationContext(), "remoteConfig", 1);

            }

        });

 /*       mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(New_Main_Activity.this, "Succeeded " + finalAdVal, Toast.LENGTH_SHORT).show();
                            sps.putInt(getApplicationContext(), "remoteConfig", Integer.parseInt(finalAdVal));
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            sps.putInt(getApplicationContext(), "remoteConfig", 1);
                            // Toast.makeText(New_Main_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });*/
    }

    /**
     * Get the data from the EditText.
     *
     * @return the String in the EditText, or "" if empty.
     */
    private String getData() {

        newhelper4 = new Newgame_DataBaseHelper4(context);
        newhelper6 = new Newgame_DataBaseHelper6(context);
        Cursor g1 = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='1' order by id desc limit 1");
        Cursor g2 = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='1' order by id desc limit 1");
        Cursor g3 = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='1' order by id desc limit 1");
        Cursor g4 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='1' order by id desc limit 1");
        Cursor g5 = newhelper.getQry("select * from newmaintable where gameid='5' and isfinish='1' order by id desc limit 1");
        Cursor g6 = newhelper.getQry("select * from newmaintable where gameid='6' and isfinish='1' order by id desc limit 1");

        Cursor g7 = newhelper2.getQry("select * from newmaintable2 where gameid='7' and isfinish='1' order by id desc limit 1");
        Cursor g8 = newhelper2.getQry("select * from newmaintable2 where gameid='8' and isfinish='1' order by id desc limit 1");

        Cursor g9 = newhelper3.getQry("select * from right_order where gameid='9' and isfinish='1' order by id desc limit 1");
        Cursor g10 = newhelper3.getQry("select * from right_order where gameid='10' and isfinish='1' order by id desc limit 1");
        Cursor g11 = newhelper3.getQry("select * from right_order where gameid='11' and isfinish='1' order by id desc limit 1");
        Cursor g12 = newhelper3.getQry("select * from right_order where gameid='12' and isfinish='1' order by id desc limit 1");

        Cursor g13 = newhelper4.getQry("select * from newgamesdb4 where gameid='13' and isfinish='1' order by id desc limit 1");
        //  Cursor g14 = newhelper4.getQry("select * from newgamesdb4 where gameid='14' and isfinish='1' order by id desc limit 1");

        Cursor g14 = newhelper5.getQry("select * from newgames5 where gameid='15' and isfinish='1' order by id desc limit 1");
        Cursor g15 = newhelper5.getQry("select * from newgames5 where gameid='16' and isfinish='1' order by id desc limit 1");
        Cursor g16 = newhelper5.getQry("select * from newgames5 where gameid='17' and isfinish='1' order by id desc limit 1");

        Cursor g17 = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='1' order by id desc limit 1");
        Cursor g18 = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='1' order by id desc limit 1");
        Cursor g19 = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='1' order by id desc limit 1");


        g1.moveToFirst();
        g2.moveToFirst();
        g3.moveToFirst();
        g4.moveToFirst();
        g5.moveToFirst();
        g6.moveToFirst();
        g7.moveToFirst();
        g8.moveToFirst();


        g9.moveToFirst();
        g10.moveToFirst();
        g11.moveToFirst();
        g12.moveToFirst();

        g13.moveToFirst();
        g14.moveToFirst();
        g15.moveToFirst();
        g16.moveToFirst();
        g17.moveToFirst();
        g18.moveToFirst();
        g19.moveToFirst();
        //  g14.moveToFirst();


        Cursor c1 = myDbHelper.getQry("select * from score");
        c1.moveToFirst();

        // int a2,a3,a4;
        String b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19;

        if (g1.getCount() == 0) {
            //a2=00;
            b1 = "no";
        } else {
            // a2 = g2.getInt(g2.getColumnIndexOrThrow("levelid"));
            b1 = String.valueOf(g1.getInt(g1.getColumnIndexOrThrow("levelid")));
        }
        if (g2.getCount() == 0) {
            //a2=00;
            b2 = "no";
        } else {
            // a2 = g2.getInt(g2.getColumnIndexOrThrow("levelid"));
            b2 = String.valueOf(g2.getInt(g2.getColumnIndexOrThrow("levelid")));
        }
        if (g3.getCount() == 0) {
            //  a3=00;
            b3 = "no";
        } else {
            //  a3 = g3.getInt(g3.getColumnIndexOrThrow("levelid"));
            b3 = String.valueOf(g3.getInt(g3.getColumnIndexOrThrow("levelid")));
        }
        if (g4.getCount() == 0) {
            // a4=00;
            b4 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b4 = String.valueOf(g4.getInt(g4.getColumnIndexOrThrow("levelid")));
        }

        if (g5.getCount() == 0) {
            // a4=00;
            b5 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b5 = String.valueOf(g5.getInt(g5.getColumnIndexOrThrow("questionid")));
        }


        if (g6.getCount() == 0) {
            // a4=00;
            b6 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b6 = String.valueOf(g6.getInt(g6.getColumnIndexOrThrow("questionid")));
        }


        if (g7.getCount() == 0) {
            // a4=00;
            b7 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b7 = String.valueOf(g7.getInt(g7.getColumnIndexOrThrow("questionid")));
        }

        if (g8.getCount() == 0) {
            // a4=00;
            b8 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b8 = String.valueOf(g8.getInt(g8.getColumnIndexOrThrow("questionid")));
        }


        if (g9.getCount() == 0) {
            // a4=00;
            b9 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b9 = String.valueOf(g9.getInt(g9.getColumnIndexOrThrow("questionid")));
        }

        if (g10.getCount() == 0) {
            // a4=00;
            b10 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b10 = String.valueOf(g10.getInt(g10.getColumnIndexOrThrow("questionid")));
        }

        if (g11.getCount() == 0) {
            // a4=00;
            b11 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b11 = String.valueOf(g11.getInt(g11.getColumnIndexOrThrow("questionid")));
        }

        if (g12.getCount() == 0) {
            // a4=00;
            b12 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b12 = String.valueOf(g12.getInt(g12.getColumnIndexOrThrow("questionid")));
        }

        if (g13.getCount() == 0) {
            // a4=00;
            b13 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b13 = String.valueOf(g13.getInt(g13.getColumnIndexOrThrow("levelid")));
        }

        if (g14.getCount() == 0) {
            // a4=00;
            b14 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b14 = String.valueOf(g14.getInt(g14.getColumnIndexOrThrow("questionid")));
        }

        if (g15.getCount() == 0) {
            // a4=00;
            b15 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b15 = String.valueOf(g15.getInt(g15.getColumnIndexOrThrow("questionid")));
        }

        if (g16.getCount() == 0) {
            // a4=00;
            b16 = "no";
        } else {
            // a4 = g4.getInt(g4.getColumnIndexOrThrow("levelid"));
            b16 = String.valueOf(g16.getInt(g16.getColumnIndexOrThrow("questionid")));
        }

        if (g17.getCount() == 0) {
            // a4=00;
            b17 = "no";
        } else {
            b17 = String.valueOf(g17.getInt(g17.getColumnIndexOrThrow("questionid")));
        }

        if (g18.getCount() == 0) {
            b18 = "no";
        } else {
            b18 = String.valueOf(g18.getInt(g18.getColumnIndexOrThrow("questionid")));
        }

        if (g19.getCount() == 0) {
            b19 = "no";
        } else {
            b19 = String.valueOf(g19.getInt(g19.getColumnIndexOrThrow("questionid")));
        }


        String upload = b1 + "#" + b2 + "#" + b3 + "#" + b4 + "#" + c1.getInt(c1.getColumnIndexOrThrow("coins")) + "#" + c1.getInt(c1.getColumnIndexOrThrow("l_points")) + "#" + b5 + "#" + b6 + "#" + b7 + "#" + b8 + "#" + b9 + "#" + b10 + "#" + b11 + "#" + b12 + "#" + b13 + "#" + b14 + "#" + b15 + "#" + b16 + "#" + b17 + "#" + b18 + "#" + b19;
        System.out.println("#########################upload data" + upload);
        return upload;
    }

    /**
     * Replace the data displaying in the EditText.
     *
     * @param data the String to display.
     */
    private void setData(String data) {
        // EditText dataEditText = (EditText) findViewById(R.id.edit_game_data);
        newhelper4 = new Newgame_DataBaseHelper4(context);
        newhelper5 = new Newgame_DataBaseHelper5(context);
        newhelper6 = new Newgame_DataBaseHelper6(context);
        if (data == null) {
            // dataEditText.setText("");
            //  Toast.makeText(context, "சேமித்த தரவுகள் ஏதும் இல்லை ", Toast.LENGTH_SHORT).show();
        } else {

            //  dataEditText.setText(data);
            System.out.println("=====================saved data" + data);
            String[] first = data.split("\\#");
            int length = first.length;
            System.out.println("=======================length" + length);
            // Toast.makeText(New_Main_Activity.this, "length"+length, Toast.LENGTH_SHORT).show();
            if (length == 6) {
                if (!first[0].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='1' and levelid<='" + first[0] + "'");
                }
                if (!first[1].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='2' and levelid<='" + first[1] + "'");
                }
                if (!first[2].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='3' and levelid<='" + first[2] + "'");
                }
                if (!first[3].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='4' and levelid<='" + first[3] + "'");

                }
                myDbHelper.executeSql("UPDATE score SET coins='" + first[4] + "'");
                myDbHelper.executeSql("UPDATE score SET l_points='" + first[5] + "'");
            } else if (length == 10) {

                if (!first[0].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='1' and levelid<='" + first[0] + "'");
                }
                if (!first[1].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='2' and levelid<='" + first[1] + "'");
                }
                if (!first[2].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='3' and levelid<='" + first[2] + "'");
                }
                if (!first[3].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='4' and levelid<='" + first[3] + "'");
                }
                if (!first[6].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='5' and questionid<='" + first[6] + "'");
                }
                if (!first[7].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='6' and questionid<='" + first[7] + "'");
                }

                if (!first[8].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='7' and questionid<='" + first[8] + "'");
                }


                myDbHelper.executeSql("UPDATE score SET coins='" + first[4] + "'");
                myDbHelper.executeSql("UPDATE score SET l_points='" + first[5] + "'");

            } else if (length == 14) {

                if (!first[0].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='1' and levelid<='" + first[0] + "'");
                }
                if (!first[1].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='2' and levelid<='" + first[1] + "'");
                }
                if (!first[2].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='3' and levelid<='" + first[2] + "'");
                }
                if (!first[3].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='4' and levelid<='" + first[3] + "'");
                }
                if (!first[6].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='5' and questionid<='" + first[6] + "'");
                }
                if (!first[7].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='6' and questionid<='" + first[7] + "'");
                }
                if (!first[8].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='7' and questionid<='" + first[8] + "'");
                }
                if (!first[9].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='8' and questionid<='" + first[9] + "'");
                }
                if (!first[10].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='9' and questionid<='" + first[10] + "'");
                }
                if (!first[11].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='10' and questionid<='" + first[11] + "'");
                }
                if (!first[12].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='11' and questionid<='" + first[12] + "'");
                }
                if (!first[13].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='12' and questionid<='" + first[13] + "'");
                }

                myDbHelper.executeSql("UPDATE score SET coins='" + first[4] + "'");
                myDbHelper.executeSql("UPDATE score SET l_points='" + first[5] + "'");
            } else if (length == 15) {
                if (!first[0].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='1' and levelid<='" + first[0] + "'");
                }
                if (!first[1].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='2' and levelid<='" + first[1] + "'");
                }
                if (!first[2].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='3' and levelid<='" + first[2] + "'");
                }
                if (!first[3].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='4' and levelid<='" + first[3] + "'");
                }
                if (!first[6].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='5' and questionid<='" + first[6] + "'");
                }
                if (!first[7].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='6' and questionid<='" + first[7] + "'");
                }
                if (!first[8].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='7' and questionid<='" + first[8] + "'");
                }
                if (!first[9].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='8' and questionid<='" + first[9] + "'");
                }
                if (!first[10].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='9' and questionid<='" + first[10] + "'");
                }
                if (!first[11].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='10' and questionid<='" + first[11] + "'");
                }
                if (!first[12].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='11' and questionid<='" + first[12] + "'");
                }
                if (!first[13].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='12' and questionid<='" + first[13] + "'");
                }
                if (!first[14].equals("no")) {
                    newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE gameid='13' and levelid<='" + first[14] + "'");
                }
               /* if (!first[15].equals("no")) {
                    newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE gameid='14' and levelid<='" + first[15] + "'");
                }*/

                myDbHelper.executeSql("UPDATE score SET coins='" + first[4] + "'");
                myDbHelper.executeSql("UPDATE score SET l_points='" + first[5] + "'");
            } else if (length == 18) {
                if (!first[0].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='1' and levelid<='" + first[0] + "'");
                }
                if (!first[1].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='2' and levelid<='" + first[1] + "'");
                }
                if (!first[2].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='3' and levelid<='" + first[2] + "'");
                }
                if (!first[3].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='4' and levelid<='" + first[3] + "'");
                }
                if (!first[6].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='5' and questionid<='" + first[6] + "'");
                }
                if (!first[7].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='6' and questionid<='" + first[7] + "'");
                }
                if (!first[8].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='7' and questionid<='" + first[8] + "'");
                }
                if (!first[9].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='8' and questionid<='" + first[9] + "'");
                }
                if (!first[10].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='9' and questionid<='" + first[10] + "'");
                }
                if (!first[11].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='10' and questionid<='" + first[11] + "'");
                }
                if (!first[12].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='11' and questionid<='" + first[12] + "'");
                }
                if (!first[13].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='12' and questionid<='" + first[13] + "'");
                }
                if (!first[14].equals("no")) {
                    newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE gameid='13' and levelid<='" + first[14] + "'");
                }
               /* if (!first[15].equals("no")) {
                    newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE gameid='14' and levelid<='" + first[15] + "'");
                }*/
                if (!first[15].equals("no")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='15' and questionid<='" + first[15] + "'");
                }
                if (!first[16].equals("no")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='16' and questionid<='" + first[16] + "'");
                }
                if (!first[17].equals("no")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='17' and questionid<='" + first[17] + "'");
                }


                myDbHelper.executeSql("UPDATE score SET coins='" + first[4] + "'");
                myDbHelper.executeSql("UPDATE score SET l_points='" + first[5] + "'");
            } else {
                if (!first[0].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='1' and levelid<='" + first[0] + "'");
                }
                if (!first[1].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='2' and levelid<='" + first[1] + "'");
                }
                if (!first[2].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='3' and levelid<='" + first[2] + "'");
                }
                if (!first[3].equals("no")) {
                    myDbHelper.executeSql("UPDATE maintable SET isfinish='1' WHERE gameid='4' and levelid<='" + first[3] + "'");
                }
                if (!first[6].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='5' and questionid<='" + first[6] + "'");
                }
                if (!first[7].equals("no")) {
                    newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE gameid='6' and questionid<='" + first[7] + "'");
                }
                if (!first[8].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='7' and questionid<='" + first[8] + "'");
                }
                if (!first[9].equals("no")) {
                    newhelper2.executeSql("UPDATE newmaintable2 SET isfinish='1' WHERE gameid='8' and questionid<='" + first[9] + "'");
                }
                if (!first[10].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='9' and questionid<='" + first[10] + "'");
                }
                if (!first[11].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='10' and questionid<='" + first[11] + "'");
                }
                if (!first[12].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='11' and questionid<='" + first[12] + "'");
                }
                if (!first[13].equals("no")) {
                    newhelper3.executeSql("UPDATE right_order SET isfinish='1' WHERE gameid='12' and questionid<='" + first[13] + "'");
                }
                if (!first[14].equals("no")) {
                    newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE gameid='13' and levelid<='" + first[14] + "'");
                }
               /* if (!first[15].equals("no")) {
                    newhelper4.executeSql("UPDATE newgamesdb4 SET isfinish='1' WHERE gameid='14' and levelid<='" + first[15] + "'");
                }*/
                if (!first[15].equals("no")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='15' and questionid<='" + first[15] + "'");
                }
                if (!first[16].equals("no")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='16' and questionid<='" + first[16] + "'");
                }
                if (!first[17].equals("no")) {
                    newhelper5.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='17' and questionid<='" + first[17] + "'");
                }

                if (!first[18].equals("no")) {
                    newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='18' and questionid<='" + first[18] + "'");
                }
                if (!first[19].equals("no")) {
                    newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='19' and questionid<='" + first[19] + "'");
                }
                if (!first[20].equals("no")) {
                    newhelper6.executeSql("UPDATE newgames5 SET isfinish='1' WHERE gameid='20' and questionid<='" + first[20] + "'");
                }
                myDbHelper.executeSql("UPDATE score SET coins='" + first[4] + "'");
                myDbHelper.executeSql("UPDATE score SET l_points='" + first[5] + "'");
            }


            settext();

            Toast.makeText(New_Main_Activity.this, "தரவுகள் பதிவேற்றப்பட்டது", Toast.LENGTH_SHORT).show();
        }
    }

    public void settext() {

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
                titt.setText("நித்ராவின் சொல்லிஅடிகள்");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.jobs_logo);
            } else if (!appInstalledOrNot(context, "nithra.tamilcrosswordpuzzle")) {
                titt.setText("தமிழ் குறுக்கெழுத்துப்போட்டி ");
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
                titt.setText("நித்ராவின் சொல்லிஅடிகள்");
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
                titt.setText("நித்ராவின் சொல்லிஅடிகள்");
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
                titt.setText("நித்ராவின் சொல்லிஅடிகள்");
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
                titt.setText("நித்ராவின் சொல்லிஅடிகள்");
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
                titt.setText("நித்ராவின் சொல்லிஅடிகள்");
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
                titt.setText("நித்ராவின் சொல்லிஅடிகள்");
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


    //--------------------------------- Purchase Functions -----------------------------------------

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

    public Animation zoomAnim() {

        ScaleAnimation animation = new ScaleAnimation((float) 0.9, (float) 0.83, (float) 0.9, (float) 0.83, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(500);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }

    public void purchasedialog() {
        final Dialog purchaseDia = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        purchaseDia.setContentView(R.layout.purchase_dialog_lay);
        Button purchase = purchaseDia.findViewById(R.id.purchase);
        Button cuscare = purchaseDia.findViewById(R.id.cuscare);
        TextView review = purchaseDia.findViewById(R.id.review);
        TextView title = purchaseDia.findViewById(R.id.title);
        LinearLayout expLay = purchaseDia.findViewById(R.id.exp_lay);

        System.out.println("#####################purchase_ads value" + sps.getInt(New_Main_Activity.this, "purchase_ads"));
        if (sps.getInt(New_Main_Activity.this, "purchase_ads") != 0) {
            title.setVisibility(View.GONE);
            purchase.setVisibility(View.GONE);
            review.setText("நீங்கள் Purchase செய்து நித்ரா சொல்லிஅடி செயலியை விளம்பரங்கள் இல்லாமல் பயன்படுத்திக்கொண்டிருக்கிறீர்கள்.");
        }

        purchase.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                purchaseDia.dismiss();
                startActivity(new Intent(New_Main_Activity.this, Billing_Activity.class));
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                // L.t(New_Main_Activity.this, U.INA);
            }
        });
        cuscare.setOnClickListener(v -> customerCaredialog());
        purchaseDia.show();
    }

    public void customerCaredialog() {
        final Dialog cusCareDia = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        cusCareDia.setContentView(R.layout.listview);
        ListView list = cusCareDia.findViewById(R.id.listview);
        ImageView back = cusCareDia.findViewById(R.id.back);
        TextView titleTxt = cusCareDia.findViewById(R.id.title);
        titleTxt.setText("            சேவை மையம்");
        List<Item> data = new ArrayList<>();

        back.setOnClickListener(view -> cusCareDia.dismiss());

        data.add(new Item(0, "நான், ஏன் ஆயுள் சந்தா Purchase செய்ய வேண்டும்?", "நித்ரா சொல்லிஅடி செயலியில் ஆயுள் சந்தா Purchase செய்து கொள்வதால் விளம்பரங்கள் (Banner, Full Screen Ads)  அனைத்து " + "பகுதிகளில் இருந்தும் முற்றிலுமாக நீக்கப்படும்."));

        data.add(new Item(0, "Premium Subscription (ஆயுள் சந்தா) செய்வதற்கு நான் எதற்காக E-mail ID-ஐ Play store-ல் Sign-in செய்ய வேண்டும்? " + "அதற்கான அவசியம் என்ன?", "நீங்கள் ஆயுள் சந்தா-வினை Purchase செய்துள்ள தகவல்களை Sign-in செய்து சேமித்து வைப்பதன் வாயிலாக " + "உங்களுடைய ஸ்மார்ட் போனை நீங்கள் மாற்றினாலோ அல்லது நித்ரா சொல்லிஅடி செயலியை Uninstall செய்து விட்டு திரும்பவும் Install செய்யும் போது  " + "Subscription-னை பெறலாம். தவறுதலாக போனையோ அல்லது நித்ரா சொல்லிஅடி செயலியோ Premium Subscription வசதியை இழக்கும் போது அதனை " + "மீண்டும் செயல்பாட்டிற்கு கொண்டு வர மிகப் பயனுள்ளதாக இருக்கும்."));

        data.add(new Item(0, "நான் எனது மொபைல் போனை தொலைத்துவிட்டேன் அல்லது எனது மொபைல் போனை மாற்றிவிட்டேன். ஆயுள் " + "சந்தா வசதியை எனது புது போனில் பயன்படுத்துவதற்கான வாய்ப்புகள் உள்ளதா?", "நிச்சயமாக உள்ளது. நீங்கள் ஆயுள் சந்தா Purchase செய்ய பயன்படுத்திய Google E-mail ID-ஐ புதிய மொபைலில்  " + "Sign-in செய்வதன் வாயிலாக  Premium Plan வசதியை Restore செய்து கொள்ளலாம்."));

        data.add(new Item(0, "நான் இரண்டு ஆண்ட்ராய்டு மொபைல் போனை பயன்படுத்திக்கொண்டிருக்கிறேன். ஒரு தடவை ஆயுள் சந்தா-ஐ  " + "Purchase செய்து இரண்டு மொபைல் போனிலும் பயன்படுத்தலாமா?", "தாராளமாக பயன்படுத்திக்கொள்ளலாம். நீங்கள் இரண்டு மொபைல் போனிலும் ஆயுள் சந்தா செய்ய பயன்படுத்திய " + "Google E-mail ID-ஐ Sign-in செய்வதன் வாயிலாகப் பெறலாம்."));

        data.add(new Item(0, "எனது Credit/Debit கார்டு பற்றிய தகவல்களை கொடுத்து Purchase செய்கிறேன். உண்மையிலேயே இது பாதுகாப்பானதாக இருக்குமா?", "நிச்சயமாக மிகவும் பாதுகாப்பான முறையாகத்தான் இது இருக்கிறது. நீங்கள்  Google வழியாக கட்டணமுறை மேற்கொள்வதால் உங்களது " + "பணபரிவர்த்தனைகளையோ, Credit/Debit கார்டு தகவல்களையோ யாரும் அணுக முடியாது."));

        data.add(new Item(0, "Payment முறையில் என்னுடைய Debit கார்டு தகவல்களை சேர்க்கமுடியவில்லை. “Correct this card info or try a different card” என்ற Error message " + "வந்து கொண்டே இருக்கிறது? இப்போது நான் என்ன செய்ய வேண்டும்?", "பொதுவாகவே Debit கார்டானது உள்நாட்டு (Domestic) பணபரிவர்த்தனைக்காக மட்டுமே பயன்பாட்டில் கொடுக்கப்பட்டிருக்கும். நீங்கள் உங்கள் வங்கியை" + " அணுகி சர்வதேச பயன்பாட்டிற்கான கார்டாக (International) மாற்றிக்கொண்டு பணபரிவர்த்தனையை மேற்கொள்ளலாம்."));

        data.add(new Item(1, "நான் ஆயுள் சந்தாவினை Purchase செய்துவிட்டேன். ஆனாலும், எனக்கு ஆயுள் சந்தா வசதி activate " + "ஆகவில்லை. இப்போது நான் என்ன செய்ய வேண்டும்?", "நீங்கள் Google-ல் Purchase செய்ததற்கான Order-ஐ எங்களின் feedback@nithra.mobi என்ற மின்னஞ்சல் முகவரிக்கு அனுப்பினால் விரைவாக " + "கவனத்தில் கொண்டு சரிசெய்யப்படும்."));


        CustomerCareAdapter adapter = new CustomerCareAdapter(New_Main_Activity.this, R.layout.listview, data);
        list.setAdapter(adapter);

        cusCareDia.show();
    }

    private void successdialog() {


        final Dialog openDialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        ImageView bonus_coin = (ImageView) openDialog.findViewById(R.id.bonus_coin);
        LinearLayout today_coin = (LinearLayout) openDialog.findViewById(R.id.today_coin);
        today_coin.setVisibility(View.INVISIBLE);
        bonus_coin.setVisibility(View.INVISIBLE);
        TextView coin_txt_content = (TextView) openDialog.findViewById(R.id.coin_txt_content);
        coin_txt_content.setText("வெற்றிகரமாக விளம்பரங்கள் நீக்கப்பட்டது.\n நன்றி");
        ok_y.setOnClickListener(v -> {
            finish();
            appRestart();

        });

        openDialog.show();


    }

    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        bld.create().show();
    }

    public void appRestart() {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        if (i != null) {
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(i);
    }

    private void makeCall(String phone) {
        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("tel:" + phone));
        startActivity(callIntent);
    }

    ////////////////////////////////////////////////////////////Word_search_tamil///////////////////////////////////////////////////////////////////////////
    public void dbadd_wordsearch() {
        System.out.println("####################dbadd_wordsearch");
        sp.putString(New_Main_Activity.this, "show_challenge", "");
        Calendar calendar2 = Calendar.getInstance();
        int cur_year = calendar2.get(Calendar.YEAR);
        int cur_month = calendar2.get(Calendar.MONTH);
        int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

        String str_month = "" + (cur_month + 1);
        if (str_month.length() == 1) {
            str_month = "0" + str_month;
        }

        String str_day = "" + cur_day;
        if (str_day.length() == 1) {
            str_day = "0" + str_day;
        }
        current_date = cur_year + "-" + str_month + "-" + str_day;

        if (sp.getString(New_Main_Activity.this, "install_date").equals("")) {
            sp.putString(New_Main_Activity.this, "install_date", current_date);
        }

        myDbHelperd = new DataBaseHelper_wordsearch(New_Main_Activity.this);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            version_code_n = pInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        if (sp.getInt(New_Main_Activity.this, "app_version") == 2) {
            sp.putString(New_Main_Activity.this, "alter", "done");
        }


        if (sp.getInt(New_Main_Activity.this, "app_version") < version_code_n) {
            try {
                myDbHelperd.createDataBase();
                System.out.println("createDataBase");
            } catch (IOException ioe) {
                System.out.println("" + ioe + "Unable to create database");
                throw new Error("Unable to create database");
            }
            try {
                myDbHelperd.openDataBase();
                System.out.println("openDataBase");
                System.out.println("####################dbadd_wordsearch###openDataBase");
            } catch (SQLException sqle) {
                throw sqle;
            }

            System.out.println("check ----------------- DB move");
            System.out.println("####################dbadd_wordsearch###DB move");
            update = 1;
            sps.putString(getApplicationContext(), "mul_shortcut", "");

            sp.putInt(New_Main_Activity.this, "app_version", version_code_n);
        }

        Inner_mydb = openOrCreateDatabase("Inner_DB", 0, null);
        mydbd = openOrCreateDatabase("WS_tamil.db", MODE_PRIVATE, null);

        Inner_mydb.execSQL("CREATE TABLE IF NOT EXISTS category(id integer,game_finish VARCHAR,game_cate VARCHAR,best_score VARCHAR,time VARCHAR,win_coin VARCHAR,bonus_coin VARCHAR)");
        Inner_mydb.execSQL("CREATE TABLE IF NOT EXISTS general(id integer,game_finish VARCHAR,game_cate VARCHAR,best_score VARCHAR,time VARCHAR,win_coin VARCHAR,bonus_coin VARCHAR)");
        Inner_mydb.execSQL("CREATE TABLE IF NOT EXISTS challenge(id integer,challenge_word VARCHAR,oposit_word VARCHAR,Q_A_word VARCHAR,missing_word VARCHAR)");


        update_word_search();
        if (sp.getString(getApplicationContext(), "setting_first").equals("")) {
            sp.putInt(getApplicationContext(), "Vibrate", 1);
            sp.putInt(getApplicationContext(), "Grid", 1);
            sp.putInt(getApplicationContext(), "Sound", 1);
            sp.putInt(getApplicationContext(), "notification_setting", 1);
            sp.putString(New_Main_Activity.this, "setting_first", "done");
        }

    }

    public void update_word_search() {

        if (update != 0) {
            try {
                for (int i = 0; i < column.size(); i++) {
                    update_cursor = Inner_mydb.rawQuery("select * from challenge where " + column.get(i) + "='" + 1 + "'", null);

                    System.out.println("check ----------------- select * from challenge where " + column.get(i) + "='" + 1 + "'");
                    System.out.println("check ----------------- update_cursor.getCount() : " + update_cursor.getCount());

                    if (update_cursor.getCount() != 0) {
                        for (int j = 0; j < update_cursor.getCount(); j++) {
                            update_cursor.moveToPosition(j);
                            int id = update_cursor.getInt(update_cursor.getColumnIndexOrThrow("id"));
                            mydbd.execSQL("UPDATE " + table.get(i) + " SET is_finish='" + 1 + "' where id='" + id + "'");
                        }
                    }
                }


            } finally {
                if (update_cursor != null) update_cursor.close();

            }
        } else {

        }


    }


    public void daily_bones() {
        openDialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);

        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView tomarrow_coin_earn = (TextView) openDialog.findViewById(R.id.tomarrow_coin_earn);


        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
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
        final String str_date1 = str_day1 + "-" + str_month1 + "-" + cur_year1;

        Date date1 = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final String date = sdf.format(date1);


        ok_y.setOnClickListener(v -> {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + ea;
            String aStringx = Integer.toString(spx);
            nl_coins_show.setText("" + spx);
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            sps.putString(context, "daily_bonus_date", date);
            openDialog.dismiss();
        });


        System.out.println("############################^^^^^^^^^^^^^^currentdate" + str_date1);
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(New_Main_Activity.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(New_Main_Activity.this, "daily_bonus_date"))) {

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


        if (sps.getString(context, "price_registration").equals("com")) {
            new Handler().postDelayed(() -> prize_data_update(context, ea), 2000);
        }


        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
      /*  final int vals = reward_play_count * 100;
        ea = ea + vals;*/
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomno = rn.nextInt(maximum - minmum + 1) + minmum;

        //String r= String.valueOf(w_id);
        //lt_id.setText(r);
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

        LinearLayout extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);
        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);
        extra_coin.setOnClickListener(v -> {
            extra_coin_s = 1;
            if (isNetworkAvailable()) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(New_Main_Activity.this, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    if (rewardedAd.isReady()) {
                        rewardedAd.showAd();
                    }

                } else {
                    new Handler().postDelayed(() -> {
                        reward_progressBar.dismiss();
                        if (fb_reward == 1) {
                            rewardedAd.showAd();
                            // mShowVideoButton.setVisibility(View.VISIBLE);
                        } else {
                            //reward(New_Main_Activity.this);
                            rewarded_ad();
                            Toast.makeText(New_Main_Activity.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);


                }
            } else {
                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }

        });
        if (openDialog.isShowing()) {

        } else {
            openDialog.show();
        }

    }

    public void didTapButton(RippleView vew) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        vew.startAnimation(myAnim);
    }

    public void activity_start_screen() {
        setContentView(R.layout.activity_intro__sc);
        final SharedPreference sps = new SharedPreference();
        final DrawerLayout drawer;
        RelativeLayout toolbar, user_img_lay;
        TextView drower;
        RelativeLayout play_game, user_achivements, user_order_leaderboard;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drower = (TextView) findViewById(R.id.drower);
        nl_coins_show = (TextView) findViewById(R.id.nl_coins_show);
        new_earncoin = (LinearLayout) findViewById(R.id.new_earncoin);
        user_name = (TextView) findViewById(R.id.nl_coins_show);
        arrow_click = (TextView) findViewById(R.id.arrow_click);
        play_word = (TextView) findViewById(R.id.play_word);
        tamila_word = (TextView) findViewById(R.id.tamila_word);
        noti_count = (TextView) findViewById(R.id.noti_count);
        play_game = (RelativeLayout) findViewById(R.id.play_game);
        user_img_lay = (RelativeLayout) findViewById(R.id.user_img_lay);
        user_achivements = (RelativeLayout) findViewById(R.id.user_achivements);
        user_order_leaderboard = (RelativeLayout) findViewById(R.id.user_order_leaderboard);

        nl_removeads = (LinearLayout) findViewById(R.id.nl_removeads);
        nl_rateus = (LinearLayout) findViewById(R.id.nl_rateus);
        nl_share = (LinearLayout) findViewById(R.id.nl_share);
        nl_howtoplay = (LinearLayout) findViewById(R.id.nl_howtoplay);
        nl_feedback = (LinearLayout) findViewById(R.id.nl_feedback);
        nl_notification = (LinearLayout) findViewById(R.id.nl_notification);

        final Animation zom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        play_game.startAnimation(zom);
        final Animation zom2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        tamila_word.setAnimation(zom2);
        arrow_click.setAnimation(zom2);

        drower.setOnClickListener(v -> {
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        user_achivements.setOnClickListener(v -> {
            //achivemnt_st();
        });
        user_order_leaderboard.setOnClickListener(v -> {
            //leader_bd();
        });
        play_game.setOnClickListener(v -> {
            Intent i = new Intent(New_Main_Activity.this, New_Main_Gamelist.class);
            startActivity(i);
        });
        nl_removeads.setOnClickListener(v -> {
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                purchasedialog();
            } else {
                Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        });
        nl_feedback.setOnClickListener(v -> send_feed());
        nl_howtoplay.setOnClickListener(v -> {
            final Dialog openDialog = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.introsdialog_web);
            WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
            TextView close = (TextView) openDialog.findViewById(R.id.close);
            TextView done_exit = (TextView) openDialog.findViewById(R.id.done_exit);
            close.setOnClickListener(v13 -> openDialog.dismiss());
            done_exit.setOnClickListener(v12 -> openDialog.dismiss());
            intros.setOnLongClickListener(v1 -> true);
            intros.loadUrl("file:///android_asset/web.html");
            openDialog.show();
        });
        nl_notification.setOnClickListener(v -> {
            Intent i = new Intent(New_Main_Activity.this, Noti_Fragment.class);
            startActivity(i);
        });
        user_img_lay.setOnClickListener(v -> {

        });
        new_earncoin.setOnClickListener(v -> dialog());


        nl_rateus.setOnClickListener(v -> rate_fun());
        nl_share.setOnClickListener(v -> share_fun());

    }

    private void performCrop() {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 420);
            cropIntent.putExtra("outputY", 420);
            File cropImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg"); // Path to save the cropped image

            img_str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropImageFile));

            startActivityForResult(cropIntent, PIC_CROP);
        } catch (ActivityNotFoundException anfe) {
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(New_Main_Activity.this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void rewarded_ad() {
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

                System.out.println("adchecked--");
                fb_reward = 1;
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
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + mCoinCount;
                        String aStringx = Integer.toString(spx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                    }
                    Handler handler = new Handler();
                    handler.postDelayed(() -> vidcoinearn(), 500);
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
                System.out.println("==adtest==" + adUnitId);
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                System.out.println("==adtest_display==" + error);
                rewardedAd.loadAd();
            }
        });
        rewardedAd.loadAd();
    }

    private void app_update_manager() {
        appUpdateManager = AppUpdateManagerFactory.create(New_Main_Activity.this);
        com.google.android.play.core.tasks.Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

                try {
                    appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, New_Main_Activity.this, 200);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            } else {
                //inapp_review_dialog();
                if (sp.getString(New_Main_Activity.this, "review_complete").equals("")) {
                    if (!sp.getString(New_Main_Activity.this, "review_time").equals("")) {
                        long before_date = Long.parseLong(sp.getString(New_Main_Activity.this, "review_time"));

                        Calendar current_date = Calendar.getInstance();

                        long currentdate_mills = current_date.getTimeInMillis();

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        String string_current_date = sdf.format(currentdate_mills);
                        String string_before_date = sdf.format(before_date);


                        //System.out.println("new Version " + date);
                        long timediff = 0;

                        try {
                            timediff = TimeUnit.DAYS.convert(sdf.parse(string_current_date).getTime() - sdf.parse(string_before_date).getTime(), TimeUnit.MILLISECONDS);

                            System.out.println("new Version " + timediff);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }


                        if ((int) timediff >= 10) {
                            System.out.println("new Version 1 " + timediff);
                            if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
                                if (sp.getString(New_Main_Activity.this, "inapp_review_first").equals("")) {
                                    sp.putString(New_Main_Activity.this, "inapp_review_first", "on");

                                } else {
                                    inapp_review_dialog();
                                }
                            }
                        }
                    }

                }
            }

        });

    }

    private void inapp_review_dialog() {
        //        ReviewManager manager =new FakeReviewManager(Main_open.this);
        final ReviewManager manager = ReviewManagerFactory.create(New_Main_Activity.this);
        com.google.android.play.core.tasks.Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                com.google.android.play.core.tasks.Task<Void> flow = manager.launchReviewFlow(New_Main_Activity.this, reviewInfo);
                flow.addOnCompleteListener(task1 -> {

                    sp.putString(New_Main_Activity.this, "review_complete", "review_completed");
                    System.out.println("####################===review_complete");
                    //Toast.makeText(Main_open.this, "review completed ", Toast.LENGTH_SHORT).show();
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            } else {
                // There was some problem, continue regardless of the result.
                //Toast.makeText(Main_open.this, "There was a problem", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void intro_scn() {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        final String mnt = currentYear + "" + currentMonth + "" + currentDay;

        if (sp.getString(New_Main_Activity.this, "daily_intro" + mnt).equals("")) {
            intro = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar);
            intro.setCancelable(false);
            intro.setContentView(R.layout.activity_new_intro_sc);
            intro.show();
            sps.putInt(New_Main_Activity.this, "addloded2", 0);
            sps.putInt(New_Main_Activity.this, "addloded", 0);
            sps.putString(New_Main_Activity.this, "og_game_on_oi", "on");
            sps.putString(New_Main_Activity.this, "ads_dialog_oi", "on");
            sps.putString(New_Main_Activity.this, "game_area", "on");
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                //Do something here
                intro.dismiss();
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


                if (sps.getString(New_Main_Activity.this, "install_date").equals("")) {

                    sps.putString(New_Main_Activity.this, "install_date", "" + str_date1);
                }

                if (sps.getString(New_Main_Activity.this, "no_dialog").equals("")) {
                    sps.putString(New_Main_Activity.this, str_date1, "yes");
                    sps.putString(New_Main_Activity.this, "no_dialog", "yes");
                }


                if (sps.getString(New_Main_Activity.this, str_date1).equals("")) {
                    daily_bones();
                    sps.putString(New_Main_Activity.this, str_date1, "yes");
                }


                sp.putString(New_Main_Activity.this, "daily_intro" + mnt, "on");

                terms_and_policy();
            }, 5000);

        } else {
            terms_and_policy();
        }
    }

    public void terms_and_policy() {
        if (sps.getInt(New_Main_Activity.this, "termsandpollicy") <= 1) {
            openDialogterm = new Dialog(New_Main_Activity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialogterm.setContentView(R.layout.termsandpolicy);
            openDialogterm.setCancelable(false);
            openDialogterm.show();
            TextView yes = (TextView) openDialogterm.findViewById(R.id.yes);
            TextView no = (TextView) openDialogterm.findViewById(R.id.no);
            TextView txt_ex = (TextView) openDialogterm.findViewById(R.id.txt_ex);
            TextView txt_ex2 = (TextView) openDialogterm.findViewById(R.id.txt_ex2);
            no.setText("AGREE & CONTINUE");
            txt_ex.setText("Privacy & Terms");
            txt_ex2.setText("Thanks for downloading or updating Solli Adi\n\n" + "By clicking privacy tab you can read our privacy policy and agree to the terms of privacy policy to continue using Nithra Solli Adi.");
            yes.setOnClickListener(v -> {
                //sps.putInt(New_Main_Activity.this, "termsandpollicy", 1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    Intent i = new Intent(context, Main_policy.class);
                    startActivity(i);
                } else {
                    Toast.makeText(New_Main_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            });
            no.setOnClickListener(v -> {
                sps.putInt(New_Main_Activity.this, "termsandpollicy", 2);
                openDialogterm.dismiss();
            });

            openDialogterm.setOnDismissListener(dialog -> {

            /*    if (sps.getInt(New_Main_Activity.this, "termsandpollicy") == 1) {
                    sps.putInt(New_Main_Activity.this, "termsandpollicy", 2);
                } else {
                    sps.putInt(New_Main_Activity.this, "termsandpollicy", 2);
                }*/

            });


        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // uiHelper.onActivityResult(requestCode, resultCode, intent, dialogCallback);

        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + intent);
        if (requestCode == 200) {
            if (resultCode != RESULT_OK) {
                // Toast.makeText(this, "Update Faild.... Please try again", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Update flow failed! Result code: " + resultCode);

            }
        }

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && intent != null && intent.getData() != null) {

            Uri uri = intent.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();


            int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
            String picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                Toast.makeText(New_Main_Activity.this, "" + uri, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(New_Main_Activity.this)) {
                nextapp(random);

            } else {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(New_Main_Activity.this);                            /*.setTitle("Delete entry")*/
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்")
                        .setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                                finish();
                                Intent i = new Intent(New_Main_Activity.this, New_Main_Activity.class);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        }

        System.out.println("@@@@" + requestCode);
        if (requestCode == 15) {
            if (resultCode == -1) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 10;
                String aStringx = Integer.toString(spx);
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

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

                if (sps.getString(New_Main_Activity.this, "complite_reg").equals("yes")) {
                    Cursor cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                    cn.moveToFirst();
                    int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                    int gm1s = gm1 + 1;
                    myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                }
                ///Reward Share
                //setcoin(1);
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

                if (sps.getString(New_Main_Activity.this, "complite_reg").equals("yes")) {
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
                    if (cn.getCount() != 0) {
                        int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                        int gm1s = gm1 + 1;
                        myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                    }

                    ///Reward Share
                }
            } else {
            }
        }

        try {


            if (requestCode == RESULT_LOAD_IMG && resultCode == New_Main_Activity.RESULT_OK && null != intent) {

                Uri selectedImageUri = intent.getData();

                Intent intentf = new Intent("com.android.camera.action.CROP");
                intentf.setDataAndType(selectedImageUri, "image/*");
                intentf.putExtra("scale", true);
                intentf.putExtra("circleCrop", "");
                intentf.putExtra("return-data", false);
                File cropImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg"); // Path to save the cropped image

                img_str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                intentf.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropImageFile));
                startActivityForResult(intentf, REQUEST_CROP_ICON);

            } else if (requestCode == REQUEST_CROP_ICON && resultCode == New_Main_Activity.RESULT_OK && null != intent) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                Bitmap bmp = BitmapFactory.decodeFile(path);
                openDialog1.dismiss();

                Bitmap bm = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                // image_view.setImageBitmap(bm);
                // user_name.setText("");
                b = baos.toByteArray();
                temp = Base64.encodeToString(b, Base64.DEFAULT);
                sps.putString(getApplicationContext(), "profile_img", temp);

            } else if (resultCode == RESULT_OK) {
                if (requestCode == CAMERA_CAPTURE) {
                    Uri uri = picUri;
                    performCrop();
                    Log.d("picUri", uri.toString());
                } else if (requestCode == PICK_IMAGE_REQUEST) {
                    picUri = intent.getData();
                    Log.d("uriGallery", picUri.toString());
                    performCrop();
                } else if (requestCode == PIC_CROP) {
                    path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                    Bitmap bmp = BitmapFactory.decodeFile(path);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
                    //image_view.setImageBitmap(bmp);
                    // user_name.setText("");
                    b = out.toByteArray();
                    temp = Base64.encodeToString(b, Base64.DEFAULT);
                    sps.putString(getApplicationContext(), "profile_img", temp);
                    openDialog1.dismiss();

                    myDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg");

                }

            } else {
                // Toast.makeText(user_name.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(New_Main_Activity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showDialog(DIALOG_DOWNLOAD_PROGRESS);
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


                File file = new File(SDCardRoot + "/Nithra/solliadi/", sps.getString(New_Main_Activity.this, "email") + "-filename.zip");

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

                unpackZip(sps.getString(New_Main_Activity.this, "email") + "-filename.zip");

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
            //  nProgressDialog.dismiss();
            deletezip();


            if (unused != null && unused.equals("ERROR_DOW")) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(New_Main_Activity.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setTitle("Network connection not available, please check it!");
                alertDialogBuilder.setPositiveButton("Ok", (dialog, id) -> {
                    dialog.dismiss();
                    downloadFileAsync.isCancelled();
                    downloadFileAsync.cancel(true);

                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }


        }
    }

    private class gcmpost_update1 extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strr) {
            ServerUtilities.gcmupdate(New_Main_Activity.this, Utils.getversionname(New_Main_Activity.this), Utils.getversioncode(New_Main_Activity.this), strr[0]);
            return "";
        }

        @Override
        protected void onPostExecute(String onlineVersions) {
            super.onPostExecute(onlineVersions);
            SharedPreference sharedPreference = new SharedPreference();
            sharedPreference.putInt(New_Main_Activity.this, "fcm_update", Utils.getversioncode(New_Main_Activity.this));
        }
    }

    private class gcmpost_update2 extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strr) {
            ServerUtilities.gcmpost(strr[0], Utils.android_id(New_Main_Activity.this), Utils.getversionname(New_Main_Activity.this), Utils.getversioncode(New_Main_Activity.this), New_Main_Activity.this);
            return "";
        }

        @Override
        protected void onPostExecute(String onlineVersions) {
            super.onPostExecute(onlineVersions);
            SharedPreference sharedPreference = new SharedPreference();
            sharedPreference.putInt(New_Main_Activity.this, "fcm_update", Utils.getversioncode(New_Main_Activity.this));
        }
    }

    public class CustomerCareAdapter extends ArrayAdapter {

        public TextView title, ans;
        public int[] colorArray = {R.color.green, R.color.green, R.color.green, R.color.green, R.color.green, R.color.green, R.color.green, R.color.green};
        Context context;
        List<Item> list;
        LayoutInflater inflater;
        LinearLayout textLay;
        Button btn;

        public CustomerCareAdapter(Context context, int resource, List<Item> list) {
            super(context, resource, list);
            this.context = context;
            this.list = list;
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.cus_care_lay, null);
            btn = view.findViewById(R.id.btn);
            title = view.findViewById(R.id.title);
            title.setText(list.get(position).getItem());

            ans = view.findViewById(R.id.ans);
            ans.setText(list.get(position).getCount());

            textLay = view.findViewById(R.id.text_lay);
            textLay.setBackgroundColor(context.getResources().getColor(colorArray[position]));

            btn.setOnClickListener(view1 -> {


                if (list.get(position).getId() == 1) {
                    if (Utils.isNetworkAvailable(context)) {
                        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("plain/text");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Nithra Solli_Adi Subscription");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@nithra.mobi"});
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    } else {
                        Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
                    }


                } else if (list.get(position).getId() == 2) {
                    makeCall("");
                }
            });

            if (list.get(position).getId() == 1) {
                btn.setText("EMAIL US");
                btn.setBackgroundColor(context.getResources().getColor(colorArray[position]));
            } else if (list.get(position).getId() == 2) {
                btn.setText("CALL US");
                btn.setBackgroundColor(context.getResources().getColor(colorArray[position]));
            } else {
                btn.setVisibility(View.GONE);
            }
            return view;
        }

    }

    private class UpdateListener implements BillingUpdateListener {


        @Override
        public void onPurchasesUpdated(List<Purchase> purchaseList) {

            for (Purchase purchase : purchaseList) {
                if (BillingManager.SKU_ID.equals(purchase.getProducts().get(0))) {

                    spa.putInt(New_Main_Activity.this, "purchase_ads", 1);

                    System.out.println("purchase status : inside" + spa.getInt(New_Main_Activity.this, "purchase_ads"));
                    // Log.d(TAG, "We have gas. Consuming it.");
                    // We should consume the purchase and fill up the tank once it was consumed
                    // mActivity.getBillingManager().consumeAsync(purchase.getPurchaseToken());
                    SharedPreference sharedPreference = new SharedPreference();
                    //sharedPreference.putInt(New_Main_Activity.this, "pur_type", 2);
                    // sharedPreference.putBoolean(New_Main_Activity.this, "purchase", true);


                }
            }


        }
    }
}
