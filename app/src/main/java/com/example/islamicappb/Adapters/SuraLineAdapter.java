package com.example.islamicappb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.islamicappb.R;
import com.example.islamicappb.SuraLinePartPojo;

import java.util.List;

public class SuraLineAdapter extends ArrayAdapter<SuraLinePartPojo> {

    private List<SuraLinePartPojo> suraLinePartPojo;
    private Context context;

    public SuraLineAdapter(@NonNull Context context, int textViewResourceId, List<SuraLinePartPojo> suraLinePartPojo) {
        super(context, textViewResourceId, suraLinePartPojo);
        this.context = context;
        this.suraLinePartPojo = suraLinePartPojo;

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

            SuraLinePartPojo suraLinePartPojo1 = suraLinePartPojo.get(position);


            if(suraLinePartPojo1 !=null)
            {

                TextView SuraName = (TextView) customView.findViewById(R.id.idSuraArbi);
                SuraName.setText(suraLinePartPojo1.getSura_arbi());



                TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraBangla);
                SuraNameBangla.setText(suraLinePartPojo1.getSura_bangla());

                TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idSuraBanglaMeaning);
                SuraNameMeaning.setText(suraLinePartPojo1.getSura_bangla_meaning());



            }

            return customView;

        }
    }
