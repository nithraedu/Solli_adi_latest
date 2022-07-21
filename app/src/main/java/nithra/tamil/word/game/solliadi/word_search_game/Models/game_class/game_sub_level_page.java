package nithra.tamil.word.game.solliadi.word_search_game.Models.game_class;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.ads.NativeAdLayout;





import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.New_Main_Gamelist;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Word_search_main;
import nithra.tamil.word.game.solliadi.word_search_game.Models.extra.TinyDB;
import nithra.tamil.word.game.solliadi.word_search_game.Models.general.WordsearchGridFragment;
import nithra.tamil.word.game.solliadi.word_search_game.Models.general.general_play;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.LikeButton;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnAnimationEndListener;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnLikeListener;

import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;


public class game_sub_level_page extends Activity implements OnLikeListener, OnAnimationEndListener {
    TextView sub_level_title;
    ImageView go_back_sub_level, sub_view_level;
    ListView sub_level_list_view;

    public static ArrayList<String> listview_sub_category = new ArrayList<>();

    public static int game_stage=0;
    ArrayList<String> Ad_loaded = new ArrayList<>();

    int native_ad_posion = 5;

    sub_level_Adapter subLevelAdapter = new sub_level_Adapter();
    SharedPreference sp = new SharedPreference();
    SQLiteDatabase mydb, Inner_mydb,exdb;

    Cursor cursor = null;
    private TinyDB tinyDB;

    String table_name = "", level_category = "", level_id = "";

    ImageView icon_ad_img;
    LinearLayout n_icon_ad;
    my_dialog myDialog_class = new my_dialog();

