package com.kotlin.app

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.os.Build
import com.argusapm.android.api.Client
import com.argusapm.android.core.Config
import com.argusapm.android.network.cloudrule.RuleSyncRequest
import com.argusapm.android.network.upload.CollectDataSyncUpload
import com.code.base.BaseApp
import com.tencent.bugly.beta.Beta


class App : BaseApp() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        val builder = Config.ConfigBuilder()
                .setAppContext(this)
                .setAppName("kotlin_demo")
                .setRuleRequest(RuleSyncRequest())
                .setUpload(CollectDataSyncUpload())
                .setAppVersion("0.0.1")
                .setApmid("phoa7kmj9fpa")
        Client.attach(builder.build())
        Client.startWork()
    }

    companion object{
        var app : App? = null
        fun getInstance(): App? {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    override fun onTerminate() {
        super.onTerminate()
        Beta.unInit()
    }

}