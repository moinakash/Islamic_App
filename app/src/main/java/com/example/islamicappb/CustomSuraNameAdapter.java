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

public class CustomSuraNameAdapter extends ArrayAdapter<SuraNameListPojo> {

    private List<SuraNameListPojo> suraNameListPojoList;
    private Context context;

    public CustomSuraNameAdapter(@NonNull Context context, int textViewResourceId, List<SuraNameListPojo> suraNameListPojoList) {
        super(context, textViewResourceId, suraNameListPojoList);
        this.context = context;
        this.suraNameListPojoList = suraNameListPojoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View customView= convertView;
        if (customView==null)
        {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = vi.inflate(R.layout.custom_suraname_layout, null);

        }

        SuraNameListPojo suraNameListPojo = suraNameListPojoList.get(position);


        if(suraNameListPojo !=null)
        {

            TextView SuraName = (TextView) customView.findViewById(R.id.idSuraNumber);
            SuraName.setText(suraNameListPojo.getSura_number());

            // thumb image
//            ImageView imageView = (ImageView) customView.findViewById(R.id.userImg);
//            imageView.setImageResource(user.getImgRes());

            TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraNameBangla);
            SuraNameBangla.setText(suraNameListPojo.getSura_name_bangla());

            TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idSuraNameMeaning);
            SuraNameMeaning.setText(suraNameListPojo.getSura_name_meaning());

            TextView SuraNameArbi = (TextView) customView.findViewById(R.id.idSuraNameArbi);
            SuraNameArbi.setText(suraNameListPojo.getSura_name_arbi());


        }

        return  customView;

    }
}
