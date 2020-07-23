package com.example.islamicappb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookmarkAdapter extends ArrayAdapter<BookmarkPojoClass> {

    private List<BookmarkPojoClass> bookmarkPojoClasses;
    private Context context;

    MyDatabasehelper myDatabasehelper;

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

            Button Delete = (Button) customView.findViewById(R.id.idDeleteBookmark);

            TextView AyatNumber = (TextView) customView.findViewById(R.id.idAyatNumber);
            AyatNumber.setText(bookmarkPojoClass1.getAyat_number());


            TextView SuraName = (TextView) customView.findViewById(R.id.idSuraName);
            SuraName.setText(bookmarkPojoClass1.getSura_name());

            TextView AyatArbi = (TextView) customView.findViewById(R.id.idAyatArbi);
            AyatArbi.setText(bookmarkPojoClass1.getSura_arbi_line());


            TextView AyatBangla = (TextView) customView.findViewById(R.id.idAyatBangla);
            AyatBangla.setText(bookmarkPojoClass1.getSura_spelling_line());

            TextView AyatMeaning = (TextView) customView.findViewById(R.id.idAyatBanglaMeaning);
            AyatMeaning.setText(bookmarkPojoClass1.getSura_meaning_line());

            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myDatabasehelper = new MyDatabasehelper(context);
                    SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();

                    String ayat = ""+bookmarkPojoClass1.getSura_arbi_line();
                    Log.e("arbiayat",""+ayat);

                    int value = myDatabasehelper.deleteData(ayat);

//                    if (value>0) {
//                        Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT).show();
//                    } else {
//                         //ayat = myDatabasehelper.deleteData(ayat);
//                        Toast.makeText(context, "deletd", Toast.LENGTH_SHORT).show();
//                    }


//                    context.finish();
//                    context.startActivity();
                    
                }
            });


        }


        return customView;

    }

}


