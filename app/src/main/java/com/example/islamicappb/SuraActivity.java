package com.example.islamicappb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
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

    public class CustomSuraNameAdapter extends ArrayAdapter<SuraNameListPojo> {

        private List<SuraNameListPojo> suraNameListPojoList;
        private Context context;

        public CustomSuraNameAdapter(@NonNull Context context, int textViewResourceId, List<SuraNameListPojo> suraNameListPojoList) {
            super(context, textViewResourceId, suraNameListPojoList);
            this.context = context;
            this.suraNameListPojoList = suraNameListPojoList;
        }

        @Override
        public int getCount() {
            return suraNameListPojoList.size();
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View customView= convertView;
            if (customView==null)
            {
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                customView = vi.inflate(R.layout.custom_suraname_layout, null);

            }

            final SuraNameListPojo suraNameListPojo = suraNameListPojoList.get(position);


            if(suraNameListPojo !=null)
            {

                TextView SuraName = (TextView) customView.findViewById(R.id.idSuraNumber);
                SuraName.setText(suraNameListPojo.getSura_number());

                // thumb image
//            ImageView imageView = (ImageView) customView.findViewById(R.id.userImg);
//            imageView.setImageResource(user.getImgRes());

                TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraNameBangla);
                SuraNameBangla.setText(suraNameListPojo.getSura_name_bangla());

                TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idSuraNameMeaning);
                SuraNameMeaning.setText(suraNameListPojo.getSura_name_meaning());

                TextView SuraNameArbi = (TextView) customView.findViewById(R.id.idSuraNameArbi);
                SuraNameArbi.setText(suraNameListPojo.getSura_name_arbi());

                customView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("main activity", "item clicked");

                        Toast.makeText(getContext(), ""+suraNameListPojo.getSura_name_bangla(), Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getBaseContext(), ReadSuraActivity.class);
                        intent.putExtra("position", ""+suraNameListPojo.getSura_number());
                        startActivity(intent);


                        //    startActivity(new Intent(getContext(),ReadSura.class).putExtra("items",suraNameListPojoList.get(position)));


                    }
                });

            }

            return customView;


        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu,menu );

        MenuItem searchItems = menu.findItem( R.id.item_search );
        SearchView searchView = (SearchView) searchItems.getActionView();

        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<SuraNameListPojo> templist = new ArrayList<>(  );

                for(SuraNameListPojo temp : suraNameListPojoList)
                {
                    if (temp.sura_number.toLowerCase().contains( newText.toLowerCase() ))
                    { templist.add( temp ); }
                }

                CustomSuraNameAdapter adp = new CustomSuraNameAdapter( SuraActivity.this,R.layout.custom_suraname_layout,templist );

                list.setAdapter( adp );


                return true;
            }


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

        } );

        return super.onCreateOptionsMenu( menu );
    }


}