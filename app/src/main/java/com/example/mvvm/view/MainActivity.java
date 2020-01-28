package com.example.mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserViewModel userViewModel=new UserViewModel(this);
        ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setUser(userViewModel);
    }
}
