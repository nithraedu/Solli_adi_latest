package nithra.tamil.word.game.solliadi.word_search_game.Models.game_class;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/*import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;*/
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;


import java.io.File;
import java.util.ArrayList;

import nithra.tamil.word.game.solliadi.DataBaseHelper;
import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.New_Main_Gamelist;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper2;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper3;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;
import nithra.tamil.word.game.solliadi.match_tha_fallows.Match_tha_fallows_game;
import nithra.tamil.word.game.solliadi.word_search_game.Models.Word_search_main;
import nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass.my_dialog;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.LikeButton;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnAnimationEndListener;
import nithra.tamil.word.game.solliadi.word_search_game.Models.like_button.OnLikeListener;

import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;


public class game_level_page extends AppCompatActivity implements OnLikeListener, OnAnimationEndListener, RewardedVideoAdListener {

    TextView level_title;
    ImageView go_back_lecvel, view_lecvel;
    ListView level_list_view;
    int gride = 5;
    int onresume_start = 0;
    int extra_coin_s = 0;
    int reward_play_count = 0;
    int ea = 0;
    TextView coin_value,nl_coins_show;
    int setval_vid;
    private MaxRewardedAd rewardedAd;

    ArrayList<String> listview_category = new ArrayList<>();
    ArrayList<String> Ad_loaded = new ArrayList<>();

    int native_ad_posion = 5, coin_point = 0, pos = 0;

    list_adapter listAdapter;

    String title_value = "", table_name = "";

    public static int level_lenth = 0;

    SharedPreference sp = new SharedPreference();
    SQLiteDatabase mydb, Inner_mydb, exdb;
    Cursor cursor = null, coin_cursor = null;

    ImageView icon_ad_img;
    LinearLayout n_icon_ad;
    my_dialog myDialog_class = new my_dialog();
    LinearLayout coin_lay;
    TextView coin_txt;

    LinearLayout Baner_frame;
    Animation bounce_anim = null;

    int fb_reward = 0;
    com.facebook.ads.RewardedVideoAd rewardedVideoAd;
    int reward_status = 0;
    //*********************reward videos process 1***********************

    String reward_video = "";

    //private final String AD_UNIT_ID = getString(R.string.rewarded);
    private static final String APP_ID = "ca-app-pub-4267540560263635~9441478701";

    private static final long COUNTER_TIME = 10;
    private static final int GAME_OVER_REWARD = 1;

    static private int mCoinCount = 20, coinearn = 1;
    private boolean mGameOver;
    private boolean mGamePaused;
    private RewardedVideoAd mRewardedVideoAd;
    private long mTimeRemaining;
    String btn_str = "";
    //reward videos process 1***********************


    // Facebook variable starts
    private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    public void onError(Ad ad, AdError adError) {

    }

    @Override
    public void onAdLoaded(Ad ad) {

    }

    @Override
    public void onAdClicked(Ad ad) {

    }

    @Override
    public void onLoggingImpression(Ad ad) {

    }

    @Override
    public void onRewardedVideoClosed() {

    }

    private enum PendingAction {
        NONE, POST_PHOTO, POST_STATUS_UPDATE
    }
 /*   private UiLifecycleHelper uiHelper;
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

    Newgame_DataBaseHelper newhelper;
    Newgame_DataBaseHelper2 newhelper2;
    Newgame_DataBaseHelper3 newhelper3;
    DataBaseHelper myDbHelper;
    SQLiteDatabase dbs, dbn, dbn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_level_page);

        mydb = openOrCreateDatabase("WS_tamil.db", MODE_PRIVATE, null);
        Inner_mydb = openOrCreateDatabase("Inner_DB", 0, null);


        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);

        myDbHelper = new DataBaseHelper(game_level_page.this);
        newhelper = new Newgame_DataBaseHelper(game_level_page.this);
        newhelper2 = new Newgame_DataBaseHelper2(game_level_page.this);
        newhelper3 = new Newgame_DataBaseHelper3(game_level_page.this);

        /*exdb = myDbHelper.getReadableDatabase();
        dbs = newhelper.getReadableDatabase();
        dbn = newhelper2.getReadableDatabase();
        dbn2 = newhelper3.getReadableDatabase();*/

