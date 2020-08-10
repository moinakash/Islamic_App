package com.example.islamicappb.activity;

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

import com.example.islamicappb.Adapters.kalimaExpandableAdapter;
import com.example.islamicappb.DatabaseHelper;
import com.example.islamicappb.pojo_classes.PojoClassForKalima;
import com.example.islamicappb.R;

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
    String str, kaja1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bishesh_namaj);

        tvDetailss = findViewById(R.id.idDetailsTv);
        scrollView1 = findViewById(R.id.idScrollView1);

        Intent intent = getIntent();
        ff = intent.getExtras().getString("shoriot");

        Log.e("tag",""+ff);

        kaja1 = getResources().getString(R.string.kaja1);



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
