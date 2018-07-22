package com.example.caatulgupta.movietimes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    ArrayList<Movie> movies;
    Context context;
    int type;

    public MovieAdapter(ArrayList<Movie> movies, Context context, int type) {
        this.movies = movies;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View movieLayout;
        if(type == 0){
             movieLayout = inflater.inflate(R.layout.nowshowing_row_layout,null);
        }else{
            movieLayout = inflater.inflate(R.layout.all_row_layout,null);
        }

        return new MovieViewHolder(movieLayout,type);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
//        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show();
        if(type==0){
            holder.titleTV.setText(movie.title);
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.backdropPath).resize(360,200).centerCrop().into(holder.image);
        }else{
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).resize(70,80).centerCrop().into(holder.posterImageView);
            holder.nameTV.setText(movie.title);
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
