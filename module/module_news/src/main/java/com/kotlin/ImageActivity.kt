package com.kotlin

import com.alibaba.android.arouter.facade.annotation.Route
import com.code.base.BaseActivity
import com.code.utils.RouterPath

@Route(path = RouterPath.ImageCenter.path_image)
class ImageActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_image
    }

    override fun getViewDataLayout(): Int {
        return 0
    }

    override fun initView() {

    }


}