package com.example.caatulgupta.movietimes;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {TV.class},version = 1)
public abstract class TVDatabase extends RoomDatabase{

    abstract TVshowsDAO getTVshowDAO();
//    abstract TVshowsDAO getWatchedTVshowDAO();

}
