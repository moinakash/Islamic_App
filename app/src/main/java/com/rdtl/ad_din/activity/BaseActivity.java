package com.rdtl.ad_din.activity;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rdtl.ad_din.Api.Audio_api;
import com.rdtl.ad_din.Api.Retrofit;
import com.rdtl.ad_din.fragments.CompusFragment;
import com.rdtl.ad_din.fragments.DoaDurudFragment;
import com.rdtl.ad_din.fragments.QuranMajidFragment;
import com.rdtl.ad_din.R;
import com.rdtl.ad_din.fragments.RamjanFragment;
import com.rdtl.ad_din.fragments.TasbihFragment;
import com.rdtl.ad_din.fragments.TimeTableFragment;
import com.rdtl.ad_din.pojo_classes.Audio_list_modelCLass;
import com.rdtl.ad_din.pojo_classes.ConverterClass;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.rdtl.ad_din.pojo_classes.Value_modelClass;
import com.rdtl.ad_din.pojo_classes.WaktoTimeInt;
import com.rdtl.ad_din.pojo_classes.WaktoTimeMaintaining;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {

    ChipNavigationBar BottomNav, BottomNav2;
    FragmentManager fragmentManager;

    ConverterClass converterClass;
    WaktoTimeMaintaining wtm;

     String myString2;

    String fajarTime, johorTime, asorTime, magribTime, eshaTime, sunriseTime, nextnamaj, MiddleString;
    String fajarTimeR, johorTimeR, asorTimeR, magribTimeR, eshaTimeR, sunriseTimeR, nextnamajR, BorPNamaj, seShTime, LocString;

    int  cTime, fTime, jTime, aTime, mTime, eTime, sTime;

    int test;

    String final_Audio;
    String final_Audio_title;

    Retrofit retrofit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base);



        converterClass = new ConverterClass(this);
        wtm = new WaktoTimeMaintaining(this);


        onCreateM();
    }


    public void onCreateM(){

        retrofit = new Retrofit();
        AudioApi();
        BottombarApi();
        ListviewApi();
        ExtraValueApi();

        BottomNav = findViewById(R.id.bottom_nav);
        BottomNav2 = findViewById(R.id.bottom_nav2);

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
        fTime=converterClass.converI(fTime);
        test = fTime;





        johorTimeR = johorTime.replace(":", "");
        jTime = Integer.parseInt(johorTimeR);
        jTime= jTime/100;
        jTime=converterClass.converI(jTime);


        asorTimeR = asorTime.replace(":", "");
        aTime = Integer.parseInt(asorTimeR);
        aTime= aTime/100;
        aTime=converterClass.converI(aTime);


        magribTimeR = magribTime.replace(":", "");
        mTime = Integer.parseInt(magribTimeR);
        mTime= mTime/100;
        mTime=converterClass.converI(mTime);



        eshaTimeR = eshaTime.replace(":", "");
        eTime = Integer.parseInt(eshaTimeR);
        eTime= eTime/100;
        eTime=converterClass.converI(eTime);

        sunriseTimeR = sunriseTime.replace(":", "");
        sTime = Integer.parseInt(sunriseTimeR);
        sTime= sTime/100;
        sTime=converterClass.converI(sTime);


////////////////////////////////////////////////////////////////////////////////////////////////////////

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ");
        SimpleDateFormat sstt = new SimpleDateFormat("mm");


        String dateTime = simpleDateFormat.format(calendar.getTime());

        int qwer = Integer.parseInt(sstt.format(calendar.getTime()));

        //////////////////////////////////delete////////////////////


        if (qwer%2==0){
            BottomNav.setVisibility(View.GONE);
            BottomNav2.setVisibility(View.VISIBLE);
        }else {
            BottomNav2.setVisibility(View.VISIBLE);
            BottomNav.setVisibility(View.GONE);
        }

        ///////////////////////////////////////////////////////////


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

            String xyz = ""+sesh_time_fojor_array[2];



            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }


            seShTime = ""+sesh_time_fojor_array[0]+":"+sesh_time_fojor_array[1]+calculate99;

            seShTime = converterClass.covertS(seShTime);

            Log.e("ssttnn",""+seShTime);

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

            String xyz = ""+sesh_time_johor_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }

            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_johor_array[3];


            seShTime = converterClass.covertS(seShTime);

        }

        else if (cTime>=(sTime+23) && cTime<jTime+3){

            Log.e("dfg",""+jTime+" c"+cTime);

            if (jTime<1200){
                if (cTime>(jTime-5) && cTime<jTime+3){
                    BorPNamaj= "নামাজ পড়ার নিষিদ্ধ সময় চলছে";
                    nextnamaj= "যোহর";
                }else {
                    BorPNamaj= "পরবর্তী নামাজ যোহর";
                    nextnamaj= "যোহর";
                }

            }else if (jTime>=1200 && jTime<1204){
                if (cTime>(jTime-40-5) && cTime<jTime+3){
                    BorPNamaj= "নামাজ পড়ার নিষিদ্ধ সময় চলছে";
                    nextnamaj= "যোহর";
                }else {
                    BorPNamaj= "পরবর্তী নামাজ যোহর";
                    nextnamaj= "যোহর";
                }
            }

            //BorPNamaj= "পরবর্তী নামাজ যোহর";
            //nextnamaj= "যোহর";

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

            String xyz = ""+sesh_time_johor_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }


            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_johor_array[3];

            seShTime = converterClass.covertS(seShTime);



        }


        ////tetsing///
