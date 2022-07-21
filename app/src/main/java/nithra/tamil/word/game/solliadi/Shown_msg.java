package nithra.tamil.word.game.solliadi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;






public class Shown_msg extends Activity {


    SharedPreference sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

       setContentView(R.layout.shown_msg);
        setFinishOnTouchOutside(false);

        sharedPreference = new SharedPreference();



        if (sharedPreference.getInt(Shown_msg.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        }else {
           /* AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);*/
        }


        Bundle extras;
        extras = getIntent().getExtras();
        String message = extras.getString("message");
        String title = extras.getString("title");



        AppCompatButton ok = (AppCompatButton) findViewById(R.id.button1);
        ok.setText("Ok");
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView1.setText("" + title);
        textView2.setText("" + message);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(sharedPreference.getInt(Shown_msg.this,"Noti_add")==1) {

                        sharedPreference.putInt(getApplicationContext(), "Noti_add", 0);
                        Intent i = new Intent(Shown_msg.this,MainActivity.class);
                        finish();
                        startActivity(i);

                }
                else{
                    Intent i = new Intent(Shown_msg.this,MainActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(sharedPreference.getInt(Shown_msg.this,"Noti_add")==1) {

                sharedPreference.putInt(getApplicationContext(), "Noti_add", 0);
                Intent i = new Intent(Shown_msg.this,Shown_msg.class);
                finish();
                startActivity(i);

        }
        else{
            Intent i = new Intent(Shown_msg.this,Shown_msg.class);
            finish();
            startActivity(i);
        }

    }



}