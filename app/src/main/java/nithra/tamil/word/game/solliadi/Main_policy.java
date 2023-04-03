package nithra.tamil.word.game.solliadi;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;


public class Main_policy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        setContentView(R.layout.activity_main_policy);

        WebView webb = (WebView) findViewById(R.id.webb);
        webb.clearCache(true);
        webb.clearHistory();
        webb.getSettings().setJavaScriptEnabled(true);
        webb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webb.loadUrl("https://www.nithra.mobi/privacy.php");


        webb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        webb.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                runOnUiThread(new Runnable() {

                    public void run() {
                        try {
                            Utils.mProgress(Main_policy.this, "ஏற்றுகிறது. காத்திருக்கவும்  ...", false).show();
                        } catch (Exception e) {

                        }
                    }
                });
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    Utils.mProgress.dismiss();
                } catch (Exception e) {

                }
                super.onPageFinished(view, url);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
