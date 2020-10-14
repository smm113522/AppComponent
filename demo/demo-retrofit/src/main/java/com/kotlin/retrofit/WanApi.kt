package com.kotlin.retrofit

import com.kotlin.demo.net.retrofit.bean.BannerBean
import com.kotlin.demo.net.retrofit.bean.BaseResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WanApi {

    companion object {
        fun get(): WanApi {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
//            if (BuildConfig.DEBUG) {
//                val loggingInterceptor = HttpLoggingInterceptor()
//                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//                clientBuilder.addInterceptor(loggingInterceptor)
//            }
            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .client(clientBuilder.build())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanApi::class.java)
        }
    }

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun bannerList(): Call<BaseResponse<List<BannerBean>>>


}
