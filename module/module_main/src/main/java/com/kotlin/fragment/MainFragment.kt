package com.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.btmv.module_main.R
import com.code.utils.RouterPath
import com.kotlin.adapter.MainAdapter
import com.kotlin.bean.MainHome
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    companion object{
        fun newInstance() : MainFragment{
            return MainFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topbar.text = "控件"
        var gridLayoutManager = GridLayoutManager(activity,4)
        recyclerView.layoutManager = gridLayoutManager as RecyclerView.LayoutManager?

        var listMain = ArrayList<MainHome>();
        listMain.add(MainHome(R.mipmap.ic_launcher,"图片",RouterPath.path_image_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher,"视频",RouterPath.path_video_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher,"mvvm",RouterPath.path_mvvm_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher,"pdf",RouterPath.path_pdf_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher,"kotlin",RouterPath.path_kotlin_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher,"host",RouterPath.path_replugin_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher,"hotfix",RouterPath.path_hotfix_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher,"mvp",RouterPath.path_mvp_activity))
        listMain.add(MainHome(R.mipmap.icon_tabbar_component_selected,"html",RouterPath.path_html_activity))
        listMain.add(MainHome(R.mipmap.icon_tabbar_component_selected,"aop",RouterPath.path_apt_activity))

        var adapter = MainAdapter(listMain,activity!!.applicationContext)

        recyclerView.adapter = adapter

    }


}