package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;

public class New_intro_sc extends AppCompatActivity {
    static final SharedPreference sps = new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_intro_sc);

        View decoreView = getWindow().getDecorView();
        decoreView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsets onApplyWindowInsets(@NonNull View v, @NonNull WindowInsets
                    insets) {
                int left = insets.getSystemWindowInsetLeft();
                int top = insets.getSystemWindowInsetTop();
                int right = insets.getSystemWindowInsetRight();
                int bottom = insets.getSystemWindowInsetBottom();
                v.setPadding(left,top,right,bottom);
                v.setBackgroundColor(Color.GRAY); // Android built-in gray
                return insets.consumeSystemWindowInsets();

            }
        });


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
