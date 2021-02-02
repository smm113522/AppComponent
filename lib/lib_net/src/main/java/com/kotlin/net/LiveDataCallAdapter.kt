package com.kotlin.net

import androidx.lifecycle.LiveData
import com.kotlin.net.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<T>(private val responseType: Type) : CallAdapter<T, LiveData<T>> {

    override fun adapt(call: Call<T>): LiveData<T> {
        return object : LiveData<T>() {
            private val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {//确保执行一次
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, thread: Throwable) {
                            var message: String = thread.message as String
                            message?.let { it ->
                                if (it.contains("Failed to connect")) {
                                    try {
                                        var response =
                                            BaseResponse(data = null, code = -1, msg = "服务器异常") as T
                                        postValue(response)
                                    } catch (e: java.lang.Exception) {
                                        e.printStackTrace()
                                    }
                                } else {
                                    val value = BaseResponse<Objects>(
                                        null,
                                        -1,
                                        thread.message ?: "获取数据失败"
                                    ) as T

                                    postValue(value)
                                }
                            }
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            if (response.isSuccessful) {
                                val body = response.body()
//                                Log.d("xxxxxxxxxxxxxx", body.toString())
                                postValue(body)
                            } else {
                                var code = response.code()
                                when (code) {
                                    404 -> {
                                        val value = BaseResponse(
                                            null,
                                            code,
                                            "服务器地址找不到了"
                                        ) as T

                                        postValue(value)
                                    }
                                    500 -> {
                                        var error = response.message()
                                        if (error != null) {
                                            val value = BaseResponse(
                                                null,
                                                -1,
                                                error
                                            ) as T

                                            postValue(value)
                                        } else {
                                            val value = BaseResponse(
                                                null,
                                                -1,
                                                "获取数据失败"
                                            ) as T

                                            postValue(value)
                                        }
                                    }
                                }

                            }
                        }
                    })
                }
            }
        }
    }


    override fun responseType() = responseType
}