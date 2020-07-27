package com.example.islamicappb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ReadSuraActivity extends AppCompatActivity {


    String sessionId;
    public static int pvalue = 1;
    DatabaseHelper db;
    private ListView listView2;
    List<SuraLinePart> suraLinePart1;

    SuraLineAdapter suraLineAdapter;

    MyDatabasehelper myDatabasehelper;

    FloatingActionButton floatingActionButton;

    public int sizeInt = 12;

    public String seekvalue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_read_sura);

        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();


        sessionId = getIntent().getStringExtra("position");
        // Toast.makeText(this, "sId "+sessionId, Toast.LENGTH_SHORT).show();
        pvalue =  Integer.parseInt(sessionId);


        floatingActionButton = findViewById(R.id.idfloatingActionButton);

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

                suraLinePart1.add(new SuraLinePart(""+cursor.getString(7),""+cursor.getString(9),""+cursor.getString(10),""+cursor.getString(4)));

            }
        }


        suraLineAdapter = new SuraLineAdapter(this,R.layout.custom_sura_part, suraLinePart1);
        listView2.setAdapter(suraLineAdapter);

    }


    public class SuraLineAdapter extends ArrayAdapter<SuraLinePart> {

        private List<SuraLinePart> suraLinePart;
        private Context context;

        TextView SuraName;

        public SuraLineAdapter(@NonNull Context context, int textViewResourceId, List<SuraLinePart> suraLinePart) {
            super(context, textViewResourceId, suraLinePart);
            this.context = context;
            this.suraLinePart = suraLinePart;

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

            final SuraLinePart suraLinePart1 = suraLinePart.get(position);


            if(suraLinePart1 !=null)
            {

                ImageButton Copy = (ImageButton) customView.findViewById(R.id.idSuraCopy);
                ImageButton Bookmark = (ImageButton) customView.findViewById(R.id.idSuraBookmark);
                ImageButton Share = (ImageButton) customView.findViewById(R.id.idSuraShare);

                TextView AyatNumber = (TextView) customView.findViewById(R.id.idAyatNumber);
                AyatNumber.setText(suraLinePart1.getSura_number());

                SuraName = (TextView) customView.findViewById(R.id.idSuraArbi);
                SuraName.setText(suraLinePart1.getSura_arbi());


                ///////////////////////////////////////////////////////




                final TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraBangla);
                SuraNameBangla.setText(suraLinePart1.getSura_bangla());

                TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idSuraBanglaMeaning);
                SuraNameMeaning.setText(suraLinePart1.getSura_bangla_meaning());


                SharedPreferences spf = getSharedPreferences("SeekValueP",Context.MODE_PRIVATE);
                seekvalue = spf.getString("skv","15");
                sizeInt = Integer.parseInt(seekvalue);

                if (sizeInt<12){
                    sizeInt = 12;
                }
                //SuraName.setTextSize(pxFromDp((sizeInt+3), ReadSuraActivity.this));
                SuraName.setTextSize(dpToSp((sizeInt+3), ReadSuraActivity.this));
                AyatNumber.setTextSize(dpToSp(sizeInt, ReadSuraActivity.this));
                SuraNameBangla.setTextSize(dpToSp(sizeInt, ReadSuraActivity.this));
                SuraNameMeaning.setTextSize(dpToSp(sizeInt, ReadSuraActivity.this));
               // SuraNameMeaning.setTextSize(pxFromDp(sizeInt, ReadSuraActivity.this));

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

                        Toast.makeText(getContext(), "copied", Toast.LENGTH_SHORT).show();

                        ClipboardManager cm = (ClipboardManager) context
                                .getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(""+suraLinePart1.getSura_bangla()+"\n"+""+suraLinePart1.getSura_arbi());

                    }
                });

                Share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+suraLinePart1.getSura_arbi()+"\n"+suraLinePart1.getSura_bangla());
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                });

                Bookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String SuraName = getIntent().getStringExtra("suraName");

                        String name = ""+SuraName;
                        String ayatnumber = ""+suraLinePart1.getSura_number();
                        String ayat = ""+suraLinePart1.getSura_arbi();
                        String spelling = ""+suraLinePart1.getSura_bangla();
                        String meaning = ""+suraLinePart1.getSura_bangla_meaning();
                        String id = "";


                        Boolean result = myDatabasehelper.findThis(ayat);

                        if (result==true){
                            Toast.makeText(ReadSuraActivity.this, "already Exist", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(ReadSuraActivity.this, "added", Toast.LENGTH_SHORT).show();
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
        //seek.setMin(5);
        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Text size: ");
        popDialog.setView(seek);

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

// Button OK

//        popDialog.setPositiveButton("OK",
//
//                new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                        dialog.dismiss();
//
//                    }
//
//                });


        popDialog.setMessage("farhad");


        popDialog.create();

        popDialog.show();

    }



    public int getCategory() {

        return pvalue;
    }

}