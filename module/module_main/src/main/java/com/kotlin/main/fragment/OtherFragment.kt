package com.kotlin.main.fragment

import android.support.v4.app.Fragment
import com.kotlin.code.base.BaseNoModelFragment
import com.kotlin.main.R
import com.kotlin.main.databinding.FragmentOtherBinding

class OtherFragment : BaseNoModelFragment<FragmentOtherBinding>() {

    companion object {
        fun newInstance(): OtherFragment {
            return OtherFragment()
        }
    }

    var list = ArrayList<Fragment>()

    override fun onCreate(): Int = R.layout.fragment_other

    override fun initView() {

    }

    override fun initData() {

    }

}