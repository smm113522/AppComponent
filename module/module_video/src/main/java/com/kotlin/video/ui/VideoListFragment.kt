package com.kotlin.video.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.adapter.OnItemClickListener
import com.kotlin.code.base.BasesFragment
import com.kotlin.video.R
import com.kotlin.video.adapter.VideoListAdapter
import com.kotlin.video.view.FullScreenVideoView
import kotlinx.android.synthetic.main.fragment_recommend.*

// 列表 视频 + 播放 和小窗
@Route(path = "/videolist/fragment")
class VideoListFragment : BasesFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_recommend
    }

    private var videoView: FullScreenVideoView? = null
    private var adapter: VideoListAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun initView(savedInstanceState: Bundle?) {
        linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = linearLayoutManager
        adapter = VideoListAdapter()
        recyclerview.adapter = adapter
        adapter?.setOnItemListener(object : OnItemClickListener<String> {
            override fun onItemClick(data: String?, position: Int) {
                playCurVideo(position)
            }
            override fun onItemLongClick(data: String?, position: Int): Boolean {
                return false
            }

        })
        videoView = FullScreenVideoView(activity)

        adapter?.addData("dddd1")
        adapter?.addData("dddd2")
        adapter?.addData("dddd3")
        adapter?.addData("dddd4")
        adapter?.addData("dddd5")
        adapter?.addData("dddd6")
        adapter?.addData("dddd7")
        adapter?.addData("dddd8")

        playCurVideo(0)

    }

    /**
     * 移除videoview父view
     */
    private fun dettachParentView(rootView: ViewGroup) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        val parent = videoView!!.parent
        if (parent != null) {
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
//                    if (ivCover != null) {
//                        ivCover.visibility = View.GONE
//                        ivCurCover = ivCover
//                    }
                }
            }.start()
        }
    }

    private fun playCurVideo(position: Int) {
//        if (position == curPlayPos) {
//            return
//        }
        val itemView = linearLayoutManager!!.findViewByPosition(position) ?: return
        val rootView = itemView.findViewById<ViewGroup>(R.id.rl_container)
        // 添加视频到view 中去
        dettachParentView(rootView)
        autoPlayVideo(position, null)
    }
}