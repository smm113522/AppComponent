package com.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.btmv.module_main.R
import com.kotlin.adapter.MainAdapter
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
        recyclerView.layoutManager = gridLayoutManager

        var list = ArrayList<String>()

        list.add("图片")
        list.add("视频")
        list.add("mvvm")
        list.add("pdf")
        list.add("kotlinx")
        list.add("host")
        list.add("hotfix")
        list.add("mvp")

        var adapter = MainAdapter(list,activity!!.applicationContext)
        recyclerView.adapter = adapter

    }


}