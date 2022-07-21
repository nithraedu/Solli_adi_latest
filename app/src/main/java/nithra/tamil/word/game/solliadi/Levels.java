package nithra.tamil.word.game.solliadi;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.widget.Toolbar;


import com.facebook.ads.NativeAdLayout;





import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;


public class Levels extends AppCompatActivity {
    TextView time1, time2, time3, score1, score2, score3, levelid, levelname, l_id_name;
    Typeface typ, tyr;
    DataBaseHelper myDbHelper;
    TextView levela, levelb, levelc;
    public static SharedPreferences mPreferences;
    SQLiteDatabase db;
    TextView action;
    TextView intro;
    MediaPlayer play1;
    TextView ttr_intro;
    ToggleButton toggleButton;
    RelativeLayout levels, levels2, levels3;
    ImageView lock1, lock2, lock3;
    SharedPreference spa = new SharedPreference();
    LinearLayout adds;

    TextView l_word1, l_word2, l_word3;
    Context context = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        myDbHelper = new DataBaseHelper(this);
        time1 = (TextView) findViewById(R.id.b_time);
        time2 = (TextView) findViewById(R.id.b_time2);
        time3 = (TextView) findViewById(R.id.b_time3);
        levela = (TextView) findViewById(R.id.l_id_name);
        levelb = (TextView) findViewById(R.id.l_id_name2);
        levelc = (TextView) findViewById(R.id.l_id_name3);
        intro = (TextView) findViewById(R.id.introduction);

        lock1 = (ImageView) findViewById(R.id.l1_lockimg);
        lock2 = (ImageView) findViewById(R.id.l2_lockimg);
        lock3 = (ImageView) findViewById(R.id.l3_lockimg);
        l_word1 = (TextView) findViewById(R.id.l_word_number1);
        l_word2 = (TextView) findViewById(R.id.l_word_number2);
        l_word3 = (TextView) findViewById(R.id.l_word_number3);


        adds = (LinearLayout) findViewById(R.id.ads_lay);
        score1 = (TextView) findViewById(R.id.b_score);
        score2 = (TextView) findViewById(R.id.b_score2);
        score3 = (TextView) findViewById(R.id.b_score3);
        levelid = (TextView) findViewById(R.id.l_level_id);
        ttr_intro = (TextView) findViewById(R.id.ttr_intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        levels = (RelativeLayout) findViewById(R.id.levels);
        Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button2_animation);
        levels.startAnimation(levels1);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
//sounds for game
        play1 = MediaPlayer.create(this, R.raw.click);
        //
        //sounds for game


        String snd = spa.getString(Levels.this, "snd");
        if (snd.equals("off")) {
            play1.setVolume(0, 0);
        } else if (snd.equals("on")) {
            play1.setVolume(1, 1);
        }
        //


        Spannable wordThree = new SpannableString("1)இந்த விளையாட்டில், 9 எழுத்துக்கள் கொடுக்கப்பட்டுள்ளது.");

        wordThree.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")), 0, wordThree.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(wordThree);


        Spannable four = new SpannableString(" \n\n2)  9 எழுத்துக்களில், 7 வார்த்தைகள் முதல் 14 வார்த்தைகள் வரை கண்டுபிடிக்க வேண்டும். ");

        four.setSpan(new ForegroundColorSpan(Color.parseColor("#ff33ff")), 0, four.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(four);

        Spannable seven = new SpannableString( "\n\n3) தொடர்ந்து சரியான  10 விடைகளை கண்டுபிடித்தால், கூடுதல் விடைகளை நாணயங்கள் குறையாமல் அறிந்து கொள்ளலாம்.");

        seven.setSpan(new ForegroundColorSpan(Color.parseColor("#664d00")), 0, seven.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(seven);

        Spannable five = new SpannableString("\n\n4) அனைத்து விடைகளையும் கண்டுபிடித்தாலோ அல்லது குறைவான நேரத்தில் அனைத்து விடைகளையும் கண்டுபிடித்தலோ கூடுதல் நாணயங்கள் வழங்கப்படும்.");

        five.setSpan(new ForegroundColorSpan(Color.parseColor("#a32966")), 0, five.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(five);


        Spannable six = new SpannableString("\n\n5) கேள்விக்குறியை அழுத்தி விடையைக் காணலாம். அவ்வாறு பார்க்கும்பொழுது 50 நாணயங்கள் குறைக்கப்படும்.");

        six.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6633")), 0, six.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(six);


        db = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);


       /* Cursor c1 = db.rawQuery("select * from maintable where gameid='4'", null);
        c1.moveToFirst();

        Cursor c2 = db.rawQuery("select * from maintable where gameid='4' and isfinish='1'", null);
        c2.moveToFirst();
        int cs2 = c2.getCount() + 1;
        l_word1.setText(c2.getCount() + "/" + c1.getCount());*/


        levels.setEnabled(true);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.action_sole, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        action = (TextView) findViewById(R.id.actionword8);
        // action.setTypeface(tyr);
        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d = getResources().getDrawable(R.drawable.actionbar_back);
        getSupportActionBar().setBackgroundDrawable(d);

        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spa.putInt(Levels.this, "val", 1);
                finish();
               /* Intent i = new Intent(Levels.this, New_Main_Activity.class);
                startActivity(i);*/
            }
        });


        levela.setText("விளையாடு");
        ttr_intro.setText("விதிமுறைகள்:");
        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play1.start();
                finish();
                Intent i = new Intent(Levels.this, Word_Game_Hard.class);
                startActivity(i);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            spa.putInt(Levels.this, "val", 1);
            finish();
           /* Intent i = new Intent(Levels.this, New_Main_Activity.class);
            startActivity(i);*/

        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onResume() {
        super.onResume();

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (spa.getInt(Levels.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            native_banner_ad_container.setVisibility(View.GONE);
        }else {
            if (Utils.isNetworkAvailable(Levels.this)){
                native_banner_ad_container.setVisibility(View.GONE);
               // fb_native(Levels.this,native_banner_ad_container);

                /* if (spa.getInt(Levels.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Levels.this,native_banner_ad_container);
                }else {
                    fb_native(Levels.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
         /*   if (spa.getInt(Levels.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(Levels.this, adds);
            }else {
                if (Utils.isNetworkAvailable(Levels.this)) {
                    spa.putInt(Levels.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(Levels.this);
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

    }
}
