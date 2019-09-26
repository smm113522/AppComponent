package com.kotlin.html.module

data class MainItem(var name: String, var timeString: String,
                    var lookNum: String, var commentNum: String,
                    var url: String,
                    var imageUrl: String,
                    var setting: Boolean,
                    var classification: List<Classification>)