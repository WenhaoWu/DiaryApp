package com.example.wenhaowu.diaryproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.R.layout;
import android.R.array;

import java.util.ArrayList;


public class show_Diary extends ActionBarActivity {

    public static final int MAX_DIARY = 10;

    public static UserSQliteHelper usdbh;
    public SQLiteDatabase db;
    public DiaryHolder[] DiaryData = new DiaryHolder[MAX_DIARY];
  //  public ArrayList
    public static String show_title, show_date, show_content;
    public static int Diary_entry_ID, Diary_delete_ID;
    public ListView lv;
    public String ListItem;
    public String sortArgs = null;
    String[] CONTEXTMENU = {"Delete","Edit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__diary);

        GetListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show__diary, menu);
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

        if (id == R.id.action_sort_by_date){
            sortArgs = "Date";
            GetListView();
            Log.e("MyTag", "Here7 "+sortArgs);
            return true;
        }

        if (id == R.id.action_sort_by_title){
            sortArgs="Title";
            GetListView();
            return true;
        }

        if (id == R.id.action_sort_by_ID){
            sortArgs="ID";
            GetListView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId()==R.id.ID_DiaryList){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(DiaryData[info.position].Diary_Title);
            menu.add(0,v.getId(),0, CONTEXTMENU[0]);
            menu.add(0,v.getId(),0, CONTEXTMENU[1]);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if (item.getTitle()== CONTEXTMENU[0]){
                diary_entry d = new diary_entry();
                Diary_delete_ID = DiaryData[info.position].Diary_ID;
                d.DeleteMethod();
                jumpToActivity(MainActivity.class,null);
            }
            else if (item.getTitle()==CONTEXTMENU[1]){
                getShowDetail(info.position);
                Log.e("MyTag","Here got the data");
                jumpToActivity(update_Diary.class,null);
            }

        //return super.onContextItemSelected(item);
        return true;


    }

    public void GetListView(){
        usdbh = new UserSQliteHelper(show_Diary.this,"DBDiary",null,1);
        db = usdbh.getWritableDatabase();

        String test = db.rawQuery("SELECT COUNT(*) FROM Diary;",null).toString();

        String[] fields = new String[]{"ID","Title","Date","Content"};
        Cursor c = db.query("Diary",fields,null,null,null,null,sortArgs);

        int i=0, j;
        if (c.moveToFirst()){
            do {
                String Title = c.getString(1);
                String Date = c.getString(2);
                String Content = c.getString(3);
                int ID = c.getInt(0);
                DiaryData[i] = new DiaryHolder(ID,Title,Date,Content);

                i++;
            }while (c.moveToNext());
        }//if ends
        db.close();

        //setting adapter and ListView layout
        lv =(ListView)findViewById(R.id.ID_DiaryList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(show_Diary.this,
                layout.simple_expandable_list_item_1 );

        //Getting the Diary title list
        for (j=0; j<i; j++){

            ListItem = DiaryData[j].Diary_Date +"    |    "+ DiaryData[j].Diary_Title;
            Log.e("MyTag",ListItem);
            adapter.add(ListItem);
        }

        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        lv.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getShowDetail(position);
                jumpToActivity(diary_entry.class,"Deleting_ID");
            }
        });
    }

    public void jumpToActivity(Class c, String s){
        Intent intent = new Intent();
        if (s != null){
            intent.putExtra(s,Diary_entry_ID);
            }
        intent.setClass(getBaseContext(), c);
        startActivity(intent);
    }

    public void getShowDetail(int p){
        show_title= DiaryData[p].Diary_Title;
        show_date= DiaryData[p].Diary_Date;
        show_content = DiaryData[p].Diary_Content;
        Diary_entry_ID = DiaryData[p].Diary_ID;
    }
}


