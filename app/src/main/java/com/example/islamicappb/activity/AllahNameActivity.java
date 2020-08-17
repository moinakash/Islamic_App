package com.example.islamicappb.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.islamicappb.pojo_classes.AllahNamePojoClass;
import com.example.islamicappb.DatabaseHelper;
import com.example.islamicappb.R;

import java.util.ArrayList;
import java.util.List;

public class AllahNameActivity extends AppCompatActivity {

    ListView list;
    List<AllahNamePojoClass> allahNamePojoClassList;

    AllahNameAdapter allahNameAdapter;
    DatabaseHelper db;
    MediaPlayer mp;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_allah_name);

        ToolBar();
        list = findViewById( R.id.idAllahNamelist);
        db = new DatabaseHelper(this);
        allahNamePojoClassList = new ArrayList();

        fetchData();
        loadData();

    }

    public void fetchData()
    {

        db = new DatabaseHelper(this);
        try {
            db.createDataBase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadData(){

      Cursor cursor = db.ShowAllahName();

        if(cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){

                allahNamePojoClassList.add(new AllahNamePojoClass(""+cursor.getString(2),
                        ""+cursor.getString(1), ""+cursor.getString(3),
                        ""+cursor.getString(4)));


            }
        }


        allahNameAdapter = new AllahNameAdapter(this,R.layout.allahname_item_layout, allahNamePojoClassList);
        list.setAdapter(allahNameAdapter);


    }


    public class AllahNameAdapter extends ArrayAdapter<AllahNamePojoClass> {

        private List<AllahNamePojoClass> allahNamePojoClassList;
        private Context context;

        Thread splashTread;

        public AllahNameAdapter(@NonNull Context context, int textViewResourceId, List<AllahNamePojoClass> allahNamePojoClassList) {
            super(context, textViewResourceId, allahNamePojoClassList);
            this.context = context;
            this.allahNamePojoClassList = allahNamePojoClassList;
        }


        @Override
        public int getCount() {
            return allahNamePojoClassList.size();
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View customView= convertView;
            if (customView==null)
            {
                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                customView = vi.inflate(R.layout.allahname_item_layout, null);


            }

            final AllahNamePojoClass allahNamePojoClass = allahNamePojoClassList.get(position);


            if(allahNamePojoClass !=null)
            {

                TextView SuraName = (TextView) customView.findViewById(R.id.idAllah_banglaName);
                SuraName.setText(allahNamePojoClass.getAllah_name_bangla());

                TextView SuraNameBangla = (TextView) customView.findViewById(R.id.idAllah_banglaMeaning);
                SuraNameBangla.setText(allahNamePojoClass.getAllah_name_meaning());

                TextView SuraNameMeaning = (TextView) customView.findViewById(R.id.idAllah_ArbiName);
                SuraNameMeaning.setText(allahNamePojoClass.getAllah_name_arbi());

                TextView AllahName_number = (TextView) customView.findViewById(R.id.name_number);
                AllahName_number.setText(allahNamePojoClass.getAllah_name_number());


                final ImageButton PLayButton = (ImageButton) customView.findViewById(R.id.idPlay_button);
                final ImageButton PLayButton2 = (ImageButton) customView.findViewById(R.id.idPlay_button2);
                PLayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final int [] audios = {R.raw.ar_rahman_01,R.raw.ar_rahim_02,R.raw.al_malik_03,R.raw.al_quddus_04,R.raw.as_salam_05,R.raw.al_mumin_06,R.raw.al_muhaymin_07,R.raw.al_aziz_08,R.raw.al_jabbar_09,R.raw.al_mutakabbir_10,
                                R.raw.al_khaliq_11,R.raw.al_bari_12,R.raw.al_musawwir_13,R.raw.al_ghaffar_14,R.raw.al_qahhar_15,R.raw.al_wahhab_16,R.raw.ar_razzaq_17,R.raw.al_fattah_18,R.raw.al_alim_19,R.raw.al_qabid_20,
                                R.raw.al_basit_21,R.raw.al_khafid_22,R.raw.ar_rafi_23,R.raw.al_muizz_24,R.raw.al_mudhill_25,R.raw.as_sami_26,R.raw.al_basir_27,R.raw.al_hakam_28,R.raw.al_adl_29,R.raw.al_latif_30,
                                R.raw.al_khabir_31,R.raw.al_halim_32,R.raw.al_azim_33,R.raw.al_ghafur_34,R.raw.ash_shakur_35,R.raw.al_ali_36,R.raw.al_kabir_37,R.raw.al_hafiz_38,R.raw.al_muqit_39,R.raw.al_hasib_40,
                                R.raw.al_jalil_41,R.raw.al_karim_42,R.raw.ar_raqib_43,R.raw.al_mujib_44,R.raw.al_wasi_45,R.raw.al_hakim_46,R.raw.al_wadud_47,R.raw.al_majid_48,R.raw.al_baith_49,R.raw.ash_shahid_50,
                                R.raw.al_haqq_51,R.raw.al_wakil_52,R.raw.al_qawi_53,R.raw.al_matin_54,R.raw.al_wali_55,R.raw.al_hamid_56,R.raw.al_muhsi_57,R.raw.al_mubdi_58,R.raw.al_muid_59,R.raw.al_muhyi_60,
                                R.raw.al_mumit_61,R.raw.al_hayy_62,R.raw.al_qayyum_63,R.raw.al_wajid_64,R.raw.al_majid_65,R.raw.al_wahid_66,R.raw.al_ahad_67,R.raw.as_samad_68,R.raw.al_qadir_69,R.raw.al_muqtadir_70,
                                R.raw.al_muqaddim_71,R.raw.al_muakhkhir_72,R.raw.al_awwal_73,R.raw.al_akhir_74,R.raw.az_zahir_75,R.raw.al_batin_76,R.raw.al_wali_77,R.raw.al_mutaali_78,R.raw.al_barr_79,R.raw.at_tawwab_80,
                                R.raw.al_muntaqim_81,R.raw.al_afuw_82,R.raw.ar_rauf_83,R.raw.malik_ul_mulk_84,R.raw.dhul_jalaal_wal_ikraam_85,R.raw.al_muqsit_86,R.raw.al_jame_87,R.raw.al_ghani_88,R.raw.al_mughni_89,R.raw.al_mani_90,
                                R.raw.ad_darr_91,R.raw.an_nafi_92,R.raw.an_nur_93,R.raw.al_hadi_94,R.raw.al_badi_95,R.raw.al_baqi_96,R.raw.al_warith_97,R.raw.ar_rashid_98,R.raw.as_sabur_99};



                        mp = MediaPlayer.create(AllahNameActivity.this, audios[position]);

                        try {
                            if (mp.isPlaying()){
                                mp.stop();
                                mp.release();

                                //problem in release
                                mp = MediaPlayer.create(getContext(),audios[position]);
                            } mp.start();

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        PLayButton.setVisibility(View.GONE);
                        PLayButton2.setVisibility(View.VISIBLE);

                          final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                PLayButton2.setVisibility(View.GONE);
                                   PLayButton.setVisibility(View.VISIBLE);
     }
                        }, 3000);


                    }


                });


            }

            return customView;


        }


    }

    private void ToolBar() {

        mToolbar = findViewById( R.id.AllahName_toolbar );
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);

         mTitle.setText("আল্লাহর গুণবাচক নাম সমূহ");
        setSupportActionBar( mToolbar );

        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }




}

