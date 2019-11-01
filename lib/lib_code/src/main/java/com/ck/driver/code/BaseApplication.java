package com.ck.driver.code;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.baidu.mobstat.StatService;
import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;
import com.qihoo360.mobilesafe.core.BuildConfig;
import com.qihoo360.replugin.RePlugin;

public class BaseApplication extends Application {

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RePlugin.App.attachBaseContext(this);
        RePlugin.enableDebugger(base, BuildConfig.DEBUG);
//        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RePlugin.App.onCreate();

        StatService.start(this);

        PgyCrashManager.register();

        PgyerCrashObservable.get().attach(new PgyerObserver() {
            @Override
            public void receivedCrash(Thread thread, Throwable throwable) {

            }
        });
        PgyerActivityManager.set(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        RePlugin.App.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        RePlugin.App.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        RePlugin.App.onConfigurationChanged(config);
    }

}
