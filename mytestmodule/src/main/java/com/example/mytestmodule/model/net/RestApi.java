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
    public Observable<ArrayList<MovieModel>> getMovie(int i)
    {
        ArrayList<MovieModel> list=new ArrayList<>();
        if (i==0)
        {
            for (int j = 0; j < 10; j++) {
                MovieModel model=new MovieModel();
                model.setId(j);
                model.setPoster("http://moviesapi.ir/images/tt0111161_poster.jpg");
                model.setImdb_rating(String.valueOf(j));
                model.setTitle(String.valueOf(j));
                model.setYear(String.valueOf(j));
                list.add(model);
            }
        }
        else {
            for (int j = 0; j < 5; j++) {
                MovieModel model=new MovieModel();
                model.setId(j);
                model.setPoster("http://moviesapi.ir/images/tt0111161_screenshot2.jpg");
                model.setImdb_rating(String.valueOf(j));
                model.setTitle(String.valueOf(j));
                model.setYear(String.valueOf(j));
                list.add(model);
            }
        }

        return Observable.just(list);
        /*return Observable.create(new ObservableOnSubscribe<ArrayList<TestMovieModel>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<TestMovieModel>> emitter) throws Exception {
                TestMoviesData moviesData=getMovieModel();
                if (!emitter.isDisposed() && moviesData!=null)
                {
                    ArrayList<TestMovieModel> models=new ArrayList<>();
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
        });*/
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
