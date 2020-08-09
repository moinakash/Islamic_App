package com.example.islamicappb.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
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

import com.codesgood.views.JustifiedTextView;
import com.example.islamicappb.pojo_classes.BookmarkPojoClass;
import com.example.islamicappb.database.MyDatabasehelper;
import com.example.islamicappb.R;

import java.util.ArrayList;
import java.util.List;



public class BookmarkActivity extends AppCompatActivity {

    Cursor cursor;
    private ListView listView;
    private MyDatabasehelper myDatabasehelper;

    List<BookmarkPojoClass> bookmarkPojoClasses;
    BookmarkAdapter bookmarkAdapter;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_bookmark);
        ToolBar();
        bookmarkPojoClasses = new ArrayList();


        listView = findViewById(R.id.idListView);

        myDatabasehelper = new MyDatabasehelper(this);

        loadData();
    }

    public void loadData() {

        ArrayList<String> listData = new ArrayList<>();

        cursor = myDatabasehelper.showAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "বুকমার্ক তথ্য উপলব্ধ নয়।", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){


                bookmarkPojoClasses.add(new BookmarkPojoClass(""+cursor.getString(1),""+cursor.getString(2),
                        ""+cursor.getString(3),""+cursor.getString(4),
                        ""+cursor.getString(5)));

                     /*         listData.add(cursor.getString(0)+" \t"+cursor.getString(1));
                listData.add(cursor.getString(0)+" \t"+cursor.getString(2));
                listData.add(cursor.getString(0)+" \t"+cursor.getString(3));*/

            }
        }

        bookmarkAdapter = new BookmarkAdapter(this,R.layout.bookmark_list_item, bookmarkPojoClasses);
        listView.setAdapter(bookmarkAdapter);



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String se = adapterView.getItemAtPosition(i).toString();
//                //String selectedvalue = adapterView.getItematPosition(i).toString();
//                Toast.makeText(getApplicationContext(), "Selected value : "+se, Toast.LENGTH_SHORT).show();
//            }
//        });


//        String selectedvalue = adapterView.getrItematPosition(i).toString();
//        Toast.makeText(getApplicationContext(), "Selected value : "+selectedvalue, Toast.LENGTH_SHORT).show();


    }



    public class BookmarkAdapter extends ArrayAdapter<BookmarkPojoClass> {

        private List<BookmarkPojoClass> bookmarkPojoClasses;
        private Context context;
        BookmarkAdapter bookmarkAdapter;

        MyDatabasehelper myDatabasehelper;

        private AlertDialog.Builder alertDialogBuilder;

        public BookmarkAdapter(@NonNull Context context, int textViewResourceId, List<BookmarkPojoClass> bookmarkPojoClasses) {
            super(context, textViewResourceId, bookmarkPojoClasses);

            this.context = context;
            this.bookmarkPojoClasses = bookmarkPojoClasses;


        }



        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View customView= convertView;
            if (customView==null)
            {
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                customView = vi.inflate(R.layout.bookmark_list_item, null);

            }





            final BookmarkPojoClass bookmarkPojoClass1 =bookmarkPojoClasses.get(position);


            if(bookmarkPojoClass1 !=null) {

                final ImageButton Delete = (ImageButton) customView.findViewById(R.id.idDeleteBookmark);
                final ImageButton Copy = (ImageButton) customView.findViewById(R.id.idCopyAyat);
                final ImageButton Share = (ImageButton) customView.findViewById(R.id.idShareAyat);

                TextView AyatNumber = (TextView) customView.findViewById(R.id.idAyatNumber);
                AyatNumber.setText(bookmarkPojoClass1.getAyat_number());


                TextView SuraName = (TextView) customView.findViewById(R.id.idSuraName);
                SuraName.setText(bookmarkPojoClass1.getSura_name());

                JustifiedTextView AyatArbi = (JustifiedTextView) customView.findViewById(R.id.idAyatArbi);
                AyatArbi.setText(Html.fromHtml(bookmarkPojoClass1.getSura_arbi_line()));


                JustifiedTextView AyatBangla = (JustifiedTextView) customView.findViewById(R.id.idAyatBangla);
                AyatBangla.setText(Html.fromHtml(bookmarkPojoClass1.getSura_spelling_line()));

                JustifiedTextView AyatMeaning = (JustifiedTextView) customView.findViewById(R.id.idAyatBanglaMeaning);
                AyatMeaning.setText(Html.fromHtml(bookmarkPojoClass1.getSura_meaning_line()));

                Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                        myAnim.setInterpolator(interpolator);

                        Delete.startAnimation(myAnim);


                        final Dialog alertDialog = new Dialog(BookmarkActivity.this);

                        alertDialog.setContentView(R.layout.delete_alert_dialog);

                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



                        TextView text = alertDialog.findViewById(R.id.idText);
                        Button NO = alertDialog.findViewById(R.id.idButtonNo);
                        Button OK = alertDialog.findViewById(R.id.idButtonOk);

                        text.setText("আপনি কি আয়াতটি মুছে ফেলতে চান?");

                        NO.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });


                        OK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                //delete

                                myDatabasehelper = new MyDatabasehelper(context);
                                SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();

                                String ayat = ""+bookmarkPojoClass1.getSura_arbi_line();
                                Log.e("arbiayat",""+ayat);

                                int value = myDatabasehelper.deleteData(ayat);

                                Toast.makeText(context, "মুছে ফেলা হয়েছে", Toast.LENGTH_SHORT).show();


                                /*  listView.notifyAll();
                                bookmarkAdapter.notifyDataSetChanged();*/

                                finish();
                                startActivity(getIntent());
                            }
                        });
                        alertDialog.show();

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

                        ClipboardManager cm = (ClipboardManager) context
                                .getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(""+bookmarkPojoClass1.getSura_name()+" আয়াত নং- "+bookmarkPojoClass1.getAyat_number()+"\n"+""+bookmarkPojoClass1.getSura_arbi_line()+""+bookmarkPojoClass1.getSura_spelling_line()+""+bookmarkPojoClass1.getSura_meaning_line());

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
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ""+bookmarkPojoClass1.getSura_name()+" আয়াত নং- "+bookmarkPojoClass1.getAyat_number()+"\n"+""+bookmarkPojoClass1.getSura_arbi_line()+""+bookmarkPojoClass1.getSura_spelling_line()+""+bookmarkPojoClass1.getSura_meaning_line());
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                });


            }


            return customView;

        }

    }

    private void ToolBar() {

        mToolbar = findViewById( R.id.Bookmark_toolbar );
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);



        //toolbar name ==>
        mTitle.setText("বুকমার্ক");
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