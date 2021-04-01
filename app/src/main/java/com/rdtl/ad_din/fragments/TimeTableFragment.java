package com.rdtl.ad_din.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.AlarmClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rdtl.ad_din.R;
import com.rdtl.ad_din.activity.BaseActivity;
import com.rdtl.ad_din.activity.EightDivisonActivity;
import com.rdtl.ad_din.pojo_classes.ConverterClass;
import com.rdtl.ad_din.pojo_classes.WaktoTimeMaintaining;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Calendar;
import java.util.Date;


import io.paperdb.Paper;
import pl.droidsonroids.gif.GifImageView;


public class TimeTableFragment extends Fragment {

    TextView tvfojor, tvjohor, tvasor, tvmagrib, tvesha;
    TextView tvCurrentNTime, tvCurrentNTime2, tvCurrentDate,tvCurrentArbiDate ,MidBorber;
    TextView tvCurrentLocation, tvNextTime,tvNamajTimeNUmber, tvNamajSeshSomoy;

    ImageButton ibFojor, ibJohor, ibAsor, ibMagrib, ibEsha;



    GifImageView Heading_background;
    LinearLayout Fjr_stroke_bg, Jhr_stroke_bg,Asr_stroke_bg, Mgrb_stroke_bg,Isha_stroke_bg, timetablebg, dateFiled;


    String text;

    int hdcY, hdcM, hdcD;

    String fojorTimeBangla,johorTimeBangla,asorTimeBangla,magribTimeBangla,eshaTimeBangla;

    String year, month, day;

    String AlermSetToast;
    String dateTime;

    ImageView locationButton;

    int ii;

    ConverterClass converterClass;
    WaktoTimeMaintaining wtm;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    

    public TimeTableFragment() {

    }


    public static TimeTableFragment newInstance(String param1, String param2) {
        TimeTableFragment fragment = new TimeTableFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);


        Paper.init(getContext());

        converterClass = new ConverterClass(getContext());
        wtm = new WaktoTimeMaintaining(getContext());

        AlermSetToast = getResources().getString(R.string.Alerm_Set_Toast);

        tvfojor = view.findViewById(R.id.idFajr);
        tvjohor = view.findViewById(R.id.idJohor);
        tvasor = view.findViewById(R.id.idAsor);
        tvmagrib = view.findViewById(R.id.idMagrib);
        tvesha = view.findViewById(R.id.idIsha);

        Heading_background = view.findViewById(R.id.idHeadingBackground);

        Fjr_stroke_bg = view.findViewById(R.id.idFojorStrokeBG);
        Jhr_stroke_bg = view.findViewById(R.id.idJohorStrokeBG);
        Asr_stroke_bg = view.findViewById(R.id.idAsorStrokeBG);
        Mgrb_stroke_bg = view.findViewById(R.id.idMagribStrokeBG);
        Isha_stroke_bg = view.findViewById(R.id.idIshaStrokeBG);

        timetablebg = view.findViewById(R.id.linearLayout_currenttime_bg);

        tvCurrentNTime = view.findViewById(R.id.idCurrentNamajTime);
        tvCurrentNTime2 = view.findViewById(R.id.idCurrentNamajTime2);
        tvCurrentDate = view.findViewById(R.id.idCurrentDate);
        tvCurrentArbiDate = view.findViewById(R.id.idCurrentDateArbi);
        MidBorber = view.findViewById(R.id.idMidBorder);

        locationButton = view.findViewById(R.id.imageView2);

        tvCurrentLocation = view.findViewById(R.id.idCurrentLocation);
        tvNextTime = view.findViewById(R.id.idNextNamaj);
        tvNamajTimeNUmber = view.findViewById(R.id.idNamajTimeInNumber);
        tvNamajSeshSomoy = view.findViewById(R.id.idSeshSomoy);

        ibFojor = view.findViewById(R.id.idAlarmFojor);
        ibJohor = view.findViewById(R.id.idAlarmJohor);
        ibAsor = view.findViewById(R.id.idAlarmAsor);
        ibMagrib = view.findViewById(R.id.idAlarmMagrib);
        ibEsha = view.findViewById(R.id.idAlarmEsha);

        dateFiled = view.findViewById(R.id.id_date);

//        Locale locale = new Locale("bn");
//        Locale.setDefault(locale);

        //////////////////////////////////////////////////////////////////////////////new///////////////////////////////////////////////

        Date date = new Date();
        Calendar cl= Calendar.getInstance();


        ////////////////////////////////



