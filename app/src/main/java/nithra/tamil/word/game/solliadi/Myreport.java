package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Myreport extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreport);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new Fragment_adapter(Myreport.this, getSupportFragmentManager()));


    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            Intent i = new Intent(Myreport.this, Game_States.class);
            startActivity(i);

        }
        return super.onKeyDown(keyCode, event);
    }
}
