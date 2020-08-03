package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class EightDivisonActivity extends AppCompatActivity {


    Button btnDhaka, btnChotto, btnRaj, btnKhul, btnBari, btnShy, btnRang, btnMoy;

    String latt = "", lonn ="", locString ="";

    SharedPreferences sharedPref;

    String ff;


/*    Spinner  spDis;*/

    MaterialSpinner spDiv,spDis;

    DatabaseHelper db;
    ArrayList<String> arrayList_div;
    ArrayAdapter<String> arrayAdapter_div;

    ArrayList<String>  arrayList_blank, arrayList_Dhaka, arrayList_Chita, arrayList_bari, arrayList_Khul, arrayList_Raj, arrayList_Rang, arrayList_Sy, arrayList_lat, arrayList_lon;

    ArrayAdapter<String> arrayAdapter_Dis;
    ArrayAdapter<String> arrayAdapter_Latlon;

    String placeName = "";

    Double latitude, longitude, latC, lonC;



    int lat, lon;

    TextView ibNextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_eight_divison);

//        btnDhaka = findViewById(R.id.idDhaka);
//        btnChotto = findViewById(R.id.idChottogram);
//        btnRaj = findViewById(R.id.idRajshahi);
//        btnKhul = findViewById(R.id.idKhulna);
//        btnBari = findViewById(R.id.idBorishal);
//        btnShy = findViewById(R.id.idShylet);
//        btnRang = findViewById(R.id.idRangpur);
//        btnMoy = findViewById(R.id.idMoymonshing);

        spDiv = findViewById(R.id.idDivision);
        spDis = findViewById(R.id.idDistrict);
        ibNextbutton = findViewById(R.id.idNextButton);
        arrayList_div = new ArrayList<>();
        db = new DatabaseHelper(this);

        fetchData();
        loadData();


        ///////////////////////////////////////////////////////

        sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
        latt = sharedPref.getString("latSp","");
        lonn = sharedPref.getString("lonSp","");
        locString = sharedPref.getString("locString","");



        /////////////////////////////////////////////////////////////////
        SharedPreferences sharedPrefMM = getSharedPreferences("mm",Context.MODE_PRIVATE);
        ff = sharedPrefMM.getString("key","");
        if (ff.equals("1")){


        }else {



            if (!latt.equals("")){
                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("latitude", latt);
                in.putExtra("longitude", lonn);
                in.putExtra("Locc", locString);

                Log.e("locat",""+locString);

                startActivity(in);
                finish();
            }

        }


        if (placeName.equals("")){

        }else {
            ibNextbutton.setVisibility(View.VISIBLE);
        }

        ibNextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                latt = String.valueOf(latC);
                lonn = String.valueOf(lonC);
                locString = placeName;


                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);

                editor.commit();


                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("latitude", latt);
                in.putExtra("longitude", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);

                finish();


            }
        });
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

    public void loadData() {

        final Cursor cursor = db.showDiv();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                arrayList_div.add(cursor.getString(3));
            }
        }

        arrayAdapter_div = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList_div);
        spDiv.setAdapter(arrayAdapter_div);




        /////////////////////////////////////////////////////////

        arrayList_blank = new ArrayList<>();
        arrayList_blank.add("");

        arrayList_Dhaka = new ArrayList<>();
        final Cursor cursorDhaka = db.showDhaka();
        if (cursorDhaka.getCount() == 0) {


        } else {
            while (cursorDhaka.moveToNext()) {
                arrayList_Dhaka.add(cursorDhaka.getString(3));
            }
        }

