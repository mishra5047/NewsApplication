package com.example.newsapplication.rests;

import com.example.newsapplication.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("sources") String source, @Query("apiKey") String apiKey);

    @GET("?category=")
    Call<ResponseModel> getNewsbyCategory(@Query("category=") String cat, @Query("apiKey") String api);
}