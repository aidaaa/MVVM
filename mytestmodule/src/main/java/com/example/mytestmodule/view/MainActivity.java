package com.example.mytestmodule.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mytestmodule.R;
import com.example.mytestmodule.databinding.ActivityMainBinding;
import com.example.mytestmodule.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieViewModel movieViewModel=new MovieViewModel(this);
        ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setRecycler(movieViewModel);
    }
}
