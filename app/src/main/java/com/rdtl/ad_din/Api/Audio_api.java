package com.rdtl.ad_din.Api;

import com.rdtl.ad_din.pojo_classes.Audio_list_modelCLass;
import com.rdtl.ad_din.pojo_classes.Value_modelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Audio_api {

    //url
    @GET("fro/audio/search")
    Call<List<Audio_list_modelCLass>> getAudio(@Query("customer_id" ) String customer_id);

    @GET("fro/search/title")
    Call<List<Value_modelClass>> getValue(@Query("title" ) String title);

}
