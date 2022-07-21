package nithra.tamil.word.game.solliadi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {

		String status = NetworkUtil.getConnectivityStatusString(context);
		SharedPreferences level = context.getSharedPreferences("level",
				context.MODE_PRIVATE);
		int isvalid = level.getInt("isvalid", 0);
		
		if (isvalid != 0) {
			GcmKeepAlive gcmKeepAlive = new GcmKeepAlive(context);
			gcmKeepAlive.broadcastIntents();
		//	toast(status+"--heart beat connect",context);
		}
		else{
		//	toast(status,context);
		}
 	}
	
	public  void toast(String str,Context context) {
 		Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
