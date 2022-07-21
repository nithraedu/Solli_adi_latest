package nithra.tamil.word.game.solliadi;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

public class Results extends AppCompatActivity {
WebView loadurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        getSupportActionBar().setDisplayShowHomeEnabled(false);
        View view=getLayoutInflater().inflate(R.layout.action_sole3, null);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        TextView action = (TextView) findViewById(R.id.actionword8);
        action.setText("முடிவுகள்");

        TextView action_back = (TextView) findViewById(R.id.action_back);
        Drawable d=getResources().getDrawable(R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(d);
        loadurl=(WebView) findViewById(R.id.lodadurl);
        loadurl.clearCache(true);
        loadurl.clearHistory();
        loadurl.getSettings().setJavaScriptEnabled(true);
        loadurl.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        Bundle b=getIntent().getExtras();
        if (b!=null)
        {
            System.out.println("=========bundle=======");
            int r=b.getInt("value1");
            if (r==1)
            {

                String summary = "<html><body><img src = https://s3.ap-south-1.amazonaws.com/solliadi-prize/daily.png  height=100% width=100%></body></html>";

                loadurl.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);

               /* String data="<html><body><a href=http://https://s3.ap-south-1.amazonaws.com/solliadi-prize/daily.png</a></body></html>";
                loadurl.loadData(data, "text/html", "UTF-8");
                loadurl.loadUrl("https://s3.ap-south-1.amazonaws.com/solliadi-prize/daily.png");*/
               /* System.out.println("r1");
                Picasso.with(Results.this)
                        .load("https://s3.ap-south-1.amazonaws.com/solliadi-prize/daily.png")
                        .error(R.drawable.logo2)
                        .placeholder(R.drawable.loading)
                        .into(result);
*/

            }else if (r==2){
                System.out.println("r2");
                String summary = "<html><body><img src = https://s3.ap-south-1.amazonaws.com/solliadi-prize/weekly.png  height=100% width=100%></body></html>";

                loadurl.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);
              //  loadurl.loadUrl("https://s3.ap-south-1.amazonaws.com/solliadi-prize/weekly.png");
               /* Picasso.with(Results.this)
                        .load("https://s3.ap-south-1.amazonaws.com/solliadi-prize/weekly.png")
                        .error(R.drawable.logo2)
                        .placeholder(R.drawable.loading)
                        .into(result);*/

            }else if (r==3){
                System.out.println("r3");
                String summary = "<html><body><img src = https://s3.ap-south-1.amazonaws.com/solliadi-prize/monthly.png  height=100% width=100%></body></html>";

                loadurl.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);
               // loadurl.loadUrl("https://s3.ap-south-1.amazonaws.com/solliadi-prize/monthly.png");
                /*Picasso.with(Results.this)
                        .load("https://s3.ap-south-1.amazonaws.com/solliadi-prize/monthly.png")
                        .error(R.drawable.logo2)
                        .placeholder(R.drawable.loading)
                        .into(result);*/
            }else if (r==4){
                System.out.println("r4");
                String summary = "<html><body><img src = https://s3.ap-south-1.amazonaws.com/solliadi-prize/share.png  height=100% width=100%></body></html>";
                loadurl.loadDataWithBaseURL("", summary, "text/html", "utf-8", null);
               // loadurl.loadUrl("https://s3.ap-south-1.amazonaws.com/solliadi-prize/share.png");
               /* Picasso.with(Results.this)
                        .load("https://s3.ap-south-1.amazonaws.com/solliadi-prize/share.png")
                        .error(R.drawable.logo2)
                        .placeholder(R.drawable.loading)
                        .into(result);*/
            }
        }
        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Results.this, Result_List.class);
                startActivity(intent);


            }
        });




    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if(keyCode==KeyEvent.KEYCODE_BACK) {

                finish();
                Intent i = new Intent(Results.this, Result_List.class);
                startActivity(i);

        }
        return super.onKeyDown(keyCode, event);
    }
}
