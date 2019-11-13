package com.kotlin.mvp.net

import com.kotlin.mvp.utils.ThreadUtils
import okhttp3.*
import java.io.IOException

class NetManager private constructor(){

    val client by lazy {
        OkHttpClient()
    }

    companion object{
        val manager by lazy {
            NetManager()
        }
    }

    fun <T>sendResquest(mRequest: MRequest<T>){

        var request = Request.Builder()
                .url(mRequest.url)
                .get()
                .build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 子线程里面
             */
            override fun onFailure(call: Call, e: IOException) {

                ThreadUtils.runOnMainThread(object : Runnable {
                    override fun run() {
                        mRequest.handler.onErrorData(e?.message)
                    }
                })
            }

            override fun onResponse(call: Call, response: Response) {
                var result = response.body?.string()

                var parseResult = mRequest.parseResult(result)

                ThreadUtils.runOnMainThread(object : Runnable {
                    override fun run() {
                        mRequest.handler.onSuccessData(parseResult)
                    }
                })
            }

        })

    }

}