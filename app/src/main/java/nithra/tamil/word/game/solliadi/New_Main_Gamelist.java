package nithra.tamil.word.game.solliadi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.example.games.basegameutils.BaseGameActivity;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Word_search_main;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.Word_search_levels;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.status_act;

public class New_Main_Gamelist extends BaseGameActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, RoomUpdateListener, RealTimeMessageReceivedListener, OnInvitationReceivedListener, RoomStatusUpdateListener, Download_completed {
    RippleView g1_solvilyatu, g2_solukulsol, g3_innaisorkalikandupidi, g4_padamparthukandupidi, g5_padathirkulkandupidi, g6_kuripumulamkandupidi, g7_piramolisorkalikandupidi, g8_seerpaduthu, g9_puthirukupathil, g10_pillaithruthu, g11_varupadukali_kandupidi, g12_aathirsolli_kandupidi, g13_poruthuga, g14_tirukural, g15_vinadivina, g16_koditaidaingaliniraipavm, g17_varthaithadal;
    RippleView g18_thadammariyavarthaigal, g19_missingwords, g20_6differences;
    TextView move_back;
    static SharedPreference sps = new SharedPreference();
    SoundPool click;
    int soundId1;
    int sv = 0;
    LinearLayout prize_game, multiplayer_games;
    FloatingActionButton down_arrow;
    ScrollView scroll_view;
    int i = 0;

    int sc_x = 0, sc_y = 0;
    GoogleApiClient mGoogleApiClient;
    int mutiplayer_siginin = 0;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    Newgame_DataBaseHelper5 newhelper5;
    public static final String TAG = "SavedGames";
    LinearLayout adds;
    ImageView colour_change;
    RelativeLayout back_main, type1, type2, type3, type4, type5, type7, prize_lay, et_2;
    TextView heading_txt1, heading_txt2, heading_txt3, heading_txt4;
    int scrooly = 0;
    int oldscrollY = 0;
    TextView arrow_click, arrow_click_t3, ex_name, nitification;
    ImageView refresh, how_to_play;
    String downok = "", downnodata = "";
    int downcheck = 0;
    DownloadFileAsync downloadFileAsync;
    ProgressDialog mProgressDialog;
    ProgressDialog nProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    SQLiteDatabase myDB = null;
    static int mul_tival = 0;
    private static final String LOADING_PHRASE_CONFIG_KEY = "app_sort_order";
    private static final String LOADING_PHRASE_CONFIG_KEY2 = "SolliadiPrize";

    static LinearLayout nativeAdLayout;
    static NativeBannerAd nativeBannerAd;

