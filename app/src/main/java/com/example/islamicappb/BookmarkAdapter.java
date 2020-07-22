package com.example.islamicappb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookmarkAdapter extends ArrayAdapter<BookmarkPojoClass> {

    private List<BookmarkPojoClass> bookmarkPojoClasses;
    private Context context;


    public BookmarkAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<BookmarkPojoClass> objects) {
        super(context, textViewResourceId, objects);

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



        BookmarkPojoClass bookmarkPojoClass =bookmarkPojoClasses.get(position);


        if(bookmarkPojoClass !=null) {

            TextView AyatNumber = (TextView) customView.findViewById(R.id.idAyatNumber);
            AyatNumber.setText(bookmarkPojoClass.getAyat_number());


            TextView SuraName = (TextView) customView.findViewById(R.id.idSuraName);
            SuraName.setText(bookmarkPojoClass.getSura_name());

            TextView AyatArbi = (TextView) customView.findViewById(R.id.idAyatArbi);
            AyatArbi.setText(bookmarkPojoClass.getSura_arbi_line());


            TextView AyatBangla = (TextView) customView.findViewById(R.id.idAyatBangla);
            AyatBangla.setText(bookmarkPojoClass.getSura_spelling_line());

            TextView AyatMeaning = (TextView) customView.findViewById(R.id.idAyatBanglaMeaning);
            AyatMeaning.setText(bookmarkPojoClass.getSura_meaning_line());


        }


        return customView;

    }

}


