package nithra.tamil.word.game.solliadi;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.RewardedVideoAd;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

public class States_Activity extends AppCompatActivity {

    static final SharedPreference sps = new SharedPreference();
    final int fb_reward = 0;
    final ArrayList<Integer> nos = new ArrayList<Integer>();
    final ArrayList<String> names = new ArrayList<String>();
    final ArrayList<Integer> scores = new ArrayList<Integer>();
    final ArrayList<String> citys = new ArrayList<String>();
    RewardedVideoAd rewardedVideoAd;
    int reward_status = 0;
    customlist_myreport adapt;
    TextView refresh;
    SQLiteDatabase db1;
    //reward videos process 1***********************
    ListView list;
    String downok = "";
    TextView yourposition, yourscore;
    ArrayList<String> date = new ArrayList<String>();
    String str_date1 = null;
    Context contexts;
    TextView weekly, month, shara, title;
    LinearLayout rewardvideo;
    TextView nodatas;
    String exception = "yes";
    int ex = 0;
    private int mCoinCount;
    private boolean mGameOver;
    private boolean mGamePaused;
    private long mTimeRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_);


        db1 = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        db1.execSQL("create table if not exists userdata_r(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,type varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");

        db1.execSQL("create table if not exists udweek(id integer,pos integer,name varchar,city varchar,score integer,time varchar,myscore integer,mytime integer,mypos integer);");
        db1.execSQL("create table if not exists udmonth(id integer,pos integer,name varchar,city varchar,score integer,time varchar,myscore integer,mytime integer,mypos integer);");
        db1.execSQL("create table if not exists udshare(id integer,pos integer,name varchar,city varchar,score integer,myscore integer,mypos integer);");

        list = (ListView) findViewById(R.id.list);

        weekly = (TextView) findViewById(R.id.weekly);
        month = (TextView) findViewById(R.id.month);
        shara = (TextView) findViewById(R.id.share);
        yourposition = (TextView) findViewById(R.id.yourposition);
        yourscore = (TextView) findViewById(R.id.yourscore);
        title = (TextView) findViewById(R.id.title);
        refresh = (TextView) findViewById(R.id.refresh);
        rewardvideo = (LinearLayout) findViewById(R.id.rewardvideo);
        nodatas = (TextView) findViewById(R.id.nodates);


        HttpParams httpParams = new BasicHttpParams();

        ConnManagerParams.setTimeout(httpParams, 10000);
        HttpConnectionParams.setConnectionTimeout(httpParams, 50000);
        HttpConnectionParams.setSoTimeout(httpParams, 50000);

        HttpClient httpClient = new DefaultHttpClient(httpParams);

///game status sending///
        if (Utils.isNetworkAvailable(getApplicationContext())) {
            Calendar calendar3 = Calendar.getInstance();
            int cur_year1 = calendar3.get(Calendar.YEAR);
            int cur_month1 = calendar3.get(Calendar.MONTH);
            int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

            String str_month1 = "" + (cur_month1 + 1);
            if (str_month1.length() == 1) {
                str_month1 = "0" + str_month1;
            }

            String str_day1 = "" + cur_day1;
            if (str_day1.length() == 1) {
                str_day1 = "0" + str_day1;
            }
            str_date1 = cur_year1 + "-" + str_month1 + "-" + str_day1;
        }

        db1.execSQL("create table if not exists userdetail(id integer,name varchar,upic varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");

        if (sps.getString(States_Activity.this, "complite_reg").equals("yes")) {
            System.out.println("=======inside");
            ///Game states for reward
            //  db1.execSQL("create table if not exists userdata_d(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
            db1.execSQL("create table if not exists userdata_r(id integer PRIMARY KEY AUTOINCREMENT,phno integer,date varchar,type varchar,gm1 integer,gm2 integer,gm3 integer,gm4 integer,score integer,playtime integer,isfinish integer);");
            ///Game states for reward

            String mobileno = null;
            Cursor sc3 = db1.rawQuery("select * from userdetail", null);
            sc3.moveToFirst();
            if (sc3.getCount() != 0) {
                mobileno = sc3.getString(sc3.getColumnIndexOrThrow("phno"));

            }

            Cursor sc2 = db1.rawQuery("select * from userdata_r where date ='" + str_date1 + "' and type='d'", null);
            sc2.moveToFirst();
            if (sc2.getCount() == 0) {
                System.out.println("=======inserting1" + mobileno);
                ContentValues cv = new ContentValues();
                cv.put("phno", "" + mobileno);
                cv.put("date", "" + str_date1);
                cv.put("type", "d");
                cv.put("gm1", "0");
                cv.put("gm2", "0");
                cv.put("gm3", "0");
                cv.put("gm4", "0");
                cv.put("score", "0");
                cv.put("playtime", "0");
                cv.put("isfinish", "0");
                db1.insert("userdata_r", null, cv);
            }

            Cursor sc4 = db1.rawQuery("select * from userdata_r where date ='" + str_date1 + "' and type='r'", null);
            sc4.moveToFirst();
            if (sc4.getCount() == 0) {
                System.out.println("=======inserting2" + str_date1);
                ContentValues cv = new ContentValues();
                cv.put("phno", "" + mobileno);
                cv.put("date", "" + str_date1);
                cv.put("type", "r");
                cv.put("gm1", "0");
                cv.put("gm2", "0");
                cv.put("gm3", "0");
                cv.put("gm4", "0");
                cv.put("score", "0");
                cv.put("playtime", "0");
                cv.put("isfinish", "0");
                db1.insert("userdata_r", null, cv);
            }


            Cursor sc5 = db1.rawQuery("select * from userdata_r where date ='" + str_date1 + "' and type='s'", null);
            sc5.moveToFirst();
            if (sc5.getCount() == 0) {
                System.out.println("=======inserting2" + str_date1);
                ContentValues cv = new ContentValues();
                cv.put("phno", "" + mobileno);
                cv.put("date", "" + str_date1);
                cv.put("type", "s");
                cv.put("gm1", "0");
                cv.put("gm2", "0");
                cv.put("gm3", "0");
                cv.put("gm4", "0");
                cv.put("score", "0");
                cv.put("playtime", "0");
                cv.put("isfinish", "0");
                db1.insert("userdata_r", null, cv);
            }
        }
