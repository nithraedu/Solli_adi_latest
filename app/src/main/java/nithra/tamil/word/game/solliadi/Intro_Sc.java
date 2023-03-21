package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Intro_Sc extends AppCompatActivity {
    static SharedPreference sps = new SharedPreference();
    DrawerLayout drawer;
    RelativeLayout toolbar;
    TextView drower;
    RelativeLayout play_game;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__sc);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
