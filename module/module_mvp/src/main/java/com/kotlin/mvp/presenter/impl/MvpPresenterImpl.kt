package com.kotlin.mvp.presenter.impl

import com.kotlin.mvp.model.MvpModel
import com.kotlin.mvp.presenter.MvpPresenter
import com.kotlin.mvp.view.MvpView

class MvpPresenterImpl(var mvpView: MvpView) : MvpPresenter{

    val mvpModel by lazy {
        MvpModel()
    }

    override fun loadMoreData(page: Int) {
        mvpView.loadMoreSucess(mvpModel.loadMoreData())
    }

    override fun loadData() {
        mvpView.loadRefreshSucess(mvpModel.loadData())
    }


}