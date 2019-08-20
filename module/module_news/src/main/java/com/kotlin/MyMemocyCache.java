package com.kotlin;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.LruCache;

public class MyMemocyCache {
    //内存大小的1/8
//    private long size = Environment.getDataDirectory().getUsableSpace();
    private long size = 1000000;
    private LruCache<String, Bitmap> lruCache = new LruCache<>((int) (size / 8));

    //取出图片
    public Bitmap getPicFromMemory(String url) {
        Bitmap bitmap = lruCache.get(url);
        return bitmap;
    }

    //向内存中保存图片
    public void setPicToMemocy(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}