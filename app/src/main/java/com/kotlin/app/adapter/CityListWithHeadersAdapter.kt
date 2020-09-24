package com.kotlin.app.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.app.R
import com.kotlin.app.bean.City
import com.kotlin.app.utils.Utils
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
import java.lang.String
import java.security.SecureRandom
import java.util.*

class CityListWithHeadersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private val items = ArrayList<City>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val textView = holder.itemView as TextView
        textView.setText(getItem(position)!!.getCityName())
    }

    override fun getHeaderId(position: Int): Long {
        return Utils.StringToLong(getItem(position)!!.firstLetter)
//        return getItem(position)!!.firstLetter.charAt(0)
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_header, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val textView = holder.itemView as TextView
        textView.setText(String.valueOf(getItem(position)?.firstLetter))
        holder.itemView.setBackgroundColor(getRandomColor())
    }

    private fun getRandomColor(): Int {
        val rgen = SecureRandom()
        return Color.HSVToColor(
            150, floatArrayOf(
                rgen.nextInt(359).toFloat(), 1f, 1f
            )
        )
    }


    fun addAll(collection: Collection<City>) {
        if (collection != null) {
            items.addAll(collection)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): City? {
        return items[position]
    }


}