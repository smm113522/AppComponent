package com.code.base

import android.app.Application
import android.content.Context

open class BaseApp : Application() {


    override fun onCreate() {
        super.onCreate()

    }

    override fun onLowMemory() {
        super.onLowMemory()

    }

    override fun onTerminate() {
        super.onTerminate()

    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }
}