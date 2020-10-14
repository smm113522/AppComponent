package com.kotlin.demo.net.retrofit

import androidx.lifecycle.LiveData
import com.kotlin.demo.net.BuildConfig
import com.kotlin.demo.net.retrofit.bean.BannerBean
import com.kotlin.demo.net.retrofit.bean.BaseResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface WanApi {
    companion object {
        fun get(): WanApi {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
            }
            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .client(clientBuilder.build())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanApi::class.java)
        }
    }

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun bannerList(): Observable<BaseResponse<List<BannerBean>>>

//    @GET("banner/json")
//    fun bannerList(): LiveData<BaseResponse<List<BannerBean>>>
}
