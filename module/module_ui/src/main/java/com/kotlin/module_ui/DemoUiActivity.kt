package com.kotlin.module_ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DemoUiActivity : AppCompatActivity() {


    var txt = "{\"code\":200,\"data\":[{\"name\":\"王浩武\",\"id\":12,\"provinceName\":\"天津\",\"cityName\":\"天津市\",\"countyName\":\"河东区\"},{\"name\":\"张三\",\"id\":16,\"provinceName\":\"北京\",\"cityName\":\"北京市\",\"countyName\":\"东城区\"},{\"name\":\"记上\",\"id\":17,\"provinceName\":\"北京\",\"cityName\":\"北京市\",\"countyName\":\"西城区\"}],\"msg\":\"执行成功\"}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_ui)

    }

}