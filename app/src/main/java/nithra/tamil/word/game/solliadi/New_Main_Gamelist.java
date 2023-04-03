package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.NativeBannerAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Word_search_main;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.Word_search_levels;

public class New_Main_Gamelist extends AppCompatActivity implements View.OnClickListener, Download_completed {
    public static final String TAG = "SavedGames";
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    static final SharedPreference sps = new SharedPreference();
    private static final String LOADING_PHRASE_CONFIG_KEY = "app_sort_order";
    private static final String LOADING_PHRASE_CONFIG_KEY2 = "SolliadiPrize";
    static int mul_tival = 0;
    static NativeBannerAd nativeBannerAd;
    final int scrooly = 0;
    final int oldscrollY = 0;
    final SharedPreference sp = new SharedPreference();
    RippleView g1_solvilyatu, g2_solukulsol, g3_innaisorkalikandupidi, g4_padamparthukandupidi, g5_padathirkulkandupidi, g6_kuripumulamkandupidi, g7_piramolisorkalikandupidi, g8_seerpaduthu, g9_puthirukupathil, g10_pillaithruthu, g11_varupadukali_kandupidi, g12_aathirsolli_kandupidi, g13_poruthuga, g14_tirukural, g15_vinadivina, g16_koditaidaingaliniraipavm, g17_varthaithadal;
    RippleView g18_thadammariyavarthaigal, g19_missingwords, g20_6differences;
    TextView move_back;
    SoundPool click;
    int soundId1;
    int sv = 0;
    LinearLayout prize_game, multiplayer_games;
    FloatingActionButton down_arrow;
    ScrollView scroll_view;
    int i = 0;
    int sc_x = 0, sc_y = 0;

    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    Newgame_DataBaseHelper4 newhelper4;
    Newgame_DataBaseHelper5 newhelper5;
    LinearLayout adds;
    ImageView colour_change;
    RelativeLayout back_main, type1, type2, type3, type4, type5, type7, prize_lay, et_2;
    TextView heading_txt1, heading_txt2, heading_txt3, heading_txt4;
    TextView arrow_click, arrow_click_t3, ex_name, nitification;
    ImageView refresh, how_to_play;
    String downok = "", downnodata = "";
    int downcheck = 0;
    DownloadFileAsync downloadFileAsync;
    ProgressDialog mProgressDialog;
    ProgressDialog nProgressDialog;
    SQLiteDatabase myDB = null;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public static boolean determineConnectivity(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.getState() == NetworkInfo.State.CONNECTED;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__main_);


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

