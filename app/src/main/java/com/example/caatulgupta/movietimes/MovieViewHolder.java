package com.example.caatulgupta.movietimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView titleTV, releaseDateTV;


    public MovieViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        titleTV = itemView.findViewById(R.id.titleTV);
        releaseDateTV = itemView.findViewById(R.id.releaseDateTV);
    }
}
