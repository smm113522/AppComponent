package com.kotlin.apt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.code.utils.NavigationUtil
import com.code.utils.RouterPath


@Route(path = RouterPath.path_apt_activity)
class AptActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apt)
    }

//    @MyRoute(paths = "")
//    @MyRoute(paths = RouterPath.path_pdf_activity)
    fun ButtonCS(view : View){
        NavigationUtil.toActivity(RouterPath.path_pdf_activity)
    }


}