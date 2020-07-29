package com.example.islamicappb;

public class PojoClassForKalima {

    private String number, arbitext, uccharontext, orthotext;


    public PojoClassForKalima(String number, String arbitext, String uccharontext, String orthotext) {
        this.number = number;
        this.arbitext = arbitext;
        this.uccharontext = uccharontext;
        this.orthotext = orthotext;

    }

    public String getArbitext() {
        return arbitext;
    }

    public String getUccharontext() {
        return uccharontext;
    }

    public String getNumber() {
        return number;
    }
    public String getOrtho() {
        return orthotext;
    }



}
