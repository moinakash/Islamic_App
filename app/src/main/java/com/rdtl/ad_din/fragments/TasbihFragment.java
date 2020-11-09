package com.rdtl.ad_din.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdtl.ad_din.R;
import com.rdtl.ad_din.pojo_classes.ConverterClass;


public class TasbihFragment extends Fragment {





    ImageView btn33, btnSound;
    Button button ;
    LinearLayout btnttlr,btnReset;
    TextView tvCurrentCounter, tvSetCount, tvTotalcount;

    ImageView img_pearl, img_pearl_1, img_pearl_2, img_pearl_3, img_pearl_4, img_pearl_6, img_pearl_7, img_pearl_8, img_pearl_9;

    ConverterClass converterClass;



    int currentcounter =0;
    private int countset = 33;
    private final int constent = 33;
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

        converterClass = new ConverterClass(getContext());




        button = view.findViewById(R.id.idButton);
        btnReset = view.findViewById(R.id.idReset);
        tvCurrentCounter = view.findViewById(R.id.idCurrentCount);
        btn33 = view.findViewById(R.id.id33);

        btnSound =  view.findViewById(R.id.idSound);
        tvSetCount = view.findViewById(R.id.idCountSet);
        tvTotalcount = view.findViewById(R.id.idtotalCount);
        btnttlr = view.findViewById(R.id.idttlr);
        img_pearl = view.findViewById(R.id.id_pearl);
        img_pearl_1 = view.findViewById(R.id.id_pearl_1);
        img_pearl_2 = view.findViewById(R.id.id_pearl_2);
        img_pearl_3 = view.findViewById(R.id.id_pearl_3);
        img_pearl_4 = view.findViewById(R.id.id_pearl_4);
        img_pearl_6 = view.findViewById(R.id.id_pearl_6);
        img_pearl_7 = view.findViewById(R.id.id_pearl_7);
        img_pearl_8 = view.findViewById(R.id.id_pearl_8);
        img_pearl_9 = view.findViewById(R.id.id_pearl_9);





        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        currentcounter = sharedPref.getInt("Count",0);
        countset = sharedPref.getInt("CountSet",33);
        totalcount = sharedPref.getInt("TotalCount",0);
        SoundInt = sharedPref.getInt("Soundintt",0);

        if (SoundInt == 0){
            btnSound.setImageResource(R.drawable.sound_off_mode);
        }else if (SoundInt == 1){
            btnSound.setImageResource(R.drawable.sound_on_mode);
        }else if (SoundInt == 2){
            btnSound.setImageResource(R.drawable.vibret_on_mode);
        }

        if (countset==33){
            btn33.setImageResource(R.drawable.button33);
        }else if (countset==99){
            btn33.setImageResource(R.drawable.button99);
        }


        /////////////////////////////////////////////////////////
        String CC = String.valueOf(currentcounter);

        CC = converterClass.covertS(CC);


        tvCurrentCounter.setText(""+CC);

        String tC = String.valueOf(totalcount);

        tC = converterClass.covertS(tC);


        tvTotalcount.setText(""+tC);



        String cS = String.valueOf(countset);

        cS = converterClass.covertS(cS);



        tvSetCount.setText(""+cS);



        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SoundInt == 0){
                    SoundInt = 1;

                    btnSound.setImageResource(R.drawable.sound_on_mode);


                }else if (SoundInt == 1){
                    SoundInt = 2;
                    btnSound.setImageResource(R.drawable.vibret_on_mode);

                }else if (SoundInt == 2){
                    SoundInt = 0;
                    btnSound.setImageResource(R.drawable.sound_off_mode);

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


                String condi = tvSetCount.getText().toString();

                if (condi.equals("৯৯")){


                    btn33.setImageResource(R.drawable.button33);


                    tvSetCount.setText("৩৩");

                    if (countset>33){
                        if (currentcounter>33){
                            currentcounter = currentcounter%33;

                            String CC = String.valueOf(currentcounter);

                            CC = converterClass.covertS(CC);



                            tvCurrentCounter.setText(""+CC);

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

                else if (condi.equals("৩৩")){



                    btn33.setImageResource(R.drawable.button99);


                    tvSetCount.setText("৯৯");

                    countset = 99;
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("CountSet", Integer.parseInt(""+ countset));
                    editor.commit();
                }







            }
        });



        mp = MediaPlayer.create(getActivity(), R.raw.metaltick);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(.1, 12);
                myAnim.setInterpolator(interpolator);

                button.startAnimation(myAnim);


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


                currentcounter = currentcounter +1;
                totalcount = totalcount +1;




                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("Count", Integer.parseInt(""+ currentcounter));
                editor.putInt("TotalCount", Integer.parseInt(""+ totalcount));
                editor.commit();

            String CC = String.valueOf(currentcounter);

                CC = converterClass.covertS(CC);


                tvCurrentCounter.setText(""+CC);

                //tvCurrentCounter.setText(""+ currentcounter);

                String tC = String.valueOf(totalcount);

                tC = converterClass.covertS(tC);



                tvTotalcount.setText(""+tC);


                startAnimation();



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



                String CC = String.valueOf(currentcounter);
                CC = converterClass.covertS(CC);



                tvCurrentCounter.setText(""+CC);


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

                String CC = String.valueOf(currentcounter);
                CC = converterClass.covertS(CC);



                tvCurrentCounter.setText(""+CC);
                tvTotalcount.setText(""+CC);


            }
        });






        Log.d("countset", "onClick: "+countset);


        return view;
    }

    private void thread() {


    }


    private void startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.anim);
        Animation animation1 = AnimationUtils.loadAnimation(getActivity(),R.anim.animone);
        Animation animations = AnimationUtils.loadAnimation(getActivity(),R.anim.anims);
        Animation animation2 = AnimationUtils.loadAnimation(getActivity(),R.anim.animtow);

        img_pearl.startAnimation(animation);
        img_pearl_1.startAnimation(animations);
        img_pearl_2.startAnimation(animation1);
        img_pearl_3.startAnimation(animation1);
        img_pearl_4.startAnimation(animation2);
        img_pearl_6.startAnimation(animation1);
        img_pearl_7.startAnimation(animation1);
        img_pearl_8.startAnimation(animation1);
        img_pearl_9.startAnimation(animation1);

    }


    class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }

}
