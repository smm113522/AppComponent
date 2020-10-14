package com.kotlin.retrofit.livadeta

import androidx.lifecycle.LiveData
import com.kotlin.demo.net.retrofit.bean.BannerBean
import com.kotlin.demo.net.retrofit.bean.BaseResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface LiveApi {

    companion object {
        fun get(): LiveApi {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .client(clientBuilder.build())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LiveApi::class.java)
        }
    }

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun bannerLiveList(): LiveData<BaseResponse<List<BannerBean>>>

    @GET("banner/json")
    fun bannerList(): Call<BaseResponse<List<BannerBean>>>
}