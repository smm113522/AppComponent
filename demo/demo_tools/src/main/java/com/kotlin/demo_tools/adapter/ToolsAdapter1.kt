package com.kotlin.demo_tools.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.kotlin.demo_tools.R
import com.kotlin.demo_tools.databinding.ItemToolsBinding


class ToolsAdapter1 : RecyclerView.Adapter<BaseDataBindingHolder<ItemToolsBinding>>() {

    var mList = ArrayList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseDataBindingHolder<ItemToolsBinding> {
//        var view = View.inflate(parent.context, R.layout.item_tools,null)
        val itemMvvmBinding: ItemToolsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tools,
            parent,
            false
        )
        return BaseDataBindingHolder(itemMvvmBinding.root)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: BaseDataBindingHolder<ItemToolsBinding>, position: Int) {
        val s = mList[position]
        holder.dataBinding!!.bean = s
        holder.dataBinding!!.executePendingBindings()
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

}
