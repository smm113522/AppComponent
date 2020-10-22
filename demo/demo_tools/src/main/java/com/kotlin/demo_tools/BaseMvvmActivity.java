package com.kotlin.demo_tools;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.ParameterizedType;

public abstract class BaseMvvmActivity<VM extends ViewModel, VDB extends ViewDataBinding> extends AppCompatActivity {
    protected VM mViewModel;
    protected VDB mViewDataBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mViewDataBind = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBind.setLifecycleOwner(this);
        //获得泛型参数的实际类型
        Class<VM> vmClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        mViewModel = ViewModelProviders.of(this).get(vmClass);

        afterCreate();
    }


    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void afterCreate();

}