        table_name = sp.getString(game_level_page.this, "table_name");
        System.out.println("##############table_name" + table_name);

        //*********************reward videos process 2***********************

//reward videos process 2***********************

        Baner_frame = (LinearLayout) findViewById(R.id.Baner_frame);
        level_list_view = (ListView) findViewById(R.id.level_list_view);
        level_title = (TextView) findViewById(R.id.level_title);
        go_back_lecvel = (ImageView) findViewById(R.id.go_back_lecvel);
        go_back_lecvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(game_level_page.this, Word_search_main.class);
                finish();
                startActivity(intent);
            }
        });

        icon_ad_img = (ImageView) findViewById(R.id.icon_ad_img);
        n_icon_ad = (LinearLayout) findViewById(R.id.n_icon_ad);
        n_icon_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      myDialog_class.WST_Native_IconAd_show();
                //dialog_native_ads.show();
            }
        });

        Bundle b = getIntent().getExtras();

        if (b != null) {

            title_value = getIntent().getStringExtra("game_type");


        }
        //uiHelper = new UiLifecycleHelper(this, callback);

        title_value = sp.getString(game_level_page.this, "game_type");
        level_title.setText(title_value);

        // bounce_anim = AnimationUtils.loadAnimation(game_level_page.this, R.anim.bounce_anim);

        coin_lay = (LinearLayout) findViewById(R.id.coin_lay);
        coin_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more_coin();
            }
        });
        coin_txt = (TextView) findViewById(R.id.coin_txt);
        if (title_value.equals("பொதுப்பிரிவு")) {
            coin_lay.setVisibility(View.GONE);

            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
                coin_txt.setText("" + coin_point);
            }
        }
        set_Adapter();
    }

    public void set_Adapter() {
        try {

            cursor = mydb.rawQuery("select DISTINCT Category from '" + table_name + "'", null);
            listview_category.clear();
            System.out.println("#########################cursor.getCount()" + cursor.getCount());
            if (cursor.getCount() > 0) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    listview_category.add(cursor.getString(cursor.getColumnIndexOrThrow("Category")));
                    System.out.println("#########################listview_category" + listview_category);
                    Ad_loaded.add(cursor.getString(cursor.getColumnIndexOrThrow("Category")));
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println("Exception==" + e.getMessage());
        }


       /* if (sp.getInt(game_level_page.this, "addcontent") == 1)
        {

            for (int j = 0; j < Ad_loaded.size(); j++) {

                if (j == 2) {
                    System.out.println("wordsearchme levelid"+native_ad_posion);

                    Ad_loaded.add(j, "ad_done");
                    listview_category.add(j, "ad_done");

                    native_ad_posion = 5 + j;

                    System.out.println("wordsearchme levelid"+native_ad_posion);

                } else if (native_ad_posion == j && j>2) {
                    {
                        Ad_loaded.add(j,  "ad_done");
                        listview_category.add(j,  "ad_done");
                        native_ad_posion += 5;
                        System.out.println("wordsearchme levelid"+native_ad_posion);

                    }
                }
            }
        }*/

        /* if (title_value.equals("பொதுப்பிரிவு"))
        {

        }else {
            try {

                cursor=mydb.rawQuery("select DISTINCT Category from '"+table_name+"'",null);

                listview_category.clear();
                if (cursor.getCount()>0)
                {
                    for (int i=0;i<cursor.getCount();i++)
                    {
                        cursor.moveToPosition(i);
                        listview_category.add(cursor.getString(cursor.getColumnIndexOrThrow()("Category")));
                    }

                }

                cursor.close();
            } catch (Exception e) {

                System.out.println("Exception==" + e.getMessage());
            }
        }*/


        listAdapter = new list_adapter();
        level_list_view.setAdapter(listAdapter);
    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {

        switch (likeButton.getId()) {
            case R.id.like_key_img: {
                sp.putInt(game_level_page.this, "animation_id", 0);
                unlock_info();
            }
            break;
            case R.id.like_lock_img: {
                sp.putInt(game_level_page.this, "animation_id", 0);
                unlock_info();
            }
            break;
        }

        likeButton.setLiked(false);

    }

    @Override
    public void liked(LikeButton likeButton) {

    }

    @Override
    public void unLiked(LikeButton likeButton) {

    }


    class list_adapter extends BaseAdapter {
        TextView level_txt;
        RelativeLayout level_go_lay, timer_message, like_key_lay, like_lock_lay;
        LinearLayout coin_message;

        FrameLayout caontent_ad_frame;

        LikeButton like_key_img, like_lock_img;


        @Override
        public int getCount() {
            return listview_category.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            final View view1 = inflater.inflate(R.layout.level_list_design, null);

            level_txt = (TextView) view1.findViewById(R.id.level_txt);

            level_go_lay = (RelativeLayout) view1.findViewById(R.id.level_go_lay);
            caontent_ad_frame = (FrameLayout) view1.findViewById(R.id.caontent_ad_frame);

            coin_message = (LinearLayout) view1.findViewById(R.id.coin_message);
            timer_message = (RelativeLayout) view1.findViewById(R.id.timer_message);
            like_key_lay = (RelativeLayout) view1.findViewById(R.id.like_key_lay);
            like_lock_lay = (RelativeLayout) view1.findViewById(R.id.like_lock_lay);

            like_key_img = (LikeButton) view1.findViewById(R.id.like_key_img);
            like_lock_img = (LikeButton) view1.findViewById(R.id.like_lock_img);

            like_key_img.setOnLikeListener(game_level_page.this);
            like_key_img.setOnAnimationEndListener(game_level_page.this);
            like_lock_img.setOnLikeListener(game_level_page.this);
            like_lock_img.setOnAnimationEndListener(game_level_page.this);


            level_txt.setText(listview_category.get(position));
            level_txt.setTextColor(Color.parseColor("#ffffff"));


            if (my_dialog.isNetworkAvailable(game_level_page.this)) {
                if (Ad_loaded.get(position).equals("ad_done")) {

                    level_go_lay.setVisibility(View.GONE);
                    //  Native_icon_ad.load_addcontent2(game_level_page.this,caontent_ad_frame);

                    // Activity_GenCategory.load_addcontent2(Activity_GenSubCategory.this,frameLayout);
                    //Word_search_main.load_addcontent2(Activity_GenSubCategory.this,frameLayout);
                }
            } else {
                caontent_ad_frame.setVisibility(View.GONE);
            }


            // Ad_loaded

            if (title_value.equals("பொதுப்பிரிவு")) {
                if (position == 0) {
                    sp.putString(getApplicationContext(), "level_unlock_" + listview_category.get(position), "level_unlock_" + listview_category.get(position));
                }

                System.out.println("----ss1 : " + sp.getString(getApplicationContext(), "level_unlock_" + listview_category.get(position)));
                System.out.println("----ss2 : " + "level_unlock_" + listview_category.get(position));

                if (sp.getString(getApplicationContext(), "level_unlock_" + listview_category.get(position)).equals("level_unlock_" + listview_category.get(position))) {

                   /* coin_message.setVisibility(View.VISIBLE);
                    timer_message.setVisibility(View.VISIBLE);*/

                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                        level_go_lay.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt2));
                    } else {
                        level_go_lay.setBackground(getResources().getDrawable(R.drawable.bt2));
                    }

                } else {

                    like_key_img.setVisibility(View.VISIBLE);
                    like_lock_img.setVisibility(View.VISIBLE);

                    like_key_lay.setVisibility(View.VISIBLE);
                    like_lock_lay.setVisibility(View.VISIBLE);

                    final int sdk = Build.VERSION.SDK_INT;
                    if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                        level_go_lay.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt3));
                        level_txt.setTextColor(Color.parseColor("#022d0d"));
                    } else {
                        level_go_lay.setBackground(getResources().getDrawable(R.drawable.bt3));
                        level_txt.setTextColor(Color.parseColor("#022d0d"));
                    }

                }
            } else {

                level_txt.setTextSize(18);
            }

            like_key_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sp.putInt(game_level_page.this, "animation_id", 1);
                    pos = position;
                    like_key_img = (LikeButton) view1.findViewById(R.id.like_key_img);
                    like_key_img.onClick(v);

                }
            });
            like_lock_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sp.putInt(game_level_page.this, "animation_id", 1);
                    pos = position;
                    like_lock_img = (LikeButton) view1.findViewById(R.id.like_lock_img);
                    like_lock_img.onClick(v);

                }
            });

            level_go_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sp.getInt(game_level_page.this,"animation_id")==0){
                        sp.putString(game_level_page.this, "level_category", listview_category.get(position));

                        if ((listview_category.size() - 1) > position) {
                            sp.putString(game_level_page.this, "next_level", listview_category.get(position + 1));
                            sp.putString(game_level_page.this, "currrent_level", listview_category.get(position));
                        }

                        myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");

                        pos = position;

                        cursor = mydb.rawQuery("select * from '" + table_name + "' where Category='" + listview_category.get(position) + "'", null);
                        level_lenth = cursor.getCount();

                        if (title_value.equals("பொதுப்பிரிவு")) {


                            if (sp.getString(getApplicationContext(), "level_unlock_" + listview_category.get(position)).equals("level_unlock_" + listview_category.get(position))) {

                                Intent intent = new Intent(game_level_page.this, game_sub_level_page.class);
                                sp.putInt(getApplicationContext(), "GRID", 10);
                                if (title_value.equals("பொதுப்பிரிவு")) {
                                    gride += position;
                                    sp.putInt(getApplicationContext(), "GRID", gride);
                                }

                                finish();
                                startActivity(intent);

                            } else {
                                sp.putInt(game_level_page.this, "animation_id", 1);
                                like_key_img = (LikeButton) view1.findViewById(R.id.like_key_img);
                                like_lock_img = (LikeButton) view1.findViewById(R.id.like_lock_img);

                                like_key_img.onClick(v);


                            }
                        } else {
                      /*  cursor = mydb.rawQuery("select * from '" + table_name + "' where Category='" + listview_category.get(position) + "'", null);
                        level_lenth = cursor.getCount();*/

                            Intent intent = new Intent(game_level_page.this, game_sub_level_page.class);
                            sp.putInt(getApplicationContext(), "GRID", 10);
                            if (title_value.equals("பொதுப்பிரிவு")) {
                                gride += position;
                                sp.putInt(getApplicationContext(), "GRID", gride);
                            }

                            finish();
                            startActivity(intent);
                        }

                    }


                }
            });

            return view1;
        }
    }

    public void unlock_info() {
        final Dialog dialog = new Dialog(game_level_page.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dia_level_unlock);
        //  dialog.getWindow().getAttributes().windowAnimations = R.style.win_anim;
        dialog.show();

        // int half_level=level_lenth/2;
        int half_level = 25;

        TextView unlock_content_txt = (TextView) dialog.findViewById(R.id.unlock_content_txt);

        String before_game = listview_category.get(pos - 1);
        if (before_game.equals("ad_done")) {
            before_game = listview_category.get(pos - 2);
        }
        unlock_content_txt.setText("" + listview_category.get(pos) + " தொகுப்பை விடுவிக்க வேண்டுமெனில் மேலேயுள்ள " + before_game + " தொகுப்பில் " + half_level + " நிலையை முடிக்கவும் (அல்லது) " + "2000 நாணயங்களை கொடுத்து தொகுப்பை விடுவிக்க வேண்டுமெனில் சரி பொத்தானை சொடுக்கவும் ");

        TextView unlock_yes = (TextView) dialog.findViewById(R.id.unlock_yes);
        unlock_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor coin_cursor = null;
                int coin_point = 0;
                try {

                    coin_cursor = myDbHelper.getQry("select * from score");
                    if (coin_cursor.getCount() != 0) {
                        coin_cursor.moveToFirst();
                        coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));

                        if (coin_point > 2000) {
                            //  myDialog_class.media_player(getApplicationContext(), R.raw.level_unlock, "normal");

                            coin_point -= 2000;
                            myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");

                            coin_txt.setText("" + coin_point);

                            sp.putString(getApplicationContext(), "level_unlock_" + listview_category.get(pos), "level_unlock_" + listview_category.get(pos));

                            listAdapter = new list_adapter();
                            level_list_view.setAdapter(listAdapter);

                        } else {
                            myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
                            //more_coin();
                            yes_no_dialog();
                        }

                    }
                } finally {
                    if (coin_cursor != null)
                        coin_cursor.close();
                }

                dialog.dismiss();
            }
        });
        ImageView close_unlock = (ImageView) dialog.findViewById(R.id.close_unlock);
        close_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void coin_earned_anim(final TextView textView, int count) {
        ValueAnimator animator = new ValueAnimator();
        //animator.setObjectValues(0, (coinearn * 10));
        animator.setObjectValues(0, count);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText("" + (int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
        if (requestCode == 11) {//sms
            if (resultCode == 1) {


            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();

            }
        }
        if (requestCode == 12) {//whats app
            if (resultCode == -1) {

                coin_collection(1, 20);
            } else {
                // Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 15) {//g plus
            if (resultCode == -1) {
                coin_collection(1, 10);
            } else {
                //  Toast.makeText(getApplicationContext(), "share and earns", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void coin_collection(int share_member, int value) {

        final Dialog dialog = new Dialog(game_level_page.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.share_dialog2);
        dialog.setCancelable(false);
        dialog.show();

        TextView ok_y = (TextView) dialog.findViewById(R.id.ok_y);
        final TextView b_scores = (TextView) dialog.findViewById(R.id.b_scores);


        try {
            coin_cursor = myDbHelper.getQry("select * from score");
            if (coin_cursor.getCount() != 0) {
                coin_cursor.moveToFirst();
                coin_point = coin_cursor.getInt(coin_cursor.getColumnIndexOrThrow("coins"));
            }
        } finally {
            if (coin_cursor != null)
                coin_cursor.close();
        }
        coin_point += (share_member * value);
        coin_earned_anim(b_scores, (share_member * value));

        myDbHelper.executeSql("UPDATE score SET coins='" + coin_point + "'");


        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  myDialog_class.media_player(game_level_page.this, R.raw.coin, "normal");
                dialog.dismiss();

               /* coin_txt.setText("" + coin_point);
                coin_txt.startAnimation(bounce_anim);*/
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                coin_txt.setText("" + coin_point);
                coin_txt.setAnimation(bounce_anim);
            }
        });
    }

/*
    private void showDialogWithoutNotificationBarInvite(String action, Bundle params) {
        final WebDialog dialog = new WebDialog.Builder(game_level_page.this,
                Session.getActiveSession(), action, params)
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {
                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error != null
                                && !(error instanceof FacebookOperationCanceledException)) {
                            Toast.makeText(game_level_page.this, "Request Not Send", Toast.LENGTH_SHORT).show();
                        }

                        try {
                            System.out.println("Invitation was sent to "
                                    + values.toString());

                            for (int i = 0; values.containsKey("to[" + i + "]"); i++) {
                                String curId = values
                                        .getString("to[" + i + "]");

                            }

                            // lastearn("invaite friends", (values.size() - 1));
                            if ((values.size() - 1) >= 1) {
                                //setcoin(values.size() - 1);

                                coin_collection((values.size() - 1), 10);
                            }

                        } catch (Exception e) {

                        }
                    }
                }).build();

        Window dialog_window = dialog.getWindow();
        dialog_window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // dialogAction = action;
        // dialogParams = params;

        dialog.show();
    }
*/


    //*********************reward videos process 3***********************











    private void addCoins(int coins) {
        mCoinCount = coins;
        sp.putInt(game_level_page.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }




    //reward videos***********************//


    @Override
    protected void onStop() {
        super.onStop();
        myDialog_class.media_player(getApplicationContext(), R.raw.click, "stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reward_video = "";

        //******reward video pocess :4

        //reward video pocess :4 ************//

    }

    @Override
    protected void onResume() {
        super.onResume();

        //******reward video pocess :4
        //loadRewardedVideoAd();

        if (!mGameOver && mGamePaused) {

        }

        //reward video pocess :4 ************//

        //myDialog_class. WST_Native_IconAd(game_level_page.this,icon_ad_img,n_icon_ad);
        // myDialog_class.WST_Native_BannerAd(game_level_page.this, Baner_frame);
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sp.getInt(game_level_page.this, "purchase_ads") == 1) {
            Baner_frame.setVisibility(View.GONE);
            native_banner_ad_container.setVisibility(View.GONE);

            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
        } else {
            native_banner_ad_container.setVisibility(View.GONE);
          /*  if (sp.getInt(game_level_page.this, "addlodedd") == 1) {
                New_Main_Activity.load_addFromMain(game_level_page.this, Baner_frame);
            } else {
                if (Utils.isNetworkAvailable(game_level_page.this)) {
                    fb_native(game_level_page.this, native_banner_ad_container);

                  *//*  if (sp.getInt(game_level_page.this, "native_banner_ads") == 1) {
                        New_Main_Gamelist.inflateAd(game_level_page.this, native_banner_ad_container);
                    } else {
                        fb_native(game_level_page.this, native_banner_ad_container);
                    }*//*
                } else {
                    native_banner_ad_container.setVisibility(View.GONE);
                }
            }*/
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myDialog_class.media_player(getApplicationContext(), R.raw.click, "normal");
        Intent intent = new Intent(game_level_page.this, Word_search_main.class);
        finish();
        startActivity(intent);
    }

    public void yes_no_dialog() {
        final Dialog openDialog_p = new Dialog(game_level_page.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_p.setContentView(R.layout.back_pess);
        final TextView yes = (TextView) openDialog_p.findViewById(R.id.yes);
        TextView no = (TextView) openDialog_p.findViewById(R.id.no);
        LinearLayout lir = (LinearLayout) openDialog_p.findViewById(R.id.lir);
        TextView txt_ex = (TextView) openDialog_p.findViewById(R.id.txt_ex);

        txt_ex.setText("உங்களிடம் போதுமான நாணயங்கள் இல்லை. நாணயங்களை அதிகப்படுத்தி கொள்ள விரும்புகிறீர்களா ?");
        yes.setText("ஆம்");
        no.setText("இல்லை");
        lir.setVisibility(View.GONE);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                more_coin();
                openDialog_p.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_p.dismiss();
            }
        });
        openDialog_p.show();
    }

    public void more_coin() {
        final Dialog openDialog_earncoin;

        openDialog_earncoin = new Dialog(game_level_page.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog_earncoin.setContentView(R.layout.earncoin);


        RelativeLayout wp = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnwa);
        RelativeLayout fb = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnfb);
        RelativeLayout gplus = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earngplus);
        TextView cancel = (TextView) openDialog_earncoin.findViewById(R.id.cancel);
        TextView ss = (TextView) openDialog_earncoin.findViewById(R.id.ssss);


        TextView wpro = (TextView) openDialog_earncoin.findViewById(R.id.wpro);

        cancel.setVisibility(View.INVISIBLE);

        //  wpro.setText("உங்களிடம் போதுமான நாணயங்கள் இல்லை செயலியை பகிர்ந்து நாணயங்கள் பெறவும் .");


        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_earncoin.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_earncoin.cancel();
            }
        });


        RelativeLayout video = (RelativeLayout) openDialog_earncoin.findViewById(R.id.earnvideo);

        final String share_content = "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh";
        String root = Environment.getExternalStorageDirectory().toString();
        File mydir = new File(root + "/.nithra/TWS");
        mydir.mkdirs();
        String fname = "Image-appshare.jpg";
        final File file = new File(mydir, fname);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra_coin_s = 0;
                if (isNetworkAvailable()) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(game_level_page.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedAd.showAd();

                        openDialog_earncoin.dismiss();
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedAd.showAd();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                    //reward(context);
                                    rewarded_ad();
                                    Toast.makeText(game_level_page.this, "மீண்டும் முயற்சிக்கவும்...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }



            }
        });

        wp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    if (appInstalledOrNot("com.whatsapp")) {
                        Uri uri = Uri.fromFile(file);
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setPackage("com.whatsapp");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if ((ContextCompat.checkSelfPermission(game_level_page.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {

                                shareIntent.setType("image/*");
                                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            } else {
                                shareIntent.setType("text/plain");
                            }
                        } else {
                            shareIntent.setType("image/*");
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                        }

                        shareIntent.putExtra(Intent.EXTRA_TEXT, share_content);
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "சொல்லிஅடி !!");
                        startActivityForResult(Intent.createChooser(shareIntent, "Share via"), 12);
                    } else {
                        Toast.makeText(game_level_page.this, "இந்த செயலி தங்களிடம் தற்போது இல்லை பிறகு முயற்சிக்கவும் . ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(game_level_page.this, "இணையத்தள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  if (isNetworkAvailable()) {

                    openDialog_earncoin.cancel();
                    btn_str = "invite";
                    if (isLoggedIn()) {
                        Bundle params = new Bundle();
                        params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                        showDialogWithoutNotificationBarInvite("apprequests", params);
                        // toast("yes");
                    } else {
                        openFacebookSession();
                        // toast("no");
                    }

                } else {
                    Toast.makeText(game_level_page.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
*/

            /*    if (isNetworkAvailable()) {

                    System.out.println("-------gg share_content.length() "+share_content.length());

                    share_content="நித்ராவின் செம்மொழி  வேட்டை \n" + "https://goo.gl/ub45C1";

                    Bundle params = new Bundle();
                    //params.putString("message", "https://play.google.com/store/apps/details?id=nithra.tamil.letter.crossword.search");
                    params.putString("message", share_content);
                    showDialogWithoutNotificationBarInvite("apprequests", params);
                } else {
                    Toast.makeText(getActivity(), "இணையத்தள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {

                    if (appInstalledOrNot("com.google.android.apps.plus")) {

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.setPackage("com.google.android.apps.plus");
                        i.putExtra(Intent.EXTRA_TEXT, share_content);
                        i.putExtra(Intent.EXTRA_SUBJECT, "சொல்லிஅடி !!");
                        startActivityForResult(Intent.createChooser(i, "Share via"), 15);

                    } else {
                        Toast.makeText(game_level_page.this, "இந்த செயலி தங்களிடம் தற்போது இல்லை பிறகு முயற்சிக்கவும் . ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(game_level_page.this, "இணையத்தள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }

        });
        openDialog_earncoin.show();
    }



    public void rewarded_ad() {
        rewardedAd = MaxRewardedAd.getInstance(getResources().getString(R.string.Reward_Ins), this);
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {
                reward_status = 1;
            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

                System.out.println("adchecked--");
                fb_reward = 1;
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                rewarded_ad();
                if (reward_status == 1) {
                    if (extra_coin_s == 0) {
                        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                        cfx.moveToFirst();
                        int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
                        int spx = skx + mCoinCount;
                        String aStringx = Integer.toString(spx);
                        myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            vidcoinearn();

                        }
                    }, 500);
                } else {
                    Toast.makeText(game_level_page.this, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
                }

                fb_reward = 0;
                rewardedAd.loadAd();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                System.out.println("==adtest==" + adUnitId);
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                System.out.println("==adtest_display==" + error);
                rewardedAd.loadAd();
            }
        });
        rewardedAd.loadAd();
    }



    public void vidcoinearn() {
        onresume_start = 0;
        if (extra_coin_s == 1) {
            extra_coin_s = 0;
            reward_play_count = reward_play_count + 1;
            //daily_bones();
            ea = ea + setval_vid;
            coin_value.setText("" + ea);
            //mCoinCount = 0;
        } else {
            final Dialog openDialog = new Dialog(game_level_page.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            openDialog.setContentView(R.layout.share_dialog2);
            openDialog.setCancelable(false);
            // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
            TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
            TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
            // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
            Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
            cfx.moveToFirst();
            final int skx = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            int spx = skx + mCoinCount;
            final String aStringx = Integer.toString(spx);
            b_scores.setText("" + mCoinCount);
            ok_y.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    if (cfx.getCount() != 0) {
                        int kks = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
//                        nl_coins_show.setText("" + kks);
                    }
                    openDialog.dismiss();
                    //mCoinCount = 0;
                }
            });
            openDialog.show();
        }
    }


/*    public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());
    }



    private void openFacebookSession() {
        Session.openActiveSession(this, true, Arrays.asList("email",
                "user_birthday", "user_hometown", "user_location"),
                new Session.StatusCallback() {
                    @Override
                    public void call(Session session, SessionState state,
                                     Exception exception) {

                        if (session != null && session.isOpened()) {
                            // toast("open");

                            if (btn_str.equals("share")) {

                                publishFeedDialog();
                            } else if (btn_str.equals("invite")) {

                                Bundle params = new Bundle();
                                params.putString("message", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் \n" +
                                        "விளையாட இங்கே கிளிக் செய்யவும் https://goo.gl/EUGjDh");
                                showDialogWithoutNotificationBarInvite(
                                        "apprequests", params);
                            }
                        }
                    }
                });
    }
    private void publishFeedDialog() {
        Bundle params = new Bundle();
        params.putString("name", "சொல்லிஅடி");
        // params.putString("caption", "");


        params.putString("name", "சொல்லிஅடி");
        // params.putString("message", "my_message");
        params.putString("link", "https://goo.gl/CcA9a8");
        params.putString("description", "நான் சொல்லிஅடி செயலியை விளையாடுகிறேன் நீங்களும் +\n" +
                "                             விளையாட இங்கே கிளிக் செய்யவும் ");
        params.putString("caption", "நான் சொல்லிஅடி விளையாட இங்கே கிளிக் செய்யவும்  ");


        WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(game_level_page.this,
                Session.getActiveSession(), params)).setOnCompleteListener(
                new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the success
                            // and the post Id.
                            final String postId = values.getString("post_id");
                            if (postId != null) {


                                try {
                                    System.out.println("Invitation was sent to "
                                            + values.toString());

                                    for (int i = 0; values.containsKey("to[" + i + "]"); i++) {
                                        String curId = values
                                                .getString("to[" + i + "]");

                                    }

                                    // lastearn("invaite friends", (values.size() - 1));
                                    if ((values.size() - 1) >= 1) {
                                        //setcoin(values.size() - 1);

                                        coin_collection((values.size() - 1), 10);
                                    }

                                } catch (Exception e) {

                                }


                            } else {
                                // User clicked the Cancel button
                                Toast.makeText(game_level_page.this,
                                        "Publish cancelled", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                            // User clicked the "x" button
                            Toast.makeText(game_level_page.this,
                                    "Publish cancelled", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            // Generic, ex: network error
                            Toast.makeText(game_level_page.this,
                                    "Error posting story", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                }).build();
        feedDialog.show();
    }*/
/*
public void reward(final Context context) {
    rewardedVideoAd = new com.facebook.ads.RewardedVideoAd(context, getString(R.string.fb_rewarded_ins));
    rewardedVideoAd.setAdListener(new com.facebook.ads.RewardedVideoAdListener() {

        @Override
        public void onRewardedVideoCompleted() {
            reward_status=1;
        }

        @Override
        public void onLoggingImpression(Ad ad) {

        }

        @Override
        public void onRewardedVideoClosed() {
            reward(context);
            if (reward_status==1){
                if (extra_coin_s == 0) {
                    Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
                    cfx.moveToFirst();
                    int skx = cfx.getInt(cfx.getColumnIndexOrThrow()("coins"));
                    int spx = skx + mCoinCount;
                    String aStringx = Integer.toString(spx);
                    myDbHelper.executeSql("UPDATE score SET coins='" + spx + "'");

                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (rvo == 2) {
                            share_earn2(mCoinCount);
                        } else {
                            vidcoinearn();
                        }
                    }
                }, 500);
            }else {
                Toast.makeText(context, "முழு காணொளியையும் பார்த்து நாணயங்களை பெற்று கொள்ளவும்.", Toast.LENGTH_SHORT).show();
            }

            fb_reward = 0;
        }

        @Override
        public void onError(Ad ad, AdError adError) {
            //Toast.makeText(context, ""+adError.getErrorCode(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdLoaded(Ad ad) {
            // Rewarded video ad is loaded and ready to be displayed
            fb_reward = 1;
        }

        @Override
        public void onAdClicked(Ad ad) {

        }
    });
    rewardedVideoAd.loadAd();
}
*/

}
