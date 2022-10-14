package com.example.newsappapifullstackapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsappapifullstackapp.model.NewsResponse;
import com.example.newsappapifullstackapp.repository.NewsRepository;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;
    String api="2c50d67d16014237bced210c85c280fc";

    public void init(){
        if (mutableLiveData != null){

            return;
        }
        newsRepository = NewsRepository.getInstance();
    }



    public void newsListData(String query,String newsCategory,String newsCountry, String key){
        mutableLiveData = newsRepository.getNews(query,newsCategory,newsCountry, key);

    }






    public LiveData<NewsResponse> getNewsRepository() {
        return mutableLiveData;
    }





}
