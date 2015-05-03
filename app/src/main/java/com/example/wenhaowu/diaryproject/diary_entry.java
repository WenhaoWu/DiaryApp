package com.example.wenhaowu.diaryproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class diary_entry extends show_Diary {

    private static int Deleting_ID =1;
    private static String test = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);

        test = "not null";

        //Receiving Deleting ID from show_Diary activity
        Bundle args = getIntent().getExtras();
        Deleting_ID = args.getInt("Deleting_ID");
        Log.e("MyTag","Deleting ID is "+ Deleting_ID);

        TextView DiaryTitle = (TextView)findViewById(R.id.ID_DiaryTitle);
        DiaryTitle.setText(show_title);

        TextView DiaryDate = (TextView)findViewById(R.id.ID_DiaryDate);
        DiaryDate.setText(show_date);

        TextView DiaryContent = (TextView)findViewById(R.id.ID_DiaryContent);
        DiaryContent.setText(show_content);

        //Delete Button
        Button button = (Button)findViewById(R.id.ID_DeleteButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteMethod();
                Intent intent = new Intent();
                intent.setClass(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        //Update Button
        Button button2 = (Button)findViewById(R.id.ID_UpdateButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent();
                intent1.setClass(getBaseContext(),update_Diary.class);
                startActivity(intent1);
            }
        });


    }


    protected void DeleteMethod(){

        if (test == null){
            Deleting_ID = show_Diary.Diary_delete_ID;
        }

        db = usdbh.getWritableDatabase();

        if (db.delete("Diary","ID="+Deleting_ID, null)==1){
            //Toast.makeText(this,"Diary Deleted",Toast.LENGTH_LONG).show();
        }//if ends
        Log.e("MyTag","Here4 herehere");

        //Get sum of diaries current
        Cursor c = db.rawQuery("SELECT COUNT(ID) FROM Diary",null);
        c.moveToFirst();
        final int tt = c.getInt(0);
        Log.e("MyTag","Here4 "+ tt);

        //Let all diaries which are behind the deleting one get an increment of 1 in their ID
        //So that the next adding diary wouldn't have the same ID
        for (int i=Deleting_ID+1; i<(tt+2); i++){
            db.execSQL("UPDATE Diary SET ID = " +(i-1)+" WHERE ID = "+i+ ";" );
        }
        db.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diary_entry, menu);
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
