package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseActivity extends AppCompatActivity {

    ChipNavigationBar BottomNav;
    FragmentManager fragmentManager;



//    final String myString = "hello";
     String myString2;

    String fajarTime, johorTime, asorTime, magribTime, eshaTime, sunriseTime, nextnamaj;
    String fajarTimeR, johorTimeR, asorTimeR, magribTimeR, eshaTimeR, sunriseTimeR, nextnamajR, BorPNamaj;

    int cTime, fTime, jTime, aTime, mTime, eTime, sTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        BottomNav = findViewById(R.id.bottom_nav);

        Fragment fragment = new TimeTableFragment();

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
        sTime= sTime/100;


////////////////////////////////////////////////////////////////////////////////////////////////////////

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ");


        String dateTime = simpleDateFormat.format(calendar.getTime());


        dateTime = dateTime.replace(" ", "");


        cTime = Integer.parseInt(dateTime);





        if (cTime>fTime && cTime<sTime){
            BorPNamaj = "বর্তমান নামাজ";
            nextnamaj = "ফজর";
        }

        if (cTime>sTime && cTime<jTime){

            BorPNamaj = "বর্তমান নামাজ";
            nextnamaj = "যোহর";
        }
        if (cTime>=jTime && cTime<(aTime-30)){
            BorPNamaj = "বর্তমান নামাজ";
            nextnamaj = "যোহর";
        }
        if (cTime>=(aTime-30) && cTime<aTime){
            BorPNamaj = "পরবর্তি নামাজ";
            nextnamaj = "আসর";
        }
        if (cTime>=aTime && cTime<(mTime-30)){
            BorPNamaj = "বর্তমান নামাজ";
            nextnamaj = "আসর";
        }
        if (cTime>=(mTime-20) && cTime<mTime){
            BorPNamaj = "পরবর্তি নামাজ";
            nextnamaj = "মাগরিব";
        }

        if (cTime>=mTime && cTime<(mTime+20)){
            BorPNamaj = "বর্তমান নামাজ";
            nextnamaj = "মাগরিব";
        }
        if (cTime>=(mTime+20) && cTime<eTime){
             BorPNamaj = "পরবর্তি নামাজ";
             nextnamaj = "এশা";
        }
        if (cTime>=eTime && cTime<2345){
            BorPNamaj = "বর্তমান নামাজ";
            nextnamaj = "এশা";
        }
        if (cTime>=2345 && cTime<2359){

            BorPNamaj = "পরবর্তি নামাজ";
            nextnamaj = "ফজর";
        }
        if (cTime>=0 && cTime<fTime){

            BorPNamaj = "পরবর্তি নামাজ";
            nextnamaj = "ফজর";
        }




        BottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                Fragment fragment = null;

                switch (id){
                    case  R.id.home:
                        fragment = new TimeTableFragment();
                        break;

                    case  R.id.discover:
                        fragment = new DiscoverFragment();
                        break;

                    case  R.id.account:
                        fragment = new QuranMajidFragment();
                        break;
                    case  R.id.account2:
                        fragment = new DiscoverFragment();
                        break;
                    case  R.id.account3:
                        fragment = new TimeTableFragment();
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
    public String bOrPNamajTime() {
        return BorPNamaj;
    }




}
