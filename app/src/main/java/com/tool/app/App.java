package com.tool.app;

import com.baidu.mobstat.StatService;
import com.tool.style.base.BaseApp;


public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            StatService.setDebugOn(true);
        }

        StatService.autoTrace(this, true, false);

    }
}
