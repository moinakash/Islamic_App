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

            SuraLinePart suraLinePart1 = suraLinePart.get(position);


            if(suraLinePart1 !=null)
            {

                TextView SuraName = (TextView) customView.findViewById(R.id.idSuraArbi);
                SuraName.setText(suraLinePart1.getSura_arbi());



                TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idSuraBangla);
                SuraNameBangla.setText(suraLinePart1.getSura_bangla());

                TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idSuraBanglaMeaning);
                SuraNameMeaning.setText(suraLinePart1.getSura_bangla_meaning());



            }

            return customView;

        }
    }
