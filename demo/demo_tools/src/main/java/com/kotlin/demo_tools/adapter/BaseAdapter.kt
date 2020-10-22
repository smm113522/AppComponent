package com.kotlin.demo_tools.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder

abstract class BaseAdapter<T, TView : View> : RecyclerView.Adapter<BaseViewHolder>() {

    var list = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            getItemView(
                parent?.context
            )
        )
    }

    /**
    获取view
     */
    abstract fun getItemView(context: Context?): TView

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var item = holder.itemView as TView
        var data = list[position]

        var layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        );
        item.layoutParams = layoutParams
        item.setOnClickListener {
            listener?.let {
                it(data)
            }
        }
        refreshItemView(item, data)
    }

    //定义函数类型变量
    var listener: ((itemBean: T) -> Unit)? = null

    fun setOnclckListener(listener: (itemBean: T) -> Unit) {
        this.listener = listener
    }

    /**
    用来给具体Adapter实现逻辑的抽象方法
     */
    abstract fun refreshItemView(itemView: TView, data: T)

    /**
     * 刷新数据
     *
     * @param data 数据源
     */
    fun refreshData(data: List<T>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    /**
     * 加载更多
     *
     * @param data 加载的新数据
     */
    fun loadMoreData(data: List<T>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

}