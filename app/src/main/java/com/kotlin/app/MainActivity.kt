package com.kotlin.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val uri = intent.data
        val uri = "/test/activity"
        ARouter.getInstance().build(uri).navigation()
        finish()
    }
}
