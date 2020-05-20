package com.kotlin.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.utils.NavigationUtil
import com.code.utils.RouterPath
import com.kotlin.main.R
import kotlinx.android.synthetic.main.controller_other.*

class OtherFragment : Fragment(){


    companion object{
        fun newInstance() : OtherFragment{
            return OtherFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.controller_other,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {

            NavigationUtil.toActivity(RouterPath.path_test_activity)

        }


    }

}