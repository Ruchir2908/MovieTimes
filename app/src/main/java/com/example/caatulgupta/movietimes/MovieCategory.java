package com.example.caatulgupta.movietimes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieCategory {

    @SerializedName("results")
    ArrayList<Movie> movies;
    @SerializedName("total_results")
    int totalMovies;
    int page;
}
