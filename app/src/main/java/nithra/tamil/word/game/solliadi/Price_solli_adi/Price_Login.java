package nithra.tamil.word.game.solliadi.Price_solli_adi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.price_url;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import nithra.tamil.word.game.solliadi.LoginActivity;
import nithra.tamil.word.game.solliadi.Main_policy;
import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;

public class Price_Login extends AppCompatActivity {
    final SharedPreference sp = new SharedPreference();
    EditText editTextone, editTexttwo, editTextthree, editTextfour;
    TextView submit, time_limit, resend_otp, otp_txt;
    ProgressBar progress_bar;
    MyCountDownTimer myCountDownTimer;
    AppCompatEditText ph_nos;
    ProgressDialog progressDialog;
    TextView signin;
    String otp = "";
    String reg_check = "";
    TextView ed_phno, intros;
    String ph_no_st = "";
    TextView pry_intros, and_sy;
    TextView ph_ed_ic;
    String u_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_price__login);
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, callback);
        //otp_screen();
        System.out.println("###########################onCreate");
        if (sp.getString(Price_Login.this, "prize_phno").equals("")) {
            if (sp.getString(Price_Login.this, "regiustration_dialog").equals("")) {
                rule_regulation("file:///android_asset/before_registration.html");
                System.out.println("###########################rule_regulation");
            } else {
                System.out.println("###########################login_screen");
                login_screen();
            }
        } else {
            System.out.println("###########################otp_screen");
            otp_screen();
        }
    }

    private void login_screen() {
        setContentView(R.layout.log_in_lay);
        ph_nos = (AppCompatEditText) findViewById(R.id.phone_no);
        signin = (TextView) findViewById(R.id.signin);
        intros = (TextView) findViewById(R.id.intros);
        pry_intros = (TextView) findViewById(R.id.pry_intros);
        and_sy = (TextView) findViewById(R.id.and_sy);
        ph_nos.setText("" + sp.getString(Price_Login.this, "prize_phno"));
        and_sy.setText("&");
        pry_intros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    // finish();
                    Intent i = new Intent(Price_Login.this, Main_policy.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Price_Login.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    if (ph_nos.getText().toString().length() != 0) {
                        if (ph_nos.getText().toString().length() == 10) {
                            verify_phno(ph_nos.getText().toString());
                            ph_no_st = ph_nos.getText().toString();
                            sp.putString(Price_Login.this, "prize_phno", ph_nos.getText().toString());
                        } else {
                            Toast.makeText(Price_Login.this, "உங்கள் தொலைபேசி எண்ணை சரிபார்க்கவும்.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Price_Login.this, "உங்கள் தொலைபேசி எண்ணை உள்ளீடு செய்யவும்.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Price_Login.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        intros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    final Dialog openDialog = new Dialog(Price_Login.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog.setContentView(R.layout.introsdialog_web);
                    WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
                    TextView close = (TextView) openDialog.findViewById(R.id.close);
                    TextView done_exit = (TextView) openDialog.findViewById(R.id.done_exit);
                    intros.setWebChromeClient(new WebChromeClient() {
                        private ProgressDialog mProgress;

                        @Override
                        public void onProgressChanged(WebView view, int progress) {
                            if (mProgress == null) {
                                mProgress = new ProgressDialog(Price_Login.this);
                                mProgress.show();
                            }
                            mProgress.setMessage("காத்திருக்கவும் " + progress + "%");
                            if (progress == 100) {
                                mProgress.dismiss();
                                mProgress = null;
                            }
                        }
                    });
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
                    intros.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                    intros.loadUrl("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/Prize/Rules_Regulations.html");
                    if (!isFinishing())openDialog.show();
                } else {
                    Toast.makeText(Price_Login.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verify_phno(final String ph_no) {
        // Showing progress dialog at user registration time.
        progressDialog = new ProgressDialog(Price_Login.this);
        progressDialog.setMessage("காத்திருக்கவும்....");
        progressDialog.show();
        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, price_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        JSONArray jArray;
                        JSONObject json_data = null;
                        String data = "";


                        try {
                            jArray = new JSONArray(ServerResponse);
                            json_data = jArray.getJSONObject(0);
                            if (json_data.getString("status").equals("new")) {
                                otp_screen();
                                reg_check = "new";
                            } else if (json_data.getString("status").equals("exist")) {
                                reg_check = "exist";
                                for (int i = 0; i < jArray.length(); i++) {
                                    sp.putString(Price_Login.this, "p_user_id", json_data.getString("user_id"));
                                    sp.putString(Price_Login.this, "p_user_name", json_data.getString("name"));
                                    sp.putString(Price_Login.this, "p_user_email", json_data.getString("email"));
                                    sp.putString(Price_Login.this, "p_user_mobile", json_data.getString("mobile"));
                                    sp.putString(Price_Login.this, "p_user_photo", json_data.getString("photo"));
                                    sp.putString(Price_Login.this, "p_user_place", json_data.getString("place"));
                                    sp.putString(Price_Login.this, "p_user_district", json_data.getString("district"));
                                    sp.putString(Price_Login.this, "p_user_reg_check", reg_check);

                                    sp.putString(Price_Login.this, "u_active_check", json_data.getString("userstatus"));
                                    u_status = json_data.getString("userstatus");
                                }

                                if (u_status.equals("inactive")) {
                                    user_inactive_dia();
                                    sp.putString(Price_Login.this, "p_user_id", "");
                                    sp.putString(Price_Login.this, "p_user_name", "");
                                    sp.putString(Price_Login.this, "p_user_email", "");
                                    sp.putString(Price_Login.this, "p_user_mobile", "");
                                    sp.putString(Price_Login.this, "p_user_photo", "");
                                    sp.putString(Price_Login.this, "p_user_place", "");
                                    sp.putString(Price_Login.this, "p_user_district", "");
                                    sp.putString(Price_Login.this, "p_user_reg_check", "");
                                    sp.putString(Price_Login.this, "prize_phno", "");
                                } else {
                                    otp_screen();
                                }

                            }
                            otp = json_data.getString("otp");
                            System.out.println("###########################PSuser_idjson" + json_data.getString("user_id"));
                            System.out.println("###########################PSuser_id" + sp.getString(Price_Login.this, "p_user_id"));
                            sp.putString(Price_Login.this, "prize_otp", otp);
                            System.out.println("#############################otp" + otp);
                            System.out.println("#############################reg_check" + reg_check);
                            //  Toast.makeText(Price_Login.this, "" + otp + "" + reg_check, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Hiding the progress dialog after all task complete.
                        // Showing response message coming from server.
                        System.out.println("ServerResponse" + ServerResponse);

                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        // Showing error message if something goes wrong.
                        // Toast.makeText(Price_Login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                        String data = volleyError.toString();
                        if (data.equals("com.android.volley.NoConnectionError: javax.net.ssl.SSLHandshakeException: Chain validation failed")) {
                            sp.putString(Price_Login.this, "prize_phno", "");
                            user_inactive_dia2("wrong_date");
                        }

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                params.put("mode", "login");
                params.put("mobile", ph_no);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(Price_Login.this);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

    public void otp_screen() {

        setContentView(R.layout.otp_view);
        editTextone = (EditText) findViewById(R.id.editTextone);
        editTexttwo = (EditText) findViewById(R.id.editTexttwo);
        editTextthree = (EditText) findViewById(R.id.editTextthree);
        editTextfour = (EditText) findViewById(R.id.editTextfour);
        submit = (TextView) findViewById(R.id.submits);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        time_limit = (TextView) findViewById(R.id.time_limit);
        resend_otp = (TextView) findViewById(R.id.resend_otp);
        ed_phno = (TextView) findViewById(R.id.ed_phno);
        ph_ed_ic = (TextView) findViewById(R.id.ph_ed_ic);
        otp_txt = (TextView) findViewById(R.id.otp_txt);
        otp_txt.setText("நீங்கள் பதிவு செய்த " + sp.getString(Price_Login.this, "ph_no") + "கைப்பேசி எண்ணிற்கு அனுப்பப்பட்டுள்ள நான்கு இலக்க OTP எண்- ஐ உள்ளீடு செய்யவும்.");
        time_limit.setVisibility(View.VISIBLE);
        progress_bar.setVisibility(View.VISIBLE);
        long minutes = 120000;
        myCountDownTimer = new MyCountDownTimer(minutes, 1000);
        myCountDownTimer.cancel();
        progress_bar.setProgress(0);
        progress_bar.setMax(120000);
        myCountDownTimer.start();


        ed_phno.setText("" + sp.getString(Price_Login.this, "prize_phno"));
        editTextone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editTextone.length() == 1) {
                    editTexttwo.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTexttwo, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
        editTexttwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editTexttwo.length() == 1) {
                    editTextthree.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextthree, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    editTextone.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextone, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
        editTextthree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editTextthree.length() == 1) {
                    editTextfour.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextfour, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    editTexttwo.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTexttwo, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
        editTextfour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextfour.length() == 0) {
                    editTextthree.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextthree, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
        editTextfour.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if (editTextfour.length() == 0) {
                        editTextthree.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editTextthree, InputMethodManager.SHOW_IMPLICIT);
                    }
                }
                return false;
            }
        });
        editTextthree.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if (editTextthree.length() == 0) {
                        editTexttwo.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editTexttwo, InputMethodManager.SHOW_IMPLICIT);
                    }
                }
                return false;
            }
        });
        editTexttwo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if (editTexttwo.length() == 0) {
                        editTextone.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(editTextone, InputMethodManager.SHOW_IMPLICIT);
                    }
                }
                return false;
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    String otp1 = editTextone.getText().toString();
                    String otp2 = editTexttwo.getText().toString();
                    String otp3 = editTextthree.getText().toString();
                    String otp4 = editTextfour.getText().toString();
                    String all_otp = otp1 + otp2 + otp3 + otp4;
                    if (all_otp.length() != 4) {
                        Toast.makeText(Price_Login.this, "OTP-ஐ உள்ளீடு செய்யவும். ", Toast.LENGTH_SHORT).show();
                    } else {
                        String otp = otp1 + "" + otp2 + "" + otp3 + "" + otp4;
                        if (otp.equals(sp.getString(Price_Login.this, "prize_otp"))) {
                            sp.putString(Price_Login.this, "otp_verify", "yes");
                            if (sp.getString(Price_Login.this, "p_user_reg_check").equals("exist")) {
                                sp.putString(Price_Login.this, "price_registration", "com");
                            }
                            if (sp.getString(Price_Login.this, "price_registration").equals("com")) {
                                finish();
                                Intent i = new Intent(Price_Login.this, Game_Status.class);
                                startActivity(i);
                            } else {
                                finish();
                                Intent i = new Intent(Price_Login.this, LoginActivity.class);
                                startActivity(i);
                            }


                        } else {
                            Toast.makeText(Price_Login.this, "சரியான OTP எண்ணை உள்ளியீடு செய்யவும்.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Price_Login.this, "உங்கள் இணைய இணைப்பை சரிபார்க்கவும்.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    verify_phno(sp.getString(Price_Login.this, "prize_phno"));
                    Toast.makeText(Price_Login.this, "மீண்டும் OTP பெற அழைப்பு அனுப்பப்பட்டது.", Toast.LENGTH_SHORT).show();

                    // Toast.makeText(Price_Login.this, "உங்கள் இணைய இணைப்பை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Price_Login.this, "உங்கள் இணைய இணைப்பை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ph_ed_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer.cancel();
                progress_bar.clearAnimation();
                myCountDownTimer.onFinish();
                time_limit.setVisibility(View.GONE);
                progress_bar.setVisibility(View.GONE);
                login_screen();
            }
        });
        ed_phno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCountDownTimer.cancel();
                progress_bar.clearAnimation();
                myCountDownTimer.onFinish();
                time_limit.setVisibility(View.GONE);
                progress_bar.setVisibility(View.GONE);
                login_screen();
            }
        });


    }
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            sp.putString(Price_Login.this, "game_area", "on");
            if (sp.getString(Price_Login.this, "sd_prize_st").equals("yes")) {
                sp.putString(Price_Login.this, "sd_prize_st", "");
                System.out.println("######################## D1");
                finish();
                Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                startActivity(i);
            } else {

                String date = sp.getString(Price_Login.this, "date");
                if (date.equals("0")) {
                    if (main_act.equals("")) {
                        System.out.println("######################## D2");
                        finish();
                        Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        System.out.println("######################## D3");
                        finish();
                    }

                } else {
                    System.out.println("######################## D4");
                    if (main_act.equals("")) {
                        System.out.println("######################## D2");
                        finish();
                        Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                        startActivity(i);
                    } else {
                        System.out.println("######################## D3");
                        finish();
                    }
                }

            }
            //finish();
        }
    };

    public void rule_regulation(String urls) {
        setContentView(R.layout.introsdialog_web);
        WebView intros = (WebView) findViewById(R.id.web_introscreen);
        TextView close = (TextView) findViewById(R.id.close);
        TextView done_exit = (TextView) findViewById(R.id.done_exit);
        done_exit.setText("பரிசு திட்டத்தில் இணைய");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.putString(Price_Login.this, "game_area", "on");
                if (sp.getString(Price_Login.this, "activity_call").equals("main")) {

                    if (main_act.equals("")) {
                        System.out.println("################### D1");
                        String date = sp.getString(Price_Login.this, "date");
                        if (date.equals("0")) {
                            System.out.println("################### D2");
                            finish();
                            Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                            startActivity(i);

                        } else {
                            System.out.println("################### D3");
                            if (main_act.equals("")) {
                                System.out.println("################### D6");
                                finish();
                                Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                System.out.println("################### D7");
                                finish();
                            }
                        }

                    } else {
                        System.out.println("################### D4");
                        finish();
                    }
                } else {
                    String date = sp.getString(Price_Login.this, "date");
                    if (date.equals("0")) {
                        if (sp.getString(Price_Login.this, "sd_prize_st").equals("yes")) {
                            System.out.println("################### D5");
                            sp.putString(Price_Login.this, "sd_prize_st", "");
                            finish();
                            Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            if (main_act.equals("")) {
                                System.out.println("################### D6");
                                finish();
                                Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                System.out.println("################### D7");
                                finish();
                            }

                        }

                    } else {
                        if (main_act.equals("")) {
                            System.out.println("################### D8");
                            finish();
                            Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            if (sp.getString(Price_Login.this, "sd_prize_st").equals("yes")) {
                                System.out.println("################### D9");
                                sp.putString(Price_Login.this, "sd_prize_st", "");
                                finish();
                                Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                                startActivity(i);
                            } else {
                                System.out.println("################### D10");
                                finish();
                            }

                        }
                    }
                }


            }
        });
        done_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    sp.putString(Price_Login.this, "regiustration_dialog", "on");
                    login_screen();
                } else {
                    Toast.makeText(Price_Login.this, "உங்கள் இணைய இணைப்பை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }


            }
        });
        intros.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        intros.loadUrl(urls);
    }

    public void user_inactive_dia() {
        final Dialog openDialog = new Dialog(Price_Login.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.festival_game_complete_dia);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView coin_txt_content = (TextView) openDialog.findViewById(R.id.coin_txt_content);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        ImageView bonus_coin = (ImageView) openDialog.findViewById(R.id.bonus_coin);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        bonus_coin.setVisibility(View.VISIBLE);
        RelativeLayout head_lay = (RelativeLayout) openDialog.findViewById(R.id.head_lay);

        head_lay.setBackgroundResource(R.color.transparent2);
        coin_txt_content.setText("உங்கள் கணக்கு தற்காலிகமாக முடக்கப்பட்டுள்ளது. மேலும் விவரங்களுக்கு feedback@nithra.mobi என்ற மின்னஞ்சல் முகவரி-யை தொடர்புகொள்ளவும்.");
        bonus_coin.setImageResource(R.drawable.on_no_game);
        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                startActivity(i);
                openDialog.dismiss();
            }
        });
        if (!isFinishing())openDialog.show();
    }

    public void user_inactive_dia2(String inactive) {
        final Dialog openDialog = new Dialog(Price_Login.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.festival_game_complete_dia);
        openDialog.setCancelable(false);
        // TextView b_score = (TextView) openDialog.findViewById(R.id.b_score);
        TextView ok_y = (TextView) openDialog.findViewById(R.id.ok_y);
        TextView coin_txt_content = (TextView) openDialog.findViewById(R.id.coin_txt_content);
        TextView b_scores = (TextView) openDialog.findViewById(R.id.b_scores);
        ImageView bonus_coin = (ImageView) openDialog.findViewById(R.id.bonus_coin);
        RelativeLayout head_lay = (RelativeLayout) openDialog.findViewById(R.id.head_lay);
        // TextView b_close = (TextView) openDialog.findViewById(R.id.b_close);
        bonus_coin.setVisibility(View.VISIBLE);
        head_lay.setBackgroundResource(R.color.transparent2);
        if (inactive.equals("inactive")) {
            coin_txt_content.setText("உங்கள் கணக்கு தற்காலிகமாக முடக்கப்பட்டுள்ளது. மேலும் விவரங்களுக்கு feedback@nithra.mobi என்ற மின்னஞ்சல் முகவரி-யை தொடர்புகொள்ளவும்.");
            bonus_coin.setImageResource(R.drawable.on_no_game);
        } else if (inactive.equals("wrong_date")) {
            coin_txt_content.setText("உங்களது அலைபேசியில் உள்ள தேதியும் இன்றைய தேதியும் மாறி உள்ளது. சரியான தேதியை மாற்றி அமைத்து விட்டு பின் தொடரவும்.");
            bonus_coin.setImageResource(R.drawable.on_date_mismatch);
        }

        ok_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Price_Login.this, New_Main_Activity.class);
                startActivity(i);
                openDialog.dismiss();
            }
        });
        if (!isFinishing())openDialog.show();
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long seconds = millisUntilFinished / 1000;
            int progress = (int) (millisUntilFinished);
            progress_bar.setProgress(progress);
            time_limit.setText(String.format("%02d", seconds / 60)
                    + ":" + String.format("%02d", seconds % 60));
        }

        @Override
        public void onFinish() {
            //time_limit.setText(Html.fromHtml("<U>Resend OTP</U>"));
            progress_bar.setProgress(0);
            time_limit.setVisibility(View.GONE);
            progress_bar.setVisibility(View.GONE);

            resend_otp.setVisibility(View.VISIBLE);
        }

    }

}
