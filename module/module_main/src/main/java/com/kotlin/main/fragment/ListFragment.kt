package com.kotlin.main.fragment

import com.kotlin.code.base.BaseNoModelActivity
import com.kotlin.main.R
import com.kotlin.main.databinding.FragmentOtherListBinding

class ListFragment:BaseNoModelActivity<FragmentOtherListBinding>() {

    companion object{
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    override fun onCreate(): Int = R.layout.fragment_other_list


    override fun initView() {

    }

    override fun initData() {

    }
}