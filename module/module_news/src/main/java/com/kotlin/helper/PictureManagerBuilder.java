package com.kotlin.helper;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

public class PictureManagerBuilder {

    public static PictureManagerBuilder builder() {
        return new PictureManagerBuilder();
    }

    public static class ManagerBuilder {

        /**
         * 打开相机
         *
         * @param activity
         * @param requestCode
         */
        public void start(Activity activity, int requestCode) {

        }

        /**
         * 打开相册
         *
         * @param fragment
         * @param requestCode
         */
        public void start(Fragment fragment, int requestCode) {

        }

    }
}
