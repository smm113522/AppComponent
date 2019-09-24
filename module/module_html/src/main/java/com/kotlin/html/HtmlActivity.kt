package com.kotlin.html

import com.alibaba.android.arouter.facade.annotation.Route
import com.code.base.BaseActivity
import com.code.utils.RouterPath
import kotlinx.android.synthetic.main.activity_html.*


@Route(path = RouterPath.path_html_activity)
class HtmlActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_html
    var url = "https://www.hdwan.net"

    override fun initView() {

        bt_get_home.setOnClickListener {


        }



    }

}