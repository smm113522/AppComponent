package com.kotlin.app.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatDelegate

class ThemeHelper {

    companion object {
        //亮色主题
        const val LIGHT_MODE = "light"

        //黑色主题
        const val DARK_MODE = "dark"

        //默认主题
        const val DEFAULT_MODE = "default"

        /**
         * 应用主题
         * @param themePref
         */
        fun applyTheme(@NonNull themePref: String) {
            when (themePref) {
                LIGHT_MODE -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                DARK_MODE -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                DEFAULT_MODE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        //跟随系统Theme 切换主题
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    } else {
                        //根据电量 自动切换
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                    }
                }
            }
        }

        /**
         * 判断深颜色主题是否开启
         */
        fun isNightMode(context: Context): Boolean {
            return (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        }
    }
}
