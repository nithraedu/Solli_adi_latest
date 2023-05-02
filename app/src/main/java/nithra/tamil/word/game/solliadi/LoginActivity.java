package nithra.tamil.word.game.solliadi;

import static nithra.tamil.word.game.solliadi.New_Main_Activity.main_act;
import static nithra.tamil.word.game.solliadi.Price_solli_adi.Urls.price_url;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;

public class LoginActivity extends AppCompatActivity {

    static final int CAMERA_CAPTURE = 5;
    static final SharedPreference sps = new SharedPreference();
    private static final int RESULT_LOAD_IMG = 1;
    private static final int REQUEST_CROP_ICON = 2;
    public static int MY_EMAIL_REQUEST_WRITE = 128;
    public static String android_id;
    final int PIC_CROP = 3;
    final int PICK_IMAGE_REQUEST = 8;
    final String[] disticts = {"மாவட்டம் ", "அரியலூர்", "சென்னை", "கோயம்புத்தூர்", "கடலூர்", "தர்மபுரி", "திண்டுக்கல்", "ஈரோடு", "காஞ்சிபுரம்", "கன்னியாகுமாரி", "கரூர்", "கிருஷ்ணகிரி", "மதுரை", "நாகப்பட்டினம்", "நாமக்கல்", "நீலகிரி", "பெரம்பலூர்", "புதுக்கோட்டை", "ராமநாதபுரம்", "சேலம்", "சிவகங்கை", "தஞ்சாவூர்", "தேனி", "தூத்துக்குடி", "திருச்சிராப்பள்ளி", "திருநெல்வேலி", "திருப்பூர்", "திருவள்ளூர்", "திருவண்ணாமலை", "திருவாரூர்", "வேலூர்", "விழுப்புரம்", "விருதுநகர்", "புதுச்சேரி", "காரைக்கால்", "யானம்", "மஹி"};
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    AppCompatEditText name, emails, phno, address;
    TextView back, login, pfedit;
    String after_otp, register_id, befour_check, before_time;
    CountDownTimer countDownTimer;          // built in android class CountDownTimer
    long totalTimeCountInMilliseconds;      // total count down time in milliseconds
    long timeBlinkInMilliseconds;           // start time of start blinking
    boolean blink;                          // controls the blinking .. on and off
    String isregster, rowid;
    String mobile_noo;
    Spinner dist;
    String citys;
    SQLiteDatabase exdb;
    CheckBox terms;
    LinearLayout adds;
    //pic picture
    Uri picUri;
    File fil;
    String fi;
    byte[] b = null;
    String temp = "", img_str = "";
    Dialog openDialog1;
    String reg_id;
    String myimg;
    byte[] b2 = null;
    String need_permission = "";
    ProgressDialog progressDialog;
    String path = "";
    File myDir = null;
    int tm_policy = 0;
    LinearLayout upload_ph_lay;
    TextView terms_content;
    String msg = "";
    RelativeLayout city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        progressDialog = new ProgressDialog(LoginActivity.this);

        exdb = this.openOrCreateDatabase("Solli_Adi", MODE_PRIVATE, null);
        exdb.execSQL("create table if not exists userdetail(id integer,name varchar,upic varchar,email varchar,phno integer,address varchar,city varchar,regid varchar);");


        setContentView(R.layout.activity_login);
        name = (AppCompatEditText) findViewById(R.id.name);
        emails = (AppCompatEditText) findViewById(R.id.email);
        phno = (AppCompatEditText) findViewById(R.id.phno);
        address = (AppCompatEditText) findViewById(R.id.address);
        login = (TextView) findViewById(R.id.login);
        login.setEnabled(true);

