package com.code.base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.code.BuildConfig
import com.qihoo360.replugin.RePlugin
import com.qihoo360.replugin.RePluginApplication
import com.qihoo360.replugin.sdk.HostCallbacks
import com.qihoo360.replugin.RePluginCallbacks
import com.qihoo360.replugin.sdk.HostEventCallbacks
import com.qihoo360.replugin.RePluginConfig
import com.qihoo360.replugin.sdk.PluginConfig
import com.qihoo360.replugin.sdk.PluginManager
import com.tencent.bugly.beta.Beta


open class BaseApp : RePluginApplication() {

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

        //1、设置AppKey
        PluginConfig.setAppKey("qh1uzr0mp2zi");
        PluginManager.init(getApplicationContext());

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

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        RePlugin.enableDebugger(base, BuildConfig.DEBUG)

        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base)
        // TODO: 安装tinker
        Beta.installTinker(this)
    }

    override fun createConfig(): RePluginConfig {
        val config = RePluginConfig()
        config.isUseHostClassIfNotFound = true
        config.verifySign = !BuildConfig.DEBUG
        //2、设置回调
        config.eventCallbacks = HostEventCallbacks(this)
        return config
    }

    override fun createCallbacks(): RePluginCallbacks {
        //3、设置回调
        return HostCallbacks(this)
    }


}