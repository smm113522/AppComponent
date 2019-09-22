package com.kotlin.helper;

public interface OnPictureCallback<T> {

    /**
     * 完成的时候会回调此方法，结果存在于result中
     *
     * @param result 扫描结果
     */
    void onCompleted(T result);

    /**
     * 当过程出错的时候会回调
     *
     * @param errorMsg 错误信息
     */
    void onError(Throwable errorMsg);

}
