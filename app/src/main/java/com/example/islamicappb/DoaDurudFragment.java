package com.example.islamicappb;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoaDurudFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoaDurudFragment extends Fragment {

    Button btnNamajerNiom, btnBisheshNamaj, btnMonajaterNiom, btnForojOSunnot,btnDoaODurud, btnTaharat, btnShoriyot;

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
        View view = inflater.inflate(R.layout.fragment_doa_durud, container, false);

        btnNamajerNiom = view.findViewById(R.id.idNamajerNiom);
        btnBisheshNamaj = view.findViewById(R.id.idBiseshNamaj);
        btnMonajaterNiom = view.findViewById(R.id.idMonajaterNiom);
        btnForojOSunnot = view.findViewById(R.id.idForojOSunnot);
        btnDoaODurud = view.findViewById(R.id.idDoaoDurud);
        btnTaharat = view.findViewById(R.id.idTaharat);
        btnShoriyot = view.findViewById(R.id.idShoriot);


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





        return view;
    }
}