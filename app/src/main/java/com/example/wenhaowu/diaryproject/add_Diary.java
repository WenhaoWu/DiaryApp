package com.example.wenhaowu.diaryproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class add_Diary extends ActionBarActivity {

    private static UserSQliteHelper usdbh;
    private static SQLiteDatabase db;
    private int Adding_ID = 0; //ID For this adding diary

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__diary);

        usdbh = new UserSQliteHelper(add_Diary.this, "Diary", null,1);
        db = usdbh.getWritableDatabase();

        //Find the current sum of diaries
        Cursor C = db.rawQuery("SELECT COUNT(ID) FROM Diary", null);
        C.moveToFirst();
        final String currentSum = C.getString(0);
        Toast.makeText(getBaseContext(),currentSum+" diaries we have",Toast.LENGTH_LONG).show();

        //set the current date for default
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        Log.e("MyTag","Here8 "+ currentDate);
        EditText t =(EditText) findViewById(R.id.Edit_Date);
        t.setText(currentDate);

        Button addDiary = (Button)findViewById(R.id.Add_Diary_OK);
        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = ((EditText)findViewById(R.id.Edit_Title)).getText().toString();
                String newDate = ((EditText)findViewById(R.id.Edit_Date)).getText().toString();
                String newContent = ((EditText)findViewById(R.id.Edit_Content)).getText().toString();
                Adding_ID = Integer.parseInt(currentSum) + 1;

                Log.e("MyTag","Here "+Adding_ID);

                ContentValues newDiary = new ContentValues();
                newDiary.put("ID", Adding_ID);
                newDiary.put("Title",newTitle);
                newDiary.put("Date",newDate);
                newDiary.put("Content",newContent);

                db.insert("Diary",null,newDiary);

                db.close();

                Toast.makeText(getBaseContext(),"Added a diary", Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setClass(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__diary, menu);
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
