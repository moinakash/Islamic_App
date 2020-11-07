package com.example.islamicappb.fragments;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.islamicappb.DatabaseHelper;
import com.example.islamicappb.activity.Amol_Activity;
import com.example.islamicappb.activity.BookmarkActivity;
import com.example.islamicappb.R;
import com.example.islamicappb.SuraActivity;
import com.example.islamicappb.activity.AllahNameActivity;
import com.example.islamicappb.activity.HadisTypeActivity;
import com.example.islamicappb.activity.KalimaActivity;
import com.example.islamicappb.pojo_classes.AmolPojoClass;


public class QuranMajidFragment extends Fragment {

    Button btnSura, btBookmark, btnAllahName, btnHadis, btnKalima;
    DatabaseHelper db;


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


        db = new DatabaseHelper(getContext());

        btnSura = view.findViewById(R.id.idSuraActivity);
        btnAllahName = view.findViewById(R.id.idAllahNameBtn);
        btBookmark = view.findViewById(R.id.idBookmarkActivity);

        btnKalima = view.findViewById(R.id.idKalimaActivity);
        btnHadis = view.findViewById(R.id.idHadis);

        btnSura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    fetchData();
                    loadData();
                    Intent suraIntent = new Intent(getContext(), SuraActivity.class);
                    startActivity(suraIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Your phone does not support this feature", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnAllahName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    fetchData();
                    loadData();
                    Intent suraIntent = new Intent(getContext(), AllahNameActivity.class);
                    startActivity(suraIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Your phone does not support this feature", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    fetchData();
                    loadData();
                    Intent suraIntent = new Intent(getContext(), BookmarkActivity.class);
                    startActivity(suraIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Your phone does not support this feature", Toast.LENGTH_SHORT).show();
                }

            /*    Intent AllahNameIntent = new Intent(getContext(), BookmarkActivity.class);
                startActivity(AllahNameIntent);*/
            }
        });
        btnKalima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    fetchData();
                    loadData();
                    Intent suraIntent = new Intent(getContext(), KalimaActivity.class);
                    startActivity(suraIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Your phone does not support this feature", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnHadis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    fetchData();
                    loadData();
                    Intent suraIntent = new Intent(getContext(), HadisTypeActivity.class);
                    startActivity(suraIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Your phone does not support this feature", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;

    }



    public void fetchData()
    {

        db = new DatabaseHelper(getContext());
        try {
            db.createDataBase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadData() {

        Cursor cursor = db.amoldata();

        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){
                      }
        }



    }


    }


