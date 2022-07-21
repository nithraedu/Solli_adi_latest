package nithra.tamil.word.game.solliadi;

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
import android.widget.RemoteViews;

public class Bonus extends BroadcastReceiver {
	final public static String ONE_TIME = "onetime";
	SQLiteDatabase db1;
	DataBaseHelper myDbHelper;
	@Override
	public void onReceive(Context context, Intent intent) {

		db1 = context.openOrCreateDatabase("Solli_Adi", context.MODE_PRIVATE, null);

		myDbHelper = new DataBaseHelper(context);
		Boolean isBooted = false;

		final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

		String action = intent.getAction();
		if (action != null && action.equalsIgnoreCase(BOOT_ACTION)) {
			isBooted = true;

		} else {
			isBooted = false; 
		}

		Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
		cfx.moveToFirst();
		if (cfx.getCount()!=0){
			int k1=cfx.getInt(cfx.getColumnIndex("coins"));
			if(k1<=100) {

				if (!isBooted) {

					//createNotification2(context);
					createNotification_double(context);
				}
			}
		}

	}


	public void createNotification2(final Context context) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Intent i = new Intent(context, MainActivity.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("coins", "ok");



		PendingIntent intent = PendingIntent.getActivity(context, 5, i,PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(intent);
		builder.setTicker(context.getResources().getString(R.string.app_name));
		builder.setSmallIcon(R.drawable.noti_backicon);
		builder.setAutoCancel(true);

		Notification notification = builder.build();
	/*	RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.remote_bonous);
		//final String time = DateFormat.getTimeInstance().format(new Date()).toString();
		//final String text = context.getResources().getString(R.string.action_settings, time);
		contentView.setTextViewText(R.id.textview, "");
		notification.contentView = contentView;

		if (Build.VERSION.SDK_INT >= 16) {
			// Inflate and set the layout for the expanded notification view
			RemoteViews expandedView =
					new RemoteViews(context.getPackageName(), R.layout.remote_bonous1);
			notification.bigContentView = expandedView;
		}*/
		//notification.setLatestEventInfo(context, "கூடுதல் நாணயங்கள் ", "100", intent);
		NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		nm.notify(5, notification);







	}


	public void createNotification_double(final Context context) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Intent i = new Intent(context, MainActivity.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("coins", "ok");


		PendingIntent intent = PendingIntent.getActivity(context, 5, i,PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(intent);
		builder.setTicker(context.getResources().getString(R.string.app_name));
		builder.setSmallIcon(R.drawable.noti_backicon);
		builder.setAutoCancel(true);

		Notification notification = builder.build();
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.bones_norma);

		int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
		contentView.setImageViewResource(R.id.img,imageid1);
		notification.contentView = contentView;

		if (Build.VERSION.SDK_INT >= 16) {
			// Inflate and set the layout for the expanded notification view
			RemoteViews expandedView =	new RemoteViews(context.getPackageName(), R.layout.bones_expand);
			int imageid = context.getResources().getIdentifier("testing", "drawable", context.getPackageName());
			contentView.setImageViewResource(R.id.img,imageid);
			notification.bigContentView = expandedView;
		}

		NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		nm.notify(5, notification);

	}

}
