package com.kotlin.mvp.net

interface ResponseHandler<T> {

    fun onError(msg: String?)

    fun onSuccess(result: T)

}