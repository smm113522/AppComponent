package com.kotlin.net

import android.text.TextUtils
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//
/*入口 为了获取  网络请求*/

interface RetrofitApi {

    companion object {
        //        var url = "http://192.168.1.13:8090/"
        var url = "http://192.168.1.67:8090/"
//        var url = "http://192.168.1.88:8090/"

        fun get(isToken: Boolean): Retrofit {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
//                .addNetworkInterceptor(getNetIntercepter())//添加Net拦截器
                .addInterceptor { chain -> //添加App拦截器
                    val original = chain.request()

                    val chainBuilder = original.newBuilder()

                    if (isToken) {
//                        var token = .decodeString("token")
//                        if (!TextUtils.isEmpty(token)) {
//                            chainBuilder
//                                .header(
//                                    "Authorization",
//                                    token
//                                )
//                        }
                    }

                    var request = chainBuilder.method(original.method(), original.body())
                        .build()

                    chain.proceed(request)
                }

            return Retrofit.Builder()
                .baseUrl(url)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
        }

        private fun getNetIntercepter(): Interceptor {
            return Interceptor { chain ->
                val request: Request = chain.request()

//                if (NetworkUtils.isConnected()){
//                    chain.proceed(request);
//                }else{
//                }

                Log.d("wyz", "net拦截请求前,url:$request")

                val response = chain.proceed(request)
                Log.d("wyz", "net拦截请求后")
                response
            }
        }
    }


}