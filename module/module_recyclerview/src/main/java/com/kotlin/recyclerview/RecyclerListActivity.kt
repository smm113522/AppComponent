package com.kotlin.recyclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kotlin.recyclerview.adapter.RecyclerListAdapter
import kotlinx.android.synthetic.main.activity_recyclerview.*


class RecyclerListActivity : AppCompatActivity(R.layout.activity_recyclerview) {

    var adapter: RecyclerListAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var list = ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
        list.add("7")
//        list.clear()
        adapter = RecyclerListAdapter(R.layout.item_recyclerview, list)
        recyclerview.adapter = adapter
        adapter!!.setEmptyView(R.layout.empty_view)
        adapter!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);

    }



}