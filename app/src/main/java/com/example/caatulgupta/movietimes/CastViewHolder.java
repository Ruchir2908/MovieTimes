package com.example.caatulgupta.movietimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class CastViewHolder extends RecyclerView.ViewHolder{

    ImageView profile;
    TextView name, charName;

    public CastViewHolder(View itemView) {
        super(itemView);
        profile = itemView.findViewById(R.id.profileImageView);
        name = itemView.findViewById(R.id.nameTextView);
        charName = itemView.findViewById(R.id.charTextView);
    }
}
