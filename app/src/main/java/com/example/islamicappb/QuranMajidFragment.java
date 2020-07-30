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
 * Use the {@link QuranMajidFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuranMajidFragment extends Fragment {

    Button btnSura, btBookmark, btnAllahName, btnNamajerNiom, btnKalima;

    //changes

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuranMajidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quranmajid, container, false);



        btnSura = view.findViewById(R.id.idSuraActivity);
        btnAllahName = view.findViewById(R.id.idAllahNameBtn);
        btBookmark = view.findViewById(R.id.idBookmarkActivity);
        /*btnNamajerNiom = view.findViewById(R.id.idNiomActivity);*/
        btnKalima = view.findViewById(R.id.idKalimaActivity);

        btnSura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent suraIntent = new Intent(getContext(),SuraActivity.class);
                startActivity(suraIntent);
            }
        });

        btnAllahName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent AllahNameIntent = new Intent(getContext(),AllahNameActivity.class);
                startActivity(AllahNameIntent);
            }
        });


        btBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent AllahNameIntent = new Intent(getContext(),BookmarkActivity.class);
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

        return view;

    }


}