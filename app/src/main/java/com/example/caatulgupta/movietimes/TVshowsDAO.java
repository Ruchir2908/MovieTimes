package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TVshowsDAO {

    @Insert
    void addShow(TV show);

    @Delete
    void removeShow(TV show);

    @Query("select * from tvdb")
    List<TV> getShows();

    @Query("select id from tvdb")
    List<Integer> getShowIds();

}
