package com.example.islamicappb;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




public class TasbihFragment extends Fragment {





    ImageView btn33;
    Button button ;
    LinearLayout btnttlr,btnReset,btnSound;
    TextView tvCurrentCounter, tvSetCount, tvTotalcount;

    ImageView img_pearl, img_pearl_1, img_pearl_2, img_pearl_3, img_pearl_4, img_pearl_6, img_pearl_7, img_pearl_8, img_pearl_9;

    ImageView ibbtn33img;



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

            Log.d("countset", "onClick: "+countset);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasbih, container, false);




        button = view.findViewById(R.id.idButton);
        btnReset = view.findViewById(R.id.idReset);
        tvCurrentCounter = view.findViewById(R.id.idCurrentCount);
        btn33 = view.findViewById(R.id.id33);
        //btn99 = view.findViewById(R.id.id99);
        btnSound = (LinearLayout) view.findViewById(R.id.idSound);
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
//        ibbtn33img = view.findViewById(R.id.idbtn33img);




        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        currentcounter = sharedPref.getInt("Count",0);
        countset = sharedPref.getInt("CountSet",33);
        totalcount = sharedPref.getInt("TotalCount",0);
        SoundInt = sharedPref.getInt("Soundintt",0);






        /////////////////////////////////////////////////////////
        String CC = String.valueOf(currentcounter);

        CC= CC.replace("0", "০");
        CC= CC.replace("1", "১");
        CC= CC.replace("2", "২");
        CC= CC.replace("3", "৩");
        CC= CC.replace("4", "৪");
        CC= CC.replace("5", "৫");
        CC= CC.replace("6", "৬");
        CC= CC.replace("7", "৭");
        CC= CC.replace("8", "৮");
        CC= CC.replace("9", "৯");


        tvCurrentCounter.setText(""+CC);
        //tvCurrentCounter.setText(""+ currentcounter);
        /////////////////////////////////////////////////////////////////////

        String tC = String.valueOf(totalcount);

        tC= tC.replace("0", "০");
        tC= tC.replace("1", "১");
        tC= tC.replace("2", "২");
        tC= tC.replace("3", "৩");
        tC= tC.replace("4", "৪");
        tC= tC.replace("5", "৫");
        tC= tC.replace("6", "৬");
        tC= tC.replace("7", "৭");
        tC= tC.replace("8", "৮");
        tC= tC.replace("9", "৯");

        tvTotalcount.setText(""+tC);

        //tvTotalcount.setText(""+ totalcount);
        //
        // //////////////////////////////////////

        String cS = String.valueOf(countset);

        cS= cS.replace("0", "০");
        cS= cS.replace("1", "১");
        cS= cS.replace("2", "২");
        cS= cS.replace("3", "৩");
        cS= cS.replace("4", "৪");
        cS= cS.replace("5", "৫");
        cS= cS.replace("6", "৬");
        cS= cS.replace("7", "৭");
        cS= cS.replace("8", "৮");
        cS= cS.replace("9", "৯");


        tvSetCount.setText(""+cS);



        //todo
        //btn33.setText(""+cS);

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SoundInt == 0){
                    SoundInt = 1;


                 //   btnSound.setText("Sound Mode");

                }else if (SoundInt == 1){
                    SoundInt = 2;
                   // btnSound.setText("Vibrator Mode");
                }else if (SoundInt == 2){
                    SoundInt = 0;
                 //   btnSound.setText("sound off mode");
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

                            CC= CC.replace("0", "০");
                            CC= CC.replace("1", "১");
                            CC= CC.replace("2", "২");
                            CC= CC.replace("3", "৩");
                            CC= CC.replace("4", "৪");
                            CC= CC.replace("5", "৫");
                            CC= CC.replace("6", "৬");
                            CC= CC.replace("7", "৭");
                            CC= CC.replace("8", "৮");
                            CC= CC.replace("9", "৯");


                            tvCurrentCounter.setText(""+CC);

                        }

                    }

                   // btn33.setText("৩৩");


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
                   // btn33.setText("৯৯");
                    countset = 99;
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("CountSet", Integer.parseInt(""+ countset));
                    editor.commit();
                }







            }
        });

//        btn99.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//
//            }
//        });


        mp = MediaPlayer.create(getActivity(), R.raw.metaltick);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
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
                Log.d("countset", "onClick: "+countset);

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

                String CC = String.valueOf(currentcounter);

                CC= CC.replace("0", "০");
                CC= CC.replace("1", "১");
                CC= CC.replace("2", "২");
                CC= CC.replace("3", "৩");
                CC= CC.replace("4", "৪");
                CC= CC.replace("5", "৫");
                CC= CC.replace("6", "৬");
                CC= CC.replace("7", "৭");
                CC= CC.replace("8", "৮");
                CC= CC.replace("9", "৯");

                tvCurrentCounter.setText(""+CC);

                //tvCurrentCounter.setText(""+ currentcounter);

                String tC = String.valueOf(totalcount);

                tC= tC.replace("0", "০");
                tC= tC.replace("1", "১");
                tC= tC.replace("2", "২");
                tC= tC.replace("3", "৩");
                tC= tC.replace("4", "৪");
                tC= tC.replace("5", "৫");
                tC= tC.replace("6", "৬");
                tC= tC.replace("7", "৭");
                tC= tC.replace("8", "৮");
                tC= tC.replace("9", "৯");

                tvTotalcount.setText(""+tC);


                startAnimation();

              //  thread();

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

                String CC = String.valueOf(currentcounter);

                CC= CC.replace("0", "০");
                CC= CC.replace("1", "১");
                CC= CC.replace("2", "২");
                CC= CC.replace("3", "৩");
                CC= CC.replace("4", "৪");
                CC= CC.replace("5", "৫");
                CC= CC.replace("6", "৬");
                CC= CC.replace("7", "৭");
                CC= CC.replace("8", "৮");
                CC= CC.replace("9", "৯");

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

                CC= CC.replace("0", "০");
                CC= CC.replace("1", "১");
                CC= CC.replace("2", "২");
                CC= CC.replace("3", "৩");
                CC= CC.replace("4", "৪");
                CC= CC.replace("5", "৫");
                CC= CC.replace("6", "৬");
                CC= CC.replace("7", "৭");
                CC= CC.replace("8", "৮");
                CC= CC.replace("9", "৯");

                tvCurrentCounter.setText(""+CC);
                tvTotalcount.setText(""+CC);


            }
        });






        Log.d("countset", "onClick: "+countset);


        return view;
    }

    private void thread() {

//        imageView.setVisibility(View.GONE);
//
//        gifImageView.setVisibility(View.VISIBLE);
//
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                imageView.setVisibility(View.VISIBLE);
//
//                gifImageView.setVisibility(View.GONE);
//
//
//            }
//        }, 200);

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
