package com.example.mytestmodule.model.net;

import com.example.mytestmodule.model.MovieModel;
import com.example.mytestmodule.model.MoviesData;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RestApi
{
    public Observable<ArrayList<MovieModel>> getMovie()
    {
        return Observable.create(new ObservableOnSubscribe<ArrayList<MovieModel>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<MovieModel>> emitter) throws Exception {
                MoviesData moviesData=getMovieModel();
                if (!emitter.isDisposed() && moviesData!=null)
                {
                    ArrayList<MovieModel> models=new ArrayList<>();
                    for (int i = 0; i < moviesData.data.size(); i++) {
                        models.add(moviesData.data.get(i));
                    }
                    emitter.onNext(models);
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
