package com.kotlin.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kotlin.mvvm.databinding.ActivityMvvmBinding;

@Route(path = "/mvvm/activity")
public class MvvmActivity extends AppCompatActivity {

    private ActivityMvvmBinding mvvmBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvvmBinding = DataBindingUtil.setContentView(MvvmActivity.this,R.layout.activity_mvvm);
        setContentView(mvvmBinding.getRoot());



    }
}
