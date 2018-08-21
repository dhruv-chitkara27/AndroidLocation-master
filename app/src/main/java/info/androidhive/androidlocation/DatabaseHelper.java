package info.androidhive.androidlocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    final public static String dbName = "location.db";
    final public static String tablename = "locTable";
    final public static String col1 = "ID";
    final public static String col2 = "position";
    final public static String col3 = "time";


    public DatabaseHelper(Context context) {
        super(context, dbName, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tablename+"(id INTEGER PRIMARY KEY AUTOINCREMENT , position VARCHAR, time VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tablename);
        onCreate(db);

    }
    public boolean insertData(String position , String time){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(col2,position);
            contentValues.put(col3,time);
            SQLiteDatabase db = this.getWritableDatabase();
            long result =  db.insert(tablename,null,contentValues);
            if(result == -1)
                return false;
            else
                return  true;
        }catch(Exception e){ Log.d("Error insert = ",e.toString()); }
       return  false;
    }

    public Cursor fetchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+tablename,null);
        return cursor;
    }
}
