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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Environment;
import androidx.core.app.NotificationCompat;
import android.text.format.Time;
import android.view.View;
import android.widget.RemoteViews;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;


public class Dailytest_p extends BroadcastReceiver {
	final public static String ONE_TIME = "onetime";
	SharedPreference sps=new SharedPreference();
	SQLiteDatabase exdb;
	String imid;
	int image_type;
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

		if (armTodayOrTomo1.equals("tomo")){
			calendar.add(Calendar.DAY_OF_MONTH, 1);
				
		}
		am.set(AlarmManager.RTC, calendar.getTimeInMillis(), pi);
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
		System.out.println("A4----------" + armTodayOrTomo1);
		return armTodayOrTomo1;
	}

	public void CancelAlarm(Context context) {
		Intent intent = new Intent(context, Dailytest_p.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 3, intent, Utils.getPendingIntent());//3
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
		sender.cancel();
		
		
	}
/*
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

		Intent intent = new Intent(context, Picture_Game_Hard.class);
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
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 3,intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(context,"இன்றைய படம் பார்த்து கண்டுபிடி", "தொட்டு தொடரவும்", pendingIntent);

		String snd = sps.getString(context, "Daily_notifications");

		if (snd.equals("yes"))
		{
			notificationManager.notify(3, notification);
		}
		//notificationManager.notify(3, notification);


	}*/

	public void createNotification_double(final Context context) {



		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Intent i = new Intent(context, Picture_Game_Hard.class);
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

		PendingIntent intent = PendingIntent.getActivity(context, 3, i,PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(intent);
		builder.setTicker(context.getResources().getString(R.string.app_name));
		builder.setSmallIcon(R.drawable.noti_backicon);
		builder.setAutoCancel(true);



		Notification notification = builder.build();
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.daily_normal_pic);

		int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
		contentView.setImageViewResource(R.id.img,imageid1);
		notification.contentView = contentView;

		if (Build.VERSION.SDK_INT >= 16) {
			// Inflate and set the layout for the expanded notification view
			RemoteViews expandedView =	new RemoteViews(context.getPackageName(), R.layout.daily_expand_picl);

			Random rn = new Random();
			random = rn.nextInt(max - min + 1) + min;

			if (random == 1) {
				expandedView.setTextViewText(R.id.pic_gametxt,"படங்களோடு போட்டியிட்டு வார்த்தையை வெல்லுங்கள் !");
			} else if (random == 2) {
				expandedView.setTextViewText(R.id.pic_gametxt,"இந்தப் படங்கள் பேசும் வார்த்தை உங்களுக்கு கேட்கிறதா?");
			} else if (random == 3) {
				expandedView.setTextViewText(R.id.pic_gametxt,"பார்த்ததும் பதில் கண்டுபிடிக்க முடியுமா உங்களால்?");
			}

			Cursor c = exdb.rawQuery("select * from dailytest where gameid=1 and isfinish='0' and date='" + str_date + "'", null);
			//Cursor c = exdb.rawQuery("select * from maintable where gameid=1 and isfinish='0' order by id limit 1", null);
			c.moveToFirst();
		    if (c.getCount() != 0) {

				imid = c.getString(c.getColumnIndexOrThrow("imagename"));
				String tfoptiona = imid;
				String[] firsta = tfoptiona.split(",");
				int i_type = firsta.length;
				image_type = i_type;


				if (image_type == 1) {
					expandedView.setViewVisibility(R.id.im_1,View.VISIBLE);
					String fullPath = Environment.getExternalStorageDirectory()
							.getAbsolutePath()
							+ "/Nithra/solliadi/";

					File file = new File(fullPath + imid + "");
					if(file.exists()){
						Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + imid + "");
						expandedView.setImageViewBitmap(R.id.im_1,bitimg1);
						notification.bigContentView = expandedView;
					}else {
						RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
						notification.bigContentView = nodata;
					}

				}else if (image_type == 2) {
					StringTokenizer word = new StringTokenizer(imid, ",");
					String word1 = word.nextToken();
					String word2 = word.nextToken();
					expandedView.setViewVisibility(R.id.im_1,View.VISIBLE);
					expandedView.setViewVisibility(R.id.im_2,View.VISIBLE);
					String fullPath = Environment.getExternalStorageDirectory()
							.getAbsolutePath()
							+ "/Nithra/solliadi/";
					File file = new File(fullPath + word1 + "");
					File file2 = new File(fullPath + word2 + "");
					if(file.exists() && file2.exists()){
						Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
						Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
						expandedView.setImageViewBitmap(R.id.im_1,bitimg1);
						expandedView.setImageViewBitmap(R.id.im_2,bitimg2);
						notification.bigContentView = expandedView;
					}else {
						RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
						notification.bigContentView = nodata;
					}

				}else if (image_type == 3) {
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
					if(file.exists() && file2.exists() && file3.exists()){
						Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
						Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
						Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
						expandedView.setImageViewBitmap(R.id.im_1, bitimg1);
						expandedView.setImageViewBitmap(R.id.im_2, bitimg2);
						expandedView.setImageViewBitmap(R.id.im_3, bitimg3);
						notification.bigContentView = expandedView;
					}else {
						RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
						notification.bigContentView = nodata;
					}

				}else if (image_type == 4) {
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
					if(file.exists() && file2.exists() && file3.exists() && file4.exists()){
						Bitmap bitimg1 = BitmapFactory.decodeFile(fullPath + word1 + "");
						Bitmap bitimg2 = BitmapFactory.decodeFile(fullPath + word2 + "");
						Bitmap bitimg3 = BitmapFactory.decodeFile(fullPath + word3 + "");
						Bitmap bitimg4 = BitmapFactory.decodeFile(fullPath + word4 + "");
						expandedView.setImageViewBitmap(R.id.im_1, bitimg1);
						expandedView.setImageViewBitmap(R.id.im_2, bitimg2);
						expandedView.setImageViewBitmap(R.id.im_3, bitimg3);
						expandedView.setImageViewBitmap(R.id.im_3, bitimg4);
						notification.bigContentView = expandedView;
					}else {
						RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
						notification.bigContentView = nodata;
					}

				}

		    }else {
				RemoteViews nodata = new RemoteViews(context.getPackageName(), R.layout.daily_expic_nodata);
				notification.bigContentView = nodata;
			}



		}

		NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		String snd = sps.getString(context, "Daily_notifications");

		if (snd.equals("yes"))
		{
			nm.notify(3, notification);
		}

	}

	public Bitmap buildUpdate(String time)
	{
		Bitmap myBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_4444);
		Canvas myCanvas = new Canvas(myBitmap);
		Paint paint = new Paint();
		//Typeface clock = Typeface.createFromAsset(context.getAssets(), "fonts/bubblegum.otf");
		//paint.setAntiAlias(true);
		//paint.setSubpixelText(true);
		//paint.setTypeface(clock);
		//paint.setStyle(Paint.Style.FILL);
		paint.setTextSize(20);

		paint.setColor(Color.WHITE);
		paint.setFakeBoldText(true);
		paint.setTextAlign(Paint.Align.CENTER);
		myCanvas.drawText(time, 100, 140, paint);
		return myBitmap;
	}

}
