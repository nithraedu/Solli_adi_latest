package nithra.tamil.word.game.solliadi.word_search_game.Models;

import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.ads.NativeAdLayout;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.adutils.Ad_NativieUtils;
import nithra.tamil.word.game.solliadi.word_search_game.Models.chellange.challenge;
import nithra.tamil.word.game.solliadi.word_search_game.Models.game_class.game_level_page;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;

public class Word_search_main extends AppCompatActivity {
    static String dialog_show_now = "";
    TextView general_play, category_play, challenge_play;
    SharedPreference sp = new SharedPreference();
    my_dialog myDialog_class = new my_dialog();
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        general_play = (TextView) findViewById(R.id.general_play);
        category_play = (TextView) findViewById(R.id.category_play);
        challenge_play = (TextView) findViewById(R.id.challenge_play);
        ads_lay = (LinearLayout) findViewById(R.id.ads_lay);

        back = (TextView) findViewById(R.id.back);
        intro = (TextView) findViewById(R.id.intro);
        intro_gif = (TextView) findViewById(R.id.intro_gif);
        System.out.println("##############dialog_show_now" + dialog_show_now);
        if (dialog_show_now.equals("dialog_show_now")) {
            dialog_show_now = "";
            System.out.println("-----------------------challenge_games() equals if ");
            challenge_games();
        }

