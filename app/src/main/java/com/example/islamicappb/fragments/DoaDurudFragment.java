package com.example.islamicappb.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.islamicappb.R;
import com.example.islamicappb.activity.Amol_Activity;
import com.example.islamicappb.activity.BaseActivity;
import com.example.islamicappb.activity.BisheshNamajActivity;
import com.example.islamicappb.activity.EightDivisonActivity;
import com.example.islamicappb.activity.InfoActivity;
import com.example.islamicappb.pojo_classes.ConverterClass;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoaDurudFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoaDurudFragment extends Fragment {

    LinearLayout btnNamajerNiom, btnBisheshNamaj, btnMonajaterNiom, btnForojOSunnot,btnDoaODurud, btnTaharat, btnShoriyot, btnGoLoc, btnRojarSomoy;

    ImageView Info;

    ConverterClass converterClass;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoaDurudFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoaDurudFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoaDurudFragment newInstance(String param1, String param2) {
        DoaDurudFragment fragment = new DoaDurudFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_namaj_niom, container, false);

        btnNamajerNiom = view.findViewById(R.id.idNamajerNiom);
        btnBisheshNamaj = view.findViewById(R.id.idBiseshNamaj);
        btnMonajaterNiom = view.findViewById(R.id.idMonajaterNiom);
        btnForojOSunnot = view.findViewById(R.id.idForojOSunnot);
        btnDoaODurud = view.findViewById(R.id.idDoaoDurud);
        btnTaharat = view.findViewById(R.id.idTaharat);
        btnShoriyot = view.findViewById(R.id.idShoriot);
        btnGoLoc = view.findViewById(R.id.idGoLocation);
        btnRojarSomoy = view.findViewById(R.id.idRojar_somoy_suchi);
        Info = view.findViewById(R.id.idInformation);

        converterClass = new ConverterClass(getContext());


        btnNamajerNiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), BisheshNamajActivity.class);
                in.putExtra("shoriot","0");
                startActivity(in);
            }
        });

        btnBisheshNamaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), BisheshNamajActivity.class);
                in.putExtra("shoriot","1");
                startActivity(in);
            }
        });

        btnMonajaterNiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), Amol_Activity.class);
             //   in.putExtra("shoriot","2");
                startActivity(in);
            }
        });

        btnForojOSunnot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), BisheshNamajActivity.class);
                in.putExtra("shoriot","3");
                startActivity(in);
            }
        });

        btnDoaODurud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), BisheshNamajActivity.class);
                in.putExtra("shoriot","4");
                startActivity(in);
            }
        });

        btnTaharat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), BisheshNamajActivity.class);
                in.putExtra("shoriot","5");
                startActivity(in);
            }
        });

        btnShoriyot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), BisheshNamajActivity.class);
                in.putExtra("shoriot","6");
                startActivity(in);
            }
        });

        btnRojarSomoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), InfoActivity.class);

                startActivity(in);
            }
        });

        SharedPreferences sharedPrefMM = getActivity().getSharedPreferences("mm",0);

        sharedPrefMM = getActivity().getSharedPreferences("mm",0);

        String kk = "0";
        SharedPreferences.Editor editor = sharedPrefMM.edit();
        editor.putString("key", kk);

        editor.commit();

        final SharedPreferences finalSharedPrefMM = sharedPrefMM;

        btnGoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String kk = "1";
                SharedPreferences.Editor editor = finalSharedPrefMM.edit();
                editor.putString("key", kk);

                editor.commit();

                Intent go = new Intent(getContext(), EightDivisonActivity.class);
                startActivity(go);


            }
        });






        return view;
    }




    void showDialog() {



/*
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.rojar_somoy_suchi_alert, null);*/

        final Dialog alertDialog = new Dialog(getContext());

        alertDialog.setContentView(R.layout.rojar_somoy_suchi_alert);

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView image = alertDialog.findViewById(R.id.img);

        TextView Saharir_shesh_somoy = alertDialog.findViewById(R.id.sahari_sesh_somoy);
        TextView Iftarir_somoy = alertDialog.findViewById(R.id.iftarir_somoy);
        TextView Exit = alertDialog.findViewById(R.id.exit);



        BaseActivity activity = (BaseActivity) getActivity();
        String fojorerTime = activity.fojorData();


        Character [] array= new Character[fojorerTime.length()];

        for (int i = 0; i < fojorerTime.length(); i++) {
            array[i] = fojorerTime.charAt(i);
        }
        String fojorTimeBangla = ""+array[0]+""+array[1]+""+array[2]+""+array[3];

        String SfHour = ""+array[0];
        String SfMinute = ""+array[2]+""+array[3];
        final int fHour = Integer.parseInt(SfHour);
        final int fMinute = Integer.parseInt(SfMinute);


        fojorTimeBangla = converterClass.covertS(fojorTimeBangla);

//        fojorTimeBangla = fojorTimeBangla.replace("0", "০");
//        fojorTimeBangla = fojorTimeBangla.replace("1", "১");
//        fojorTimeBangla = fojorTimeBangla.replace("2", "২");
//        fojorTimeBangla = fojorTimeBangla.replace("3", "৩");
//        fojorTimeBangla = fojorTimeBangla.replace("4", "৪");
//        fojorTimeBangla = fojorTimeBangla.replace("5", "৫");
//        fojorTimeBangla = fojorTimeBangla.replace("6", "৬");
//        fojorTimeBangla = fojorTimeBangla.replace("7", "৭");
//        fojorTimeBangla = fojorTimeBangla.replace("8", "৮");
//        fojorTimeBangla = fojorTimeBangla.replace("9", "৯");

        Saharir_shesh_somoy.setText(""+fojorTimeBangla);



        String magriberTime = activity.magribData();

        Character [] magribarray= new Character[magriberTime.length()];

        for (int i = 0; i < magriberTime.length(); i++) {
            magribarray[i] = magriberTime.charAt(i);
        }

        String txtMagrib = ""+magribarray[0]+""+magribarray[1];
        int mInt = Integer.parseInt(txtMagrib);
        int mgInt = mInt;
        if (mInt>12){
            mInt = mInt - 12;
        }

        String magribTimeBangla = ""+mInt+""+magribarray[2]+""+magribarray[3]+""+magribarray[4];

        //String SfHour = ""+array[0]+""+array[1];
        String SmMinute = ""+magribarray[3]+""+magribarray[4];
        final int mHour = mgInt;
        final int mMinute = Integer.parseInt(SmMinute);


        magribTimeBangla = converterClass.covertS(magribTimeBangla);

        magribTimeBangla= magribTimeBangla.replace("0", "০");
        magribTimeBangla= magribTimeBangla.replace("1", "১");
        magribTimeBangla= magribTimeBangla.replace("2", "২");
        magribTimeBangla= magribTimeBangla.replace("3", "৩");
        magribTimeBangla= magribTimeBangla.replace("4", "৪");
        magribTimeBangla= magribTimeBangla.replace("5", "৫");
        magribTimeBangla= magribTimeBangla.replace("6", "৬");
        magribTimeBangla= magribTimeBangla.replace("7", "৭");
        magribTimeBangla= magribTimeBangla.replace("8", "৮");
        magribTimeBangla= magribTimeBangla.replace("9", "৯");


        Iftarir_somoy.setText(""+magribTimeBangla);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });


        alertDialog.show();



    }
}