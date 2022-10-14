package com.example.newsappapifullstackapp.network;

import com.example.newsappapifullstackapp.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRetrofitApi {


    // https://newsapi.org/v2/top-headlines?country=us&apiKey="add your api key"

    //https://newsapi.org/v2/top-headlines?country=us&apiKey="add your api key"

    //https://newsapi.org/v2/


    @GET("top-headlines")
    Call<NewsResponse> getNewsList(@Query("q") String query,
                                   @Query("category") String newsCategory,
                                   @Query("country")   String newsCountry,

                                   @Query("apiKey") String apiKey);



    @GET("top-headlines")
    Call<NewsResponse> getSearch(@Query("q") String query,
                                 @Query("country")   String newsCountry,
                                 @Query("apiKey") String apiKey);







}
