package com.kotlin.video

import android.app.Activity
import android.os.Bundle
import android.os.Environment
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.utils.RouterPath
import kotlinx.android.synthetic.main.activity_video.*
import okhttp3.*
import java.io.*

@Route(path = RouterPath.path_video_activity)
class DemoActivity : Activity() {

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        var murl = et_url.text.toString().trim()
        bt_download.setOnClickListener {
            val request: Request = Request.Builder()
                    .url(murl)
                    .build()
            val call: Call = okHttpClient.newCall(request)
            call.enqueue(callback)
        }
    }

    var callback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            val startsPoint = 0
            // 保存文件到本地
            // 保存文件到本地
            var `is`: InputStream? = null
            var randomAccessFile: RandomAccessFile? = null
            var bis: BufferedInputStream? = null

            val buff = ByteArray(2048)
            var len = 0
            var downloadLength = 0
            try {
                var size = response.body()!!.contentLength()

                `is` = response.body()!!.byteStream()
                bis = BufferedInputStream(`is`)
                val file: File = getFile("xxx.mp4")
                // 随机访问文件，可以指定断点续传的起始位置
                randomAccessFile = RandomAccessFile(file, "rwd")
                randomAccessFile.seek(startsPoint.toLong())
                while (bis.read(buff).also { len = it } != -1) {
                    randomAccessFile.write(buff, 0, len)
                    downloadLength += len
                    println(downloadLength)
                    println(size)
                    val d: Long = downloadLength * 10000 / size
                    println(d)
                    progressBar.progress = d.toInt()
                }

                // 下载完成
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    `is`?.close()
                    bis?.close()
                    randomAccessFile?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }


    private fun getFile(fileName: String): File {
        val root = Environment.getExternalStorageDirectory().path
        return File(root, fileName)
    }

}