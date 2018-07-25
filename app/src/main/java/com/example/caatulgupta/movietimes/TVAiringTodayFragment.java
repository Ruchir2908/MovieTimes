package com.example.caatulgupta.movietimes;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.caatulgupta.movietimes.MainActivity.API_KEY;

public class TVAiringTodayFragment extends Fragment {

    RecyclerView airingTodayRecyclerView;
    Retrofit retrofit;
    TVTimesService service;
    Adapter adapter;
    ArrayList<TV> shows = new ArrayList<>();
    ProgressBar progressBar;
    CardView cardView;
    onTVAiringTodayListener listener;

    public TVAiringTodayFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_tvairing_today, container, false);

        airingTodayRecyclerView = output.findViewById(R.id.airingTodayRecyclerView);
        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getTVservice();
        adapter = new Adapter(null,shows,getContext(),0,"TV");
        airingTodayRecyclerView.setAdapter(adapter);
        progressBar = output.findViewById(R.id.progressBar);
        cardView = output.findViewById(R.id.cardView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        airingTodayRecyclerView.setLayoutManager(layoutManager);

        Call<TVCategory> call = service.getTVShows("airing_today",API_KEY);
        call.enqueue(new Callback<TVCategory>() {
            @Override
            public void onResponse(Call<TVCategory> call, Response<TVCategory> response) {
                if(response.body()!=null) {
//                    progressBar.setVisibility(View.GONE);
//                    cardView.setVisibility(View.VISIBLE);
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

//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
//            TV show = shows.get(i);
//            if(listener!=null){
//                listener.onTVAiringTodaySelected(show);
//            }
//        }
    }

    public interface onTVAiringTodayListener{

        void onTVAiringTodaySelected(TV show);

    }

}
