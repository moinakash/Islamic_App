package com.example.islamicappb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KalimaActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListNombres;
    private HashMap<String, PojoClassForKalima> listaContactos;
    private int lastExpandedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalima);

        init();

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

        /**
         expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
        @Override
        public void onGroupCollapse(int groupPosition) {
        Toast.makeText(getBaseContext(),"List Collapsed:" +
        expandableListNombres.get(groupPosition),Toast.LENGTH_SHORT).show();
        }
        });


         expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
        int groupPosition, int childPosition, long id) {

        Toast.makeText(getBaseContext(),
        expandableListNombres.get(groupPosition) +
        " ---> " + listaContactos.get(expandableListNombres.get(groupPosition))
        ,Toast.LENGTH_SHORT).show();
        return false;
        }
        });
         */

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

        listaC.put("১", new PojoClassForKalima("কালিমা-ই তাইয়্যিবাহ",
                "لآ اِلَهَ اِلّا اللّهُ مُحَمَّدٌ رَسُوُل اللّهِ\n",
                "লা~ইলা-হা ইল্লাল্ল-হু মুহাম্মাদুর রসূলুল্ল-হ্\u200C।\n",
                "আল্লাহ্ ছাড়া কোন সত্য উপাস্য নাই, মুহাম্মাদ(সাল্লাল্লাহু আলাইহি ওয়াসাল্লাম) তাঁহার প্রেরিত রসূল।"));

        listaC.put("২", new PojoClassForKalima("কালিমা-ই শাহাদাহ",
                "اشْهَدُ انْ لّآ اِلهَ اِلَّا اللّهُ وَحْدَه لَا شَرِيْكَ لَه، وَ اَشْهَدُ اَنَّ مُحَمَّدً اعَبْدُه وَرَسُولُه\n",
                "আশ্\u200Cহাদু আল-লা-ইলা-হা ইল্লাল্লা-হু ওয়াহ্\u200Cদাহু-লা-শারীকালাহু ওয়া আশ্\u200Cহাদু আন্না মুহাম্মাদান আ'বদুহু ওয়া রাসূলুহু।",
                "আমি সাক্ষ্য দিচ্ছি যে, আল্লাহ্\u200C ছাড়া কোন উপাস্য নাই। তিনি এক, অদ্বিতীয় এবং আরও সাক্ষ্য দিতেছি যে, মুহাম্মাদ তাঁহার বান্দা ও প্রেরিত রাসুল।"));

        listaC.put("৩", new PojoClassForKalima("কালিমা-ই তামজীদ",
                "ﺳُﺒْﺤَﺎﻥ ﺍﻟِﻠّﻪ ﻭَ ﺍﻟْﺤَﻤْﺪُ ﻟِﻠّﻪِ ﻭَ ﻵ ﺍِﻟﻪَ ﺍِﻟّﺎ ﺍﻟﻠّﻪُ، ﻭَ ﺍﻟﻠّﻪُ ﺍَﻛْﺒَﺮُ ﻭَﻻ ﺣَﻮْﻝَ ﻭَﻻَ ﻗُﻮَّﺓَ ﺍِﻟَّﺎ ﺑِﺎﻟﻠّﻪِ ﺍﻟْﻌَﻠِﻰّ ﺍﻟْﻌَﻈِﻴْﻢ\n",
                "সুবহানআল্লাহি ওয়াল হামদু লিল্লাহি ওয়ালা ইলাহা ইল্লাল্লাহু ওয়াল্লাহু আকবার, ওয়ালা হাওলা ওয়ালা কুয়াতা ইল্লা বিল্লাহিল আলীইল আজিম।",
                "মহিমান্বিত আল্লাহ্\u200Cর, সমস্ত প্রশংসা আল্লাহ্\u200Cর। আল্লাহ্\u200C ছাড়া কোন মাবুদ নাই। আল্লাহ্\u200C মহান। সমস্ত পবিত্রতা আল্লাহ্\u200Cর, সকল প্রশংসা আল্লাহ্\u200Cর। আল্লাহ্\u200C ছাড়া কোন শক্তি নাই, কোন ক্ষমতা নাই, তিনি সম্মানিত, তিনি মহান।"));

        listaC.put("৪", new PojoClassForKalima("কালিমা-ই তাওহীদ",
                "لا الهَ اِلَّا اللّهُ وَحْدَهُ لا شَرِيْكَ لَهْ، لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ يُحْى وَ يُمِيْتُ وَ هُوَحَىُّ لَّا يَمُوْتُ اَبَدًا اَبَدًا ط ذُو الْجَلَالِ وَ الْاِكْرَامِ ط بِيَدِهِ الْخَيْرُ ط وَهُوَ عَلى كُلِّ شَئ ٍ قَدِيْرٌ\n",
                "লা ইলাহা ইল্লাল্লাহু ওয়াহদাহু লা শারিকা লাহু, লাহুল মুলকু ওলাহুল হামদু উহয়ী ওয়া ইয়োমিতু ওয়া হুয়া হাইয়ুল লা ইয়ামুতু আবাদান আবাদা জুল জালালি ওয়াল ইকরাম বি ইয়াদিহিল খাইর ওয়া হুয়া আলা কুল্লি শাইয়িন কাদির।\n",
                "আল্লাহ ব্যতীত কোন উপাস্য নেই, তিনি এক ও অদ্বিতীয়। তাঁর কোন অংশীদার নেই। সকল ক্ষমতা এবং প্রশংসা তাঁরই জন্য। তিনিই জীবন ও মৃত্যুর মালিক। তিনি চিরঞ্জীব, তিনি সকল সম্মানের মালিক। তাঁর হাতেই সকল মঙ্গল এবং তিনি সবকিছুর উপর ক্ষমতা রাখেন।\n"));

        listaC.put("৫", new PojoClassForKalima("কালিমা-ই আস্তাগফির",
                "اسْتَغْفِرُ اللّهَ رَبِّىْ مِنْ كُلِّ ذَنْبٍ اَذْنَبْتُه عَمَدًا اَوْ خَطَاً سِرًّا اَوْ عَلَانِيَةً وَاَتُوْبُ اِلَيْهِ مِنْ الذَّنْبِ الَّذِىْ اَعْلَمُ وَ مِنْ الذَّنْبِ الَّذِىْ لا اَعْلَمُ اِنَّكَ اَنْتَ عَلَّامُ الغُيُبِ وَ سَتَّارُ الْعُيُوْبِ و َغَفَّارُ الذُّنُوْبِ وَ لا حَوْلَ وَلا قُوَّةَ اِلَّا بِاللّهِ الْعَلِىِّ العَظِيْم\n",
                "আস্তাগফিরুল্লাহা রাব্বি মিন কুল্লি জামবি আয নাবতুহু আমাদান আওখাতাআন সিররান আওয়ালা নিয়াতান ওয়াতুবু ইলাইহি মিনাযযামবিল্লাজি ওয়ামিনাযযামবিল্লাজি লা আলামু ইন্নাকা আনতা আল্লামুল গুয়ুবী ওয়া সাত্তারুল উইয়ুবী ওয়া গাফফারুজজুনুবি ওয়ালা হাওলা ওয়ালা কুয়াতা ইল্লা বিল্লাহিল আলিউল আযীম।\n",
                "আমি আল্লাহ্\u200Cর কাছে ক্ষমা চাই সকল পাপ থেকে, যা আমি সংঘটিত করেছি আমার জ্ঞাতসারে অথবা অজ্ঞাতসারে, গোপনে বা প্রকাশ্যে এবং আমি আমার পালনকর্তার আশ্রয় চাই সেই পাপ থেকে, যে পাপ আমি জানি এবং যে পাপ আমি জানিনা। অবশ্যই আপনি লুকানো এবং গোপন (ভুল) পাপ সম্পর্কে জানেন এবং ক্ষমাশীল। আল্লাহ্\u200C ছাড়া কোন শক্তি নেই, কোন ক্ষমতা নেই, তিনি সম্মানিত, তিনি মহান।\n"));

        listaC.put("৬", new PojoClassForKalima("কালিমা-ই রুদ-ই কুফর",

                "اَللّهُمََّ اِنّىْ اَعُوْدُ بِكَ مِنْ انْ اُشْرِكَ بِكَ شَيئًا وََّ اَنَا اَعْلَمُ بِه وَ اسْتَغْفِرُكَ لِمَا لا اَعْلَمُ بِه تُبْتُ عَنْهُ وَ تَبَرَّاْتُ مِنَ الْكُفْرِ وَ الشّرْكِ وَ الْكِذْبِ وَ الْغِيْبَةِ وَ الْبِدْعَةِ وَ النَّمِيْمَةِ وَ الْفَوَاحِشِ وَ الْبُهْتَانِ وَ الْمَعَاصِىْ كُلِّهَا وَ اَسْلَمْتُ وَ اَقُوْلُ لآ اِلهَ اِلَّا اللّهُ مُحَمَّدُ رَّسُوْلُ اللّهِ\n",
                "আল্লাহুম্মা ইন্নি আউযুবিকা মিন উশরিকা বিকা শাইআও ওয়ানা আলামু বিহি ওয়াস্তাগফিরুকা লিমালা আলামু বিহি তুবতু আনহু ওয়া তাবাররাতু মিনাল-কুফরি ওয়াশ-শিরকী ওয়াল-কিজবি ওয়ালা-গিবাতি ওয়াল-বিদাতি ওয়ান্না-মিমাতি ওয়াল-ফাওয়া হিমি ওয়াল-রুহতানী ওয়াল-মাআসি কুল্লিহা ওয়াসলামতু ওয়াকুলু লা ইলাহা ইল্লাল্লাহু মুহাম্মাদুর রাসুলুল্লাহ",
                "হে আল্লাহ্\u200C নিশ্চয়, ক্ষমা চাই শিরক করা থেকে (আল্লাহ্\u200Cর সাথে কাউকে শরিক করা), আমি ক্ষমা চাই সকল পাপ থেকে যা সম্পর্কে আমি সচেতন নই বা জানি না, আমি পুনরায় ঘোষণা করছি, আমি সকল প্রকার কুফর থেকে, শিরক থেকে, মিথ্যা (কথা বলা), গীবত, বিদাত, পরনিন্দা, অশ্লীলতা এবং অন্যান্য সকল পাপ থেকে মুক্ত। আমি ইসলাম স্বীকার এবং বিশ্বাস করি এবং ঘোষণা দিচ্ছি যে, আল্লাহ্\u200C ছাড়া কোন প্রভু নেই এবং মুহাম্মদ আল্লাহ্\u200Cর প্রেরিত রাসুল।"));



        return listaC;
    }

}
