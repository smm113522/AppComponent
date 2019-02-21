package com.tool.net;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxjavaHelper {

    /**
     * 切换线程操作
     * @return Observable转换器
     */
    public static <T> ObservableTransformer<T, T> observeOnMainThread() {

        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}