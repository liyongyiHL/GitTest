package com.performancedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bonree-lidong on 2017/9/20.
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String name = "count"; //数据库名称

    private static final int version = 1; //数据库版本

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement, name varchar(20), age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("ALTER TABLE person ADD phone VARCHAR(12)"); //往表中增加一列
    }
}
