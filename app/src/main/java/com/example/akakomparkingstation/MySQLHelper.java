package com.example.akakomparkingstation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.io.Serializable;

public class MySQLHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "listadmin.db";
    private static final int DATABASE_VERSION = 1;
    //table name
    public static final String TABLE = "data";
    //columns
    public static final String nama = "nama";
    public static final String user = "user";
    public MySQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //TOFO Auto-generate constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE
                + "(id"
                + " Integer primary key autoincrement,  " +nama
                + " text not null, " + user
                + " text not null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO Auto-generated method stub
        //db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        //onCreate(db);

    }
}
