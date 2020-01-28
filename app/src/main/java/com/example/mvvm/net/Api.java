package com.example.mvvm.net;

import com.example.mvvm.model.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api
{
    @GET("movies")
    Call<MoviesData> getMovie(@Query("page") int page);

}
