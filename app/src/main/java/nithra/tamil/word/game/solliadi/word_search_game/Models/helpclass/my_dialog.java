package nithra.tamil.word.game.solliadi.word_search_game.Models.helpclass;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import nithra.tamil.word.game.solliadi.SharedPreference;


/**
 * Created by dhanasekaran on 27/1/18.
 */

public class my_dialog extends AppCompatActivity {

    SharedPreference sp = new SharedPreference();
    MediaPlayer mediaPlayer = null;
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connec.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void media_player(Context context, int resources, String play_mode) {
        mediaPlayer = MediaPlayer.create(context, resources);

    /*    if (sp.getInt(context, "Sound") != 0) {
            if (play_mode.equals("normal")) {
                mediaPlayer.start();
            } else if (play_mode.equals("stop")) {
                mediaPlayer.stop();
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mediaPlayer.release();
                }
            });
        }*/
 if (sp.getString(context, "snd").equals("on")) {
            if (play_mode.equals("normal")) {
                mediaPlayer.start();
            } else if (play_mode.equals("stop")) {
                mediaPlayer.stop();
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mediaPlayer.release();
                }
            });
        }

    }
    public static int[] getXY(View view) {

        int[] viewLocation = new int[2];
        view.getLocationInWindow(viewLocation);

        return viewLocation;
    }
}
