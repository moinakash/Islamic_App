package com.rdtl.ad_din.activity;

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
import android.graphics.PorterDuff;
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

import com.rdtl.ad_din.DatabaseHelper;
import com.rdtl.ad_din.pojo_classes.HadisLinePartPojo;
import com.rdtl.ad_din.MyDatabasehelper;
import com.rdtl.ad_din.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ReadHadisActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    String sessionId;
    public static int pvalue = 1;
    DatabaseHelper db;
    private ListView listView2;
    List<HadisLinePartPojo> hadisLinePartPojo1;

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
        pvalue =  Integer.parseInt(sessionId);


        floatingActionButton = findViewById(R.id.idfloatingActionButton);

        listView2 = findViewById(R.id.idListViewHadis);
        hadisLinePartPojo1 = new ArrayList();

        db = new DatabaseHelper(this);

        loadData();
    }

    public void loadData() {

        sessionId = getIntent().getStringExtra("position");

        pvalue =  Integer.parseInt(sessionId);

        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = db.Hadis(pvalue);


        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){

                hadisLinePartPojo1.add(new HadisLinePartPojo(""+cursor.getString(4),""+cursor.getString(5),""+cursor.getString(6),""+cursor.getString(1)));

            }
        }


        hadisLineAdapter = new HadisLineAdapter(this,R.layout.custom_sura_part, hadisLinePartPojo1);
        listView2.setAdapter(hadisLineAdapter);

    }


    public class HadisLineAdapter extends ArrayAdapter<HadisLinePartPojo> {

        private List<HadisLinePartPojo> hadisLinePartPojo;
        private Context context;

        TextView HadisArbi;

        public HadisLineAdapter(@NonNull Context context, int textViewResourceId, List<HadisLinePartPojo> hadisLinePartPojo) {
            super(context, textViewResourceId, hadisLinePartPojo);
            this.context = context;
            this.hadisLinePartPojo = hadisLinePartPojo;


        }

        @Override
        public int getCount() {
            return hadisLinePartPojo.size();
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

            final HadisLinePartPojo hadisLinePartPojo1 = hadisLinePartPojo.get(position);


            if(hadisLinePartPojo1 !=null)
            {

                final ImageButton Copy = (ImageButton) customView.findViewById(R.id.idSuraCopy);
                final ImageButton Bookmark = (ImageButton) customView.findViewById(R.id.idSuraBookmark);
                final ImageButton Share = (ImageButton) customView.findViewById(R.id.idSuraShare);

                TextView Number = (TextView) customView.findViewById(R.id.idAyatNumber);
                Number.setText(hadisLinePartPojo1.getHadis_index());

                HadisArbi = (TextView) customView.findViewById(R.id.idSuraArbi);
                HadisArbi.setText(hadisLinePartPojo1.getHadis_arbi());


                ///////////////////////////////////////////////////////


                final TextView hadisBangla = (TextView) customView.findViewById(R.id.idSuraBangla);
                hadisBangla.setText(hadisLinePartPojo1.getHadis_bangla());

                TextView hadisutso = (TextView) customView.findViewById(R.id.idSuraBanglaMeaning);
                hadisutso.setText(hadisLinePartPojo1.getHadis_utso());



                /////////////////////////////////////////////////////////////////////////
                Copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String Source = getString(R.string.Credit);

                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Copy.startAnimation(myAnim);

                        Toast.makeText(getContext(), "অনুলিপি করা হয়েছে", Toast.LENGTH_SHORT).show();

                        ClipboardManager cm = (ClipboardManager) context
                                .getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(""+ hadisLinePartPojo1.getHadis_arbi()+"\n"+""+ hadisLinePartPojo1.getHadis_bangla()+""+ hadisLinePartPojo1.getHadis_utso()+Source);

                    }
                });

                Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String Source = getString(R.string.Credit);
                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Share.startAnimation(myAnim);

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+ hadisLinePartPojo1.getHadis_arbi()+"\n"+ hadisLinePartPojo1.getHadis_bangla()+""+ hadisLinePartPojo1.getHadis_utso()+Source);
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                });



                SharedPreferences spf = getSharedPreferences("SeekValueP",Context.MODE_PRIVATE);
                seekvalue = spf.getString("skv","15");
                sizeInt = Integer.parseInt(seekvalue);

                if (sizeInt<12){
                    sizeInt = 12;
                }
                //SuraName.setTextSize(pxFromDp((sizeInt+3), ReadSuraActivity.this));

                HadisArbi.setTextSize(dpToSp(sizeInt, ReadHadisActivity.this));
                hadisBangla.setTextSize(dpToSp(sizeInt, ReadHadisActivity.this));
                hadisutso.setTextSize(dpToSp(sizeInt, ReadHadisActivity.this));


                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    //@RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {

                        ShowDialog();


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

    public void ShowDialog()
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final SeekBar seek = new SeekBar(this);
        seek.setMax(25);

        popDialog.setTitle("ফন্ট সাইজ");
        popDialog.setView(seek);

        seek.getProgressDrawable().setColorFilter(getResources().getColor(R.color.base_color), PorterDuff.Mode.MULTIPLY);
        seek.getThumb().setColorFilter(getResources().getColor(R.color.bottom_nav_option), PorterDuff.Mode.SRC_ATOP);

        SharedPreferences spf = getSharedPreferences("SeekValueP",Context.MODE_PRIVATE);
        seekvalue = spf.getString("skv","15");
        seek.setProgress(Integer.parseInt(seekvalue));

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                sizeInt = progress;
                String kk;
                kk = String.valueOf(sizeInt);
                //SuraName.setTextSize(pxFromDp(sizeInt, ReadSuraActivity.this));
                SharedPreferences spf = getSharedPreferences("SeekValueP",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.putString("skv",kk);
                editor.commit();
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }

            public void onStopTrackingTouch(SeekBar seekBar) { finish();  startActivity(getIntent());            }

        });




        popDialog.setMessage("ফন্ট সাইজ পরিবর্তন করুন");


        popDialog.create();

        popDialog.show();

    }

    public static float pxFromDp(float dp, Context mContext) {
        return dp * mContext.getResources().getDisplayMetrics().density;
    }

    public static int dpToSp(float dp, Context context) {
        return (int) (pxFromDp(dp, context) / context.getResources().getDisplayMetrics().scaledDensity);
    }


}