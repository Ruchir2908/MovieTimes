package com.example.caatulgupta.movietimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class ReviewsViewHolder extends RecyclerView.ViewHolder{

    TextView reviewNameTV, reviewTV;

    public ReviewsViewHolder(View itemView) {
        super(itemView);
        reviewNameTV = itemView.findViewById(R.id.reviewNameTV);
        reviewTV = itemView.findViewById(R.id.reviewTV);
    }
}
