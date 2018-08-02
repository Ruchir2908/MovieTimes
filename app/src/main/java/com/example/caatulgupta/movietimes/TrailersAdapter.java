package com.example.caatulgupta.movietimes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersViewHolder> {

    ArrayList<Videos> videos;
    Context context;

    public TrailersAdapter(ArrayList<Videos> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.tv_row_layout,null);
//        View layout = inflater.inflate(R.layout.trailer_row_layout,null);
        return new TrailersViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        final Videos video = videos.get(position);
        if(!video.type.equals("Trailer")){
            return;
        }
        Picasso.get().load("http://img.youtube.com/vi/"+video.key+"/hqdefault.jpg").resize(550,400).centerCrop().into(holder.thumbnail);
        holder.textView.setText(video.name);

//        holder.playerView

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
//                builder.setTitle(video.name);
//                builder.setView(R.layout.you_tube_player);
//                android.support.v7.app.AlertDialog dialog = builder.create();
//                dialog.show();

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.key));
//                Intent webIntent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("http://www.youtube.com/watch?v=" + id));
//                try {
                    context.startActivity(appIntent);
//                } catch (ActivityNotFoundException ex) {
//                    context.startActivity(webIntent);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

}
