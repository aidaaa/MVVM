package com.example.mvvm.net;

import android.util.Log;

import com.example.mvvm.model.MoviesData;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RestApiImpl implements RestApi {
    @Override
    public Observable<MoviesData> getObservable() {
        return Observable.create(new ObservableOnSubscribe<MoviesData>() {
            @Override
            public void subscribe(ObservableEmitter<MoviesData> emitter) throws Exception {
                MoviesData moviesData=getDataNet();

                if (!emitter.isDisposed() && moviesData!=null)
                {
                    emitter.onNext(moviesData);
                    emitter.onComplete();
                }
                else
                {
                    emitter.onError(new Exception());
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public MoviesData getDataNet()
    {
        Api api=ApiService.getRetrofit().create(Api.class);
        try {
            return api.getMovie(1).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
