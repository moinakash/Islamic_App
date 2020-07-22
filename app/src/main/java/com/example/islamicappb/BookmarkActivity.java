package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {

    ListView LV;

    BookmarkAdapter bookmarkAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        LV = findViewById(R.id.idListView);

        ArrayList<String> listData = new ArrayList<>();



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.bookmark_list_item,R.id.list_item,listData);
        LV.setAdapter(adapter);


    }
}