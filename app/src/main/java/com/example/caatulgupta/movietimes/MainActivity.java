package com.example.caatulgupta.movietimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String API_KEY = "98247b6d9263ea7606524c339461f256";
    Retrofit retrofit;
    MovieTimesService service;
    MovieAdapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();

        adapter = new MovieAdapter(movies,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);


        Call<MovieCategory> call = service.getMovies("now_playing",API_KEY);
        call.enqueue(new Callback<MovieCategory>() {
            @Override
            public void onResponse(Call<MovieCategory> call, Response<MovieCategory> response) {
                if(response.body()!=null) {

                    Toast.makeText(MainActivity.this, "HIIII", Toast.LENGTH_SHORT).show();
                    MovieCategory movieCategory = response.body();
                    movies.clear();
                    movies.addAll(movieCategory.movies);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "BYEEE", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MovieCategory> call, Throwable t) {
                Log.i("lalala",t.getMessage());
                Log.i("lalala",t.getLocalizedMessage());
                Toast.makeText(MainActivity.this,"OH NO"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
