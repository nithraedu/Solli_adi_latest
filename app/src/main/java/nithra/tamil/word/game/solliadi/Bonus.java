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
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

public class Bonus extends BroadcastReceiver {
    final public static String ONE_TIME = "onetime";
    SQLiteDatabase db1;
    DataBaseHelper myDbHelper;

    @Override
    public void onReceive(Context context, Intent intent) {

        db1 = context.openOrCreateDatabase("Solli_Adi", Context.MODE_PRIVATE, null);

        myDbHelper = new DataBaseHelper(context);
        boolean isBooted;

        final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

        String action = intent.getAction();
        isBooted = action != null && action.equalsIgnoreCase(BOOT_ACTION);

        Cursor cfx = myDbHelper.getQry("SELECT * FROM score ");
        cfx.moveToFirst();
        if (cfx.getCount() != 0) {
            int k1 = cfx.getInt(cfx.getColumnIndexOrThrow("coins"));
            if (k1 <= 100) {

                if (!isBooted) {

                    //createNotification2(context);
                    createNotification_double(context);
                }
            }
        }

    }


    public void createNotification_double(final Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NotificationHelper.PRIMARY_CHANNEL);
        Intent i = new Intent(context, New_Main_Activity.class);
        //i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("coins", "ok");


        PendingIntent intent = PendingIntent.getActivity(context, 5, i, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);
        builder.setTicker(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.noti_backicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.bones_norma);

        int imageid1 = context.getResources().getIdentifier("testing_1", "drawable", context.getPackageName());
        contentView.setImageViewResource(R.id.img, imageid1);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.bones_expand);
            int imageid = context.getResources().getIdentifier("testing", "drawable", context.getPackageName());
            contentView.setImageViewResource(R.id.img, imageid);
            notification.bigContentView = expandedView;
        }

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(5, notification);

    }

}
