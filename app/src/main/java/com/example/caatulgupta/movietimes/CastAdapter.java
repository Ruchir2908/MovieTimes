package com.example.caatulgupta.movietimes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastViewHolder> {

    ArrayList<Cast> casts;
    Context context;

    public CastAdapter(ArrayList<Cast> casts, Context context) {
        this.casts = casts;
        this.context = context;
    }


    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View output = inflater.inflate(R.layout.cast_layout,null);
        return new CastViewHolder(output);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = casts.get(position);
        holder.name.setText(cast.name);
        holder.charName.setText(cast.characterName);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+cast.profilePath).resize(360,200).centerCrop().into(holder.profile);
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }
}
