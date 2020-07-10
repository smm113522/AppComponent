package com.kotlin.video.ui

import android.R.attr.name
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.base.BasesFragment
import com.kotlin.video.R
import com.kotlin.video.adapter.VideoItemAdapter
import com.kotlin.video.view.FullScreenVideoView
import com.kotlin.video.view.OnViewPagerListener
import com.kotlin.video.view.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.fragment_recommend.*


@Route(path = "/recommend/fragment")
class RecommendFragment : BasesFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_recommend
    }

    private var adapter: VideoItemAdapter? = null
    private var viewPagerLayoutManager: ViewPagerLayoutManager? = null

    /** 当前播放视频位置  */
    private var curPlayPos = 0
    private var videoView: FullScreenVideoView? = null

    private var ivCurCover: ImageView? = null

    override fun initView(savedInstanceState: Bundle?) {
        viewPagerLayoutManager = ViewPagerLayoutManager(activity)
        recyclerview.setLayoutManager(viewPagerLayoutManager)
        recyclerview.scrollToPosition(curPlayPos)
        adapter = VideoItemAdapter()
        recyclerview.adapter = adapter

        videoView = FullScreenVideoView(activity)
        viewPagerLayoutManager?.setOnViewPagerListener(object : OnViewPagerListener {

            override fun onInitComplete() {
                playCurVideo(curPlayPos)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (ivCurCover != null) {
                    ivCurCover!!.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                playCurVideo(position)
            }

        })
        adapter?.addData("dddd1")
        adapter?.addData("dddd2")
        adapter?.addData("dddd3")
        adapter?.addData("dddd4")
        adapter?.addData("dddd5")
        adapter?.addData("dddd6")
    }

    override fun onResume() {
        super.onResume()

        //返回时，推荐页面可见，则继续播放视频
        videoView!!.start()
    }

    /**
     * 移除videoview父view
     */
    private fun dettachParentView(rootView: ViewGroup) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        val parent = videoView!!.parent
        if(parent != null) {
            var d = parent as ViewGroup
            d.removeView(videoView)
        }
        rootView.addView(videoView, 0)
    }

    /**
     * 自动播放视频
     */
    private fun autoPlayVideo(position: Int, ivCover: ImageView?) {

        val id: Int = activity!!.getApplication().getResources().getIdentifier("video" + position, "raw", activity!!.getApplication().getPackageName())

        val bgVideoPath = "android.resource://" + activity!!.packageName.toString() + "/" + id
        videoView!!.setVideoPath(bgVideoPath)
        videoView!!.start()
        videoView!!.setOnPreparedListener { mp ->
            mp.isLooping = true

            //延迟取消封面，避免加载视频黑屏
            object : CountDownTimer(200, 200) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    if (ivCover != null) {
                        ivCover.visibility = View.GONE
                        ivCurCover = ivCover
                    }
                }
            }.start()
        }
    }

    private fun playCurVideo(position: Int) {
//        if (position == curPlayPos) {
//            return
//        }
        val itemView = viewPagerLayoutManager!!.findViewByPosition(position) ?: return
        val rootView = itemView.findViewById<ViewGroup>(R.id.rl_container)
        // 添加视频到view 中去
        dettachParentView(rootView)
        autoPlayVideo(position, null)
    }
}