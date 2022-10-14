package com.example.newsappapifullstackapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.newsappapifullstackapp.model.NewsArticle;
import com.example.newsappapifullstackapp.view.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import newsappapifullstackapp.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{


    Context context;
    ArrayList<NewsArticle> articles;


    public NewsAdapter(Context context, ArrayList<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    public void setResultForSearch( ArrayList<NewsArticle> articles){
        this.articles = articles;

    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new  NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, DetailsActivity.class);

                intent.putExtra("source", articles.get(holder.getAdapterPosition()).getSource().getId());
               intent.putExtra("urlLoad", articles.get(holder.getAdapterPosition()).getUrl());
               intent.putExtra("author", articles.get(holder.getAdapterPosition()).getAuthor());
               intent.putExtra("title", articles.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("description", articles.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("image", articles.get(holder.getAdapterPosition()).getUrlToImage());
             intent.putExtra("published", articles.get(holder.getAdapterPosition()).getPublishedAt());
             context.startActivity(intent);

            }
        });

        holder.newsName.setText(articles.get(position).getTitle());
        holder.newsDescription.setText(articles.get(position).getDescription());
        holder.newsSource.setText(articles.get(position).getSource().getName());
        Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
       return articles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView newsName;
        TextView newsDescription;
        TextView newsSource;
        ImageView imageView;
        CardView cardView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            newsName = itemView.findViewById(R.id.newsName);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsSource=itemView.findViewById(R.id.newsSource);
            imageView = itemView.findViewById(R.id.imageNews);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
