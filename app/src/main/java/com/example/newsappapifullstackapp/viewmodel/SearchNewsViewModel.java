package com.example.newsappapifullstackapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsappapifullstackapp.model.NewsResponse;
import com.example.newsappapifullstackapp.repository.SearchNewsRepository;

public class SearchNewsViewModel   extends AndroidViewModel {

    private SearchNewsRepository searchNewsRepository;
    private LiveData<NewsResponse> NewsResponseViewModelLiveData;

    String api="2c50d67d16014237bced210c85c280fc";


    public  SearchNewsViewModel(@NonNull Application application){
        super(application);
    }


    public void init(){
        searchNewsRepository = new SearchNewsRepository();
        NewsResponseViewModelLiveData = searchNewsRepository.getSearchResponseLiveData();
    }


    public void  searchNews(String query, String newsCountry, String key) {
        searchNewsRepository.searchNews(query, newsCountry, key);
    }


    public LiveData<NewsResponse>  getNewsResponseViewModelLiveData(){
        return NewsResponseViewModelLiveData;
    }




}
