package com.kotlin.video

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.code.utils.NavigationUtil
import com.kotlin.code.utils.RouterPath
import kotlinx.android.synthetic.main.activity_video.*
import okhttp3.*
import java.io.*
import java.util.concurrent.Executors


@Route(path = RouterPath.path_video_activity)
class DemoActivity : Activity() {

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient()
    }

    var murl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        murl = et_url.text.toString().trim()
        bt_download.setOnClickListener {
            //            val request: Request = Request.Builder()
//                    .url(murl)
//                    .build()
//            val call: Call = okHttpClient.newCall(request)
//
//            call.enqueue(callback)
            saveFileByUrl(progressBar, this, murl)
        }
        bt_4j.setOnClickListener {
            NavigationUtil.toActivity(RouterPath.path_4jTorrent_activity)
//            var intent = Intent(this, TorrentjActivity::class.java)
//            startActivity(intent)
        }
        bt_j_torrent.setOnClickListener {
            NavigationUtil.toActivity(RouterPath.path_jTorrent_activity)
        }
    }

    var callback = object : Callback {

        override fun onFailure(call: Call, e: IOException) {
            Toast.makeText(this@DemoActivity, "获取失败", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(call: Call, response: Response) {
            var fileName = hello.getHeaderFileName(murl, response)
            val startsPoint = 0
            // 保存文件到本地
            var `is`: InputStream? = null
            var randomAccessFile: RandomAccessFile? = null
            var bis: BufferedInputStream? = null

            val buff = ByteArray(2048)
            var len = 0
            var downloadLength: Int = 0
            try {
                var total = response.body()!!.contentLength()
                `is` = response.body()!!.byteStream()
                bis = BufferedInputStream(`is`)
                val file: File = getFile(fileName)
                // 随机访问文件，可以指定断点续传的起始位置
                randomAccessFile = RandomAccessFile(file, "rwd")
                randomAccessFile.seek(startsPoint.toLong())
                while (bis.read(buff).also { len = it } != -1) {
                    randomAccessFile.write(buff, 0, len)

                    downloadLength += len
                    println(downloadLength)
                    println(total)
                    var progress = (downloadLength * 1.0f / total * 100)
                    println(progress)
                    Log.e(TORRENT, "progress=$progress")
                    progressBar.progress = progress.toInt()
                }
                Toast.makeText(this@DemoActivity, "下载完成,地址-》" + file.absolutePath, Toast.LENGTH_SHORT).show()
                progressBar.progress = 0
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

    fun saveFileByUrl(
            progressBar: ProgressBar,
            context: Context,
            uri: String?
    ) {
        val mainHandler = Handler(Looper.getMainLooper())
        val executor =
                Executors.newSingleThreadExecutor()
        executor.execute(Runnable {
            var request = Request.Builder()
                    .url(uri)
                    .build();
            var call = OkHttpClient().newCall(request);
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    mainHandler.post {
                        Toast.makeText(
                                context,
                                "保存失败！",
                                Toast.LENGTH_SHORT
                        ).show()

                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    // 保存文件到本地
                    var fileName = hello.getHeaderFileName(uri, response)
                    val startsPoint = 0
                    // 保存文件到本地
                    var `is`: InputStream? = null
                    var randomAccessFile: RandomAccessFile? = null
                    var bis: BufferedInputStream? = null

                    val buff = ByteArray(2048)
                    var len = 0
                    var downloadLength: Int = 0
                    try {
                        var total = response.body()!!.contentLength()
                        `is` = response.body()!!.byteStream()
                        bis = BufferedInputStream(`is`)
                        val file: File = getFile(fileName)
                        // 随机访问文件，可以指定断点续传的起始位置
                        randomAccessFile = RandomAccessFile(file, "rwd")
                        randomAccessFile.seek(startsPoint.toLong())
                        while (bis.read(buff).also { len = it } != -1) {
                            randomAccessFile.write(buff, 0, len)

                            downloadLength += len
//                            println(downloadLength)
//                            println(total)
                            var progress = (downloadLength * 1.0f / total * 100)
//                            println(progress)
                            Log.e(TORRENT, "progress=$progress")
                            progressBar.progress = progress.toInt()

                        }
                        mainHandler.post {
                            Toast.makeText(
                                    context,
                                    "下载完成,地址->" + file.absolutePath,
                                    Toast.LENGTH_SHORT
                            ).show()
                            progressBar.progress = 0

                            ARouter.getInstance().build(RouterPath.path_player_activity)
                                    .withString("url", file.absolutePath)
                                    .navigation();
                        }
                        // 下载完成
                    } catch (e: Exception) {
                        e.printStackTrace()
                        mainHandler.post {
                            Toast.makeText(
                                    context,
                                    "保存失败！",
                                    Toast.LENGTH_SHORT
                            ).show()

                        }
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

            });

        })
    }


    companion object {
        private const val TORRENT = "demo"
    }

    private fun getFile(fileName: String): File {
        val root = Environment.getExternalStorageDirectory().path
        return File(root, fileName)
    }

}

