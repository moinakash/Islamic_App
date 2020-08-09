package com.example.islamicappb.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islamicappb.AmolPojoClass;
import com.example.islamicappb.DatabaseHelper;
import com.example.islamicappb.MyDatabasehelper;
import com.example.islamicappb.R;

import java.util.ArrayList;
import java.util.List;

public class Amol_Activity extends AppCompatActivity {


    public static List<String> data;
    DatabaseHelper db;

    //  private ListView listView;

    ListView list;
    List<AmolPojoClass> amolPojoClasses;


    CustomAmolAdapter customAmolAdapter;

    MyDatabasehelper myDatabasehelper;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amol);

        ToolBar();

        myDatabasehelper = new MyDatabasehelper(this);


        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();


        // listView = findViewById(R.id.idListView);


        list = findViewById( R.id.idListViewAmol);



        db = new DatabaseHelper(this);

        amolPojoClasses = new ArrayList();


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

        Cursor cursor = db.amoldata();

        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){

                amolPojoClasses.add(new AmolPojoClass(""+cursor.getString(1),""+cursor.getString(2),""+cursor.getString(3)));


            }
        }


        customAmolAdapter = new CustomAmolAdapter(this,R.layout.custom_amol_listview, amolPojoClasses);
        list.setAdapter(customAmolAdapter);


    }


    public class CustomAmolAdapter extends ArrayAdapter<AmolPojoClass> {

        private List<AmolPojoClass> amolPojoClasses;
        private Context context;

        public CustomAmolAdapter(@NonNull Context context, int textViewResourceId, List<AmolPojoClass> amolPojoClasses) {
            super(context, textViewResourceId, amolPojoClasses);
            this.context = context;
            this.amolPojoClasses = amolPojoClasses;
        }

        @Override
        public int getCount() {
            return amolPojoClasses.size();
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
                customView = vi.inflate(R.layout.custom_amol_listview, null);

            }

            final AmolPojoClass amolPojoClasses3 = amolPojoClasses.get(position);


            if(amolPojoClasses3 !=null)
            {

                TextView AmolNumber = (TextView) customView.findViewById(R.id.idAmolNumber);
                AmolNumber.setText(amolPojoClasses3.getAmolNumber());

                TextView Amol = (TextView) customView.findViewById(R.id.idAmol);
                Amol.setText(amolPojoClasses3.getAmol());

                TextView AmolSource = (TextView) customView.findViewById(R.id.idSource);
                AmolSource.setText(amolPojoClasses3.getAmolSource());

                final ImageButton Copy = (ImageButton) customView.findViewById(R.id.idAmolCopy);

                final ImageButton Share = (ImageButton) customView.findViewById(R.id.idAmolShare);

                Copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Copy.startAnimation(myAnim);

                        Toast.makeText(getContext(), "অনুলিপি করা হয়েছে", Toast.LENGTH_SHORT).show();

                        ClipboardManager cm = (ClipboardManager) context
                                .getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(""+amolPojoClasses3.getAmol()+"\n"+""+amolPojoClasses3.getAmolSource());

                    }
                });

                Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Share.startAnimation(myAnim);

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+amolPojoClasses3.getAmol()+"\n"+""+amolPojoClasses3.getAmolSource());
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                });





       /*         customView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("main activity", "item clicked");

                        Intent intent = new Intent(getBaseContext(), ReadHadisActivity.class);
                        intent.putExtra("typename",""+hadisTypePojoList.getHadisType());
                        intent.putExtra("position", ""+hadisTypePojoList.getHadisType_Id());
                        startActivity(intent);


                    }
                });*/

            }

            return customView;

        }

        class MyBounceInterpolator implements android.view.animation.Interpolator {
            private double mAmplitude = 1;
            private double mFrequency = 10;

            MyBounceInterpolator(double amplitude, double frequency) {
                mAmplitude = amplitude;
                mFrequency = frequency;
            }

            public float getInterpolation(float time) {
                return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                        Math.cos(mFrequency * time) + 1);
            }
        }



    }

    private void ToolBar() {

        mToolbar = findViewById( R.id.SuraName_toolbar );
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);




        //toolbar name ==>
        mTitle.setText("আমল সমূহ");
        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }
}