        intro_gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdialog();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //  Intent intent = new Intent(Word_search_main.this, New_Main_Activity.class);
                //   startActivity(intent);
            }
        });
        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog openDialog = new Dialog(Word_search_main.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.introsdialog_web);
                WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
                TextView close = (TextView) openDialog.findViewById(R.id.close);
                TextView done_exit = (TextView) openDialog.findViewById(R.id.done_exit);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog.dismiss();
                    }
                });
                done_exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog.dismiss();
                    }
                });
                intros.loadUrl("file:///android_asset/ws_intro.html");
                intros.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return true;
                    }
                });
                openDialog.show();
            }
        });
        general_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(Word_search_main.this, R.raw.click, "normal");
                Intent intent = new Intent(Word_search_main.this, game_level_page.class);
                sp.putString(Word_search_main.this, "game_type", "பொதுப்பிரிவு");
                sp.putString(Word_search_main.this, "table_name", "general");
                finish();
                startActivity(intent);
            }
        });
        category_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(Word_search_main.this, R.raw.click, "normal");
                Intent intent = new Intent(Word_search_main.this, game_level_page.class);
                sp.putString(Word_search_main.this, "game_type", "வகைகள்");
                sp.putString(Word_search_main.this, "table_name", "category");
                finish();
                startActivity(intent);
            }
        });
        challenge_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog_class.media_player(Word_search_main.this, R.raw.click, "normal");
                challenge_games();


            }
        });
        if (sp.getString(Word_search_main.this, "startdialog").equals("")) {
            startdialog();
        }

        LinearLayout adds = (LinearLayout) findViewById(R.id.ads_lay);
        if (sp.getInt(Word_search_main.this, "purchase_ads") == 0) {
            if (Utils.isNetworkAvailable(Word_search_main.this)) {
                Ad_NativieUtils.load_add_facebook(this, getResources().getString(R.string.Senthamil_Thedal_Native_Banner_new), adds);
            } else {
                adds.setVisibility(View.GONE);
            }
        } else {
            adds.setVisibility(View.GONE);
        }

    }

    private void loads_ads_banner() {
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sp.getInt(Word_search_main.this, "purchase_ads") == 1) {
            ads_lay.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);

            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            if (Utils.isNetworkAvailable(Word_search_main.this)) {
                fb_native(Word_search_main.this, native_banner_ad_container);
                /*  if (sp.getInt(Word_search_main.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Word_search_main.this,native_banner_ad_container);
                }else {
                    fb_native(Word_search_main.this,native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

         /*   if (sp.getInt(Word_search_main.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(Word_search_main.this, ads_lay);
            } else {
                if (Utils.isNetworkAvailable(Word_search_main.this)) {
                    sp.putInt(Word_search_main.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(Word_search_main.this);
                    adView.setAdUnitId(getString(R.string.main_banner_ori));

                    adView.setAdSize(AdSize.SMART_BANNER);
                    AdRequest request = new AdRequest.Builder().build();
                    adView.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            System.out.println("@@@loaded");
                            ads_lay.removeAllViews();
                            ads_lay.addView(adView);
                            ads_lay.setVisibility(View.VISIBLE);
                            super.onAdLoaded();
                        }

                        @Override
                        public void onAdFailedToLoad(int i) {
                            System.out.println("@@@NOt loaded");
                            super.onAdFailedToLoad(i);
                        }
                    });
                    adView.loadAd(request);

                }
            }*/
        }
    }

    public void challenge_games() {

        System.out.println("-----------------------challenge_games() ");

        // Dailytest_ok = "";

        sp.putString(Word_search_main.this, "show_challenge", "");

        dialog = new Dialog(Word_search_main.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_challenge_games);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;


        TextView normal_saval_game = (TextView) dialog.findViewById(R.id.normal_saval_game);
        TextView oposite_saval_game = (TextView) dialog.findViewById(R.id.oposite_saval_game);
        TextView question_saval_game = (TextView) dialog.findViewById(R.id.question_saval_game);
        TextView mw_saval_game = (TextView) dialog.findViewById(R.id.mw_saval_game);
        ImageView saval_back = (ImageView) dialog.findViewById(R.id.saval_back);

        final LinearLayout normal_baner_cha = (LinearLayout) dialog.findViewById(R.id.normal_baner_cha);
        final LinearLayout saval_icon_ad = (LinearLayout) dialog.findViewById(R.id.saval_icon_ad);
        final ImageView saval_icon_ad_img = (ImageView) dialog.findViewById(R.id.saval_icon_ad_img);
        final FrameLayout Baner_frame_challenge = (FrameLayout) dialog.findViewById(R.id.adplaceholder_challenge);

        System.out.println("-----------------------challenge_games() purchase_ads : " + sp.getInt(Word_search_main.this, "purchase_ads"));
        if (sp.getInt(Word_search_main.this, "purchase_ads") == 1) {
            normal_baner_cha.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            System.out.println("-----------------------challenge_games() addlodedd : " + sp.getInt(Word_search_main.this, "addlodedd"));
            NativeAdLayout native_banner_ad_container = (NativeAdLayout) dialog.findViewById(R.id.native_banner_ad_container);
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                native_banner_ad_container.setVisibility(View.GONE);
                //fb_native(getApplicationContext(),native_banner_ad_container);

                /*  if (sp.getInt(getApplicationContext(),"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(getApplicationContext(),native_banner_ad_container);
                }else {
                    fb_native(getApplicationContext(),native_banner_ad_container);
                }*/
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

          /*  if (sp.getInt(Word_search_main.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(Word_search_main.this, normal_baner_cha);
            } else {*/
/*
            if (Utils.isNetworkAvailable(Word_search_main.this)) {
                //  sp.putInt(Word_search_main.this, "addlodedd", 2);
                System.out.println("@IMG");
                final AdView adView = new AdView(Word_search_main.this);
                adView.setAdUnitId(getString(R.string.main_banner_ori));

                adView.setAdSize(AdSize.SMART_BANNER);
                AdRequest request = new AdRequest.Builder().build();
                adView.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        System.out.println("@@@loaded");
                        normal_baner_cha.removeAllViews();
                        normal_baner_cha.addView(adView);
                        normal_baner_cha.setVisibility(View.VISIBLE);
                        super.onAdLoaded();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        System.out.println("@@@NOt loaded");
                        super.onAdFailedToLoad(i);
                    }
                });
                adView.loadAd(request);

            }
*/
            // }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //   myDialog_class.WST_Native_BannerAd(New_Main_Activity.this, Baner_frame_challenge);
            }
        }, 200);


        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

                if (sp.getInt(Word_search_main.this, "purchase_ads") == 1) {
                    ads_lay.setVisibility(View.GONE);
                    System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
                    native_banner_ad_container.setVisibility(View.GONE);

                } else {
                    if (Utils.isNetworkAvailable(Word_search_main.this)) {
                        fb_native(Word_search_main.this, native_banner_ad_container);

                      /*  if (sp.getInt(Word_search_main.this, "native_banner_ads") == 1) {
                            New_Main_Gamelist.inflateAd(Word_search_main.this, native_banner_ad_container);
                        } else {
                            fb_native(Word_search_main.this, native_banner_ad_container);
                        }*/
                    } else {
                        native_banner_ad_container.setVisibility(View.GONE);
                    }

                 /*   if (sp.getInt(Word_search_main.this, "addlodedd") == 1) {
                        New_Main_Activity.load_addFromMain(Word_search_main.this, ads_lay);
                    } else {
                        if (Utils.isNetworkAvailable(Word_search_main.this)) {
                            sp.putInt(Word_search_main.this, "addlodedd", 2);
                            System.out.println("@IMG");
                            final AdView adView = new AdView(Word_search_main.this);
                            adView.setAdUnitId(getString(R.string.main_banner_ori));

                            adView.setAdSize(AdSize.SMART_BANNER);
                            AdRequest request = new AdRequest.Builder().build();
                            adView.setAdListener(new AdListener() {
                                public void onAdLoaded() {
                                    System.out.println("@@@loaded");
                                    ads_lay.removeAllViews();
                                    ads_lay.addView(adView);
                                    ads_lay.setVisibility(View.VISIBLE);
                                    super.onAdLoaded();
                                }

                                @Override
                                public void onAdFailedToLoad(int i) {
                                    System.out.println("@@@NOt loaded");
                                    super.onAdFailedToLoad(i);
                                }
                            });
                            adView.loadAd(request);

                        }
                    }*/
                }
                //   myDialog_class.WST_Native_BannerAd(New_Main_Activity.this, Baner_frame);
            }
        });

        //myDialog_class.WST_Native_IconAd(New_Main_Activity.this, saval_icon_ad_img, saval_icon_ad);

        // Native_icon_ad.load_add(Word_search_main.this, normal_baner_cha);




        /*final String icon_ad_id = "ca-app-pub-4267540560263635/1690188219";

        dialog_native_ads = new Dialog(New_Main_Activity.this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog_native_ads.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_native_ads.setContentView(R.layout.dialog_native_ad);
        dialog_native_ads.setCanceledOnTouchOutside(true);
        adplaceholder_dia = (FrameLayout) dialog_native_ads.findViewById(R.id.adplaceholder_dia);
        native_dismiss = (ImageView) dialog_native_ads.findViewById(R.id.native_dismiss);
        native_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_native_ads.dismiss();
            }
        });
        dialog_native_ads.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Native_icon_ad.setNativeAdvanced_icon_ads(adplaceholder_dia, icon_ad_id, New_Main_Activity.this, saval_icon_ad, saval_icon_ad_img);
            }
        });


        if (myDialog_class.isNetworkAvailable(New_Main_Activity.this)) {
            System.out.println("------icon_native_ads icon_native_ads()");

            Native_icon_ad.setNativeAdvanced_icon_ads(adplaceholder_dia, icon_ad_id, New_Main_Activity.this, saval_icon_ad, saval_icon_ad_img);

            Native_icon_ad.advancads_banner_Install(New_Main_Activity.this,icon_ad_id,Baner_frame_challenge);

        } else {
            saval_icon_ad.setVisibility(View.GONE);
            Baner_frame_challenge.setVisibility(View.GONE);
        }*/

        saval_icon_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // myDialog_class.WST_Native_IconAd_show();
            }
        });

        saval_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
                dialog.dismiss();
            }
        });


        sp.putString(Word_search_main.this, "option_show", "");

        normal_saval_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_show_now = "dialog_show_now";
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
                Intent intent = new Intent(Word_search_main.this, challenge.class);
                finish();
                sp.putString(Word_search_main.this, "saval_type", "வார்த்தை தேடல்");
                sp.putString(Word_search_main.this, "game_type", "challenge_word");
                sp.putString(Word_search_main.this, "table_name", "challenge");
                startActivity(intent);

            }
        });
        oposite_saval_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_show_now = "dialog_show_now";
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");

                Intent intent = new Intent(Word_search_main.this, challenge.class);
                finish();
                sp.putString(Word_search_main.this, "option_show", "show");
                sp.putString(Word_search_main.this, "game_type", "oposit_word");
                sp.putString(Word_search_main.this, "table_name", "yethirsoll");
                sp.putString(Word_search_main.this, "saval_type", "எதிர்சொல்");
                startActivity(intent);
            }
        });

        question_saval_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_show_now = "dialog_show_now";
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
                Intent intent = new Intent(Word_search_main.this, challenge.class);
                finish();
                sp.putString(Word_search_main.this, "option_show", "show");
                sp.putString(Word_search_main.this, "game_type", "Q_A_word");
                sp.putString(Word_search_main.this, "table_name", "Q_A");
                sp.putString(Word_search_main.this, "saval_type", "கேள்வி பதில்கள்");
                startActivity(intent);
            }
        });

        mw_saval_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        // Intent intent = new Intent(Word_search_main.this, New_Main_Activity.class);
        // startActivity(intent);
    }

    public void startdialog() {
        final Dialog openDialog_s = new Dialog(Word_search_main.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_s.setContentView(R.layout.guid_dialog);
        openDialog_s.setCancelable(false);
        ImageView gif_load = (ImageView) openDialog_s.findViewById(R.id.gif_load);
        TextView done_exit = (TextView) openDialog_s.findViewById(R.id.done_exit);

        Glide.with(this).load(R.drawable.gif_load).placeholder(R.drawable.gif_load).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(gif_load);

        done_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_s.dismiss();
                sp.putString(Word_search_main.this, "startdialog", "yes");
            }
        });

        openDialog_s.show();
    }

}
