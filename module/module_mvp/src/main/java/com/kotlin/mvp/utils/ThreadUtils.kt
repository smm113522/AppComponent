package com.kotlin.mvp.utils

import android.os.Handler
import android.os.Looper

object ThreadUtils {

    var handle = Handler(Looper.getMainLooper())
    /*
        运行主线中
     */
    fun runOnMainThread(runnable: Runnable) {
        handle.post(runnable)
    }

}