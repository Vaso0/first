package com.example.sqlite13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;


public class DBhelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "storeDB";
    public static final String TABLE_PRODUCT = "contacts";
    public static final String TABLE_USERS = "usersTable";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PRICE = "price";

    public static final String KEY_IdUs = "id_User";
    public static final String KEY_Name = "title";
    public static final String KEY_Password = "price";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PRODUCT + "(" + KEY_ID
                + " integer primary key," + " text," + KEY_TITLE + " text," + KEY_PRICE + " integer" + ")");


        db.execSQL("create table " + TABLE_USERS + "("
                + KEY_IdUs
                + " integer primary key," + " text," + KEY_Name + " text," + KEY_Password + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_PRODUCT);
        db.execSQL("drop table if exists " + TABLE_USERS);
        onCreate(db);

    }
}
