package com.kotlin.code.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.code.bean.DialogBean
import com.kotlin.code.lifecycle.BaseViewModel
import com.kotlin.code.view.LoadingDialog

public abstract class BasesActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected var viewModel: VM? = null

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        /**
         * 将这两个初始化函数插在[BaseActivity.initDataBinding]
         */
        viewModel = initViewModel()
        initObserve()
    }

    abstract fun getLayoutId(): Int

    /**
     * 初始化ViewModel
     */
    protected abstract fun initViewModel(): VM

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    open fun initObserve() {
        if (viewModel == null) return
        viewModel?.getShowDialog(this, object : Observer<DialogBean> {
            override fun onChanged(bean: DialogBean?) {
                if (bean!!.isShow) {
                    showDialog(bean.msg)
                } else {
                    dismissDialog()
                }
            }

        })
        viewModel?.getError(this, Observer<Any?> { obj -> showError(obj) })
    }

    /**
     * ViewModel层发生了错误
     */
    protected abstract fun showError(obj: Any?)

    /**
     * 显示用户等待框
     *
     * @param msg 提示信息
     */
    protected open fun showDialog(msg: String?) {
        if (loadingDialog != null && loadingDialog!!.isShowing()) {
            loadingDialog?.setLoadingMsg(msg)
        } else {
            loadingDialog = LoadingDialog(this)
            loadingDialog?.setLoadingMsg(msg)
            loadingDialog?.show()
        }
    }

    /**
     * 隐藏等待框
     */
    protected open fun dismissDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing()) {
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }


}