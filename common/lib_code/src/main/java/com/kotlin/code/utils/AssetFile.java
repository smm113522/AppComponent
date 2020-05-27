package com.kotlin.code.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetFile {
//    @param assetFileName assets目录下的Apk源文件路径
    private String assetFileName;
    private Context context;

    public AssetFile(Context context) {
        this.context = context;
    }

    public AssetFile fromAsset(String assetFileName) {
        this.assetFileName = assetFileName;
        return this;
    }

    /**
     * @param filePath "hello.txt"  设置文件大小为10K
     */
    public void toSdcard(String filePath) {
        try {
            InputStream inStream = context.getAssets().open(assetFileName);
            OutputStream outStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024 * 10];
            int length = inStream.read(buffer);
            outStream.write(buffer, 0, length);
            outStream.flush();
            inStream.close();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    public void copyAssetsFileToAppFiles(String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = context.getAssets().open(assetFileName);
//            fos = context.openFileOutput(newFileName, Context.MODE_PRIVATE);
            fos = new FileOutputStream(new File(newFileName), true); // true will be same as Context.MODE_APPEND
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param file   复制到/data/data/package_name/files/目录下文件名
     */
    public void copyAssetsFileToAppFiles(File file) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = context.getAssets().open(assetFileName);
//            fos = context.openFileOutput(newFileName, Context.MODE_PRIVATE);
            fos = new FileOutputStream(file, true); // true will be same as Context.MODE_APPEND
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
