package com.example.islamicappb;

public class SuraNameListPojo {



    String sura_number;
    String sura_name_bangla;
    String sura_name_meaning;
    String sura_name_arbi;
    String sura_total_ayat;
    String sura_place;
    String sura_name_english;


    public SuraNameListPojo(String sura_number, String sura_name_bangla, String sura_name_meaning , String sura_name_arbi,String sura_total_ayat,String sura_place,String sura_name_english) {


        this.sura_number = sura_number;
        this.sura_name_bangla = sura_name_bangla;
        this.sura_name_meaning = sura_name_meaning;
        this.sura_name_arbi =sura_name_arbi;
        this.sura_total_ayat = sura_total_ayat;
        this.sura_place = sura_place;
        this.sura_name_english = sura_name_english;
    }

    public String getSura_number() {
        return sura_number;
    }

    public String getSura_name_bangla() {
        return sura_name_bangla;
    }

    public String getSura_name_meaning() {
        return sura_name_meaning;
    }

    public String getSura_name_arbi() {
        return sura_name_arbi;
    }

    public String getSura_total_ayat() {
        return sura_total_ayat;
    }

    public String getSura_place() {
        return sura_place;
    }

    public String getSura_name_english() {
        return sura_name_english;
    }
}
