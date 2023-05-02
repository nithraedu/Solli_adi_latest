package nithra.tamil.word.game.solliadi.Price_solli_adi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.price_url;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import nithra.tamil.word.game.solliadi.LoginActivity;
import nithra.tamil.word.game.solliadi.New_Main_Activity;
import nithra.tamil.word.game.solliadi.Newgame_DataBaseHelper;
import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;
import nithra.tamil.word.game.solliadi.Utils;

public class Game_Status extends AppCompatActivity {
    static final SharedPreference sp = new SharedPreference();
    static String price_date = "";
    static String price_date_d = "";
    static String price_month_date = "";
    static String price_year_date = "";
    static String prize_u_id = "";
    static int score_ed = 0;
    static int old_score_ed = 0;
    static String pos = "";
    static String price_pre_month_date = "";
    static String price_date_dm = "";
    TextView back;
    TextView your_pos;
    WebView loading_data;
    String urls_status, pre_month_url, pre_month_my_url, winner_url;
    TextView retry;
    LinearLayout adds;
    TextView intros, your_score;
    TextView current_month_status, previus_month_status, previus_month_my_status, date_show;
    String month, year, score;
    String show_txt = "";
    TextView name_txt, winner_list;
    TextView u_nilai, u_pulli;
    LinearLayout pf_edit;
    String u_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__status);

        Newgame_DataBaseHelper myDbHelper = new Newgame_DataBaseHelper(Game_Status.this);
        myDbHelper.executeSql("create table if not exists prize_data(id INTEGER PRIMARY KEY AUTOINCREMENT,date varchar,score integer,isfinish integer DEFAULT 0);");
        back = (TextView) findViewById(R.id.back);
        intros = (TextView) findViewById(R.id.intros);
        your_pos = (TextView) findViewById(R.id.your_pos);
        retry = (TextView) findViewById(R.id.retry);
        your_score = (TextView) findViewById(R.id.your_score);
        date_show = (TextView) findViewById(R.id.date_show);
        name_txt = (TextView) findViewById(R.id.name_txt);
        winner_list = (TextView) findViewById(R.id.winner_list);

        u_nilai = (TextView) findViewById(R.id.u_nilai);
        u_pulli = (TextView) findViewById(R.id.u_pulli);
        pf_edit = (LinearLayout) findViewById(R.id.pf_edit);
        // loading_data=(WebView)findViewById(R.id.loading_data);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        final String urls = sp.getString(Game_Status.this, "p_user_photo");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading);
        requestOptions.error(R.drawable.price_pf);
        current_month_status = (TextView) findViewById(R.id.current_month_status);
        previus_month_status = (TextView) findViewById(R.id.previus_month_status);
        previus_month_my_status = (TextView) findViewById(R.id.previus_month_my_status);


        intros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    popupdialog();
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        current_month_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_txt = "நடப்பு மாத நிலை மற்றும் புள்ளி பட்டியல்";
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    loadstatus_url(urls_status);
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        previus_month_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_txt = "கடந்த மாத நிலை மற்றும் புள்ளி பட்டியல்";
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    loadstatus_url(pre_month_url);
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        previus_month_my_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_txt = "கடந்த மாத உங்கள் நிலை மற்றும் புள்ளி பட்டியல்";
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    loadstatus_url(pre_month_my_url);
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        winner_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_txt = "வெற்றியாளர்கள்";
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    loadstatus_url(winner_url);
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });

        send_prize_data(Game_Status.this);

        pf_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    finish();
                    Intent i = new Intent(Game_Status.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }

            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    send_prize_data(Game_Status.this);
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.putString(Game_Status.this, "game_area", "on");
                if (sp.getString(Game_Status.this, "sd_prize_st").equals("yes")) {
                    sp.putString(Game_Status.this, "sd_prize_st", "");
                    System.out.println("###########DDDDDDD 1");
                    finish();
                    Intent i = new Intent(Game_Status.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    String date = sp.getString(Game_Status.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            System.out.println("###########DDDDDDD 2");
                            finish();
                            Intent i = new Intent(Game_Status.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            System.out.println("###########DDDDDDD 3");
                            finish();
                        }
                    } else {
                        if (main_act.equals("")) {
                            System.out.println("###########DDDDDDD 2");
                            finish();
                            Intent i = new Intent(Game_Status.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            System.out.println("###########DDDDDDD 3");
                            finish();
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        sp.putString(Game_Status.this, "game_area", "on");
        if (sp.getString(Game_Status.this, "sd_prize_st").equals("yes")) {
            sp.putString(Game_Status.this, "sd_prize_st", "");
            finish();
            Intent i = new Intent(Game_Status.this, New_Main_Activity.class);
            startActivity(i);
        } else {
            String date = sp.getString(Game_Status.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    finish();
                    Intent i = new Intent(Game_Status.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    finish();
                }
            } else {
                if (main_act.equals("")) {
                    System.out.println("###########DDDDDDD 2");
                    finish();
                    Intent i = new Intent(Game_Status.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    System.out.println("###########DDDDDDD 3");
                    finish();
                }
            }
        }


    }

    public void send_prize_data(final Context context) {

        // loading_data.setVisibility(View.VISIBLE);
        retry.setVisibility(View.INVISIBLE);
        Utils.mProgress(Game_Status.this, " தரவுகளை ஏற்றுகிறது. காத்திருக்கவும்.....", false).show();

        Calendar calendar3 = Calendar.getInstance();
        price_year_date = String.valueOf(calendar3.get(Calendar.YEAR));
        int cur_month1 = calendar3.get(Calendar.MONTH);
        int cur_day1 = calendar3.get(Calendar.DAY_OF_MONTH);

        price_month_date = "" + (cur_month1 + 1);
        if (price_month_date.length() == 1) {
            price_month_date = "0" + price_month_date;
        }


        price_date_d = price_month_date + "-" + price_year_date;
        System.out.println("#################PS_date" + price_date_d);

        price_pre_month_date = "" + (cur_month1);
        if (price_pre_month_date.length() == 1) {
            price_pre_month_date = "0" + price_pre_month_date;
        }
        price_date_dm = price_pre_month_date + "-" + price_year_date;
        System.out.println("#################PSV_date" + price_date_dm);

        Newgame_DataBaseHelper myDbHelper = new Newgame_DataBaseHelper(Game_Status.this);

        Cursor ol = myDbHelper.getQry("select * from prize_data where date ='" + price_date_dm + "'");
        ol.moveToFirst();
        System.out.println("#################PS_count" + ol.getCount());
        if (ol.getCount() != 0) {
            old_score_ed = ol.getInt(ol.getColumnIndexOrThrow("score"));
        }

        Cursor up = myDbHelper.getQry("select * from prize_data where date ='" + price_date_d + "'");
        up.moveToFirst();
        System.out.println("#################PS_count" + up.getCount());
        if (up.getCount() != 0) {
            score_ed = up.getInt(up.getColumnIndexOrThrow("score"));
        }
        prize_u_id = sp.getString(Game_Status.this, "p_user_id");
        System.out.println("#################PS" + score_ed);
        System.out.println("#################PS_id" + prize_u_id);
        // Showing progress dialog at user registration time.
        // Creating string request with post method.

        StringRequest stringRequest = new StringRequest(Request.Method.POST, price_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        JSONArray jArray, jArray2;
                        JSONObject json_data;
                        JSONObject json_data2;
                        String data = "";
                        try {
                            jArray = new JSONArray(ServerResponse);
                            json_data = jArray.getJSONObject(0);
                            if (json_data.getString("status").equals("success")) {
                                for (int i = 0; i < jArray.length(); i++) {
                                    urls_status = json_data.getString("web");
                                    pre_month_url = json_data.getString("preweb");
                                    pre_month_my_url = json_data.getString("pre_month_status");
                                    winner_url = json_data.getString("winner");
                                    String result = json_data.getString("result");
                                    System.out.println("##############result###########" + result);
                                    System.out.println("##############winner_url###########" + winner_url);
                                    jArray2 = new JSONArray(result);
                                    json_data2 = jArray2.getJSONObject(0);
                                    for (int j = 0; j < jArray2.length(); j++) {
                                        pos = json_data2.getString("position");
                                        month = json_data2.getString("month");
                                        year = json_data2.getString("year");
                                        score = json_data2.getString("score");
                                        System.out.println("##############pos###########" + pos);
                                        u_status = json_data2.getString("userstatus");
                                        sp.putString(Game_Status.this, "u_active_check", json_data2.getString("userstatus"));

                                    }
                                    //loading_data.loadUrl(urls_status);
                                    Utils.mProgress.dismiss();

                                }

                                if (u_status.equals("inactive")) {
                                    user_inactive_dia("inactive");
                                } else {
                                    if (month.equals(price_month_date) && year.equals(price_year_date)) {
                                        if (score.equals("0")) {
                                            u_pulli.setText("பரிசுத்திட்ட புள்ளிகள்:");
                                            your_score.setText(score);
                                            u_nilai.setText("உங்கள் நிலை:");
                                            u_nilai.setVisibility(View.INVISIBLE);
                                            your_pos.setVisibility(View.INVISIBLE);
                                            date_show.setText("பரிசுத்திட்ட மாதம்: " + month + " / " + year);
                                            name_txt.setText("" + sp.getString(Game_Status.this, "p_user_name"));
                                        } else {
                                            u_nilai.setText("உங்கள் நிலை:");
                                            u_pulli.setText("பரிசுத்திட்ட புள்ளிகள்:");
                                            your_pos.setText("" + pos);
                                            date_show.setText("பரிசுத்திட்ட மாதம்: " + month + " / " + year);
                                            your_score.setText(score);
                                            name_txt.setText("" + sp.getString(Game_Status.this, "p_user_name"));
                                        }
                                    } else {
                                        user_inactive_dia("wrong_date");
                                    }

                                }

                                System.out.println("##############urls###########" + urls_status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Hiding the progress dialog after all task complete.
                        // Showing response message coming from server.
                        System.out.println("Game Status ServerResponse" + ServerResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Hiding the progress dialog after all task complete.
                        // Showing error message if something goes wrong.
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            //This indicates that the reuest has either time out or there is no connection
                            Utils.mProgress.dismiss();
                            retry.setVisibility(View.VISIBLE);
                        } else if (error instanceof AuthFailureError) {
                            //Error indicating that there was an Authentication Failure while performing the request
                            Utils.mProgress.dismiss();
                            retry.setVisibility(View.VISIBLE);
                        } else if (error instanceof ServerError) {
                            //Indicates that the server responded with a error response
                            Utils.mProgress.dismiss();
                            retry.setVisibility(View.VISIBLE);
                        } else if (error instanceof NetworkError) {
                            //Indicates that there was network error while performing the request
                            Utils.mProgress.dismiss();
                            retry.setVisibility(View.VISIBLE);
                        } else if (error instanceof ParseError) {
                            // Indicates that the server response could not be parsed
                            //  Toast.makeText(Game_Status.this, "ParseError", Toast.LENGTH_LONG).show();
                            Utils.mProgress.dismiss();
                            //  loading_data.setVisibility(View.INVISIBLE);
                            retry.setVisibility(View.VISIBLE);
                        }
                        String data = error.toString();
                        if (data.equals("com.android.volley.NoConnectionError: javax.net.ssl.SSLHandshakeException: Chain validation failed")) {
                            retry.setVisibility(View.GONE);
                            user_inactive_dia("wrong_date");
                        }
                        //Toast.makeText(Game_Status.this, ""+error.toString(), Toast.LENGTH_LONG).show();
                        System.out.println("###############errors" + error);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                params.put("mode", "GameStatus");
                params.put("user_id", prize_u_id);
                params.put("month", price_month_date);
                params.put("year", price_year_date);
                params.put("score", String.valueOf(score_ed));
                params.put("pre_month", String.valueOf(price_pre_month_date));
                params.put("pre_year", String.valueOf(price_year_date));
                params.put("old_score", String.valueOf(old_score_ed));
                System.out.println("####Tuser_id" + prize_u_id);
                System.out.println("####Tmonth" + price_month_date);
                System.out.println("####Tyear" + price_year_date);
                System.out.println("####Tscore" + score_ed);
                return params;
            }

        };
        // Creating RequestQueue.

        RequestQueue requestQueue = Volley.newRequestQueue(Game_Status.this);
        // Adding the StringRequest object into requestQueue.

        requestQueue.add(stringRequest);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sp.getInt(Game_Status.this, "purchase_ads") == 1) {
            System.out.println("@@@@@@@@@@@@@@@@@@---Ads purchase done");
            adds.setVisibility(View.GONE);
        } else {
        }
    }

    public void loadstatus_url(String urls) {
        final Dialog openDialog = new Dialog(Game_Status.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.prize_result_show);
        WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
        TextView close = (TextView) openDialog.findViewById(R.id.close);
        TextView done_exit = (TextView) openDialog.findViewById(R.id.done_exit);
        done_exit.setVisibility(View.GONE);
        TextView heading_txt = (TextView) openDialog.findViewById(R.id.heading_txt);
        final LinearLayout ads_lay = (LinearLayout) openDialog.findViewById(R.id.ads_lay);
        intros.getSettings().setJavaScriptEnabled(true);
        intros.setWebViewClient(new WebViewClient());
        WebSettings webSettings = intros.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        intros.getSettings().setJavaScriptEnabled(true);
        intros.getSettings().setDomStorageEnabled(true);


        heading_txt.setText(show_txt);
        intros.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog mProgress;

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (mProgress == null) {
                    mProgress = new ProgressDialog(Game_Status.this);
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
        intros.loadUrl(urls);


        if (!isFinishing())openDialog.show();
    }

    public void popupdialog() {
        final Dialog openDialog = new Dialog(Game_Status.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog.setContentView(R.layout.rule_type_dialog);
        TextView game_rule = (TextView) openDialog.findViewById(R.id.game_rule);
        TextView prize_rule = (TextView) openDialog.findViewById(R.id.prize_rule);
        TextView close = (TextView) openDialog.findViewById(R.id.close);
        game_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    rule_regulation("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/Prize/Rules_Regulations.html");

                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }
            }
        });
        prize_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    rule_regulation("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/Prize/Solliadi_Rules_Regulations.html");
                } else {
                    Toast.makeText(Game_Status.this, "இணையதள சேவையை சரிபார்க்கவும்", Toast.LENGTH_SHORT).show();
                }

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog.dismiss();
            }
        });
        if (!isFinishing())openDialog.show();
    }

    public void rule_regulation(String urls) {
        final Dialog openDialog = new Dialog(Game_Status.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
        intros.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        intros.loadUrl(urls);
        if (!isFinishing())openDialog.show();
    }

    public void user_inactive_dia(String inactive) {
        final Dialog openDialog = new Dialog(Game_Status.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
                Intent i = new Intent(Game_Status.this, New_Main_Activity.class);
                startActivity(i);
                openDialog.dismiss();
            }
        });
        if (!isFinishing())openDialog.show();
    }

}
