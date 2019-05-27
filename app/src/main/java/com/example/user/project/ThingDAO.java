package com.example.user.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ThingDAO {
    // 表格名稱
    public static final String TABLE_NAME = "thing";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String THING_TITLE = "thing_title";
    public static final String THING_PRICE = "thing_price";
    public static final String THING_SIZE = "thing_size";
    public static final String THING_DESCRIPTION = "thing_description";
    public static final String THING_PICTURE = "thing_picture";
    public static final String USER_ID = "user_id";

    public static final String[] COLUMNS = {KEY_ID, THING_TITLE, THING_PRICE,THING_SIZE,THING_DESCRIPTION,THING_PICTURE,USER_ID};

    public static final String[]SHOW_COLUMNS ={KEY_ID, THING_TITLE, THING_PRICE,THING_SIZE,THING_DESCRIPTION,THING_PICTURE,USER_ID };


    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    THING_TITLE + " TEXT NOT NULL, " +
                    THING_PRICE + " INTEGER NOT NULL DEFAULT 0, " +
                    THING_SIZE + " INTEGER NOT NULL DEFAULT 0, " +
                    THING_DESCRIPTION + " TEXT NOT NULL DEFAULT '', " +
                    THING_PICTURE + " TEXT NOT NULL DEFAULT '', " +
                    USER_ID + " INTEGER NOT NULL ) ";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public ThingDAO(Context context) {
        db = MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }
    // 新增參數指定的物件
    public Thing insert(Thing thing) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料


        cv.put(THING_TITLE, thing.getTITLE());
        cv.put(THING_PRICE, thing.getPRICE());
        cv.put(THING_SIZE, thing.getSIZE());
        cv.put(THING_DESCRIPTION, thing.getDESCRIPTION());
        cv.put(THING_PICTURE, thing.getPICTURE());
        cv.put(USER_ID, thing.getUId());

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        thing.setID(id);

        // 回傳結果
        return thing;
    }

    // 修改參數指定的物件
    public boolean update(Thing thing) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(THING_TITLE, thing.getTITLE());
        cv.put(THING_PRICE, thing.getPRICE());
        cv.put(THING_SIZE, thing.getSIZE());
        cv.put(THING_DESCRIPTION, thing.getDESCRIPTION());
        cv.put(THING_PICTURE, thing.getPICTURE());
        cv.put(USER_ID, thing.getUId());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + thing.getId();
        Log.i("SQL Command",where);

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
    public List<Thing> getAll() {
        List<Thing> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Thing get(long id) {
        // 準備回傳結果用的物件
        Thing thing = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            //讀取包裝一筆資料的物件
            thing = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return thing;
    }

    public List<Thing> getByUID(long uid){
        List<Thing> result = new ArrayList<>();

        String where = USER_ID + "=" + uid;

        Cursor cursor = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 把Cursor目前的資料包裝為物件
    public Thing getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Thing result = new Thing();

        result.setID(cursor.getLong(0));
        result.setTITLE(cursor.getString(1));
        result.setPRICE(cursor.getInt(2));
        result.setSIZE(cursor.getInt(3));
        result.setDESCRIPTION(cursor.getString(4));
        result.setPICTIRE(cursor.getString(5));
        result.setUID(cursor.getLong(6));

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


    // 建立範例資料
    public void sample() {
        Thing thing = new Thing(0,"玩家11號",21001, 411, "我是 玩家1號 的說明文字","https://upload.cc/i1/2019/05/26/XKwfqv.jpg",1);
        Thing thing2 = new Thing(0,"玩家21號",22100, 412, "我是 玩家2號 的說明文字","https://upload.cc/i1/2019/05/26/ZgCW2v.jpg",1);
        Thing thing3 = new Thing(0,"玩家31號",23100, 413, "我是 玩家3號 的說明文字","https://upload.cc/i1/2019/05/26/p8BSAF.jpg",2);
        Thing thing4 = new Thing(0,"玩家41號",21400, 414, "我是 玩家4號 的說明文字","https://upload.cc/i1/2019/05/26/MlEmaZ.jpg",2);
        insert(thing);
        insert(thing2);
        insert(thing3);
        insert(thing4);
    }



}
