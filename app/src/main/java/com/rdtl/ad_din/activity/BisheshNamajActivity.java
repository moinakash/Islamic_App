package com.rdtl.ad_din.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.graphics.Color;

import android.os.Bundle;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ScrollView;


import com.rdtl.ad_din.Adapters.kalimaExpandableAdapter;
import com.rdtl.ad_din.DatabaseHelper;
import com.rdtl.ad_din.pojo_classes.PojoClassForKalima;
import com.rdtl.ad_din.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BisheshNamajActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListNombres;
    private HashMap<String, PojoClassForKalima> listaContactos;
    private int lastExpandedPosition = -1;

    private Toolbar mToolbar;

    String ff;


    ScrollView scrollView1;
    DatabaseHelper db;
    String str, kaja1;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bishesh_namaj);


        webView = findViewById(R.id.idWebView);

        Intent intent = getIntent();
        ff = intent.getExtras().getString("shoriot");



        kaja1 = getResources().getString(R.string.kaja1);



        ToolBar();
        init();




        if (ff.equals("1") || ff.equals("6") || ff.equals("4")){

            webView.setVisibility(View.GONE);

            expandableListView.setAdapter(expandableListAdapter);

            expandableListView.setOnGroupExpandListener( new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                        expandableListView.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;
                }
            });
        }else if (ff.equals("0")){

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/namajerniom.html");
            webView.setBackgroundColor(Color.TRANSPARENT);
        }
        else if (ff.equals("2")){


        }
        else if (ff.equals("3")){

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/forojosunnot.html");
            webView.setBackgroundColor(Color.TRANSPARENT);


        }
        else if (ff.equals("5")) {


            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/taharathtml.html");
            webView.setBackgroundColor(Color.TRANSPARENT);

        }

    }

    private void init() {
        this.expandableListView = findViewById(R.id.expandableListView);
        this.listaContactos = getContactos();
        this.expandableListNombres = new ArrayList<>(listaContactos.keySet());
        this.expandableListAdapter = new kalimaExpandableAdapter(this,
                expandableListNombres, listaContactos);

    }


    private HashMap<String, PojoClassForKalima> getContactos() {
        HashMap<String, PojoClassForKalima> listaC = new HashMap<>();

        if (ff.equals("1")){

            listaC.put("১", new PojoClassForKalima("ক্বাযা",
                    ""+getResources().getString(R.string.kaja1),
                    ""+getResources().getString(R.string.kaja2),
                    ""+getResources().getString(R.string.kaja3)));

            listaC.put("২", new PojoClassForKalima("কছর",
                    ""+getResources().getString(R.string.kosor1),
                    ""+getResources().getString(R.string.kosor2),
                    ""+getResources().getString(R.string.kosor3)));

            listaC.put("৩", new PojoClassForKalima("বিতর",
                    ""+getResources().getString(R.string.bitor1),
                    ""+getResources().getString(R.string.bitor2),
                    ""+getResources().getString(R.string.bitor3)));

            listaC.put("৪", new PojoClassForKalima("জুমআ",
                    ""+getResources().getString(R.string.juma1),
                    ""+getResources().getString(R.string.juma2),
                    ""+getResources().getString(R.string.juma3)));

            listaC.put("৫", new PojoClassForKalima("ঈদের নামাজ",
                    ""+getResources().getString(R.string.eid1),
                    ""+getResources().getString(R.string.eid2),
                    ""+getResources().getString(R.string.eid3)));


            listaC.put("৬", new PojoClassForKalima("তারাবীহ",

                    ""+getResources().getString(R.string.tarabi1),
                    ""+getResources().getString(R.string.tarabi2),
                    ""+getResources().getString(R.string.tarabi3)));

            listaC.put("৭", new PojoClassForKalima("তাহাজ্জুদ",

                    ""+getResources().getString(R.string.tahajjud1),
                    ""+getResources().getString(R.string.tahajjud2),
                    ""+getResources().getString(R.string.tahajjud3)));

            listaC.put("৮", new PojoClassForKalima("সালাতুল তাসবীহ",
                    ""+getResources().getString(R.string.stasbih1),
                    ""+getResources().getString(R.string.stasbih2),
                    ""+getResources().getString(R.string.stasbih3)
                    ));
        }

        else if (ff.equals("6")){

            listaC.put("1", new PojoClassForKalima("ফরজ",

                    ""+getResources().getString(R.string.foroj1),
                    ""+getResources().getString(R.string.foroj2),
                    ""+getResources().getString(R.string.foroj3)));
            listaC.put("2", new PojoClassForKalima("ওয়াজিব",

                    ""+getResources().getString(R.string.wajib1),
                    ""+getResources().getString(R.string.wajib2),
                    ""+getResources().getString(R.string.wajib3)));
            listaC.put("3", new PojoClassForKalima("সুন্নত",

                   ""+getResources().getString(R.string.sunnot1),
                    ""+getResources().getString(R.string.sunnot2),
                    ""+getResources().getString(R.string.sunnot3)));
            listaC.put("4", new PojoClassForKalima("নফল ও মুস্তাহাব",

                    ""+getResources().getString(R.string.nofol1),
                    ""+getResources().getString(R.string.nofol2),
                    ""+getResources().getString(R.string.nofol3)));
            listaC.put("5", new PojoClassForKalima("মুবাহ",

                    ""+getResources().getString(R.string.mubah1),
                    ""+getResources().getString(R.string.mubah2),
                    ""+getResources().getString(R.string.mubah3)));
            listaC.put("6", new PojoClassForKalima("হারাম",

                    ""+getResources().getString(R.string.haram1),
                    ""+getResources().getString(R.string.haram2),
                     ""+getResources().getString(R.string.haram3)));
            listaC.put("7", new PojoClassForKalima("মাকরূহ",

                    ""+getResources().getString(R.string.makruh1),
                    ""+getResources().getString(R.string.makruh2),
                    ""+getResources().getString(R.string.makruh3)));

            listaC.put("8", new PojoClassForKalima("বিদআ’ত",

                    ""+getResources().getString(R.string.bidat1),
                    ""+getResources().getString(R.string.bidat2),
                    ""+getResources().getString(R.string.bidat3)));
            listaC.put("9", new PojoClassForKalima("সিজদাহে সাহু",

                    ""+getResources().getString(R.string.sijdashahu1),
                    ""+getResources().getString(R.string.sijdashahu2),
                    ""+getResources().getString(R.string.sijdashahu3)));
        }
        else if (ff.equals("4")){
            listaC.put("1", new PojoClassForKalima("সানা",

                    ""+getResources().getString(R.string.sana1),
                    ""+getResources().getString(R.string.sana2),
                    ""+getResources().getString(R.string.sana3)));
            listaC.put("2", new PojoClassForKalima("তাশাহহুদ",

                    ""+getResources().getString(R.string.tasahud1),
                    ""+getResources().getString(R.string.tasahud2),
                    ""+getResources().getString(R.string.tasahud3)));
            listaC.put("3", new PojoClassForKalima("দুরুদে ইব্রাহীম",

                    ""+getResources().getString(R.string.durudibrahim1),
                    ""+getResources().getString(R.string.durudibrahim2),
                    ""+getResources().getString(R.string.durudibrahim3)));
            listaC.put("4", new PojoClassForKalima("দুয়ায়ে মাছুরা",

                    ""+getResources().getString(R.string.doyaemasura1),
                    ""+getResources().getString(R.string.doyamasura2),
                    ""+getResources().getString(R.string.doyamasura3)));

            listaC.put("5", new PojoClassForKalima("দুয়ায়ে কুনুত ",

                    ""+getResources().getString(R.string.doyaekunut1),
                  ""+getResources().getString(R.string.doyaekunut2),
                    ""+getResources().getString(R.string.doyaekunut3)));

        }



        return listaC;
    }

    private void ToolBar() {

        mToolbar = findViewById( R.id.kalima_toolbar );

        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }


}
