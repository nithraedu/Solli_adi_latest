package nithra.tamil.word.game.solliadi;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Utils {
    public static final String MORE_APPS = "https://play.google.com/store/apps/developer?id=Nithra";
    public static ProgressDialog mProgress;
    public static String gcm_updation_link = "https://www.nithra.mobi/appgcm/gcmmahabharatham/update.php";

    public static ProgressDialog mProgress(Context context, String txt, Boolean aBoolean) {
        mProgress = new ProgressDialog(context);
        mProgress.setMessage(txt);
        mProgress.setCancelable(aBoolean);
        return mProgress;
    }

    public static int getPendingIntent() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            return PendingIntent.FLAG_MUTABLE;
        } else {
            return PendingIntent.FLAG_UPDATE_CURRENT;
        }
    }

    public static void settypeface(Context context, TextView textView) {
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), "new.ttf");
        textView.setTypeface(tf1);
    }

    public static int getversioncode(Context c) {
        PackageInfo pInfo = null;
        try {
            pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo.versionCode;
    }

    public static String getversionname(Context c) {
        PackageInfo pInfo = null;
        try {
            pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo.versionName;
    }

    public static void toast_normal(Context c, String s) {
        Toast.makeText(c, "" + s, Toast.LENGTH_SHORT).show();
    }

    public static void toast_center(Context context, String str) {
        Toast toast = Toast.makeText(context, "" + str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static String getDeviceName() {
        String Manufacturer = Build.MANUFACTURER;
        String Model = Build.MODEL;
        String Brand = Build.BRAND;
        String Product = Build.PRODUCT;
        return Manufacturer + "-" + Model + "-" + Brand + "-" + Product;
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean result = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = true;
                    }
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        result = true;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public static String body_font() {
        String bodyFont = "<style> body { font-size:20px; } table { font-size:20px; <font face='bamini'> }</style>"
                + "<style> @font-face { font-family:'bamini'; src: url('file:///android_asset/baamini.ttf') } </style>";
        return bodyFont;
    }

    public static String pad(String str) {
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String android_id(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String am_pm1(int hur, int min) {
        String AM_PM = "AM";
        if (hur >= 12) {
            hur = hur - 12;
            AM_PM = "PM";
        } else {
            AM_PM = "AM";
        }
        if (hur == 0) {
            hur = 12;
        }
        return Utils.pad("" + hur) + " : " + Utils.pad("" + min) + " " + AM_PM;
    }

    public static void date_put(Context context, String str, int val) {
        Calendar calendar = Calendar.getInstance();
        long next_hour = calendar.getTimeInMillis() + val * DateUtils.DAY_IN_MILLIS;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/M/yyyy");
        Date results = new Date(next_hour);
        String formatted = sdf1.format(results);
        StringTokenizer st2 = new StringTokenizer(formatted, "/");
        int rep_day = Integer.parseInt(st2.nextToken());
        int rep_month = Integer.parseInt(st2.nextToken());
        int rep_year = Integer.parseInt(st2.nextToken());
        rep_month = rep_month - 1;
        String strdate = rep_day + "/" + rep_month + "/" + rep_year;
        SharedPreference sharedPreference = new SharedPreference();
        sharedPreference.putString(context, str, strdate);
    }

    public static Boolean gcm_update(Context context) {
        boolean aBoolean = false;
        SharedPreference sharedPreference = new SharedPreference();
        Calendar calendar = Calendar.getInstance();
        long next_hour = calendar.getTimeInMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/M/yyyy");
        Date result = new Date(next_hour);
        String formatted = sdf1.format(result);
        StringTokenizer st2 = new StringTokenizer(formatted, "/");
        int rep_day = Integer.parseInt(st2.nextToken());
        int rep_month = Integer.parseInt(st2.nextToken());
        int rep_year = Integer.parseInt(st2.nextToken());
        rep_month = rep_month - 1;
        String today_date = rep_day + "/" + rep_month + "/" + rep_year;
        Date date_today = null, date_app_update = null;
        try {
            date_today = sdf1.parse(today_date);
            if (!sharedPreference.getString(context, "gcm_update").equals("")) {
                date_app_update = sdf1.parse(sharedPreference.getString(context, "gcm_update"));
            } else {
                date_app_update = sdf1.parse(today_date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("gcm_update : error");
        }
        if (sharedPreference.getString(context, "gcm_update").equals("")) {
            aBoolean = true;
            System.out.println("gcm_update : " + aBoolean);
        } else if (date_today.compareTo(date_app_update) >= 0) {
            aBoolean = true;
            System.out.println("gcm_update : " + aBoolean);
        }
        return aBoolean;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static int versioncode_get(Context context) {
        PackageInfo pInfo = null;

        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pInfo.versionCode;
    }

    public static String versionname_get(Context context) {
        PackageInfo pInfo = null;

        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pInfo.versionName;
    }
}