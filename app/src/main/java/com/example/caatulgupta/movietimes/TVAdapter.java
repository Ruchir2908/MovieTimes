package com.example.caatulgupta.movietimes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TVAdapter extends RecyclerView.Adapter<TVViewHolder> {

    ArrayList<TV> shows;
    Context context;

    public TVAdapter( ArrayList<TV> shows,Context context) {
        this.context = context;
        this.shows = shows;
    }

    @NonNull
    @Override
    public TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tvLayout = inflater.inflate(R.layout.tv_row_layout,null);
        return new TVViewHolder(tvLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull TVViewHolder holder, int position) {
        TV show = shows.get(position);
        holder.titleTV.setText(show.name);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+show.backdropPath).resize(70,80).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }
}
