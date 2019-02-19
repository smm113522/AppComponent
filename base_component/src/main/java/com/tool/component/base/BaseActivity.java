package com.tool.component.base;

import android.app.Activity;
import android.os.Bundle;

import com.tool.component.utils.ToastUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
    }
    public abstract int getLayout();
    public abstract void initView();

    public Activity getActivity(){
        return this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.cancelAll();
    }
}
