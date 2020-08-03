package com.example.islamicappb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
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

    TextView tvLastSuraName;

    CustomSuraNameAdapter customSuraNameAdapter;

    MyDatabasehelper myDatabasehelper;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_sura);

        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();
        oncreate();

        // listView = findViewById(R.id.idListView);


        list = findViewById( R.id.idListView);
        tvLastSuraName = findViewById( R.id.idLastSuraName);



        SharedPreferences sharedPref = com.example.islamicappb.SuraActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String defaultValue = sharedPref.getString("LastSuraName","");
        //int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);


        tvLastSuraName.setText(""+defaultValue);

        db = new DatabaseHelper(this);

        suraNameListPojoList = new ArrayList();


        data = new ArrayList<>();


        fetchData();

        loadData();

    }

    public void oncreate(){
        list = findViewById( R.id.idListView);
        tvLastSuraName = findViewById( R.id.idLastSuraName);

        ToolBar();



        SharedPreferences sharedPref = com.example.islamicappb.SuraActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String defaultValue = sharedPref.getString("LastSuraName","");
        //int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);


        tvLastSuraName.setText(""+defaultValue);

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



        Cursor cursor = db.showAllData();

        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){

                suraNameListPojoList.add(new SuraNameListPojo(""+cursor.getString(0),""+cursor.getString(1),""+cursor.getString(3),""+cursor.getString(2),"("+cursor.getString(7)+")",""+cursor.getString(9)));


            }
        }


        customSuraNameAdapter = new CustomSuraNameAdapter(this,R.layout.custom_suraname_layout, suraNameListPojoList);
        list.setAdapter(customSuraNameAdapter);



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

                TextView Sura_total_ayat = (TextView) customView.findViewById(R.id.idSura_total_Ayat);
                Sura_total_ayat.setText(suraNameListPojo.getSura_total_ayat());

                TextView Sura_place = (TextView) customView.findViewById(R.id.idSuraPlace);
                Sura_place.setText(suraNameListPojo.getSura_place());

                customView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("main activity", "item clicked");

                        ////////////////////////////////////sharePref//////////////////////////

                        String LastRead = ""+suraNameListPojo.getSura_name_bangla();
//
//
//                        editor.putString("LastSuraName",""+LastRead);
//                        editor.apply();


                        SharedPreferences sharedPref = com.example.islamicappb.SuraActivity.this.getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putString("LastSuraName", ""+LastRead);
                        editor.commit();



                        Intent intent = new Intent(getBaseContext(), ReadSuraActivity.class);
                        intent.putExtra("position", ""+suraNameListPojo.getSura_number());
                        intent.putExtra("suraName", ""+suraNameListPojo.getSura_name_bangla());
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


    @Override
    protected void onPause() {
        super.onPause();

        oncreate();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        oncreate();
    }


    private void ToolBar() {

        mToolbar = findViewById( R.id.QuranMazid_toolbar );
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        //toolbar name ==>
        mTitle.setText("কুরআন মাজীদ");
        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }

}