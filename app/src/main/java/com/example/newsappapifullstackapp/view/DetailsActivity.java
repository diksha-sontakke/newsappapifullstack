package com.example.newsappapifullstackapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.newsappapifullstackapp.model.NewsArticle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import newsappapifullstackapp.R;

public class DetailsActivity extends AppCompatActivity {


    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();


    private NewsArticle newsArticle;

    String api="";//add your api key
    String source;

    Intent intent;


    AppCompatButton urlButton;
    ImageView imageBackBusiness,imageDetailNews;
    TextView titleDetails,authorDetails,descriptionDetails,publishedAtDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );


        urlButton=findViewById(R.id.urlButton);

        imageDetailNews=findViewById(R.id.imageDetailNews);
        imageBackBusiness=findViewById(R.id.imageBackBusiness);

        titleDetails=findViewById(R.id.titleDetail);
        authorDetails=findViewById(R.id.authorDetail);
        descriptionDetails=findViewById(R.id.descriptionDetails);
        publishedAtDetails=findViewById(R.id.publishAt);


        // newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        //        newsViewModel.init();

        intent=getIntent();
        source= intent.getStringExtra("source");

        authorDetails.setText(intent.getStringExtra("author"));
        titleDetails.setText(intent.getStringExtra("title"));
        descriptionDetails.setText(intent.getStringExtra("description"));
        publishedAtDetails.setText(intent.getStringExtra("published"));
        Picasso.get().load(intent.getStringExtra("image")).into(imageDetailNews);




        urlDetailButtonWebView();
        onBackPressedImage();



    }

    private void urlDetailButtonWebView(){
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, WebViewActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private void onBackPressedImage() {
        imageBackBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}