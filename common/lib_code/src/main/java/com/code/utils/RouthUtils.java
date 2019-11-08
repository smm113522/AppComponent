package com.code.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;

import com.ryg.dynamicload.internal.DLIntent;
import com.ryg.dynamicload.internal.DLPluginManager;
import com.ryg.utils.DLUtils;

import java.io.File;

public class RouthUtils {

    public static void simulateInstallExternalPlugin(Context context) {
        String pluginFolder = "demo.apk";
        simulateInstallExternalPlugin(context, pluginFolder);
    }

    /**
     * 模拟安装或升级（覆盖安装）外置插件
     * 注意：为方便演示，外置插件临时放置到Host的assets/external目录下，具体说明见README</p>
     */
    public static void simulateInstallExternalPlugin(Context context, String pluginFolder) {

        String demo3apkPath = "external" + File.separator + pluginFolder;

        // 文件是否已经存在？直接删除重来
        // 本data 数据里面
        String pluginFilePath = context.getFilesDir().getAbsolutePath() + File.separator + pluginFolder;
//        pluginFilePath = Environment.getExternalStorageDirectory().toString() + "/DynamicLoadHost";

        File pluginFile = new File(pluginFilePath);
        if (!pluginFile.exists()) {
            pluginFile.mkdirs();
        }

        pluginFilePath = Environment.getExternalStorageDirectory().toString() + "/DynamicLoadHost" + File.separator + pluginFolder;

        pluginFile = new File(pluginFilePath);
        if (pluginFile.exists()) {
            deleteQuietly(pluginFile);
        }

        if (!pluginFile.exists()) {
            //      //   开始复制
            new AssetFile(context).fromAsset(demo3apkPath).copyAssetsFileToAppFiles(pluginFile.getAbsolutePath());
        }

        DLPluginManager.getInstance(context).loadApk(pluginFilePath);
        PackageInfo packageInfo = DLUtils.getPackageInfo(context, pluginFilePath);
        if (packageInfo != null) {
            DLPluginManager pluginManager = DLPluginManager.getInstance(context);
            pluginManager.startPluginActivity(
                    context,
                    new DLIntent(packageInfo.packageName, packageInfo.activities[0].name)
            );
        }

    }

    public static boolean deleteQuietly(final File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
//                cleanDirectory(file);

            }
        } catch (final Exception ignored) {
        }

        try {
            return file.delete();
        } catch (final Exception ignored) {
            return false;
        }
    }


}
