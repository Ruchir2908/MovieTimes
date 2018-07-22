package com.example.caatulgupta.movietimes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

class TV {

    String name;
    @SerializedName("genre_ids")
    ArrayList<Integer> genres;
    float popularity;
    @SerializedName("vote_count")
    int voteCount;
    @SerializedName("first_air_date")
    String airDate;
    @SerializedName("backdrop_path")
    String backdropPath;
    int id;
    @SerializedName("vote_average")
    float avgVote;
    @SerializedName("poster_path")
    String posterPath;
    String overview;

}
