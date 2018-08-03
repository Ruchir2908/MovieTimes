package com.example.caatulgupta.movietimes;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.ALARM_SERVICE;
import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class RecommendedMovies extends Fragment {

    MoviesDAO moviesDAO, watchedMoviesDAO;
    RecyclerView recyclerView;
    TextView nothingToShow;
    ImageView empty;
    Adapter adapter;
    ArrayList<Movie> movies;

    public RecommendedMovies() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.navigation_items_layout,container,false);
        recyclerView = output.findViewById(R.id.RecyclerView);
        nothingToShow = output.findViewById(R.id.nothingToShow);
        empty = output.findViewById(R.id.empty);

        MovieDatabase movieDatabase = Room.databaseBuilder(getContext().getApplicationContext(),MovieDatabase.class,"recommended_moviedb").allowMainThreadQueries().build();
        moviesDAO = movieDatabase.getMovieDAO();

        ArrayList<Movie> movies = (ArrayList)moviesDAO.getMovies();
        if(movies.isEmpty()){
            nothingToShow.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }else{
            nothingToShow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
        adapter = new Adapter(movies,null,getContext(),1,"movie");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

//        MovieDatabase watchedMovieDatabase = Room.databaseBuilder(getContext().getApplicationContext(),MovieDatabase.class,"watched_moviedb").allowMainThreadQueries().build();
//        watchedMoviesDAO = watchedMovieDatabase.getMovieDAO();
//
//        List<Movie> watchMovies = watchedMoviesDAO.getMovies();
////        Toast.makeText(getContext(), watchMovies.size(), Toast.LENGTH_SHORT).show();
//        for(int i=0;i<watchMovies.size();i++){
////            Toast.makeText(getContext(), "Enter W", Toast.LENGTH_SHORT).show();
//
//        }
//
//        adapter = new Adapter(movies,null,getContext(),1,"movie");
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
//        recyclerView.setLayoutManager(layoutManager);

        return output;
    }

}
