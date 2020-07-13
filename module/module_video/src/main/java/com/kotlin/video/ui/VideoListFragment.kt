package com.kotlin.video.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.base.BasesFragment
import com.kotlin.video.R
import com.kotlin.video.list.MyVideoView
import com.kotlin.video.list.VideoBean
import com.kotlin.video.list.VideoRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_recommend.*

// 列表 视频 + 播放 和小窗
@Route(path = "/videolist/fragment")
class VideoListFragment : BasesFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_recommend
    }

    private var videoPosition = -1
    private val videoBeanList: ArrayList<VideoBean> = ArrayList()
    private var videoView: MyVideoView? = null
    private var adapter: VideoRecyclerAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    private var videoRootViewFl: FrameLayout? = null
    private var fullScreen: FrameLayout? = null
    private var lastView: View? = null
    private val imageIds = intArrayOf(R.drawable.hzw_a, R.drawable.hzw_b,
            R.drawable.hzw_d, R.drawable.hzw_e, R.drawable.hzw_f, R.drawable.hzw_h,
            R.drawable.hzw_i, R.drawable.hzw_j, R.drawable.hzw_k)

    private var VIDEO_PATH = "http://dn-chunyu.qbox.me/fwb/static/images/home/video/video_aboutCY_A.mp4"

    override fun initView(savedInstanceState: Bundle?) {
        linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = linearLayoutManager
        initData()

        adapter = VideoRecyclerAdapter(videoBeanList)
        recyclerview.adapter = adapter

//        videoView = MyVideoView(activity)

        videoRootViewFl = video_root_fl
        fullScreen = video_full_screen

        adapter?.setListener(object : VideoRecyclerAdapter.OnClickPlayListener {
            override fun onPlayClick(view: View, videoPath: String) {
                showVideo(view, videoPath)
            }
        })
        recyclerview.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                if (videoPosition == -1 || videoRootViewFl!!.getVisibility() != View.VISIBLE) {
                    return
                }
                if (videoPosition == recyclerview.getChildAdapterPosition(view)) {
                    videoPosition = -1
                    showVideo(view, VIDEO_PATH)
                }
            }

            override fun onChildViewDetachedFromWindow(view: View) {
                if (videoView == null || videoRootViewFl!!.getVisibility() == View.VISIBLE) return
                var v = view.findViewById<View>(R.id.item_video_root_fl)
                if (v != null) {
                    val fl = v as FrameLayout
                    videoPosition = recyclerview.getChildAdapterPosition(view)
                    if (fl.childCount > 0) {
                        fl.removeAllViews()
                        var position = 0
                        if (videoView!!.isPlaying) {
                            position = videoView!!.position
                            videoView!!.stop()
                        }
                        videoRootViewFl!!.setVisibility(View.VISIBLE)
                        videoRootViewFl!!.removeAllViews()
//                        lastView = videoRootViewFl
                        lastView = view
                        videoRootViewFl!!.addView(videoView, ViewGroup.LayoutParams(-1, -1))
                        videoView!!.setVideoPath(VIDEO_PATH)
                        videoView!!.start()
                        videoView!!.seekTo(position)
                        if (videoView!!.isPause()) {
                            videoView!!.resume();
                        }
                    }
                    fl.visibility = View.GONE
                }
                v = view.findViewById(R.id.item_imageview)
                if (v != null) {
                    if (v.visibility != View.VISIBLE) {
                        v.visibility = View.VISIBLE
                    }
                }
                v = view.findViewById(R.id.item_image_play)
                if (v != null) {
                    if (v.visibility != View.VISIBLE) {
                        v.visibility = View.VISIBLE
                    }
                }
            }
        })

    }

    private fun initData() {
        var videoBean: VideoBean
        for (i in 0..99) {
            videoBean = VideoBean(imageIds[i % imageIds.size], VIDEO_PATH)
            videoBeanList.add(videoBean)
        }
    }
    var showXiao = false;
    private fun showVideo(view: View, videoPath: String) {
        removeVideoView()
        if (videoRootViewFl!!.visibility == View.VISIBLE) {
            videoRootViewFl!!.removeAllViews()
            videoRootViewFl!!.visibility = View.GONE
            showXiao = false
        }
        if (videoView == null) {
            videoView = MyVideoView(activity)
            videoView?.setListener(object : MyVideoView.IFullScreenListener {
                override fun onClickFull(isFull: Boolean) {
                    if (videoRootViewFl!!.visibility == View.VISIBLE) {
                        showXiao = true;
                    }
                    if (videoView!!.isPlaying) {
                        videoView!!.stop()
                    }
                    if (isFull) {
                        fullScreen?.setVisibility(View.VISIBLE);
                        removeVideoView();
                        videoRootViewFl?.removeAllViews()
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        fullScreen?.removeAllViews()
                        fullScreen?.addView(videoView, ViewGroup.LayoutParams(-1, -1));
                        videoView?.setVideoPath(VIDEO_PATH);
//                        videoView?.start();
                        if (!videoView!!.isPlaying) {
                            videoView!!.resume()
                        }
                    } else {
                        fullScreen?.removeAllViews();
                        fullScreen?.setVisibility(View.GONE);
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                        if (lastView instanceof ViewGroup) {
//                            ((ViewGroup) lastView).addView(videoView);
//                        }
                        if (showXiao){
                            videoRootViewFl!!.addView(videoView, ViewGroup.LayoutParams(-1, -1))
                            videoRootViewFl!!.visibility = View.VISIBLE
                        }

                        videoView?.setVideoPath(VIDEO_PATH);
//                        videoView?.start();
                        if (!videoView!!.isPlaying) {
                            videoView!!.resume()
                        }

                    }
                }
            })
        }
        videoView?.stop()
        var v: View? = view.findViewById(R.id.item_imageview)
        if (v != null) v.visibility = View.INVISIBLE
        v = view.findViewById(R.id.item_image_play)
        if (v != null) v.visibility = View.INVISIBLE
        v = view.findViewById(R.id.item_video_root_fl)
        if (v != null) {
            v.visibility = View.VISIBLE
            val fl = v as FrameLayout
            fl.removeAllViews()
            fl.addView(videoView, ViewGroup.LayoutParams(-1, -1))
            VIDEO_PATH = videoPath
            videoView!!.setVideoPath(videoPath)
            videoView!!.start()
        }
        lastView = view
    }

    private fun removeVideoView() {
        var v: View
        lastView?.let {
            v = it.findViewById(R.id.item_imageview)
            if (v != null) v.visibility = View.VISIBLE
            v = it.findViewById(R.id.item_image_play)
            if (v != null) v.visibility = View.VISIBLE
            v = it.findViewById(R.id.item_video_root_fl)
            if (v != null) {
                val ll = v as FrameLayout
                ll.removeAllViews()
                v.setVisibility(View.GONE)
            }
        }
//        if (lastView != null) {
//            v = lastView!!.findViewById(R.id.item_imageview)
//            if (v != null) v.visibility = View.VISIBLE
//            v = lastView!!.findViewById(R.id.item_image_play)
//            if (v != null) v.visibility = View.VISIBLE
//            v = lastView!!.findViewById(R.id.item_video_root_fl)
//            if (v != null) {
//                val ll = v as FrameLayout
//                ll.removeAllViews()
//                v.setVisibility(View.GONE)
//            }
//        }
    }

    override fun onDestroy() {
        if (videoView != null) {
            videoView!!.stop()
        }
        super.onDestroy()
    }

}