package com.kotlin.mvp.net

interface ResponseHandler<T> {

    fun onErrorData(msg: String?)

    fun onSuccessData(result: T)

}