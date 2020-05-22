package com.kotlin.code.base;

import android.arch.lifecycle.Observer;
import android.databinding.ViewDataBinding;

import com.kotlin.code.bean.DialogBean;
import com.kotlin.code.lifecycle.BaseViewModel;


/**
 * 文件名:    BaseActivity
 * 创建时间:  2019-03-27 on 10:46
 * 描述:     TODO ViewModel、ViewDataBinding都需要的基类
 */

public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding>
        extends BaseNoModelActivity<DB> {

    protected VM viewModel;

    @Override
    protected DB initDataBinding(int layoutId) {
        DB db = super.initDataBinding(layoutId);
        /**
         * 将这两个初始化函数插在{@link BaseActivity#initDataBinding}
         */
        viewModel = initViewModel();
        initObserve();
        return db;
    }

    /**
     * 初始化ViewModel
     */
    protected abstract VM initViewModel();

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (viewModel == null) return;
        viewModel.getShowDialog(this, new Observer<DialogBean>() {
            @Override
            public void onChanged(DialogBean bean) {
                if (bean.isShow()) {
                    showDialog(bean.getMsg());
                } else {
                    dismissDialog();
                }
            }
        });
        viewModel.getError(this, new Observer<Object>() {
            @Override
            public void onChanged(Object obj) {
                showError(obj);
            }
        });
    }

    /**
     * ViewModel层发生了错误
     */
    protected abstract void showError(Object obj);
}
