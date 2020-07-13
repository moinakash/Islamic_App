package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllahNameActivity extends AppCompatActivity {

    ListView list;
    List<AllahNamePojoClass> allahNamePojoClassList;

    AllahNameAdapter allahNameAdapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allah_name);

        list = findViewById( R.id.idAllahNamelist);

        db = new DatabaseHelper(this);

        allahNamePojoClassList = new ArrayList();

        fetchData();
        loadData();

    }

    public void fetchData()
    {

        db = new DatabaseHelper(this);
        try {
            db.createDataBase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadData(){






        Cursor cursor = db.ShowAllahName();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                allahNamePojoClassList.add(new AllahNamePojoClass(""+cursor.getString(2),""+cursor.getString(1),""+cursor.getString(3)));


                //    listData.add(cursor.getString(0)+" \t"+cursor.getString(1));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(2));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(3));
            }
        }


        allahNameAdapter = new AllahNameAdapter(this,R.layout.allahname_item_layout, allahNamePojoClassList);
        list.setAdapter(allahNameAdapter);


//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.idTextView,listData);
//        listView.setAdapter(adapter);


    }


}