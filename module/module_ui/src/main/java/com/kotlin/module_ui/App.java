package com.kotlin.module_ui;

import android.app.Application;

import com.kotlin.code.BaseApp;

public class App extends BaseApp {


    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler instance = CrashHandler.getInstance();
        instance.init(this);
    }
}