///game status sending///


        nos.clear();
        names.clear();
        citys.clear();
        scores.clear();
        String mypos = null;
        String myscore = null;
        Cursor r = db1.rawQuery("select * from udweek order by id asc", null);

        if (r.getCount() != 0) {
            for (int j = 0; j < r.getCount(); j++) {
                r.moveToPosition(j);
                nos.add(r.getInt(r.getColumnIndexOrThrow("id")));
                names.add(r.getString(r.getColumnIndexOrThrow("name")));
                citys.add(r.getString(r.getColumnIndexOrThrow("city")));
                scores.add(r.getInt(r.getColumnIndexOrThrow("score")));
                String time = r.getString(r.getColumnIndexOrThrow("time"));
                myscore = r.getString(r.getColumnIndexOrThrow("myscore"));
                String mytime = r.getString(r.getColumnIndexOrThrow("mytime"));
                mypos = r.getString(r.getColumnIndexOrThrow("mypos"));
                nodatas.setVisibility(View.INVISIBLE);
                adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                list.setAdapter(adapt);
            }
            list.setBackgroundColor(Color.parseColor("#ffc933"));
            title.setText("இந்த வாரம் முன்னிலையில் உள்ளவர்கள்.");
            yourposition.setText("உங்களின் நிலை :" + mypos);
            yourscore.setText("உங்களின் மதிப்பெண் :" + myscore);
        } else {
            nos.clear();
            names.clear();
            citys.clear();
            scores.clear();
            list.setBackgroundColor(Color.parseColor("#ffc933"));
            adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
            list.setAdapter(adapt);
            nodatas.setVisibility(View.VISIBLE);
            yourposition.setText("உங்களின் நிலை :");
            yourscore.setText("உங்களின் மதிப்பெண் :");
        }

        if (sps.getString(States_Activity.this, "inside_S").equals("")) {
            //weekly
            if (Utils.isNetworkAvailable(getApplicationContext())) {
                Utils.mProgress(States_Activity.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();
                Utils.mProgress.setCancelable(false);
                db1.execSQL("UPDATE userdata_r SET isfinish=0 WHERE date='" + str_date1 + "'");
                ex = 1;
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {

                        userstates_send();

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        if (exception.equals("yes")) {
                            dialog();

                        } else {
                            nos.clear();
                            names.clear();
                            citys.clear();
                            scores.clear();
                            String mypos = null;
                            String myscore = null;
                            Cursor r = db1.rawQuery("select * from udweek order by id asc", null);

                            if (r.getCount() != 0) {
                                for (int j = 0; j < r.getCount(); j++) {
                                    r.moveToPosition(j);
                                    nos.add(r.getInt(r.getColumnIndexOrThrow("id")));
                                    names.add(r.getString(r.getColumnIndexOrThrow("name")));
                                    citys.add(r.getString(r.getColumnIndexOrThrow("city")));
                                    scores.add(r.getInt(r.getColumnIndexOrThrow("score")));
                                    String time = r.getString(r.getColumnIndexOrThrow("time"));
                                    myscore = r.getString(r.getColumnIndexOrThrow("myscore"));
                                    String mytime = r.getString(r.getColumnIndexOrThrow("mytime"));
                                    mypos = r.getString(r.getColumnIndexOrThrow("mypos"));
                                    nodatas.setVisibility(View.INVISIBLE);
                                    adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                                    list.setAdapter(adapt);
                                }
                                list.setBackgroundColor(Color.parseColor("#ffc933"));
                                title.setText("இந்த வாரம் முன்னிலையில் உள்ளவர்கள்.");
                                yourposition.setText("உங்களின் நிலை :" + mypos);
                                yourscore.setText("உங்களின் மதிப்பெண் :" + myscore);
                            } else {
                                nos.clear();
                                names.clear();
                                citys.clear();
                                scores.clear();
                                list.setBackgroundColor(Color.parseColor("#ffc933"));
                                adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                                list.setAdapter(adapt);
                                nodatas.setVisibility(View.VISIBLE);
                                yourposition.setText("உங்களின் நிலை :");
                                yourscore.setText("உங்களின் மதிப்பெண் :");
                            }
                        }

                        ex = 0;
                        Utils.mProgress.dismiss();
///
                    }
                }.execute();


            } else {
                //Toast.makeText(States_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
            }
            //sps.putString(States_Activity.this,"inside_States1","yes");
        }


        rewardvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(States_Activity.this)) {
                    final ProgressDialog reward_progressBar = ProgressDialog.show(States_Activity.this, "" + "Reward video", "Loading...");
                    if (fb_reward == 1) {
                        reward_progressBar.dismiss();
                        rewardedVideoAd.show();
                        rewardvideo.setVisibility(View.INVISIBLE);
                    } else {
                        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reward_progressBar.dismiss();
                                if (fb_reward == 1) {
                                    rewardedVideoAd.show();
                                    // mShowVideoButton.setVisibility(View.VISIBLE);
                                } else {
                                }
                            }
                        }, 2000);
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    db1.execSQL("UPDATE userdata_r SET isfinish=0 WHERE date='" + str_date1 + "'");
                    Utils.mProgress(States_Activity.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();
                    Utils.mProgress.setCancelable(false);
                    ex = 1;
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {

                            userstates_send();

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                            System.out.println("=========States_Activity=======current date update success");
                            if (exception.equals("yes")) {
                                dialog();
                            } else {
                                nos.clear();
                                names.clear();
                                citys.clear();
                                scores.clear();
                                String mypos = null;
                                String myscore = null;
                                Cursor r = db1.rawQuery("select * from udweek order by id asc", null);

                                if (r.getCount() != 0) {
                                    for (int j = 0; j < r.getCount(); j++) {
                                        r.moveToPosition(j);
                                        nos.add(r.getInt(r.getColumnIndexOrThrow("id")));
                                        names.add(r.getString(r.getColumnIndexOrThrow("name")));
                                        citys.add(r.getString(r.getColumnIndexOrThrow("city")));
                                        scores.add(r.getInt(r.getColumnIndexOrThrow("score")));
                                        String time = r.getString(r.getColumnIndexOrThrow("time"));
                                        myscore = r.getString(r.getColumnIndexOrThrow("myscore"));
                                        String mytime = r.getString(r.getColumnIndexOrThrow("mytime"));
                                        mypos = r.getString(r.getColumnIndexOrThrow("mypos"));
                                        nodatas.setVisibility(View.INVISIBLE);
                                        adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                                        list.setAdapter(adapt);
                                    }
                                    list.setBackgroundColor(Color.parseColor("#ffc933"));
                                    title.setText("இந்த வாரம் முன்னிலையில் உள்ளவர்கள். ");
                                    yourposition.setText("உங்களின் நிலை :" + mypos);
                                    yourscore.setText("உங்களின் மதிப்பெண் :" + myscore);
                                } else {
                                    nos.clear();
                                    names.clear();
                                    citys.clear();
                                    scores.clear();
                                    list.setBackgroundColor(Color.parseColor("#ffc933"));
                                    adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                                    list.setAdapter(adapt);
                                    nodatas.setVisibility(View.VISIBLE);
                                    yourposition.setText("உங்களின் நிலை :");
                                    yourscore.setText("உங்களின் மதிப்பெண் :");

                                }
                            }

                            ex = 0;
                            Utils.mProgress.dismiss();
                        }
                    }.execute();


                } else {
                    Toast.makeText(States_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nos.clear();
                names.clear();
                citys.clear();
                scores.clear();
                String mypos = null;
                String myscore = null;
                Cursor r = db1.rawQuery("select * from udweek order by id asc", null);

                if (r.getCount() != 0) {
                    for (int j = 0; j < r.getCount(); j++) {
                        r.moveToPosition(j);
                        nos.add(r.getInt(r.getColumnIndexOrThrow("id")));
                        names.add(r.getString(r.getColumnIndexOrThrow("name")));
                        citys.add(r.getString(r.getColumnIndexOrThrow("city")));
                        scores.add(r.getInt(r.getColumnIndexOrThrow("score")));
                        String time = r.getString(r.getColumnIndexOrThrow("time"));
                        myscore = r.getString(r.getColumnIndexOrThrow("myscore"));
                        String mytime = r.getString(r.getColumnIndexOrThrow("mytime"));
                        mypos = r.getString(r.getColumnIndexOrThrow("mypos"));
                        nodatas.setVisibility(View.INVISIBLE);
                        adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                        list.setAdapter(adapt);
                    }
                    list.setBackgroundColor(Color.parseColor("#ffc933"));
                    title.setText("இந்த வாரம் முன்னிலையில் உள்ளவர்கள். ");
                    yourposition.setText("உங்களின் நிலை :" + mypos);
                    yourscore.setText("உங்களின் மதிப்பெண் :" + myscore);
                } else {
                    nos.clear();
                    names.clear();
                    citys.clear();
                    scores.clear();
                    list.setBackgroundColor(Color.parseColor("#ffc933"));
                    adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                    list.setAdapter(adapt);
                    nodatas.setVisibility(View.VISIBLE);
                    yourposition.setText("உங்களின் நிலை :");
                    yourscore.setText("உங்களின் மதிப்பெண் :");
                }


            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nos.clear();
                names.clear();
                citys.clear();
                scores.clear();
                String myscore = null;
                String mypos = null;
                Cursor r = db1.rawQuery("select * from udmonth order by id asc", null);
                if (r.getCount() != 0) {
                    for (int j = 0; j < r.getCount(); j++) {
                        r.moveToPosition(j);
                        nos.add(r.getInt(r.getColumnIndexOrThrow("id")));
                        names.add(r.getString(r.getColumnIndexOrThrow("name")));
                        citys.add(r.getString(r.getColumnIndexOrThrow("city")));
                        scores.add(r.getInt(r.getColumnIndexOrThrow("score")));
                        String time = r.getString(r.getColumnIndexOrThrow("time"));
                        myscore = r.getString(r.getColumnIndexOrThrow("myscore"));
                        String mytime = r.getString(r.getColumnIndexOrThrow("mytime"));
                        mypos = r.getString(r.getColumnIndexOrThrow("mypos"));
                        nodatas.setVisibility(View.INVISIBLE);
                        adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                        list.setAdapter(adapt);
                    }

                    list.setBackgroundColor(Color.parseColor("#20418c"));
                    title.setText("இந்த மாதம் முன்னிலையில் உள்ளவர்கள். ");
                    yourposition.setText("உங்களின் நிலை :" + mypos);
                    yourscore.setText("உங்களின் மதிப்பெண் :" + myscore);
                } else {
                    nos.clear();
                    names.clear();
                    citys.clear();
                    scores.clear();
                    list.setBackgroundColor(Color.parseColor("#20418c"));
                    adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                    list.setAdapter(adapt);
                    nodatas.setVisibility(View.VISIBLE);
                    yourposition.setText("உங்களின் நிலை :");
                    yourscore.setText("உங்களின் மதிப்பெண் :");
                }

            }
        });

        shara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nos.clear();
                names.clear();
                citys.clear();
                scores.clear();
                String mypos = null;
                String myscore = null;
                Cursor r = db1.rawQuery("select * from udshare order by id asc", null);

                if (r.getCount() != 0) {
                    for (int j = 0; j < r.getCount(); j++) {
                        r.moveToPosition(j);
                        nos.add(r.getInt(r.getColumnIndexOrThrow("id")));
                        names.add(r.getString(r.getColumnIndexOrThrow("name")));
                        citys.add(r.getString(r.getColumnIndexOrThrow("city")));
                        scores.add(r.getInt(r.getColumnIndexOrThrow("score")));
                        myscore = r.getString(r.getColumnIndexOrThrow("myscore"));
                        mypos = r.getString(r.getColumnIndexOrThrow("mypos"));
                        nodatas.setVisibility(View.INVISIBLE);
                        adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                        list.setAdapter(adapt);
                    }
                    list.setBackgroundColor(Color.parseColor("#5c047e"));
                    title.setText("இந்த மாதம் பகிர் முன்னிலையில் உள்ளவர்கள். ");
                    yourposition.setText("உங்களின் நிலை :" + mypos);
                    yourscore.setText("உங்களின் மதிப்பெண் :" + myscore);
                } else {
                    nos.clear();
                    names.clear();
                    citys.clear();
                    scores.clear();
                    list.setBackgroundColor(Color.parseColor("#5c047e"));
                    nodatas.setVisibility(View.VISIBLE);
                    adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                    list.setAdapter(adapt);
                    yourposition.setText("உங்களின் நிலை :");
                    yourscore.setText("உங்களின் மதிப்பெண் :");
                }

            }
        });


