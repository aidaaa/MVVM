package com.example.mvvm.net;

import com.example.mvvm.model.MoviesData;

import io.reactivex.Observable;

public interface RestApi {
    Observable<MoviesData> getObservable();
}
