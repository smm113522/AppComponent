package com.kotlin.video;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Call;
import okhttp3.Response;
import okio.Buffer;

import static com.tencent.bugly.beta.Beta.downloadListener;

public class hello {


    public void onResponse(Call call, Response response) throws IOException {
        long length = response.body().contentLength();
        if (length == 0) {
            // 说明文件已经下载完，直接跳转安装就好
//            downloadListener.complete(String.valueOf(getFile().getAbsoluteFile()));
            return;
        }
        int startsPoint = 0;
        // 保存文件到本地
        InputStream is = null;
        RandomAccessFile randomAccessFile = null;
        BufferedInputStream bis = null;

        byte[] buff = new byte[2048];
        int len = 0;
        try {
            is = response.body().byteStream();
            bis = new BufferedInputStream(is);

            File file = getFile();
            // 随机访问文件，可以指定断点续传的起始位置
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(startsPoint);
            while ((len = bis.read(buff)) != -1) {
                randomAccessFile.write(buff, 0, len);
            }

            // 下载完成

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private File getFile() {
        String root = Environment.getExternalStorageDirectory().getPath();
        File file = new File(root, "updateDemo.apk");
        return file;
    }

    private long getFileStart() {
        String root = Environment.getExternalStorageDirectory().getPath();
        File file = new File(root, "updateDemo.apk");
        return file.length();
    }



}
