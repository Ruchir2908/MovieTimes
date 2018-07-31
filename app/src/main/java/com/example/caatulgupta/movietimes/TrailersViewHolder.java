package com.example.caatulgupta.movietimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class TrailersViewHolder extends RecyclerView.ViewHolder{

    ImageView thumbnail;
    TextView textView;

    public TrailersViewHolder(View itemView) {
        super(itemView);
        thumbnail = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.titleTV);


    }


}
