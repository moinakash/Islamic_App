package com.example.islamicappb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AllahNameAdapter extends ArrayAdapter<AllahNamePojoClass> {

    private List<AllahNamePojoClass> allahNamePojoClassList;
    private Context context;


    public AllahNameAdapter(@NonNull Context context, int textViewResourceId, List<AllahNamePojoClass> allahNamePojoClassList) {
        super(context, textViewResourceId, allahNamePojoClassList);
        this.context = context;
        this.allahNamePojoClassList = allahNamePojoClassList;
    }


    @Override
    public int getCount() {
        return allahNamePojoClassList.size();
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
            customView = vi.inflate(R.layout.allahname_item_layout, null);
////////////////////////////////////

        }

        final AllahNamePojoClass allahNamePojoClass = allahNamePojoClassList.get(position);


        if(allahNamePojoClass !=null)
        {

            TextView SuraName = (TextView) customView.findViewById(R.id.idAllah_banglaName);
            SuraName.setText(allahNamePojoClass.getAllah_name_bangla());

            TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idAllah_banglaMeaning);
            SuraNameBangla.setText(allahNamePojoClass.getAllah_name_meaning());

            TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idAllah_ArbiName);
            SuraNameMeaning.setText(allahNamePojoClass.getAllah_name_arbi());



        }

        return customView;


    }


}