// adapt=new customlist_myreport(States_Activity.this,date,nos,names,scores);


    }

    private void dialog() {


        AlertDialog alertDialog = new AlertDialog.Builder(States_Activity.this).create();
        alertDialog.setMessage("நேரம் முடிந்தது. நீங்கள் மீண்டும் முயற்சிக்க விரும்புகிறீர்களா? ");
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ஆம் ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (Utils.isNetworkAvailable(getApplicationContext())) {
                            dialog.dismiss();
                            Utils.mProgress(States_Activity.this, " தரவுகளை ஏற்றுகிறது, காத்திருக்கவும்.....", true).show();
                            Utils.mProgress.setCancelable(false);
                            db1.execSQL("UPDATE userdata_r SET isfinish=0 WHERE date='" + str_date1 + "'");
                            ex = 1;
                            new AsyncTask<Void, Void, Void>() {

                                @Override
                                protected Void doInBackground(Void... params) {

                                    userstates_send();

                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    super.onPostExecute(aVoid);

                                    if (exception.equals("yes")) {
                                        dialog();

                                    } else {
                                        nos.clear();
                                        names.clear();
                                        citys.clear();
                                        scores.clear();
                                        String mypos = null;
                                        String myscore = null;
                                        Cursor r = db1.rawQuery("select * from udweek order by id asc", null);

                                        if (r.getCount() != 0) {
                                            for (int j = 0; j < r.getCount(); j++) {
                                                r.moveToPosition(j);
                                                nos.add(r.getInt(r.getColumnIndexOrThrow("id")));
                                                names.add(r.getString(r.getColumnIndexOrThrow("name")));
                                                citys.add(r.getString(r.getColumnIndexOrThrow("city")));
                                                scores.add(r.getInt(r.getColumnIndexOrThrow("score")));
                                                String time = r.getString(r.getColumnIndexOrThrow("time"));
                                                myscore = r.getString(r.getColumnIndexOrThrow("myscore"));
                                                String mytime = r.getString(r.getColumnIndexOrThrow("mytime"));
                                                mypos = r.getString(r.getColumnIndexOrThrow("mypos"));
                                                nodatas.setVisibility(View.INVISIBLE);
                                                adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                                                list.setAdapter(adapt);
                                            }
                                            list.setBackgroundColor(Color.parseColor("#ffc933"));
                                            title.setText("இந்த வாரம் முன்னிலையில் உள்ளவர்கள்.");
                                            yourposition.setText("உங்களின் நிலை :" + mypos);
                                            yourscore.setText("உங்களின் மதிப்பெண் :" + myscore);
                                        } else {
                                            nos.clear();
                                            names.clear();
                                            citys.clear();
                                            scores.clear();
                                            list.setBackgroundColor(Color.parseColor("#ffc933"));
                                            adapt = new customlist_myreport(States_Activity.this, nos, names, scores, citys);
                                            list.setAdapter(adapt);
                                            nodatas.setVisibility(View.VISIBLE);
                                            yourposition.setText("உங்களின் நிலை :");
                                            yourscore.setText("உங்களின் மதிப்பெண் :");
                                        }
                                    }

                                    ex = 0;
                                    Utils.mProgress.dismiss();
///
                                }
                            }.execute();


                        } else {
                            Toast.makeText(States_Activity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "இல்லை  ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        Intent i = new Intent(States_Activity.this, Game_States.class);
                        startActivity(i);

                    }
                });

        alertDialog.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {


            finish();
            Intent i = new Intent(States_Activity.this, Game_States.class);
            startActivity(i);

        }
        return super.onKeyDown(keyCode, event);
    }


    public void userstates_send() {


        if (sps.getString(States_Activity.this, "complite_reg").equals("yes")) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            String date = null;
            String mobileno = null;
            String reg_id = null, email = null, android_id = null;
            String dgame1 = null, dgame2 = null, dgame3 = null, dgame4 = null, dscore = null, dplaytime = null;
            String rgame1 = null, rgame2 = null, rgame3 = null, rgame4 = null, rscore = null, rplaytime = null;
            String share_count = null;


            String dates = "";
            String dgame1s = "", dgame2s = "", dgame3s = "", dgame4s = "", dscores = "", dplaytimes = "";
            String rgame1s = "", rgame2s = "", rgame3s = "", rgame4s = "", rscores = "", rplaytimes = "";
            String share_counts = "";
            String up_date = "";

            Cursor sc3 = db1.rawQuery("select * from userdetail", null);
            sc3.moveToFirst();
            if (sc3.getCount() != 0) {
                mobileno = sc3.getString(sc3.getColumnIndexOrThrow("phno"));
                email = sc3.getString(sc3.getColumnIndexOrThrow("email"));
                reg_id = sc3.getString(sc3.getColumnIndexOrThrow("regid"));
                android_id = Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }


            Calendar calendar2 = Calendar.getInstance();
            int cur_year = calendar2.get(Calendar.YEAR);
            int cur_month = calendar2.get(Calendar.MONTH);
            int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

            String str_month = "" + (cur_month + 1);
            if (str_month.length() == 1) {
                str_month = "0" + str_month;
            }

            String str_day = "" + cur_day;
            if (str_day.length() == 1) {
                str_day = "0" + str_day;
            }
            String std = cur_year + "-" + str_month + "-" + str_day;
            Cursor sc2 = db1.rawQuery("select distinct (date) from userdata_r where isfinish=0 and date<='" + std + "' ", null);

            //Cursor sc2 = db1.rawQuery("select distinct (date) from userdata_r where date='" + std + "' ", null);


            if (sc2.getCount() != 0) {
                for (int i = 0; i < sc2.getCount(); i++) {
                    sc2.moveToPosition(i);
                    if (sc2.getCount() != 0) {


                        date = sc2.getString(sc2.getColumnIndexOrThrow("date"));
                        dates = date + "," + dates;
                        up_date = up_date + "or date='" + date + "'";


                        // Toast.makeText(MainActivity.this, "dates"+dates, Toast.LENGTH_SHORT).show();

                        Cursor r = db1.rawQuery("select * from userdata_r where date ='" + date + "' and type='d'", null);
                        r.moveToFirst();
                        if (r.getCount() != 0) {
                            for (int j = 0; j < r.getCount(); j++) {
                                dgame1 = r.getString(r.getColumnIndexOrThrow("gm1"));
                                dgame2 = r.getString(r.getColumnIndexOrThrow("gm2"));
                                dgame3 = r.getString(r.getColumnIndexOrThrow("gm3"));
                                dgame4 = r.getString(r.getColumnIndexOrThrow("gm4"));
                                dscore = r.getString(r.getColumnIndexOrThrow("score"));
                                dplaytime = r.getString(r.getColumnIndexOrThrow("playtime"));
                            }
                            dgame1s = dgame1 + "," + dgame1s;
                            dgame2s = dgame2 + "," + dgame2s;
                            dgame3s = dgame3 + "," + dgame3s;
                            dgame4s = dgame4 + "," + dgame4s;
                            dscores = dscore + "," + dscores;
                            dplaytimes = dplaytime + "," + dplaytimes;


                        }

                        Cursor d = db1.rawQuery("select * from userdata_r where date ='" + date + "' and type='r'", null);
                        d.moveToFirst();
                        if (d.getCount() != 0) {
                            for (int j = 0; j < d.getCount(); j++) {

                                rgame1 = d.getString(d.getColumnIndexOrThrow("gm1"));
                                rgame2 = d.getString(d.getColumnIndexOrThrow("gm2"));
                                rgame3 = d.getString(d.getColumnIndexOrThrow("gm3"));
                                rgame4 = d.getString(d.getColumnIndexOrThrow("gm4"));
                                rscore = d.getString(d.getColumnIndexOrThrow("score"));
                                rplaytime = d.getString(d.getColumnIndexOrThrow("playtime"));

                                rgame1s = rgame1 + "," + rgame1s;
                                rgame2s = rgame2 + "," + rgame2s;
                                rgame3s = rgame3 + "," + rgame3s;
                                rgame4s = rgame4 + "," + rgame4s;
                                rscores = rscore + "," + rscores;
                                rplaytimes = rplaytime + "," + rplaytimes;
                            }

                        }

                        Cursor s = db1.rawQuery("select * from userdata_r where date ='" + date + "' and type='s'", null);
                        s.moveToFirst();
                        if (s.getCount() != 0) {
                            for (int j = 0; j < s.getCount(); j++) {

                                share_count = s.getString(s.getColumnIndexOrThrow("score"));
                                share_counts = share_count + "," + share_counts;

                            }
                        }

                    }


                }


                up_date = up_date.substring(3);

                System.out.println("==================States_Activity==============date" + up_date);
                // Toast.makeText(MainActivity.this, ""+up_date, Toast.LENGTH_SHORT).show();
                System.out.println("date==========" + up_date);
                System.out.println("==============email" + email + "date" + dates);

                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("androidid", android_id));
                nameValuePairs.add(new BasicNameValuePair("registrationid", reg_id));
                nameValuePairs.add(new BasicNameValuePair("mobileno", mobileno));

                nameValuePairs.add(new BasicNameValuePair("date", dates));
                nameValuePairs.add(new BasicNameValuePair("dgame1", dgame1s));
                nameValuePairs.add(new BasicNameValuePair("dgame2", dgame2s));
                nameValuePairs.add(new BasicNameValuePair("dgame3", dgame3s));
                nameValuePairs.add(new BasicNameValuePair("dgame4", dgame4s));
                nameValuePairs.add(new BasicNameValuePair("dplaytime", dplaytimes));
                nameValuePairs.add(new BasicNameValuePair("dscore", dscores));

                nameValuePairs.add(new BasicNameValuePair("rgame1", rgame1s));
                nameValuePairs.add(new BasicNameValuePair("rgame2", rgame2s));
                nameValuePairs.add(new BasicNameValuePair("rgame3", rgame3s));
                nameValuePairs.add(new BasicNameValuePair("rgame4", rgame4s));
                nameValuePairs.add(new BasicNameValuePair("rplaytime", rplaytimes));
                nameValuePairs.add(new BasicNameValuePair("rscore", rscores));

                nameValuePairs.add(new BasicNameValuePair("share", share_counts));
                String result = null;

                InputStream is = null;
                StringBuilder sb = null;

                //https://nithra.mobi/solliadi/userstatus_prize.php
                try {
                    System.out.println("========*==*====try");
                    HttpParams httpParams = new BasicHttpParams();
                    ConnManagerParams.setTimeout(httpParams, 1000);
                    HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
                    HttpConnectionParams.setSoTimeout(httpParams, 30000);
                    HttpClient httpClient = new DefaultHttpClient(httpParams);
                    HttpPost httpPost = new HttpPost("https://nithra.mobi/solliadi/userstatus_prize.php");

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity entity = httpResponse.getEntity();
                    is = entity.getContent();
                    //Do Someting if input-string not null
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
                        sb = new StringBuilder();
                        sb.append(reader.readLine() + "\n");
                        String line = "0";
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        is.close();
                        result = sb.toString();

                        System.err.println("result*****===" + result);


                    } catch (Exception e) {
                    }

                    try {
                        JSONArray jArray = new JSONArray(result);
                        System.err.println("result===" + result);
                        System.out.println("===  " + jArray.length());
                        JSONObject json_data = null;
                        //isvalid=""+jArray.length();
                        downok = "" + jArray.length();
                        System.out.print("insert ord ============" + downok);
                        if (jArray.length() > 0) {
                            json_data = jArray.getJSONObject(0);
                            for (int k = 0; k < jArray.length(); k++) {
                                System.out.print("Insert for=======");
                                json_data = jArray.getJSONObject(k);
                                System.out.print("ord Result============" + result);
                                String results = json_data.getString("Status");
                                System.out.println("=============Status" + results);
                                if (results.equals("success")) {
                                    exception = "no";
                                    System.out.println("==========Status ok");
                                    System.out.println("===========update_success");
                                    db1.execSQL("UPDATE userdata_r SET isfinish=1 WHERE" + up_date + "");


                                    db1.delete("udweek", null, null);
                                    db1.delete("udmonth", null, null);
                                    db1.delete("udshare", null, null);


                                    if (json_data.getString("ws1").equals("null")) {

                                    } else {
                                        ContentValues cv = new ContentValues();
                                        cv.put("name", json_data.getString("wn1"));
                                        cv.put("city", json_data.getString("wd1"));
                                        cv.put("id", "1");
                                        cv.put("score", json_data.getString("ws1"));
                                        cv.put("time", json_data.getString("wt1"));
                                        cv.put("myscore", json_data.getString("yws"));
                                        cv.put("mytime", json_data.getString("ywt"));
                                        cv.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cv);
                                    }

                                    if (json_data.getString("ws2").equals("null")) {

                                    } else {
                                        ContentValues cvw1 = new ContentValues();
                                        cvw1.put("name", json_data.getString("wn2"));
                                        cvw1.put("city", json_data.getString("wd2"));
                                        cvw1.put("id", "2");
                                        cvw1.put("score", json_data.getString("ws2"));
                                        cvw1.put("time", json_data.getString("wt2"));
                                        cvw1.put("myscore", json_data.getString("yws"));
                                        cvw1.put("mytime", json_data.getString("ywt"));
                                        cvw1.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw1);
                                    }

                                    if (json_data.getString("ws3").equals("null")) {

                                    } else {
                                        ContentValues cvw2 = new ContentValues();
                                        cvw2.put("name", json_data.getString("wn3"));
                                        cvw2.put("city", json_data.getString("wd3"));
                                        cvw2.put("id", "3");
                                        cvw2.put("score", json_data.getString("ws3"));
                                        cvw2.put("time", json_data.getString("wt3"));
                                        cvw2.put("myscore", json_data.getString("yws"));
                                        cvw2.put("mytime", json_data.getString("ywt"));
                                        cvw2.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw2);
                                    }

                                    if (json_data.getString("ws4").equals("null")) {

                                    } else {
                                        ContentValues cvw3 = new ContentValues();
                                        cvw3.put("name", json_data.getString("wn4"));
                                        cvw3.put("city", json_data.getString("wd4"));
                                        cvw3.put("id", "4");
                                        cvw3.put("score", json_data.getString("ws4"));
                                        cvw3.put("time", json_data.getString("wt4"));
                                        cvw3.put("myscore", json_data.getString("yws"));
                                        cvw3.put("mytime", json_data.getString("ywt"));
                                        cvw3.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw3);
                                    }

                                    if (json_data.getString("ws5").equals("null")) {

                                    } else {
                                        ContentValues cvw4 = new ContentValues();
                                        cvw4.put("name", json_data.getString("wn5"));
                                        cvw4.put("city", json_data.getString("wd5"));
                                        cvw4.put("id", "5");
                                        cvw4.put("score", json_data.getString("ws5"));
                                        cvw4.put("time", json_data.getString("wt5"));
                                        cvw4.put("myscore", json_data.getString("yws"));
                                        cvw4.put("mytime", json_data.getString("ywt"));
                                        cvw4.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw4);
                                    }

                                    if (json_data.getString("ws6").equals("null")) {

                                    } else {
                                        ContentValues cvw5 = new ContentValues();
                                        cvw5.put("name", json_data.getString("wn6"));
                                        cvw5.put("city", json_data.getString("wd6"));
                                        cvw5.put("id", "6");
                                        cvw5.put("score", json_data.getString("ws6"));
                                        cvw5.put("time", json_data.getString("wt6"));
                                        cvw5.put("myscore", json_data.getString("yws"));
                                        cvw5.put("mytime", json_data.getString("ywt"));
                                        cvw5.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw5);
                                    }

                                    if (json_data.getString("ws7").equals("null")) {

                                    } else {
                                        ContentValues cvw6 = new ContentValues();
                                        cvw6.put("name", json_data.getString("wn7"));
                                        cvw6.put("city", json_data.getString("wd7"));
                                        cvw6.put("id", "7");
                                        cvw6.put("score", json_data.getString("ws7"));
                                        cvw6.put("time", json_data.getString("wt7"));
                                        cvw6.put("myscore", json_data.getString("yws"));
                                        cvw6.put("mytime", json_data.getString("ywt"));
                                        cvw6.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw6);
                                    }

                                    if (json_data.getString("ws8").equals("null")) {

                                    } else {
                                        ContentValues cvw7 = new ContentValues();
                                        cvw7.put("name", json_data.getString("wn8"));
                                        cvw7.put("city", json_data.getString("wd8"));
                                        cvw7.put("id", "8");
                                        cvw7.put("score", json_data.getString("ws8"));
                                        cvw7.put("time", json_data.getString("wt8"));
                                        cvw7.put("myscore", json_data.getString("yws"));
                                        cvw7.put("mytime", json_data.getString("ywt"));
                                        cvw7.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw7);
                                    }

                                    if (json_data.getString("ws9").equals("null")) {

                                    } else {
                                        ContentValues cvw8 = new ContentValues();
                                        cvw8.put("name", json_data.getString("wn9"));
                                        cvw8.put("city", json_data.getString("wd9"));
                                        cvw8.put("id", "9");
                                        cvw8.put("score", json_data.getString("ws9"));
                                        cvw8.put("time", json_data.getString("wt9"));
                                        cvw8.put("myscore", json_data.getString("yws"));
                                        cvw8.put("mytime", json_data.getString("ywt"));
                                        cvw8.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw8);
                                    }

                                    if (json_data.getString("ws10").equals("null")) {

                                    } else {
                                        ContentValues cvw9 = new ContentValues();
                                        cvw9.put("name", json_data.getString("wn10"));
                                        cvw9.put("city", json_data.getString("wd10"));
                                        cvw9.put("id", "10");
                                        cvw9.put("score", json_data.getString("ws10"));
                                        cvw9.put("time", json_data.getString("wt10"));
                                        cvw9.put("myscore", json_data.getString("yws"));
                                        cvw9.put("mytime", json_data.getString("ywt"));
                                        cvw9.put("mypos", json_data.getString("ywp"));
                                        db1.insert("udweek", null, cvw9);
                                    }

                                    if (json_data.getString("ms1").equals("null")) {

                                    } else {
                                        ContentValues udmonth = new ContentValues();
                                        udmonth.put("name", json_data.getString("mn1"));
                                        udmonth.put("city", json_data.getString("md1"));
                                        udmonth.put("id", "1");
                                        udmonth.put("score", json_data.getString("ms1"));
                                        udmonth.put("time", json_data.getString("mt1"));
                                        udmonth.put("myscore", json_data.getString("yms"));
                                        udmonth.put("mytime", json_data.getString("ymt"));
                                        udmonth.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth);
                                    }

                                    if (json_data.getString("ms2").equals("null")) {

                                    } else {
                                        ContentValues udmonth1 = new ContentValues();
                                        udmonth1.put("name", json_data.getString("mn2"));
                                        udmonth1.put("city", json_data.getString("md2"));
                                        udmonth1.put("id", "2");
                                        udmonth1.put("score", json_data.getString("ms2"));
                                        udmonth1.put("time", json_data.getString("mt2"));
                                        udmonth1.put("myscore", json_data.getString("yms"));
                                        udmonth1.put("mytime", json_data.getString("ymt"));
                                        udmonth1.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth1);
                                    }

                                    if (json_data.getString("ms3").equals("null")) {

                                    } else {
                                        ContentValues udmonth2 = new ContentValues();
                                        udmonth2.put("name", json_data.getString("mn3"));
                                        udmonth2.put("city", json_data.getString("md3"));
                                        udmonth2.put("id", "3");
                                        udmonth2.put("score", json_data.getString("ms3"));
                                        udmonth2.put("time", json_data.getString("mt3"));
                                        udmonth2.put("myscore", json_data.getString("yms"));
                                        udmonth2.put("mytime", json_data.getString("ymt"));
                                        udmonth2.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth2);
                                    }

                                    if (json_data.getString("ms4").equals("null")) {

                                    } else {
                                        ContentValues udmonth3 = new ContentValues();
                                        udmonth3.put("name", json_data.getString("mn4"));
                                        udmonth3.put("city", json_data.getString("md4"));
                                        udmonth3.put("id", "4");
                                        udmonth3.put("score", json_data.getString("ms4"));
                                        udmonth3.put("time", json_data.getString("mt4"));
                                        udmonth3.put("myscore", json_data.getString("yms"));
                                        udmonth3.put("mytime", json_data.getString("ymt"));
                                        udmonth3.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth3);
                                    }
                                    if (json_data.getString("ms5").equals("null")) {

                                    } else {
                                        ContentValues udmonth4 = new ContentValues();
                                        udmonth4.put("name", json_data.getString("mn5"));
                                        udmonth4.put("city", json_data.getString("md5"));
                                        udmonth4.put("id", "5");
                                        udmonth4.put("score", json_data.getString("ms5"));
                                        udmonth4.put("time", json_data.getString("mt5"));
                                        udmonth4.put("myscore", json_data.getString("yms"));
                                        udmonth4.put("mytime", json_data.getString("ymt"));
                                        udmonth4.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth4);
                                    }
                                    if (json_data.getString("ms6").equals("null")) {

                                    } else {
                                        ContentValues udmonth5 = new ContentValues();
                                        udmonth5.put("name", json_data.getString("mn6"));
                                        udmonth5.put("city", json_data.getString("md6"));
                                        udmonth5.put("id", "6");
                                        udmonth5.put("score", json_data.getString("ms6"));
                                        udmonth5.put("time", json_data.getString("mt6"));
                                        udmonth5.put("myscore", json_data.getString("yms"));
                                        udmonth5.put("mytime", json_data.getString("ymt"));
                                        udmonth5.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth5);
                                    }
                                    if (json_data.getString("ms7").equals("null")) {

                                    } else {
                                        ContentValues udmonth6 = new ContentValues();
                                        udmonth6.put("name", json_data.getString("mn7"));
                                        udmonth6.put("city", json_data.getString("md7"));
                                        udmonth6.put("id", "7");
                                        udmonth6.put("score", json_data.getString("ms7"));
                                        udmonth6.put("time", json_data.getString("mt7"));
                                        udmonth6.put("myscore", json_data.getString("yms"));
                                        udmonth6.put("mytime", json_data.getString("ymt"));
                                        udmonth6.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth6);
                                    }
                                    if (json_data.getString("ms8").equals("null")) {

                                    } else {
                                        ContentValues udmonth7 = new ContentValues();
                                        udmonth7.put("name", json_data.getString("mn8"));
                                        udmonth7.put("city", json_data.getString("md8"));
                                        udmonth7.put("id", "8");
                                        udmonth7.put("score", json_data.getString("ms8"));
                                        udmonth7.put("time", json_data.getString("mt8"));
                                        udmonth7.put("myscore", json_data.getString("yms"));
                                        udmonth7.put("mytime", json_data.getString("ymt"));
                                        udmonth7.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth7);
                                    }
                                    if (json_data.getString("ms9").equals("null")) {

                                    } else {
                                        ContentValues udmonth8 = new ContentValues();
                                        udmonth8.put("name", json_data.getString("mn9"));
                                        udmonth8.put("city", json_data.getString("md9"));
                                        udmonth8.put("id", "9");
                                        udmonth8.put("score", json_data.getString("ms9"));
                                        udmonth8.put("time", json_data.getString("mt9"));
                                        udmonth8.put("myscore", json_data.getString("yms"));
                                        udmonth8.put("mytime", json_data.getString("ymt"));
                                        udmonth8.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth8);
                                    }
                                    if (json_data.getString("ms10").equals("null")) {

                                    } else {
                                        ContentValues udmonth9 = new ContentValues();
                                        udmonth9.put("name", json_data.getString("mn10"));
                                        udmonth9.put("city", json_data.getString("md10"));
                                        udmonth9.put("id", "10");
                                        udmonth9.put("score", json_data.getString("ms10"));
                                        udmonth9.put("time", json_data.getString("mt10"));
                                        udmonth9.put("myscore", json_data.getString("yms"));
                                        udmonth9.put("mytime", json_data.getString("ymt"));
                                        udmonth9.put("mypos", json_data.getString("ymp"));
                                        db1.insert("udmonth", null, udmonth9);
                                    }

                                    if (json_data.getString("ss1").equals("null")) {

                                    } else {
                                        ContentValues udshare = new ContentValues();
                                        udshare.put("name", json_data.getString("sn1"));
                                        udshare.put("city", json_data.getString("sd1"));
                                        udshare.put("id", "1");
                                        udshare.put("score", json_data.getString("ss1"));
                                        udshare.put("myscore", json_data.getString("ys"));
                                        udshare.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare);
                                    }

                                    if (json_data.getString("ss2").equals("null")) {

                                    } else {
                                        ContentValues udshare1 = new ContentValues();
                                        udshare1.put("name", json_data.getString("sn2"));
                                        udshare1.put("city", json_data.getString("sd2"));
                                        udshare1.put("id", "2");
                                        udshare1.put("score", json_data.getString("ss2"));
                                        udshare1.put("myscore", json_data.getString("ys"));
                                        udshare1.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare1);
                                    }


                                    if (json_data.getString("ss3").equals("null")) {

                                    } else {
                                        ContentValues udshare2 = new ContentValues();
                                        udshare2.put("name", json_data.getString("sn3"));
                                        udshare2.put("city", json_data.getString("sd3"));
                                        udshare2.put("id", "3");
                                        udshare2.put("score", json_data.getString("ss3"));
                                        udshare2.put("myscore", json_data.getString("ys"));
                                        udshare2.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare2);
                                    }


                                    if (json_data.getString("ss4").equals("null")) {

                                    } else {
                                        ContentValues udshare3 = new ContentValues();
                                        udshare3.put("name", json_data.getString("sn4"));
                                        udshare3.put("city", json_data.getString("sd4"));
                                        udshare3.put("id", "4");
                                        udshare3.put("score", json_data.getString("ss4"));
                                        udshare3.put("myscore", json_data.getString("ys"));
                                        udshare3.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare3);
                                    }

                                    if (json_data.getString("ss5").equals("null")) {

                                    } else {
                                        ContentValues udshare4 = new ContentValues();
                                        udshare4.put("name", json_data.getString("sn5"));
                                        udshare4.put("city", json_data.getString("sd5"));
                                        udshare4.put("id", "5");
                                        udshare4.put("score", json_data.getString("ss5"));
                                        udshare4.put("myscore", json_data.getString("ys"));
                                        udshare4.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare4);
                                    }

                                    if (json_data.getString("ss6").equals("null")) {

                                    } else {
                                        ContentValues udshare5 = new ContentValues();
                                        udshare5.put("name", json_data.getString("sn6"));
                                        udshare5.put("city", json_data.getString("sd6"));
                                        udshare5.put("id", "6");
                                        udshare5.put("score", json_data.getString("ss6"));
                                        udshare5.put("myscore", json_data.getString("ys"));
                                        udshare5.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare5);
                                    }
                                    if (json_data.getString("ss7").equals("null")) {

                                    } else {
                                        ContentValues udshare6 = new ContentValues();
                                        udshare6.put("name", json_data.getString("sn7"));
                                        udshare6.put("city", json_data.getString("sd7"));
                                        udshare6.put("id", "7");
                                        udshare6.put("score", json_data.getString("ss7"));
                                        udshare6.put("myscore", json_data.getString("ys"));
                                        udshare6.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare6);
                                    }
                                    if (json_data.getString("ss8").equals("null")) {

                                    } else {
                                        ContentValues udshare7 = new ContentValues();
                                        udshare7.put("name", json_data.getString("sn8"));
                                        udshare7.put("city", json_data.getString("sd8"));
                                        udshare7.put("id", "8");
                                        udshare7.put("score", json_data.getString("ss8"));
                                        udshare7.put("myscore", json_data.getString("ys"));
                                        udshare7.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare7);
                                    }
                                    if (json_data.getString("ss9").equals("null")) {

                                    } else {
                                        ContentValues udshare8 = new ContentValues();
                                        udshare8.put("name", json_data.getString("sn9"));
                                        udshare8.put("city", json_data.getString("sd9"));
                                        udshare8.put("id", "9");
                                        udshare8.put("score", json_data.getString("ss9"));
                                        udshare8.put("myscore", json_data.getString("ys"));
                                        udshare8.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare8);
                                    }
                                    if (json_data.getString("ss10").equals("null")) {

                                    } else {
                                        ContentValues udshare9 = new ContentValues();
                                        udshare9.put("name", json_data.getString("sn10"));
                                        udshare9.put("city", json_data.getString("sd10"));
                                        udshare9.put("id", "10");
                                        udshare9.put("score", json_data.getString("ss10"));
                                        udshare9.put("myscore", json_data.getString("ys"));
                                        udshare9.put("mypos", json_data.getString("yp"));
                                        db1.insert("udshare", null, udshare9);
                                    }

                                    System.out.println("=================today" + str_date1);


                                }
                            }
                        }
                    } catch (JSONException e1) {
                    } catch (android.net.ParseException e1) {
                    }


                } catch (ConnectTimeoutException e) {
                    //Here Connection TimeOut excepion

                    System.out.println("===============catch");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        //pauseGame();


    }

    protected void onResume() {
        super.onResume();
        if (!mGameOver && mGamePaused) {
            // resumeGame();
        }

    }


    //*********************reward videos process 3***********************


    private void addCoins(int coins) {
        mCoinCount = coins;
        sps.putInt(States_Activity.this, "reward_coin_txt", coins);
        //mCoinCountText.setText("Coins: " + mCoinCount);
    }


    //reward videos***********************//
    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
