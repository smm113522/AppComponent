package com.kotlin.demo.net.bean

data class BodyBean<T>(
    var returnCode: String,
    var returnMsg: String,
    var returnData: T
)