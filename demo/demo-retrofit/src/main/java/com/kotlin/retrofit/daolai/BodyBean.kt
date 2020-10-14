package com.kotlin.retrofit.daolai

data class BodyBean<T>(
    var returnCode: String,
    val returnMsg: String,
    val returnData: T
)