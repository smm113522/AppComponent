package com.kotlin.app.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

class OtherAdapter(var list: List<String>) : RecyclerView.Adapter<OtherAdapter.OtherHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OtherHolder {
        return OtherHolder(TextView(p0.context))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OtherHolder, p1: Int) {
        holder.textView.text = list[p1]
    }


    class OtherHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}