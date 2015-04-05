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




public class MainActivity extends ActionBarActivity {

    public static UserSQliteHelper usdbh;
    public static SQLiteDatabase db;

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





    public void addDiary(){

        Intent intent = new Intent();
        intent.setClass(getBaseContext(),add_Diary.class);
        startActivity(intent);
    }


    public void showDiary(){
        Intent intent = new Intent();
        intent.setClass(getBaseContext(),show_Diary.class);
        startActivity(intent);
    }

    public void clearData(){
        usdbh = new UserSQliteHelper(MainActivity.this, "DBDiary",null,1);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
