package nithra.tamil.word.game.solliadi.word_search_game.Models.general;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.game_sub_level_page;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;


public class general_play extends AppCompatActivity {

    /////////native advance////////////
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-4267540560263635/9323490091";
    private static final String ADMOB_APP_ID = "ca-app-pub-4267540560263635~3166935503";
    /////////native advance////////////
    public static FrameLayout add3;
    static SharedPreference sp = new SharedPreference();
    // Facebook variable starts
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    WordsearchGridFragment wordsearchGridFragment;
    SQLiteDatabase mydb;
    String level_category = "", level_id = "";
    my_dialog myDialog_class = new my_dialog();
    boolean start_time = false;
    SharedPreference sps = new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     /*   if (!sp.getString(getApplicationContext(), "genral_showcase").equals("")) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }*/

        setContentView(R.layout.activity_general_play);

        mydb = general_play.this.openOrCreateDatabase("WS_tamil.db", MODE_PRIVATE, null);

        level_category = sp.getString(getApplicationContext(), "level_category");
        level_id = sp.getString(getApplicationContext(), "sub_level_category");

        System.out.println("-----gg level_category : " + sp.getString(getApplicationContext(), "level_category"));
        System.out.println("-----gg sub_level_category : " + sp.getString(getApplicationContext(), "sub_level_category"));

        wordsearchGridFragment = new WordsearchGridFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, wordsearchGridFragment);
        fragmentTransaction.commit();


        if (sp.getInt(general_play.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
        } else {
            if (my_dialog.isNetworkAvailable(general_play.this)) {
                //fb_addload_score_screen(general_play.this);

                /**/

            }
        }


        sp.putInt(general_play.this, "animation_id", 0);

        if (sp.getInt(general_play.this, "purchase_ads") == 1) {
        } else {
            // advancads();
        }

        // uiHelper = new UiLifecycleHelper(general_play.this, callback);
    }
  /*  private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            // onSessionStateChange(session, state, exception);
        }
    };
    private FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
        @Override
        public void onError(FacebookDialog.PendingCall pendingCall,
                            Exception error, Bundle data) {
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
        }

        @Override
        public void onComplete(FacebookDialog.PendingCall pendingCall,
                               Bundle data) {
            Log.d("HelloFacebook", "Success!");
        }
    };*/
    // facebook variable ends

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        sp.putString(general_play.this, "ws_general_intro", "no");
        sp.putString(general_play.this, "ws_general_showcase_intro", "yes");
        exit_function_new();

    }

    public void exit_function() {
        start_time = false;

        myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
        WordsearchGridFragment.timeWhenStopped = WordsearchGridFragment.chronometer.getBase() - SystemClock.elapsedRealtime();
        WordsearchGridFragment.chronometer.stop();
        sp.putString(getApplicationContext(), "" + level_category + "_" + level_id, "" + WordsearchGridFragment.timeWhenStopped);

        final Dialog dialog = new Dialog(general_play.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_exit);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
        TextView cancel_exit = (TextView) dialog.findViewById(R.id.cancel_exit);
        TextView done_exit = (TextView) dialog.findViewById(R.id.done_exit);
        dialog.show();
        cancel_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time = true;
                dialog.dismiss();
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
            }
        });
        done_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time = true;

                dialog.dismiss();
                Intent intent = new Intent(general_play.this, game_sub_level_page.class);
                finish();
                startActivity(intent);
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!start_time) {
                    WordsearchGridFragment.timeWhenStopped = Long.parseLong(sp.getString(general_play.this, "" + level_category + "_" + level_id));
                    WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
                    WordsearchGridFragment.chronometer.start();
                }

            }
        });

    }

    public void exit_function_new() {

        start_time = false;

        myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
        WordsearchGridFragment.timeWhenStopped = WordsearchGridFragment.chronometer.getBase() - SystemClock.elapsedRealtime();
        WordsearchGridFragment.chronometer.stop();


        sp.putString(getApplicationContext(), "" + level_category + "_" + level_id, "" + WordsearchGridFragment.timeWhenStopped);


        final Dialog openDialog_p = new Dialog(general_play.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        final TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
        TextView no = (TextView) openDialog_p.findViewById(R.id.no);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time = true;


                openDialog_p.dismiss();
                Intent intent = new Intent(general_play.this, game_sub_level_page.class);
                finish();
                startActivity(intent);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start_time = true;
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
                WordsearchGridFragment.chronometer.start();

                openDialog_p.dismiss();
            }
        });
        openDialog_p.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!start_time) {
                    WordsearchGridFragment.timeWhenStopped = Long.parseLong(sp.getString(general_play.this, "" + level_category + "_" + level_id));
                    WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
                    WordsearchGridFragment.chronometer.start();
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


    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }
}
