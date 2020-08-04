package com.example.islamicappb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
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
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Locale;

import io.paperdb.Paper;


public class TimeTableFragment extends Fragment {

    TextView tvfojor, tvjohor, tvasor, tvmagrib, tvesha;
    TextView tvCurrentNTime, tvCurrentDate,tvCurrentArbiDate ,MidBorber;
    TextView tvCurrentLocation, tvNextTime,tvNamajTimeNUmber, tvNamajSeshSomoy;

    ImageButton ibFojor, ibJohor, ibAsor, ibMagrib, ibEsha;


    String text;

    String fojorTimeBangla,johorTimeBangla,asorTimeBangla,magribTimeBangla,eshaTimeBangla;

    String year, month, day;
    int ii;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    

    public TimeTableFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_timetable, container, false);


        Paper.init(getContext());

        tvfojor = view.findViewById(R.id.idFajr);
        tvjohor = view.findViewById(R.id.idJohor);
        tvasor = view.findViewById(R.id.idAsor);
        tvmagrib = view.findViewById(R.id.idMagrib);
        tvesha = view.findViewById(R.id.idIsha);

        tvCurrentNTime = view.findViewById(R.id.idCurrentNamajTime);
        tvCurrentDate = view.findViewById(R.id.idCurrentDate);
        tvCurrentArbiDate = view.findViewById(R.id.idCurrentDateArbi);
        MidBorber = view.findViewById(R.id.idMidBorder);

        tvCurrentLocation = view.findViewById(R.id.idCurrentLocation);
        tvNextTime = view.findViewById(R.id.idNextNamaj);
        tvNamajTimeNUmber = view.findViewById(R.id.idNamajTimeInNumber);
        tvNamajSeshSomoy = view.findViewById(R.id.idSeshSomoy);

        ibFojor = view.findViewById(R.id.idAlarmFojor);
        ibJohor = view.findViewById(R.id.idAlarmJohor);
        ibAsor = view.findViewById(R.id.idAlarmAsor);
        ibMagrib = view.findViewById(R.id.idAlarmMagrib);
        ibEsha = view.findViewById(R.id.idAlarmEsha);

        Locale locale = new Locale("bn");
        Locale.setDefault(locale);
