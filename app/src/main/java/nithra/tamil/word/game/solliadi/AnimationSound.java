package nithra.tamil.word.game.solliadi;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by nithrs on 27/4/16.
 */
public class AnimationSound {
    MediaPlayer mpsound;

    AnimationSound(Context context, int id) {

// Creates an instance of the mediaplayer using the passed mp3 file

        mpsound = MediaPlayer.create(context, id);

    }

// When this method is called, the sound is played in a loop until the stopsound method is called

    public void startsound() {
        if (mpsound == null) {

        }
        mpsound.start();
        mpsound.setLooping(true);
    }

// When this method is called , the playing of the sound is stopped

    public void stopsound() {
        if (mpsound != null) {
            if (mpsound.isPlaying()) {
                mpsound.stop();
                mpsound.setLooping(true);
            }

//Finally release everything and clear out the mediaplayer object

            mpsound.release();
            mpsound = null;
        }

    }
}

