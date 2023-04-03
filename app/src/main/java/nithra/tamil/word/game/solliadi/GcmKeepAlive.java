package nithra.tamil.word.game.solliadi;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

public class GcmKeepAlive {

    protected final Context mContext;
    protected final Intent gTalkHeartBeatIntent;
    protected final Intent mcsHeartBeatIntent;
    protected CountDownTimer timer;

    public GcmKeepAlive(Context context) {
        mContext = context;
        gTalkHeartBeatIntent = new Intent(
                "com.google.android.intent.action.GTALK_HEARTBEAT");
        mcsHeartBeatIntent = new Intent(
                "com.google.android.intent.action.MCS_HEARTBEAT");
    }

    public void broadcastIntents() {
        System.out.println("sending heart beat to keep gcm alive");
        mContext.sendBroadcast(gTalkHeartBeatIntent);
        mContext.sendBroadcast(mcsHeartBeatIntent);
    }

}