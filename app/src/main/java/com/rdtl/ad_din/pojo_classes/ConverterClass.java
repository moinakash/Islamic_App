package com.rdtl.ad_din.pojo_classes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ConverterClass {

    private final Context convertcontext;

    public ConverterClass(Context convertcontext) {
        this.convertcontext = convertcontext;
    }

    public String covertS(String seShTime)
    {
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
        return seShTime;
    }

    public String covertE(String seShTime)
    {
        seShTime= seShTime.replace( "০","0");
        seShTime= seShTime.replace( "১","1");
        seShTime= seShTime.replace( "২","2");
        seShTime= seShTime.replace( "৩","3");
        seShTime= seShTime.replace("৪","4");
        seShTime= seShTime.replace( "৫","5");
        seShTime= seShTime.replace( "৬","6");
        seShTime= seShTime.replace( "৭","7");
        seShTime= seShTime.replace( "৮","8");
        seShTime= seShTime.replace("৯","9");
        return seShTime;
    }


    public Integer converI(int ct){

        int wholeValue = ct;

        int HR = wholeValue/100;
        int MM = wholeValue%100;

        return wholeValue;
    }




}
