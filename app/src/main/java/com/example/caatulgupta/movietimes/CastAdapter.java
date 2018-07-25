package com.example.caatulgupta.movietimes;

import android.content.Context;
import android.content.Intent;
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
    public void onBindViewHolder(@NonNull CastViewHolder holder, final int position) {
        Cast cast = casts.get(position);
        holder.name.setText(cast.name);
        holder.charName.setText(cast.character);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+cast.profilePath).resize(360,200).centerCrop().into(holder.profile);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CastDetails.class);
                intent.putExtra("cast",casts.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return casts.size();
    }
}
