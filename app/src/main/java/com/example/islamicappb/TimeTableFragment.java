package com.example.islamicappb;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Locale;


public class TimeTableFragment extends Fragment {

    TextView tvfojor, tvjohor, tvasor, tvmagrib, tvesha;
    TextView tvCurrentNTime, tvCurrentDate,tvCurrentArbiDate;
    TextView tvCurrentLocation, tvNextTime,tvNamajTimeNUmber, tvNamajSeshSomoy;


    String text;


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



        tvfojor = view.findViewById(R.id.idFajr);
        tvjohor = view.findViewById(R.id.idJohor);
        tvasor = view.findViewById(R.id.idAsor);
        tvmagrib = view.findViewById(R.id.idMagrib);
        tvesha = view.findViewById(R.id.idIsha);

        tvCurrentNTime = view.findViewById(R.id.idCurrentNamajTime);
        tvCurrentDate = view.findViewById(R.id.idCurrentDate);
        tvCurrentArbiDate = view.findViewById(R.id.idCurrentDateArbi);

        tvCurrentLocation = view.findViewById(R.id.idCurrentLocation);
        tvNextTime = view.findViewById(R.id.idNextNamaj);
        tvNamajTimeNUmber = view.findViewById(R.id.idNamajTimeInNumber);
        tvNamajSeshSomoy = view.findViewById(R.id.idSeshSomoy);

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
            tvCurrentArbiDate.setText(text+" হিঃ");

        }

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.O) {
            text = "Its only for android orio";
           tvCurrentArbiDate.setVisibility(View.GONE);

        }




        BaseActivity activity = (BaseActivity) getActivity();
        String fojorerTime = activity.fojorData();
        String johorerTime = activity.johorData();
        String asorerTime = activity.asorData();
        String magriberTime = activity.magribData();
        String esharTime = activity.eshaData();

        String CurrentNamajTime = activity.currentNamajTime();


        tvfojor.setText(fojorerTime);
        tvjohor.setText(johorerTime);
        tvasor.setText(asorerTime);
        tvmagrib.setText(magriberTime);
        tvesha.setText(esharTime);


        tvCurrentNTime.setText(""+CurrentNamajTime);
        tvCurrentDate.setText(dateTime);
      //  tvCurrentArbiDate.setText(text);

        tvCurrentLocation.setText("");
        tvNextTime.setText("");
        tvNamajTimeNUmber.setText("");
        tvNamajSeshSomoy.setText("");

        return view;


    }
}