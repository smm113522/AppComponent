package com.kotlin.demo_tools.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.kotlin.demo_tools.R
import com.kotlin.demo_tools.databinding.ItemToolsBinding


class ToolsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var itemBinding: ItemToolsBinding? = null;

    init {
        var layoutInflater = LayoutInflater.from(context)
        itemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_tools, this, false)
        addView(itemBinding!!.root)
    }

    fun setData(bean: String) {
        itemBinding!!.bean = bean
        //迫使数据立即绑定
//        itemBinding!!.executePendingBindings()
    }

}