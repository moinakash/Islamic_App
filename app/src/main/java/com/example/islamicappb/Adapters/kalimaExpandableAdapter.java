package com.example.islamicappb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.islamicappb.R;
import com.example.islamicappb.pojo_classes.PojoClassForKalima;

import java.util.HashMap;
import java.util.List;

public class kalimaExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> title;
    private HashMap<String, PojoClassForKalima> expandableListDetalles;

    public kalimaExpandableAdapter(Context context,
                                   List<String> title,
                                   HashMap<String, PojoClassForKalima> expandableListDetalles) {


        this.context = context;
        this.title = title;
        this.expandableListDetalles = expandableListDetalles;
    }


    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final PojoClassForKalima pojoClassForKalima = (PojoClassForKalima) getChild(groupPosition, childPosition);

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.list_item, null);

        }




        final TextView textKalimaArbi = convertView.findViewById(R.id.idKalimaArbi);
        final TextView textKalimaUccharon = convertView.findViewById(R.id.idKalimaUccharon);
        final TextView textkalimaOrtho = convertView.findViewById(R.id.idKalimaBangla);

        textKalimaArbi.setText(""+ pojoClassForKalima.getArbitext());
        textKalimaUccharon.setText(""+ pojoClassForKalima.getUccharontext());
        textkalimaOrtho.setText(""+ pojoClassForKalima.getOrtho());


        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        convertView.startAnimation(animation);


        return convertView;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {


        String number = (String) getGroup(groupPosition);
        PojoClassForKalima pojoClassForKalima = (PojoClassForKalima) getChild(groupPosition,0);

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.custom_kalima_name_list_item, null);

        }

        TextView KalimaNaaam = convertView.findViewById(R.id.idKalimaNaaam);

        KalimaNaaam.setText(pojoClassForKalima.getNumber());

        return convertView;
    }


    @Override
    public int getGroupCount() {
        return this.title.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.title.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListDetalles.get(this.title.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
