package com.kotlin.main.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.kotlin.code.lifecycle.BaseViewModel
import com.kotlin.code.utils.RouterPath
import com.kotlin.main.R
import com.kotlin.main.bean.MainHome

public class MainViewModel : BaseViewModel() {
    private val progress: MutableLiveData<Int?> = MutableLiveData()

    protected var mainList: MutableLiveData<ArrayList<MainHome>> = MutableLiveData()

    fun getProgress(): MutableLiveData<Int?>? {
        return progress
    }
    fun getMianList(): MutableLiveData<ArrayList<MainHome>> {
        return mainList
    }
    
    fun requestData() {

        var listMain = ArrayList<MainHome>();
//        listMain.add(MainHome(R.mipmap.ic_launcher,"图片",RouterPath.path_image_activity))
        listMain.add(MainHome(R.mipmap.ic_launcher, "视频", RouterPath.path_video_activity,0))
        listMain.add(MainHome(R.mipmap.ic_launcher, "抖音", RouterPath.path_recommend_fragment,1))
        listMain.add(MainHome(R.mipmap.ic_launcher, "videolist", "/videolist/fragment",1))
//        listMain.add(MainHome(R.mipmap.ic_launcher,"mvvm",RouterPath.path_mvvm_activity))
//        listMain.add(MainHome(R.mipmap.ic_launcher,"pdf",RouterPath.path_pdf_activity))
//        listMain.add(MainHome(R.mipmap.ic_launcher,"kotlin",RouterPath.path_kotlin_activity))
//        listMain.add(MainHome(R.mipmap.ic_launcher,"host",RouterPath.path_replugin_activity))
//        listMain.add(MainHome(R.mipmap.ic_launcher,"hotfix",RouterPath.path_hotfix_activity))
//        listMain.add(MainHome(R.mipmap.ic_launcher,"mvp",RouterPath.path_mvp_activity))
//        listMain.add(MainHome(R.mipmap.icon_tabbar_component_selected,"html",RouterPath.path_html_activity))
//        listMain.add(MainHome(R.mipmap.icon_tabbar_component_selected,"aop",RouterPath.path_apt_activity))
        mainList.value = listMain
    }

}