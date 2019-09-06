package com.kotlin.app

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.os.Build
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.argusapm.android.api.Client
import com.code.BuildConfig
import com.code.base.BaseApp
import com.tencent.bugly.beta.Beta
import com.argusapm.android.api.Client.startWork
import com.argusapm.android.core.Config
import com.argusapm.android.network.upload.CollectDataSyncUpload
import com.argusapm.android.network.cloudrule.RuleSyncRequest
import com.argusapm.android.core.Config.ConfigBuilder
import com.qihoo360.loader2.PluginStatusController.setAppContext
import com.tencent.bugly.crashreport.CrashReport.setAppVersion



class App : BaseApp() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        val builder = Config.ConfigBuilder()
                .setAppContext(this)
                .setAppName("apm_demo")
                .setRuleRequest(RuleSyncRequest())
                .setUpload(CollectDataSyncUpload())
                .setAppVersion("0.0.1")
                .setApmid("qh1uzr0mp2zi")
        Client.attach(builder.build())
        Client.startWork()
    }

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