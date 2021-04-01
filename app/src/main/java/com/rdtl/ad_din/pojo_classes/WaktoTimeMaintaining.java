package com.rdtl.ad_din.pojo_classes;

import android.content.Context;
import android.util.Log;

public class WaktoTimeMaintaining {

    private final Context wtmcontext;

    public WaktoTimeMaintaining(Context wtmcontext) {
        this.wtmcontext = wtmcontext;
    }


    public String ftimewithaddm(String actualtime){


        return  actualtime;
    }

    public String jtimewithaddm(String actualtimeS){

        actualtimeS = actualtimeS.replace(":", "");
        int actualtime= Integer.parseInt(actualtimeS);
        actualtime = actualtime/100;

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
            finaltime = ""+mainHour+":"+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+":0"+mainMinute;
        }else {
            finaltime = ""+mainHour+":"+mainMinute;
        }

        //actualtime = Integer.parseInt(finaltime);

        Log.e("jhtt",""+finaltime);

        return  finaltime;
    }

    public String atimewithaddm(String actualtimeS){

        actualtimeS = actualtimeS.replace(":", "");
        int actualtime= Integer.parseInt(actualtimeS);
        actualtime = actualtime/100;
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
            finaltime = ""+mainHour+":"+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+":0"+mainMinute;
        }else {
            finaltime = ""+mainHour+":"+mainMinute;
        }

        //actualtime = Integer.parseInt(finaltime);

        return  finaltime;
    }

    public String mtimewithaddm(String actualtimeS){

        actualtimeS = actualtimeS.replace(":", "");
        int actualtime= Integer.parseInt(actualtimeS);
        actualtime = actualtime/100;
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
            finaltime = ""+mainHour+":"+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+":0"+mainMinute;
        }else {
            finaltime = ""+mainHour+":"+mainMinute;
        }

        //actualtime = Integer.parseInt(finaltime);

        return  finaltime;
    }

    public String etimewithaddm(String actualtimeS){

        actualtimeS = actualtimeS.replace(":", "");
        int actualtime= Integer.parseInt(actualtimeS);
        actualtime = actualtime/100;
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
            finaltime = ""+mainHour+":"+"00";
        }else if ((mainMinute/10)==0){
            finaltime = ""+mainHour+":0"+mainMinute;
        }
        else {
            finaltime = ""+mainHour+":"+mainMinute;
        }

        //actualtime = Integer.parseInt(finaltime);

        return  finaltime;
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
