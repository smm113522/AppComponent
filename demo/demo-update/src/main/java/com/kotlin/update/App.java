//版权
package com.kotlin.update;

import android.app.Application;
import android.content.Context;

import com.kotlin.code.BaseApp;
import com.pgyer.pgyersdk.PgyerSDKManager;
import com.pgyer.pgyersdk.pgyerenum.FeatureEnum;

/**
 * 功能：${1:功能描述},
 * 作者：SSSSS,
 * 邮箱：DDDDD@qq.com,
 * 时间： 2021/1/26 11:04 星期二
 * 版本：v1.0",
 * 修改记录：",
 * 修改内容：",
 * 修改人员：",
 * 修改时间：",
 */
public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        init(getApplicationContext());

//        PgyerSDKManager.reportException(Exception e);

    }

    //初始化
    private static void init(Context application) {
        new PgyerSDKManager.InitSdk()
                .setContext(application) //设置上下问对象
                .enable(FeatureEnum.CHECK_UPDATE)  //添加检查新版本
                .build();
    }
}
