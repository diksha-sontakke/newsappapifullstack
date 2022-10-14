package com.example.newsappapifullstackapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import com.example.newsappapifullstackapp.adapter.NewsAdapter;
import com.example.newsappapifullstackapp.model.NewsArticle;
import com.example.newsappapifullstackapp.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import newsappapifullstackapp.R;

public class TechnologyActivity extends AppCompatActivity {

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    NewsViewModel newsViewModel;
    ImageView backHome;

    String api=""; //add your api key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

       // getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        recyclerView = findViewById(R.id.recyclerViewNews);
        backHome=findViewById(R.id.imageBackTechno);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init();

        newsViewModel.newsListData(null,"technology","us", api);

        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            List<NewsArticle> newsArticles = newsResponse.getArticles();
            articleArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();
        onBackPressedImage();
    }



    private void onBackPressedImage() {
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TechnologyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(TechnologyActivity.this, articleArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }
}