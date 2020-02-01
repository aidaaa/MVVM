package com.example.mytestmodule.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytestmodule.R;
import com.example.mytestmodule.databinding.MovieItemBinding;
import com.example.mytestmodule.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieviewHolder> {

    ArrayList<MovieViewModel> movieViewModels;
    LayoutInflater inflater;

    public MovieAdapter(ArrayList<MovieViewModel> movieViewModels) {
        this.movieViewModels = movieViewModels;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater==null)
            inflater=LayoutInflater.from(viewGroup.getContext());
        MovieItemBinding movieItemBinding= DataBindingUtil.inflate(inflater, R.layout.movie_item,viewGroup,false);
        return new MovieviewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieviewHolder viewHolder, int i) {
        viewHolder.bind(movieViewModels.get(i));
    }

    @Override
    public int getItemCount() {
        return movieViewModels.size();
    }

    public class MovieviewHolder extends RecyclerView.ViewHolder
    {
        MovieItemBinding movieItemBinding;

        public MovieviewHolder(@NonNull  MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding=movieItemBinding;
        }

        public void bind(MovieViewModel movieViewModel)
        {
            movieItemBinding.setMovie(movieViewModel);
            movieItemBinding.executePendingBindings();
        }
    }
}
