package nithra.tamil.word.game.solliadi;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
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
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Otp_verification extends AppCompatActivity {
    static final SharedPreference sps = new SharedPreference();
    TextView signin, resend;
    EditText otpverify;
    String android_id;
    SQLiteDatabase exdb;
    String isregster, register_id, after_otp, before_time;
    String mobile_noo;
    LinearLayout adds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        exdb.execSQL("create table if not exists userdetail(id integer,name varchar,upic varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");

        sps.putString(Otp_verification.this, "otpis", "on");
        sps.putString(Otp_verification.this, "otpis2", "on");

        signin = (TextView) findViewById(R.id.signin);
        otpverify = (EditText) findViewById(R.id.otpverify);
        resend = (TextView) findViewById(R.id.resend);
        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        adds = (LinearLayout) findViewById(R.id.ads_lay);

        Handler handler8 = new Handler(Looper.myLooper());
        handler8.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Score Adding
                resend.setVisibility(View.VISIBLE);
            }
        }, 80000);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String otp = otpverify.getText().toString();

                final String oo = otpverify.getText().toString();
                System.out.println("==========###" + oo);
                if ((otp.length() == 4)) {
                    Utils.mProgress(Otp_verification.this, "ஒருமுறை கடவுச்சொல்லை சரிபார்க்கிறது........... ", false).show();
                    Utils.mProgress.setCancelable(false);

                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {
                            otp_send(oo, "otp");
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Utils.mProgress.dismiss();

                            if (register_id.equals("0")) {
                                if (after_otp.equals("4")) {

                                } else if (after_otp.equals("2")) {

                                    Toast.makeText(Otp_verification.this, "ஒரு முறை கடவுச்சொல் தவறாக உள்ளது.  தயவுசெய்து சாிபார்க்கவும். ", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                if (after_otp.equals("com")) {

                                    sps.putString(Otp_verification.this, "complite_reg", "yes");
                                    sps.putString(Otp_verification.this, "otpis2", "off");
                                    finish();
                                    Intent i = new Intent(Otp_verification.this, Game_States.class);
                                    startActivity(i);
                                }
                            }


// Toast.makeText(LoginActivity.this, ""+isregster, Toast.LENGTH_SHORT).show();
                        }
                    }.execute();
                } else {
                    Toast.makeText(Otp_verification.this, "ஒரு முறை கடவுச்சொல் தவறாக உள்ளது.  தயவுசெய்து சாிபார்க்கவும். ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resend.setVisibility(View.INVISIBLE);
                availcheck("" + sps.getString(Otp_verification.this, "ph_no"));
            }
        });
    }

    public void otp_send(String otp, String action){
        String email = null;
        email = Utils.android_id(Otp_verification.this);
        RetofitClient retrofit = new RetofitClient();
        Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

        HashMap<String,String> map = new  HashMap<String,String>();
        map.put("otp","" + otp);
        map.put("action", "" + action);
        map.put("email","" + email);
        map.put("mobileno", "" + sps.getString(Otp_verification.this, "ph_no"));
        map.put("androidid", "" + android_id);

        Call<List<HashMap<String,String>>> call = api.getMainlogindata(map);

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
                        JSONArray   jArray = new JSONArray(result);
                        JSONObject json_data = null;
                        for (int i = 0; i < jArray.length(); i++) {
                            json_data = jArray.getJSONObject(i);
                            after_otp = json_data.getString("response");
                            register_id = json_data.getString("registrationid");
                            mobile_noo = json_data.getString("mobileno");
                            System.out.println("====name===" + json_data.getString("name"));

                            ContentValues cv = new ContentValues();
                            cv.put("id", json_data.getString("id"));
                            cv.put("name", json_data.getString("name"));
                            cv.put("email", json_data.getString("email"));
                            cv.put("phno", json_data.getString("mobileno"));

                            cv.put("address", json_data.getString("address"));
                            cv.put("city", json_data.getString("district"));
                            cv.put("regid", json_data.getString("registrationid"));
                            exdb.insert("userdetail", null, cv);

                            //  String isregster2=json_data.getString("email");
                            System.out.println("====////" + after_otp + "  - " + register_id);

                        }
                    } catch (JSONException e1) {
                        System.out.print("Result JSONException ========== " + e1);

                    }

                } else {
                    System.out.print("Result Responce goto else  ========== ");


                }
            }

            @Override
            public void onFailure(Call<List<HashMap<String,String>>> call, Throwable t) {
                System.out.print("Result onFailure  ========== " + call);
                System.out.print("Result onFailure1  ========== " + t);

                // Handle network failures
            }
        });


    }


    public void availcheck(final String phno){
        RetofitClient retrofit = new RetofitClient();
        Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

        HashMap<String,String> map = new  HashMap<String,String>();
        map.put("mobileno","" + phno);
        map.put("action", "first");
        map.put("androidid","" + android_id);

        Call<List<HashMap<String,String>>> call = api.getMainlogindata(map);

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
                        System.err.println("Update===" + result);
                        System.out.println("===  " + jArray.length());
                        JSONObject json_data = null;
                        //isvalid=""+jArray.length();
                        if (jArray.length() > 0) {
                            json_data = jArray.getJSONObject(0);
                            //sps.putString(Main_Login.this,"otp_re","yes");
                            for (int i = 0; i < jArray.length(); i++) {
                                System.out.print("Insert for=======");
                                json_data = jArray.getJSONObject(i);
                                isregster = json_data.getString("response");
                                register_id = json_data.getString("registrationid");


                            }
                        }
                    } catch (JSONException e1) {
                        System.out.print("Result JSONException ========== " +e1);

                    }

                } else {
                    System.out.print("Result Responce goto else  ========== ");
                }
            }

            @Override
            public void onFailure(Call<List<HashMap<String,String>>> call, Throwable t) {
                System.out.print("Result onFailure  ========== " +call );
                System.out.print("Result onFailure1  ========== " +t );
                // Handle network failures
            }
        });


    }

    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            finish();
            Intent i = new Intent(Otp_verification.this, New_Main_Activity.class);
            startActivity(i);
        }
    };

}
