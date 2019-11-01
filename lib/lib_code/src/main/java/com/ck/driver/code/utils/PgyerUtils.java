package com.ck.driver.code.utils;

import com.pgyersdk.feedback.PgyerFeedbackManager;
import com.pgyersdk.update.PgyUpdateManager;

public class PgyerUtils {

    /**
     * 初始化 摇一摇
     */
    public static void initPgyer(){
        new PgyerFeedbackManager.PgyerFeedbackBuilder()
                .setShakeInvoke(true)       //设置是否摇一摇的方式激活反馈，默认为 true
//                        .setColorDialogTitle("")    //设置Dialog 标题栏的背景色，默认为颜色为#ffffff
//                        .setColorTitleBg("")        //设置Dialog 标题的字体颜色，默认为颜色为#2E2D2D
                .setDisplayType(PgyerFeedbackManager.TYPE.DIALOG_TYPE)   //设置以Dialog 的方式打开
                .setMoreParam("KEY1","VALUE1")
                .setMoreParam("KEY2","VALUE2")
                .builder()
                .register();                //注册摇一摇的方式
    }

    /**
     * 初始化 版本更新
     */
    public static void initPgyUpdate(){
        new PgyUpdateManager.Builder()
                .setForced(false)                //设置是否强制更新
                .setUserCanRetry(false)         //失败后是否提示重新下载
                .setDeleteHistroyApk(true)     // 检查更新前是否删除本地历史 Apk
                .register();
    }


}
