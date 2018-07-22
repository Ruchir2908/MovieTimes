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


/**
 * A simple {@link Fragment} subclass.
 */
public class TVOnAirFragment extends Fragment {


    RecyclerView onAirRecyclerView;
    Retrofit retrofit;
    TVTimesService service;
    TVAdapter adapter;
    ArrayList<TV> shows = new ArrayList<>();

    public TVOnAirFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_tvon_air, container, false);

        onAirRecyclerView = output.findViewById(R.id.onAirRecyclerView);
        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getTVservice();
        adapter = new TVAdapter(shows,getContext());
        onAirRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        onAirRecyclerView.setLayoutManager(layoutManager);

        Call<TVCategory> call = service.getTVShows("on_the_air",API_KEY);
        call.enqueue(new Callback<TVCategory>() {
            @Override
            public void onResponse(Call<TVCategory> call, Response<TVCategory> response) {
                if(response.body()!=null) {
                    TVCategory tvCategory = response.body();
                    shows.clear();
                    shows.addAll(tvCategory.shows);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "BYEEE", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TVCategory> call, Throwable t) {
            }
        });
        return output;
    }

}
