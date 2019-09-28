package com.kotlin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.kotlin.helper.OnPictureCallback;
import com.kotlin.helper.PictureManager;

import java.io.File;
import java.io.FileOutputStream;

public class MyDiskCache {

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath();

    //保存图片
    public void setPicToDisk(String url) {
        File file = new File(path, url);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));//将图片压缩到sdcard
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //取出图片
    public Bitmap getPicFromDisk(String url) {
        File file = new File(path, url);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            return bitmap;
        }
        return null;
    }
}