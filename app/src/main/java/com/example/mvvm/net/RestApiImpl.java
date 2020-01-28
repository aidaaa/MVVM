package com.example.mvvm.net;

import com.example.mvvm.model.MoviesData;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final MoviesData[] moviesData = {new MoviesData()};
        Api api=ApiService.getRetrofit().create(Api.class);
        Call<MoviesData> call=api.getMovie(1);
        call.enqueue(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {
                moviesData[0] =response.body();
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {

            }
        });
        return moviesData[0];
    }
}
