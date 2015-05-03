package com.example.wenhaowu.diaryproject;

/**
 * Created by wenhaowu on 31/12/14.
 */
public class DiaryHolder {

    public int Diary_ID;
    public String Diary_Title;
    public String Diary_Date;
    public String Diary_Content;

    //constructor


    public DiaryHolder(int diary_Id ,String diary_Title, String diary_Date, String diary_Content) {
        this.Diary_ID = diary_Id;
        this.Diary_Title = diary_Title;
        this.Diary_Date = diary_Date;
        this.Diary_Content = diary_Content;
    }
}
