package com.ck.driver.lamedemo2c

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thl.filechooser.FileChooser
import com.thl.filechooser.FileInfo
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioFormat
import android.media.MediaPlayer
import android.net.Uri
import android.text.TextUtils
import com.ck.driver.lametomp3.LameMp3
import com.ck.driver.lametomp3.Mp3Recorder
import java.io.File


class MainActivity : AppCompatActivity() {

    var path_source = ""// 获取 file 的路径
    var mp3_path_source = "" // 文件mp3的路径
    var mp3Recorder: Mp3Recorder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissins(object : PermissionUtils.OnPermissionListener {
            override fun onPermissionGranted() {

            }

            override fun onPermissionDenied(deniedPermissions: Array<String>) {
                Toast.makeText(this@MainActivity, "未获取到存储权限", Toast.LENGTH_SHORT).show()
            }
        })

        // Example of a call to a native method
        // 测试native 里面的方法
        sample_text.text =
            stringFromJNI() + "｜1 + 3 = " + add(1, 3).toString() + "｜lame版本：v" + getVersion()

        /**
         * 获取文件
         */
        bt_file.setOnClickListener {

            requestPermissins(object : PermissionUtils.OnPermissionListener {
                override fun onPermissionGranted() {

                    val fileChooser =
                        FileChooser(this@MainActivity, object : FileChooser.FileChoosenListener {
                            override fun onFileChoosen(filePath: String) {
                                path_source = filePath
                                tv_file_path.text = path_source

                            }
                        })

                    fileChooser.setBackIconRes(R.drawable.btn_back)
                    fileChooser.setTitle("选择文件路径")
                    fileChooser.setDoneText("确定")
                    fileChooser.setThemeColor(R.color.colorPrimary)

                    fileChooser.setChooseType(FileInfo.FILE_TYPE_FILE)
                    fileChooser.showFile(true)  //是否显示文件
                    fileChooser.open()

                }

                override fun onPermissionDenied(deniedPermissions: Array<String>) {
                    Toast.makeText(this@MainActivity, "未获取到存储权限", Toast.LENGTH_SHORT).show()
                }
            })
        }

        mp3Recorder = Mp3Recorder()

        /**
         * 转换文件
         */
        bt_encoder.setOnClickListener {
            if (TextUtils.isEmpty(path_source)) {
                Toast.makeText(this, "请选择文件", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mp3_path_source =
                Mp3Recorder.ROOTPATH + File.separator + "/Download/" + System.currentTimeMillis() + ".mp3"

            var wavFile = File(path_source)
            var mp3File = File(mp3_path_source)

            if (!wavFile.exists()) {
                Toast.makeText(this, "音频文件不存在", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }

            if (mp3File.exists()) {
                mp3File.delete();
            }

            val wavPath = wavFile.toString()
            val mp3Path = mp3File.toString()

            Thread(Runnable {

                LameMp3.encodeFile(wavPath, mp3Path)

                runOnUiThread {
                    tv_mp3_file_path.text = mp3_path_source
                    Toast.makeText(this@MainActivity, "转码成功", Toast.LENGTH_SHORT).show()
                }

            }).start()

//            requestPermissins(object : PermissionUtils.OnPermissionListener {
//                override fun onPermissionGranted() {
//                    if (!mp3Recorder?.isRecording()!!) {
//                        mp3Recorder?.startMp3Record()
//                        bt_encoder.setText("停止录音")
//                    } else {
//                        mp3Recorder?.stopMp3Record()
//                        bt_encoder.setText("开始录音")
//                    }
//                }
//
//                override fun onPermissionDenied(deniedPermissions: Array<String>) {
//
//                }
//            })


        }
        var isStop = false
        /**
         * 播放mp3
         */
        bt_player.setOnClickListener {
            if (TextUtils.isEmpty(path_source)) {
                Toast.makeText(this, "请选择文件", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var mediaPlayer = MediaPlayer()
            if (!isStop) {
                isStop = true
                mediaPlayer = MediaPlayer.create(this, Uri.fromFile(File(path_source)))
                mediaPlayer.start()
                mediaPlayer.isLooping = false
            }
            // 播放完成
            mediaPlayer.setOnCompletionListener {
                isStop = false
            }
        }
        /**
         * 图片转换
         */
        bt_gauss.setOnClickListener {
            //            val bitmap =
//                BitmapFactory.decodeResource(this.getResources(),R.mipmap.test)

            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test)
            gaussBlur(bitmap)
            //把Bitmap对象设置给iv2
            iv_gauss.setImageBitmap(bitmap);
        }
        //测试
//        tv_mp3_file_path.text = add(1, 3).toString() + "////-----测试版本：" + getVersion()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    // 静态注册
    external fun stringFromJNI(): String

    // 动态注册
    external fun add(a: Int, b: Int): Int

    // 图片模糊处理
    external fun gaussBlur(bitmap: Bitmap)

    // 获取lame 的版本
    external fun getVersion(): String


    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    private fun requestPermissins(mOnPermissionListener: PermissionUtils.OnPermissionListener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mOnPermissionListener.onPermissionGranted()
            return
        }
        val permissions = arrayOf(
            "android.permission.RECORD_AUDIO",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
        )
        PermissionUtils.requestPermissions(this, 0, permissions, mOnPermissionListener)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }
}
