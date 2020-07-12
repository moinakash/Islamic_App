package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ReadSuraActivity extends AppCompatActivity {


    String sessionId;
    public static int pvalue = 1;

    DatabaseHelper db;

    private ListView listView2;


    List<SuraLinePart> suraLinePart1;

    SuraLineAdapter suraLineAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sura);

        sessionId = getIntent().getStringExtra("position");
        // Toast.makeText(this, "sId "+sessionId, Toast.LENGTH_SHORT).show();
        pvalue =  Integer.parseInt(sessionId);



        listView2 = findViewById(R.id.idListView2);
        suraLinePart1 = new ArrayList();

        db = new DatabaseHelper(this);

        loadData();
    }


    public void loadData() {

        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = db.ShowSura();


        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                suraLinePart1.add(new SuraLinePart(""+cursor.getString(6),""+cursor.getString(8),""+cursor.getString(9)));


                //               listData.add(cursor.getString(6));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(2));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(3));
            }
        }


        suraLineAdapter = new SuraLineAdapter(this,R.layout.custom_sura_part, suraLinePart1);
        listView2.setAdapter(suraLineAdapter);

//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.idTextView,listData);
//        listView2.setAdapter(adapter);

    }

    public int getCategory() {

        return pvalue;
    }

}