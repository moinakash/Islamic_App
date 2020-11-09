package com.rdtl.ad_din.pojo_classes;

public class AllahNamePojoClass {

    String allah_name_bangla;
    String allah_name_arbi;
    String allah_name_meaning;
    String allah_name_number;

    public AllahNamePojoClass(String allah_name_bangla, String allah_name_arbi, String allah_name_meaning,String allah_name_number) {
        this.allah_name_bangla = allah_name_bangla;
        this.allah_name_arbi = allah_name_arbi;
        this.allah_name_meaning = allah_name_meaning;
        this.allah_name_number = allah_name_number;
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

    public String getAllah_name_number() {
        return allah_name_number;
    }
}
