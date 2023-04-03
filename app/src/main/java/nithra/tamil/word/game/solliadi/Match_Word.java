package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.New_Main_Activity.prize_data_update;

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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseSequence;
import nithra.tamil.word.game.solliadi.showcase.MaterialShowcaseView;
import nithra.tamil.word.game.solliadi.showcase.ShowcaseConfig;

public class Match_Word extends AppCompatActivity implements Download_completed {

    // facebook variable ends
    public static final String TAG = "SavedGames";
    static int mCoinCount = 20;
    static int rvo = 0;
    final SharedPreference sps = new SharedPreference();
    final int min = 1;
    final int max = 3;
    final int gameid = 6;
    final Context context = this;
    final int minmumd = 1;
    final int maximumd = 4;
    int fb_reward = 0;
    int reward_status = 0;
    String btn_str = "";
    TextView ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9, ans10;
    ImageView value_ans1, value_ans2, value_ans3, value_ans4, value_ans5, value_ans6, value_ans7, value_ans8, value_ans9, value_ans10;
    TextView button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16;
    Animation clickzoom;
    LinearLayout adds, anslist2, list2_pic;
    TextView s_word_number, s_score_edit, hint, p_coins, p_coins_red, earncoin, to_no;
    SoundPool click, win, coin, worng, cr_ans;
    Chronometer focus;
    SQLiteDatabase exdb, dbs, dbn, dbn2;
    TextView pm_word;
    int random;
    int rdvalu = 2;
    int u_id, questionid;
    String question, answer, suff_words;
    int answerlength;
    int x = 0;
    long ttstop;
    int sv = 0;
    int k = 1;
    int s = 0;
    int y;
    Typeface tyr;
    PopupWindow popupWindow;
    TextView toggleButton;
    TextView p_setting;
    SoundPool spz1, spz2, spz3, spz4;
    int soundId1, soundId2, soundId3, soundId4;
    int e2;
    TextView tx1, tx2;
    String retype = "s";
    int case2 = 0, tot2 = 30, tt_case2, tt_tot2;
    TextView p_facebook, p_watts_app, p_gplues;
    Dialog openDialog_s, openDialog_p;
    TextView next_continue;
    TextView ttscores;
    /////////Native_BackPress_Advanced////////////
    RelativeLayout helpshare_layout;
    Dialog openDialog_earncoin;
    LinearLayout qwt, pm_layout, top;
    RelativeLayout head;
    int share_name = 0;
    RelativeLayout adsicon, adsicon2;
    int loadaddcontent = 0;
    LinearLayout ads_layout_bottom;
    int spxdr = 0;
    int daily_start = 0;
    int skxw = 0;
    int versionCode = 0;
    int answer_types = 0;
    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    Newgame_DataBaseHelper4 newhelper4;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    TextView coin_value;
    int randomnod;
    Dialog openDialog;
    int setval_vid;
    FirebaseAnalytics mFirebaseAnalytics;
    int f_sec;
    int dia_dismiss = 0;
    Handler handler;
    Runnable my_runnable;
    private RewardedAd rewardedAd;
    private InterstitialAd mInterstitialAd;

    @Override
    public void download_completed(String status) {
        System.out.println("#############################status" + status);
        if (status.equals("nodata")) {
            nextgamesdialog();
        } else {
            next();
        }
    }

