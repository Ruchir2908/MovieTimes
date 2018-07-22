package com.example.caatulgupta.movietimes;


import android.content.Context;
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

public class MovieNowShowingFragment extends Fragment {

    RecyclerView nowShowingRecyclerView;
    Retrofit retrofit;
    MovieTimesService service;
    MovieAdapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();
    NowShowingFragmentCallBack listener;

    public MovieNowShowingFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof NowShowingFragmentCallBack){
            listener = (NowShowingFragmentCallBack)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_now_showing, container, false);

        nowShowingRecyclerView = output.findViewById(R.id.nowShowingRecyclerView);
        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();
        adapter = new MovieAdapter(movies,getContext(),0);
        nowShowingRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        nowShowingRecyclerView.setLayoutManager(layoutManager);

        Call<MovieCategory> call = service.getMovies("now_playing",API_KEY);
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

//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
//            Movie movie = movies.get(i);
//            if(listener!=null){
//                listener.onNowShowingMovieSelected(movie);
//            }
//        }

        return output;
    }

    public interface NowShowingFragmentCallBack{
        void onNowShowingMovieSelected(Movie movie);
    }

}
