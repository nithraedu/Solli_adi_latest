package nithra.tamil.word.game.solliadi;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Newgame_DataBaseHelper4 extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper"; // Tag just for the LogCat
    private static final String DB_NAME = "Newgames4.db";// Database name
    // window
    // destination path (location) of our database on device
    private static String DB_PATH = "";
    private final Context mContext;
    Cursor c;
    SharedPreferences mPreferences;
    private SQLiteDatabase mDataBase;

    public Newgame_DataBaseHelper4(Context context) {
        super(context, DB_NAME, null, 3);// 1? its Database Version
        DB_PATH = context.getDatabasePath(DB_NAME).getPath();
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

    public Cursor getQry(String Qry) {
        try {
            Newgame_DataBaseHelper4 newgame_dataBaseHelper4;
            newgame_dataBaseHelper4 = new Newgame_DataBaseHelper4(mContext);
            SQLiteDatabase sq = this.getReadableDatabase();
            sq = newgame_dataBaseHelper4.getWritableDatabase();
            sq = newgame_dataBaseHelper4.getReadableDatabase();
            c = sq.rawQuery(Qry, null);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
        return c;
    }

    public void executeSql(String Qry) {
        try {
            Newgame_DataBaseHelper4 newgame_dataBaseHelper4;
            newgame_dataBaseHelper4 = new Newgame_DataBaseHelper4(mContext);
            SQLiteDatabase sq = this.getReadableDatabase();
            sq = newgame_dataBaseHelper4.getWritableDatabase();
            sq = newgame_dataBaseHelper4.getReadableDatabase();
            sq.execSQL(Qry);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }


    }

    public void insert_data(String table, String n_val, ContentValues cv) {
        SQLiteDatabase sq = this.getReadableDatabase();
        sq.insert(table, null, cv);

    }

    public void createDataBase() {
        // If database not exists copy it from the assets

        boolean mDataBaseExist = checkDataBase();

        // mDataBaseExist=false;
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
        } catch (IOException mIOException) {
            throw new Error("ErrorCopyingDataBase");
        }
    }

    public void createDataBaseIFexits() {
        // If database not exists copy it from the assets

        boolean mDataBaseExist = checkDataBase();

        // mDataBaseExist=false;

        this.getReadableDatabase();
        this.close();
        try {
            // Copy the database from assests
            copyDataBase();
            Log.e(TAG, "createDatabase database created");
        } catch (IOException mIOException) {
            throw new Error("ErrorCopyingDataBase");
        }

    }

    // Check that the database exists here: /data/data/your package/databases/Da
    // Name
    public boolean checkDataBase() {

        File dbFile = new File(DB_PATH + DB_NAME);
        // Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    // Copy the database from assets
    public void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    // Open the database, so we can query it
    public void openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        // Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }
}