    private void backexitnet() {
        if (main_act.equals("")) {
            finish();
            Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match__word);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        dbs = this.openOrCreateDatabase("Newgames.db", MODE_PRIVATE, null);
        dbn = this.openOrCreateDatabase("Newgames2.db", MODE_PRIVATE, null);
        dbn2 = this.openOrCreateDatabase("Newgames3.db", MODE_PRIVATE, null);


        if (sps.getString(Match_Word.this, "new_user_db").equals("")) {

        } else {
            if (sps.getString(Match_Word.this, "new_user_db").equals("on")) {
                sps.putString(Match_Word.this, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(Match_Word.this, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }


        myDbHelper = new DataBaseHelper(context);
        newhelper = new Newgame_DataBaseHelper(context);
        newhelper2 = new Newgame_DataBaseHelper2(context);
        newhelper3 = new Newgame_DataBaseHelper3(context);
        newhelper4 = new Newgame_DataBaseHelper4(context);


        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        find();
        versionCode = BuildConfig.VERSION_CODE;

        MobileAds.initialize(this);
        rewarded_adnew();
        if (sps.getInt(context, "purchase_ads") == 0) {
            Utills.INSTANCE.initializeAdzz(this);
            industrialload();
        }
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        Utills.INSTANCE.load_add_AppLovin(this, adds);


        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toggleButton = (TextView) popupView.findViewById(R.id.toggle);
        String snd = sps.getString(Match_Word.this, "snd");

        openDialog_s = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.score_screen);
        adsicon = (RelativeLayout) openDialog_s.findViewById(R.id.adsicon);
        ads_layout_bottom = (LinearLayout) openDialog_s.findViewById(R.id.fl_adplaceholder);
        /////////


        ImageView prize_logo = (ImageView) findViewById(R.id.prize_logo);
        if (sps.getInt(Match_Word.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        p_setting = (TextView) findViewById(R.id.s_settings);
        if (snd.equals("off")) {
            p_setting.setBackgroundResource(R.drawable.sound_off);
            // toggleButton.setBackgroundResource(R.drawable.off);
            sv = 0;

        } else if (snd.equals("on")) {
            p_setting.setBackgroundResource(R.drawable.sound_on);
            //toggleButton.setBackgroundResource(R.drawable.on);
            sv = 1;

        }

        Bundle extras;
        extras = getIntent().getExtras();
        if (extras != null) {
            String message = extras.getString("datee");
            try {
                if (message.equals("d")) {

                }
            } catch (Exception e) {
                e.printStackTrace();
                message = "no";
            }

            if (message.length() > 6) {
                sps.putString(Match_Word.this, "date", message);
                next();
            } else {
                sps.putString(Match_Word.this, "date", "0");
                next();
            }
        } else {
            sps.putString(Match_Word.this, "date", "0");
            next();
        }

        if (sps.getString(Match_Word.this, "mtcs_intro").equals("")) {
            showcase_dismiss();
            ShowcaseConfig config = new ShowcaseConfig();
            config.setDelay(100); // half second between each showcase view

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Match_Word.this, "sequence example match2");
            sequence.setConfig(config);
            sequence.addSequenceItem(value_ans1, "விடையை பார்க்க கேள்விக்குறி பொத்தானை அழுத்தி விடை காணலாம்.", "அடுத்து");

            sequence.addSequenceItem(new MaterialShowcaseView.Builder(Match_Word.this).setTarget(p_facebook).setDismissText("சரி").setContentText("சமூக வலைத்தளங்களை பயன்படுத்தி இந்த வினாவை  உங்களது நண்பர்களுக்கு பகிர்ந்து விடையை தெரிந்து கொள்ளலாம்.").build()).setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
                @Override
                public void onDismiss(MaterialShowcaseView itemView, int position) {

                    if (position == 1) {
                        sps.putString(Match_Word.this, "mtc_time_start", "yes");
                        sps.putString(Match_Word.this, "showcase_dismiss_mw", "yes");
                        focus.setBase(SystemClock.elapsedRealtime());
                        focus.start();

                    }
                }
            });

            sps.putString(Match_Word.this, "mtcs_intro", "no");
            sequence.start();
        }

        if (sps.getInt(Match_Word.this, "reward_coin_txt") == 0) {
            sps.putInt(Match_Word.this, "reward_coin_txt", 20);
        }

        button1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int sk = 0;
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button1.startAnimation(clickzoom);
                        String ts = button1.getText().toString();
                        anscheck(ts, 1);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button2.startAnimation(clickzoom);
                        String ts = button2.getText().toString();
                        anscheck(ts, 2);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });
        button3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button3.startAnimation(clickzoom);
                        String ts = button3.getText().toString();
                        anscheck(ts, 3);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });
        button4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button4.startAnimation(clickzoom);
                        String ts = button4.getText().toString();
                        anscheck(ts, 4);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button5.startAnimation(clickzoom);
                        String ts = button5.getText().toString();
                        anscheck(ts, 5);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button6.startAnimation(clickzoom);
                        String ts = button6.getText().toString();
                        anscheck(ts, 6);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button7.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button7.startAnimation(clickzoom);
                        String ts = button7.getText().toString();
                        anscheck(ts, 7);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button8.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button8.startAnimation(clickzoom);
                        String ts = button8.getText().toString();
                        anscheck(ts, 8);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button9.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button9.startAnimation(clickzoom);
                        String ts = button9.getText().toString();
                        anscheck(ts, 9);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button10.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button10.startAnimation(clickzoom);
                        String ts = button10.getText().toString();
                        anscheck(ts, 10);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });
        button11.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button11.startAnimation(clickzoom);
                        String ts = button11.getText().toString();
                        anscheck(ts, 11);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });
        button12.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button12.startAnimation(clickzoom);
                        String ts = button12.getText().toString();
                        anscheck(ts, 12);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button13.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button13.startAnimation(clickzoom);
                        String ts = button13.getText().toString();
                        anscheck(ts, 13);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button14.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button14.startAnimation(clickzoom);
                        String ts = button14.getText().toString();
                        anscheck(ts, 14);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });

        button15.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button15.startAnimation(clickzoom);
                        String ts = button15.getText().toString();
                        anscheck(ts, 15);
                    } else {
                        dialog(1);
                    }

                }
                return true;
            }
        });
        button16.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
                    cfw.moveToFirst();
                    int sk = 0;
                    if (cfw.getCount() != 0) {
                        sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
                    }
                    if (sk > 50) {
                        button16.startAnimation(clickzoom);
                        String ts = button16.getText().toString();
                        anscheck(ts, 16);
                    } else {
                        dialog(1);
                    }

                }

                return true;
            }
        });

        qwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(0);
            }
        });
        earncoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(0);
            }
        });
        pm_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        p_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p_setting.setBackgroundResource(R.drawable.sound_off);
                String snd = sps.getString(Match_Word.this, "snd");
                if (snd.equals("off")) {
                    sps.putString(Match_Word.this, "snd", "on");
                    p_setting.setBackgroundResource(R.drawable.sound_on);
                    sv = 1;
                } else if (snd.equals("on")) {
                    sps.putString(Match_Word.this, "snd", "off");
                    p_setting.setBackgroundResource(R.drawable.sound_off);
                    sv = 0;
                }
            }
        });

        value_ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });
        value_ans10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ask();
            }
        });

        p_gplues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 3;
                String a = "com.google.android.apps.plus";
                permission(a);
            }
        });
        p_watts_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 2;
                String a = "com.whatsapp";
                permission(a);
            }
        });
        p_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_name = 1;
                final String a = "com.facebook.katana";
                permission(a);
            }
        });

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k == 1) {

                } else {
                    popupWindow.dismiss();
                    k = 1;
                }
            }
        });
    }

    private void user_ask() {

        Cursor cfw = myDbHelper.getQry("SELECT * FROM score");
        cfw.moveToFirst();
        int sk = 0;
        if (cfw.getCount() != 0) {
            sk = cfw.getInt(cfw.getColumnIndexOrThrow("coins"));
        }

        if (sk > 50) {

            if (sps.getString(getApplicationContext(), "checkbox_ans").equals("yes")) {
                Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                cd.moveToFirst();
                if (cd.getCount() != 0) {
                    if (x <= answerlength) {
                        x++;

                        String ts = cd.getString(cd.getColumnIndexOrThrow("answer"));
                        myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + ts + "'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                        myDbHelper.executeSql("UPDATE answertable SET useranswer=0 WHERE answer='" + ts + "'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                        if (ans1.length() == 0) {
                            ans1.setText(ts);
                            value_ans1.setBackgroundResource(R.drawable.tick_background);
                            value_ans1.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 1) {
                                value_ans2.setVisibility(View.VISIBLE);
                            }

                        } else if (ans2.length() == 0) {
                            ans2.setText(ts);
                            value_ans2.setBackgroundResource(R.drawable.tick_background);
                            value_ans2.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 2) {
                                value_ans3.setVisibility(View.VISIBLE);
                            }

                        } else if (ans3.length() == 0) {
                            ans3.setText(ts);
                            value_ans3.setBackgroundResource(R.drawable.tick_background);
                            value_ans3.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 3) {
                                value_ans4.setVisibility(View.VISIBLE);
                            }

                        } else if (ans4.length() == 0) {
                            ans4.setText(ts);
                            value_ans4.setBackgroundResource(R.drawable.tick_background);
                            value_ans4.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 4) {
                                value_ans5.setVisibility(View.VISIBLE);
                            }
                        } else if (ans5.length() == 0) {
                            ans5.setText(ts);
                            value_ans5.setBackgroundResource(R.drawable.tick_background);
                            value_ans5.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 5) {
                                value_ans6.setVisibility(View.VISIBLE);
                            }

                        } else if (ans6.length() == 0) {
                            ans6.setText(ts);
                            value_ans6.setBackgroundResource(R.drawable.tick_background);
                            value_ans6.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 6) {
                                value_ans7.setVisibility(View.VISIBLE);
                            }
                        } else if (ans7.length() == 0) {
                            ans7.setText(ts);
                            value_ans7.setBackgroundResource(R.drawable.tick_background);
                            value_ans7.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 7) {
                                value_ans8.setVisibility(View.VISIBLE);
                            }

                        } else if (ans8.length() == 0) {
                            ans8.setText(ts);
                            value_ans8.setBackgroundResource(R.drawable.tick_background);
                            value_ans8.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 8) {
                                value_ans9.setVisibility(View.VISIBLE);
                            }
                        } else if (ans9.length() == 0) {
                            ans9.setText(ts);
                            value_ans9.setBackgroundResource(R.drawable.tick_background);
                            value_ans9.setClickable(false);
                            setuserentered(ts);
                            if (answerlength > 9) {
                                value_ans10.setVisibility(View.VISIBLE);
                            }
                        } else if (ans10.length() == 0) {
                            ans10.setText(ts);
                            value_ans10.setBackgroundResource(R.drawable.tick_background);
                            value_ans10.setClickable(false);
                            setuserentered(ts);
                        }

                        coinanim_red();
                        if (x >= answerlength) {
                            enablefalse();
                            String date = sps.getString(Match_Word.this, "date");
                            if (date.equals("0")) {
                                newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'");

                            } else {
                                newhelper.executeSql("UPDATE newmaintable SET daily= '1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'");

                            }
                            //  newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
                            ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                            focus.stop();
                            price_update();
                            Handler handler = new Handler(Looper.myLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    adShow();
                                }
                            }, 3000);
                        }

                    }
                }
            } else {
                final Dialog openDialog = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.show_ans);
                TextView yes = (TextView) openDialog.findViewById(R.id.yes);
                TextView no = (TextView) openDialog.findViewById(R.id.no);
                TextView txt_ex2 = (TextView) openDialog.findViewById(R.id.txt_ex2);
                txt_ex2.setText("மொத்த நாணயங்களில் 20 குறைக்கப்படும்");
                CheckBox checkbox_ans = (CheckBox) openDialog.findViewById(R.id.checkbox_ans);
                checkbox_ans.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            sps.putString(getApplicationContext(), "checkbox_ans", "yes");
                        } else {
                            sps.putString(getApplicationContext(), "checkbox_ans", "");
                        }
                    }
                });


                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where isfinish='0'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' order by random() limit 1");
                        cd.moveToFirst();
                        if (cd.getCount() != 0) {
                            if (x <= answerlength) {
                                x++;

                                String ts = cd.getString(cd.getColumnIndexOrThrow("answer"));
                                myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + ts + "'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                                myDbHelper.executeSql("UPDATE answertable SET useranswer=0 WHERE answer='" + ts + "'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                                if (ans1.length() == 0) {
                                    ans1.setText(ts);
                                    value_ans1.setBackgroundResource(R.drawable.tick_background);
                                    value_ans1.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 1) {
                                        value_ans2.setVisibility(View.VISIBLE);
                                    }

                                } else if (ans2.length() == 0) {
                                    ans2.setText(ts);
                                    value_ans2.setBackgroundResource(R.drawable.tick_background);
                                    value_ans2.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 2) {
                                        value_ans3.setVisibility(View.VISIBLE);
                                    }

                                } else if (ans3.length() == 0) {
                                    ans3.setText(ts);
                                    value_ans3.setBackgroundResource(R.drawable.tick_background);
                                    value_ans3.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 3) {
                                        value_ans4.setVisibility(View.VISIBLE);
                                    }

                                } else if (ans4.length() == 0) {
                                    ans4.setText(ts);
                                    value_ans4.setBackgroundResource(R.drawable.tick_background);
                                    value_ans4.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 4) {
                                        value_ans5.setVisibility(View.VISIBLE);
                                    }
                                } else if (ans5.length() == 0) {
                                    ans5.setText(ts);
                                    value_ans5.setBackgroundResource(R.drawable.tick_background);
                                    value_ans5.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 5) {
                                        value_ans6.setVisibility(View.VISIBLE);
                                    }

                                } else if (ans6.length() == 0) {
                                    ans6.setText(ts);
                                    value_ans6.setBackgroundResource(R.drawable.tick_background);
                                    value_ans6.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 6) {
                                        value_ans7.setVisibility(View.VISIBLE);
                                    }
                                } else if (ans7.length() == 0) {
                                    ans7.setText(ts);
                                    value_ans7.setBackgroundResource(R.drawable.tick_background);
                                    value_ans7.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 7) {
                                        value_ans8.setVisibility(View.VISIBLE);
                                    }

                                } else if (ans8.length() == 0) {
                                    ans8.setText(ts);
                                    value_ans8.setBackgroundResource(R.drawable.tick_background);
                                    value_ans8.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 8) {
                                        value_ans9.setVisibility(View.VISIBLE);
                                    }
                                } else if (ans9.length() == 0) {
                                    ans9.setText(ts);
                                    value_ans9.setBackgroundResource(R.drawable.tick_background);
                                    value_ans9.setClickable(false);
                                    setuserentered(ts);
                                    if (answerlength > 9) {
                                        value_ans10.setVisibility(View.VISIBLE);
                                    }
                                } else if (ans10.length() == 0) {
                                    ans10.setText(ts);
                                    value_ans10.setBackgroundResource(R.drawable.tick_background);
                                    value_ans10.setClickable(false);
                                    setuserentered(ts);
                                }

                                coinanim_red();
                                if (x >= answerlength) {
                                    price_update();
                                    enablefalse();
                                    String date = sps.getString(Match_Word.this, "date");
                                    if (date.equals("0")) {
                                        newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'");
                                    } else {
                                        newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "' and daily='" + 1 + "'");
                                    }
                                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                                    focus.stop();
                                    Handler handler = new Handler(Looper.myLooper());
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            adShow();
                                        }
                                    }, 3000);
                                }
                            }
                        }
                        openDialog.dismiss();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

    private void enablefalse() {

        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        button10.setEnabled(false);
        button11.setEnabled(false);
        button12.setEnabled(false);
        button13.setEnabled(false);
        button14.setEnabled(false);
        button15.setEnabled(false);
        button16.setEnabled(false);
    }

    private void find() {


        head = (RelativeLayout) findViewById(R.id.head);
        //Sound Pool Sounds
        spz1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = spz1.load(Match_Word.this, R.raw.click, 1);
        spz2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = spz2.load(Match_Word.this, R.raw.wrong, 1);
        spz3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = spz3.load(Match_Word.this, R.raw.win, 1);
        spz4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = spz4.load(Match_Word.this, R.raw.coins, 1);
        coin = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = coin.load(Match_Word.this, R.raw.coins, 1);
        worng = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = worng.load(Match_Word.this, R.raw.wrong, 1);
///
        p_coins = (TextView) findViewById(R.id.p_coins);
        p_coins_red = (TextView) findViewById(R.id.p_coins_red);

        p_setting = (TextView) findViewById(R.id.s_settings);
        adsicon2 = (RelativeLayout) findViewById(R.id.adsicon2);
        s_word_number = (TextView) findViewById(R.id.s_word_number);
        s_score_edit = (TextView) findViewById(R.id.s_score_edit);
        focus = (Chronometer) findViewById(R.id.s_time_edit);
        anslist2 = (LinearLayout) findViewById(R.id.anslist2);
        list2_pic = (LinearLayout) findViewById(R.id.list2_pic);
        pm_layout = (LinearLayout) findViewById(R.id.pm_layout);

        earncoin = (TextView) findViewById(R.id.earncoin);
        clickzoom = AnimationUtils.loadAnimation(this, R.anim.mw_click_zoom);
        qwt = (LinearLayout) findViewById(R.id.qwt);
        button1 = (TextView) findViewById(R.id.button1);
        button2 = (TextView) findViewById(R.id.button2);
        button3 = (TextView) findViewById(R.id.button3);
        button4 = (TextView) findViewById(R.id.button4);
        button5 = (TextView) findViewById(R.id.button5);
        button6 = (TextView) findViewById(R.id.button6);
        button7 = (TextView) findViewById(R.id.button7);
        button8 = (TextView) findViewById(R.id.button8);
        button9 = (TextView) findViewById(R.id.button9);
        button10 = (TextView) findViewById(R.id.button10);
        button11 = (TextView) findViewById(R.id.button11);
        button12 = (TextView) findViewById(R.id.button12);
        button13 = (TextView) findViewById(R.id.button13);
        button14 = (TextView) findViewById(R.id.button14);
        button15 = (TextView) findViewById(R.id.button15);
        button16 = (TextView) findViewById(R.id.button16);
        top = (LinearLayout) findViewById(R.id.top);
        helpshare_layout = (RelativeLayout) findViewById(R.id.helpshare_layout);

        pm_word = (TextView) findViewById(R.id.pm_word);

        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        ans4 = (TextView) findViewById(R.id.ans4);
        ans5 = (TextView) findViewById(R.id.ans5);
        ans6 = (TextView) findViewById(R.id.ans8);
        ans7 = (TextView) findViewById(R.id.ans9);
        ans8 = (TextView) findViewById(R.id.ans10);
        ans9 = (TextView) findViewById(R.id.ans11);
        ans10 = (TextView) findViewById(R.id.ans12);

        p_facebook = (TextView) findViewById(R.id.h_facebook);
        p_watts_app = (TextView) findViewById(R.id.h_watts_app);
        p_gplues = (TextView) findViewById(R.id.h_gplues);

        value_ans1 = (ImageView) findViewById(R.id.value_ans1);
        value_ans2 = (ImageView) findViewById(R.id.value_ans2);
        value_ans3 = (ImageView) findViewById(R.id.value_ans3);
        value_ans4 = (ImageView) findViewById(R.id.value_ans4);
        value_ans5 = (ImageView) findViewById(R.id.value_ans5);
        value_ans6 = (ImageView) findViewById(R.id.value_ans8);
        value_ans7 = (ImageView) findViewById(R.id.value_ans9);
        value_ans8 = (ImageView) findViewById(R.id.value_ans10);
        value_ans9 = (ImageView) findViewById(R.id.value_ans11);
        value_ans10 = (ImageView) findViewById(R.id.value_ans12);

        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        if (cfq.getCount() != 0) {
            int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
            String tr = String.valueOf(skq);
            s_score_edit.setText(tr);
        }


    }

    private void next() {


        Cursor cfq = myDbHelper.getQry("SELECT * FROM score ");
        cfq.moveToFirst();
        if (cfq.getCount() != 0) {
            int skq = cfq.getInt(cfq.getColumnIndexOrThrow("coins"));
            String tr = String.valueOf(skq);
            s_score_edit.setText(tr);
        }

        //  newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid=210 and gameid='" + gameid + "'");

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        button10.setEnabled(true);
        button11.setEnabled(true);
        button12.setEnabled(true);
        button13.setEnabled(true);
        button14.setEnabled(true);
        button15.setEnabled(true);
        button16.setEnabled(true);


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

        if (sps.getString(Match_Word.this, str_date1).equals("")) {

            daily_bones();
            sps.putString(Match_Word.this, str_date1, "yes");

        }

        if (daily_start == 1) {
            sps.putString(context, "date", "0");
            rdvalu = 2;
            daily_start = 0;
            finish();
            openDialog_s.dismiss();
            Intent i = new Intent(Match_Word.this, Match_Word.class);
            startActivity(i);
            Toast.makeText(Match_Word.this, "தினசரி விளையாட்டுகள் முடிந்தது.வழக்கமான இணைச்சொற்களை  கண்டுபிடி விளையாட்டுக்குள் செல்கிறது. ", Toast.LENGTH_LONG).show();
        }

        String date = sps.getString(Match_Word.this, "date");
        if (date.equals("0")) {
            rdvalu = 2;
            Cursor c2 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and isfinish='1'");
            c2.moveToFirst();
            if (c2.getCount() != 0) {
                int count1 = c2.getCount() + 1;
                String no = String.valueOf(count1);
                s_word_number.setText(no/*+"/"+c1.getCount()*/);
            }

        } else {
            if (sps.getInt(Match_Word.this, "purchase_ads") == 1) {

            } else {
                sps.putInt(context, "addloded_rect_bck", 0);
                sps.putInt(context, "addloded_rect_mul", 0);

            }

            rdvalu = 1;
            String tfoption = date;
            String[] first = tfoption.split("-");
            s_word_number.setText("" + first[2] + "-" + first[1] + "-" + first[0]);
            s_word_number.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }


        Cursor c1;

        if (date.equals("0")) {
            c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and isfinish='0'");
        } else {
            daily_start = 1;
            c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and daily='0' order by random() limit 1");
        }
        // Cursor c1 = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and isfinish='0'");
        c1.moveToFirst();
        if (c1.getCount() != 0) {
            head.setVisibility(View.VISIBLE);
            u_id = c1.getInt(c1.getColumnIndexOrThrow("id"));
            questionid = c1.getInt(c1.getColumnIndexOrThrow("questionid"));
            question = c1.getString(c1.getColumnIndexOrThrow("question"));
            answer = c1.getString(c1.getColumnIndexOrThrow("answer"));
            suff_words = c1.getString(c1.getColumnIndexOrThrow("sf_words"));
            int clue = c1.getInt(c1.getColumnIndexOrThrow("clue"));
            int playtime = c1.getInt(c1.getColumnIndexOrThrow("playtime"));

            String tfoption = answer;
            String[] first = tfoption.split(",");

            int letter_type = first.length;
            answerlength = letter_type;

            //Toast.makeText(Match_Word.this, ""+suff_words, Toast.LENGTH_SHORT).show();
            String tfoption2 = suff_words;
            String[] first1 = tfoption2.split(",");
            int suff_type = first1.length;

            pm_word.setText(question);


            answer_types = answerlength + suff_type;


            if (answer_types == 6) {
                int width = getResources().getDimensionPixelSize(R.dimen.wh_60);//your textview height
                //   int width = 300;//your textview height
                button1.getLayoutParams().width = width;
                button2.getLayoutParams().width = width;
                button3.getLayoutParams().width = width;
                button5.getLayoutParams().width = width;
                button6.getLayoutParams().width = width;
                button7.getLayoutParams().width = width;
            }

            //  Toast.makeText(Match_Word.this, "answer_types"+answer_types, Toast.LENGTH_SHORT).show();
            System.out.println("#################qgameid" + gameid);
            System.out.println("#################questionid" + questionid);
            Cursor answ = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + questionid + "' and rd = '" + rdvalu + "'");
            System.out.println("#################qansw.getCount()" + answ.getCount());

            if (answ.getCount() == 0) {

                reset();
                Random rn = new Random();
                random = rn.nextInt(max - min + 1) + min;
                sps.putInt(Match_Word.this, "randomnumber", random);
                if (random == 1) {
                    pm_layout.setBackgroundResource(R.color.dark_orange);
                } else if (random == 2) {
                    pm_layout.setBackgroundResource(R.color.rippelColor3);
                } else {
                    pm_layout.setBackgroundResource(R.color.transparent);
                }

                for (int i = 0; i < letter_type; i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("gameid", gameid);
                    cv.put("levelid", questionid);
                    System.out.println("#################qfirst[i].trim()" + first[i].trim());
                    cv.put("answer", first[i].trim());
                    cv.put("rd", rdvalu);
                    cv.put("isfinish", "0");
                    myDbHelper.insert_data("answertable", null, cv);
                    setanswer(letter_type);
                    // Toast.makeText(Match_Word.this, "inserting"+first[i].trim(), Toast.LENGTH_SHORT).show();
                }
            } else if (answ.getCount() != 0) {

                random = sps.getInt(Match_Word.this, "randomnumber");
                setanswer(letter_type);
                if (random == 1) {
                    pm_layout.setBackgroundResource(R.color.dark_orange);
                } else if (random == 2) {
                    pm_layout.setBackgroundResource(R.color.rippelColor3);
                } else {
                    pm_layout.setBackgroundResource(R.color.transparent);
                }

                System.out.println("#######################RD VALUE" + rdvalu);
                Cursor csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + questionid + "' and rd='" + rdvalu + "' and isfinish='1'");
                csk.moveToFirst();
                x = csk.getCount();
                for (int i = 0; i < csk.getCount(); i++) {
                    csk.moveToPosition(i);
                    String ts = csk.getString(csk.getColumnIndexOrThrow("answer"));
                    if (ans1.length() == 0) {
                        ans1.setText(ts);
                        value_ans1.setBackgroundResource(R.drawable.tick_background);
                        value_ans1.setClickable(false);
                        if (answerlength > 1) {
                            value_ans2.setVisibility(View.VISIBLE);
                        }

                    } else if (ans2.length() == 0) {
                        ans2.setText(ts);
                        value_ans2.setBackgroundResource(R.drawable.tick_background);
                        value_ans2.setClickable(false);
                        if (answerlength > 2) {
                            value_ans3.setVisibility(View.VISIBLE);
                        }

                    } else if (ans3.length() == 0) {
                        ans3.setText(ts);
                        value_ans3.setBackgroundResource(R.drawable.tick_background);
                        value_ans3.setClickable(false);
                        if (answerlength > 3) {
                            value_ans4.setVisibility(View.VISIBLE);
                        }

                    } else if (ans4.length() == 0) {
                        ans4.setText(ts);
                        value_ans4.setBackgroundResource(R.drawable.tick_background);
                        value_ans4.setClickable(false);

                        if (answerlength > 4) {
                            value_ans5.setVisibility(View.VISIBLE);
                        }
                    } else if (ans5.length() == 0) {
                        ans5.setText(ts);
                        value_ans5.setBackgroundResource(R.drawable.tick_background);
                        value_ans5.setClickable(false);

                        if (answerlength > 5) {
                            value_ans6.setVisibility(View.VISIBLE);
                        }

                    } else if (ans6.length() == 0) {
                        ans6.setText(ts);
                        value_ans6.setBackgroundResource(R.drawable.tick_background);
                        value_ans6.setClickable(false);

                        if (answerlength > 6) {
                            value_ans7.setVisibility(View.VISIBLE);
                        }
                    } else if (ans7.length() == 0) {
                        ans7.setText(ts);
                        value_ans7.setBackgroundResource(R.drawable.tick_background);
                        value_ans7.setClickable(false);

                        if (answerlength > 7) {
                            value_ans8.setVisibility(View.VISIBLE);
                        }

                    } else if (ans8.length() == 0) {
                        ans8.setText(ts);
                        value_ans8.setBackgroundResource(R.drawable.tick_background);
                        value_ans8.setClickable(false);

                        if (answerlength > 8) {
                            value_ans9.setVisibility(View.VISIBLE);
                        }
                    } else if (ans9.length() == 0) {
                        ans9.setText(ts);
                        value_ans9.setBackgroundResource(R.drawable.tick_background);
                        value_ans9.setClickable(false);

                        if (answerlength > 9) {
                            value_ans10.setVisibility(View.VISIBLE);
                        }
                    } else if (ans10.length() == 0) {
                        ans10.setText(ts);
                        value_ans10.setBackgroundResource(R.drawable.tick_background);
                        value_ans10.setClickable(false);

                    }
                    if (x >= answ.getCount()) {
                        String dates = sps.getString(Match_Word.this, "date");
                        if (dates.equals("0")) {
                            newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'");
                        } else {
                            newhelper.executeSql("UPDATE newmaintable SET daily='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'");
                        }
                        adShow();
                    }
                    setuserentered(ts);
                }
            }
        } else {
            downloaddata_regular();
            //  nextgamesdialog();
        }
    }

    private void daily_bones() {
        openDialog = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.daily_bones_newd2);
        openDialog.setCancelable(false);
        //  TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);

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


        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    int spx = skx + ea;
                    String aStringx = Integer.toString(spx);
                    s_score_edit.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    sps.putString(context, "daily_bonus_date", date);
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
        System.out.println("############################^^^^^^^^^^^^^^saveddate" + sps.getString(Match_Word.this, "daily_bonus_date"));

        if (str_date1.equals(sps.getString(Match_Word.this, "daily_bonus_date"))) {

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
        prize_data_update(context, ea);
        coin_value = (TextView) openDialog.findViewById(R.id.coin_value);
        coin_value.setText("" + ea);
        setval_vid = ea;
        Random rn = new Random();
        randomnod = rn.nextInt(maximumd - minmumd + 1) + minmumd;

        String ran_score = "";
        if (randomnod == 1) {
            sps.putInt(context, "daily_bonus_count", 1);
            ran_score = "150";
        } else if (randomnod == 2) {
            sps.putInt(context, "daily_bonus_count", 2);
            ran_score = "200";
        } else if (randomnod == 3) {
            sps.putInt(context, "daily_bonus_count", 3);
            ran_score = "250";
        } else if (randomnod == 4) {
            sps.putInt(context, "daily_bonus_count", 4);
            ran_score = "300";
        }

        tomarrow_coin_earn.setText("நாளைய தினத்திற்கான ஊக்க நாணயங்கள் : " + ran_score);

        extra_coin = (LinearLayout) openDialog.findViewById(R.id.extra_coin);

        extra_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extra_coin_s = 1;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Match_Word.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        show_reward();
                    } else {
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    show_reward();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(Match_Word.this);
                                    rewarded_adnew();
                                    Toast.makeText(Match_Word.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        openDialog.show();
    }

    private void reset() {
        x = 0;
        if (sps.getString(Match_Word.this, "mtc_time_start").equals("")) {

        } else {
            focus.setBase(SystemClock.elapsedRealtime());
            focus.start();
        }

        value_ans1.setClickable(true);
        value_ans2.setClickable(true);
        value_ans3.setClickable(true);
        value_ans4.setClickable(true);
        value_ans5.setClickable(true);
        value_ans6.setClickable(true);
        value_ans7.setClickable(true);
        value_ans8.setClickable(true);
        value_ans9.setClickable(true);
        value_ans10.setClickable(true);


        value_ans2.setVisibility(View.GONE);
        value_ans3.setVisibility(View.GONE);
        value_ans4.setVisibility(View.GONE);
        value_ans5.setVisibility(View.GONE);
        value_ans6.setVisibility(View.GONE);
        value_ans7.setVisibility(View.GONE);
        value_ans8.setVisibility(View.GONE);
        value_ans9.setVisibility(View.GONE);
        value_ans10.setVisibility(View.GONE);


        button1.setBackgroundResource(R.drawable.game_buttons);
        button2.setBackgroundResource(R.drawable.game_buttons);
        button3.setBackgroundResource(R.drawable.game_buttons);
        button4.setBackgroundResource(R.drawable.game_buttons);
        button5.setBackgroundResource(R.drawable.game_buttons);
        button6.setBackgroundResource(R.drawable.game_buttons);
        button7.setBackgroundResource(R.drawable.game_buttons);
        button8.setBackgroundResource(R.drawable.game_buttons);
        button9.setBackgroundResource(R.drawable.game_buttons);
        button10.setBackgroundResource(R.drawable.game_buttons);
        button11.setBackgroundResource(R.drawable.game_buttons);
        button12.setBackgroundResource(R.drawable.game_buttons);
        button13.setBackgroundResource(R.drawable.game_buttons);
        button14.setBackgroundResource(R.drawable.game_buttons);
        button15.setBackgroundResource(R.drawable.game_buttons);
        button16.setBackgroundResource(R.drawable.game_buttons);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        button10.setEnabled(true);
        button11.setEnabled(true);
        button12.setEnabled(true);
        button13.setEnabled(true);
        button14.setEnabled(true);
        button15.setEnabled(true);
        button16.setEnabled(true);

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button13.setText("");
        button14.setText("");
        button15.setText("");
        button16.setText("");


        ans1.setText("");
        ans2.setText("");
        ans3.setText("");
        ans4.setText("");
        ans5.setText("");
        ans6.setText("");
        ans7.setText("");
        ans8.setText("");
        ans9.setText("");
        ans10.setText("");

        ans1.setVisibility(View.GONE);
        ans2.setVisibility(View.GONE);
        ans3.setVisibility(View.GONE);
        ans4.setVisibility(View.GONE);
        ans5.setVisibility(View.GONE);
        ans6.setVisibility(View.GONE);
        ans7.setVisibility(View.GONE);
        ans8.setVisibility(View.GONE);
        ans9.setVisibility(View.GONE);
        ans10.setVisibility(View.GONE);

        value_ans1.setBackgroundResource(R.drawable.yellow_question);
        value_ans2.setBackgroundResource(R.drawable.yellow_question);
        value_ans3.setBackgroundResource(R.drawable.yellow_question);
        value_ans4.setBackgroundResource(R.drawable.yellow_question);
        value_ans5.setBackgroundResource(R.drawable.yellow_question);
        value_ans6.setBackgroundResource(R.drawable.yellow_question);
        value_ans7.setBackgroundResource(R.drawable.yellow_question);
        value_ans8.setBackgroundResource(R.drawable.yellow_question);
        value_ans9.setBackgroundResource(R.drawable.yellow_question);
        value_ans10.setBackgroundResource(R.drawable.yellow_question);


        value_ans1.setVisibility(View.VISIBLE);

        sps.putInt(Match_Word.this, "mwbts1", 0);
        sps.putInt(Match_Word.this, "mwbts2", 0);
        sps.putInt(Match_Word.this, "mwbts3", 0);
        sps.putInt(Match_Word.this, "mwbts4", 0);
        sps.putInt(Match_Word.this, "mwbts5", 0);
        sps.putInt(Match_Word.this, "mwbts6", 0);
        sps.putInt(Match_Word.this, "mwbts7", 0);
        sps.putInt(Match_Word.this, "mwbts8", 0);
        sps.putInt(Match_Word.this, "mwbts9", 0);
        sps.putInt(Match_Word.this, "mwbts10", 0);
        sps.putInt(Match_Word.this, "mwbts11", 0);
        sps.putInt(Match_Word.this, "mwbts12", 0);
        sps.putInt(Match_Word.this, "mwbts13", 0);
        sps.putInt(Match_Word.this, "mwbts14", 0);
        sps.putInt(Match_Word.this, "mwbts15", 0);
        sps.putInt(Match_Word.this, "mwbts16", 0);


    }

    private void setuserentered(String ts) {


        if (sps.getInt(Match_Word.this, "mwbts1") == 1) {
            button1.setBackgroundResource(R.drawable.worng_ans);
            button1.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts2") == 1) {
            button2.setBackgroundResource(R.drawable.worng_ans);
            button2.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts3") == 1) {
            button3.setBackgroundResource(R.drawable.worng_ans);
            button3.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts4") == 1) {
            button4.setBackgroundResource(R.drawable.worng_ans);
            button4.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts5") == 1) {
            button5.setBackgroundResource(R.drawable.worng_ans);
            button5.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts6") == 1) {
            button6.setBackgroundResource(R.drawable.worng_ans);
            button6.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts7") == 1) {
            button7.setBackgroundResource(R.drawable.worng_ans);
            button7.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts8") == 1) {
            button8.setBackgroundResource(R.drawable.worng_ans);
            button8.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts9") == 1) {
            button9.setBackgroundResource(R.drawable.worng_ans);
            button9.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts10") == 1) {
            button10.setBackgroundResource(R.drawable.worng_ans);
            button10.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts11") == 1) {
            button11.setBackgroundResource(R.drawable.worng_ans);
            button11.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts12") == 1) {
            button12.setBackgroundResource(R.drawable.worng_ans);
            button12.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts13") == 1) {
            button13.setBackgroundResource(R.drawable.worng_ans);
            button13.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts14") == 1) {
            button14.setBackgroundResource(R.drawable.worng_ans);
            button14.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts15") == 1) {
            button15.setBackgroundResource(R.drawable.worng_ans);
            button15.setEnabled(false);
        }
        if (sps.getInt(Match_Word.this, "mwbts16") == 1) {
            button16.setBackgroundResource(R.drawable.worng_ans);
            button16.setEnabled(false);
        }


        if (ts.equals(button1.getText().toString())) {
            button1.setBackgroundResource(R.drawable.right_ans);
            button1.setEnabled(false);
        } else if (ts.equals(button2.getText().toString())) {
            button2.setBackgroundResource(R.drawable.right_ans);
            button2.setEnabled(false);
        } else if (ts.equals(button3.getText().toString())) {
            button3.setBackgroundResource(R.drawable.right_ans);
            button3.setEnabled(false);
        } else if (ts.equals(button4.getText().toString())) {
            button4.setBackgroundResource(R.drawable.right_ans);
            button4.setEnabled(false);
        } else if (ts.equals(button5.getText().toString())) {
            button5.setBackgroundResource(R.drawable.right_ans);
            button5.setEnabled(false);
        } else if (ts.equals(button6.getText().toString())) {
            button6.setBackgroundResource(R.drawable.right_ans);
            button6.setEnabled(false);
        } else if (ts.equals(button7.getText().toString())) {
            button7.setBackgroundResource(R.drawable.right_ans);
            button7.setEnabled(false);
        } else if (ts.equals(button8.getText().toString())) {
            button8.setBackgroundResource(R.drawable.right_ans);
            button8.setEnabled(false);
        } else if (ts.equals(button9.getText().toString())) {
            button9.setBackgroundResource(R.drawable.right_ans);
            button9.setEnabled(false);
        } else if (ts.equals(button10.getText().toString())) {
            button10.setBackgroundResource(R.drawable.right_ans);
            button10.setEnabled(false);
        } else if (ts.equals(button11.getText().toString())) {
            button11.setBackgroundResource(R.drawable.right_ans);
            button11.setEnabled(false);
        } else if (ts.equals(button12.getText().toString())) {
            button12.setBackgroundResource(R.drawable.right_ans);
            button12.setEnabled(false);
        } else if (ts.equals(button13.getText().toString())) {
            button13.setBackgroundResource(R.drawable.right_ans);
            button13.setEnabled(false);
        } else if (ts.equals(button14.getText().toString())) {
            button14.setBackgroundResource(R.drawable.right_ans);
            button14.setEnabled(false);
        } else if (ts.equals(button15.getText().toString())) {
            button15.setBackgroundResource(R.drawable.right_ans);
            button15.setEnabled(false);
        } else if (ts.equals(button16.getText().toString())) {
            button16.setBackgroundResource(R.drawable.right_ans);
            button16.setEnabled(false);
        }
    }

    private void setanswer(int letter_type) {
        if (letter_type == 1) {
            anslist2.setVisibility(View.GONE);
            list2_pic.setVisibility(View.GONE);

            ans1.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            button1.setText(answer);

        } else if (letter_type == 2) {

            anslist2.setVisibility(View.GONE);
            list2_pic.setVisibility(View.GONE);

            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);
            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }

        } else if (letter_type == 3) {
            anslist2.setVisibility(View.GONE);
            list2_pic.setVisibility(View.GONE);
            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }
        } else if (letter_type == 4) {
            anslist2.setVisibility(View.GONE);
            list2_pic.setVisibility(View.GONE);

            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }
        } else if (letter_type == 5) {

            anslist2.setVisibility(View.GONE);
            list2_pic.setVisibility(View.GONE);

            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }
        } else if (letter_type == 6) {
            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);

            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();


            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            ans6.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }
        } else if (letter_type == 7) {

            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);

            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();


            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            ans6.setVisibility(View.VISIBLE);
            ans7.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }

        } else if (letter_type == 8) {
            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);

            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();
            final String letters8 = tokenizerw.nextToken().trim();


            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            ans6.setVisibility(View.VISIBLE);
            ans7.setVisibility(View.VISIBLE);
            ans8.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }

        } else if (letter_type == 9) {
            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);

            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();
            final String letters8 = tokenizerw.nextToken().trim();
            final String letters9 = tokenizerw.nextToken().trim();


            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            ans6.setVisibility(View.VISIBLE);
            ans7.setVisibility(View.VISIBLE);
            ans8.setVisibility(View.VISIBLE);
            ans9.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }

        } else if (letter_type == 10) {
            anslist2.setVisibility(View.VISIBLE);
            list2_pic.setVisibility(View.VISIBLE);

            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();
            final String letters8 = tokenizerw.nextToken().trim();
            final String letters9 = tokenizerw.nextToken().trim();
            final String letters10 = tokenizerw.nextToken().trim();


            ans1.setVisibility(View.VISIBLE);
            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);
            ans4.setVisibility(View.VISIBLE);
            ans5.setVisibility(View.VISIBLE);
            ans6.setVisibility(View.VISIBLE);
            ans7.setVisibility(View.VISIBLE);
            ans8.setVisibility(View.VISIBLE);
            ans9.setVisibility(View.VISIBLE);
            ans10.setVisibility(View.VISIBLE);
            value_ans1.setVisibility(View.VISIBLE);

            if (answer_types == 6) {
                answerset_new_version(letter_type);
            } else {
                answerset(letter_type);
            }
        }
    }

    public void onBackPressed() {
        sps.putString(Match_Word.this, "game_area", "on");
        sps.putString(Match_Word.this, "mtc_time_start", "yes");
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            sps.putInt(Match_Word.this, "addlodedd", 0);
            s = 1;
            openDialog_p = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog_p.setContentView(R.layout.back_pess);
            TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
            TextView no = (TextView) openDialog_p.findViewById(R.id.no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                    focus.stop();
                    newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    newhelper.executeSql("UPDATE newmaintable SET clue='" + random + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                    if (main_act.equals("")) {
                        finish();
                        //     openDialog_s.dismiss();
                        Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();

                    }
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
    }

    protected void onResume() {
        super.onResume();
        if (handler != null) handler.postDelayed(my_runnable, 1000);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ON Resume  " + sps.getInt(getApplicationContext(), "Game1_Stage_Close_VV"));

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(Match_Word.this);
        mFirebaseAnalytics.setCurrentScreen(this, "Find Equal word Game", null);


        String date = sps.getString(Match_Word.this, "date");
        int pos;
        if (date.equals("0")) {
        } else {
        }

        Cursor cs = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "' and questionid='" + questionid + "'");
        cs.moveToFirst();
        long dscore = 0;
        if (cs.getCount() != 0) {
            dscore = cs.getInt(cs.getColumnIndexOrThrow("playtime"));
        }
        //  long wt=sps.getInt(Word_Game_Hard.this,"old_time_start");

        if (sps.getString(Match_Word.this, "mtc_time_start").equals("")) {

        } else {
            focus.setBase(SystemClock.elapsedRealtime() + dscore);
            focus.start();
        }

    }

    public void answerset(int letter_type) {

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        button7.setVisibility(View.VISIBLE);
        button8.setVisibility(View.VISIBLE);
        button9.setVisibility(View.VISIBLE);
        button10.setVisibility(View.VISIBLE);
        button11.setVisibility(View.VISIBLE);
        button12.setVisibility(View.VISIBLE);
        button13.setVisibility(View.VISIBLE);
        button14.setVisibility(View.VISIBLE);
        button15.setVisibility(View.VISIBLE);
        button16.setVisibility(View.VISIBLE);

        if (letter_type == 1) {

        } else if (letter_type == 2) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            ////////////suff/////////////

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();
            final String qt8 = tokenizer.nextToken().trim();
            final String qt9 = tokenizer.nextToken().trim();
            final String qt10 = tokenizer.nextToken().trim();
            final String qt11 = tokenizer.nextToken().trim();
            final String qt12 = tokenizer.nextToken().trim();
            final String qt13 = tokenizer.nextToken().trim();
            final String qt14 = tokenizer.nextToken().trim();

            if (random == 1) {
                button1.setText(qt1);
                button2.setText(qt2);
                button3.setText(qt3);
                button4.setText(qt4);
                button5.setText(qt5);
                button6.setText(qt6);
                button7.setText(letters2);
                button8.setText(qt7);
                button9.setText(letters1);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(qt10);
                button13.setText(qt11);
                button14.setText(qt12);
                button15.setText(qt13);
                button16.setText(qt14);

            } else if (random == 2) {

                button1.setText(qt1);
                button2.setText(qt2);
                button3.setText(qt3);
                button4.setText(qt4);
                button5.setText(qt5);
                button6.setText(letters1);
                button7.setText(qt6);
                button8.setText(qt7);
                button9.setText(qt10);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(letters2);
                button13.setText(qt11);
                button14.setText(qt12);
                button15.setText(qt13);
                button16.setText(qt14);

            } else if (random == 3) {

                button1.setText(qt1);
                button2.setText(letters2);
                button3.setText(qt3);
                button4.setText(qt4);
                button5.setText(letters1);
                button6.setText(qt5);
                button7.setText(qt6);
                button8.setText(qt7);
                button9.setText(qt10);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(qt2);
                button13.setText(qt11);
                button14.setText(qt12);
                button15.setText(qt13);
                button16.setText(qt14);

            }


        } else if (letter_type == 3) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();


            ////////////suff/////////////

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();
            final String qt8 = tokenizer.nextToken().trim();
            final String qt9 = tokenizer.nextToken().trim();
            final String qt10 = tokenizer.nextToken().trim();
            final String qt11 = tokenizer.nextToken().trim();
            final String qt12 = tokenizer.nextToken().trim();
            final String qt13 = tokenizer.nextToken().trim();

            if (random == 1) {
                button1.setText(qt1);
                button2.setText(qt5);
                button3.setText(letters3);
                button4.setText(qt4);
                button5.setText(qt7);
                button6.setText(letters2);
                button7.setText(qt6);
                button8.setText(letters1);
                button9.setText(qt10);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(qt2);
                button13.setText(qt11);
                button14.setText(qt12);
                button15.setText(qt13);
                button16.setText(qt3);

            } else if (random == 2) {
                button1.setText(qt1);
                button2.setText(qt5);
                button3.setText(qt2);
                button4.setText(qt4);
                button5.setText(letters1);
                button6.setText(qt13);
                button7.setText(qt6);
                button8.setText(qt7);
                button9.setText(qt10);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(letters3);
                button13.setText(qt11);
                button14.setText(qt12);
                button15.setText(letters2);
                button16.setText(qt3);

            } else if (random == 3) {

                button1.setText(qt1);
                button2.setText(letters2);
                button3.setText(qt2);
                button4.setText(qt4);
                button5.setText(qt10);
                button6.setText(qt13);
                button7.setText(qt6);
                button8.setText(qt7);
                button9.setText(letters1);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(qt12);
                button13.setText(qt11);
                button14.setText(letters3);
                button15.setText(qt5);
                button16.setText(qt3);

            }

        } else if (letter_type == 4) {

            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();

            ////////////suff/////////////

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();
            final String qt8 = tokenizer.nextToken().trim();
            final String qt9 = tokenizer.nextToken().trim();
            final String qt10 = tokenizer.nextToken().trim();
            final String qt11 = tokenizer.nextToken().trim();
            final String qt12 = tokenizer.nextToken().trim();


            if (random == 1) {

                button1.setText(letters4);
                button2.setText(qt5);
                button3.setText(letters3);
                button4.setText(qt4);
                button5.setText(qt10);
                button6.setText(qt1);
                button7.setText(qt6);
                button8.setText(qt7);
                button9.setText(letters1);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(qt12);
                button13.setText(qt11);
                button14.setText(qt2);
                button15.setText(letters2);
                button16.setText(qt3);

            } else if (random == 2) {

                button1.setText(qt6);
                button2.setText(qt5);
                button3.setText(qt2);
                button4.setText(letters2);
                button5.setText(qt10);
                button6.setText(letters1);
                button7.setText(letters4);
                button8.setText(qt7);
                button9.setText(qt1);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(qt12);
                button13.setText(qt11);
                button14.setText(letters3);
                button15.setText(qt4);
                button16.setText(qt3);

            } else if (random == 3) {

                button1.setText(qt6);
                button2.setText(qt5);
                button3.setText(qt2);
                button4.setText(letters2);
                button5.setText(qt10);
                button6.setText(letters1);
                button7.setText(letters4);
                button8.setText(qt7);
                button9.setText(qt1);
                button10.setText(qt8);
                button11.setText(qt9);
                button12.setText(qt12);
                button13.setText(qt11);
                button14.setText(letters3);
                button15.setText(qt4);
                button16.setText(qt3);
            }


        } else if (letter_type == 5) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();


            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();
            final String qt8 = tokenizer.nextToken().trim();
            final String qt9 = tokenizer.nextToken().trim();
            final String qt10 = tokenizer.nextToken().trim();
            final String qt11 = tokenizer.nextToken().trim();

            if (random == 1) {

                button1.setText(qt6);
                button2.setText(qt5);
                button3.setText(letters3);
                button4.setText(letters4);
                button5.setText(qt10);
                button6.setText(qt9);
                button7.setText(qt3);
                button8.setText(qt7);
                button9.setText(letters5);
                button10.setText(qt8);
                button11.setText(letters1);
                button12.setText(qt1);
                button13.setText(qt11);
                button14.setText(qt2);
                button15.setText(qt4);
                button16.setText(letters2);

            } else if (random == 2) {

                button1.setText(letters5);
                button2.setText(qt5);
                button3.setText(qt8);
                button4.setText(qt10);
                button5.setText(letters4);
                button6.setText(qt9);
                button7.setText(qt3);
                button8.setText(qt7);
                button9.setText(letters2);
                button10.setText(letters3);
                button11.setText(qt4);
                button12.setText(qt1);
                button13.setText(qt11);
                button14.setText(qt2);
                button15.setText(letters1);
                button16.setText(qt6);

            } else if (random == 3) {

                button1.setText(qt10);
                button2.setText(letters2);
                button3.setText(letters3);
                button4.setText(letters4);
                button5.setText(letters5);
                button6.setText(qt9);
                button7.setText(qt3);
                button8.setText(letters1);
                button9.setText(qt5);
                button10.setText(qt8);
                button11.setText(qt4);
                button12.setText(qt1);
                button13.setText(qt11);
                button14.setText(qt2);
                button15.setText(qt7);
                button16.setText(qt6);

            }

        } else if (letter_type == 6) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();


            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();
            final String qt8 = tokenizer.nextToken().trim();
            final String qt9 = tokenizer.nextToken().trim();
            final String qt10 = tokenizer.nextToken().trim();

            if (random == 1) {

                button1.setText(qt10);
                button2.setText(qt6);
                button3.setText(letters3);
                button4.setText(qt4);
                button5.setText(qt3);
                button6.setText(letters6);
                button7.setText(letters5);
                button8.setText(letters4);
                button9.setText(qt5);
                button10.setText(qt8);
                button11.setText(letters1);
                button12.setText(qt1);
                button13.setText(qt9);
                button14.setText(qt2);
                button15.setText(qt7);
                button16.setText(letters2);


            } else if (random == 2) {

                button1.setText(letters6);
                button2.setText(qt6);
                button3.setText(qt8);
                button4.setText(qt4);
                button5.setText(qt3);
                button6.setText(qt10);
                button7.setText(letters5);
                button8.setText(qt5);
                button9.setText(letters2);
                button10.setText(letters3);
                button11.setText(qt9);
                button12.setText(qt1);
                button13.setText(letters1);
                button14.setText(qt2);
                button15.setText(qt7);
                button16.setText(letters4);

            } else if (random == 3) {
                button1.setText(qt8);
                button2.setText(letters5);
                button3.setText(letters6);
                button4.setText(qt4);
                button5.setText(letters4);
                button6.setText(qt10);
                button7.setText(qt3);
                button8.setText(letters2);
                button9.setText(qt5);
                button10.setText(qt1);
                button11.setText(qt9);
                button12.setText(letters3);
                button13.setText(qt7);
                button14.setText(qt2);
                button15.setText(letters1);
                button16.setText(qt6);

            }


        } else if (letter_type == 7) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();
            final String qt8 = tokenizer.nextToken().trim();
            final String qt9 = tokenizer.nextToken().trim();

            if (random == 1) {

                button1.setText(letters6);
                button2.setText(letters5);
                button3.setText(qt8);
                button4.setText(qt4);
                button5.setText(letters4);
                button6.setText(qt3);
                button7.setText(letters7);
                button8.setText(letters2);
                button9.setText(qt5);
                button10.setText(qt1);
                button11.setText(qt9);
                button12.setText(letters3);
                button13.setText(qt7);
                button14.setText(qt2);
                button15.setText(letters1);
                button16.setText(qt6);


            } else if (random == 2) {

                button1.setText(qt3);
                button2.setText(letters2);
                button3.setText(letters3);
                button4.setText(qt4);
                button5.setText(qt5);
                button6.setText(letters7);
                button7.setText(qt1);
                button8.setText(letters4);
                button9.setText(letters5);
                button10.setText(letters6);
                button11.setText(qt9);
                button12.setText(qt8);
                button13.setText(qt7);
                button14.setText(letters1);
                button15.setText(qt2);
                button16.setText(qt6);


            } else if (random == 3) {

                button1.setText(qt3);
                button2.setText(letters2);
                button3.setText(letters3);
                button4.setText(qt4);
                button5.setText(qt5);
                button6.setText(letters7);
                button7.setText(qt1);
                button8.setText(letters4);
                button9.setText(letters5);
                button10.setText(letters6);
                button11.setText(qt9);
                button12.setText(qt8);
                button13.setText(qt7);
                button14.setText(letters1);
                button15.setText(qt2);
                button16.setText(qt6);

            }


        } else if (letter_type == 8) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();
            final String letters8 = tokenizerw.nextToken().trim();


            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();
            final String qt8 = tokenizer.nextToken().trim();

            if (random == 1) {

                button1.setText(qt3);
                button2.setText(qt1);
                button3.setText(qt2);
                button4.setText(letters6);
                button5.setText(letters1);
                button6.setText(letters2);
                button7.setText(letters7);
                button8.setText(letters3);
                button9.setText(letters8);
                button10.setText(qt4);
                button11.setText(qt8);
                button12.setText(letters5);
                button13.setText(qt7);
                button14.setText(qt5);
                button15.setText(letters4);
                button16.setText(qt6);


            } else if (random == 2) {
                button1.setText(letters7);
                button2.setText(letters2);
                button3.setText(letters3);
                button4.setText(qt1);
                button5.setText(qt4);
                button6.setText(letters6);
                button7.setText(qt3);
                button8.setText(qt2);
                button9.setText(letters8);
                button10.setText(letters1);
                button11.setText(letters5);
                button12.setText(qt8);
                button13.setText(qt7);
                button14.setText(letters4);
                button15.setText(qt5);
                button16.setText(qt6);

            } else if (random == 3) {

                button1.setText(qt4);
                button2.setText(letters2);
                button3.setText(qt2);
                button4.setText(letters4);
                button5.setText(letters7);
                button6.setText(qt6);
                button7.setText(qt3);
                button8.setText(letters8);
                button9.setText(letters3);
                button10.setText(letters5);
                button11.setText(qt7);
                button12.setText(qt8);
                button13.setText(letters1);
                button14.setText(qt1);
                button15.setText(qt5);
                button16.setText(letters6);


                button13.setText(letters1);
                button2.setText(letters2);
                button9.setText(letters3);
                button4.setText(letters4);
                button10.setText(letters5);
                button16.setText(letters6);
                button5.setText(letters7);
                button8.setText(letters8);
            }

        } else if (letter_type == 9) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();
            final String letters8 = tokenizerw.nextToken().trim();
            final String letters9 = tokenizerw.nextToken().trim();


            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();
            final String qt7 = tokenizer.nextToken().trim();

            if (random == 1) {
                button1.setText(qt1);
                button2.setText(letters2);
                button3.setText(qt2);
                button4.setText(letters4);
                button5.setText(qt3);
                button6.setText(letters6);
                button7.setText(letters3);
                button8.setText(letters8);
                button9.setText(letters5);
                button10.setText(qt4);
                button11.setText(letters1);
                button12.setText(letters7);
                button13.setText(qt5);
                button14.setText(qt6);
                button15.setText(letters9);
                button16.setText(qt7);

            } else if (random == 2) {

                button1.setText(letters7);
                button2.setText(letters8);
                button3.setText(qt1);
                button4.setText(letters1);
                button5.setText(letters5);
                button6.setText(letters6);
                button7.setText(qt2);
                button8.setText(qt3);
                button9.setText(letters9);
                button10.setText(qt4);
                button11.setText(qt5);
                button12.setText(letters4);
                button13.setText(letters3);
                button14.setText(qt6);
                button15.setText(qt7);
                button16.setText(letters2);

            } else if (random == 3) {
                button1.setText(qt1);
                button2.setText(qt2);
                button3.setText(letters4);
                button4.setText(letters9);
                button5.setText(qt3);
                button6.setText(letters2);
                button7.setText(letters5);
                button8.setText(letters3);
                button9.setText(qt7);
                button10.setText(letters1);
                button11.setText(qt4);
                button12.setText(letters7);
                button13.setText(qt5);
                button14.setText(letters8);
                button15.setText(letters6);
                button16.setText(qt6);
            }


        } else if (letter_type == 10) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();
            final String letters7 = tokenizerw.nextToken().trim();
            final String letters8 = tokenizerw.nextToken().trim();
            final String letters9 = tokenizerw.nextToken().trim();
            final String letters10 = tokenizerw.nextToken().trim();

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();
            final String qt5 = tokenizer.nextToken().trim();
            final String qt6 = tokenizer.nextToken().trim();

            if (random == 1) {

                button1.setText(qt1);
                button2.setText(qt2);
                button3.setText(letters3);
                button4.setText(qt3);
                button5.setText(letters5);
                button6.setText(letters6);
                button7.setText(qt4);
                button8.setText(letters8);
                button9.setText(letters9);
                button10.setText(letters10);
                button11.setText(letters4);
                button12.setText(letters7);
                button13.setText(letters1);
                button14.setText(qt5);
                button15.setText(qt6);
                button16.setText(letters2);

            } else if (random == 2) {
                button1.setText(letters7);
                button2.setText(letters5);
                button3.setText(letters2);
                button4.setText(letters4);
                button5.setText(qt1);
                button6.setText(letters6);
                button7.setText(letters1);
                button8.setText(letters8);
                button9.setText(qt2);
                button10.setText(letters10);
                button11.setText(qt3);
                button12.setText(qt4);
                button13.setText(qt5);
                button14.setText(qt6);
                button15.setText(letters3);
                button16.setText(letters9);

            } else if (random == 3) {
                button1.setText(letters5);
                button2.setText(letters2);
                button3.setText(qt1);
                button4.setText(letters4);
                button5.setText(qt2);
                button6.setText(qt3);
                button7.setText(letters8);
                button8.setText(qt4);
                button9.setText(letters6);
                button10.setText(letters10);
                button11.setText(letters9);
                button12.setText(qt5);
                button13.setText(letters3);
                button14.setText(qt6);
                button15.setText(letters7);
                button16.setText(letters1);
            }

        }
    }

    public void answerset_new_version(int letter_type) {

        if (letter_type == 1) {

        } else if (letter_type == 2) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            ////////////suff/////////////

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();
            final String qt4 = tokenizer.nextToken().trim();


            if (random == 1) {
                button1.setText(qt1);
                button2.setText(qt2);
                button3.setText(qt3);
                button5.setText(letters1);
                button6.setText(qt4);
                button7.setText(letters2);
            } else if (random == 2) {
                button1.setText(qt1);
                button2.setText(letters2);
                button3.setText(qt3);
                button5.setText(qt2);
                button6.setText(letters1);
                button7.setText(qt4);
            } else if (random == 3) {
                button1.setText(qt1);
                button2.setText(letters2);
                button3.setText(qt3);
                button5.setText(letters1);
                button6.setText(qt4);
                button7.setText(qt2);
            }


        } else if (letter_type == 3) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();


            ////////////suff/////////////

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();
            final String qt3 = tokenizer.nextToken().trim();


            if (random == 1) {
                button1.setText(qt1);
                button2.setText(qt3);
                button3.setText(letters3);
                button5.setText(qt2);
                button6.setText(letters2);
                button7.setText(letters1);

            } else if (random == 2) {
                button1.setText(qt1);
                button2.setText(letters3);
                button3.setText(qt2);
                button5.setText(letters1);
                button6.setText(qt3);
                button7.setText(letters2);

            } else if (random == 3) {

                button1.setText(qt1);
                button2.setText(letters2);
                button3.setText(letters3);
                button5.setText(qt3);
                button6.setText(letters1);
                button7.setText(qt2);

            }

        } else if (letter_type == 4) {

            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();

            ////////////suff/////////////

            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();
            final String qt2 = tokenizer.nextToken().trim();


            if (random == 1) {

                button1.setText(letters4);
                button2.setText(letters2);
                button3.setText(letters3);
                button5.setText(qt2);
                button6.setText(qt1);
                button7.setText(letters1);

            } else if (random == 2) {

                button1.setText(letters2);
                button2.setText(qt2);
                button3.setText(letters3);
                button5.setText(qt1);
                button6.setText(letters1);
                button7.setText(letters4);

            } else if (random == 3) {

                button1.setText(letters2);
                button2.setText(qt1);
                button3.setText(qt2);
                button5.setText(letters3);
                button6.setText(letters1);
                button7.setText(letters4);


            }


        } else if (letter_type == 5) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();


            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");
            final String qt1 = tokenizer.nextToken().trim();


            if (random == 1) {

                button1.setText(letters2);
                button2.setText(letters5);
                button3.setText(letters3);
                button5.setText(letters4);
                button6.setText(letters1);
                button7.setText(qt1);

            } else if (random == 2) {

                button1.setText(letters5);
                button2.setText(qt1);
                button3.setText(letters1);
                button5.setText(letters4);
                button6.setText(letters2);
                button7.setText(letters3);

            } else if (random == 3) {

                button1.setText(qt1);
                button2.setText(letters2);
                button3.setText(letters3);
                button5.setText(letters5);
                button6.setText(letters4);
                button7.setText(letters1);

            }

        } else if (letter_type == 6) {
            StringTokenizer tokenizerw = new StringTokenizer(answer, ",");
            final String letters1 = tokenizerw.nextToken().trim();
            final String letters2 = tokenizerw.nextToken().trim();
            final String letters3 = tokenizerw.nextToken().trim();
            final String letters4 = tokenizerw.nextToken().trim();
            final String letters5 = tokenizerw.nextToken().trim();
            final String letters6 = tokenizerw.nextToken().trim();


            StringTokenizer tokenizer = new StringTokenizer(suff_words, ",");


            if (random == 1) {

                button1.setText(letters2);
                button2.setText(letters4);
                button3.setText(letters3);
                button5.setText(letters1);
                button6.setText(letters6);
                button7.setText(letters5);

            } else if (random == 2) {

                button1.setText(letters6);
                button2.setText(letters4);
                button3.setText(letters2);
                button5.setText(letters1);
                button6.setText(letters3);
                button7.setText(letters5);

            } else if (random == 3) {
                button1.setText(letters1);
                button2.setText(letters5);
                button3.setText(letters6);
                button5.setText(letters4);
                button6.setText(letters3);
                button7.setText(letters2);

            }


        }
    }

    public void anscheck(String ts, int i) {
        // Toast.makeText(Match_Word.this, ""+ts, Toast.LENGTH_SHORT).show();

        Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_animation);

        if (ts.length() != 0) {
            String date = sps.getString(Match_Word.this, "date");

            Cursor cs = myDbHelper.getQry("select * from answertable where answer LIKE'" + ts + "'and isfinish='0'and levelid='" + questionid + "'and gameid='" + gameid + "' and rd='" + rdvalu + "'");

            cs.moveToFirst();
            if (cs.getCount() != 0) {

                if (x <= answerlength) {
                    x++;

                    // Toast.makeText(Match_Word.this, "Correct Answer", Toast.LENGTH_SHORT).show();

                    myDbHelper.executeSql("UPDATE answertable SET isfinish=1 WHERE answer='" + ts + "'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "' ");
                    myDbHelper.executeSql("UPDATE answertable SET useranswer=1 WHERE answer='" + ts + "'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");

                    if (ans1.length() == 0) {
                        ans1.setText(ts);
                        ans1.startAnimation(levels1);
                        value_ans1.setBackgroundResource(R.drawable.tick_background);
                        value_ans1.setClickable(false);
                        if (answerlength > 1) {
                            value_ans2.setVisibility(View.VISIBLE);
                        }

                    } else if (ans2.length() == 0) {
                        ans2.setText(ts);
                        ans2.startAnimation(levels1);
                        value_ans2.setBackgroundResource(R.drawable.tick_background);
                        value_ans2.setClickable(false);
                        if (answerlength > 2) {
                            value_ans3.setVisibility(View.VISIBLE);
                        }
                    } else if (ans3.length() == 0) {
                        ans3.setText(ts);
                        ans3.startAnimation(levels1);
                        value_ans3.setBackgroundResource(R.drawable.tick_background);
                        value_ans3.setClickable(false);
                        if (answerlength > 3) {
                            value_ans4.setVisibility(View.VISIBLE);
                        }

                    } else if (ans4.length() == 0) {
                        ans4.setText(ts);
                        ans4.startAnimation(levels1);
                        value_ans4.setBackgroundResource(R.drawable.tick_background);
                        value_ans4.setClickable(false);

                        if (answerlength > 4) {
                            value_ans5.setVisibility(View.VISIBLE);
                        }
                    } else if (ans5.length() == 0) {
                        ans5.setText(ts);
                        ans5.startAnimation(levels1);
                        value_ans5.setBackgroundResource(R.drawable.tick_background);
                        value_ans5.setClickable(false);

                        if (answerlength > 5) {
                            value_ans6.setVisibility(View.VISIBLE);
                        }

                    } else if (ans6.length() == 0) {
                        ans6.setText(ts);
                        ans6.startAnimation(levels1);
                        value_ans6.setBackgroundResource(R.drawable.tick_background);
                        value_ans6.setClickable(false);

                        if (answerlength > 6) {
                            value_ans7.setVisibility(View.VISIBLE);
                        }
                    } else if (ans7.length() == 0) {
                        ans7.setText(ts);
                        ans7.startAnimation(levels1);
                        value_ans7.setBackgroundResource(R.drawable.tick_background);
                        value_ans7.setClickable(false);

                        if (answerlength > 7) {
                            value_ans8.setVisibility(View.VISIBLE);
                        }

                    } else if (ans8.length() == 0) {
                        ans8.setText(ts);
                        ans8.startAnimation(levels1);
                        value_ans8.setBackgroundResource(R.drawable.tick_background);
                        value_ans8.setClickable(false);

                        if (answerlength > 8) {
                            value_ans9.setVisibility(View.VISIBLE);
                        }
                    } else if (ans9.length() == 0) {
                        ans9.setText(ts);
                        ans9.startAnimation(levels1);
                        value_ans9.setBackgroundResource(R.drawable.tick_background);
                        value_ans9.setClickable(false);

                        if (answerlength > 9) {
                            value_ans10.setVisibility(View.VISIBLE);
                        }
                    } else if (ans10.length() == 0) {
                        ans10.setText(ts);
                        ans10.startAnimation(levels1);
                        value_ans10.setBackgroundResource(R.drawable.tick_background);
                        value_ans10.setClickable(false);

                    }
                    if (i == 1) {
                        button1.setBackgroundResource(R.drawable.right_ans);
                        button1.setEnabled(false);
                    } else if (i == 2) {
                        button2.setBackgroundResource(R.drawable.right_ans);
                        button2.setEnabled(false);
                    } else if (i == 3) {
                        button3.setBackgroundResource(R.drawable.right_ans);
                        button3.setEnabled(false);
                    } else if (i == 4) {
                        button4.setBackgroundResource(R.drawable.right_ans);
                        button4.setEnabled(false);
                    } else if (i == 5) {
                        button5.setBackgroundResource(R.drawable.right_ans);
                        button5.setEnabled(false);
                    } else if (i == 6) {
                        button6.setBackgroundResource(R.drawable.right_ans);
                        button6.setEnabled(false);
                    } else if (i == 7) {
                        button7.setBackgroundResource(R.drawable.right_ans);
                        button7.setEnabled(false);
                    } else if (i == 8) {
                        button8.setBackgroundResource(R.drawable.right_ans);
                        button8.setEnabled(false);
                    } else if (i == 9) {
                        button9.setBackgroundResource(R.drawable.right_ans);
                        button9.setEnabled(false);
                    } else if (i == 10) {
                        button10.setBackgroundResource(R.drawable.right_ans);
                        button10.setEnabled(false);
                    } else if (i == 11) {
                        button11.setBackgroundResource(R.drawable.right_ans);
                        button11.setEnabled(false);
                    } else if (i == 12) {
                        button12.setBackgroundResource(R.drawable.right_ans);
                        button12.setEnabled(false);
                    } else if (i == 13) {
                        button13.setBackgroundResource(R.drawable.right_ans);
                        button13.setEnabled(false);
                    } else if (i == 14) {
                        button14.setBackgroundResource(R.drawable.right_ans);
                        button14.setEnabled(false);
                    } else if (i == 15) {
                        button15.setBackgroundResource(R.drawable.right_ans);
                        button15.setEnabled(false);
                    } else if (i == 16) {
                        button16.setBackgroundResource(R.drawable.right_ans);
                        button16.setEnabled(false);
                    }
                    coinanim();
                    if (x >= answerlength) {
                        price_update();
                        enablefalse();
                        if (date.equals("0")) {
                            newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'");

                        } else {
                            newhelper.executeSql("UPDATE newmaintable SET daily='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "'");

                        }
                        // newhelper.executeSql("UPDATE newmaintable SET isfinish='1' WHERE questionid='" + questionid + "'and gameid='" + gameid + "' and daily='" + 1 + "'");

                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        focus.stop();

                        Handler handler = new Handler(Looper.myLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                adShow();
                            }
                        }, 3000);
                    }

                }
            } else {
                coinanim_red();
                if (i == 1) {
                    button1.setBackgroundResource(R.drawable.worng_ans);
                    button1.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts1", 1);
                } else if (i == 2) {
                    button2.setBackgroundResource(R.drawable.worng_ans);
                    button2.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts2", 1);
                } else if (i == 3) {
                    button3.setBackgroundResource(R.drawable.worng_ans);
                    button3.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts3", 1);
                } else if (i == 4) {
                    button4.setBackgroundResource(R.drawable.worng_ans);
                    button4.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts4", 1);
                } else if (i == 5) {
                    button5.setBackgroundResource(R.drawable.worng_ans);
                    button5.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts5", 1);
                } else if (i == 6) {
                    button6.setBackgroundResource(R.drawable.worng_ans);
                    button6.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts6", 1);
                } else if (i == 7) {
                    button7.setBackgroundResource(R.drawable.worng_ans);
                    button7.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts7", 1);
                } else if (i == 8) {
                    button8.setBackgroundResource(R.drawable.worng_ans);
                    button8.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts8", 1);
                } else if (i == 9) {
                    button9.setBackgroundResource(R.drawable.worng_ans);
                    button9.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts9", 1);
                } else if (i == 10) {
                    button10.setBackgroundResource(R.drawable.worng_ans);
                    button10.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts10", 1);
                } else if (i == 11) {
                    button11.setBackgroundResource(R.drawable.worng_ans);
                    button11.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts11", 1);
                } else if (i == 12) {
                    button12.setBackgroundResource(R.drawable.worng_ans);
                    button12.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts12", 1);
                } else if (i == 13) {
                    button13.setBackgroundResource(R.drawable.worng_ans);
                    button13.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts13", 1);
                } else if (i == 14) {
                    button14.setBackgroundResource(R.drawable.worng_ans);
                    button14.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts14", 1);
                } else if (i == 15) {
                    button15.setBackgroundResource(R.drawable.worng_ans);
                    button15.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts15", 1);
                } else if (i == 16) {
                    button16.setBackgroundResource(R.drawable.worng_ans);
                    button16.setEnabled(false);
                    sps.putInt(Match_Word.this, "mwbts16", 1);
                }

                //Toast.makeText(Match_Word.this, "Worng Answer", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) handler.removeCallbacks(my_runnable);
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
        String date = sps.getString(Match_Word.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        }


    }

    public void showpopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.settings, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        final String snd = sps.getString(Match_Word.this, "snd");
        toggleButton = (TextView) popupView.findViewById(R.id.toggle);

        if (snd.equals("off")) {

            toggleButton.setBackgroundResource(R.drawable.off);

        } else if (snd.equals("on")) {

            toggleButton.setBackgroundResource(R.drawable.on);

        }

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snd = sps.getString(Match_Word.this, "snd");
                System.out.println("*****click");
                if (snd.equals("off")) {

                    System.out.println("*****on");
                    sps.putString(Match_Word.this, "snd", "on");
                    toggleButton.setBackgroundResource(R.drawable.on);
                    sv = 1;

                }
                if (snd.equals("on")) {
                    System.out.println("*****off");

                    sps.putString(Match_Word.this, "snd", "off");
                    toggleButton.setBackgroundResource(R.drawable.off);
                    sv = 0;

                }
            }
        });

        popupWindow.showAsDropDown(p_setting, 50, -10);

    }

    public void coinanim() {
        ////
        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + 10;
            myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
        }

        //score intial

        spz4.play(soundId4, sv, sv, 0, 0, sv);
        p_coins.setVisibility(View.VISIBLE);
        int[] locationInWindow = new int[2];
        p_coins.getLocationInWindow(locationInWindow);
        int[] locationOnScreen = new int[2];
        p_coins.getLocationOnScreen(locationOnScreen);
        float sourceX = locationOnScreen[0];
        float sourceY = locationOnScreen[1];
        int[] locationInWindowSecond = new int[2];
        s_score_edit.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        s_score_edit.getLocationOnScreen(locationOnScreenSecond);
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

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                s_score_edit.startAnimation(levels1);
            }
        }, 1500);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cfx = null;
                Cursor ch = null;
                try {
                    System.out.println("####################***********cfx");
                    cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        String aStringx = Integer.toString(skx);
                        s_score_edit.setText(aStringx);
                    }
                    ch = myDbHelper.getQry("SELECT * FROM score ");
                    ch.moveToFirst();
                    if (ch.getCount() != 0) {
                        int sh = ch.getInt(ch.getColumnIndexOrThrow("l_points"));
                        int shh = sh + 50;
                        myDbHelper.executeSql("UPDATE score SET l_points='" + shh + "'");
                    }
                } catch (Exception e) {
                    // exception handling
                    System.out.println("####################***********Exception");
                } finally {
                    System.out.println("####################***********close");
                    if (cfx != null) {
                        cfx.close();
                    }
                    if (ch != null) {
                        ch.close();
                    }
                }


                // setSc();
            }
        }, 1200);

    }

    public void coinanim_red() {
////
        Cursor cfx = null;
        try {
            cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                int spx = skx - 20;
                myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
            }
        } catch (Exception e) {
            // exception handling
        } finally {
            if (cfx != null) {
                cfx.close();
            }
        }


        //score intial


        // spz4.play(soundId4, sv, sv, 0, 0, sv);
        worng.play(soundId2, sv, sv, 0, 0, sv);
        p_coins_red.setVisibility(View.VISIBLE);
        int[] locationInWindow = new int[2];
        p_coins_red.getLocationInWindow(locationInWindow);
        int[] locationOnScreen = new int[2];
        p_coins_red.getLocationOnScreen(locationOnScreen);
        float sourceX = locationOnScreen[0];
        float sourceY = locationOnScreen[1];
        int[] locationInWindowSecond = new int[2];
        p_coins.getLocationInWindow(locationInWindowSecond);
        int[] locationOnScreenSecond = new int[2];
        p_coins.getLocationOnScreen(locationOnScreenSecond);
        float destinationX = locationOnScreenSecond[0];
        float destinationY = locationOnScreenSecond[1];
        TranslateAnimation transAnimation = new TranslateAnimation(0f, (destinationX - sourceX), 0f, (destinationY - sourceY));
        transAnimation.setDuration(700);
        p_coins_red.startAnimation(transAnimation);
        p_coins_red.postDelayed(new Runnable() {
            @Override
            public void run() {
                p_coins_red.setVisibility(View.INVISIBLE);
            }
        }, transAnimation.getDuration());

        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_animation);
                s_score_edit.startAnimation(levels1);
            }
        }, 1500);

        Handler handler21 = new Handler(Looper.myLooper());
        handler21.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cfx = null;
                try {
                    cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        String aStringx = Integer.toString(skx);
                        s_score_edit.setText(aStringx);
                    }
                } catch (Exception e) {
                    // exception handling
                } finally {
                    if (cfx != null) {
                        cfx.close();
                    }
                }


                // setSc();
            }
        }, 1000);

    }

    public void setSc() {

        if (s == 1) {
            openDialog_p.dismiss();
            s = 0;
        }

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
        video_earn.setText("காணொளியை பார்த்து " + sps.getInt(Match_Word.this, "reward_coin_txt") + "+ நாணயங்கள் பெற");

        ImageView prize_logo = (ImageView) openDialog_s.findViewById(R.id.prize_logo);
        if (sps.getInt(Match_Word.this, "remoteConfig_prize") == 1) {
            prize_logo.setVisibility(View.VISIBLE);
        } else {
            prize_logo.setVisibility(View.GONE);
        }
        prize_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    if (sps.getString(Match_Word.this, "price_registration").equals("com")) {
                        finish();
                        Intent i = new Intent(Match_Word.this, Game_Status.class);
                        startActivity(i);
                    } else {
                        if (sps.getString(Match_Word.this, "otp_verify").equals("yes")) {
                            finish();
                            Intent i = new Intent(Match_Word.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            finish();
                            Intent i = new Intent(Match_Word.this, Price_Login.class);
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(Match_Word.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Match_Word.this, R.anim.blink_animation);
        vid_earn.startAnimation(myFadeInAnimation);


        if (sps.getInt(Match_Word.this, "purchase_ads") == 1) {
            ads_layout_bottom.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(context)) {
                //New_Main_Activity.load_add_fb_rect_score_screen(context, ads_layout_bottom);
            } else {
                ads_layout_bottom.setVisibility(View.GONE);
            }

            //New_Main_Activity.load_addFromMain_multiplayer(Match_Word.this, ads_layout_bottom);
        }


        if (sps.getString(Match_Word.this, "complite_reg").equals("yes")) {
            String dates = sps.getString(Match_Word.this, "date");
            if (dates.equals("0")) {
                rewardvideo.setVisibility(View.VISIBLE);
            }
        }
        Cursor csk = null;
        try {
            csk = myDbHelper.getQry("select * from answertable where gameid='" + gameid + "' and levelid='" + questionid + "' and rd='" + rdvalu + "' and isfinish='1' and useranswer='0'");
            csk.moveToFirst();
            if (csk.getCount() == 0) {
                rewardvideo.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            // exception handling
        } finally {
            if (csk != null) {
                csk.close();
            }
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
                if (Utils.isNetworkAvailable(context)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        show_reward();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    show_reward();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {

                                    rewarded_adnew();
                                    Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

            }
        });

        rewardvideo.setOnClickListener(v -> {
            rvo = 2;
            if (Utils.isNetworkAvailable(context)) {
                final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");
                if (fb_reward == 1) {
                    reward_progressBar.dismiss();
                    show_reward();
                    rewardvideo.setVisibility(View.INVISIBLE);
                } else {
                    new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reward_progressBar.dismiss();
                            if (fb_reward == 1) {
                                show_reward();
                                // mShowVideoButton.setVisibility(View.VISIBLE);
                            } else {

                                rewarded_adnew();
                                Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000);
                }
            } else {

                Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

            }
        });
        wtp.setOnClickListener(view -> {
            if (isNetworkAvailable()) {
                final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                if (appinstalled) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.setPackage("com.whatsapp");
                    String msg = ("நான் சொல்லிஅடி செயலியில் சொல்லுக்குள் சொல் நிலை " + s_word_number.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
        fbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                        String msg = ("நான் சொல்லிஅடி செயலியில் சொல்லுக்குள் சொல் நிலை" + s_word_number.getText().toString() + " ஐ முடித்துள்ளேன்.நீங்களும் விளையாட விரும்பினால் கீழே உள்ள இணைய முகவரியை சொடுக்கவும்் https://goo.gl/CcA9a8");
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
        int uans = 0;
        Cursor cd = null;
        try {
            cd = myDbHelper.getQry("SELECT answer FROM answertable where useranswer='1'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
            cd.moveToFirst();
            if (cd.getCount() != 0) {
                uans = cd.getCount();
            }
        } catch (Exception e) {
            // exception handling
        } finally {
            if (cd != null) {
                cd.close();
            }
        }

        int tt_time;

        if (answer_types == 6) {
            tt_time = 123;
        } else {
            tt_time = 123;
        }
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

            String date = sps.getString(Match_Word.this, "date");
            if (!date.equals("0")) {
                next_continue.setText("சரி");
            }
            Handler handler1 = new Handler(Looper.myLooper());
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    coin.play(soundId4, sv, sv, 0, 0, sv);
                    //play1.start();
                    cns3.setVisibility(View.VISIBLE);
                }
            }, 500);
            Handler handler2 = new Handler(Looper.myLooper());
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // play2.start();
                    coin.play(soundId4, sv, sv, 0, 0, sv);
                    cns1.setVisibility(View.VISIBLE);
                }
            }, 1000);
            Handler handler3 = new Handler(Looper.myLooper());
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //  play3.start();
                    coin.play(soundId4, sv, sv, 0, 0, sv);
                    cns2.setVisibility(View.VISIBLE);
                }
            }, 1500);


            Handler handler6 = new Handler(Looper.myLooper());
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
            Handler handler7 = new Handler(Looper.myLooper());
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
            Handler handler8 = new Handler(Looper.myLooper());
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

            Cursor cfx = null;
            try {
                cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    tt_tot2 = tt_case2 + 30;
                    String aStringx = Integer.toString(tt_case2);
                    ttscores.setText(aStringx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + tt_tot2 + "'");
                }
            } catch (Exception e) {
                // exception handling
            } finally {
                if (cfx != null) {
                    cfx.close();
                }
            }


            Handler handler11 = new Handler(Looper.myLooper());
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

            Handler hand = new Handler(Looper.myLooper());
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
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();

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
            String date = sps.getString(Match_Word.this, "date");
            if (!date.equals("0")) {
                next_continue.setText("சரி");
            }
            Cursor cfx = null;
            try {
                cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    tt_case2 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    tt_tot2 = tt_case2;
                    String aStringx = Integer.toString(tt_case2);
                    ttscores.setText(aStringx);
                }
            } catch (Exception e) {
                // exception handling
            } finally {
                if (cfx != null) {
                    cfx.close();
                }
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
                    dia_dismiss = 1;
                    openDialog_s.dismiss();
                    next();
                }
            });
        }

        openDialog_s.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dia_dismiss != 1) {
                    sps.putString(Match_Word.this, "game_area", "on");
                    tot2 = 0;
                    tt_tot2 = 0;
                    case2 = 0;
                    y = 0;
                    tt_case2 = 0;
                    String date = sps.getString(Match_Word.this, "date");
                    if (date.equals("0")) {


                        if (main_act.equals("")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            finish();
                            openDialog_s.dismiss();

                        }
                    } else {
                        if (sps.getString(Match_Word.this, "Exp_list").equals("on")) {
                            finish();
                            openDialog_s.dismiss();
                            Intent i = new Intent(Match_Word.this, Expandable_List_View.class);
                            startActivity(i);

                        } else {
                            if (main_act.equals("")) {
                                finish();
                                openDialog_s.dismiss();
                                Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(Match_Word.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }

    //*********************reward videos process 3***********************

    public void vidcoinearn() {
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea = ea + setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
            TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
            Cursor cfx = null;
            try {
                cfx = myDbHelper.getQry("SELECT * FROM score ");
                cfx.moveToFirst();
                if (cfx.getCount() != 0) {
                    final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                    spxdr = skx;

                }
            } catch (Exception e) {
                // exception handling
            } finally {
                if (cfx != null) {
                    cfx.close();
                }
            }


            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s_score_edit.setText("" + spxdr);
                    openDialog.dismiss();
                    //mCoinCount = 0;
                }
            });

            openDialog.show();
        }

    }


    //reward videos***********************//

    public void share_earn(int a) {

        final Dialog openDialog = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = null;
        try {
            cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                skxw = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));

            }
        } catch (Exception e) {
            // exception handling
        } finally {
            if (cfx != null) {
                cfx.close();
            }
        }


        b_scores.setText("" + a);


        final int finalSkx = skxw;
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_score_edit.setText("" + skxw);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }

    public void share_earn2(int a) {
        int skx = 0;
        final Dialog openDialog = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.share_dialog2);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        Cursor cfx = null;
        try {
            cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            if (cfx.getCount() != 0) {
                skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                b_scores.setText("" + a);


            }
        } catch (Exception e) {
            // exception handling
        } finally {
            if (cfx != null) {
                cfx.close();
            }
        }


        final int finalSkx = skx;
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttscores.setText("" + finalSkx);
                s_score_edit.setText("" + finalSkx);
                openDialog.dismiss();
                //mCoinCount = 0;
            }
        });

        openDialog.show();
    }

    public void industrialload() {
        if (mInterstitialAd != null) return;
        Log.i(TAG, "onAdLoadedCalled");
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getResources().getString(R.string.Game2_Stage_Close_PS), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                interstiallistener();
                Log.i(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d(TAG, loadAdError.toString());
                mInterstitialAd = null;
                handler = null;
                Log.i(TAG, "onAdLoadedfailed" + loadAdError.getMessage());
            }
        });

    }

    public void interstiallistener() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
                handler = null;
                Utills.INSTANCE.Loading_Dialog_dismiss();
                setSc();
                industrialload();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
                handler = null;
                Utills.INSTANCE.Loading_Dialog_dismiss();
                sps.putInt(getApplicationContext(), "Game2_Stage_Close_PS", 0);
                setSc();
            }

        });
    }

    public void adShow() {
        if (sps.getInt(getApplicationContext(), "Game2_Stage_Close_PS") == Utills.interstitialadCount && mInterstitialAd != null) {
            sps.putInt(getApplicationContext(), "Game2_Stage_Close_PS", 0);
            Utills.INSTANCE.Loading_Dialog(this);
            handler = new Handler(Looper.myLooper());
            my_runnable = () -> {
                mInterstitialAd.show(this);
            };
            handler.postDelayed(my_runnable, 2500);
        } else {
            sps.putInt(getApplicationContext(), "Game2_Stage_Close_PS", (sps.getInt(getApplicationContext(), "Game2_Stage_Close_PS") + 1));
            if (sps.getInt(this, "Game2_Stage_Close_PS") > Utills.interstitialadCount)
                sps.putInt(this, "Game2_Stage_Close_PS", 0);
            setSc();
            //Toast.makeText(context, "" + sps.getInt(this, "Game2_Stage_Close_PS"), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);

        if (requestCode == 0) {
            if (Utils.isNetworkAvailable(Match_Word.this)) {
                download_datas();
            } else {
                head.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Match_Word.this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("புதிய வினாக்களை பதிவிறக்கம் செய்ய இணையத்தை ஆன் செய்யவும்").setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        sps.putInt(Match_Word.this, "goto_sett", 1);
                        dialog.dismiss();
                    }
                }).setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String date = sps.getString(Match_Word.this, "date");
                        if (date.equals("0")) {
                            backexitnet();
                        } else {
                            backexitnet();
                        }
                        dialog.dismiss();
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        }

        if (requestCode == 15) {
            if (resultCode == -1) {
                Cursor cfx = null;
                try {
                    cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + 10;
                        String aStringx = Integer.toString(spx);
                        // s_score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    }
                } catch (Exception e) {
                    // exception handling
                } finally {
                    if (cfx != null) {
                        cfx.close();
                    }
                }


                //setcoin(1);
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

                if (sps.getString(Match_Word.this, "complite_reg").equals("yes")) {
                    Cursor cn = null;
                    try {
                        cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                        cn.moveToFirst();
                        if (cn.getCount() != 0) {
                            int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                            int gm1s = gm1 + 1;
                            myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");

                        }
                    } catch (Exception e) {
                        // exception handling
                    } finally {
                        if (cn != null) {
                            cn.close();
                        }
                    }


                }
                ///Reward Share
            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 12) {
            if (resultCode == -1) {
                Cursor cfx = null;
                try {
                    cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + 20;
                        String aStringx = Integer.toString(spx);
                        // s_score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    }
                } catch (Exception e) {
                    // exception handling
                } finally {
                    if (cfx != null) {
                        cfx.close();
                    }
                }


                share_earn(20);
                if (sps.getString(Match_Word.this, "complite_reg").equals("yes")) {
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
                    Cursor cn = null;
                    try {
                        cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                        cn.moveToFirst();
                        if (cn.getCount() != 0) {
                            int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                            int gm1s = gm1 + 1;
                            myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                            ///Reward Share
                        }
                    } catch (Exception e) {
                        // exception handling
                    } finally {
                        if (cn != null) {
                            cn.close();
                        }
                    }


                }
            } else {
            }
        }

        if (requestCode == 21) {
            if (resultCode == -1) {
                Cursor cfx = null;
                try {
                    cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + 20;
                        String aStringx = Integer.toString(spx);
                        // s_score.setText(aStringx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    }
                } catch (Exception e) {
                    // exception handling
                } finally {
                    if (cfx != null) {
                        cfx.close();
                    }
                }


                share_earn2(20);
                if (sps.getString(Match_Word.this, "complite_reg").equals("yes")) {
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
                    Cursor cn = null;
                    try {
                        cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                        cn.moveToFirst();
                        if (cn.getCount() != 0) {
                            int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                            int gm1s = gm1 + 1;
                            myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                            ///Reward Share
                        }
                    } catch (Exception e) {
                        // exception handling
                    } finally {
                        if (cn != null) {
                            cn.close();
                        }
                    }


                }
            } else {
            }
        }
        if (requestCode == 16) {
            if (resultCode == -1) {
                Cursor cfx = null;
                try {
                    cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + 10;
                        String aStringx = Integer.toString(spx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");
                    }
                } catch (Exception e) {
                    // exception handling
                } finally {
                    if (cfx != null) {
                        cfx.close();
                    }
                }


                share_earn2(10);
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

                if (sps.getString(Match_Word.this, "complite_reg").equals("yes")) {
                    Cursor cn = null;
                    try {
                        cn = myDbHelper.getQry("SELECT * FROM userdata_r  where type ='" + retype + "'and date='" + str_date1 + "'");
                        cn.moveToFirst();
                        if (cn.getCount() != 0) {
                            int gm1 = cn.getInt(cn.getColumnIndexOrThrow("score"));
                            int gm1s = gm1 + 1;
                            myDbHelper.executeSql("UPDATE userdata_r SET score='" + gm1s + "' where type ='" + retype + "'and date='" + str_date1 + "'");
                        }
                    } catch (Exception e) {
                        // exception handling
                    } finally {
                        if (cn != null) {
                            cn.close();
                        }
                    }


                }
                ///Reward Share

            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void dialog(int i) {
        openDialog_earncoin = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = (TextView) openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = (TextView) openDialog_earncoin.findViewById(R.id.ssss);

        TextView wpro = (TextView) openDialog_earncoin.findViewById(R.id.wpro);
        if (i == 1) {
            cancel.setVisibility(View.INVISIBLE);
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
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rvo = 1;
                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(context, "" + "Reward video", "Loading...");

                    if (fb_reward == 1) {
                        focus.stop();
                        ttstop = focus.getBase() - SystemClock.elapsedRealtime();
                        String date = sps.getString(Match_Word.this, "date");
                        int pos;
                        if (date.equals("0")) {
                            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");
                            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        } else {
                            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");
                            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
                        }
                        reward_progressBar.dismiss();
                        show_reward();
                        openDialog_earncoin.cancel();

                        // mShowVideoButton.setVisibility(View.VISIBLE);
                    } else {

                        rewarded_adnew();
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();

                                Toast.makeText(context, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
             /*   rvo = 1;
                extra_coin_s = 0;

                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(Match_Word.this, "" + "Reward video", "Loading...");


                   *//*
                 *//*
                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }*/

            }
        });

        wp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    final boolean appinstalled = appInstalledOrNot("com.whatsapp");
                    if (appinstalled) {
                        openDialog_earncoin.dismiss();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.whatsapp");
                        String msg = ("நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" + "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        i.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivityForResult(Intent.createChooser(i, "Share via"), 12);

                        ///shareing reward
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

                if (isNetworkAvailable()) {
                    final boolean appinstalled = appInstalledOrNot("com.google.android.apps.plus");
                    if (appinstalled) {
                        openDialog_earncoin.dismiss();
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

            }

        });
        openDialog_earncoin.show();
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
                    newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

                    //Uri uri = Uri.fromFile(file);
                    Uri uri = FileProvider.getUriForFile(Match_Word.this, Match_Word.this.getPackageName(), file);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setPackage(a);
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TEXT, " நித்ராவின் சொல்லிஅடி செயலியை விளையாடிக் கொண்டிருக்கிறேன் இந்த இணைச்சொற்களை  கண்டுபிடி விளையாட்டின் விடையை என்னோடு பகிர்ந்து கொள்ளுங்கள்.  https://goo.gl/bRqmah");
                    share.putExtra(Intent.EXTRA_SUBJECT, "Solli_adi");
                    //  share.putExtra(android.content.Intent.EXTRA_TEXT,"Shared via Tamil Calendar Offline.\nClick here to download"+ "\nhttps://goo.gl/ITvWGu");
                    startActivity(Intent.createChooser(share, "Share Card Using"));
                } else {
                    Toast.makeText(Match_Word.this, "இந்த செயலி தங்களிடம் இல்லை", Toast.LENGTH_SHORT).show();
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void permission(final String a) {
        focus.stop();
        ttstop = focus.getBase() - SystemClock.elapsedRealtime();


        String date = sps.getString(Match_Word.this, "date");
        int pos;
        if (date.equals("0")) {
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "'");

            //  myDbHelper.executeSql("UPDATE maintable SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        } else {
            newhelper.executeSql("UPDATE newmaintable SET playtime='" + ttstop + "' WHERE questionid='" + questionid + "' and gameid='" + gameid + "' and daily='0'");

            //  myDbHelper.executeSql("UPDATE dailytest SET noclue='" + noclue + "' WHERE levelid='" + w_id + "' and gameid='" + gameid + "'");
        }
        helpshare(a);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 152) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(Match_Word.this, "permission", 1);
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
                        sps.putInt(Match_Word.this, "permission", 2);
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                        sps.putInt(Match_Word.this, "permission", 0);
                    }
                }
            }

        }
    }

    public void nextgamesdialog() {
        final Dialog openDialog = new Dialog(Match_Word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.nextgame_find);
        TextView next_game = (TextView) openDialog.findViewById(R.id.next_game);
        TextView p_game = (TextView) openDialog.findViewById(R.id.picgame);
        TextView c_game = (TextView) openDialog.findViewById(R.id.hintgame);
        TextView s_game = (TextView) openDialog.findViewById(R.id.solgame);
        TextView w_game = (TextView) openDialog.findViewById(R.id.wordgame);
        TextView exit = (TextView) openDialog.findViewById(R.id.exit);
        openDialog.setCancelable(false);

        String date = sps.getString(Match_Word.this, "date");
        if (date.equals("0")) {
            next_game.setText("இணைச்சொற்களை  கண்டுபிடி தற்போதைய நிலைகள் முடிவடைந்துவிட்டது தங்களுக்கான புதிய நிலைகள் விரைவில் இணைக்கப்படும்.மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள்.");
        } else {
            next_game.setText("இணைச்சொற்களை  கண்டுபிடி புதிய  பதிவுகள் இல்லை. மேலும் நீங்கள் சிறப்பாக விளையாட  காத்திருக்கும் விளையாட்டுக்கள். ");

        }

        c_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Clue_Game_Hard.class);
                startActivity(i);
            }
        });
        s_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Solukul_Sol.class);
                startActivity(i);
            }
        });
        w_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
        p_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Picture_Game_Hard.class);
                startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    sps.putString(Match_Word.this, "game_area", "on");
                    finish();
                }
                sps.putString(Match_Word.this, "date", "0");
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
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Match_Word.class);
                startActivity(i);
            }
        });
        odd_man_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Odd_man_out.class);
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
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Opposite_word.class);
                startActivity(i);
            }
        });
        ote_to_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Ote_to_Tamil.class);
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
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Makeword_Rightorder.class);
                startActivity(i);
            }
        });
        puthir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Riddle_game.class);
                startActivity(i);
            }
        });
        tirukural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Tirukural.class);
                startActivity(i);
            }
        });
        pilaithiruthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, WordError_correction.class);
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
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Fill_in_blanks.class);
                startActivity(i);
            }
        });


        Newgame_DataBaseHelper6 newhelper6 = new Newgame_DataBaseHelper6(Match_Word.this);
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
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Jamble_word_game.class);
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
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Missing_Words.class);
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
                sps.putString(Match_Word.this, "date", "0");
                Intent i = new Intent(Match_Word.this, Find_difference_between_pictures.class);
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
                    Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
                    startActivity(i);
                } else {

                    sps.putString(Match_Word.this, "game_area", "on");
                    finish();
                }
                openDialog.dismiss();
                //sps.putString(Odd_man_out.this, "date", "0");
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
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
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
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
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
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
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
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
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
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
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
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
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
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
                titt.setText("நித்ராவின் வேலைவாய்ப்புகள்");
                inss.setTag("https://play.google.com/store/apps/details?id=nithra.jobs.career.placement&referrer=utm_source%3DSOLLIADI_CROSS");
                logo.setImageResource(R.drawable.jobs_logo);
            } else {
                titt.setText("நித்ரா  செயலிகள்");
                inss.setTag("https://play.google.com/store/apps/developer?id=Nithra");
                logo.setImageResource(R.drawable.nithralogo);
            }
        }

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(context)) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(inss.getTag().toString())));
                } else {
                    Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
        });

        inss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(context)) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(view.getTag().toString())));
                } else {
                    Utils.toast_center(context, "இணையதள சேவையை சரிபார்க்கவும் ");
                }
            }
        });
    }

    private boolean appInstalledOrNot(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    //*** In ad area **
    @Override
    public void onDestroy() {
        super.onDestroy();
        // uiHelper.onDestroy();
        if (openDialog_p != null && openDialog_p.isShowing()) {
            openDialog_p.dismiss();
        }
        rewardedAd = null;
        mInterstitialAd = null;
        handler = null;
    }


    public void showcase_dismiss() {
        Handler handler30 = new Handler(Looper.myLooper());
        handler30.postDelayed(() -> {

            if (sps.getString(Match_Word.this, "showcase_dismiss_mw").equals("")) {
                showcase_dismiss();
            } else {
                sps.putString(context, "mtc_time_start", "yes");
                focus.setBase(SystemClock.elapsedRealtime());
                focus.start();

            }

        }, 800);
    }

    public void price_update() {
        ////////////////Prize//////////////////
        int uans = 0;
        Cursor cd = myDbHelper.getQry("SELECT answer FROM answertable where useranswer='1'and levelid='" + questionid + "'and gameid='" + gameid + "'and rd='" + rdvalu + "'");
        cd.moveToFirst();
        if (cd.getCount() != 0) {
            uans = cd.getCount();
        }
        long timeElapsed = SystemClock.elapsedRealtime() - focus.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

        int min = hours * 60;
        int sec = min * 60;
        int sec2 = minutes * 60;
        f_sec = sec + sec2 + seconds;
        String date = sps.getString(Match_Word.this, "date");
        if (date.equals("0")) {
            int val = sps.getInt(Match_Word.this, "mwbts1") + sps.getInt(Match_Word.this, "mwbts2") + sps.getInt(Match_Word.this, "mwbts3") + sps.getInt(Match_Word.this, "mwbts4") + sps.getInt(Match_Word.this, "mwbts5") + sps.getInt(Match_Word.this, "mwbts6");
            System.out.println("##########################val" + val);
            if (val == 0) {
                if (timeElapsed <= 91300) {
                    if (uans == answerlength) {
                        prize_data_update(Match_Word.this, 75);
                    } else if (uans != 0) {
                        prize_data_update(Match_Word.this, 25);
                    }
                } else if (timeElapsed > 91300) {
                    if (uans == answerlength) {
                        prize_data_update(Match_Word.this, 50);
                    } else if (uans != 0) {
                        prize_data_update(Match_Word.this, 25);
                    }
                } else {
                    prize_data_update(Match_Word.this, 25);
                }
            } else {
                prize_data_update(Match_Word.this, 25);
            }

        } else {
            int val = sps.getInt(Match_Word.this, "mwbts1") + sps.getInt(Match_Word.this, "mwbts2") + sps.getInt(Match_Word.this, "mwbts3") + sps.getInt(Match_Word.this, "mwbts4") + sps.getInt(Match_Word.this, "mwbts5") + sps.getInt(Match_Word.this, "mwbts6");
            System.out.println("##########################val" + val);
            if (val == 0) {
                if (timeElapsed <= 91300) {
                    if (uans == answerlength) {
                        prize_data_update(Match_Word.this, 100);
                    } else if (uans != 0) {
                        prize_data_update(Match_Word.this, 50);
                    }
                } else if (timeElapsed > 91300) {
                    if (uans == answerlength) {
                        prize_data_update(Match_Word.this, 75);
                    } else if (uans != 0) {
                        prize_data_update(Match_Word.this, 50);
                    }
                } else {
                    prize_data_update(Match_Word.this, 50);
                }
            } else {
                prize_data_update(Match_Word.this, 50);
            }

        }
        ////////////////Prize//////////////////
    }

    private void download_datas() {
        Cursor cz = newhelper.getQry("select * from newmaintable where gameid='" + gameid + "'order by questionid desc limit 1");
        String questionid_d = "";
        cz.moveToFirst();
        if (cz.getCount() != 0) {
            questionid_d = String.valueOf(cz.getInt(cz.getColumnIndexOrThrow("questionid")));
        }

        System.out.println("----------------------Download_server");
        Download_data_server download_data_server = new Download_data_server(Match_Word.this, questionid_d, "6");
        download_data_server.execute();
    }

    public void downloaddata_regular() {

        head.setVisibility(View.INVISIBLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Match_Word.this);
        // alertDialogBuilder.setTitle("Update available");
        alertDialogBuilder.setMessage("மேலும் விளையாட வினாக்களை பதிவிறக்கம் செய்ய விரும்புகிறீர்களா ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton("ஆம்", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DownLoad Letters and Words

                if (Utils.isNetworkAvailable(Match_Word.this)) {
                    download_datas();
                } else {

                    head.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Match_Word.this);                           /* .setTitle("Delete entry")*/
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage("புதிய பதிவுகளை  பதிவிறக்கம் செய்ய இணையதள சேவையை சரிபார்க்கவும்").setPositiveButton("அமைப்பு", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete

                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                            sps.putInt(Match_Word.this, "goto_sett", 1);


                            dialog.dismiss();
                        }
                    }).setNegativeButton("பின்னர்", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            sps.putString(Match_Word.this, "game_area", "on");

                            String date = sps.getString(Match_Word.this, "date");
                            if (date.equals("0")) {
                                if (main_act.equals("")) {
                                    finish();
                                    Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
                                    startActivity(i);
                                } else {
                                    finish();
                                }
                            } else {
                                if (date.equals("0")) {
                                    backexitnet();
                                } else {
                                    backexitnet();
                                }
                            }
                            dialog.dismiss();
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert).show();
                }

            }
        });
        alertDialogBuilder.setPositiveButton("இல்லை ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                sps.putString(Match_Word.this, "game_area", "on");
                String date = sps.getString(Match_Word.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        finish();
                        Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                } else {
                    finish();
                    Intent i = new Intent(Match_Word.this, New_Main_Activity.class);
                    startActivity(i);
                }
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void rewarded_adnew() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, getResources().getString(R.string.Reward), adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error.
                Log.d(TAG, loadAdError.toString());
                rewardedAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                rewardedAd = ad;
                fb_reward = 1;
                adslisner();
                Log.d(TAG, "Ad was loaded.");
            }
        });


    }

    public void adslisner() {
        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {

            @Override
            public void onAdDismissedFullScreenContent() {
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
                    Handler handler = new Handler(Looper.myLooper());
                    handler.postDelayed(() -> {
                        if (rvo == 2) {
                            share_earn2(mCoinCount);
                        } else {
                            vidcoinearn();
                        }
                    }, 500);
                } else {
                    Toast.makeText(context, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
                }

                fb_reward = 0;

            }

        });
    }

    public void show_reward() {
        if (rewardedAd != null) {
            rewardedAd.show(this, rewardItem -> {
                // Handle the reward.
                Log.d(TAG, "The user earned the reward.");
                int rewardAmount = rewardItem.getAmount();
                String rewardType = rewardItem.getType();
                reward_status = 1;
            });
        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }
    }

}
