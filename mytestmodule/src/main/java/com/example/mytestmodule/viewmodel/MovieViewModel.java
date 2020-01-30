package com.example.mytestmodule.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.mytestmodule.BR;
import com.example.mytestmodule.model.MovieModel;

public class MovieViewModel extends BaseObservable {
    private Context context;
    private MovieModel movieModel;

    @Bindable
    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        notifyPropertyChanged(BR.movieModel);
    }

    public MovieViewModel(Context context) {
        this.context = context;
    }

    public MovieViewModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }
}
