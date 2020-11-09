package com.rdtl.ad_din.pojo_classes;

public class HadisLinePartPojo {

    String hadis_arbi;
    String hadis_bangla;
    String hadis_utso;
    String hadis_index;

    public HadisLinePartPojo(String hadis_arbi, String hadis_bangla, String hadis_utso, String hadis_index) {
        this.hadis_arbi = hadis_arbi;
        this.hadis_bangla = hadis_bangla;
        this.hadis_utso = hadis_utso;
        this.hadis_index = hadis_index;
    }

    public String getHadis_arbi() {
        return hadis_arbi;
    }

    public String getHadis_bangla() {
        return hadis_bangla;
    }

    public String getHadis_utso() {
        return hadis_utso;
    }

    public String getHadis_index() {
        return hadis_index;
    }
}
