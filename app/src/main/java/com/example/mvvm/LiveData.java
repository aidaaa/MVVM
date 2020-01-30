package com.example.mvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;

import com.example.mvvm.model.MoviesData;
import com.example.mvvm.net.Api;
import com.example.mvvm.net.ApiService;
import com.example.mvvm.net.RestApi;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveData extends ViewModel{

       private MutableLiveData<MoviesData> currentName=new MutableLiveData<>();

        public MutableLiveData<MoviesData> getDataNet()
        {
            Api api= ApiService.getRetrofit().create(Api.class);

            Call<MoviesData> call=api.getMovie(1);
            call.enqueue(new Callback<MoviesData>() {
                @Override
                public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {
                    currentName.setValue(response.body());
                }

                @Override
                public void onFailure(Call<MoviesData> call, Throwable t) {

                }
            });
            return currentName;
        }
}

