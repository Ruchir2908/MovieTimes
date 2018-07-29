package com.example.caatulgupta.movietimes;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class MovieTopRatedFragment extends Fragment {

    RecyclerView topRatedRecyclerView;
    Retrofit retrofit;
    MovieTimesService service;
    Adapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();
    ProgressBar topRatedProgressBar;
    boolean isScrolling = false;
    int currentItems,totalItems,scrolledItems,page = 3;

    public MovieTopRatedFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_top_rated, container, false);

        topRatedRecyclerView = output.findViewById(R.id.topRatedRecyclerView);
        topRatedProgressBar = output.findViewById(R.id.topRatedProgressBar);
        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();
        adapter = new Adapter(movies,null,getContext(),0,"movie");
        topRatedRecyclerView.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        topRatedRecyclerView.setLayoutManager(layoutManager);

        topRatedRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrolledItems = layoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems+scrolledItems)==totalItems){
                    isScrolling = false;
                    fetchData();
                }
            }
        });

//        Call<MovieCategory> call = service.getMovies("top_rated",API_KEY,1);
//        call.enqueue(new Callback<MovieCategory>() {
//            @Override
//            public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
//                if(response.body()!=null) {
//                    MovieCategory movieCategory = response.body();
//                    movies.clear();
//                    movies.addAll(movieCategory.movies);
//                    adapter.notifyDataSetChanged();
//                }else{
//                    Toast.makeText(getContext(), "BYEEE", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieCategory> call, Throwable t) {
//            }
//        });
        fetchData();
        return output;
    }

    public void fetchData(){
        topRatedProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<MovieCategory> call = service.getMovies("top_rated",API_KEY,page);
                call.enqueue(new Callback<MovieCategory>() {
                    @Override
                    public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
                        if(response.body()!=null) {
                            MovieCategory movieCategory = response.body();
//                            movies.clear();
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
                topRatedProgressBar.setVisibility(View.GONE);
                page++;
            }
        },0);

    }

}
