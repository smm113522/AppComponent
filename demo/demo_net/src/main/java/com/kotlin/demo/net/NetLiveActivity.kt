package com.kotlin.demo.net

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.kotlin.demo.net.bean.BodyBean
import com.kotlin.demo.net.bean.VersionBean
import kotlinx.android.synthetic.main.activity_demo_net.*

class NetLiveActivity : AppCompatActivity(R.layout.activity_demo_net) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh.autoRefresh()
        bt_get.setOnClickListener {
            scopeNetLife {
                result.text =
                    Get<BodyBean<VersionBean>>("/sounds/sys/v2/getVersion?platform=android"){

                    }.await()
                        .toString()
            }

        }



    }
}