package com.example.mytestmodule.test.testviewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.mytestmodule.BR;
import com.example.mytestmodule.model.MovieModel;
import com.example.mytestmodule.model.net.RestApi;
import com.example.mytestmodule.test.testview.testadapter.TestAdapter;
import com.example.mytestmodule.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TestViewModel extends BaseObservable implements Observer<ArrayList<MovieModel>>
{
    Context context;
    private MovieModel movieModel;
    private ArrayList<TestViewModel> testViewModels=new ArrayList<>();
    MutableLiveData<ArrayList<TestViewModel>> mutableLiveData=new MutableLiveData<>();

    @Bindable
    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        notifyPropertyChanged(BR.movieModel);
    }

    @Bindable
    public ArrayList<TestViewModel> getTestViewModels() {
        return testViewModels;
    }

    public void setTestViewModels(ArrayList<TestViewModel> testViewModels) {
        this.testViewModels = testViewModels;
        notifyPropertyChanged(BR.testViewModels);
    }

    @Bindable
    public MutableLiveData<ArrayList<TestViewModel>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<ArrayList<TestViewModel>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
        notifyPropertyChanged(BR.mutableLiveData);
    }

    public TestViewModel(Context context) {
        this.context = context;
        final RestApi restApi=new RestApi();

        restApi.getMovie(0).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this);
              /*  .subscribe(new Observer<ArrayList<MovieModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<MovieModel> movieModels) {
                        for (int i = 0; i < movieModels.size(); i++) {
                            TestViewModel testViewModel=new TestViewModel(movieModels.get(i));
                            testViewModels.add(testViewModel);
                        }
                        mutableLiveData.setValue(testViewModels);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    public TestViewModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    @BindingAdapter("bind")
    public static void bindRV(final RecyclerView recyclerView , MutableLiveData<ArrayList<TestViewModel>> arrayListMutableLiveData)
    {
        arrayListMutableLiveData.observe((LifecycleOwner) recyclerView.getContext(), new android.arch.lifecycle.Observer<ArrayList<TestViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<TestViewModel> testViewModels) {
                TestAdapter adapter=new TestAdapter(testViewModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @BindingAdapter({"imageUrl" , "pic"})
    public static void ontouch(ImageView imageView,String path,Picasso picasso)
    {
        picasso.with(imageView.getContext()).load(path).into(imageView);
    }

    public void onCategoryClick(View view, TestViewModel model)
    {
        RestApi restApi=new RestApi();
        restApi.getMovie(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this);
                /*.subscribe(new Observer<ArrayList<MovieModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<MovieModel> movieModels) {
                        if (testViewModels.size()>0)
                        {
                            testViewModels.clear();
                        }
                        for (int i = 0; i < movieModels.size(); i++) {
                            TestViewModel testViewModel=new TestViewModel(movieModels.get(i));
                            testViewModels.add(testViewModel);
                        }
                        mutableLiveData.setValue(testViewModels);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ArrayList<MovieModel> movieModels) {
        if (testViewModels.size()>0)
        {
            testViewModels.clear();
        }
        for (int i = 0; i < movieModels.size(); i++) {
            TestViewModel testViewModel=new TestViewModel(movieModels.get(i));
            testViewModels.add(testViewModel);
        }
        mutableLiveData.setValue(testViewModels);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
