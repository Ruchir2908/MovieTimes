package com.example.caatulgupta.movietimes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVTimesService {

    @GET("tv/{category}")
    Call<TVCategory> getTVShows(@Path("category")String category, @Query("api_key")String key);

    @GET("tv/{tvId}/similar")
    Call<TVCategory> getSimilarTVShows(@Path("tvId")int tvId,@Query("api_key")String key);

    @GET("tv/{tvId}/recommendations")
    Call<TVCategory> getRecommendations(@Path("tvId")int id,@Query("api_key")String key);

    @GET("tv/{tvId}/credits")
    Call<CastCrew> getCast(@Path("tvId")int id,@Query("api_key")String key);

    @GET("person/{pid}/tv_credits")
    Call<CastCrew> getCastsTVShows(@Path("pid")int id, @Query("api_key")String key);

}