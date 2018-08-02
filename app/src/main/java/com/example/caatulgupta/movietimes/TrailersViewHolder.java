package com.example.caatulgupta.movietimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerView;

class TrailersViewHolder extends RecyclerView.ViewHolder{

    ImageView thumbnail;
    TextView textView;
    YouTubePlayerView playerView;

    public TrailersViewHolder(View itemView) {
        super(itemView);
        thumbnail = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.titleTV);
        playerView = itemView.findViewById(R.id.youtube_player);

    }


}
