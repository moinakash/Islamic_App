package com.example.islamicappb;

public class SuraLinePartPojo {


    String sura_arbi;
    String sura_bangla;
    String sura_bangla_meaning;
    String sura_number;

    public SuraLinePartPojo(String sura_arbi, String sura_bangla, String sura_bangla_meaning, String sura_number) {
        this.sura_arbi = sura_arbi;
        this.sura_bangla = sura_bangla;
        this.sura_bangla_meaning = sura_bangla_meaning;
        this.sura_number = sura_number;
    }

    public String getSura_arbi() {
        return sura_arbi;
    }

    public String getSura_bangla() {
        return sura_bangla;
    }

    public String getSura_bangla_meaning() {
        return sura_bangla_meaning;
    }

    public String getSura_number() {
        return sura_number;
    }
}
