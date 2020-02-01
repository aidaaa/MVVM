package com.example.mytestmodule.test.testview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mytestmodule.R;
import com.example.mytestmodule.databinding.ActivityTestMainBinding;
import com.example.mytestmodule.test.testviewmodel.TestViewModel;

public class TestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestViewModel model=new TestViewModel(this);
        ActivityTestMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_test_main);
        binding.setMain(model);
    }
}
