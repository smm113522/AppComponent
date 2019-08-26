package com.kotlin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.code.base.BaseApp;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFresco {
    private static MyMemocyCache myMemocyCache;
    private static MyDiskCache myDiskCache;
    private static MyFresco myFresco;
    private static Context mContext;

    public static MyFresco init(Context context) {
        myMemocyCache = new MyMemocyCache();
        myDiskCache = new MyDiskCache();
        myFresco = new MyFresco();
        mContext = context;
        return myFresco;
    }

    //加载图片的方法
    public Bitmap loadPic(String url) {
        //检查内存里有没有
        Bitmap picFromMemory = myMemocyCache.getPicFromMemory(url);
        if (picFromMemory == null) {
            //去磁盘里获取
            Bitmap picFromDisk = myDiskCache.getPicFromDisk(url);
            if (picFromDisk == null) {
                //去网络获取图片
                Bitmap picFromNet = getPicFromNet(url);
                if (picFromNet == null) {
                    //网络图片没有就返回一个默认图片
                    Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
                    return bitmap;
                } else {
                    return picFromNet;
                }
            } else {
                return picFromDisk;
            }
        } else {
            return picFromMemory;
        }

    }

    //从网络中获取图片
    public Bitmap getPicFromNet(final String url) {
        //使用HttpUrlConnction
        final Bitmap[] soure = new Bitmap[0];

        Glide.with(BaseApp.Companion.getInstance()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                myMemocyCache.setPicToMemocy(url,bitmap);
                myDiskCache.setPicToDisk(url);
                soure[0] = bitmap;
            }

        });

//        URL u = null;
//        try {
//            u = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
//            connection.setConnectTimeout(500000);
//            if (connection.getResponseCode() == 200) {
//                InputStream inputStream = connection.getInputStream();//获取输入流相当于Bitmap
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//
////                myMemocyCache.setPicToMemocy(url,bitmap);
////                myDiskCache.setPicToDisk(url);
//
//                return bitmap;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return soure[0];
    }
}
