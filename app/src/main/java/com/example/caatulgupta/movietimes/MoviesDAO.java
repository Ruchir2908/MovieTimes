package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.media.midi.MidiOutputPort;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MoviesDAO {

    @Insert
    void addMovie(Movie movie);

    @Insert
    void addMovies(ArrayList<Movie> movies);

    @Delete
    void removeMovie(Movie movie);

    @Delete
    void removeMovies(ArrayList<Movie> movies);

    @Query("select * from moviedb")
    List<Movie> getMovies();

    @Query("select id from moviedb")
    List<Integer> getMovieIds();

}
