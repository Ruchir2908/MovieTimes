package com.example.caatulgupta.movietimes;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;
import static java.lang.System.in;

public class MovieDetails extends AppCompatActivity {

    TextView releaseDateTV, languageTV, ratingTV, genresTV, overviewTV;
    RecyclerView trailersRV, castRV, recommendationsRV, similarRV, reviewRV;
    ImageView posterImageView,backdropImageView;
    com.getbase.floatingactionbutton.FloatingActionButton favouriteFAB, watchedFAB;
    MoviesDAO moviesDAO, watchedMoviesDAO;
    String genreList;

    Retrofit retrofit;
    MovieTimesService service;
    Adapter similarAdapter, recommendationsAdapter;
    ReviewsAdapter reviewsAdapter;
    TrailersAdapter  trailerAdapter;
    CastAdapter castAdapter;
    ArrayList<Movie> similarMovies = new ArrayList<>();
    ArrayList<Movie> recommendationsMovies = new ArrayList<>();
    ArrayList<Cast> casts = new ArrayList<>();
    ArrayList<Videos> videos = new ArrayList<>();
    ArrayList<Review> reviews = new ArrayList<>();
    ArrayList<Genres> genres = new ArrayList<>();

    void findById(){
        releaseDateTV = findViewById(R.id.releaseDateTV);
        languageTV = findViewById(R.id.languageTV);
        ratingTV = findViewById(R.id.ratingTV);
        genresTV = findViewById(R.id.genreTV);
        overviewTV = findViewById(R.id.overviewTV);
        trailersRV = findViewById(R.id.trailersRecyclerView);
        castRV = findViewById(R.id.castRecyclerView);
        similarRV = findViewById(R.id.similarRecyclerView);
//        recommendationsRV = findViewById(R.id.recommendationsRecyclerView);
        reviewRV = findViewById(R.id.reviewRecyclerView);
        posterImageView = findViewById(R.id.posterImageView);
        backdropImageView = findViewById(R.id.backdropImageView);
        favouriteFAB = findViewById(R.id.favoriteFAB);
        watchedFAB = findViewById(R.id.watchedFAB);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findById();

        MovieDatabase movieDatabase = Room.databaseBuilder(getApplicationContext(),MovieDatabase.class,"moviedb").allowMainThreadQueries().build();
        moviesDAO = movieDatabase.getMovieDAO();
        MovieDatabase watchedMovieDatabase = Room.databaseBuilder(getApplicationContext(),MovieDatabase.class,"watched_moviedb").allowMainThreadQueries().build();
        watchedMoviesDAO = watchedMovieDatabase.getMovieDAO();

        Intent intent = getIntent();
        final Movie movie = (Movie)intent.getSerializableExtra("movie");
        releaseDateTV.setText(movie.releaseDate);
        if(movie.language.equals("en")){
            languageTV.setText("Eng");
        }else if(movie.language.equals("hi")){
            languageTV.setText("Hin");
        }
        ratingTV.setText(movie.avgVote+" ★");
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
        reviewsAdapter = new ReviewsAdapter(reviews,this);

        trailersRV.setAdapter(trailerAdapter);
        castRV.setAdapter(castAdapter);
//        recommendationsRV.setAdapter(recommendationsAdapter);
        similarRV.setAdapter(similarAdapter);
        reviewRV.setAdapter(reviewsAdapter);


        LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager castLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        final LinearLayoutManager recommendationsLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager similarLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        trailersRV.setLayoutManager(trailersLayoutManager);
        castRV.setLayoutManager(castLayoutManager);
//        recommendationsRV.setLayoutManager(recommendationsLayoutManager);
        similarRV.setLayoutManager(similarLayoutManager);
        reviewRV.setLayoutManager(reviewsLayoutManager);

        Call<GenreObject> callGenres = service.getMovieGenres(API_KEY);
        callGenres.enqueue(new Callback<GenreObject>() {
            @Override
            public void onResponse(Call<GenreObject> call, Response<GenreObject> response) {
                if(response.body()!=null){
//                    ArrayList<Genres> genre = new ArrayList<>();
//                    genre.addAll(response.body());
                    genres.clear();
                    genres.addAll(response.body().genres);
                    genreList = "";
                }
            }

            @Override
            public void onFailure(Call<GenreObject> call, Throwable t) {

            }
        });

        for(int i=0;i<genres.size();i++){
            if(genres.contains(movie.genreIds.get(i))){
                genreList.concat(genres.get(i).name+",");
            }

        }

        genresTV.setText(genreList);

        Call<Reviews> callReviews = service.getReviews(movie.id,API_KEY);
        callReviews.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if(response.body()!=null){
                    Reviews review = response.body();
                    reviews.clear();
                    reviews.addAll(review.results);
                    reviewsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });

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

        favouriteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> ids = moviesDAO.getMovieIds();
                if(ids.contains(movie.id)){
                    moviesDAO.removeMovie(movie);
                    Toast.makeText(MovieDetails.this, "Favourite removed", Toast.LENGTH_SHORT).show();
                }else{
                    moviesDAO.addMovie(movie);
                    Toast.makeText(MovieDetails.this, "Favourite added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        watchedFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> ids = watchedMoviesDAO.getMovieIds();
                if(ids.contains(movie.id)){
                    watchedMoviesDAO.removeMovie(movie);
                    Toast.makeText(MovieDetails.this, "Removed from watched", Toast.LENGTH_SHORT).show();
                }else{
                    watchedMoviesDAO.addMovie(movie);
                    Toast.makeText(MovieDetails.this, "Added to watched", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