//        Configuration config =
//                getContext().getResources().getConfiguration();
//        config.setLocale(locale);
//
//        createConfigurationContext(config);
        Calendar cl = Calendar.getInstance(locale);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy ");
        String dateTime = simpleDateFormat.format(cl.getTime());



        HijrahDate islamyDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR),cl.get(Calendar.MONTH)+1, cl.get(Calendar.DATE)));
            text = islamyDate.toString();
            text = text.replace("Hijrah-umalqura AH", "");




            ////////////////////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////////


            text = text.replace("0", "০");
            text = text.replace("1", "১");
            text = text.replace("2", "২");
            text = text.replace("3", "৩");
            text = text.replace("4", "৪");
            text = text.replace("5", "৫");
            text = text.replace("6", "৬");
            text = text.replace("7", "৭");
            text = text.replace("8", "৮");
            text = text.replace("9", "৯");


            //////////////////////////////////////////////////////

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


        }

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.O) {
            text = "Its only for android orio";
           tvCurrentArbiDate.setVisibility(View.GONE);
           MidBorber.setVisibility(View.GONE);

        }




        BaseActivity activity = (BaseActivity) getActivity();
        String fojorerTime = activity.fojorData();
        String johorerTime = activity.johorData();
        String asorerTime = activity.asorData();
        String magriberTime = activity.magribData();
        String esharTime = activity.eshaData();

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

        for (int i = 0; i < fojorerTime.length(); i++) {
            array[i] = fojorerTime.charAt(i);
        }
        fojorTimeBangla = ""+array[0]+""+array[1]+""+array[2]+""+array[3];

        String SfHour = ""+array[0];
        String SfMinute = ""+array[2]+""+array[3];
        final int fHour = Integer.parseInt(SfHour);
        final int fMinute = Integer.parseInt(SfMinute);


        fojorTimeBangla = fojorTimeBangla.replace("0", "০");
        fojorTimeBangla = fojorTimeBangla.replace("1", "১");
        fojorTimeBangla = fojorTimeBangla.replace("2", "২");
        fojorTimeBangla = fojorTimeBangla.replace("3", "৩");
        fojorTimeBangla = fojorTimeBangla.replace("4", "৪");
        fojorTimeBangla = fojorTimeBangla.replace("5", "৫");
        fojorTimeBangla = fojorTimeBangla.replace("6", "৬");
        fojorTimeBangla = fojorTimeBangla.replace("7", "৭");
        fojorTimeBangla = fojorTimeBangla.replace("8", "৮");
        fojorTimeBangla = fojorTimeBangla.replace("9", "৯");





        ////johor time bangla
        Character [] johorarray= new Character[johorerTime.length()];

        for (int i = 0; i < johorerTime.length(); i++) {
            johorarray[i] = johorerTime.charAt(i);
        }
        String txtJohor = ""+johorarray[0]+""+johorarray[1];
        int jInt = Integer.parseInt(txtJohor);
        int jhInt = jInt;
        if (jInt>12){
            jInt = jInt - 12;
        }

        johorTimeBangla = ""+jInt+""+johorarray[2]+""+johorarray[3]+""+johorarray[4];



        //String SjHour = ""+array[0]+""+array[1];
        String SjMinute = ""+johorarray[3]+""+johorarray[4];

        final int jHour = jhInt;
        final int jMinute = Integer.parseInt(SjMinute);

        johorTimeBangla= johorTimeBangla.replace("0", "০");
        johorTimeBangla= johorTimeBangla.replace("1", "১");
        johorTimeBangla= johorTimeBangla.replace("2", "২");
        johorTimeBangla= johorTimeBangla.replace("3", "৩");
        johorTimeBangla= johorTimeBangla.replace("4", "৪");
        johorTimeBangla= johorTimeBangla.replace("5", "৫");
        johorTimeBangla= johorTimeBangla.replace("6", "৬");
        johorTimeBangla= johorTimeBangla.replace("7", "৭");
        johorTimeBangla= johorTimeBangla.replace("8", "৮");
        johorTimeBangla= johorTimeBangla.replace("9", "৯");




        ////Asor time bangla
        Character [] asorarray= new Character[asorerTime.length()];

        for (int i = 0; i < asorerTime.length(); i++) {
            asorarray[i] = asorerTime.charAt(i);
        }

        String txtAsor = ""+asorarray[0]+""+asorarray[1];
        int aInt = Integer.parseInt(txtAsor);
        int asInt = aInt;
        if (aInt>12){
            aInt = aInt - 12;
        }

        asorTimeBangla = ""+aInt+""+asorarray[2]+""+asorarray[3]+""+asorarray[4];


        //String SfHour = ""+array[0]+""+array[1];
        String SaMinute = ""+asorarray[3]+""+asorarray[4];
        final int aHour = asInt;
        final int aMinute = Integer.parseInt(SaMinute);

       // String asorTimeBangla = ""+asorarray[0]+""+asorarray[1]+""+asorarray[2]+""+asorarray[3]+""+asorarray[4];
        asorTimeBangla= asorTimeBangla.replace("0", "০");
        asorTimeBangla= asorTimeBangla.replace("1", "১");
        asorTimeBangla= asorTimeBangla.replace("2", "২");
        asorTimeBangla= asorTimeBangla.replace("3", "৩");
        asorTimeBangla= asorTimeBangla.replace("4", "৪");
        asorTimeBangla= asorTimeBangla.replace("5", "৫");
        asorTimeBangla= asorTimeBangla.replace("6", "৬");
        asorTimeBangla= asorTimeBangla.replace("7", "৭");
        asorTimeBangla= asorTimeBangla.replace("8", "৮");
        asorTimeBangla= asorTimeBangla.replace("9", "৯");


        ////magrib time bangla
        Character [] magribarray= new Character[magriberTime.length()];

        for (int i = 0; i < magriberTime.length(); i++) {
            magribarray[i] = magriberTime.charAt(i);
        }

        String txtMagrib = ""+magribarray[0]+""+magribarray[1];
        int mInt = Integer.parseInt(txtMagrib);
        int mgInt = mInt;
        if (mInt>12){
            mInt = mInt - 12;
        }

      magribTimeBangla = ""+mInt+""+magribarray[2]+""+magribarray[3]+""+magribarray[4];

        //String SfHour = ""+array[0]+""+array[1];
        String SmMinute = ""+magribarray[3]+""+magribarray[4];
        final int mHour = mgInt;
        final int mMinute = Integer.parseInt(SmMinute);


        magribTimeBangla= magribTimeBangla.replace("0", "০");
        magribTimeBangla= magribTimeBangla.replace("1", "১");
        magribTimeBangla= magribTimeBangla.replace("2", "২");
        magribTimeBangla= magribTimeBangla.replace("3", "৩");
        magribTimeBangla= magribTimeBangla.replace("4", "৪");
        magribTimeBangla= magribTimeBangla.replace("5", "৫");
        magribTimeBangla= magribTimeBangla.replace("6", "৬");
        magribTimeBangla= magribTimeBangla.replace("7", "৭");
        magribTimeBangla= magribTimeBangla.replace("8", "৮");
        magribTimeBangla= magribTimeBangla.replace("9", "৯");

        ////Esha time bangla
        Character [] eshaarray= new Character[esharTime.length()];

        for (int i = 0; i < esharTime.length(); i++) {
            eshaarray[i] = esharTime.charAt(i);
        }

        String txtEsha = ""+eshaarray[0]+""+eshaarray[1];
        int eInt = Integer.parseInt(txtEsha);
        int ehInt = eInt;
        if (eInt>12){
            eInt = eInt - 12;
        }

        eshaTimeBangla = ""+eInt+""+eshaarray[2]+""+eshaarray[3]+""+eshaarray[4];

       // String SfHour = ""+array[0]+""+array[1];
        String SeMinute = ""+eshaarray[3]+""+eshaarray[4];
        final int eHour = ehInt;
        final int eMinute = Integer.parseInt(SeMinute);
        //String eshaTimeBangla = ""+eshaarray[0]+""+eshaarray[1]+""+eshaarray[2]+""+eshaarray[3]+""+eshaarray[4];
        eshaTimeBangla= eshaTimeBangla.replace("0", "০");
        eshaTimeBangla= eshaTimeBangla.replace("1", "১");
        eshaTimeBangla= eshaTimeBangla.replace("2", "২");
        eshaTimeBangla= eshaTimeBangla.replace("3", "৩");
        eshaTimeBangla= eshaTimeBangla.replace("4", "৪");
        eshaTimeBangla= eshaTimeBangla.replace("5", "৫");
        eshaTimeBangla= eshaTimeBangla.replace("6", "৬");
        eshaTimeBangla= eshaTimeBangla.replace("7", "৭");
        eshaTimeBangla= eshaTimeBangla.replace("8", "৮");
        eshaTimeBangla= eshaTimeBangla.replace("9", "৯");

        tvfojor.setText("০"+fojorTimeBangla);
        tvjohor.setText(johorTimeBangla);
        tvasor.setText("০"+asorTimeBangla);
        tvmagrib.setText("০"+magribTimeBangla);
        tvesha.setText("০"+eshaTimeBangla);

        Paper.book().write("FajorPB",tvfojor.getText().toString());
        Paper.book().write("JohorPB",tvjohor.getText().toString());
        Paper.book().write("AsorPB",tvasor.getText().toString());
        Paper.book().write("MagribPB",tvmagrib.getText().toString());
        Paper.book().write("IshaPB",tvesha.getText().toString());


