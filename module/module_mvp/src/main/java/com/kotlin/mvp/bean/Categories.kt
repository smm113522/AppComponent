package com.kotlin.mvp.bean

data class Categories(var error: Boolean, var results: List<Results>?)

data class Results(var _id: String, var en_name: String, var name: String, var rank: String)