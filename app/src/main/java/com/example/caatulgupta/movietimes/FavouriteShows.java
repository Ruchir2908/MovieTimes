package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
public class FavouriteShows extends Fragment {

    TVshowsDAO tVshowsDAO;
    RecyclerView recyclerView;
    public static Adapter adapter;

    public FavouriteShows() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.navigation_items_layout,container,false);
        recyclerView = output.findViewById(R.id.RecyclerView);

        TVDatabase tvDatabase = Room.databaseBuilder(getContext().getApplicationContext(),TVDatabase.class,"tvdb").allowMainThreadQueries().build();
        tVshowsDAO = tvDatabase.getTVshowDAO();

        ArrayList<TV> shows = (ArrayList)tVshowsDAO.getShows();
        adapter = new Adapter(null,shows,getContext(),1,"TV");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        return output;
    }

}
