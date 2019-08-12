//package com.kotlin.app
//
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import com.alibaba.android.arouter.facade.Postcard
//import com.alibaba.android.arouter.facade.callback.NavCallback
//import com.alibaba.android.arouter.launcher.ARouter
//
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
////        val uri = intent.data
//        val uri = "/other/activity"
//        ARouter.getInstance().build(uri)
//                .navigation(this, object : NavCallback() {
//
//                    override fun onFound(postcard: Postcard?) {
//                        Log.d("ARouter", "找到了")
//                    }
//
//                    override fun onLost(postcard: Postcard?) {
//                        Log.d("ARouter", "找不到了")
//                    }
//
//                    override fun onArrival(postcard: Postcard) {
//                        Log.d("ARouter", "跳转完了")
//                    }
//
//                    override fun onInterrupt(postcard: Postcard?) {
//                        Log.d("ARouter", "被拦截了")
//                    }
//
//                })
////        finish()
//    }
//}
