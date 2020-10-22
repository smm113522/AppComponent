package com.kotlin.demo_tools.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kotlin.demo_tools.view.ToolsView


class ToolsAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var mList = ArrayList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        var view = ToolsView(parent.context)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    /**
     * 刷新数据
     *
     * @param data 数据源
     */
    fun refreshData(data: List<String>) {
        mList.clear()
        mList.addAll(data!!)
        notifyDataSetChanged()
    }

    /**
     * 加载更多
     *
     * @param data 加载的新数据
     */
    fun loadMoreData(data: List<String>) {
        mList.addAll(data!!)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var view = holder.itemView as ToolsView
        view.setData(mList[position])
    }

}
