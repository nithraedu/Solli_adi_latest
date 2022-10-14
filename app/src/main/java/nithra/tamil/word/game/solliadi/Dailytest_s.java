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
import android.view.View;
import android.widget.RemoteViews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;


public class Dailytest_s extends BroadcastReceiver {
	final public static String ONE_TIME = "onetime";
	SharedPreference sps=new SharedPreference();
	SQLiteDatabase exdb;
	String letters;
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

				if (sps.getString(context,"newgame_notification").equals("start")){
					if (sps.getInt(context,"notification_order")==0){
						noti.createNotification_double_pic(context);
						sps.putInt(context,"notification_order",1);
					}else if (sps.getInt(context,"notification_order")==1){
						noti.createNotification_double_clue(context);
						sps.putInt(context,"notification_order",2);
					}else if (sps.getInt(context,"notification_order")==2){
						noti.createNotification_double_solukulsol(context);
						sps.putInt(context,"notification_order",3);
					}else if (sps.getInt(context,"notification_order")==3){
						noti.createNotification_double_wordgame(context);
						sps.putInt(context,"notification_order",4);
					}else if (sps.getInt(context,"notification_order")==4){
						noti.createNotification_double_find_diffward(context);
						sps.putInt(context,"notification_order",5);
					}else if (sps.getInt(context,"notification_order")==5){
						noti.createNotification_double_find_equealword(context);
						sps.putInt(context,"notification_order",6);
					}else if (sps.getInt(context,"notification_order")==6){
						noti.createNotification_double_find_oppsiteword(context);
						sps.putInt(context,"notification_order",7);
					}else if (sps.getInt(context,"notification_order")==7){
						noti.createNotification_double_find_other_language(context);
						sps.putInt(context,"notification_order",8);
					}else if (sps.getInt(context,"notification_order")==8){
						noti.createNotification_double_find_serpaduthu(context);
						sps.putInt(context,"notification_order",9);
					}else if (sps.getInt(context,"notification_order")==9){
						noti.createNotification_double_find_riddles(context);
						sps.putInt(context,"notification_order",10);
					}else if (sps.getInt(context,"notification_order")==10){
						noti.createNotification_trikural(context);
						sps.putInt(context,"notification_order",11);
					}else if (sps.getInt(context,"notification_order")==11){
						noti.createNotification_error_correction(context);
						sps.putInt(context,"notification_order",0);
					}
				}else {

					if (sps.getInt(context,"notification_order")==0){
						noti.createNotification_double_pic(context);
						sps.putInt(context,"notification_order",1);
					}else if (sps.getInt(context,"notification_order")==1){
						noti.createNotification_double_clue(context);
						sps.putInt(context,"notification_order",2);
					}else if (sps.getInt(context,"notification_order")==2){
						noti.createNotification_double_solukulsol(context);
						sps.putInt(context,"notification_order",3);
					}else if (sps.getInt(context,"notification_order")==3){
						noti.createNotification_double_wordgame(context);
						sps.putInt(context,"notification_order",0);
					}

				}
			} catch (Exception e) {

			}

		}




		CancelAlarm(context);
	
		try {
          
            SetAlarm1(context, Dailytest_s.armTodayOrTomo1("17", "0"));
             System.out.println("Aleram-----3");
    		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	


	public void SetAlarm1(Context context, String armTodayOrTomo1) {

		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, Dailytest_s.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);

		
		
		PendingIntent pi = PendingIntent.getBroadcast(context, 2, intent, Utils.getPendingIntent());//2
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		if (armTodayOrTomo1.equals("tomo")){
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
		System.out.println("A3----------" + armTodayOrTomo1);
		return armTodayOrTomo1;
	}

	public void CancelAlarm(Context context) {
		Intent intent = new Intent(context, Dailytest_s.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 2, intent,Utils.getPendingIntent());//2
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
		sender.cancel();
		
		
	}

	public void createNotification2(Context context) {

		Calendar calendar2 = Calendar.getInstance();
		int cur_year = calendar2.get(Calendar.YEAR);
		int cur_month = calendar2.get(Calendar.MONTH);
		int cur_day = calendar2.get(Calendar.DAY_OF_MONTH);



		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.noti_backicon,"Solli_Adi!", System.currentTimeMillis());
		// Hide the notification after its selected
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;

		Intent intent = new Intent(context, Solukul_Sol.class);
		String str_month = "" + (cur_month + 1);
		if (str_month.length() == 1) {
			str_month = "0" + str_month;
		}

		String str_day = ""+cur_day;
		if(str_day.length()==1){
			str_day = "0"+str_day;
		}
		String str_date = cur_year+"-"+str_month+"-"+str_day;

		intent.putExtra("datee",str_date);


		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 2,intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//notification.setLatestEventInfo(context, "இன்றைய  சொல்லுக்குள் சொல்", "தொட்டு தொடரவும்", pendingIntent);



		String snd = sps.getString(context, "Daily_notifications");

		if (snd.equals("yes"))
		{
			notificationManager.notify(2, notification);
		}

		//	notificationManager.notify(2, notification);
	}

	public void createNotification_double(final Context context) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Intent i = new Intent(context, Solukul_Sol.class);
		sps.putString(context,"Exp_list","off");
		sps.putInt(context, "addlodedd", 2);
		sps.putString(context,"yes_reward","yes");
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

		String str_day = ""+cur_day;
		if(str_day.length()==1){
			str_day = "0"+str_day;
		}
		String str_date = cur_year+"-"+str_month+"-"+str_day;

		i.putExtra("datee",str_date);

		PendingIntent intent = PendingIntent.getActivity(context, 2, i,PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(intent);
		builder.setTicker(context.getResources().getString(R.string.app_name));
		builder.setSmallIcon(R.drawable.noti_backicon);
		builder.setAutoCancel(true);



		Notification notification = builder.build();
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_solkulsol);

		int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
		contentView.setImageViewResource(R.id.img,imageid1);
		notification.contentView = contentView;

		if (Build.VERSION.SDK_INT >= 16) {
			// Inflate and set the layout for the expanded notification view
			RemoteViews expandedView =	new RemoteViews(context.getPackageName(), R.layout.daily_expand_solulkulsol);


			Random rn = new Random();
			random = rn.nextInt(max - min + 1) + min;

			if (random == 1) {
				expandedView.setTextViewText(R.id.sol_gametxt,"சொல்லுக்குள் புதைந்திருக்கும் சொற்களை கண்டுபிடியுங்கள் !");
			} else if (random == 2) {
				expandedView.setTextViewText(R.id.sol_gametxt,"சொல்லுக்குள் இத்தனை சொற்களா?");
			} else if (random == 3) {
				expandedView.setTextViewText(R.id.sol_gametxt,"சொல்லுக்குள் இத்தனை புதுமைகளா?");
			}
			//Cursor c = exdb.rawQuery("select * from maintable where gameid=3 and isfinish='0' order by id limit 1", null);
			Cursor c = exdb.rawQuery("select * from dailytest where gameid=3 and isfinish='0' and date='"+ str_date +"'", null);
			c.moveToFirst();
			if (c.getCount() != 0)
			{
				letters = c.getString(c.getColumnIndex("letters"));

				String tfoption = letters;
				String[] first=tfoption.split(",");
				int letter_type=first.length;

				if (letter_type==1)
				{
					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setTextViewText(R.id.s_b1,letters);

				}else if (letter_type==2)
				{
					StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
					final String letters1 = tokenizerw.nextToken().trim();
					final String letters2 = tokenizerw.nextToken().trim();
					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b2,View.VISIBLE);
					expandedView.setTextViewText(R.id.s_b1,letters1);
					expandedView.setTextViewText(R.id.s_b2,letters2);
				}else if (letter_type==3)
				{

					StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
					final String letters1 = tokenizerw.nextToken().trim();
					final String letters2 = tokenizerw.nextToken().trim();
					final String letters3 = tokenizerw.nextToken().trim();
					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b2,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b3,View.VISIBLE);
					expandedView.setTextViewText(R.id.s_b1,letters1);
					expandedView.setTextViewText(R.id.s_b2,letters2);
					expandedView.setTextViewText(R.id.s_b3,letters3);
				}else if (letter_type==4)
				{

					StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
					final String letters1 = tokenizerw.nextToken().trim();
					final String letters2 = tokenizerw.nextToken().trim();
					final String letters3 = tokenizerw.nextToken().trim();
					final String letters4 = tokenizerw.nextToken().trim();
					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b2,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b3,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b4,View.VISIBLE);
					expandedView.setTextViewText(R.id.s_b1,letters1);
					expandedView.setTextViewText(R.id.s_b2,letters2);
					expandedView.setTextViewText(R.id.s_b3,letters3);
					expandedView.setTextViewText(R.id.s_b4,letters4);
				}else if (letter_type==5)
				{

					StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
					final String letters1 = tokenizerw.nextToken().trim();
					final String letters2 = tokenizerw.nextToken().trim();
					final String letters3 = tokenizerw.nextToken().trim();
					final String letters4 = tokenizerw.nextToken().trim();
					final String letters5 = tokenizerw.nextToken().trim();

					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b2,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b3,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b4,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b5,View.VISIBLE);

					expandedView.setTextViewText(R.id.s_b1,letters1);
					expandedView.setTextViewText(R.id.s_b2,letters2);
					expandedView.setTextViewText(R.id.s_b3,letters3);
					expandedView.setTextViewText(R.id.s_b4,letters4);
					expandedView.setTextViewText(R.id.s_b5,letters5);

				}else if (letter_type==6)
				{

					StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
					final String letters1 = tokenizerw.nextToken().trim();
					final String letters2 = tokenizerw.nextToken().trim();
					final String letters3 = tokenizerw.nextToken().trim();
					final String letters4 = tokenizerw.nextToken().trim();
					final String letters5 = tokenizerw.nextToken().trim();
					final String letters6 = tokenizerw.nextToken().trim();

					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b2,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b3,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b4,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b5,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b6,View.VISIBLE);
					expandedView.setTextViewText(R.id.s_b1,letters1);
					expandedView.setTextViewText(R.id.s_b2,letters2);
					expandedView.setTextViewText(R.id.s_b3,letters3);
					expandedView.setTextViewText(R.id.s_b4,letters4);
					expandedView.setTextViewText(R.id.s_b5,letters5);
					expandedView.setTextViewText(R.id.s_b6,letters6);
				}else if (letter_type==7)
				{
					StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
					final String letters1 = tokenizerw.nextToken().trim();
					final String letters2 = tokenizerw.nextToken().trim();
					final String letters3 = tokenizerw.nextToken().trim();
					final String letters4 = tokenizerw.nextToken().trim();
					final String letters5 = tokenizerw.nextToken().trim();
					final String letters6 = tokenizerw.nextToken().trim();
					final String letters7 = tokenizerw.nextToken().trim();

					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b2,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b3,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b4,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b5,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b6,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b7,View.VISIBLE);
					expandedView.setTextViewText(R.id.s_b1,letters1);
					expandedView.setTextViewText(R.id.s_b2,letters2);
					expandedView.setTextViewText(R.id.s_b3,letters3);
					expandedView.setTextViewText(R.id.s_b4,letters4);
					expandedView.setTextViewText(R.id.s_b5,letters5);
					expandedView.setTextViewText(R.id.s_b6,letters6);
					expandedView.setTextViewText(R.id.s_b7,letters7);
				}else if (letter_type==8)
				{

					StringTokenizer tokenizerw = new StringTokenizer(letters, ",");
					final String letters1 = tokenizerw.nextToken().trim();
					final String letters2 = tokenizerw.nextToken().trim();
					final String letters3 = tokenizerw.nextToken().trim();
					final String letters4 = tokenizerw.nextToken().trim();
					final String letters5 = tokenizerw.nextToken().trim();
					final String letters6 = tokenizerw.nextToken().trim();
					final String letters7 = tokenizerw.nextToken().trim();
					final String letters8 = tokenizerw.nextToken().trim();

					expandedView.setViewVisibility(R.id.s_b1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b2,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b3,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b4,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b5,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b6,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b7,View.VISIBLE);
					expandedView.setViewVisibility(R.id.s_b8,View.VISIBLE);
					expandedView.setTextViewText(R.id.s_b1,letters1);
					expandedView.setTextViewText(R.id.s_b2,letters2);
					expandedView.setTextViewText(R.id.s_b3,letters3);
					expandedView.setTextViewText(R.id.s_b4,letters4);
					expandedView.setTextViewText(R.id.s_b5,letters5);
					expandedView.setTextViewText(R.id.s_b6,letters6);
					expandedView.setTextViewText(R.id.s_b7,letters7);
					expandedView.setTextViewText(R.id.s_b8,letters8);
				}
				notification.bigContentView = expandedView;
			}else
			{
				RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_exsol_nodata);
				notification.bigContentView = nodata;
			}


		}

		NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

		String snd = sps.getString(context, "Daily_notifications");

		if (snd.equals("yes"))
		{
			nm.notify(2, notification);
		}

	}





}
