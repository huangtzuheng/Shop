package com.example.user.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    // 資料庫名稱
    public static final String DATABASE_NAME = "MyDatabase.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }


    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new MyDBHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //建立db的Table與Table裡的欄位項目
        String TABLE = "CREATE TABLE " + "sqldb" + " ("
                + "_id"  + " INTEGER primary key autoincrement, "
                + "COLOR" + " text , "
                + "TITLE" + " text , "
                + "PRICE" + " text , "
                + "SIZE" + " text , "
                + "DESCRIPTION" + " text, "
                + "PIC" + " text , "
                + "PIC2" + " text , "
                + "PIC3" + " text  )";


        db.execSQL(TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格
        db.execSQL("DROP TABLE IF EXISTS " + ItemDAO.TABLE_NAME);
        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }
    public Cursor select()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("sqldb", null, null, null, null, null, null);
        return cursor;
    }
    public long insert(String DATETIME, String COLOR, String TITLE, String PRICE, String SIZE, String DESCRIPTION, String PIC1, String PIC2, String PIC3)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DATETIME", DATETIME);
        cv.put("COLOR", COLOR);
        cv.put("TITLE", TITLE);
        cv.put("PRICE", PRICE);
        cv.put("SIZE", SIZE);
        cv.put("DESCRIPTION", DESCRIPTION);
        cv.put("PIC1", PIC1);
        cv.put("PIC2", PIC2);
        cv.put("PIC3", PIC3);
        long row = db.insert("sqldb", null, cv);
        return row;
    }
    //刪除Table單筆資料，帶入id
    public void delete(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id" + " = " + Integer.toString(id) ;
        db.delete("sqldb", where, null);
    }

    //刪除Table全部資料
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + " sqldb" );
    }
}

