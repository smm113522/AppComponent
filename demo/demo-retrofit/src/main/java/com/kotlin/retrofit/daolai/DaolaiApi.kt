package com.kotlin.retrofit.daolai

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface DaolaiApi {

    companion object {
        fun get(): DaolaiApi {
            val clintBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .baseUrl("http://59.111.89.141:8769/")
                .client(clintBuilder.build())
//                .addCallAdapterFactory()
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .build()
                .create(DaolaiApi::class.java)

        }
    }

    /**
     * 注册发送验证码
     */
    @POST("sounds/user/getVcode")
    fun getVcode(@Query("phone") phone: String): Call<BodyBean<String>>

    //注册
    @POST("sounds/user/userNewRegister")
    fun register(
        @Query("phone") accountNumber: String,
        @Query("vcode") vcode: String,
        @Query("password") password: String
    ): Call<BodyBean<String>>

    //登录
    @POST("sounds/user/v2/login")
    fun login(
        @Query("accountNumber") accountNumber: String,
        @Query("vcode") vcode: String,
        @Query("type") type: String,
        @Query("password") password: String
    ): Call<BodyBean<UserInfo>>
}