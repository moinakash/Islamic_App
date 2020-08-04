package com.example.islamicappb;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CompusFragment extends Fragment implements SensorEventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

//    private ImageView MainImageDialer,MainImageQiblat;
//    private TextView Teks_bawah ,Teks_atas;

    ImageView ic_compus,imageView;
    private static SensorManager sensorManager;
    private static Sensor sensor;
    private float currentDegree;

    public CompusFragment() {

    }

    public static CompusFragment newInstance(String param1, String param2) {
        CompusFragment fragment = new CompusFragment();
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

        View view = inflater.inflate(R.layout.fragment_compus, container, false);

        imageView = view.findViewById(R.id.img);
        ic_compus = view.findViewById(R.id.compus);
        sensorManager =(SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor =sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        thread();
        if(sensor != null)
        {
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);

        }else
        {
            Toast.makeText(getContext(), "দুঃখিত, আপনার মোবাইলে কম্পাস সেন্সর নেই।", Toast.LENGTH_SHORT).show();

            showDialog();
        }




        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int degree = Math.round(event.values[0]);
        RotateAnimation animation = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        ic_compus.setAnimation(animation);
        currentDegree= -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void thread() {



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.GONE);
                ic_compus.setVisibility(View.VISIBLE);


            }
        }, 100);

    }


    void showDialog() {



/*
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.rojar_somoy_suchi_alert, null);*/

        final Dialog alertDialog = new Dialog(getContext());

        alertDialog.setContentView(R.layout.rojar_somoy_suchi_alert);

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView image = alertDialog.findViewById(R.id.img);
        image.setVisibility(View.GONE);

        TextView Saharir_shesh_somoy = alertDialog.findViewById(R.id.sahari_sesh_somoy);
        TextView Iftarir_somoy = alertDialog.findViewById(R.id.iftarir_somoy);
        TextView Sahari = alertDialog.findViewById(R.id.sahari);
        TextView Iftari = alertDialog.findViewById(R.id.iftari);
        TextView Exit = alertDialog.findViewById(R.id.exit);



        Saharir_shesh_somoy.setText("দুঃখিত, আপনার মোবাইলে কম্পাস সেন্সর নেই।");
        Iftarir_somoy.setVisibility(View.GONE);
        Sahari.setVisibility(View.GONE);
        Iftari.setVisibility(View.GONE);
        Exit.setVisibility(View.GONE);


        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });


        alertDialog.show();



    }

}