//        Intent intent = new Intent(getContext(), WidgetProvider.class);
//        intent.putExtra("hello","102");
//     //   intent.putExtra(NextActivity.PLAYERS, players);
//        startActivity(intent);

        ibFojor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "ফজরের ওয়াক্ত শুরু");
                i.putExtra(AlarmClock.EXTRA_HOUR, fHour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, fMinute);
                Toast.makeText(getContext(), "সম্পূর্ন হয়েছে", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "সম্পূর্ন হয়েছে", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "সম্পূর্ন হয়েছে", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "সম্পূর্ন হয়েছে", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "সম্পূর্ন হয়েছে", Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });


        tvNextTime.setText(""+BOrPNamajTime);
        tvCurrentNTime.setText(""+CurrentNamajTime);
        tvCurrentDate.setText(dateTime);
      //  tvCurrentArbiDate.setText(text);


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

                Intent go = new Intent(getContext(),EightDivisonActivity.class);
                startActivity(go);
                
            }
        });

        ////////////////////////////////////////////////////////////////////////////






        tvNamajTimeNUmber.setText("");
        tvNamajSeshSomoy.setText(""+NamajerSeshTime);


        String matchString = tvCurrentNTime.getText().toString();



        if (matchString.equals("ফজর")){
            tvNamajTimeNUmber.setText(""+fojorTimeBangla);
        } else if (matchString.equals("যোহর")){
            tvNamajTimeNUmber.setText(""+johorTimeBangla);
        } else if (matchString.equals("আসর")){
            tvNamajTimeNUmber.setText(""+asorTimeBangla);
        } else if (matchString.equals("মাগরিব")){
            tvNamajTimeNUmber.setText(""+magribTimeBangla);
        } else if (matchString.equals("এশা")){
            tvNamajTimeNUmber.setText(""+eshaTimeBangla);
        }

        if (matchString.equals("পরবর্তী নামাজ যোহর")){
            tvNamajTimeNUmber.setText(""+johorTimeBangla);
        }
        if (matchString.equals("পরবর্তী নামাজ মাগরিব")){
            tvNamajTimeNUmber.setText(""+magribTimeBangla);
        }


        return view;


    }

    public String getFojorTimeBangla(){

        return fojorTimeBangla;
    }
    public String getJohorTimeBangla(){

        return johorTimeBangla;
    }
    public String getAsorTimeBangla(){

        return asorTimeBangla;
    }
    public String getMagribTimeBangla(){

        return magribTimeBangla;
    }
    public String getEshaTimeBangla(){

        return eshaTimeBangla;
    }

}