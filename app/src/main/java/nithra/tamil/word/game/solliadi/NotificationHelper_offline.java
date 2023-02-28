package nithra.tamil.word.game.solliadi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.NotificationCompat;

import android.view.View;
import android.widget.RemoteViews;

import java.io.File;
import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;

class NotificationHelper_offline extends ContextWrapper {
    private NotificationManager manager;
    public static final String PRIMARY_CHANNEL = "default";
    NotificationChannel chan1 = null;
    Context context;

    public NotificationHelper_offline(Context base) {
        super(base);
        context = base;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chan1 = new NotificationChannel(PRIMARY_CHANNEL, "Primary Channel", NotificationManager.IMPORTANCE_DEFAULT);
            chan1.setLightColor(Color.GREEN);
            chan1.setShowBadge(true);
            chan1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(chan1);
        }
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public void createNotification_double_clue(final Context context) {

        SharedPreference sps = new SharedPreference();
        String sb, sa;
        int type;
        int min = 1;
        int max = 3;
        int random;
        SQLiteDatabase exdb;
        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);
        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }
      /*  Notification.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, PRIMARY_CHANNEL);
        }*/
        Intent i = new Intent(context, Clue_Game_Hard.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 4, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

       /* Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }*/
        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_clue);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_clue);
            Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;

            if (random == 1) {
                expandedView.setTextViewText(R.id.clue_gametxt, "இதோ ஒரு புதிர் ! உங்களால் விடை காண முடியுமா???");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.clue_gametxt, "குறிப்புகளில் உள்ள குறிப்பிட்ட வார்த்தை என்ன?");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.clue_gametxt, "உங்களால் கண்டுபிடிக்க முடியுமா?");
            }

            //expandedView.setTextViewText(R.id.clue_gametxt,head("fgdtyucjiyf8 tyfrduuyviyg68b \n tf7gi7ynfrfg rftf6fuyh"));

            Cursor c = myDbHelper.getQry("select * from dailytest where gameid=2 and isfinish='0' and date='" + str_date + "'");
            c.moveToFirst();
            if (c.getCount() != 0) {
                sb = c.getString(c.getColumnIndexOrThrow("hints"));
                sa = c.getString(c.getColumnIndexOrThrow("letters"));
                StringTokenizer tok = new StringTokenizer(sb, ",");
                String cs1 = tok.nextToken();
                expandedView.setTextViewText(R.id.clue_noti, "1." + cs1);

                String tfoption = sa;
                String[] first = tfoption.split(",");
                type = first.length;

                String a = "ட,ம்,எ,ன்,கை,மே,சை,மா,நா,டு,போ,க்,கு,வ,ர";

                if (type == 1) {
                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    expandedView.setTextViewText(R.id.c_b1, letter1);
                    expandedView.setTextViewText(R.id.c_b2, (letter2));
                    expandedView.setTextViewText(R.id.c_b3, (letter3));
                    expandedView.setTextViewText(R.id.c_b4, (letter9));
                    expandedView.setTextViewText(R.id.c_b5, (sa));
                    expandedView.setTextViewText(R.id.c_b6, (letter10));
                    expandedView.setTextViewText(R.id.c_b7, (letter6));
                    expandedView.setTextViewText(R.id.c_b8, (letter5));
                    expandedView.setTextViewText(R.id.c_b9, (letter4));


                } else if (type == 2) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();

                    expandedView.setTextViewText(R.id.c_b1, (letter10));
                    expandedView.setTextViewText(R.id.c_b2, (letter3));
                    expandedView.setTextViewText(R.id.c_b3, (word1));
                    expandedView.setTextViewText(R.id.c_b4, (letter11));
                    expandedView.setTextViewText(R.id.c_b5, (letter7));
                    expandedView.setTextViewText(R.id.c_b6, (letter1));
                    expandedView.setTextViewText(R.id.c_b7, (letter6));
                    expandedView.setTextViewText(R.id.c_b8, (letter13));
                    expandedView.setTextViewText(R.id.c_b9, (word2));

                } else if (type == 3) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String word3 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();
                    expandedView.setTextViewText(R.id.c_b1, (letter12));
                    expandedView.setTextViewText(R.id.c_b2, (word3));
                    expandedView.setTextViewText(R.id.c_b3, (letter8));
                    expandedView.setTextViewText(R.id.c_b4, (letter15));
                    expandedView.setTextViewText(R.id.c_b5, (word1));
                    expandedView.setTextViewText(R.id.c_b6, (letter11));
                    expandedView.setTextViewText(R.id.c_b7, (letter9));
                    expandedView.setTextViewText(R.id.c_b8, (word2));
                    expandedView.setTextViewText(R.id.c_b9, (letter4));
                } else if (type == 4) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String word3 = word.nextToken().trim();
                    String word4 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();

                    expandedView.setTextViewText(R.id.c_b1, (word3));
                    expandedView.setTextViewText(R.id.c_b2, (letter2));
                    expandedView.setTextViewText(R.id.c_b3, (word4));
                    expandedView.setTextViewText(R.id.c_b4, (letter10));
                    expandedView.setTextViewText(R.id.c_b5, (word2));
                    expandedView.setTextViewText(R.id.c_b6, (letter12));
                    expandedView.setTextViewText(R.id.c_b7, (letter6));
                    expandedView.setTextViewText(R.id.c_b8, (word4));
                    expandedView.setTextViewText(R.id.c_b9, (letter2));

                } else if (type == 5) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String word3 = word.nextToken().trim();
                    String word4 = word.nextToken().trim();
                    String word5 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();
                    expandedView.setTextViewText(R.id.c_b1, (word3));
                    expandedView.setTextViewText(R.id.c_b2, (word1));
                    expandedView.setTextViewText(R.id.c_b3, (word4));
                    expandedView.setTextViewText(R.id.c_b4, (letter10));
                    expandedView.setTextViewText(R.id.c_b5, (word2));
                    expandedView.setTextViewText(R.id.c_b6, (letter12));
                    expandedView.setTextViewText(R.id.c_b7, (letter6));
                    expandedView.setTextViewText(R.id.c_b8, (letter9));
                    expandedView.setTextViewText(R.id.c_b9, (word5));

                } else if (type == 6) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String word3 = word.nextToken().trim();
                    String word4 = word.nextToken().trim();
                    String word5 = word.nextToken().trim();
                    String word6 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();

                    expandedView.setTextViewText(R.id.c_b1, (word5));
                    expandedView.setTextViewText(R.id.c_b2, (word2));
                    expandedView.setTextViewText(R.id.c_b3, (letter4));
                    expandedView.setTextViewText(R.id.c_b4, (word6));
                    expandedView.setTextViewText(R.id.c_b5, (letter1));
                    expandedView.setTextViewText(R.id.c_b6, (word4));
                    expandedView.setTextViewText(R.id.c_b7, (letter8));
                    expandedView.setTextViewText(R.id.c_b8, (word1));
                    expandedView.setTextViewText(R.id.c_b9, (word3));


                } else if (type == 7) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String word3 = word.nextToken().trim();
                    String word4 = word.nextToken().trim();
                    String word5 = word.nextToken().trim();
                    String word6 = word.nextToken().trim();
                    String word7 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();


                    expandedView.setTextViewText(R.id.c_b1, (word3));
                    expandedView.setTextViewText(R.id.c_b2, (word5));
                    expandedView.setTextViewText(R.id.c_b3, (word2));
                    expandedView.setTextViewText(R.id.c_b4, (word7));
                    expandedView.setTextViewText(R.id.c_b5, (word6));
                    expandedView.setTextViewText(R.id.c_b6, (word4));
                    expandedView.setTextViewText(R.id.c_b7, (letter10));
                    expandedView.setTextViewText(R.id.c_b8, (word1));
                    expandedView.setTextViewText(R.id.c_b9, (letter12));

                } else if (type == 8) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String word3 = word.nextToken().trim();
                    String word4 = word.nextToken().trim();
                    String word5 = word.nextToken().trim();
                    String word6 = word.nextToken().trim();
                    String word7 = word.nextToken().trim();
                    String word8 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();

                    expandedView.setTextViewText(R.id.c_b1, (word6));
                    expandedView.setTextViewText(R.id.c_b2, (letter6));
                    expandedView.setTextViewText(R.id.c_b3, (word8));
                    expandedView.setTextViewText(R.id.c_b4, (word5));
                    expandedView.setTextViewText(R.id.c_b5, (word7));
                    expandedView.setTextViewText(R.id.c_b6, (word4));
                    expandedView.setTextViewText(R.id.c_b7, (word1));
                    expandedView.setTextViewText(R.id.c_b8, (word3));
                    expandedView.setTextViewText(R.id.c_b9, (word2));


                } else if (type == 9) {

                    StringTokenizer tokenizer = new StringTokenizer(a, ",");
                    StringTokenizer word = new StringTokenizer(sa, ",");
                    String word1 = word.nextToken().trim();
                    String word2 = word.nextToken().trim();
                    String word3 = word.nextToken().trim();
                    String word4 = word.nextToken().trim();
                    String word5 = word.nextToken().trim();
                    String word6 = word.nextToken().trim();
                    String word7 = word.nextToken().trim();
                    String word8 = word.nextToken().trim();
                    String word9 = word.nextToken().trim();
                    String letter1 = tokenizer.nextToken().trim();
                    String letter2 = tokenizer.nextToken().trim();
                    String letter3 = tokenizer.nextToken().trim();
                    String letter4 = tokenizer.nextToken().trim();
                    String letter5 = tokenizer.nextToken().trim();
                    String letter6 = tokenizer.nextToken().trim();
                    String letter7 = tokenizer.nextToken().trim();
                    String letter8 = tokenizer.nextToken().trim();
                    String letter9 = tokenizer.nextToken().trim();
                    String letter10 = tokenizer.nextToken().trim();
                    String letter11 = tokenizer.nextToken().trim();
                    String letter12 = tokenizer.nextToken().trim();
                    String letter13 = tokenizer.nextToken().trim();
                    String letter14 = tokenizer.nextToken().trim();
                    String letter15 = tokenizer.nextToken().trim();

                    expandedView.setTextViewText(R.id.c_b1, (word6));
                    expandedView.setTextViewText(R.id.c_b2, (word7));
                    expandedView.setTextViewText(R.id.c_b3, (word8));
                    expandedView.setTextViewText(R.id.c_b4, (word5));
                    expandedView.setTextViewText(R.id.c_b5, (word9));
                    expandedView.setTextViewText(R.id.c_b6, (word4));
                    expandedView.setTextViewText(R.id.c_b7, (word1));
                    expandedView.setTextViewText(R.id.c_b8, (word3));
                    expandedView.setTextViewText(R.id.c_b9, (word2));

                }
                notification.bigContentView = expandedView;
            } else {
                RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_exclue_nodata);
                notification.bigContentView = nodata;
            }

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(4, notification);
        }
    }

    public void createNotification_double_pic(final Context context) {
        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        String imid;
        int image_type;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);
        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }
        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent i = new Intent(context, Picture_Game_Hard.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 3, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);


      /*  Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }*/

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_pic);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_picl);

            Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;

            if (random == 1) {
                expandedView.setTextViewText(R.id.pic_gametxt, "படங்களோடு போட்டியிட்டு வார்த்தையை வெல்லுங்கள் !");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.pic_gametxt, "இந்தப் படங்கள் பேசும் வார்த்தை உங்களுக்கு கேட்கிறதா?");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.pic_gametxt, "பார்த்ததும் பதில் கண்டுபிடிக்க முடியுமா உங்களால்?");
            }

            Cursor c = myDbHelper.getQry("select * from dailytest where gameid=1 and isfinish='0' and date='" + str_date + "'");
            //Cursor c = exdb.rawQuery("select * from maintable where gameid=1 and isfinish='0' order by id limit 1", null);
            c.moveToFirst();
            if (c.getCount() != 0) {

                imid = c.getString(c.getColumnIndexOrThrow("imagename"));
                String tfoptiona = imid;
                String[] firsta = tfoptiona.split(",");
                int i_type = firsta.length;
                image_type = i_type;


                if (image_type == 1) {
                    expandedView.setViewVisibility(R.id.im_1, View.VISIBLE);
                    String fullPath = Environment.getExternalStorageDirectory()
                            .getAbsolutePath()
                            + "/Nithra/solliadi/";

                    File file = new File(fullPath + imid + "");
                    if (file.exists()) {
                        Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
                        expandedView.setImageViewBitmap(R.id.im_1, bitimg1);
                        notification.bigContentView = expandedView;
                    } else {
                        RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
                        notification.bigContentView = nodata;
                    }

                } else if (image_type == 2) {
                    StringTokenizer word = new StringTokenizer(imid, ",");
                    String word1 = word.nextToken();
                    String word2 = word.nextToken();
                    expandedView.setViewVisibility(R.id.im_1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.im_2, View.VISIBLE);
                    String fullPath = Environment.getExternalStorageDirectory()
                            .getAbsolutePath()
                            + "/Nithra/solliadi/";
                    File file = new File(fullPath + word1 + "");
                    File file2 = new File(fullPath + word2 + "");
                    if (file.exists() && file2.exists()) {
                        Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                        Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                        expandedView.setImageViewBitmap(R.id.im_1, bitimg1);
                        expandedView.setImageViewBitmap(R.id.im_2, bitimg2);
                        notification.bigContentView = expandedView;
                    } else {
                        RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
                        notification.bigContentView = nodata;
                    }

                } else if (image_type == 3) {
                    StringTokenizer word = new StringTokenizer(imid, ",");
                    String word1 = word.nextToken();
                    String word2 = word.nextToken();
                    String word3 = word.nextToken();
                    expandedView.setViewVisibility(R.id.im_1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.im_2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.im_3, View.VISIBLE);
                    String fullPath = Environment.getExternalStorageDirectory()
                            .getAbsolutePath()
                            + "/Nithra/solliadi/";
                    File file = new File(fullPath + word1 + "");
                    File file2 = new File(fullPath + word2 + "");
                    File file3 = new File(fullPath + word3 + "");
                    if (file.exists() && file2.exists() && file3.exists()) {
                        Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                        Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                        Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                        expandedView.setImageViewBitmap(R.id.im_1, bitimg1);
                        expandedView.setImageViewBitmap(R.id.im_2, bitimg2);
                        expandedView.setImageViewBitmap(R.id.im_3, bitimg3);
                        notification.bigContentView = expandedView;
                    } else {
                        RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
                        notification.bigContentView = nodata;
                    }

                } else if (image_type == 4) {
                    StringTokenizer word = new StringTokenizer(imid, ",");
                    String word1 = word.nextToken();
                    String word2 = word.nextToken();
                    String word3 = word.nextToken();
                    String word4 = word.nextToken();
                    expandedView.setViewVisibility(R.id.im_1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.im_2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.im_3, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.im_4, View.VISIBLE);
                    String fullPath = Environment.getExternalStorageDirectory()
                            .getAbsolutePath()
                            + "/Nithra/solliadi/";
                    File file = new File(fullPath + word1 + "");
                    File file2 = new File(fullPath + word2 + "");
                    File file3 = new File(fullPath + word3 + "");
                    File file4 = new File(fullPath + word4 + "");
                    if (file.exists() && file2.exists() && file3.exists() && file4.exists()) {
                        Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
                        Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
                        Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
                        Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
                        expandedView.setImageViewBitmap(R.id.im_1, bitimg1);
                        expandedView.setImageViewBitmap(R.id.im_2, bitimg2);
                        expandedView.setImageViewBitmap(R.id.im_3, bitimg3);
                        expandedView.setImageViewBitmap(R.id.im_3, bitimg4);
                        notification.bigContentView = expandedView;
                    } else {
                        RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
                        notification.bigContentView = nodata;
                    }

                }

            } else {
                RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
                notification.bigContentView = nodata;
            }


        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(3, notification);
        }

    }

    public void createNotification_double_solukulsol(final Context context) {
        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        String letters;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);
        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        //NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent i = new Intent(context, Solukul_Sol.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 2, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

     /*   Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }*/

        Notification notification = builder.build();

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_solkulsol);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_solulkulsol);


            Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;

            if (random == 1) {
                expandedView.setTextViewText(R.id.sol_gametxt, "சொல்லுக்குள் புதைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.sol_gametxt, "சொல்லுக்குள் இத்தனை சொற்களா?");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.sol_gametxt, "சொல்லுக்குள் இத்தனை புதுமைகளா?");
            }
            //Cursor c = exdb.rawQuery("select * from maintable where gameid=3 and isfinish='0' order by id limit 1", null);
            Cursor c = myDbHelper.getQry("select * from dailytest where gameid=3 and isfinish='0' and date='" + str_date + "'");
            c.moveToFirst();
            if (c.getCount() != 0) {
                letters = c.getString(c.getColumnIndexOrThrow("letters"));

                String tfoption = letters;
                String[] first = tfoption.split(",");
                int letter_type = first.length;

                if (letter_type == 1) {
                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setTextViewText(R.id.s_b1, letters);

                } else if (letter_type == 2) {
                    StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
                    final String letters1 = tokenizerw.nextToken().trim();
                    final String letters2 = tokenizerw.nextToken().trim();
                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b2, View.VISIBLE);
                    expandedView.setTextViewText(R.id.s_b1, letters1);
                    expandedView.setTextViewText(R.id.s_b2, letters2);
                } else if (letter_type == 3) {

                    StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
                    final String letters1 = tokenizerw.nextToken().trim();
                    final String letters2 = tokenizerw.nextToken().trim();
                    final String letters3 = tokenizerw.nextToken().trim();
                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b3, View.VISIBLE);
                    expandedView.setTextViewText(R.id.s_b1, letters1);
                    expandedView.setTextViewText(R.id.s_b2, letters2);
                    expandedView.setTextViewText(R.id.s_b3, letters3);
                } else if (letter_type == 4) {

                    StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
                    final String letters1 = tokenizerw.nextToken().trim();
                    final String letters2 = tokenizerw.nextToken().trim();
                    final String letters3 = tokenizerw.nextToken().trim();
                    final String letters4 = tokenizerw.nextToken().trim();
                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b3, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b4, View.VISIBLE);
                    expandedView.setTextViewText(R.id.s_b1, letters1);
                    expandedView.setTextViewText(R.id.s_b2, letters2);
                    expandedView.setTextViewText(R.id.s_b3, letters3);
                    expandedView.setTextViewText(R.id.s_b4, letters4);
                } else if (letter_type == 5) {

                    StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
                    final String letters1 = tokenizerw.nextToken().trim();
                    final String letters2 = tokenizerw.nextToken().trim();
                    final String letters3 = tokenizerw.nextToken().trim();
                    final String letters4 = tokenizerw.nextToken().trim();
                    final String letters5 = tokenizerw.nextToken().trim();

                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b3, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b4, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b5, View.VISIBLE);

                    expandedView.setTextViewText(R.id.s_b1, letters1);
                    expandedView.setTextViewText(R.id.s_b2, letters2);
                    expandedView.setTextViewText(R.id.s_b3, letters3);
                    expandedView.setTextViewText(R.id.s_b4, letters4);
                    expandedView.setTextViewText(R.id.s_b5, letters5);

                } else if (letter_type == 6) {

                    StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
                    final String letters1 = tokenizerw.nextToken().trim();
                    final String letters2 = tokenizerw.nextToken().trim();
                    final String letters3 = tokenizerw.nextToken().trim();
                    final String letters4 = tokenizerw.nextToken().trim();
                    final String letters5 = tokenizerw.nextToken().trim();
                    final String letters6 = tokenizerw.nextToken().trim();

                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b3, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b4, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b5, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b6, View.VISIBLE);
                    expandedView.setTextViewText(R.id.s_b1, letters1);
                    expandedView.setTextViewText(R.id.s_b2, letters2);
                    expandedView.setTextViewText(R.id.s_b3, letters3);
                    expandedView.setTextViewText(R.id.s_b4, letters4);
                    expandedView.setTextViewText(R.id.s_b5, letters5);
                    expandedView.setTextViewText(R.id.s_b6, letters6);
                } else if (letter_type == 7) {
                    StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
                    final String letters1 = tokenizerw.nextToken().trim();
                    final String letters2 = tokenizerw.nextToken().trim();
                    final String letters3 = tokenizerw.nextToken().trim();
                    final String letters4 = tokenizerw.nextToken().trim();
                    final String letters5 = tokenizerw.nextToken().trim();
                    final String letters6 = tokenizerw.nextToken().trim();
                    final String letters7 = tokenizerw.nextToken().trim();

                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b3, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b4, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b5, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b6, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b7, View.VISIBLE);
                    expandedView.setTextViewText(R.id.s_b1, letters1);
                    expandedView.setTextViewText(R.id.s_b2, letters2);
                    expandedView.setTextViewText(R.id.s_b3, letters3);
                    expandedView.setTextViewText(R.id.s_b4, letters4);
                    expandedView.setTextViewText(R.id.s_b5, letters5);
                    expandedView.setTextViewText(R.id.s_b6, letters6);
                    expandedView.setTextViewText(R.id.s_b7, letters7);
                } else if (letter_type == 8) {

                    StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
                    final String letters1 = tokenizerw.nextToken().trim();
                    final String letters2 = tokenizerw.nextToken().trim();
                    final String letters3 = tokenizerw.nextToken().trim();
                    final String letters4 = tokenizerw.nextToken().trim();
                    final String letters5 = tokenizerw.nextToken().trim();
                    final String letters6 = tokenizerw.nextToken().trim();
                    final String letters7 = tokenizerw.nextToken().trim();
                    final String letters8 = tokenizerw.nextToken().trim();

                    expandedView.setViewVisibility(R.id.s_b1, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b2, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b3, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b4, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b5, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b6, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b7, View.VISIBLE);
                    expandedView.setViewVisibility(R.id.s_b8, View.VISIBLE);
                    expandedView.setTextViewText(R.id.s_b1, letters1);
                    expandedView.setTextViewText(R.id.s_b2, letters2);
                    expandedView.setTextViewText(R.id.s_b3, letters3);
                    expandedView.setTextViewText(R.id.s_b4, letters4);
                    expandedView.setTextViewText(R.id.s_b5, letters5);
                    expandedView.setTextViewText(R.id.s_b6, letters6);
                    expandedView.setTextViewText(R.id.s_b7, letters7);
                    expandedView.setTextViewText(R.id.s_b8, letters8);
                }
                notification.bigContentView = expandedView;
            } else {
                RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_exsol_nodata);
                notification.bigContentView = nodata;
            }


        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(2, notification);
        }

    }

    public void createNotification_double_wordgame(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Word_Game_Hard.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 1, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand);

            Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;

            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }

            //Cursor c = exdb.rawQuery("select * from maintable where gameid=4 and isfinish='0' order by id limit 1", null);
            Cursor c = myDbHelper.getQry("select * from dailytest where gameid=4 and isfinish='0' and date='" + str_date + "'");
            c.moveToFirst();
            if (c.getCount() != 0) {
                String sa = c.getString(c.getColumnIndexOrThrow("letters"));
                StringTokenizer tokenizer = new StringTokenizer(sa, ",");
                final String letter1 = tokenizer.nextToken();
                String letter2 = tokenizer.nextToken().trim();
                String letter3 = tokenizer.nextToken().trim();
                String letter4 = tokenizer.nextToken().trim();
                String letter5 = tokenizer.nextToken().trim();
                String letter6 = tokenizer.nextToken().trim();
                String letter7 = tokenizer.nextToken().trim();
                String letter8 = tokenizer.nextToken().trim();
                String letter9 = tokenizer.nextToken().trim();

                expandedView.setTextViewText(R.id.w_b1, letter1);
                expandedView.setTextViewText(R.id.w_b2, letter2);
                expandedView.setTextViewText(R.id.w_b3, letter3);
                expandedView.setTextViewText(R.id.w_b4, letter4);
                expandedView.setTextViewText(R.id.w_b5, letter5);
                expandedView.setTextViewText(R.id.w_b6, letter6);
                expandedView.setTextViewText(R.id.w_b7, letter7);
                expandedView.setTextViewText(R.id.w_b8, letter8);
                expandedView.setTextViewText(R.id.w_b9, letter9);

                notification.bigContentView = expandedView;
            } else {
                RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_exword_nodata);
                notification.bigContentView = nodata;
            }
        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(1, notification);
        }
    }

    public void createNotification_double_find_diffward(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;


        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);
        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Odd_man_out.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 5, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_diff);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_diff);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_diff);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(5, notification);
        }
    }

    public void createNotification_double_find_equealword(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;
        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Match_Word.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 6, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_innai);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_innai);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_innai);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(6, notification);
        }
    }

    public void createNotification_double_find_oppsiteword(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;
        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Opposite_word.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 7, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_opps);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_opps);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_opps);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(7, notification);
        }
    }

    public void createNotification_double_find_other_language(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Ote_to_Tamil.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 8, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_otlang);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_otlang);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_otlang);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(8, notification);
        }
    }

    public void createNotification_double_find_serpaduthu(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Makeword_Rightorder.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 9, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_order);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_order);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_order);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(9, notification);
        }
    }

    public void createNotification_double_find_riddles(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Riddle_game.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 10, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_riddle);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_riddle);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_riddle);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(10, notification);
        }
    }

    public void createNotification_trikural(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, Tirukural.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 11, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_tiru);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_tiru);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_tiru);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(11, notification);
        }
    }

    public void createNotification_error_correction(final Context context) {

        SharedPreference sps = new SharedPreference();
        SQLiteDatabase exdb;
        int min = 1;
        int max = 3;
        int random;

        if (sps.getString(context, "new_user_db").equals("")) {

        } else {
            if (sps.getString(context, "new_user_db").equals("on")) {
                sps.putString(context, "db_name_start", "Tamil_Game2.db");
                Commen_string.dbs_name = "Tamil_Game2.db";
            } else {
                sps.putString(context, "db_name_start", "Solli_Adi");
                Commen_string.dbs_name = "Solli_Adi";
            }

        }
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(context);

        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        // NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL);

        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent i = new Intent(context, WordError_correction.class);
        sps.putString(context, "Exp_list", "off");
        sps.putInt(context, "addlodedd", 2);
        sps.putString(context, "yes_reward", "yes");
        sps.putInt(context,"native_banner_ads",0);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


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
        String str_date = cur_year + "-" + str_month + "-" + str_day;

        i.putExtra("datee", str_date);

        sps.putString(context, "date", "" + str_date);

        PendingIntent intent = PendingIntent.getActivity(context, 12, i, Utils.getPendingIntent());
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_error);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.daily_expand_error);

           /* Random rn = new Random();
            random = rn.nextInt(max - min + 1) + min;
            if (random == 1) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");
            } else if (random == 2) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
            } else if (random == 3) {
                expandedView.setTextViewText(R.id.word_gametxt,"எழுத்துக்களில் மறைந்திருக்கும் சொற்களை சொல்லுங்கள் !");
            }*/

            expandedView.setTextViewText(R.id.word_gametxt, "எழுத்துக்களோடு விளையாடி சொற்களை வெல்லுங்கள் !!");


            RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expand_error);
            notification.bigContentView = nodata;

        }

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        String snd = sps.getString(context, "Daily_notifications");

        if (snd.equals("yes")) {
            nm.notify(12, notification);
        }
    }

}
