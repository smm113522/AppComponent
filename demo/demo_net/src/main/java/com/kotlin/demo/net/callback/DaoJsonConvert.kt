package com.kotlin.demo.net.callback

import com.drake.net.convert.DefaultConvert
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class DaoJsonConvert : DefaultConvert(code = "returnCode", message = "returnMsg", success = "200") {

    override fun <S> String.parseBody(succeed: Type): S? {
        return GsonBuilder().serializeNulls().create().fromJson(this, succeed)
    }
}