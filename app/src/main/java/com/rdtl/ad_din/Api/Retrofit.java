package com.rdtl.ad_din.Api;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;


public class Retrofit {
   // String baseUrl = "http://127.0.0.1:8000/";
    String baseUrl = "https://dailyislam.amaderkagoj.com/";



    Context context;

    public <T> T getService(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass);
    }

    public synchronized retrofit2.Retrofit getRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        OkHttpClient client = httpClient.build();
        return new retrofit2.Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}