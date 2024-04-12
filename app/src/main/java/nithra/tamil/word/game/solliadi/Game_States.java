package nithra.tamil.word.game.solliadi;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Game_States extends AppCompatActivity {


    static final SharedPreference sps = new SharedPreference();
    SQLiteDatabase exdb;
    RelativeLayout myreport, result, profile, rule;
    LinearLayout adds;
    TextView tandc;
    String downok = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__states);


        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        exdb.execSQL("create table if not exists userdetail(id integer,name varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");


        myreport = (RelativeLayout) findViewById(R.id.myreport);
        result = (RelativeLayout) findViewById(R.id.result);
        profile = (RelativeLayout) findViewById(R.id.profile);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        tandc = (TextView) findViewById(R.id.tandc);
        rule = (RelativeLayout) findViewById(R.id.rule);


        if (Utils.isNetworkAvailable(getApplicationContext())) {
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
            final String str_date = cur_year + "-" + str_month + "-" + str_day;

            if (sps.getString(Game_States.this, "s2" + str_date).equals("")) {
                System.out.println("=============userstates_send");
                ///Prgress Bar Running:
                new AsyncTask<String, String, String>() {

                    @Override
                    protected String doInBackground(String... params) {
                        userstates_send();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        sps.putString(Game_States.this, "s2" + str_date, "yes");

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
                if (!isFinishing())openDialog.show();
            }
        });
        myreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Game_States.this)) {
                    finish();
                    Intent intent = new Intent(Game_States.this, States_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Game_States.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(Game_States.this)) {
                    finish();
                    Intent intent = new Intent(Game_States.this, Result_List.class);
                    startActivity(intent);
                } else {
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
                if (!isFinishing())openDialog.show();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            Intent i = new Intent(Game_States.this, New_Main_Activity.class);
            startActivity(i);

        }
        return super.onKeyDown(keyCode, event);
    }

    public void userstates_send(){
        if (sps.getString(Game_States.this, "complite_reg").equals("yes")) {
            RetofitClient retrofit = new RetofitClient();
            Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

            HashMap<String,String> map = new  HashMap<String,String>();



          //  ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            String date;
            String mobileno = null;
            String reg_id = null, email = null, android_id = null;
            String dgame1 = null, dgame2 = null, dgame3 = null, dgame4 = null, dscore = null, dplaytime = null;
            String rgame1, rgame2, rgame3, rgame4, rscore, rplaytime;
            String share_count;


            String dates = "";
            String dgame1s = "", dgame2s = "", dgame3s = "", dgame4s = "", dscores = "", dplaytimes = "";
            String rgame1s = "", rgame2s = "", rgame3s = "", rgame4s = "", rscores = "", rplaytimes = "";
            String share_counts = "";
            String up_date = "";

            Cursor sc3 = exdb.rawQuery("select * from userdetail", null);
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


            Cursor sc2 = exdb.rawQuery("select distinct (date) from userdata_r where isfinish=0 and date<'" + std + "' ", null);

            if (sc2.getCount() != 0) {
                for (int i = 0; i < sc2.getCount(); i++) {
                    sc2.moveToPosition(i);
                    if (sc2.getCount() != 0) {
                        date = sc2.getString(sc2.getColumnIndexOrThrow("date"));
                        dates = date + "," + dates;

                        up_date = up_date + "or date='" + date + "'";
                        // Toast.makeText(MainActivity.this, "dates"+dates, Toast.LENGTH_SHORT).show();

                        Cursor r = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='d'", null);
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

                        Cursor d = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='r'", null);
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

                        Cursor s = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='s'", null);
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
                // Toast.makeText(MainActivity.this, ""+up_date, Toast.LENGTH_SHORT).show();
                System.out.println("date==========" + up_date);


                map.put("email",email);
                map.put("androidid",android_id);
                map.put("registrationid",reg_id);
                map.put("mobileno",mobileno);

                map.put("date",dates);
                map.put("dgame1",dgame1s);
                map.put("dgame2",dgame2s);
                map.put("dgame3",dgame3s);
                map.put("dgame4",dgame4s);
                map.put("dplaytime",dplaytimes);
                map.put("dscore",dscores);

                map.put("rgame1",rgame1s);
                map.put("rgame2",rgame2s);
                map.put("rgame3",rgame3s);
                map.put("rgame4",rgame4s);
                map.put("rplaytime",rplaytimes);
                map.put("rscore",rscores);

                map.put("share",share_counts);


                Call<List<HashMap<String,String>>> call = api.getGameStates_userstates_senddata(map);
                System.out.println("date###==========" + up_date);

                String finalUp_date = up_date;
                call.enqueue(new Callback<List<HashMap<String,String>>>() {

                    @Override
                    public void onResponse(Call<List<HashMap<String,String>>> call, Response<List<HashMap<String,String>>> response) {
                        if (response.isSuccessful()) {


                    /*String date = sps.getString(New_Main_Activity.this, "date");
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    String line = "";
                    while ((line = rd.readLine()) != null) Log.e("HttpResponse", line);*/

                            Gson gson= new Gson();
                            String result =gson.toJson(response.body());

                            System.out.print("Result============123" + result);

                            try {
                                JSONArray jArray = new JSONArray(result);
                                System.err.println("#######result===" + result);
                                System.out.println("#######===  " + jArray.length());
                                JSONObject json_data;
                                //isvalid=""+jArray.length();
                                downok = "" + jArray.length();
                                System.out.print("########insert ord ============" + downok);
                                if (jArray.length() > 0) {
                                    json_data = jArray.getJSONObject(0);
                                    for (int k = 0; k < jArray.length(); k++) {

                                        String results = json_data.getString("Status");
                                        System.out.println("=============Status" + results);
                                        if (results.equals("success")) {
                                            exdb.execSQL("UPDATE userdata_r SET isfinish=1 WHERE '" + finalUp_date + "'");
                                            System.out.println("==========Status ok");
                                            System.out.println("===========update_success");
                                            System.out.print("Insert for=======" + finalUp_date);
                                        }

                                        json_data = jArray.getJSONObject(k);

                                    }
                                }
                            } catch (JSONException e1) {
                            }

                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<HashMap<String,String>>> call, Throwable t) {
                        // Handle network failures
                    }
                });



            }
        }


    }


 /*   public void userstates_sendnew() {
        if (sps.getString(Game_States.this, "complite_reg").equals("yes")) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            String date;
            String mobileno = null;
            String reg_id = null, email = null, android_id = null;
            String dgame1 = null, dgame2 = null, dgame3 = null, dgame4 = null, dscore = null, dplaytime = null;
            String rgame1, rgame2, rgame3, rgame4, rscore, rplaytime;
            String share_count;


            String dates = "";
            String dgame1s = "", dgame2s = "", dgame3s = "", dgame4s = "", dscores = "", dplaytimes = "";
            String rgame1s = "", rgame2s = "", rgame3s = "", rgame4s = "", rscores = "", rplaytimes = "";
            String share_counts = "";
            String up_date = "";

            Cursor sc3 = exdb.rawQuery("select * from userdetail", null);
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


            Cursor sc2 = exdb.rawQuery("select distinct (date) from userdata_r where isfinish=0 and date<'" + std + "' ", null);

            if (sc2.getCount() != 0) {
                for (int i = 0; i < sc2.getCount(); i++) {
                    sc2.moveToPosition(i);
                    if (sc2.getCount() != 0) {
                        date = sc2.getString(sc2.getColumnIndexOrThrow("date"));
                        dates = date + "," + dates;

                        up_date = up_date + "or date='" + date + "'";
                        // Toast.makeText(MainActivity.this, "dates"+dates, Toast.LENGTH_SHORT).show();

                        Cursor r = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='d'", null);
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

                        Cursor d = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='r'", null);
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

                        Cursor s = exdb.rawQuery("select * from userdata_r where date ='" + date + "' and type='s'", null);
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
                // Toast.makeText(MainActivity.this, ""+up_date, Toast.LENGTH_SHORT).show();
                System.out.println("date==========" + up_date);


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
                StringBuilder sb;
                System.out.println("date###==========" + up_date);
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/userstatus_prize.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in https connection" + e.toString());
                }
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();

                    System.out.print("ord Result============" + result);


                } catch (Exception e) {
                }

                try {
                    JSONArray jArray = new JSONArray(result);
                    System.err.println("#######result===" + result);
                    System.out.println("#######===  " + jArray.length());
                    JSONObject json_data;
                    //isvalid=""+jArray.length();
                    downok = "" + jArray.length();
                    System.out.print("########insert ord ============" + downok);
                    if (jArray.length() > 0) {
                        json_data = jArray.getJSONObject(0);
                        for (int k = 0; k < jArray.length(); k++) {

                            String results = json_data.getString("Status");
                            System.out.println("=============Status" + results);
                            if (results.equals("success")) {
                                exdb.execSQL("UPDATE userdata_r SET isfinish=1 WHERE " + up_date + "");
                                System.out.println("==========Status ok");
                                System.out.println("===========update_success");
                                System.out.print("Insert for=======" + up_date);
                            }

                            json_data = jArray.getJSONObject(k);

                        }
                    }
                } catch (JSONException e1) {
                }

                catch (android.net.ParseException e1) {
                }

            }
        }

    }*/
}
