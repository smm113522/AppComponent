package com.kotlin.module_ui

data class Bean(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
)

data class Data(
    val cityName: String,
    val countyName: String,
    val id: Int,
    val name: String,
    val provinceName: String
)