package com.example.caatulgupta.movietimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class TVDetails extends AppCompatActivity {

    TextView releaseDateTV, languageTV, ratingTV, genresTV, overviewTV;
    RecyclerView trailersRV, castRV, recommendationsRV, similarRV;
    ImageView posterImageView, backdropImageView;

    Retrofit retrofit;
    TVTimesService service;
    Adapter similarAdapter, recommendationsAdapter;
    TrailersAdapter  trailerAdapter;
    CastAdapter castAdapter;
    ArrayList<TV> similarShows = new ArrayList<>();
    ArrayList<TV> recommendationsShows = new ArrayList<>();
    ArrayList<Cast> casts = new ArrayList<>();
    ArrayList<Videos> videos = new ArrayList<>();

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
        posterImageView= findViewById(R.id.posterImageView);
        backdropImageView = findViewById(R.id.backdropImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findById();

        Intent intent = getIntent();
        TV show = (TV)intent.getSerializableExtra("show");
        releaseDateTV.setText(show.airDate);
        ratingTV.setText(show.avgVote+"");
        genresTV.setText(show.genres+"");
        overviewTV.setText(show.overview);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+show.posterPath).resize(400,550).centerCrop().into(posterImageView);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+show.backdropPath).resize(600,500).centerCrop().into(backdropImageView);

        CollapsingToolbarLayout layout = findViewById(R.id.toolbar_layout);
        layout.setTitle(show.name);

        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getTVservice();
        similarAdapter = new Adapter(null,similarShows,this,1,"TV");
        trailerAdapter = new TrailersAdapter(videos,this);
        recommendationsAdapter = new Adapter(null,recommendationsShows,this,1,"TV");
        castAdapter = new CastAdapter(casts,this);

        trailersRV.setAdapter(trailerAdapter);
        castRV.setAdapter(castAdapter);
//        recommendationsRV.setAdapter(recommendationsAdapter);
        similarRV.setAdapter(similarAdapter);

        LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager castLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        LinearLayoutManager recommendationsLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager similarLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        trailersRV.setLayoutManager(trailersLayoutManager);
        castRV.setLayoutManager(castLayoutManager);
//        recommendationsRV.setLayoutManager(recommendationsLayoutManager);
        similarRV.setLayoutManager(similarLayoutManager);

        Call<Trailers> callVideos = service.getVideos(show.id,API_KEY);
        callVideos.enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                if(response.body()!=null){
                    Trailers trailers = response.body();
                    videos.clear();
                    videos.addAll(trailers.results);
                    trailerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {

            }
        });

        Call<TVCategory> call = service.getSimilarTVShows(show.id,API_KEY);
        call.enqueue(new Callback<TVCategory>() {
            @Override
            public void onResponse(Call<TVCategory> call, Response<TVCategory> response) {
                if(response.body()!=null) {
                    TVCategory tvCategory = response.body();
                    similarShows.clear();
                    similarShows.addAll(tvCategory.shows);
                    similarAdapter.notifyDataSetChanged();
                }else{
                }

            }

            @Override
            public void onFailure(Call<TVCategory> call, Throwable t) {
            }
        });

        Call<TVCategory> call2 = service.getRecommendations(show.id,API_KEY);
        call2.enqueue(new Callback<TVCategory>() {
            @Override
            public void onResponse(Call<TVCategory> call, Response<TVCategory> response) {
                if(response.body()!=null) {
                    TVCategory tvCategory = response.body();
                    recommendationsShows.clear();
                    recommendationsShows.addAll(tvCategory.shows);
                    recommendationsAdapter.notifyDataSetChanged();
                }else{
                }

            }

            @Override
            public void onFailure(Call<TVCategory> call, Throwable t) {
            }
        });

        Call<CastCrew> call3 = service.getCast(show.id,API_KEY);
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




    }
}
