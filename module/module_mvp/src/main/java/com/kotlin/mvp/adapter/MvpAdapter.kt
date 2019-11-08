package com.kotlin.mvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.kotlin.mvp.view.MvpItemView
import java.util.ArrayList

class MvpAdapter : RecyclerView.Adapter<MvpAdapter.MvpViewHolder>() {

    var list = ArrayList<String>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, postion: Int): MvpViewHolder {
        return MvpViewHolder(MvpItemView(viewGroup.context))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: MvpViewHolder, position: Int) {
        var mvpView:MvpItemView = viewHolder.itemView as MvpItemView
        var data = list[position]
        mvpView.setData(data)
    }

    fun loadData(list: ArrayList<String>) {
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun loadMoreData(list: ArrayList<String>) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }


    class MvpViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}