//        arrayAdapter_Dis = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, (List) arrayList_Dhaka);
//        spDis.setAdapter(arrayAdapter_Dis);



        arrayList_Chita = new ArrayList<>();

        final Cursor cursor_Chita = db.showChit();
        if (cursor_Chita.getCount() == 0) {

        } else {
            while (cursor_Chita.moveToNext()) {
                arrayList_Chita.add(cursor_Chita.getString(3));
            }
        }



        arrayList_bari = new ArrayList<>();
        final Cursor cursor_bari = db.showBari();
        if (cursor_bari.getCount() == 0) {

        } else {
            while (cursor_bari.moveToNext()) {
                arrayList_bari.add(cursor_bari.getString(3));
            }
        }


        arrayList_Khul = new ArrayList<>();
        final Cursor cursor_Khul = db.shoeKhul();
        if (cursor_Khul.getCount() == 0) {

        } else {
            while (cursor_Khul.moveToNext()) {
                arrayList_Khul.add(cursor_Khul.getString(3));
            }
        }



        arrayList_Raj = new ArrayList<>();
        final Cursor cursor_Raj = db.showRaj();
        if (cursor_Raj.getCount() == 0) {

        } else {
            while (cursor_Raj.moveToNext()) {
                arrayList_Raj.add(cursor_Raj.getString(3));
            }
        }



        arrayList_Rang = new ArrayList<>();
        final Cursor cursor_Rong = db.showRong();
        if (cursor_Rong.getCount() == 0) {

        } else {
            while (cursor_Rong.moveToNext()) {
                arrayList_Rang.add(cursor_Rong.getString(3));
            }
        }





        arrayList_Sy = new ArrayList<>();
        int a = 7;
        final Cursor cursor_Sy = db.showSy(a);
        if (cursor_Sy.getCount() == 0) {

        } else {
            while (cursor_Sy.moveToNext()) {
                arrayList_Sy.add(cursor_Sy.getString(3));
            }
        }


        spDiv.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int i, long id, String item) {
                if (i==0){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_blank);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
                if (i==1){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Dhaka);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
                if (i==2){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Chita);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
                if (i==3){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, (List) arrayList_bari);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
                if (i==4){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, (List) arrayList_Khul);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
                if (i==5){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, (List) arrayList_Raj);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
                if (i==6){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, (List) arrayList_Rang);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
                if (i==7){
                    arrayAdapter_Dis = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, (List) arrayList_Sy);
                    spDis.setAdapter(arrayAdapter_Dis);
                }
            }

     /*       @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }*/


        });

        spDis.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int i, long id, String item) {

//                Toast.makeText(getApplicationContext(), ""+adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();

//              /*  placeName = ""+adapterView.getItemAtPosition(i).toString(*/);

        placeName = ""+item;




                switch(placeName) {
                    case "ঢাকা":
                        latC = 23.810332;
                        lonC = 90.412518;
                        break;
                    case "ফরিদপুর":

                        latC = 23.542392;
                        lonC = 89.630892;
                        break;
                    case "গাজীপুর":

                        latC = 24.095817;
                        lonC = 90.412518;
                        break;
                    case "গোপালগঞ্জ":

                        latC = 23.048815;
                        lonC = 89.88793;
                        break;
                    case "জামালপুর":

                        latC = 25.083093;
                        lonC = 89.785322;
                        break;
                    case "কিশোরগঞ্জ":

                        latC = 24.426046;
                        lonC = 90.982067;
                        break;
                    case "মাদারীপুর":

                        latC = 23.239335;
                        lonC = 90.186964;
                        break;
                    case "মানিকগঞ্জ":

                        latC = 23.861651;
                        lonC = 90.000323;
                        break;
                    case "মুন্সিগঞ্জ":
                        latC = 23.498093;
                        lonC = 90.412662;

                        break;
                    case "ময়মনসিংহ":

                        latC = 24.743448;
                        lonC = 90.398383;
                        break;
                    case "নারায়ণগঞ্জ":

                        latC = 23.615234;
                        lonC = 90.503893;
                        break;
                    case "নরসিংদী":

                        latC = 24.134378;
                        lonC = 90.786006;
                        break;
                    case "নেত্রকোনা":

                        latC = 24.870955;
                        lonC = 90.727887;
                        break;
                    case "রাজবাড়ি":

                        latC = 23.7574305;
                        lonC = 89.6444665;
                        break;
                    case "শরীয়তপুর":

                        latC = 23.242321;
                        lonC = 90.434771;
                        break;
                    case "শেরপুর":

                        latC = 25.0204933;
                        lonC = 90.0152966;
                        break;
                    case "টাঙ্গাইল":

                        latC = 24.391743;
                        lonC = 89.994826;
                        break;
                    case "বগুড়া":

                        latC = 24.8465228;
                        lonC = 89.377755;
                        break;
                    case "জয়পুরহাট":

                        latC = 25.096774;
                        lonC = 89.022713;
                        break;
                    case "নওগাঁ":

                        latC = 24.91316;
                        lonC = 88.753095;
                        break;
                    case "নাটোর":

                        latC = 24.420556;
                        lonC = 89.000282;
                        break;

                    case "নবাবগঞ্জ":
                        latC = 24.5965034;
                        lonC = 88.2775122;
                        break;
                    case "পাবনা":

                        latC = 23.998524;
                        lonC = 89.233645;
                        break;
                    case "রাজশাহী":

                        latC = 24.363589;
                        lonC = 88.624135;
                        break;
                    case "সিরাজগঞ্জ":

                        latC = 24.4533978;
                        lonC = 89.7006815;
                        break;
                    case "দিনাজপুর":

                        latC = 25.6217061;
                        lonC = 88.6354504;
                        break;
                    case "গাইবান্ধা":

                        latC = 25.328751;
                        lonC = 89.528088;
                        break;
                    case "কুড়িগ্রাম":

                        latC = 25.805445;
                        lonC = 89.636174;
                        break;
                    case "লালমনিরহাট":

                        latC = 25.99234;
                        lonC = 89.284725;
                        break;
                    case "নীলফামারী":
                        latC = 25.931794;
                        lonC = 88.856006;

                        break;
                    case "পঞ্চগড়":

                        latC = 26.3411;
                        lonC = 88.5541606;
                        break;
                    case "রংপুর":

                        latC = 25.7558096;
                        lonC = 89.244462;
                        break;
                    case "ঠাকুরগাঁও":

                        latC = 26.0336945;
                        lonC = 88.4616834;
                        break;
                    case "বরগুনা":

                        latC = 22.095291;
                        lonC = 90.11207;
                        break;
                    case "বরিশাল":

                        latC = 22.702921;
                        lonC = 90.346597;
                        break;
                    case "ভোলা":

                        latC = 22.685923;
                        lonC = 90.648179;
                        break;
                    case "ঝালকাঠি":

                        latC = 22.57208;
                        lonC = 90.186964;
                        break;
                    case "পটুয়াখালী":

                        latC = 22.3596316;
                        lonC = 90.3298712;
                        break;
                    case "পিরোজপুর":

                        latC = 22.584105;
                        lonC = 89.972013;
                        break;
                    case "বান্দরবান":

                        latC = 22.1953275;
                        lonC = 92.2183773;
                        break;
                    case "ব্রাহ্মণবাড়িয়া":

                        latC = 23.9570904;
                        lonC = 91.1119286;
                        break;
                    case "চাঁদপুর":

                        latC = 23.2332585;
                        lonC = 91.834073;
                        break;

                    case "চট্টগ্রাম":
                        latC = 22.335109;
                        lonC = 90.6712912;
                        break;
                    case "কুমিল্লা":

                        latC = 23.4682747;
                        lonC = 91.1788135;
                        break;
                    case "কক্স বাজার":

                        latC = 21.439464;
                        lonC = 92.007732;
                        break;
                    case "ফেনী":

                        latC = 23.023231;
                        lonC = 91.3840844;
                        break;
                    case "খাগড়াছড়ি":

                        latC = 23.119285;
                        lonC = 91.984663;
                        break;
                    case "লক্ষ্মীপুর":

                        latC = 22.942477;
                        lonC = 90.841184;
                        break;
                    case "নোয়াখালী":

                        latC = 22.869563;
                        lonC = 91.099398;
                        break;
                    case "রাঙ্গামাটি":

                        latC = 22.732417;
                        lonC = 92.298513;
                        break;
                    case "হবিগঞ্জ":
                        latC = 24.374945;
                        lonC = 91.41553;

                        break;
                    case "মৌলভীবাজার":

                        latC = 24.482934;
                        lonC = 91.777417;
                        break;
                    case "সুনামগঞ্জ":

                        latC = 25.0658042;
                        lonC = 91.3950115;
                        break;
                    case "সিলেট":

                        latC = 24.8897956;
                        lonC = 91.8697894;
                        break;
                    case "বাগেরহাট":

                        latC = 22.651568;
                        lonC = 89.785938;
                        break;
                    case "চুয়াডাঙ্গা":

                        latC = 23.6401961;
                        lonC = 88.841841;
                        break;
                    case "যশোর":

                        latC = 23.16643;
                        lonC = 89.2081126;
                        break;
                    case "ঝিনাইদহ":

                        latC = 23.5448176;
                        lonC = 89.1539213;
                        break;
                    case "খুলনা":

                        latC = 22.815774;
                        lonC = 89.568679;
                        break;
                    case "কুষ্টিয়া":

                        latC = 23.901258;
                        lonC = 89.120482;
                        break;
                    case "মাগুরা":

                        latC = 23.487337;
                        lonC = 89.419956;
                        break;
                    case "মেহেরপুর":

                        latC = 23.762213;
                        lonC = 88.631821;
                        break;
                    case "নড়াইল":

                        latC = 23.172534;
                        lonC = 89.512672;
                        break;
                    case "সাতক্ষীরা":

                        latC = 22.315481;
                        lonC = 89.111453;
                        break;

                    default:

                        break;
                }

                latitude = latC;

                longitude = lonC;

                if (placeName.equals("")){

                }else {
                    ibNextbutton.setVisibility(View.VISIBLE);
                }

            }



        });





    }
}