    LinearLayout Baner_frame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_sub_level_page);

        mydb = openOrCreateDatabase("WS_tamil.db", MODE_PRIVATE, null);
        Inner_mydb = openOrCreateDatabase("Inner_DB", 0, null);
        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);

        tinyDB = new TinyDB(game_sub_level_page.this);

        table_name = sp.getString(game_sub_level_page.this, "table_name");
        level_category = sp.getString(game_sub_level_page.this, "level_category");

        Cursor win_cursor = Inner_mydb.rawQuery("select * from '" + table_name + "'", null);
        if (win_cursor.getCount() != 0) {
            for (int i = 0; i < win_cursor.getCount(); i++) {
                win_cursor.moveToPosition(i);
                /*System.out.println("-----------------id : " + win_cursor.getString(win_cursor.getColumnIndex("id")));
                System.out.println("-----------------game_cate : " + win_cursor.getString(win_cursor.getColumnIndex("game_cate")));*/
            }
        }


        Baner_frame = (LinearLayout) findViewById(R.id.Baner_frame);
        sub_level_list_view = (ListView) findViewById(R.id.sub_level_list_view);
        sub_level_title = (TextView) findViewById(R.id.sub_level_title);
        go_back_sub_level = (ImageView) findViewById(R.id.go_back_sub_level);
        go_back_sub_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(game_sub_level_page.this, game_level_page.class);
                finish();
                startActivity(intent);
            }
        });

        icon_ad_img = (ImageView) findViewById(R.id.icon_ad_img);
        n_icon_ad = (LinearLayout) findViewById(R.id.n_icon_ad);
        n_icon_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myDialog_class.WST_Native_IconAd_show();
                //dialog_native_ads.show();
            }
        });


        sub_level_title.setText(sp.getString(game_sub_level_page.this, "level_category"));
        listview_sub_category.clear();
        Ad_loaded.clear();
        for (int i = 0; i < game_level_page.level_lenth; i++) {
            listview_sub_category.add("" + (i + 1));
            Ad_loaded.add("no_add");
        }

        game_stage=listview_sub_category.size();

        /*if (sp.getInt(game_sub_level_page.this, "addcontent") == 1)
        {

            for (int j = 0; j < Ad_loaded.size(); j++) {

                if (j == 2) {
                    Ad_loaded.add(j, "ad_done");
                    listview_sub_category.add(j, "ad_done");
                    native_ad_posion = 5 + j;
                    System.out.println("wordsearchme levelid"+native_ad_posion);

                } else if (native_ad_posion == j && j>2) {
                    {
                        Ad_loaded.add(j,  "ad_done");
                        listview_sub_category.add(j,  "ad_done");
                        native_ad_posion += 5;
                        System.out.println("wordsearchme levelid"+native_ad_posion);

                    }
                }
            }
        }*/


        sub_level_list_view.setAdapter(subLevelAdapter);

    }


    @Override
    public void liked(LikeButton likeButton) {

    }

    @Override
    public void unLiked(LikeButton likeButton) {


    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {
        switch (likeButton.getId()) {
            case R.id.like_key_img: {
                unlock_info();
            }
            break;
            case R.id.like_lock_img: {
                unlock_info();
            }
            break;
        }

        likeButton.setLiked(false);
    }




    public class sub_level_Adapter extends BaseAdapter {

        TextView sub_level_txt, finish_report_txt,coin_msg_coin;
        Chronometer timer_msg_chrome;

        FrameLayout caontent_ad_frame;

        RelativeLayout level_go_lay,timer_message,like_key_lay,like_lock_lay;
        LinearLayout coin_message;

        LikeButton like_key_img, like_lock_img;

        Cursor win_cursor = null;

        @Override
        public int getCount() {
            return listview_sub_category.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            final View view1 = inflater.inflate(R.layout.level_list_design, null);

            caontent_ad_frame = (FrameLayout) view1.findViewById(R.id.caontent_ad_frame);
            sub_level_txt = (TextView) view1.findViewById(R.id.level_txt);
            finish_report_txt = (TextView) view1.findViewById(R.id.finish_report_txt);
            coin_msg_coin = (TextView) view1.findViewById(R.id.coin_msg_coin);
            timer_msg_chrome = (Chronometer) view1.findViewById(R.id.timer_msg_chrome);

            level_go_lay = (RelativeLayout) view1.findViewById(R.id.level_go_lay);
            like_key_lay = (RelativeLayout) view1.findViewById(R.id.like_key_lay);
            like_lock_lay = (RelativeLayout) view1.findViewById(R.id.like_lock_lay);

            coin_message = (LinearLayout) view1.findViewById(R.id.coin_message);
            timer_message = (RelativeLayout) view1.findViewById(R.id.timer_message);

            like_key_img = (LikeButton) view1.findViewById(R.id.like_key_img);
            like_lock_img = (LikeButton) view1.findViewById(R.id.like_lock_img);

            like_key_img.setOnLikeListener(game_sub_level_page.this);
            like_key_img.setOnAnimationEndListener(game_sub_level_page.this);

            like_lock_img.setOnLikeListener(game_sub_level_page.this);
            like_lock_img.setOnAnimationEndListener(game_sub_level_page.this);

            sub_level_txt.setText("நிலை " + listview_sub_category.get(position));

            finish_report_txt.setVisibility(View.VISIBLE);

            if (my_dialog.isNetworkAvailable(game_sub_level_page.this))
            {
                if (Ad_loaded.get(position).equals("ad_done")) {

                    level_go_lay.setVisibility(View.GONE);
                  //  Native_icon_ad.load_addcontent2(game_sub_level_page.this,caontent_ad_frame);
                }
            }else
            {
                caontent_ad_frame.setVisibility(View.GONE);
            }

            try {
                level_id = listview_sub_category.get(position);
                win_cursor = Inner_mydb.rawQuery("select * from '" + table_name + "' where id='" + level_id + "' and game_cate='" + level_category + "'", null);

                if (win_cursor.getCount() == 0) {
                    finish_report_txt.setText("விடுவிக்க ...");
                    //if (position == position) {
                   if (position==0) {
                        finish_report_txt.setText("விளையாடலாமே !");
                    }

                } else {
                    win_cursor.moveToFirst();
                    int game_finish = win_cursor.getInt(win_cursor.getColumnIndex("game_finish"));
                    String best_score = win_cursor.getString(win_cursor.getColumnIndex("best_score"));
                    long time = win_cursor.getLong(win_cursor.getColumnIndex("time"));

                    timer_msg_chrome.setBase(SystemClock.elapsedRealtime() + time);
                    coin_msg_coin.setText(best_score);

                    finish_report_txt.setText("விளையாடலாமே !");
                    if (game_finish != 0) {
                        finish_report_txt.setText("முடிக்கப்பட்டது !");
                    }
                }


            } finally {
                if (win_cursor != null)
                    win_cursor.close();
            }


            //if (position == position) {
            if (position == 0) {
                coin_message.setVisibility(View.VISIBLE);
                timer_message.setVisibility(View.VISIBLE);

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    level_go_lay.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt1));
                } else {
                    level_go_lay.setBackground(getResources().getDrawable(R.drawable.bt1));
                }
            } else {

                level_id = listview_sub_category.get(position);

                try {

                    win_cursor = Inner_mydb.rawQuery("select * from '" + table_name + "' where id='" + level_id + "' and game_cate='" + level_category + "'", null);


                    System.out.println("select * from '" + table_name + "' where id='" + level_id + "' and game_cate='" + level_category + "'");

                    if (win_cursor.getCount() == 0) {

                        like_key_img.setVisibility(View.VISIBLE);
                        like_lock_img.setVisibility(View.VISIBLE);
                        like_key_lay.setVisibility(View.VISIBLE);
                        like_lock_lay.setVisibility(View.VISIBLE);

                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            level_go_lay.setBackground(getResources().getDrawable(R.drawable.bt2));

                        } else {
                            level_go_lay.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt2));
                        }
                    } else {

                        coin_message.setVisibility(View.VISIBLE);
                        timer_message.setVisibility(View.VISIBLE);

                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            level_go_lay.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt1));
                        } else {
                            level_go_lay.setBackground(getResources().getDrawable(R.drawable.bt1));
                        }
                    }
                } finally {
                    if (win_cursor != null)
                        win_cursor.close();
                }


            }

            like_lock_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    level_id = listview_sub_category.get(position);
                    like_lock_img = (LikeButton) view1.findViewById(R.id.like_lock_img);
                    like_lock_img.onClick(v);
                }
            });
            like_key_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    level_id = listview_sub_category.get(position);
                    like_key_img = (LikeButton) view1.findViewById(R.id.like_key_img);
                    like_key_img.onClick(v);
                }
            });



            level_go_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");

                    int game_finish = 0;

                    level_id = listview_sub_category.get(position);

                    try {
                        win_cursor = Inner_mydb.rawQuery("select * from '" + table_name + "' where id='" + level_id + "' and game_cate='" + level_category + "'", null);

                        if (win_cursor.getCount() != 0) {
                            win_cursor.moveToFirst();
                            game_finish = win_cursor.getInt(win_cursor.getColumnIndex("game_finish"));
                            if (game_finish == 1) {
                                retry_concept();
                            } else {
                                sp.putString(game_sub_level_page.this, "sub_level_category", "" + level_id);
                                Intent intent = new Intent(game_sub_level_page.this, general_play.class);
                                finish();
                                startActivity(intent);
                            }

                        } else {

                            //if (position == position) {
                            if (position == 0) {
                                sp.putString(game_sub_level_page.this, "sub_level_category", "" + level_id);
                                Intent intent = new Intent(game_sub_level_page.this, general_play.class);
                                finish();
                                startActivity(intent);
                            } else {
                                like_key_img = (LikeButton) view1.findViewById(R.id.like_key_img);
                                like_lock_img = (LikeButton) view1.findViewById(R.id.like_lock_img);
                                like_key_img.onClick(v);
                            }

                        }
                    } finally {
                        if (win_cursor != null)
                            win_cursor.close();
                    }
                }
            });

            return view1;
        }
    }

    public void unlock_info()
    {
        final Dialog dialog = new Dialog(game_sub_level_page.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_level_unlock);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
        if (!dialog.isShowing()){
            dialog.show();
        }


        TextView unlock_content_txt=(TextView) dialog.findViewById(R.id.unlock_content_txt);

        unlock_content_txt.setText("நிலை "+(Integer.parseInt(level_id)-1)+" ஐ முடித்தால் மட்டுமே நிலை "+level_id+ " விடுவிக்கப்படும்");

        TextView unlock_yes=(TextView) dialog.findViewById(R.id.unlock_yes);
        unlock_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
                dialog.dismiss();
            }
        });
        ImageView close_unlock=(ImageView)dialog.findViewById(R.id.close_unlock);
        close_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
                dialog.dismiss();
            }
        });
    }

    public void retry_concept() {
        final Dialog dialog = new Dialog(game_sub_level_page.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_retry);
        dialog.setCancelable(false);

        TextView cancel_retry = (TextView) dialog.findViewById(R.id.cancel_retry);
        TextView done_retry = (TextView) dialog.findViewById(R.id.done_retry);

        cancel_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        done_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
                dialog.dismiss();
                WordsearchGridFragment.mFoundWords.clear();
                WordsearchGridFragment.mSolution.clear();

               // Inner_mydb.execSQL("UPDATE '"+table_name+"' SET game_finish='" + 2 + "' where id='" + level_id + "' and game_cate='" + level_category + "'");

                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("game_finish", 2);
                Inner_mydb.update(table_name, contentValues12, "id='" + level_id + "' and game_cate='"+level_category+"'", null);
                //game_cate='" + level_category + "'

                tinyDB.putListObject("solutions" + level_category + level_id, new ArrayList<>(WordsearchGridFragment.mSolution));
                tinyDB.putListObject("found" + level_category + level_id, new ArrayList<>(WordsearchGridFragment.mFoundWords));

                sp.putString(game_sub_level_page.this, "sub_level_category", "" + level_id);
                Intent intent = new Intent(game_sub_level_page.this, general_play.class);
                finish();
                startActivity(intent);
            }
        });


        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myDialog_class.media_player(getApplicationContext(), R.raw.click, "stop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //myDialog_class. WST_Native_IconAd(game_sub_level_page.this,icon_ad_img,n_icon_ad);
       // myDialog_class.WST_Native_BannerAd(game_sub_level_page.this, Baner_frame);

        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sp.getInt(game_sub_level_page.this, "purchase_ads") == 1) {
            Baner_frame.setVisibility(View.GONE);
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            native_banner_ad_container.setVisibility(View.GONE);

        } else {
            native_banner_ad_container.setVisibility(View.GONE);
           /* if (Utils.isNetworkAvailable(game_sub_level_page.this)) {
                fb_native(game_sub_level_page.this, native_banner_ad_container);
                *//*  if (sp.getInt(game_sub_level_page.this, "native_banner_ads") == 1) {
                    New_Main_Gamelist.inflateAd(game_sub_level_page.this, native_banner_ad_container);
                } else {
                    fb_native(game_sub_level_page.this, native_banner_ad_container);
                }*//*
            } else {
                native_banner_ad_container.setVisibility(View.GONE);
            }
*/
            /*if (sp.getInt(game_sub_level_page.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(game_sub_level_page.this, Baner_frame);
            } else {
                if (Utils.isNetworkAvailable(game_sub_level_page.this)) {
                    sp.putInt(game_sub_level_page.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(game_sub_level_page.this);
                    adView.setAdUnitId(getString(R.string.main_banner_ori));

                    adView.setAdSize(AdSize.SMART_BANNER);
                    AdRequest request = new AdRequest.Builder().build();
                    adView.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            System.out.println("@@@loaded");
                            Baner_frame.removeAllViews();
                            Baner_frame.addView(adView);
                            Baner_frame.setVisibility(View.VISIBLE);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(game_sub_level_page.this, game_level_page.class);
        finish();
        startActivity(intent);
    }



    //***************  Backup funtion   *******************
    public void backup() {
        final ProgressDialog dialog = ProgressDialog.show(game_sub_level_page.this, "TWS", "Creating Backup Please wait....", true);
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                dialog.dismiss();

            }
        };
        Thread checkUpdate = new Thread() {
            public void run() {

                try {
                    File dbFile =
                            new File(Environment.getDataDirectory() + "/data/nithra.tamil.letter.crossword.search/databases/Inner_DB");
                    FileInputStream in = new FileInputStream(dbFile);

                    File sdCard = getFilesDir();
                    File dir = new File(sdCard.getAbsolutePath() + "/Nithra/TWS/");
                    dir.mkdirs();
                    File file = new File(dir, "Inner_DB");
                    FileOutputStream f = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len1 = 0;
                    while ((len1 = in.read(buffer)) > 0) {
                        f.write(buffer, 0, len1);
                    }
                    f.close();

                } catch (Exception e) {
                    Log.d("CopyFileFromAssetsToSD", e.getMessage());

                }
                handler.sendEmptyMessage(0);
            }
        };
        checkUpdate.start();
    }
}