        Utills.INSTANCE.GameComplete(this);

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
        scroll_view.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (scroll_view.getChildAt(0).getBottom() <= (scroll_view.getHeight() + scroll_view.getScrollY())) {
                //scroll view is at bottom
                down_arrow.setVisibility(View.GONE);
            } else {
                //scroll view is not at bottom
                down_arrow.setVisibility(View.VISIBLE);
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

        nitification.setOnClickListener(v -> {
            Intent i = new Intent(New_Main_Gamelist.this, Noti_Fragment.class);
            startActivity(i);
        });
        how_to_play.setOnClickListener(v -> {
            intro();
        });


        refresh.setOnClickListener(v -> {

            if (determineConnectivity(New_Main_Gamelist.this)) downloaddata();
            else
                Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
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
            case R.id.g4_padamparthukandupidi:
                g4_padamparthukandupidi_start();
                break;
            case R.id.g5_padathirkulkandupidi:
                g5_padathirkulkandupidi_start();
                break;
            case R.id.g15_vinadivina:
                g15_vinadivina_start();
                break;
            case R.id.g14_tirukural:
                g14_tirukural_start();
                break;
            case R.id.g20_6differences:
                g20_6differences_start();
                break;


            case R.id.g6_kuripumulamkandupidi:
                குறிப்புமூலம்();
                break;
            case R.id.g9_puthirukupathil:
                g9_puthirukupathil_start();
                break;
            case R.id.g3_innaisorkalikandupidi:
                g3_innaisorkalikandupidi_start();
                break;
            case R.id.g12_aathirsolli_kandupidi:
                g12_aathirsolli_kandupidi_start();
                break;
            case R.id.g11_varupadukali_kandupidi:
                g11_varupadukali_kandupidi_start();
                break;


            case R.id.g2_solukulsol:
                g2_solukulsol_start();
                break;
            case R.id.g17_varthaithadal:
                g17_varthaithadal_start();
                break;
            case R.id.g16_koditaidaingaliniraipavm:
                g16_koditaidaingaliniraipavm_start();
                break;
            case R.id.g19_missingwords:
                g19_missingwors_start();
                break;
            case R.id.g18_jumblewords:
                g18_thadamariya_varthaithadal_start();
                break;


            case R.id.g1_solvilyatu:
                g1_solvilyatu_start();
                break;
            case R.id.g13_poruthuga:
                g13_poruthuga_start();
                break;
            case R.id.g10_pillaithruthu:
                g10_pillaithruthu_start();
                break;
            case R.id.g8_seerpaduthu:
                g8_seerpaduthu_start();
                break;
            case R.id.g7_piramolisorkalikandupidi:
                g7_piramolisorkalikandupidi_start();
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

                final Handler handler = new Handler(Looper.myLooper());
                new Thread(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    handler.post(() -> {
                        //scroll_view.fullScroll(View.FOCUS_DOWN);
                        scroll_view.smoothScrollTo(sc_x, sc_y);
                    });
                }).start();
                break;
            case R.id.move_back:
                finish();
                break;
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

    private void g18_thadamariya_varthaithadal_start() {
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

    private void குறிப்புமூலம்() {
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
                Toast.makeText(New_Main_Gamelist.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }

        }).setNegativeButton("இல்லை", (dialog, which) -> dialog.dismiss()).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public void downloadcheck(final String lastid, final String daily) {
        Utils.mProgress(New_Main_Gamelist.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", false).show();
        Utils.mProgress.setCancelable(false);
        new AsyncTask<Void, Void, Void>() {

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
                                        if (exists("https://nithra.mobi/solliadi/" + sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip")) {
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


        String str_url = "https://nithra.mobi/solliadi/" + sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip";

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

    public void downloadcheck1(final String lastid, final String daily) {
        new AsyncTask<Void, Void, Void>() {

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
                                        if (exists("https://nithra.mobi/solliadi/" + sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip")) {
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
                    new AlertDialog.Builder(New_Main_Gamelist.this)
                            /*.setTitle("Delete entry")*/.setMessage("பதிவுகள் ஏதும் இல்லை .பிறகு முயற்சிக்கவும் ").setPositiveButton("சரி", (dialog, which) -> {

                    }).setIcon(android.R.drawable.ic_dialog_alert).show();

                    downcheck = 0;

                } else {

                    new AlertDialog.Builder(New_Main_Gamelist.this)
                            /*.setTitle("Delete entry")*/.setMessage("புதிய பதிவுகள் ஏற்றப்பட்டது. விளையாடி மகிழவும்.   ").setPositiveButton("சரி", (dialog, which) -> {

                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
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
        close.setOnClickListener(v -> openDialog.dismiss());
        done_exit.setOnClickListener(v -> openDialog.dismiss());
        intros.setOnLongClickListener(v -> true);
        intros.loadUrl("file:///android_asset/web.html");
        openDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 151) {
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
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, task -> {
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

        });

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
/*


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

    class DownloadFileAsync extends AsyncTask<String, String, String> {

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


                File file = new File(SDCardRoot + "/Nithra/solliadi/", sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip");

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

                unpackZip(sps.getString(New_Main_Gamelist.this, "email") + "-filename.zip");

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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(New_Main_Gamelist.this);
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

}
