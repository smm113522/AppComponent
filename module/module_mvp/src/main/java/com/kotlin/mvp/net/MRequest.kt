package com.kotlin.mvp.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType


open class MRequest<T>(var url: String, val handler: ResponseHandler<T>) {

    /**
     * 解析数据
     */
    fun parseResult(result: String?): T {
        val gson = Gson()
        //获取泛型类型
        val type = (this.javaClass
                .genericSuperclass as ParameterizedType).getActualTypeArguments()[0]
        val result = gson.fromJson<T>(result, type)
        return result
    }

    /**
     * 执行 request
     */
    fun exeute(){
        NetManager.manager.sendResquest(this)
    }


}