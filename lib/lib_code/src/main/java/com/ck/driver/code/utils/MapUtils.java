package com.ck.driver.code.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;

public class MapUtils {


    public static void startMap(Activity context) {
        if (RePlugin.isPluginInstalled("flutter_app")) {
            boolean start = RePlugin.startActivity(context, RePlugin.createIntent("flutter_app", "com.example.flutter_app.MainActivity"));
        } else {
            Toast.makeText(context, "You must install demo3 first!", Toast.LENGTH_SHORT).show();
        }
    }
}
