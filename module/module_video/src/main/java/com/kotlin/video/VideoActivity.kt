package com.kotlin.video

//package com.kotlin.video
//
//import android.Manifest
//import android.content.pm.ActivityInfo
//import android.view.View
//import com.code.base.BaseActivity
//import com.shuyu.gsyvideoplayer.GSYVideoManager
//import com.shuyu.gsyvideoplayer.utils.OrientationUtils
//import kotlinx.android.synthetic.main.activity_video.*
//
///**
// * 视频播放
// * 和下载功能
// */
//
////@Route(path = RouterPath.path_video_activity)
//class VideoActivity : BaseActivity() {
//
//    override fun getLayoutId(): Int = R.layout.activity_video
//
//    private val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE)
//    var orientationUtils: OrientationUtils? = null
//    override fun initView() {
//        PermissionUtils.requestMorePermissions(this, permissions, 1)
//
//        //设置旋转
//        orientationUtils = OrientationUtils(this, video_player)
//
//        val source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
//        video_player.setUp(source1, true, "测试视频")
//
//        //增加title
//        video_player.getTitleTextView().setVisibility(View.VISIBLE);
//        //设置返回键
//        video_player.getBackButton().setVisibility(View.VISIBLE);
//
//        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
//        video_player.getFullscreenButton().setOnClickListener(View.OnClickListener {
//            orientationUtils?.resolveByClick()
//        })
//        //是否可以滑动调整
//        video_player.setIsTouchWiget(true)
//        //设置返回按键功能
//        video_player.getBackButton().setOnClickListener(View.OnClickListener { onBackPressed() })
//        video_player.startPlayLogic()
//
////        val demo3apkPath = "test5.torrent"
////        val pluginFolder = "test5.torrent"
////
////        var pluginFilePath = Environment.getExternalStorageDirectory().toString() + "/DynamicLoadHost" + File.separator + pluginFolder
////
////        var pluginFile = File(pluginFilePath)
////
////        if (!pluginFile.exists()) {
////            //      //   开始复制
////            AssetFile(applicationContext).fromAsset(demo3apkPath).copyAssetsFileToAppFiles(pluginFile.getAbsolutePath())
////        }
////
////        bt_download.setOnClickListener {
////            Thread(Runnable {
////                DownloadTorrent.begin(pluginFile.absolutePath)
////            }).start()
////        }
//
//    }
//
//    override fun onPause() {
//        super.onPause()
//        video_player.onVideoPause()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        video_player.onVideoResume()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        GSYVideoManager.releaseAllVideos()
//        if (orientationUtils != null)
//            orientationUtils?.releaseListener()
//    }
//
//    override fun onBackPressed() {
//        //先返回正常状态
//        if (orientationUtils?.getScreenType() === ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            video_player.getFullscreenButton().performClick()
//            return
//        }
//        //释放所有
//        video_player.setVideoAllCallBack(null)
//        super.onBackPressed()
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//}