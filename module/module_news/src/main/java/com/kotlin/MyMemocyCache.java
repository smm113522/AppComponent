package com.kotlin;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static android.content.Context.ACTIVITY_SERVICE;

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

    public int getSize(Context context){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();

        activityManager.getMemoryInfo(info);

        String tag = MyMemocyCache.class.getName();

        Log.i(tag,"系统剩余内存:"+(info.availMem >> 10)+"k");

        Log.i(tag,"系统是否处于低内存运行："+info.lowMemory);

        Log.i(tag,"当系统剩余内存低于"+info.threshold+"时就看成低内存运行");
        return (int) (info.availMem >> 10);
    }

    //向内存中保存图片
    public void setPicToMemocy(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }

    private static final String MEM_INFO_PATH = "/proc/meminfo";
    public static final String MEMTOTAL = "MemTotal";
    public static final String MEMFREE = "MemFree";

    /**
     * 得到可用内存大小
     *
     * @param context
     * @param memfree
     * @return
     */
    public static String getMemoryFree(Context context, String memfree) {
        return getMemInfoIype(context, MEMFREE);
    }

    /**
     * 得到type info
     *
     * @param context
     * @param type
     * @return
     */
    public static String getMemInfoIype(Context context, String type) {
        try {
            FileReader fileReader = new FileReader(MEM_INFO_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader, 4 * 1024);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains(type)) {
                    break;
                }
            }
            bufferedReader.close();
            /* \\s表示   空格,回车,换行等空白符,
            +号表示一个或多个的意思     */
            String[] array = str.split("\\s+");
            // 获得系统总内存，单位是KB，乘以1024转换为Byte
            int length = Integer.valueOf(array[1]).intValue() * 1024;
            return android.text.format.Formatter.formatFileSize(context, length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    原文链接：https://blog.csdn.net/wangjicong_215/article/details/74078913

}