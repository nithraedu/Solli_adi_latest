package nithra.tamil.word.game.solliadi;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.ads.NativeAdLayout;





import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import static nithra.tamil.word.game.solliadi.New_Main_Gamelist.fb_native;

public class Game_States extends AppCompatActivity {


    SQLiteDatabase exdb;
    RelativeLayout myreport,result,profile,rule;
    LinearLayout adds;
    TextView tandc;
    static SharedPreference sps=new SharedPreference();
    String downok = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__states);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        exdb.execSQL("create table if not exists userdetail(id integer,name varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");


        myreport=(RelativeLayout) findViewById(R.id.myreport);
        result=(RelativeLayout)findViewById(R.id.result);
        profile=(RelativeLayout)findViewById(R.id.profile);
        adds=(LinearLayout)findViewById(R.id.ads_lay);
        tandc=(TextView)findViewById(R.id.tandc);
        rule=(RelativeLayout)findViewById(R.id.rule);


        if (Utils.isNetworkAvailable(getApplicationContext())) {
            Calendar calendar2 = Calendar.getInstance();
            int cur_year = calendar2.get(Calendar.YEAR);
            int cur_month = calendar2.get(Calendar.MONTH);
            int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);

            String str_month = "" + (cur_month + 1);
            if (str_month.length() == 1) {
                str_month = "0" + str_month;
            }

            String str_day = ""+cur_day;
            if(str_day.length()==1){
                str_day = "0"+str_day;
            }
            final String str_date = cur_year+"-"+str_month+"-"+str_day;

            if (sps.getString(Game_States.this,"s2"+str_date).equals("")) {
                System.out.println("=============userstates_send");
                ///Prgress Bar Running:
                new AsyncTask<String,String,String>(){

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        //   Utils.mProgress(MainActivity.this,"முதல் தடவை தரவுகளை ஏற்றுகிறது. சில நிமிடங்கள் வரை ஆகலாம், காத்திருக்கவும்.....",false).show();
                    }

                    @Override
                    protected String doInBackground(String... params) {
                        userstates_send();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        sps.putString(Game_States.this,"s2"+str_date,"yes");

                    }
                }.execute();


            }

        }

        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog openDialog = new Dialog(Game_States.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.rule);
                WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
                TextView ok = (TextView) openDialog.findViewById(R.id.ok);
                intros.loadUrl("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/Prize/Rules_Regulations.html");
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog.dismiss();
                    }
                });
                openDialog.show();
            }
        });
        myreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Game_States.this)) {
                    finish();
                    Intent intent = new Intent(Game_States.this, States_Activity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Game_States.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Game_States.this)) {
                    finish();
                    Intent intent = new Intent(Game_States.this,Result_List.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Game_States.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Game_States.this, LoginActivity.class);
                startActivity(i);
            }
        });

        tandc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog openDialog = new Dialog(Game_States.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                openDialog.setContentView(R.layout.rule);
                WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
                TextView ok = (TextView) openDialog.findViewById(R.id.ok);
                intros.loadUrl("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/Prize/Rules_Regulations.html");
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog.dismiss();
                    }
                });
                openDialog.show();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if(keyCode==KeyEvent.KEYCODE_BACK) {

            finish();
            Intent i = new Intent(Game_States.this, MainActivity.class);
            startActivity(i);

        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onResume() {
        super.onResume();
        NativeAdLayout native_banner_ad_container = (NativeAdLayout) findViewById(R.id.native_banner_ad_container);

        if (sps.getInt(Game_States.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase interstitial done");
            native_banner_ad_container.setVisibility(View.GONE);
        }else {
            if (Utils.isNetworkAvailable(Game_States.this)){
                fb_native(Game_States.this,native_banner_ad_container);
               /* if (sps.getInt(Game_States.this,"native_banner_ads")==1){
                    New_Main_Gamelist.inflateAd(Game_States.this,native_banner_ad_container);
                }else {
                    fb_native(Game_States.this,native_banner_ad_container);
                }*/
            }else {
                native_banner_ad_container.setVisibility(View.GONE);
            }

          /*  if (sps.getInt(Game_States.this, "addlodedd") == 1) {
                MainActivity.load_addFromMain(Game_States.this, adds);
            } else {
                if (Utils.isNetworkAvailable(Game_States.this)) {
                    sps.putInt(Game_States.this, "addlodedd", 2);
                    System.out.println("@IMG");
                    final AdView adView = new AdView(Game_States.this);
                    adView.setAdUnitId(getString(R.string.main_banner_ori));

                    adView.setAdSize(AdSize.SMART_BANNER);
                    AdRequest request = new AdRequest.Builder().build();
                    adView.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            System.out.println("@@@loaded");
                            adds.removeAllViews();
                            adds.addView(adView);
                            adds.setVisibility(View.VISIBLE);
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

    public void userstates_send()
    {
        if(sps.getString(Game_States.this,"complite_reg").equals("yes")) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            String date = null;
            String mobileno = null;
            String reg_id = null,email = null,android_id = null;
            String dgame1 = null,dgame2 = null,dgame3 = null,dgame4 = null,dscore = null,dplaytime = null;
            String rgame1 = null,rgame2 = null,rgame3 = null,rgame4 = null,rscore = null,rplaytime = null;
            String share_count = null;


            String dates = "";
            String dgame1s = "",dgame2s= "",dgame3s = "",dgame4s = "",dscores = "",dplaytimes = "";
            String rgame1s = "",rgame2s = "",rgame3s = "",rgame4s = "",rscores = "",rplaytimes = "";
            String share_counts = "";
            String up_date="";

            Cursor sc3 = exdb.rawQuery("select * from userdetail", null);
            sc3.moveToFirst();
            if (sc3.getCount() != 0) {
                mobileno=sc3.getString(sc3.getColumnIndex("phno"));
                email = sc3.getString(sc3.getColumnIndex("email"));
                reg_id = sc3.getString(sc3.getColumnIndex("regid"));
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


            Cursor sc2 = exdb.rawQuery("select distinct (date) from userdata_r where isfinish=0 and date<'" + std + "' ", null);

            if (sc2.getCount()!=0)
            {
                for (int i = 0; i < sc2.getCount(); i++) {
                    sc2.moveToPosition(i);
                    if (sc2.getCount()!=0)
                    {
                        date = sc2.getString(sc2.getColumnIndex("date"));
                        dates=date+","+dates;

                        up_date=up_date+"or date='"+date+"'";
                        // Toast.makeText(MainActivity.this, "dates"+dates, Toast.LENGTH_SHORT).show();

                        Cursor r = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='d'", null);
                        r.moveToFirst();
                        if (r.getCount()!=0) {
                            for (int j = 0; j < r.getCount(); j++) {

                                dgame1 = r.getString(r.getColumnIndex("gm1"));
                                dgame2 = r.getString(r.getColumnIndex("gm2"));
                                dgame3 = r.getString(r.getColumnIndex("gm3"));
                                dgame4 = r.getString(r.getColumnIndex("gm4"));
                                dscore = r.getString(r.getColumnIndex("score"));
                                dplaytime = r.getString(r.getColumnIndex("playtime"));

                            }

                            dgame1s=dgame1+","+dgame1s;
                            dgame2s=dgame2+","+dgame2s;
                            dgame3s=dgame3+","+dgame3s;
                            dgame4s=dgame4+","+dgame4s;
                            dscores=dscore+","+dscores;
                            dplaytimes=dplaytime+","+dplaytimes;


                        }

                        Cursor d = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='r'", null);
                        d.moveToFirst();
                        if (d.getCount()!=0) {
                            for (int j = 0; j < d.getCount(); j++) {

                                rgame1 = d.getString(d.getColumnIndex("gm1"));
                                rgame2 = d.getString(d.getColumnIndex("gm2"));
                                rgame3 = d.getString(d.getColumnIndex("gm3"));
                                rgame4 = d.getString(d.getColumnIndex("gm4"));
                                rscore = d.getString(d.getColumnIndex("score"));
                                rplaytime = d.getString(d.getColumnIndex("playtime"));

                                rgame1s=rgame1+","+rgame1s;
                                rgame2s=rgame2+","+rgame2s;
                                rgame3s=rgame3+","+rgame3s;
                                rgame4s=rgame4+","+rgame4s;
                                rscores=rscore+","+rscores;
                                rplaytimes=rplaytime+","+rplaytimes;
                            }

                        }

                        Cursor s = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='s'", null);
                        s.moveToFirst();
                        if (s.getCount()!=0) {
                            for (int j = 0; j < s.getCount(); j++) {
                                share_count = s.getString(s.getColumnIndex("score"));

                                share_counts=share_count+","+share_counts;

                            }
                        }

                    }


                }

                up_date=up_date.substring(3);
                // Toast.makeText(MainActivity.this, ""+up_date, Toast.LENGTH_SHORT).show();
                System.out.println("date=========="+up_date);


                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("androidid",android_id));
                nameValuePairs.add(new BasicNameValuePair("registrationid",reg_id));
                nameValuePairs.add(new BasicNameValuePair("mobileno",mobileno));

                nameValuePairs.add(new BasicNameValuePair("date",dates));
                nameValuePairs.add(new BasicNameValuePair("dgame1",dgame1s));
                nameValuePairs.add(new BasicNameValuePair("dgame2",dgame2s));
                nameValuePairs.add(new BasicNameValuePair("dgame3",dgame3s));
                nameValuePairs.add(new BasicNameValuePair("dgame4",dgame4s));
                nameValuePairs.add(new BasicNameValuePair("dplaytime",dplaytimes));
                nameValuePairs.add(new BasicNameValuePair("dscore",dscores));

                nameValuePairs.add(new BasicNameValuePair("rgame1",rgame1s));
                nameValuePairs.add(new BasicNameValuePair("rgame2",rgame2s));
                nameValuePairs.add(new BasicNameValuePair("rgame3",rgame3s));
                nameValuePairs.add(new BasicNameValuePair("rgame4",rgame4s));
                nameValuePairs.add(new BasicNameValuePair("rplaytime",rplaytimes));
                nameValuePairs.add(new BasicNameValuePair("rscore",rscores));

                nameValuePairs.add(new BasicNameValuePair("share",share_counts));
                String result = null;

                InputStream is = null;
                StringBuilder sb=null;
                System.out.println("date###=========="+up_date);
                try{
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/userstatus_prize.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                }catch(Exception e){
                    Log.e("log_tag", "Error in https connection" + e.toString());
                }
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line="0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result=sb.toString();

                    System.out.print("ord Result============"+result);




                }catch(Exception e){
                }

                try{
                    JSONArray jArray = new JSONArray(result);
                    System.err.println("#######result==="+result);
                    System.out.println("#######===  "+jArray.length());
                    JSONObject json_data=null;
                    //isvalid=""+jArray.length();
                    downok=""+jArray.length();
                    System.out.print("########insert ord ============"+downok);
                    if(jArray.length()>0) {
                        json_data = jArray.getJSONObject(0);
                        for (int k = 0; k < jArray.length(); k++){

                            String results=json_data.getString("Status");
                            System.out.println("=============Status"+results);
                            if (results.equals("success")){
                                exdb.execSQL("UPDATE userdata_r SET isfinish=1 WHERE " + up_date + "");
                                System.out.println("==========Status ok");
                                System.out.println("===========update_success");
                                System.out.print("Insert for======="+up_date);
                            }

                            json_data = jArray.getJSONObject(k);

                        }
                    }
                }
                catch(JSONException e1){
                } catch (android.net.ParseException e1) {
                }

            }
        }

    }
}
