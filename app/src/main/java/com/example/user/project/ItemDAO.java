package com.example.user.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

// 資料功能類別
public class ItemDAO {
    // 表格名稱
    public static final String TABLE_NAME = "item";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String DATETIME_COLUMN = "datetime";//購買時間
    public static final String COLOR_COLUMN = "color";//顏色
    public static final String TITLE_COLUMN = "title";//商品名
    public static final String PRICE_COLUMN = "price";//價錢
    public static final String SIZE_COLUMN = "size";//大小
    public static final String DESCRIPTION_COLUMN = "description";//敘述
    public static final String PIC1_COLUMN = "pic1";
    public static final String PIC2_COLUMN = "pic2";
    public static final String PIC3_COLUMN = "pic3";

    public static final String[] COLUMNS =
            {
                    KEY_ID, DATETIME_COLUMN, COLOR_COLUMN,TITLE_COLUMN,PRICE_COLUMN,SIZE_COLUMN,DESCRIPTION_COLUMN,PIC1_COLUMN,PIC2_COLUMN,PIC2_COLUMN,PIC3_COLUMN
            };
    public static final String[]SHOW_COLUMNS =
            {
            KEY_ID,DATETIME_COLUMN,DESCRIPTION_COLUMN
            };
    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATETIME_COLUMN + " INTEGER NOT NULL, " +
                    COLOR_COLUMN + " INTEGER NOT NULL, " +
                    TITLE_COLUMN + " TEXT NOT NULL, " +
                    PRICE_COLUMN + " TEXT NOT NULL, " +
                    SIZE_COLUMN + " TEXT NOT NULL, " +
                    DESCRIPTION_COLUMN + "  TEXT NOT NULL, " +
                    PIC1_COLUMN + " TEXT NOT NULL, " +
                    PIC2_COLUMN + "  TEXT NOT NULL, " +
                    PIC3_COLUMN + "  TEXT NOT NULL,";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public ItemDAO(Context context) {
        db = MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

}
