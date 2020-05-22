package com.kotlin.main.fragment

import com.kotlin.code.base.BaseNoModelFragment
import com.kotlin.main.R
import com.kotlin.main.databinding.FragmentOtherBinding

class OtherFragment : BaseNoModelFragment<FragmentOtherBinding>() {

    companion object {
        fun newInstance(): OtherFragment {
            return OtherFragment()
        }
    }

    override fun onCreate(): Int = R.layout.fragment_other

    override fun initView() {
        dataBinding.button.setOnClickListener {

        }
    }

    override fun initData() {

    }

}