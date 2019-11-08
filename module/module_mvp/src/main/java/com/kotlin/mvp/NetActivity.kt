package com.kotlin.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.kotlin.mvp.utils.ThreadUtils
import kotlinx.android.synthetic.main.activity_net.*
import okhttp3.*
import java.io.IOException

class NetActivity : AppCompatActivity() {

    var url = "http://gank.io/api/xiandu/categories";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)
        /// 数据初始化

        var client = OkHttpClient()

        var request = Request.Builder()
                .url(url)
                .get()
                .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程里面
             */
            override fun onFailure(call: Call, e: IOException) {
//                Toast.makeText(applicationContext,"获取数据失败",Toast.LENGTH_SHORT).show()
                print("获取数据失败")
            }

            override fun onResponse(call: Call, response: Response) {

//                print("获取数据成功")

                ThreadUtils.runOnMainThread(object : Runnable {
                    override fun run() {
                        var result = response.body?.string()
                        Log.d("okhttp",result)
                        Toast.makeText(this@NetActivity, "获取数据成功", Toast.LENGTH_SHORT).show()
                        tv_result.text = result
                    }
                })
            }

        })


    }

}