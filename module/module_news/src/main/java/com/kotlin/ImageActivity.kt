package com.kotlin

import android.widget.Button
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.code.base.BaseActivity
import com.code.utils.RouterPath

@Route(path = RouterPath.ImageCenter.path_image)
class ImageActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_image
    }

    override fun getViewDataLayout(): Int {
        return 0
    }

    lateinit var bt: Button
    lateinit var image: ImageView
    lateinit var myFresco: MyFresco
    private val pic = "http://www.taopic.com/uploads/allimg/140320/235013-14032020515270.jpg"

    override fun initView() {
        bt = findViewById(R.id.bt_jiazai)
        image = findViewById(R.id.iv_load)
        myFresco = MyFresco.init(this);


        bt.setOnClickListener {
//            Thread {
                Glide.with(applicationContext).load(pic).into(image)
//                val bit: Bitmap = myFresco.loadPic(pic)
//                runOnUiThread {
//                    image.setImageBitmap(bit)
//                }
//            }.start()


        }
    }


}