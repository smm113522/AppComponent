package com.kotlin.main

import android.graphics.Color
import com.code.base.BaseActivity
import com.kotlin.main.fragment.MainFragment
import com.kotlin.main.fragment.OtherFragment
import com.kotlin.main.view.bean.ItemBar
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    var listBar = ArrayList<ItemBar>()

    override fun initView() {
        //更新jar 包
        Beta.checkUpgrade()

        listBar.add(ItemBar("控件",R.mipmap.icon_tabbar_component_selected,
                R.mipmap.icon_tabbar_component,MainFragment.newInstance().javaClass))

        listBar.add(ItemBar("组件",R.mipmap.icon_tabbar_component_selected,
                R.mipmap.icon_tabbar_component,OtherFragment.newInstance().javaClass))

        initBar(listBar)

    }

    fun initBar(list: ArrayList<ItemBar>){

        bottom_bar.init(supportFragmentManager, 750.0, 1334.0)
                .setImgSize(44.0, 44.0)
                .setFontSize(22.0)
                .setTabPadding(16.0, 8.0, 8.0)
                .setChangeColor(Color.parseColor("#2879B8"), Color.parseColor("#666666"))
                .addListTabItem(list)
                .isShowDivider(true)
                .setDividerColor(Color.parseColor("#20000000"))
                .setTabBarBackgroundColor(Color.parseColor("#FFFFFFFF"))
                .setCurrentTab(0)

    }




}