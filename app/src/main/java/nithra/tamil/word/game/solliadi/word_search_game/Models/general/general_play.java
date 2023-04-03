package nithra.tamil.word.game.solliadi.word_search_game.Models.general;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.game_sub_level_page;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;


public class general_play extends AppCompatActivity {

    static final SharedPreference sp = new SharedPreference();
    final my_dialog myDialog_class = new my_dialog();
    // Facebook variable starts
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";
    WordsearchGridFragment wordsearchGridFragment;
    SQLiteDatabase mydb;
    String level_category = "", level_id = "";
    boolean start_time = false;
    SharedPreference sps = new SharedPreference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


        sp.putInt(general_play.this, "animation_id", 0);

        if (sp.getInt(general_play.this, "purchase_ads") == 1) {
        } else {
            // advancads();
        }

        // uiHelper = new UiLifecycleHelper(general_play.this, callback);
    }
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
        cancel_exit.setOnClickListener(v -> {
            start_time = true;
            dialog.dismiss();
            WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
            WordsearchGridFragment.chronometer.start();
        });
        done_exit.setOnClickListener(v -> {
            start_time = true;

            dialog.dismiss();
            Intent intent = new Intent(general_play.this, game_sub_level_page.class);
            finish();
            startActivity(intent);
        });

        dialog.setOnDismissListener(dialog1 -> {
            if (!start_time) {
                WordsearchGridFragment.timeWhenStopped = Long.parseLong(sp.getString(general_play.this, "" + level_category + "_" + level_id));
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
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


        yes.setOnClickListener(v -> {
            start_time = true;


            openDialog_p.dismiss();
            Intent intent = new Intent(general_play.this, game_sub_level_page.class);
            finish();
            startActivity(intent);

        });
        no.setOnClickListener(v -> {

            start_time = true;
            WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
            WordsearchGridFragment.chronometer.start();

            openDialog_p.dismiss();
        });
        openDialog_p.setOnDismissListener(dialog -> {
            if (!start_time) {
                WordsearchGridFragment.timeWhenStopped = Long.parseLong(sp.getString(general_play.this, "" + level_category + "_" + level_id));
                WordsearchGridFragment.chronometer.setBase(SystemClock.elapsedRealtime() + WordsearchGridFragment.timeWhenStopped);
                WordsearchGridFragment.chronometer.start();
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
