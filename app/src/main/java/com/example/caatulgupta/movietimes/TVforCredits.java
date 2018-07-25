
package com.example.caatulgupta.movietimes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVforCredits {

    int id;
    String name;
    @SerializedName("genre_ids")
    List<Integer> genreIds;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("vote_count")
    int voteCount;
    @SerializedName("vote_average")
    float avgVote;
    @SerializedName("episode_count")
    int episodeCount;
    @SerializedName("first_air_date")
    String firstAirDate;
    @SerializedName("backdrop_path")
    String backdropPath;
    String overview;

}
