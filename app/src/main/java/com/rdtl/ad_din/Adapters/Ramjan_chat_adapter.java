package com.rdtl.ad_din.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rdtl.ad_din.R;
import com.rdtl.ad_din.pojo_classes.ramjan_chart_modelClass;

import java.util.List;

public class Ramjan_chat_adapter extends ArrayAdapter<ramjan_chart_modelClass> {

    private List<ramjan_chart_modelClass> ramjan_chart_modelClassList;
    private Context context;

    public Ramjan_chat_adapter(@NonNull Context context, int textViewResourceId, List<ramjan_chart_modelClass> ramjan_chart_modelClassList) {
        super(context, textViewResourceId, ramjan_chart_modelClassList);
        this.context = context;
        this.ramjan_chart_modelClassList = ramjan_chart_modelClassList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        SharedPreferences prefForRamjan;
        prefForRamjan = getContext().getSharedPreferences("Ramjan",0);
        String arabikYr = prefForRamjan.getString("arabikyr","১৪৪২");
        String arabikDy = prefForRamjan.getString("arabikdy","১");
        String engYr = prefForRamjan.getString("engyr","২০২১");

        View customView = convertView;
        if (customView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = vi.inflate(R.layout.chart_items, null);

        }

        ramjan_chart_modelClass ramjan_chart_modelClass_obj = ramjan_chart_modelClassList.get(position);


        if (ramjan_chart_modelClass_obj != null) {


            LinearLayout llparent = (LinearLayout) customView.findViewById(R.id.idChartparent);

            TextView Ramjan_chart_id = (TextView) customView.findViewById(R.id.id_roja_num);
            Ramjan_chart_id.setText(""+ramjan_chart_modelClass_obj.getNumber());



            TextView Ramjan_chart_date = (TextView) customView.findViewById(R.id.id_roja_date);
            Ramjan_chart_date.setText(""+ramjan_chart_modelClass_obj.getDate());


            if (ramjan_chart_modelClass_obj.getNumber().equals(""+arabikDy)){

                llparent.setBackgroundColor(Color.parseColor("#DCB742"));
            }else {

                llparent.setBackgroundColor(Color.TRANSPARENT);

            }

        }

        return customView;

    }
}