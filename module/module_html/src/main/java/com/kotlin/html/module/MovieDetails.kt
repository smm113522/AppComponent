package com.kotlin.html.module

data class MovieDetails(var title: String, var titleShort: String,//标题
                        var imageUrl: String,// 封面
                        var author: String,//作者
                        var synopsis: String,// 简介
                        var imageList: List<String>,//图片集合
                        var download_bt: String,//bt
                        var download_magnetic: String,//磁力
                        var post_content: String//包含导演评分，编剧主演
)