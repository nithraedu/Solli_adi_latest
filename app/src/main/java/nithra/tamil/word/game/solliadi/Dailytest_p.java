package nithra.tamil.word.game.solliadi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Dailytest_p extends BroadcastReceiver {
    final public static String ONE_TIME = "onetime";
    final SharedPreference sps = new SharedPreference();
    final int min = 1;
    final int max = 3;
    SQLiteDatabase exdb;
    String imid;
    int image_type;
    int random;
    NotificationHelper_offline noti;

    public static String armTodayOrTomo1(String selectedHour,
                                         String selectedMinute) throws ParseException {
        String defti = selectedHour + ":" + selectedMinute;

        Time time = new Time();
        time.setToNow();

        String armTodayOrTomo1;
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
        System.out.println("A4----------" + armTodayOrTomo1);
        return armTodayOrTomo1;
    }

    @Override

    public void onReceive(Context context, Intent intent) {
        noti = new NotificationHelper_offline(context);
        exdb = context.openOrCreateDatabase("Solli_Adi", Context.MODE_PRIVATE, null);
        boolean isBooted;

        final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

        String action = intent.getAction();
        isBooted = action != null && action.equalsIgnoreCase(BOOT_ACTION);

        if (!isBooted) {
            try {


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

            SetAlarm1(context, Dailytest_p.armTodayOrTomo1("6", "0"));
            System.out.println("Aleram-----4");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void SetAlarm1(Context context, String armTodayOrTomo1) {

        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Dailytest_p.class);

        intent.putExtra(ONE_TIME, Boolean.FALSE);


        PendingIntent pi = PendingIntent.getBroadcast(context, 3, intent, Utils.getPendingIntent());//3
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (armTodayOrTomo1.equals("tomo")) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
        am.set(AlarmManager.RTC, calendar.getTimeInMillis(), pi);
        //am.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(),pi),pi);

    }

    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, Dailytest_p.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 3, intent, Utils.getPendingIntent());//3
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        sender.cancel();


    }


}
