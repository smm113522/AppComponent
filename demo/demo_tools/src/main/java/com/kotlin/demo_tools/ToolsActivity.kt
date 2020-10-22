package com.kotlin.demo_tools

import android.content.Context
import androidx.lifecycle.Observer
import com.kotlin.demo_tools.adapter.BaseAdapter
import com.kotlin.demo_tools.adapter.ToolsAdapter
import com.kotlin.demo_tools.databinding.ActivityToolsBinding
import com.kotlin.demo_tools.view.ToolsView
import com.kotlin.demo_tools.viewholder.ToolViewModel

class ToolsActivity : BaseMvvmActivity<ToolViewModel, ActivityToolsBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_tools

    class Tools2Adapter : BaseAdapter<String, ToolsView>() {
        override fun getItemView(context: Context?): ToolsView {
            return ToolsView(context!!)
        }

        override fun refreshItemView(itemView: ToolsView, data: String) {
            itemView.setData(data)
        }


    }

//    private val adapter: ToolsAdapter by lazy {
//        ToolsAdapter()
//    }

    private val adapter: Tools2Adapter by lazy {
        Tools2Adapter()
    }

    override fun afterCreate() {
        mViewDataBind.adapter = adapter
        mViewModel.list.observe(this, Observer {
            adapter.refreshData(it)
        })

        mViewModel.refreshData();
    }

}