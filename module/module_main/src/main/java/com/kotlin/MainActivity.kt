package com.kotlin

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.btmv.module_main.R
import com.code.base.BaseActivity

class MainActivity : BaseActivity() {

    lateinit var recyclerview : RecyclerView

    override fun getLayout(): Int {
        return 0
    }

    override fun getViewDataLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        recyclerview = findViewById(R.id.recyclerview)
        val spanCount = 3
        recyclerview.setLayoutManager(GridLayoutManager(applicationContext, spanCount))
//        recyclerview.addItemDecoration(GridDividerItemDecoration(getContext(), spanCount))


    }


}