package com.kotlin.update

import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.base.java.BaseMvvmActivity
import com.kotlin.update.databinding.ActivityUpdataTestBinding
import com.pgyer.pgyersdk.PgyerSDKManager

@Route(path = "/updata/activity")
class UpDataTestActivity : BaseMvvmActivity<ActivityUpdataTestBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_updata_test;

    override fun initView() {
         PgyerSDKManager.checkSoftwareUpdate(this)

        dataBinding.btUpdata.setOnClickListener {



        }
    }

    override fun initData() {

    }
}