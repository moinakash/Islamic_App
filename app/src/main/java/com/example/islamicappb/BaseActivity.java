package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.paperdb.Paper;

public class BaseActivity extends AppCompatActivity {

    ChipNavigationBar BottomNav;
    FragmentManager fragmentManager;



//    final String myString = "hello";
     String myString2;

    String fajarTime, johorTime, asorTime, magribTime, eshaTime, sunriseTime, nextnamaj;
    String fajarTimeR, johorTimeR, asorTimeR, magribTimeR, eshaTimeR, sunriseTimeR, nextnamajR, BorPNamaj, seShTime, LocString;

    int  cTime, fTime, jTime, aTime, mTime, eTime, sTime;

    int test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base);

        Paper.init(this);


        onCreateM();
    }


    public void onCreateM(){


        BottomNav = findViewById(R.id.bottom_nav);

        Fragment fragment = new TimeTableFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();





        myString2 = "hello2";

        fajarTime = getIntent().getExtras().getString("fajor");
        johorTime = getIntent().getExtras().getString("johor");
        asorTime = getIntent().getExtras().getString("asor");
        magribTime = getIntent().getExtras().getString("magrib");
        eshaTime = getIntent().getExtras().getString("esha");
        sunriseTime = getIntent().getExtras().getString("sunrise");
        LocString = getIntent().getExtras().getString("Location");
        Log.e("Location",LocString);


        fajarTimeR = fajarTime.replace(":", "");
        fTime = Integer.parseInt(fajarTimeR);
        fTime= fTime/100;
        test = fTime;

        Log.d("TAG", "onCreate: "+test);





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
            BorPNamaj= "বর্তমান নামাজ";
            nextnamaj= "ফজর";

            ////////sesh time fojor

            String fojorSeshTimeTxt = String.valueOf(sTime);

            Character [] sesh_time_fojor_array= new Character[fojorSeshTimeTxt.length()];

            for (int i = 0; i < fojorSeshTimeTxt.length(); i++) {
                sesh_time_fojor_array[i] = fojorSeshTimeTxt.charAt(i);
            }

            seShTime = ""+sesh_time_fojor_array[0]+":"+sesh_time_fojor_array[1]+""+sesh_time_fojor_array[2];

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=sTime && cTime<(sTime+23)){
            BorPNamaj= "নামাজ পড়া নিষেধ";
            nextnamaj= "পরবর্তী নামাজ যোহর";

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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=(sTime+23) && cTime<(jTime-5)){
            BorPNamaj= "পরবর্তী নামাজ";
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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=(jTime-5) && cTime<jTime){
            BorPNamaj= "নামাজ পড়া নিষেধ";
            nextnamaj= "পরবর্তী নামাজ যোহর";

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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=jTime && cTime<(aTime-20)){
            BorPNamaj= "বর্তমান নামাজ";
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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=(aTime-20) && cTime<aTime){
            BorPNamaj= "পরবর্তী নামাজ";
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

            seShTime= seShTime.replace("0", "০" );
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=aTime && cTime<(mTime-10)){
            BorPNamaj= "বর্তমান নামাজ";
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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=(mTime-10) && cTime<mTime){
            BorPNamaj= "নামাজ পড়া নিষেধ";
            nextnamaj= "পরবর্তী নামাজ মাগরিব";

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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=mTime && cTime<(mTime+20)){
            BorPNamaj= "বর্তমান নামাজ";
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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }


        else if (cTime>=(mTime+20) && cTime<eTime){
            BorPNamaj= "পরবর্তী নামাজ";
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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=eTime && cTime>0){
            BorPNamaj= "বর্তমান নামাজ";
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

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

        }

        else if (cTime>=0 && cTime<fTime){
            BorPNamaj= "পরবর্তী নামাজ";
            nextnamaj= "ফজর";

            ////////sesh time fojor

            String fojorSeshTimeTxt = String.valueOf(sTime);

            Character [] sesh_time_fojor_array= new Character[fojorSeshTimeTxt.length()];

            for (int i = 0; i < fojorSeshTimeTxt.length(); i++) {
                sesh_time_fojor_array[i] = fojorSeshTimeTxt.charAt(i);
            }

            seShTime = ""+sesh_time_fojor_array[0]+":"+sesh_time_fojor_array[1]+""+sesh_time_fojor_array[2];

            seShTime= seShTime.replace("0", "০");
            seShTime= seShTime.replace("1", "১");
            seShTime= seShTime.replace("2", "২");
            seShTime= seShTime.replace("3", "৩");
            seShTime= seShTime.replace("4", "৪");
            seShTime= seShTime.replace("5", "৫");
            seShTime= seShTime.replace("6", "৬");
            seShTime= seShTime.replace("7", "৭");
            seShTime= seShTime.replace("8", "৮");
            seShTime= seShTime.replace("9", "৯");

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
                        fragment = new TimeTableFragment();
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

public int getTest(){

        return test;
}


  public int getfTime(){

   Log.d("TAG", "onUpdate: "+fTime);


        return test;
  }
    public int getjTime(){

        return jTime;
    }
    public int getaTime(){

        return aTime;
    }
    public int getmTime(){

        return mTime;
    }
    public int geteTime(){

        return eTime;
    }

}
