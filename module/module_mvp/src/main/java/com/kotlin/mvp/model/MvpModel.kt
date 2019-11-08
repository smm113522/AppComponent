package com.kotlin.mvp.model

import java.util.ArrayList

class MvpModel {

    /**
     * 刷新
     */
    fun loadData(): ArrayList<String> {
        var list = ArrayList<String>()
        list.add("2222")
        list.add("2222")
        list.add("2222")
        list.add("2222")
        list.add("2222")
        return list
    }

    /**
     * 更多
     */
    fun loadMoreData(): ArrayList<String> {
        var list = ArrayList<String>()
        list.add("5555")
        list.add("5555")
        list.add("5555")
        list.add("5555")
        list.add("5555")
        return list
    }



}