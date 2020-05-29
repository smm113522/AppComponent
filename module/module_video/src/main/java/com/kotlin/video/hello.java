package com.kotlin.video;

import android.os.Environment;
import android.text.TextUtils;

import com.kotlin.code.utils.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

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

    /**
     * 解析文件头
     * Content-Disposition:attachment;filename=FileName.txt
     * Content-Disposition: attachment; filename*="UTF-8''%E6%9B%BF%E6%8D%A2%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.pdf"
     */
    public static String getHeaderFileName(String path,Response response) {
        String dispositionHeader = response.header("Content-Disposition");
        if (!TextUtils.isEmpty(dispositionHeader)) {
            dispositionHeader.replace("attachment;filename=", "");
            dispositionHeader.replace("filename*=utf-8", "");
            String[] strings = dispositionHeader.split("; ");
            if (strings.length > 1) {
                dispositionHeader = strings[1].replace("filename=", "");
                dispositionHeader = dispositionHeader.replace("\"", "");
                return dispositionHeader;
            }
            String uuid = UUID.randomUUID().toString();  //转化为String对象
            return uuid + "." + Utils.INSTANCE.parseSuffix(path);
        }
        String uuid = UUID.randomUUID().toString();  //转化为String对象
        return uuid + "." + Utils.INSTANCE.parseSuffix(path);
    }




}
