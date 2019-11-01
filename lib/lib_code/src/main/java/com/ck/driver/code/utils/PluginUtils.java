package com.ck.driver.code.utils;

import android.app.Activity;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;

public class PluginUtils {

    public static void startPlugin(Activity context) {
        if (RePlugin.isPluginInstalled("flutter_app")) {
            boolean start = RePlugin.startActivity(context, RePlugin.createIntent("flutter_app", "com.example.flutter_app.MainActivity"));
        } else {
            Toast.makeText(context, "You must install demo3 first!", Toast.LENGTH_SHORT).show();
        }
    }

}
