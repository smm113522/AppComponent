package com.kotlin.retrofit.live.daolai

import androidx.lifecycle.LiveData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface LiveApi {

    companion object {
        fun get(): LiveApi {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .baseUrl("http://59.111.89.141:8769/")
                .client(clientBuilder.build())
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
//                .addConverterFactory(ResponseConverterFactory.create())
                    // adapter 好像配置了，对于gsonconverter 就不起作用了。
                    // 先写converterfactory 才行。。。执行完获取正确的数据之后 在进行数据转换就可以了。
                .build()
                .create(LiveApi::class.java)
        }
    }

    //登录
    @POST("sounds/user/v2/login")
    fun login(
        @Query("accountNumber") accountNumber: String,
        @Query("vcode") vcode: String,
        @Query("type") type: String,
        @Query("password") password: String
    ): LiveData<BodyBean<UserInfo>>
}