package com.kotlin.mvp.view

interface MvpView {
    fun onError(txt:String)
    fun loadRefreshSucess(list:ArrayList<String>)
    fun loadMoreSucess(list:ArrayList<String>)
}