package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;

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

    TextView tvDetailss;
    ScrollView scrollView1;
    DatabaseHelper db;
    String str, Test1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bishesh_namaj);

        tvDetailss = findViewById(R.id.idDetailsTv);
        scrollView1 = findViewById(R.id.idScrollView1);

        Intent intent = getIntent();
        ff = intent.getExtras().getString("shoriot");

        Log.e("tag",""+ff);



        ToolBar();
        init();




        if (ff.equals("1") || ff.equals("6") || ff.equals("4")){

            scrollView1.setVisibility(View.GONE);

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
            db = new DatabaseHelper(this);
            Cursor cursor = db.namajerNiom();

            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("niom"));
            }
            tvDetailss.setText(""+str);
        }
        else if (ff.equals("2")){

            tvDetailss.setText("মুনাজাতের নিয়ম\n" +
                    "১, দুয়া করার সময় দুই হাতের মাঝে সামান্য ফাঁক রাখতে হবে। আঙুলগুলোর মাঝে স্বাভাবিক পরিমাণে ফাঁক থাকবে।\n" +
                    "২, দুয়া করার সময় হাত বুক পর্যন্ত তুলতে হবে এবং হাতের তালু চেহারার দিকে থাকবে। দৃষ্টি দুই হাতের মধ্যবর্তী স্থানের দিকে থাকবে।\n" +
                    "৩, হাত মুখের কাছে নেওয়া যাবে না। হাতের তালু খোলা রাখতে হবে, গুঠানো যাবে না।");
        }
        else if (ff.equals("3")){

            db = new DatabaseHelper(this);
            Cursor cursor = db.forojosunnot();

            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("details"));
            }
            tvDetailss.setText("" + str);


        }
        else if (ff.equals("5")) {

            db = new DatabaseHelper(this);
            Cursor cursor = db.taharat();

            if (cursor.moveToFirst()) {
                str = cursor.getString(cursor.getColumnIndex("description"));
            }
            tvDetailss.setText("" + str);


        }




    }

    private void init() {
        this.expandableListView = findViewById(R.id.expandableListView);
        this.listaContactos = getContactos();
        this.expandableListNombres = new ArrayList<>(listaContactos.keySet());
        this.expandableListAdapter = new CustomExpandableListAdapterforKalima(this,
                expandableListNombres, listaContactos);

    }


    private HashMap<String, PojoClassForKalima> getContactos() {
        HashMap<String, PojoClassForKalima> listaC = new HashMap<>();

        if (ff.equals("1")){

            listaC.put("১", new PojoClassForKalima("ক্বাযা",
                    "কোনো ওয়াক্তের ফরজ বা ওয়াজিব নামাজ অন্য নামাজের ওয়াক্তে পড়াকেই ক্বাযা নামাজ বলে।\n" +
                            "\n" +
                            "কোনো ব্যক্তি কোনো ওয়াক্তের ফরজ বা ওয়াজিব নামাজ ছেড়ে দিলে তাকে ইহকালে ও পরকালে ভয়ংকর শাস্তি পেতে হবে। তাই কোনো ওয়াক্তের নামাজ ক্বাযা হয়ে গেলে যত তাড়াতাড়ি সম্ভব তত তাড়াতাড়ি ক্বাযা নামাজ আদায় করে নেওয়া উচিত। নিচে ক্বাযা নামাজে আদায় করার নির্দেশনাগুলো দেওয়া হল।\n" +
                            "\n" +
                            "১, সুন্নতে মুয়াক্কাদা এবং নফল নামাজ ক্বাযা পড়া যাবে না। তবে ফজরের নামাজ ক্বাযা হলে এর সুন্নত সহ ক্বাযা পড়া উত্তম।\n" +
                            "\n" +
                            "২, ঘুমের কারণে ক্বাযা হলে ঘুম থেকে উঠেই ক্বাযা পড়া উত্তম (তবে নিষিদ্ধ সময়গুলো বাদ দিয়ে)।\n" +
                            "\n" +
                            "৩, জুমুআ’র নামাজ ক্বাযা হলে দুই রাকআত ওয়াজিবের পরিবর্তে যোহরের নামাজের ৪ রাকআত ফরজ নামাজ পড়তে হবে। জুমুআ’র ওয়াক্তের সময় নামাজ বাসায় পড়লেও যোহরের নামাজের ৪ রাকআত ফরজ নামাজ পড়তে হবে।\n" +
                            "\n" +
                            "৪, ভ্রমণের সময়ের কোনো ওয়াক্তের নামাজ ক্বাযা হলে গাড়ি থেকে নেমে অথবা বাসায় ফিরে সেই নামাজ পড়ে নিতে হবে।",
                    "৫, যদি দীর্ঘকাল (কয়েক মাস বা বছর) নামাজ ক্বাযা করলে দিন অনুমান করে, বর্তমান ওয়াক্তের সাথে মিল রেখে ধারাবাহিকভাবে ঐ অতীতের ওয়াক্তের নামাজের জন্য নিয়ত করে ক্বাযা পড়তে হবে।",
                    "৬, একাধিক ওয়াক্তের ক্বাযা পড়ার সহজ পদ্ধতি হলো প্রত্যেক ওয়াক্তের ফরজ নামাজের পরে আগের যেকোনো দিনের ক্বাযা আদায় করে নেয়া। কোনো ওয়াক্তের নামাজ ক্বাযা হলে পরের ওয়াক্তের ফরজ নামাজের পরেও নামাজ পড়ে নেওয়া যাবে।"
                    ));

            listaC.put("২", new PojoClassForKalima("কছর",
                    "দূরের পথ ভ্রমণ করার সময় বা দূরে কোথাও বেড়াতে বা কাজে গেলে ফরজ ৪ রাকআত নামাজের পরিবর্তে ২ রাকআত নামাজ পড়তে হয় এবং সুন্নতে মুয়াক্কাদা নামাজ মুস্তাহাব হয়ে যায়। নামাজের এই বিধানকে কছর এবং ওই ভ্রমণরত ব্যক্তিকে মুসাফির বলা হয়।\n" +
                            "\n" +
                            "কতদূর গেলে কেউ মুসাফির হবে সে বিষয়ে হাদিস রয়েছে। হাদিসটি নিচে দেওয়া হল।\n" +
                            "\n" +
                            "ইমাম বুখারি রহ. বলেন, “ইবনে উমর (রাঃ) এবং ইবনে আব্বাস (রাঃ) ৪ বারীদ সফরের সময় কছর পড়া এবং রোজা ভাঙ্গার কথা বলেছেন। আর সেটি হল, ১৬ ফরসখ” (সহিহ বুখারি, নামাজ কছর করা অধ্যায়)।\n" +
                            "\n" +
                            "১ বারীদ ১২ মাইল হয়ে থাকে আর ১ ফরসখ ৩ মাইল হয়ে থাকে। সুতরাং ৪ (বারীদ) কে ১২ দিয়ে গুণ দিলে কিংবা ১৬ (ফরসখ) কে ৩ দিয়ে গুণ দিলে পাওয়া যায় ৪৮ মাইল (প্রায় ৭৭.২৫ কিলোমিটার)। নিচে কছর নামাজ আদায় করার নির্দেশনাগুলো দেওয়া হল।\n" +
                            "\n" +
                            "১, দুই অথবা তিন রাকআত বিশিষ্ট ফরজ ও ওয়াজিব নামাজের জন্য কছর নেই, সুতরাং ফযর, মাগরিব ও বিতরের নামাজ যথাক্রমে দুই, তিন, ও তিন রাকআতই পড়তে হবে।\n" +
                            "\n" +
                            "২, যোহর, আছর ও এশার নামাজ কছরের জন্য কেবল দুই রাকআত পড়তে হবে। চার রাকআত পড়লে গুনাহ হবে এবং নামাজ শুদ্ধ হবে না, আবার আদায় করতে হবে। তবে যদি কেউ ভুলে চার রাকআত পড়ে ফেলে কিন্তু শেষ বৈঠকের আগে ভুল বুঝতে পেরে সিজদাহে সাহু করে নেয়, তাহলে নামাজ শুদ্ধ হবে।\n" +
                            "\n" +
                            "৩, ভ্রমণ অবস্তায় সুন্নতে মুয়াক্কাদা নামাজ নফলে পরিণত হয়, গন্তব্যস্থলে পৌঁছানোর আগ পর্যন্ত না পড়ায় ভাল। তবে ফযরের ফরজ নামাজের আগের দুই রাকআত সুন্নত নামাজ পড়াই উত্তম, কারণ এই দুই রাকআত সুন্নতে মুয়াক্কাদা ওয়াজিবের মত গুরুত্বপূর্ণ। গন্তব্যস্থলে পৌঁছানোর পরে ব্যস্ততা না থাকলে অন্য ওয়াক্তের সুন্নতে মুয়াক্কাদা নামাজও পড়া যাবে।\n" +
                            "\n" +
                            "৪, কেউ যদি ১৫ বা তার বেশিদিনের জন্য দূরে কোথাও বেড়াতে বা কাজে যায় তাহলে সে আর মুসাফির থাকবে না। তাকে সব নামাজ পূর্ণরূপে পড়তে হবে। তবে নিয়ত বেশিদিনের হলে কছর পড়া যাবে না আর নিয়ত কমদিনের হলে কছর পড়তে হবে।",
                    "৫, মসজিদে জামাআতের সাথে নামাজ পড়লে মুসাফিরকে ফরজ নামাজ পূর্ণরূপেই পড়তে হবে (ইমামকে অনুসরণ করা ফরজ)। অনুরূপভাবে, নিজের শ্বশুরবাড়ি বা নিজের অন্য কোনো বাড়ি যদি ৭৭.২৫ কিলোমিটার বা তার বেশি দূরেও অবস্থিত হয় তবুও সব নামাজ পূর্ণরূপে পড়তে হবে।",
                    "৬, মুকিম (যে মুসাফির না), অবস্তার ক্বাযা নামাজ মুসাফির অবস্তায় পড়লে পূর্ণরূপেই পড়তে হবে এবং মুসাফির অবস্তার ক্বাযা নামাজ মুকিম অবস্তায় পড়লে কছরই পড়তে হবে।"
                    ));

            listaC.put("৩", new PojoClassForKalima("বিতর",
                    "এশার নামাজের পরে তিন রাকাআত ওয়াজিব নামাজ পড়তে হয়, একেই বিতরের নামাজ বলা হয়। \n\n বিতর নামাজ পড়া ওয়াজিব। রমজান মাস ব্যতীত অন্য সকল মাসে বিতরের নামাজ নিজে নিজে পড়তে হয়। রমজান মাসে তারাবিহ নামাজ পড়ার পর ইমামের সঙ্গে জামাআতে বিতর নামাজ পড়া যায়। নিচে বিতরের নামাজ আদায় করার নির্দেশনাগুলো দেওয়া হল।\n" +
                            "\n" +
                            "১, বিতরের নামাজের প্রথম দুই রাকাআত ফরজ নামাজের মত আদায় করতে হয়।\n\n" +
                            "২, তৃতীয় রাকাআতে কিরআত (সূরা ফাতিহা ও অন্য একটি সূরা পড়া) করার পরে নিঃশব্দে দুয়ায়ে কুনুত পড়তে হয়।",
                    "৩, দুয়ায়ে কুনুত পড়ার পরে ফরজ নামাজের শেষ রাকাআতের মত রুকু, সিজদাহ, শেষ বৈঠকে তাশাহহুদ, দুরুদে ইব্রাহীম ও দুয়ায়ে মাছুরা পড়ে সালাম ফিরানোর মাধ্যমে বিতরের নামাজ শেষ করতে হয়।",

                    "৪, তৃতীয় রাকাআতে দুয়ায়ে কুনুত পড়তে ভুলে গেলে অথবা প্রথম বা দ্বিতীয় রাকাআতে দুয়ায়ে কুনুত পড়ে ফেললে নামাজের শেষ বৈঠকে তাশাহহুদের পরে সিজদাহে সাহু করতে হবে।\n"
                    ));

            listaC.put("৪", new PojoClassForKalima("জুমআ",
                    "প্রতি শুক্রবার যোহরের নামাজের ওয়াক্তে যোহরের নামাজের পরিবর্তে জুমুআর নামাজ পড়তে হয়।",
                    " জুমুআর নামাজ ফরজে আইন। জুমুআর নামাজ মোট বার রাকআত, খুতবার আগে চার রাকআত সুন্নতে মুয়াক্কাদা (কাবলাল জুমুআ), খুতবার পরে জামআতের সাথে দুই রাকআত ফরজ, আর ফরজ নামাজের পরে আরও চার রাকআত সুন্নতে মুয়াক্কাদা (বা’দাল জুমুআ) এবং সবশেষে দুই রাকআত সুন্নতে গায়েরে মুয়াক্কাদা পড়তে হয়। নিচে জুমুআর নামাজ আদায় করার নির্দেশনাগুলো দেওয়া হল।",
                           "জুমুআর দুই রাকআত ফরজ নামাজ সাধারণ ফরজ নামাজের মতই। মসজিদে খুতবা শুরুর আগেই গিয়ে কাবলাল জুমুআর জন্য নিয়ত করে চার রাকআত সুন্নতে মুয়াক্কাদা নামাজ একা পড়ে নিতে হবে। তারপরে দ্বিতীয় আজান দেওয়া হবে এবং আজান শেষে খতিব খুতবা শুরু করবে। খুতবা শোনা ওয়াজিব, এই সময়ে তাহিয়্যাতুল মসজিদ নামাজ ব্যতীত অন্য কোনো নামাজ না পড়াই উত্তম। খুতবার সময় কারো সাথে কথা বলা বা খুতবা শুনতে ব্যঘাত ঘটে এমন কোনো কাজ করা হারাম। খুতবা শেষ হলে ফরজ নামাজের জন্য জামআতে দাঁড়াতে হবে এবং ইমামের সাথে পড়তে হবে। ফরজ নামাজ শেষ হলে মুনাজাত হবে। মুনাজাতের পরে চার রাকআত সুন্নতে মুয়াক্কাদা ও দুই রাকআত সুন্নতে গায়েরে মুয়াক্কাদা একা পড়ে নিতে হবে।\n" ));
            listaC.put("৫", new PojoClassForKalima("ঈদের নামাজ",
                    "প্রতি বছর উভয় ঈদের দিন সকালে ঈদগাহে অথবা মসজিদে জামআতের সাথে দুই রাকআত নামাজ পড়তে হয়।\n" +
                            "\n" +
                            "ঈদুল ফিতর ও ঈদুল আযহা এই দুই ঈদের নামাজের নিয়ম একই। কেবল নিয়ত ভিন্ন হবে। উভয় ঈদের নামাজই ওয়াজিব। ঈদের নামাজ নিচে ঈদের নামাজ আদায় করার নির্দেশনাগুলো দেওয়া হল।\n" +
                            "\n" +
                            "১, ঈদের নামাজের জন্য আজান এবং ইকামত দিতে হয়না।\n" +
                            "\n" +
                            "২, “আমি ইমামের সাথে ঈদের দুই রাকআত ওয়াজিব নামাজ পড়ছি” এই কথা খেয়াল রেখে নামাজের নিয়ত করতে হবে।\n" +
                            "\n" +
                            "৩, সাধারণ দুই রাকআত নামাজে যে কয়বার তাকবীরে তাহরীমা পড়তে হয় তার থেকে ছয়বার অতিরিক্ত তাকবীরে তাহরীমা পড়তে হবে।\n" +
                            "\n" +
                            "৪, প্রথম রাকআতে তাকবীরে তাহরীমা, হাত বাঁধা ও সানা পড়ার পরে হাত ছেড়ে দিয়ে তিনবার অতিরিক্ত তাকবীরে তাহরীমা পড়তে হবে। প্রথম দুইবার হাত কান পর্যন্ত তুলে হাত ছেড়ে দিতে হবে এবং তৃতীয়বার হাত বাঁধতে হবে। \n" +
                            "\n" +
                            "৫, দ্বিতীয় রাকআতের কিরাআতের পরে ও রুকুর আগে বাকি তিনবার অতিরিক্ত তাকবীরে তাহরীমা পড়তে হবে। তিনবারই হাত কান পর্যন্ত তুলে হাত ছেড়ে দিতে হবে এবং সাধারণ নিয়মের তাকবীরে তাহরীমাটি পড়ে রুকুতে যেতে হবে।\n" +
                            "\n" +
                            "৬, ইমাম দ্বিতীয় রাকআতের রুকুর আগে অতিরিক্ত তাকবীরে তাহরীমা পড়তে ভুলে গেলে অথবা মুক্তাদি দ্বিতীয় রাকআতের অতিরিক্ত তাকবীরে তাহরীমার পরে নামাজে অংশ নিলে রুকুতে থাকা অবস্তায় নিঃশব্দে অতিরিক্ত তাকবীরে তাহরীমা পড়ে নিবে। রুকু ভেঙ্গে আবার দাঁড়িয়ে অতিরিক্ত তাকবীরে তাহরীমা পড়া যাবে না। কিন্তু রুকুর পরে দাঁড়িয়ে অতিরিক্ত তাকবীরে তাহরীমা পড়লেও চলবে, সিজদাহে সাহু করতে হবে না।\n" +
                            "\n" +
                            "৭, নামাজের পর ইমাম মুক্তাদিদের দিকে মুখ করে খুতবা পড়বেন। ঈদের নামাজের পড়ে খুতবা পড়া সুন্নত আর শোনা ওয়াজিব।\n" +
                            "\n" +
                            "৮, ঈদের নামাজ জামআত ছাড়া একা শুরু করা যাবে না। মসজিদ বা ঈদগাহের নামাজ শেষ হয়ে গেলে যারা জামআতে অংশ নিতে পারেনি তারা একত্রে নতুন জামআত করতে পারবে। একই ইমাম বা মুক্তাদিরা একবারের বেশি ঈদের নামাজ পড়তে পারবে না।\n" +
                            "\n" +
                            "\n" +
                            "ঈদুল আযহার কিছু ওয়াজিব আছে। সেগুলো নিচে দেওয়া হল।\n" +
                            "\n" +
                            "১, ৯ ই জিলহজ্জ (হজ্জ শুরুর দিন) ফযর থেকে ১৩ ই জিলহজ্জ আছর পর্যন্ত\n" +
                            "মোট ২৩ ওয়াক্ত ফরজ নামাজ জামআতে পড়ার পরে তাকবীরে তাশরীক (আল্লাহু আকবর আল্লাহু আকবর লা-ইলাহা ইল্লাল্লাহু ওয়াল্লাহু আকবর আল্লাহু আকবর ওয়া লিল্লাহিল হামদ) পড়া ওয়াজিব।\n" +
                            "\n" +
                            "২, তাকবীরে তাশরীক উচ্চস্বরে বলা ওয়াজিব। মহিলারা নিঃশব্দে পড়বে।\n",
                    "৩, ফরজ নামাজের সালাম দেওয়া শেষ হলেই তাকবীরে তাশরীক পড়তে হবে।",
                    "৪, ইমাম তাকবীরে তাশরীক পড়ার কথা ভুলে গেলে মুক্তাদিরা উচ্চস্বরে তাকবীরে তাশরীক পড়া শুরু করবে। ইমামের অপেক্ষায় বসে থাকা যাবে না। ফরজ নামাজ একা শেষ করলেও তাকবীরে তাশরীক পড়তে হবে।\n"
                    ));

            listaC.put("৬", new PojoClassForKalima("তারাবীহ",

                    "রমজান মাসের প্রত্যেক রোজার আগের রাতে এশার ফরজ ও সুন্নত নামাজের পরে ২০ রাকআত করে নামাজ পড়তে হয়।\n" +
                            "\n" +
                            "তারাবীহর নামাজ সুন্নতে মুয়াক্কাদা। জামআত করে মসজিদে পড়া সুন্নতে মুয়াক্কাদা কেফায়া। তারাবীহর নামাজের জামআত মসজিদ ছাড়াও বাড়ী বা অন্য কোনো স্থানেও করা যাবে। তবে মসজিদের জামআতের ফজিলত বেশী। একা একা বাড়ীতেও পড়লেও হবে। কিন্তু যদি এলাকার কোনো মসজিদে তারাবীহর জামআতই না হয় তাহলে এলাকার সবাই গুনাহগার হবে। নিচে তারাবীহর নামাজ আদায় করার নির্দেশনাগুলো দেওয়া হল।\n" +
                            "\n" +
                            "১, তারাবীহর নামাজ রমজান মাসের চাঁদ দেখা গেলে শুরু হয় এবং শাওয়াল মাসের চাঁদ দেখা গেলে শেষ হয়। চাঁদ দেখা যাওয়ার ভিত্তিতে কোনো রমজানে ২৯ দিন এবং কোনো রমজানে ৩০ দিন তারাবীহ নামাজ পড়তে হয়।\n" +
                            "\n" +
                            "২, এশার ফরজ ও দুই রাকআত সুন্নতে মুয়াক্কাদা নামাজের পরে তারাবীহর নামাজ পড়া শুরু করতে হয়। তারাবীহর নামাজ শেষ হলে বিতরের নামাজ পড়তে হয়\n" +
                            "\n" +
                            "৩, যদি কেউ ভুল করে এশার নামাজের আগে তারাবীহর নামাজ পড়ে তাহলে তার কোনো নামাজই আদায় হবেনা। তাকে পুনরায় এশার নামাজ পড়ে তারপরে আবার তারাবীহর নামাজ পড়তে হবে।\n" +
                            "\n" +
                            "৪, তারাবীহর নামাজ মোট পাঁচ ভাগে ভাগ করা হয়। প্রত্যেক ভাগকে তারাবীহ বলে। প্রতি তারাবীহতে চার রাকআত নামাজ এবং মোট নামাজ পাঁচ তারাবীহ। সুতরাং মোট নামাজ বিশ রাকআত।\n" +
                            "\n" +
                            "৫, “আমি ইমামের সাথে তারাবীহর দুই রাকআত সুন্নতে মুয়াক্কাদা নামাজ পড়ছি” এই কথা \n" +
                            "খেয়াল রেখে নামাজের নিয়ত করতে হবে।\n" +
                            "\n" +
                            "৬, প্রতি দুই রাকআত পর পর সালাম দিতে হয়। প্রতি এক তারাবীহর পর এক তারাবীহ পরিমান সময় বিশ্রাম করা মুস্তাহাব। কিন্তু এত বেশী সময় বসে থাকলে মুক্তাদিগণের কষ্ট হয় তাই সামান্য সময় বসলেও চলবে।\n" +
                            "\n" +
                            "৭, এই বিশ্রামের সময় দুয়া করা, যিকির করা ও তাসবীহ পড়া ও দরূদ পড়া মুস্তাহাব। প্রচলিত দুয়াটি নিম্নরুপঃ\n" +
                            "\n" +
                            "سُبْحَانَ ذِي الْمُلْكِ وَالْمَلَكُوتِ، سُبْحَانَ ذِي الْعِزَّةِ وَالْعَظْمَةِ وَالْهَيْبَةِ وَالْقُدْرَةِ وَالْكِبْرِيَاءِ وَالْجَبَرُوتِ، سُبْحَانَ الْمَلِكِ الْحَيِّ الَّذِي لَا يَنَامُ وَلَا يَمُوتُ، سُبُّوحٌ قُدُّوسٌ رَبُّ الْمَلَائِكَةِ وَالرُّوحِ،\n" +
                            "\n" +
                            "উচ্চারণঃ সুবহানা জিল মুলকি ওয়াল মালাকুতি, সুবহানা জিল ইয্যাতি ওয়াল আঝমাতি ওয়াল হায়বাতি ওয়াল কুদরাতি ওয়াল কিব্রিয়ায়ি ওয়াল ঝাবারুতি, সুবহানাল মালিকিল হাইয়্যিল্লাজি লা ইয়ানামু ওয়া লা ইয়ামুতু, সুব্বুহুন কুদ্দুসুন রাব্বুল মালায়িকাতি ওয়াররূহ।\n" +
                            "\n" +
                            "৮, যদি কেউ জামআত ছাড়া এশার নামাজ পড়ে তারাবীহর জামআতে অংশ নেয় তাহলে তা জায়েয হবে। কিন্তু যদি সবাই একা একা এশার নামাজ পড়ে তারাবীহর জামআতে অংশ নেয় তাহলে তা জায়েয হবে না।\n" +
                            "\n" +
                            "৯, তারাবীহর নামাজের মধ্যে একবার কুরআন খতম করা সুন্নত। মুক্তাদিদের আলস্যের কারণে ইমামের কুরআন খতম ছেড়ে দেওয়া উচিত নয়।\n" +
                            "\n" +
                            "১০, কিন্তু যদি ছোট সূরাগুলো দিয়ে তারাবীহর জামআত পড়া হয় তাতে গুনাহ হবে না।\n" +
                            "\n" +
                            "১১, রমজান মাসের শুরুতে বেতন ঠিক করে তারাবীহর ইমাম নিযুক্ত করা জায়েয না।\n" +
                            "\n" +
                            "১২, প্রতি দুই রাকাতের কিরআত সমান সমান হওয়া মুস্তাহাব, কোনো দুই রাকআতে সামান্য বেশী হলে গুনাহ হবে না।\n" +
                            "\n" +
                            "১৩, কেউ যদি মসজিদে গিয়ে দেখে যে, তারাবীহর নামাজ শুরু হয়ে গিয়েছে তবে তাকে একা একা জামআত থেকে একটু দূরে দাঁড়িয়ে এশার নামাজ পড়ে নিতে হবে। তারপরে তারাবীহর জামআতে অংশ নিবে। তারাবীহর পরে বিতরও জামআতের সাথেই পড়তে হবে। মুনাজাতের পরে যত রাকআত তারাবীহ জামআতে পড়া হয় নি তত রাকআত একা একা পড়ে নিতে হবে।\n" +
                            "\n" +
                            "১৪, যদি নিকটস্থ মসজিদের ইমাম কিরআত ভুল বা অতি দ্রুত পড়ে, তাহলে অন্য মসজিদের তারাবীহর জামআতে অংশ নিলে গুনাহ হবে না।\n" +
                            "\n" +
                            "১৫, রমজান মাসে একা একা বিতর পড়া অপেক্ষা জামআতের সাথে বিতর\n" +
                            "পড়া উত্তম। জামআতে বিতর নামাজের তিন রাকআতেই ইমাম সশব্দে কিরআত\n" +
                            "পড়বেন। দুয়ায়ে কুনুত ইমাম ও মুক্তাদি উভয়কে নিঃশব্দে পড়তে হবে।\n" +
                            "\n" +
                            "১৬, তারাবীহর নামাজের ক্বাযা পড়া জায়েয না। ক্বাযা পড়লে মাকরূহ হবে।\n" +
                            "\n" +
                            "১৭, ঘুম ঘুম ভাব নিয়ে তারাবীহ পড়া মাকরূহ।\n" +
                            "\n" +
                            "১৮, তারাবীহ ও বিতর নামাজ শেষ করে নীচের দুয়াটি পড়া যায়। এটি ছাড়া অন্য যেকোনো দুয়াও পড়া যায়।",
                    "اَللَهُمَّ اِنَّا نَسْئَالُكَ الْجَنَّةَ وَ نَعُوْذُبِكَ مِنَ النَّارِ، يَا خَالِقَ الْجَنَّةَ وَالنَّارِ، بِرَحْمَتِكَ يَاعَزِيْزُ يَا غَفَّارُ يَا كَرِيْمُ يَا سَتَّارُ يَا رَحِيْمُ يَاجَبَّارُ يَاخَالِقُ يَابَارُّ، اَللَّهُمَّ اَجِرْنَا مِنَ النَّارِ، يَا مُجِيْرُ يَا مُجِيْرُ يَا مُجِيْرُ، بِرَحْمَتِكَ يَا اَرْحَمَ الرَّحِمِيْنَ،\n" ,
                    "উচ্চারণঃ আল্লাহুম্মা ইন্না নাসআলুকাল জান্নাতা ওয়া নাউজুবিকা মিনাননার, ইয়া খালিক্বাল জান্নাতি ওয়ান নার, বিরাহমাতিকা ইয়া আঝিঝু ইয়া গাফফার, ইয়া কারিমু ইয়া সাত্তার, ইয়া রাহিমু ইয়া ঝাব্বার, ইয়া খালিকু ইয়া বার্রু, আল্লাহুম্মা আঝিরনা মিনান নার, ইয়া মুঝিরু, ইয়া মুঝিরু, ইয়া মুঝির, বিরাহমাতিকা ইয়া আরহামার রাহিমিন।\n"
                    ));

            listaC.put("৭", new PojoClassForKalima("তাহাজ্জুদ",

                    "তাহাজ্জুদ একটি গুরুত্বপূর্ণ নফল নামাজ, এই নামাজের সময় বা পরে করা ইস্তিগফার ও দুয়া কবুল হওয়ার সম্ভাবনা বেশি, কারণ তাহাজ্জুদ আল্লাহ্\u200Cর বান্দাকে আল্লাহ্\u200Cর নিকটবর্তী ও প্রিয়তর করে। নিয়মিত তাহাজ্জুদ পড়লে আল্লাহ্\u200Cর সন্তুষ্টি ও রহমত লাভ করা যায়।",
                    "রাতের দুই তৃতীয়াংশ অতিবাহিত হওয়ার পর এবং ফযরের ওয়াক্ত শুরু হওয়ার আগে তাহাজ্জুদের নামাজ পড়তে হয়। তাহাজ্জুদের রাকআত সংখ্যা নির্দিষ্ট না। কমপক্ষে দুই রাকআত থেকে বার রাকআত পর্যন্ত পড়া যায়। দুই রাকআত করে ভেঙ্গে ভেঙ্গে পড়তে হবে। ",
                    "কিরআত সশব্দে অথবা নিঃশব্দে উভয়ভাবেই পড়া যায়।"));

            listaC.put("৮", new PojoClassForKalima("সালাতুল তাসবীহ",

                    "সালাতুল তাসবীহ একটি গুরুত্বপূর্ণ নফল নামাজ। কেউ এই নামাজ পড়লে আল্লাহ তার জীবনের প্রথম ও শেষ, অতীত ও বর্তমান, ইচ্ছাকৃত ও অনিচ্ছাকৃত, ছোট ও বড় এবং প্রকাশ্য ও গোপন সব গুনাহ ক্ষমা করে দেন।\n" +
                            "\n" +
                            "আমাদের রসূলুল্লাহ (সাল্লাল্লাহু ‘আলাইহি ওয়া সাল্লাম) আমাদেরকে এই নামাজ সম্ভব হলে প্রতিদিন একবার, অন্যথায় সপ্তাহে একবার, অন্যথায় মাসে একবার, এটাও সম্ভব না হলে বছরে একবার, যদি তাও সম্ভব না হয় তাহলে জীবনে অন্তত একবার আদায় করতে উপদেশ দিয়েছেন (সুনানে আবু দাউদ, হাদীস নং-১২৯৭)।\n" +
                            "\n" +
                            "এই নামাজ মোট চার রাকআত। সাধারণ নফল নামাজ থেকে এই নামাজের পার্থক্য হল এই নামাজের প্রতি রাকআতে ৭৫ বার করে মোট ৩০০ বার একটি তাসবীহ পড়তে হয়। তাহবীহটি নিচে দেওয়া হল।\n" +
                            "\n" +
                            "سُبْحَانَ اللَّهِ وَالْحَمْدُ لِلَّهِ وَلاَ إِلَهَ إِلاَّ اللَّهُ وَاللَّهُ أَكْبَرُ،\n" +
                            "\n" +
                            "উচ্চারণঃ সুবহানাল্লাহ ওয়াল-হামদুলিল্লাহ ওয়া লা ইলাহা ইল্লাল্লাহু ওয়াল্লাহু আকবার।\n" +
                            "\n" +
                            "সালাতুল তাসবীহ আদায় করার নির্দেশনাগুলো নিচে দেওয়া হল।\n" +
                            "\n" +
                            "১, প্রথম রাকআতে কিরআতের পরে তাসবীহটি ১৫ বার পড়তে হবে।\n" +
                            "\n" +
                            "২, রুকুতে থাকা অবস্থায় রুকুর তাসবীহ পড়ার পরে তাসবীহটি ১০ বার পড়তে হবে।\n" +
                            "\n" +
                            "৩, রুকু থেকে উঠে দাঁড়িয়ে \"রাব্বানা লাকাল হামদ\" পড়ার পরে তাসবীহটি ১০ বার পড়তে হবে।\n" +
                            "\n" +
                            "৪, প্রথম সিজদাহতে থাকা অবস্থায় সিজদাহর তাসবীহ পড়ার পরে তাসবীহটি ১০ বার পড়তে হবে।\n" +
                            "\n" +
                            "৫, প্রথম সিজদাহ থেকে উঠে বসে তাসবীহটি ১০ বার পড়তে হবে।\n" +
                            "\n" +
                            "৬, দ্বিতীয় সিজদাহতে থাকা অবস্থায় সিজদাহর তাসবীহ পড়ার পরে তাসবীহটি ১০ বার পড়তে হবে।\n" +
                            "\n" +
                            "৭, সিজদাহ থেকে থেকে উঠে দাঁড়িয়ে তাসবীহটি ১০ বার পড়তে হবে এবং দ্বিতীয় রাকআতে কিরআত শুরু করতে হবে।\n" +
                            "\n" +
                            "৮, প্রথম রাকআতের অনুরুপভাবে দ্বিতীয়, তৃতীয় ও চতুর্থ রাকআতেও তাসবীহটি ৭৫ বার করে পড়তে হবে।\n" +
                            "\n" +
                            "৯, দ্বিতীয় রাকআতের বৈঠকের তাশাহ্হুদ পড়ার আগেই তাসবীহটি ১০ বার পড়তে হবে।\n\n" +
                            "১০, চতুর্থ রাকআতের শেষ বৈঠকের তাশাহ্হুদ পড়ার আগেই তাসবীহটি ১০ বার পড়তে হবে।\n" +
                            "\n" +
                            "সালাতুল তাসবীহর কোনো অংশে তাসবীহটি পড়তে সম্পূর্ণ ভুলে গেলে বা ভুলে নির্দিষ্ট সংখ্যার থেকে কম বার পড়লে পরবর্তী যে অংশে মনে পড়বে, সে অংশেই তাসবীহটি ভুলে যাওয়া সংখ্যক বার বেশি পড়ে নিতে হবে।" ,
                    "এই নামাজে কোনো কারণে সিজদাহে সাহু ওয়াজিব হলে সেই সিজদাহে সাহুতে থাকা অবস্থায় এবং তার মধ্যকার বসায় তাসবীহটি পড়তে হবে না।" ,
                    "তাসবীহর সংখ্যা মনে রাখার জন্য আঙুলের দাগ গোনা যাবে না, কিন্তু আঙুল হাতের কব্জি বা হাঁটু বা মাটির সাথে চেপে চেপে মনে রাখা যাবে।\n"));
        }

        else if (ff.equals("6")){

            listaC.put("1", new PojoClassForKalima("ফরজ",

                    "যে কাজ করতে আল্লাহ আমাদেরকে সরাসরি নির্দেশ দিয়েছেন, সে কাজকে ফরজ বলা হয়।\n" +
                            "\n" +
                            "ফরজ কাজ না করলে ইহকালে ও পরকালে কঠিন শাস্তি হবে।\n" +
                            "\n" +
                            "ফরজ দুই রকম। ",
                    "১, ফরজে আইন\n" +
                            "যে সমস্ত কাজ প্রত্যেক দায়িত্বশীল, বিবেকবান এবং প্রাপ্তবয়স্ক মুসলিমের জন্য আদায় করা অপরিহার্য, এক দল মুসলিম তা সঠিকভাবে আদায় করলেও অন্যরা দায়িত্বমুক্ত হয় না, সেগুলোকে ফরজে আইন বলে। \n" +
                            "\n" +
                            "উদাহরণঃ কালিমা অন্তরে বিশ্বাস করা, ৫ ওয়াক্তের ফরজ নামাজ, রমজান মাসের রোজা, সামর্থ্য থাকলে হজ্জ করা, সামর্থ্য থাকলে যাকাত দেওয়া, ওয়াদা পালন করা, আমানত রক্ষা করা, সত্য কথা বলা, হালাল খাবার খাওয়া।\n",
                    "২, ফরজে কিফায়া\n" +
                            "যে সমস্ত কাজ প্রত্যেক মুসলিমের ওপর ব্যক্তিগত পর্যায়ে অপরিহার্য হয় না, বরং মুসলিম সমাজের ওপর এমনভাবে আরোপিত হয় যে এক দল মুসলিম তা সঠিকভাবে আদায় করলে অন্যরা দায়িত্বমুক্ত হয়ে যায়, তবে কেউ না করলে সকলেই গুনাহগার হবে, সেগুলোকে ফরজে কিফায়া বলে। \n" +
                            "\n" +
                            "উদাহরণঃ জানাজার নামাজ পড়া, কাফন দাফনের ব্যবস্থা করা।\n"));
            listaC.put("2", new PojoClassForKalima("ওয়াজিব",

                    "ওয়াজিব ফরজের মতই গুরুত্বপূর্ণ।  ",
                    " ওয়াজিব কাজ না করলেও কঠিন শাস্তি হবে।",
                    " উদাহরণঃ বিতরের নামাজ পড়া।"));
            listaC.put("3", new PojoClassForKalima("সুন্নত",

                   "আল্লাহর পাঠানো শেষ নবী ও রসূল মুহাম্মদ (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) এবং তাঁর সাহাবীরা যে রকম ভাবে জীবনযাপন ও ইবাদত করতেন, সেগুলোকে সুন্নত বলে।\n" +
                           "\n" +
                           "সুন্নত দুই রকম।\n",
                    "১, সুন্নতে মুয়াক্কাদা\n" +
                            "যে সমস্ত কাজ রসূল (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) এবং তাঁর সাহাবীরা কোনো বিশেষ কারণ ছাড়া নিয়মিত করতেন, সেগুলোকে সুন্নতে মুয়াক্কাদা বলে। \n" +
                            "\n" +
                            "সুন্নতে মুয়াক্কাদার গুরুত্ব ওয়াজিবেরই মতই। কোনো কারণে ফরজ বা ওয়াজিব কাজ বাদ পড়লে পরে তার ক্বাযা আদায় করতে হয়। কিন্তু সুন্নতে মুয়াক্কাদা বাদ পড়লে তার ক্বাযা আদায় করতে হয় না। বিশেষ কোনো কারণ ব্যতীত কেউ সুন্নতে মুয়াক্কাদা নিয়মিত বাদ দিলে সে গুনাহগার এবং রসূলের (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) খাস শাফায়াত থেকে বঞ্চিত হবে।\n" +
                            "\n" +
                            "উদাহরণঃ আজান, ইকামত, ফজরের নামাজের আগের দুই রাকাত সুন্নত নামাজ।\n",
                    "২, সুন্নতে গায়েরে মুয়াক্কাদা\n" +
                            "যে সমস্ত কাজ রসূল (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) এবং তাঁর সাহাবীরা মাঝে মাঝে করতেন এবং মাঝে মাঝে ছেড়ে দিতেন, সেগুলোকে সুন্নতে গায়েরে মুয়াক্কাদা বলে।\n" +
                            "\n" +
                            "সুন্নতে গায়েরে মুয়াক্কাদা বাদ দিলে গুনাহ হয় না।\n" +
                            "\n" +
                            "উদাহরণঃ আসরের ফরজ নামাজের আগে চার রাকআত, জুমুআর ফরজ ও সুন্নতে মুয়াক্কাদার নামাজের পরে দুই রাকআত।\n" ));
            listaC.put("4", new PojoClassForKalima("নফল ও মুস্তাহাব",

                    "যে সমস্ত কাজ রসূল (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) এবং তাঁর সাহাবীরা অধিকাংশ সময়ই ছেড়ে দিতেন, সেগুলোকে মুস্তাহাব বলে।",
                    " মুস্তাহাব বাদ দিলে গুনাহ হয় না। ",
                    " উদাহরণঃ তাহাজ্জুদের নামাজ পড়া।"));
            listaC.put("5", new PojoClassForKalima("মুবাহ",

                    "যে সমস্ত কাজে আল্লাহ তাআ’লা মানুষকে স্বাধীনতা দিয়েছেন, সেগুলোকে মুবাহ বলে।",
                    " মানুষ ইচ্ছা করলে মুবাহ কাজ করতেও পারে, ইচ্ছা করলে করতে নাও পারে। মুবাহ কাজ করলে সওয়াবও নাই, ছেড়ে দিলে গুনাহও নাই। জায়েজ যে কাজগুলো রয়েছে সেগুলো মুবাহর অনুরুপ।",
                    " উদাহরণঃ পান ও আহার করা, কৃষিকাজ করা, ব্যবসা করা, চাকুরী করা, দেশ ভ্রমণ করা ইত্যাদি।"));
            listaC.put("6", new PojoClassForKalima("হারাম",

                    "যে সমস্ত কাজ করতে আল্লাহ্\u200C কুরআনে ও রসূল (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) হাদিসে নিষেধ করেছেন, সেগুলোকে হারাম বলে।",
                    " হারাম ফরজের বিপরীত। কিছু হারাম কাজের জন্য শরীয়তে শাস্তির বিধানও আছে। হারামকে হারাম হিসেবে অস্বীকার করা কুফরী এবং বড় গুনাহের কাজ।",
                    " উদাহরণঃ শুকরের গোশত খাওয়া, মদ পান করা, নামাজ না পড়া, অসৎ উপায়ে রোজগার করা, আমানত রক্ষা না করা।"));
            listaC.put("7", new PojoClassForKalima("মাকরূহ",

                    "যে সমস্ত কাজ করা শরীয়তে নিষেধ আছে, সেগুলোকে মাকরূহ বলে। \n" +
                            "\n" +
                            "মাকরুহ শব্দের অর্থ অপ্রিয়। মাকরূহ কাজ না করাই উত্তম। মাকরূহ কাজে গুনাহর সম্ভাবনা আছে। মাকরুহ কাজ বর্জন করলে সওয়াব পাওয়া যায়।\n" +
                            "\n" +
                            "মাকরূহ দুই রকম।",
                    "১, মাকরূহে তাহরিমী\n" +
                            "মাকরূহে তাহরিমী হারামের কাছাকাছি। মাকরূহে তাহরিমী ওয়াজিবের বিপরীত। উপযুক্ত কোনো কারণ ছাড়া মাকরূহে তাহরিমী পালন করলে আযাব হবে।\n",
                    "২, মাকরূহে তানযিহী\n" +
                            "মাকরূহে তানযিহী হালালের কাছাকাছি। মাকরূহে তানযিহী সুন্নত বা মুস্তাহাবের বিপরীত। মাকরূহে তানযিহী কাজ করলে গুনাহ নেই কিন্তু ছেড়ে দিলে সওয়াব আছে।\n"));

            listaC.put("8", new PojoClassForKalima("বিদআ’ত",

                    "যে সমস্ত কাজ রসূল (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) এবং তাঁর সাহাবীরা করেননি এবং কুরআন বা সহিহ হাদিসে কাজগুলো অন্তত মুবাহ হওয়ার কোনো নমুনাও পাওয়া যায় না, সেগুলোকে বিদআ’ত বলে।",
                    " অধিকাংশ বিদআ’তই হারাম এবং মানুষকে কুফরীর পথে নিয়ে যায়। বিদআ’ত কাজগুলো পালন করা বড় গুনাহের কাজ।",
                    " উদাহরণঃ মাজারে পীর বা হুজুরের পূজা করা, মৃত ব্যক্তির চল্লিশাকে জরুরী মনে করা।"));
            listaC.put("9", new PojoClassForKalima("সিজদাহে সাহু",

                    "এর অর্থ সংশোধনের সিজদাহ। নামাজের কোন ওয়াজিব অংশ (রুকন) আদায় করতে ভুলে গেলে শেষ বৈঠকে সিজদাহে সাহু করলে নামাজ শুদ্ধ হয়ে যায়। ",
                    "নামাজের কোন ফরজ অংশ আদায় করতে ভুলে গেলে অথবা ওয়াজিব অংশ ইচ্ছা করে ছেড়ে দিলে সিজদাহে সাহু করলেও নামাজ শুদ্ধ হবে না, নামাজ আবার আদায় করতে হবে।",
                    " সিজদাহে সাহু করার নিয়ম হল, শেষ বৈঠকে বসে তাশাহ্হুদ পড়ার পরে অতিরিক্ত দুইটি সিজদাহ করে আবারও তাশাহ্হুদ পড়তে হবে এবং সাধারণ নামাজের মত দুরুদে ইব্রাহীম ও দুয়ায়ে মাছুরা পড়ে নামাজ শেষ করতে হবে।"));
        }
        else if (ff.equals("4")){
            listaC.put("1", new PojoClassForKalima("সানা",

                    "سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ، وَتَبَارَكَ اسْمُكَ، وَتَعَالَى جَدُّكَ، وَلاَ إِلَهَ غَيْرُكَ،\n",
                    "সুবহানাকা আল্লাহুম্মা ওয়া বিহামদিকা, ওয়া তাবারাকাসমুকা, ওয়া তাআ’লা যাদ্দুকা, ওয়া লা ইলাহা গাইরুকা।",
                    "হে আল্লাহ! আমি তোমার পবিত্রতা বর্ণনা করছি, তুমি প্রশংসাময়। তোমার নাম বরকতময়, তোমার মর্যাদা অতি উচ্চে, আর তুমি ব্যতীত কোনো মাবুদ (যার জন্য ইবাদত করা যায়) নেই।"));
            listaC.put("2", new PojoClassForKalima("তাশাহহুদ",

                    "التَّحِيَّاتُ لِلَّهِ وَالصَّلَوَاتُ وَالطَّيِّبَاتُ، السَّلامُ عَلَيْكَ أَيُّهَا النَّبِيُّ وَرَحْمَةُ اللَّهِ وَبَرَكَاتُهُ، السَّلامُ عَلَيْنَا وَعَلَى عِبَادِ اللَّهِ الصَّالِحِينَ، أَشْهَدُ أَنْ لا إِلَهَ إِلا اللَّهُ، وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ،",
                    "আত্তাহিয়্যাতু লিল্লাহি, ওয়াস সালাওয়াতু, ওয়াত-তাইয়্যিবাতু, আস সালামু আলাইকা, আইয়্যুহান নাবিয়্যু ওয়া রাহমাতুল্লাহি ওয়া বারাকাতুহ, আস সালামু আলাইনা ওয়া আলা ইবাদিল্লাহিস-সালিহীন। আশহাদু আল-লা ইলাহা ইল্লাল্লাহু, ওয়া আশহাদু আন্না মুহাম্মাদান আবদুহু ওয়া রাসূলুহু।",
                    "সব মৌখিক, দৈহিক ও আর্থিক ইবাদত আল্লাহর জন্য। হে নবী! আপনার প্রতি সালাম এবং আল্লাহর রহমত ও বরকত বর্ষিত হোক। সালাম আমাদের প্রতি এবং আল্লাহর নেক বান্দাদের প্রতি। আমি সাক্ষ্য প্রদান করছি আল্লাহ ব্যতিত আর কোনো মাবুদ নাই এবং আমি আরও সাক্ষ্য দিচ্ছি যে, নিশ্চয়ই মুহাম্মাদ (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) আল্লাহর বান্দা ও রাসুল।"));
            listaC.put("3", new PojoClassForKalima("দুরুদে ইব্রাহীম",

                    "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ كَمَا صَلَّيْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيمَ، إِنَّكَ حَمِيدٌ مَجِيدٌ، اللَّهُمَّ بَارِكَ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ كَمَا بَارَكْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيمَ، إِنَّكَ حَمِيدٌ مَجِيدٌ،",
                    "আল্লাহুম্মা সাল্লিআলা মুহাম্মাদিঁ ওয়া আলা আলি মুহাম্মাদিন কামা সাল্লাইতা আলা ইব্রাহীমা ওয়া আলা আলি ইব্রাহীম, ইন্নাকা হামিদুম মাজীদ। আল্লাহুম্মা বারিক আলা মুহাম্মাদিঁ ওয়া আলা আলি মুহাম্মাদিন কামা বারকতা আলা ইব্রাহীমা ওয়া আলা আলি ইব্রাহীম, ইন্নাকা হামিদুম মাজীদ।",
                    "হে আল্লাহ! তুমি মুহাম্মদ (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) ও তাঁর বংশধরের উপর রহমত বর্ষণ কর, যেমন তুমি হজরত ইব্রাহীম (আলাইহি ওয়া সাল্লাম) ও তাঁর বংশধরের উপর রহমত বর্ষণ করেছ। নিশ্চয় তুমি প্রশংসিত ও মহিমান্বিত। হে আল্লাহ! তুমি হজরত মুহাম্মদ (সাল্লাল্লাহু আলাইহি ওয়া সাল্লাম) ও তাঁর বংশধরের উপর বরকত বর্ষণ কর, যেমন তুমি হজরত ইব্রাহীম (আলাইহি ওয়া সাল্লাম) ও তাঁর বংশধরের উপর বরকত বর্ষণ করেছ। নিশ্চয় তুমি প্রশংসিত ও মহিমান্বিত।"));
            listaC.put("4", new PojoClassForKalima("দুয়ায়ে মাছুরা",

                    "اللَّهُمَّ إِنِّي ظَلَمْتُ نَفْسِي ظُلْماً كَثِيراً، وَلَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ، فَاغْفِرْ لِي مَغْفِرَةً مِنْ عِنْدِكَ وَارْحَمْنِي إِنَّكَ أَنْتَ الْغَفُورُ الرَّحِيمُ،\n",
                    "আল্লাহুম্মা ইন্নী যালামতু নাফসী যুলমান কাসীরাওঁ, ওয়ালা ইয়াগ ফিরুয-যুনূবা ইল্লা আন্তা, ফাগফির লী মাগফিরাতাম মিন ইন্দিকা ওয়ার হামনী ইন্নাকা আন্তাল-গফুরুর-রাহীম।\n",
                    "হে আল্লাহ্\u200C! আমি আমার আত্মার উপর বড় অত্যাচার করেছি এবং গুনাহ মাফকারী একমাত্র আপনিই। অতএব আপনি নিজ থেকেই আমাকে সম্পূর্ণ ক্ষমা করুন ও আমার প্রতি দয়া করুন, নিশ্চয়ই আপনি ক্ষমাশীল ও দয়ালু।"));
            listaC.put("5", new PojoClassForKalima("দুয়ায়ে কুনুত ",

                    "اَللَّهُمَّ إِنَّا نَسْتَعِينُكَ وَنَسْتَغْفِرُكَ وَنُؤْمِنُ بِكَ وَنَتَوَكَّلُ عَلَيْكَ وَنُثْنِئْ عَلَيْكَ الخَيْرَ، وَنَشْكُرُكَ وَلَا نَكْفُرُكَ وَنَخْلَعُ وَنَتْرُكُ مَنْ ئَّفْجُرُكَ، اَللَّهُمَّ إِيَّاكَ نَعْبُدُ وَلَكَ نُصَلِّئ وَنَسْجُدُ وَإِلَيْكَ نَسْعأئ وَنَحْفِدُ وَنَرْجُو رَحْمَتَكَ وَنَخْشآئ عَذَابَكَ إِنَّ عَذَابَكَ بِالكُفَّارِ مُلْحَقٌ،",
                    "আল্লাহুম্মা ইন্না নাস্তায়ীনুকা, ওয়া নাসতাগফিরুকা, ওয়ানুমিনুবিকা, ওয়ানা তাওয়াক্কালু আলাইকা, ওয়া নুসনি আলাইকাল খাইর। ওয়া নাশকুরুকা ওয়ালা নাকফুরুকা, ওয়া নাখলাউ ওয়া নাতরুকু মাইয়াফ জুরুকা। আল্লাহুম্মা ইয়্যাকানা বুদু ওয়ালাকা নুছল্লি ওয়া নাসজুদু ওয়া ইলাইকা নাসআ, ওয়া নাহফিদু ওয়া নারজু রহমাতাকা ওয়া নাখ্শা আযাবাকা ইন্না আযাবাকা বিল কুফফারি মুলহিক।",
                    "হে আল্লাহ! আমরা তোমারই সাহায্য চাই, তোমারই কাছে ক্ষমা চাই, তোমারই প্রতি বিশ্বাস রাখি, তোমারই ওপর ভরসা করি এবং সকল মঙ্গল তোমারই দিকে ন্যস্ত করি। আমরা তোমার কৃতজ্ঞ হয়ে চলি, অকৃতজ্ঞ হই না। হে আল্লাহ! আমরা তোমারই দাসত্ব করি, তোমারই জন্য নামাজ পড়ি, তোমাকেই সিজদাহ করি, তোমারই দিকে দৌড়াই ও এগিয়ে চলি। আমরা তোমারই রহমত আশা করি এবং তোমার আযাবকে ভয় করি, আর তোমার আযাবতো কাফেরদের জন্যই র্নিধারিত।"));

        }



        return listaC;
    }

    private void ToolBar() {

        mToolbar = findViewById( R.id.kalima_toolbar );
 /*       TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);*/




/*

        //toolbar name ==>
        if (ff.equals("1")){
            mTitle.setText("বিশেষ নামাজ");
        }
        else if (ff.equals("2")){
            mTitle.setText("মোনাজাতের নিয়ম");
        }
        else if (ff.equals("3")){
            mTitle.setText("ফরজ ও সুন্নত নামাজের পার্থক্য");
        }
        else if (ff.equals("4")){
            mTitle.setText("দোয়া ও দুরুদ");
        }
        else if (ff.equals("5")){
            mTitle.setText("তাহারাত");
        }
        else if (ff.equals("6")){
            mTitle.setText("শরীয়তের বিভিন্ন প্রয়োজনীয় শব্দ");
        }else if (ff.equals("0")){
            mTitle.setText("নামাজের নিয়ম");
        }
*/

        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }

}
