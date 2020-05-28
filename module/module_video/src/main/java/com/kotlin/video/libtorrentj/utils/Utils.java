package com.kotlin.video.libtorrentj.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Utils {

    public static String getAppVersionName(Context context)
    {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            /* Ignore */
        }

        return null;
    }

    /*
     * Return version components in these format: [major, minor, revision]
     */

    public static int[] getVersionComponents(String versionName)
    {
        int[] version = new int[3];
        if (versionName == null)
            return version;

        /* Discard additional information */
        versionName = getAppVersionNumber(versionName);

        String[] components = versionName.split("\\.");
        if (components.length < 2)
            return version;

        try {
            version[0] = Integer.parseInt(components[0]);
            version[1] = Integer.parseInt(components[1]);
            if (components.length >= 3)
                version[2] = Integer.parseInt(components[2]);

        } catch (NumberFormatException e) {
            /* Ignore */
        }

        return version;
    }

    /*
     * Without additional information (e.g -DEBUG)
     */

    public static String getAppVersionNumber(String versionName)
    {
        if (versionName == null)
            return null;

        int index = versionName.indexOf("-");
        if (index >= 0)
            versionName = versionName.substring(0, index);

        return versionName;
    }
}
