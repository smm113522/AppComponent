package com.code.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.code.BuildConfig

open class BaseApp : Application() {

    companion object {
        private var instance: Application? = null
        fun getInstance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()               // 打印日志
            ARouter.openDebug()             // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)       // 尽可能早，推荐在Application中初始化

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