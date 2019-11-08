package com.kotlin.mvp.presenter


interface MvpPresenter {
    fun loadData()
    fun loadMoreData(page: Int)
}