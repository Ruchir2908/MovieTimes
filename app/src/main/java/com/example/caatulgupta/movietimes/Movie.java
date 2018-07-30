package com.example.caatulgupta.movietimes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "moviedb")
public class Movie implements Serializable{

    @SerializedName("vote_count")
    int voteCount;
    @PrimaryKey
    int id;
    boolean video;
    @SerializedName("vote_average")
    float avgVote;
    String title;
    float popularity;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("original_language")
    String language;
    @SerializedName("original_title")
    String originalTitle;
    @Ignore
    @SerializedName("genre_ids")
    ArrayList<Integer> genreIds;
    @SerializedName("backdrop_path")
    String backdropPath;
    boolean adult;
    String overview;
    @SerializedName("release_date")
    String releaseDate;

}
