package com.kotlin

import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.btmv.module_main.R
import com.code.base.BaseActivity
import com.code.utils.RouterPath
import com.code.utils.ToastsUtils

@Route(path = "/test/activity")
class TestActivity : BaseActivity() {

    override fun getLayout(): Int {
        return 0
    }

    override fun getViewDataLayout(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        ToastsUtils.showToast("hhhh",true)

        var uri = "/other/activity"
        uri = "/image/activity"
        uri = RouterPath.ImageCenter.path_image


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
    }



}