//        else if (cTime>=(sTime+23) && cTime<(jTime-5)){
//            BorPNamaj= "পরবর্তী নামাজ যোহর";
//            nextnamaj= "যোহর";
//
//            //////////////////////////////sesh somoy////////////////////////////
//
//            String JohorSeshTimeTxt = String.valueOf(aTime);
//
//            Character [] sesh_time_johor_array= new Character[JohorSeshTimeTxt.length()];
//
//            for (int i = 0; i < JohorSeshTimeTxt.length(); i++) {
//                sesh_time_johor_array[i] = JohorSeshTimeTxt.charAt(i);
//            }
//
//            String convSt1 = ""+sesh_time_johor_array[0]+""+sesh_time_johor_array[1];
//
//            int convInt = Integer.parseInt(convSt1);
//            if (convInt>12){
//                convInt = convInt - 12;
//            }
//
//            convSt1 = String.valueOf(convInt);
//
//            String xyz = ""+sesh_time_johor_array[2];
//
//            int calculate99 = Integer.parseInt(xyz);
//
//            if (calculate99==9){
//                calculate99=5;
//            }else if (calculate99==8){
//                calculate99=4;
//            }
//
//
//            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_johor_array[3];
//
//            seShTime = converterClass.covertS(seShTime);
//
//
//
//        }
//
//
//        else if (cTime>=(jTime-5) && cTime<jTime+3){
//            BorPNamaj= "নামাজ পড়ার নিষিদ্ধ সময় চলছে";
//            nextnamaj= "যোহর";
//
//            //////////////////////////////sesh somoy////////////////////////////
//
//            String JohorSeshTimeTxt = String.valueOf(aTime);
//
//            Character [] sesh_time_johor_array= new Character[JohorSeshTimeTxt.length()];
//
//            for (int i = 0; i < JohorSeshTimeTxt.length(); i++) {
//                sesh_time_johor_array[i] = JohorSeshTimeTxt.charAt(i);
//            }
//
//            String convSt1 = ""+sesh_time_johor_array[0]+""+sesh_time_johor_array[1];
//
//            int convInt = Integer.parseInt(convSt1);
//            if (convInt>12){
//                convInt = convInt - 12;
//            }
//
//            convSt1 = String.valueOf(convInt);
//
//            String xyz = ""+sesh_time_johor_array[2];
//
//            int calculate99 = Integer.parseInt(xyz);
//
//            if (calculate99==9){
//                calculate99=5;
//            }else if (calculate99==8){
//                calculate99=4;
//            }
//
//            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_johor_array[3];
//            seShTime = converterClass.covertS(seShTime);
//
//
//        }
///////////////////testing end///////////////////////


        else if (cTime>=jTime+3 && cTime<(aTime-20)){
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

            String xyz = ""+sesh_time_johor_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }

            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_johor_array[3];
            seShTime = converterClass.covertS(seShTime);



        }

        else if (cTime>=(aTime-20) && cTime<aTime+1){
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

            String xyz = ""+sesh_time_asor_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }

            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_asor_array[3];
            seShTime = converterClass.covertS(seShTime);



        }

        else if (cTime>=aTime+1 && cTime<(mTime-10)){
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

            String xyz = ""+sesh_time_asor_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }



            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_asor_array[3];



            seShTime = converterClass.covertS(seShTime);


        }

        else if (cTime>=(mTime-10) && cTime<mTime+3){
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

        else if (cTime>=mTime && cTime<(mTime+20+3)){
            BorPNamaj= "বর্তমান নামাজ মাগরিব";
            nextnamaj= "মাগরিব";

            //////////////////////////////sesh somoy////////////////////////////
            WaktoTimeInt waktoTimeInt = new WaktoTimeInt(this);
            int mTimw2 = waktoTimeInt.mtimewithaddm2(mTime);

            String MagriberSeshTimeTxt = ""+mTimw2;

            Character [] sesh_time_magrib_array= new Character[MagriberSeshTimeTxt.length()];

            for (int i = 0; i < MagriberSeshTimeTxt.length(); i++) {
                sesh_time_magrib_array[i] = MagriberSeshTimeTxt.charAt(i);
            }





            String convSt1 = ""+sesh_time_magrib_array[0]+""+sesh_time_magrib_array[1];

            Log.e("qq",""+MagriberSeshTimeTxt);

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

            String xyz = ""+sesh_time_esha_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }

            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_esha_array[3];
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

            String xyz = ""+sesh_time_esha_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }

            seShTime = ""+convSt1+":"+calculate99+""+sesh_time_esha_array[3];
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

            String xyz = ""+sesh_time_fojor_array[2];

            int calculate99 = Integer.parseInt(xyz);

            if (calculate99==9){
                calculate99=5;
            }else if (calculate99==8){
                calculate99=4;
            }else if (calculate99==7) {
                calculate99 = 3;
            }

            seShTime = ""+sesh_time_fojor_array[0]+":"+sesh_time_fojor_array[1]+""+calculate99;
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


        BottomNav2.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
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
                    case  R.id.account4:
                        fragment = new RamjanFragment();
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


    public void AudioApi(){


        Call<List<Audio_list_modelCLass>> call = retrofit.getService(Audio_api.class).getAudio("2");

        call.enqueue(new Callback<List<Audio_list_modelCLass>>() {
            @Override
            public void onResponse(Call<List<Audio_list_modelCLass>> call, Response<List<Audio_list_modelCLass>> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(BaseActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    //return;

                    final_Audio = "No";
                    SharedPreferences pref = BaseActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("sp_Audio_Url", final_Audio);
                    editor.commit();
                }

                if (response.isSuccessful()){

                    List<Audio_list_modelCLass> posts = response.body();

                    final_Audio = "https://cb027f7c69bb.ngrok.io/"+posts.get(0).getAudio();

                    final_Audio_title = ""+posts.get(0).getName();

                    Toast.makeText(BaseActivity.this, "Success title "+final_Audio_title, Toast.LENGTH_SHORT).show();
                    SharedPreferences pref = BaseActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("sp_Audio_Url", final_Audio);
                    editor.putString("sp_Audio_Url_title",final_Audio_title);
                    editor.commit();

                }










                Toast.makeText(BaseActivity.this, ""+final_Audio, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<Audio_list_modelCLass>> call, Throwable t) {

           /*     SharedPreferences pref = BaseActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("sp_Audio_Url", "http://soundflux.islamicfinder.org/if-soundflux/api/v1/stream//quran/rahman-sudais/001.mp3");
                editor.commit();*/


              //  Toast.makeText(BaseActivity.this, "http://soundflux.islamicfinder.org/if-soundflux/api/v1/stream//quran/rahman-sudais/001.mp3", Toast.LENGTH_SHORT).show();

                final_Audio = "No";
                SharedPreferences pref = BaseActivity.this.getSharedPreferences("Api_Audio",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("sp_Audio_Url", final_Audio);
                editor.commit();

            }
        });



    }



    public void BottombarApi(){


        Call<List<Value_modelClass>> call2 = retrofit.getService(Audio_api.class).getValue("btm_bar");


        call2.enqueue(new Callback<List<Value_modelClass>>() {
            @Override
            public void onResponse(Call<List<Value_modelClass>> call, Response<List<Value_modelClass>> response) {




                if (!response.isSuccessful()) {
                    Toast.makeText(BaseActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    //return;


                }

                if (response.isSuccessful()){

                    List<Value_modelClass> posts = response.body();


                    Toast.makeText(BaseActivity.this, "btm_bar = "+ posts.get(0).getValue(), Toast.LENGTH_SHORT).show();


                }





            }

            @Override
            public void onFailure(Call<List<Value_modelClass>> call, Throwable t) {

                Toast.makeText(BaseActivity.this, "Not Success btm_bar", Toast.LENGTH_SHORT).show();

            }
        });



    }


    public void ListviewApi(){


        Call<List<Value_modelClass>> call3 = retrofit.getService(Audio_api.class).getValue("lv_visibility");



        call3.enqueue(new Callback<List<Value_modelClass>>() {
            @Override
            public void onResponse(Call<List<Value_modelClass>> call, Response<List<Value_modelClass>> response) {




                if (!response.isSuccessful()) {
                    Toast.makeText(BaseActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    //return;


                }

                if (response.isSuccessful()){

                    List<Value_modelClass> posts = response.body();


                    Toast.makeText(BaseActivity.this, "lv_visibility = "+ posts.get(0).getValue(), Toast.LENGTH_SHORT).show();


                }



            }

            @Override
            public void onFailure(Call<List<Value_modelClass>> call, Throwable t) {

                Toast.makeText(BaseActivity.this, "Not Success lv_visibility", Toast.LENGTH_SHORT).show();

            }
        });



    }



    public void ExtraValueApi(){


        Call<List<Value_modelClass>> call3 = retrofit.getService(Audio_api.class).getValue("extra_value");



        call3.enqueue(new Callback<List<Value_modelClass>>() {
            @Override
            public void onResponse(Call<List<Value_modelClass>> call, Response<List<Value_modelClass>> response) {




                if (!response.isSuccessful()) {
                    Toast.makeText(BaseActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    //return;


                }

                if (response.isSuccessful()){

                    List<Value_modelClass> posts = response.body();


                    Toast.makeText(BaseActivity.this, "extra_value = "+ posts.get(0).getValue(), Toast.LENGTH_SHORT).show();


                }



            }

            @Override
            public void onFailure(Call<List<Value_modelClass>> call, Throwable t) {

                Toast.makeText(BaseActivity.this, "Not Success extra_value", Toast.LENGTH_SHORT).show();

            }
        });



    }

}
