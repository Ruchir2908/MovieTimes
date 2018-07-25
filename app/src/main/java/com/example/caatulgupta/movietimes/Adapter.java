package com.example.caatulgupta.movietimes;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<Movie> movies;
    ArrayList<TV> shows;
    Context context;
    int layoutType;
    String type;

    public Adapter(ArrayList<Movie> movies,ArrayList<TV> shows, Context context, int layoutType, String type) {
        if(type.equals("movie")){
            this.movies = movies;
        }else{
            this.shows = shows;
        }
        this.movies = movies;
        this.context = context;
        this.layoutType = layoutType;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View Layout;
        if(layoutType == 0){
             Layout = inflater.inflate(R.layout.nowshowing_row_layout,null);
        }else{
            Layout = inflater.inflate(R.layout.all_row_layout,null);
        }

        return new ViewHolder(Layout,layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(type.equals("movie")){
            Movie movie = movies.get(position);
            if(layoutType==0){
                holder.titleTV.setText(movie.title);
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.backdropPath).resize(360,200).centerCrop().into(holder.image);
            }else{
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).resize(70,80).centerCrop().into(holder.posterImageView);
                holder.nameTV.setText(movie.title);
            }
        }else{
            TV show = shows.get(position);
            if(layoutType==0){
                holder.titleTV.setText(show.name);
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+show.backdropPath).resize(360,200).centerCrop().into(holder.image);
            }else{
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+show.posterPath).resize(70,80).centerCrop().into(holder.posterImageView);
                holder.nameTV.setText(show.name);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, movies.get(position).title, Toast.LENGTH_SHORT).show();
                if(type.equals("movie")){
                    Movie movie = movies.get(position);
                    Intent intent = new Intent(context,MovieDetails.class);
                    intent.putExtra("movie",movie);
                    context.startActivity(intent);
                }else{
                    TV show = shows.get(position);
                    Intent intent = new Intent(context,TVDetails.class);
                    intent.putExtra("show",show);
                    context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if(type.equals("movie")){
            return movies.size();
        }else{
            return shows.size();
        }

    }
}
