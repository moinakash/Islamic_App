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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rdtl.ad_din.DatabaseHelper;
import com.rdtl.ad_din.MyDatabasehelper;
import com.rdtl.ad_din.R;
import com.rdtl.ad_din.SuraActivity;
import com.rdtl.ad_din.SuraLinePartPojo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ReadSuraActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    String sessionId;
    public static int pvalue = 1;
    DatabaseHelper db;
    private ListView listView2;
    List<SuraLinePartPojo> suraLinePartPojo1;

    SuraLineAdapter suraLineAdapter;

    MyDatabasehelper myDatabasehelper;

    FloatingActionButton floatingActionButton;

    public int sizeInt = 12;

    public String seekvalue;

    LinearLayout tvBismillah;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_read_sura);
        tvBismillah = (LinearLayout) findViewById(R.id.idBismillah);
        ToolBar();
        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();




        sessionId = getIntent().getStringExtra("position");

        pvalue =  Integer.parseInt(sessionId);


        floatingActionButton = findViewById(R.id.idfloatingActionButton);

        listView2 = findViewById(R.id.idListView2);
        suraLinePartPojo1 = new ArrayList();

        db = new DatabaseHelper(this);

        loadData();
    }


    public void loadData() {

        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = db.ShowSura();


        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){

                suraLinePartPojo1.add(new SuraLinePartPojo(""+cursor.getString(5),""+cursor.getString(7),""+cursor.getString(8),""+cursor.getString(4)));

            }
        }


        suraLineAdapter = new SuraLineAdapter(this,R.layout.custom_sura_part, suraLinePartPojo1);
        listView2.setAdapter(suraLineAdapter);

       // Helper.getListViewSize(listView2);

    }


    public class SuraLineAdapter extends ArrayAdapter<SuraLinePartPojo> {

        private List<SuraLinePartPojo> suraLinePartPojo;
        private Context context;

        TextView SuraName;

        public SuraLineAdapter(@NonNull Context context, int textViewResourceId, List<SuraLinePartPojo> suraLinePartPojo) {
            super(context, textViewResourceId, suraLinePartPojo);
            this.context = context;
            this.suraLinePartPojo = suraLinePartPojo;


        }

        @Override
        public int getCount() {
            return suraLinePartPojo.size();
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
                customView = vi.inflate(R.layout.custom_sura_part, null);

            }

            final SuraLinePartPojo suraLinePartPojo1 = suraLinePartPojo.get(position);


            if(suraLinePartPojo1 !=null)
            {

                final ImageButton Copy = (ImageButton) customView.findViewById(R.id.idSuraCopy);
                final ImageButton Bookmark = (ImageButton) customView.findViewById(R.id.idSuraBookmark);
                final ImageButton Share = (ImageButton) customView.findViewById(R.id.idSuraShare);

                TextView AyatNumber = (TextView) customView.findViewById(R.id.idAyatNumber);
                AyatNumber.setText(suraLinePartPojo1.getSura_number());

                SuraName = (TextView) customView.findViewById(R.id.idSuraArbi);
                SuraName.setText(suraLinePartPojo1.getSura_arbi());


                ///////////////////////////////////////////////////////




                final TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraBangla);
                SuraNameBangla.setText(suraLinePartPojo1.getSura_bangla());

                TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idSuraBanglaMeaning);
                SuraNameMeaning.setText(suraLinePartPojo1.getSura_bangla_meaning());


                SharedPreferences spf = getSharedPreferences("SeekValueP",Context.MODE_PRIVATE);
                seekvalue = spf.getString("skv","15");
                sizeInt = Integer.parseInt(seekvalue);

                if (sizeInt<12){
                    sizeInt = 12;
                }

                SuraName.setTextSize(dpToSp((sizeInt+3), ReadSuraActivity.this));

                SuraNameBangla.setTextSize(dpToSp(sizeInt, ReadSuraActivity.this));
                SuraNameMeaning.setTextSize(dpToSp(sizeInt, ReadSuraActivity.this));

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    //@RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {

                        ShowDialog();


                    }
                });

                Copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Copy.startAnimation(myAnim);

                        Toast.makeText(getContext(), "অনুলিপি করা হয়েছে", Toast.LENGTH_SHORT).show();

                        String SuraName = getIntent().getStringExtra("suraName");
                        String ayatnumber = ""+ suraLinePartPojo1.getSura_number();

                        String Source = getString(R.string.Credit);

                        ClipboardManager cm = (ClipboardManager) context
                                .getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(""+SuraName+" আয়াত নং-"+ayatnumber+"\n"+""+ suraLinePartPojo1.getSura_bangla()+"\n"+""+ suraLinePartPojo1.getSura_arbi()+"\n"+ suraLinePartPojo1.getSura_bangla_meaning()+Source);

                    }
                });

                Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Share.startAnimation(myAnim);

                        String SuraName = getIntent().getStringExtra("suraName");
                        String ayatnumber = ""+ suraLinePartPojo1.getSura_number();
                        String Source = getString(R.string.Credit);

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+SuraName+" আয়াত নং-"+ayatnumber+"\n"+""+ suraLinePartPojo1.getSura_arbi()+"\n"+ suraLinePartPojo1.getSura_bangla()+"\n"+ suraLinePartPojo1.getSura_bangla_meaning()+Source);
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                });

                Bookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Bookmark.startAnimation(myAnim);

                        String SuraName = getIntent().getStringExtra("suraName");

                        String name = ""+SuraName;
                        String ayatnumber = ""+ suraLinePartPojo1.getSura_number();
                        String ayat = ""+ suraLinePartPojo1.getSura_arbi();
                        String spelling = ""+ suraLinePartPojo1.getSura_bangla();
                        String meaning = ""+ suraLinePartPojo1.getSura_bangla_meaning();
                        String id = "";


                        Boolean result = myDatabasehelper.findThis(ayat);

                        if (result==true){
                            Toast.makeText(ReadSuraActivity.this, "আয়াত সংরক্ষিত আছে", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(ReadSuraActivity.this, "আয়াত সংরক্ষিত হয়েছে", Toast.LENGTH_SHORT).show();
                            long rowId =  myDatabasehelper.insertData(name,ayatnumber,ayat,spelling,meaning);
                        }

                    }
                });




            }

            return customView;

        }
    }


    public static float pxFromDp(float dp, Context mContext) {
        return dp * mContext.getResources().getDisplayMetrics().density;
    }

    public static int dpToSp(float dp, Context context) {
        return (int) (pxFromDp(dp, context) / context.getResources().getDisplayMetrics().scaledDensity);
    }



   // @RequiresApi(api = Build.VERSION_CODES.O)
    public void ShowDialog()
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final SeekBar seek = new SeekBar(this);
        seek.setMax(20);

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



    public int getCategory() {

        return pvalue;
    }

    private void ToolBar() {

        mToolbar = findViewById( R.id.SuraName_toolbar );
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

        String SuraName = getIntent().getStringExtra("suraName");


        //toolbar name ==>
        if (SuraName.equals("আল ফাতিহা"))
        {
            tvBismillah.setVisibility(View.GONE);
        }
        if (SuraName.equals("আত-তাওবাহ্\u200C"))
        {
            tvBismillah.setVisibility(View.GONE);
        }

        mTitle.setText(""+SuraName);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(this, SuraActivity.class);
        startActivity(back);
        finish();
    }
}