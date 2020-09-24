package com.kotlin.app.ui.dashboard.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class DemoService : Service(){

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)
        return Service.START_STICKY
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}