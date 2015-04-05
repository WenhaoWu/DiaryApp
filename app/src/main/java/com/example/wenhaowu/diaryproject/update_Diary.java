package com.example.wenhaowu.diaryproject;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class update_Diary extends show_Diary {


    public String Updating_Title, Updating_Date, Updating_Content;
    public int Updating_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Updating_Title = show_title;
        Updating_Date = show_date;
        Updating_Content = show_content;
        Updating_ID = Diary_entry_ID;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__diary);

        EditText t = (EditText) findViewById(R.id.Edit_Title_Update);
        t.setText(Updating_Title);

        EditText t1 = (EditText)findViewById(R.id.Edit_Date_Update);
        t1.setText(Updating_Date);

        EditText t2 = (EditText)findViewById(R.id.Edit_Content_Update);
        t2.setText(Updating_Content);

        Button b = (Button)findViewById(R.id.Update_Diary_OK);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updating_Title = ((EditText) findViewById(R.id.Edit_Title_Update)).getText().toString();
                Updating_Date = ((EditText) findViewById(R.id.Edit_Date_Update)).getText().toString();
                Updating_Content = ((EditText) findViewById(R.id.Edit_Content_Update)).getText().toString();

                ContentValues newDiary = new ContentValues();
                newDiary.put("Title", Updating_Title);
                newDiary.put("Date", Updating_Date);
                newDiary.put("Content",Updating_Content);

                String where = "id=?";
                String[] whereArgs = new String[]{String.valueOf(Updating_ID)};

                db = usdbh.getWritableDatabase();
                db.update("Diary",newDiary,where,whereArgs);
                db.close();

                Intent intent2 = new Intent();
                intent2.setClass(getBaseContext(),MainActivity.class);
                startActivity(intent2);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update__diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
