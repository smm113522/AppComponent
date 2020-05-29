package com.kotlin.video.ui

import android.support.v4.app.Fragment
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.code.base.BaseNoModelActivity
import com.kotlin.code.utils.LogUtils
import com.kotlin.code.utils.RouterPath
import com.kotlin.video.R
import com.kotlin.video.databinding.ActivityFragmentBinding


@Route(path = RouterPath.path_fragment_activity)
class FragmentActvity : BaseNoModelActivity<ActivityFragmentBinding>() {

    @JvmField
    @Autowired
    var fragmentPath = ""

    override fun onCreate(): Int = R.layout.activity_fragment

    override fun initView() {
        ARouter.getInstance().inject(this)
        if (!TextUtils.isEmpty(fragmentPath)) {
            LogUtils.debug(fragmentPath)
            val fragment = ARouter.getInstance().build(fragmentPath).navigation() as Fragment
            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit()
            }
        }

    }

    override fun initData() {

    }
}