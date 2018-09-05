package com.example.gyaneshbohara.databasedemo_beta.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataTableCreation {


    /**changes(table creating string) made(08.04) by #Gyanesh **/
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataContract.DataEntry.TABLE_NAME + " (" +
                    DataContract.DataEntry._ID + " INTEGER PRIMARY KEY," +
                    DataContract.DataEntry.COLUMN_CUSTOM_ID+ " INTEGER ," +
                    DataContract.DataEntry.COLUMN_LAYOUT_ID+ " TEXT ," +
                    DataContract.DataEntry.COLUMN_DATE+ " DATE ," +
                    DataContract.DataEntry.COLUMN_FILE_NAME + " TEXT," +
                    DataContract.DataEntry.COLUMN_TOTAL_SIZE + " TEXT," +
                    DataContract.DataEntry.COLUMN_REMAINING + " TEXT," +
                    DataContract.DataEntry.COLUMN_FLAG + " INTEGER)";



    /**changes(table deletion string) made(08.04) by #Gyanesh **/
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataContract.DataEntry.TABLE_NAME;




    DatabaseTableHelper databaseTableHelper;


    public DataTableCreation(Context context) throws Exception{

        databaseTableHelper=new DatabaseTableHelper(context);


    }







    /**change(function insert data into database) made(018.08.04) by #Gyanesh **/
    public void insertDownloadInforamtion(int id,String layoutId,String date,String fileName,String totalSize,String remaining,int isTrue) throws Exception{

        // Gets the data repository in write mode
        SQLiteDatabase db = databaseTableHelper.getWritableDatabase();

       // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DataContract.DataEntry.COLUMN_CUSTOM_ID,id);
        values.put(DataContract.DataEntry.COLUMN_LAYOUT_ID,layoutId);
        values.put(DataContract.DataEntry.COLUMN_DATE,date);
        values.put(DataContract.DataEntry.COLUMN_FILE_NAME, fileName);
        values.put(DataContract.DataEntry.COLUMN_TOTAL_SIZE, totalSize);
        values.put(DataContract.DataEntry.COLUMN_REMAINING, remaining);
        values.put(DataContract.DataEntry.COLUMN_FLAG, isTrue);

        long newRowId = db.insert(DataContract.DataEntry.TABLE_NAME, null, values);

    }

    /**change(function will return all data into cursor) made(018.08.04) by #Gyanesh **/
    public Cursor getAllInformation(Context context) throws Exception{

        SQLiteDatabase db = databaseTableHelper.getReadableDatabase();


        Cursor  cursor = db.rawQuery("select * from "+ DataContract.DataEntry.TABLE_NAME,null);

        return cursor;

    }

    public String  getColumnInfo(Context context,int id,String columnName)throws Exception{

        SQLiteDatabase db = databaseTableHelper.getReadableDatabase();

        String query = "SELECT * FROM "+ DataContract.DataEntry.TABLE_NAME+" WHERE "+ DataContract.DataEntry.COLUMN_CUSTOM_ID +" = "+ id;

        Cursor  cursor = db.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        String fileName=cursor.getString(cursor.getColumnIndex(columnName));

        db.close();

        return fileName;

    }


    public void delete(int id) throws Exception{
        SQLiteDatabase db = databaseTableHelper.getReadableDatabase();

        db.delete(DataContract.DataEntry.TABLE_NAME, DataContract.DataEntry.COLUMN_CUSTOM_ID+" = "+id, null);


        db.close();

    }

    public void deleteAll() throws Exception{

        SQLiteDatabase db = databaseTableHelper.getReadableDatabase();

        db.execSQL("delete * from" + DataContract.DataEntry.TABLE_NAME);

        db.close();
    }

    public class DatabaseTableHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "ngage.db";

        public DatabaseTableHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }





}
