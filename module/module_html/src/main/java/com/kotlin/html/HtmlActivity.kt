package com.kotlin.html

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.code.base.BaseActivity
import com.code.utils.CameraThreadPool
import com.code.utils.RouterPath
import kotlinx.android.synthetic.main.activity_html.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


@Route(path = RouterPath.path_html_activity)
class HtmlActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_html
    var url = "https://www.hdwan.net"

    override fun initView() {
        bt_get_home.setOnClickListener {
            getHtmlHome()
        }
    }

    fun getHtmlHome() {
        CameraThreadPool.execute {

            var homeDocument : Document = Jsoup.connect(url).get()
            Log.e("一、HTML內容", homeDocument.toString());

            var html:String = homeDocument.html();


        }


    }

}