        cl.setTime(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy ");
        dateTime = simpleDateFormat.format(cl.getTime());



        HijrahDate islamyDate = null;


        hdcY = cl.get(Calendar.YEAR);
        hdcM = cl.get(Calendar.MONTH)+1;
        hdcD = cl.get(Calendar.DATE);

        dateCalForHizri();

        Log.e("hdc vl", "y"+hdcY+" m"+hdcM+" d"+hdcD);

            islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(hdcY,hdcM,hdcD));
            text = islamyDate.toString();
            text = text.replace("Hijrah-umalqura AH", "");






            text = converterClass.covertS(text);



            text = text.replace("-০১-", ",মহরম,");
            text = text.replace("-০2-", "সফল");
            text = text.replace("-০৩-", "রবিউল আউয়াল");
            text = text.replace("-০৪-", "রবিউস সানি");
            text = text.replace("-০৫-", "জমাদিউল আউয়াল");
            text = text.replace("-০৬-", "জমাদিউস সানি");
            text = text.replace("-০৭-", "রজব");
            text = text.replace("-০৮-", "শাবান");
            text = text.replace("-০৯-", "রমজান");
            text = text.replace("-১০-", "শওয়াল");
            text = text.replace("-১১-", ",জিলক্বদ,");
            text = text.replace("-১২-", "জিলহজ্জ");





            Character [] text_array= new Character[text.length()];

            int i;
            for ( i = 0; i < text.length(); i++) {
                text_array[i] = text.charAt(i);
                ii = i;
            }

            year = ""+text_array[0]+""+text_array[1]+""+text_array[2]+""+text_array[3]+""+text_array[4];
            day = ""+text_array[(ii-1)]+""+text_array[(ii)];

            String txtf = "";
            for ( i = 5; i < (ii-1); i++){


                txtf = ""+txtf+""+text_array[i];

            }

            tvCurrentArbiDate.setText(day+" "+txtf+""+year);







        ///////////////////////////////////////////////////new finish///////////////////////////////////////////////////////////////////




        ///////////////////////////////////////////////////////old start////////////////////////////////////////////////////////////


//        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M ){
//
//            DateFormat df = new java.text.SimpleDateFormat("dd MMM yyyy");
//            String dfT = converterClass.covertS(df.format(java.util.Calendar.getInstance().getTime()));
//
//            dfT = dfT.replace("Jan", "জানুয়ারি");
//            dfT = dfT.replace("Feb", "ফেব্রুয়ারি");
//            dfT = dfT.replace("Mar", "মার্চ");
//            dfT = dfT.replace("Apr", "এপ্রিল");
//            dfT = dfT.replace("May", "মে");
//            dfT = dfT.replace("Jun", "জুন");
//            dfT = dfT.replace("Jul", "জুলাই");
//            dfT = dfT.replace("Aug", "আগষ্ট");
//            dfT = dfT.replace("Sep", "সেপ্টেম্বর");
//            dfT = dfT.replace("Oct", "অক্টোবর");
//            dfT = dfT.replace("Nov", "নভেম্বর");
//            dfT = dfT.replace("Dec", "ডিসেম্বর");
//
//
//            dateTime = dfT;
//
//
//
//        }else {
//
//            Calendar cl = Calendar.getInstance(locale);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy ");
//            dateTime = simpleDateFormat.format(cl.getTime());
//
//
//
//            HijrahDate islamyDate = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR),cl.get(Calendar.MONTH)+1, cl.get(Calendar.DATE)));
//                text = islamyDate.toString();
//                text = text.replace("Hijrah-umalqura AH", "");
//
//
//
//
//
//
//                text = converterClass.covertS(text);
//
//
//
//                text = text.replace("-০১-", ",মহরম,");
//                text = text.replace("-০2-", "সফল");
//                text = text.replace("-০৩-", "রবিউল আউয়াল");
//                text = text.replace("-০৪-", "রবিউস সানি");
//                text = text.replace("-০৫-", "জমাদিউল আউয়াল");
//                text = text.replace("-০৬-", "জমাদিউস সানি");
//                text = text.replace("-০৭-", "রজব");
//                text = text.replace("-০৮-", "শাবান");
//                text = text.replace("-০৯-", "রমজান");
//                text = text.replace("-১০-", "শওয়াল");
//                text = text.replace("-১১-", ",জিলক্বদ,");
//                text = text.replace("-১২-", "জিলহজ্জ");
//
//
//
//
//
//                Character [] text_array= new Character[text.length()];
//
//                int i;
//                for ( i = 0; i < text.length(); i++) {
//                    text_array[i] = text.charAt(i);
//                    ii = i;
//                }
//
//                year = ""+text_array[0]+""+text_array[1]+""+text_array[2]+""+text_array[3]+""+text_array[4];
//                day = ""+text_array[(ii-1)]+""+text_array[(ii)];
//
//                String txtf = "";
//                for ( i = 5; i < (ii-1); i++){
//
//
//                    txtf = ""+txtf+""+text_array[i];
//
//                }
//
//                tvCurrentArbiDate.setText(day+" "+txtf+""+year);
//
//
//            }
//
//            if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.O) {
//                text = "Its only for android orio";
//                tvCurrentArbiDate.setVisibility(View.GONE);
//                MidBorber.setVisibility(View.GONE);
//
//            }
//
//        }


