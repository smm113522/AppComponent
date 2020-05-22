package com.kotlin.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.code.utils.NavigationUtil
import com.kotlin.main.R
import com.kotlin.main.bean.MainHome


class MainAdapter(val datas: List<MainHome>, val context: Context) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainHolder {
        return MainHolder(LayoutInflater.from(context).inflate(R.layout.item_main, p0, false))
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(p0: MainHolder, p1: Int) {
        p0.itemText.text = datas.get(p1).title
        p0.iv_icon.setImageResource(datas.get(p1).icon)
        p0.iv_icon.setOnClickListener({
            datas.get(p1).auth?.let { it1 -> NavigationUtil.toActivity(it1) }
        });
    }


    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemText: TextView = itemView.findViewById(R.id.title_template)
        var iv_icon: ImageView = itemView.findViewById(R.id.iv_icon)

    }
}