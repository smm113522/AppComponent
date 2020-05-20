package com.kotlin.main

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.code.base.BaseActivity
import com.code.utils.RouterPath
import com.kotlin.main.service.DownloadService
import com.kotlin.main.service.IDownloadeInter
import kotlinx.android.synthetic.main.activity_test.*


@Route(path = RouterPath.path_service_activity)
class ServiceActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_test

    lateinit var downloadBinder : DownloadService.DownloadBinder;

    val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as DownloadService.DownloadBinder
            downloadBinder.service.setCallBack(object : IDownloadeInter {
                override fun getResult(num: Int) {
                    tv_txt.text = "计数" + num
                }
            })
//            downloadBinder.stoptDownload()
//            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d("hello",name.toString())

        }

        override fun onBindingDied(name: ComponentName) {

        }

        override fun onNullBinding(name: ComponentName) {

        }

    }

    override fun initView() {

        var mSocketIntent = Intent(this, DownloadService::class.java)
        bindService(mSocketIntent, connection, BIND_AUTO_CREATE);//获取到Socket服务实例

        bt_begin.setOnClickListener {
            downloadBinder.service.start()
        }

        bt_end.setOnClickListener {
            downloadBinder.service.stop()
        }

//        tv_txt.text = "dddd"

    }



}