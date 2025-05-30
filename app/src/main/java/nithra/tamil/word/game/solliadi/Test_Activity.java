package nithra.tamil.word.game.solliadi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowInsets;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class Test_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_);

        final LinearLayout ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
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

    }
}
