package com.kotlin.helper;

import android.content.Intent;

abstract class PictureStrategy {

    /**
     * 发起照相
     */
    abstract void startPhotoCamera();

    /**
     * 发起相册
     */
    abstract void startPhotograph();

    /**
     * 结果回调
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    abstract void onActivityResult(int requestCode, int resultCode, Intent data);
}
