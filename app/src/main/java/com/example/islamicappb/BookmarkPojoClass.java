package com.example.islamicappb;

public class BookmarkPojoClass {

//    int imgRes;

    String sura_name;
    String ayat_number;
    String sura_arbi_line;
    String sura_spelling_line;
    String sura_meaning_line;


    public BookmarkPojoClass(String sura_name, String ayat_number, String sura_arbi_line , String sura_spelling_line,String sura_meaning_line) {
//        this.imgRes = imgRes;

        this.sura_name = sura_name;
        this.ayat_number = ayat_number;
        this.sura_arbi_line = sura_arbi_line;
        this.sura_spelling_line =sura_spelling_line;
        this.sura_meaning_line =sura_meaning_line;
    }

    public String getSura_name() {
        return sura_name;
    }

    public String getAyat_number() {
        return ayat_number;
    }

    public String getSura_arbi_line() {
        return sura_arbi_line;
    }

    public String getSura_spelling_line() {
        return sura_spelling_line;
    }

    public String getSura_meaning_line() {
        return sura_meaning_line;
    }
}
