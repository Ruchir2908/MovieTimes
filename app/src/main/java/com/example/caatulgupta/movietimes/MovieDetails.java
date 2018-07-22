package com.example.caatulgupta.movietimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class MovieDetails extends AppCompatActivity {

    TextView releaseDateTV, languageTV, ratingTV, genresTV, overviewTV;
    RecyclerView trailersRV, castRV, recommendationsRV, similarRV;

    Retrofit retrofit;
    MovieTimesService service;
    MovieAdapter similarAdapter, trailerAdapter, recommendationsAdapter;
    CastAdapter castAdapter;
    ArrayList<Movie> similarMovies = new ArrayList<>();
    ArrayList<Movie> recommendationsMovies = new ArrayList<>();
    ArrayList<Cast> casts = new ArrayList<>();

    void findById(){
        releaseDateTV = findViewById(R.id.releaseDateTV);
        languageTV = findViewById(R.id.languageTV);
        ratingTV = findViewById(R.id.ratingTV);
        genresTV = findViewById(R.id.genreTV);
        overviewTV = findViewById(R.id.overviewTV);
        trailersRV = findViewById(R.id.trailersRecyclerView);
        castRV = findViewById(R.id.castRecyclerView);
        similarRV = findViewById(R.id.similarRecyclerView);
        recommendationsRV = findViewById(R.id.recommendationsRecyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findById();

        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();
        similarAdapter = new MovieAdapter(similarMovies,this,1);
//        trailerAdapter = new MovieAdapter(recommendationsMovies,this,1);
        recommendationsAdapter = new MovieAdapter(recommendationsMovies,this,1);
        castAdapter = new CastAdapter(casts,this);

//        trailersRV.setAdapter(trailerAdapter);
        castRV.setAdapter(castAdapter);
        recommendationsRV.setAdapter(recommendationsAdapter);
        similarRV.setAdapter(similarAdapter);

//        LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager castLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager recommendationsLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager similarLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

//        trailersRV.setLayoutManager(trailersLayoutManager);
        castRV.setLayoutManager(castLayoutManager);
        recommendationsRV.setLayoutManager(recommendationsLayoutManager);
        similarRV.setLayoutManager(similarLayoutManager);

        Call<MovieCategory> call = service.getSimilarMovies(351286,API_KEY);
        call.enqueue(new Callback<MovieCategory>() {
            @Override
            public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
                if(response.body()!=null) {
                    MovieCategory movieCategory = response.body();
                    similarMovies.clear();
                    similarMovies.addAll(movieCategory.movies);
                    similarAdapter.notifyDataSetChanged();
                }else{
                }

            }

            @Override
            public void onFailure(Call<MovieCategory> call, Throwable t) {
            }
        });

        Call<MovieCategory> call2 = service.getRecommendations(351286,API_KEY);
        call2.enqueue(new Callback<MovieCategory>() {
            @Override
            public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
                if(response.body()!=null) {
                    MovieCategory movieCategory = response.body();
                    recommendationsMovies.clear();
                    recommendationsMovies.addAll(movieCategory.movies);
                    recommendationsAdapter.notifyDataSetChanged();
                }else{
                }

            }

            @Override
            public void onFailure(Call<MovieCategory> call, Throwable t) {
            }
        });

        Call<CastCrew> call3 = service.getCast(351286,API_KEY);
        call3.enqueue(new Callback<CastCrew>() {
            @Override
            public void onResponse(Call<CastCrew> call, Response<CastCrew> response) {
                if(response.body()!=null) {
                    CastCrew castCrew = response.body();
                    casts.clear();
                    casts.addAll(castCrew.cast);
                    castAdapter.notifyDataSetChanged();
                }else{
                }

            }

            @Override
            public void onFailure(Call<CastCrew> call, Throwable t) {
            }
        });

        Intent intent = getIntent();


    }
}
