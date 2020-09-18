package com.kotlin.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kotlin.app.R
import com.kotlin.app.utils.GlideEngine
import com.kotlin.app.video.CameraActivity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initView(root)
        return root
    }

    fun initView(root: View) {
        var bt_take_photograph = root.findViewById<Button>(R.id.bt_take_photograph)
        bt_take_photograph.setOnClickListener {
            PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .forResult(object : OnResultCallbackListener<LocalMedia?> {
                    override fun onResult(result: List<LocalMedia?>) {
                        // 结果回调
                    }

                    override fun onCancel() {
                        // 取消
                    }
                })
        }
        var bt_take_video = root.findViewById<Button>(R.id.bt_take_video)
        bt_take_video.setOnClickListener {

            //启动CameraActivity.java
            val cameraIntent = Intent(activity, CameraActivity::class.java)

            cameraIntent.putExtra("type", PictureConfig.TYPE_ALL)
            startActivityForResult(cameraIntent, PictureConfig.REQUEST_CAMERA)
        }
//        bt_take_small_video.setOnClickListener {
//
//        }
        var bt_photograph = root.findViewById<Button>(R.id.bt_photograph)
        bt_photograph.setOnClickListener {
            PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())
                .imageEngine(GlideEngine.createGlideEngine())
                .forResult(object : OnResultCallbackListener<LocalMedia?> {
                    override fun onResult(result: List<LocalMedia?>) {
                        // 结果回调
                    }

                    override fun onCancel() {
                        // 取消
                    }
                })
        }
//        bt_video.setOnClickListener {
//
//        }
//        bt_photograph_cut.setOnClickListener {
//
//
//        }
//        bt_photograph_look.setOnClickListener {
////            PictureSelector.create(this)
////                .themeStyle(R.style.picture_default_style)
////                .isNotPreviewDownload(true)
////                .imageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
////                .openExternalPreview(0, selectList);
//        }


    }

}