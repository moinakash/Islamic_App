package com.example.islamicappb;

public class HadisTypePojoList {

    String HadisType;
    String HadisType_Id;

    public HadisTypePojoList(String hadisType, String hadisType_Id) {
        HadisType = hadisType;
        HadisType_Id = hadisType_Id;
    }

    public String getHadisType() {
        return HadisType;
    }

    public String getHadisType_Id() {
        return HadisType_Id;
    }
}
