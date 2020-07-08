package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar BottomNav;
    FragmentManager fragmentManager;



    final String myString = "hello";
     String myString2;

    String fajarTime, johorTime, asorTime, magribTime, eshaTime, sunriseTime;

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


}
