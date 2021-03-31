package com.rdtl.ad_din.pojo_classes;

import android.content.Context;

public class WaktoTimeInt {

    private final Context wtmcontext;

    public WaktoTimeInt(Context wtmcontext) {
        this.wtmcontext = wtmcontext;
    }


    public Integer ftimewithaddm(int actualtime){


        return  actualtime;
    }

    public Integer jtimewithaddm(int actualtime){



        int mainHour = fulltimetohour(actualtime);
        int mainMinute = fulltimetoMinute(actualtime);

        if ((mainMinute+3)>=60){
            mainHour+=1;
            mainMinute=(mainMinute+3)-60;
        }else {
            mainMinute+=3;
        }

        String finaltime;

        if (mainMinute==0){
            finaltime = ""+mainHour+""+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+"0"+mainMinute;
        }else {
            finaltime = ""+mainHour+""+mainMinute;
        }

        actualtime = Integer.parseInt(finaltime);

        return  actualtime;
    }

    public Integer atimewithaddm(int actualtime){


        int mainHour = fulltimetohour(actualtime);
        int mainMinute = fulltimetoMinute(actualtime);

        if ((mainMinute+1)>=60){
            mainHour+=1;
            mainMinute=(mainMinute+1)-60;
        }else {
            mainMinute+=1;
        }

        String finaltime;

        if (mainMinute==0){
            finaltime = ""+mainHour+""+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+"0"+mainMinute;
        }else {
            finaltime = ""+mainHour+""+mainMinute;
        }

        actualtime = Integer.parseInt(finaltime);

        return  actualtime;
    }

    public Integer mtimewithaddm(int actualtime){


        int mainHour = fulltimetohour(actualtime);
        int mainMinute = fulltimetoMinute(actualtime);

        if ((mainMinute+3)>=60){
            mainHour+=1;
            mainMinute=(mainMinute+3)-60;
        }else {
            mainMinute+=3;
        }

        String finaltime;

        if (mainMinute==0){
            finaltime = ""+mainHour+""+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+"0"+mainMinute;
        }else {
            finaltime = ""+mainHour+""+mainMinute;
        }

        actualtime = Integer.parseInt(finaltime);

        return  actualtime;
    }

    public Integer etimewithaddm(int actualtime){


        int mainHour = fulltimetohour(actualtime);
        int mainMinute = fulltimetoMinute(actualtime);

        if ((mainMinute+1)>=60){
            mainHour+=1;
            mainMinute=(mainMinute+1)-60;
        }else {
            mainMinute+=1;
        }

        String finaltime;

        if (mainMinute==0){
            finaltime = ""+mainHour+""+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+"0"+mainMinute;
        }else {
            finaltime = ""+mainHour+""+mainMinute;
        }

        actualtime = Integer.parseInt(finaltime);

        return  actualtime;
    }

    public Integer mtimewithaddm2(int actualtime){


        int mainHour = fulltimetohour(actualtime);
        int mainMinute = fulltimetoMinute(actualtime);

        if ((mainMinute+20+3)>=60){
            mainHour+=1;
            mainMinute=(mainMinute+20+3)-60;
        }else {
            mainMinute=mainMinute+20+3;
        }

        String finaltime;

        if (mainMinute==0){
            finaltime = ""+mainHour+""+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+"0"+mainMinute;
        }else {
            finaltime = ""+mainHour+""+mainMinute;
        }

        actualtime = Integer.parseInt(finaltime);

        return  actualtime;
    }


    public Integer fulltimetohour(int fulltimeH){
        fulltimeH = fulltimeH/100;
        return fulltimeH;
    }

    public Integer fulltimetoMinute(int fulltimeM){
        fulltimeM = fulltimeM%100;
        return fulltimeM;
    }

}
