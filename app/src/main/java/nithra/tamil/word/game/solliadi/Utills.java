package nithra.tamil.word.game.solliadi;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by NITHRA-G6 on 12/19/2015.
 */
public class Utills {
    public static final String DATE_TIME_FORMAT_WS = "dd/MM/yyyy HH:mm:ss";
    public static final String TAG_BACKUP_LOCATION = "/Nithra/Tamil_Game/Backup/";
    public static final String MAIN_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + TAG_BACKUP_LOCATION;
    public static Date getCurrentDateTime(String format){
        Date date = null;
        try {
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => "+c.getTime());

            SimpleDateFormat df = new SimpleDateFormat(format);
            String formattedDate = df.format(c.getTime());
            date = c.getTime();
        } catch (Exception e) {
            //  printValue("getCurrentDateTime", e.toString());
        }
        return date;
    }
    public static void createStorageLocation() {
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + Utills.TAG_BACKUP_LOCATION);
            if (!dir.exists())
                dir.mkdirs();
        } catch (Exception e) {
            //Utills.printValue("HomeActivity - createStorageLocation()", e.toString());
        }
    }
}
