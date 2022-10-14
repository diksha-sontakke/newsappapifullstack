package com.example.newsappapifullstackapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;


import com.example.newsappapifullstackapp.adapter.NewsAdapter;
import com.example.newsappapifullstackapp.firebase.EditProfileActivity;
import com.example.newsappapifullstackapp.model.NewsArticle;
import com.example.newsappapifullstackapp.view.search.SearchActivity;
import com.example.newsappapifullstackapp.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import newsappapifullstackapp.R;

public class MainActivity extends AppCompatActivity {

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    NewsViewModel newsViewModel;

    ImageView searchImage;

    ImageView imageMenu;


    String api="";  //add your api key

    AppCompatButton business,entertainment,health,science,sports,technology;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        recyclerView = findViewById(R.id.recyclerViewNews);

        searchImage=findViewById(R.id.searchImage);



        business=findViewById(R.id.business);
        business.setOnClickListener(this::onClick);

        entertainment=findViewById(R.id.entertainment);
        entertainment.setOnClickListener(this::onClick);

        health=findViewById(R.id.health);
        health.setOnClickListener(this::onClick);

        science=findViewById(R.id.science);
        science.setOnClickListener(this::onClick);

        sports=findViewById(R.id.sports);
        sports.setOnClickListener(this::onClick);

        technology=findViewById(R.id.technology);
        technology.setOnClickListener(this::onClick);

        imageMenu=findViewById(R.id.menuImage);




        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init();

        newsViewModel.newsListData(null,"general","us", api);

        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            List<NewsArticle> newsArticles = newsResponse.getArticles();
            articleArrayList.addAll(newsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();

        popMenuImageButton();

        onSearchPressedImage();

    }



    private void onSearchPressedImage() {
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, SearchActivity.class);


                // Intent intent = new Intent(MainActivity.this, SearchFragment.class);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    public void popMenuImageButton(){
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu= new PopupMenu(MainActivity.this,imageMenu);
                popupMenu.getMenuInflater().inflate(R.menu.home_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.editProfile:
                                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                                overridePendingTransition(0, 0);
                                return true;
                            default:
                                return  false;


                        }

                    }
                });

                popupMenu.show();
            }
        });


    }


    private void onClick(View v) {

        switch (v.getId()) {
            case R.id.business:
                Intent intent = new Intent(this, BusinessActivity.class);
                this.startActivity(intent);
                break;

            case R.id.entertainment:

                Intent intent_ent = new Intent(this, EntertainmentActivity.class);
                this.startActivity(intent_ent);
                break;

            case R.id.health:
                Intent intent_one = new Intent(this, HealthActivity.class);
                this.startActivity(intent_one);
                break;


            case R.id.science:
                Intent intent_two = new Intent(this, ScienceActivity.class);
                this.startActivity(intent_two);
                break;
            case R.id.sports:

                Intent intent_three = new Intent(this, SportsActivity.class);
                this.startActivity(intent_three);
                break;
            case R.id.technology:
                Intent intent_four = new Intent(this, TechnologyActivity.class);
                this.startActivity(intent_four);
                break;

        }
    }





    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }







}