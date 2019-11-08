package com.kotlin.mvp.vidget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.kotlin.mvp.R
import kotlinx.android.synthetic.main.item_mvp.view.*

class MvpItemView : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_mvp,this)
    }

    fun setData(string: String){
        tv_txt.text = string
    }
}