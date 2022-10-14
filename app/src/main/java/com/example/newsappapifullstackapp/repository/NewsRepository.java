package com.example.newsappapifullstackapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.newsappapifullstackapp.model.NewsResponse;
import com.example.newsappapifullstackapp.network.NewsRetrofitApi;
import com.example.newsappapifullstackapp.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsRetrofitApi newsApi;


    public NewsRepository(){
        newsApi = RetrofitService.createService(NewsRetrofitApi.class);
    }


    public MutableLiveData<NewsResponse> getNews(String query,String newsCategory, String newsCountry, String key){
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getNewsList(query,newsCategory,newsCountry, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }








}
