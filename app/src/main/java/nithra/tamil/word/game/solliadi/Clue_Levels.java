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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.ads.NativeAdLayout;


public class Clue_Levels extends AppCompatActivity {
    public static SharedPreferences mPreferences;
    TextView time1, time2, time3, score1, score2, score3, levelid, levelname;
    Typeface typ, tyr;
    DataBaseHelper myDbHelper;
    TextView levela, levelb, levelc;
    SQLiteDatabase exdb;
    TextView action;
    TextView intro;
    TextView ttr_intro;
    MediaPlayer play1;
    SharedPreference spa = new SharedPreference();
    ImageView lock1, lock2, lock3;
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
        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        lock1 = (ImageView) findViewById(R.id.l1_lockimg);
        lock2 = (ImageView) findViewById(R.id.l2_lockimg);
        lock3 = (ImageView) findViewById(R.id.l3_lockimg);
        intro = (TextView) findViewById(R.id.introduction);


        l_word1 = (TextView) findViewById(R.id.l_word_number1);
        l_word2 = (TextView) findViewById(R.id.l_word_number2);
        l_word3 = (TextView) findViewById(R.id.l_word_number3);


        adds = (LinearLayout) findViewById(R.id.ads_lay);
        score1 = (TextView) findViewById(R.id.b_score);
        score2 = (TextView) findViewById(R.id.b_score2);
        score3 = (TextView) findViewById(R.id.b_score3);
        levelid = (TextView) findViewById(R.id.l_level_id);
        levelname = (TextView) findViewById(R.id.l_id_name);
        ttr_intro = (TextView) findViewById(R.id.ttr_intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        RelativeLayout levels = (RelativeLayout) findViewById(R.id.levels);
        Animation levels1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button2_animation);
        levels.startAnimation(levels1);
        RelativeLayout levels2 = (RelativeLayout) findViewById(R.id.levels2);
        Animation levels12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button2_animation_delay1);
        levels2.startAnimation(levels12);
        RelativeLayout levels3 = (RelativeLayout) findViewById(R.id.levels3);
        Animation levels13 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button2_animation_delay2);
        levels3.startAnimation(levels13);


//sounds for game
        play1 = MediaPlayer.create(this, R.raw.click);
        //
        //sounds for game


        String snd = spa.getString(Clue_Levels.this, "snd");
        if (snd.equals("off")) {
            play1.setVolume(0, 0);
        } else if (snd.equals("on")) {
            play1.setVolume(1, 1);
        }
        //


        Spannable wordTwo = new SpannableString("1)  இங்கு 3 குறிப்புகள் கொடுக்கப்படும் அதன் மூலம் விடையை கண்டுபிடிக்க வேண்டும்.");

        wordTwo.setSpan(new ForegroundColorSpan(Color.BLUE), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(wordTwo);

        Spannable wordThree = new SpannableString("\n\n2) முதலில் 1 குறிப்பு மட்டும் தரப்படும். 30 வினாடிகளுக்குப் பிறகு அடுத்த குறிப்பு தரப்படும். அடுத்த 30 வினாடிகளில் இறுதி குறிப்பு தரப்படும்.");

        wordThree.setSpan(new ForegroundColorSpan(Color.parseColor("#ff33ff")), 0, wordThree.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(wordThree);


        Spannable four = new SpannableString("\n\n3)  சரியான விடையை முதல் குறிப்பில்  கண்டுபிடித்துவிட்டால் 20 நாணயங்கள் வழங்கப்படும்.  இரண்டு குறிப்புகளைப் பயன்படுத்தி கண்டுபிடித்தால் 15 நாணயங்கள் வழங்கப்படும். மூன்றாவது குறிப்பில் கண்டுபிடிக்கப்படும் வார்த்தைகளுக்கு 10 நாணயங்கள் மட்டுமே வழங்கப்படும்.");

        four.setSpan(new ForegroundColorSpan(Color.parseColor("#800080")), 0, four.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(four);


        Spannable six = new SpannableString("\n\n4) கேள்விக்குறியை அழுத்தி விடையைக் காணலாம். அவ்வாறு பார்க்கும்பொழுது 100 நாணயங்கள் குறைக்கப்படும்.");

        six.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6633")), 0, six.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(six);


//
   /*     exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        Cursor c1 = exdb.rawQuery("select * from maintable where gameid= 2", null);
        c1.moveToFirst();

        Cursor c2 = exdb.rawQuery("select * from maintable where gameid= 2 and isfinish='1'", null);
        c2.moveToFirst();
        int cs2 = c2.getCount()+1;
        l_word1.setText(c2.getCount() + "/" + c1.getCount());
*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.action_sole, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        action = (TextView) findViewById(R.id.actionword8);
        action.setText("குறிப்புகள் மூலம் கண்டுபிடி");
        //action.setTypeface(tyr);
        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d = getResources().getDrawable(R.drawable.actionbar_back);
        getSupportActionBar().setBackgroundDrawable(d);


        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spa.putInt(Clue_Levels.this, "val", 1);
                finish();
              /*  Intent i = new Intent(Clue_Levels.this, New_Main_Activity.class);
                startActivity(i);*/
            }
        });
//
        levela = (TextView) findViewById(R.id.l_id_name);
        levelb = (TextView) findViewById(R.id.l_id_name2);
        levelc = (TextView) findViewById(R.id.l_id_name3);

        levela.setText("விளையாடு");

        ttr_intro.setText("விதிமுறைகள்:");
        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play1.start();
                finish();
                Intent i = new Intent(Clue_Levels.this, Clue_Game_Hard.class);
                startActivity(i);

            }
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            spa.putInt(Clue_Levels.this, "val", 1);
            finish();
           /* Intent i = new Intent(Clue_Levels.this, New_Main_Activity.class);
            startActivity(i);*/

        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onResume() {
        super.onResume();
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (spa.getInt(Clue_Levels.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(Clue_Levels.this)) {
                native_banner_ad_container.setVisibility(View.GONE);
                // native_banner_ad_container.setVisibility(View.GONE);
                //  fb_native(Clue_Levels.this,native_banner_ad_container);

                /*   if (spa.getInt(Clue_Levels.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Clue_Levels.this,native_banner_ad_container);
                }else {
                    fb_native(Clue_Levels.this,native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

       /*     if (spa.getInt(Clue_Levels.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(Clue_Levels.this, adds);
            }else {
                if (Utils.isNetworkAvailable(Clue_Levels.this)) {
                    spa.putInt(Clue_Levels.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(Clue_Levels.this);
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
