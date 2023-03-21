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

import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;


public class match_the_words_levels extends AppCompatActivity {
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


        String snd = spa.getString(match_the_words_levels.this, "snd");
        if (snd.equals("off")) {
            play1.setVolume(0, 0);
        } else if (snd.equals("on")) {
            play1.setVolume(1, 1);
        }
        //


        //  Spannable wordTwo = new SpannableString( "1)  மேலே கொடுக்கப்பட்ட வார்த்தைக்கு, கீழே கொடுக்கப்பட்டுள்ள 16 வார்த்தைகளில் இணையான வார்த்தைகளை கண்டுபிடிக்க வேண்டும்.\n எ.கா: கேள்வி : நிலா \n விடை : கோள், திங்கள், மதி, நிலவு, சந்திரன் ");
        Spannable wordTwo = new SpannableString("1)  கொடுத்திருக்கும் சொல்லுக்கு பொருத்தமான சொல்லை தேர்ந்தெடுத்து பொருத்த வேண்டும்.");

        wordTwo.setSpan(new ForegroundColorSpan(Color.BLUE), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(wordTwo);

        // Spannable wordThree = new SpannableString( "\n\n2) கொடுக்கப்பட்டுள்ள 16 வார்த்தைகள் :மதி, பூமி, வியாழன், கோள், சனி, விண், வானம், திங்கள், வெளிச்சம், நிலவு, மாதம், ஆதவன், கனலி, கதிரவன், சூரியன், சந்திரன்.");
        Spannable wordThree = new SpannableString("\n\n2) சரியாக பொருத்தும் ஒவ்வொரு சொல்லுக்கும் 20 நாணயங்கள் வழங்கப்படும்.");

        wordThree.setSpan(new ForegroundColorSpan(Color.parseColor("#ff33ff")), 0, wordThree.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(wordThree);


        Spannable four = new SpannableString("\n\n3)  தவறாக பொருத்தும் ஒவ்வொரு சொல்லுக்கும் 20 நாணயங்கள் குறைக்கப்படும்.");

        four.setSpan(new ForegroundColorSpan(Color.parseColor("#800080")), 0, four.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(four);


        // Spannable six = new SpannableString( "\n\n4) தவறான விடையை தேர்வு செய்தாலோ அல்லது ? குறியில் கொடுக்கப்பட்டுள்ள குழுவின் மூலம் சரியான விடையை தேர்வு செய்யும் பட்சத்தில், உங்களின் மொத்த மதிப்பெண்களில் இருந்து 30 மதிப்பெண்கள் இழக்க நேரிடும்.");
        Spannable six = new SpannableString("\n\n4)விடை தெரியவில்லையெனில் கேள்விக்குறி பொத்தானை அழுத்தி விடையைக் காணலாம். அவ்வாறு பார்க்கும்பொழுது 50 நாணயங்கள் குறைக்கப்படும்.");

        six.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6633")), 0, six.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(six);

       /* Spannable seven = new SpannableString( "\n\n5)இந்த விளையாட்டில் கொடுக்கப்பட்டுள்ள அனைத்து வார்த்தைகளையும் 30 விநாடிகள் கண்டு பிடித்தால் தங்களுக்கு கூடுதலாக 30 நாணயங்கள் வழங்கப்படும்.");

        seven.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6633")), 0, seven.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(seven);

        Spannable sev = new SpannableString( "\n\n6)(?) குறியை பயன்படுத்தாமல் அனைத்து விடைகளையும் கண்டுபிடிக்கும் பட்சத்தில் கூடுதலாக 30 சிறப்பு நாணயங்கள் வழங்கப்படும். ");

        sev.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6633")), 0, sev.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(sev);*/
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
        View view = getLayoutInflater().inflate(R.layout.action_sole_m, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        action = (TextView) findViewById(R.id.actionword8);
        action.setText("பொருத்துக");
        //action.setTypeface(tyr);
        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d = getResources().getDrawable(R.drawable.actionbar_back);
        getSupportActionBar().setBackgroundDrawable(d);


        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spa.putInt(match_the_words_levels.this, "val", 1);
                finish();
               /* Intent i = new Intent(Match_words_levels.this, New_Main_Activity.class);
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
                Intent i = new Intent(match_the_words_levels.this, Match_tha_fallows_game.class);
                startActivity(i);

            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            spa.putInt(match_the_words_levels.this, "val", 1);
            finish();
            /*Intent i = new Intent(Match_words_levels.this, New_Main_Activity.class);
            startActivity(i);*/

        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onResume() {
        super.onResume();

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (spa.getInt(match_the_words_levels.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            native_banner_ad_container.setVisibility(View.GONE);
        } else {
            if (Utils.isNetworkAvailable(match_the_words_levels.this)) {
                native_banner_ad_container.setVisibility(View.GONE);
                // fb_native(match_the_words_levels.this,native_banner_ad_container);

                /*   if (spa.getInt(match_the_words_levels.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(match_the_words_levels.this,native_banner_ad_container);
                }else {
                    fb_native(match_the_words_levels.this,native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
        /*    if (spa.getInt(match_the_words_levels.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(match_the_words_levels.this, adds);
            }else {
                if (Utils.isNetworkAvailable(match_the_words_levels.this)) {
                    spa.putInt(match_the_words_levels.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(match_the_words_levels.this);
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
