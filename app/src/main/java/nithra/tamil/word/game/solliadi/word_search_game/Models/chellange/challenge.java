package nithra.tamil.word.game.solliadi.word_search_game.Models.chellange;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;


public class challenge extends AppCompatActivity {


    public static FrameLayout add3;
    static SharedPreference sp = new SharedPreference();
    // Facebook variable starts
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    SQLiteDatabase mydb;
    challenge_WS_GridFragment challenge_ws_gridFragment;
    my_dialog myDialog_class = new my_dialog();
    boolean start_time = false;
    FrameLayout fl_adplaceholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // no_tool();
       /* if (!sp.getString(getApplicationContext(), "saval_showcase").equals("")) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }*/
        setContentView(R.layout.activity_challenge);

        setContentView(R.layout.activity_general_play);

        mydb = openOrCreateDatabase("WS_tamil.db", MODE_PRIVATE, null);

        sp.putString(challenge.this, "show_challenge", "done");

        sp.putInt(challenge.this, "GRID", 10);

        challenge_ws_gridFragment = new challenge_WS_GridFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, challenge_ws_gridFragment);
        fragmentTransaction.commit();


        if (sp.getInt(challenge.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            if (my_dialog.isNetworkAvailable(challenge.this)) {
                //fb_addload_score_screen(challenge.this);

                /*AdRequest notadRequest = new AdRequest.Builder().build();
                interstitialAd.loadAd(notadRequest);*/

            }
        }

        fl_adplaceholder = (FrameLayout) findViewById(R.id.fl_adplaceholder);


        if (sp.getInt(challenge.this, "purchase_ads") == 1) {
        } else {
            // advancads();
        }

        //uiHelper = new UiLifecycleHelper(challenge.this, callback);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exit_fun_new();


        sp.putString(challenge.this, "ws_challenge_intro", "no");
        sp.putString(challenge.this, "ws_challenge_showcase_intro", "yes");

    }

    private void exit_fun_new() {
        if (!sp.getString(challenge.this, "ws_challenge_intro").equals("")) {
            start_time = false;
            challenge_ws_gridFragment.countDownTimer.cancel();
        }


        myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");


        final Dialog openDialog_p = new Dialog(challenge.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        final TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
        TextView no = (TextView) openDialog_p.findViewById(R.id.no);
        final CheckBox checkbox_back = (CheckBox) openDialog_p.findViewById(R.id.checkbox_back);
        LinearLayout ads_lay = (LinearLayout) openDialog_p.findViewById(R.id.fl_adplaceholder);

        TextView noask = (TextView) openDialog_p.findViewById(R.id.noask);
        //  noask.setVisibility(View.INVISIBLE);

        ads_lay.setVisibility(View.GONE);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time = true;
                openDialog_p.dismiss();
                challenge_ws_gridFragment.winning_report(challenge.this, "exit");
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time = true;
                openDialog_p.dismiss();
                challenge_ws_gridFragment.startTimer();
            }
        });
        openDialog_p.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!start_time) {
                    challenge_ws_gridFragment.startTimer();
                }
            }
        });

        openDialog_p.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);


    }

}
