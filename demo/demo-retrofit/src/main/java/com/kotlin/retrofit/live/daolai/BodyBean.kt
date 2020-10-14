package com.kotlin.retrofit.live.daolai

data class BodyBean<T>(
    var returnCode: String,
    val returnMsg: String,
    val returnData: T?
)