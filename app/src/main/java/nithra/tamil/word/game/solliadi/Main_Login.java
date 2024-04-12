package nithra.tamil.word.game.solliadi;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Login extends AppCompatActivity {
    static final SharedPreference sps = new SharedPreference();
    public static String android_id;
    EditText phno_edit, otps;
    TextView signin, signup;
    String isregster, register_id, after_otp, before_time;
    SQLiteDatabase exdb;
    CountDownTimer countDownTimer;          // built in android class CountDownTimer
    long totalTimeCountInMilliseconds;      // total count down time in milliseconds
    long timeBlinkInMilliseconds;           // start time of start blinking
    boolean blink;
    String mobile_noo;
    LinearLayout adds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__login);


        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        exdb.execSQL("create table if not exists userdetail(id integer,name varchar,upic varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");

        phno_edit = (EditText) findViewById(R.id.phno_edit);
        signin = (TextView) findViewById(R.id.signin);
        signup = (TextView) findViewById(R.id.signup);
        adds = (LinearLayout) findViewById(R.id.ads_lay);


        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);


        if (sps.getString(Main_Login.this, "otpis").equals("on")) {
            finish();
            Intent i = new Intent(Main_Login.this, Otp_verification.class);
            startActivity(i);
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String phno = phno_edit.getText().toString();
                if ((phno.length() != 10)) {
                    Toast.makeText(Main_Login.this, "தயவு செய்து மொபைல் எண்ணை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        Utils.mProgress(Main_Login.this, "தரவுகள் சரிபார்க்கிறது........... ", false).show();
                        Utils.mProgress.setCancelable(false);
                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            protected Void doInBackground(Void... params) {
                                availcheck(phno);

                                System.out.println("====availcheck" + phno);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                Utils.mProgress.dismiss();
                                if (isregster.equals("com")) {
                                    sps.putString(Main_Login.this, "ph_no", "" + phno);
                                    finish();
                                    Intent i = new Intent(Main_Login.this, Otp_verification.class);
                                    startActivity(i);

                                } else if (isregster.equals("not")) {
                                    sps.putString(Main_Login.this, "ph_no", "" + phno);
                                    finish();
                                    Intent i = new Intent(Main_Login.this, Otp_verification.class);
                                    startActivity(i);

                                    // otp_dialog(1, 0, "yes");
                                } else if (isregster.equals("new")) {
                                    sps.putString(Main_Login.this, "ph_noe", "" + phno);
                                    finish();
                                    Intent i = new Intent(Main_Login.this, LoginActivity.class);
                                    startActivity(i);
                                    //  Toast.makeText(Main_Login.this, "உங்கள் எண் பதிவு செய்யப்படவில்லை. பதிவு செய்யவும்.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }.execute();
                    } else {
                        Toast.makeText(Main_Login.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Main_Login.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }


    public void availcheck(final String phno) {
        RetofitClient retrofit = new RetofitClient();
        Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

        HashMap<String,String> map = new  HashMap<String,String>();
        map.put("mobileno", "" + phno);
        map.put("action","first");
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
                        JSONObject json_data;
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



    public void otp_dialog(int s, int r, String time) {
        final Dialog dialog = new Dialog(Main_Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.otp_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // dialog.setCancelable(false);

        final EditText otp = (EditText) dialog.findViewById(R.id.otp);
        final EditText mno = (EditText) dialog.findViewById(R.id.mno);
        final TextView send = (TextView) dialog.findViewById(R.id.send);
        final TextView resend = (TextView) dialog.findViewById(R.id.resend);
        final TextView textViewShowTime = (TextView) dialog.findViewById(R.id.tvTimeCount);
        final TextView txt = (TextView) dialog.findViewById(R.id.txt);
        final ImageView img = (ImageView) dialog.findViewById(R.id.img);
        mno.setText("" + sps.getString(Main_Login.this, "ph_no"));
        if (s == 0) {
            send.setVisibility(View.GONE);
            otp.setVisibility(View.GONE);
            resend.setVisibility(View.VISIBLE);
            mno.setVisibility(View.VISIBLE);
            textViewShowTime.setVisibility(View.GONE);
        } else {
            txt.setText("உங்களது OTP எண் SMS மூலம் அனுப்பப்பட்டது. \nஅந்த எண்னை கீழே உள்ளீடு செய்து அனுப்பவும்.  ");
            img.setImageResource(0);
            img.setImageResource(R.drawable.otpgen);
            send.setVisibility(View.VISIBLE);
            otp.setVisibility(View.VISIBLE);
            textViewShowTime.setVisibility(View.VISIBLE);
            if (time.equals("yes")) {
                totalTimeCountInMilliseconds = Integer.parseInt(before_time) * 1000L;      // time count for 2 minutes = 60 seconds

            } else {
                totalTimeCountInMilliseconds = 300 * 1000;      // time count for 2 minutes = 60 seconds
            }

            timeBlinkInMilliseconds = 30 * 1000;            // blink starts at 1/2 minutes = 30 seconds
            countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
                // 500 means, onTick function will be called at every 500 milliseconds
                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    long seconds = leftTimeInMilliseconds / 1000;

                    if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                        textViewShowTime.setTextColor(Color.RED);
                        // change the style of the textview .. giving a red alert style

                        if (blink) {
                            textViewShowTime.setVisibility(View.VISIBLE);
                            // if blink is true, textview will be visible
                        } else {
                            textViewShowTime.setVisibility(View.INVISIBLE);
                        }

                        blink = !blink;         // toggle the value of blink
                    }

                    textViewShowTime.setText(String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60));
                    // format the textview to show the easily readable format
                }

                @Override
                public void onFinish() {
                    // this function will be called when the timecount is finished
                    textViewShowTime.setText("Time up!");
                    textViewShowTime.setVisibility(View.VISIBLE);
                    resend.setVisibility(View.VISIBLE);
                    mno.setVisibility(View.VISIBLE);
                    txt.setText("நேரம் முடிந்துவிட்டது மீண்டும் புதிய OTP எண்னை பெறுவதற்கு கீழே உள்ள பொத்தானை அனுப்பவும் ");
                    img.setImageResource(0);
                    img.setImageResource(R.drawable.timeup);
                    send.setVisibility(View.GONE);
                    otp.setVisibility(View.GONE);
                    textViewShowTime.setVisibility(View.GONE);
                }

            }.start();
        }
        if (r == 0) {
            resend.setVisibility(View.GONE);
            mno.setVisibility(View.GONE);

        } else {
            resend.setVisibility(View.VISIBLE);
            mno.setVisibility(View.VISIBLE);
            txt.setText("நேரம் முடிந்துவிட்டது மீண்டும் புதிய OTP எண்னை பெறுவதற்கு கீழே உள்ள பொத்தானை அனுப்பவும் ");
            img.setImageResource(0);
            img.setImageResource(R.drawable.timeup);
        }


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String oo = otp.getText().toString();
                //  Toast.makeText(LoginActivity.this, oo+"  -  "+rowid, Toast.LENGTH_SHORT).show();

                if ((oo.length() == 4)) {

                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {
                            otp_send(oo, "otp");
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            if (register_id.equals("0")) {
                                if (after_otp.equals("4")) {
                                    Toast.makeText(Main_Login.this, "Time over", Toast.LENGTH_SHORT).show();
                                    //resend otp gen
                                    // otp_send("", "resend");
                                    countDownTimer.cancel();
                                    resend.setVisibility(View.VISIBLE);
                                    mno.setVisibility(View.VISIBLE);
                                    txt.setText("நேரம் முடிந்துவிட்டது மீண்டும் புதிய OTP எண்னை பெறுவதற்கு கீழே உள்ள பொத்தானை அனுப்பவும் ");
                                    img.setImageResource(0);
                                    img.setImageResource(R.drawable.timeup);
                                    send.setVisibility(View.GONE);
                                    otp.setVisibility(View.GONE);
                                    textViewShowTime.setVisibility(View.GONE);
                                } else if (after_otp.equals("2")) {

                                    Toast.makeText(Main_Login.this, " OTP Wrong,  please type correct OTP number.", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                if (after_otp.equals("com")) {
                                    completd(register_id);
                                    sps.putString(Main_Login.this, "complite_reg", "yes");
                                    countDownTimer.cancel();
                                    dialog.cancel();
                                }
                            }
// Toast.makeText(LoginActivity.this, ""+isregster, Toast.LENGTH_SHORT).show();
                        }
                    }.execute();
                } else {
                    Toast.makeText(Main_Login.this, "Enter 4 digit OTP Number ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile_noo = mno.getText().toString();

                if (mno.getText().toString().length() == 10) {
                    totalTimeCountInMilliseconds = 300 * 1000;      // time count for 2 minutes = 60 seconds
                    timeBlinkInMilliseconds = 30 * 1000;

                    countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
                        // 500 means, onTick function will be called at every 500 milliseconds
                        @Override
                        public void onTick(long leftTimeInMilliseconds) {
                            long seconds = leftTimeInMilliseconds / 1000;

                            if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                                textViewShowTime.setTextColor(Color.RED);
                                // change the style of the textview .. giving a red alert style

                                if (blink) {
                                    textViewShowTime.setVisibility(View.VISIBLE);
                                    // if blink is true, textview will be visible
                                } else {
                                    textViewShowTime.setVisibility(View.INVISIBLE);
                                }

                                blink = !blink;         // toggle the value of blink
                            }

                            textViewShowTime.setText(String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60));
                            // format the textview to show the easily readable format
                        }

                        @Override
                        public void onFinish() {
                            // this function will be called when the timecount is finished
                            textViewShowTime.setText("Time up!");
                            textViewShowTime.setVisibility(View.VISIBLE);
                            resend.setVisibility(View.VISIBLE);
                            mno.setVisibility(View.VISIBLE);
                            txt.setText("நேரம் முடிந்துவிட்டது மீண்டும் புதிய OTP எண்னை பெறுவதற்கு கீழே உள்ள பொத்தானை அனுப்பவும் ");
                            img.setImageResource(0);
                            img.setImageResource(R.drawable.timeup);
                            send.setVisibility(View.GONE);
                            otp.setVisibility(View.GONE);
                            textViewShowTime.setVisibility(View.GONE);
                        }

                    }.start();
                    txt.setText("உங்களது OTP எண் SMS மூலம் அனுப்பப்பட்டது. \nஅந்த எண்னை கீழே உள்ளீடு செய்து அனுப்பவும்.  ");
                    img.setImageResource(0);
                    img.setImageResource(R.drawable.otpgen);
                    send.setVisibility(View.VISIBLE);
                    resend.setVisibility(View.GONE);
                    mno.setVisibility(View.GONE);
                    otp.setVisibility(View.VISIBLE);
                    textViewShowTime.setVisibility(View.VISIBLE);
                    //  Toast.makeText(LoginActivity.this, oo+"  -  "+rowid, Toast.LENGTH_SHORT).show();


                    final String phno = phno_edit.getText().toString();

                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {
                            availcheck(phno);
                            dialog.cancel();

                            System.out.println("====availcheck" + phno);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                            if (isregster.equals("com")) {
                                sps.putString(Main_Login.this, "ph_no", "" + mno.getText().toString());
                                otp_dialog(1, 0, "yes");

                            } else if (isregster.equals("not")) {
                                dialog.dismiss();
                                sps.putString(Main_Login.this, "ph_no", "" + mno.getText().toString());
                                otp_dialog(1, 0, "yes");
                            } else if (isregster.equals("new")) {
                                Toast.makeText(Main_Login.this, "You Are Not Registerd User...Please Sign Up  ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }.execute();

                } else {
                    Toast.makeText(Main_Login.this, "Please Check Your  10 digit Mobile Number .. ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();


    }

    public void otp_send(String otp, String action){
        String email;
        email = Utils.android_id(Main_Login.this);

        RetofitClient retrofit = new RetofitClient();
        Retrofitstart api = retrofit.RetrofitExample().create(Retrofitstart.class);

        HashMap<String,String> map = new  HashMap<String,String>();
        map.put("otp","" + otp);
        map.put("action","" + action);
        map.put("email","" + email);
        map.put("mobileno", "" + sps.getString(Main_Login.this, "ph_no"));
        map.put("androidid","" + android_id);


        Call<List<HashMap<String,String>>> call = api.getMainlogin_otp_senddata(map);

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
                        JSONArray  jArray = new JSONArray(result);
                        JSONObject json_data;
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

   /* public void otp_sendnew(String otp, String action) {
        String email;
        email = Utils.android_id(Main_Login.this);

        JSONArray jArray;
        String result = null;
        InputStream is;
        StringBuilder sb;

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
        if (action.equals("otp")) {
            nameValuePairs.add(new BasicNameValuePair("otp", "" + otp));
        }
        nameValuePairs.add(new BasicNameValuePair("action", "" + action));
        nameValuePairs.add(new BasicNameValuePair("email", "" + email));
        nameValuePairs.add(new BasicNameValuePair("mobileno", "" + sps.getString(Main_Login.this, "ph_no")));
        nameValuePairs.add(new BasicNameValuePair("androidid", "" + android_id));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://nithra.mobi/solliadi/regisrtation.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

            System.out.println("====result**" + result);
        } catch (Exception e) {
        }
        try {
            jArray = new JSONArray(result);
            JSONObject json_data;
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
        } catch (android.net.ParseException e1) {
        }


    }*/

    public void completd(String com) {
        final Dialog dialog = new Dialog(Main_Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.completed);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView reg_id = (TextView) dialog.findViewById(R.id.txt_id);
        TextView complete = (TextView) dialog.findViewById(R.id.com);


        String split = "" + com.charAt(0) + com.charAt(1) + "-" + com.charAt(2) + com.charAt(3) + "-" + com.charAt(4) + com.charAt(5) + "-" + com.charAt(6) + com.charAt(7);
        reg_id.setText("" + split);


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i = new Intent(Main_Login.this, Game_States.class);
                startActivity(i);
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            Intent i = new Intent(Main_Login.this, New_Main_Activity.class);
            startActivity(i);

        }


        return super.onKeyDown(keyCode, event);
    }
}