        pfedit = (TextView) findViewById(R.id.pfedit);
        terms_content = (TextView) findViewById(R.id.terms_content);
        dist = (Spinner) findViewById(R.id.spdist);
        terms = (CheckBox) findViewById(R.id.terms);
        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        adds = (LinearLayout) findViewById(R.id.ads_lay);
        upload_ph_lay = (LinearLayout) findViewById(R.id.upload_ph_lay);
        back = (TextView) findViewById(R.id.back);
        city = (RelativeLayout) findViewById(R.id.city);
        ArrayAdapter dis = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_spinner_item, disticts);
        dis.setDropDownViewResource(R.layout.spinner_list_item);

        msg = "தரவுகள் வெற்றிகரமாக சேமிக்கப்பட்டது";
        dist.setAdapter(dis);
        myDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg");


        phno.setText("" + sps.getString(LoginActivity.this, "prize_phno"));
        phno.setEnabled(false);

        if (sps.getString(LoginActivity.this, "price_registration").equals("com")) {
            // login.setVisibility(View.INVISIBLE);
            login.setText("புதுப்பிக்க");
            msg = "தரவுகள் வெற்றிகரமாக புதுப்பிக்கப்பட்டது.";

            terms.setVisibility(View.INVISIBLE);
            terms_content.setVisibility(View.INVISIBLE);
            name.setText("" + sps.getString(LoginActivity.this, "p_user_name"));
            emails.setText("" + sps.getString(LoginActivity.this, "p_user_email"));
            address.setText("" + sps.getString(LoginActivity.this, "p_user_place"));
            citys = sps.getString(LoginActivity.this, "p_user_district");
            tm_policy = 1;
            selection();
            String urls = sps.getString(LoginActivity.this, "p_user_photo");
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.loading);
            requestOptions.error(R.drawable.pf_profilepic);


        } else {
            if ((ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                //ActivityCompat.requestPermissions(New_Main_Activity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 141);
            } else {
                File fdelete = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg");
                System.out.println("----file fdelete.exists() : " + fdelete.exists());
                if (fdelete.exists()) {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                    Bitmap bmp = BitmapFactory.decodeFile(path);
                    Bitmap bm = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    // user_name.setText("");
                }
            }

        }


        pfedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emails.setEnabled(true);
                name.setEnabled(true);
                address.setEnabled(true);
                dist.setEnabled(true);
                login.setVisibility(View.VISIBLE);
                login.setEnabled(true);
                login.setText("மேம்படுத்து");
            }
        });


        dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                // imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                // imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sps.putString(LoginActivity.this, "game_area", "on");
                if (sps.getString(LoginActivity.this, "price_registration").equals("com")) {
                    finish();
                    Intent i = new Intent(LoginActivity.this, Game_Status.class);
                    startActivity(i);
                } else {
                    String date = sps.getString(LoginActivity.this, "date");
                    if (date.equals("0")) {
                        if (main_act.equals("")) {
                            System.out.println("######################## D2");
                            finish();
                            Intent i = new Intent(LoginActivity.this, New_Main_Activity.class);
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
                            Intent i = new Intent(LoginActivity.this, New_Main_Activity.class);
                            startActivity(i);
                        } else {
                            System.out.println("######################## D3");
                            finish();
                        }
                    }
                }
            }
        });
        upload_ph_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if ((ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        if (sps.getInt(LoginActivity.this, "permission") == 2) {
                            androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this).create();
                            alertDialog.setMessage("உங்கள் புகைப்படத்தை பதிவேற்றம் செய்ய  settings-ல்  உள்ள storage permission-யை allow செய்யவேண்டும்");
                            alertDialog.setCancelable(false);
                            alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, "Settings ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            Intent intent = new Intent();
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                            intent.setData(uri);
                                            getApplicationContext().startActivity(intent);
                                        }
                                    });

                            alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE, "Exit ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.show();
                        } else {
                            androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this).create();
                            alertDialog.setMessage("புகைப்படம் பதிவு செய்ய பின்வரும் permission-யை allow செய்யவேண்டும்");
                            sps.putString(LoginActivity.this, "permission_grand", "yes");
                            alertDialog.setCancelable(false);
                            alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, "OK ",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            if ((ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                                                ActivityCompat.requestPermissions(LoginActivity.this,
                                                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 151);
                                            } else {
                                                set_photo();
                                            }
                                        }
                                    });

                            alertDialog.show();
                        }
                    } else {
                        set_photo();
                    }
                } else {
                    set_photo();
                }
            }
        });

        terms_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    final Dialog openDialog = new Dialog(LoginActivity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    openDialog.setContentView(R.layout.rule);
                    WebView intros = (WebView) openDialog.findViewById(R.id.web_introscreen);
                    TextView ok = (TextView) openDialog.findViewById(R.id.ok);
                    TextView done_exit = (TextView) openDialog.findViewById(R.id.done_exit);
                    intros.loadUrl("https://s3.ap-south-1.amazonaws.com/nithra-solliadi/Prize/Rules_Regulations.html");
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tm_policy = 0;
                            openDialog.dismiss();
                        }
                    });
                    done_exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tm_policy = 1;
                            terms.setChecked(true);
                            openDialog.dismiss();
                        }
                    });
                    if (!isFinishing())openDialog.show();
                } else {
                    Toast.makeText(LoginActivity.this, "இணையதள சேவையை சரிபார்க்கவும் ", Toast.LENGTH_SHORT).show();

                }

            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tm_policy = 1;
                } else {
                    tm_policy = 0;
                }
                // Toast.makeText(LoginActivity.this, "tm_policy"+tm_policy, Toast.LENGTH_SHORT).show();
            }
        });

        if (sps.getString(LoginActivity.this, "logincomplite").equals("yes")) {
            terms.setVisibility(View.INVISIBLE);
            terms_content.setVisibility(View.INVISIBLE);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isNetworkAvailable(getApplicationContext())) {

                    final String name_v = name.getText().toString();
                    final String emails_v = emails.getText().toString();
                    final String phno_v = phno.getText().toString();
                    final String address_v = address.getText().toString();
                    final String dd = dist.getSelectedItem().toString();

                    if ((name_v.length() > 0) && ((emails_v.length() > 0) && (phno_v.length() == 10)) && (!dd.equals("மாவட்டம் ")) && (address_v.length() > 0)) {
                        if (emails_v.matches(emailPattern) && emails_v.length() > 0) {
                            if (tm_policy == 1) {
                                login.setEnabled(false);
                                new AsyncTask<String, String, String>() {
                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        //   Utils.mProgress(New_Main_Activity.this,"முதல் தடவை தரவுகளை ஏற்றுகிறது. சில நிமிடங்கள் வரை ஆகலாம், காத்திருக்கவும்.....",false).show();
                                        progressDialog.setMessage("காத்திருக்கவும்....");
                                        progressDialog.show();
                                    }

                                    @Override
                                    protected String doInBackground(String... params) {
                                        verify_phno(name_v, emails_v, phno_v, address_v, dd);
                                        return null;
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
                                        super.onPostExecute(s);
                                        progressDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                                        sps.putString(LoginActivity.this, "game_area", "on");
                                        finish();
                                        Intent i = new Intent(LoginActivity.this, Game_Status.class);
                                        startActivity(i);
                                    }
                                }.execute();
                            } else {
                                Toast.makeText(LoginActivity.this, "விதிமுறைகள் மற்றும் நிபந்தனைகளை ஏற்கவும்.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "உங்கள் மின்னஞ்சல் முகவரியை சரிபார்க்கவும்.", Toast.LENGTH_SHORT).show();

                        }


                    } else {
                        if ((phno_v.length() != 10)) {
                            Toast.makeText(LoginActivity.this, "தயவு செய்து மொபைல் எண்ணை சரிபார்க்கவும்.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "அனைத்து தகவல்களையும்  பூர்த்தி செய்யவும்.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "இணையதள சேவையை சரிபார்க்கவும்.", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    private void set_photo() {
        openDialog1 = new Dialog(LoginActivity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        openDialog1.setContentView(R.layout.pick_photo);
        TextView camera = (TextView) openDialog1.findViewById(R.id.camera);
        TextView gallery = (TextView) openDialog1.findViewById(R.id.gallery);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";

                File fdelete = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg");

                System.out.println("----file fdelete.exists() : " + fdelete.exists());

                if (fdelete.exists()) {
                    if (fdelete.delete()) {
                        System.out.println("----file Deleted :" + uri);
                    } else {
                        System.out.println("----file not Deleted :" + uri);
                    }
                }

                try {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                    File imageFile = new File(imageFilePath);
                    picUri = Uri.fromFile(imageFile);

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);


                } catch (ActivityNotFoundException anfe) {
                    String errorMessage = "Whoops - your device doesn't support capturing images!";
                    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }


            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";

                File fdelete = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg");

                System.out.println("----file fdelete.exists() : " + fdelete.exists());

                if (fdelete.exists()) {
                    if (fdelete.delete()) {
                        System.out.println("----file Deleted :" + uri);
                    } else {
                        System.out.println("----file not Deleted :" + uri);
                    }
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
        openDialog1.show();
    }


    public void selection() {

        if (citys.equals("அரியலூர்")) {
            dist.setSelection(1);
        } else if (citys.equals("சென்னை")) {
            dist.setSelection(2);
        } else if (citys.equals("கோயம்புத்தூர்")) {
            dist.setSelection(3);
        } else if (citys.equals("கடலூர்")) {
            dist.setSelection(4);
        } else if (citys.equals("தர்மபுரி")) {
            dist.setSelection(5);
        } else if (citys.equals("திண்டுக்கல்")) {
            dist.setSelection(6);
        } else if (citys.equals("ஈரோடு")) {
            dist.setSelection(7);
        } else if (citys.equals("காஞ்சிபுரம்")) {
            dist.setSelection(8);
        } else if (citys.equals("கன்னியாகுமாரி")) {
            dist.setSelection(9);
        } else if (citys.equals("கரூர்")) {
            dist.setSelection(10);
        } else if (citys.equals("கிருஷ்ணகிரி")) {
            dist.setSelection(11);
        } else if (citys.equals("மதுரை")) {
            dist.setSelection(12);
        } else if (citys.equals("நாகப்பட்டினம்")) {
            dist.setSelection(13);
        } else if (citys.equals("நாமக்கல்")) {
            dist.setSelection(14);
        } else if (citys.equals("நீலகிரி")) {
            dist.setSelection(15);
        } else if (citys.equals("பெரம்பலூர்")) {
            dist.setSelection(16);
        } else if (citys.equals("புதுக்கோட்டை")) {
            dist.setSelection(17);
        } else if (citys.equals("ராமநாதபுரம்")) {
            dist.setSelection(18);
        } else if (citys.equals("சேலம்")) {
            dist.setSelection(19);
        } else if (citys.equals("சிவகங்கை")) {
            dist.setSelection(20);
        } else if (citys.equals("தஞ்சாவூர்")) {
            dist.setSelection(21);
        } else if (citys.equals("தேனி")) {
            dist.setSelection(22);
        } else if (citys.equals("தூத்துக்குடி")) {
            dist.setSelection(23);
        } else if (citys.equals("திருச்சிராப்பள்ளி")) {
            dist.setSelection(24);
        } else if (citys.equals("திருநெல்வேலி")) {
            dist.setSelection(25);
        } else if (citys.equals("திருப்பூர்")) {
            dist.setSelection(26);
        } else if (citys.equals("திருவள்ளூர்")) {
            dist.setSelection(27);
        } else if (citys.equals("திருவண்ணாமலை")) {
            dist.setSelection(28);
        } else if (citys.equals("திருவாரூர்")) {
            dist.setSelection(29);
        } else if (citys.equals("வேலூர்")) {
            dist.setSelection(30);
        } else if (citys.equals("விழுப்புரம்")) {
            dist.setSelection(31);
        } else if (citys.equals("விருதுநகர்")) {
            dist.setSelection(32);
        } else if (citys.equals("புதுச்சேரி")) {
            dist.setSelection(33);
        } else if (citys.equals("காரைக்கால்")) {
            dist.setSelection(34);
        } else if (citys.equals("யானம்")) {
            dist.setSelection(35);
        } else if (citys.equals("மஹி")) {
            dist.setSelection(36);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 151) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sps.putInt(LoginActivity.this, "permission", 1);
                set_photo();
            } else if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission

                    boolean showRationale = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    }
                    System.out.println("PERMISSION_DENIED : " + showRationale);

                    if (!showRationale) {
                        sps.putInt(LoginActivity.this, "permission", 2);
                        //  sp.putString(New_Main_Activity.this, "PERMISSION_DENIED", "yes");

                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                        alertDialog.setMessage("உங்கள் புகைப்படத்தை பதிவேற்றம் செய்ய  settings-ல்  உள்ள permission-யை allow செய்யவேண்டும்");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Intent intent = new Intent();
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                        intent.setData(uri);
                                        getApplicationContext().startActivity(intent);
                                    }
                                });

                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.show();

                    }
                }

            }
        }
    }


    private void performCrop() {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 420);
            cropIntent.putExtra("outputY", 420);
            File cropImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg"); // Path to save the cropped image

            img_str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropImageFile));

            startActivityForResult(cropIntent, PIC_CROP);
        } catch (ActivityNotFoundException anfe) {
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {


            if (requestCode == RESULT_LOAD_IMG && resultCode == New_Main_Activity.RESULT_OK && null != data) {

                Uri selectedImageUri = data.getData();

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(selectedImageUri, "image/*");
                intent.putExtra("scale", true);
                intent.putExtra("circleCrop", "");
                intent.putExtra("return-data", false);
                File cropImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg"); // Path to save the cropped image

                img_str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropImageFile));
                startActivityForResult(intent, REQUEST_CROP_ICON);

            } else if (requestCode == REQUEST_CROP_ICON && resultCode == New_Main_Activity.RESULT_OK && null != data) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                Bitmap bmp = BitmapFactory.decodeFile(path);
                openDialog1.dismiss();

                Bitmap bm = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

                b = baos.toByteArray();
                temp = Base64.encodeToString(b, Base64.DEFAULT);

                sps.putString(getApplicationContext(), "profile_img", temp);

            } else if (resultCode == RESULT_OK) {
                if (requestCode == CAMERA_CAPTURE) {
                    Uri uri = picUri;
                    performCrop();
                    Log.d("picUri", uri.toString());

                } else if (requestCode == PICK_IMAGE_REQUEST) {
                    picUri = data.getData();

                    Log.d("uriGallery", picUri.toString());
                    performCrop();
                } else if (requestCode == PIC_CROP) {

                    path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
                    Bitmap bmp = BitmapFactory.decodeFile(path);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
                    b = out.toByteArray();
                    temp = Base64.encodeToString(b, Base64.DEFAULT);
                    sps.putString(getApplicationContext(), "profile_img", temp);
                    openDialog1.dismiss();

                    myDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg");

                }

            } else {
                // Toast.makeText(user_name.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    public void verify_phno(final String name, final String email, final String ph_no, final String place, final String district) {
        // Showing progress dialog at user registration time.
        String name_en;
        String place_en;
        String district_en;
        String mo = null;
        name_en = URLEncoder.encode(name, StandardCharsets.UTF_8);
        place_en = URLEncoder.encode(place, StandardCharsets.UTF_8);
        district_en = URLEncoder.encode(district, StandardCharsets.UTF_8);

        String status = "";
        String charset = "UTF-8";
        try {
            MultipartUtility multipart = new MultipartUtility(price_url, charset);
            multipart.addFormField("mode", "register");
            multipart.addFormField("mobile", ph_no);
            multipart.addFormField("name", name_en);
            multipart.addFormField("email", email);
            multipart.addFormField("place", place_en);
            multipart.addFormField("district", district_en);
            System.out.println("================================districtstart" + district_en);
            multipart.addFormField("otp", sps.getString(LoginActivity.this, "prize_otp"));
            String uri_photo = Environment.getExternalStorageDirectory().getAbsolutePath() + "/profile_img.jpg";
            File myDir = new File(uri_photo);
            if (myDir.exists()) {
                multipart.addFilePart("image", myDir);
                myDir.delete();
            }

            Log.v("rht", "SERVER REPLIED:df");
            List<String> response = multipart.finish();

            Log.v("rht", "SERVER REPLIED:");

            for (String line : response) {
                Log.v("rht", "Line : " + line);
                System.out.println("===========line" + line);
                JSONObject json_data;
                try {
                    if (line != null) {
                        JSONArray jArray = new JSONArray(line);
                        System.out.println("===========status1" + status);
                        for (int i = 0; i < jArray.length(); i++) {
                            System.out.print("Insert for=======");
                            json_data = jArray.getJSONObject(i);
                            status = json_data.getString("status");
                            if (status.equals("success")) {
                                System.out.println("===========status" + status);
                                sps.putString(LoginActivity.this, "p_user_id", json_data.getString("user_id"));
                                sps.putString(LoginActivity.this, "p_user_name", json_data.getString("name"));
                                sps.putString(LoginActivity.this, "p_user_email", json_data.getString("email"));
                                sps.putString(LoginActivity.this, "p_user_mobile", json_data.getString("mobile"));
                                sps.putString(LoginActivity.this, "p_user_photo", json_data.getString("photo"));
                                sps.putString(LoginActivity.this, "p_user_place", json_data.getString("place"));
                                sps.putString(LoginActivity.this, "p_user_district", json_data.getString("district"));
                                sps.putString(LoginActivity.this, "price_registration", "com");

                                System.out.println("================================district" + json_data.getString("district"));
                            }
                            System.out.println("===========status" + status);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        sps.putString(LoginActivity.this, "game_area", "on");
        if (sps.getString(LoginActivity.this, "price_registration").equals("com")) {
            finish();
            Intent i = new Intent(LoginActivity.this, Game_Status.class);
            startActivity(i);
        } else {
            String date = sps.getString(LoginActivity.this, "date");
            if (date.equals("0")) {
                if (main_act.equals("")) {
                    System.out.println("######################## D2");
                    finish();
                    Intent i = new Intent(LoginActivity.this, New_Main_Activity.class);
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
                    Intent i = new Intent(LoginActivity.this, New_Main_Activity.class);
                    startActivity(i);
                } else {
                    System.out.println("######################## D3");
                    finish();
                }
            }
        }
    }
}
