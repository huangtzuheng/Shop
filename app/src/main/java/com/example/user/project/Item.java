package com.example.user.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 資料功能類別
public class Item {
    private  long id;
    private String DATETIME;
    private String COLOR;
    private String TITLE;
    private String PRICE;
    private String SIZE;
    private String DESCRIPTION;
    private String PIC1;
    private String PIC2;
    private String PIC3;

    public Item(){

    }


    public Item(long id, String DATETIME, String COLOR, String TITLE, String PRICE, String SIZE, String DESCRIPTION, String PIC1, String PIC2, String PIC3) {
        this.id = id;
        this.DATETIME = DATETIME;
        this.COLOR = COLOR;
        this.TITLE = TITLE;
        this.PRICE = PRICE;
        this.SIZE = SIZE;
        this.DESCRIPTION = DESCRIPTION;
        this.PIC1 = PIC1;
        this.PIC2 = PIC2;
        this.PIC3 = PIC3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getSIZE() {
        return SIZE;
    }

    public void setSIZE(String SIZE) {
        this.SIZE = SIZE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPIC1() {
        return PIC1;
    }

    public void setPIC1(String PIC1) {
        this.PIC1 = PIC1;
    }

    public String getPIC2() {
        return PIC2;
    }

    public void setPIC2(String PIC2) {
        this.PIC2 = PIC2;
    }

    public String getPIC3() {
        return PIC3;
    }

    public void setPIC3(String PIC3) {
        this.PIC3 = PIC3;
    }
}
