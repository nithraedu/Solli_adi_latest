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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class Ote_to_tamil_Levels extends AppCompatActivity {
    public static SharedPreferences mPreferences;
    final SharedPreference spa = new SharedPreference();
    TextView time1, time2, time3, score1, score2, score3, levelid, levelname;
    Typeface typ, tyr;
    DataBaseHelper myDbHelper;
    TextView levela, levelb, levelc;
    SQLiteDatabase exdb;
    TextView action;
    TextView intro;
    TextView ttr_intro;
    MediaPlayer play1;
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


        String snd = spa.getString(Ote_to_tamil_Levels.this, "snd");
        if (snd.equals("off")) {
            play1.setVolume(0, 0);
        } else if (snd.equals("on")) {
            play1.setVolume(1, 1);
        }
        //


        Spannable wordTwo = new SpannableString("1) கொடுக்கப்பட்ட பிறமொழி சொற்களுக்கு இணையான தமிழ்  சொல்லை கண்டுபிடிக்க வேண்டும்.");

        wordTwo.setSpan(new ForegroundColorSpan(Color.BLUE), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(wordTwo);


        Spannable four = new SpannableString("\n\n2)  சரியான விடையை கண்டுபிடித்துவிட்டால் 50 நாணயங்கள் வழங்கப்படும். ");

        four.setSpan(new ForegroundColorSpan(Color.parseColor("#800080")), 0, four.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(four);


        Spannable six = new SpannableString("\n\n3) கேள்விக்குறியை அழுத்தி விடையைக் காணலாம். அவ்வாறு பார்க்கும்பொழுது 100 நாணயங்கள் குறைக்கப்படும்.");

        six.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6633")), 0, six.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        intro.append(six);


//

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.action_sole, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        tyr = Typeface.createFromAsset(getAssets(), "TAMHN0BT.TTF");
        action = (TextView) findViewById(R.id.actionword8);
        action.setText("பிறமொழி சொற்கள்");
        //action.setTypeface(tyr);
        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d = getResources().getDrawable(R.drawable.actionbar_back);
        getSupportActionBar().setBackgroundDrawable(d);


        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spa.putInt(Ote_to_tamil_Levels.this, "val", 1);
                finish();
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
                Intent i = new Intent(Ote_to_tamil_Levels.this, Ote_to_Tamil.class);
                startActivity(i);

            }
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            spa.putInt(Ote_to_tamil_Levels.this, "val", 1);
            finish();

        }
        return super.onKeyDown(keyCode, event);
    }

}
