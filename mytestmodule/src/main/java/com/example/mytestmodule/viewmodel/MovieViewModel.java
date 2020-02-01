package com.example.mytestmodule.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mytestmodule.BR;
import com.example.mytestmodule.model.MovieModel;
import com.example.mytestmodule.model.net.RestApi;
import com.example.mytestmodule.view.adapter.MovieAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends BaseObservable {

    private Context context;
    private MovieModel movieModel;
    private MutableLiveData<ArrayList<MovieViewModel>> arrayListMutableLiveData = new MutableLiveData<>();

    public MovieViewModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public MovieViewModel(Context context) {
        this.context = context;

        RestApi restApi = new RestApi();
        restApi.getMovie(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<MovieModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<MovieModel> movieModels) {

                        ArrayList<MovieViewModel> list = new ArrayList<>();
                        for (int i = 0; i < movieModels.size(); i++) {
                            MovieViewModel mvm = new MovieViewModel(movieModels.get(i));
                            list.add(mvm);
                        }
                        arrayListMutableLiveData.setValue(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Bindable
    public MutableLiveData<ArrayList<MovieViewModel>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public void setArrayListMutableLiveData(MutableLiveData<ArrayList<MovieViewModel>> arrayListMutableLiveData) {
        this.arrayListMutableLiveData = arrayListMutableLiveData;
        notifyPropertyChanged(BR.arrayListMutableLiveData);
    }

    @Bindable
    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        notifyPropertyChanged(BR.movieModel);
    }

    @BindingAdapter("bind:rv")
    public static void rvBind(final RecyclerView recyclerView, MutableLiveData<ArrayList<MovieViewModel>> liveData) {
        liveData.observe((LifecycleOwner) recyclerView.getContext(), new android.arch.lifecycle.Observer<ArrayList<MovieViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MovieViewModel> movieModels) {
                MovieAdapter adapter = new MovieAdapter(movieModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @BindingAdapter({"imageUrl", "picasso"})
    public static void setImageUrl(ImageView view, String poserPath, Picasso picasso) {
        picasso.with(view.getContext()).load(poserPath).into(view);
    }

    public void onCategoryClick(View view, MovieViewModel model)
    {
        RestApi restApi = new RestApi();
        restApi.getMovie(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<MovieModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<MovieModel> movieModels) {

                        ArrayList<MovieViewModel> list = new ArrayList<>();
                        for (int i = 0; i < movieModels.size(); i++) {
                            MovieViewModel mvm = new MovieViewModel(movieModels.get(i));
                            list.add(mvm);
                        }
                        arrayListMutableLiveData.setValue(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
