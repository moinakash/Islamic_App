package com.example.islamicappb.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.islamicappb.activity.BookmarkActivity;
import com.example.islamicappb.R;
import com.example.islamicappb.SuraActivity;
import com.example.islamicappb.activity.AllahNameActivity;
import com.example.islamicappb.activity.HadisTypeActivity;
import com.example.islamicappb.activity.KalimaActivity;


public class QuranMajidFragment extends Fragment {

    Button btnSura, btBookmark, btnAllahName, btnHadis, btnKalima;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public QuranMajidFragment() {

    }


    public static QuranMajidFragment newInstance(String param1, String param2) {
        QuranMajidFragment fragment = new QuranMajidFragment();
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

        View view = inflater.inflate(R.layout.fragment_quranmajid, container, false);



        btnSura = view.findViewById(R.id.idSuraActivity);
        btnAllahName = view.findViewById(R.id.idAllahNameBtn);
        btBookmark = view.findViewById(R.id.idBookmarkActivity);

        btnKalima = view.findViewById(R.id.idKalimaActivity);
        btnHadis = view.findViewById(R.id.idHadis);

        btnSura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent suraIntent = new Intent(getContext(), SuraActivity.class);
                startActivity(suraIntent);
            }
        });

        btnAllahName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent AllahNameIntent = new Intent(getContext(), AllahNameActivity.class);
                startActivity(AllahNameIntent);
            }
        });


        btBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent AllahNameIntent = new Intent(getContext(), BookmarkActivity.class);
                startActivity(AllahNameIntent);
            }
        });
        btnKalima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent KalimaIntent = new Intent(getContext(), KalimaActivity.class);
                startActivity(KalimaIntent);
            }
        });

        btnHadis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HadisIntent = new Intent(getContext(), HadisTypeActivity.class);
                startActivity(HadisIntent);
            }
        });

        return view;

    }


}