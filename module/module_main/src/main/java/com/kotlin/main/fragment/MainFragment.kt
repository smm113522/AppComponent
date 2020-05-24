package com.kotlin.main.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.kotlin.code.adapter.OnItemClickListener
import com.kotlin.code.base.BaseFragment
import com.kotlin.code.utils.NavigationUtil
import com.kotlin.main.R
import com.kotlin.main.adapter.MainAdatpters
import com.kotlin.main.bean.MainHome
import com.kotlin.main.databinding.FragmentMainBinding
import com.kotlin.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun initViewModel(): MainViewModel {
        return ViewModelProviders.of(getActivity()!!).get(MainViewModel::class.java)
    }

    override fun onCreate(): Int = R.layout.fragment_main
    var adapter: MainAdatpters? = null

    override fun initView() {
        topbar.text = "控件"
        var gridLayoutManager = GridLayoutManager(activity, 4)
        recyclerView.layoutManager = gridLayoutManager
//        var listMain = ArrayList<MainHome>();
//        adapter = MainAdapter(listMain, activity!!.applicationContext)
        adapter = MainAdatpters()
        recyclerView.adapter = adapter

        adapter?.setOnItemListener(object : OnItemClickListener<MainHome> {
            override fun onItemClick(data: MainHome, position: Int) {
                NavigationUtil.toActivity(data.auth!!)
            }

            override fun onItemLongClick(data: MainHome?, position: Int): Boolean {
                return false
            }

        })

    }

    override fun initData() {
        dataBinding.model = viewModel
        //数据请求成功通知
        viewModel.getMianList().observe(this, object : Observer<ArrayList<MainHome>?> {

            override fun onChanged(t: ArrayList<MainHome>?) {
                adapter?.setNewData(t)
            }
        })

        viewModel.requestData()
    }

    override fun showError(obj: Any?) {
    }


}