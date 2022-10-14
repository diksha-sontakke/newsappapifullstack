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
import java.util.List;

import newsappapifullstackapp.R;

public class NewsSearchAdapter extends RecyclerView.Adapter<NewsSearchAdapter.NewsSearchViewHolder> {


    Context context;
    private List<NewsArticle> newsArticlesForSearch = new ArrayList<>();



    public NewsSearchAdapter(Context context, List<NewsArticle> newsArticlesForSearch) {
        this.context = context;
        this.newsArticlesForSearch = newsArticlesForSearch;
    }

    public void setResults(List<NewsArticle> newsArticlesForSearch){
        this.newsArticlesForSearch=newsArticlesForSearch;
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public NewsSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);


        return new NewsSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsSearchViewHolder holder, int position) {


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);

                intent.putExtra("source", newsArticlesForSearch.get(holder.getAdapterPosition()).getSource().getId());
                intent.putExtra("urlLoad", newsArticlesForSearch.get(holder.getAdapterPosition()).getUrl());
                intent.putExtra("author",newsArticlesForSearch.get(holder.getAdapterPosition()).getAuthor());
                intent.putExtra("title", newsArticlesForSearch.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("description", newsArticlesForSearch.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("image", newsArticlesForSearch.get(holder.getAdapterPosition()).getUrlToImage());
                intent.putExtra("published", newsArticlesForSearch.get(holder.getAdapterPosition()).getPublishedAt());
                context.startActivity(intent);

            }
        });

        holder.newsName.setText(newsArticlesForSearch.get(position).getTitle());
        holder.newsDescription.setText(newsArticlesForSearch.get(position).getDescription());
        holder.newsSource.setText(newsArticlesForSearch.get(position).getSource().getName());
        Picasso.get().load(newsArticlesForSearch.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return newsArticlesForSearch.size();
    }

    class NewsSearchViewHolder extends RecyclerView.ViewHolder {

        TextView newsName;
        TextView newsDescription;
        TextView newsSource;
        ImageView imageView;
        CardView cardView;


        public NewsSearchViewHolder(@NonNull View itemView) {
            super(itemView);

            newsName = itemView.findViewById(R.id.newsName);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsSource=itemView.findViewById(R.id.newsSource);
            imageView = itemView.findViewById(R.id.imageNews);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
