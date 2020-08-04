package com.example.islamicappb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoaDurudFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoaDurudFragment extends Fragment {

    LinearLayout btnNamajerNiom, btnBisheshNamaj, btnMonajaterNiom, btnForojOSunnot,btnDoaODurud, btnTaharat, btnShoriyot, btnGoLoc, btnRojarSomoy;

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


        btnNamajerNiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), NamajerNiomActivity.class);
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
                Intent in = new Intent(getContext(), BisheshNamajActivity.class);
                in.putExtra("shoriot","2");
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

                Intent go = new Intent(getContext(),EightDivisonActivity.class);
                startActivity(go);


            }
        });






        return view;
    }




    void showDialog() {




        final LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.rojar_somoy_suchi_alert, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();

        ImageView image = view.findViewById(R.id.img);

        TextView Saharir_shesh_somoy = view.findViewById(R.id.sahari_sesh_somoy);
        TextView Iftarir_somoy = view.findViewById(R.id.iftarir_somoy);
        Button Exit = view.findViewById(R.id.exit);

        Exit.setText("ঠিক আছে");

        Saharir_shesh_somoy.setText("----");
        Iftarir_somoy.setText("----");

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });


        alertDialog.show();



    }
}