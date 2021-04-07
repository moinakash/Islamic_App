package com.rdtl.ad_din.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rdtl.ad_din.Adapters.Ramjan_chat_adapter;
import com.rdtl.ad_din.R;
import com.rdtl.ad_din.activity.BaseActivity;
import com.rdtl.ad_din.activity.OurNotificationActivity;
import com.rdtl.ad_din.activity.RamajanDetailsActivity;
import com.rdtl.ad_din.pojo_classes.ConverterClass;
import com.rdtl.ad_din.pojo_classes.FullLengthListView;
import com.rdtl.ad_din.pojo_classes.WaktoTimeMaintaining;
import com.rdtl.ad_din.pojo_classes.ramjan_chart_modelClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RamjanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RamjanFragment extends Fragment {



    private AlphaAnimation buttonClick;

    Button btnRojarNiot, btnRojarFojilot, btnIftererSunnot, btnSyukriya, btnCharkaj, btnTarabi, btnKaronChara, btnrojaVanga, btnKaffara, btnvulDharona;
    Button btnPastAndMeswak, btnRomjanKRohomot, btnRojayDhan, btnJannatJahannam, btnFitra, btnRojarNirdesh, btnItekaf, btnRojobMash;

    ImageView ivBell, ivShine, ivUpperFrame;

    FullLengthListView RahmatList,MagferatList,NajatList;

    TextView tvUprTime, tvLwrTime, tvTodayRamjan, tvThisYear;

    ArrayList<ramjan_chart_modelClass> ramjan_chart_modelClasses_List;
    ArrayList<ramjan_chart_modelClass> ramjan_chart_modelClasses_List2;
    ArrayList<ramjan_chart_modelClass> ramjan_chart_modelClasses_List3;

    Intent in;

    String lvV;
    SharedPreferences prefAudio;

    ConverterClass converterClass;
    WaktoTimeMaintaining wtm;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RamjanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RamjanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RamjanFragment newInstance(String param1, String param2) {
        RamjanFragment fragment = new RamjanFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ramjan, container, false);



        buttonClick = new AlphaAnimation(1F, 0.8F);

        RahmatList = view.findViewById(R.id.idRahmatList);
        MagferatList = view.findViewById(R.id.idMagferatList);
        NajatList = view.findViewById(R.id.idNajatList);


        ivBell = view.findViewById(R.id.idBell);

        ivUpperFrame = view.findViewById(R.id.idUpperFrame);

        btnRojarNiot = view.findViewById(R.id.idRojarNiotBtn);
        btnRojarFojilot = view.findViewById(R.id.idRojarFojilot);
        btnIftererSunnot = view.findViewById(R.id.idIfterASunnotKhabar);
        btnSyukriya = view.findViewById(R.id.idSokriya);
        btnCharkaj = view.findViewById(R.id.idRojarCharkaj);
        btnTarabi = view.findViewById(R.id.idTarabirNioim);
        btnKaronChara = view.findViewById(R.id.idKaronChara);
        btnrojaVanga = view.findViewById(R.id.idRojavanga);
        btnKaffara = view.findViewById(R.id.idRojarkaffara);
        btnvulDharona = view.findViewById(R.id.idVulDharona);
        btnPastAndMeswak = view.findViewById(R.id.idPastAndbrush);
        btnRomjanKRohomot = view.findViewById(R.id.idRomankeRohomot);
        btnRojayDhan = view.findViewById(R.id.idRojayDhan);
        btnJannatJahannam = view.findViewById(R.id.idJannatJahannam);
        btnFitra = view.findViewById(R.id.idFitrah);
        btnRojarNirdesh = view.findViewById(R.id.idRojayNirdesh);
        btnItekaf = view.findViewById(R.id.idItekaf);
        btnRojobMash = view.findViewById(R.id.idRojonMasherBorkot);


        tvUprTime = view.findViewById(R.id.idAjkerUpperPartTime);
        tvLwrTime = view.findViewById(R.id.idAjkerLowerPartTime);
        tvTodayRamjan = view.findViewById(R.id.idTodayRamjan);
        tvThisYear = view.findViewById(R.id.idThisYear);


        converterClass = new ConverterClass(getContext());
        wtm = new WaktoTimeMaintaining(getContext());


        listviews();
        timesForSheheri();





        ivBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), OurNotificationActivity.class);
                startActivity(in);
            }
        });


        onclickButtons();

        return view;
    }


    private void listviews() {





        ramjan_chart_modelClasses_List = new ArrayList<ramjan_chart_modelClass>();

        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(1,"০১","১৪ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(2,"০২","১৫ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(3,"০৩","১৬ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(4,"০৪","১৭ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(5,"০৫","১৮ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(6,"০৬","১৯ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(7,"০৭","২০ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(8,"০৮","২১ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(9,"০৯","২২ এপ্রিল"));
        ramjan_chart_modelClasses_List.add(new ramjan_chart_modelClass(10,"১০","২৩ এপ্রিল"));


        Ramjan_chat_adapter ramjan_chat_adapter = new Ramjan_chat_adapter(getContext(), R.layout.chart_items, ramjan_chart_modelClasses_List);

        RahmatList.setAdapter(ramjan_chat_adapter);










        ramjan_chart_modelClasses_List2 = new ArrayList<ramjan_chart_modelClass>();

        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(1,"১১","২৪ এপ্রিল"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(2,"১২","২৫ এপ্রিল"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(3,"১৩","২৬ এপ্রিল"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(4,"১৪","২৭ এপ্রিল"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(5,"১৫","২৮ এপ্রিল"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(6,"১৬","২৯ এপ্রিল"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(7,"১৭","৩০ এপ্রিল"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(8,"১৮","০১ মে"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(9,"১৯","০২ মে"));
        ramjan_chart_modelClasses_List2.add(new ramjan_chart_modelClass(10,"২০","০৩ মে"));


        Ramjan_chat_adapter ramjan_chat_adapter2 = new Ramjan_chat_adapter(getContext(), R.layout.chart_items, ramjan_chart_modelClasses_List2);

        MagferatList.setAdapter(ramjan_chat_adapter2);




        ramjan_chart_modelClasses_List3 = new ArrayList<ramjan_chart_modelClass>();

        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(1,"২১","০৪ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(2,"২২","০৫ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(3,"২৩","০৬ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(4,"২৪","০৭ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(5,"২৫","০৮ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(6,"২৬","০৯ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(7,"২৭","১০ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(8,"২৮","১১ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(9,"২৯","১২ মে"));
        ramjan_chart_modelClasses_List3.add(new ramjan_chart_modelClass(10,"৩০","১৩ মে"));


        Ramjan_chat_adapter ramjan_chat_adapter3 = new Ramjan_chat_adapter(getContext(), R.layout.chart_items, ramjan_chart_modelClasses_List3);

        NajatList.setAdapter(ramjan_chat_adapter3);





    }


    private void onclickButtons(){


        btnRojarNiot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojarNiotBtn");
                startActivity(in);
                btnRojarNiot.startAnimation(buttonClick);
            }
        });
        btnRojarFojilot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojarFojilot");
                startActivity(in);
                btnRojarFojilot.startAnimation(buttonClick);
            }
        });
        btnIftererSunnot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webIfterASunnotKhabar");
                startActivity(in);
                btnIftererSunnot.startAnimation(buttonClick);
            }
        });
        btnSyukriya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webSokriya");
                startActivity(in);
                btnSyukriya.startAnimation(buttonClick);
            }
        });
        btnCharkaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojarCharkaj");
                startActivity(in);
                btnCharkaj.startAnimation(buttonClick);
            }
        });
        btnTarabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webTarabirNioim");
                startActivity(in);
                btnTarabi.startAnimation(buttonClick);
            }
        });
        btnKaronChara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webKaronChara");
                startActivity(in);
                btnKaronChara.startAnimation(buttonClick);
            }
        });
        btnrojaVanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojavanga");
                startActivity(in);
                btnrojaVanga.startAnimation(buttonClick);
            }
        });
        btnKaffara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojarkaffara");
                startActivity(in);
                btnKaffara.startAnimation(buttonClick);
            }
        });
        btnvulDharona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webVulDharona");
                startActivity(in);
                btnvulDharona.startAnimation(buttonClick);
            }
        });
        btnPastAndMeswak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webPastAndbrush");
                startActivity(in);
                btnPastAndMeswak.startAnimation(buttonClick);
            }
        });
        btnRomjanKRohomot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRomankeRohomot");
                startActivity(in);
                btnRomjanKRohomot.startAnimation(buttonClick);
            }
        });
        btnRojayDhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojayDhan");
                startActivity(in);
                btnRojayDhan.startAnimation(buttonClick);
            }
        });
        btnJannatJahannam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webJannatJahannam");
                startActivity(in);
                btnJannatJahannam.startAnimation(buttonClick);
            }
        });
        btnFitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webFitrah");
                startActivity(in);
                btnFitra.startAnimation(buttonClick);
            }
        });
        btnRojarNirdesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojayNirdesh");
                startActivity(in);
                btnRojarNirdesh.startAnimation(buttonClick);
            }
        });
        btnItekaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webItekaf");
                startActivity(in);
                btnItekaf.startAnimation(buttonClick);
            }
        });
        btnRojobMash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(getContext(), RamajanDetailsActivity.class);
                in.putExtra("RequiredWeb", "webRojonMasherBorkot");
                startActivity(in);
                btnRojobMash.startAnimation(buttonClick);
            }
        });

    }



    private void timesForSheheri(){


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ");
        SimpleDateFormat sstt = new SimpleDateFormat("mm");


        String dateTime = simpleDateFormat.format(calendar.getTime());
        dateTime = dateTime.replace(" ", "");
        int cTime = Integer.parseInt(dateTime);


        Toast.makeText(getContext(), "CTimeeeee"+cTime, Toast.LENGTH_SHORT).show();

        if (cTime>1600 && cTime<2000){
            ivBell.setBackgroundResource(R.drawable.bell_with_red);
        }
        if (cTime>400 && cTime <700){
            ivBell.setBackgroundResource(R.drawable.bell_with_red);
        }


        //cTime = Integer.parseInt(dateTime);



        SharedPreferences prefForRamjan;
        prefForRamjan = getActivity().getSharedPreferences("Ramjan",0);
        String arabikYr = prefForRamjan.getString("arabikyr","১৪৪২");
        String arabikDy = prefForRamjan.getString("arabikdy","১");
        String engYr = prefForRamjan.getString("engyr","২০২১");


        tvTodayRamjan.setText("আজ " + arabikDy + " তম \nরমজান");
        tvThisYear.setText("মাহে রমজান\n"+converterClass.covertS(engYr)+" ("+arabikYr+")");


        BaseActivity activity = (BaseActivity) getActivity();
        String fojorerTime = wtm.sheheritimewithsubm(""+activity.fojorData());


        Character [] array= new Character[fojorerTime.length()];

        for (int i = 0; i < fojorerTime.length(); i++) {
            array[i] = fojorerTime.charAt(i);
        }
        String fojorTimeBangla = ""+array[0]+""+array[1]+""+array[2]+""+array[3];




        fojorTimeBangla = converterClass.covertS(fojorTimeBangla);






        String magriberTime = wtm.mtimewithaddm(activity.magribData());

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

        String magribTimeBangla = ""+mInt+""+magribarray[2]+""+magribarray[3]+""+magribarray[4];


        String SmMinute = ""+magribarray[3]+""+magribarray[4];
        final int mHour = mgInt;
        final int mMinute = Integer.parseInt(SmMinute);


        magribTimeBangla = converterClass.covertS(magribTimeBangla);



        if (cTime<=1130){
            tvUprTime.setText("আজকের সেহরির শেষ সময় \n"+fojorTimeBangla);
            tvLwrTime.setText("আজকের ইফতারের সময় \n"+magribTimeBangla);
        }else {
            tvLwrTime.setText("পরবর্তী সেহরির শেষ সময় \n"+fojorTimeBangla);
            tvUprTime.setText("আজকের ইফতারের সময় \n"+magribTimeBangla);
        }





    }





