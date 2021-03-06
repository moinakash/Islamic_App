package com.rdtl.ad_din.activity;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdtl.ad_din.fragments.CompusFragment;
import com.rdtl.ad_din.fragments.DoaDurudFragment;
import com.rdtl.ad_din.fragments.QuranMajidFragment;
import com.rdtl.ad_din.R;
import com.rdtl.ad_din.fragments.TasbihFragment;
import com.rdtl.ad_din.fragments.TimeTableFragment;
import com.rdtl.ad_din.pojo_classes.ConverterClass;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseActivity extends AppCompatActivity {

    ChipNavigationBar BottomNav;
    FragmentManager fragmentManager;

    ConverterClass converterClass;




     String myString2;

    String fajarTime, johorTime, asorTime, magribTime, eshaTime, sunriseTime, nextnamaj, MiddleString;
    String fajarTimeR, johorTimeR, asorTimeR, magribTimeR, eshaTimeR, sunriseTimeR, nextnamajR, BorPNamaj, seShTime, LocString;

    int  cTime, fTime, jTime, aTime, mTime, eTime, sTime;

    int test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base);



        converterClass = new ConverterClass(this);


        onCreateM();
    }


    public void onCreateM(){


        BottomNav = findViewById(R.id.bottom_nav);

        Fragment fragment = new TimeTableFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();



        fajarTime = getIntent().getExtras().getString("fajor");
        johorTime = getIntent().getExtras().getString("johor");
        asorTime = getIntent().getExtras().getString("asor");
        magribTime = getIntent().getExtras().getString("magrib");
        eshaTime = getIntent().getExtras().getString("esha");
        sunriseTime = getIntent().getExtras().getString("sunrise");
        LocString = getIntent().getExtras().getString("Location");



        fajarTimeR = fajarTime.replace(":", "");
        fTime = Integer.parseInt(fajarTimeR);
        fTime= fTime/100;
        test = fTime;





        johorTimeR = johorTime.replace(":", "");
        jTime = Integer.parseInt(johorTimeR);
        jTime= jTime/100;


        asorTimeR = asorTime.replace(":", "");
        aTime = Integer.parseInt(asorTimeR);
        aTime= aTime/100;


        magribTimeR = magribTime.replace(":", "");
        mTime = Integer.parseInt(magribTimeR);
        mTime= mTime/100;


        eshaTimeR = eshaTime.replace(":", "");
        eTime = Integer.parseInt(eshaTimeR);
        eTime= eTime/100;

        sunriseTimeR = sunriseTime.replace(":", "");
        sTime = Integer.parseInt(sunriseTimeR);
        sTime= sTime/100;


////////////////////////////////////////////////////////////////////////////////////////////////////////

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ");


        String dateTime = simpleDateFormat.format(calendar.getTime());


        dateTime = dateTime.replace(" ", "");


        cTime = Integer.parseInt(dateTime);






        if (cTime>=fTime && cTime<sTime){
            BorPNamaj= "বর্তমান নামাজ ফজর";
            nextnamaj= "ফজর";


            ////////sesh time fojor

            String fojorSeshTimeTxt = String.valueOf(sTime);

            Character [] sesh_time_fojor_array= new Character[fojorSeshTimeTxt.length()];

            for (int i = 0; i < fojorSeshTimeTxt.length(); i++) {
                sesh_time_fojor_array[i] = fojorSeshTimeTxt.charAt(i);
            }

            seShTime = ""+sesh_time_fojor_array[0]+":"+sesh_time_fojor_array[1]+""+sesh_time_fojor_array[2];

            seShTime = converterClass.covertS(seShTime);


        }

        else if (cTime>=sTime && cTime<(sTime+23)){
            BorPNamaj= "নামাজ পড়ার নিষিদ্ধ সময় চলছে";
            nextnamaj= "যোহর";

            /////////////////////////////////Suru time add korte hobe////////////////////////



            //////////////////////////////sesh somoy////////////////////////////

            String JohorSeshTimeTxt = String.valueOf(aTime);

            Character [] sesh_time_johor_array= new Character[JohorSeshTimeTxt.length()];

            for (int i = 0; i < JohorSeshTimeTxt.length(); i++) {
                sesh_time_johor_array[i] = JohorSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_johor_array[0]+""+sesh_time_johor_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_johor_array[2]+""+sesh_time_johor_array[3];


            seShTime = converterClass.covertS(seShTime);

        }

        else if (cTime>=(sTime+23) && cTime<(jTime-5)){
            BorPNamaj= "পরবর্তী নামাজ যোহর";
            nextnamaj= "যোহর";

            //////////////////////////////sesh somoy////////////////////////////

            String JohorSeshTimeTxt = String.valueOf(aTime);

            Character [] sesh_time_johor_array= new Character[JohorSeshTimeTxt.length()];

            for (int i = 0; i < JohorSeshTimeTxt.length(); i++) {
                sesh_time_johor_array[i] = JohorSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_johor_array[0]+""+sesh_time_johor_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_johor_array[2]+""+sesh_time_johor_array[3];

            seShTime = converterClass.covertS(seShTime);



        }

        else if (cTime>=(jTime-5) && cTime<jTime){
            BorPNamaj= "নামাজ পড়ার নিষিদ্ধ সময় চলছে";
            nextnamaj= "যোহর";

            //////////////////////////////sesh somoy////////////////////////////

            String JohorSeshTimeTxt = String.valueOf(aTime);

            Character [] sesh_time_johor_array= new Character[JohorSeshTimeTxt.length()];

            for (int i = 0; i < JohorSeshTimeTxt.length(); i++) {
                sesh_time_johor_array[i] = JohorSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_johor_array[0]+""+sesh_time_johor_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_johor_array[2]+""+sesh_time_johor_array[3];
            seShTime = converterClass.covertS(seShTime);


        }

        else if (cTime>=jTime && cTime<(aTime-20)){
            BorPNamaj= "বর্তমান নামাজ যোহর";
            nextnamaj= "যোহর";

            //////////////////////////////sesh somoy////////////////////////////

            String JohorSeshTimeTxt = String.valueOf(aTime);

            Character [] sesh_time_johor_array= new Character[JohorSeshTimeTxt.length()];

            for (int i = 0; i < JohorSeshTimeTxt.length(); i++) {
                sesh_time_johor_array[i] = JohorSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_johor_array[0]+""+sesh_time_johor_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_johor_array[2]+""+sesh_time_johor_array[3];
            seShTime = converterClass.covertS(seShTime);



        }

        else if (cTime>=(aTime-20) && cTime<aTime){
            BorPNamaj= "পরবর্তী নামাজ আসর";
            nextnamaj= "আসর";

            //////////////////////////////sesh somoy////////////////////////////

            String AsorerSeshTimeTxt = String.valueOf(mTime-10);

            Character [] sesh_time_asor_array= new Character[AsorerSeshTimeTxt.length()];

            for (int i = 0; i < AsorerSeshTimeTxt.length(); i++) {
                sesh_time_asor_array[i] = AsorerSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_asor_array[0]+""+sesh_time_asor_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_asor_array[2]+""+sesh_time_asor_array[3];
            seShTime = converterClass.covertS(seShTime);



        }

        else if (cTime>=aTime && cTime<(mTime-10)){
            BorPNamaj= "বর্তমান নামাজ আসর";
            nextnamaj= "আসর";

            //////////////////////////////sesh somoy////////////////////////////

            String AsorerSeshTimeTxt = String.valueOf(mTime-10);

            Character [] sesh_time_asor_array= new Character[AsorerSeshTimeTxt.length()];

            for (int i = 0; i < AsorerSeshTimeTxt.length(); i++) {
                sesh_time_asor_array[i] = AsorerSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_asor_array[0]+""+sesh_time_asor_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_asor_array[2]+""+sesh_time_asor_array[3];
            seShTime = converterClass.covertS(seShTime);


        }

        else if (cTime>=(mTime-10) && cTime<mTime){
            BorPNamaj= "নামাজ পড়ার নিষিদ্ধ সময় চলছে";
            nextnamaj= "মাগরিব";

            //////////////////////////////sesh somoy////////////////////////////

            String MagriberSeshTimeTxt = String.valueOf(mTime+20);

            Character [] sesh_time_magrib_array= new Character[MagriberSeshTimeTxt.length()];

            for (int i = 0; i < MagriberSeshTimeTxt.length(); i++) {
                sesh_time_magrib_array[i] = MagriberSeshTimeTxt.charAt(i);
            }




            String convSt1 = ""+sesh_time_magrib_array[0]+""+sesh_time_magrib_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            String lastTwodigitString = ""+sesh_time_magrib_array[2]+""+sesh_time_magrib_array[3];

            int lastTwodigitInt = Integer.parseInt(lastTwodigitString);

            if (lastTwodigitInt>=60){
                lastTwodigitInt = lastTwodigitInt - 60;
                lastTwodigitString = String.valueOf(lastTwodigitInt);
                convInt = convInt + 1;
            }
            if (lastTwodigitInt<10){

                lastTwodigitString = "0"+lastTwodigitInt;

            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+lastTwodigitString;
            seShTime = converterClass.covertS(seShTime);


        }

        else if (cTime>=mTime && cTime<(mTime+20)){
            BorPNamaj= "বর্তমান নামাজ মাগরিব";
            nextnamaj= "মাগরিব";

            //////////////////////////////sesh somoy////////////////////////////

            String MagriberSeshTimeTxt = String.valueOf(mTime+20);

            Character [] sesh_time_magrib_array= new Character[MagriberSeshTimeTxt.length()];

            for (int i = 0; i < MagriberSeshTimeTxt.length(); i++) {
                sesh_time_magrib_array[i] = MagriberSeshTimeTxt.charAt(i);
            }




            String convSt1 = ""+sesh_time_magrib_array[0]+""+sesh_time_magrib_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            String lastTwodigitString = ""+sesh_time_magrib_array[2]+""+sesh_time_magrib_array[3];

            int lastTwodigitInt = Integer.parseInt(lastTwodigitString);

            if (lastTwodigitInt>=60){
                lastTwodigitInt = lastTwodigitInt - 60;
                lastTwodigitString = String.valueOf(lastTwodigitInt);
                convInt = convInt + 1;
            }
            if (lastTwodigitInt<10){

                lastTwodigitString = "0"+lastTwodigitInt;

            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+lastTwodigitString;
            seShTime = converterClass.covertS(seShTime);



        }


        else if (cTime>=(mTime+20) && cTime<eTime){
            BorPNamaj= "পরবর্তী নামাজ এশা";
            nextnamaj= "এশা";

            //////////////////////////////sesh somoy////////////////////////////

            String EsharSeshTimeTxt = "1200";

            Character [] sesh_time_esha_array= new Character[EsharSeshTimeTxt.length()];

            for (int i = 0; i < EsharSeshTimeTxt.length(); i++) {
                sesh_time_esha_array[i] = EsharSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_esha_array[0]+""+sesh_time_esha_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_esha_array[2]+""+sesh_time_esha_array[3];
            seShTime = converterClass.covertS(seShTime);



        }

        else if (cTime>=eTime && cTime>0){
            BorPNamaj= "বর্তমান নামাজ এশা";
            nextnamaj= "এশা";


            //////////////////////////////sesh somoy////////////////////////////

            String EsharSeshTimeTxt = "1200";

            Character [] sesh_time_esha_array= new Character[EsharSeshTimeTxt.length()];

            for (int i = 0; i < EsharSeshTimeTxt.length(); i++) {
                sesh_time_esha_array[i] = EsharSeshTimeTxt.charAt(i);
            }

            String convSt1 = ""+sesh_time_esha_array[0]+""+sesh_time_esha_array[1];

            int convInt = Integer.parseInt(convSt1);
            if (convInt>12){
                convInt = convInt - 12;
            }

            convSt1 = String.valueOf(convInt);

            seShTime = ""+convSt1+":"+sesh_time_esha_array[2]+""+sesh_time_esha_array[3];
            seShTime = converterClass.covertS(seShTime);



        }

        else if (cTime>=0 && cTime<fTime){
            BorPNamaj= "পরবর্তী নামাজ ফজর";
            nextnamaj= "ফজর";

            ////////sesh time fojor

            String fojorSeshTimeTxt = String.valueOf(sTime);

            Character [] sesh_time_fojor_array= new Character[fojorSeshTimeTxt.length()];

            for (int i = 0; i < fojorSeshTimeTxt.length(); i++) {
                sesh_time_fojor_array[i] = fojorSeshTimeTxt.charAt(i);
            }

            seShTime = ""+sesh_time_fojor_array[0]+":"+sesh_time_fojor_array[1]+""+sesh_time_fojor_array[2];
            seShTime = converterClass.covertS(seShTime);



        }




        BottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                Fragment fragment = null;

                switch (id){
                    case  R.id.home:
                        fragment = new TimeTableFragment();
                        break;

                    case  R.id.discover:
                        fragment = new CompusFragment();
                        break;

                    case  R.id.account:
                        fragment = new QuranMajidFragment();
                        break;
                    case  R.id.account2:
                        fragment = new TasbihFragment();
                        break;
                    case  R.id.account3:
                        fragment = new DoaDurudFragment();
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


    public  String fojorData() {

        Log.d("TAG", "fojorData: "+fajarTime );

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
    public String currentNamajTime() {
        return nextnamaj;
    }
    public String bOrPNamajTime() {
        return BorPNamaj;
    }

    public String seshTimeOfNamaj() {
        return seShTime;
    }

    public String LocationString() {
        return LocString;
    }



    @Override
    public void onBackPressed() {

        showDialog();



    }


    void showDialog() {




        final LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        ImageView image = view.findViewById(R.id.img);

        Button acceptButton = view.findViewById(R.id.acceptButton);
        Button cancelButton = view.findViewById(R.id.cancelButton);
        TextView textView = view.findViewById(R.id.text);

        textView.setText("আপনি কি প্রস্থান করতে চান?");

        cancelButton.setText("না");
        acceptButton.setText("হ্যাঁ");

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.cancel();

            }
        });



        alertDialog.show();



    }

}
