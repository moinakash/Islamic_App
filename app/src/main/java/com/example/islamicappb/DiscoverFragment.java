package com.example.islamicappb;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DiscoverFragment extends Fragment implements SensorEventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

//    private ImageView MainImageDialer,MainImageQiblat;
//    private TextView Teks_bawah ,Teks_atas;

    ImageView ic_compus;
    private static SensorManager sensorManager;
    private static Sensor sensor;
    private float currentDegree;

    public DiscoverFragment() {

    }

    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
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

        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        ic_compus = view.findViewById(R.id.compus);
        sensorManager =(SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor =sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        if(sensor != null)
        {
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);

        }else
        {
            Toast.makeText(getContext(), "not Supported", Toast.LENGTH_SHORT).show();
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
}