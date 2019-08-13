package com.code.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getViewDataLayout() == 0) {
            setContentView(getLayout())
        } else {
            binding = DataBindingUtil.setContentView(this, getViewDataLayout())
        }

        initView()
    }

    abstract fun getLayout(): Int;

    abstract fun getViewDataLayout(): Int;

    abstract fun initView();

}