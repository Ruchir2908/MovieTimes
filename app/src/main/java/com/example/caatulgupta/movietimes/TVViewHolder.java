package com.example.caatulgupta.movietimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class TVViewHolder extends RecyclerView.ViewHolder{

    ImageView image;
    TextView titleTV;

    public TVViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        titleTV = itemView.findViewById(R.id.titleTV);
    }

}
