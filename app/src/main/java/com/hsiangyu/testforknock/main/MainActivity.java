package com.hsiangyu.testforknock.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsiangyu.testforknock.R;
import com.hsiangyu.testforknock.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;

    /* -------------------------- Life cycle */

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.destroy();
        }
    }

    /* ---------------------------------- Binding */

    /**
     * Initial binding
     */
    private void initBinding() {
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel = new MainViewModel(this);
        binding.setViewModel(viewModel);
    }
}
