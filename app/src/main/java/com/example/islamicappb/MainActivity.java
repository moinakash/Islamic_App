package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar BottomNav;
    FragmentManager fragmentManager;



//    final String myString = "hello";
     String myString2;

    String fajarTime, johorTime, asorTime, magribTime, eshaTime, sunriseTime, nextnamaj;
    String fajarTimeR, johorTimeR, asorTimeR, magribTimeR, eshaTimeR, sunriseTimeR, nextnamajR;

    int cTime, fTime, jTime, aTime, mTime, eTime, sTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNav = findViewById(R.id.bottom_nav);

        Fragment fragment = new HomeFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();



        myString2 = "hello2";

        fajarTime = getIntent().getExtras().getString("fajor");
        johorTime = getIntent().getExtras().getString("johor");
        asorTime = getIntent().getExtras().getString("asor");
        magribTime = getIntent().getExtras().getString("magrib");
        eshaTime = getIntent().getExtras().getString("esha");
        sunriseTime = getIntent().getExtras().getString("sunrise");


        fajarTimeR = fajarTime.replace(":", "");
        fTime = Integer.parseInt(fajarTimeR);
        fTime= fTime/100;


        johorTimeR = johorTime.replace(":", "");
        jTime = Integer.parseInt(johorTimeR);
        jTime= jTime/100;


        asorTimeR = asorTime.replace(":", "");
        aTime = Integer.parseInt(asorTimeR);
        aTime= aTime/100;


        magribTimeR = magribTime.replace(":", "");
        mTime = Integer.parseInt(magribTimeR);
        mTime= mTime/100;


        eshaTimeR = eshaTime.replace(":", "");
        eTime = Integer.parseInt(eshaTimeR);
        eTime= eTime/100;

        sunriseTimeR = sunriseTime.replace(":", "");
        sTime = Integer.parseInt(sunriseTimeR);
        sTime= eTime/100;

////////////////////////////////////////////////////////////////////////////////////////////////////////

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ");


        String dateTime = simpleDateFormat.format(calendar.getTime());


        dateTime = dateTime.replace(" ", "");


        cTime = Integer.parseInt(dateTime);




        if (cTime>fTime && cTime<sTime){
            nextnamaj = "Fojor namaj";
        }
        if (cTime>sTime && cTime<jTime){

            nextnamaj = "poroborti namaj johor";
        }
        if (cTime>=jTime && cTime<(aTime-30)){
            nextnamaj = "Johor namaj";
        }
        if (cTime>=(aTime-30) && cTime<aTime){
            nextnamaj = "poroborti namaj asor";
        }
        if (cTime>=aTime && cTime<(mTime-20)){
            nextnamaj = "Asor namaj";
        }
        if (cTime>=(mTime-20) && cTime<mTime){
            nextnamaj = "poroborti namaj Magrib";
        }

        if (cTime>=mTime && cTime<(mTime+30)){
            nextnamaj = "Magrib namaj";
        }
        if (cTime>=(mTime+30) && cTime<eTime){
             nextnamaj = "poroborti namaj Esha";
        }
        if (cTime>=eTime && cTime<2345){
            nextnamaj = "Esha namaj";
        }
        if (cTime>=2345 && cTime<2359){

            nextnamaj = "poroborti namaj fojor";
        }
        if (cTime>=0 && cTime<fTime){

            nextnamaj = "poroborti namaj fojor";
        }




        BottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                Fragment fragment = null;

                switch (id){
                    case  R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case  R.id.discover:
                        fragment = new DiscoverFragment();
                        break;

                    case  R.id.account:
                        fragment = new AccountFragment();
                        break;
                    case  R.id.account2:
                        fragment = new DiscoverFragment();
                        break;
                    case  R.id.account3:
                        fragment = new HomeFragment();
                        break;

                }

                if (fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                }else {
                    Log.e("hi","fffff");
                }
            }
        });



    }

    public String fojorData() {
        return fajarTime;
    }
    public String johorData() {
        return johorTime;
    }
    public String asorData() {
        return asorTime;
    }
    public String magribData() {
        return magribTime;
    }
    public String eshaData() {
        return eshaTime;
    }
    public String currentNamajTime() {
        return nextnamaj;
    }




}
