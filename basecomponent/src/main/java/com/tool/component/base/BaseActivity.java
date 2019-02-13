package com.tool.component.base;

import android.os.Bundle;

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

}
