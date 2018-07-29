package com.example.caatulgupta.movietimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class MovieDetails extends AppCompatActivity {

    TextView releaseDateTV, languageTV, ratingTV, genresTV, overviewTV;
    RecyclerView trailersRV, castRV, recommendationsRV, similarRV;
    ImageView posterImageView,backdropImageView;

    Retrofit retrofit;
    MovieTimesService service;
    Adapter similarAdapter, recommendationsAdapter;
    TrailersAdapter  trailerAdapter;
    CastAdapter castAdapter;
    ArrayList<Movie> similarMovies = new ArrayList<>();
    ArrayList<Movie> recommendationsMovies = new ArrayList<>();
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
        posterImageView = findViewById(R.id.posterImageView);
        backdropImageView = findViewById(R.id.backdropImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findById();

        Intent intent = getIntent();
        Movie movie = (Movie)intent.getSerializableExtra("movie");
        releaseDateTV.setText(movie.releaseDate);
        languageTV.setText(movie.language);
        ratingTV.setText(movie.avgVote+"");
        genresTV.setText(movie.genreIds+"");
        overviewTV.setText(movie.overview);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).resize(400,550).centerCrop().into(posterImageView);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.backdropPath).resize(600,500).centerCrop().into(backdropImageView);


        CollapsingToolbarLayout layout =findViewById(R.id.toolbar_layout);
        layout.setTitle(movie.title);

        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();
        similarAdapter = new Adapter(similarMovies,null,this,1,"movie");
        trailerAdapter = new TrailersAdapter(videos,this);
        recommendationsAdapter = new Adapter(recommendationsMovies,null,this,1,"movie");
        castAdapter = new CastAdapter(casts,this);

        trailersRV.setAdapter(trailerAdapter);
        castRV.setAdapter(castAdapter);
//        recommendationsRV.setAdapter(recommendationsAdapter);
        similarRV.setAdapter(similarAdapter);

        LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager castLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        final LinearLayoutManager recommendationsLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager similarLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        trailersRV.setLayoutManager(trailersLayoutManager);
        castRV.setLayoutManager(castLayoutManager);
//        recommendationsRV.setLayoutManager(recommendationsLayoutManager);
        similarRV.setLayoutManager(similarLayoutManager);

        Call<Trailers> callVideos = service.getVideos(movie.id,API_KEY);
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

        Call<MovieCategory> call = service.getSimilarMovies(movie.id,API_KEY);
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

//        Call<MovieCategory> call2 = service.getRecommendations(movie.id,API_KEY);
//        call2.enqueue(new Callback<MovieCategory>() {
//            @Override
//            public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
//                if(response.body()!=null) {
//                    MovieCategory movieCategory = response.body();
//                    recommendationsMovies.clear();
//                    recommendationsMovies.addAll(movieCategory.movies);
//                    recommendationsAdapter.notifyDataSetChanged();
//                }else{
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieCategory> call, Throwable t) {
//            }
//        });

        Call<CastCrew> call3 = service.getCast(movie.id,API_KEY);
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
