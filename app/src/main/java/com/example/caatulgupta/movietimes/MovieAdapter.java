package com.example.caatulgupta.movietimes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    ArrayList<Movie> movies;
    Context context;

    public MovieAdapter(ArrayList<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View movieLayout = inflater.inflate(R.layout.row_layout,null);
        return new MovieViewHolder(movieLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
//        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show();
        holder.titleTV.setText(movie.title);
        holder.releaseDateTV.setText(movie.releaseDate);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.backdropPath).resize(350,200).centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
