package com.example.caatulgupta.movietimes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieTimesService {

    @GET("movie/{category}")
    Call<MovieCategory> getMovies(@Path("category") String category, @Query("api_key")String key);

}
