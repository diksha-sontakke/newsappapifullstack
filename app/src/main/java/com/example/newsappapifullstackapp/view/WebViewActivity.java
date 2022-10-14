package com.example.newsappapifullstackapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;



import newsappapifullstackapp.R;

public class WebViewActivity extends AppCompatActivity {


    ImageView backHome;
    WebView webView;
    private ProgressDialog progDailog;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        progDailog = ProgressDialog.show(WebViewActivity.this, "Loading","Please wait...", true);
        progDailog.setCancelable(false);

        backHome=findViewById(R.id.imageBackWebView);
        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        //Intent intent=getParentActivityIntent();
        url = intent.getStringExtra("urlLoad");



        webView.getSettings().setJavaScriptEnabled(true);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);

        //webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });

        webView.loadUrl("https://us.cnn.com/");

        //webView.loadUrl(url);




        onBackPressedImage();
    }


    private void onBackPressedImage() {
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}