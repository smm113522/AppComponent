package com.kotlin.net


/**
 * @author 2020/11/4
 * @description 基类
 */
open class BaseResponse<T>(
    var data: T? = null,
    var code: Int = -1,
    var msg: String = "",
    var success: Boolean = true
) {
    fun isOk(): Boolean {
        if (code == 200) {
            return true
        } else if (code == 401) {
            return false
        } else {
            return false
        }
    }
}