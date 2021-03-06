package com.rdtl.ad_din.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.rdtl.ad_din.R
import java.sql.DriverManager
import java.util.*

class KotlinActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        //////////////////////////////////////////////////////////////////////////////////////////


        var Lat: String = intent.getStringExtra("latitude")
        var Lon: String = intent.getStringExtra("longitude")
        var Loc: String = intent.getStringExtra("Locc")



        /////////////////////////////////////////////////////////


        val today = SimpleDate(GregorianCalendar())
        val location = Location(Lat.toDouble(), Lon.toDouble(), 6.0, 0)
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
        DriverManager.println("day  --->" + today.day )




        DriverManager.println("----------------------------------------")

        val TV = findViewById<TextView>(R.id.idTv).apply {
            text = "Fajar: " + prayerTimes.fajr() + "\nJohor: " + prayerTimes.maghrib() + "\ntoday: " + today.day
        }


        var but = findViewById<Button>(R.id.idjavaa) as Button


        var intent = Intent(applicationContext, BaseActivity::class.java)

        intent.putExtra("fajor", "" + prayerTimes.fajr())
        intent.putExtra("johor", "" + prayerTimes.thuhr())
        intent.putExtra("asor", "" + prayerTimes.assr())
        intent.putExtra("magrib", "" + prayerTimes.maghrib())
        intent.putExtra("esha", "" + prayerTimes.ishaa())
        intent.putExtra("sunrise", "" + prayerTimes.shuruq())
        intent.putExtra("Location", "" +Loc)
        startActivity(intent)
        finish();

//    }



    }
}

