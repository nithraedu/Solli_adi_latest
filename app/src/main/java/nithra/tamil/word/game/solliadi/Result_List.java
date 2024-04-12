package nithra.tamil.word.game.solliadi;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Result_List extends AppCompatActivity {
    static final SharedPreference sps = new SharedPreference();
    TextView daily_result, weekly_result, monthly_result, share_result;
    LinearLayout adds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result__list);
        daily_result = (TextView) findViewById(R.id.daily_result);
        weekly_result = (TextView) findViewById(R.id.weekly_result);
        monthly_result = (TextView) findViewById(R.id.monthly_result);
        share_result = (TextView) findViewById(R.id.share_result);
        adds = (LinearLayout) findViewById(R.id.ads_lay);

        daily_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1", 1);
                startActivity(i);
            }
        });
        weekly_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1", 2);
                startActivity(i);
            }
        });
        monthly_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1", 3);
                startActivity(i);
            }
        });
        share_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Result_List.this, Results.class);
                i.putExtra("value1", 4);
                startActivity(i);
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            Intent i = new Intent(Result_List.this, Game_States.class);
            startActivity(i);

        }
        return super.onKeyDown(keyCode, event);
    }
}
