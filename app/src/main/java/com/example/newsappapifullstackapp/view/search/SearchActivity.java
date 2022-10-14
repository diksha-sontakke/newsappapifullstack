package com.example.newsappapifullstackapp.view.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.newsappapifullstackapp.view.BusinessActivity;
import com.example.newsappapifullstackapp.view.MainActivity;

import newsappapifullstackapp.R;

public class SearchActivity extends AppCompatActivity {


    private ImageView imageBackSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        imageBackSearch=findViewById(R.id.imageBackSearch);


        imageBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}