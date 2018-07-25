package com.example.caatulgupta.movietimes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class CastDetails extends AppCompatActivity {

    ImageView profileImage;
    Adapter moviesAdapter, showsAdapter;
    RecyclerView CastMoviesRecyclerView, CastTVShowsRecyclerView;
    TextView aboutCastTextView;

    Retrofit retrofit;
    MovieTimesService MovieService;
    TVTimesService TVService;
    ArrayList<Movie> CastMovies = new ArrayList<>();
    ArrayList<TV> CastShows = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileImage = findViewById(R.id.image_id);
        CastMoviesRecyclerView = findViewById(R.id.CastMovieRecyclerView);
        CastTVShowsRecyclerView = findViewById(R.id.CastTvShowsRecyclerView);
        aboutCastTextView = findViewById(R.id.aboutCastTextView);

        Intent intent = getIntent();
        Cast cast = (Cast)intent.getSerializableExtra("cast");
        aboutCastTextView.setText(cast.biography);

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+cast.profilePath).centerCrop().resize(300,300).into(profileImage);
        toolbarLayout.setTitle(cast.name);
        toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBlack));


        retrofit = ApiClient.getRetrofit();
        MovieService = ApiClient.getService();
        TVService = ApiClient.getTVservice();
        moviesAdapter = new Adapter(CastMovies,null,this,1,"movie");
        showsAdapter = new Adapter(null,CastShows,this,1,"TV");

        CastMoviesRecyclerView.setAdapter(moviesAdapter);
        CastTVShowsRecyclerView.setAdapter(showsAdapter);

        LinearLayoutManager movieLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        CastMoviesRecyclerView.setLayoutManager(movieLayoutManager);
        LinearLayoutManager showLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        CastTVShowsRecyclerView.setLayoutManager(showLayoutManager);

        Call<MovieCredits> call = MovieService.getCastsMovie(cast.id,API_KEY);
        call.enqueue(new Callback<MovieCredits>() {
            @Override
            public void onResponse(Call<MovieCredits> call, Response<MovieCredits> response) {
                if(response.body()!=null) {
                    MovieCredits movieCredits = response.body();
                    CastMovies.clear();
                    CastMovies.addAll(movieCredits.movieCast);
                    moviesAdapter.notifyDataSetChanged();
                }else{
                }

            }

            @Override
            public void onFailure(Call<MovieCredits> call, Throwable t) {
            }
        });

        Call<TVCredits> call2 = TVService.getCastsTVShows(cast.id,API_KEY);
        call2.enqueue(new Callback<TVCredits>() {
            @Override
            public void onResponse(Call<TVCredits> call, Response<TVCredits> response) {
                if(response.body()!=null) {
                    TVCredits tvCredits = response.body();
                    CastShows.clear();
                    CastShows.addAll(tvCredits.TVCast);
                    showsAdapter.notifyDataSetChanged();
                }else{
                }

            }

            @Override
            public void onFailure(Call<TVCredits> call, Throwable t) {
            }
        });


    }
}
