package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMovies extends Fragment {

    MoviesDAO moviesDAO;

    public FavouriteMovies() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.all_row_layout,container,false);

        MovieDatabase movieDatabase = Room.databaseBuilder(getContext().getApplicationContext(),MovieDatabase.class,"moviedb").allowMainThreadQueries().build();
        moviesDAO = movieDatabase.getMovieDAO();

        List<Movie> movies = moviesDAO.getMovies();


        return output;
    }

}
