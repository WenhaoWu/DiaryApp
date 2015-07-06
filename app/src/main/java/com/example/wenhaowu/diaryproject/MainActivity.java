package com.example.wenhaowu.diaryproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends ActionBarActivity {

    private static UserSQliteHelper usdbh;
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    //Add Diary
        Button button;
        button = (Button)findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDiary();
            }
        });

    //Show Diary
        Button button2=(Button)findViewById(R.id.showButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDiary();
            }
        });

    //Clear Database
        Button button3=(Button)findViewById(R.id.clearButton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });


    }





    private void addDiary(){

        Intent intent = new Intent();
        intent.setClass(getBaseContext(),add_Diary.class);
        startActivity(intent);
    }

    private void showDiary(){
        Intent intent = new Intent();
        intent.setClass(getBaseContext(),show_Diary.class);
        startActivity(intent);
    }

    private void clearData(){
        usdbh = new UserSQliteHelper(MainActivity.this, "Diary",null,2);
        db=usdbh.getWritableDatabase();

        db.execSQL("DELETE FROM Diary");
        Toast.makeText(getBaseContext(),"Cleared All Data", Toast.LENGTH_LONG).show();

        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        boolean music_flag = false;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id== R.id.music){
            Intent svc=new Intent(this, backgroundSound_Service.class);
            if (music_flag == false){
                startService(svc);
                music_flag = true;
            }
            else if (music_flag == true){
                stopService(svc);
                music_flag = false;
            }

        }


        return super.onOptionsItemSelected(item);
    }
}
