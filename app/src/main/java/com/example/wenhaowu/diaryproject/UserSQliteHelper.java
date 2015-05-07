package com.example.wenhaowu.diaryproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wenhaowu on 27/12/14.
 */
public class UserSQliteHelper extends SQLiteOpenHelper{


    private static final String createSQL ="CREATE TABLE Diary(ID INTEGER AUTO_INCREMENT PRiMARY KEY, Title TEXT, Date DATE, Weather TEXT, Content TEXT)";


    public UserSQliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Diary");
        db.execSQL(createSQL);
    }
}