//    @Override
//    public void onClick(View v) {
//        Intent in;
//
//        switch(v.getId()){
//            case R.id.idRojarNiotBtn:
//
//                break;
//            case R.id.idRojarFojilot:
//
//                break;
//            case R.id.idIfterASunnotKhabar:
//
//                break;
//            case R.id.idSokriya:
//
//                break;
//            case R.id.idRojarCharkaj:
//
//                break;
//            case R.id.idTarabirNioim:
//
//                break;
//            case R.id.idKaronChara:
//
//                break;
//            case R.id.idRojavanga:
//
//                break;
//            case R.id.idRojarkaffara:
//
//                break;
//            case R.id.idVulDharona:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webVulDharona");
//                startActivity(in);
//                btnvulDharona.startAnimation(buttonClick);
//                break;
//            case R.id.idPastAndbrush:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webPastAndbrush");
//                startActivity(in);
//                btnPastAndMeswak.startAnimation(buttonClick);
//                break;
//            case R.id.idRomankeRohomot:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webRomankeRohomot");
//                startActivity(in);
//                btnRomjanKRohomot.startAnimation(buttonClick);
//                break;
//            case R.id.idRojayDhan:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webRojayDhan");
//                startActivity(in);
//                btnRojayDhan.startAnimation(buttonClick);
//                break;
//            case R.id.idJannatJahannam:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webJannatJahannam");
//                startActivity(in);
//                btnJannatJahannam.startAnimation(buttonClick);
//                break;
//            case R.id.idFitrah:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webFitrah");
//                startActivity(in);
//                btnFitra.startAnimation(buttonClick);
//                break;
//            case R.id.idRojayNirdesh:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webRojayNirdesh");
//                startActivity(in);
//                btnRojarNirdesh.startAnimation(buttonClick);
//                break;
//            case R.id.idItekaf:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webItekaf");
//                startActivity(in);
//                btnItekaf.startAnimation(buttonClick);
//                break;
//            case R.id.idRojonMasherBorkot:
//                in = new Intent(getContext(), RamajanDetailsActivity.class);
//                in.putExtra("RequiredWeb", "webRojonMasherBorkot");
//                startActivity(in);
//                btnRojobMash.startAnimation(buttonClick);
//                break;
//            default:
//                break;
//        }
//    }

}