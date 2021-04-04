package com.rdtl.ad_din.pojo_classes;

public class ramjan_chart_modelClass {

    int id;
    String number;
    String date;


    public ramjan_chart_modelClass(int id, String number, String date) {
        this.id = id;
        this.number = number;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
