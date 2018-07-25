package com.example.caatulgupta.movietimes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class MovieUpcomingFragment extends Fragment {

    RecyclerView upcomingRecylclerView;
    Retrofit retrofit;
    MovieTimesService service;
    Adapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();

    public MovieUpcomingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_upcoming, container, false);

        upcomingRecylclerView = output.findViewById(R.id.upcomingRecyclerView);
        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();
        adapter = new Adapter(movies,null,getContext(),0,"movie");
        upcomingRecylclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        upcomingRecylclerView.setLayoutManager(layoutManager);

        Call<MovieCategory> call = service.getMovies("upcoming",API_KEY);
        call.enqueue(new Callback<MovieCategory>() {
            @Override
            public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
                if(response.body()!=null) {
                    MovieCategory movieCategory = response.body();
                    movies.clear();
                    movies.addAll(movieCategory.movies);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "BYEEE", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MovieCategory> call, Throwable t) {
            }
        });

        return output;
    }

}
