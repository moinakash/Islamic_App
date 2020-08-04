package com.example.islamicappb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ReadHadisActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    String sessionId;
    public static int pvalue = 1;
    DatabaseHelper db;
    private ListView listView2;
    List<HadisLinePart> hadisLinePart1;

    HadisLineAdapter hadisLineAdapter;

    MyDatabasehelper myDatabasehelper;

    FloatingActionButton floatingActionButton;

    public int sizeInt = 12;

    public String seekvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_read_hadis);


        ToolBar();
        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();


        sessionId = getIntent().getStringExtra("position");
        // Toast.makeText(this, "sId "+sessionId, Toast.LENGTH_SHORT).show();
        pvalue =  Integer.parseInt(sessionId);


        floatingActionButton = findViewById(R.id.idfloatingActionButton);

        listView2 = findViewById(R.id.idListViewHadis);
        hadisLinePart1 = new ArrayList();

        db = new DatabaseHelper(this);

        loadData();
    }

    public void loadData() {

        sessionId = getIntent().getStringExtra("position");
        // Toast.makeText(this, "sId "+sessionId, Toast.LENGTH_SHORT).show();
        pvalue =  Integer.parseInt(sessionId);

        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = db.Hadis(pvalue);


        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){

                hadisLinePart1.add(new HadisLinePart(""+cursor.getString(4),""+cursor.getString(5),""+cursor.getString(6),""+cursor.getString(1)));

            }
        }


        hadisLineAdapter = new HadisLineAdapter(this,R.layout.custom_sura_part,hadisLinePart1);
        listView2.setAdapter(hadisLineAdapter);

    }


    public class HadisLineAdapter extends ArrayAdapter<HadisLinePart> {

        private List<HadisLinePart> hadisLinePart;
        private Context context;

        TextView HadisArbi;

        public HadisLineAdapter(@NonNull Context context, int textViewResourceId, List<HadisLinePart> hadisLinePart) {
            super(context, textViewResourceId, hadisLinePart);
            this.context = context;
            this.hadisLinePart = hadisLinePart;


        }

        @Override
        public int getCount() {
            return hadisLinePart.size();
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
                customView = vi.inflate(R.layout.custom_hadis_part, null);

            }

            final HadisLinePart hadisLinePart1 = hadisLinePart.get(position);


            if(hadisLinePart1 !=null)
            {

                final ImageButton Copy = (ImageButton) customView.findViewById(R.id.idSuraCopy);
                final ImageButton Bookmark = (ImageButton) customView.findViewById(R.id.idSuraBookmark);
                final ImageButton Share = (ImageButton) customView.findViewById(R.id.idSuraShare);

                TextView Number = (TextView) customView.findViewById(R.id.idAyatNumber);
                Number.setText(hadisLinePart1.getHadis_index());

                HadisArbi = (TextView) customView.findViewById(R.id.idSuraArbi);
                HadisArbi.setText(hadisLinePart1.getHadis_arbi());


                ///////////////////////////////////////////////////////


                final TextView hadisBangla = (TextView) customView.findViewById(R.id.idSuraBangla);
                hadisBangla.setText(hadisLinePart1.getHadis_bangla());

                TextView hadisutso = (TextView) customView.findViewById(R.id.idSuraBanglaMeaning);
                hadisutso.setText(hadisLinePart1.getHadis_utso());



                /////////////////////////////////////////////////////////////////////////
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
                        cm.setText(""+hadisLinePart1.getHadis_arbi()+"\n"+""+hadisLinePart1.getHadis_bangla()+""+hadisLinePart1.getHadis_utso());

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
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+hadisLinePart1.getHadis_arbi()+"\n"+hadisLinePart1.getHadis_bangla()+""+hadisLinePart1.getHadis_utso());
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                });


            }

            return customView;

        }
    }





    public int getCategory() {

        return pvalue;
    }

    private void ToolBar() {

        mToolbar = findViewById( R.id.SuraName_toolbar );
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        String HadisTypeName = getIntent().getStringExtra("typename");

        String name = ""+HadisTypeName;

        //toolbar name ==>
        mTitle.setText(""+HadisTypeName);
        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

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