package com.example.newsappapifullstackapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsappapifullstackapp.model.NewsResponse;
import com.example.newsappapifullstackapp.network.NewsRetrofitApi;
import com.example.newsappapifullstackapp.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchNewsRepository {


    private NewsRetrofitApi newsApi;

    private MutableLiveData<NewsResponse> newsSearchResponseMutableLiveData;

    public SearchNewsRepository() {
        newsSearchResponseMutableLiveData = new MutableLiveData<>();
        newsApi = RetrofitService.createService(NewsRetrofitApi.class);
    }


    public void  searchNews(String query, String newsCountry, String key){
        newsApi.getSearch(query, newsCountry, key)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if(response.body() != null){

                            newsSearchResponseMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        newsSearchResponseMutableLiveData.postValue(null);

                    }
                });



    }

    public LiveData<NewsResponse> getSearchResponseLiveData(){
        return  newsSearchResponseMutableLiveData;
    }
}
