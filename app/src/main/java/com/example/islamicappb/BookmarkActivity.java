package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class BookmarkActivity extends AppCompatActivity {

    Cursor cursor;
    private ListView listView;
    private MyDatabasehelper myDatabasehelper;

    List<BookmarkPojoClass> bookmarkPojoClasses;
    BookmarkAdapter bookmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bookmarkPojoClasses = new ArrayList();

        listView = findViewById(R.id.idListView);

        myDatabasehelper = new MyDatabasehelper(this);

        loadData();
    }

    public void loadData() {

        ArrayList<String> listData = new ArrayList<>();

        cursor = myDatabasehelper.showAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){


                bookmarkPojoClasses.add(new BookmarkPojoClass(""+cursor.getString(1),""+cursor.getString(1),
                        ""+cursor.getString(1),""+cursor.getString(1),
                        ""+cursor.getString(1)));

                     /*         listData.add(cursor.getString(0)+" \t"+cursor.getString(1));
                listData.add(cursor.getString(0)+" \t"+cursor.getString(2));
                listData.add(cursor.getString(0)+" \t"+cursor.getString(3));*/

            }
        }

        bookmarkAdapter = new BookmarkAdapter(this,R.layout.bookmark_list_item, bookmarkPojoClasses);
        listView.setAdapter(bookmarkAdapter);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String se = adapterView.getItemAtPosition(i).toString();
//                //String selectedvalue = adapterView.getItematPosition(i).toString();
//                Toast.makeText(getApplicationContext(), "Selected value : "+se, Toast.LENGTH_SHORT).show();
//            }
//        });


//        String selectedvalue = adapterView.getrItematPosition(i).toString();
//        Toast.makeText(getApplicationContext(), "Selected value : "+selectedvalue, Toast.LENGTH_SHORT).show();


    }



}