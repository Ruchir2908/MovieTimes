package com.example.caatulgupta.movietimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView image,posterImageView;
    TextView titleTV, releaseDateTV, nameTV;


    public ViewHolder(View itemView, int type) {
        super(itemView);
        if(type==0){
            image = itemView.findViewById(R.id.image);
            titleTV = itemView.findViewById(R.id.titleTV);
        }else{
            posterImageView = itemView.findViewById(R.id.posterImageView);
            nameTV = itemView.findViewById(R.id.nameTextView);
        }

    }
}
