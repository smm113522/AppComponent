package com.kotlin.video

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.utils.RouterPath

@Route(path = RouterPath.path_video_activity)
class DemoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
    }

}