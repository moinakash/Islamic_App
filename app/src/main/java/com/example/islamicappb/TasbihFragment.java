package com.example.islamicappb;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;


public class TasbihFragment extends Fragment {




    GifImageView gifImageView;
    ImageView imageView;
    Button button, btnReset, btn33, btn99, btnttlr, btnSound;
    TextView tvCurrentCounter, tvSetCount, tvTotalcount;



    int currentcounter =0;
    int countset = 33;
    int totalcount =0;
    int SoundInt = 0;
    String defaultValue;
    MediaPlayer mp;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public TasbihFragment() {

    }


    public static TasbihFragment newInstance(String param1, String param2) {
        TasbihFragment fragment = new TasbihFragment();
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
        View view = inflater.inflate(R.layout.fragment_tasbih, container, false);


        imageView = view.findViewById(R.id.idImage);
        gifImageView = view.findViewById(R.id.idGif);
        button = view.findViewById(R.id.idButton);
        btnReset = view.findViewById(R.id.idReset);
        tvCurrentCounter = view.findViewById(R.id.idCurrentCount);
        btn33 = view.findViewById(R.id.id33);
        btn99 = view.findViewById(R.id.id99);
        btnSound = view.findViewById(R.id.idSound);
        tvSetCount = view.findViewById(R.id.idCountSet);
        tvTotalcount = view.findViewById(R.id.idtotalCount);
        btnttlr = view.findViewById(R.id.idttlr);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        currentcounter = sharedPref.getInt("Count",0);
        countset = sharedPref.getInt("CountSet",0);
        totalcount = sharedPref.getInt("TotalCount",0);
        SoundInt = sharedPref.getInt("Soundintt",0);
        tvCurrentCounter.setText(""+ currentcounter);
        tvTotalcount.setText(""+ totalcount);



        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SoundInt == 0){
                    SoundInt = 1;

                    btnSound.setText("Sound Mode");

                }else if (SoundInt == 1){
                    SoundInt = 2;
                    btnSound.setText("Vibrator Mode");
                }else if (SoundInt == 2){
                    SoundInt = 0;
                    btnSound.setText("sound off mode");
                }

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("Soundintt", Integer.parseInt(""+ SoundInt));
                editor.commit();

            }
        });


        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSetCount.setText("33");

                if (countset>33){
                    if (currentcounter>33){
                        tvCurrentCounter.setText(""+(currentcounter%33));
                        currentcounter = currentcounter%33;
                    }

                }



                countset = 33;
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("CountSet", Integer.parseInt(""+ countset));
                editor.putInt("TotalCount", Integer.parseInt(""+ totalcount));
                editor.putInt("Count", Integer.parseInt(""+ currentcounter));
                editor.commit();





            }
        });

        btn99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSetCount.setText("99");
                countset = 99;
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("CountSet", Integer.parseInt(""+ countset));
                editor.commit();
            }
        });


        mp = MediaPlayer.create(getActivity(), R.raw.metaltick);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (SoundInt == 1){

                    try {
                        if (mp.isPlaying()){
                            mp.stop();
                            mp.release();
                            mp = MediaPlayer.create(getContext(),R.raw.metaltick);
                        } mp.start();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                if (SoundInt == 2){

                    try {
                        Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(100);

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }





                if (currentcounter == countset){
                    currentcounter = 0;
                }

//                else if(currentcounter>countset){
//                    currentcounter = currentcounter%countset;
//                }

                currentcounter = currentcounter +1;
                totalcount = totalcount +1;




                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("Count", Integer.parseInt(""+ currentcounter));
                editor.putInt("TotalCount", Integer.parseInt(""+ totalcount));
                editor.commit();

                //defaultValue = sharedPref.getString("LastSuraName","");

                tvCurrentCounter.setText(""+ currentcounter);
                tvTotalcount.setText(""+totalcount);



                thread();

            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentcounter = 00;

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("Count", Integer.parseInt(""+ currentcounter));
                editor.commit();

                //defaultValue = sharedPref.getString("LastSuraName","");

                tvCurrentCounter.setText(""+ currentcounter);


            }
        });

        btnttlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentcounter = 00;
                totalcount = 00;


                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("Count", Integer.parseInt(""+ currentcounter));
                editor.putInt("TTlCount", Integer.parseInt(""+ totalcount));
                editor.commit();

                //defaultValue = sharedPref.getString("LastSuraName","");

                tvCurrentCounter.setText(""+ currentcounter);
                tvTotalcount.setText(""+ totalcount);


            }
        });








        return view;
    }

    private void thread() {

        imageView.setVisibility(View.GONE);

        gifImageView.setVisibility(View.VISIBLE);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.VISIBLE);

                gifImageView.setVisibility(View.GONE);


            }
        }, 200);

    }


}