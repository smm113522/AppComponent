package com.kotlin.demo.net

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.drake.net.cacheEnabled
import com.drake.net.initNet
import com.kotlin.demo.net.callback.DaoJsonConvert
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class NetApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            ClassicsHeader(this)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context)
        }

        initNet(Contents.HTTPS) {
            converter(DaoJsonConvert())
            cacheEnabled()
        }


    }

}