package nithra.tamil.word.game.solliadi;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestAdapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private final DataBaseHelper mDbHelper;
    private SQLiteDatabase mDb;


    public TestAdapter(Context context) {
        this.mContext = context;

        mDbHelper = new DataBaseHelper(mContext);
    }

    public TestAdapter createDatabase() throws SQLException {

        mDbHelper.createDataBase();

        return this;
    }

    public TestAdapter open() throws SQLException {
        try {


            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();


        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException);
            throw mSQLException;
        }
        return this;
    }

    public void close() {

        mDbHelper.close();

    }
}