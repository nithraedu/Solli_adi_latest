package nithra.tamil.word.game.solliadi.word_search_game.Models;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.word_search_game.Models.chellange.challenge;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.game_level_page;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;

public class Word_search_main extends AppCompatActivity {
    static String dialog_show_now = "";
    final SharedPreference sp = new SharedPreference();
    final my_dialog myDialog_class = new my_dialog();
    TextView general_play, category_play, challenge_play;
    LinearLayout ads_lay;
    TextView back, intro;
    ImageView gif_load;
    TextView intro_gif;
    Dialog dialog;

    int ids = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search_main);


        general_play = findViewById(R.id.general_play);
        category_play = findViewById(R.id.category_play);
        challenge_play = findViewById(R.id.challenge_play);
        ads_lay = findViewById(R.id.ads_lay);

        back = findViewById(R.id.back);
        intro = findViewById(R.id.intro);
        intro_gif = findViewById(R.id.intro_gif);
        System.out.println("##############dialog_show_now" + dialog_show_now);
        if (dialog_show_now.equals("dialog_show_now")) {
            dialog_show_now = "";
            System.out.println("-----------------------challenge_games() equals if ");
            challenge_games();
        }

        intro_gif.setOnClickListener(v -> startdialog());
        back.setOnClickListener(v -> finish());
        intro.setOnClickListener(v -> {
            final Dialog openDialog = new Dialog(Word_search_main.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.introsdialog_web);
            WebView intros = openDialog.findViewById(R.id.web_introscreen);
            TextView close = openDialog.findViewById(R.id.close);
            TextView done_exit = openDialog.findViewById(R.id.done_exit);
            close.setOnClickListener(v13 -> openDialog.dismiss());
            done_exit.setOnClickListener(v12 -> openDialog.dismiss());
            intros.loadUrl("file:///android_asset/ws_intro.html");
            intros.setOnLongClickListener(v1 -> true);
            openDialog.show();
        });
        general_play.setOnClickListener(v -> {
            myDialog_class.media_player(Word_search_main.this, R.raw.click, "normal");
            Intent intent = new Intent(Word_search_main.this, game_level_page.class);
            sp.putString(Word_search_main.this, "game_type", "பொதுப்பிரிவு");
            sp.putString(Word_search_main.this, "table_name", "general");
            finish();
            startActivity(intent);
        });
        category_play.setOnClickListener(v -> {
            myDialog_class.media_player(Word_search_main.this, R.raw.click, "normal");
            Intent intent = new Intent(Word_search_main.this, game_level_page.class);
            sp.putString(Word_search_main.this, "game_type", "வகைகள்");
            sp.putString(Word_search_main.this, "table_name", "category");
            finish();
            startActivity(intent);
        });
        challenge_play.setOnClickListener(v -> {

            myDialog_class.media_player(Word_search_main.this, R.raw.click, "normal");
            challenge_games();


        });
        if (sp.getString(Word_search_main.this, "startdialog").equals("")) {
            startdialog();
        }

        LinearLayout adds = findViewById(R.id.ads_lay);
        if (sp.getInt(Word_search_main.this, "purchase_ads") == 0) {
            if (Utils.isNetworkAvailable(Word_search_main.this)) {

            } else {
                adds.setVisibility(View.GONE);
            }
        } else {
            adds.setVisibility(View.GONE);
        }

    }


    public void challenge_games() {

        System.out.println("-----------------------challenge_games() ");

        // Dailytest_ok = "";

        sp.putString(Word_search_main.this, "show_challenge", "");

        dialog = new Dialog(Word_search_main.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_challenge_games);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;


        TextView normal_saval_game = dialog.findViewById(R.id.normal_saval_game);
        TextView oposite_saval_game = dialog.findViewById(R.id.oposite_saval_game);
        TextView question_saval_game = dialog.findViewById(R.id.question_saval_game);
        TextView mw_saval_game = dialog.findViewById(R.id.mw_saval_game);
        ImageView saval_back = dialog.findViewById(R.id.saval_back);

        final LinearLayout normal_baner_cha = dialog.findViewById(R.id.normal_baner_cha);
        final LinearLayout saval_icon_ad = dialog.findViewById(R.id.saval_icon_ad);
        final ImageView saval_icon_ad_img = dialog.findViewById(R.id.saval_icon_ad_img);
        final FrameLayout Baner_frame_challenge = dialog.findViewById(R.id.adplaceholder_challenge);

        System.out.println("-----------------------challenge_games() purchase_ads : " + sp.getInt(Word_search_main.this, "purchase_ads"));
        if (sp.getInt(Word_search_main.this, "purchase_ads") == 1) {
            normal_baner_cha.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            System.out.println("-----------------------challenge_games() addlodedd : " + sp.getInt(Word_search_main.this, "addlodedd"));

        }

        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(() -> {

            //   myDialog_class.WST_Native_BannerAd(New_Main_Activity.this, Baner_frame_challenge);
        }, 200);


        dialog.setOnDismissListener(dialog -> {

            //   myDialog_class.WST_Native_BannerAd(New_Main_Activity.this, Baner_frame);
        });


        saval_icon_ad.setOnClickListener(view -> {
            // myDialog_class.WST_Native_IconAd_show();
        });

        saval_back.setOnClickListener(v -> {
            myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
            dialog.dismiss();
        });


        sp.putString(Word_search_main.this, "option_show", "");

        normal_saval_game.setOnClickListener(v -> {
            dialog_show_now = "dialog_show_now";
            myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
            Intent intent = new Intent(Word_search_main.this, challenge.class);
            finish();
            sp.putString(Word_search_main.this, "saval_type", "வார்த்தை தேடல்");
            sp.putString(Word_search_main.this, "game_type", "challenge_word");
            sp.putString(Word_search_main.this, "table_name", "challenge");
            startActivity(intent);

        });
        oposite_saval_game.setOnClickListener(v -> {
            dialog_show_now = "dialog_show_now";
            myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");

            Intent intent = new Intent(Word_search_main.this, challenge.class);
            finish();
            sp.putString(Word_search_main.this, "option_show", "show");
            sp.putString(Word_search_main.this, "game_type", "oposit_word");
            sp.putString(Word_search_main.this, "table_name", "yethirsoll");
            sp.putString(Word_search_main.this, "saval_type", "எதிர்சொல்");
            startActivity(intent);
        });

        question_saval_game.setOnClickListener(v -> {
            dialog_show_now = "dialog_show_now";
            myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
            Intent intent = new Intent(Word_search_main.this, challenge.class);
            finish();
            sp.putString(Word_search_main.this, "option_show", "show");
            sp.putString(Word_search_main.this, "game_type", "Q_A_word");
            sp.putString(Word_search_main.this, "table_name", "Q_A");
            sp.putString(Word_search_main.this, "saval_type", "கேள்வி பதில்கள்");
            startActivity(intent);
        });

        mw_saval_game.setOnClickListener(v -> {

            if (ids == 0) {
                ids = 1;
                dialog_show_now = "dialog_show_now";
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");

                Intent intent = new Intent(Word_search_main.this, challenge.class);
                finish();
                sp.putString(Word_search_main.this, "option_show", "");
                sp.putString(Word_search_main.this, "game_type", "missing_word");
                sp.putString(Word_search_main.this, "table_name", "missing_word");
                sp.putString(Word_search_main.this, "saval_type", "விடுபட்ட வார்த்தை");
                startActivity(intent);
            }

        });

        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void startdialog() {
        final Dialog openDialog_s = new Dialog(Word_search_main.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.guid_dialog);
        openDialog_s.setCancelable(false);
        ImageView gif_load = openDialog_s.findViewById(R.id.gif_load);
        TextView done_exit = openDialog_s.findViewById(R.id.done_exit);

        Glide.with(this).load(R.drawable.gif_load).placeholder(R.drawable.gif_load).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(gif_load);

        done_exit.setOnClickListener(v -> {
            openDialog_s.dismiss();
            sp.putString(Word_search_main.this, "startdialog", "yes");
        });

        openDialog_s.show();
    }

}
