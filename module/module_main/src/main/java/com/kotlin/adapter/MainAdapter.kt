package com.kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import com.btmv.module_main.R


class MainAdapter(val datas: List<String>, val context: Context) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainHolder {
        var itemView: View = LayoutInflater.from(context).inflate(R.layout.item_main, p0, false)
        return MainHolder(itemView)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(p0: MainHolder, p1: Int) {
        p0.itemText.text = datas.get(p1)
    }


    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemText: TextView = itemView.findViewById(R.id.title_template)

    }
}