        /////////////////////////////////////////////////old finish////////////////////////////////////////////////////////








        BaseActivity activity = (BaseActivity) getActivity();
        String fojorerTime = wtm.ftimewithaddm(activity.fojorData());
        String johorerTime = wtm.jtimewithaddm(activity.johorData());
        String asorerTime = wtm.atimewithaddm(activity.asorData());
        String magriberTime = wtm.mtimewithaddm(activity.magribData());
        String esharTime = wtm.etimewithaddm(activity.eshaData());

        int fjrt,jhrt,asrt,mgrt,esrt;
//        fjrt = Integer.parseInt(fojorerTime);
//        jhrt = Integer.parseInt(johorerTime);
//        asrt = Integer.parseInt(asorerTime);
//        mgrt = Integer.parseInt(magriberTime);
//        esrt = Integer.parseInt(esharTime);

        String CurrentNamajTime = activity.currentNamajTime();


        String BOrPNamajTime = activity.bOrPNamajTime();

        String NamajerSeshTime = activity.seshTimeOfNamaj();
        String LocatString = activity.LocationString();


        ///////////////////////////////////////////////////////////////////////////////////////////
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (!LocatString.isEmpty()){
            sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("sthan", ""+LocatString);
            editor.commit();
        }

        LocatString = sharedPref.getString("sthan","");


        ///////////////////////////////////////////////////////////////////


        Log.e("jhamela",""+activity.johorData());
       //TODO


        /////////////////////////////////////

       // tvCurrentLocation.setText(""+LocatString);

        ////fojor time bangla
        Character [] array= new Character[fojorerTime.length()];

        for ( i = 0; i < fojorerTime.length(); i++) {
            array[i] = fojorerTime.charAt(i);
        }
        fojorTimeBangla = ""+array[0]+""+array[1]+""+array[2]+""+array[3];

        String SfHour = ""+array[0];
        String SfMinute = ""+array[2]+""+array[3];
        final int fHour = Integer.parseInt(SfHour);
        final int fMinute = Integer.parseInt(SfMinute);


        fojorTimeBangla = converterClass.covertS(fojorTimeBangla);


        ////johor time bangla
        Character [] johorarray= new Character[johorerTime.length()];

        for ( i = 0; i < johorerTime.length(); i++) {
            johorarray[i] = johorerTime.charAt(i);
        }
        String txtJohor = ""+johorarray[0]+""+johorarray[1];
        int jInt = Integer.parseInt(txtJohor);
        int jhInt = jInt;
        if (jInt>12){
            jInt = jInt - 12;
        }



        johorTimeBangla = ""+jInt+""+johorarray[2]+""+johorarray[3]+""+johorarray[4];




        String SjMinute = ""+johorarray[3]+""+johorarray[4];

        final int jHour = jhInt;
        final int jMinute = Integer.parseInt(SjMinute);

        johorTimeBangla = converterClass.covertS(johorTimeBangla);

        ////Asor time bangla
        Character [] asorarray= new Character[asorerTime.length()];

        for ( i = 0; i < asorerTime.length(); i++) {
            asorarray[i] = asorerTime.charAt(i);
        }

        String txtAsor = ""+asorarray[0]+""+asorarray[1];
        int aInt = Integer.parseInt(txtAsor);
        int asInt = aInt;
        if (aInt>12){
            aInt = aInt - 12;
        }

        asorTimeBangla = ""+aInt+""+asorarray[2]+""+asorarray[3]+""+asorarray[4];



        String SaMinute = ""+asorarray[3]+""+asorarray[4];
        final int aHour = asInt;
        final int aMinute = Integer.parseInt(SaMinute);

        asorTimeBangla = converterClass.covertS(asorTimeBangla);



        ////magrib time bangla
        Character [] magribarray= new Character[magriberTime.length()];

        for ( i = 0; i < magriberTime.length(); i++) {
            magribarray[i] = magriberTime.charAt(i);
        }

