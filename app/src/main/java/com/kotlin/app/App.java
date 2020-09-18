package com.kotlin.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }


}
