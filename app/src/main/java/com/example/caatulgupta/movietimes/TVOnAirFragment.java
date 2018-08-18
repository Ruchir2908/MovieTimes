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

public class TVOnAirFragment extends Fragment {


    RecyclerView onAirRecyclerView;
    Retrofit retrofit;
    TVTimesService service;
    Adapter adapter;
    ArrayList<TV> shows = new ArrayList<>();
    ProgressBar onAirProgressBar;
    boolean isScrolling = false;
    int currentItems,totalItems,scrolledItems,page = 1;

    public TVOnAirFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_tvon_air, container, false);

        onAirRecyclerView = output.findViewById(R.id.onAirRecyclerView);
        onAirProgressBar = output.findViewById(R.id.onAirProgressBar);
        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getTVservice();
        adapter = new Adapter(null,shows,getContext(),0,"TV");
        onAirRecyclerView.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        onAirRecyclerView.setLayoutManager(layoutManager);

        onAirRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        fetchData();
        return output;
    }

    public void fetchData(){
        onAirProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<TVCategory> call = service.getTVShows("on_the_air",API_KEY,page);
                call.enqueue(new Callback<TVCategory>() {
                    @Override
                    public void onResponse(Call<TVCategory> call, Response<TVCategory> response) {
                        if(response.body()!=null) {
                            TVCategory tvCategory = response.body();
                            shows.addAll(tvCategory.shows);
                            adapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getContext(), "No Internet connection", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<TVCategory> call, Throwable t) {
                    }
                });
                onAirProgressBar.setVisibility(View.GONE);
                page++;
            }
        },0);

    }

}
