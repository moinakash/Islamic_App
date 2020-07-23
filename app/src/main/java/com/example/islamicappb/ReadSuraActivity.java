package com.example.islamicappb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sura);

        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();


        sessionId = getIntent().getStringExtra("position");
        // Toast.makeText(this, "sId "+sessionId, Toast.LENGTH_SHORT).show();
        pvalue =  Integer.parseInt(sessionId);



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


                //               listData.add(cursor.getString(6));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(2));
//                listData.add(cursor.getString(0)+" \t"+cursor.getString(3));
            }
        }


        suraLineAdapter = new SuraLineAdapter(this,R.layout.custom_sura_part, suraLinePart1);
        listView2.setAdapter(suraLineAdapter);

//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.idTextView,listData);
//        listView2.setAdapter(adapter);

    }


    public class SuraLineAdapter extends ArrayAdapter<SuraLinePart> {

        private List<SuraLinePart> suraLinePart;
        private Context context;

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
                TextView SuraName = (TextView) customView.findViewById(R.id.idSuraArbi);
                SuraName.setText(suraLinePart1.getSura_arbi());



                final TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraBangla);
                SuraNameBangla.setText(suraLinePart1.getSura_bangla());

                TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idSuraBanglaMeaning);
                SuraNameMeaning.setText(suraLinePart1.getSura_bangla_meaning());



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


                        long rowId =  myDatabasehelper.insertData(name,ayatnumber,ayat,spelling,meaning);

                        if (rowId==-1)
                        {
                            //Toast.makeText(this, "failed ", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            // Toast.makeText(this, "ssfl "+rowId, Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }

            return customView;

        }
    }




    public int getCategory() {

        return pvalue;
    }

}