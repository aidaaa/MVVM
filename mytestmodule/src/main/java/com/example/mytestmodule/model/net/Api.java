package com.example.mytestmodule.model.net;

import com.example.mytestmodule.model.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api
{
    @GET("movies")
    Call<MoviesData> getMoview(@Query("page") int page);
}
