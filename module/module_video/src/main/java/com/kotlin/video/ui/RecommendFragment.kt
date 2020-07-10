package com.kotlin.video.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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
     * 自动播放视频
     */
    private fun autoPlayVideo(position: Int, ivCover: ImageView?) {
        val bgVideoPath = "android.resource://" + activity!!.packageName.toString() + "/" + R.raw.video1
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
        if (position == curPlayPos) {
            return
        }
        val itemView = viewPagerLayoutManager!!.findViewByPosition(position) ?: return

        autoPlayVideo(curPlayPos, null)
    }
}