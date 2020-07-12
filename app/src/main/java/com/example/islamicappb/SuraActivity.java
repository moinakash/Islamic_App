package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SuraActivity
        extends AppCompatActivity {



    public static List<String> data;
    DatabaseHelper db;

    //  private ListView listView;

    ListView list;
    List<SuraNameListPojo> suraNameListPojoList;

    CustomSuraNameAdapter customSuraNameAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sura);



        // listView = findViewById(R.id.idListView);


        list = findViewById( R.id.idListView);




        db = new DatabaseHelper(this);

        suraNameListPojoList = new ArrayList();


        data = new ArrayList<>();
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


    public void loadData() {

        //  ArrayList<String> listData = new ArrayList<>();






        Cursor cursor = db.showAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                suraNameListPojoList.add(new SuraNameListPojo(""+cursor.getString(0),""+cursor.getString(1),""+cursor.getString(3),""+cursor.getString(2)));


                //    listData.add(cursor.getString(0)+" \t"+cursor.getString(1));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(2));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(3));
            }
        }


        customSuraNameAdapter = new CustomSuraNameAdapter(this,R.layout.custom_suraname_layout, suraNameListPojoList);
        list.setAdapter(customSuraNameAdapter);


//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.idTextView,listData);
//        listView.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //  Toast.makeText( getApplicationContext(), suraNameListPojoList+ " is selected",Toast.LENGTH_SHORT).show();

//                String se = (String) adapterView.getItemAtPosition(i);
//
                int f = i+1;

                Toast.makeText(getApplicationContext(), "Selected value : "+f, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), ReadSuraActivity.class);
                intent.putExtra("position", ""+f);
                startActivity(intent);
            }
        });



    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate( R.menu.menu,menu );
//
//        MenuItem searchItems = menu.findItem( R.id.item_search );
//        SearchView searchView = (SearchView) searchItems.getActionView();
//
//        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
//
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ArrayList<SuraNameListPojo> templist = new ArrayList<>(  );
//
//                for(SuraNameListPojo temp : suraNameListPojoList)
//                {
//                    if (temp.sura_name_bangla.toLowerCase().contains( newText.toLowerCase() ))
//                    { templist.add( temp ); }
//                }
//
//                CustomSuraNameAdapter adp = new CustomSuraNameAdapter( SuraActivity.this,R.layout.custom_suraname_layout,templist );
//
//                list.setAdapter( adp );
//
//
//                return true;
//            }
//
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//        } );
//
//        return super.onCreateOptionsMenu( menu );
//    }


}