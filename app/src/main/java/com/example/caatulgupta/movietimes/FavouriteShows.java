package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class FavouriteShows extends Fragment {

    TVshowsDAO tVshowsDAO;
    RecyclerView recyclerView;
    TextView nothingToShow;
    ImageView empty;
    Adapter adapter;

    public FavouriteShows() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.navigation_items_layout,container,false);
        recyclerView = output.findViewById(R.id.RecyclerView);
        nothingToShow = output.findViewById(R.id.nothingToShow);
        empty = output.findViewById(R.id.empty);

        TVDatabase tvDatabase = Room.databaseBuilder(getContext().getApplicationContext(),TVDatabase.class,"tvdb").allowMainThreadQueries().build();
        tVshowsDAO = tvDatabase.getTVshowDAO();

        ArrayList<TV> shows = (ArrayList)tVshowsDAO.getShows();
        if(shows.isEmpty()){
            nothingToShow.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);

        }else{
            nothingToShow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
        adapter = new Adapter(null,shows,getContext(),1,"TV");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        return output;
    }

}
