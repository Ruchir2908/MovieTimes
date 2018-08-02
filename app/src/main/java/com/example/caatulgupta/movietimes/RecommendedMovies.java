package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class RecommendedMovies extends Fragment {

    MoviesDAO moviesDAO, watchedMoviesDAO;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Movie> movies;
    Retrofit retrofit;
    MovieTimesService service;

    public RecommendedMovies() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.navigation_items_layout,container,false);
        recyclerView = output.findViewById(R.id.RecyclerView);

        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();

        MovieDatabase movieDatabase = Room.databaseBuilder(getContext().getApplicationContext(),MovieDatabase.class,"moviedb").allowMainThreadQueries().build();
        moviesDAO = movieDatabase.getMovieDAO();

        List<Integer> favMovies = moviesDAO.getMovieIds();
        Toast.makeText(getContext(), favMovies.size(), Toast.LENGTH_SHORT).show();
        for(int i=0;i<favMovies.size();i++){
            Call<MovieCategory> call2 = service.getRecommendations(favMovies.get(i),API_KEY);
            call2.enqueue(new Callback<MovieCategory>() {
                @Override
                public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
                    if(response.body()!=null) {
                        MovieCategory movieCategory = response.body();
                        movies.addAll(movieCategory.movies);
                        adapter.notifyDataSetChanged();
                    }else{
                    }

                }

                @Override
                public void onFailure(Call<MovieCategory> call, Throwable t) {
                }
            });
        }

        MovieDatabase watchedMovieDatabase = Room.databaseBuilder(getContext().getApplicationContext(),MovieDatabase.class,"watched_moviedb").allowMainThreadQueries().build();
        watchedMoviesDAO = watchedMovieDatabase.getMovieDAO();

        List<Integer> watchMovies = watchedMoviesDAO.getMovieIds();
        Toast.makeText(getContext(), watchMovies.size(), Toast.LENGTH_SHORT).show();
        for(int i=0;i<watchMovies.size();i++){
            Call<MovieCategory> call2 = service.getRecommendations(watchMovies.get(i),API_KEY);
            call2.enqueue(new Callback<MovieCategory>() {
                @Override
                public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
                    if(response.body()!=null) {
                        MovieCategory movieCategory = response.body();
                        movies.addAll(movieCategory.movies);
                        adapter.notifyDataSetChanged();
                    }else{
                    }

                }

                @Override
                public void onFailure(Call<MovieCategory> call, Throwable t) {
                }
            });
        }

        adapter = new Adapter(movies,null,getContext(),1,"movie");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        return output;
    }

}
