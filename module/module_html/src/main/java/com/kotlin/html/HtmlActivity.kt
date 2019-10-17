package com.kotlin.html

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.code.base.BaseActivity
import com.code.utils.CameraThreadPool
import com.code.utils.RouterPath
import com.kotlin.html.module.Classification
import kotlinx.android.synthetic.main.activity_html.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import android.system.Os.link
import android.R.attr
import android.text.TextUtils
import com.code.utils.NavigationUtil
import com.kotlin.html.module.MainItem
import com.kotlin.html.module.MovieDetails

/**
 * https://www.cnblogs.com/yueshutong/p/9381530.html
 */

@Route(path = RouterPath.path_html_activity)
class HtmlActivity : BaseActivity() {

    private val TAG = "HtmlActivity"

    override fun getLayoutId(): Int = R.layout.activity_html
    var url = "https://www.hdwan.net"
    var url1 = "https://www.hdwan.net/page/1"
    var urlDetails = "https://www.hdwan.net/44091.html"

    override fun initView() {
        bt_get_home.setOnClickListener {
            NavigationUtil.toWebActivity("http://www.baidu.com","baidu")
        }
        getHtmlHome()

    }

    var html = "";
    fun getHtmlHome() {
        CameraThreadPool.execute {

            var homeDocument: Document = Jsoup.connect(url).get()
            Log.e(TAG, homeDocument.toString());

            html = homeDocument.html();
//            var list = getListClassification(html)
//            Log.e(TAG, list.toString())

            var main_list = getListItem(html)
            Log.e(TAG, main_list.toString())

            var detailsDocument: Document = Jsoup.connect(urlDetails).get()

            var movieDetails = getMovieDetails(detailsDocument.html())
            Log.e(TAG, movieDetails.toString())

        }
    }

    fun getListClassification(html: String): List<Classification> {
        var list = ArrayList<Classification>();
        val doc = Jsoup.parseBodyFragment(html)
        val body = doc.body()

        list.add(Classification("首页", url))

        val document = Jsoup.parse(body.html())
//        var elements = document.getElementsByClass("mainmenus container")
//
//        var tensorflow = elements.html();
//        val document1 = Jsoup.parse(tensorflow)
//        var elementClass = document1.getElementsByClass("mainmenu")
//
//        var tensorflow1 = elementClass.html();
//        val document2 = Jsoup.parse(tensorflow1)
//        var elementClass1 = document2.getElementsByClass("topnav")
//
//        var tensorflow2 = elementClass1.html();
//        val document3 = Jsoup.parse(tensorflow2)
//
//        var elementClass2 = document3.getElementsByClass("menu")

        var elements = document.getElementsByClass("mainmenus container")
                .select("div.mainmenu")
                .select("div.topnav")
                .select("ul.menu")

        val document4 = Jsoup.parse(elements.html())
        val links = document4.getElementsByTag("a")
        for (it in links) {
            val linkHref = it.attr("href")
            val linkText = it.text()

            list.add(Classification(linkText, linkHref))
        }


        return list

    }

    fun getListItem(html: String): List<MainItem> {
        var list = ArrayList<MainItem>();
        val doc = Jsoup.parseBodyFragment(html)
        val body = doc.body()

        val document = Jsoup.parse(body.html())

        var elements = document.getElementsByClass("container")
                .select("div.mainleft")
                .select("ul#post_container")
        Log.e(TAG, elements.html())
        val document4 = Jsoup.parse(elements.html())
        val links = document4.getElementsByTag("li")
        for (it in links) {
            Log.e(TAG, it.toString())
            var mainItem = MainItem("", "", "",
                    "", "", "", false, emptyList())
            var setting = it.getElementsByClass("sticky").text()
            mainItem.setting = !TextUtils.isEmpty(setting)// 是否置顶

            var thumbnail = it.getElementsByClass("thumbnail").html()
            val docThum = Jsoup.parse(thumbnail)
            val elementa = docThum.getElementsByTag("a")

            mainItem.url = elementa.attr("href")

            val elements = docThum.getElementsByTag("img")
            mainItem.name = docThum.title()

            mainItem.imageUrl = elements.attr("src")

            mainItem.timeString = it.getElementsByClass("info_date info_ico").text()
            mainItem.lookNum = it.getElementsByClass("info_views info_ico").text()
            mainItem.commentNum = it.getElementsByClass("info_comment info_ico").get(0).text()

            // 分类
            var classification = it.getElementsByClass("info_category info_ico").html()
            val doc = Jsoup.parse(classification)
            val links = doc.getElementsByTag("a")
            var list1 = ArrayList<Classification>();

            for (it in links) {
                val linkHref = it.attr("href")
                val linkText = it.text()

                list1.add(Classification(linkText, linkHref))
            }

            mainItem.classification = list1

            list.add(mainItem)

        }

        return list
    }

    /**
     * 目前没有做完
     */
    fun getMovieDetails(html: String): MovieDetails {

        val doc = Jsoup.parseBodyFragment(html)
        val body = doc.body()

        val document = Jsoup.parse(body.html())

        var elements = document.getElementsByClass("container")
                .select("div.mainleft")

        var movieDetails = MovieDetails("", "", "", "", "",
                emptyList(), "", "", "")

        val document1 = Jsoup.parse(elements.html())
        movieDetails.author = document1.getElementsByClass("info_author info_ico").text()
        //时间
//        movieDetails.author = document1.getElementsByClass("info_date info_ico").text()
        //看的人数
//        movieDetails.author = document1.getElementsByClass("info_views info_ico").text()
        //评论数
//        movieDetails.author = document1.getElementsByClass("info_comment info_ico").text()
        //分类集合
//        movieDetails.author = document1.getElementsByClass("info_category info_ico").text()
//        var list1 = ArrayList<Classification>()
//        val links = Jsoup.parse(document1.getElementsByClass("info_category info_ico").html()).getElementsByTag("a")
//        for (it in links) {
//            val linkHref = it.attr("href")
//            val linkText = it.text()
//
//            list1.add(Classification(linkText, linkHref))
//        }
        //end

        // 下载地址 bt
        var download = document1.getElementsByClass("dw-box dw-box-download");

        val document11 = Jsoup.parse(download.html())
        var download21 = document11.getElementsByTag("a")

        movieDetails.download_bt = download21.attr("href")
        // 下载地址 magnetic
        var download1 = document1.getElementsByClass("dw-box dw-box-warning");

        val document2 = Jsoup.parse(download1.html())
        var download2 = document2.getElementsByTag("a")

        movieDetails.download_magnetic = download2.attr("href")
//        end
        elements = document1.select("div#post_content")
        Log.e(TAG, elements.toString())

        var d = elements.get(0)
        Log.e(TAG, d.toString())

//        var d1 = elements.get(1)
//        Log.e(TAG, d1.toString())
//
//        var d2 = elements.get(2)
//        Log.e(TAG, d2.toString())
//
//        var d3 = elements.get(3)
//        Log.e(TAG, d3.toString())
//
//        var d4 = elements.get(4)
//        Log.e(TAG, d4.toString())
//
//        var d5 = elements.get(5)
//        Log.e(TAG, d5.toString())

        val ele = d.getElementsByTag("img")
        movieDetails.title = ele.attr("alt")

        // 封面图片
        movieDetails.imageUrl = ele.attr("src")

        return movieDetails

    }

    /***
     * 模拟登录
     */

}