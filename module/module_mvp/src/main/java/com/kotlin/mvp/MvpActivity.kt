package com.kotlin.mvp;

import android.graphics.Color
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.alibaba.android.arouter.facade.annotation.Route;
import com.code.utils.RouterPath;
import com.code.utils.ToastsUtils
import com.kotlin.mvp.adapter.MvpAdapter
import com.kotlin.mvp.bean.Categories
import com.kotlin.mvp.model.MvpRequest
import com.kotlin.mvp.net.ResponseHandler
import com.kotlin.mvp.presenter.impl.MvpPresenterImpl
import com.kotlin.mvp.view.MvpView
import kotlinx.android.synthetic.main.activity_mvp.*

@Route(path = RouterPath.path_mvp_activity)
class MvpActivity : AppCompatActivity(), MvpView {

    val adapter by lazy {
        MvpAdapter()
    }

    val mvpPresenter by lazy {
        MvpPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        swipelayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.CYAN)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        /**
         * 下拉刷新
         */
        swipelayout.setOnRefreshListener {
            mvpPresenter.loadData()
        }

        mvpPresenter.loadData()

        /**
         * 上拉更多
         */
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    var layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        var manager: LinearLayoutManager = layoutManager
                        var position = manager.findLastVisibleItemPosition()
                        if (adapter.itemCount - 1 == position) {
                            mvpPresenter.loadMoreData(0)
                        }
                    }
                }
            }

        });

        MvpRequest(object : ResponseHandler<Categories> {

            override fun onError(msg: String?) {
                onError(msg)
            }

            override fun onSuccess(result: Categories) {
                onError(result.toString())
            }

        }).exeute()


    }

    override fun onError(txt: String) {
        ToastsUtils.showToastOnUiThread(this, txt)
    }

    override fun loadRefreshSucess(list:ArrayList<String>) {
        adapter.loadData(list)
        swipelayout.isRefreshing = false
    }

    override fun loadMoreSucess(list:ArrayList<String>) {
        adapter.loadMoreData(list)
    }

}
