package com.example.caatulgupta.movietimes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieTimesService {

    @GET("movie/{category}")
    Call<MovieCategory> getMovies(@Path("category") String category, @Query("api_key")String key, @Query("page")int page);

    @GET("movie/{movieId}/similar")
    Call<MovieCategory> getSimilarMovies(@Path("movieId")int movieId,@Query("api_key")String key);

    @GET("movie/{movieId}/recommendations")
    Call<MovieCategory> getRecommendations(@Path("movieId")int id,@Query("api_key")String key);

    @GET("movie/{movieId}/videos")
    Call<Trailers> getVideos(@Path("movieId")int id, @Query("api_key")String key);

    @GET("movie/{movieId}/credits")
    Call<CastCrew> getCast(@Path("movieId")int id, @Query("api_key")String key);

    @GET("person/{pid}/movie_credits")
    Call<MovieCredits> getCastsMovie(@Path("pid")int id, @Query("api_key")String key);

    @GET("multi")
    Call<SearchResponse> search(@Query("api_key")String key, @Query("query")String query);

    @GET("person/{pid}")
    Call<Person> getPerson(@Path("pid")int pid, @Query("api_key")String key);

}
