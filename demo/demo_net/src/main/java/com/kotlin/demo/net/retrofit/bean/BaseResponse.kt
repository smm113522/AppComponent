package com.kotlin.demo.net.retrofit.bean

/**
 * @author Laizexin on 2020/10/12
 * @description
 */
open class BaseResponse<T>(var data: T, var errorCode: Int = -1, var errorMsg: String = "")
