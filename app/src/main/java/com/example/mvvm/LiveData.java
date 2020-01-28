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

public class LiveData {
    public class RestApiImpl extends ViewModel{

       private MutableLiveData<MoviesData> currentName;
       Context context;

       public MutableLiveData<MoviesData> getMoviesDataMutableLiveData()
       {
           if (currentName == null) {
               currentName.observe((LifecycleOwner) context, new Observer<MoviesData>() {
                   @Override
                   public void onChanged(@Nullable MoviesData moviesData) {
                       moviesData=getDataNet();
                   }
               });
           }
           return currentName;
       }

        public MoviesData getDataNet()
        {
            Api api= ApiService.getRetrofit().create(Api.class);
            try {
                return api.getMovie(1).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }


    }

}
