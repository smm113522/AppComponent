package com.kotlin.html

import com.alibaba.android.arouter.facade.annotation.Route
import com.code.base.BaseActivity
import com.code.utils.RouterPath

@Route(path = RouterPath.path_web_activity)
class WebActivity : BaseActivity(){


    override fun getLayoutId(): Int = R.layout.activity_web

    override fun initView() {



    }


}