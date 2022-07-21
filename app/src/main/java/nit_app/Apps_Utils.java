package nit_app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import nithra.tamil.word.game.solliadi.R;
import nithra.tamil.word.game.solliadi.SharedPreference;

/**
 * Created by NITHRA-G5 on 12/2/2015.
 */
public class Apps_Utils {

    public static String app, tit,  app_des,  app_downlods,logo;

    public static Dialog cross_app;

    public static void app_install_check(Context context, DataBaseHelper1 myDB) {
        Cursor c = myDB.getQry("select * from nit_apps ");

        if (c.getCount() != 0) {
            for (int i = 0; i < c.getCount(); i++) {
                c.moveToPosition(i);
                if (Apps_Utils.appInstalledOrNot(context, c.getString(c.getColumnIndex("package"))) == true) {
                    myDB.executeSql("UPDATE nit_apps SET is_ins='1' where package = '" + c.getString(c.getColumnIndex("package")) + "'");
                }

            }
        }
    }

    public static boolean appInstalledOrNot(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    public static Boolean cross_app_shown(Context context,DataBaseHelper1 myDB){
        Boolean aBoolean = false;
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


        if(sharedPreference.getInt(context,"share_cross")==0){

            sharedPreference.putInt(context,"share_cross",rep_day);
        }
        if (sharedPreference.getInt(context, "share_cross") == rep_day) {


            aBoolean=false;
        }else {

            rep_month = rep_month - 1;

            String today_date = rep_day + "/" + rep_month + "/" + rep_year;

            Date date_today = null, date_app_update = null;

            try {
                date_today = sdf1.parse(today_date);
                if (!sharedPreference.getString(context, "open_date").equals("")) {
                    date_app_update = sdf1.parse(sharedPreference.getString(context, "open_date"));
                }
                else{
                    date_app_update = sdf1.parse(today_date);;
                }

            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

      /*  if(sharedPreference.getString(context,"open_date").equals(today_date)||sharedPreference.getString(context,"open_date").equals("")){
            Apps_Utils.date_put(context,"open_date",3);
            aBoolean = true;
        }*/

            if (sharedPreference.getString(context, "open_date").equals("")) {
                Apps_Utils.date_put(context, "open_date", 3);
                aBoolean = true;
            } else {
                if (date_today.compareTo(date_app_update) >= 0) {
                    aBoolean = true;
                }
            }

            Cursor c = myDB.getQry("select * from nit_apps where is_ins='0'");

            if (c.getCount() == 0) {
                aBoolean = false;
            }
        }




        return aBoolean;
    }

    public static Boolean cross_app_shown(Context context){
        Boolean aBoolean = false;
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
            if (!sharedPreference.getString(context, "rate_date").equals("")) {
                date_app_update = sdf1.parse(sharedPreference.getString(context, "rate_date"));
            }
            else{
                date_app_update = sdf1.parse(today_date);;
            }

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        if (sharedPreference.getString(context, "rate_date").equals("")) {

            aBoolean = true;
        } else {
            if (date_today.compareTo(date_app_update) >= 0) {
                aBoolean = true;
                /*sharedPreference.putInt(context, "ratecheckval", 0);*/
            }
        }

        if(sharedPreference.getInt(context,"ratecheckval")==0){
            aBoolean = true;
        }

        return aBoolean;
    }




    public static void app_data(DataBaseHelper1 myDB) {
        Cursor c = myDB.getQry("select * from nit_apps where is_ins='0' AND show_num != shown");

        /*public static String app, tit,  app_des,  app_downlods;
        public static  int logo;*/

        int i1 = 0;
        Random r = new Random();
            i1 = r.nextInt(3)+1;

        if (c.getCount() != 0) {
            c.moveToFirst();
            app = c.getString(c.getColumnIndex("package"));
            tit = c.getString(c.getColumnIndex("app_name"));
            app_des = c.getString(c.getColumnIndex("desc"+i1));
            app_downlods = c.getString(c.getColumnIndex("downloads"));
            logo = c.getString(c.getColumnIndex("logo"));

            int val =  c.getInt(c.getColumnIndex("shown"))+1;
            myDB.executeSql("UPDATE nit_apps SET shown='"+val+"' where package = '"+c.getString(c.getColumnIndex("package"))+"'");

        }
        else{
            Cursor cv = myDB.getQry("select * from nit_apps where is_ins='0'");

            if (cv.getCount()!= 0) {
                myDB.executeSql("UPDATE nit_apps SET shown='0'");
                cv.moveToFirst();

                app = cv.getString(cv.getColumnIndex("package"));
                tit = cv.getString(cv.getColumnIndex("app_name"));
                app_des = cv.getString(cv.getColumnIndex("desc"+i1));
                app_downlods = cv.getString(cv.getColumnIndex("downloads"));
                logo = cv.getString(cv.getColumnIndex("logo"));

                int val =  cv.getInt(cv.getColumnIndex("shown"))+1;
                myDB.executeSql("UPDATE nit_apps SET shown='"+val+"' where package = '"+cv.getString(cv.getColumnIndex("package"))+"'");
            }
        }
    }


    public static Dialog cross_app(final Context context, DataBaseHelper1 myDB){
        app_data(myDB);
              cross_app = new Dialog(context,
                    android.R.style.Theme_Translucent_NoTitleBar);
        cross_app.setContentView(R.layout.cross_app1);

            AppCompatButton button1 = (AppCompatButton) cross_app.findViewById(R.id.button1);
            AppCompatTextView app_name = (AppCompatTextView) cross_app.findViewById(R.id.app_name);
            AppCompatTextView app_dec = (AppCompatTextView) cross_app.findViewById(R.id.app_dec);

            AppCompatTextView app_down = (AppCompatTextView) cross_app.findViewById(R.id.app_down);

            final String diclink = "https://play.google.com/store/apps/details?id="+app+"&referrer=utm_source%3DSOLLIADI_CROSS";
            app_name.setText(""+tit);
            app_dec.setText(""+app_des);
            app_down.setText(""+app_downlods);
            ImageView icon = (ImageView) cross_app.findViewById(R.id.imageView1);
            int imageid1 = context.getResources().getIdentifier(logo, "drawable",context.getPackageName());
            icon.setImageResource(imageid1);

            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent internet = new Intent();
                    internet.setAction(Intent.ACTION_VIEW);
                    internet.addCategory(Intent.CATEGORY_BROWSABLE);
                    internet.setData(Uri.parse(diclink));
                    context.startActivity(internet);
                    cross_app.dismiss();
                }
            });

            /*dialogvaa.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    rateusfun();
                }
            });*/


        return cross_app;

        }




    public static void date_put(Context context,String str, int val){
        Calendar calendar=Calendar.getInstance();
        long next_hour= calendar.getTimeInMillis() + val * DateUtils.DAY_IN_MILLIS;

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/M/yyyy");
        Date results = new Date(next_hour);
        String formatted = sdf1.format(results);

        StringTokenizer st2 = new StringTokenizer(formatted, "/");
        int rep_day = Integer.parseInt(st2.nextToken());
        int rep_month = Integer.parseInt(st2.nextToken());
        int rep_year = Integer.parseInt(st2.nextToken());

        rep_month=rep_month-1;

        String strdate = rep_day + "/" + rep_month + "/" + rep_year;

        SharedPreference sharedPreference = new SharedPreference();
        sharedPreference.putString(context, str, strdate);
    }

}
