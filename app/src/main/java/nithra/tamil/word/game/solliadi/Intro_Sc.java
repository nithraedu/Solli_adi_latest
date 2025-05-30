package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowInsets;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Intro_Sc extends AppCompatActivity {
    static final SharedPreference sps = new SharedPreference();
    DrawerLayout drawer;
    RelativeLayout toolbar;
    TextView drower;
    RelativeLayout play_game;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__sc);

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

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drower = (TextView) findViewById(R.id.drower);

        play_game = (RelativeLayout) findViewById(R.id.play_game);

        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        play_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sps.putInt(Intro_Sc.this, "native_banner_ads", 0);
                finish();
                Intent i = new Intent(Intro_Sc.this, New_Main_Activity.class);
                startActivity(i);
            }
        });
    }

}
