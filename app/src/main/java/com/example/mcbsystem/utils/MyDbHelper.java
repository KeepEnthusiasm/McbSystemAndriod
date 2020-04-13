package com.example.mcbsystem.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    //数据库名称
    public static final String DB_NAME="mcb_database.db";
    //表名称
    public static final String TABLE_NAME="tb_user";
    public MyDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String sql="create table "+TABLE_NAME+"( id integer primary key autoincrement," +
                    "name varchar,age int ,gender varchar,birthday varchar," +
                    "address varchar,phone long,email varchar" +
                    ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
