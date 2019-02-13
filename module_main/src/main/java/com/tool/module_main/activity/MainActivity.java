package com.tool.module_main.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.component.base.BaseActivity;
import com.tool.module_main.R;

import androidx.annotation.Nullable;

@Route(path = "/main/activity")
public class MainActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
