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

class MainActivity : AppCompatActivity() {

    var path_source = ""// 获取 file 的路径
    var mp3_path_source = "" // 文件mp3的路径

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
        sample_text.text = stringFromJNI()// 测试native 里面的方法
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
        /**
         * 转换文件
         */
        bt_encoder.setOnClickListener {


        }
        /**
         * 播放mp3
         */
        bt_player.setOnClickListener {

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

        tv_mp3_file_path.text = add(1,3).toString() + "////-----测试"
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    // 静态注册
    external fun stringFromJNI(): String
    // 动态注册
    external fun add(a: Int, b: Int): Int

    external fun gaussBlur(bitmap: Bitmap)

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
