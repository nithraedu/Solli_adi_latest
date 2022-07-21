package nit_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper1 extends SQLiteOpenHelper {
	private static String TAG = "DataBaseHelper"; // Tag just for the LogCat
													// window
	// destination path (location) of our database on device
	///private static String DB_PATH = "";
	private static String DB_NAME = "app_db.db";// Database name
	private SQLiteDatabase mDataBase;
	private final Context mContext;
	Cursor c;
	SharedPreferences mPreferences;

	public DataBaseHelper1(Context context) {
		super(context, DB_NAME, null, 3);// 1? its Database Version
		
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	public int getQryTestQuery(String Qry) {

		try {
			SQLiteDatabase sq = this.getReadableDatabase();
			c = sq.rawQuery(Qry, null);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public Cursor getQry(String Qry) {
		SQLiteDatabase sq = this.getReadableDatabase();
		c = sq.rawQuery(Qry, null);
		return c;
	}

	public Cursor getQry1(String Qry) {
		try {
			SQLiteDatabase sq = this.getReadableDatabase();
			c = sq.rawQuery(Qry, null);
			return c;
		} catch (Exception e) {

		}
		return c;
	}

	public void executeSql(String Qry) {

		SQLiteDatabase sq = this.getReadableDatabase();
		sq.execSQL(Qry);

	}

	public void executeSql1(String Qry) {
		try {
			SQLiteDatabase sq = this.getReadableDatabase();
			sq.execSQL(Qry);
		} catch (Exception e) {

		}
	}

	public void update_Bookmark(String tableName, String questionId,
			String bookMark) {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("bookmark", bookMark);
		sq.update(tableName, values, "ques_id='" + questionId + "'", null);
	}

	public void update_questionTab_by_user_ans(String tableName,
			String questionId, String userOption) {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("user_ans", userOption);
		sq.update(tableName, values, "ques_id='" + questionId + "'", null);
	}

	public void update_daily_test_ques(String tableName, String questionId,
			int updateDailyTest) {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("daily_test_no", updateDailyTest + "");
		sq.update(tableName, values, "ques_id='" + questionId + "'", null);
	}

/*
	public void createDataBase() throws IOException {
		// If database not exists copy it from the assets

		boolean mDataBaseExist = checkDataBase();

		// mDataBaseExist=false;
		String DB_PATH = "/data/data/" + mContext.getPackageName() + "/databases/";
		if (mDataBaseExist) {

			File dbFile = new File(DB_PATH + DB_NAME);
			dbFile.delete();
		}

		// if (!mDataBaseExist) {
		this.getReadableDatabase();
		this.close();
		try {
			// Copy the database from assests
			copyDataBase();
			Log.e(TAG, "createDatabase database created");
			// Toast.makeText(mContext,
			// "aptitudequestiondb database copied",
			// Toast.LENGTH_LONG).show();
		} catch (IOException mIOException) {
			throw new Error("ErrorCopyingDataBase");
		}
		// } else {
		// // Toast.makeText(mContext, "aptitudequestiondb Already exits",
		// // Toast.LENGTH_LONG).show();
		// }
	}*/


	public void createDataBase() throws IOException {
		// If database not exists copy it from the assets

		boolean mDataBaseExist = checkDataBase();

		// mDataBaseExist=false;
		String DB_PATH = "/data/data/" + mContext.getPackageName() + "/databases/";
		if (mDataBaseExist) {

			File dbFile = new File(DB_PATH + DB_NAME);
			dbFile.delete();
		}

		// if (!mDataBaseExist) {
		this.getReadableDatabase();
		this.close();
		try {
			// Copy the database from assests
			copyDataBase();
			Log.e(TAG, "createDatabase database created");
			// Toast.makeText(mContext,
			// "aptitudequestiondb database copied",
			// Toast.LENGTH_LONG).show();
		} catch (IOException mIOException) {
		throw new RuntimeException(mIOException);
	}/*catch (IOException mIOException) {
			throw new Error("ErrorCopyingDataBase");
		}*/
		// } else {
		// // Toast.makeText(mContext, "aptitudequestiondb Already exits",
		// // Toast.LENGTH_LONG).show();
		// }


	}

	public void createDataBaseIFexits() throws IOException {
		// If database not exists copy it from the assets

		boolean mDataBaseExist = checkDataBase();

		// mDataBaseExist=false;

		this.getReadableDatabase();
		this.close();
		try {
			// Copy the database from assests
			copyDataBase();
			Log.e(TAG, "createDatabase database created");
			// Toast.makeText(mContext,
			// "aptitudequestiondb database copied",
			// Toast.LENGTH_LONG).show();
		} catch (IOException mIOException) {
			throw new Error("ErrorCopyingDataBase");
		}

	}

	// Check that the database exists here: /data/data/your package/databases/Da
	// Name
	public  boolean checkDataBase() {
		String DB_PATH = "/data/data/" + mContext.getPackageName() + "/databases/";
		File dbFile = new File(DB_PATH + DB_NAME);
		// Log.v("dbFile", dbFile + "   "+ dbFile.exists());
		return dbFile.exists();
	}

	// Copy the database from assets
	public void copyDataBase() throws IOException {
		String DB_PATH = "/data/data/" + mContext.getPackageName() + "/databases/";
		InputStream mInput = mContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream mOutput = new FileOutputStream(outFileName);
		byte[] mBuffer = new byte[1024];
	//	int mLength;
		while ((mInput.read(mBuffer)) > 0) {
			mOutput.write(mBuffer);
		}
		mOutput.flush();
		mOutput.close();
		mInput.close();
	}

	// Open the database, so we can query it
	public boolean openDataBase() throws SQLException {
		String DB_PATH = "/data/data/" + mContext.getPackageName() + "/databases/";
		String mPath = DB_PATH + DB_NAME;
		// Log.v("mPath", mPath);
		mDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
		// mDataBase = SQLiteDatabase.openDatabase(mPath, null,
		// SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		return mDataBase != null;
	}

	@Override
	public synchronized void close() {
		if (mDataBase != null)
			mDataBase.close();
		super.close();
	}

	// below is database helper one

	public void toast(String str) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public void insertGCM_Message(String title, String message, String date,
			String gcm_show, String gcm_isread) {
		// TODO Auto-generated method stub
		SQLiteDatabase sq = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put("title", title);
		cv.put("message", message);
		cv.put("date", date);
		cv.put("gcm_show", gcm_show);
		cv.put("gcm_isread", gcm_isread);

		sq.insert("GCM_Data", null, cv);

		System.out.println("Data added to GCM_Data table");
		sq.close();
	}

	public void update_byNotificationId(int NotificationMsgId) {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("gcm_isread", "1");
		sq.update("gcm_data", values, "id=" + NotificationMsgId, null);
	}

	public void update_byID(String v1, String id, String id1) {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("name", v1);
		sq.update("topics", values, "name='" + id + "' and tablename='" + id1
				+ "'", null);
	}

	// public void update_practice_last_ques(String topicName, String
	// quesposition) {
	// ContentValues values = new ContentValues();
	// SQLiteDatabase sq = this.getReadableDatabase();
	// values.put("quesposition", quesposition);
	// sq.update("topics", values, "name='" + topicName + "'", null);
	// }

	public void update_topics() {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("quesposition", "0");
		sq.update("topics", values, "", null);
	}

	public void update_IfUserRateedOurApp() {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("isactive", "1");
		sq.update("topics", values, "isactive='0'", null);
	}

	public void DelQry() {

		mPreferences = mContext.getSharedPreferences("", mContext.MODE_PRIVATE);
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("isactive", "1");
		sq.delete("daily_test", "date='" + mPreferences.getString("date", "")
				+ "'", null);
	}

	public void insert_daily_test_result(int correctAnswer, int wrongAnswer,
			int skipped, int notattempt, int totques, String randomArray) {
		// TODO Auto-generated method stub
		SQLiteDatabase sq = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		mPreferences = mContext.getSharedPreferences("", mContext.MODE_PRIVATE);

		values.put("date", mPreferences.getString("date", ""));
		values.put("correctans", correctAnswer);
		values.put("wrong", wrongAnswer);
		values.put("skipped", skipped);
		values.put("notattempt", notattempt);
		values.put("totques", totques);
		values.put("random_array_value", randomArray);
		sq.insert("daily_test", null, values);

		System.out.println("Data added to daily_test table");
		sq.close();
	}

	public void update_daily_test_result(int correctAnswer, int wrongAnswer,
			int skipped, int notattempt, int totques, String randomArray) {
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();

		mPreferences = mContext.getSharedPreferences("", mContext.MODE_PRIVATE);
		values.put("correctans", correctAnswer);
		values.put("wrong", wrongAnswer);
		values.put("skipped", skipped);
		values.put("notattempt", notattempt);
		values.put("totques", totques);
		values.put("random_array_value", randomArray);
		sq.update("daily_test", values,
				"date='" + mPreferences.getString("date", "") + "'", null);
	}

	public void updateTopics(String tab_name, int correctAnswer,
			int wrongAnswer, int skipped, int notattempt) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		SQLiteDatabase sq = this.getReadableDatabase();
		values.put("correctans", correctAnswer);
		values.put("wrong", wrongAnswer);
		values.put("skipped", skipped);
		values.put("notattempt", notattempt);
		values.put("attended", "1");
		sq.update("topics", values, "tablename='" + tab_name + "'", null);
		System.out.println("Updated : ");
	}
}
