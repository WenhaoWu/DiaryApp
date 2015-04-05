package com.example.wenhaowu.diaryproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wenhaowu on 27/12/14.
 */
public class UserSQliteHelper extends SQLiteOpenHelper{
    public static final String TABLE_MEMBER = "Diary";
    public static final String MEMBER_ID ="ID";
    public static final String MEMBER_TITLE = "Title";
    public static final String MEMBER_DATE = "Date";
    public static final String MEMBER_CONTENT = "Content";

    private static final String create_table= "create table Diary"+"("+MEMBER_ID+" INTEGER PRIMARY KEY AUTO_INCREMENT ,"+
                                               MEMBER_TITLE+" TEXT,"+MEMBER_DATE+" DATE,"+
                                               MEMBER_CONTENT+" TEXT)";

    private static final String createSQL ="CREATE TABLE Diary(ID INTEGER PRiMARY KEY AUTO_INCREMENT, Title TEXT, Date DATE, Content TEXT)";

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
