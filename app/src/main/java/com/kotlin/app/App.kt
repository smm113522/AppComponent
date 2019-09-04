package com.kotlin.app

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.os.Build
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.code.BuildConfig
import com.code.base.BaseApp
import com.tencent.bugly.beta.Beta

class App : BaseApp() {


    override fun onCreate() {
        super.onCreate()
        TinkerUtils.onCreate(this)
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    fun registerActivityLifecycleCallback(
            callbacks: Application.ActivityLifecycleCallbacks) {
        registerActivityLifecycleCallbacks(callbacks)
    }

    override fun onTerminate() {
        super.onTerminate()
        Beta.unInit()
    }

}