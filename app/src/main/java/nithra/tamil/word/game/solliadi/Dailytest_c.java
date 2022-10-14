package nithra.tamil.word.game.solliadi;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.text.format.Time;
import android.widget.RemoteViews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;


public class Dailytest_c extends BroadcastReceiver {
    final public static String ONE_TIME = "onetime";
    SharedPreference sps = new SharedPreference();
    SQLiteDatabase exdb;
    String sb, sa;
    int type;
    int min = 1;
    int max = 3;
    int random;
    NotificationHelper_offline noti;

    @Override
    public void onReceive(Context context, Intent intent) {
        noti = new NotificationHelper_offline(context);
        exdb = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

        Boolean isBooted = false;

        final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

        String action = intent.getAction();
        if (action != null && action.equalsIgnoreCase(BOOT_ACTION)) {
            isBooted = true;

        } else {
            isBooted = false;
        }


        if (!isBooted) {
            try {
                //	createNotification2(context);

                //sps.putInt(context,"daily_test_order",sps.getInt(context,"daily_test_order")+1);


                //noti.createNotification_double_clue(context);
                //noti.createNotification_double_find_diffward(context);

                if (sps.getString(context, "newgame_notification").equals("start")) {
                    if (sps.getInt(context, "notification_order") == 0) {
                        noti.createNotification_double_pic(context);
                        sps.putInt(context, "notification_order", 1);
                    } else if (sps.getInt(context, "notification_order") == 1) {
                        noti.createNotification_double_clue(context);
                        sps.putInt(context, "notification_order", 2);
                    } else if (sps.getInt(context, "notification_order") == 2) {
                        noti.createNotification_double_solukulsol(context);
                        sps.putInt(context, "notification_order", 3);
                    } else if (sps.getInt(context, "notification_order") == 3) {
                        noti.createNotification_double_wordgame(context);
                        sps.putInt(context, "notification_order", 4);
                    } else if (sps.getInt(context, "notification_order") == 4) {
                        noti.createNotification_double_find_diffward(context);
                        sps.putInt(context, "notification_order", 5);
                    } else if (sps.getInt(context, "notification_order") == 5) {
                        noti.createNotification_double_find_equealword(context);
                        sps.putInt(context, "notification_order", 6);
                    } else if (sps.getInt(context, "notification_order") == 6) {
                        noti.createNotification_double_find_oppsiteword(context);
                        sps.putInt(context, "notification_order", 7);
                    } else if (sps.getInt(context, "notification_order") == 7) {
                        noti.createNotification_double_find_other_language(context);
                        sps.putInt(context, "notification_order", 8);
                    } else if (sps.getInt(context, "notification_order") == 8) {
                        noti.createNotification_double_find_serpaduthu(context);
                        sps.putInt(context, "notification_order", 9);
                    } else if (sps.getInt(context, "notification_order") == 9) {
                        noti.createNotification_double_find_riddles(context);
                        sps.putInt(context, "notification_order", 10);
                    } else if (sps.getInt(context, "notification_order") == 10) {
                        noti.createNotification_trikural(context);
                        sps.putInt(context, "notification_order", 11);
                    } else if (sps.getInt(context, "notification_order") == 11) {
                        noti.createNotification_error_correction(context);
                        sps.putInt(context, "notification_order", 0);
                    }
                } else {

                    if (sps.getInt(context, "notification_order") == 0) {
                        noti.createNotification_double_pic(context);
                        sps.putInt(context, "notification_order", 1);
                    } else if (sps.getInt(context, "notification_order") == 1) {
                        noti.createNotification_double_clue(context);
                        sps.putInt(context, "notification_order", 2);
                    } else if (sps.getInt(context, "notification_order") == 2) {
                        noti.createNotification_double_solukulsol(context);
                        sps.putInt(context, "notification_order", 3);
                    } else if (sps.getInt(context, "notification_order") == 3) {
                        noti.createNotification_double_wordgame(context);
                        sps.putInt(context, "notification_order", 0);
                    }

                }


            } catch (Exception e) {

            }

        }


        CancelAlarm(context);

        try {

            SetAlarm1(context, Dailytest_c.armTodayOrTomo1("13", "0"));
            System.out.println("Aleram-----2");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void SetAlarm1(Context context, String armTodayOrTomo1) {

        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Dailytest_c.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);


        PendingIntent pi = PendingIntent.getBroadcast(context, 4, intent, Utils.getPendingIntent());//4
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (armTodayOrTomo1.equals("tomo")) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
        am.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pi);
        //am.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(),pi),pi);

    }

    public static String armTodayOrTomo1(String selectedHour,
                                         String selectedMinute) throws ParseException {
        String defti = selectedHour + ":" + selectedMinute;

        Time time = new Time();
        time.setToNow();

        String armTodayOrTomo1 = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date time1 = sdf.parse(time.hour + ":" + time.minute);
        Date time2 = sdf.parse(selectedHour + ":" + selectedMinute);

        System.out.println("curTime 2 : " + time.hour + ":" + time.minute);
        System.out.println("newTime 2 : " + selectedHour + ":" + selectedMinute);
        if (time1.compareTo(time2) >= 0) {
            armTodayOrTomo1 = "tomo";
        } else {
            armTodayOrTomo1 = "today";
        }
        System.out.println("A2----------" + armTodayOrTomo1);
        return armTodayOrTomo1;
    }

    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, Dailytest_c.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 4, intent, Utils.getPendingIntent());//4
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        sender.cancel();


    }


}
