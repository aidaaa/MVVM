package com.example.mvvm.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    public String base_url="http://moviesapi.ir/api/v1/";

    public static Retrofit getRetrofit(){
        OkHttpClient.Builder client=new OkHttpClient.Builder();

        client.readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(base_url)
                .client(client.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
