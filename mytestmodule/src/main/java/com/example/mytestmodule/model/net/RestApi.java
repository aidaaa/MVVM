package com.example.mytestmodule.model.net;

import com.example.mytestmodule.model.MovieModel;
import com.example.mytestmodule.model.MoviesData;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RestApi
{
    public Observable<MovieModel> getMovie()
    {
        return Observable.create(new ObservableOnSubscribe<MovieModel>() {
            @Override
            public void subscribe(ObservableEmitter<MovieModel> emitter) throws Exception {
                MoviesData moviesData=getMovieModel();
                if (!emitter.isDisposed() && moviesData!=null)
                {
                    for (int i = 0; i < moviesData.data.size(); i++) {
                        emitter.onNext(moviesData.data.get(i));
                    }
                    emitter.onComplete();
                }
                else
                {
                    emitter.onError(new Exception());
                }
            }
        });
    }

    public MoviesData getMovieModel()
    {
        ApiService apiService=new ApiService();
        Api api=apiService.getRetrofit().create(Api.class);
        try {
           return api.getMoview(1).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
