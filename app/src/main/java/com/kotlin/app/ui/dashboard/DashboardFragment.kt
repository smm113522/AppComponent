package com.kotlin.app.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlin.app.R
import com.kotlin.app.adapter.CityListWithHeadersAdapter
import com.kotlin.app.bean.City
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.lang.reflect.Type
import java.util.*

class DashboardFragment : Fragment(), OnQuickSideBarTouchListener {

    private lateinit var dashboardViewModel: DashboardViewModel
    val cityDataList =
        "[{\"cityName\":\"广州\",\"firstLetter\":\"☆\"},{\"cityName\":\"北京\",\"firstLetter\":\"☆\"},{\"cityName\":\"上海\",\"firstLetter\":\"☆\"},{\"cityName\":\"深圳\",\"firstLetter\":\"☆\"},{\"cityName\":\"鞍山\",\"firstLetter\":\"A\"},{\"cityName\":\"安庆\",\"firstLetter\":\"A\"},{\"cityName\":\"阿克苏\",\"firstLetter\":\"A\"},{\"cityName\":\"阿拉尔\",\"firstLetter\":\"A\"},{\"cityName\":\"阿拉善盟\",\"firstLetter\":\"A\"},{\"cityName\":\"澳门\",\"firstLetter\":\"A\"},{\"cityName\":\"北京\",\"firstLetter\":\"B\"},{\"cityName\":\"保定\",\"firstLetter\":\"B\"},{\"cityName\":\"宝鸡\",\"firstLetter\":\"B\"},{\"cityName\":\"百色\",\"firstLetter\":\"B\"},{\"cityName\":\"北海\",\"firstLetter\":\"B\"},{\"cityName\":\"博尔塔拉\",\"firstLetter\":\"B\"},{\"cityName\":\"东莞\",\"firstLetter\":\"D\"},{\"cityName\":\"定西\",\"firstLetter\":\"D\"},{\"cityName\":\"邵阳\",\"firstLetter\":\"S\"},{\"cityName\":\"汕尾\",\"firstLetter\":\"S\"},{\"cityName\":\"三亚\",\"firstLetter\":\"S\"},{\"cityName\":\"商洛\",\"firstLetter\":\"S\"},{\"cityName\":\"山南\",\"firstLetter\":\"S\"},{\"cityName\":\"石嘴山\",\"firstLetter\":\"S\"},{\"cityName\":\"石河子\",\"firstLetter\":\"S\"},{\"cityName\":\"天津\",\"firstLetter\":\"T\"},{\"cityName\":\"铜陵\",\"firstLetter\":\"T\"},{\"cityName\":\"泰安\",\"firstLetter\":\"T\"},{\"cityName\":\"天门\",\"firstLetter\":\"T\"},{\"cityName\":\"中卫\",\"firstLetter\":\"Z\"}]"

    var letters = HashMap<String, Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //设置监听
        quickSideBarView.setOnQuickSideBarTouchListener(this)

        //设置列表数据和浮动header
        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(layoutManager)

        // Add the sticky headers decoration
        val adapter: CityListWithHeadersAdapter =
            CityListWithHeadersAdapter()

        //GSON解释出来
        val listType: Type =
            object : TypeToken<LinkedList<City?>?>() {}.getType()
        val gson = Gson()
        val cities: LinkedList<City> = gson.fromJson(cityDataList, listType)

        val customLetters = ArrayList<String>()

        var position = 0
        for (city in cities) {
            val letter: String = city.getFirstLetter()
            //如果没有这个key则加入并把位置也加入
            if (!letters.containsKey(letter)) {
                letters.put(letter, position)
                customLetters.add(letter)
            }
            position++
        }

        //不自定义则默认26个字母
        quickSideBarView.setLetters(customLetters)
        adapter.addAll(cities)
        recyclerView.setAdapter(adapter)

        val headersDecor = StickyRecyclerHeadersDecoration(adapter)
        recyclerView.addItemDecoration(headersDecor)

    }

    override fun onLetterTouching(touching: Boolean) {
        //可以自己加入动画效果渐显渐隐

        //可以自己加入动画效果渐显渐隐
        quickSideBarTipsView.visibility = if (touching) View.VISIBLE else View.INVISIBLE
    }

    override fun onLetterChanged(letter: String, position: Int, y: Float) {
        quickSideBarTipsView.setText(letter, position, y)
        //有此key则获取位置并滚动到该位置
        //有此key则获取位置并滚动到该位置
        if (letters.containsKey(letter)) {
            recyclerView.scrollToPosition(letters.get(letter)!!)
        }
    }


}