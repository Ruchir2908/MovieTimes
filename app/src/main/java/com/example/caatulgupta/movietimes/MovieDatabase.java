package com.example.caatulgupta.movietimes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Movie.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    abstract MoviesDAO getMovieDAO();
//    abstract MoviesDAO getWatchedMovieDAO();

}
