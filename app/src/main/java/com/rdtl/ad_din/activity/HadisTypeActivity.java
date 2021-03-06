package com.rdtl.ad_din.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rdtl.ad_din.DatabaseHelper;
import com.rdtl.ad_din.Helper;
import com.rdtl.ad_din.pojo_classes.HadisTypePojoList;
import com.rdtl.ad_din.MyDatabasehelper;
import com.rdtl.ad_din.R;

import java.util.ArrayList;
import java.util.List;

public class HadisTypeActivity extends AppCompatActivity {

    public static List<String> data;
    DatabaseHelper db;

    ListView list;
    List<HadisTypePojoList> hadisTypePojoList2;

    CustomHadisTypeAdapter customHadisTypeAdapter;

    MyDatabasehelper myDatabasehelper;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadis_type);

        ToolBar();


        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();

        list = findViewById( R.id.idListView);



        db = new DatabaseHelper(this);

        hadisTypePojoList2 = new ArrayList();


        data = new ArrayList<>();




        try {
            fetchData();
            loadData();
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Your phone does not support this feature", Toast.LENGTH_SHORT).show();
        }
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

        Cursor cursor = db.HadisData();

        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){

                hadisTypePojoList2.add(new HadisTypePojoList(""+cursor.getString(1),""+cursor.getString(0)));


            }
        }


        customHadisTypeAdapter = new CustomHadisTypeAdapter(this,R.layout.custom_suraname_layout, hadisTypePojoList2);
        list.setAdapter(customHadisTypeAdapter);
        Helper.getListViewSize(list);

    }


    public class CustomHadisTypeAdapter extends ArrayAdapter<HadisTypePojoList> {

        private List<HadisTypePojoList> hadisTypePojoList2;
        private Context context;

        public CustomHadisTypeAdapter(@NonNull Context context, int textViewResourceId, List<HadisTypePojoList> hadisTypePojoList2) {
            super(context, textViewResourceId, hadisTypePojoList2);
            this.context = context;
            this.hadisTypePojoList2 = hadisTypePojoList2;
        }

        @Override
        public int getCount() {
            return hadisTypePojoList2.size();
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
                customView = vi.inflate(R.layout.custom_hadistype_layout, null);

            }

            final HadisTypePojoList hadisTypePojoList = hadisTypePojoList2.get(position);


            if(hadisTypePojoList !=null)
            {

                TextView HadisType = (TextView) customView.findViewById(R.id.idhadisType);
                HadisType.setText(hadisTypePojoList.getHadisType());

                customView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("main activity", "item clicked");

                        Intent intent = new Intent(getBaseContext(), ReadHadisActivity.class);
                        intent.putExtra("typename",""+hadisTypePojoList.getHadisType());
                        intent.putExtra("position", ""+hadisTypePojoList.getHadisType_Id());
                        startActivity(intent);


                    }
                });

            }

            return customView;

        }

    }




    private void ToolBar() {

        mToolbar = findViewById( R.id.SuraName_toolbar );
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        String HadisTypeName = getIntent().getStringExtra("typename");

        String name = ""+HadisTypeName;

        //toolbar name ==>
        mTitle.setText("হাদীস");
        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }

}
