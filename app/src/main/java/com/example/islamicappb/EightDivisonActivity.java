package com.example.islamicappb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EightDivisonActivity extends AppCompatActivity {


    Button btnDhaka, btnChotto, btnRaj, btnKhul, btnBari, btnShy, btnRang, btnMoy;

    String latt = "", lonn ="", locString="";

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_divison);

        btnDhaka = findViewById(R.id.idDhaka);
        btnChotto = findViewById(R.id.idChottogram);
        btnRaj = findViewById(R.id.idRajshahi);
        btnKhul = findViewById(R.id.idKhulna);
        btnBari = findViewById(R.id.idBorishal);
        btnShy = findViewById(R.id.idShylet);
        btnRang = findViewById(R.id.idRangpur);
        btnMoy = findViewById(R.id.idMoymonshing);


        ///////////////////////////////////////////////////////

        sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
        latt = sharedPref.getString("latSp","");
        lonn = sharedPref.getString("lonSp","");
        locString = sharedPref.getString("locString","");

        if (!latt.equals("")){
            Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
            in.putExtra("latitude", latt);
            in.putExtra("longitude", lonn);
            in.putExtra("Locc", locString);

            startActivity(in);
            finish();
        }


        ///////////////////////////////////////////////////////////////////////////////


        btnDhaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+23.777176;
                lonn = ""+90.399452;
                locString = "ঢাকা";

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



            }
        });

        btnChotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+22.341900;
                lonn = ""+91.815536;

                locString = "চট্টগ্রাম";

                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);
                editor.commit();

                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("Username", latt);
                in.putExtra("Password", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);

            }
        });

        btnRaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+24.006355;
                lonn = ""+89.249298;

                locString = "রাজশাহী";

                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);
                editor.commit();


                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("Username", latt);
                in.putExtra("Password", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);
            }
        });

        btnKhul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+22.820000;
                lonn = ""+89.550003;

                locString = "খুলনা";

                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);
                editor.commit();


                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("Username", latt);
                in.putExtra("Password", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);
            }
        });

        btnBari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+22.974237;
                lonn = ""+90.222122;
                locString = "বরিশাল";


                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);
                editor.commit();


                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("Username", latt);
                in.putExtra("Password", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);
            }
        });

        btnShy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+24.886436;
                lonn = ""+91.880722;
                locString = "সিলেট";

                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);
                editor.commit();


                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("Username", latt);
                in.putExtra("Password", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);

            }
        });

        btnRang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+25.744860;
                lonn = ""+89.275589;
                locString = "রংপুর";

                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);
                editor.commit();


                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("Username", latt);
                in.putExtra("Password", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);
            }
        });

        btnMoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                latt = ""+24.743448;
                lonn = ""+90.398384;
                locString = "ময়মনসিংহ";

                sharedPref = EightDivisonActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("latSp", ""+latt);
                editor.putString("lonSp", ""+lonn);
                editor.putString("Locc", ""+locString);
                editor.commit();


                Intent in = new Intent(EightDivisonActivity.this,KotlinActivity.class);
                in.putExtra("Username", latt);
                in.putExtra("Password", lonn);
                in.putExtra("Locc", ""+locString);
                startActivity(in);
            }
        });
    }
}