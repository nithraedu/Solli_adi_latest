package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;

public class New_intro_sc extends AppCompatActivity {
    static final SharedPreference sps = new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_intro_sc);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sps.putInt(New_intro_sc.this, "addloded2", 0);
        sps.putInt(New_intro_sc.this, "addloded", 0);
        sps.putString(New_intro_sc.this, "og_game_on_oi", "on");
        sps.putString(New_intro_sc.this, "ads_dialog_oi", "on");
        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sps.putString(New_intro_sc.this, "game_area", "on");
                finish();
                Intent i = new Intent(New_intro_sc.this, New_Main_Activity.class);
                startActivity(i);
            }
        }, 2500);
    }
}
