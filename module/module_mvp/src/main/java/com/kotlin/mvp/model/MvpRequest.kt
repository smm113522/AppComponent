package com.kotlin.mvp.model

import com.kotlin.mvp.bean.Categories
import com.kotlin.mvp.net.MRequest
import com.kotlin.mvp.net.ResponseHandler

class MvpRequest(handler: ResponseHandler<Categories>) :
        MRequest<Categories>("http://gank.io/api/xiandu/categories",handler) {
}