        String txtMagrib = ""+magribarray[0]+""+magribarray[1];
        int mInt = Integer.parseInt(txtMagrib);
        int mgInt = mInt;
        if (mInt>12){
            mInt = mInt - 12;
        }

      magribTimeBangla = ""+mInt+""+magribarray[2]+""+magribarray[3]+""+magribarray[4];

        String SmMinute = ""+magribarray[3]+""+magribarray[4];
        final int mHour = mgInt;
        final int mMinute = Integer.parseInt(SmMinute);


        magribTimeBangla = converterClass.covertS(magribTimeBangla);


        ////Esha time bangla
        Character [] eshaarray= new Character[esharTime.length()];

        for ( i = 0; i < esharTime.length(); i++) {
            eshaarray[i] = esharTime.charAt(i);
        }

        String txtEsha = ""+eshaarray[0]+""+eshaarray[1];
        int eInt = Integer.parseInt(txtEsha);
        int ehInt = eInt;
        if (eInt>12){
            eInt = eInt - 12;
        }

        eshaTimeBangla = ""+eInt+""+eshaarray[2]+""+eshaarray[3]+""+eshaarray[4];

        String SeMinute = ""+eshaarray[3]+""+eshaarray[4];
        final int eHour = ehInt;
        final int eMinute = Integer.parseInt(SeMinute);
        //String eshaTimeBangla = ""+eshaarray[0]+""+eshaarray[1]+""+eshaarray[2]+""+eshaarray[3]+""+eshaarray[4];

        eshaTimeBangla = converterClass.covertS(eshaTimeBangla);



        tvfojor.setText("০"+fojorTimeBangla);
        tvjohor.setText(johorTimeBangla);
        tvasor.setText("০"+asorTimeBangla);
        tvmagrib.setText("০"+magribTimeBangla);
        tvesha.setText("০"+eshaTimeBangla);

        SharedPreferences sharedPrefWidget = getActivity().getSharedPreferences("WidgetMM",0);

        sharedPrefWidget = getActivity().getSharedPreferences("mm",0);



        SharedPreferences.Editor editorWidget = sharedPrefWidget.edit();
        editorWidget.putString("fw", "০"+fojorTimeBangla);
        editorWidget.putString("jw", ""+johorTimeBangla);
        editorWidget.putString("aw", "০"+asorTimeBangla);
        editorWidget.putString("mw", "০"+magribTimeBangla);
        editorWidget.putString("ew", "০"+eshaTimeBangla);
        editorWidget.putString("lc", ""+LocatString);

        editorWidget.commit();





        ibFojor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "ফজরের ওয়াক্ত শুরু");
                i.putExtra(AlarmClock.EXTRA_HOUR, fHour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, fMinute);
                Toast.makeText(getContext(), ""+AlermSetToast, Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });
        ibJohor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "যোহরের ওয়াক্ত শুরু");
                i.putExtra(AlarmClock.EXTRA_HOUR, jHour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, jMinute);
                Toast.makeText(getContext(), ""+AlermSetToast, Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });
        ibAsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "আসরের ওয়াক্ত শুরু");
                i.putExtra(AlarmClock.EXTRA_HOUR, aHour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, aMinute);
                Toast.makeText(getContext(), ""+AlermSetToast, Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });
        ibMagrib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "মাগরিবের ওয়াক্ত শুরু");
                i.putExtra(AlarmClock.EXTRA_HOUR, mHour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, mMinute);
                Toast.makeText(getContext(), ""+AlermSetToast, Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });
        ibEsha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "এশার ওয়াক্ত শুরু");
                i.putExtra(AlarmClock.EXTRA_HOUR, eHour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, eMinute);
                Toast.makeText(getContext(), ""+AlermSetToast, Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });


        tvNextTime.setText(""+BOrPNamajTime);
        tvCurrentNTime.setText(""+CurrentNamajTime);

