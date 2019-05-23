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
                    KEY_ID,DATETIME_COLUMN,DESCRIPTION_COLUMN,PIC1_COLUMN
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
    // 新增參數指定的物件
    public Item insert(Item item) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DATETIME_COLUMN, item.getDATETIME());//購買時間
        cv.put(COLOR_COLUMN, item.getCOLOR());//顏色
        cv.put(TITLE_COLUMN, item.getTITLE());//商品名
        cv.put(PRICE_COLUMN, item.getPRICE());//商品價錢
        cv.put(SIZE_COLUMN, item.getSIZE());//商品大小
        cv.put(DESCRIPTION_COLUMN, item.getDESCRIPTION());
        cv.put(PIC1_COLUMN, item.getPIC1());
        cv.put(PIC2_COLUMN, item.getPIC2());
        cv.put(PIC3_COLUMN, item.getPIC3());

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.setId(id);
        // 回傳結果
        return item;
    }

    // 修改參數指定的物件
    public boolean update(Item item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DATETIME_COLUMN, item.getDATETIME());
        cv.put(COLOR_COLUMN, item.getCOLOR());
        cv.put(TITLE_COLUMN, item.getTITLE());
        cv.put(PRICE_COLUMN, item.getPRICE());
        cv.put(SIZE_COLUMN, item.getSIZE());
        cv.put(DESCRIPTION_COLUMN, item.getDESCRIPTION());
        cv.put(PIC1_COLUMN, item.getPIC1());
        cv.put(PIC2_COLUMN, item.getPIC2());
        cv.put(PIC3_COLUMN, item.getPIC3());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 讀取所有記事資料
    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Item get(long id) {
        // 準備回傳結果用的物件
        Item item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            //讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    public Item getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Item result = new Item();

        result.setId(cursor.getLong(0));
        result.setDATETIME(cursor.getString(1));
        result.setCOLOR(cursor.getString(2));
        result.setTITLE(cursor.getString(3));
        result.setPRICE(cursor.getString(4));
        result.setSIZE(cursor.getString(5));
        result.setDESCRIPTION(cursor.getString(6));
        result.setPIC1(cursor.getString(7));
        result.setPIC2(cursor.getString(8));
        result.setPIC3(cursor.getString(9));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

}