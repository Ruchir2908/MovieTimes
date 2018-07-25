package com.example.caatulgupta.movietimes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable{

    @SerializedName("vote_count")
    int voteCount;
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
    @SerializedName("genre_ids")
    ArrayList<Integer> genreIds;
    @SerializedName("backdrop_path")
    String backdropPath;
    boolean adult;
    String overview;
    @SerializedName("release_date")
    String releaseDate;

}
