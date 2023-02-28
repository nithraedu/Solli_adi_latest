package nithra.tamil.word.game.solliadi;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
/*import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.WebDialog;*/








import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.example.games.basegameutils.GameHelper;
import com.google.firebase.analytics.FirebaseAnalytics;

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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;
import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.mul_tival;

public class Solli_adi_multiplayer extends Activity implements RoomUpdateListener, RealTimeMessageReceivedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnInvitationReceivedListener, RoomStatusUpdateListener{


    private GoogleApiClient mGoogleApiClient;
    int mCurScreen = -1;
    TextView quickgame, m_score, short_cut;
    SharedPreference sp = new SharedPreference();
    ////////////////////////////////////////////intialize player//////////////////////////////////////////
    // Request codes for the UIs that we show with startActivityForResult:
    // final static int RC_SELECT_PLAYERS = 10000;
    final static int RC_INVITATION_INBOX = 10001;
    final static int RC_WAITING_ROOM = 10002;
    private static final int RC_SELECT_PLAYERS = 9006;

    String mRoomId = null;
    // Are we currently resolving a connection failure?
    private boolean mResolvingConnectionFailure = false;

    // Has the user clicked the sign-in button?
    private boolean mSignInClicked = false;

    // Set to true to automatically start the sign in flow when the Activity starts.
    // Set to false to require the user to click the button in order to sign in.
    private boolean mAutoStartSignInFlow = true;

    // Request code used to invoke sign in user interactions.
    private static final int RC_SIGN_IN = 9001;
    ArrayList<Participant> mParticipants = null;
    boolean mMultiplayer = false;
    byte[] mMsgBuf;
    byte[] receive_bytes;
    byte[] receive_bytesd;
    byte[] send_bytes;
    byte[] send_bytes_cnt;
    byte[] send_bytes_cnt_t;
    String mMyId = null;
    int score_s = 0;
    LinearLayout adds;
    String btn_str = "";


    ////////////////////////////////////////////////Advance ADS/////////////////////////////////

    /////////native advance////////////
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-4267540560263635/9323490091";
    private static final String ADMOB_APP_ID = "ca-app-pub-4267540560263635~3166935503";
    /////////Native_Top_Advanced////////////
    private static final String ADMOB_AD_UNIT_ID_Top = "ca-app-pub-4267540560263635/2303543680";
    /////////Native_Top_Advanced////////////
    /////////Native_BackPress_Advanced////////////
    private static final String ADMOB_AD_UNIT_ID_back = "ca-app-pub-4267540560263635/3321111884";
    /////////Native_BackPress_Advanced////////////
    /////////native advance////////////
    public static FrameLayout add2, add3, add5;
    static SharedPreference spd = new SharedPreference();
    public static FrameLayout add_p;
    ////////////////////////////////////////////////Advance ADS///////////////////////////////////////////

    int fb_reward = 0;
    RewardedVideoAd rewardedVideoAd;
    int reward_status = 0;
    ////////////////////////////////////////////intialize player//////////////////////////////////////////
    //*********************reward videos process 1***********************
    //private final String AD_UNIT_ID = getString(R.string.rewarded);
    private static final String APP_ID = "ca-app-pub-4267540560263635~9441478701";
    private static final long COUNTER_TIME = 10;
    private static final int GAME_OVER_REWARD = 1;


    private boolean mGameOver;
    private boolean mGamePaused;

    private long mTimeRemaining;
    static int mCoinCount=20;
    //*********************reward videos process 1***********************    //////////////////////////////////////////////GAME INITIALIZE//////////////////////////////////////////
    //*********************Industrial 1***********************    //////////////////////////////////////////////GAME INITIALIZE//////////////////////////////////////////

    //*********************Industrial 1***********************    //////////////////////////////////////////////GAME INITIALIZE//////////////////////////////////////////
    TextView vl1, vl2, vl3, vl4, vl5, vl6, vl7, vl8, s_verify;
    TextView sb1, sb2, sb3, sb4, sb5, sb6, sb7, sb8;
    ImageView im1, im2, im3, im4, im5, im6, im7;
    LinearLayout ads_lay;
    EditText ans_edit;
    SQLiteDatabase exdb, db1, db2, db3;
    int gameid = 3;
    String letters;
    int u_id;
    String answertype;
    int letterid, lettertype;
    int answer_type;
    int rdvalu = 3;
    int x;
    int y;
    Button s_clear;
    TextView opponent_count, qus_count, your_count;
    Map<String, Integer> mParticipantScore = new HashMap<String, Integer>();
    Set<String> mFinishedParticipants = new HashSet<String>();
    //////////////////////////////////////////////GAME INITIALIZE//////////////////////////////////////////
    final static String TAG = "ButtonClicker2000";
    ////////////////////////////////////////////////RANDOM NUMBER//////////////////////////////////////////


    ///////////////////////////////////New Games Random///////////////////////////////////////////////////
    int min_2_ng = 1;
    int max_2_ng = 12;
    int randoms_2_ng;
    ///////////////////////////////////New Games Random///////////////////////////////////////////////////

    ///////////////////////game_start random////////////
    int min_g = 1;
    int max_g = 200;
    int randoms_g;
    ///////////////////////game_start random////////////
    int min = 1;
    int max = 194;
    int randoms;
    //////////////////////R2
    int min_2 = 1;
    int max_2 = 6;
    int randoms_2;
    /////////////////////R2
    int opponent_score = 0;
    int play_after = 0;

    //////////////////////G1
    int g1_min = 1;
    int g1_max = 194;
    int g1_randoms;
    //////////////////////G1

    //////////////////////G2
    int g2_min = 1;
    int g2_max = 194;
    int g2_randoms;
    //////////////////////G2

    //////////////////////G3
    int g3_min = 1;
    int g3_max = 194;
    int g3_randoms;
    //////////////////////G3

    //////////////////////G4
    int g4_min = 1;
    int g4_max = 194;
    int g4_randoms;
    //////////////////////G4

    //////////////////////G5
    int g5_min = 1;
    int g5_max = 194;
    int g5_randoms;
    //////////////////////G5

    //////////////////////G6
    int g6_min = 1;
    int g6_max = 194;
    int g6_randoms;
    //////////////////////G6

    //////////////////////G7
    int g7_min = 1;
    int g7_max = 194;
    int g7_randoms;
    //////////////////////G7

    //////////////////////G8
    int g8_min = 1;
    int g8_max = 194;
    int g8_randoms;
    //////////////////////G8

    //////////////////////G9
    int g9_min = 1;
    int g9_max = 194;
    int g9_randoms;
    //////////////////////G9

    //////////////////////G10
    int g10_min = 1;
    int g10_max = 194;
    int g10_randoms;
    //////////////////////G10

    //////////////////////G11
    int g11_min = 1;
    int g11_max = 194;
    int g11_randoms;
    //////////////////////G11

    //////////////////////G12
    int g12_min = 1;
    int g12_max = 194;
    int g12_randoms;
    //////////////////////G12
    int o_u_id, o_questionid;
    String o_question, o_answer;
    ////////////////////////////////////////////////RANDOM NUMBER//////////////////////////////////////////

    ////////////////////////////////////////////////Picture Game Initalize/////////////////////////////////
    TextView p_bt1, p_bt2, p_bt3, p_bt4, p_bt5, p_bt6, p_bt7, p_bt8, p_bt9, p_bt10, p_bt11, p_bt12, p_bt13, p_bt14, p_bt15, p_bt16, p_clear, ans_highlite;
    ImageView p_img1, p_img2, p_img3, p_img4;
    EditText p_edit;
    String sa;
    String imid;
    int wordid;
    String id;
    int word_type, image_type;
    int im11, im12, im13, im14;
    LinearLayout list4;
    ////////////////////////////////////////////////Picture Game Initalize/////////////////////////////////

    ////////////////////////////////////////////////Clue Game Initalize////////////////////////////////////
    TextView clue1, clue2, clue3, clue1_txt, clue2_txt, clue3_txt;
    TextView c_bt1, c_bt2, c_bt3, c_bt4, c_bt5, c_bt6, c_bt7, c_bt8, c_bt9, c_bt10, c_bt11, c_bt12, c_bt13, c_bt14, c_bt15, c_bt16;
    TextView c_verify, c_clear, ans_high, c_clue;
    EditText c_edit;
    TextView c_ans;
    int c_type;
    ////////////////////////////////////////////////Clue Game Initalize////////////////////////////////////

    ////////////////////////////////////////////////sol Game Initalize/////////////////////////////////////
    ImageView w_q1, w_q2, w_q3, w_q4, w_q5, w_q6, w_q7, w_q8, w_q9, w_q10, w_q11, w_q12, w_q13, w_q14;
    Button clear;
    TextView verify;
    RelativeLayout edit_buttons_layout, head;
    TextView w_bt2, w_bt3, w_bt4, w_bt5, w_bt6, w_bt7, w_bt8, w_bt9, w_bt10, w_bt11, w_bt12, w_bt13, w_bt14, w_bt15, w_bt16, w_bt1;
    TextView w_vl1, w_vl2, w_vl3, w_vl4, w_vl5, w_vl6, w_vl7, w_vl8, w_vl9, w_vl10, w_vl11, w_vl12, w_vl13, w_vl14;
    int tans;
    ////////////////////////////////////////////////sol Game Initalize/////////////////////////////////////////////////////
    ////////////////////////////////////////////////odd man out  Game Initalize/////////////////////////////////////////////////////
    TextView o_bt1, o_bt2, o_bt3, o_bt4, o_bt5, o_bt6, o_question_txt;
    TextView o_bts1, o_bts2, o_bts3, o_bts4, o_s_word_number, o_p_coins, o_to_no, o_p_setting, o_discription;
    RelativeLayout o_head, o_sixcat, o_fourcat, o_below, o_bottom;
    LinearLayout o_adds, o_qwt, o_helpshare_layout;
    Animation clickzoom, clickzoom2;
    int o_q_type = 0;
    ////////////////////////////////////////////////odd mabn out Game Initalize/////////////////////////////////////////////////////
    TextView settings;
    int sv = 0;
    SoundPool spz1, spz2, spz3, spz4, cr_ans;
    int soundId1, soundId2, soundId3, soundId4, soundId5;
    CircleImageView ads_logo, ads_logo2;
    RelativeLayout adsicon2;
    int progresss = 0;
    TextView doubling;
    String img_url1, player_name1, img_url2, player_name2;
    /////////////////////////////////////////////////Opponent and My img//////////////////////////////////////////////////////////
    ImageView opp_img, my_img;
    /////////////////////////////////////////////////Opponent and My img/////////////////////////////////////
    /////////////////////////////////////////////////No of Games/////////////////////////////////////
    int tt_game = 5;
    int st_game = 0;
    int ut_game = 0;
    TextView opponent_m_count, your_m_count;
    TextView intro_multiplayer;
    protected GameHelper mHelper;
    // Requested clients. By default, that's just the games client.
    protected int mRequestedClients = CLIENT_GAMES;


    protected boolean mDebugLog = false;
    // We expose these constants here because we don't want users of this class
    // to have to know about GameHelper at all.
    public static final int CLIENT_GAMES = GameHelper.CLIENT_GAMES;
    public static final int CLIENT_PLUS = GameHelper.CLIENT_PLUS;
    public static final int CLIENT_ALL = GameHelper.CLIENT_ALL;
    /////////////////////////////////////////////////No of Games/////////////////////////////////////
    //MyReceiver myres=new MyReceiver();
    int st = 0;
    int loading = 0;

    int gameactive_you = 1;
    int gameactive_opponent = 1;
    int ps = 0;
    Dialog openDialog_p;
    int backdialog = 0;

    TextView oppe_msg, my_msg, mys_img;
    ImageView oppe_img;
    Dialog message_dia;
    String send_mmsg = "", go_message = "";

    ArrayList<String> chat_history = new ArrayList<>();

    String receive_msg = "";
    String count_no = "";

    int message_dia_no = 0;
    Dialog chat_historyd;
    int chathd = 0;
    int p_count = 0;
    int c_count = 0;
    int s_count = 0;
    int ss_count = 0;

    int g5_count_odd = 0;
    int g6_count_equal = 0;
    int g7_count_diff = 0;
    int g8_count_etot = 0;
    int g9_count_arg = 0;
    int g10_count_riddle = 0;
    int g11_count_errorc = 0;
    int g12_count_tir = 0;


    int totalgame_count = 0;
    TextView inviteplayer;
    TextView see_invitations;
    String isdown = "";
    String email = "";

    DownloadFileAsync downloadFileAsync;
    ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    Dialog openDialogk;
    ImageView pic_show;
    int picdig = 0;

    int o_ans_position;
    int opp_comp_count;
    int ram13;

    ///////////////////////////////////////////////////////////Right Order////////////////////////////////////////////////////////////////
    TextView m_bt1, m_bt2, m_bt3, m_bt4, m_bt5, m_bt6, m_bt7, m_bt8, m_bt9, m_bt10, m_bt11, m_bt12, m_bt13, m_bt14, m_bt15, m_bt16, m_clear, m_question_txt;
    EditText m_edit;
    String m_questionid, m_question, m_answer, m_split_word;
    int m_type = 0;
    ///////////////////////////////////////////////////////////Right Order////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////OTE to TAMIL////////////////////////////////////////////////////////////////
    TextView question, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15, bt16, question_txt;
    int type;
    ///////////////////////////////////////////////////////////OTE to TAMIL////////////////////////////////////////////////////////////////
    String r_questionid, r_question, answer, split_word, er_questionid;


    RelativeLayout quick_lay, invite_lay, recive_lay;
    chat_adapter chatAdapter;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__multiplayer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        db1 = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        db2 = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        db3 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);

        if (sp.getString(Solli_adi_multiplayer.this, "new_user_db").equals("")) {

        } else {
            if (sp.getString(Solli_adi_multiplayer.this, "new_user_db").equals("on")) {
                sp.putString(Solli_adi_multiplayer.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sp.putString(Solli_adi_multiplayer.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }


        myDbHelper = new DataBaseHelper(Solli_adi_multiplayer.this);
        newhelper = new Newgame_DataBaseHelper(Solli_adi_multiplayer.this);
        newhelper2 = new Newgame_DataBaseHelper2(Solli_adi_multiplayer.this);
        newhelper3 = new Newgame_DataBaseHelper3(Solli_adi_multiplayer.this);


        Bundle extras;
        extras = getIntent().getExtras();
        if (extras != null) {
            String idd = extras.getString("mul");
            if (idd.equals("multi")){
                sp.putString(this, "multi_on", "yes");
            }
        }

      /*  exdb = myDbHelper.getReadableDatabase();
        db1= newhelper.getReadableDatabase();
        db2= newhelper2.getReadableDatabase();
        db3 = newhelper3.getReadableDatabase();*/

        ////////////////////////intialize api client//////////////////////////////////////////////
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
////////////////////////intialize api client//////////////////////////////////////////////
////////////////////////Rewarded  video///////////////////////////////////////////////////

        //loadRewardedVideoAd();
////////////////////////Rewarded video ////////////////////////////////////////////////////
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//////////////////////////////////////////////Adv//////////////////////////////////////////

//////////////////////////////////////////////Adv//////////////////////////////////////////
        /*if (sp.getString(Solli_adi_multiplayer.this,"mt_score_intialize").equals(""))
        {
            sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", 100);
            sp.putString(Solli_adi_multiplayer.this, "mt_score_intialize", "yes");
        }*/

       /* Intent i=new Intent();
        myres.onReceive(Solli_adi_multiplayer.this,i);*/
        intro_multiplayer = (TextView) findViewById(R.id.intro_multiplayer);
        m_score = (TextView) findViewById(R.id.m_score);


        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            m_score.setText("" + skx);
        }
        switchToScreen(R.layout.activity_my__multiplayer);
        quickgame = (TextView) findViewById(R.id.quickgame);
        quick_lay = (RelativeLayout) findViewById(R.id.l_lay);
        invite_lay = (RelativeLayout) findViewById(R.id.l_lay2);
        recive_lay = (RelativeLayout) findViewById(R.id.l_lay3);

        if (sp.getString(Solli_adi_multiplayer.this, "mul_intro").equals("")) {

            if (Utils.isNetworkAvailable(getApplicationContext())) {
                info_dia();
                sp.putString(Solli_adi_multiplayer.this, "mul_intro", "on");
            } else {
                // Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }

        }

        Cursor c2s = myDbHelper.getQry("select * from maintable where gameid='1'");
        c2s.moveToFirst();
        if (c2s.getCount() != 0) {
            p_count = c2s.getCount();
        }


        Cursor c2c = myDbHelper.getQry("select * from maintable where gameid='2'");
        c2c.moveToFirst();
        if (c2c.getCount() != 0) {
            c_count = c2c.getCount();
        }

        Cursor c2ss = myDbHelper.getQry("select * from maintable where gameid='3'");
        c2ss.moveToFirst();
        if (c2ss.getCount() != 0) {
            ss_count = c2ss.getCount();
        }

        Cursor c2s1 = myDbHelper.getQry("select * from maintable where gameid='4'");
        c2s1.moveToFirst();
        if (c2s1.getCount() != 0) {
            s_count = c2s1.getCount();
        }

        Cursor c5 = newhelper.getQry("select * from newmaintable where gameid='5'");
        c5.moveToFirst();
        if (c5.getCount() != 0) {
            g5_count_odd = c5.getCount();
        }

        Cursor c6 = newhelper.getQry("select * from newmaintable where gameid='6'");
        c6.moveToFirst();
        if (c6.getCount() != 0) {
            g6_count_equal = c6.getCount();
        }

        Cursor c7 = newhelper2.getQry("select * from newmaintable2 where gameid='7'");
        c7.moveToFirst();
        if (c7.getCount() != 0) {
            g7_count_diff = c7.getCount();
        }

        Cursor c8 = newhelper2.getQry("select * from newmaintable2 where gameid='8'");
        c8.moveToFirst();
        if (c8.getCount() != 0) {
            g8_count_etot = c8.getCount();
        }

        Cursor c9 = newhelper3.getQry("select * from right_order where gameid='9'");
        c9.moveToFirst();
        if (c9.getCount() != 0) {
            g9_count_arg = c9.getCount();
        }

        Cursor c10 = newhelper3.getQry("select * from right_order where gameid='10'");
        c10.moveToFirst();
        if (c10.getCount() != 0) {
            g10_count_riddle = c10.getCount();
        }

        Cursor c11 = newhelper3.getQry("select * from right_order where gameid='11'");
        c11.moveToFirst();
        if (c11.getCount() != 0) {
            g11_count_errorc = c11.getCount();
        }

        Cursor c12 = newhelper3.getQry("select * from right_order where gameid='12'");
        c12.moveToFirst();
        if (c12.getCount() != 0) {
            g12_count_tir = c12.getCount();
        }
        short_cut = (TextView) findViewById(R.id.short_cut);
        if (sp.getString(Solli_adi_multiplayer.this, "mul_shortcut").equals("")) {

        } else {
            short_cut.setVisibility(View.INVISIBLE);
        }
        short_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog_p.setContentView(R.layout.shortcut_icon_dialog);
                TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
                TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        create_shortcut();
                        openDialog_p.dismiss();
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
        });
        quick_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
                    if (!appinstalled) {
                        AlertDialog alertDialog = new AlertDialog.Builder(Solli_adi_multiplayer.this).create();
                        alertDialog.setMessage("நிகழ்நேர போட்டி  விளையாட கூகிள் பிளே கேம்ஸ்யை இன்ஸ்டால் செய்யவும். ");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.play.games")));

                                    }
                                });

                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                            System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-if");
                            if (sp.getString(Solli_adi_multiplayer.this, "multiplayer_start").equals("on")) {
                                game_direct_start();
                            } else {
                                start_dialog();
                            }

                        } else {
                            System.out.println("=============need to connect client");
                            final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                            openDialog_p.setContentView(R.layout.googleapiclient_connect);
                            LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                            // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sp.putString(Solli_adi_multiplayer.this, "signinagain_leader", "yes");
                                    sp.putString(Solli_adi_multiplayer.this, "signinagain", "yes");
                                    Log.d(TAG, "Connecting client.");
                                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                                        // start the sign-in flow
                                        mSignInClicked = true;
                                        mGoogleApiClient.connect();
                                        openDialog_p.dismiss();
                                    } else {
                                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் .", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            openDialog_p.show();
                            System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-else");

                        }

                    }
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }



             /*myDbHelper.executeSql("UPDATE maintable SET rtm=0");
                myDbHelper.executeSql("delete from answertable where rd=3");

                final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
                if (!appinstalled) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Solli_adi_multiplayer.this).create();
                    alertDia'c120505b88672879'
                    log.setMessage("புள்ளிகளை பார்க்க கூகிள் பிளே கேம்ஸ்யை இன்ஸ்டால் செய்யவும். ");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.play.games")));

                                }
                            });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else{
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        if (getApiClient().isConnected()) {
                            if (isSignedIn()) {
                                start_dialog();
                            } else {
                                beginUserInitiatedSignIn();
                                mGoogleApiClient.connect();
                                System.out.println("=============Api already connected else");

                            }
                        } else {
                            if (sp.getString(Solli_adi_multiplayer.this,"signinagain_leader").equals(""))
                            {
                                System.out.println("=============need to connect client");
                                final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                                openDialog_p.setContentView(R.layout.googleapiclient_connect);
                                LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                                // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        sp.putString(Solli_adi_multiplayer.this,"signinagain_leader","yes");
                                        sp.putString(Solli_adi_multiplayer.this,"signinagain","yes");
                                        Log.d(TAG, "Connecting client.");
                                        beginUserInitiatedSignIn();
                                        mGoogleApiClient.connect();
                                        openDialog_p.dismiss();
                                    }
                                });
                                openDialog_p.show();

                            }else
                            {
                                start_dialog();
                            }
                        }
                    }else
                    {
                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }
                }
*/
            }
        });


        inviteplayer = (TextView) findViewById(R.id.inviteplayer);
        see_invitations = (TextView) findViewById(R.id.see_invitations);
        invite_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
                    if (!appinstalled) {
                        AlertDialog alertDialog = new AlertDialog.Builder(Solli_adi_multiplayer.this).create();
                        alertDialog.setMessage("நிகழ்நேர போட்டி  விளையாட கூகிள் பிளே கேம்ஸ்யை இன்ஸ்டால் செய்யவும். ");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.play.games")));

                                    }
                                });

                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                            System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-if");
                            if (sp.getString(Solli_adi_multiplayer.this, "multiplayer_start").equals("on")) {
                                game_direct_start_invite();
                               /* if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                                    System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                                } else {

                                }
                                Intent intent;
                                intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 1);
                                switchToScreen(R.id.screen_wait);
                                startActivityForResult(intent, RC_SELECT_PLAYERS);*/
                            } else {
                                start_dialog_invite();
                            }

                        } else {
                            System.out.println("=============need to connect client");
                            final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                            openDialog_p.setContentView(R.layout.googleapiclient_connect);
                            LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                            // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sp.putString(Solli_adi_multiplayer.this, "signinagain_leader", "yes");
                                    sp.putString(Solli_adi_multiplayer.this, "signinagain", "yes");
                                    Log.d(TAG, "Connecting client.");
                                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                                        // start the sign-in flow
                                        mSignInClicked = true;
                                        mGoogleApiClient.connect();
                                        openDialog_p.dismiss();
                                    } else {
                                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் .", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            openDialog_p.show();
                            System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-else");

                        }

                    }
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recive_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
                    if (!appinstalled) {
                        AlertDialog alertDialog = new AlertDialog.Builder(Solli_adi_multiplayer.this).create();
                        alertDialog.setMessage("நிகழ்நேர போட்டி  விளையாட கூகிள் பிளே கேம்ஸ்யை இன்ஸ்டால் செய்யவும். ");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.play.games")));

                                    }
                                });

                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                            System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-if");
                            if (sp.getString(Solli_adi_multiplayer.this, "multiplayer_start").equals("on")) {
                                game_direct_start_see_invite();
                               /* if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                                    System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                                } else {

                                }
                                Intent intent;
                                intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 1);
                                switchToScreen(R.id.screen_wait);
                                startActivityForResult(intent, RC_SELECT_PLAYERS);*/
                            } else {
                                start_dialog_see_invite();
                            }

                        } else {
                            System.out.println("=============need to connect client");
                            final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                            openDialog_p.setContentView(R.layout.googleapiclient_connect);
                            LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                            // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sp.putString(Solli_adi_multiplayer.this, "signinagain_leader", "yes");
                                    sp.putString(Solli_adi_multiplayer.this, "signinagain", "yes");
                                    Log.d(TAG, "Connecting client.");
                                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                                        // start the sign-in flow
                                        mSignInClicked = true;
                                        mGoogleApiClient.connect();
                                        openDialog_p.dismiss();
                                    } else {
                                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் .", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            openDialog_p.show();
                            System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-else");

                        }

                    }
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }


             /*   if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                    System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                } else {

                }
                Intent intent;
                intent = Games.Invitations.getInvitationInboxIntent(mGoogleApiClient);
                switchToScreen(R.id.screen_wait);
                startActivityForResult(intent, RC_INVITATION_INBOX);*/
            }
        });

        adds = (LinearLayout) findViewById(R.id.ads_lay);
        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);

        doubling = (TextView) findViewById(R.id.doubling);
        if (sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") > 100) {
            //doubling.setVisibility(View.VISIBLE);
        }
/*
        doubling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog_p.setContentView(R.layout.points_to_coins_dialog);
                TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
                TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                TextView txt_e=======================================totalgamex = (TextView) openDialog_p.findViewById(R.id.txt_ex);
                TextView entry = (TextView) openDialog_p.findViewById(R.id.entry);
                TextView winning = (TextView) openDialog_p.findViewById(R.id.winning);
                txt_ex.setText("புள்ளிகளை நாணையங்களாக மாற்ற வேண்டுமா?");
                entry.setText("இழக்கும் நாணயங்கள் : 100");
                winning.setText("பெரும் நாணயங்கள்: 50");
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int points=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
                        int real_points=points-100;
                        int get_coins=real_points/2;
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + get_coins;
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                        sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", 100);
                        m_score.setText("" + sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));
                        Toast.makeText(Solli_adi_multiplayer.this, "நீங்கள் பெற்ற கூடுதல் நாணயங்கள்"+get_coins, Toast.LENGTH_SHORT).show();
                        openDialog_p.dismiss();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openDialog_p.dismiss();
                    }
                });
                openDialog_p.show();
            }
        });*/
////////////////////////////////////////////////////////////////////////Industrial Ads///////////////////////////////////////////////////////////

        //AdRequest notadRequest = new AdRequest.Builder().build();
        //interstitialAd.loadAd(notadRequest);
////////////////////////////////////////////////////////////////////////Industrial Ads///////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            //  New_Main_Activity.load_addFromMain_multiplayer(Solli_adi_multiplayer.this,adds);
            //  sp.putInt(Solli_adi_multiplayer.this, "addloded_rect_mul", 0);
            // New_Main_Activity.load_addMultiPlay_REC(Solli_adi_multiplayer.this,adds);
           /* AdView adView = new AdView(Solli_adi_multiplayer.this);
            //AdSize nativ=new AdSize(340,140);
            adView.setAdUnitId(getString(R.string.multiplayer_banner));
            adView.setAdSize(AdSize.SMART_BANNER);
            //adView.setAdSize(nativ);
            adds.removeAllViews();
            adds.addView(adView);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    System.out.println("WWWWWWWWWWWWWWWWWWWWLoaded");
                    adds.setVisibility(View.VISIBLE);
                }

                public void onAdFailedToLoad(int errorCode) {
                    System.out.println("WWWWWWWWWWWWWWWWWWWWFaild" + errorCode);
                }

            });
            AdRequest request = new AdRequest.Builder().build();
            adView.loadAd(request);*/
            New_Main_Activity.load_add_multiplayerfb_rect(Solli_adi_multiplayer.this,adds);
            // install_ads();
            //  advancads_content();
        }

//Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Solli_adi_multiplayer.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Solli_adi_multiplayer.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Solli_adi_multiplayer.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Solli_adi_multiplayer.this, R.raw.coins, 1);
        cr_ans = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId5 = cr_ans.load(Solli_adi_multiplayer.this, R.raw.cr_ans, 1);

        /*  final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);*/

        ads_logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsicon2.setVisibility(View.INVISIBLE);

            }
        });


        message_dia = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        //  final Dialog message_dia = new Dialog(WS_muliplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        // message_dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        message_dia.setContentView(R.layout.dia_message);

     /*   if (sp.getString(Solli_adi_multiplayer.this, "multiplayer_intro").equals("")) {

            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Solli_adi_multiplayer.this, "sequenced_multiplayer");
            sequence.setConfig(config);
            // sequence.addSequenceItem(u_verify, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");
            // sequence.addSequenceItem(pic_clue, "குறிப்பை பார்க்க பச்சை நிற பொத்தானை அழுத்தவும் .", "அடுத்து");
            //  sequence.addSequenceItem(helpshare_layout, "சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.", "சரி");


            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Solli_adi_multiplayer.this)
                    .setTarget(intro_multiplayer)
                    .setDismissText("சரி")
                    .setContentText("நிகழ்நேர போட்டி விளையாடும் முறையை அறிந்து கொள்ள இங்கே  கிளிக்  செய்யவும்.")
                    .build())
                    .setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                        @Override
                        public void onDismiss(MaterialShowcaseView itemView, int position) {

                            if (position == 0) {

                            }
                        }
                    });

            sp.putString(Solli_adi_multiplayer.this, "multiplayer_intro", "no");
            sequence.start();
        }
*/

        intro_multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    info_dia();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void info_dia () {
        final Dialog openDialog = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.introsdialog_web);
        WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
        //  intros.loadUrl("file:///android_asset/intro_multiplayer.html");
        // intros.loadUrl("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/intro_multiplayer.html");
        // intros.loadUrl("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/intro_multiplayer_game.html");
        intros.loadUrl("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/intro_mp_game.html");
        TextView close = (TextView) openDialog.findViewById(R.id.close);
        TextView done_exit = (TextView) openDialog.findViewById(R.id.done_exit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog.dismiss();
            }
        });
        done_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog.dismiss();
            }
        });
        intros.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        openDialog.show();
    }

    private void start_dialog () {
        chat_history.clear();
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        //int curr_score=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
        if (skx > 100) {
            final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.m_bet_dialog);
            openDialog_p.setCancelable(false);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);
            TextView heading = (TextView) openDialog_p.findViewById(R.id.heading);
            heading.setText("விளையாடு");
            heading.setVisibility(View.VISIBLE);
            final CheckBox dont_ask = (CheckBox) openDialog_p.findViewById(R.id.dont_ask);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                        System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                    } else {

                    }
                    gameid = 0;
                    play_after = 0;
                    myDbHelper.executeSql("UPDATE maintable SET rtm=0");
                    myDbHelper.executeSql("delete from answertable where rd=3");
                    score_s = 0;
                    letterid = 0;
                    st_game = 0;
                    ut_game = 0;
                    st = 0;
                    backdialog = 0;
                    chathd = 0;
                    message_dia_no = 0;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    Random rn = new Random();
                    randoms = rn.nextInt(max - min + 1) + min;
                    System.out.println("============================RC_WAITING_ROOM=============randoms" + randoms);

                    Random rnd = new Random();
                    randoms_2 = rnd.nextInt(max_2 - min_2 + 1) + min_2;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    g1_max = p_count;
                    Random g1_r = new Random();
                    g1_randoms = g1_r.nextInt(g1_max - g1_min + 1) + g1_min;

                    g2_max = c_count;
                    Random g2_r = new Random();
                    g2_randoms = g2_r.nextInt(g2_max - g2_min + 1) + g2_min;

                    g3_max = ss_count;
                    Random g3_r = new Random();
                    g3_randoms = g3_r.nextInt(g3_max - g3_min + 1) + g3_min;

                    g4_max = s_count;
                    Random g4_r = new Random();
                    g4_randoms = g4_r.nextInt(g4_max - g4_min + 1) + g4_min;

                    g5_max = g5_count_odd;
                    Random g5_r = new Random();
                    g5_randoms = g5_r.nextInt(g5_max - g5_min + 1) + g5_min;

                    g6_max = g6_count_equal;
                    Random g6_r = new Random();
                    g6_randoms = g6_r.nextInt(g6_max - g6_min + 1) + g6_min;

                    g7_max = g7_count_diff;
                    Random g7_r = new Random();
                    g7_randoms = g7_r.nextInt(g7_max - g7_min + 1) + g7_min;

                    g8_max = g8_count_etot;
                    Random g8_r = new Random();
                    g8_randoms = g8_r.nextInt(g8_max - g8_min + 1) + g8_min;

                    g9_max = g9_count_arg;
                    Random g9_r = new Random();
                    g9_randoms = g9_r.nextInt(g9_max - g9_min + 1) + g9_min;

                    g10_max = g10_count_riddle;
                    Random g10_r = new Random();
                    g10_randoms = g10_r.nextInt(g10_max - g10_min + 1) + g10_min;

                    g11_max = g11_count_errorc;
                    Random g11_r = new Random();
                    g11_randoms = g11_r.nextInt(g11_max - g11_min + 1) + g11_min;

                    g12_max = g12_count_tir;
                    Random g12_r = new Random();
                    g12_randoms = g12_r.nextInt(g12_max - g12_min + 1) + g12_min;


                    Random rndg = new Random();
                    randoms_2_ng = rndg.nextInt(max_2_ng - min_2_ng + 1) + min_2_ng;


                    /*int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") - 50;
                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                    sp.putString(Solli_adi_multiplayer.this, "initialstarting", "");
                    m_score.setText("" + sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));*/
                    sp.putString(Solli_adi_multiplayer.this, "initialstarting", "");
                   /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx - 100;
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    m_score.setText(""+spx);*/
                    ps = 1;
                    Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", false).show();
                    Utils.mProgress.setCancelable(false);
                    startQuickGame();
                    if (dont_ask.isChecked()) {
                        sp.putString(Solli_adi_multiplayer.this, "multiplayer_start", "on");
                    }
                    openDialog_p.dismiss();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog_p.dismiss();
                }
            });


            openDialog_p.show();
        } else {
            earncoin();
        }

    }

    private void game_direct_start () {
        chat_history.clear();

        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        //int curr_score=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
        if (skx > 100) {
            if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            } else {

            }

            gameid = 0;
            play_after = 0;
            myDbHelper.executeSql("UPDATE maintable SET rtm=0");
            myDbHelper.executeSql("delete from answertable where rd=3");
            score_s = 0;
            letterid = 0;
            st_game = 0;
            ut_game = 0;
            st = 0;
            backdialog = 0;
            chathd = 0;
            message_dia_no = 0;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Random rn = new Random();
            randoms = rn.nextInt(max - min + 1) + min;
            System.out.println("============================RC_WAITING_ROOM=============randoms" + randoms);

            Random rnd = new Random();
            randoms_2 = rnd.nextInt(max_2 - min_2 + 1) + min_2;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            g1_max = p_count;
            Random g1_r = new Random();
            g1_randoms = g1_r.nextInt(g1_max - g1_min + 1) + g1_min;

            g2_max = c_count;
            Random g2_r = new Random();
            g2_randoms = g2_r.nextInt(g2_max - g2_min + 1) + g2_min;

            g3_max = ss_count;
            Random g3_r = new Random();
            g3_randoms = g3_r.nextInt(g3_max - g3_min + 1) + g3_min;

            g4_max = s_count;
            Random g4_r = new Random();
            g4_randoms = g4_r.nextInt(g4_max - g4_min + 1) + g4_min;

            g5_max = g5_count_odd;
            Random g5_r = new Random();
            g5_randoms = g5_r.nextInt(g5_max - g5_min + 1) + g5_min;

            g6_max = g6_count_equal;
            Random g6_r = new Random();
            g6_randoms = g6_r.nextInt(g6_max - g6_min + 1) + g6_min;

            g7_max = g7_count_diff;
            Random g7_r = new Random();
            g7_randoms = g7_r.nextInt(g7_max - g7_min + 1) + g7_min;

            g8_max = g8_count_etot;
            Random g8_r = new Random();
            g8_randoms = g8_r.nextInt(g8_max - g8_min + 1) + g8_min;

            g9_max = g9_count_arg;
            Random g9_r = new Random();
            g9_randoms = g9_r.nextInt(g9_max - g9_min + 1) + g9_min;

            g10_max = g10_count_riddle;
            Random g10_r = new Random();
            g10_randoms = g10_r.nextInt(g10_max - g10_min + 1) + g10_min;

            g11_max = g11_count_errorc;
            Random g11_r = new Random();
            g11_randoms = g11_r.nextInt(g11_max - g11_min + 1) + g11_min;

            g12_max = g12_count_tir;
            Random g12_r = new Random();
            g12_randoms = g12_r.nextInt(g12_max - g12_min + 1) + g12_min;


            Random rndg = new Random();
            randoms_2_ng = rndg.nextInt(max_2_ng - min_2_ng + 1) + min_2_ng;


                    /*int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") - 50;
                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                    sp.putString(Solli_adi_multiplayer.this, "initialstarting", "");
                    m_score.setText("" + sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));*/
            sp.putString(Solli_adi_multiplayer.this, "initialstarting", "");
                   /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx - 100;
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    m_score.setText(""+spx);*/
            ps = 1;
            Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", false).show();
            Utils.mProgress.setCancelable(false);
            startQuickGame();


        } else {
            earncoin();
        }

    }

    private void start_dialog_invite () {
        chat_history.clear();

        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        //int curr_score=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
        if (skx > 100) {
            final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.m_bet_dialog);
            openDialog_p.setCancelable(false);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);
            TextView heading = (TextView) openDialog_p.findViewById(R.id.heading);
            heading.setText("நண்பரை அழைக்க");
            heading.setVisibility(View.VISIBLE);
            final CheckBox dont_ask = (CheckBox) openDialog_p.findViewById(R.id.dont_ask);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", true).show();


                    if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                        System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                    } else {

                    }
                    Intent intent;
                    intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 1);
                    switchToScreen(R.id.screen_wait);
                    startActivityForResult(intent, RC_SELECT_PLAYERS);
                    if (dont_ask.isChecked()) {
                        sp.putString(Solli_adi_multiplayer.this, "multiplayer_start", "on");
                    }
                    Utils.mProgress.dismiss();
                    openDialog_p.dismiss();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog_p.dismiss();
                }
            });

            openDialog_p.show();
        } else {
            earncoin();
        }

    }

    private void start_dialog_see_invite () {

        chat_history.clear();

        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        //int curr_score=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
        if (skx > 100) {
            final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.m_bet_dialog);
            openDialog_p.setCancelable(false);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);
            TextView heading = (TextView) openDialog_p.findViewById(R.id.heading);
            heading.setText("அழைப்பை ஏற்க");
            heading.setVisibility(View.VISIBLE);

            final CheckBox dont_ask = (CheckBox) openDialog_p.findViewById(R.id.dont_ask);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", true).show();
                    if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                        System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                    } else {

                    }
                    Intent intent;
                    intent = Games.Invitations.getInvitationInboxIntent(mGoogleApiClient);
                    switchToScreen(R.id.screen_wait);
                    startActivityForResult(intent, RC_INVITATION_INBOX);

                    openDialog_p.dismiss();
                    Utils.mProgress.dismiss();
                    if (dont_ask.isChecked()) {
                        sp.putString(Solli_adi_multiplayer.this, "multiplayer_start", "on");
                    }
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog_p.dismiss();
                }
            });

            openDialog_p.show();
        } else {
            earncoin();
        }

    }


    private void game_direct_start_invite () {
        Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", true).show();
        chat_history.clear();
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        //int curr_score=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
        if (skx > 100) {

            if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            } else {

            }
            Intent intent;
            intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 1);
            switchToScreen(R.id.screen_wait);
            startActivityForResult(intent, RC_SELECT_PLAYERS);

        } else {
            earncoin();
        }
        Utils.mProgress.dismiss();

    }

    private void game_direct_start_see_invite () {

        Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", true).show();
        chat_history.clear();
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        //int curr_score=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
        if (skx > 100) {
            if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            } else {

            }
            Intent intent;
            intent = Games.Invitations.getInvitationInboxIntent(mGoogleApiClient);
            switchToScreen(R.id.screen_wait);
            startActivityForResult(intent, RC_INVITATION_INBOX);

        } else {
            earncoin();
        }
        Utils.mProgress.dismiss();

    }


    void switchToScreen ( int screenId){
        mCurScreen = screenId;
    }

    void startQuickGame () {
        // quick-start a game with 1 randomly selected opponent
        final int MIN_OPPONENTS = 1, MAX_OPPONENTS = 1;
        Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(MIN_OPPONENTS, MAX_OPPONENTS, 0);
        RoomConfig.Builder rtmConfigBuilder = RoomConfig.builder(this);
        rtmConfigBuilder.setMessageReceivedListener(this);
        rtmConfigBuilder.setRoomStatusUpdateListener(this);
        rtmConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
        // switchToScreen(R.id.screen_wait);
        // Toast.makeText(Solli_adi_multiplayer.this, "Loading.......", Toast.LENGTH_SHORT).show();

        keepScreenOn();
        // resetGameVars();
        Games.RealTimeMultiplayer.create(mGoogleApiClient, rtmConfigBuilder.build());


    }

    private void invitePlayers () {
      /*  // launch the player selection screen
        // minimum: 1 other player; maximum: 3 other players
        Games.getRealTimeMultiplayerClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .getSelectOpponentsIntent(1, 3, true)
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_SELECT_PLAYERS);
                    }
                });*/
    }

    // Sets the flag to keep this screen on. It's recommended to do that during
    // the
    // handshake when setting up a game, because if the screen turns off, the
    // game will be
    // cancelled.
    void keepScreenOn () {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    // Activity just got to the foreground. We switch to the wait screen because we will now
    // go through the sign-in flow (remember that, yes, every time the Activity comes back to the
    // foreground we go through the sign-in flow -- but if the user is already authenticated,
    // this flow simply succeeds and is imperceptible).
    @Override
    public void onStart () {
        // switchToScreen(R.id.screen_wait);
        if (mCurScreen == R.layout.activity_my__multiplayer) {
         /*   FrameLayout frameLayout =
                    (FrameLayout) findViewById(R.id.fl_adplaceholder);
            load_add_install(Solli_adi_multiplayer.this,frameLayout);*/


        }

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            System.out.println("=======================not connected");
        } else {
            System.out.println("=======================Connecting client.");
            mGoogleApiClient.connect();
        }


        super.onStart();
    }


    @Override
    public void onActivityResult ( int requestCode, int responseCode,
                                   Intent intent){


        System.out.println("=========*******requestCode ===== " + requestCode);
        System.out.println("=========*******responseCode ===== " + requestCode);

        switch (requestCode) {

            case RC_SELECT_PLAYERS:
                // we got the result from the "select players" UI -- ready to create the room
                handleSelectPlayersResult(responseCode, intent);

                break;
            case RC_INVITATION_INBOX:
                // we got the result from the "select invitation" UI (invitation inbox). We're
                // ready to accept the selected invitation:
                handleInvitationInboxResult(responseCode, intent);

                break;
            case RC_WAITING_ROOM:
                // we got the result from the "waiting room" UI.
                if (responseCode == Activity.RESULT_OK) {
                    System.out.println("=====*======================RESULT_OK");
                    // ready to start playing
                    Log.d(TAG, "Starting game (waiting room returned OK).");
                    //  startGame(true);
                   /* finish();
                    Intent i = new Intent(Multiplayer.this, Multiplayer_Odd_man_out.class);
                    startActivity(i)*/
                    //game_start();
//////////////////////////////////////////////////////////////////////////////////
                    System.out.println("###########################RC_WAITING_ROOM");
                    Random rnw = new Random();
                    randoms_g = rnw.nextInt(max_g - min_g + 1) + min_g;

                    Handler handler9 = new Handler();
                    handler9.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gameactive_you = 1;
                            mMultiplayer = true;
                            broadcastScore(true);
                        }
                    }, randoms_g);

                    System.out.println("=====*======================broadcastScore");
                    Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", false).show();
                    Utils.mProgress.setCancelable(true);

                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Utils.mProgress.dismiss();
                        }
                    }, 2000);
//////////////////////////////////////////////////////////////////////////////////

                    // Toast.makeText(Solli_adi_multiplayer.this, "Success!!!!!", Toast.LENGTH_SHORT).show();

                } else if (responseCode == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                    if (ps == 1) {
                        ps = 0;
                        Utils.mProgress.dismiss();
                    }
                    System.out.println("=====*======================RESULT_LEFT_ROOM");
                    // player indicated that they want to leave the room
                    //leaveRoom();
                } else if (responseCode == Activity.RESULT_CANCELED) {
                    leaveRoom();
                    if (ps == 1) {
                        ps = 0;
                        Utils.mProgress.dismiss();
                    }
                    System.out.println("=====*=======================RESULT_CANCELED");
                    // Dialog was cancelled (user pressed back key, for instance). In our game,
                    // this means leaving the room too. In more elaborate games, this could mean
                    // something else (like minimizing the waiting room UI).
                    //leaveRoom();
                }
                break;
            case RC_SIGN_IN:
                Log.d(TAG, "onActivityResult with requestCode == RC_SIGN_IN, responseCode="
                        + responseCode + ", intent=" + intent);
                mSignInClicked = false;
                mResolvingConnectionFailure = false;
                if (responseCode == RESULT_OK) {
                    mGoogleApiClient.connect();
                } else {
                    BaseGameUtils.showActivityResultError(this, requestCode, responseCode, R.string.signin_other_error);
                }
                break;
        }

        if (requestCode == 15) {
            if (responseCode == -1) {
              /*  int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                share_earn(100);*/

                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 20;
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                share_earn(20);
                //  m_score.setText(""+sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));
                ///Reward Share
            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 12) {
            if (responseCode == -1) {
             /*   int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);*/
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 20;
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                share_earn(20);
                // m_score.setText(""+sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));
            } else {
            }
        }

        super.onActivityResult(requestCode, responseCode, intent);
    }

    private void exitgame () {

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            setContentView(R.layout.activity_my__multiplayer);
            switchToScreen(R.layout.activity_my__multiplayer);
            quickgame = (TextView) findViewById(R.id.quickgame);

            quick_lay = (RelativeLayout) findViewById(R.id.l_lay);
            invite_lay = (RelativeLayout) findViewById(R.id.l_lay2);
            recive_lay = (RelativeLayout) findViewById(R.id.l_lay3);

            m_score = (TextView) findViewById(R.id.m_score);
            adds.setVisibility(View.VISIBLE);

            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            m_score.setText("" + skx);
            ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
            adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
            //install_ads_doalug();
            adds = (LinearLayout) findViewById(R.id.ads_lay);
            NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

            if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                native_banner_ad_container.setVisibility(View.GONE);
            } else {
                if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                    //fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                    New_Main_Activity.load_add_multiplayerfb_rect(Solli_adi_multiplayer.this,adds);

                    //  New_Main_Activity.load_add_multiplayerfb_rect(Solli_adi_multiplayer.this,adds);

                    //fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);

                    /* if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                        New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                    }else {
                        fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                    }*/
                }else {
                    adds.setVisibility(View.GONE);
                }
                // install_ads();
               /* adds = (LinearLayout) findViewById(R.id.ads_lay);
                //  New_Main_Activity.load_addFromMain_multiplayer(Solli_adi_multiplayer.this,adds);
                //  sp.putInt(Solli_adi_multiplayer.this, "addloded_rect_mul", 0);
                // New_Main_Activity.load_addMultiPlay_REC(Solli_adi_multiplayer.this,adds);

                AdView adView = new AdView(Solli_adi_multiplayer.this);
                //AdSize nativ=new AdSize(340,140);
                adView.setAdUnitId(getString(R.string.multiplayer_banner));
                adView.setAdSize(AdSize.SMART_BANNER);
                //adView.setAdSize(nativ);
                adds.removeAllViews();
                adds.addView(adView);
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        System.out.println("WWWWWWWWWWWWWWWWWWWWLoaded");
                        adds.setVisibility(View.VISIBLE);
                    }

                    public void onAdFailedToLoad(int errorCode) {
                        System.out.println("WWWWWWWWWWWWWWWWWWWWFaild" + errorCode);
                    }

                });
                AdRequest request = new AdRequest.Builder().build();
                adView.loadAd(request);*/

            }

            doubling = (TextView) findViewById(R.id.doubling);
            if (sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") > 100) {
                // doubling.setVisibility(View.VISIBLE);
            }
           /* doubling.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog_p.setContentView(R.layout.points_to_coins_dialog);
                    TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
                    TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                    TextView txt_ex = (TextView) openDialog_p.findViewById(R.id.txt_ex);
                    TextView entry = (TextView) openDialog_p.findViewById(R.id.entry);
                    TextView winning = (TextView) openDialog_p.findViewById(R.id.winning);
                    txt_ex.setText("புள்ளிகளை நாணையங்களாக மாற்ற வேண்டுமா?");
                    entry.setText("இழக்கும் நாணயங்கள் : 100");
                    winning.setText("பெரும் நாணயங்கள்: 50");
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int points=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
                            int real_points=points-100;
                            int get_coins=real_points/2;
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx + get_coins;
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                            sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", 100);
                            m_score.setText("" + sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));
                            Toast.makeText(Solli_adi_multiplayer.this, "நீங்கள் பெற்ற கூடுதல் நாணயங்கள்"+get_coins, Toast.LENGTH_SHORT).show();
                            openDialog_p.dismiss();
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openDialog_p.dismiss();
                        }
                    });
                    openDialog_p.show();
                }
            });*/
            // adds = (LinearLayout) findViewById(R.id.ads_lay);
            // New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);

          /*  final Animation pendulam;
            pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
            adsicon2.startAnimation(pendulam);*/
            ads_logo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adsicon2.setVisibility(View.INVISIBLE);

                }
            });

            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_adplaceholder);
            //  load_add_install(Solli_adi_multiplayer.this, frameLayout);
            short_cut = (TextView) findViewById(R.id.short_cut);

            if (sp.getString(Solli_adi_multiplayer.this, "mul_shortcut").equals("")) {
            } else {
                short_cut.setVisibility(View.INVISIBLE);
            }

            short_cut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog_p.setContentView(R.layout.shortcut_icon_dialog);
                    TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
                    TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            create_shortcut();
                            openDialog_p.dismiss();
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
            });
            quick_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // play_after=0;

                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
                        if (!appinstalled) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Solli_adi_multiplayer.this).create();
                            alertDialog.setMessage("நிகழ்நேர போட்டி  விளையாட கூகிள் பிளே கேம்ஸ்யை இன்ஸ்டால் செய்யவும். ");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            startActivity(new Intent(Intent.ACTION_VIEW,
                                                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.play.games")));

                                        }
                                    });

                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else {

                            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                                System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-if");
                                if (sp.getString(Solli_adi_multiplayer.this, "multiplayer_start").equals("on")) {
                                    game_direct_start();
                                } else {
                                    start_dialog();
                                }
                            } else {
                                System.out.println("=============need to connect client");
                                final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                                openDialog_p.setContentView(R.layout.googleapiclient_connect);
                                LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                                // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        sp.putString(Solli_adi_multiplayer.this, "signinagain_leader", "yes");
                                        sp.putString(Solli_adi_multiplayer.this, "signinagain", "yes");
                                        Log.d(TAG, "Connecting client.");
                                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                                            // start the sign-in flow
                                            mSignInClicked = true;
                                            mGoogleApiClient.connect();
                                            openDialog_p.dismiss();
                                        } else {
                                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் .", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                openDialog_p.show();
                                System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-else");
                            }
                        }
                    } else {
                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }

                  /*  if (Utils.isNetworkAvailable(getApplicationContext())) {
                        start_dialog();
                    }else
                    {
                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }*/
                }
            });

            inviteplayer = (TextView) findViewById(R.id.inviteplayer);
            see_invitations = (TextView) findViewById(R.id.see_invitations);

            invite_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
                        if (!appinstalled) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Solli_adi_multiplayer.this).create();
                            alertDialog.setMessage("நிகழ்நேர போட்டி  விளையாட கூகிள் பிளே கேம்ஸ்யை இன்ஸ்டால் செய்யவும். ");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            startActivity(new Intent(Intent.ACTION_VIEW,
                                                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.play.games")));

                                        }
                                    });

                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else {
                            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                                System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-if");
                                if (sp.getString(Solli_adi_multiplayer.this, "multiplayer_start").equals("on")) {
                                    game_direct_start_invite();
                               /* if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                                    System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                                } else {

                                }
                                Intent intent;
                                intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 1);
                                switchToScreen(R.id.screen_wait);
                                startActivityForResult(intent, RC_SELECT_PLAYERS);*/
                                } else {
                                    start_dialog_invite();
                                }

                            } else {
                                System.out.println("=============need to connect client");
                                final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                                openDialog_p.setContentView(R.layout.googleapiclient_connect);
                                LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                                // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        sp.putString(Solli_adi_multiplayer.this, "signinagain_leader", "yes");
                                        sp.putString(Solli_adi_multiplayer.this, "signinagain", "yes");
                                        Log.d(TAG, "Connecting client.");
                                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                                            // start the sign-in flow
                                            mSignInClicked = true;
                                            mGoogleApiClient.connect();
                                            openDialog_p.dismiss();
                                        } else {
                                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் .", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                openDialog_p.show();
                                System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-else");

                            }

                        }
                    } else {
                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            recive_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
                        if (!appinstalled) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Solli_adi_multiplayer.this).create();
                            alertDialog.setMessage("நிகழ்நேர போட்டி  விளையாட கூகிள் பிளே கேம்ஸ்யை இன்ஸ்டால் செய்யவும். ");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            startActivity(new Intent(Intent.ACTION_VIEW,
                                                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.play.games")));

                                        }
                                    });

                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else {
                            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                                System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-if");
                                if (sp.getString(Solli_adi_multiplayer.this, "multiplayer_start").equals("on")) {
                                    game_direct_start_see_invite();
                               /* if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
                                    System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
                                } else {

                                }
                                Intent intent;
                                intent = Games.RealTimeMultiplayer.getSelectOpponentsIntent(mGoogleApiClient, 1, 1);
                                switchToScreen(R.id.screen_wait);
                                startActivityForResult(intent, RC_SELECT_PLAYERS);*/
                                } else {
                                    start_dialog_see_invite();
                                }

                            } else {
                                System.out.println("=============need to connect client");
                                final Dialog openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                                openDialog_p.setContentView(R.layout.googleapiclient_connect);
                                LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                                // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        sp.putString(Solli_adi_multiplayer.this, "signinagain_leader", "yes");
                                        sp.putString(Solli_adi_multiplayer.this, "signinagain", "yes");
                                        Log.d(TAG, "Connecting client.");
                                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                                            // start the sign-in flow
                                            mSignInClicked = true;
                                            mGoogleApiClient.connect();
                                            openDialog_p.dismiss();
                                        } else {
                                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் .", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                openDialog_p.show();
                                System.out.println("GoogleApiClient mp_welcom_page() dialog.show()-else");

                            }

                        }
                    } else {
                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            intro_multiplayer = (TextView) findViewById(R.id.intro_multiplayer);
            intro_multiplayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        info_dia();
                    } else {
                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            System.out.println("========================exitgame_--activity_my__multiplayer");
        } else {
            finish();
            Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
            startActivity(i);
            System.out.println("========================exitgame else");
        }
    }

    // Show the waiting room UI to track the progress of other players as they enter the
    // room and get connected.
    void showWaitingRoom (Room room){
        // minimum number of players required for our game
        // For simplicity, we require everyone to join the game before we start it
        // (this is signaled by Integer.MAX_VALUE).
        final int MIN_PLAYERS = Integer.MAX_VALUE;
        Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(mGoogleApiClient, room, MIN_PLAYERS);
        // show waiting room UI
        startActivityForResult(i, RC_WAITING_ROOM);

    }

    // Show error message about game being cancelled and return to main screen.
    void showGameError () {
        System.out.println("=====================showGameError");
        Log.d(TAG, "showGameError, code ");
        BaseGameUtils.makeSimpleDialog(this, getString(R.string.game_problem));
        //switchToMainScreen();
        exitgame();
    }

    void updateRoom (Room room){
        if (room != null) {
            mParticipants = room.getParticipants();
        }
        if (mParticipants != null) {
            //updatePeerScoresDisplay(0);
        }
    }

    // Broadcast my score to everybody else.
    void broadcastScore ( boolean finalScore){
        System.out.println("=========broadcastScore==finalScore========" + finalScore);
        System.out.println("=========go_message" + go_message);
        System.out.println("###########################broadcastScore");
        if (go_message.equals("go_message")) {
            go_message = "";
            mMsgBuf = new byte[(send_mmsg.length() + 6)];
            mMsgBuf[0] = (byte) ('S');
            mMsgBuf[1] = (byte) ('W');
            mMsgBuf[2] = (byte) ('W');
            mMsgBuf[3] = (byte) ('W');
            mMsgBuf[4] = (byte) ('W');
            gameactive_opponent = 1;
            mMsgBuf[5] = (byte) gameactive_opponent;

            int i = 6;
            try {
                send_bytes = new byte[1];
                System.out.println("send_msg  : " + send_mmsg);
                send_bytes = send_mmsg.getBytes("UTF-8");
                System.out.println("send_msg send_bytes : " + send_bytes);
                System.out.println("###########################send_msg send_bytes");
                /* for (int i1=0;i<send_bytes.length;i1++)
                {
                    System.out.println("send_msg send_bytes : " + send_bytes[i1]);

                }*/
                for (byte b : send_bytes) {
                    mMsgBuf[i] = b;
                    i += 1;
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.out.println("send_msg bytes e : " + e);
            }

            for (Participant p : mParticipants) {
                int is = 0;
                System.out.println("----------------------getDisplayName" + p.getDisplayName());
                if (is == 0) {
                    System.out.println("---------------------  My ID" + p.getParticipantId());
                    System.out.println("---------------------  eMy Name" + p.getDisplayName());
                    System.out.println("---------------------  My Url" + p.getHiResImageUrl());
                    is = 1;
                } else {
                    System.out.println("---------------------  Opponent Id" + p.getParticipantId());
                    System.out.println("---------------------  Opponent Name" + p.getDisplayName());
                    System.out.println("---------------------  Opponent Url" + p.getHiResImageUrl());
                    is = 2;
                }

                if (p.getParticipantId().equals(mMyId))
                    continue;
                if (p.getStatus() != Participant.STATUS_JOINED)
                    continue;
                if (finalScore) {
                    // final score notification must be sent via reliable message
                    Games.RealTimeMultiplayer.sendReliableMessage(mGoogleApiClient, null, mMsgBuf,
                            mRoomId, p.getParticipantId());
                } else {
                    // it's an interim score notification, so we can use unreliable
                    Games.RealTimeMultiplayer.sendUnreliableMessage(mGoogleApiClient, mMsgBuf, mRoomId,
                            p.getParticipantId());
                }
            }

        } else {
            if (gameactive_you == 1) {
                // gameactive_you = 0;
                mMsgBuf = new byte[(p_count + 6)];
                if (!mMultiplayer)
                    return; // playing single-player mode

                // First byte in message indicates whether it's a final score or not
                mMsgBuf[0] = (byte) (finalScore ? 'F' : 'U');

                // Second byte is the score.
                mMsgBuf[1] = (byte) score_s;
                System.out.println("=========score=======" + score_s);

                mMsgBuf[2] = (byte) randoms;

                mMsgBuf[3] = (byte) randoms_2;

                mMsgBuf[4] = (byte) st_game;

                System.out.println("----------------------------------gameactive_opponent" + gameactive_opponent);
                gameactive_opponent = 1;
                mMsgBuf[5] = (byte) gameactive_opponent;
                System.out.println("=============================mMsgBuf.length" + mMsgBuf.length);

/////////////////////////////////////////////////////////////////////////////game_count_sending////////////////////////////////////////////
                System.out.println("#####################################randoms_2_ng" + randoms_2_ng);
                if (mMsgBuf.length > 6) {
                    String ps_count = "";
                    //ps_count = String.valueOf(p_count+"-"+c_count+"-"+s_count+"-"+ss_count);
                    ps_count = String.valueOf(g1_randoms + "-" + g2_randoms + "-" + g3_randoms + "-" + g4_randoms + "-" + g5_randoms + "-" + g6_randoms + "-" + g7_randoms + "-" + g8_randoms + "-" + g9_randoms + "-" + g10_randoms + "-" + g11_randoms + "-" + g12_randoms + "-" + randoms_2_ng);
                    int i = 6;
                    try {
                        send_bytes_cnt = new byte[1];
                        System.out.println("send_msg  : " + ps_count);
                        send_bytes_cnt = ps_count.getBytes("UTF-8");
                        System.out.println("send_msg send_bytes : " + send_bytes_cnt);
                        for (byte b : send_bytes_cnt) {
                            mMsgBuf[i] = b;
                            i += 1;
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        System.out.println("send_msg bytes e : " + e);
                    }


                   /* String pc_count = "";
                    pc_count = String.valueOf(c_count);
                    pc_count="2"+pc_count;
                    i += 1;
                    try {
                        send_bytes_cnt_t = new byte[1];
                        System.out.println("send_msg  : " + pc_count);
                        send_bytes_cnt_t = ps_count.getBytes("UTF-8");
                        System.out.println("send_msg send_bytes : " + send_bytes_cnt_t);
                        for (byte b : send_bytes_cnt_t) {
                            mMsgBuf[i] = b;
                            i += 1;
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        System.out.println("send_msg bytes e : " + e);
                    }*/

                } else {
                    System.out.println("=============================p_count" + mMsgBuf[6]);
                }
/////////////////////////////////////////////////////////////////////////////game_count_sending////////////////////////////////////////////


                // Send to every other participant.
                for (Participant p : mParticipants) {
                    int i = 0;
                    System.out.println("----------------------getDisplayName" + p.getDisplayName());
                    if (i == 0) {
                        System.out.println("---------------------  My ID" + p.getParticipantId());
                        System.out.println("---------------------  My Name" + p.getDisplayName());
                        System.out.println("---------------------  My Url" + p.getHiResImageUrl());
                        i = 1;
                    } else {
                        System.out.println("---------------------  Opponent Id" + p.getParticipantId());
                        System.out.println("---------------------  Opponent Name" + p.getDisplayName());
                        System.out.println("---------------------  Opponent Url" + p.getHiResImageUrl());
                        i = 2;
                    }

                    if (p.getParticipantId().equals(mMyId))
                        continue;
                    if (p.getStatus() != Participant.STATUS_JOINED)
                        continue;
                    if (finalScore) {
                        // final score notification must be sent via reliable message
                        Games.RealTimeMultiplayer.sendReliableMessage(mGoogleApiClient, null, mMsgBuf,
                                mRoomId, p.getParticipantId());
                        System.out.println("###########################finalScore" + finalScore);
                    } else {
                        // it's an interim score notification, so we can use unreliable
                        Games.RealTimeMultiplayer.sendUnreliableMessage(mGoogleApiClient, mMsgBuf, mRoomId,
                                p.getParticipantId());
                        System.out.println("###########################finalScore else" + finalScore);
                    }
                }
            } else {
                System.out.println("----------------------------------gameactive_you_mr3" + gameactive_you);
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 200;
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                finalscreen(200, "உங்கள் எதிராளி வெளியேறிவிட்டார். நீங்கள் வெற்றி அடைந்துவிட்டீர்கள் உங்களுக்கான நாணயங்கள் ");
            }
        }

    }

    // Broadcast my score to everybody else.
    // Activity is going to the background. We have to leave the current room.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onStop () {
        Log.d(TAG, "**** got onStop");

        // if we're in a room, leave it.
        //leaveRoom();

        // stop trying to keep the screen on
        stopKeepingScreenOn();

   /* if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
        setContentView(R.layout.activity_my__multiplayer);
    } else {
        //switchToScreen(R.id.screen_wait);
       // Toast.makeText(Solli_adi_multiplayer.this, "OnS Loading....... ", Toast.LENGTH_SHORT).show();
    }*/
        super.onStop();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onPause () {
        super.onPause();
        pauseGame();


    }

    @Override
    protected void onResume () {
        super.onResume();
        System.out.println("===========================On resume");
        //install_ads_doalug();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Solli_adi_multiplayer.this);
        mFirebaseAnalytics.setCurrentScreen(Solli_adi_multiplayer.this, "Solliadi Multi Player", null);

//////////////////////////////////////////

        if (!mGameOver && mGamePaused) {
            resumeGame();
        }

///////////////////////////////////////////
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if (mCurScreen == R.layout.activity_my__multiplayer) {

        }
      /*  if (sp.getInt(Solli_adi_multiplayer.this, "addlodedd") == 1) {
            New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);
        }else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
    }

    // Leave the room.
    void leaveRoom () {

        Log.d(TAG, "Leaving room.");
        // mSecondsLeft = 0;
        stopKeepingScreenOn();
        if (mRoomId != null) {
            Games.RealTimeMultiplayer.leave(mGoogleApiClient, this, mRoomId);
            mRoomId = null;
            // switchToScreen(R.id.screen_wait);
            exitgame();
            // Toast.makeText(Solli_adi_multiplayer.this, "PL Loading", Toast.LENGTH_SHORT).show();
        } else {
            //  switchToMainScreen();
            exitgame();
        }
    }

    // Clears the flag that keeps the screen on.
    void stopKeepingScreenOn () {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    // Handle back key to make sure we cleanly leave a game if we are in the middle of one
    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent e){
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (sp.getString(getApplicationContext(), "multi_on").equals("yes")) {
                System.out.println("@@====================================multi_on");
                if (mCurScreen == R.layout.activity_my__multiplayer) {
                    System.out.println("@@====================================finish");
                    finish();
                    Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                    startActivity(i);


                } else {
                    if (sp.getString(getApplicationContext(), "checkbox_back_m").equals("yes")) {
                        System.out.println("@@====================================checkbox_back_m");
                        if (mCurScreen == R.layout.activity_solukul_sol_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.activity_pic__game_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.activity_clue__game_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.activity_word__game_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.activity_my__multiplayer) {
                            finish();
                            Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                            startActivity(i);
                        } else if (mCurScreen == R.layout.odd_man_out_multiplayer) {
                            finish();
                        } else if (mCurScreen == R.layout.makeword__rightorder_multiplayer) {
                            finish();
                        } else if (mCurScreen == R.layout.opposite_word_multiplayer) {
                            finish();
                        } else if (mCurScreen == R.layout.ote_to_tamil_multiplayer) {
                            finish();
                        } else if (mCurScreen == R.layout.riddle_game_multiplayer) {
                            finish();
                        } else if (mCurScreen == R.layout.error_correction_multiplayer) {
                            finish();
                        }
                    } else {
                        openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        openDialog_p.setContentView(R.layout.back_pess_multiplayer);
                        TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
                        TextView txt_ex = (TextView) openDialog_p.findViewById(R.id.txt_ex);
                        TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                        CheckBox checkbox_back = (CheckBox) openDialog_p.findViewById(R.id.checkbox_back);
                        FrameLayout ads_lay = (FrameLayout) openDialog_p.findViewById(R.id.fl_adplaceholder);
                        checkbox_back.setVisibility(View.GONE);
                        txt_ex.setText("நீங்கள் வெளியேறினால் உங்களது வெற்றிநாணயங்கள் உங்கள் எதிராளிக்கு சென்றுவிடும்.");

                        backdialog = 1;
                        System.out.println("@@====================================openDialog_p");

                        checkbox_back.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                if (isChecked == true) {
                                    sp.putString(getApplicationContext(), "checkbox_back_m", "yes");
                                } else {
                                    sp.putString(getApplicationContext(), "checkbox_back_m", "");
                                }
                            }
                        });

                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                System.out.println("===========================mCurScreen " + mCurScreen + " R.layout.activity_my__multiplayer" + R.layout.my_game_oddmanout + "R.layout.activity_my__multiplayer" + R.layout.activity_my__multiplayer);
                                if (mCurScreen == R.layout.activity_solukul_sol_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_pic__game_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_clue__game_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_word__game_multiplayer) {

                                    leaveRoom();
                                } else if (mCurScreen == R.layout.odd_man_out_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.opposite_word_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.makeword__rightorder_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_my__multiplayer) {
                                    finish();
                                    Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else if (mCurScreen == R.layout.ote_to_tamil_multiplayer) {
                                    finish();
                                } else if (mCurScreen == R.layout.riddle_game_multiplayer) {
                                    finish();
                                } else if (mCurScreen == R.layout.error_correction_multiplayer) {
                                    finish();
                                }
                                backdialog = 0;
                                openDialog_p.dismiss();
                            }
                        });
                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backdialog = 0;
                                openDialog_p.dismiss();
                            }
                        });
                        openDialog_p.show();
                    }

                }
            } else {
                if (mCurScreen == R.layout.activity_my__multiplayer) {
                    System.out.println("@@====================================finish");
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        if (mul_tival==1){
                            finish();
                        }else{
                            finish();
                            Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Gamelist.class);
                            startActivity(i);
                        }
                    }
                } else {
                    if (sp.getString(getApplicationContext(), "checkbox_back_m").equals("yes")) {
                        System.out.println("@@====================================checkbox_back_m");
                        if (mCurScreen == R.layout.activity_solukul_sol_multiplayer) {

                            leaveRoom();
                        } else if (mCurScreen == R.layout.activity_pic__game_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.activity_clue__game_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.activity_word__game_multiplayer) {

                            leaveRoom();
                        } else if (mCurScreen == R.layout.odd_man_out_multiplayer) {

                            leaveRoom();
                        } else if (mCurScreen == R.layout.opposite_word_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.makeword__rightorder_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.ote_to_tamil_multiplayer) {

                            leaveRoom();
                        } else if (mCurScreen == R.layout.riddle_game_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.error_correction_multiplayer) {

                            leaveRoom();

                        } else if (mCurScreen == R.layout.activity_my__multiplayer) {

                                if (main_act.equals("")) {
                                    finish();
                                    Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else {
                                    if (mul_tival==1){
                                        finish();
                                    }else{
                                        finish();
                                        Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Gamelist.class);
                                        startActivity(i);
                                    }
                                }

                        }
                    } else {
                        openDialog_p = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        openDialog_p.setContentView(R.layout.back_pess_multiplayer);
                        TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
                        TextView txt_ex = (TextView) openDialog_p.findViewById(R.id.txt_ex);
                        TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                        CheckBox checkbox_back = (CheckBox) openDialog_p.findViewById(R.id.checkbox_back);
                        FrameLayout ads_lay = (FrameLayout) openDialog_p.findViewById(R.id.fl_adplaceholder);
                        checkbox_back.setVisibility(View.GONE);
                        txt_ex.setText("நீங்கள் வெளியேறினால் உங்களது வெற்றிநாணயங்கள் உங்கள் எதிராளிக்கு சென்றுவிடும்.");

                        backdialog = 1;
                        System.out.println("@@====================================openDialog_p");
                        checkbox_back.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                if (isChecked == true) {
                                    sp.putString(getApplicationContext(), "checkbox_back_m", "yes");
                                } else {
                                    sp.putString(getApplicationContext(), "checkbox_back_m", "");
                                }
                            }
                        });
                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                System.out.println("===========================mCurScreen " + mCurScreen + " R.layout.activity_my__multiplayer" + R.layout.my_game_oddmanout + "R.layout.activity_my__multiplayer" + R.layout.activity_my__multiplayer);
                                if (mCurScreen == R.layout.activity_solukul_sol_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_pic__game_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_clue__game_multiplayer) {
                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_word__game_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.odd_man_out_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.opposite_word_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.makeword__rightorder_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.ote_to_tamil_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.riddle_game_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.error_correction_multiplayer) {

                                    leaveRoom();

                                } else if (mCurScreen == R.layout.activity_my__multiplayer) {
                                        if (main_act.equals("")) {
                                            finish();
                                            Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                                            startActivity(i);
                                        } else {
                                            if (mul_tival==1){
                                                finish();
                                            }else{
                                                finish();
                                                Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Gamelist.class);
                                                startActivity(i);
                                            }

                                    }
                                }
                                backdialog = 0;
                                openDialog_p.dismiss();
                            }
                        });
                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                backdialog = 0;
                                openDialog_p.dismiss();
                            }
                        });
                        openDialog_p.show();
                    }

                }

            }


            return true;
        }

        return super.onKeyDown(keyCode, e);
    }

    //////////////////////////////////////////////////Game Starts Picture Game//////////////////////////////////////////////////////////////
    public void game_start_picture_game () {
        gameid = 1;
        setContentView(R.layout.activity_pic__game_multiplayer);
        switchToScreen(R.layout.activity_pic__game_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        settings = (TextView) findViewById(R.id.settings);
        p_bt1 = (TextView) findViewById(R.id.p_button1);
        p_bt2 = (TextView) findViewById(R.id.p_button2);
        p_bt3 = (TextView) findViewById(R.id.p_button3);
        p_bt4 = (TextView) findViewById(R.id.p_button4);
        p_bt5 = (TextView) findViewById(R.id.p_button5);
        p_bt6 = (TextView) findViewById(R.id.p_button6);
        p_bt7 = (TextView) findViewById(R.id.p_button7);
        p_bt8 = (TextView) findViewById(R.id.p_button8);
        p_bt9 = (TextView) findViewById(R.id.p_button9);
        p_bt10 = (TextView) findViewById(R.id.p_button10);
        p_bt11 = (TextView) findViewById(R.id.p_button11);
        p_bt12 = (TextView) findViewById(R.id.p_button12);
        p_bt13 = (TextView) findViewById(R.id.p_button13);
        p_bt14 = (TextView) findViewById(R.id.p_button14);
        p_bt15 = (TextView) findViewById(R.id.p_button15);
        p_bt16 = (TextView) findViewById(R.id.p_button16);
        p_img1 = (ImageView) findViewById(R.id.image_1);
        p_img2 = (ImageView) findViewById(R.id.image_2);
        p_img3 = (ImageView) findViewById(R.id.image_3);
        p_img4 = (ImageView) findViewById(R.id.image_4);
        p_clear = (TextView) findViewById(R.id.p_clear);
        ans_edit = (EditText) findViewById(R.id.p_ans_editer);
        p_edit = (EditText) findViewById(R.id.p_ans_editer);
        ans_highlite = (TextView) findViewById(R.id.ans_highlite);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        list4 = (LinearLayout) findViewById(R.id.list4);
        opp_img = (ImageView) findViewById(R.id.opp_img);
        my_img = (ImageView) findViewById(R.id.my_img);

        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);


        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);
        Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);
        ans_highlite.setVisibility(View.INVISIBLE);

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        // New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);

                /*if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
            // install_ads();
            /*adds = (LinearLayout) findViewById(R.id.ads_lay);
            //  New_Main_Activity.load_addFromMain_multiplayer(Solli_adi_multiplayer.this,adds);
            //  sp.putInt(Solli_adi_multiplayer.this, "addloded_rect_mul", 0);
            // New_Main_Activity.load_addMultiPlay_REC(Solli_adi_multiplayer.this,adds);

            AdView adView = new AdView(Solli_adi_multiplayer.this);
            //AdSize nativ=new AdSize(340,140);
            adView.setAdUnitId(getString(R.string.multiplayer_banner));
            adView.setAdSize(AdSize.SMART_BANNER);
            //adView.setAdSize(nativ);
            adds.removeAllViews();
            adds.addView(adView);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    System.out.println("WWWWWWWWWWWWWWWWWWWWLoaded");
                    adds.setVisibility(View.VISIBLE);
                }

                public void onAdFailedToLoad(int errorCode) {
                    System.out.println("WWWWWWWWWWWWWWWWWWWWFaild" + errorCode);
                }

            });
            AdRequest request = new AdRequest.Builder().build();
            adView.loadAd(request);*/

        }

        ////////
        next_picture_game();
        ////////
        sound();
        p_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(p_edit.getWindowToken(), 0);
            }
        });
        p_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(p_edit.getWindowToken(), 0);
                return true;
            }
        });

     /*   p_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(1);
                picdig=1;
            }
        });
        p_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(2);
                picdig=1;
            }
        });
        p_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(3);
                picdig=1;
            }
        });
        p_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(4);
                picdig=1;
            }
        });*/
        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        p_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(1);
                picdig = 1;
            }
        });
        p_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(2);
                picdig = 1;
            }
        });
        p_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(3);
                picdig = 1;
            }
        });
        p_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic_show(4);
                picdig = 1;
            }
        });
        oppe_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oppe_msg.setVisibility(View.INVISIBLE);
            }
        });
        p_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt1.startAnimation(shake);
                String ts = p_bt1.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt2.startAnimation(shake);
                String ts = p_bt2.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt3.startAnimation(shake);
                String ts = p_bt3.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt5.startAnimation(shake);
                String ts = p_bt5.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt6.startAnimation(shake);
                String ts = p_bt6.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt7.startAnimation(shake);
                String ts = p_bt7.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt9.startAnimation(shake);
                String ts = p_bt9.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt10.startAnimation(shake);
                String ts = p_bt10.getText().toString();
                p_edit.append(ts);

            }
        });
        p_bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt11.startAnimation(shake);
                String ts = p_bt11.getText().toString();
                p_edit.append(ts);
            }
        });

        p_bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt4.startAnimation(shake);
                String ts = p_bt4.getText().toString();
                p_edit.append(ts);
            }
        });

        p_bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt8.startAnimation(shake);
                String ts = p_bt8.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt12.startAnimation(shake);
                String ts = p_bt12.getText().toString();
                p_edit.append(ts);

            }
        });
        p_bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt13.startAnimation(shake);
                String ts = p_bt13.getText().toString();
                p_edit.append(ts);
            }
        });

        p_bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt14.startAnimation(shake);
                String ts = p_bt14.getText().toString();
                p_edit.append(ts);
            }
        });
        p_bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt15.startAnimation(shake);
                String ts = p_bt15.getText().toString();
                p_edit.append(ts);

            }
        });
        p_bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                p_bt16.startAnimation(shake);
                String ts = p_bt16.getText().toString();
                p_edit.append(ts);

            }
        });
        p_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                // spz1.play(soundId1, sv, sv, 0, 0, sv);
                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });

        p_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                p_edit.setText("");
                return false;
            }
        });

        //////Answer Verify//////
        p_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String answer = p_edit.getText().toString();
                Cursor cd = myDbHelper.getQry("SELECT * FROM maintable where answer ='" + answer + "' and rtm='0' and levelid='" + letterid + "'and gameid='" + gameid + "'");
                cd.moveToFirst();
                if (cd.getCount() != 0) {

                    ans_highlite.setText(answer);
                    ans_highlite.setVisibility(View.VISIBLE);
                    list4.setVisibility(View.INVISIBLE);
                    myDbHelper.executeSql("UPDATE maintable SET rtm=1 WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");

                    st_game = st_game + 1;
                    your_m_count.setText("" + st_game);
                    if (st_game == tt_game) {


                        if (play_after == 0) {
                            play_after = 1;
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                                                /*    int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                                }
                            }, 2000);
                        }

                        mMultiplayer = true;
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            gameactive_you = 1;
                            broadcastScore(false);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }


                    } else {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            //gameactive_you = 1;
                            broadcastScore(true);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letterid = letterid + 1;
                                next_picture_game();
                            }
                        }, 2000);
                    }
                }
            }
        });
        //////Answer Verify//////

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        adsicon2.setVisibility(View.INVISIBLE);
        //install_ads_doalug();
      /*  final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);*/
        ads_logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsicon2.setVisibility(View.INVISIBLE);

            }
        });


    }

    public void next_picture_game () {

        p_bt1.setVisibility(View.VISIBLE);
        p_bt2.setVisibility(View.VISIBLE);
        p_bt3.setVisibility(View.VISIBLE);
        p_bt5.setVisibility(View.VISIBLE);
        p_bt6.setVisibility(View.VISIBLE);
        p_bt7.setVisibility(View.VISIBLE);
        p_bt9.setVisibility(View.VISIBLE);
        p_bt10.setVisibility(View.VISIBLE);
        p_bt11.setVisibility(View.VISIBLE);
        p_bt4.setVisibility(View.VISIBLE);
        p_bt8.setVisibility(View.VISIBLE);
        p_bt12.setVisibility(View.VISIBLE);
        p_bt13.setVisibility(View.VISIBLE);
        p_bt14.setVisibility(View.VISIBLE);
        p_bt15.setVisibility(View.VISIBLE);
        p_bt16.setVisibility(View.VISIBLE);

        p_bt1.setText("");
        p_bt2.setText("");
        p_bt3.setText("");
        p_bt5.setText("");
        p_bt6.setText("");
        p_bt7.setText("");
        p_bt9.setText("");
        p_bt10.setText("");
        p_bt11.setText("");
        p_bt4.setText("");
        p_bt8.setText("");
        p_bt12.setText("");
        p_bt13.setText("");
        p_bt14.setText("");
        p_bt15.setText("");
        p_bt16.setText("");
        p_edit.setText("");

        list4.setVisibility(View.VISIBLE);
        ans_highlite.setVisibility(View.INVISIBLE);
        p_edit.setVisibility(View.VISIBLE);

        if (letterid == 170) {
            letterid = 70;
        }
        Cursor c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'and levelid='" + letterid + "' and rtm='0'");
        c.moveToFirst();

        if (c.getCount() != 0) {
            sa = c.getString(c.getColumnIndexOrThrow("letters"));
            imid = c.getString(c.getColumnIndexOrThrow("imagename"));
            wordid = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("levelid")));
            id = c.getString(c.getColumnIndexOrThrow("id"));
            isdown = c.getString(c.getColumnIndexOrThrow("isdownload"));
            System.out.println("################################imid" + imid);

            String tfoption = sa;
            String[] first = tfoption.split(",");
            int w_type = first.length;
            word_type = w_type;

            String tfoptiona = imid;
            String[] firsta = tfoptiona.split(",");
            int i_type = firsta.length;
            image_type = i_type;

            int min_a = 1;
            int max_a = 3;
            Random rn_a = new Random();
            int random_a = rn_a.nextInt(max_a - min_a + 1) + min_a;
            System.out.println("*****************rnd*** " + random_a);
            if (random_a == 1) {
                simple();
            } else if (random_a == 2) {
                medium();
            } else if (random_a == 3) {
                hard();
            }
        }

    }

    public void simple () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";

     /*   pic_clue.setVisibility(View.VISIBLE);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Picture_Game_Hard.this, R.anim.blink_animation);
        pic_clue.startAnimation(myFadeInAnimation);
        pic_clue.setBackgroundResource(R.drawable.bulbs);
        pic_clue.setText("1");*/

        p_bt4.setVisibility(View.GONE);
        p_bt8.setVisibility(View.GONE);
        p_bt12.setVisibility(View.GONE);
        p_bt13.setVisibility(View.GONE);
        p_bt14.setVisibility(View.GONE);
        p_bt15.setVisibility(View.GONE);
        p_bt16.setVisibility(View.GONE);

        p_bt1.setText("");
        p_bt2.setText("");
        p_bt3.setText("");
        p_bt4.setText("");
        p_bt5.setText("");
        p_bt6.setText("");
        p_bt7.setText("");
        p_bt8.setText("");
        p_bt9.setText("");
        p_bt10.setText("");
        p_bt11.setText("");
        p_bt12.setText("");
        p_bt13.setText("");
        p_bt14.setText("");
        p_bt15.setText("");
        p_bt16.setText("");
        p_edit.setText("");
        //Animation in buttons
        LinearLayout bl1 = (LinearLayout) findViewById(R.id.p_buttonlist1_layout);
        LinearLayout bl2 = (LinearLayout) findViewById(R.id.p_buttonlist2_layout);
        LinearLayout bl3 = (LinearLayout) findViewById(R.id.p_buttonlist3_layout);
        LinearLayout bl4 = (LinearLayout) findViewById(R.id.p_buttonlist4_layout);
       /* Animation h_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button1);
        Animation h_game2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button2);
        bl1.startAnimation(h_game);
        bl2.startAnimation(h_game2);
        bl3.startAnimation(h_game);
        bl4.startAnimation(h_game2);*/


        p_img1.setVisibility(View.GONE);
        p_img2.setVisibility(View.GONE);
        p_img3.setVisibility(View.GONE);
        p_img4.setVisibility(View.GONE);


        if (word_type == 1) {

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
            p_bt1.setText(letter1);
            p_bt2.setText(letter2);
            p_bt3.setText(letter3);
            p_bt5.setText(sa);
            p_bt6.setText(letter6);
            p_bt7.setText(letter7);
            p_bt9.setText(letter9);
            p_bt10.setText(letter10);
            p_bt11.setText(letter11);

        } else if (word_type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter1);
            p_bt2.setText(letter2);
            p_bt3.setText(word1);
            p_bt5.setText(letter5);
            p_bt6.setText(letter6);
            p_bt7.setText(letter7);
            p_bt9.setText(letter9);
            p_bt10.setText(word2);
            p_bt11.setText(letter11);

        } else if (word_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word2);
            p_bt2.setText(letter5);
            p_bt3.setText(letter2);
            p_bt5.setText(word1);
            p_bt6.setText(letter9);
            p_bt7.setText(letter7);
            p_bt9.setText(letter6);
            p_bt10.setText(letter14);
            p_bt11.setText(word3);

        } else if (word_type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter7);
            p_bt2.setText(letter10);
            p_bt3.setText(word3);
            p_bt5.setText(letter6);
            p_bt6.setText(word2);
            p_bt7.setText(word4);
            p_bt9.setText(letter12);
            p_bt10.setText(letter7);
            p_bt11.setText(word1);

        } else if (word_type == 5) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter7);
            p_bt2.setText(word5);
            p_bt3.setText(letter14);
            p_bt5.setText(letter2);
            p_bt6.setText(word4);
            p_bt7.setText(word3);
            p_bt9.setText(word1);
            p_bt10.setText(letter6);
            p_bt11.setText(word2);
        } else if (word_type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word5);
            p_bt2.setText(word2);
            p_bt3.setText(letter4);
            p_bt5.setText(word6);
            p_bt6.setText(letter1);
            p_bt7.setText(word4);
            p_bt9.setText(letter8);
            p_bt10.setText(word1);
            p_bt11.setText(word3);

        } else if (word_type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word3);
            p_bt2.setText(word5);
            p_bt3.setText(word2);
            p_bt5.setText(word7);
            p_bt6.setText(word6);
            p_bt7.setText(word4);
            p_bt9.setText(letter10);
            p_bt10.setText(word1);
            p_bt11.setText(letter12);

        } else if (word_type == 8) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word6);
            p_bt2.setText(letter6);
            p_bt3.setText(word8);
            p_bt5.setText(word5);
            p_bt6.setText(word7);
            p_bt7.setText(word4);
            p_bt9.setText(word1);
            p_bt10.setText(word3);
            p_bt11.setText(word2);
        } else if (word_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word6);
            p_bt2.setText(word1);
            p_bt3.setText(word8);
            p_bt5.setText(word5);
            p_bt6.setText(word9);
            p_bt7.setText(word7);
            p_bt9.setText(word4);
            p_bt10.setText(word3);
            p_bt11.setText(word2);

        }

        if (image_type == 1) {
            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(imid.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im1);
            } else {

                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + imid + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
                    // Toast.makeText(Picture_Game_Hard.this, ""+imid, Toast.LENGTH_SHORT).show();
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    p_img1.setImageDrawable(bd);
                    p_img1.setVisibility(View.VISIBLE);
                } else {

                    if ((ContextCompat.checkSelfPermission(Solli_adi_multiplayer.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        ActivityCompat.requestPermissions(Solli_adi_multiplayer.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 150);
                    } else {
                        missingimage();
                    }
                }


            }


        } else if (image_type == 2) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");


                if (file.exists() && file2.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);
                } else {
                    missingimage();
                }
            }


        } else if (image_type == 3) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
                p_img3.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");

                if (file.exists() && file2.exists() && file3.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    p_img3.setImageDrawable(bd3);
                } else {
                    missingimage();

                }
            }


        } else if (image_type == 4) {

            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());
                im14 = getResources().getIdentifier(word4.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img4.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
                p_img3.setImageResource(im13);
                p_img4.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img4.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                File file4 = new File(fullPath + word4 + "");
                if (file.exists() && file2.exists() && file3.exists() && file4.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    p_img3.setImageDrawable(bd3);

                    Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
                    Resources res4 = getResources();
                    BitmapDrawable bd4 = new BitmapDrawable(res4, bitimg4);
                    p_img4.setImageDrawable(bd4);
                } else {
                    missingimage();
                }

            }


        }


    }

    public void medium () {
        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";


        p_bt1.setText("");
        p_bt2.setText("");
        p_bt3.setText("");
        p_bt4.setText("");
        p_bt5.setText("");
        p_bt6.setText("");
        p_bt7.setText("");
        p_bt8.setText("");
        p_bt9.setText("");
        p_bt10.setText("");
        p_bt11.setText("");
        p_bt12.setText("");
        p_bt13.setText("");
        p_bt14.setText("");
        p_bt15.setText("");
        p_bt16.setText("");

        p_bt13.setVisibility(View.GONE);
        p_bt14.setVisibility(View.GONE);
        p_bt15.setVisibility(View.GONE);
        p_bt16.setVisibility(View.GONE);
        //Button Anim
        LinearLayout bl1 = (LinearLayout) findViewById(R.id.p_buttonlist1_layout);
        LinearLayout bl2 = (LinearLayout) findViewById(R.id.p_buttonlist2_layout);
        LinearLayout bl3 = (LinearLayout) findViewById(R.id.p_buttonlist3_layout);
        LinearLayout bl4 = (LinearLayout) findViewById(R.id.p_buttonlist4_layout);
       /* Animation h_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button1);
        Animation h_game2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.word_button2);
        bl1.startAnimation(h_game);
        bl2.startAnimation(h_game2);
        bl3.startAnimation(h_game);
        bl4.startAnimation(h_game2);*/
        //

        p_img1.setVisibility(View.GONE);
        p_img2.setVisibility(View.GONE);
        p_img3.setVisibility(View.GONE);
        p_img4.setVisibility(View.GONE);
        list4.setVisibility(View.VISIBLE);
        if (word_type == 1) {


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
            p_bt1.setText(letter1);
            p_bt2.setText(letter2);
            p_bt3.setText(sa);
            p_bt4.setText(letter11);
            p_bt5.setText(letter5);
            p_bt6.setText(letter6);
            p_bt7.setText(letter7);
            p_bt8.setText(letter3);
            p_bt9.setText(letter9);
            p_bt10.setText(letter15);
            p_bt11.setText(letter4);
            p_bt12.setText(letter12);


        } else if (word_type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter5);
            p_bt2.setText(letter2);
            p_bt3.setText(word1);
            p_bt4.setText(letter4);
            p_bt5.setText(letter1);
            p_bt6.setText(letter6);
            p_bt7.setText(letter11);
            p_bt8.setText(letter3);
            p_bt9.setText(letter9);
            p_bt10.setText(letter13);
            p_bt11.setText(letter7);
            p_bt12.setText(word2);


        } else if (word_type == 3) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word2);
            p_bt2.setText(letter15);
            p_bt3.setText(word3);
            p_bt4.setText(letter4);
            p_bt5.setText(word1);
            p_bt6.setText(letter9);
            p_bt7.setText(letter7);
            p_bt8.setText(letter3);
            p_bt9.setText(letter6);
            p_bt10.setText(word2);
            p_bt11.setText(letter2);
            p_bt12.setText(letter1);


        } else if (word_type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter13);
            p_bt2.setText(letter10);
            p_bt3.setText(word3);
            p_bt4.setText(letter4);
            p_bt5.setText(letter6);
            p_bt6.setText(word1);
            p_bt7.setText(letter7);
            p_bt8.setText(letter3);
            p_bt9.setText(letter10);
            p_bt10.setText(word4);
            p_bt11.setText(letter12);
            p_bt12.setText(word2);


        } else if (word_type == 5) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter7);
            p_bt2.setText(word5);
            p_bt3.setText(word3);
            p_bt4.setText(letter4);
            p_bt5.setText(letter13);
            p_bt6.setText(word4);
            p_bt7.setText(letter3);
            p_bt8.setText(letter3);
            p_bt9.setText(word1);
            p_bt10.setText(letter6);
            p_bt11.setText(letter8);
            p_bt12.setText(word2);


        } else if (word_type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word2);
            p_bt2.setText(letter9);
            p_bt3.setText(letter4);
            p_bt4.setText(word5);
            p_bt5.setText(letter6);
            p_bt6.setText(letter1);
            p_bt7.setText(word4);
            p_bt8.setText(word6);
            p_bt9.setText(letter8);
            p_bt10.setText(word1);
            p_bt11.setText(word3);
            p_bt12.setText(letter4);


        } else if (word_type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter8);
            p_bt2.setText(letter4);
            p_bt3.setText(word5);
            p_bt4.setText(word2);
            p_bt5.setText(word7);
            p_bt6.setText(word3);
            p_bt7.setText(word4);
            p_bt8.setText(letter11);
            p_bt9.setText(letter10);
            p_bt10.setText(word1);
            p_bt11.setText(letter12);
            p_bt12.setText(word6);


        } else if (word_type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word6);
            p_bt2.setText(letter6);
            p_bt3.setText(word8);
            p_bt4.setText(letter3);
            p_bt5.setText(word5);
            p_bt6.setText(word4);
            p_bt7.setText(letter7);
            p_bt8.setText(word7);
            p_bt9.setText(word1);
            p_bt10.setText(word3);
            p_bt11.setText(word2);
            p_bt12.setText(letter8);


        } else if (word_type == 9) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word6);
            p_bt2.setText(word1);
            p_bt3.setText(word8);
            p_bt4.setText(letter3);
            p_bt5.setText(word5);
            p_bt6.setText(word9);
            p_bt7.setText(letter6);
            p_bt8.setText(word4);
            p_bt9.setText(word2);
            p_bt10.setText(word3);
            p_bt11.setText(word8);
            p_bt12.setText(word7);


        }


        if (image_type == 1) {
            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(imid.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im1);
            } else {
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + imid + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
                    // Toast.makeText(Picture_Game_Hard.this, ""+imid, Toast.LENGTH_SHORT).show();
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    p_img1.setImageDrawable(bd);
                    p_img1.setVisibility(View.VISIBLE);
                } else {
                    missingimage();

                }
            }


        } else if (image_type == 2) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");


                if (file.exists() && file2.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);
                } else {
                    missingimage();
                }
            }


        } else if (image_type == 3) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
                p_img3.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");

                if (file.exists() && file2.exists() && file3.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    p_img3.setImageDrawable(bd3);
                } else {
                    missingimage();

                }
            }


        } else if (image_type == 4) {

            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());
                im14 = getResources().getIdentifier(word4.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img4.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
                p_img3.setImageResource(im13);
                p_img4.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img4.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                File file4 = new File(fullPath + word4 + "");
                if (file.exists() && file2.exists() && file3.exists() && file4.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    p_img3.setImageDrawable(bd3);

                    Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
                    Resources res4 = getResources();
                    BitmapDrawable bd4 = new BitmapDrawable(res4, bitimg4);
                    p_img4.setImageDrawable(bd4);
                } else {
                    missingimage();
                }

            }


        }
    }

    public void hard () {

        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";

        p_bt1.setText("");
        p_bt2.setText("");
        p_bt3.setText("");
        p_bt4.setText("");
        p_bt5.setText("");
        p_bt6.setText("");
        p_bt7.setText("");
        p_bt8.setText("");
        p_bt9.setText("");
        p_bt10.setText("");
        p_bt11.setText("");
        p_bt12.setText("");
        p_bt13.setText("");
        p_bt14.setText("");
        p_bt15.setText("");
        p_bt16.setText("");

        p_img1.setVisibility(View.GONE);
        p_img2.setVisibility(View.GONE);
        p_img3.setVisibility(View.GONE);
        p_img4.setVisibility(View.GONE);

        list4.setVisibility(View.VISIBLE);
        //Button Anim
        LinearLayout bl1 = (LinearLayout) findViewById(R.id.p_buttonlist1_layout);
        LinearLayout bl2 = (LinearLayout) findViewById(R.id.p_buttonlist2_layout);
        LinearLayout bl3 = (LinearLayout) findViewById(R.id.p_buttonlist3_layout);
        LinearLayout bl4 = (LinearLayout) findViewById(R.id.p_buttonlist4_layout);


        if (word_type == 1) {
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
            p_bt1.setText(letter1);
            p_bt2.setText(letter2);
            p_bt3.setText(sa);
            p_bt4.setText(letter11);
            p_bt5.setText(letter5);
            p_bt6.setText(letter6);
            p_bt7.setText(letter7);
            p_bt8.setText(letter3);
            p_bt9.setText(letter9);
            p_bt10.setText(letter15);
            p_bt11.setText(letter4);
            p_bt12.setText(letter12);
            p_bt13.setText(letter10);
            p_bt14.setText(letter14);
            p_bt15.setText(letter12);
            p_bt16.setText(letter13);
        } else if (word_type == 2) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter5);
            p_bt2.setText(letter2);
            p_bt3.setText(word1);
            p_bt4.setText(letter4);
            p_bt5.setText(letter1);
            p_bt6.setText(letter6);
            p_bt7.setText(letter11);
            p_bt8.setText(letter3);
            p_bt9.setText(letter9);
            p_bt10.setText(letter13);
            p_bt11.setText(letter7);
            p_bt12.setText(letter12);
            p_bt13.setText(letter14);
            p_bt14.setText(word2);
            p_bt15.setText(letter10);
            p_bt16.setText(letter8);
        } else if (word_type == 3) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word2);
            p_bt2.setText(letter15);
            p_bt3.setText(word3);
            p_bt4.setText(letter4);
            p_bt5.setText(word1);
            p_bt6.setText(letter9);
            p_bt7.setText(letter7);
            p_bt8.setText(letter3);
            p_bt9.setText(letter6);
            p_bt10.setText(word2);
            p_bt11.setText(letter2);
            p_bt12.setText(letter1);
            p_bt13.setText(letter14);
            p_bt14.setText(letter12);
            p_bt15.setText(letter10);
            p_bt16.setText(letter13);
        } else if (word_type == 4) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter13);
            p_bt2.setText(letter10);
            p_bt3.setText(word3);
            p_bt4.setText(letter4);
            p_bt5.setText(letter6);
            p_bt6.setText(letter11);
            p_bt7.setText(letter7);
            p_bt8.setText(letter3);
            p_bt9.setText(letter10);
            p_bt10.setText(word4);
            p_bt11.setText(letter12);
            p_bt12.setText(letter9);
            p_bt13.setText(letter14);
            p_bt14.setText(letter15);
            p_bt15.setText(word1);
            p_bt16.setText(word2);
        } else if (word_type == 5) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter7);
            p_bt2.setText(word5);
            p_bt3.setText(word3);
            p_bt4.setText(letter4);
            p_bt5.setText(letter13);
            p_bt6.setText(word4);
            p_bt7.setText(letter3);
            p_bt8.setText(letter3);
            p_bt9.setText(word1);
            p_bt10.setText(letter6);
            p_bt11.setText(letter8);
            p_bt12.setText(word2);
            p_bt13.setText(letter14);
            p_bt14.setText(letter12);
            p_bt15.setText(letter9);
            p_bt16.setText(letter10);
        } else if (word_type == 6) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter14);
            p_bt2.setText(letter9);
            p_bt3.setText(letter4);
            p_bt4.setText(word5);
            p_bt5.setText(letter13);
            p_bt6.setText(letter8);
            p_bt7.setText(word4);
            p_bt8.setText(letter7);
            p_bt9.setText(letter1);
            p_bt10.setText(word1);
            p_bt11.setText(word3);
            p_bt12.setText(letter4);
            p_bt13.setText(word6);
            p_bt14.setText(letter11);
            p_bt15.setText(letter6);
            p_bt16.setText(word2);
        } else if (word_type == 7) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(letter2);
            p_bt2.setText(letter4);
            p_bt3.setText(word5);
            p_bt4.setText(word2);
            p_bt5.setText(word7);
            p_bt6.setText(letter13);
            p_bt7.setText(word4);
            p_bt8.setText(letter8);
            p_bt9.setText(letter10);
            p_bt10.setText(letter15);
            p_bt11.setText(letter12);
            p_bt12.setText(word6);
            p_bt13.setText(letter6);
            p_bt14.setText(word1);
            p_bt15.setText(letter11);
            p_bt16.setText(word3);
        } else if (word_type == 8) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word6);
            p_bt2.setText(letter15);
            p_bt3.setText(word8);
            p_bt4.setText(letter13);
            p_bt5.setText(word5);
            p_bt6.setText(word4);
            p_bt7.setText(letter12);
            p_bt8.setText(word7);
            p_bt9.setText(word1);
            p_bt10.setText(word3);
            p_bt11.setText(letter14);
            p_bt12.setText(letter8);
            p_bt13.setText(letter6);
            p_bt14.setText(letter7);
            p_bt15.setText(word2);
            p_bt16.setText(letter10);

        } else if (word_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            p_bt1.setText(word6);
            p_bt2.setText(word1);
            p_bt3.setText(letter2);
            p_bt4.setText(letter13);
            p_bt5.setText(word5);
            p_bt6.setText(word9);
            p_bt7.setText(letter6);
            p_bt8.setText(word4);
            p_bt9.setText(letter3);
            p_bt10.setText(word3);
            p_bt11.setText(word8);
            p_bt12.setText(word7);
            p_bt13.setText(letter6);
            p_bt14.setText(letter2);
            p_bt15.setText(word2);
            p_bt16.setText(word8);

        }


        if (image_type == 1) {
            if (isdown.equals("0")) {
                int im1 = getResources().getIdentifier(imid.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im1);
            } else {
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";
                File file = new File(fullPath + imid + "");
                if (file.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
                    // Toast.makeText(Picture_Game_Hard.this, ""+imid, Toast.LENGTH_SHORT).show();
                    Resources res = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res, bitimg1);
                    p_img1.setImageDrawable(bd);
                    p_img1.setVisibility(View.VISIBLE);
                } else {
                    missingimage();
                }
            }
        } else if (image_type == 2) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                if (file.exists() && file2.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);
                } else {
                    missingimage();
                }
            }

        } else if (image_type == 3) {
            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
                p_img3.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");

                if (file.exists() && file2.exists() && file3.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    p_img3.setImageDrawable(bd3);
                } else {
                    missingimage();
                }
            }


        } else if (image_type == 4) {

            if (isdown.equals("0")) {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                im11 = getResources().getIdentifier(word1.replace(".webp", ""), "drawable", getPackageName());
                im12 = getResources().getIdentifier(word2.replace(".webp", ""), "drawable", getPackageName());
                im13 = getResources().getIdentifier(word3.replace(".webp", ""), "drawable", getPackageName());
                im14 = getResources().getIdentifier(word4.replace(".webp", ""), "drawable", getPackageName());
                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img4.setVisibility(View.VISIBLE);
                p_img1.setImageResource(im11);
                p_img2.setImageResource(im12);
                p_img3.setImageResource(im13);
                p_img4.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";

                p_img1.setVisibility(View.VISIBLE);
                p_img2.setVisibility(View.VISIBLE);
                p_img3.setVisibility(View.VISIBLE);
                p_img4.setVisibility(View.VISIBLE);

                File file = new File(fullPath + word1 + "");
                File file2 = new File(fullPath + word2 + "");
                File file3 = new File(fullPath + word3 + "");
                File file4 = new File(fullPath + word4 + "");
                if (file.exists() && file2.exists() && file3.exists() && file4.exists()) {
                    Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                    Resources res1 = getResources();
                    BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                    p_img1.setImageDrawable(bd);

                    Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                    Resources res2 = getResources();
                    BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                    p_img2.setImageDrawable(bd2);


                    Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                    Resources res3 = getResources();
                    BitmapDrawable bd3 = new BitmapDrawable(res3, bitimg3);
                    p_img3.setImageDrawable(bd3);

                    Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
                    Resources res4 = getResources();
                    BitmapDrawable bd4 = new BitmapDrawable(res4, bitimg4);
                    p_img4.setImageDrawable(bd4);
                } else {
                    missingimage();
                }

            }
        }
    }
    //////////////////////////////////////////////////Game Starts Picture Game//////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////Game Starts Clue Game//////////////////////////////////////////////////////////////
    public void game_start_clue_game () {
        gameid = 2;
        setContentView(R.layout.activity_clue__game_multiplayer);
        switchToScreen(R.layout.activity_clue__game_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ans_edit = (EditText) findViewById(R.id.clue_ans_editer);
        settings = (TextView) findViewById(R.id.settings);
        c_clear = (TextView) findViewById(R.id.clue_clear);
        ans_high = (TextView) findViewById(R.id.ans_highlite);
        ans_highlite = (TextView) findViewById(R.id.ans_highlite);
        c_clear = (Button) findViewById(R.id.clue_clear);
        c_ans = (TextView) findViewById(R.id.c_ans);
        clue1 = (TextView) findViewById(R.id.clue1_ans);
        clue2 = (TextView) findViewById(R.id.clue2_ans);
        clue3 = (TextView) findViewById(R.id.clue3_ans);
        clue1_txt = (TextView) findViewById(R.id.clue1_txt);
        clue2_txt = (TextView) findViewById(R.id.clue2_txt);
        clue3_txt = (TextView) findViewById(R.id.clue3_txt);

        adds = (LinearLayout) findViewById(R.id.ads_lay);
        list4 = (LinearLayout) findViewById(R.id.list4);

        c_bt1 = (TextView) findViewById(R.id.c_button1);
        c_bt2 = (TextView) findViewById(R.id.c_button2);
        c_bt3 = (TextView) findViewById(R.id.c_button3);
        c_bt4 = (TextView) findViewById(R.id.c_button4);
        c_bt5 = (TextView) findViewById(R.id.c_button5);
        c_bt6 = (TextView) findViewById(R.id.c_button6);
        c_bt7 = (TextView) findViewById(R.id.c_button7);
        c_bt8 = (TextView) findViewById(R.id.c_button8);
        c_bt9 = (TextView) findViewById(R.id.c_button9);
        c_bt10 = (TextView) findViewById(R.id.c_button10);
        c_bt11 = (TextView) findViewById(R.id.c_button11);
        c_bt12 = (TextView) findViewById(R.id.c_button12);
        c_bt13 = (TextView) findViewById(R.id.c_button13);
        c_bt14 = (TextView) findViewById(R.id.c_button14);
        c_bt15 = (TextView) findViewById(R.id.c_button15);
        c_bt16 = (TextView) findViewById(R.id.c_button16);
        opp_img = (ImageView) findViewById(R.id.opp_img);
        my_img = (ImageView) findViewById(R.id.my_img);

        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);

        opponent_count = (TextView) findViewById(R.id.opponent_count);
        qus_count = (TextView) findViewById(R.id.qus_count);
        your_count = (TextView) findViewById(R.id.your_count);
        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);
        your_count.setText("" + score_s);


        Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);

        next_clue_game();
        sound();
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        ans_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ans_edit.getWindowToken(), 0);
            }
        });
        ans_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ans_edit.getWindowToken(), 0);
                return true;
            }
        });

        oppe_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                oppe_msg.setVisibility(View.INVISIBLE);
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        c_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt1.startAnimation(shake);
                String ts = c_bt1.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt2.startAnimation(shake);
                String ts = c_bt2.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                // click.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt3.startAnimation(shake);
                String ts = c_bt3.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt5.startAnimation(shake);
                String ts = c_bt5.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt6.startAnimation(shake);
                String ts = c_bt6.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt7.startAnimation(shake);
                String ts = c_bt7.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt9.startAnimation(shake);
                String ts = c_bt9.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt10.startAnimation(shake);
                String ts = c_bt10.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt11.startAnimation(shake);
                String ts = c_bt11.getText().toString();
                ans_edit.append(ts);
            }
        });

        c_bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt4.startAnimation(shake);
                String ts = c_bt4.getText().toString();
                ans_edit.append(ts);
            }
        });

        c_bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt8.startAnimation(shake);
                String ts = c_bt8.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt12.startAnimation(shake);
                String ts = c_bt12.getText().toString();
                ans_edit.append(ts);

            }
        });
        c_bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt13.startAnimation(shake);
                String ts = c_bt13.getText().toString();
                ans_edit.append(ts);
            }
        });

        c_bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt14.startAnimation(shake);
                String ts = c_bt14.getText().toString();
                ans_edit.append(ts);
            }
        });
        c_bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt15.startAnimation(shake);
                String ts = c_bt15.getText().toString();
                ans_edit.append(ts);

            }
        });
        c_bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                c_bt16.startAnimation(shake);
                String ts = c_bt16.getText().toString();
                ans_edit.append(ts);

            }
        });


        c_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });

        c_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ans_edit.setText("");
                return false;
            }
        });


//User Verifing Answer
        c_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Verifing Answer
        ans_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = ans_edit.getText().toString();
                Cursor cd = myDbHelper.getQry("SELECT * FROM maintable where answer ='" + answer + "' and rtm='0' and levelid='" + letterid + "'and gameid='" + gameid + "'");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    ans_highlite.setText(answer);
                    ans_highlite.setVisibility(View.VISIBLE);
                    list4.setVisibility(View.INVISIBLE);
                    myDbHelper.executeSql("UPDATE maintable SET rtm=1 WHERE levelid='" + letterid + "'and gameid='" + gameid + "'");


                 /*   mMultiplayer = true;
                    broadcastScore(false);

                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx + 200;
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                            finalscreen(200,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");

                           *//* int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                            sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                            finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*//*
                        }
                    }, 2000);
*/

                    st_game = st_game + 1;
                    your_m_count.setText("" + st_game);
                    if (st_game == tt_game) {


                        if (play_after == 0) {
                            play_after = 1;
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");

                               /* int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                                }
                            }, 2000);
                        }

                        mMultiplayer = true;
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            gameactive_you = 1;
                            broadcastScore(false);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }

                    } else {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            // gameactive_you = 1;
                            broadcastScore(true);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letterid = letterid + 1;
                                next_clue_game();

                            }
                        }, 2000);

                    }

                }

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
      /*  final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);*/
        adsicon2.setVisibility(View.INVISIBLE);
        //install_ads_doalug();
        ads_logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsicon2.setVisibility(View.INVISIBLE);

            }
        });

    }

    public void next_clue_game () {
        ans_highlite.setVisibility(View.INVISIBLE);
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
               /* if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }


        c_bt1.setText("");
        c_bt2.setText("");
        c_bt3.setText("");
        c_bt5.setText("");
        c_bt6.setText("");
        c_bt7.setText("");
        c_bt9.setText("");
        c_bt10.setText("");
        c_bt11.setText("");
        c_bt4.setText("");
        c_bt8.setText("");
        c_bt12.setText("");
        c_bt13.setText("");
        c_bt14.setText("");
        c_bt15.setText("");
        c_bt16.setText("");

        c_bt1.setVisibility(View.VISIBLE);
        c_bt2.setVisibility(View.VISIBLE);
        c_bt3.setVisibility(View.VISIBLE);
        c_bt5.setVisibility(View.VISIBLE);
        c_bt6.setVisibility(View.VISIBLE);
        c_bt7.setVisibility(View.VISIBLE);
        c_bt9.setVisibility(View.VISIBLE);
        c_bt10.setVisibility(View.VISIBLE);
        c_bt11.setVisibility(View.VISIBLE);
        c_bt4.setVisibility(View.VISIBLE);
        c_bt8.setVisibility(View.VISIBLE);
        c_bt12.setVisibility(View.VISIBLE);
        c_bt13.setVisibility(View.VISIBLE);
        c_bt14.setVisibility(View.VISIBLE);
        c_bt15.setVisibility(View.VISIBLE);
        c_bt16.setVisibility(View.VISIBLE);

        ans_edit.setText("");
        clue1.setText("");
        clue2.setText("");
        clue3.setText("");
        clue1_txt.setText("");
        clue2_txt.setText("");
        clue3_txt.setText("");
        clue1.setVisibility(View.VISIBLE);
        clue2.setVisibility(View.VISIBLE);
        clue3.setVisibility(View.VISIBLE);
        clue1_txt.setVisibility(View.VISIBLE);
        clue2_txt.setVisibility(View.VISIBLE);
        clue3_txt.setVisibility(View.VISIBLE);

        list4.setVisibility(View.VISIBLE);


        gameid = 2;
        Cursor c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'and levelid='" + letterid + "' and rtm='0'");
        c.moveToFirst();
        if (c.getCount() != 0) {
            sa = c.getString(c.getColumnIndexOrThrow("letters"));
            String sb = c.getString(c.getColumnIndexOrThrow("hints"));


            StringTokenizer tok = new StringTokenizer(sb, ",");
            String cs1 = tok.nextToken();
            String cs2 = tok.nextToken();
            String cs3 = tok.nextToken();

            clue1.setVisibility(View.VISIBLE);
            clue1.setText("1: " + cs1);

            clue2.setVisibility(View.VISIBLE);
            clue2.setText("2: " + cs2);

            clue1.setVisibility(View.VISIBLE);
            clue3.setText("3: " + cs3);


            String tfoption = sa;
            String[] first = tfoption.split(",");
            c_type = first.length;

            int min_a = 1;
            int max_a = 3;
            Random rn_a = new Random();
            int random_a = rn_a.nextInt(max_a - min_a + 1) + min_a;
            System.out.println("*****************rnd*** " + random_a);
            if (random_a == 1) {
                simple_c();
            } else if (random_a == 2) {
                medium_c();
            } else if (random_a == 3) {
                hard_c();
            }
        }

    }

    public void simple_c () {
        c_bt1.setVisibility(View.VISIBLE);
        c_bt2.setVisibility(View.VISIBLE);
        c_bt3.setVisibility(View.VISIBLE);
        c_bt5.setVisibility(View.VISIBLE);
        c_bt6.setVisibility(View.VISIBLE);
        c_bt7.setVisibility(View.VISIBLE);
        c_bt9.setVisibility(View.VISIBLE);
        c_bt10.setVisibility(View.VISIBLE);
        c_bt11.setVisibility(View.VISIBLE);
        c_bt4.setVisibility(View.VISIBLE);
        c_bt8.setVisibility(View.VISIBLE);
        c_bt12.setVisibility(View.VISIBLE);
        c_bt13.setVisibility(View.VISIBLE);
        c_bt14.setVisibility(View.VISIBLE);
        c_bt15.setVisibility(View.VISIBLE);
        c_bt16.setVisibility(View.VISIBLE);
        c_bt1.setText("");
        c_bt2.setText("");
        c_bt3.setText("");
        c_bt4.setText("");
        c_bt5.setText("");
        c_bt6.setText("");
        c_bt7.setText("");
        c_bt8.setText("");
        c_bt9.setText("");
        c_bt10.setText("");
        c_bt11.setText("");
        c_bt12.setText("");
        c_bt13.setText("");
        c_bt14.setText("");
        c_bt15.setText("");
        c_bt16.setText("");

        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";
        c_bt4.setVisibility(View.GONE);
        c_bt8.setVisibility(View.GONE);
        c_bt12.setVisibility(View.GONE);
        c_bt13.setVisibility(View.GONE);
        c_bt14.setVisibility(View.GONE);
        c_bt15.setVisibility(View.GONE);
        c_bt16.setVisibility(View.GONE);
        if (c_type == 1) {
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
            c_bt1.setText(letter1);
            c_bt2.setText(letter2);
            c_bt3.setText(letter3);
            c_bt5.setText(sa);
            c_bt6.setText(letter6);
            c_bt7.setText(letter7);
            c_bt9.setText(letter9);
            c_bt10.setText(letter10);
            c_bt11.setText(letter11);

        } else if (c_type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter1);
            c_bt2.setText(letter2);
            c_bt3.setText(word1);
            c_bt5.setText(letter5);
            c_bt6.setText(letter6);
            c_bt7.setText(letter7);
            c_bt9.setText(letter9);
            c_bt10.setText(word2);
            c_bt11.setText(letter11);

        } else if (c_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word2);
            c_bt2.setText(letter5);
            c_bt3.setText(letter2);
            c_bt5.setText(word1);
            c_bt6.setText(letter9);
            c_bt7.setText(letter7);
            c_bt9.setText(letter6);
            c_bt10.setText(letter14);
            c_bt11.setText(word3);

        } else if (c_type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word2);
            c_bt2.setText(letter10);
            c_bt3.setText(word3);
            c_bt5.setText(letter6);
            c_bt6.setText(letter13);
            c_bt7.setText(word4);
            c_bt9.setText(word1);
            c_bt10.setText(letter7);
            c_bt11.setText(letter12);

        } else if (c_type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter7);
            c_bt2.setText(word5);
            c_bt3.setText(letter14);
            c_bt5.setText(letter2);
            c_bt6.setText(word4);
            c_bt7.setText(word3);
            c_bt9.setText(word1);
            c_bt10.setText(letter6);
            c_bt11.setText(word2);

        } else if (c_type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word5);
            c_bt2.setText(word2);
            c_bt3.setText(letter4);
            c_bt5.setText(word6);
            c_bt6.setText(letter1);
            c_bt7.setText(word4);
            c_bt9.setText(letter8);
            c_bt10.setText(word1);
            c_bt11.setText(word3);

        } else if (c_type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word3);
            c_bt2.setText(word5);
            c_bt3.setText(word2);
            c_bt5.setText(word7);
            c_bt6.setText(word6);
            c_bt7.setText(word4);
            c_bt9.setText(letter10);
            c_bt10.setText(word1);
            c_bt11.setText(letter12);

        } else if (c_type == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word6);
            c_bt2.setText(letter6);
            c_bt3.setText(word8);
            c_bt5.setText(word5);
            c_bt6.setText(word7);
            c_bt7.setText(word4);
            c_bt9.setText(word1);
            c_bt10.setText(word3);
            c_bt11.setText(word2);

        } else if (c_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word6);
            c_bt2.setText(word1);
            c_bt3.setText(word8);
            c_bt5.setText(word5);
            c_bt6.setText(word9);
            c_bt7.setText(word7);
            c_bt9.setText(word4);
            c_bt10.setText(word3);
            c_bt11.setText(word2);

        }
    }

    public void medium_c () {
        c_bt1.setText("");
        c_bt2.setText("");
        c_bt3.setText("");
        c_bt4.setText("");
        c_bt5.setText("");
        c_bt6.setText("");
        c_bt7.setText("");
        c_bt8.setText("");
        c_bt9.setText("");
        c_bt10.setText("");
        c_bt11.setText("");
        c_bt12.setText("");
        c_bt13.setText("");
        c_bt14.setText("");
        c_bt15.setText("");
        c_bt16.setText("");
        c_bt1.setVisibility(View.VISIBLE);
        c_bt2.setVisibility(View.VISIBLE);
        c_bt3.setVisibility(View.VISIBLE);
        c_bt5.setVisibility(View.VISIBLE);
        c_bt6.setVisibility(View.VISIBLE);
        c_bt7.setVisibility(View.VISIBLE);
        c_bt9.setVisibility(View.VISIBLE);
        c_bt10.setVisibility(View.VISIBLE);
        c_bt11.setVisibility(View.VISIBLE);
        c_bt4.setVisibility(View.VISIBLE);
        c_bt8.setVisibility(View.VISIBLE);
        c_bt12.setVisibility(View.VISIBLE);
        c_bt13.setVisibility(View.VISIBLE);
        c_bt14.setVisibility(View.VISIBLE);
        c_bt15.setVisibility(View.VISIBLE);
        c_bt16.setVisibility(View.VISIBLE);

        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        c_bt13.setVisibility(View.GONE);
        c_bt14.setVisibility(View.GONE);
        c_bt15.setVisibility(View.GONE);
        c_bt16.setVisibility(View.GONE);
        if (c_type == 1) {

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
            c_bt1.setText(letter1);
            c_bt2.setText(letter2);
            c_bt3.setText(sa);
            c_bt4.setText(letter11);
            c_bt5.setText(letter5);
            c_bt6.setText(letter6);
            c_bt7.setText(letter7);
            c_bt8.setText(letter3);
            c_bt9.setText(letter9);
            c_bt10.setText(letter15);
            c_bt11.setText(letter4);
            c_bt12.setText(letter12);


        } else if (c_type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter5);
            c_bt2.setText(letter2);
            c_bt3.setText(word1);
            c_bt4.setText(letter4);
            c_bt5.setText(letter1);
            c_bt6.setText(letter6);
            c_bt7.setText(letter11);
            c_bt8.setText(letter3);
            c_bt9.setText(letter9);
            c_bt10.setText(letter13);
            c_bt11.setText(letter7);
            c_bt12.setText(word2);


        } else if (c_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word2);
            c_bt2.setText(letter15);
            c_bt3.setText(word3);
            c_bt4.setText(letter4);
            c_bt5.setText(word1);
            c_bt6.setText(letter9);
            c_bt7.setText(letter7);
            c_bt8.setText(letter3);
            c_bt9.setText(letter6);
            c_bt10.setText(word2);
            c_bt11.setText(letter2);
            c_bt12.setText(letter1);


        } else if (c_type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter13);
            c_bt2.setText(letter10);
            c_bt3.setText(word3);
            c_bt4.setText(letter4);
            c_bt5.setText(letter6);
            c_bt6.setText(word1);
            c_bt7.setText(letter7);
            c_bt8.setText(letter3);
            c_bt9.setText(letter10);
            c_bt10.setText(word4);
            c_bt11.setText(letter12);
            c_bt12.setText(word2);


        } else if (c_type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter7);
            c_bt2.setText(word5);
            c_bt3.setText(word3);
            c_bt4.setText(letter4);
            c_bt5.setText(letter13);
            c_bt6.setText(word4);
            c_bt7.setText(letter3);
            c_bt8.setText(letter3);
            c_bt9.setText(word1);
            c_bt10.setText(letter6);
            c_bt11.setText(letter8);
            c_bt12.setText(word2);

        } else if (c_type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word2);
            c_bt2.setText(letter9);
            c_bt3.setText(letter4);
            c_bt4.setText(word5);
            c_bt5.setText(letter6);
            c_bt6.setText(letter1);
            c_bt7.setText(word4);
            c_bt8.setText(word6);
            c_bt9.setText(letter8);
            c_bt10.setText(word1);
            c_bt11.setText(word3);
            c_bt12.setText(letter4);


        } else if (c_type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter8);
            c_bt2.setText(letter4);
            c_bt3.setText(word5);
            c_bt4.setText(word2);
            c_bt5.setText(word7);
            c_bt6.setText(word3);
            c_bt7.setText(word4);
            c_bt8.setText(letter11);
            c_bt9.setText(letter10);
            c_bt10.setText(word1);
            c_bt11.setText(letter12);
            c_bt12.setText(word6);


        } else if (c_type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word6);
            c_bt2.setText(letter6);
            c_bt3.setText(word8);
            c_bt4.setText(letter3);
            c_bt5.setText(word5);
            c_bt6.setText(word4);
            c_bt7.setText(letter7);
            c_bt8.setText(word7);
            c_bt9.setText(word1);
            c_bt10.setText(word3);
            c_bt11.setText(word2);
            c_bt12.setText(letter8);


        } else if (c_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word6);
            c_bt2.setText(word1);
            c_bt3.setText(word8);
            c_bt4.setText(letter3);
            c_bt5.setText(word5);
            c_bt6.setText(word9);
            c_bt7.setText(letter6);
            c_bt8.setText(word4);
            c_bt9.setText(word2);
            c_bt10.setText(word3);
            c_bt11.setText(word8);
            c_bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }
    }

    public void hard_c () {
        c_bt1.setText("");
        c_bt2.setText("");
        c_bt3.setText("");
        c_bt4.setText("");
        c_bt5.setText("");
        c_bt6.setText("");
        c_bt7.setText("");
        c_bt8.setText("");
        c_bt9.setText("");
        c_bt10.setText("");
        c_bt11.setText("");
        c_bt12.setText("");
        c_bt13.setText("");
        c_bt14.setText("");
        c_bt15.setText("");
        c_bt16.setText("");
        c_bt1.setVisibility(View.VISIBLE);
        c_bt2.setVisibility(View.VISIBLE);
        c_bt3.setVisibility(View.VISIBLE);
        c_bt5.setVisibility(View.VISIBLE);
        c_bt6.setVisibility(View.VISIBLE);
        c_bt7.setVisibility(View.VISIBLE);
        c_bt9.setVisibility(View.VISIBLE);
        c_bt10.setVisibility(View.VISIBLE);
        c_bt11.setVisibility(View.VISIBLE);
        c_bt4.setVisibility(View.VISIBLE);
        c_bt8.setVisibility(View.VISIBLE);
        c_bt12.setVisibility(View.VISIBLE);
        c_bt13.setVisibility(View.VISIBLE);
        c_bt14.setVisibility(View.VISIBLE);
        c_bt15.setVisibility(View.VISIBLE);
        c_bt16.setVisibility(View.VISIBLE);

        String a = "கே,சே,பி,யா,தி,ரே,ஏ,வ்,ர்,கே,ஃ,ஓ,ப,ல்,இ,து,பு,மு,ரூ";

        if (c_type == 1) {
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
            c_bt1.setText(letter1);
            c_bt2.setText(letter2);
            c_bt3.setText(sa);
            c_bt4.setText(letter11);
            c_bt5.setText(letter5);
            c_bt6.setText(letter6);
            c_bt7.setText(letter7);
            c_bt8.setText(letter3);
            c_bt9.setText(letter9);
            c_bt10.setText(letter15);
            c_bt11.setText(letter4);
            c_bt12.setText(letter12);
            c_bt13.setText(letter10);
            c_bt14.setText(letter14);
            c_bt15.setText(letter12);
            c_bt16.setText(letter13);
        } else if (c_type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter5);
            c_bt2.setText(letter2);
            c_bt3.setText(word1);
            c_bt4.setText(letter4);
            c_bt5.setText(letter1);
            c_bt6.setText(letter6);
            c_bt7.setText(letter11);
            c_bt8.setText(letter3);
            c_bt9.setText(letter9);
            c_bt10.setText(letter13);
            c_bt11.setText(letter7);
            c_bt12.setText(letter12);
            c_bt13.setText(letter14);
            c_bt14.setText(word2);
            c_bt15.setText(letter10);
            c_bt16.setText(letter8);


        } else if (c_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word2);
            c_bt2.setText(letter15);
            c_bt3.setText(word3);
            c_bt4.setText(letter4);
            c_bt5.setText(word1);
            c_bt6.setText(letter9);
            c_bt7.setText(letter7);
            c_bt8.setText(letter3);
            c_bt9.setText(letter6);
            c_bt10.setText(word2);
            c_bt11.setText(letter2);
            c_bt12.setText(letter1);
            c_bt13.setText(letter14);
            c_bt14.setText(letter12);
            c_bt15.setText(letter10);
            c_bt16.setText(letter13);

        } else if (c_type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter13);
            c_bt2.setText(letter10);
            c_bt3.setText(word3);
            c_bt4.setText(letter4);
            c_bt5.setText(letter6);
            c_bt6.setText(letter11);
            c_bt7.setText(letter7);
            c_bt8.setText(letter3);
            c_bt9.setText(letter10);
            c_bt10.setText(word4);
            c_bt11.setText(letter12);
            c_bt12.setText(letter9);
            c_bt13.setText(letter14);
            c_bt14.setText(letter15);
            c_bt15.setText(word1);
            c_bt16.setText(word2);

        } else if (c_type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter7);
            c_bt2.setText(word5);
            c_bt3.setText(word3);
            c_bt4.setText(letter4);
            c_bt5.setText(letter13);
            c_bt6.setText(word4);
            c_bt7.setText(letter3);
            c_bt8.setText(letter3);
            c_bt9.setText(word1);
            c_bt10.setText(letter6);
            c_bt11.setText(letter8);
            c_bt12.setText(word2);
            c_bt13.setText(letter14);
            c_bt14.setText(letter12);
            c_bt15.setText(letter9);
            c_bt16.setText(letter10);


        } else if (c_type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter14);
            c_bt2.setText(letter9);
            c_bt3.setText(letter4);
            c_bt4.setText(word5);
            c_bt5.setText(letter13);
            c_bt6.setText(letter8);
            c_bt7.setText(word4);
            c_bt8.setText(letter7);
            c_bt9.setText(letter1);
            c_bt10.setText(word1);
            c_bt11.setText(word3);
            c_bt12.setText(letter4);
            c_bt13.setText(word6);
            c_bt14.setText(letter11);
            c_bt15.setText(letter6);
            c_bt16.setText(word2);


        } else if (c_type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(letter2);
            c_bt2.setText(letter4);
            c_bt3.setText(word5);
            c_bt4.setText(word2);
            c_bt5.setText(word7);
            c_bt6.setText(letter13);
            c_bt7.setText(word4);
            c_bt8.setText(letter8);
            c_bt9.setText(letter10);
            c_bt10.setText(letter15);
            c_bt11.setText(letter12);
            c_bt12.setText(word6);
            c_bt13.setText(letter6);
            c_bt14.setText(word1);
            c_bt15.setText(letter11);
            c_bt16.setText(word3);

        } else if (c_type == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word6);
            c_bt2.setText(letter15);
            c_bt3.setText(word8);
            c_bt4.setText(letter13);
            c_bt5.setText(word5);
            c_bt6.setText(word4);
            c_bt7.setText(letter12);
            c_bt8.setText(word7);
            c_bt9.setText(word1);
            c_bt10.setText(word3);
            c_bt11.setText(letter14);
            c_bt12.setText(letter8);
            c_bt13.setText(letter6);
            c_bt14.setText(letter7);
            c_bt15.setText(word2);
            c_bt16.setText(letter10);

        } else if (c_type == 9) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            c_bt1.setText(word6);
            c_bt2.setText(word1);
            c_bt3.setText(letter2);
            c_bt4.setText(letter13);
            c_bt5.setText(word5);
            c_bt6.setText(word9);
            c_bt7.setText(letter6);
            c_bt8.setText(word4);
            c_bt9.setText(letter3);
            c_bt10.setText(word3);
            c_bt11.setText(word8);
            c_bt12.setText(word7);
            c_bt13.setText(letter6);
            c_bt14.setText(letter2);
            c_bt15.setText(word2);
            c_bt16.setText(word8);
        }
    }
    //////////////////////////////////////////////////Game Starts Clue Game//////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////Game Starts Sol Game//////////////////////////////////////////////////////////////
    public void game_start_sol_game () {
        gameid = 4;
        setContentView(R.layout.activity_word__game_multiplayer);
        switchToScreen(R.layout.activity_word__game_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        edit_buttons_layout = (RelativeLayout) findViewById(R.id.edit_buttons_layout);
        head = (RelativeLayout) findViewById(R.id.w_head);
        verify = (TextView) findViewById(R.id.verify);
        clear = (Button) findViewById(R.id.clear);
        settings = (TextView) findViewById(R.id.settings);
     /* w_q1=(ImageView)findViewById(R.id.value_ans1);
        w_q2=(ImageView)findViewById(R.id.value_ans2);
        w_q3=(ImageView)findViewById(R.id.value_ans3);
        w_q4=(ImageView)findViewById(R.id.value_ans4);
        w_q5=(ImageView)findViewById(R.id.value_ans5);
        w_q6=(ImageView)findViewById(R.id.value_ans6);
        w_q7=(ImageView)findViewById(R.id.value_ans7);
        w_q8=(ImageView)findViewById(R.id.value_ans8);
        w_q9=(ImageView)findViewById(R.id.value_ans9);
        w_q10=(ImageView)findViewById(R.id.value_ans10);
        w_q11=(ImageView)findViewById(R.id.value_ans11);
        w_q12=(ImageView)findViewById(R.id.value_ans12);
        w_q13=(ImageView)findViewById(R.id.value_ans13);
        w_q14=(ImageView)findViewById(R.id.value_ans14);*/

        ans_edit = (EditText) findViewById(R.id.ans_editer);
        w_bt1 = (TextView) findViewById(R.id.button1);
        w_bt2 = (TextView) findViewById(R.id.button2);
        w_bt3 = (TextView) findViewById(R.id.button3);
        w_bt4 = (TextView) findViewById(R.id.button4);
        w_bt5 = (TextView) findViewById(R.id.button5);
        w_bt6 = (TextView) findViewById(R.id.button6);
        w_bt7 = (TextView) findViewById(R.id.button7);
        w_bt8 = (TextView) findViewById(R.id.button8);
        w_bt9 = (TextView) findViewById(R.id.button9);
        w_bt10 = (TextView) findViewById(R.id.button10);
        w_bt11 = (TextView) findViewById(R.id.button11);
        w_bt12 = (TextView) findViewById(R.id.button12);
        w_bt13 = (TextView) findViewById(R.id.button13);
        w_bt14 = (TextView) findViewById(R.id.button14);
        w_bt15 = (TextView) findViewById(R.id.button15);
        w_bt16 = (TextView) findViewById(R.id.button16);
        adds = (LinearLayout) findViewById(R.id.ads_lay);

        w_vl1 = (TextView) findViewById(R.id.ans1);
        w_vl2 = (TextView) findViewById(R.id.ans2);
        w_vl3 = (TextView) findViewById(R.id.ans3);
        w_vl4 = (TextView) findViewById(R.id.ans4);
        w_vl5 = (TextView) findViewById(R.id.ans5);
        w_vl6 = (TextView) findViewById(R.id.ans6);
        w_vl7 = (TextView) findViewById(R.id.ans7);
        w_vl8 = (TextView) findViewById(R.id.ans8);
        w_vl9 = (TextView) findViewById(R.id.ans9);
        w_vl10 = (TextView) findViewById(R.id.ans10);
        w_vl11 = (TextView) findViewById(R.id.ans11);
        w_vl12 = (TextView) findViewById(R.id.ans12);
        w_vl13 = (TextView) findViewById(R.id.ans13);
        w_vl14 = (TextView) findViewById(R.id.ans14);
        p_clear = (TextView) findViewById(R.id.clear);
        opponent_count = (TextView) findViewById(R.id.opponent_count);
        qus_count = (TextView) findViewById(R.id.qus_count);
        your_count = (TextView) findViewById(R.id.your_count);

        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);

        opp_img = (ImageView) findViewById(R.id.opp_img);
        my_img = (ImageView) findViewById(R.id.my_img);
        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);

        Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);

        your_count.setText("" + score_s);

        word_game_next();

        sound();
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        ans_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ans_edit.getWindowToken(), 0);
            }
        });
        ans_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ans_edit.getWindowToken(), 0);
                return true;
            }
        });

        oppe_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                oppe_msg.setVisibility(View.INVISIBLE);
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        w_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt1.startAnimation(shake);
                String ts = w_bt1.getText().toString();
                ans_edit.append(ts);

            }
        });
        w_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt2.startAnimation(shake);
                String ts = w_bt2.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt3.startAnimation(shake);
                String ts = w_bt3.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt5.startAnimation(shake);
                String ts = w_bt5.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt6.startAnimation(shake);
                String ts = w_bt6.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt7.startAnimation(shake);
                String ts = w_bt7.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt9.startAnimation(shake);
                String ts = w_bt9.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt10.startAnimation(shake);
                String ts = w_bt10.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt11.startAnimation(shake);
                String ts = w_bt11.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt4.startAnimation(shake);
                String ts = w_bt4.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt8.startAnimation(shake);
                String ts = w_bt8.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt12.startAnimation(shake);
                String ts = w_bt12.getText().toString();
                ans_edit.append(ts);

            }
        });
        w_bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt13.startAnimation(shake);
                String ts = w_bt13.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt14.startAnimation(shake);
                String ts = w_bt14.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt15.startAnimation(shake);
                String ts = w_bt15.getText().toString();
                ans_edit.append(ts);
            }
        });
        w_bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                w_bt16.startAnimation(shake);
                String ts = w_bt16.getText().toString();
                ans_edit.append(ts);
            }
        });

        p_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                // spz1.play(soundId1, sv, sv, 0, 0, sv);
                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });

        p_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ans_edit.setText("");
                return false;
            }
        });


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = ans_edit.getText().toString();
                if (ans.length() != 0) {
                    Cursor cs = myDbHelper.getQry("select * from answertable where answer LIKE'" + ans + "'and afinish='1'and levelid=" + letterid + " and gameid=" + gameid + " and rd='" + rdvalu + "'");
                    cs.moveToFirst();
                    if (cs.getCount() != 0) {

                        cr_ans.play(soundId5, sv, sv, 0, 0, sv);
                        ans_edit.setText("");

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

                        Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where answer ='" + ans + "' and afinish='0' and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                        cd.moveToFirst();

                        if (cd.getCount() != 0) {
                            if (x <= tans) {


                                //win.play(soundId3, sv, sv, 0, 0, sv);

                                myDbHelper.executeSql("UPDATE answertable SET afinish=1 WHERE answer='" + ans + "'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "'");
                                myDbHelper.executeSql("UPDATE answertable SET useranswer=0 WHERE answer='" + ans + "'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "'");


                                // ans_selection(x,ans);

                                ans_edit.setText("");
                                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);

                                if (w_vl1.length() == 0) {
                                    w_vl1.setText(ans);
                                    w_vl1.startAnimation(levels1);

                                } else if (w_vl2.length() == 0) {
                                    w_vl2.setText(ans);
                                    w_vl2.startAnimation(levels1);

                                } else if (w_vl3.length() == 0) {
                                    w_vl3.setText(ans);
                                    w_vl3.startAnimation(levels1);

                                } else if (w_vl4.length() == 0) {
                                    w_vl4.setText(ans);
                                    w_vl4.startAnimation(levels1);

                                } else if (w_vl5.length() == 0) {
                                    w_vl5.setText(ans);
                                    w_vl5.startAnimation(levels1);

                                } else if (w_vl6.length() == 0) {
                                    w_vl6.setText(ans);
                                    w_vl6.startAnimation(levels1);

                                } else if (w_vl7.length() == 0) {
                                    w_vl7.setText(ans);
                                    w_vl7.startAnimation(levels1);

                                } else if (w_vl8.length() == 0) {
                                    w_vl8.setText(ans);
                                    w_vl8.startAnimation(levels1);

                                } else if (w_vl9.length() == 0) {
                                    w_vl9.setText(ans);
                                    w_vl9.startAnimation(levels1);

                                } else if (w_vl10.length() == 0) {
                                    w_vl10.setText(ans);
                                    w_vl10.startAnimation(levels1);

                                } else if (w_vl11.length() == 0) {
                                    w_vl11.setText(ans);
                                    w_vl11.startAnimation(levels1);

                                } else if (w_vl12.length() == 0) {
                                    w_vl12.setText(ans);
                                    w_vl12.startAnimation(levels1);

                                } else if (w_vl13.length() == 0) {
                                    w_vl13.setText(ans);
                                    w_vl13.startAnimation(levels1);

                                } else if (w_vl14.length() == 0) {
                                    w_vl14.setText(ans);
                                    w_vl14.startAnimation(levels1);

                                }


                                x++;


                                //////////////////////////////////////////////Your Score and Opponent Score Sending to Multiplayer///////////////////////////////////////////////////////////////////
                                score_s = score_s + 1;
                                mMultiplayer = true;
                                if (Utils.isNetworkAvailable(getApplicationContext())) {
                                    // gameactive_you = 1;
                                    broadcastScore(true);
                                } else {
                                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                                    exitgame();
                                }

                                your_count.setText("" + score_s);
                                //////////////////////////////////////////////Your Score and Opponent Score Sending to Multiplayer///////////////////////////////////////////////////////////////////

                            }
                            if (x >= tans) {
                                verify.setVisibility(View.INVISIBLE);
                                myDbHelper.executeSql("UPDATE maintable SET rtm='1' WHERE levelid='" + letterid + "'and gameid=" + gameid + "");

                             /*   mMultiplayer = true;
                                broadcastScore(false);
                                your_count.setText(""+score_s);


                                if (score_s>opponent_score){
                                    play_after = 1;
                                    // Toast.makeText(Solli_adi_multiplayer.this, "You Won the Match You Got 100 Points", Toast.LENGTH_SHORT).show();
                                   *//* int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*//*
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(200,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                                }*/


                                st_game = st_game + 1;
                                your_m_count.setText("" + st_game);
                                if (st_game == tt_game) {
                                    if (st_game > ut_game) {


                                        if (play_after == 0) {
                                            Handler handler8 = new Handler();
                                            handler8.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                                    cfx.moveToFirst();
                                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                                    int spx = skx + 200;
                                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                                    finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");

                               /* int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                                                }
                                            }, 2000);
                                            play_after = 1;
                                            mMultiplayer = true;
                                        }
                                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                                            gameactive_you = 1;
                                            broadcastScore(false);
                                        } else {
                                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                                            exitgame();
                                        }
                                    }

                                } else {
                                    score_s = 0;

                                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                                        gameactive_you = 1;
                                        broadcastScore(true);
                                    } else {
                                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                                        exitgame();
                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            letterid = letterid + 1;
                                            word_game_next();
                                        }
                                    }, 2000);

                                }

                            }
                        } else {

                            spz2.play(soundId2, sv, sv, 0, 0, sv);
                            ans_edit.setText("");
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

                    // worng.play(soundId2, sv, sv, 0, 0, sv);
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
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        adsicon2.setVisibility(View.INVISIBLE);
        //install_ads_doalug();
  /*      final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);*/
        ads_logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsicon2.setVisibility(View.INVISIBLE);

            }
        });


    }

    public void word_game_next () {
        ans_edit.setText("");
        w_bt1.setText("");
        w_bt2.setText("");
        w_bt3.setText("");
        w_bt5.setText("");
        w_bt6.setText("");
        w_bt7.setText("");
        w_bt9.setText("");
        w_bt10.setText("");
        w_bt11.setText("");
        w_bt4.setText("");
        w_bt8.setText("");
        w_bt12.setText("");
        w_bt13.setText("");
        w_bt14.setText("");
        w_bt15.setText("");
        w_bt16.setText("");
        w_vl1.setText("");
        w_vl2.setText("");
        w_vl3.setText("");
        w_vl4.setText("");
        w_vl5.setText("");
        w_vl6.setText("");
        w_vl7.setText("");
        w_vl8.setText("");
        w_vl9.setText("");
        w_vl10.setText("");
        w_vl11.setText("");
        w_vl12.setText("");
        w_vl13.setText("");
        w_vl14.setText("");


        LinearLayout linearLayout;
        linearLayout = (LinearLayout) findViewById(R.id.anslist2);
        linearLayout.setVisibility(View.GONE);
        LinearLayout linearLayout1;
        linearLayout1 = (LinearLayout) findViewById(R.id.list2_pic);
        linearLayout1.setVisibility(View.GONE);
        w_vl1.setVisibility(View.GONE);
        w_vl2.setVisibility(View.GONE);
        w_vl3.setVisibility(View.GONE);
        w_vl4.setVisibility(View.GONE);
        w_vl5.setVisibility(View.GONE);
        w_vl6.setVisibility(View.GONE);
        w_vl7.setVisibility(View.GONE);
        w_vl8.setVisibility(View.GONE);
        w_vl9.setVisibility(View.GONE);
        w_vl10.setVisibility(View.GONE);
        w_vl11.setVisibility(View.GONE);
        w_vl12.setVisibility(View.GONE);
        w_vl13.setVisibility(View.GONE);
        w_vl14.setVisibility(View.GONE);

        your_count.setText("" + score_s);
        // opponent_count.setText("0");

        x = 1;
        if (letterid == 176) {
            letterid = 177;
        }
        Cursor c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'and levelid='" + letterid + "' and rtm='0'");
        c.moveToFirst();
        if (c.getCount() != 0) {
            u_id = c.getInt(c.getColumnIndexOrThrow("id"));
            letters = c.getString(c.getColumnIndexOrThrow("letters"));
            letterid = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("levelid")));
            answertype = c.getString(c.getColumnIndexOrThrow("answer"));
        }

        StringTokenizer tokenizer = new StringTokenizer(letters, ",");
        final String letter1 = tokenizer.nextToken();
        String letter2 = tokenizer.nextToken().trim();
        String letter3 = tokenizer.nextToken().trim();
        String letter4 = tokenizer.nextToken().trim();
        String letter5 = tokenizer.nextToken().trim();
        String letter6 = tokenizer.nextToken().trim();
        String letter7 = tokenizer.nextToken().trim();
        String letter8 = tokenizer.nextToken().trim();
        String letter9 = tokenizer.nextToken().trim();


        w_bt1.setText(letter1);
        w_bt2.setText(letter2);
        w_bt3.setText(letter3);
        w_bt5.setText(letter4);
        w_bt6.setText(letter5);
        w_bt7.setText(letter6);
        w_bt9.setText(letter7);
        w_bt10.setText(letter8);
        w_bt11.setText(letter9);

///////////////////////////////////////////////////////   LETTER TYPE ///////////////////////////////////////////////////////////////
        String tfoption = letters;
        String[] first = tfoption.split(",");
        int letter_type = first.length;

        String tfoption1 = answertype;
        String[] first1 = tfoption1.split(",");
        answer_type = first1.length;
        Cursor answ = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "'");
        if (answ.getCount() == 0) {
            for (int i = 0; i < answer_type; i++) {
                ContentValues cv = new ContentValues();
                cv.put("gameid", gameid);
                cv.put("levelid", letterid);
                cv.put("answer", first1[i].trim());
                cv.put("rd", 3);
                cv.put("afinish", "0");
                myDbHelper.insert_data("answertable", null, cv);
            }
        }

        verify.setVisibility(View.VISIBLE);
//////////////////////////////////////////////////////////////////ANSWER TYPE GREATER 7/////////////////////////////////////////
        if (answer_type >= 7 && answer_type <= 10) {
            tans = 8;
            simple_w();

        } else if (answer_type >= 11 && answer_type <= 15) {
            tans = 11;
            medium_w();
        } else if (answer_type >= 16) {
            tans = 15;
            hard_w();
        }
    }

    private void medium_w () {
        LinearLayout linearLayout;
        linearLayout = (LinearLayout) findViewById(R.id.anslist2);
        linearLayout.setVisibility(View.VISIBLE);
        w_vl1.setVisibility(View.VISIBLE);
        w_vl2.setVisibility(View.VISIBLE);
        w_vl3.setVisibility(View.VISIBLE);
        w_vl4.setVisibility(View.VISIBLE);
        w_vl5.setVisibility(View.VISIBLE);
        w_vl6.setVisibility(View.VISIBLE);
        w_vl7.setVisibility(View.VISIBLE);
        w_vl8.setVisibility(View.VISIBLE);
        w_vl9.setVisibility(View.VISIBLE);
        w_vl10.setVisibility(View.VISIBLE);
    }

    private void simple_w () {
//second game layout visibility
        LinearLayout linearLayout;
        linearLayout = (LinearLayout) findViewById(R.id.anslist2);
        linearLayout.setVisibility(View.GONE);
        LinearLayout linearLayout1;
        linearLayout1 = (LinearLayout) findViewById(R.id.list2_pic);
        linearLayout1.setVisibility(View.GONE);
        //

        w_vl1.setVisibility(View.VISIBLE);
        w_vl2.setVisibility(View.VISIBLE);
        w_vl3.setVisibility(View.VISIBLE);
        w_vl4.setVisibility(View.VISIBLE);
        w_vl5.setVisibility(View.VISIBLE);
        w_vl6.setVisibility(View.VISIBLE);
        w_vl7.setVisibility(View.VISIBLE);
    }

    public void hard_w () {

        LinearLayout linearLayout;
        linearLayout = (LinearLayout) findViewById(R.id.anslist2);
        linearLayout.setVisibility(View.VISIBLE);

        w_vl1.setVisibility(View.VISIBLE);
        w_vl2.setVisibility(View.VISIBLE);
        w_vl3.setVisibility(View.VISIBLE);
        w_vl4.setVisibility(View.VISIBLE);
        w_vl5.setVisibility(View.VISIBLE);
        w_vl6.setVisibility(View.VISIBLE);
        w_vl7.setVisibility(View.VISIBLE);
        w_vl8.setVisibility(View.VISIBLE);
        w_vl9.setVisibility(View.VISIBLE);
        w_vl10.setVisibility(View.VISIBLE);
        w_vl11.setVisibility(View.VISIBLE);
        w_vl12.setVisibility(View.VISIBLE);
        w_vl13.setVisibility(View.VISIBLE);
        w_vl14.setVisibility(View.VISIBLE);
    }
    //////////////////////////////////////////////////Game Starts Sol Game//////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////Game Starts solukulsol//////////////////////////////////////////////////////////////
    public void game_start_solukulsol () {
        gameid = 3;
        setContentView(R.layout.activity_solukul_sol_multiplayer);
        switchToScreen(R.layout.activity_solukul_sol_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        settings = (TextView) findViewById(R.id.settings);
        sb1 = (TextView) findViewById(R.id.s_b1);
        sb2 = (TextView) findViewById(R.id.s_b2);
        sb3 = (TextView) findViewById(R.id.s_b3);
        sb4 = (TextView) findViewById(R.id.s_b4);
        sb5 = (TextView) findViewById(R.id.s_b5);
        sb6 = (TextView) findViewById(R.id.s_b6);
        sb7 = (TextView) findViewById(R.id.s_b7);
        sb8 = (TextView) findViewById(R.id.s_b8);
        ans_edit = (EditText) findViewById(R.id.s_ans_editer);
        vl1 = (TextView) findViewById(R.id.s_ans1);
        vl2 = (TextView) findViewById(R.id.s_ans2);
        vl3 = (TextView) findViewById(R.id.s_ans3);
        vl4 = (TextView) findViewById(R.id.s_ans4);
        vl5 = (TextView) findViewById(R.id.s_ans5);
        vl6 = (TextView) findViewById(R.id.s_ans6);
        vl7 = (TextView) findViewById(R.id.s_ans7);
        s_clear = (Button) findViewById(R.id.s_clear);
        s_verify = (TextView) findViewById(R.id.s_verify);


        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);


        opponent_count = (TextView) findViewById(R.id.opponent_count);
        qus_count = (TextView) findViewById(R.id.qus_count);
        your_count = (TextView) findViewById(R.id.your_count);
        opp_img = (ImageView) findViewById(R.id.opp_img);
        my_img = (ImageView) findViewById(R.id.my_img);
        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);

        Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);


        your_count.setText("" + score_s);
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        adds = (LinearLayout) findViewById(R.id.ads_lay);
        //  New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);

                /* if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }

        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });

        ans_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ans_edit.getWindowToken(), 0);
            }
        });
        ans_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(ans_edit.getWindowToken(), 0);
                return true;
            }
        });
        ///////////////////////////////NEXT GAME STARTS/////////////////////////////////////////////////////
        next();
        ///////////////////////////////NEXT GAME STARTS/////////////////////////////////////////////////////
        sound();

        ////////////////////////////////////////////////CLICK LISTENER///////////////////////////////////////////////////////
        oppe_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                oppe_msg.setVisibility(View.INVISIBLE);
            }
        });

        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb1.startAnimation(shake);
                String ts = sb1.getText().toString();
                ans_edit.append(ts);
            }
        });
        sb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb2.startAnimation(shake);
                String ts = sb2.getText().toString();
                ans_edit.append(ts);
            }
        });
        sb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb3.startAnimation(shake);
                String ts = sb3.getText().toString();
                ans_edit.append(ts);
            }
        });
        sb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb4.startAnimation(shake);
                String ts = sb4.getText().toString();
                ans_edit.append(ts);
            }
        });
        sb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb5.startAnimation(shake);
                String ts = sb5.getText().toString();
                ans_edit.append(ts);
            }
        });
        sb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb6.startAnimation(shake);
                String ts = sb6.getText().toString();
                ans_edit.append(ts);
            }
        });
        sb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb7.startAnimation(shake);
                String ts = sb7.getText().toString();
                ans_edit.append(ts);
            }
        });
        sb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                sb8.startAnimation(shake);
                String ts = sb8.getText().toString();
                ans_edit.append(ts);
            }
        });

        s_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pressKey(KeyEvent.KEYCODE_DEL);
            }
        });
        s_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ans_edit.setText("");
                return false;
            }
        });

        ans_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(s_verify.getWindowToken(), 0);
            }
        });
        ans_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(s_verify.getWindowToken(), 0);

                return true;
            }
        });

        s_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = ans_edit.getText().toString();
                if (ans.length() != 0) {
                    Cursor cs = myDbHelper.getQry("select * from answertable where answer LIKE'" + ans + "'and afinish='1'and levelid=" + letterid + " and gameid=" + gameid + " and rd='" + rdvalu + "'");
                    cs.moveToFirst();
                    if (cs.getCount() != 0) {
                        cr_ans.play(soundId5, sv, sv, 0, 0, sv);
                        ans_edit.setText("");
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
                        textView.setTextSize(19);
                        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        view.setLayoutParams(params);
                        snackbar.show();
                    } else {

                        Cursor cd = myDbHelper.getQry("SELECT * FROM answertable where answer ='" + ans + "' and afinish='0' and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "' ");
                        cd.moveToFirst();
                        if (cd.getCount() != 0) {
                            if (x <= answer_type) {
                                y = y + 1;
                                //win.play(soundId3, sv, sv, 0, 0, sv);

                                myDbHelper.executeSql("UPDATE answertable SET afinish=1 WHERE answer='" + ans + "'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "'");
                                myDbHelper.executeSql("UPDATE answertable SET useranswer=0 WHERE answer='" + ans + "'and levelid='" + letterid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "'");
                                //
                                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
                                ans_edit.setText("");
                                if (vl1.length() == 0) {
                                    vl1.setText(ans);
                                    vl1.startAnimation(levels1);
                                } else if (vl2.length() == 0) {
                                    vl2.setText(ans);
                                    vl2.startAnimation(levels1);
                                } else if (vl3.length() == 0) {
                                    vl3.setText(ans);
                                    vl3.startAnimation(levels1);
                                } else if (vl4.length() == 0) {
                                    vl4.setText(ans);
                                    vl4.startAnimation(levels1);
                                } else if (vl5.length() == 0) {
                                    vl5.setText(ans);
                                    vl5.startAnimation(levels1);
                                } else if (vl6.length() == 0) {
                                    vl6.setText(ans);
                                    vl6.startAnimation(levels1);
                                } else if (vl7.length() == 0) {
                                    vl7.setText(ans);
                                    vl7.startAnimation(levels1);
                                }
                                x++;
                                //////////////////////////////////////////////Your Score and Opponent Score Sending to Multiplayer///////////////////////////////////////////////////////////////////
                                score_s = score_s + 1;
                                mMultiplayer = true;


                                if (Utils.isNetworkAvailable(getApplicationContext())) {
                                    // gameactive_you = 1;
                                    broadcastScore(true);
                                } else {
                                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                                    exitgame();
                                }
                                your_count.setText("" + score_s);
                                //////////////////////////////////////////////Your Score and Opponent Score Sending to Multiplayer///////////////////////////////////////////////////////////////////
                            }
                            if (x > answer_type) {
                                s_verify.setVisibility(View.INVISIBLE);
                                myDbHelper.executeSql("UPDATE maintable SET rtm='1' WHERE levelid='" + letterid + "'and gameid=" + gameid + "");


                                st_game = st_game + 1;
                                your_m_count.setText("" + st_game);
                                if (st_game == tt_game) {
                                    if (st_game > ut_game) {


                                        if (play_after == 0) {
                                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                            cfx.moveToFirst();
                                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                            int spx = skx + 200;
                                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                            finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                                            play_after = 1;
                                            mMultiplayer = true;
                                        }
                                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                                            gameactive_you = 1;
                                            broadcastScore(false);
                                        } else {
                                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                                            exitgame();
                                        }
                                        your_count.setText("" + score_s);

                                    }

                                } else {
                                    score_s = 0;

                                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                                        //gameactive_you = 1;
                                        broadcastScore(true);
                                    } else {
                                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                                        exitgame();
                                    }
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            letterid = letterid + 1;
                                            next();
                                        }
                                    }, 2000);

                                }





                            /*    mMultiplayer = true;
                                broadcastScore(false);
                                your_count.setText(""+score_s);

                                if (score_s>opponent_score){
                                    play_after = 1;
                                   // Toast.makeText(Solli_adi_multiplayer.this, "You Won the Match You Got 100 Points", Toast.LENGTH_SHORT).show();
                                  *//*  int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
*//*
                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");

                                }
*/


                              /* // completegame();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        letterid=letterid+1;
                                        next();
                                    }
                                }, 2000);*/
                            }
                        } else {
                            //r1.start();
                            spz2.play(soundId2, sv, sv, 0, 0, sv);
                            ans_edit.setText("");
                            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "சரியான விடையாக இருக்கலாம் .\n ஆனால் எங்கள் தொகுப்பில் இல்லை ", Snackbar.LENGTH_SHORT);
                            final View view = snackbar.getView();
                            TextView textView = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                            view.setBackgroundResource(R.drawable.answershow);
                            textView.setTextColor(Color.parseColor("#FFFFFF"));
                            // textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                            textView.setTextSize(17);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            textView.setTextSize(17);
                            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
                            params.gravity = Gravity.TOP;
                            view.setLayoutParams(params);
                            snackbar.show();
                        }
                    }
                } else {
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.myCoordinatorLayout);
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "நிரப்பவும்", Snackbar.LENGTH_SHORT);
                    final View view = snackbar.getView();
                    TextView textView = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
                    view.setBackgroundResource(R.drawable.answershow);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    // textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                    textView.setTextSize(19);
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
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });


        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        adsicon2.setVisibility(View.INVISIBLE);
        //  install_ads_doalug();
    /*    final Animation pendulam;
        pendulam = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sake);
        adsicon2.startAnimation(pendulam);*/
        ads_logo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsicon2.setVisibility(View.INVISIBLE);

            }
        });

    }

    public void next () {


        your_count.setText("" + score_s);
        //opponent_count.setText("0");
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                /* if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }


        x = 1;
        resetfields();
        Cursor c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'and levelid='" + letterid + "' and rtm='0'");
        c.moveToFirst();
        if (c.getCount() != 0) {
            u_id = c.getInt(c.getColumnIndexOrThrow("id"));
            letters = c.getString(c.getColumnIndexOrThrow("letters"));
            letterid = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("levelid")));
            answertype = c.getString(c.getColumnIndexOrThrow("answer"));
        }

///////////////////////////////////////////////////////   LETTER TYPE ///////////////////////////////////////////////////////////////
        String tfoption = letters;
        String[] first = tfoption.split(",");
        int letter_type = first.length;

        String tfoption1 = answertype;
        String[] first1 = tfoption1.split(",");
        answer_type = first1.length;
        Cursor answ = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + letterid + "' and rd='" + rdvalu + "'");
        if (answ.getCount() == 0) {
            for (int i = 0; i < answer_type; i++) {
                ContentValues cv = new ContentValues();
                cv.put("gameid", gameid);
                cv.put("levelid", letterid);
                cv.put("answer", first1[i].trim());
                cv.put("rd", 3);
                cv.put("afinish", "0");
                myDbHelper.insert_data("answertable", null, cv);
            }
        }

        s_verify.setVisibility(View.VISIBLE);
//////////////////////////////////////////////////////////////////ANSWER TYPE GREATER 7/////////////////////////////////////////
        if (answer_type > 7) {
            answer_type = 7;
        }
//////////////////////////////////////////////////////////////////ANSWER TYPE GREATER 7/////////////////////////////////////////

        if (letter_type == 1) {
            sb1.setVisibility(View.VISIBLE);
            sb1.setText(letters);

        } else if (letter_type == 2) {
            StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            sb1.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.VISIBLE);
            sb1.setText(letters1);
            sb2.setText(letters2);
        } else if (letter_type == 3) {

            StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            sb1.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.VISIBLE);
            sb3.setVisibility(View.VISIBLE);
            sb1.setText(letters1);
            sb2.setText(letters2);
            sb3.setText(letters3);
        } else if (letter_type == 4) {

            StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            sb1.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.VISIBLE);
            sb3.setVisibility(View.VISIBLE);
            sb4.setVisibility(View.VISIBLE);
            sb1.setText(letters1);
            sb2.setText(letters2);
            sb3.setText(letters3);
            sb4.setText(letters4);
        } else if (letter_type == 5) {

            StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();

            sb1.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.VISIBLE);
            sb3.setVisibility(View.VISIBLE);
            sb4.setVisibility(View.VISIBLE);
            sb5.setVisibility(View.VISIBLE);

            sb1.setText(letters1);
            sb2.setText(letters2);
            sb3.setText(letters3);
            sb4.setText(letters4);
            sb5.setText(letters5);

        } else if (letter_type == 6) {

            StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();

            sb1.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.VISIBLE);
            sb3.setVisibility(View.VISIBLE);
            sb4.setVisibility(View.VISIBLE);
            sb5.setVisibility(View.VISIBLE);
            sb6.setVisibility(View.VISIBLE);
            sb1.setText(letters1);
            sb2.setText(letters2);
            sb3.setText(letters3);
            sb4.setText(letters4);
            sb5.setText(letters5);
            sb6.setText(letters6);
        } else if (letter_type == 7) {
            StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();

            sb1.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.VISIBLE);
            sb3.setVisibility(View.VISIBLE);
            sb4.setVisibility(View.VISIBLE);
            sb5.setVisibility(View.VISIBLE);
            sb6.setVisibility(View.VISIBLE);
            sb7.setVisibility(View.VISIBLE);
            sb1.setText(letters1);
            sb2.setText(letters2);
            sb3.setText(letters3);
            sb4.setText(letters4);
            sb5.setText(letters5);
            sb6.setText(letters6);
            sb7.setText(letters7);
        } else if (letter_type == 8) {

            StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();
            final String letters8 = tokenizerw.nextToken().trim();

            sb1.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.VISIBLE);
            sb3.setVisibility(View.VISIBLE);
            sb4.setVisibility(View.VISIBLE);
            sb5.setVisibility(View.VISIBLE);
            sb6.setVisibility(View.VISIBLE);
            sb7.setVisibility(View.VISIBLE);
            sb8.setVisibility(View.VISIBLE);
            sb1.setText(letters1);
            sb2.setText(letters2);
            sb3.setText(letters3);
            sb4.setText(letters4);
            sb5.setText(letters5);
            sb6.setText(letters6);
            sb7.setText(letters7);
            sb8.setText(letters8);
        }


        if (answer_type == 1) {
            vl1.setVisibility(View.VISIBLE);
        } else if (answer_type == 2) {
            vl1.setVisibility(View.VISIBLE);
            vl2.setVisibility(View.VISIBLE);
        } else if (answer_type == 3) {
            vl1.setVisibility(View.VISIBLE);
            vl2.setVisibility(View.VISIBLE);
            vl3.setVisibility(View.VISIBLE);
        } else if (answer_type == 4) {
            vl1.setVisibility(View.VISIBLE);
            vl2.setVisibility(View.VISIBLE);
            vl3.setVisibility(View.VISIBLE);
            vl4.setVisibility(View.VISIBLE);
        } else if (answer_type == 5) {
            vl1.setVisibility(View.VISIBLE);
            vl2.setVisibility(View.VISIBLE);
            vl3.setVisibility(View.VISIBLE);
            vl4.setVisibility(View.VISIBLE);
            vl5.setVisibility(View.VISIBLE);
        } else if (answer_type == 6) {
            vl1.setVisibility(View.VISIBLE);
            vl2.setVisibility(View.VISIBLE);
            vl3.setVisibility(View.VISIBLE);
            vl4.setVisibility(View.VISIBLE);
            vl5.setVisibility(View.VISIBLE);
            vl6.setVisibility(View.VISIBLE);
        } else if (answer_type == 7) {
            vl1.setVisibility(View.VISIBLE);
            vl2.setVisibility(View.VISIBLE);
            vl3.setVisibility(View.VISIBLE);
            vl4.setVisibility(View.VISIBLE);
            vl5.setVisibility(View.VISIBLE);
            vl6.setVisibility(View.VISIBLE);
            vl7.setVisibility(View.VISIBLE);
        }


    }

    public void resetfields () {
        sb1.setVisibility(View.GONE);
        sb2.setVisibility(View.GONE);
        sb3.setVisibility(View.GONE);
        sb4.setVisibility(View.GONE);
        sb5.setVisibility(View.GONE);
        sb6.setVisibility(View.GONE);
        sb7.setVisibility(View.GONE);
        sb8.setVisibility(View.GONE);


        vl1.setVisibility(View.GONE);
        vl2.setVisibility(View.GONE);
        vl3.setVisibility(View.GONE);
        vl4.setVisibility(View.GONE);
        vl5.setVisibility(View.GONE);
        vl6.setVisibility(View.GONE);
        vl7.setVisibility(View.GONE);


        sb1.setText("");
        sb2.setText("");
        sb3.setText("");
        sb4.setText("");
        sb5.setText("");
        sb6.setText("");
        sb7.setText("");
        sb8.setText("");

        vl1.setText("");
        vl2.setText("");
        vl3.setText("");
        vl4.setText("");
        vl5.setText("");
        vl6.setText("");
        vl7.setText("");
    }

    private void pressKey ( int keycode){
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        ans_edit.onKeyDown(keycode, event);
    }

    private void pressKey_c ( int keycode){
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        c_edit.onKeyDown(keycode, event);
    }

    private void pressKey_r ( int keycode){
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        m_edit.onKeyDown(keycode, event);
    }

    public void updatePeerScoresDisplay ( int i){
        if (mCurScreen == R.layout.activity_solukul_sol_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);

        } else if (mCurScreen == R.layout.activity_word__game_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);

        } else if (mCurScreen == R.layout.activity_clue__game_multiplayer) {
            opponent_m_count.setText("" + ut_game);

        } else if (mCurScreen == R.layout.activity_pic__game_multiplayer) {
            opponent_m_count.setText("" + ut_game);
        } else if (mCurScreen == R.layout.odd_man_out_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);

        } else if (mCurScreen == R.layout.opposite_word_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);

        } else if (mCurScreen == R.layout.makeword__rightorder_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);

        } else if (mCurScreen == R.layout.ote_to_tamil_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);

        } else if (mCurScreen == R.layout.riddle_game_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);

        } else if (mCurScreen == R.layout.error_correction_multiplayer) {
            opponent_count = (TextView) findViewById(R.id.opponent_count);
            opponent_count.setText("" + i);
            opponent_m_count.setText("" + ut_game);
            Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);
            opponent_count.startAnimation(levels1);
        }

    }
    //////////////////////////////////////////////////Game Starts solukulsol//////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////Override Methods in multiplayer////////////////////////////////////////////////////////
    @Override
    public void onRoomCreated ( int statusCode, Room room){


        //      System.out.println("=====================onRoomCreated=====Room"+room);
//        System.out.println("+++++++++++++++++++++++++++++++++onRoomCreated"+room.getParticipantIds());


        Log.d(TAG, "onRoomCreated(" + statusCode + ", " + room + ")");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Log.e(TAG, "*** Error: onRoomCreated, status " + statusCode);
            showGameError();
            return;
        }

        // save room ID so we can leave cleanly before the game starts.
        mRoomId = room.getRoomId();
        // show the waiting room UI
        showWaitingRoom(room);
        if (Utils.mProgress.isShowing()) {
            Utils.mProgress.dismiss();
        }

    }

    @Override
    public void onJoinedRoom ( int statusCode, Room room){


        System.out.println("=====================onJoinedRoom==statusCode" + statusCode);
        Log.d(TAG, "onJoinedRoom(" + statusCode + ", " + room + ")");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Log.e(TAG, "*** Error: onRoomConnected, status " + statusCode);
            showGameError();
            return;
        }

        // show the waiting room UI
        showWaitingRoom(room);
        if (Utils.mProgress.isShowing()) {
            Utils.mProgress.dismiss();
        }

    }

    @Override
    public void onLeftRoom ( int i, String s){
        System.out.println("=====================onleft room");
        exitgame();
    }

    @Override
    public void onRoomConnected ( int statusCode, Room room){
        Log.d(TAG, "onRoomConnected(" + statusCode + ", " + room + ")");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Log.e(TAG, "*** Error: onRoomConnected, status " + statusCode);
            showGameError();
            return;
        }
        updateRoom(room);
    }

    @Override
    public void onRealTimeMessageReceived (RealTimeMessage rtm){
        System.out.println("===============================onRealTimeMessageReceived");
        byte[] buf = rtm.getMessageData();
        String sender = rtm.getSenderParticipantId();
        System.out.println("#####################################Message received:" + (char) buf[0] + "/" + (int) buf[1] + "/" + (int) buf[2] + "/" + (int) buf[3]);
        Log.d(TAG, "Message received: " + (char) buf[0] + "/" + (int) buf[1] + "/" + (int) buf[2] + "/" + (int) buf[3]);
        System.out.println("===========================buf[0] " + (char) buf[0]);
        if ((char) buf[0] == 'S') {
            oppe_msg.setText("");
            receive_msg = "";
            String string = "";
            receive_bytes = new byte[1];

            String chat_oppo = "";
            for (byte b : buf) {
                receive_bytes[0] = b;

               /* System.out.println("send_msg b : "+b);

               String s = new String(receive_bytes);
                System.out.println("send_msg s : "+s);*/
                try {
                    string = new String(receive_bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                receive_msg += string;
                System.out.println("send_msg bytes receive_msg : " + receive_msg);

                 /*String myString = receive_msg;
                String removeFromThisPart = "SWWWW1";
                myString = myString .substring(0, myString .lastIndexOf( removeFromThisPart ));
                System.out.println("=======================myString"+myString);*/


                String newValue = receive_msg.replace("SWWWW", "");
                oppe_msg.setText("" + newValue);

                chat_oppo = "!" + oppe_msg.getText().toString();

                System.out.println("+++++++++++++++++++++chat_history R " + chat_history);
                System.out.println("send_msg bytes receive_msg.substring(0,5) : " + "" + newValue);
               // oppe_msg.setVisibility(View.VISIBLE);
                cr_ans.play(soundId5, sv, sv, 0, 0, sv);
             /*   Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        oppe_msg.setVisibility(View.INVISIBLE);
                    }
                }, 3000);*/
            }
            chat_history.add(chat_oppo);

            if (message_dia.isShowing()) {
                chatAdapter.notifyDataSetChanged();
            }
        } else {
            System.out.println("#####################################Message received:randoms" + randoms + "##############random outside" + (int) buf[2]);
            System.out.println("=========randoms" + randoms + "random outside" + (int) buf[2]);
            System.out.println("----------------------------------gameactive_you_mr" + gameactive_you);
            gameactive_you = (int) buf[5];
            System.out.println("----------------------------------gameactive_you_mr2" + gameactive_you);

            System.out.println("=======================================buf.length" + buf.length);


            if (buf[0] == 'F' || buf[0] == 'U') {
                // score update.
                int existingScore = mParticipantScore.containsKey(sender) ?
                        mParticipantScore.get(sender) : 0;
                int thisScore = (int) buf[1];

                int random = (int) buf[2];
                random = random & 0xFF;

                int random_2s = (int) buf[3];
                random_2s = random_2s & 0xFF;

                opponent_score = (int) buf[1];
                int gg_game = (int) buf[4];
                ut_game = gg_game;

                System.out.println("#####################################opponentcount" + ut_game);

                System.out.println("=======================================opponentcount" + ut_game);

                opp_comp_count = thisScore;
////////////////////////////////////////////////////////////game count///////////////////////////////////////////////////////
                if (buf.length > 6) {
                    System.out.println("##############################################Length is 6 greater");

                    count_no = "";
                    receive_bytesd = new byte[1];
                    String chat_oppo = "";
                    String string = "";
                    for (byte b : buf) {
                        receive_bytesd[0] = b;
                        try {
                            string = new String(receive_bytesd, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        count_no += string;
                    }
                    System.out.println("=================================send_msg bytes count_no : " + count_no);
                    String str = count_no;
                    //str = str.replaceAll("[^\\d.]", "");
                    str = str.replaceAll("[^\\d-]", "");


                    String kkp = str.toString();
                    String[] stra = kkp.split("-");

                    // s_word_number.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
                    // String number = count_no.replaceAll("\\D+", "");

                    System.out.println("=================================str : " + str);
                    //System.out.println("=================================stra : " + stra.length);
                    System.out.println("=================================game1 : " + stra[0]);
                    System.out.println("=================================game2 : " + stra[1]);
                    System.out.println("=================================game3 : " + stra[2]);
                    System.out.println("=================================game4 : " + stra[3]);
                    System.out.println("=================================randoms_2_ng : " + stra[4]);
                    int ram1 = Integer.parseInt(stra[0]);
                    int ram2 = Integer.parseInt(stra[1]);
                    int ram3 = Integer.parseInt(stra[2]);
                    int ram4 = Integer.parseInt(stra[3]);
                    int ram5 = Integer.parseInt(stra[4]);
                    int ram6 = Integer.parseInt(stra[5]);
                    int ram7 = Integer.parseInt(stra[6]);
                    int ram8 = Integer.parseInt(stra[7]);
                    int ram9 = Integer.parseInt(stra[8]);
                    int ram10 = Integer.parseInt(stra[9]);
                    int ram11 = Integer.parseInt(stra[10]);
                    int ram12 = Integer.parseInt(stra[11]);
                    ram13 = Integer.parseInt(stra[12]);
                    System.out.println("=================================randoms_2_ng ram4: " + ram5);
                    //////////////////////////////////////////////////////////////NEW FUNCTION////////////////////////////////////
                    if (sp.getString(Solli_adi_multiplayer.this, "initialstarting").equals("")) {

                        System.out.println("##############################################randoms_2" + randoms_2);
                        System.out.println("##############################################random_2s" + random_2s);
                        System.out.println("##############################################letterid" + letterid);
                        System.out.println("##############################################g1_randoms" + g1_randoms);
                        System.out.println("##############################################ram1" + ram1);
                        System.out.println("##############################################g2_randoms" + g2_randoms);
                        System.out.println("##############################################ram2" + ram2);
                        System.out.println("##############################################g3_randoms" + g3_randoms);
                        System.out.println("##############################################ram3" + ram3);
                        System.out.println("##############################################g4_randoms" + g4_randoms);
                        System.out.println("##############################################ram4" + ram4);
                        System.out.println("##############################################g5_randoms" + g5_randoms);
                        System.out.println("##############################################ram5" + ram5);
                        System.out.println("##############################################g6_randoms" + g6_randoms);
                        System.out.println("##############################################ram6" + ram6);
                        System.out.println("##############################################g7_randoms" + g7_randoms);
                        System.out.println("##############################################ram7" + ram7);
                        System.out.println("##############################################g8_randoms" + g8_randoms);
                        System.out.println("##############################################ram8" + ram8);
                        System.out.println("##############################################g9_randoms" + g9_randoms);
                        System.out.println("##############################################ram9" + ram9);
                        System.out.println("##############################################g10_randoms" + g10_randoms);
                        System.out.println("##############################################ram10" + ram10);
                        System.out.println("##############################################g11_randoms" + g11_randoms);
                        System.out.println("##############################################ram11" + ram11);
                        System.out.println("##############################################g12_randoms" + g12_randoms);
                        System.out.println("##############################################ram12" + ram12);
                        System.out.println("##############################################ram13" + ram13);

                        if (buf.length > 6) {
                           /* if (randoms_2_ng < ram13) {
                                if (g5_randoms < ram5) {
                                    letterid = g5_randoms;
                                } else if (g5_randoms > ram5) {
                                    letterid = ram5;
                                } else if (g5_randoms == ram5) {
                                    letterid = 5;
                                }
                            } else if (randoms_2_ng > ram13) {
                                if (g5_randoms < ram5) {
                                    letterid = g5_randoms;
                                } else if (g5_randoms > ram5) {
                                    letterid = ram5;
                                } else if (g5_randoms == ram5) {
                                    letterid = 5;
                                }
                            } else if (randoms_2_ng == ram13) {
                                letterid = 5;
                            }*/
                            System.out.println("=====================randoms_2_ng" + randoms_2_ng + "===========================" + ram13);

                            if (randoms_2_ng < ram13) {

                                if (ram13 == 1) {
                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                    //game_start_picture_game();

                                } else if (ram13 == 2) {

                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }

                                    // game_start_clue_game();
                                } else if (ram13 == 3) {

                                    if (g3_randoms < ram3) {
                                        letterid = g3_randoms;
                                    } else if (g3_randoms > ram3) {
                                        letterid = ram3;
                                    } else if (g3_randoms == ram3) {
                                        letterid = 5;
                                    }
                                    // game_start_solukulsol();
                                } else if (ram13 == 4) {
                                    if (g4_randoms < ram4) {
                                        letterid = g4_randoms;
                                    } else if (g4_randoms > ram4) {
                                        letterid = ram4;
                                    } else if (g4_randoms == ram4) {
                                        letterid = 5;
                                    }
                                    // game_start_sol_game();
                                } else if (ram13 == 5) {

                                    if (g5_randoms < ram5) {
                                        letterid = g5_randoms;
                                    } else if (g5_randoms > ram5) {
                                        letterid = ram5;
                                    } else if (g5_randoms == ram5) {
                                        letterid = 5;
                                    }
                                    //game_start_picture_game();
                                } else if (ram13 == 6) {
                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }
                                   /* if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }*/
                                   /* if (g6_randoms < ram6) {
                                        letterid = g6_randoms;
                                    } else if (g6_randoms > ram6) {
                                        letterid = ram2;
                                    } else if (g6_randoms == ram6) {
                                        letterid = 5;
                                    }*/
                                    //game_start_clue_game();
                                } else if (ram13 == 7) {
                                    if (g7_randoms < ram7) {
                                        letterid = g7_randoms;
                                    } else if (g7_randoms > ram7) {
                                        letterid = ram7;
                                    } else if (g7_randoms == ram7) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (ram13 == 8) {
                                    if (g8_randoms < ram8) {
                                        letterid = g8_randoms;
                                    } else if (g8_randoms > ram8) {
                                        letterid = ram8;
                                    } else if (g8_randoms == ram8) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (ram13 == 9) {
                                    if (g9_randoms < ram9) {
                                        letterid = g9_randoms;
                                    } else if (g9_randoms > ram9) {
                                        letterid = ram9;
                                    } else if (g9_randoms == ram9) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (ram13 == 10) {
                                    if (g10_randoms < ram10) {
                                        letterid = g10_randoms;
                                    } else if (g10_randoms > ram10) {
                                        letterid = ram10;
                                    } else if (g10_randoms == ram10) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (ram13 == 11) {
                                    if (g11_randoms < ram11) {
                                        letterid = g11_randoms;
                                    } else if (g11_randoms > ram11) {
                                        letterid = ram11;
                                    } else if (g11_randoms == ram11) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (ram13 == 12) {
                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                  /*  if (g12_randoms < ram12) {
                                        letterid = g12_randoms;
                                    } else if (g12_randoms > ram12) {
                                        letterid = ram2;
                                    } else if (g12_randoms == ram12) {
                                        letterid = 5;
                                    }*/
                                    //game_start_clue_game();
                                }
                                System.out.println("=========randoms======" + randoms_2 + "=====random====" + random_2s);
                            } else if (randoms_2_ng > ram13) {

                                if (randoms_2_ng == 1) {
                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                    //game_start_picture_game();

                                } else if (randoms_2_ng == 2) {

                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }

                                    // game_start_clue_game();
                                } else if (randoms_2_ng == 3) {

                                    if (g3_randoms < ram3) {
                                        letterid = g3_randoms;
                                    } else if (g3_randoms > ram3) {
                                        letterid = ram3;
                                    } else if (g3_randoms == ram3) {
                                        letterid = 5;
                                    }
                                    // game_start_solukulsol();
                                } else if (randoms_2_ng == 4) {
                                    if (g4_randoms < ram4) {
                                        letterid = g4_randoms;
                                    } else if (g4_randoms > ram4) {
                                        letterid = ram4;
                                    } else if (g4_randoms == ram4) {
                                        letterid = 5;
                                    }
                                    // game_start_sol_game();
                                } else if (randoms_2_ng == 5) {

                                    if (g5_randoms < ram5) {
                                        letterid = g5_randoms;
                                    } else if (g5_randoms > ram5) {
                                        letterid = ram5;
                                    } else if (g5_randoms == ram5) {
                                        letterid = 5;
                                    }
                                    //game_start_picture_game();
                                } else if (randoms_2_ng == 6) {
                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }
                                   /* if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }*/
                                   /* if (g6_randoms < ram6) {
                                        letterid = g6_randoms;
                                    } else if (g6_randoms > ram6) {
                                        letterid = ram2;
                                    } else if (g6_randoms == ram6) {
                                        letterid = 5;
                                    }*/
                                    //game_start_clue_game();
                                } else if (randoms_2_ng == 7) {
                                    if (g7_randoms < ram7) {
                                        letterid = g7_randoms;
                                    } else if (g7_randoms > ram7) {
                                        letterid = ram7;
                                    } else if (g7_randoms == ram7) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (randoms_2_ng == 8) {
                                    if (g8_randoms < ram8) {
                                        letterid = g8_randoms;
                                    } else if (g8_randoms > ram8) {
                                        letterid = ram8;
                                    } else if (g8_randoms == ram8) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (randoms_2_ng == 9) {
                                    if (g9_randoms < ram9) {
                                        letterid = g9_randoms;
                                    } else if (g9_randoms > ram9) {
                                        letterid = ram9;
                                    } else if (g9_randoms == ram9) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (randoms_2_ng == 10) {
                                    if (g10_randoms < ram10) {
                                        letterid = g10_randoms;
                                    } else if (g10_randoms > ram10) {
                                        letterid = ram10;
                                    } else if (g10_randoms == ram10) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (randoms_2_ng == 11) {
                                    if (g11_randoms < ram11) {
                                        letterid = g11_randoms;
                                    } else if (g11_randoms > ram11) {
                                        letterid = ram11;
                                    } else if (g11_randoms == ram11) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                } else if (randoms_2_ng == 12) {
                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                   /* if (g12_randoms < ram12) {
                                        letterid = g12_randoms;
                                    } else if (g12_randoms > ram12) {
                                        letterid = ram2;
                                    } else if (g12_randoms == ram12) {
                                        letterid = 5;
                                    }*/
                                    //game_start_clue_game();
                                }

                            } else if (randoms_2_ng == ram13) {
                                if (g1_randoms < ram1) {
                                    letterid = g1_randoms;
                                } else if (g1_randoms > ram1) {
                                    letterid = ram1;
                                } else if (g1_randoms == ram1) {
                                    letterid = 5;
                                }
                                //game_start_picture_game();
                            }
                        } else {
                            if (randoms_2 < random_2s) {
                                if (random_2s == 1) {
                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                    //game_start_picture_game();
                                } else if (random_2s == 2) {

                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }

                                    // game_start_clue_game();
                                } else if (random_2s == 3) {

                                    if (g3_randoms < ram3) {
                                        letterid = g3_randoms;
                                    } else if (g3_randoms > ram3) {
                                        letterid = ram3;
                                    } else if (g3_randoms == ram3) {
                                        letterid = 5;
                                    }
                                    // game_start_solukulsol();
                                } else if (random_2s == 4) {
                                    if (g4_randoms < ram4) {
                                        letterid = g4_randoms;
                                    } else if (g4_randoms > ram4) {
                                        letterid = ram4;
                                    } else if (g4_randoms == ram4) {
                                        letterid = 5;
                                    }
                                    // game_start_sol_game();
                                } else if (random_2s == 5) {

                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                    //game_start_picture_game();
                                } else if (random_2s == 6) {
                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }
                                    //game_start_clue_game();
                                }
                                System.out.println("=========randoms======" + randoms_2 + "=====random====" + random_2s);
                            } else if (randoms_2 > random_2s) {
                                if (randoms_2 == 1) {
                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                    // game_start_picture_game();
                                } else if (randoms_2 == 2) {
                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }

                                    // game_start_clue_game();
                                } else if (randoms_2 == 3) {
                                    if (g3_randoms < ram3) {
                                        letterid = g3_randoms;
                                    } else if (g3_randoms > ram3) {
                                        letterid = ram3;
                                    } else if (g3_randoms == ram3) {
                                        letterid = 5;
                                    }
                                    // game_start_solukulsol();
                                } else if (randoms_2 == 4) {
                                    if (g4_randoms < ram4) {
                                        letterid = g4_randoms;
                                    } else if (g4_randoms > ram4) {
                                        letterid = ram4;
                                    } else if (g4_randoms == ram4) {
                                        letterid = 5;
                                    }
                                    // game_start_sol_game();
                                } else if (randoms_2 == 5) {
                                    if (g1_randoms < ram1) {
                                        letterid = g1_randoms;
                                    } else if (g1_randoms > ram1) {
                                        letterid = ram1;
                                    } else if (g1_randoms == ram1) {
                                        letterid = 5;
                                    }
                                    // game_start_picture_game();
                                } else if (randoms_2 == 6) {
                                    if (g2_randoms < ram2) {
                                        letterid = g2_randoms;
                                    } else if (g2_randoms > ram2) {
                                        letterid = ram2;
                                    } else if (g2_randoms == ram2) {
                                        letterid = 5;
                                    }
                                    // game_start_clue_game();
                                }
                            } else if (randoms_2 == random_2s) {
                                if (g1_randoms < ram1) {
                                    letterid = g1_randoms;
                                } else if (g1_randoms > ram1) {
                                    letterid = ram1;
                                } else if (g1_randoms == ram1) {
                                    letterid = 5;
                                }
                                //game_start_picture_game();
                            }
                        }

                    }

                    //////////////////////////////////////////////////////////////NEW FUNCTION////////////////////////////////////
                } else {
                    if (sp.getString(Solli_adi_multiplayer.this, "initialstarting").equals("")) {

                        if (randoms < random) {
                            letterid = random;
                            //game_start_solukulsol();
                            //game_start_picture_game();
                            System.out.println("=========randoms" + randoms + "random" + random);
                        } else if (randoms > random) {
                            letterid = randoms;
                            //game_start_solukulsol();
                            //game_start_picture_game();
                            System.out.println("=========randoms" + randoms + "random" + random);
                        } else if (randoms == random) {
                            letterid = 5;
                            //game_start_solukulsol();
                            //game_start_picture_game();
                            System.out.println("=========randoms" + randoms + "random" + random);
                        }

                    }

                    System.out.println("##############################################Length is 6 leser");

////////////////////////////////////////////////////////////game count//////////////////////////////////////////////////////
                }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if (sp.getString(Solli_adi_multiplayer.this, "initialstarting").equals("")) {

                   /* if (randoms < random) {
                        letterid = random;
                        //game_start_solukulsol();
                        //game_start_picture_game();
                        System.out.println("=========randoms" + randoms + "random" + random);
                    } else if (randoms > random) {
                        letterid = randoms;
                        //game_start_solukulsol();
                        //game_start_picture_game();
                        System.out.println("=========randoms" + randoms + "random" + random);
                    } else if (randoms == random) {
                        letterid = 5;
                        //game_start_solukulsol();
                        //game_start_picture_game();
                        System.out.println("=========randoms" + randoms + "random" + random);
                    }*/

                    if (buf.length > 6) {

                        if (randoms_2_ng < ram13) {
                            if (ram13 == 1) {
                                game_start_picture_game();
                                // game_start_oppo_word();
                            } else if (ram13 == 2) {
                                game_start_clue_game();
                                // game_start_oppo_word();
                            } else if (ram13 == 3) {
                                game_start_solukulsol();
                                //  game_start_oppo_word();
                            } else if (ram13 == 4) {
                                game_start_sol_game();
                                // game_start_oppo_word();
                            } else if (ram13 == 5) {
                                game_start_odd_man_out();
                                // game_start_oppo_word();
                            } else if (ram13 == 6) {
                                game_start_clue_game();
                                //  game_start_picture_game();
                                // game_start_oppo_word();
                            } else if (ram13 == 7) {
                                game_start_oppo_word();
                            } else if (ram13 == 8) {
                                game_start_ote_to_tamil();
                                // game_start_oppo_word();
                            } else if (ram13 == 9) {
                                game_start_right_order();
                                // game_start_oppo_word();
                            } else if (ram13 == 10) {
                                game_start_riddles();
                                // game_start_oppo_word();
                            } else if (ram13 == 11) {

                                game_start_error_correction();
                                //game_start_oppo_word();
                            } else if (ram13 == 12) {
                                game_start_picture_game();
                                //game_start_oppo_word();
                            }
                            System.out.println("=========randoms======" + randoms_2_ng + "=====ram13====" + ram13);
                        } else if (randoms_2_ng > ram13) {
                            if (randoms_2_ng == 1) {
                                game_start_picture_game();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 2) {
                                game_start_clue_game();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 3) {
                                game_start_solukulsol();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 4) {
                                game_start_sol_game();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 5) {
                                game_start_odd_man_out();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 6) {
                                game_start_clue_game();
                                //  game_start_picture_game();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 7) {
                                game_start_oppo_word();
                            } else if (randoms_2_ng == 8) {
                                game_start_ote_to_tamil();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 9) {
                                game_start_right_order();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 10) {
                                game_start_riddles();
                                // game_start_oppo_word();
                            } else if (randoms_2_ng == 11) {
                                game_start_error_correction();
                                //game_start_oppo_word();
                            } else if (randoms_2_ng == 12) {
                                game_start_picture_game();
                                // game_start_oppo_word();
                            }
                            System.out.println("=========randoms=======" + randoms_2_ng + "======ram13======" + ram13);
                        } else if (randoms_2_ng == ram13) {
                            //game_start_odd_man_out();
                            //game_start_oppo_word();
                            //game_start_right_order();
                            //game_start_ote_to_tamil();
                            //game_start_riddles();
                            //game_start_error_correction();
                            game_start_solukulsol();
                            // game_start_oppo_word();
                            System.out.println("=========randoms========" + randoms_2_ng + "=========ram13=====" + ram13);
                        }

                    } else {
                        if (randoms_2 < random_2s) {
                            if (random_2s == 1) {
                                game_start_picture_game();
                                //game_start_sol_game();
                            } else if (random_2s == 2) {
                                game_start_clue_game();
                                //game_start_sol_game();
                            } else if (random_2s == 3) {
                                game_start_solukulsol();
                                //game_start_sol_game();
                            } else if (random_2s == 4) {
                                //game_start_solukulsol();
                                game_start_sol_game();
                            } else if (random_2s == 5) {
                                //game_start_solukulsol();
                                game_start_picture_game();
                            } else if (random_2s == 6) {
                                //game_start_solukulsol();
                                game_start_clue_game();
                            }
                            System.out.println("=========randoms======" + randoms_2 + "=====random====" + random_2s);
                        } else if (randoms_2 > random_2s) {
                            if (randoms_2 == 1) {
                                game_start_picture_game();

                                //game_start_sol_game();
                            } else if (randoms_2 == 2) {
                                game_start_clue_game();
                                //game_start_sol_game();
                            } else if (randoms_2 == 3) {
                                game_start_solukulsol();
                                //game_start_sol_game();
                            } else if (randoms_2 == 4) {
                                // game_start_solukulsol();
                                game_start_sol_game();
                            } else if (randoms_2 == 5) {
                                //game_start_solukulsol();
                                game_start_picture_game();
                            } else if (randoms_2 == 6) {
                                //game_start_solukulsol();
                                game_start_clue_game();
                            }
                            System.out.println("=========randoms=======" + randoms_2 + "======random======" + random_2s);
                        } else if (randoms_2 == random_2s) {
                            game_start_picture_game();
                            // game_start_sol_game();
                            System.out.println("=========randoms========" + randoms_2 + "=========random=====" + random_2s);
                        }

                    }

                    sp.putString(Solli_adi_multiplayer.this, "initialstarting", "yes");
                }
////////////////////////////////////////////////////////////////////////////////////////////
                if (buf[0] == 'U') {

                    //Toast.makeText(Solli_adi_multiplayer.this, "Game Over Your Opponent Won The Match", Toast.LENGTH_SHORT).show();
             /*   Handler handler = new Handler();
                  handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exitgame();
                    }
                }, 1000);*/
                    if (gameid == 1) {
                        opponent_m_count.setText("" + ut_game);
                        Cursor c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'and levelid='" + letterid + "' and rtm='0'");
                        c.moveToFirst();
                        if (c.getCount() != 0) {
                            String ans = c.getString(c.getColumnIndexOrThrow("answer"));
                            ans_highlite.setText(ans);
                            ans_highlite.setVisibility(View.VISIBLE);
                            list4.setVisibility(View.INVISIBLE);
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");

                                    }

                                }
                            }, 2000);
                        }
                    } else if (gameid == 2) {
                        opponent_m_count.setText("" + ut_game);
                        Cursor c = myDbHelper.getQry("select * from maintable where gameid='" + gameid + "'and levelid='" + letterid + "' and rtm='0'");
                        c.moveToFirst();
                        if (c.getCount() != 0) {
                            String ans = c.getString(c.getColumnIndexOrThrow("answer"));
                            ans_highlite.setText(ans);
                            ans_highlite.setVisibility(View.VISIBLE);
                            list4.setVisibility(View.INVISIBLE);
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");
                                    }

                                }
                            }, 2000);

                        }
                    } else if (gameid == 5) {
                        opponent_m_count.setText("" + ut_game);
                        if (opp_comp_count < score_s) {
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx + 200;
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                                    }
                                }
                            }, 2000);

                        } else if (opp_comp_count > score_s) {
                           /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx - 200;
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");
                                    }

                                }
                            }, 2000);

                        } else if (opp_comp_count == score_s) {
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx + 100;
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(100, "ஆட்டம் சமமாக முடிந்தது.இருவரும் சமமான புள்ளிகளை எடுத்துஉள்ளிர்கள்.");

                                    }
                                }
                            }, 2000);
                        }
                    } else if (gameid == 7) {
                        opponent_m_count.setText("" + ut_game);
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@opp_comp_count" + opp_comp_count);
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@score_s" + score_s);
                        if (opp_comp_count < score_s) {
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx + 200;
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@spx" + spx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");

                                    }
                                }
                            }, 2000);
                        } else if (opp_comp_count > score_s) {
                        /*    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@skx"+skx);
                            int spx = skx - 200;
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@spx"+spx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");

                                    }
                                }
                            }, 2000);
                        } else if (opp_comp_count == score_s) {
                            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                            cfx.moveToFirst();
                            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                            int spx = skx + 100;
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@spx" + spx);
                            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (play_after == 0) {
                                        play_after = 1;
                                        finalscreen(100, "ஆட்டம் சமமாக முடிந்தது.இருவரும் சமமான புள்ளிகளை எடுத்துஉள்ளிர்கள்.");
                                    }
                                }
                            }, 2000);

                        }
                    } else {
                        opponent_m_count.setText("" + ut_game);

                        Handler handler8 = new Handler();
                        handler8.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (play_after == 0) {
                                    play_after = 1;
                                    opponent_m_count.setText("5");
                                    finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");
                                }
                            }
                        }, 2000);


                    }
                } else {
                    updatePeerScoresDisplay((int) buf[1]);
                }
                System.out.println("=========onRealTimeMessageReceived==existingScore========" + existingScore);
                System.out.println("=========onRealTimeMessageReceived==thisScore========" + thisScore);
                if (thisScore > existingScore) {
                    // this check is necessary because packets may arrive out of
                    // order, so we
                    // should only ever consider the highest score we received, as
                    // we know in our
                    // game there is no way to lose points. If there was a way to
                    // lose points,
                    // we'd have to add a "serial number" to the packet.
                    mParticipantScore.put(sender, thisScore);
                }
                // update the scores on the screen
                // if it's a final score, mark this participant as having finished
                // the game
                if ((char) buf[0] == 'F') {
                    mFinishedParticipants.add(rtm.getSenderParticipantId());
                }

               /* mMultiplayer = true;
                broadcastScore(true);*/
            }

        }

        if (Utils.mProgress.isShowing()) {
            Utils.mProgress.dismiss();
        }


    }

    @Override
    public void onConnected (@Nullable Bundle bundle){
        System.out.println("===============================onConnected: " + bundle);
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
        //int curr_score=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score");
        if (skx > 100) {
            if (bundle != null) {

                Intent intent;
                intent = Games.Invitations.getInvitationInboxIntent(mGoogleApiClient);
                switchToScreen(R.id.screen_wait);
                startActivityForResult(intent, RC_INVITATION_INBOX);
            }

            if (sp.getString(Solli_adi_multiplayer.this, "mul_invitation").equals("start")) {
                if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                    Intent intent;
                    intent = Games.Invitations.getInvitationInboxIntent(mGoogleApiClient);
                    switchToScreen(R.id.screen_wait);
                    startActivityForResult(intent, RC_INVITATION_INBOX);
                }
            }
        } else {
            if (bundle != null) {
                earncoin();
            }

        }

    }

    @Override
    public void onConnectionSuspended ( int i){
        System.out.println("===============================onConnectionSuspended" + i);
    }

    @Override
    public void onConnectionFailed (@NonNull ConnectionResult connectionResult){
        System.out.println("===============================onConnectionFailed" + connectionResult);
    }

    @Override
    public void onInvitationReceived (Invitation invitation){
        System.out.println("===============================onInvitationReceived" + invitation);
    }

    @Override
    public void onInvitationRemoved (String s){
        System.out.println("===============================onInvitationRemoved" + s);
    }

    @Override
    public void onRoomConnecting (Room room){
        System.out.println("===============================onRoomConnecting" + room);
        System.out.println("+++++++++++++++++++++++++++++++++onRoomConnecting" + room.getParticipantIds());
    }

    @Override
    public void onRoomAutoMatching (Room room){
        System.out.println("===============================onRoomAutoMatching" + room);
    }

    @Override
    public void onPeerInvitedToRoom (Room room, List < String > list){
        System.out.println("===============================onPeerInvitedToRoom" + list);
    }

    @Override
    public void onPeerDeclined (Room room, List < String > list){
        System.out.println("===============================onPeerDeclined" + list);
    }

    @Override
    public void onPeerJoined (Room room, List < String > list){

        gameid = 0;
        play_after = 0;
        myDbHelper.executeSql("UPDATE maintable SET rtm=0");
        myDbHelper.executeSql("delete from answertable where rd=3");
        score_s = 0;
        letterid = 0;
        st_game = 0;
        ut_game = 0;
        st = 0;
        backdialog = 0;
        chathd = 0;
        message_dia_no = 0;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Random rn = new Random();
        randoms = rn.nextInt(max - min + 1) + min;
        System.out.println("============================RC_WAITING_ROOM=============randoms" + randoms);

        Random rnd = new Random();
        randoms_2 = rnd.nextInt(max_2 - min_2 + 1) + min_2;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        g1_max = p_count;
        Random g1_r = new Random();
        g1_randoms = g1_r.nextInt(g1_max - g1_min + 1) + g1_min;

        g2_max = c_count;
        Random g2_r = new Random();
        g2_randoms = g2_r.nextInt(g2_max - g2_min + 1) + g2_min;

        g3_max = ss_count;
        Random g3_r = new Random();
        g3_randoms = g3_r.nextInt(g3_max - g3_min + 1) + g3_min;

        g4_max = s_count;
        Random g4_r = new Random();
        g4_randoms = g4_r.nextInt(g4_max - g4_min + 1) + g4_min;

        g5_max = g5_count_odd;
        Random g5_r = new Random();
        g5_randoms = g5_r.nextInt(g5_max - g5_min + 1) + g5_min;

        g6_max = g6_count_equal;
        Random g6_r = new Random();
        g6_randoms = g6_r.nextInt(g6_max - g6_min + 1) + g6_min;

        g7_max = g7_count_diff;
        Random g7_r = new Random();
        g7_randoms = g7_r.nextInt(g7_max - g7_min + 1) + g7_min;

        g8_max = g8_count_etot;
        Random g8_r = new Random();
        g8_randoms = g8_r.nextInt(g8_max - g8_min + 1) + g8_min;

        g9_max = g9_count_arg;
        Random g9_r = new Random();
        g9_randoms = g9_r.nextInt(g9_max - g9_min + 1) + g9_min;

        g10_max = g10_count_riddle;
        Random g10_r = new Random();
        g10_randoms = g10_r.nextInt(g10_max - g10_min + 1) + g10_min;

        g11_max = g11_count_errorc;
        Random g11_r = new Random();
        g11_randoms = g11_r.nextInt(g11_max - g11_min + 1) + g11_min;

        g12_max = g12_count_tir;
        Random g12_r = new Random();
        g12_randoms = g12_r.nextInt(g12_max - g12_min + 1) + g12_min;


        Random rndg = new Random();
        randoms_2_ng = rndg.nextInt(max_2_ng - min_2_ng + 1) + min_2_ng;

                    /*int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") - 50;
                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                    sp.putString(Solli_adi_multiplayer.this, "initialstarting", "");
                    m_score.setText("" + sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));*/

        sp.putString(Solli_adi_multiplayer.this, "initialstarting", "");

                   /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int ===============================onConnectedBundlespx = skx - 100;
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    m_score.setText(""+spx);*/

        // ps = 1;
       /* Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", false).show();
        Utils.mProgress.setCancelable(false);*/

        System.out.println("===============================onPeerJoined" + list);
    }

    @Override
    public void onPeerLeft (Room room, List < String > list){
        System.out.println("===============================onPeerLeft" + list);
    }

    @Override
    public void onConnectedToRoom (Room room){
        System.out.println("===============================onConnectedToRoom" + room);
//get participants and my ID:
        mParticipants = room.getParticipants();
        mMyId = room.getParticipantId(Games.Players.getCurrentPlayerId(mGoogleApiClient));

        System.out.println("me_check player onConnectedToRoom mMyId : " + mMyId);
        System.out.println("me_check player onConnectedToRoom mParticipants : " + mParticipants);

        for (Participant p : mParticipants) {

            System.out.println("me_check player Participant p DisplayName : " + p.getDisplayName());
            System.out.println("me_check player Participant p getParticipantId : " + p.getParticipantId());
            System.out.println("me_check player Participant p getHiResImageUrl : " + p.getHiResImageUrl());


            if (mMyId.equals(p.getParticipantId())) {
                img_url1 = "" + p.getHiResImageUrl();
                player_name1 = "" + p.getDisplayName();
                System.out.println("me_check player player_name1 if : " + player_name1);
                System.out.println("me_check player img_url1: " + img_url1);
            } else {
                img_url2 = "" + p.getHiResImageUrl();
                player_name2 = "" + p.getDisplayName();
                System.out.println("me_check player img_url2: " + img_url2);
                System.out.println("me_check player player_name2: " + player_name2);
            }
        }

        // save room ID if its not initialized in onRoomCreated() so we can leave cleanly before the game starts.
        if (mRoomId == null)
            mRoomId = room.getRoomId();

        // print out the list of participants (for debug purposes)
        System.out.println("GoogleApiClient onConnectedToRoom mRoomId : " + mRoomId);
        System.out.println("GoogleApiClient onConnectedToRoom mMyId : " + mMyId);
        System.out.println("GoogleApiClient onConnectedToRoom room : " + room);

    }

    @Override
    public void onDisconnectedFromRoom (Room room){
        System.out.println("===============================onDisconnectedFromRoom" + room);
        //exitgame();

        if (play_after == 0) {
            play_after = 1;
            // Toast.makeText(Solli_adi_multiplayer.this, "Your Opponent Left The Match You Won.You Got 100 Points", Toast.LENGTH_SHORT).show();
       /*     int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
            sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
            finalscreen(100,"உங்கள் எதிராளி வெளியேறிவிட்டார். நீங்கள் வெற்றி அடைந்துவிட்டீர்கள் உங்களுக்கான நாணயங்கள் ");
*/
            mRoomId = null;
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 200;
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            finalscreen(200, "உங்கள் எதிராளி வெளியேறிவிட்டார். நீங்கள் வெற்றி அடைந்துவிட்டீர்கள் உங்களுக்கான நாணயங்கள் ");
        } else {
            mRoomId = null;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    exitgame();
                }
            }, 1000);
        }


    }

    @Override
    public void onPeersConnected (Room room, List < String > list){
        System.out.println("===============================onPeersConnected" + list);
    }

    @Override
    public void onPeersDisconnected (Room room, List < String > list){
        System.out.println("===============================onPeersDisconnected" + list);
    }

    @Override
    public void onP2PConnected (String s){

        if (ps == 1) {
            ps = 0;
            Utils.mProgress.dismiss();
        }
        if (st == 0) {
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx - 100;
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            m_score.setText("" + spx);
            st = 1;

        }


        System.out.println("===============================onP2PConnected" + s);
    }

    @Override
    public void onP2PDisconnected (String s){
        System.out.println("===============================onP2PDisconnected" + s);
    }

/////////////////////////////////////////////////////////Override Methods in multiplayer///////////////////////////////////////////////////
/////////////////////////////////////////////////////////Another Override Methods for multiplayer///////////////////////////////////////////
/////////////////////////////////////////////////////////Another Override Methods for multiplayer///////////////////////////////////////////
/////////////////////////////////////////////////////////Earn Coin Dialog/////////////////////////////////////////////////////////////////

    public void earncoin () {
        final Dialog openDialog_earncoin = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin_multiplayer);


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

                if (isNetworkAvailable()) {


                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }
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
                /*if (isNetworkAvailable()) {


                    openDialog_earncoin.cancel();
                    btn_str = "invite";
                    if (isLoggedIn()) {
                        Bundle params = new Bundle();
                        params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        showDialogWithoutNotificationBarInvite("apprequests",
                                params);
                        // toast("yes");
                    } else {
                      //  openFacebookSession();
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

    public boolean appInstalledOrNot (String uri){
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

    private boolean isNetworkAvailable () {
        ConnectivityManager connec = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    private void pauseGame () {
        // mCountDownTimer.cancel();
        mGamePaused = true;
    }

    private void resumeGame () {
        // createTimer(mTimeRemaining);
        mGamePaused = false;
    }

    private void loadRewardedVideoAd () {

    }

    private void addCoins ( int coins){
        mCoinCount = coins;
        sp.putInt(Solli_adi_multiplayer.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }

    private void startGame () {

        //loadRewardedVideoAd();
        mGamePaused = false;
        mGameOver = false;
    }

    private void showRewardedVideo () {
        //mShowVideoButton.setVisibility(View.INVISIBLE);

    }

    public void finalscreen ( final int a, String content){
        final Dialog openDialog = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.final_score_showing_dialog);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        TextView coin_txt_content = (TextView) openDialog.findViewById(R.id.coin_txt_content);
        TextView u1_name = (TextView) openDialog.findViewById(R.id.u1_name);
        TextView u2_name = (TextView) openDialog.findViewById(R.id.u2_name);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        ImageView my_profile = (ImageView) openDialog.findViewById(R.id.my_profile);
        ImageView opp_profile = (ImageView) openDialog.findViewById(R.id.opp_profile);
        LinearLayout my_high = (LinearLayout) openDialog.findViewById(R.id.my_high);
        LinearLayout opp_high = (LinearLayout) openDialog.findViewById(R.id.opp_high);
        price_update(a);
        Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_profile);

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(opp_profile);
        if (a == 200) {
            my_high.setBackgroundColor(Color.parseColor("#fcf804"));
        } else {
            opp_high.setBackgroundColor(Color.parseColor("#fcf804"));
        }

        b_scores.setText("" + a);

        u1_name.setText("" + player_name1);
        u2_name.setText("" + player_name2);

        coin_txt_content.setText(content);
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRoomId = null;
                if (message_dia.isShowing()) {
                    message_dia.dismiss();
                }
                if (backdialog == 1) {
                    openDialog_p.dismiss();
                }
                if (chathd == 1) {
                    chat_historyd.dismiss();
                }
                if (message_dia_no == 1) {
                    message_dia.dismiss();
                }
                exitgame();
                openDialog.dismiss();
            }
        });
        if (!isFinishing()) {

            openDialog.show();


        }

    }

    public void share_earn ( final int a){
        final Dialog openDialog = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        final TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        b_scores.setText("" + a);
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* int ad=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + a;
                sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", ad);
                m_score.setText(""+sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));
*/
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                m_score.setText("" + skx);

                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }

    public void vidcoinearn () {
        final Dialog openDialog = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog_multiplayer);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                m_score.setText(""+sp.getInt(Solli_adi_multiplayer.this, "muliplay_score"));*/

                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx + 100;
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                m_score.setText("" + spx);

                openDialog.dismiss();

                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }

    //reward videos***********************//
/*    private void showDialogWithoutNotificationBarInvite(String action, Bundle params) {
        final WebDialog dialog = new WebDialog.Builder(Solli_adi_multiplayer.this,
                Session.getActiveSession(), action, params)
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {
                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error != null
                                && !(error instanceof FacebookOperationCanceledException)) {

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

                                int spx = (values.size() - 1) * 10;
                                share_earn(spx);

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

    private void openFacebookSession() {
        Session.openActiveSession(this, true, Arrays.asList("email",
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

/*    public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());
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

        params.putString("caption", "நான் சொல்லிஅடி செயலியில் சொல்லுக்குள் சொல்   முடித்துள்ளேன்.");
        // params.putString("picture","https://s3-ap-southeast-1.amazonaws.com/nithra-tamil/solliadi_logo.webp");
        WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(this,
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

                            } else {
                                // User clicked the Cancel button
                                Toast.makeText(getApplicationContext(),
                                        "Publish cancelled", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                            // User clicked the "x" button
                            Toast.makeText(getApplicationContext(),
                                    "Publish cancelled", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            // Generic, ex: network error
                            Toast.makeText(getApplicationContext(),
                                    "Error posting story", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                }).build();
        feedDialog.show();
    }*/

   /* public void multiplayerimg() {
        for (Participant p : mRoomCurrent.getParticipants()) {

            //makeToast("should be loading pic");
            ImageManager IM = ImageManager.create(this);
            IM.loadImage(new ImageManager.OnImageLoadedListener() {

                @Override
                public void onImageLoaded(Uri arg0, Drawable drawable, boolean arg2) {
                    if (drawable == null) {
                        theGameInterface.picLoaded(participantID);
                    } else {
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        try {
                            FileOutputStream out = openFileOutput("pic" + participantID + ".png", Context.MODE_MULTI_PROCESS);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                            out.flush();
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            //dLog(e.getStackTrace().toString());
                        }
                    }

                }
            }, p.getIconImageUri());
        }
    }*/
///////////////////////////////////////////////////////////Earn Coin Dialog/////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////Ads_Layout/////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////Ads_Layout/////////////////////////////////////////////////////////////////
    public void sound () {
        String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
        if (snd.equals("off")) {
            settings.setBackgroundResource(R.drawable.sound_off);
            // toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;

        } else if (snd.equals("on")) {
            //  toggleButton.setBackgroundResource(R.drawable.on);
            settings.setBackgroundResource(R.drawable.sound_on);
            sv = 1;

        }
    }


    protected boolean isSignedIn () {
        return mHelper.isSignedIn();
    }

    protected GoogleApiClient getApiClient () {
        return mHelper.getApiClient();
    }

    protected void beginUserInitiatedSignIn () {
        mHelper.beginUserInitiatedSignIn();
    }

    public GameHelper getGameHelper () {
        if (mHelper == null) {
            mHelper = new GameHelper(this, mRequestedClients);
            mHelper.enableDebugLog(mDebugLog);
        }
        return mHelper;
    }

    public void chat_history () {

        if (Utils.isNetworkAvailable(getApplicationContext())) {
            message_con();
        } else {
            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
        }
       /* System.out.println("+++++++++++++++++++++chat_his");
        chat_historyd = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        chat_historyd.setContentView(R.layout.chat_history);
        ListView list_l = (ListView) chat_historyd.findViewById(R.id.list_l);
        TextView close = (TextView) chat_historyd.findViewById(R.id.ssss);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chathd = 0;
                chat_historyd.dismiss();
            }
        });
        chat_adapter chatAdapter = new chat_adapter();
        list_l.setAdapter(chatAdapter);
        chat_historyd.show();
        chathd = 1;*/

    }


    public class chat_adapter extends BaseAdapter {

        TextView my_chat, opp_chat;

        @Override
        public int getCount() {
            System.out.println("+++++++++++++++++++++chat_history.size()" + chat_history.size());
            return chat_history.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chat_history_textview, null);

            my_chat = (TextView) view.findViewById(R.id.my_chat);
            opp_chat = (TextView) view.findViewById(R.id.opp_chat);

            if (chat_history.get(i).contains("!")) {
                opp_chat.setVisibility(View.VISIBLE);
                opp_chat.setText(chat_history.get(i).replace("!", ""));
                my_chat.setVisibility(View.GONE);
            } else {
                opp_chat.setVisibility(View.GONE);
                my_chat.setText(chat_history.get(i));
                my_chat.setVisibility(View.VISIBLE);
            }

            return view;
        }
    }

    // "Invite friends" button. We react by creating a room with those players.
    private void handleSelectPlayersResult ( int response, Intent data){
        if (response != Activity.RESULT_OK) {
            Log.w(TAG, "*** select players UI cancelled, " + response);
            exitgame();
            return;
        }
        Log.d(TAG, "Select players UI succeeded.");
        // get the invitee list
        final ArrayList<String> invitees = data.getStringArrayListExtra(Games.EXTRA_PLAYER_IDS);
        Log.d(TAG, "Invitee count: " + invitees.size());
        // get the automatch criteria
        Bundle autoMatchCriteria = null;
        int minAutoMatchPlayers = data.getIntExtra(com.google.android.gms.games.multiplayer.Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, 0);
        int maxAutoMatchPlayers = data.getIntExtra(com.google.android.gms.games.multiplayer.Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, 0);
        if (minAutoMatchPlayers > 0 || maxAutoMatchPlayers > 0) {
            autoMatchCriteria = RoomConfig.createAutoMatchCriteria(
                    minAutoMatchPlayers, maxAutoMatchPlayers, 0);
            Log.d(TAG, "Automatch criteria: " + autoMatchCriteria);
        }
        // create the room
        Log.d(TAG, "Creating room...");
        RoomConfig.Builder rtmConfigBuilder = RoomConfig.builder(this);
        rtmConfigBuilder.addPlayersToInvite(invitees);
        rtmConfigBuilder.setMessageReceivedListener(this);
        rtmConfigBuilder.setRoomStatusUpdateListener(this);
        if (autoMatchCriteria != null) {
            rtmConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
        }
        switchToScreen(R.id.screen_wait);
        keepScreenOn();
        // resetGameVars();
        Games.RealTimeMultiplayer.create(mGoogleApiClient, rtmConfigBuilder.build());
        Log.d(TAG, "Room created, waiting for it to be ready...");
        Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", false).show();

    }

    // Handle the result of the invitation inbox UI, where the player can pick an invitation
    // to accept. We react by accepting the selected invitation, if any.
    private void handleInvitationInboxResult ( int response, Intent data){
        if (response != Activity.RESULT_OK) {
            Log.w(TAG, "*** invitation inbox UI cancelled, " + response);
            exitgame();
            return;
        }
        Log.d(TAG, "Invitation inbox UI succeeded.");
        Invitation inv = data.getExtras().getParcelable(com.google.android.gms.games.multiplayer.Multiplayer.EXTRA_INVITATION);
        // accept invitation
        acceptInviteToRoom(inv.getInvitationId());
    }

    // Accept the given invitation.
    void acceptInviteToRoom (String invId){
        // accept the invitation
        Log.d(TAG, "Accepting invitation: " + invId);
        RoomConfig.Builder roomConfigBuilder = RoomConfig.builder(this);
        roomConfigBuilder.setInvitationIdToAccept(invId)
                .setMessageReceivedListener(this)
                .setRoomStatusUpdateListener(this);
        //switchToScreen(R.id.screen_wait);
        keepScreenOn();
        //resetGameVars();
        Games.RealTimeMultiplayer.join(mGoogleApiClient, roomConfigBuilder.build());

        Utils.mProgress(Solli_adi_multiplayer.this, "காத்திருக்கவும்.....", false).show();
    }

    public void message_con () {
       /* final Dialog message_dia = new Dialog(WS_muliplayer.this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        //  final Dialog message_dia = new Dialog(WS_muliplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        message_dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        message_dia.setContentView(R.layout.dia_message);*/
        RelativeLayout parent_lay = (RelativeLayout) message_dia.findViewById(R.id.parent_lay);
        RelativeLayout tool_lay = (RelativeLayout) message_dia.findViewById(R.id.tool_lay);
        final EditText msg_txt = (EditText) message_dia.findViewById(R.id.msg_txt);
        ImageView msg_dismiss = (ImageView) message_dia.findViewById(R.id.msg_dismiss);
        final TextView send_msg = (TextView) message_dia.findViewById(R.id.send_msg);
        msg_txt.setText("");
        final TextView msg1 = (TextView) message_dia.findViewById(R.id.msg1);
        final TextView msg2 = (TextView) message_dia.findViewById(R.id.msg2);
        final TextView msg3 = (TextView) message_dia.findViewById(R.id.msg3);
        final TextView msg4 = (TextView) message_dia.findViewById(R.id.msg4);
        final TextView msg5 = (TextView) message_dia.findViewById(R.id.msg5);
        final TextView msg6 = (TextView) message_dia.findViewById(R.id.msg6);
        final TextView msg7 = (TextView) message_dia.findViewById(R.id.msg7);
        final TextView msg8 = (TextView) message_dia.findViewById(R.id.msg8);
        ListView list_l = (ListView) message_dia.findViewById(R.id.list_l);

        chatAdapter = new chat_adapter();
        list_l.setAdapter(chatAdapter);


        list_l.smoothScrollToPosition(chatAdapter.getCount() - 1);
        list_l.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list_l.setStackFromBottom(true);

        message_dia_no = 1;

        message_dia.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

      /*  msg_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(msg_txt.getWindowToken(), 0);
            }
        });
        msg_txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });*/
        /*InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(msg_txt.getWindowToken(), 0);*/
      /*  ListView msg_list = (ListView) message_dia.findViewById(R.id.msg_list);
        msg_ListAdpater msg_listAdpater = new msg_ListAdpater();
        msg_list.setAdapter(msg_listAdpater);
        msg_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                *//*message_dia.dismiss();
                go_message = "go_message";
                send_msg = ""+msg_word.get(position);
                mMultiplayer = true;
                broadcastScore(true);*//*
                msg_txt.setText(msg_word.get(position));
                msg_txt.setSelection(msg_word.get(position).length());
            }
        });*/


        msg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mm1 = msg1.getText().toString();
                //  msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    chatAdapter.notifyDataSetChanged();

                    //message_dia.dismiss();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        msg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mm1 = msg2.getText().toString();
                //  msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    // message_dia.dismiss();
                    chatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        msg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mm1 = msg3.getText().toString();
                //  msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    // message_dia.dismiss();
                    chatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        msg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mm1 = msg4.getText().toString();
                //  msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    // message_dia.dismiss();
                    chatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        msg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mm1 = msg5.getText().toString();
                // msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    //message_dia.dismiss();
                    chatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        msg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mm1 = msg6.getText().toString();
                // msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    // message_dia.dismiss();
                    chatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        msg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mm1 = msg7.getText().toString();
                // msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    // message_dia.dismiss();
                    chatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        msg8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mm1 = msg8.getText().toString();
                // msg_txt.setText(""+mm1);
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    go_message = "go_message";
                    send_mmsg = mm1;
                    chat_history.add(send_mmsg);
                    mMultiplayer = true;
                    broadcastScore(true);
                    message_dia_no = 0;
                    // message_dia.dismiss();
                    chatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
       /* InputMethodManager imm1 = (InputMethodManager) Solli_adi_multiplayer.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);*/

        msg_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message_dia_no = 0;
                message_dia.dismiss();
               /* InputMethodManager imm = (InputMethodManager) WS_muliplayer.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(msg_txt.getWindowToken(), 0);*/
            }
        });
        send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_mmsg = msg_txt.getText().toString();
                send_mmsg = send_mmsg.trim();
                System.out.println("###############################send_mmsg" + send_mmsg.length());
                if (TextUtils.isEmpty(send_mmsg)) {
                    Toast.makeText(Solli_adi_multiplayer.this, "நிரப்பவும்", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        go_message = "go_message";
                        send_mmsg = msg_txt.getText().toString();

                        chat_history.add(send_mmsg);
                        System.out.println("+++++++++++++++++++++chat_history S " + chat_history);
                        mMultiplayer = true;
                        broadcastScore(true);
                        message_dia_no = 0;
                        chatAdapter.notifyDataSetChanged();
                        msg_txt.setText("");
                        //  message_dia.dismiss();
                    } else {
                        Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }

                }


            /*    final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chat_me.setVisibility(View.GONE);
                    }
                }, 3000);
                chat_me.setVisibility(View.VISIBLE);
                chat_me.setText(send_msg);*/
      /*          InputMethodManager imm = (InputMethodManager) Solli_adi_multiplayer.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(msg_txt.getWindowToken(), 0);*/
            }
        });
       /* message_dia.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                InputMethodManager imm = (InputMethodManager) Solli_adi_multiplayer.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

               *//* InputMethodManager imm = (InputMethodManager) WS_muliplayer.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(msg_txt.getWindowToken(), 0);*//*
            }
        });*/


        message_dia.show();
    }

    public void missingimage () {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Solli_adi_multiplayer.this);                            /*.setTitle("Delete entry")*/

        alertDialogBuilder.setMessage("படங்கள் இல்லை பதிவிறக்கம் செய்யவேண்டுமா? ");
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("ஆம்", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            String date = sp.getString(Solli_adi_multiplayer.this, "date");
                            if (date.equals("0")) {
                                Cursor cursor1 = myDbHelper.getQry("SELECT id FROM maintable order by id desc");
                                cursor1.moveToFirst();
                                String lastid = null;
                                if (cursor1.getCount() != 0) {
                                    lastid = cursor1.getString(cursor1.getColumnIndexOrThrow("id"));
                                }
                                int idf = Integer.parseInt(id);
                                idf = idf + 20;
                                downpic(id, String.valueOf(idf));
                            } else {
                                Cursor cursor1 = myDbHelper.getQry("SELECT id FROM dailytest order by id desc");
                                cursor1.moveToFirst();
                                String lastid = null;

                                if (cursor1.getCount() != 0) {
                                    lastid = cursor1.getString(cursor1.getColumnIndexOrThrow("id"));
                                }
                                int idf = Integer.parseInt(id);
                                idf = idf + 20;
                                downpic(id, String.valueOf(idf));
                            }
                            dialog.dismiss();
                        } else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Solli_adi_multiplayer.this);                         /*   .setTitle("Delete entry")*/
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
                                            finish();
                                            Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                                            startActivity(i);
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

    public void downpic ( final String first, final String last){
        // w_head.setVisibility(View.INVISIBLE);
        Utils.mProgress(Solli_adi_multiplayer.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();


        System.out.println("######################wordid" + first + "#####################last" + last);
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
                nameValuePairs.add(new BasicNameValuePair("firstid", first));
                nameValuePairs.add(new BasicNameValuePair("lastid", last));
                String date = sp.getString(Solli_adi_multiplayer.this, "date");
                nameValuePairs.add(new BasicNameValuePair("mode", "regular"));
                email = sp.getString(Solli_adi_multiplayer.this, "email");
                nameValuePairs.add(new BasicNameValuePair("email", email));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/missingdata.php");
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
                checkmemory();

            }
        }.execute();
    }

    public void checkmemory () {
        String url = "";
        url = "https://nithra.mobi/solliadi/" + email + "-filename.zip";


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
        double filesize = (length * 3) / 1204;


        if (length > 0) {

            hrSize = dec.format(filesize).concat(" KB");
            System.out.println("KB:--" + hrSize);

        }


        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdAvailSize = (double) stat.getAvailableBlocks()
                * (double) stat.getBlockSize();

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
            System.out.print("========downconform");
            //startdownload();
            startDownload();
            //newdown();
        } else {

            goappmanager();
        }


    }

    public void goappmanager () {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext(), android.R.style.Theme_Dialog);
        builder1.setMessage("No free space clean your storage");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(Intent.ACTION_MANAGE_PACKAGE_STORAGE);
                        startActivity(i);
                    }
                });
        builder1.setNegativeButton("Later",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    public void startDownload () {


        String str_url = "https://nithra.mobi/solliadi/" + email + "-filename.zip";

        //     new DownloadFileAsync().execute(str_url);


        downloadFileAsync = new DownloadFileAsync();
        downloadFileAsync.execute(str_url);
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
            if (unused != null && unused.equals("ERROR_DOW")) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Solli_adi_multiplayer.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setTitle("Network connection not available, please check it!");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        downloadFileAsync.isCancelled();
                        downloadFileAsync.cancel(true);
                        if (exists("https://nithra.mobi/solliadi/" + email + "-filename.zip") == true) {
                            System.out.print("========zip ok");
                            checkmemory();
                        }

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            } else {
                Utils.mProgress.dismiss();
                //newdown();
                Cursor c;
                c = myDbHelper.getQry("select * from maintable where gameid='1'");
                c.moveToFirst();
                if (c.getCount() != 0) {
                    game_start_picture_game();
                } else {
                    //  nextgamesdialog();
                }
            }

            try {

            } catch (Exception e) {
                System.out.println("result=======////==" + e);
            }
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


    public int unpackZip (String ZIP_FILE_NAME){

        InputStream is;
        ZipInputStream zis;
        try {

            String fullPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/Nithra/solliadi/";
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

    public static boolean exists (String URLName){
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


    protected Dialog onCreateDialog ( int id){

        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(Solli_adi_multiplayer.this);
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

    @Override
    public void onRequestPermissionsResult ( int requestCode, String permissions[],
                                             int[] grantResults){

        if (requestCode == 150) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sp.putInt(Solli_adi_multiplayer.this, "permission", 1);
                missingimage();
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    if (!showRationale) {
                        sp.putInt(Solli_adi_multiplayer.this, "permission", 2);
                        finish();
                        Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                        startActivity(i);
                    } else if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sp.putInt(Solli_adi_multiplayer.this, "permission", 0);
                        finish();
                        Intent i = new Intent(Solli_adi_multiplayer.this, New_Main_Activity.class);
                        startActivity(i);
                    }
                }
            }
        }
    }

    public void pic_show ( int a){
        openDialogk = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialogk.setContentView(R.layout.show_pic);
        pic_show = (ImageView) openDialogk.findViewById(R.id.pic_show);
        Button cancel = (Button) openDialogk.findViewById(R.id.p_cancel);

        if (a == 1) {
            if (isdown.equals("0")) {

                pic_show.setImageResource(im11);

            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";
                Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                Resources res1 = getResources();
                BitmapDrawable bd = new BitmapDrawable(res1, bitimg1);
                pic_show.setImageDrawable(bd);


            }
        } else if (a == 2) {
            if (isdown.equals("0")) {
                pic_show.setImageResource(im12);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();

                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";
                Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                Resources res2 = getResources();
                BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                pic_show.setImageDrawable(bd2);


            }

        } else if (a == 3) {

            if (isdown.equals("0")) {
                pic_show.setImageResource(im13);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";
                Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word3 + "");
                Resources res2 = getResources();
                BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                pic_show.setImageDrawable(bd2);


            }

        } else if (a == 4) {
            if (isdown.equals("0")) {
                pic_show.setImageResource(im14);
            } else {
                StringTokenizer word = new StringTokenizer(imid, ",");
                String word1 = word.nextToken();
                String word2 = word.nextToken();
                String word3 = word.nextToken();
                String word4 = word.nextToken();
                String fullPath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/Nithra/solliadi/";
                Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word4 + "");
                Resources res2 = getResources();
                BitmapDrawable bd2 = new BitmapDrawable(res2, bitimg2);
                pic_show.setImageDrawable(bd2);
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

    public void game_start_odd_man_out () {


        gameid = 5;

        setContentView(R.layout.odd_man_out_multiplayer);
        switchToScreen(R.layout.odd_man_out_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        clickzoom = AnimationUtils.loadAnimation(this, R.anim.click_zoom);
        clickzoom2 = AnimationUtils.loadAnimation(this, R.anim.click_zoom2);
        o_bt1 = (TextView) findViewById(R.id.bt1);
        o_bt2 = (TextView) findViewById(R.id.bt2);
        o_bt3 = (TextView) findViewById(R.id.bt3);
        o_bt4 = (TextView) findViewById(R.id.bt4);
        o_bt5 = (TextView) findViewById(R.id.bt5);
        o_bt6 = (TextView) findViewById(R.id.bt6);
        o_sixcat = (RelativeLayout) findViewById(R.id.sixcat);
        o_fourcat = (RelativeLayout) findViewById(R.id.fourcat);
        o_bts1 = (TextView) findViewById(R.id.bts1);
        o_bts2 = (TextView) findViewById(R.id.bts2);
        o_bts3 = (TextView) findViewById(R.id.bts3);
        o_bts4 = (TextView) findViewById(R.id.bts4);
        o_adds = (LinearLayout) findViewById(R.id.ads_lay);
        o_s_word_number = (TextView) findViewById(R.id.s_word_number);
        o_p_coins = (TextView) findViewById(R.id.p_coins);
        o_head = (RelativeLayout) findViewById(R.id.head);
        o_to_no = (TextView) findViewById(R.id.s_word_number);
        settings = (TextView) findViewById(R.id.settings);
        o_below = (RelativeLayout) findViewById(R.id.below);
        o_bottom = (RelativeLayout) findViewById(R.id.bottom);
        o_qwt = (LinearLayout) findViewById(R.id.qwt);
        o_helpshare_layout = (LinearLayout) findViewById(R.id.helpshare_layout);
        o_discription = (TextView) findViewById(R.id.discription);
        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);
        your_count = (TextView) findViewById(R.id.your_count);
        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Solli_adi_multiplayer.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Solli_adi_multiplayer.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Solli_adi_multiplayer.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Solli_adi_multiplayer.this, R.raw.coins, 1);
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        // New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, o_adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                /* if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
                adView.setAdUnitId(getString(R.string.main_banner_ori));

                adView.setAdSize(AdSize.SMART_BANNER);
                AdRequest request = new AdRequest.Builder().build();
                adView.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        System.out.println("@@@loaded");
                        o_adds.removeAllViews();
                        o_adds.addView(adView);
                        o_adds.setVisibility(View.VISIBLE);
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
*/


        }
        sound();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        o_bt1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    o_bt1.startAnimation(clickzoom);
                    String ts = o_bt1.getText().toString();
                    verify(ts, "bt1");
                }

                return true;

            }
        });
        o_bt2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt2.startAnimation(clickzoom);
                    String ts = o_bt2.getText().toString();
                    verify(ts, "bt2");


                }

                return true;

            }
        });
        o_bt3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt3.startAnimation(clickzoom);
                    String ts = o_bt3.getText().toString();
                    verify(ts, "bt3");


                }

                return true;

            }
        });
        o_bt4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt4.startAnimation(clickzoom);
                    String ts = o_bt4.getText().toString();
                    verify(ts, "bt4");


                }

                return true;

            }
        });
        o_bt5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt5.startAnimation(clickzoom);
                    String ts = o_bt5.getText().toString();
                    verify(ts, "bt5");

                }

                return true;

            }
        });
        o_bt6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt6.startAnimation(clickzoom);
                    String ts = o_bt6.getText().toString();
                    verify(ts, "bt6");


                }

                return true;

            }
        });
        o_bts1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bts1.startAnimation(clickzoom);
                    String ts = o_bts1.getText().toString();
                    verify(ts, "bts1");

                }

                return true;

            }
        });

        o_bts2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bts2.startAnimation(clickzoom);
                    String ts = o_bts2.getText().toString();
                    verify(ts, "bts2");

                }

                return true;

            }
        });

        o_bts3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bts3.startAnimation(clickzoom);
                    String ts = o_bts3.getText().toString();
                    verify(ts, "bts3");


                }

                return true;

            }
        });

        o_bts4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bts4.startAnimation(clickzoom);
                    String ts = o_bts4.getText().toString();
                    verify(ts, "bts4");


                }

                return true;

            }
        });

        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);
       /* Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);*/

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        next_odd_man_out();

    }

    public void verify (String ans, String button){

        Cursor cs = newhelper.getQry("select * from newmaintable where answer LIKE'" + ans + "'and rtm='0'and questionid=" + o_questionid + " and gameid=" + gameid + "");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            changecolour_r(button);
            newhelper.executeSql("UPDATE newmaintable SET rtm=1 WHERE questionid='" + letterid + "'and gameid='" + gameid + "'");
            st_game = st_game + 1;
            score_s = score_s + 1;
            your_m_count.setText("" + st_game);
            your_count.setText("" + score_s);
            if (st_game == tt_game) {
                if (play_after == 0) {
                    play_after = 1;
                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (opp_comp_count < score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                            } else if (opp_comp_count > score_s) {
                               /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx - 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/
                                finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");
                            } else if (opp_comp_count == score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 100;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(100, "ஆட்டம் சமமாக முடிந்தது.இருவரும் சமமான புள்ளிகளை எடுத்துஉள்ளிர்கள்.");
                            }

                                                /*    int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                        }
                    }, 2000);
                }

                mMultiplayer = true;
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    gameactive_you = 1;
                    broadcastScore(false);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }


            } else {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    //gameactive_you = 1;
                    broadcastScore(true);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterid = letterid + 1;
                        next_odd_man_out();
                    }
                }, 2000);
            }
            //setSc();

        } else {
            newhelper.executeSql("UPDATE newmaintable SET rtm=1 WHERE questionid='" + letterid + "'and gameid='" + gameid + "'");
            st_game = st_game + 1;
            changecolour_w(button);
            your_m_count.setText("" + st_game);
            if (st_game == tt_game) {
                if (play_after == 0) {
                    play_after = 1;
                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (opp_comp_count < score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                            } else if (opp_comp_count > score_s) {
                               /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx - 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/
                                finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");
                            } else if (opp_comp_count == score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 100;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(100, "ஆட்டம் சமமாக முடிந்தது.இருவரும் சமமான புள்ளிகளை எடுத்துஉள்ளிர்கள்.");
                            }
                        }
                    }, 2000);
                }

                mMultiplayer = true;
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    gameactive_you = 1;
                    broadcastScore(false);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }
            } else {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    //gameactive_you = 1;
                    broadcastScore(true);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterid = letterid + 1;
                        next_odd_man_out();
                    }
                }, 2000);
            }
        }
    }

    public void verify_oppo (String ans, String button){

        Cursor cs = newhelper2.getQry("select * from newmaintable2 where answer LIKE'" + ans + "'and rtm='0'and questionid=" + o_questionid + " and gameid=" + gameid + "");
        cs.moveToFirst();
        if (cs.getCount() != 0) {
            changecolour_r(button);
            newhelper2.executeSql("UPDATE newmaintable2 SET rtm=1 WHERE questionid='" + letterid + "'and gameid='" + gameid + "'");
            st_game = st_game + 1;
            score_s = score_s + 1;
            your_m_count.setText("" + st_game);
            your_count.setText("" + score_s);
            if (st_game == tt_game) {
                if (play_after == 0) {
                    play_after = 1;
                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (opp_comp_count < score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                            } else if (opp_comp_count > score_s) {
                               /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx - 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/
                                finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");
                            } else if (opp_comp_count == score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 100;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(100, "ஆட்டம் சமமாக முடிந்தது.இருவரும் சமமான புள்ளிகளை எடுத்துஉள்ளிர்கள்.");
                            }

                                                /*    int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                        }
                    }, 2000);
                }

                mMultiplayer = true;
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    gameactive_you = 1;
                    broadcastScore(false);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }


            } else {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    //gameactive_you = 1;
                    broadcastScore(true);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterid = letterid + 1;
                        next_start_oppo_word();
                    }
                }, 2000);
            }
            //setSc();

        } else {
            newhelper2.executeSql("UPDATE newmaintable2 SET rtm=1 WHERE questionid='" + letterid + "'and gameid='" + gameid + "'");
            st_game = st_game + 1;
            changecolour_w(button);
            your_m_count.setText("" + st_game);
            if (st_game == tt_game) {
                if (play_after == 0) {
                    play_after = 1;
                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (opp_comp_count < score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                            } else if (opp_comp_count > score_s) {
                               /* Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx - 200;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");*/
                                finalscreen(0, "ஆட்டம் முடிந்தது.உங்கள் எதிராளி வெற்றிபெற்றுவிட்டார்.");
                            } else if (opp_comp_count == score_s) {
                                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                cfx.moveToFirst();
                                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                int spx = skx + 100;
                                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                finalscreen(100, "ஆட்டம் சமமாக முடிந்தது.இருவரும் சமமான புள்ளிகளை எடுத்துஉள்ளிர்கள்.");
                            }
                        }
                    }, 2000);
                }

                mMultiplayer = true;
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    gameactive_you = 1;
                    broadcastScore(false);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }
            } else {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    //gameactive_you = 1;
                    broadcastScore(true);
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    exitgame();
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterid = letterid + 1;
                        next_start_oppo_word();
                    }
                }, 2000);
            }
        }
    }

    private void next_odd_man_out () {

        o_bts1.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bts2.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bts3.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bts4.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bt1.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bt2.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bt3.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bt4.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bt5.setBackgroundResource(R.drawable.curve_for_odd_man_out);
        o_bt6.setBackgroundResource(R.drawable.curve_for_odd_man_out);

        o_bts1.setEnabled(true);
        o_bts2.setEnabled(true);
        o_bts3.setEnabled(true);
        o_bts4.setEnabled(true);
        o_bt1.setEnabled(true);
        o_bt2.setEnabled(true);
        o_bt3.setEnabled(true);
        o_bt4.setEnabled(true);
        o_bt5.setEnabled(true);
        o_bt6.setEnabled(true);


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


        /*Cursor c2 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "'and questionid='" + letterid + "'");
        c2.moveToFirst();
        int count1 = c2.getCount() + 1;
        String no = String.valueOf(count1);*/


        Cursor c1 = null;
        c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "'and questionid='" + letterid + "'");
        c1.moveToFirst();
        if (c1.getCount() != 0) {
            u_id = c1.getInt(c1.getColumnIndexOrThrow("id"));
            o_questionid = c1.getInt(c1.getColumnIndexOrThrow("questionid"));
            o_question = c1.getString(c1.getColumnIndexOrThrow("question"));
            o_answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
            int clue = c1.getInt(c1.getColumnIndexOrThrow("clue"));
            int playtime = c1.getInt(c1.getColumnIndexOrThrow("playtime"));
            String tfoption = o_question;
            String[] first = tfoption.split(",");
            int letter_type = first.length;
            // Toast.makeText(Odd_man_out.this, ""+clue, Toast.LENGTH_SHORT).show();


            if (letter_type == 1) {
                o_bt1.setVisibility(View.VISIBLE);
                o_bt1.setText(o_question);

            } else if (letter_type == 2) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
            } else if (letter_type == 3) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt3.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
                o_bt3.setText(letters3);
            } else if (letter_type == 4) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                o_sixcat.setVisibility(View.GONE);
                o_fourcat.setVisibility(View.VISIBLE);
                o_bts1.setVisibility(View.VISIBLE);
                o_bts2.setVisibility(View.VISIBLE);
                o_bts3.setVisibility(View.VISIBLE);
                o_bts4.setVisibility(View.VISIBLE);
                o_bts1.setText(letters1);
                o_bts2.setText(letters2);
                o_bts3.setText(letters3);
                o_bts4.setText(letters4);
                o_q_type = 4;
                if (letters1.equals(o_answer)) {
                    o_ans_position = 1;
                } else if (letters2.equals(o_answer)) {
                    o_ans_position = 2;
                } else if (letters3.equals(o_answer)) {
                    o_ans_position = 3;
                } else if (letters4.equals(o_answer)) {
                    o_ans_position = 4;
                }
            } else if (letter_type == 5) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt3.setVisibility(View.VISIBLE);
                o_bt4.setVisibility(View.VISIBLE);
                o_bt5.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
                o_bt3.setText(letters3);
                o_bt4.setText(letters4);
                o_bt5.setText(letters5);
            } else if (letter_type == 6) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                final String letters6 = tokenizerw.nextToken().trim();
                o_sixcat.setVisibility(View.VISIBLE);
                o_fourcat.setVisibility(View.GONE);
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt3.setVisibility(View.VISIBLE);
                o_bt4.setVisibility(View.VISIBLE);
                o_bt5.setVisibility(View.VISIBLE);
                o_bt6.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
                o_bt3.setText(letters3);
                o_bt4.setText(letters4);
                o_bt5.setText(letters5);
                o_bt6.setText(letters6);
                o_q_type = 6;
                if (letters1.equals(o_answer)) {
                    o_ans_position = 1;
                } else if (letters2.equals(o_answer)) {
                    o_ans_position = 2;
                } else if (letters3.equals(o_answer)) {
                    o_ans_position = 3;
                } else if (letters4.equals(o_answer)) {
                    o_ans_position = 4;
                } else if (letters5.equals(o_answer)) {
                    o_ans_position = 5;
                } else if (letters6.equals(o_answer)) {
                    o_ans_position = 6;
                }
            }

        }

    }

    private void changecolour_w (String button){

        if (button.equals("bts1")) {
            o_bts1.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts2")) {
            o_bts2.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts3")) {
            o_bts3.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bts4")) {
            o_bts4.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt1")) {
            o_bt1.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt2")) {
            o_bt2.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt3")) {
            o_bt3.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt4")) {
            o_bt4.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt5")) {
            o_bt5.setBackgroundResource(R.color.worngans);
        } else if (button.equals("bt6")) {
            o_bt6.setBackgroundResource(R.color.worngans);
        }
        o_bts1.setEnabled(false);
        o_bts2.setEnabled(false);
        o_bts3.setEnabled(false);
        o_bts4.setEnabled(false);
        o_bt1.setEnabled(false);
        o_bt2.setEnabled(false);
        o_bt3.setEnabled(false);
        o_bt4.setEnabled(false);
        o_bt5.setEnabled(false);
        o_bt6.setEnabled(false);
    }

    private void changecolour_r (String button){

        if (button.equals("bts1")) {
            o_bts1.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts2")) {
            o_bts2.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts3")) {
            o_bts3.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bts4")) {
            o_bts4.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt1")) {
            o_bt1.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt2")) {
            o_bt2.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt3")) {
            o_bt3.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt4")) {
            o_bt4.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt5")) {
            o_bt5.setBackgroundResource(R.color.rightans);
        } else if (button.equals("bt6")) {
            o_bt6.setBackgroundResource(R.color.rightans);
        }
        o_bts1.setEnabled(false);
        o_bts2.setEnabled(false);
        o_bts3.setEnabled(false);
        o_bts4.setEnabled(false);
        o_bt1.setEnabled(false);
        o_bt2.setEnabled(false);
        o_bt3.setEnabled(false);
        o_bt4.setEnabled(false);
        o_bt5.setEnabled(false);
        o_bt6.setEnabled(false);
    }

    public void game_start_oppo_word () {
        gameid = 7;
        setContentView(R.layout.opposite_word_multiplayer);
        switchToScreen(R.layout.opposite_word_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        clickzoom = AnimationUtils.loadAnimation(this, R.anim.click_zoom);
        clickzoom2 = AnimationUtils.loadAnimation(this, R.anim.click_zoom2);

        o_bt1 = (TextView) findViewById(R.id.bt1);
        o_bt2 = (TextView) findViewById(R.id.bt2);
        o_bt3 = (TextView) findViewById(R.id.bt3);
        o_bt4 = (TextView) findViewById(R.id.bt4);
        o_bt5 = (TextView) findViewById(R.id.bt5);
        o_bt6 = (TextView) findViewById(R.id.bt6);
        o_sixcat = (RelativeLayout) findViewById(R.id.sixcat);
        o_fourcat = (RelativeLayout) findViewById(R.id.fourcat);
        o_bts1 = (TextView) findViewById(R.id.bts1);
        o_bts2 = (TextView) findViewById(R.id.bts2);
        o_bts3 = (TextView) findViewById(R.id.bts3);
        o_bts4 = (TextView) findViewById(R.id.bts4);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        o_s_word_number = (TextView) findViewById(R.id.s_word_number);
        o_p_coins = (TextView) findViewById(R.id.p_coins);
        head = (RelativeLayout) findViewById(R.id.head);
        settings = (TextView) findViewById(R.id.settings);
        o_below = (RelativeLayout) findViewById(R.id.below);
        o_bottom = (RelativeLayout) findViewById(R.id.bottom);
        o_qwt = (LinearLayout) findViewById(R.id.qwt);
        o_question_txt = (TextView) findViewById(R.id.question);
        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);
        your_count = (TextView) findViewById(R.id.your_count);
        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Solli_adi_multiplayer.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Solli_adi_multiplayer.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Solli_adi_multiplayer.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Solli_adi_multiplayer.this, R.raw.coins, 1);
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        // New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, o_adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);

                /*    if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }
        sound();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        o_bt1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt1.startAnimation(clickzoom);
                    String ts = o_bt1.getText().toString();
                    verify_oppo(ts, "bt1");


                }

                return true;

            }
        });
        o_bt2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    o_bt2.startAnimation(clickzoom);
                    String ts = o_bt2.getText().toString();
                    verify_oppo(ts, "bt2");
                }

                return true;

            }
        });
        o_bt3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt3.startAnimation(clickzoom);
                    String ts = o_bt3.getText().toString();
                    verify_oppo(ts, "bt3");

                }

                return true;

            }
        });
        o_bt4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt4.startAnimation(clickzoom);
                    String ts = o_bt4.getText().toString();
                    verify_oppo(ts, "bt4");

                }

                return true;

            }
        });
        o_bt5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bt5.startAnimation(clickzoom);
                    String ts = o_bt5.getText().toString();
                    verify_oppo(ts, "bt5");


                }

                return true;

            }
        });
        o_bt6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    o_bt6.startAnimation(clickzoom);
                    String ts = o_bt6.getText().toString();
                    verify_oppo(ts, "bt6");

                }

                return true;

            }
        });
        o_bts1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bts1.startAnimation(clickzoom);
                    String ts = o_bts1.getText().toString();
                    verify_oppo(ts, "bts1");


                }

                return true;

            }
        });
        o_bts2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bts2.startAnimation(clickzoom);
                    String ts = o_bts2.getText().toString();
                    verify_oppo(ts, "bts2");


                }

                return true;

            }
        });
        o_bts3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    o_bts3.startAnimation(clickzoom);
                    String ts = o_bts3.getText().toString();
                    verify_oppo(ts, "bts3");
                }

                return true;

            }
        });
        o_bts4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    o_bts4.startAnimation(clickzoom);
                    String ts = o_bts4.getText().toString();
                    verify_oppo(ts, "bts4");

                }

                return true;

            }
        });


        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);
       /* Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);*/

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next_start_oppo_word();

    }

    public void next_start_oppo_word () {

        o_bts1.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bts2.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bts3.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bts4.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bt1.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bt2.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bt3.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bt4.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bt5.setBackgroundResource(R.drawable.curve_opposite_word);
        o_bt6.setBackgroundResource(R.drawable.curve_opposite_word);

        o_bts1.setEnabled(true);
        o_bts2.setEnabled(true);
        o_bts3.setEnabled(true);
        o_bts4.setEnabled(true);
        o_bt1.setEnabled(true);
        o_bt2.setEnabled(true);
        o_bt3.setEnabled(true);
        o_bt4.setEnabled(true);
        o_bt5.setEnabled(true);
        o_bt6.setEnabled(true);


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

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^gameid" + gameid + "^^^^^^^^^^^^^^^^letterid" + letterid);
        Cursor c1 = null;

        c1 = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and questionid='" + letterid + "'");

        c1.moveToFirst();
        if (c1.getCount() != 0) {
            u_id = c1.getInt(c1.getColumnIndexOrThrow("id"));
            o_questionid = c1.getInt(c1.getColumnIndexOrThrow("questionid"));
            o_question = c1.getString(c1.getColumnIndexOrThrow("question"));
            o_answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
            int clue = c1.getInt(c1.getColumnIndexOrThrow("clue"));
            String question_tt = c1.getString(c1.getColumnIndexOrThrow("sf_words"));
            int playtime = c1.getInt(c1.getColumnIndexOrThrow("playtime"));
            o_question_txt.setText(question_tt);
            String tfoption = o_question;
            String[] first = tfoption.split(",");
            int letter_type = first.length;

            if (letter_type == 1) {
                o_bt1.setVisibility(View.VISIBLE);
                o_bt1.setText(o_question);
            } else if (letter_type == 2) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
            } else if (letter_type == 3) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt3.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
                o_bt3.setText(letters3);
            } else if (letter_type == 4) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                o_sixcat.setVisibility(View.GONE);
                o_fourcat.setVisibility(View.VISIBLE);
                o_bts1.setVisibility(View.VISIBLE);
                o_bts2.setVisibility(View.VISIBLE);
                o_bts3.setVisibility(View.VISIBLE);
                o_bts4.setVisibility(View.VISIBLE);
                o_bts1.setText(letters1);
                o_bts2.setText(letters2);
                o_bts3.setText(letters3);
                o_bts4.setText(letters4);
                o_q_type = 4;
                if (letters1.equals(o_answer)) {
                    o_ans_position = 1;
                } else if (letters2.equals(o_answer)) {
                    o_ans_position = 2;
                } else if (letters3.equals(o_answer)) {
                    o_ans_position = 3;
                } else if (letters4.equals(o_answer)) {
                    o_ans_position = 4;
                }
            } else if (letter_type == 5) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt3.setVisibility(View.VISIBLE);
                o_bt4.setVisibility(View.VISIBLE);
                o_bt5.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
                o_bt3.setText(letters3);
                o_bt4.setText(letters4);
                o_bt5.setText(letters5);
            } else if (letter_type == 6) {
                StringTokenizer tokenizerw = new StringTokenizer(o_question, ",");
                final String letters1 = tokenizerw.nextToken().trim();
                final String letters2 = tokenizerw.nextToken().trim();
                final String letters3 = tokenizerw.nextToken().trim();
                final String letters4 = tokenizerw.nextToken().trim();
                final String letters5 = tokenizerw.nextToken().trim();
                final String letters6 = tokenizerw.nextToken().trim();
                o_sixcat.setVisibility(View.VISIBLE);
                o_fourcat.setVisibility(View.GONE);
                o_bt1.setVisibility(View.VISIBLE);
                o_bt2.setVisibility(View.VISIBLE);
                o_bt3.setVisibility(View.VISIBLE);
                o_bt4.setVisibility(View.VISIBLE);
                o_bt5.setVisibility(View.VISIBLE);
                o_bt6.setVisibility(View.VISIBLE);
                o_bt1.setText(letters1);
                o_bt2.setText(letters2);
                o_bt3.setText(letters3);
                o_bt4.setText(letters4);
                o_bt5.setText(letters5);
                o_bt6.setText(letters6);
                o_q_type = 6;
                if (letters1.equals(o_answer)) {
                    o_ans_position = 1;
                } else if (letters2.equals(o_answer)) {
                    o_ans_position = 2;
                } else if (letters3.equals(o_answer)) {
                    o_ans_position = 3;
                } else if (letters4.equals(o_answer)) {
                    o_ans_position = 4;
                } else if (letters5.equals(o_answer)) {
                    o_ans_position = 5;
                } else if (letters6.equals(o_answer)) {
                    o_ans_position = 6;
                }
            }
        }
    }

    public void game_start_right_order () {
        gameid = 9;
        setContentView(R.layout.makeword__rightorder_multiplayer);
        switchToScreen(R.layout.makeword__rightorder_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        m_question_txt = (TextView) findViewById(R.id.question_txt);
        c_clear = (TextView) findViewById(R.id.clue_clear);
        c_ans = (TextView) findViewById(R.id.c_ans);
        ans_high = (TextView) findViewById(R.id.ans_highlite);

        m_bt1 = (TextView) findViewById(R.id.c_button1);
        m_bt2 = (TextView) findViewById(R.id.c_button2);
        m_bt3 = (TextView) findViewById(R.id.c_button3);
        m_bt4 = (TextView) findViewById(R.id.c_button4);
        m_bt5 = (TextView) findViewById(R.id.c_button5);
        m_bt6 = (TextView) findViewById(R.id.c_button6);
        m_bt7 = (TextView) findViewById(R.id.c_button7);
        m_bt8 = (TextView) findViewById(R.id.c_button8);
        m_bt9 = (TextView) findViewById(R.id.c_button9);
        m_bt10 = (TextView) findViewById(R.id.c_button10);
        m_bt11 = (TextView) findViewById(R.id.c_button11);
        m_bt12 = (TextView) findViewById(R.id.c_button12);
        m_bt13 = (TextView) findViewById(R.id.c_button13);
        m_bt14 = (TextView) findViewById(R.id.c_button14);
        m_bt15 = (TextView) findViewById(R.id.c_button15);
        m_bt16 = (TextView) findViewById(R.id.c_button16);
        m_bt16 = (TextView) findViewById(R.id.c_button16);

        settings = (TextView) findViewById(R.id.settings);
        m_edit = (EditText) findViewById(R.id.clue_ans_editer);
        adds = (LinearLayout) findViewById(R.id.ads_lay);

        list4 = (LinearLayout) findViewById(R.id.list4);
        m_clear = (TextView) findViewById(R.id.clue_clear);

        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        //  New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            native_banner_ad_container.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);

                /* if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }


        m_bt1.setText("");
        m_bt2.setText("");
        m_bt3.setText("");
        m_bt5.setText("");
        m_bt6.setText("");
        m_bt7.setText("");
        m_bt9.setText("");
        m_bt10.setText("");
        m_bt11.setText("");
        m_bt4.setText("");
        m_bt8.setText("");
        m_bt12.setText("");
        m_bt13.setText("");
        m_bt14.setText("");
        m_bt15.setText("");
        m_bt16.setText("");
        m_edit.setText("");

        m_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                pressKey_r(KeyEvent.KEYCODE_DEL);
            }
        });

        m_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                m_edit.setText("");
                return false;
            }
        });


        m_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(m_edit.getWindowToken(), 0);
            }
        });
        m_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(m_edit.getWindowToken(), 0);

                return true;
            }
        });
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Solli_adi_multiplayer.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Solli_adi_multiplayer.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Solli_adi_multiplayer.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Solli_adi_multiplayer.this, R.raw.coins, 1);


        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);
       /* Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);*/

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        m_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt1.startAnimation(shake);
                String ts = m_bt1.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt2.startAnimation(shake);
                String ts = m_bt2.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt3.startAnimation(shake);
                String ts = m_bt3.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt5.startAnimation(shake);
                String ts = m_bt5.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt6.startAnimation(shake);
                String ts = m_bt6.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt7.startAnimation(shake);
                String ts = m_bt7.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt9.startAnimation(shake);
                String ts = m_bt9.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt10.startAnimation(shake);
                String ts = m_bt10.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt11.startAnimation(shake);
                String ts = m_bt11.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt4.startAnimation(shake);
                String ts = m_bt4.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt8.startAnimation(shake);
                String ts = m_bt8.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt12.startAnimation(shake);
                String ts = m_bt12.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt13.startAnimation(shake);
                String ts = m_bt13.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt14.startAnimation(shake);
                String ts = m_bt14.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt15.startAnimation(shake);
                String ts = m_bt15.getText().toString();
                m_edit.append(ts);
            }
        });
        m_bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                m_bt16.startAnimation(shake);
                String ts = m_bt16.getText().toString();
                m_edit.append(ts);
            }
        });

        sound();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });


        m_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = m_edit.getText().toString();
                Log.e("to value", answer);
                System.out.println("==========value" + answer);
                Cursor cd = newhelper3.getQry("SELECT * FROM right_order where answer ='" + answer + "' and rtm='0' and questionid='" + letterid + "' and gameid='" + gameid + "'");
                System.out.println("============results" + "SELECT * FROM right_order where answer ='" + answer + "' and rtm='0' and questionid='" + letterid + "' and gameid='" + gameid + "'");
                cd.moveToFirst();

                if (cd.getCount() != 0) {
                    // spz3.play(soundId3, sv, sv, 0, 0, sv);
                    //Toast.makeText(Clue_Game_Hard.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    c_ans.setEnabled(false);
                    list4.setVisibility(View.INVISIBLE);
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(answer);
                    newhelper3.executeSql("UPDATE right_order SET rtm=1 WHERE questionid='" + letterid + "' and gameid='" + gameid + "'");


                    st_game = st_game + 1;
                    your_m_count.setText("" + st_game);
                    if (st_game == tt_game) {


                        if (play_after == 0) {
                            play_after = 1;
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");

                                                /*    int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                                }
                            }, 2000);
                        }

                        mMultiplayer = true;
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            gameactive_you = 1;
                            broadcastScore(false);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }


                    } else {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            //gameactive_you = 1;
                            broadcastScore(true);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letterid = letterid + 1;
                                nextgame_rightorder();
                            }
                        }, 2000);
                    }
                }
            }
        });
        nextgame_rightorder();
    }

    public void nextgame_rightorder () {
        m_bt1.setText("");
        m_bt2.setText("");
        m_bt3.setText("");
        m_bt5.setText("");
        m_bt6.setText("");
        m_bt7.setText("");
        m_bt9.setText("");
        m_bt10.setText("");
        m_bt11.setText("");
        m_bt4.setText("");
        m_bt8.setText("");
        m_bt12.setText("");
        m_bt13.setText("");
        m_bt14.setText("");
        m_bt15.setText("");
        m_bt16.setText("");
        m_edit.setText("");
        m_edit.setVisibility(View.VISIBLE);
        ans_high.setVisibility(View.GONE);
        list4.setVisibility(View.VISIBLE);

        Cursor c;
        c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and questionid='" + letterid + "' and rtm='0'");
        c.moveToFirst();


        if (c.getCount() != 0) {
            m_questionid = c.getString(c.getColumnIndexOrThrow("questionid"));
            m_question = c.getString(c.getColumnIndexOrThrow("question"));
            m_answer = c.getString(c.getColumnIndexOrThrow("answer"));
            m_split_word = c.getString(c.getColumnIndexOrThrow("splitword"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));

            m_question_txt.setText(m_question);
            ans_high.setText(m_answer);


            System.out.println("##############split_waord" + m_split_word);
            System.out.println("##############question" + m_question);
            System.out.println("##############questionid" + m_questionid);
            System.out.println("##############answer" + m_answer);

            String tfoption = m_split_word;
            String[] first = tfoption.split(",");
            m_type = first.length;

            //  Toast.makeText(context, "!!!!!!!!!!randomno"+randomno, Toast.LENGTH_SHORT).show();
            int minmum = 1;
            int maximum = 3;
            Random rn = new Random();
            int randomno = rn.nextInt(maximum - minmum + 1) + minmum;

            if (randomno == 1) {
                m_setgame();
            } else if (randomno == 2) {
                m_medium();
            } else if (randomno == 3) {
                m_medium1();
            }

        }

    }

    public void m_setgame () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        m_bt13.setVisibility(View.GONE);
        m_bt14.setVisibility(View.GONE);
        m_bt15.setVisibility(View.GONE);
        m_bt16.setVisibility(View.GONE);
        if (m_type == 1) {

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
            m_bt1.setText(letter1);
            m_bt2.setText(letter2);
            m_bt3.setText(m_split_word);
            m_bt4.setText(letter11);
            m_bt5.setText(letter5);
            m_bt6.setText(letter6);
            m_bt7.setText(letter7);
            m_bt8.setText(letter3);
            m_bt9.setText(letter9);
            m_bt10.setText(letter15);
            m_bt11.setText(letter4);
            m_bt12.setText(letter12);


        } else if (m_type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter5);
            m_bt2.setText(letter2);
            m_bt3.setText(word1);
            m_bt4.setText(letter4);
            m_bt5.setText(letter1);
            m_bt6.setText(letter6);
            m_bt7.setText(letter11);
            m_bt8.setText(letter3);
            m_bt9.setText(letter9);
            m_bt10.setText(letter13);
            m_bt11.setText(letter7);
            m_bt12.setText(word2);
                /*m_bt13.setText(letter14);
                m_bt14.setText(word2);
                m_bt15.setText(letter10);
                m_bt16.setText(letter8);*/


        } else if (m_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word2);
            m_bt2.setText(letter15);
            m_bt3.setText(word3);
            m_bt4.setText(letter4);
            m_bt5.setText(word1);
            m_bt6.setText(letter9);
            m_bt7.setText(letter7);
            m_bt8.setText(letter3);
            m_bt9.setText(letter6);
            m_bt10.setText(word2);
            m_bt11.setText(letter2);
            m_bt12.setText(letter1);
               /* m_bt13.setText(letter14);
                m_bt14.setText(letter12);
                m_bt15.setText(letter10);
                m_bt16.setText(letter13);*/


        } else if (m_type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter13);
            m_bt2.setText(letter10);
            m_bt3.setText(word3);
            m_bt4.setText(letter4);
            m_bt5.setText(letter6);
            m_bt6.setText(word1);
            m_bt7.setText(letter7);
            m_bt8.setText(letter3);
            m_bt9.setText(letter10);
            m_bt10.setText(word4);
            m_bt11.setText(letter12);
            m_bt12.setText(word2);
              /*  m_bt13.setText(letter14);
                m_bt14.setText(letter15);
                m_bt15.setText(word1);
                m_bt16.setText(word2);*/


        } else if (m_type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter7);
            m_bt2.setText(word5);
            m_bt3.setText(word3);
            m_bt4.setText(letter4);
            m_bt5.setText(letter13);
            m_bt6.setText(word4);
            m_bt7.setText(letter3);
            m_bt8.setText(letter3);
            m_bt9.setText(word1);
            m_bt10.setText(letter6);
            m_bt11.setText(letter8);
            m_bt12.setText(word2);
               /* m_bt13.setText(letter14);
                m_bt14.setText(letter12);
                m_bt15.setText(letter9);
                m_bt16.setText(letter10);*/


        } else if (m_type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word2);
            m_bt2.setText(letter9);
            m_bt3.setText(letter4);
            m_bt4.setText(word5);
            m_bt5.setText(letter6);
            m_bt6.setText(letter1);
            m_bt7.setText(word4);
            m_bt8.setText(word6);
            m_bt9.setText(letter8);
            m_bt10.setText(word1);
            m_bt11.setText(word3);
            m_bt12.setText(letter4);
              /*  m_bt13.setText(word6);
                m_bt14.setText(letter11);
                m_bt15.setText(letter13);
                m_bt16.setText(word2);
*/


        } else if (m_type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter8);
            m_bt2.setText(letter4);
            m_bt3.setText(word5);
            m_bt4.setText(word2);
            m_bt5.setText(word7);
            m_bt6.setText(word3);
            m_bt7.setText(word4);
            m_bt8.setText(letter11);
            m_bt9.setText(letter10);
            m_bt10.setText(word1);
            m_bt11.setText(letter12);
            m_bt12.setText(word6);
               /* m_bt13.setText(letter6);
                m_bt14.setText(word1);
                m_bt15.setText(letter13);
                m_bt16.setText(word3);*/


        } else if (m_type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word6);
            m_bt2.setText(letter6);
            m_bt3.setText(word8);
            m_bt4.setText(letter3);
            m_bt5.setText(word5);
            m_bt6.setText(word4);
            m_bt7.setText(letter7);
            m_bt8.setText(word7);
            m_bt9.setText(word1);
            m_bt10.setText(word3);
            m_bt11.setText(word2);
            m_bt12.setText(letter8);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(letter10);*/

        } else if (m_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word6);
            m_bt2.setText(word1);
            m_bt3.setText(word8);
            m_bt4.setText(letter3);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(letter6);
            m_bt8.setText(word4);
            m_bt9.setText(word2);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word7);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        }
    }

    public void m_medium () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        m_bt13.setVisibility(View.GONE);
        m_bt14.setVisibility(View.GONE);
        m_bt15.setVisibility(View.GONE);
        m_bt16.setVisibility(View.GONE);
        if (m_type == 1) {

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
            m_bt1.setText(letter1);
            m_bt2.setText(letter2);
            m_bt3.setText(m_split_word);
            m_bt4.setText(letter11);
            m_bt5.setText(letter5);
            m_bt6.setText(letter6);
            m_bt7.setText(letter7);
            m_bt8.setText(letter3);
            m_bt9.setText(letter9);
            m_bt10.setText(letter15);
            m_bt11.setText(letter4);
            m_bt12.setText(letter12);
              /*  m_bt13.setText(letter10);
                m_bt14.setText(letter14);
                m_bt15.setText(letter12);
                m_bt16.setText(letter13);*/


        } else if (m_type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter5);
            m_bt2.setText(letter2);
            m_bt3.setText(word1);
            m_bt4.setText(letter4);
            m_bt5.setText(letter1);
            m_bt6.setText(letter6);
            m_bt7.setText(letter11);
            m_bt8.setText(letter3);
            m_bt9.setText(letter9);
            m_bt10.setText(letter13);
            m_bt11.setText(letter7);
            m_bt12.setText(word2);
                /*m_bt13.setText(letter14);
                m_bt14.setText(word2);
                m_bt15.setText(letter10);
                m_bt16.setText(letter8);*/


        } else if (m_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word2);
            m_bt2.setText(letter15);
            m_bt3.setText(word3);
            m_bt4.setText(letter4);
            m_bt5.setText(word1);
            m_bt6.setText(letter9);
            m_bt7.setText(letter7);
            m_bt8.setText(letter3);
            m_bt9.setText(letter6);
            m_bt10.setText(word2);
            m_bt11.setText(letter2);
            m_bt12.setText(letter1);
               /* m_bt13.setText(letter14);
                m_bt14.setText(letter12);
                m_bt15.setText(letter10);
                m_bt16.setText(letter13);*/


        } else if (m_type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter13);
            m_bt2.setText(letter10);
            m_bt3.setText(word3);
            m_bt4.setText(letter4);
            m_bt5.setText(letter6);
            m_bt6.setText(word1);
            m_bt7.setText(letter7);
            m_bt8.setText(letter3);
            m_bt9.setText(letter10);
            m_bt10.setText(word4);
            m_bt11.setText(letter12);
            m_bt12.setText(word2);
              /*  m_bt13.setText(letter14);
                m_bt14.setText(letter15);
                m_bt15.setText(word1);
                m_bt16.setText(word2);*/


        } else if (m_type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter7);
            m_bt2.setText(word5);
            m_bt3.setText(word3);
            m_bt4.setText(letter4);
            m_bt5.setText(letter13);
            m_bt6.setText(word4);
            m_bt7.setText(letter3);
            m_bt8.setText(letter3);
            m_bt9.setText(word1);
            m_bt10.setText(letter6);
            m_bt11.setText(letter8);
            m_bt12.setText(word2);
               /* m_bt13.setText(letter14);
                m_bt14.setText(letter12);
                m_bt15.setText(letter9);
                m_bt16.setText(letter10);*/


        } else if (m_type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word2);
            m_bt2.setText(letter9);
            m_bt3.setText(letter4);
            m_bt4.setText(word5);
            m_bt5.setText(letter6);
            m_bt6.setText(letter1);
            m_bt7.setText(word4);
            m_bt8.setText(word6);
            m_bt9.setText(letter8);
            m_bt10.setText(word1);
            m_bt11.setText(word3);
            m_bt12.setText(letter4);
              /*  m_bt13.setText(word6);
                m_bt14.setText(letter11);
                m_bt15.setText(letter13);
                m_bt16.setText(word2);
*/


        } else if (m_type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter8);
            m_bt2.setText(letter4);
            m_bt3.setText(word5);
            m_bt4.setText(word2);
            m_bt5.setText(word7);
            m_bt6.setText(word3);
            m_bt7.setText(word4);
            m_bt8.setText(letter11);
            m_bt9.setText(letter10);
            m_bt10.setText(word1);
            m_bt11.setText(letter12);
            m_bt12.setText(word6);
               /* m_bt13.setText(letter6);
                m_bt14.setText(word1);
                m_bt15.setText(letter13);
                m_bt16.setText(word3);*/


        } else if (m_type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word6);
            m_bt2.setText(letter6);
            m_bt3.setText(word8);
            m_bt4.setText(letter3);
            m_bt5.setText(word5);
            m_bt6.setText(word4);
            m_bt7.setText(letter7);
            m_bt8.setText(word7);
            m_bt9.setText(word1);
            m_bt10.setText(word3);
            m_bt11.setText(word2);
            m_bt12.setText(letter8);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(letter10);*/

        } else if (m_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word6);
            m_bt2.setText(word1);
            m_bt3.setText(word8);
            m_bt4.setText(letter3);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(letter6);
            m_bt8.setText(word4);
            m_bt9.setText(word2);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word7);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        } else if (m_type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            m_bt1.setText(word6);
            m_bt2.setText(word1);
            m_bt3.setText(word8);
            m_bt4.setText(letter3);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(word10);
            m_bt8.setText(word4);
            m_bt9.setText(word2);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word7);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        } else if (m_type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            m_bt1.setText(word6);
            m_bt2.setText(word1);
            m_bt3.setText(word8);
            m_bt4.setText(word11);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(word10);
            m_bt8.setText(word4);
            m_bt9.setText(word2);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word7);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        } else if (m_type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            m_bt1.setText(word6);
            m_bt2.setText(word1);
            m_bt3.setText(word12);
            m_bt4.setText(word11);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(word10);
            m_bt8.setText(word4);
            m_bt9.setText(word2);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word7);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        }


    }

    public void m_medium1 () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        m_bt13.setVisibility(View.GONE);
        m_bt14.setVisibility(View.GONE);
        m_bt15.setVisibility(View.GONE);
        m_bt16.setVisibility(View.GONE);
        if (m_type == 1) {

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
            m_bt1.setText(letter1);
            m_bt2.setText(letter2);
            m_bt3.setText(m_split_word);
            m_bt4.setText(letter11);
            m_bt5.setText(letter5);
            m_bt6.setText(letter6);
            m_bt7.setText(letter7);
            m_bt8.setText(letter3);
            m_bt9.setText(letter9);
            m_bt10.setText(letter15);
            m_bt11.setText(letter4);
            m_bt12.setText(letter12);
              /*  m_bt13.setText(letter10);
                m_bt14.setText(letter14);
                m_bt15.setText(letter12);
                m_bt16.setText(letter13);*/


        } else if (m_type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter5);
            m_bt2.setText(letter2);
            m_bt3.setText(letter6);
            m_bt4.setText(letter4);
            m_bt5.setText(letter1);
            m_bt6.setText(word1);
            m_bt7.setText(letter11);
            m_bt8.setText(letter3);
            m_bt9.setText(letter9);
            m_bt10.setText(word2);
            m_bt11.setText(letter7);
            m_bt12.setText(letter13);
                /*m_bt13.setText(letter14);
                m_bt14.setText(word2);
                m_bt15.setText(letter10);
                m_bt16.setText(letter8);*/


        } else if (m_type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter7);
            m_bt2.setText(letter15);
            m_bt3.setText(letter1);
            m_bt4.setText(letter4);
            m_bt5.setText(word3);
            m_bt6.setText(letter9);
            m_bt7.setText(word2);
            m_bt8.setText(letter3);
            m_bt9.setText(letter6);
            m_bt10.setText(word1);
            m_bt11.setText(word2);
            m_bt12.setText(letter2);
               /* m_bt13.setText(letter14);
                m_bt14.setText(letter12);
                m_bt15.setText(letter10);
                m_bt16.setText(letter13);*/


        } else if (m_type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter13);
            m_bt2.setText(word3);
            m_bt3.setText(letter10);
            m_bt4.setText(letter4);
            m_bt5.setText(letter6);
            m_bt6.setText(letter7);
            m_bt7.setText(letter10);
            m_bt8.setText(letter3);
            m_bt9.setText(word1);
            m_bt10.setText(word4);
            m_bt11.setText(letter12);
            m_bt12.setText(word2);



              /*  m_bt13.setText(letter14);
                m_bt14.setText(letter15);
                m_bt15.setText(word1);
                m_bt16.setText(word2);*/


        } else if (m_type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter7);
            m_bt2.setText(letter4);
            m_bt3.setText(letter13);
            m_bt4.setText(word5);
            m_bt5.setText(word3);
            m_bt6.setText(letter3);
            m_bt7.setText(word4);
            m_bt8.setText(word1);
            m_bt9.setText(letter3);
            m_bt10.setText(letter6);
            m_bt11.setText(word2);
            m_bt12.setText(letter8);
               /* m_bt13.setText(letter14);
                m_bt14.setText(letter12);
                m_bt15.setText(letter9);
                m_bt16.setText(letter10);*/


        } else if (m_type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(letter6);
            m_bt2.setText(word5);
            m_bt3.setText(letter4);
            m_bt4.setText(letter1);
            m_bt5.setText(word2);
            m_bt6.setText(letter9);
            m_bt7.setText(word4);
            m_bt8.setText(word6);
            m_bt9.setText(word1);
            m_bt10.setText(letter8);
            m_bt11.setText(letter4);
            m_bt12.setText(word3);
              /*  m_bt13.setText(word6);
                m_bt14.setText(letter11);
                m_bt15.setText(letter13);
                m_bt16.setText(word2);
*/


        } else if (m_type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word5);
            m_bt2.setText(letter8);
            m_bt3.setText(letter4);
            m_bt4.setText(letter11);
            m_bt5.setText(word7);
            m_bt6.setText(word2);
            m_bt7.setText(word4);
            m_bt8.setText(word3);
            m_bt9.setText(letter10);
            m_bt10.setText(word1);
            m_bt11.setText(letter12);
            m_bt12.setText(word6);
               /* m_bt13.setText(letter6);
                m_bt14.setText(word1);
                m_bt15.setText(letter13);
                m_bt16.setText(word3);*/


        } else if (m_type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word6);
            m_bt2.setText(word8);
            m_bt3.setText(letter6);
            m_bt4.setText(letter3);
            m_bt5.setText(word5);
            m_bt6.setText(word4);
            m_bt7.setText(letter7);
            m_bt8.setText(word7);
            m_bt9.setText(word1);
            m_bt10.setText(word3);
            m_bt11.setText(word2);
            m_bt12.setText(letter8);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(letter10);*/

        } else if (m_type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
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
            m_bt1.setText(word6);
            m_bt2.setText(word1);
            m_bt3.setText(word8);
            m_bt4.setText(word9);
            m_bt5.setText(word5);
            m_bt6.setText(letter3);
            m_bt7.setText(word3);
            m_bt8.setText(word4);
            m_bt9.setText(word2);
            m_bt10.setText(word8);
            m_bt11.setText(letter6);
            m_bt12.setText(word7);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        } else if (m_type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            m_bt1.setText(word6);
            m_bt2.setText(word10);
            m_bt3.setText(word8);
            m_bt4.setText(word1);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(letter3);
            m_bt8.setText(word4);
            m_bt9.setText(word7);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word2);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        } else if (m_type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            m_bt1.setText(word2);
            m_bt2.setText(word1);
            m_bt3.setText(word8);
            m_bt4.setText(word4);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(word7);
            m_bt8.setText(word11);
            m_bt9.setText(word6);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word10);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        } else if (m_type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(m_split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            m_bt1.setText(word6);
            m_bt2.setText(word1);
            m_bt3.setText(word12);
            m_bt4.setText(word11);
            m_bt5.setText(word5);
            m_bt6.setText(word9);
            m_bt7.setText(word10);
            m_bt8.setText(word4);
            m_bt9.setText(word2);
            m_bt10.setText(word3);
            m_bt11.setText(word8);
            m_bt12.setText(word7);
              /*  m_bt13.setText(letter6);
                m_bt14.setText(letter11);
                m_bt15.setText(word2);
                m_bt16.setText(word8);*/

        }

    }

    public void game_start_ote_to_tamil () {
        gameid = 8;
        setContentView(R.layout.ote_to_tamil_multiplayer);
        switchToScreen(R.layout.ote_to_tamil_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        c_edit = (EditText) findViewById(R.id.clue_ans_editer);
        c_clear = (TextView) findViewById(R.id.clue_clear);
        ans_high = (TextView) findViewById(R.id.ans_highlite);
        edit_buttons_layout = (RelativeLayout) findViewById(R.id.edit_buttons_layout);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        ads_logo2 = (CircleImageView) findViewById(R.id.ads_logo2);
        // c_verify = (Button) findViewById(R.id.clue_verify);
        c_clear = (Button) findViewById(R.id.clue_clear);
        c_ans = (TextView) findViewById(R.id.c_ans);
        question = (TextView) findViewById(R.id.question);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        list4 = (LinearLayout) findViewById(R.id.list4);

        bt1 = (TextView) findViewById(R.id.c_button1);
        bt2 = (TextView) findViewById(R.id.c_button2);
        bt3 = (TextView) findViewById(R.id.c_button3);
        bt4 = (TextView) findViewById(R.id.c_button4);
        bt5 = (TextView) findViewById(R.id.c_button5);
        bt6 = (TextView) findViewById(R.id.c_button6);
        bt7 = (TextView) findViewById(R.id.c_button7);
        bt8 = (TextView) findViewById(R.id.c_button8);
        bt9 = (TextView) findViewById(R.id.c_button9);
        bt10 = (TextView) findViewById(R.id.c_button10);
        bt11 = (TextView) findViewById(R.id.c_button11);
        bt12 = (TextView) findViewById(R.id.c_button12);
        bt13 = (TextView) findViewById(R.id.c_button13);
        bt14 = (TextView) findViewById(R.id.c_button14);
        bt15 = (TextView) findViewById(R.id.c_button15);
        bt16 = (TextView) findViewById(R.id.c_button16);
        settings = (TextView) findViewById(R.id.settings);

        sound();
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        // New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);

                /*  if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }


        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);

       /* Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);*/
        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);

        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });

        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt1.startAnimation(shake);
                String ts = bt1.getText().toString();
                c_edit.append(ts);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt2.startAnimation(shake);
                String ts = bt2.getText().toString();
                c_edit.append(ts);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt3.startAnimation(shake);
                String ts = bt3.getText().toString();
                c_edit.append(ts);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt5.startAnimation(shake);
                String ts = bt5.getText().toString();
                c_edit.append(ts);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt6.startAnimation(shake);
                String ts = bt6.getText().toString();
                c_edit.append(ts);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt7.startAnimation(shake);
                String ts = bt7.getText().toString();
                c_edit.append(ts);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt9.startAnimation(shake);
                String ts = bt9.getText().toString();
                c_edit.append(ts);
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt10.startAnimation(shake);
                String ts = bt10.getText().toString();
                c_edit.append(ts);
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt11.startAnimation(shake);
                String ts = bt11.getText().toString();
                c_edit.append(ts);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt4.startAnimation(shake);
                String ts = bt4.getText().toString();
                c_edit.append(ts);
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt8.startAnimation(shake);
                String ts = bt8.getText().toString();
                c_edit.append(ts);
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt12.startAnimation(shake);
                String ts = bt12.getText().toString();
                c_edit.append(ts);

            }
        });
        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt13.startAnimation(shake);
                String ts = bt13.getText().toString();
                c_edit.append(ts);
            }
        });

        bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt14.startAnimation(shake);
                String ts = bt14.getText().toString();
                c_edit.append(ts);
            }
        });
        bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt15.startAnimation(shake);
                String ts = bt15.getText().toString();
                c_edit.append(ts);

            }
        });
        bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt16.startAnimation(shake);
                String ts = bt16.getText().toString();
                c_edit.append(ts);

            }
        });


        c_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                pressKey_c(KeyEvent.KEYCODE_DEL);
            }
        });

        c_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c_edit.setText("");
                return false;
            }
        });

        c_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);
            }
        });

        c_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);
                return true;
            }
        });

        c_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = c_edit.getText().toString();
                Cursor cd;
                cd = newhelper2.getQry("SELECT * FROM newmaintable2 where answer ='" + answer + "' and rtm='0' and questionid='" + letterid + "' and gameid='" + gameid + "'");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    //Toast.makeText(Ote_to_Tamil.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(answer);
                    //bulb invisible
                    //
                    list4.setVisibility(View.INVISIBLE);
                    newhelper2.executeSql("UPDATE newmaintable2 SET rtm=1 WHERE questionid='" + letterid + "' and gameid='" + gameid + "'");


                    st_game = st_game + 1;
                    your_m_count.setText("" + st_game);
                    if (st_game == tt_game) {
                        if (play_after == 0) {
                            play_after = 1;
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                                                /*    int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                                }
                            }, 2000);
                        }

                        mMultiplayer = true;
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            gameactive_you = 1;
                            broadcastScore(false);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }


                    } else {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            //gameactive_you = 1;
                            broadcastScore(true);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letterid = letterid + 1;
                                nextgame_ote_to_tamil();
                            }
                        }, 2000);
                    }

                }
            }
        });
        nextgame_ote_to_tamil();

    }

    public void nextgame_ote_to_tamil () {
        //daily bonus
        bt4.setVisibility(View.VISIBLE);
        bt8.setVisibility(View.VISIBLE);
        bt12.setVisibility(View.VISIBLE);
        bt13.setVisibility(View.VISIBLE);
        bt14.setVisibility(View.VISIBLE);
        bt15.setVisibility(View.VISIBLE);
        bt16.setVisibility(View.VISIBLE);
        c_ans.setEnabled(true);


        bt1.setText("");
        bt2.setText("");
        bt3.setText("");
        bt5.setText("");
        bt6.setText("");
        bt7.setText("");
        bt9.setText("");
        bt10.setText("");
        bt11.setText("");
        bt4.setText("");
        bt8.setText("");
        bt12.setText("");
        bt13.setText("");
        bt14.setText("");
        bt15.setText("");
        bt16.setText("");
        c_edit.setText("");

        ans_high.setVisibility(View.GONE);
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        list4.setVisibility(View.VISIBLE);


        LinearLayout bl1 = (LinearLayout) findViewById(R.id.c_buttonlist1_layout);
        LinearLayout bl2 = (LinearLayout) findViewById(R.id.c_buttonlist2_layout);
        LinearLayout bl3 = (LinearLayout) findViewById(R.id.c_buttonlist3_layout);
        LinearLayout bl4 = (LinearLayout) findViewById(R.id.c_buttonlist4_layout);
        Cursor c;
        c = newhelper2.getQry("select * from newmaintable2 where gameid='" + gameid + "' and rtm='0' and questionid='" + letterid + "'");
        c.moveToFirst();
        if (c.getCount() != 0) {
            sa = c.getString(c.getColumnIndexOrThrow("sf_words"));
            o_questionid = c.getInt(c.getColumnIndexOrThrow("questionid"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));
            question.setText(c.getString(c.getColumnIndexOrThrow("question")));
            String tfoption = sa;
            String[] first = tfoption.split(",");
            type = first.length;
            int minmum = 1;
            int maximum = 3;
            int randomno;
            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;
            //String r= String.valueOf(w_id);
            //lt_id.setText(r);
            if (randomno == 1) {
                o_simple();
            } else if (randomno == 2) {
                o_medium();
            } else if (randomno == 3) {
                o_hard();
            }


            System.out.println("#################################sa" + sa);
        }


    }

    public void o_simple () {
        String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";
        bt4.setVisibility(View.GONE);
        bt8.setVisibility(View.GONE);
        bt12.setVisibility(View.GONE);
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(letter3);
            bt5.setText(sa);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt9.setText(letter9);
            bt10.setText(letter10);
            bt11.setText(letter11);

        } else if (type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt9.setText(letter9);
            bt10.setText(word2);
            bt11.setText(letter11);

        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word2);
            bt2.setText(letter5);
            bt3.setText(letter2);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt9.setText(letter6);
            bt10.setText(letter14);
            bt11.setText(word3);

        } else if (type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word2);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt5.setText(letter6);
            bt6.setText(letter13);
            bt7.setText(word4);
            bt9.setText(word1);
            bt10.setText(letter7);
            bt11.setText(letter12);

        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(letter14);
            bt5.setText(letter2);
            bt6.setText(word4);
            bt7.setText(word3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(word2);

        } else if (type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word5);
            bt2.setText(word2);
            bt3.setText(letter4);
            bt5.setText(word6);
            bt6.setText(letter1);
            bt7.setText(word4);
            bt9.setText(letter8);
            bt10.setText(word1);
            bt11.setText(word3);

        } else if (type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word3);
            bt2.setText(word5);
            bt3.setText(word2);
            bt5.setText(word7);
            bt6.setText(word6);
            bt7.setText(word4);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);

        } else if (type == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word6);
            bt2.setText(letter6);
            bt3.setText(word8);
            bt5.setText(word5);
            bt6.setText(word7);
            bt7.setText(word4);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word7);
            bt9.setText(word4);
            bt10.setText(word3);
            bt11.setText(word2);

        }
    }

    public void o_medium () {

        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(sa);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(letter6);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter13);
            bt11.setText(letter7);
            bt12.setText(word2);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word2);
            bt2.setText(letter15);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word2);
            bt11.setText(letter2);
            bt12.setText(letter1);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(word1);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter10);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);
              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter13);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter8);
            bt12.setText(word2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word2);
            bt2.setText(letter9);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(letter6);
            bt6.setText(letter1);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(letter8);
            bt10.setText(word1);
            bt11.setText(word3);
            bt12.setText(letter4);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter8);
            bt2.setText(letter4);
            bt3.setText(word5);
            bt4.setText(word2);
            bt5.setText(word7);
            bt6.setText(word3);
            bt7.setText(word4);
            bt8.setText(letter11);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word6);
            bt2.setText(letter6);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter6);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }
    }

    public void o_hard () {
        String a = "கே,சே,பி,யா,தி,ரே,ஏ,வ்,ர்,கே,ஃ,ஓ,ப,ல்,இ,து,பு,மு,ரூ";

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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(sa);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
            bt13.setText(letter10);
            bt14.setText(letter14);
            bt15.setText(letter12);
            bt16.setText(letter13);
        } else if (type == 2) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(letter6);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter13);
            bt11.setText(letter7);
            bt12.setText(letter12);
            bt13.setText(letter14);
            bt14.setText(word2);
            bt15.setText(letter10);
            bt16.setText(letter8);


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word2);
            bt2.setText(letter15);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word2);
            bt11.setText(letter2);
            bt12.setText(letter1);
            bt13.setText(letter14);
            bt14.setText(letter12);
            bt15.setText(letter10);
            bt16.setText(letter13);

        } else if (type == 4) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(letter11);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter10);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(letter9);
            bt13.setText(letter14);
            bt14.setText(letter15);
            bt15.setText(word1);
            bt16.setText(word2);

        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter13);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter8);
            bt12.setText(word2);
            bt13.setText(letter14);
            bt14.setText(letter12);
            bt15.setText(letter9);
            bt16.setText(letter10);


        } else if (type == 6) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter14);
            bt2.setText(letter9);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(letter13);
            bt6.setText(letter8);
            bt7.setText(word4);
            bt8.setText(letter7);
            bt9.setText(letter1);
            bt10.setText(word1);
            bt11.setText(word3);
            bt12.setText(letter4);
            bt13.setText(word6);
            bt14.setText(letter11);
            bt15.setText(letter6);
            bt16.setText(word2);


        } else if (type == 7) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(letter2);
            bt2.setText(letter4);
            bt3.setText(word5);
            bt4.setText(word2);
            bt5.setText(word7);
            bt6.setText(letter13);
            bt7.setText(word4);
            bt8.setText(letter8);
            bt9.setText(letter10);
            bt10.setText(letter15);
            bt11.setText(letter12);
            bt12.setText(word6);
            bt13.setText(letter6);
            bt14.setText(word1);
            bt15.setText(letter11);
            bt16.setText(word3);

        } else if (type == 8) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word6);
            bt2.setText(letter15);
            bt3.setText(word8);
            bt4.setText(letter13);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter12);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(letter14);
            bt12.setText(letter8);
            bt13.setText(letter6);
            bt14.setText(letter7);
            bt15.setText(word2);
            bt16.setText(letter10);

        } else if (type == 9) {
            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(sa, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(letter2);
            bt4.setText(letter13);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter6);
            bt8.setText(word4);
            bt9.setText(letter3);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
            bt13.setText(letter6);
            bt14.setText(letter2);
            bt15.setText(word2);
            bt16.setText(word8);
        }
    }

    public void game_start_riddles () {

        gameid = 10;
        setContentView(R.layout.riddle_game_multiplayer);
        switchToScreen(R.layout.riddle_game_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        question_txt = (TextView) findViewById(R.id.question_txt);
        question_txt.setTextSize(20);
        c_clear = (TextView) findViewById(R.id.clue_clear);
        c_ans = (TextView) findViewById(R.id.c_ans);
        ans_high = (TextView) findViewById(R.id.ans_highlite);


        bt1 = (TextView) findViewById(R.id.c_button1);
        bt2 = (TextView) findViewById(R.id.c_button2);
        bt3 = (TextView) findViewById(R.id.c_button3);
        bt4 = (TextView) findViewById(R.id.c_button4);
        bt5 = (TextView) findViewById(R.id.c_button5);
        bt6 = (TextView) findViewById(R.id.c_button6);
        bt7 = (TextView) findViewById(R.id.c_button7);
        bt8 = (TextView) findViewById(R.id.c_button8);
        bt9 = (TextView) findViewById(R.id.c_button9);
        bt10 = (TextView) findViewById(R.id.c_button10);
        bt11 = (TextView) findViewById(R.id.c_button11);
        bt12 = (TextView) findViewById(R.id.c_button12);
        bt13 = (TextView) findViewById(R.id.c_button13);
        bt14 = (TextView) findViewById(R.id.c_button14);
        bt15 = (TextView) findViewById(R.id.c_button15);
        bt16 = (TextView) findViewById(R.id.c_button16);


        settings = (TextView) findViewById(R.id.settings);
        c_edit = (EditText) findViewById(R.id.clue_ans_editer);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        list4 = (LinearLayout) findViewById(R.id.list4);

        bt1.setText("");
        bt2.setText("");
        bt3.setText("");
        bt5.setText("");
        bt6.setText("");
        bt7.setText("");
        bt9.setText("");
        bt10.setText("");
        bt11.setText("");
        bt4.setText("");
        bt8.setText("");
        bt12.setText("");
        bt13.setText("");
        bt14.setText("");
        bt15.setText("");
        bt16.setText("");
        c_edit.setText("");
        sound();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        // New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)){
                fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);

                /*  if (sp.getInt(Solli_adi_multiplayer.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Solli_adi_multiplayer.this,native_banner_ad_container);
                }else {
                    fb_native(Solli_adi_multiplayer.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }


        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);
       /* Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);*/

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        c_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);
            }
        });
        c_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);

                return true;
            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt1.startAnimation(shake);
                String ts = bt1.getText().toString();
                c_edit.append(ts);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt2.startAnimation(shake);
                String ts = bt2.getText().toString();
                c_edit.append(ts);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt3.startAnimation(shake);
                String ts = bt3.getText().toString();
                c_edit.append(ts);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt5.startAnimation(shake);
                String ts = bt5.getText().toString();
                c_edit.append(ts);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);

                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt6.startAnimation(shake);
                String ts = bt6.getText().toString();
                c_edit.append(ts);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt7.startAnimation(shake);
                String ts = bt7.getText().toString();
                c_edit.append(ts);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt9.startAnimation(shake);
                String ts = bt9.getText().toString();
                c_edit.append(ts);
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt10.startAnimation(shake);
                String ts = bt10.getText().toString();
                c_edit.append(ts);
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt11.startAnimation(shake);
                String ts = bt11.getText().toString();
                c_edit.append(ts);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt4.startAnimation(shake);
                String ts = bt4.getText().toString();
                c_edit.append(ts);
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt8.startAnimation(shake);
                String ts = bt8.getText().toString();
                c_edit.append(ts);
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt12.startAnimation(shake);
                String ts = bt12.getText().toString();
                c_edit.append(ts);

            }
        });
        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt13.startAnimation(shake);
                String ts = bt13.getText().toString();
                c_edit.append(ts);
            }
        });

        bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt14.startAnimation(shake);
                String ts = bt14.getText().toString();
                c_edit.append(ts);
            }
        });
        bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt15.startAnimation(shake);
                String ts = bt15.getText().toString();
                c_edit.append(ts);

            }
        });
        bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt16.startAnimation(shake);
                String ts = bt16.getText().toString();
                c_edit.append(ts);

            }
        });


        c_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = c_edit.getText().toString();
                Log.e("to value", answer);
                System.out.println("==========value" + answer);
                Cursor cd;
                cd = newhelper3.getQry("SELECT * FROM right_order where answer ='" + answer + "' and rtm='0' and questionid='" + r_questionid + "' and gameid='" + gameid + "'");
                System.out.println("============results" + "SELECT * FROM right_order where answer ='" + answer + "' and rtm='0' and questionid='" + r_questionid + "' and gameid='" + gameid + "'");
                cd.moveToFirst();


                if (cd.getCount() != 0) {
                    //Toast.makeText(Clue_Game_Hard.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    c_ans.setBackgroundResource(R.drawable.tick_background);
                    c_ans.setEnabled(false);
                    list4.setVisibility(View.INVISIBLE);
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(answer);
                    newhelper3.executeSql("UPDATE right_order SET rtm=1 WHERE questionid='" + r_questionid + "' and gameid='" + gameid + "'");

                    st_game = st_game + 1;
                    your_m_count.setText("" + st_game);
                    if (st_game == tt_game) {
                        if (play_after == 0) {
                            play_after = 1;
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                                                /*    int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                                }
                            }, 2000);
                        }

                        mMultiplayer = true;
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            gameactive_you = 1;
                            broadcastScore(false);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }


                    } else {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            //gameactive_you = 1;
                            broadcastScore(true);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letterid = letterid + 1;
                                nextgame_riddles();
                            }
                        }, 2000);
                    }

                }
            }
        });
        c_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                pressKey_c(KeyEvent.KEYCODE_DEL);
            }
        });

        c_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c_edit.setText("");
                return false;
            }
        });
        nextgame_riddles();
    }

    public void nextgame_riddles () {

        c_edit.setText("");
        ans_high.setVisibility(View.GONE);
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        list4.setVisibility(View.VISIBLE);
        c_ans.setEnabled(true);


        Cursor c;
        c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and rtm='0' and questionid='" + letterid + "' ");
        c.moveToFirst();

        if (c.getCount() != 0) {
            r_questionid = c.getString(c.getColumnIndexOrThrow("questionid"));
            r_question = c.getString(c.getColumnIndexOrThrow("question"));
            answer = c.getString(c.getColumnIndexOrThrow("answer"));
            split_word = c.getString(c.getColumnIndexOrThrow("splitword"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));


            question_txt.setText(r_question);
            ans_high.setText(answer);

            String tfoption = split_word;
            String[] first = tfoption.split(",");
            type = first.length;

            int minmum = 1;
            int maximum = 3;
            Random rn = new Random();
            int randomno = rn.nextInt(maximum - minmum + 1) + minmum;
            System.out.println("##################################answer" + answer);
            if (randomno == 1) {
                r_medium();
            } else if (randomno == 2) {
                r_medium1();
            } else if (randomno == 3) {
                r_medium2();
            }

        }


    }

    public void r_medium () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(letter6);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter13);
            bt11.setText(letter7);
            bt12.setText(word2);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word2);
            bt2.setText(letter15);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word2);
            bt11.setText(letter2);
            bt12.setText(letter1);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(word1);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter10);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);
              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter13);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter8);
            bt12.setText(word2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word2);
            bt2.setText(letter9);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(letter6);
            bt6.setText(letter1);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(letter8);
            bt10.setText(word1);
            bt11.setText(word3);
            bt12.setText(letter4);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter8);
            bt2.setText(letter4);
            bt3.setText(word5);
            bt4.setText(word2);
            bt5.setText(word7);
            bt6.setText(word3);
            bt7.setText(word4);
            bt8.setText(letter11);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(letter6);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter6);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }
    }

    public void r_medium1 () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(letter6);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter13);
            bt11.setText(letter7);
            bt12.setText(word2);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word2);
            bt2.setText(letter15);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word2);
            bt11.setText(letter2);
            bt12.setText(letter1);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(word1);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter10);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);
              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter13);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter8);
            bt12.setText(word2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word2);
            bt2.setText(letter9);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(letter6);
            bt6.setText(letter1);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(letter8);
            bt10.setText(word1);
            bt11.setText(word3);
            bt12.setText(letter4);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter8);
            bt2.setText(letter4);
            bt3.setText(word5);
            bt4.setText(word2);
            bt5.setText(word7);
            bt6.setText(word3);
            bt7.setText(word4);
            bt8.setText(letter11);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(letter6);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter6);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word12);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }


    }

    public void r_medium2 () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(letter6);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(word1);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(word2);
            bt11.setText(letter7);
            bt12.setText(letter13);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(letter15);
            bt3.setText(letter1);
            bt4.setText(letter4);
            bt5.setText(word3);
            bt6.setText(letter9);
            bt7.setText(word2);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word1);
            bt11.setText(word2);
            bt12.setText(letter2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter13);
            bt2.setText(word3);
            bt3.setText(letter10);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(letter7);
            bt7.setText(letter10);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);



              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(letter4);
            bt3.setText(letter13);
            bt4.setText(word5);
            bt5.setText(word3);
            bt6.setText(letter3);
            bt7.setText(word4);
            bt8.setText(word1);
            bt9.setText(letter3);
            bt10.setText(letter6);
            bt11.setText(word2);
            bt12.setText(letter8);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter6);
            bt2.setText(word5);
            bt3.setText(letter4);
            bt4.setText(letter1);
            bt5.setText(word2);
            bt6.setText(letter9);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(word1);
            bt10.setText(letter8);
            bt11.setText(letter4);
            bt12.setText(word3);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word5);
            bt2.setText(letter8);
            bt3.setText(letter4);
            bt4.setText(letter11);
            bt5.setText(word7);
            bt6.setText(word2);
            bt7.setText(word4);
            bt8.setText(word3);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word8);
            bt3.setText(letter6);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word9);
            bt5.setText(word5);
            bt6.setText(letter3);
            bt7.setText(word3);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word8);
            bt11.setText(letter6);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word10);
            bt3.setText(word8);
            bt4.setText(word1);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter3);
            bt8.setText(word4);
            bt9.setText(word7);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word2);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            bt1.setText(word2);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word4);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word7);
            bt8.setText(word11);
            bt9.setText(word6);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word10);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word12);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }

    }

    public void game_start_error_correction () {

        gameid = 11;
        setContentView(R.layout.error_correction_multiplayer);
        switchToScreen(R.layout.error_correction_multiplayer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        question_txt = (TextView) findViewById(R.id.question_txt);
        c_clear = (TextView) findViewById(R.id.clue_clear);
        c_ans = (TextView) findViewById(R.id.c_ans);
        ans_high = (TextView) findViewById(R.id.ans_highlite);


        bt1 = (TextView) findViewById(R.id.c_button1);
        bt2 = (TextView) findViewById(R.id.c_button2);
        bt3 = (TextView) findViewById(R.id.c_button3);
        bt4 = (TextView) findViewById(R.id.c_button4);
        bt5 = (TextView) findViewById(R.id.c_button5);
        bt6 = (TextView) findViewById(R.id.c_button6);
        bt7 = (TextView) findViewById(R.id.c_button7);
        bt8 = (TextView) findViewById(R.id.c_button8);
        bt9 = (TextView) findViewById(R.id.c_button9);
        bt10 = (TextView) findViewById(R.id.c_button10);
        bt11 = (TextView) findViewById(R.id.c_button11);
        bt12 = (TextView) findViewById(R.id.c_button12);
        bt13 = (TextView) findViewById(R.id.c_button13);
        bt14 = (TextView) findViewById(R.id.c_button14);
        bt15 = (TextView) findViewById(R.id.c_button15);
        bt16 = (TextView) findViewById(R.id.c_button16);


        settings = (TextView) findViewById(R.id.settings);
        c_edit = (EditText) findViewById(R.id.clue_ans_editer);
        adds = (LinearLayout) findViewById(R.id.ads_lay);

        list4 = (LinearLayout) findViewById(R.id.list4);

        bt1.setText("");
        bt2.setText("");
        bt3.setText("");
        bt5.setText("");
        bt6.setText("");
        bt7.setText("");
        bt9.setText("");
        bt10.setText("");
        bt11.setText("");
        bt4.setText("");
        bt8.setText("");
        bt12.setText("");
        bt13.setText("");
        bt14.setText("");
        bt15.setText("");
        bt16.setText("");
        c_edit.setText("");

        sound();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundResource(R.drawable.sound_off);
                String snd = sp.getString(Solli_adi_multiplayer.this, "snd");
                if (snd.equals("off")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "on");
                    settings.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sp.putString(Solli_adi_multiplayer.this, "snd", "off");
                    settings.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        opponent_m_count = (TextView) findViewById(R.id.opponent_m_count);
        your_m_count = (TextView) findViewById(R.id.your_m_count);
        your_m_count.setText("" + st_game);


        //New_Main_Activity.load_addFromMain(Solli_adi_multiplayer.this, adds);
        if (sp.getInt(Solli_adi_multiplayer.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {

/*
            if (Utils.isNetworkAvailable(Solli_adi_multiplayer.this)) {
                sp.putInt(Solli_adi_multiplayer.this, "addlodedd", 2);
                System.out.println("@IMG");
                adView = new AdView(Solli_adi_multiplayer.this);
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
*/


        }


        oppe_msg = (TextView) findViewById(R.id.oppe_msg);
        my_msg = (TextView) findViewById(R.id.my_msg);
        mys_img = (TextView) findViewById(R.id.mys_img);
        oppe_img = (ImageView) findViewById(R.id.oppe_img);
       /* Glide.with(getApplicationContext())
                .load(img_url1)
                .into(my_img);*/

        Glide.with(getApplicationContext())
                .load(img_url2)
                .into(oppe_img);
        oppe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_history();
            }
        });
        mys_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    message_con();
                } else {
                    Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        c_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);
            }
        });
        c_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(c_edit.getWindowToken(), 0);

                return true;
            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c1.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt1.startAnimation(shake);
                String ts = bt1.getText().toString();
                c_edit.append(ts);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c2.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt2.startAnimation(shake);
                String ts = bt2.getText().toString();
                c_edit.append(ts);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c3.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt3.startAnimation(shake);
                String ts = bt3.getText().toString();
                c_edit.append(ts);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c4.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt5.startAnimation(shake);
                String ts = bt5.getText().toString();
                c_edit.append(ts);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c5.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);

                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt6.startAnimation(shake);
                String ts = bt6.getText().toString();
                c_edit.append(ts);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c6.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt7.startAnimation(shake);
                String ts = bt7.getText().toString();
                c_edit.append(ts);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c7.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt9.startAnimation(shake);
                String ts = bt9.getText().toString();
                c_edit.append(ts);
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c8.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt10.startAnimation(shake);
                String ts = bt10.getText().toString();
                c_edit.append(ts);
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c9.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt11.startAnimation(shake);
                String ts = bt11.getText().toString();
                c_edit.append(ts);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c10.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt4.startAnimation(shake);
                String ts = bt4.getText().toString();
                c_edit.append(ts);
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c11.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt8.startAnimation(shake);
                String ts = bt8.getText().toString();
                c_edit.append(ts);
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c12.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt12.startAnimation(shake);
                String ts = bt12.getText().toString();
                c_edit.append(ts);

            }
        });
        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c13.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt13.startAnimation(shake);
                String ts = bt13.getText().toString();
                c_edit.append(ts);
            }
        });

        bt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c14.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt14.startAnimation(shake);
                String ts = bt14.getText().toString();
                c_edit.append(ts);
            }
        });
        bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // c15.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt15.startAnimation(shake);
                String ts = bt15.getText().toString();
                c_edit.append(ts);

            }
        });
        bt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c16.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                Animation shake = AnimationUtils.loadAnimation(Solli_adi_multiplayer.this, R.anim.button_shake);
                bt16.startAnimation(shake);
                String ts = bt16.getText().toString();
                c_edit.append(ts);

            }
        });

        c_edit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String answer = c_edit.getText().toString();
                System.out.println("==========value" + answer);
                Cursor cd;

                cd = newhelper3.getQry("SELECT * FROM right_order where answer ='" + answer + "' and rtm='0' and questionid='" + letterid + "' and gameid='" + gameid + "'");
                System.out.println("============results" + "SELECT * FROM right_order where answer ='" + answer + "' and rtm='0' and questionid='" + letterid + "' and gameid='" + gameid + "'");
                cd.moveToFirst();


                if (cd.getCount() != 0) {
                    // spz3.play(soundId3, sv, sv, 0, 0, sv);
                    //Toast.makeText(Clue_Game_Hard.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    c_ans.setBackgroundResource(R.drawable.tick_background);
                    c_ans.setEnabled(false);
                    list4.setVisibility(View.INVISIBLE);
                    ans_high.setVisibility(View.VISIBLE);
                    ans_high.setText(answer);
                    newhelper3.executeSql("UPDATE right_order SET rtm=1 WHERE questionid='" + letterid + "' and gameid='" + gameid + "'");

                    st_game = st_game + 1;
                    your_m_count.setText("" + st_game);
                    if (st_game == tt_game) {
                        if (play_after == 0) {
                            play_after = 1;
                            Handler handler8 = new Handler();
                            handler8.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                                    cfx.moveToFirst();
                                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                                    int spx = skx + 200;
                                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                                    finalscreen(200, "நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");
                                                /*    int a=sp.getInt(Solli_adi_multiplayer.this, "muliplay_score") + 100;
                                                    sp.putInt(Solli_adi_multiplayer.this, "muliplay_score", a);
                                                    finalscreen(100,"நீங்கள் வெற்றி அடைந்துவிட்டீர்கள். உங்களுக்கான நாணயங்கள்");*/
                                }
                            }, 2000);
                        }

                        mMultiplayer = true;
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            gameactive_you = 1;
                            broadcastScore(false);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }


                    } else {
                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            //gameactive_you = 1;
                            broadcastScore(true);
                        } else {
                            Toast.makeText(Solli_adi_multiplayer.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                            exitgame();
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letterid = letterid + 1;
                                nextgame_riddles();
                            }
                        }, 2000);
                    }


                }
            }
        });
        c_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //c17.start();
                spz1.play(soundId1, sv, sv, 0, 0, sv);
                pressKey_c(KeyEvent.KEYCODE_DEL);
            }
        });

        c_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c_edit.setText("");
                return false;
            }
        });

        nextgame_error_correction();
    }

    public void nextgame_error_correction () {
        c_edit.setText("");
        ans_high.setVisibility(View.GONE);
        c_ans.setBackgroundResource(R.drawable.yellow_question);
        list4.setVisibility(View.VISIBLE);
        c_ans.setEnabled(true);


        Cursor c;

        c = newhelper3.getQry("select * from right_order where gameid='" + gameid + "' and rtm='0' and questionid='" + letterid + "'");
        c.moveToFirst();

        if (c.getCount() != 0) {
            er_questionid = c.getString(c.getColumnIndexOrThrow("questionid"));
            String er_question = c.getString(c.getColumnIndexOrThrow("question"));
            answer = c.getString(c.getColumnIndexOrThrow("answer"));
            split_word = c.getString(c.getColumnIndexOrThrow("splitword"));
            int playtime = c.getInt(c.getColumnIndexOrThrow("playtime"));


            question_txt.setText(er_question);
            ans_high.setText(answer);

            String tfoption = split_word;
            String[] first = tfoption.split(",");
            type = first.length;

            int minmum = 1;
            int maximum = 3;
            int randomno;
            Random rn = new Random();
            randomno = rn.nextInt(maximum - minmum + 1) + minmum;


            if (randomno == 1) {
                er_medium();
            } else if (randomno == 2) {
                er_medium1();
            } else if (randomno == 3) {
                er_medium2();
            }
        }
    }

    public void er_medium () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(word1);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(letter6);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter13);
            bt11.setText(letter7);
            bt12.setText(word2);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word2);
            bt2.setText(letter15);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(word1);
            bt6.setText(letter9);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word2);
            bt11.setText(letter2);
            bt12.setText(letter1);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(word1);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter10);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);
              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(word5);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter13);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter8);
            bt12.setText(word2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word2);
            bt2.setText(letter9);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(letter6);
            bt6.setText(letter1);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(letter8);
            bt10.setText(word1);
            bt11.setText(word3);
            bt12.setText(letter4);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter8);
            bt2.setText(letter4);
            bt3.setText(word5);
            bt4.setText(word2);
            bt5.setText(word7);
            bt6.setText(word3);
            bt7.setText(word4);
            bt8.setText(letter11);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(letter6);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter6);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word12);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }


    }

    public void er_medium1 () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(letter6);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(word1);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(word2);
            bt11.setText(letter7);
            bt12.setText(letter13);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(letter15);
            bt3.setText(letter1);
            bt4.setText(letter4);
            bt5.setText(word3);
            bt6.setText(letter9);
            bt7.setText(word2);
            bt8.setText(letter3);
            bt9.setText(letter6);
            bt10.setText(word1);
            bt11.setText(word2);
            bt12.setText(letter2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter13);
            bt2.setText(word3);
            bt3.setText(letter10);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(letter7);
            bt7.setText(letter10);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(word4);
            bt11.setText(letter12);
            bt12.setText(word2);



              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(letter4);
            bt3.setText(letter13);
            bt4.setText(word5);
            bt5.setText(word3);
            bt6.setText(letter3);
            bt7.setText(word4);
            bt8.setText(word1);
            bt9.setText(letter3);
            bt10.setText(letter6);
            bt11.setText(word2);
            bt12.setText(letter8);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter6);
            bt2.setText(word5);
            bt3.setText(letter4);
            bt4.setText(letter1);
            bt5.setText(word2);
            bt6.setText(letter9);
            bt7.setText(word4);
            bt8.setText(word6);
            bt9.setText(word1);
            bt10.setText(letter8);
            bt11.setText(letter4);
            bt12.setText(word3);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word5);
            bt2.setText(letter8);
            bt3.setText(letter4);
            bt4.setText(letter11);
            bt5.setText(word7);
            bt6.setText(word2);
            bt7.setText(word4);
            bt8.setText(word3);
            bt9.setText(letter10);
            bt10.setText(word1);
            bt11.setText(letter12);
            bt12.setText(word6);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word8);
            bt3.setText(letter6);
            bt4.setText(letter3);
            bt5.setText(word5);
            bt6.setText(word4);
            bt7.setText(letter7);
            bt8.setText(word7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word9);
            bt5.setText(word5);
            bt6.setText(letter3);
            bt7.setText(word3);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word8);
            bt11.setText(letter6);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word10);
            bt3.setText(word8);
            bt4.setText(word1);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter3);
            bt8.setText(word4);
            bt9.setText(word7);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word2);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            bt1.setText(word2);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word4);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word7);
            bt8.setText(word11);
            bt9.setText(word6);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word10);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word12);
            bt4.setText(word11);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }

    }

    public void er_medium2 () {
        String a = "சீ,ட்,பே,ரு,ணி,இ,லை,ஒ,ற்,றை,மீ,ரி,க்,அ,சை";
        bt13.setVisibility(View.GONE);
        bt14.setVisibility(View.GONE);
        bt15.setVisibility(View.GONE);
        bt16.setVisibility(View.GONE);
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
            bt1.setText(letter1);
            bt2.setText(letter2);
            bt3.setText(split_word);
            bt4.setText(letter11);
            bt5.setText(letter5);
            bt6.setText(letter6);
            bt7.setText(letter7);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(letter15);
            bt11.setText(letter4);
            bt12.setText(letter12);
              /*  bt13.setText(letter10);
                bt14.setText(letter14);
                bt15.setText(letter12);
                bt16.setText(letter13);*/


        } else if (type == 2) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter5);
            bt2.setText(letter2);
            bt3.setText(letter6);
            bt4.setText(letter4);
            bt5.setText(letter1);
            bt6.setText(word1);
            bt7.setText(letter11);
            bt8.setText(letter3);
            bt9.setText(letter9);
            bt10.setText(word2);
            bt11.setText(letter7);
            bt12.setText(letter13);
                /*bt13.setText(letter14);
                bt14.setText(word2);
                bt15.setText(letter10);
                bt16.setText(letter8);*/


        } else if (type == 3) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(word3);
            bt3.setText(letter1);
            bt4.setText(letter4);
            bt5.setText(letter15);
            bt6.setText(letter9);
            bt7.setText(word2);
            bt8.setText(letter3);
            bt9.setText(word1);
            bt10.setText(letter6);
            bt11.setText(letter2);
            bt12.setText(word2);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter10);
                bt16.setText(letter13);*/


        } else if (type == 4) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter13);
            bt2.setText(letter10);
            bt3.setText(word3);
            bt4.setText(letter4);
            bt5.setText(letter6);
            bt6.setText(letter7);
            bt7.setText(letter10);
            bt8.setText(word1);
            bt9.setText(letter3);
            bt10.setText(letter12);
            bt11.setText(word4);
            bt12.setText(word2);



              /*  bt13.setText(letter14);
                bt14.setText(letter15);
                bt15.setText(word1);
                bt16.setText(word2);*/


        } else if (type == 5) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter7);
            bt2.setText(word3);
            bt3.setText(letter13);
            bt4.setText(word5);
            bt5.setText(letter4);
            bt6.setText(word4);
            bt7.setText(letter3);
            bt8.setText(word1);
            bt9.setText(letter3);
            bt10.setText(word2);
            bt11.setText(letter6);
            bt12.setText(letter8);
               /* bt13.setText(letter14);
                bt14.setText(letter12);
                bt15.setText(letter9);
                bt16.setText(letter10);*/


        } else if (type == 6) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(letter6);
            bt2.setText(letter1);
            bt3.setText(letter4);
            bt4.setText(word5);
            bt5.setText(word2);
            bt6.setText(word4);
            bt7.setText(letter9);
            bt8.setText(word6);
            bt9.setText(word1);
            bt10.setText(letter8);
            bt11.setText(word3);
            bt12.setText(letter4);
              /*  bt13.setText(word6);
                bt14.setText(letter11);
                bt15.setText(letter13);
                bt16.setText(word2);
*/


        } else if (type == 7) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word5);
            bt2.setText(letter8);
            bt3.setText(word7);
            bt4.setText(letter11);
            bt5.setText(letter4);
            bt6.setText(word2);
            bt7.setText(letter10);
            bt8.setText(word3);
            bt9.setText(word4);
            bt10.setText(word1);
            bt11.setText(word6);
            bt12.setText(letter12);
               /* bt13.setText(letter6);
                bt14.setText(word1);
                bt15.setText(letter13);
                bt16.setText(word3);*/


        } else if (type == 8) {


            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word8);
            bt3.setText(letter6);
            bt4.setText(word5);
            bt5.setText(letter3);
            bt6.setText(word4);
            bt7.setText(word7);
            bt8.setText(letter7);
            bt9.setText(word1);
            bt10.setText(word3);
            bt11.setText(word2);
            bt12.setText(letter8);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(letter10);*/

        } else if (type == 9) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word9);
            bt5.setText(word5);
            bt6.setText(word3);
            bt7.setText(letter3);
            bt8.setText(word4);
            bt9.setText(word2);
            bt10.setText(word8);
            bt11.setText(letter6);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 10) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word10);
            bt3.setText(word8);
            bt4.setText(word1);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(letter3);
            bt8.setText(word4);
            bt9.setText(word7);
            bt10.setText(word3);
            bt11.setText(word8);
            bt12.setText(word2);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 11) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
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
            bt1.setText(word2);
            bt2.setText(word1);
            bt3.setText(word8);
            bt4.setText(word8);
            bt5.setText(word5);
            bt6.setText(word9);
            bt7.setText(word7);
            bt8.setText(word11);
            bt9.setText(word6);
            bt10.setText(word3);
            bt11.setText(word4);
            bt12.setText(word10);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        } else if (type == 12) {

            StringTokenizer tokenizer = new StringTokenizer(a, ",");
            StringTokenizer word = new StringTokenizer(split_word, ",");
            String word1 = word.nextToken().trim();
            String word2 = word.nextToken().trim();
            String word3 = word.nextToken().trim();
            String word4 = word.nextToken().trim();
            String word5 = word.nextToken().trim();
            String word6 = word.nextToken().trim();
            String word7 = word.nextToken().trim();
            String word8 = word.nextToken().trim();
            String word9 = word.nextToken().trim();
            String word10 = word.nextToken().trim();
            String word11 = word.nextToken().trim();
            String word12 = word.nextToken().trim();
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
            bt1.setText(word6);
            bt2.setText(word1);
            bt3.setText(word12);
            bt4.setText(word5);
            bt5.setText(word11);
            bt6.setText(word9);
            bt7.setText(word10);
            bt8.setText(word4);
            bt9.setText(word3);
            bt10.setText(word2);
            bt11.setText(word8);
            bt12.setText(word7);
              /*  bt13.setText(letter6);
                bt14.setText(letter11);
                bt15.setText(word2);
                bt16.setText(word8);*/

        }

    }

    public void create_shortcut () {
        addShortcut();
/*
        Intent shortcutIntent = new Intent(getApplicationContext(), Solli_adi_multiplayer.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        int flags = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT;
        shortcutIntent.addFlags(flags);

        Intent addIntent = new Intent();
        addIntent.putExtra("duplicate", false);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.multiplayer));
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource
                .fromContext(getApplicationContext(), R.drawable.mul_logo));

        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
        Toast.makeText(this, "நிகழ்நேர போட்டிக்கான Shortcut Icon உருவாக்கபட்டது", Toast.LENGTH_SHORT).show();
        sp.putString(Solli_adi_multiplayer.this,"mul_shortcut","on");


        short_cut.setVisibility(View.INVISIBLE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            Intent shortcutIntent1 = new Intent(getApplicationContext(), Solli_adi_multiplayer.class);
            shortcutIntent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shortcutIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            shortcutIntent1.putExtra("duplicate", false);
            shortcutIntent1.setAction(Intent.ACTION_MAIN);


            ShortcutInfoCompat shortcut = new ShortcutInfoCompat.Builder(Solli_adi_multiplayer.this, "shortcut")
                    .setShortLabel(getResources().getString(R.string.multiplayer))
                    .setIcon(IconCompat.createWithResource(getApplicationContext(), R.drawable.mul_logo))
                    .setIntent(shortcutIntent1)
                    .build();

            ShortcutManagerCompat.requestPinShortcut(getApplicationContext(), shortcut);
            sp.putString(Solli_adi_multiplayer.this,"mul_shortcut","on");
            short_cut.setVisibility(View.INVISIBLE);


            Handler handler8 = new Handler();
            handler8.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Solli_adi_multiplayer.this, "நிகழ்நேர போட்டிக்கான Shortcut Icon உருவாக்கபட்டது", Toast.LENGTH_SHORT).show();
                }
            }, 1000);


        }*/
        //   sps.put
        //   String(New_Main_Activity.this, "check_box", "on");
    }

    private void addShortcut () {
        //Adding shortcut for New_Main_Activity
        //on Home screen
        Intent shortcutIntentn = new Intent(getApplicationContext(),
                Solli_adi_multiplayer.class);

        shortcutIntentn.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntentn);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, R.string.multiplayer);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                        R.drawable.mul_logo));

        addIntent
                .setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        addIntent.putExtra("duplicate", false);  //may it's already there so don't duplicate
        getApplicationContext().sendBroadcast(addIntent);
        sp.putString(Solli_adi_multiplayer.this, "mul_shortcut", "on");
        short_cut.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "நிகழ்நேர போட்டிக்கான Shortcut Icon உருவாக்கபட்டது", Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Intent shortcutIntent = new Intent(getApplicationContext(), Solli_adi_multiplayer.class);
            shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            shortcutIntent.setAction(Intent.ACTION_MAIN);

            ShortcutInfoCompat shortcut = new ShortcutInfoCompat.Builder(Solli_adi_multiplayer.this, "shortcut")
                    .setShortLabel(getResources().getString(R.string.em))
                    .setIcon(IconCompat.createWithResource(getApplicationContext(), R.drawable.mul_logo))
                    .setIntent(shortcutIntent)
                    .build();
            ShortcutManagerCompat.requestPinShortcut(getApplicationContext(), shortcut, null);

            sp.putString(Solli_adi_multiplayer.this, "mul_shortcut", "on");
            short_cut.setVisibility(View.INVISIBLE);
            Handler handler8 = new Handler();
            handler8.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Solli_adi_multiplayer.this, "நிகழ்நேர போட்டிக்கான Shortcut Icon உருவாக்கபட்டது", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }


    }

    public void startdialog () {
        final Dialog openDialog_s = new Dialog(Solli_adi_multiplayer.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.guid_dialog);
        openDialog_s.setCancelable(false);
        GifImageView gif_load = (GifImageView) openDialog_s.findViewById(R.id.gif_load);
        TextView done_exit = (TextView) openDialog_s.findViewById(R.id.done_exit);
      /*  Glide.with(Word_search_main.this)
                .load(getResources().getDrawable(R.drawable.gif_load)).asGif()
                .crossFade()
                .into(gif_load);*/
        gif_load.setBackgroundResource(R.drawable.gif_load);
        done_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_s.dismiss();
                sp.putString(Solli_adi_multiplayer.this, "startdialog", "yes");
            }
        });

        openDialog_s.show();
    }

    public void price_update ( int score){
        ////////////////Prize//////////////////
        prize_data_update(Solli_adi_multiplayer.this, score);
        ////////////////Prize//////////////////
    }

}

