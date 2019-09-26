package com.kotlin

import android.Manifest
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.code.base.BaseActivity
import com.code.utils.PermissionUtils
import com.code.utils.RouterPath
import com.kotlin.helper.OnPictureCallback
import com.kotlin.helper.PictureManager
import com.zwy.nsfw.api.NsfwHelper

@Route(path = RouterPath.path_image_activity)
class ImageActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_image

    private var nsfwHelper: NsfwHelper? = null

    lateinit var bt: Button
    lateinit var image: ImageView
    lateinit var myFresco: MyFresco
    private val pic = "http://www.taopic.com/uploads/allimg/140320/235013-14032020515270.jpg"

    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun initView() {
        bt = findViewById(R.id.bt_jiazai)
        image = findViewById(R.id.iv_load)
        myFresco = MyFresco.init(this);

        PermissionUtils.requestMorePermissions(this, permissions, 1)

        initNsfwHelper()

        bt.setOnClickListener {
            // 照相
//            PictureManager.getInstance()
//                    .with(this)
//                    .setCallback(object : OnPictureCallback<String> {
//                        override fun onCompleted(result: String?) {
//                            Log.d("dddd", result)//
//                            Glide.with(applicationContext).load(result).into(image)
//                        }
//
//                        override fun onError(errorMsg: Throwable?) {
//
//                        }
//                    }).startPhotoCamera()
// 选择图库
            PictureManager.getInstance()
                    .with(this)
                    .setCallback(object : OnPictureCallback<String> {
                        override fun onCompleted(result: String?) {
                            Log.d("dddd", result)//
                            Glide.with(applicationContext).load(result).into(image)

                            nsfwHelper?.scanBitmap(null, NsfwHelper.OnScanBitmapListener { sfw, nsfw ->


                            })
                        }

                        override fun onError(errorMsg: Throwable?) {

                        }
                    }).startPhotograph()

        }
    }

    private fun initNsfwHelper() {
        nsfwHelper = NsfwHelper.getInstance(this, true, 4)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        PictureManager.getInstance().with(this).onActivityResult(requestCode, resultCode, data)
    }
}