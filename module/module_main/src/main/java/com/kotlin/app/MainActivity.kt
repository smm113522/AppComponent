package com.kotlin.app

import android.graphics.Color
import com.btmv.module_main.R
import com.code.base.BaseActivity
import com.kotlin.view.BottomTabBar
import com.kotlin.view.bean.ItemBar
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    var listBar = ArrayList<ItemBar>();

    override fun initView() {
        //更新jar 包
        Beta.checkUpgrade()





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