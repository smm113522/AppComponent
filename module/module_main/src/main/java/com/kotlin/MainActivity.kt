package com.kotlin

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.btmv.module_main.R
import com.code.base.BaseActivity
import com.kotlin.fragment.HelperComponentsController
import com.kotlin.fragment.HomeComponentsController
import com.kotlin.fragment.BaseController
import com.qmuiteam.qmui.widget.QMUITabSegment
import com.qmuiteam.qmui.widget.QMUIViewPager
import java.util.*

class MainActivity : BaseActivity() {

    lateinit var mViewPager : QMUIViewPager
    lateinit var mTabSegment: QMUITabSegment

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getViewDataLayout(): Int {
        return 0
    }

    override fun initView() {
        mViewPager = findViewById(R.id.pager)
        mTabSegment = this.findViewById(R.id.tabs)

        val normalColor = applicationContext.resources.getColor(R.color.qmui_config_color_gray_6)
        val selectColor = applicationContext.resources.getColor(R.color.qmui_config_color_blue)
        mTabSegment.setDefaultNormalColor(normalColor)
        mTabSegment.setDefaultSelectedColor(selectColor)


        val component = QMUITabSegment.Tab(
                ContextCompat.getDrawable(applicationContext, R.mipmap.icon_tabbar_component),
                ContextCompat.getDrawable(applicationContext, R.mipmap.icon_tabbar_component_selected),
                "Components", false
        )

        val util = QMUITabSegment.Tab(
                ContextCompat.getDrawable(applicationContext, R.mipmap.icon_tabbar_component),
                ContextCompat.getDrawable(applicationContext, R.mipmap.icon_tabbar_component_selected),
                "Helper", false
        )
        val lab = QMUITabSegment.Tab(
                ContextCompat.getDrawable(applicationContext, R.mipmap.icon_tabbar_component),
                ContextCompat.getDrawable(applicationContext, R.mipmap.icon_tabbar_component_selected),
                "Lab", false
        )
        mTabSegment.setHasIndicator(false)
        mTabSegment.addTab(component)
                .addTab(util)
                .addTab(lab)

        val listener = object : BaseController.HomeControlListener {
            override fun startFragment(fragment: Fragment) {
//                HomeController.startFragment(fragment)
            }
        }

        mPages = HashMap()

        val homeComponentsController = HomeComponentsController(applicationContext)
        homeComponentsController.setHomeControlListener(listener)
        mPages!![Pager.COMPONENT] = homeComponentsController

        val homeUtilController = HelperComponentsController(applicationContext)
        homeUtilController.setHomeControlListener(listener)
        mPages!![Pager.UTIL] = homeUtilController

        val homeLabController = HomeComponentsController(applicationContext)
        homeLabController.setHomeControlListener(listener)
        mPages!![Pager.LAB] = homeLabController

        mViewPager.adapter = mPagerAdapter
        mTabSegment.setupWithViewPager(mViewPager, false)

    }

    internal enum class Pager {
        COMPONENT, UTIL, LAB;


        companion object {

            fun getPagerFromPositon(position: Int): Pager {
                when (position) {
                    0 -> return COMPONENT
                    1 -> return UTIL
                    2 -> return LAB
                    else -> return COMPONENT
                }
            }
        }
    }

    private var mPages: HashMap<Pager, BaseController>? = null

    private val mPagerAdapter = object : PagerAdapter() {

        private var mChildCount = 0

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun getCount(): Int {
            return mPages!!.size
        }

        override
        fun instantiateItem(container: ViewGroup, position: Int): Any {
            val page = mPages!![Pager.getPagerFromPositon(position)]
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            container.addView(page, params)
            return page!!
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getItemPosition(`object`: Any): Int {
            return if (mChildCount == 0) {
                PagerAdapter.POSITION_NONE
            } else super.getItemPosition(`object`)
        }

        override fun notifyDataSetChanged() {
            mChildCount = count
            super.notifyDataSetChanged()
        }
    }


}