    static NativeBannerAd nativeBannerAd_senthamil;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    SharedPreference sp = new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__main_);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Sound Pool Sounds
        click = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = click.load(New_Main_Gamelist.this, R.raw.click, 1);

        myDbHelper = new DataBaseHelper(New_Main_Gamelist.this);
        newhelper = new Newgame_DataBaseHelper(New_Main_Gamelist.this);
        newhelper2 = new Newgame_DataBaseHelper2(New_Main_Gamelist.this);
        newhelper3 = new Newgame_DataBaseHelper3(New_Main_Gamelist.this);
        newhelper4 = new Newgame_DataBaseHelper4(New_Main_Gamelist.this);
        newhelper5 = new Newgame_DataBaseHelper5(New_Main_Gamelist.this);

        String snd = sps.getString(New_Main_Gamelist.this, "snd");
        if (snd.equals("off")) {
            sv = 0;
        } else if (snd.equals("on")) {
            sv = 1;
        }
        AudienceNetworkAds.initialize(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES) // Games
                .addScope(Drive.SCOPE_APPFOLDER) // SavedGames
                .build();
        mul_tival = 1;
        scroll_view = (ScrollView) findViewById(R.id.scroll_view);
        g1_solvilyatu = (RippleView) findViewById(R.id.g1_solvilyatu);
        g18_thadammariyavarthaigal = (RippleView) findViewById(R.id.g18_jumblewords);
        g19_missingwords = (RippleView) findViewById(R.id.g19_missingwords);
        g20_6differences = (RippleView) findViewById(R.id.g20_6differences);
        g2_solukulsol = (RippleView) findViewById(R.id.g2_solukulsol);
        g3_innaisorkalikandupidi = (RippleView) findViewById(R.id.g3_innaisorkalikandupidi);
        g4_padamparthukandupidi = (RippleView) findViewById(R.id.g4_padamparthukandupidi);
        g5_padathirkulkandupidi = (RippleView) findViewById(R.id.g5_padathirkulkandupidi);
        g6_kuripumulamkandupidi = (RippleView) findViewById(R.id.g6_kuripumulamkandupidi);
        g7_piramolisorkalikandupidi = (RippleView) findViewById(R.id.g7_piramolisorkalikandupidi);
        g8_seerpaduthu = (RippleView) findViewById(R.id.g8_seerpaduthu);
        g9_puthirukupathil = (RippleView) findViewById(R.id.g9_puthirukupathil);
        g10_pillaithruthu = (RippleView) findViewById(R.id.g10_pillaithruthu);
        g11_varupadukali_kandupidi = (RippleView) findViewById(R.id.g11_varupadukali_kandupidi);
        g12_aathirsolli_kandupidi = (RippleView) findViewById(R.id.g12_aathirsolli_kandupidi);
        g13_poruthuga = (RippleView) findViewById(R.id.g13_poruthuga);
        g14_tirukural = (RippleView) findViewById(R.id.g14_tirukural);
        g15_vinadivina = (RippleView) findViewById(R.id.g15_vinadivina);
        g16_koditaidaingaliniraipavm = (RippleView) findViewById(R.id.g16_koditaidaingaliniraipavm);
        g17_varthaithadal = (RippleView) findViewById(R.id.g17_varthaithadal);
        prize_game = (LinearLayout) findViewById(R.id.prize_game);
        multiplayer_games = (LinearLayout) findViewById(R.id.multiplayer_games);
        move_back = (TextView) findViewById(R.id.move_back);
        down_arrow = (FloatingActionButton) findViewById(R.id.down_arrow);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        nitification = (TextView) findViewById(R.id.notification);
        refresh = (ImageView) findViewById(R.id.refresh);
        how_to_play = (ImageView) findViewById(R.id.how_to_play);
        back_main = (RelativeLayout) findViewById(R.id.back_main);
        type1 = (RelativeLayout) findViewById(R.id.type1);
        type2 = (RelativeLayout) findViewById(R.id.type2);
        type3 = (RelativeLayout) findViewById(R.id.type3);
        type4 = (RelativeLayout) findViewById(R.id.type4);
        type5 = (RelativeLayout) findViewById(R.id.type5);
        type7 = (RelativeLayout) findViewById(R.id.type7);
        prize_lay = (RelativeLayout) findViewById(R.id.prize_lay);
        prize_enable();
        sp.putString(New_Main_Gamelist.this, "sd_prize_st", "");

        if (sp.getInt(New_Main_Gamelist.this, "remoteConfig_prize") == 1) {
            //prices.setVisibility(View.VISIBLE);
            prize_lay.setVisibility(View.VISIBLE);
        } else {
            // prices.setVisibility(View.GONE);
            prize_lay.setVisibility(View.GONE);
        }


        et_2 = (RelativeLayout) findViewById(R.id.et_2);

        heading_txt1 = (TextView) findViewById(R.id.heading_txt1);
        heading_txt2 = (TextView) findViewById(R.id.heading_txt2);
        heading_txt3 = (TextView) findViewById(R.id.heading_txt3);
        heading_txt4 = (TextView) findViewById(R.id.heading_txt4);
        arrow_click = (TextView) findViewById(R.id.arrow_click);
        arrow_click_t3 = (TextView) findViewById(R.id.arrow_click_t3);
        ex_name = (TextView) findViewById(R.id.ex_name);


        g1_solvilyatu.setOnClickListener(this);
        g1_solvilyatu.setOnClickListener(this);
        g2_solukulsol.setOnClickListener(this);
        g3_innaisorkalikandupidi.setOnClickListener(this);
        g4_padamparthukandupidi.setOnClickListener(this);
        g5_padathirkulkandupidi.setOnClickListener(this);
        g6_kuripumulamkandupidi.setOnClickListener(this);
        g7_piramolisorkalikandupidi.setOnClickListener(this);
        g8_seerpaduthu.setOnClickListener(this);
        g9_puthirukupathil.setOnClickListener(this);
        g10_pillaithruthu.setOnClickListener(this);
        g11_varupadukali_kandupidi.setOnClickListener(this);
        g12_aathirsolli_kandupidi.setOnClickListener(this);
        g13_poruthuga.setOnClickListener(this);
        g14_tirukural.setOnClickListener(this);
        g15_vinadivina.setOnClickListener(this);
        g16_koditaidaingaliniraipavm.setOnClickListener(this);
        g17_varthaithadal.setOnClickListener(this);
        g18_thadammariyavarthaigal.setOnClickListener(this);
        g19_missingwords.setOnClickListener(this);
        g20_6differences.setOnClickListener(this);
        prize_game.setOnClickListener(this);
        multiplayer_games.setOnClickListener(this);
        move_back.setOnClickListener(this);
        down_arrow.setOnClickListener(this);

        Animation w_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button1and3_animation);
        type1.startAnimation(w_game);

        Animation s_game = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button2_animation);
        type4.startAnimation(s_game);
        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
        arrow_click.startAnimation(blink);
        arrow_click_t3.startAnimation(blink);
        ex_name.startAnimation(blink);
              scroll_view.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (scroll_view.getChildAt(0).getBottom()
                                <= (scroll_view.getHeight() + scroll_view.getScrollY())) {
                            //scroll view is at bottom
                            down_arrow.setVisibility(View.GONE);
                        } else {
                            //scroll view is not at bottom
                            down_arrow.setVisibility(View.VISIBLE);
                        }
                    }
                });
        myDB = this.openOrCreateDatabase("myDB", MODE_PRIVATE, null);
        Cursor cv = myDB.rawQuery("select * from noti_cal where isclose='0'", null);
        System.out.println("============cv.getcount" + cv.getCount());
        if (cv.getCount() <= 9) {
            nitification.setText("" + cv.getCount());
        } else if (cv.getCount() > 9) {
            nitification.setText("9+");
        } else {
            nitification.setText("0");
        }

        nitification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(New_Main_Gamelist.this, Noti_Fragment.class);
                startActivity(i);
            }
        });
    /*    refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        how_to_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intro();
                //gamecount_list(New_Main_Gamelist.this);
            }
        });
        prize_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prize_game_start();
            }
        });
        et_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //multiplayer_games_start();
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (sps.getString(New_Main_Gamelist.this, "permission_grand").equals("")) {
                        sps.putString(New_Main_Gamelist.this, "permission_grand", "yes");
                        //  First_register("yes");
                        AlertDialog alertDialog = new AlertDialog.Builder(New_Main_Gamelist.this).create();
                        alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய பின்வரும் permission-யை allow செய்யவேண்டும்");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        if ((ContextCompat.checkSelfPermission(New_Main_Gamelist.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                            ActivityCompat.requestPermissions(New_Main_Gamelist.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
                                        } else {
                                            if (determineConnectivity(New_Main_Gamelist.this))
                                                downloaddata();
                                            else
                                                Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        alertDialog.show();

                    } else {
                        if ((ContextCompat.checkSelfPermission(New_Main_Gamelist.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                            if (sps.getInt(New_Main_Gamelist.this, "permission") == 2) {
                                AlertDialog alertDialog = new AlertDialog.Builder(New_Main_Gamelist.this).create();
                                alertDialog.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய  settings-யில்  உள்ள permission-யை allow செய்யவேண்டும்");
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
                                if ((ContextCompat.checkSelfPermission(New_Main_Gamelist.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                    ActivityCompat.requestPermissions(New_Main_Gamelist.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
                                } else {
                                    if (determineConnectivity(New_Main_Gamelist.this))
                                        downloaddata();
                                    else
                                        Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            if ((ContextCompat.checkSelfPermission(New_Main_Gamelist.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                ActivityCompat.requestPermissions(New_Main_Gamelist.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
                            } else {
                                if (determineConnectivity(New_Main_Gamelist.this))
                                    downloaddata();
                                else
                                    Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                } else {
                    downloaddata();
                }*/

                if (determineConnectivity(New_Main_Gamelist.this))
                    downloaddata();
                else
                    Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onBackPressed() {

        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(New_Main_Gamelist.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.g1_solvilyatu:
                g1_solvilyatu_start();
                break;
            case R.id.g2_solukulsol:
                g2_solukulsol_start();
                break;
            case R.id.g3_innaisorkalikandupidi:
                g3_innaisorkalikandupidi_start();
                break;
            case R.id.g4_padamparthukandupidi:
                g4_padamparthukandupidi_start();
                break;
            case R.id.g5_padathirkulkandupidi:
                g5_padathirkulkandupidi_start();
                break;
            case R.id.g6_kuripumulamkandupidi:
                g6_kuripumulamkandupidi_start();
                break;
            case R.id.g7_piramolisorkalikandupidi:
                g7_piramolisorkalikandupidi_start();
                break;
            case R.id.g8_seerpaduthu:
                g8_seerpaduthu_start();
                break;
            case R.id.g9_puthirukupathil:
                g9_puthirukupathil_start();
                break;
            case R.id.g10_pillaithruthu:
                g10_pillaithruthu_start();
                break;
            case R.id.g11_varupadukali_kandupidi:
                g11_varupadukali_kandupidi_start();
                break;
            case R.id.g12_aathirsolli_kandupidi:
                g12_aathirsolli_kandupidi_start();
                break;
            case R.id.g13_poruthuga:
                g13_poruthuga_start();
                break;
            case R.id.g14_tirukural:
                g14_tirukural_start();
                break;
            case R.id.g15_vinadivina:
                g15_vinadivina_start();
                break;
            case R.id.g16_koditaidaingaliniraipavm:
                g16_koditaidaingaliniraipavm_start();
                break;
            case R.id.g17_varthaithadal:
                g17_varthaithadal_start();
                break;
            case R.id.g18_jumblewords:
                g18_varthaithadal_start();
                break;
            case R.id.g19_missingwords:
                g19_missingwors_start();
                break;
            case R.id.g20_6differences:
                g20_6differences_start();
                break;
            case R.id.prize_game:
                //prize_game_start();
                break;
            case R.id.multiplayer_games:
                //multiplayer_games_start();
                break;
            case R.id.down_arrow:
                //scroll_view.scrollTo(0, scroll_view.getBottom());
                if (scrooly <= oldscrollY) {
                    down_arrow.setVisibility(View.GONE);
                    //scroll up
                } else {
                    down_arrow.setVisibility(View.VISIBLE);
                }
                sc_x = sc_x + 30;
                sc_y = sc_y + 610;
                // scroll_view.smoothScrollTo(sc_x, sc_y);

                final Handler handler = new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //scroll_view.fullScroll(View.FOCUS_DOWN);
                                scroll_view.smoothScrollTo(sc_x, sc_y);
                            }
                        });
                    }
                }).start();
                break;
            case R.id.move_back:
                finish();
                break;
        }
    }


    private void prize_game_start() {
        if (determineConnectivity(New_Main_Gamelist.this)) {
            if (sps.getString(New_Main_Gamelist.this, "price_registration").equals("com")) {
                Intent i = new Intent(New_Main_Gamelist.this, Game_Status.class);
                startActivity(i);
            } else {
                if (sps.getString(New_Main_Gamelist.this, "otp_verify").equals("yes")) {
                    Intent i = new Intent(New_Main_Gamelist.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    sps.putString(New_Main_Gamelist.this, "activity_call", "main");
                    Intent i = new Intent(New_Main_Gamelist.this, Price_Login.class);
                    startActivity(i);
                }
            }
        } else {
            Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
        }
    }

    private void multiplayer_games_start() {

        mutiplayer_siginin = 0;
        sps.putString(New_Main_Gamelist.this, "multi_on", "no");
        System.out.println("=============leader board");


        myDbHelper.executeSql("UPDATE maintable SET rtm=0");
        newhelper.executeSql("UPDATE newmaintable SET rtm=0");
        newhelper2.executeSql("UPDATE newmaintable2 SET rtm=0");
        newhelper3.executeSql("UPDATE right_order SET rtm=0");
        myDbHelper.executeSql("delete from answertable where rd=3");

        final boolean appinstalled = appInstalledOrNot("com.google.android.play.games");
        if (!appinstalled) {
            AlertDialog alertDialog = new AlertDialog.Builder(New_Main_Gamelist.this).create();
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
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                if (getApiClient().isConnected()) {
                    if (isSignedIn()) {
                        mul_tival = 0;
                        finish();
                        System.out.println("=============Api already connected");
                        Intent i = new Intent(New_Main_Gamelist.this, Solli_adi_multiplayer.class);
                        startActivity(i);

                    } else {
                        beginUserInitiatedSignIn();
                        mGoogleApiClient.connect();
                        System.out.println("=============Api already connected else");
                    }
                } else {
                    if (sps.getString(New_Main_Gamelist.this, "signinagain_leader").equals("")) {
                        System.out.println("=============need to connect client");
                        final Dialog openDialog_p = new Dialog(New_Main_Gamelist.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        openDialog_p.setContentView(R.layout.googleapiclient_connect);
                        LinearLayout yes = (LinearLayout) openDialog_p.findViewById(R.id.sign_in_btn);
                        // TextView no = (TextView) openDialog_p.findViewById(R.id.no);
                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sps.putString(New_Main_Gamelist.this, "signinagain_leader", "yes");
                                sps.putString(New_Main_Gamelist.this, "signinagain", "yes");
                                Log.d(TAG, "Connecting client.");
                                mutiplayer_siginin = 1;
                                beginUserInitiatedSignIn();
                                mGoogleApiClient.connect();
                                openDialog_p.dismiss();
                            }
                        });
                        openDialog_p.show();

                    } else {
                        System.out.println("=============Api already connected");
                        mGoogleApiClient.connect();
                        mul_tival = 0;
                        finish();
                        Intent i = new Intent(New_Main_Gamelist.this, Solli_adi_multiplayer.class);
                        startActivity(i);
                    }
                }
            } else {
                Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void g19_missingwors_start() {
        if (sps.getString(New_Main_Gamelist.this, "missing_word").equals("")) {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Missing_words_levels.class);
            startActivity(main);
            sps.putString(New_Main_Gamelist.this, "missing_word", "yes");
        } else {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Missing_Words.class);
            startActivity(main);
        }
    }

    private void g20_6differences_start() {
        if (sps.getString(New_Main_Gamelist.this, "6_differece").equals("")) {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Find_differcence_between_pictures_levels.class);
            startActivity(main);
            sps.putString(New_Main_Gamelist.this, "6_differece", "yes");
        } else {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Find_difference_between_pictures.class);
            startActivity(main);
        }
    }

    private void g18_varthaithadal_start() {
        if (sps.getString(New_Main_Gamelist.this, "jamble_word").equals("")) {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Jamble_word_game_levels.class);
            startActivity(main);
            sps.putString(New_Main_Gamelist.this, "jamble_word", "yes");
        } else {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Jamble_word_game.class);
            startActivity(main);
        }
    }

    private void g17_varthaithadal_start() {
        if (sps.getString(New_Main_Gamelist.this, "Word_search_mainintro").equals("")) {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Word_search_levels.class);
            startActivity(main);
            sps.putString(New_Main_Gamelist.this, "Word_search_mainintro", "yes");
        } else {
            sps.putString(New_Main_Gamelist.this, "date", "0");
            click.play(soundId1, sv, sv, 0, 0, sv);
            Intent main = new Intent(getApplicationContext(), Word_search_main.class);
            startActivity(main);
        }
    }

    private void g16_koditaidaingaliniraipavm_start() {
        if (sps.getString(New_Main_Gamelist.this, "fill_in_blanks_intro").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Fill_in_blanks_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "fill_in_blanks_intro", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Fill_in_blanks.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g15_vinadivina_start() {
        if (sps.getString(New_Main_Gamelist.this, "Quiz_game_intro").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Quiz_game_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "Quiz_game_intro", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Quiz_Game.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g14_tirukural_start() {
        if (sps.getString(New_Main_Gamelist.this, "tirukural_s_intro").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Tirukural_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "tirukural_s_intro", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Tirukural.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g13_poruthuga_start() {
        if (sps.getString(New_Main_Gamelist.this, "match_the_fallows_intro").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, match_the_words_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "match_the_fallows_intro", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Match_tha_fallows_game.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g12_aathirsolli_kandupidi_start() {
        if (sps.getString(New_Main_Gamelist.this, "opposite_word_intro_one").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Opposite_word_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "opposite_word_intro_one", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Opposite_word.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g11_varupadukali_kandupidi_start() {
        if (sps.getString(New_Main_Gamelist.this, "oddman_intro_one").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Odd_man_out_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "oddman_intro_one", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Odd_man_out.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g10_pillaithruthu_start() {
        if (sps.getString(New_Main_Gamelist.this, "error_correction_s_intro").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, WordError_correction_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "error_correction_s_intro", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, WordError_correction.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g9_puthirukupathil_start() {
        if (sps.getString(New_Main_Gamelist.this, "riddle_intro").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Riddle_game_level.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "riddle_intro", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Riddle_game.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g1_solvilyatu_start() {
        if (sps.getString(New_Main_Gamelist.this, "wordintro_one").equals("")) {
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            sps.putString(New_Main_Gamelist.this, "open1", "l1");
            Intent i = new Intent(New_Main_Gamelist.this, Levels.class);
            startActivity(i);
            sps.putString(New_Main_Gamelist.this, "wordintro_one", "yes");
        } else {
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            sps.putString(New_Main_Gamelist.this, "open1", "l1");
            Intent i = new Intent(New_Main_Gamelist.this, Word_Game_Hard.class);
            startActivity(i);
        }
    }

    private void g2_solukulsol_start() {
        if (sps.getString(New_Main_Gamelist.this, "sosintro_one").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, SolukulSol_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "sosintro_one", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Solukul_Sol.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g3_innaisorkalikandupidi_start() {
        if (sps.getString(New_Main_Gamelist.this, "matchword_intro_one").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Match_words_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "matchword_intro_one", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Match_Word.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g5_padathirkulkandupidi_start() {
        if (sps.getString(New_Main_Gamelist.this, "pic_to_words_intro").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, pic_to_words_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "pic_to_words_intro", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Find_words_from_picture.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g7_piramolisorkalikandupidi_start() {
        if (sps.getString(New_Main_Gamelist.this, "english_to_tamil_intro_one").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Ote_to_tamil_Levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "english_to_tamil_intro_one", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Ote_to_Tamil.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g6_kuripumulamkandupidi_start() {
        if (sps.getString(New_Main_Gamelist.this, "hintintro_one").equals("")) {
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            sps.putString(New_Main_Gamelist.this, "hint", "h1");
            Intent i = new Intent(New_Main_Gamelist.this, Clue_Levels.class);
            startActivity(i);
            sps.putString(New_Main_Gamelist.this, "hintintro_one", "yes");
        } else {
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            sps.putString(New_Main_Gamelist.this, "hint", "h1");
            Intent i = new Intent(New_Main_Gamelist.this, Clue_Game_Hard.class);
            startActivity(i);
        }
    }

    private void g8_seerpaduthu_start() {
        if (sps.getString(New_Main_Gamelist.this, "Makeword_Rightorder").equals("")) {
            Intent i = new Intent(New_Main_Gamelist.this, Make_wordrightorder_levels.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "Makeword_Rightorder", "yes");
        } else {
            Intent i = new Intent(New_Main_Gamelist.this, Makeword_Rightorder.class);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            startActivity(i);
            click.play(soundId1, sv, sv, 0, 0, sv);
        }
    }

    private void g4_padamparthukandupidi_start() {
        if (sps.getString(New_Main_Gamelist.this, "picintro_one").equals("")) {
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            sps.putString(New_Main_Gamelist.this, "pic1", "p1");
            Intent i = new Intent(New_Main_Gamelist.this, Picture_Levels.class);
            startActivity(i);
            sps.putString(New_Main_Gamelist.this, "picintro_one", "yes");
        } else {
            click.play(soundId1, sv, sv, 0, 0, sv);
            sps.putString(New_Main_Gamelist.this, "date", "0");
            sps.putString(New_Main_Gamelist.this, "pic1", "p1");
            Intent i = new Intent(New_Main_Gamelist.this, Picture_Game_Hard.class);
            startActivity(i);
        }
    }

    public static boolean determineConnectivity(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.getState() == NetworkInfo.State.CONNECTED;
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onInvitationReceived(Invitation invitation) {

    }

    @Override
    public void onInvitationRemoved(String s) {

    }

    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {

    }

    @Override
    public void onRoomConnecting(Room room) {

    }

    @Override
    public void onRoomAutoMatching(Room room) {

    }

    @Override
    public void onPeerInvitedToRoom(Room room, List<String> list) {

    }

    @Override
    public void onPeerDeclined(Room room, List<String> list) {

    }

    @Override
    public void onPeerJoined(Room room, List<String> list) {

    }

    @Override
    public void onPeerLeft(Room room, List<String> list) {

    }

    @Override
    public void onConnectedToRoom(Room room) {

    }

    @Override
    public void onDisconnectedFromRoom(Room room) {

    }

    @Override
    public void onPeersConnected(Room room, List<String> list) {

    }

    @Override
    public void onPeersDisconnected(Room room, List<String> list) {

    }

    @Override
    public void onP2PConnected(String s) {

    }

    @Override
    public void onP2PDisconnected(String s) {

    }

    @Override
    public void onRoomCreated(int i, Room room) {

    }

    @Override
    public void onJoinedRoom(int i, Room room) {

    }

    @Override
    public void onLeftRoom(int i, String s) {

    }

    @Override
    public void onRoomConnected(int i, Room room) {

    }

    @Override
    public void download_completed(String status) {

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

    @Override
    protected void onResume() {
        super.onResume();

        if (sps.getInt(New_Main_Gamelist.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {

            nativeAdLayout = (LinearLayout) findViewById(R.id.native_banner_ad_container);
            if (Utils.isNetworkAvailable(New_Main_Gamelist.this)) {

                //fb_native(New_Main_Gamelist.this,nativeAdLayout);
                //naive_banner(nativeAdLayout);
            } else {
                nativeAdLayout.setVisibility(View.GONE);
            }

          /*  if (sps.getInt(New_Main_Gamelist.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(New_Main_Gamelist.this, adds);
            } else {
                if (Utils.isNetworkAvailable(New_Main_Gamelist.this)) {
                    sps.putInt(New_Main_Gamelist.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(New_Main_Gamelist.this);
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
        myDB = this.openOrCreateDatabase("myDB", MODE_PRIVATE, null);
        Cursor cv = myDB.rawQuery("select * from noti_cal where isclose='0'", null);
        System.out.println("============cv.getcount" + cv.getCount());
        if (cv.getCount() <= 9) {
            nitification.setText("" + cv.getCount());
        } else if (cv.getCount() > 9) {
            nitification.setText("9+");
        } else {
            nitification.setText("0");
        }

    }

    public void downloaddata() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(New_Main_Gamelist.this);                            /*.setTitle("Delete entry")*/
        alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய வேண்டுமா ?")
                .setPositiveButton("ஆம்", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
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
                            Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("இல்லை", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void downloadcheck(final String lastid, final String daily) {
        Utils.mProgress(New_Main_Gamelist.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", false).show();
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
                nameValuePairs.add(new BasicNameValuePair("email", sps.getString(New_Main_Gamelist.this, "email")));
                //nameValuePairs.add(new BasicNameValuePair("type", "a2z"));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(MainActivity.data_check);
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

                                    String newName = json_data.getString("answer").toString().replaceAll(" ", "");

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
                                        if (exists("https://nithra.mobi/solliadi/" + sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip") == true) {
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

    public void checkmemory() {
        String url = "";
        url = "https://nithra.mobi/solliadi/" + sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip";


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


        StatFs stat = new StatFs(getFilesDir().getPath());
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

                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


    public void startDownload() {


        String str_url = "https://nithra.mobi/solliadi/" + sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip";

        //     new DownloadFileAsync().execute(str_url);


        downloadFileAsync = new DownloadFileAsync();
        downloadFileAsync.execute(str_url);
    }

    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                nProgressDialog = new ProgressDialog(this);
                nProgressDialog.setMessage("படங்கள் பதிவிறக்கம் செய்யப்படுகிறது காத்திருக்கவும்.... ");
                nProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                nProgressDialog.setCancelable(false);
                nProgressDialog.show();
                // playy();

                return nProgressDialog;

            default:
                return null;
        }
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


                File file = new File(SDCardRoot + "/Nithra/solliadi/", sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip");

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

                unpackZip(sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip");

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
            //  nProgressDialog.dismiss();
            deletezip();


            if (unused != null && unused.equals("ERROR_DOW")) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(New_Main_Gamelist.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setTitle("Network connection not available, please check it!");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        downloadFileAsync.isCancelled();
                        downloadFileAsync.cancel(true);

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            try {

            } catch (Exception e) {
                System.out.println("result=======////==" + e);
            }
        }
     /*   protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC",progress[0]);
            nProgressDialog.setProgress(Integer.parseInt(progress[0]));
			*//*if(!isNetworkAvailable()){
                downloadFileAsync.isCancelled();
				//downloadFileAsync.cancel(true);

			}*//*
        }*/
    }

    public void deletezip() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("filename", sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip"));
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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
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
                nameValuePairs.add(new BasicNameValuePair("email", sps.getString(New_Main_Gamelist.this, "email")));
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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
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
                                    String newName = json_data.getString("answer").toString().replaceAll(" ", "");
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
                                        if (exists("https://nithra.mobi/solliadi/" + sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip") == true) {
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

            /*    Cursor c2s = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0'");
                c2s.moveToFirst();
                int cs2s = c2s.getCount();
                p_id.setText("(" + cs2s + ")");

                Cursor c2c = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='0'");
                c2c.moveToFirst();
                int cs2c = c2c.getCount();
                c_id.setText("(" + cs2c + ")");

                Cursor c2ss = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='0'");
                c2ss.moveToFirst();
                int cs2ss = c2ss.getCount();
                ss_id.setText("(" + cs2ss + ")");

                Cursor c2s1 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0'");
                c2s1.moveToFirst();
                int cs2s1 = c2s1.getCount();
                s_id.setText("(" + cs2s1 + ")");

                Cursor cn1 = newhelper.getQry("select * from newmaintable where gameid='5' and isfinish='0'");
                cn1.moveToFirst();
                int cns1 = cn1.getCount();
                oddmanout_ss_id.setText("(" + cns1 + ")");

                Cursor cn2 = newhelper.getQry("select * from newmaintable where gameid='6' and isfinish='0'");
                cn2.moveToFirst();
                int cns2 = cn2.getCount();
                matchwords_no.setText("(" + cns2 + ")");

                Cursor cnss1 = newhelper2.getQry("select * from newmaintable2 where gameid='7' and isfinish='0'");
                cnss1.moveToFirst();
                int cnsss1 = cnss1.getCount();
                opposite_word_id.setText("(" + cnsss1 + ")");

                Cursor cn22 = newhelper2.getQry("select * from newmaintable2 where gameid='8' and isfinish='0'");
                cn22.moveToFirst();
                int cns22 = cn22.getCount();
                english_to_tamil_id.setText("(" + cns22 + ")");

                Cursor cn23 = newhelper3.getQry("select * from right_order where gameid='9' and isfinish='0'");
                cn23.moveToFirst();
                int cns23 = cn23.getCount();
                right_order_id.setText("(" + cns23 + ")");

                Cursor cn24 = newhelper3.getQry("select * from right_order where gameid='10' and isfinish='0'");
                cn24.moveToFirst();
                int cns24 = cn24.getCount();
                riddle_id.setText("(" + cns24 + ")");

                Cursor cn25 = newhelper3.getQry("select * from right_order where gameid='12' and isfinish='0'");
                cn25.moveToFirst();
                int cns25 = cn25.getCount();
                tirukuralid.setText("(" + cns25 + ")");

                Cursor cn26 = newhelper3.getQry("select * from right_order where gameid='11' and isfinish='0'");
                cn26.moveToFirst();
                int cns26 = cn26.getCount();
                error_correction_id.setText("(" + cns26 + ")");

                Cursor cn26s = newhelper4.getQry("select * from newgamesdb4 where gameid='13' and isfinish='0'");
                cn26s.moveToFirst();
                int cns26s = cn26s.getCount();
                fill_in_blanks_id.setText("(" + cns26s + ")");

                Cursor cn26ws = newhelper5.getQry("select * from newgames5 where gameid='17' and isfinish='0'");
                cn26ws.moveToFirst();
                int cns26ws = cn26ws.getCount();
                eng_to_tamil_no.setText("(" + cns26ws + ")");

                Cursor cn27ws = newhelper5.getQry("select * from newgames5 where gameid='16' and isfinish='0'");
                cn27ws.moveToFirst();
                int cns27ws = cn27ws.getCount();
                pic_to_words_no.setText("(" + cns27ws + ")");

                Cursor cn28ws = newhelper5.getQry("select * from newgames5 where gameid='15' and isfinish='0'");
                cn28ws.moveToFirst();
                int cns28ws = cn28ws.getCount();
                match_words_no.setText("(" + cns28ws + ")");*/

                Utils.mProgress.dismiss();
                if (downcheck == 2) {
                    new AlertDialog.Builder(New_Main_Gamelist.this)
                            /*.setTitle("Delete entry")*/
                            .setMessage("பதிவுகள் ஏதும் இல்லை .பிறகு முயற்சிக்கவும் ")
                            .setPositiveButton("சரி", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    downcheck = 0;

                } else {

                    new AlertDialog.Builder(New_Main_Gamelist.this)
                            /*.setTitle("Delete entry")*/
                            .setMessage("புதிய பதிவுகள் ஏற்றப்பட்டது. விளையாடி மகிழவும்.   ")
                            .setPositiveButton("சரி", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    downcheck = 0;
                    downok = "";
                    downnodata = "";


                }


            }
        }.execute();
    }

    public void intro() {
        final Dialog openDialog = new Dialog(New_Main_Gamelist.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.introsdialog_web);
        WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
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
        intros.loadUrl("file:///android_asset/web.html");
        openDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 151: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sps.putInt(New_Main_Gamelist.this, "permission", 1);
                    downloaddata();
                } else {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = false;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                        }
                        if (!showRationale) {
                            sps.putInt(New_Main_Gamelist.this, "permission", 2);
                        } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                            sps.putInt(New_Main_Gamelist.this, "permission", 0);
                        }
                    }
                }
            }
            break;
        }
    }

    public void gamecount_list(Context context) {

        Dialog openDialog_s = new Dialog(New_Main_Gamelist.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.game_list_count);
        TextView g1_solvilyatu_c, g2_solukulsol_c, g3_innaisorkalikandupidi_c, g4_padamparthukandupidi_c, g5_padathirkulkandupidi_c, g6_kuripumulamkandupidi_c, g7_piramolisorkalikandupidi_c, g8_seerpaduthu_c, g9_puthirukupathil_c, g10_pillaithruthu, g11_varupadukali_kandupidi_c, g12_aathirsolli_kandupidi_c, g13_poruthuga_c, g14_tirukural_c, g15_vinadivina_c, g16_koditaidaingaliniraipavm_c, g17_varthaithadal_c;
        TextView g18_jumblewords_c, g19_missingwords_c, g20_6differences_c;
        openDialog_s.show();


        myDbHelper = new DataBaseHelper(context);
        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);
        newhelper5 = new Newgame_DataBaseHelper5(context);
        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(context);
        g4_padamparthukandupidi_c = (TextView) openDialog_s.findViewById(R.id.g4_padamparthukandupidi_c);
        Cursor c2s = myDbHelper.getQry("select * from maintable where gameid='1' and isfinish='0'");
        if (c2s != null && c2s.moveToFirst()) {
            if (c2s.getCount() != 0) {
                int cs2s = c2s.getCount();
                if (cs2s != 0) {
                    g4_padamparthukandupidi_c.setText("(" + cs2s + ")");
                } else {
                    g4_padamparthukandupidi_c.setText("");
                }
            } else if (c2s.getCount() == 0) {
                g4_padamparthukandupidi_c.setText("");
            }
        } else {
            g4_padamparthukandupidi_c.setText("");
        }

        g6_kuripumulamkandupidi_c = (TextView) openDialog_s.findViewById(R.id.g6_kuripumulamkandupidi_c);
        Cursor c2c = myDbHelper.getQry("select * from maintable where gameid='2' and isfinish='0'");
        if (c2c != null && c2c.moveToFirst()) {
            if (c2c.getCount() != 0) {
                int cs2c = c2c.getCount();
                if (cs2c != 0) {
                    g6_kuripumulamkandupidi_c.setText("(" + cs2c + ")");
                } else {
                    g6_kuripumulamkandupidi_c.setText("");
                }
            } else if (c2c.getCount() == 0) {
                g6_kuripumulamkandupidi_c.setText("");
            }
        } else {
            g6_kuripumulamkandupidi_c.setText("");
        }
        g2_solukulsol_c = (TextView) openDialog_s.findViewById(R.id.g2_solukulsol_c);
        Cursor c2ss = myDbHelper.getQry("select * from maintable where gameid='3' and isfinish='0'");
        if (c2ss != null && c2ss.moveToFirst()) {
            System.out.println("------------c2ss.getCount()" + c2ss.getCount());
            if (c2ss.getCount() != 0) {
                int cs2ss = c2ss.getCount();
                if (cs2ss != 0) {
                    g2_solukulsol_c.setText("(" + cs2ss + ")");
                } else {
                    g2_solukulsol_c.setText("");
                }
            } else if (c2ss.getCount() == 0) {
                g2_solukulsol_c.setText("");
            }
        } else {
            g2_solukulsol_c.setText("");
        }

        g1_solvilyatu_c = (TextView) openDialog_s.findViewById(R.id.g1_solvilyatu_c);

        Cursor c2s1 = myDbHelper.getQry("select * from maintable where gameid='4' and isfinish='0'");
        if (c2s1 != null && c2s1.moveToFirst()) {
            if (c2s1.getCount() != 0) {
                int cs2s1 = c2s1.getCount();
                if (cs2s1 != 0) {
                    g1_solvilyatu_c.setText("(" + cs2s1 + ")");
                } else {
                    g1_solvilyatu_c.setText("");
                }
            } else if (c2s1.getCount() == 0) {
                g1_solvilyatu_c.setText("");
            }
        } else {
            g1_solvilyatu_c.setText("");
        }

        g11_varupadukali_kandupidi_c = (TextView) openDialog_s.findViewById(R.id.g11_varupadukali_kandupidi_c);

        Cursor cn1 = newhelper.getQry("select * from newmaintable where gameid='5' and isfinish='0'");
        if (cn1 != null && cn1.moveToFirst()) {
            if (cn1.getCount() != 0) {
                int cns1 = cn1.getCount();
                if (cns1 != 0) {
                    g11_varupadukali_kandupidi_c.setText("(" + cns1 + ")");
                } else {
                    g11_varupadukali_kandupidi_c.setText("");
                }
            } else if (cn1.getCount() == 0) {
                g11_varupadukali_kandupidi_c.setText("");
            }
        } else {
            g11_varupadukali_kandupidi_c.setText("");
        }

        g3_innaisorkalikandupidi_c = (TextView) openDialog_s.findViewById(R.id.g3_innaisorkalikandupidi_c);

        Cursor cn2 = newhelper.getQry("select * from newmaintable where gameid='6' and isfinish='0'");
        if (cn2 != null && cn2.moveToFirst()) {
            if (cn2.getCount() != 0) {
                int cns2 = cn2.getCount();
                if (cns2 != 0) {
                    g3_innaisorkalikandupidi_c.setText("(" + cns2 + ")");
                } else {
                    g3_innaisorkalikandupidi_c.setText("");
                }
            } else if (cn2.getCount() == 0) {
                g3_innaisorkalikandupidi_c.setText("");
            }
        } else {
            g3_innaisorkalikandupidi_c.setText("");
        }
        g12_aathirsolli_kandupidi_c = (TextView) openDialog_s.findViewById(R.id.g12_aathirsolli_kandupidi_c);

        Cursor cnss1 = newhelper2.getQry("select * from newmaintable2 where gameid='7' and isfinish='0'");
        System.out.println("------------cnss1.getCount()" + cnss1.getCount());
        if (cnss1 != null && cnss1.moveToFirst()) {
            if (cnss1.getCount() != 0) {
                int cnsss1 = cnss1.getCount();
                if (cnsss1 != 0) {
                    System.out.println("------------opposite_word_id" + cnss1.getCount());
                    g12_aathirsolli_kandupidi_c.setText("(" + cnsss1 + ")");
                } else {
                    System.out.println("------------opposite_word_id==0" + cnss1.getCount());
                    g12_aathirsolli_kandupidi_c.setText("");
                }
            } else if (cnss1.getCount() == 0) {
                System.out.println("------------opposite_word_id==el0" + cnss1.getCount());
                g12_aathirsolli_kandupidi_c.setText("");
            }
        } else {
            g12_aathirsolli_kandupidi_c.setText("");
            System.out.println("------------opposite_word_id==odd" + cnss1.getCount());

        }

        g7_piramolisorkalikandupidi_c = (TextView) openDialog_s.findViewById(R.id.g7_piramolisorkalikandupidi_c);

        Cursor cn22 = newhelper2.getQry("select * from newmaintable2 where gameid='8' and isfinish='0'");
        if (cn22 != null && cn22.moveToFirst()) {
            if (cn22.getCount() != 0) {
                int cns22 = cn22.getCount();
                if (cns22 != 0) {
                    g7_piramolisorkalikandupidi_c.setText("(" + cns22 + ")");
                } else {
                    g7_piramolisorkalikandupidi_c.setText("");
                }
            } else if (cn22.getCount() == 0) {
                g7_piramolisorkalikandupidi_c.setText("");
            }
        } else {
            g7_piramolisorkalikandupidi_c.setText("");
        }

        g8_seerpaduthu_c = (TextView) openDialog_s.findViewById(R.id.g8_seerpaduthu_c);

        Cursor cn23 = newhelper3.getQry("select * from right_order where gameid='9' and isfinish='0'");
        if (cn23 != null && cn23.moveToFirst()) {
            if (cn23.getCount() != 0) {
                int cns23 = cn23.getCount();
                if (cns23 != 0) {
                    g8_seerpaduthu_c.setText("(" + cns23 + ")");
                } else {
                    g8_seerpaduthu_c.setText("");
                }
            } else if (cn23.getCount() == 0) {
                g8_seerpaduthu_c.setText("");
            }
        } else {
            g8_seerpaduthu_c.setText("");
        }
        g9_puthirukupathil_c = (TextView) openDialog_s.findViewById(R.id.g9_puthirukupathil_c);
        Cursor cn24 = newhelper3.getQry("select * from right_order where gameid='10' and isfinish='0'");
        if (cn24 != null && cn24.moveToFirst()) {
            if (cn24.getCount() != 0) {
                int cns24 = cn24.getCount();
                if (cns24 != 0) {
                    g9_puthirukupathil_c.setText("(" + cns24 + ")");
                } else {
                    g9_puthirukupathil_c.setText("");
                }
            } else if (cn24.getCount() == 0) {
                g9_puthirukupathil_c.setText("");
            }
        } else {
            g9_puthirukupathil_c.setText("");
        }

        g14_tirukural_c = (TextView) openDialog_s.findViewById(R.id.g14_tirukural_c);

        Cursor cn25 = newhelper3.getQry("select * from right_order where gameid='12' and isfinish='0'");
        if (cn25 != null && cn25.moveToFirst()) {
            if (cn25.getCount() != 0) {
                int cns25 = cn25.getCount();
                if (cns25 != 0) {
                    g14_tirukural_c.setText("(" + cns25 + ")");
                } else {
                    g14_tirukural_c.setText("");
                }
            } else if (cn25.getCount() == 0) {
                g14_tirukural_c.setText("");
            }
        } else {
            g14_tirukural_c.setText("");
        }

        g10_pillaithruthu = (TextView) openDialog_s.findViewById(R.id.g10_pillaithruthu_c);

        Cursor cn26 = newhelper3.getQry("select * from right_order where gameid='11' and isfinish='0'");
        if (cn26 != null && cn26.moveToFirst()) {
            if (cn26.getCount() != 0) {
                int cns26 = cn26.getCount();
                if (cns26 != 0) {
                    g10_pillaithruthu.setText("(" + cns26 + ")");
                } else {
                    g10_pillaithruthu.setText("");
                }
            } else if (cn26.getCount() == 0) {
                g10_pillaithruthu.setText("");
            }
        } else {
            g10_pillaithruthu.setText("");
        }

        g16_koditaidaingaliniraipavm_c = (TextView) openDialog_s.findViewById(R.id.g16_koditaidaingaliniraipavm_c);

        if (sps.getString(New_Main_Gamelist.this, "bending_total5").equals("yes")) {
            Cursor cn26s = newhelper4.getQry("select * from newgamesdb4 where gameid='13' and isfinish='0'");
            if (cn26s != null && cn26s.moveToFirst()) {
                if (cn26s.getCount() != 0) {
                    int cns26s = cn26s.getCount();
                    if (cns26s != 0) {
                        g16_koditaidaingaliniraipavm_c.setText("(" + cns26s + ")");
                    } else {
                        g16_koditaidaingaliniraipavm_c.setText("");
                    }
                } else if (cn26s.getCount() == 0) {
                    g16_koditaidaingaliniraipavm_c.setText("");
                }
            } else {
                g16_koditaidaingaliniraipavm_c.setText("");
            }

        }

        g15_vinadivina_c = (TextView) openDialog_s.findViewById(R.id.g15_vinadivina_c);

        if (sps.getString(New_Main_Gamelist.this, "bending_total6").equals("yes")) {
            Cursor cn26ws = newhelper5.getQry("select * from newgames5 where gameid='17' and isfinish='0'");
            System.out.println("------------cn26ws.getCount()" + cn26ws.getCount());
            if (cn26ws != null && cn26ws.moveToFirst()) {
                if (cn26ws.getCount() != 0) {
                    int cns26ws = cn26ws.getCount();
                    if (cns26ws != 0) {
                        System.out.println("------------eng_to_tamil_nonot0" + cn26ws.getCount());
                        g15_vinadivina_c.setText("(" + cns26ws + ")");
                    } else {
                        g15_vinadivina_c.setText("");
                        System.out.println("------------eng_to_tamil_no" + cn26ws.getCount());
                    }
                } else if (cn26ws.getCount() == 0) {
                    System.out.println("------------eng_to_tamil_no==0" + cn26ws.getCount());
                    g15_vinadivina_c.setText("");
                }
            } else {
                g15_vinadivina_c.setText("");
            }

            g5_padathirkulkandupidi_c = (TextView) openDialog_s.findViewById(R.id.g5_padathirkulkandupidi_c);
            Cursor cn27ws = newhelper5.getQry("select * from newgames5 where gameid='16' and isfinish='0'");
            if (cn27ws != null && cn27ws.moveToFirst()) {
                if (cn27ws.getCount() != 0) {
                    int cns27ws = cn27ws.getCount();
                    if (cns27ws != 0) {
                        g5_padathirkulkandupidi_c.setText("(" + cns27ws + ")");
                    } else {
                        g5_padathirkulkandupidi_c.setText("");
                    }
                } else if (cn27ws.getCount() == 0) {
                    g5_padathirkulkandupidi_c.setText("");
                }
            } else {
                g5_padathirkulkandupidi_c.setText("");
            }

            g13_poruthuga_c = (TextView) openDialog_s.findViewById(R.id.g13_poruthuga_c);
            Cursor cn28ws = newhelper5.getQry("select * from newgames5 where gameid='15' and isfinish='0'");
            if (cn28ws != null && cn28ws.moveToFirst()) {
                if (cn28ws.getCount() != 0) {
                    int cns28ws = cn28ws.getCount();
                    if (cns28ws != 0) {
                        g13_poruthuga_c.setText("(" + cns28ws + ")");
                    } else {
                        g13_poruthuga_c.setText("");
                    }
                } else if (cn28ws.getCount() == 0) {
                    g13_poruthuga_c.setText("");
                }
            } else {
                g13_poruthuga_c.setText("");
            }


            g18_jumblewords_c = (TextView) openDialog_s.findViewById(R.id.g18_jumblewords_c);

            Cursor g18 = newhelper6.getQry("select * from newgames5 where gameid='18' and isfinish='0'");
            if (g18 != null && g18.moveToFirst()) {
                if (g18.getCount() != 0) {
                    int g18s = g18.getCount();
                    if (g18s != 0) {
                        g18_jumblewords_c.setText("(" + g18s + ")");
                    } else {
                        g18_jumblewords_c.setText("");
                    }
                } else if (g18.getCount() == 0) {
                    g18_jumblewords_c.setText("");
                }
            } else {
                g18_jumblewords_c.setText("");
            }

            g19_missingwords_c = (TextView) openDialog_s.findViewById(R.id.g19_missingwords_c);
            Cursor g19 = newhelper6.getQry("select * from newgames5 where gameid='19' and isfinish='0'");
            if (g19 != null && g19.moveToFirst()) {
                if (g19.getCount() != 0) {
                    int g19s = g19.getCount();
                    if (g19s != 0) {
                        g19_missingwords_c.setText("(" + g19s + ")");
                    } else {
                        g19_missingwords_c.setText("");
                    }
                } else if (g19.getCount() == 0) {
                    g19_missingwords_c.setText("");
                }
            } else {
                g19_missingwords_c.setText("");
            }


            g20_6differences_c = (TextView) openDialog_s.findViewById(R.id.g20_6differences_c);

            Cursor ers = newhelper6.getQry("select * from newgames5 where gameid='20' and isfinish='0'");
            if (ers != null && ers.moveToFirst()) {
                if (ers.getCount() != 0) {
                    int ersd = ers.getCount();
                    if (ersd != 0) {
                        g20_6differences_c.setText("(" + ersd + ")");
                    } else {
                        g20_6differences_c.setText("");
                    }
                } else if (cn28ws.getCount() == 0) {
                    g20_6differences_c.setText("");
                }
            } else {
                g20_6differences_c.setText("");
            }

        }
    }

    public static void fb_native(final Context context, final NativeAdLayout nativeAdLayout) {

        System.out.println("=====--check fb_native() : ");

        if (sps.getInt(context, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            //  nativeBannerAd = new NativeBannerAd(context,  context.getString(R.string.fb_native_banner));

            nativeBannerAd = new NativeBannerAd(context, context.getString(R.string.fb_native_banner));

            NativeAdListener nativeAdListener = new NativeAdListener() {

                @Override
                public void onMediaDownloaded(Ad ad) {
                    // Native ad finished downloading all assets
                    Log.e(TAG, "Native ad finished downloading all assets.");
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Native ad failed to load
                    Log.e(TAG, "Native ad failed to load: " + adError.getErrorCode());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Native ad is loaded and ready to be displayed
                    System.out.println("=====--check fb_native() onAdLoaded : ");
                    System.out.println("-----------------ads loded");
                    // Race condition, load() called again before last ad was displayed
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd(context, nativeAdLayout);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Native ad clicked
                    Log.d(TAG, "Native ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Native ad impression
                    Log.d(TAG, "Native ad impression logged!");
                }
            };
            // load the ad
            nativeBannerAd.loadAd(
                    nativeBannerAd.buildLoadAdConfig()
                            .withAdListener(nativeAdListener)
                            .build());




            /*
            nativeBannerAd.setAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    System.out.println("=====--check fb_native() adError : " + adError.getErrorMessage());
                    System.out.println("-----------------ads error" + adError.getErrorCode());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    System.out.println("=====--check fb_native() onAdLoaded : ");
                    System.out.println("-----------------ads loded");
                    // Race condition, load() called again before last ad was displayed
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd(context, nativeAdLayout);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
*/
            // load the ad
            //  nativeBannerAd.loadAd();
        }

    }


    public static void inflateAd(Context context, NativeAdLayout nativeAdLayout) {
        // Unregister last ad
        System.out.println("##############################################inflateAd");
        LinearLayout fb_native_adView;

        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        fb_native_adView = (LinearLayout) inflater.inflate(R.layout.native_banner_ad_layout, nativeAdLayout, false);
        nativeAdLayout.addView(fb_native_adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = fb_native_adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = fb_native_adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = fb_native_adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = fb_native_adView.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = fb_native_adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(fb_native_adView, nativeAdIconView, clickableViews);


    }


    public static void fb_native_Puthayal_Sorkal_Native_Banner(final Context context, final NativeAdLayout nativeAdLayout) {

        System.out.println("=====--check fb_native() : ");

        if (sps.getInt(context, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            final NativeBannerAd nativeBannerAd = new NativeBannerAd(context, context.getString(R.string.Puthayal_Sorkal_Native_Banner));
            NativeAdListener nativeAdListener = new NativeAdListener() {

                @Override
                public void onMediaDownloaded(Ad ad) {
                    // Native ad finished downloading all assets
                    Log.e(TAG, "Native ad finished downloading all assets.");
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Native ad failed to load
                    Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Native ad is loaded and ready to be displayed
                    Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd_Puthayal_Sorkal_Native_Banner(context, nativeAdLayout, nativeBannerAd);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Native ad clicked
                    Log.d(TAG, "Native ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Native ad impression
                    Log.d(TAG, "Native ad impression logged!");
                }
            };
            // load the ad
            nativeBannerAd.loadAd(
                    nativeBannerAd.buildLoadAdConfig()
                            .withAdListener(nativeAdListener)
                            .build());
            /*nativeBannerAd.setAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    System.out.println("=====--check fb_native() adError : " + adError.getErrorMessage());
                    System.out.println("-----------------ads error" + adError.getErrorCode());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    System.out.println("=====--check fb_native() onAdLoaded : ");
                    System.out.println("-----------------ads loded");
                    // Race condition, load() called again before last ad was displayed
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd_Puthayal_Sorkal_Native_Banner(context, nativeAdLayout, nativeBannerAd);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            // load the ad
            nativeBannerAd.loadAd();*/
        }

    }

    public static void inflateAd_Puthayal_Sorkal_Native_Banner(Context context, NativeAdLayout nativeAdLayout, NativeBannerAd nativeBannerAd) {
        // Unregister last ad
        System.out.println("##############################################inflateAd_Puthayal_Sorkal_Native_Banner");
        LinearLayout fb_native_adView;

        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        fb_native_adView = (LinearLayout) inflater.inflate(R.layout.native_banner_ad_layout, nativeAdLayout, false);
        nativeAdLayout.addView(fb_native_adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = fb_native_adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = fb_native_adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = fb_native_adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = fb_native_adView.findViewById(R.id.native_ad_sponsored_label);
        //AdIconView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);
        //   AdIconView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);
        MediaView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);

        Button nativeAdCallToAction = fb_native_adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(fb_native_adView, nativeAdIconView, clickableViews);


    }

    public static void fb_native_Senthamil_Thedal_Native_Banner(final Context context, final NativeAdLayout nativeAdLayout) {

        System.out.println("=====--check fb_native() : ");

        if (sps.getInt(context, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            final NativeBannerAd nativeBannerAd = new NativeBannerAd(context, context.getString(R.string.Senthamil_Thedal_Native_Banner));
            NativeAdListener nativeAdListener = new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                    // Native ad finished downloading all assets
                    Log.e(TAG, "Native ad finished downloading all assets.");
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Native ad failed to load
                    Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Native ad is loaded and ready to be displayed
                    Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd_Senthamil_Thedal_Native_Banner(context, nativeAdLayout, nativeBannerAd);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Native ad clicked
                    Log.d(TAG, "Native ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Native ad impression
                    Log.d(TAG, "Native ad impression logged!");
                }
            };
            // load the ad
            nativeBannerAd.loadAd(
                    nativeBannerAd.buildLoadAdConfig()
                            .withAdListener(nativeAdListener)
                            .build());
            /*  nativeBannerAd.setAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    System.out.println("=====--check fb_native() adError : " + adError.getErrorMessage());
                    System.out.println("-----------------ads error" + adError.getErrorCode());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    System.out.println("=====--check fb_native() onAdLoaded : ");
                    System.out.println("-----------------ads loded");
                    // Race condition, load() called again before last ad was displayed
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd_Senthamil_Thedal_Native_Banner(context, nativeAdLayout, nativeBannerAd);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            // load the ad
            nativeBannerAd.loadAd();*/
        }

    }

    public static void inflateAd_Senthamil_Thedal_Native_Banner(Context context, NativeAdLayout nativeAdLayout, NativeBannerAd nativeBannerAd) {
        // Unregister last ad

        LinearLayout fb_native_adView;

        nativeBannerAd.unregisterView();
        System.out.println("################################inflateAd_Senthamil_Thedal_Native_Banner");
        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        fb_native_adView = (LinearLayout) inflater.inflate(R.layout.native_banner_ad_layout, nativeAdLayout, false);
        nativeAdLayout.addView(fb_native_adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = fb_native_adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = fb_native_adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = fb_native_adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = fb_native_adView.findViewById(R.id.native_ad_sponsored_label);
        //AdIconView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);
        MediaView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = fb_native_adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(fb_native_adView, nativeAdIconView, clickableViews);


    }

    public static void fb_native_Ragasiya_sorgal_Native_Banner(final Context context, final NativeAdLayout nativeAdLayout) {

        System.out.println("=====--check fb_native() : ");

        if (sps.getInt(context, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            final NativeBannerAd nativeBannerAd = new NativeBannerAd(context, context.getString(R.string.Ragasiya_sorgal_Native_Banner));
            NativeAdListener nativeAdListener = new NativeAdListener() {

                @Override
                public void onMediaDownloaded(Ad ad) {
                    // Native ad finished downloading all assets
                    Log.e(TAG, "Native ad finished downloading all assets.");
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Native ad failed to load
                    Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Native ad is loaded and ready to be displayed
                    Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd_Ragasiya_sorgal_Native_Banner(context, nativeAdLayout, nativeBannerAd);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Native ad clicked
                    Log.d(TAG, "Native ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Native ad impression
                    Log.d(TAG, "Native ad impression logged!");
                }
            };
            // load the ad
            nativeBannerAd.loadAd(
                    nativeBannerAd.buildLoadAdConfig()
                            .withAdListener(nativeAdListener)
                            .build());
            /*     nativeBannerAd.setAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    System.out.println("=====--check fb_native() adError : " + adError.getErrorMessage());
                    System.out.println("-----------------ads error" + adError.getErrorCode());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    System.out.println("=====--check fb_native() onAdLoaded : ");
                    System.out.println("-----------------ads loded");
                    // Race condition, load() called again before last ad was displayed
                    sps.putInt(context, "native_banner_ads", 1);
                    if (nativeBannerAd == null || nativeBannerAd != ad) {
                        return;
                    }
                    // Inflate Native Banner Ad into Container
                    inflateAd_Ragasiya_sorgal_Native_Banner(context, nativeAdLayout,nativeBannerAd);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            // load the ad
            nativeBannerAd.loadAd();*/
        }

    }

    public static void inflateAd_Ragasiya_sorgal_Native_Banner(Context context, NativeAdLayout nativeAdLayout, NativeBannerAd nativeBannerAd) {
        // Unregister last ad
        System.out.println("##############################################inflateAd_Ragasiya_sorgal_Native_Banner");
        LinearLayout fb_native_adView;

        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        fb_native_adView = (LinearLayout) inflater.inflate(R.layout.native_banner_ad_layout, nativeAdLayout, false);
        nativeAdLayout.addView(fb_native_adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = fb_native_adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = fb_native_adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = fb_native_adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = fb_native_adView.findViewById(R.id.native_ad_sponsored_label);
        // AdIconView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);
        MediaView nativeAdIconView = fb_native_adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = fb_native_adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(fb_native_adView, nativeAdIconView, clickableViews);


    }

/*
    public void naive_banner(final LinearLayout layout) {
        final NativeBannerAd mNativeBannerAd = new NativeBannerAd(this, "1746769098928603_2532949333643905");
        // Initiate a request to load an ad.
        mNativeBannerAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                System.out.println("###########################ADS ONERROR" + adError.getErrorCode());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                System.out.println("###########################ADS LODED");
              */
/*  NativeAdViewAttributes viewAttributes = new NativeAdViewAttributes()
                        .setBackgroundColor(Color.BLACK)
                        .setTitleTextColor(Color.WHITE)
                        .setDescriptionTextColor(Color.LTGRAY)
                        .setButtonColor(Color.WHITE)
                        .setButtonTextColor(Color.BLACK);*//*


                // Render the Native Banner Ad Template
                View adView = NativeBannerAdView.render(New_Main_Gamelist.this, mNativeBannerAd, NativeBannerAdView.Type.HEIGHT_120);
                // Add the Native Banner Ad View to your ad container
                layout.addView(adView);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        mNativeBannerAd.loadAd();
    }
*/


    public void prize_enable() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
      /*  FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);*/
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        String adVal = "";
        try {
            adVal = mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY2);
        } catch (Exception e) {
            adVal = "1";
        }
   /*     long cacheExpiration = 3600; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }*/
        final String finalAdVal = adVal;
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {

                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            sps.putInt(getApplicationContext(), "remoteConfig_prize", Integer.parseInt(finalAdVal));
                            if (sp.getInt(New_Main_Gamelist.this, "remoteConfig_prize") == 1) {
                                // prices.setVisibility(View.VISIBLE);
                                prize_lay.setVisibility(View.VISIBLE);
                            } else {
                                //  prices.setVisibility(View.GONE);
                                prize_lay.setVisibility(View.GONE);
                            }

                        } else {

                        }

                    }


                });

    /*    mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(New_Main_Activity.this, "Succeeded " + finalAdVal, Toast.LENGTH_SHORT).show();
                            sps.putInt(getApplicationContext(), "remoteConfig_prize", Integer.parseInt(finalAdVal));
                            mFirebaseRemoteConfig.activateFetched();
                            if (sp.getInt(New_Main_Gamelist.this, "remoteConfig_prize") == 1) {
                                // prices.setVisibility(View.VISIBLE);
                                prize_lay.setVisibility(View.VISIBLE);
                            } else {
                                //  prices.setVisibility(View.GONE);
                                prize_lay.setVisibility(View.GONE);
                            }
                        } else {

                            // sps.putInt(getApplicationContext(), "remoteConfig_prize", 1);
                            // Toast.makeText(New_Main_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });*/
    }

}
