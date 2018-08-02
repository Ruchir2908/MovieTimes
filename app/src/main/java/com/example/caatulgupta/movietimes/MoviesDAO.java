package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.media.midi.MidiOutputPort;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MoviesDAO {

    @Insert
    void addMovie(Movie movie);

    @Delete
    void removeMovie(Movie movie);

    @Query("select * from moviedb")
    List<Movie> getMovies();

    @Query("select id from moviedb")
    List<Integer> getMovieIds();

}
