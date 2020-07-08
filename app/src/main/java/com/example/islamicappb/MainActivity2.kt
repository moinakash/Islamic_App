package com.example.islamicappb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import java.sql.DriverManager
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val today = SimpleDate(GregorianCalendar())
        val location = Location(23.777176, 90.399452, 6.0, 0)
        val azan = Azan(location, Method.KARACHI_HANAF)
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)
        DriverManager.println("----------------results------------------------")
        DriverManager.println("date ---> " + today.day + " / " + today.month + " / " + today.year)
        DriverManager.println("imsaak ---> $imsaak")
        DriverManager.println("Fajr ---> " + prayerTimes.fajr())
        DriverManager.println("sunrise --->" + prayerTimes.shuruq())
        DriverManager.println("Zuhr --->" + prayerTimes.thuhr())
        DriverManager.println("Asr --->" + prayerTimes.assr())
        DriverManager.println("Maghrib --->" + prayerTimes.maghrib())
        DriverManager.println("ISHA  --->" + prayerTimes.ishaa())

        DriverManager.println("----------------------------------------")

        val TV = findViewById<TextView>(R.id.idTv).apply {
            text = "Fajar: " + prayerTimes.fajr() + "\nJohor: " + prayerTimes.maghrib() + "\ntoday: " + today.day
        }


        var but = findViewById<Button>(R.id.idjavaa) as Button

//        but.setOnClickListener {
//            //Define intent
        var intent = Intent(applicationContext, MainActivity::class.java)
// Here "first" is key and 123 is value
        intent.putExtra("fajor", "" + prayerTimes.fajr())
        intent.putExtra("johor", "" + prayerTimes.thuhr())
        intent.putExtra("asor", "" + prayerTimes.assr())
        intent.putExtra("magrib", "" + prayerTimes.maghrib())
        intent.putExtra("esha", "" + prayerTimes.ishaa())
        intent.putExtra("sunrise", "" + prayerTimes.shuruq())
        startActivity(intent)
        finish();

//    }

    }
}