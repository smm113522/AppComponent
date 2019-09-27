package com.kotlin.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Message

class DownloadService : Service() {

    var mBinder = DownloadBinder()

    inner class DownloadBinder : Binder(){

        val service: DownloadService
            get() = this@DownloadService

        fun startDownload(){

        }

        fun stoptDownload(){

        }

        fun getProgress() : Int{
            return 0
        }

    }
    var numberReader = 1

    var handler = object : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg!!.what == 0){
                numberReader ++
                mCallBack?.getResult(numberReader)
                start()
            }
        }

    }
    private var mCallBack: IDownloadeInter? = null

    fun setCallBack(callBack: IDownloadeInter) {
        mCallBack = callBack
    }


    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()

    }

    fun stop(){
        var message = Message()
        message.what = 1
        handler.removeMessages(0)
    }

    fun start(){
        Thread(Runnable {
            var message = Message()
            message.what = 1
            handler.sendMessageAtTime(Message(),10000)
        }).start()

    }

}