//        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M ){
//
//            //dateFiled.setVisibility(View.GONE);
////            tvCurrentDate.setText(dateTime);
////            tvCurrentArbiDate.setVisibility(View.GONE);
//            MidBorber.setVisibility(View.GONE);
//
//
//
//        }else {
//            tvCurrentDate.setText(dateTime);
//        }

        String dfT = dateTime;

            dfT = dfT.replace("January", "জানুয়ারি");
            dfT = dfT.replace("February", "ফেব্রুয়ারি");
            dfT = dfT.replace("March", "মার্চ");
            dfT = dfT.replace("April", "এপ্রিল");
            dfT = dfT.replace("May", "মে");
            dfT = dfT.replace("June", "জুন");
            dfT = dfT.replace("July", "জুলাই");
            dfT = dfT.replace("August", "আগষ্ট");
            dfT = dfT.replace("September", "সেপ্টেম্বর");
            dfT = dfT.replace("October", "অক্টোবর");
            dfT = dfT.replace("November", "নভেম্বর");
            dfT = dfT.replace("December", "ডিসেম্বর");

        dfT = converterClass.covertS(dfT);

        tvCurrentDate.setText(dfT);


        //////////////////////////////////////////////////////////////////////////
        tvCurrentLocation.setText(""+LocatString);

        SharedPreferences sharedPrefMM = getActivity().getSharedPreferences("mm",0);

        sharedPrefMM = getActivity().getSharedPreferences("mm",0);

        String kk = "0";
        SharedPreferences.Editor editor = sharedPrefMM.edit();
        editor.putString("key", kk);

        editor.commit();

        final SharedPreferences finalSharedPrefMM = sharedPrefMM;
        tvCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String kk = "1";
                SharedPreferences.Editor editor = finalSharedPrefMM.edit();
                editor.putString("key", kk);

                editor.commit();

                Intent go = new Intent(getContext(), EightDivisonActivity.class);
                startActivity(go);

            }
        });


        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String kk = "1";
                SharedPreferences.Editor editor = finalSharedPrefMM.edit();
                editor.putString("key", kk);

                editor.commit();

                Intent go = new Intent(getContext(), EightDivisonActivity.class);
                startActivity(go);

            }
        });








        tvNamajTimeNUmber.setText("");
        tvNamajSeshSomoy.setText(""+NamajerSeshTime);


        String matchString = tvCurrentNTime.getText().toString();




        if (matchString.equals("ফজর")){
            tvNamajTimeNUmber.setText(""+fojorTimeBangla);

            Heading_background.setImageResource(R.drawable.fajor_bg);


            Fjr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.timetable_shape));
            Jhr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Asr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Mgrb_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Isha_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));

        } else if (matchString.equals("যোহর")){
            tvNamajTimeNUmber.setText(""+johorTimeBangla);
            Heading_background.setImageResource(R.drawable.johor_bg);
            Fjr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Jhr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.timetable_shape));
            Asr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Mgrb_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Isha_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));

        } else if (matchString.equals("আসর")){
            tvNamajTimeNUmber.setText(""+asorTimeBangla);
            Heading_background.setImageResource(R.drawable.asor_bg);
            Fjr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Jhr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Asr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.timetable_shape));
            Mgrb_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Isha_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));

        } else if (matchString.equals("মাগরিব")){
            tvNamajTimeNUmber.setText(""+magribTimeBangla);
            Heading_background.setImageResource(R.drawable.magrib_bg);
            Fjr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Jhr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Asr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Mgrb_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.timetable_shape));
            Isha_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));

        } else if (matchString.equals("এশা")){
            tvNamajTimeNUmber.setText(""+eshaTimeBangla);
            Heading_background.setImageResource(R.drawable.esha_bg);
            Fjr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Jhr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Asr_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Mgrb_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.time_table_gray));
            Isha_stroke_bg.setBackground(getContext().getResources().getDrawable(R.drawable.timetable_shape));

        }



        if (tvNextTime.getText().toString().equals("নামাজ পড়ার নিষিদ্ধ সময় চলছে")){
            timetablebg.setBackground(getContext().getResources().getDrawable(R.drawable.red_bg));
        }


        return view;


    }



    private void dateCalForHizri(){


        int md;






        if ((hdcD-1)<=0){


            if((hdcM-1)<=0){

                hdcY=hdcY-1;
                hdcM=12;
                md = funcM(hdcM);

                hdcD = (hdcD+md)-1;
            }
            else{

                hdcM = hdcM-1;
                md = funcM(hdcM);
                hdcD = (hdcD+md)-1;
            }

        }
        else{

            hdcD=hdcD-1;
            hdcM=hdcM;
            hdcY=hdcY;
        }





    }


    private int funcM(int m){

        switch(m)
        {
            case 1:
                m = 31;
                break;

            case 2:
                m = 28;
                break;

            case 3:
                m = 31;
                break;

            case 4:
                m = 30;
                break;

            case 5:
                m = 31;
                break;

            case 6:
                m = 30;
                break;

            case 7:
                m = 31;
                break;

            case 8:
                m = 31;
                break;
            case 9:
                m = 30;
                break;

            case 10:
                m = 31;
                break;

            case 11:
                m = 30;
                break;

            case 12:
                m = 31;
                break;


            // operator doesn't match any case constant +, -, *, /
            default:
                m=30;

        }

        return m;
    }


}