package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class FavouriteMovies extends Fragment {

    MoviesDAO moviesDAO;
    RecyclerView favMoviesRV;
    Adapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();

    public FavouriteMovies() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.all_row_layout,container,false);

        MovieDatabase movieDatabase = Room.databaseBuilder(getContext().getApplicationContext(),MovieDatabase.class,"moviedb").allowMainThreadQueries().build();
        moviesDAO = movieDatabase.getMovieDAO();

        ArrayList<Movie> movies = (ArrayList)moviesDAO.getMovies();
        adapter = new Adapter(movies,null,getContext(),1,"movie");
        favMoviesRV.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        favMoviesRV.setLayoutManager(layoutManager);


        return output;
    }

}
