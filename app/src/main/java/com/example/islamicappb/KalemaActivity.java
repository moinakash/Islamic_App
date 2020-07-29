package com.example.islamicappb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KalemaActivity extends AppCompatActivity {


    public static List<String> data;
    DatabaseHelper db;

    //  private ListView listView;

    ListView list;

    List<KalemaPojoList> kalemaPojoLists;

    KalemaNameCustomAdapter kalemaNameCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalema);

        oncreate();


    }


    public void oncreate(){
        list = findViewById( R.id.idListView);
        db = new DatabaseHelper(this);

        kalemaPojoLists = new ArrayList();


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


        Cursor cursor = db.KalemaData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                kalemaPojoLists.add(new KalemaPojoList(""+cursor.getString(5),""+cursor.getString(1)));


                //    listData.add(cursor.getString(0)+" \t"+cursor.getString(1));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(2));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(3));
            }
        }


        kalemaNameCustomAdapter = new KalemaNameCustomAdapter(this,R.layout.custom_kalema_name_layout, kalemaPojoLists);
        list.setAdapter(kalemaNameCustomAdapter);






    }


    public class KalemaNameCustomAdapter extends ArrayAdapter<KalemaPojoList> {

        private List<KalemaPojoList> kalemaPojoLists;
        private Context context;

        public KalemaNameCustomAdapter(@NonNull Context context, int textViewResourceId, List<KalemaPojoList> suraNameListPojoList) {
            super(context, textViewResourceId, suraNameListPojoList);
            this.context = context;
            this.kalemaPojoLists = suraNameListPojoList;
        }

        @Override
        public int getCount() {
            return kalemaPojoLists.size();
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
                customView = vi.inflate(R.layout.custom_kalema_name_layout, null);

            }

            final KalemaPojoList kalemaPojoList = kalemaPojoLists.get(position);


            if(kalemaPojoList !=null)
            {

                TextView SuraName = (TextView) customView.findViewById(R.id.idSuraNumber);
                SuraName.setText(kalemaPojoList.get_number());

                // thumb image
//            ImageView imageView = (ImageView) customView.findViewById(R.id.userImg);
//            imageView.setImageResource(user.getImgRes());

                TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraNameBangla);
                SuraNameBangla.setText(kalemaPojoList.get_name());



                customView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("main activity", "item clicked");

                        ////////////////////////////////////sharePref//////////////////////////

                        String LastRead = ""+kalemaPojoList.get_name();

                        Toast.makeText(getContext(), ""+LastRead, Toast.LENGTH_SHORT).show();
//
//
//                        editor.putString("LastSuraName",""+LastRead);
//                        editor.apply();


//                        SharedPreferences sharedPref = com.example.islamicappb.SuraActivity.this.getPreferences(Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPref.edit();
//
//                        editor.putString("LastSuraName", ""+LastRead);
//                        editor.commit();








//                        Toast.makeText(getContext(), ""+suraNameListPojo.getSura_name_bangla(), Toast.LENGTH_SHORT).show();
//
//
//                        Intent intent = new Intent(getBaseContext(), ReadSuraActivity.class);
//                        intent.putExtra("position", ""+suraNameListPojo.getSura_number());
//                        intent.putExtra("suraName", ""+suraNameListPojo.getSura_name_bangla());
//                        startActivity(intent);


                        //    startActivity(new Intent(getContext(),ReadSura.class).putExtra("items",suraNameListPojoList.get(position)));


                    }
                });

            }

            return customView;


        }


    }
}