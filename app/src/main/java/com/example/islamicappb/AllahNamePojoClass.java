package com.example.islamicappb;

public class AllahNamePojoClass {

    String allah_name_bangla;
    String allah_name_arbi;
    String allah_name_meaning;

    public AllahNamePojoClass(String allah_name_bangla, String allah_name_arbi, String allah_name_meaning) {
        this.allah_name_bangla = allah_name_bangla;
        this.allah_name_arbi = allah_name_arbi;
        this.allah_name_meaning = allah_name_meaning;
    }

    public String getAllah_name_bangla() {
        return allah_name_bangla;
    }

    public String getAllah_name_arbi() {
        return allah_name_arbi;
    }

    public String getAllah_name_meaning() {
        return allah_name_meaning;
    }
}
