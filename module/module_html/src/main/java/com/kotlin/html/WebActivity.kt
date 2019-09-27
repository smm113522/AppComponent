package com.kotlin.html

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.code.base.BaseActivity
import com.code.utils.RouterPath
import com.alibaba.android.arouter.facade.annotation.Autowired


@Route(path = RouterPath.path_web_activity)
class WebActivity : BaseActivity() {

    @Autowired
    @JvmField var url: String? = null
    @Autowired
    @JvmField var title: String? = null

    override fun getLayoutId(): Int = R.layout.activity_web

    override fun initView() {
        ARouter.getInstance().inject(this);



    }


}