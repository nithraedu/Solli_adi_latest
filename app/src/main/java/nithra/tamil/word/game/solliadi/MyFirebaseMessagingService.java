package nithra.tamil.word.game.solliadi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import nithra.tamil.word.game.solliadi.Price_solli_adi.Game_Status;
import nithra.tamil.word.game.solliadi.Price_solli_adi.Price_Login;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    SQLiteDatabase myDB;
    SharedPreference sharedPreference;
    int iddd;
    int isclose = 0;
    String game_mode = "";
    private NotificationHelper noti;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        myDB = openOrCreateDatabase("myDB", 0, null);
        noti = new NotificationHelper(this);
        String tablenew = "noti_cal";
        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + tablenew + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR," +
                "bm VARCHAR,ntype VARCHAR,url VARCHAR);");
        sharedPreference = new SharedPreference();
        if (remoteMessage.getData().size() > 0) {

            try {
                Log.e("Data Payload: ", remoteMessage.getData().toString());
                Map<String, String> params = remoteMessage.getData();
                JSONObject object = new JSONObject(params);

                Log.e("JSON_OBJECT", object.toString());
                //JSONObject json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(object);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception: ", e.getMessage());
            }
        }


    }

    @Override
    public void onNewToken(String refreshedToken) {
        super.onNewToken(refreshedToken);
        storeRegIdInPref(refreshedToken);
        sendRegistrationToServer(refreshedToken);
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);

        System.out.println("Token : " + refreshedToken);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
        System.out.println("tokens : " + token);
        SharedPreference sharedPreference = new SharedPreference();
        sharedPreference.putString(this, "token", token);
        ServerUtilities.gcmpost(token, Utils.android_id(this), Utils.getversionname(this), Utils.getversioncode(this), this);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }

    private void handleDataMessage(JSONObject data) {
        /*Log.e(TAG, "push json: " + json.toString());*/
        /*JSONObject data = null;*/

        /*data = json.getJSONObject("data");*/
        try {
            String message = data.getString("message");
            String title = data.getString("title");
            String date = data.getString("date");
            String time = data.getString("time");
            String type = data.getString("type");


            String bm = data.getString("bm");

            String ntype = data.getString("ntype");
            String url = data.getString("url");
            String pac = data.getString("pac");
            int intent_id = (int) System.currentTimeMillis();
            if (!sharedPreference.getString(getApplicationContext(), "old_msg").equals(message) || !sharedPreference.getString(getApplicationContext(), "old_tit").equals(title)) {
                sharedPreference.putString(getApplicationContext(), "old_msg", message);
                sharedPreference.putString(getApplicationContext(), "old_tit", title);
                try {
                    if (type.equals("s")) {

                    }
                } catch (Exception e) {
                    type = "s";
                }
                try {
                    if (pac.equals("s")) {

                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    pac = "no";
                }
                try {
                    if (bm.equals("s")) {

                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    bm = "no";
                }
                try {
                    if (ntype.equals("s")) {

                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    ntype = "no";
                }

                title = URLDecoder.decode(title, StandardCharsets.UTF_8);

                System.out.println("============================type" + type);
                if (type.equals("s")) {
                    System.out.println("str_titlestr_title" + message);
                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                    myDB.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values " +
                            "('" + title + "','" + message + "','" + date + "','" + time + "','" + isclose + "','s','" + bm + "','" + ntype + "','" + url + "');");
                    sharedPreference.putInt(this, "typee", 0);
                    Cursor c = myDB.rawQuery("select id from noti_cal", null);
                    c.moveToLast();
                    iddd = c.getInt(0);
                    myDB.close();
                    if (sharedPreference.getBoolean(this, "notiS_onoff")) {
                        if (ntype.equals("bt")) {
                            noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else if (ntype.equals("bi")) {
                            noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else {
                            noti.Notification_bm(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        }
                    }
                } else if (type.equals("h")) {

                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);

                    if (ntype.equals("bt")) {
                        noti.Notification_custom(0, title, message, url, "bt", bm, sharedPreference
                                .getInt(this, "sund_chk1"), New_Main_Activity.class);
                    } else if (ntype.equals("bi")) {
                        noti.Notification_custom(0, title, message, url, "bi", bm, sharedPreference
                                .getInt(this, "sund_chk1"), New_Main_Activity.class);
                    }
                } else if (type.equals("st")) {
                    System.out.println("str_titlestr_title" + message);
                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                    myDB.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values " +
                            "('" + title + "','" + message + "','" + date + "','" + time + "','" + isclose + "','s','" + bm + "','" + ntype + "','" + url + "');");
                    sharedPreference.putInt(this, "typee", 0);
                    Cursor c = myDB.rawQuery("select id from noti_cal", null);
                    c.moveToLast();
                    iddd = c.getInt(0);
                    Cursor c1 = myDB.rawQuery("select id from noti_cal where isclose = '0'", null);
                    if (c1.getCount() != 0) {

                    } else {

                    }
                    myDB.close();
                    if (sharedPreference.getBoolean(this, "notiS_onoff")) {
                        if (ntype.equals("bt")) {
                            noti.Notification_bm(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else if (ntype.equals("bi")) {
                            noti.Notification_bm(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else {
                            noti.Notification_bm(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        }
                    }
                } else if (type.equals("w")) {
                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                    myDB.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values " +
                            "('" + title + "','" + message + "','" + date + "','" + time + "','" + isclose + "','w','" + bm + "','" + ntype + "','" + url + "');");
                    sharedPreference.putInt(this, "typee", 0);
                    Cursor c = myDB.rawQuery("select id from noti_cal", null);
                    c.moveToLast();
                    iddd = c.getInt(0);
                    Cursor c1 = myDB.rawQuery("select id from noti_cal where isclose = '0'", null);
                    if (c1.getCount() != 0) {

                    } else {

                    }
                    myDB.close();
                    if (sharedPreference.getBoolean(this, "notiS_onoff")) {
                        if (ntype.equals("bt")) {
                            noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else if (ntype.equals("bi")) {
                            noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else {
                            noti.Notification_bm(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        }
                    }
                } else if (type.equals("ns")) {
                    myDB.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values " +
                            "('" + title + "','" + message + "','" + date + "','" + time + "','" + isclose + "','ns','" + bm + "','" + ntype + "','" + url + "');");
                    Cursor c = myDB.rawQuery("select id from noti_cal", null);
                    c.moveToLast();
                    iddd = c.getInt(0);

                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                    Cursor c1 = myDB.rawQuery("select id from noti_cal where isclose = '0'", null);
                    if (c1.getCount() != 0) {

                    } else {

                    }
                    myDB.close();
                } else if (type.equals("ins")) {
                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                    if (sharedPreference.getBoolean(this, "notiS_onoff")) {
                        noti.Notification1(intent_id, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                    }
                } else if (type.equals("og")) {


                    System.out.println("============================gtype" + game_mode);

                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);

                    if (ntype.equals("bt")) {
                        sharedPreference.putString(this, "og_game_on", "yes");
                        noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), New_Main_Activity.class);
                    } else if (ntype.equals("bi")) {
                        sharedPreference.putString(this, "og_game_on", "yes");
                        noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), New_Main_Activity.class);
                    } else {
                        sharedPreference.putString(this, "og_game_on", "yes");
                        noti.Notification_bm(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), New_Main_Activity.class);
                    }

                } else if (type.equals("sp")) {
                    System.out.println("============================gtype" + game_mode);

                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);

                    if (ntype.equals("bt")) {
                        sharedPreference.putString(this, "sd_prize_st", "yes");
                        if (sharedPreference.getString(this, "price_registration").equals("com")) {
                            noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), Game_Status.class);
                        } else {
                            if (sharedPreference.getString(this, "otp_verify").equals("yes")) {
                                noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), LoginActivity.class);
                            } else {
                                noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), Price_Login.class);
                            }

                        }
                    } else if (ntype.equals("bi")) {
                        sharedPreference.putString(this, "sd_prize_st", "yes");
                        if (sharedPreference.getString(this, "price_registration").equals("com")) {
                            noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), Game_Status.class);
                        } else {
                            if (sharedPreference.getString(this, "otp_verify").equals("yes")) {
                                noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), LoginActivity.class);
                            } else {
                                noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), Price_Login.class);
                            }
                        }
                    } else {
                        sharedPreference.putString(this, "sd_prize_st", "yes");
                        if (sharedPreference.getString(this, "price_registration").equals("com")) {
                            noti.Notification_bm(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), Game_Status.class);
                        } else {
                            if (sharedPreference.getString(this, "otp_verify").equals("yes")) {
                                noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), LoginActivity.class);
                            } else {
                                noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), Price_Login.class);
                            }
                        }
                    }
                   /* if (ntype.equals("bt")) {
                        if (sharedPreference.getString(this, "price_registration").equals("com")) {
                            noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), Game_Status.class);
                        } else {
                            noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), Price_Login.class);
                        }
                    } else if (ntype.equals("bi")) {

                        if (sharedPreference.getString(this, "price_registration").equals("com")) {
                            noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), Game_Status.class);
                        } else {
                            noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), Price_Login.class);
                        }
                    } else {
                        if (sharedPreference.getString(this, "price_registration").equals("com")) {
                            noti.Notification_custom(iddd, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), Game_Status.class);
                        } else {
                            noti.Notification_custom(iddd, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), Price_Login.class);
                        }
                    }*/
                } else if (type.equals("ap")) {
                    if (appInstalledOrNot(pac)) {

                    } else {

                        if (ntype.equals("n")) {
                            bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                            noti.Notification1(intent_id, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else if (ntype.equals("bt")) {
                            bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                            noti.Notification1(intent_id, title, message, url, "bt", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else if (ntype.equals("bi")) {
                            bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                            noti.Notification1(intent_id, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        } else if (ntype.equals("w")) {
                            bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                            noti.Notification1(intent_id, title, message, url, "bi", bm, sharedPreference.getInt(this, "sund_chk1"), ST_Activity.class);
                        }
                    }
                } else if (type.equals("rao")) {

                   /* msgg = message;
                    titt = title;
                    bmmm = bm;*/
                    bm = URLDecoder.decode(bm, StandardCharsets.UTF_8);
                    if (sharedPreference.getInt(this, "purchase_ads") == 0) {
                        sharedPreference.putString(this, "ads_dialog", "on");
                        if (ntype.equals("bt")) {
                            noti.Notification_rao(type, 0, title, message, url, "bt", bm, New_Main_Activity.class);
                        } else if (ntype.equals("bi")) {
                            noti.Notification_rao(type, 0, title, message, url, "bi", bm, New_Main_Activity.class);
                        }
                    }
                } else if (type.equals("u")) {
                    sharedPreference.putInt(this, "gcmvcode", Integer.parseInt(message));
                    sharedPreference.putInt(this, "isvupdate", 1);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}