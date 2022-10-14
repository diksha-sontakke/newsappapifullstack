package com.example.newsappapifullstackapp.view.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.newsappapifullstackapp.adapter.NewsSearchAdapter;
import com.example.newsappapifullstackapp.model.NewsArticle;
import com.example.newsappapifullstackapp.model.NewsResponse;
import com.example.newsappapifullstackapp.view.BusinessActivity;
import com.example.newsappapifullstackapp.view.MainActivity;
import com.example.newsappapifullstackapp.viewmodel.SearchNewsViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import newsappapifullstackapp.R;


public class SearchFragment extends Fragment {

    ArrayList<NewsArticle> articleSearchArrayList = new ArrayList<>();
    String api="";  //add your api key
    private SearchNewsViewModel searchNewsViewModel;
    private NewsSearchAdapter newsSearchAdapter;


    private ImageView searchImageViewButton;
    private TextInputEditText searchTextInputEditText;
    RecyclerView recyclerView;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        searchNewsViewModel = ViewModelProviders.of(this).get(SearchNewsViewModel.class);
        searchNewsViewModel.init();
        searchNewsViewModel.getNewsResponseViewModelLiveData().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                if(newsResponse != null){
                    newsSearchAdapter.setResults(newsResponse.getArticles());
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        newsSearchAdapter = new NewsSearchAdapter(container.getContext(),articleSearchArrayList);
        recyclerView = view.findViewById(R.id.recyclerViewNewsForSearch);

        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(newsSearchAdapter);

        searchTextInputEditText=view.findViewById(R.id.searchTextInputEditText);
        searchImageViewButton=view.findViewById(R.id.searchImageView);




        searchImageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });


        return view;
    }

    public  void performSearch(){

        String keyword = searchTextInputEditText.getEditableText().toString();
        searchNewsViewModel.searchNews(keyword,"us",api);


    }



}