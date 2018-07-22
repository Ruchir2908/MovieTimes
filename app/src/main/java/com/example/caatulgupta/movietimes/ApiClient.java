package com.example.caatulgupta.movietimes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static MovieTimesService service;
    private static TVTimesService TVservice;

    public static Retrofit getRetrofit(){
        if(retrofit==null){


            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }
        return retrofit;
    }

    public static MovieTimesService getService(){
        if(service == null){
            service = getRetrofit().create(MovieTimesService.class);
        }
        return service;
    }

    public static TVTimesService getTVservice(){
        if(TVservice == null){
            TVservice = getRetrofit().create(TVTimesService.class);
        }
        return TVservice;
    }

}
