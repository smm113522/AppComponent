package com.kotlin.demo.net

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.kotlin.demo.net.bean.BodyBean
import com.kotlin.demo.net.bean.VersionBean
import com.kotlin.demo.net.okhttp.OkHttpUtils
import com.kotlin.demo.net.retrofit.BaseObserver
import com.kotlin.demo.net.retrofit.WanApi
import com.kotlin.demo.net.retrofit.bean.BannerBean
import com.kotlin.demo.net.retrofit.bean.BaseResponse
import com.kotlin.demo.net.retrofit.execute
import kotlinx.android.synthetic.main.activity_demo_net.*
import java.util.concurrent.Executors

class NetLiveActivity : AppCompatActivity(R.layout.activity_demo_net) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh.autoRefresh()
        bt_get.setOnClickListener {
            scopeNetLife {
                result.text =
                    Get<BodyBean<VersionBean>>("/sounds/sys/v2/getVersion?platform=android") {
                    }.await()
                        .toString()
            }

        }
        var liveData: MutableLiveData<BaseResponse<List<BannerBean>>> = MutableLiveData()
        var observable = WanApi.get().bannerList();
        observable.execute(BaseObserver(liveData, null))
        liveData.observe(this, androidx.lifecycle.Observer { response ->
            result.text = response.toString()

        })
//            .subscribe {
//                result.text = it.toString()
//            }


//        var executors = Executors.newSingleThreadExecutor();
//        executors.execute{
//            var data = OkHttpUtils.getInstance()
//                .getData(Contents.HTTPS + "/sounds/sys/v2/getVersion?platform=android")
//            result.text = data.body?.string()
//
//        }
//        executors.shutdown()

//        result.text = getData()// 不可用的
        /*OkHttpUtils.getInstance()
            .getDataAsyn(Contents.HTTPS + "/sounds/sys/v2/getVersion?platform=android",
                object : OkHttpUtils.NetCall {
                    override fun failed(call: Call?, e: IOException?) {

                    }

                    override fun success(call: Call?, response: Response) {
                        if (response.isSuccessful) {
                            result.text = response.body?.string()
                            println(response.body.toString())
                        }
                    }
                })*/

    }

    fun getData(): String {
        var data = ""
        var executors = Executors.newSingleThreadExecutor();
        executors.execute {
            var response = OkHttpUtils.getInstance()
                .getData(Contents.HTTPS + "/sounds/sys/v2/getVersion?platform=android")
            data = response.body?.string().toString()
        }
        executors.shutdown()
        return data;
    }
}