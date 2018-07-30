package com.example.caatulgupta.movietimes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "tvdb")
public class TV implements Serializable{

    String name;
    @Ignore
    @SerializedName("genre_ids")
    ArrayList<Integer> genres;
    float popularity;
    @SerializedName("vote_count")
    int voteCount;
    @SerializedName("first_air_date")
    String airDate;
    @SerializedName("backdrop_path")
    String backdropPath;
    @PrimaryKey
    int id;
    @SerializedName("vote_average")
    float avgVote;
    @SerializedName("poster_path")
    String posterPath;
    String overview;

}
