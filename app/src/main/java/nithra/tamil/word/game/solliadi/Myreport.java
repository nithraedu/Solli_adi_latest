package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Myreport extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreport);

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
