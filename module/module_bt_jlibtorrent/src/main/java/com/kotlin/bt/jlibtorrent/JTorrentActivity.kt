package com.kotlin.bt.jlibtorrent

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.TorrentOptions
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.kotlin.bt.jlibtorrent.databinding.ActivityJTorrentBinding
import com.kotlin.code.base.BaseNoModelActivity
import com.kotlin.code.utils.RouterPath
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.net.URLDecoder

@SuppressLint("SetTextI18n")
@Route(path = RouterPath.path_jTorrent_activity)
class JTorrentActivity : BaseNoModelActivity<ActivityJTorrentBinding>(), TorrentListener {

    override fun onCreate(): Int = R.layout.activity_j_torrent
    private lateinit var torrentStream: TorrentStream
    private var streamUrl = "magnet:?xt=urn:btih:88594aaacbde40ef3e2510c47374ec0aa396c08e&dn=bbb%5Fsunflower%5F1080p%5F30fps%5Fnormal.mp4&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80%2Fannounce&tr=udp%3A%2F%2Ftracker.publicbt.com%3A80%2Fannounce&ws=http%3A%2F%2Fdistribution.bbb3d.renderfarming.net%2Fvideo%2Fmp4%2Fbbb%5Fsunflower%5F1080p%5F30fps%5Fnormal.mp4"

    var onClickListener = View.OnClickListener {

        streamUrl = dataBinding.etDown.text.toString()

        dataBinding.progress.progress = 0
        if (torrentStream.isStreaming) {
            torrentStream.stopStream()
            dataBinding.button.text = "Start stream"
            return@OnClickListener
        }
        torrentStream.startStream(streamUrl)
        dataBinding.button.text = "Stop stream"
    }
    private val THUMB = "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg"

    override fun initView() {

        val action = intent.action
        val data = intent.data
        if (action != null && action == Intent.ACTION_VIEW && data != null) {
            try {
                streamUrl = URLDecoder.decode(data.toString(), "utf-8")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        val torrentOptions = TorrentOptions.Builder()
                .saveLocation(filesDir)
                .removeFilesAfterStop(true)
                .build()
        torrentStream = TorrentStream.init(torrentOptions)
        torrentStream.addListener(this)

        dataBinding.etDown.setText(streamUrl)
        dataBinding.button.setOnClickListener(onClickListener)
        dataBinding.progress.max = 100

//        PlayAction("http://dlhls.cdn.zhanqi.tv/zqlive/35180_KUDhx.m3u8")
//        PlayAction("http://220.161.87.62:8800/hls/0/index.m3u8")//直播
//        PlayAction("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4")

//        PlayVlcAction("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4")

    }

    override fun initData() {

    }

    override fun onStreamReady(torrent: Torrent) {
        dataBinding.progress.progress = 100
        val mediaFile = torrent.videoFile
        Log.d(TORRENT, "onStreamReady: $mediaFile")

        PlayAction(mediaFile.absolutePath)
        PlayVlcAction(mediaFile.absolutePath)
        // Create a sharing intent
//        startActivity(Intent().apply {
//            action = Intent.ACTION_VIEW
//            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mediaFile.extension)
//            val authority = "${BuildConfig.APPLICATION_ID}.provider"
//            data = FileProvider.getUriForFile(this@MainActivity, authority, mediaFile)
//            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or
//                    Intent.FLAG_ACTIVITY_CLEAR_TOP
//        })
    }

    override fun onStreamPrepared(torrent: Torrent?) {
        Log.d(TORRENT, "onStreamPrepared")
    }

    override fun onStreamStopped() {
        Log.d(TORRENT, "onStreamStopped")
    }

    override fun onStreamStarted(torrent: Torrent?) {
        Log.d(TORRENT, "onStreamStarted")
    }

    override fun onStreamProgress(torrent: Torrent, status: StreamStatus) {
        if (status.bufferProgress <= 100 && dataBinding.progress.progress < 100 && dataBinding.progress.progress != status.bufferProgress) {
            Log.d(TORRENT, "Progress: " + status.bufferProgress)
            dataBinding.progress.progress = status.bufferProgress
        }
    }

    override fun onStreamError(torrent: Torrent?, e: Exception?) {
        Log.e(TORRENT, "onStreamError", e)
        dataBinding.button.text = "Start stream"
    }

    companion object {
        private const val TORRENT = "Torrent"
    }


    fun PlayAction(path: String) {
        Log.d(TORRENT, "onStreamfinish===" + path)
//        player.setUrl(path) //设置视频地址
//
//        val controller = StandardVideoController(this)
//        controller.addDefaultControlComponent("标题", true)
//        player.setVideoController(controller) //设置控制器
//
//        player.start() //开始播放，不调用则不自动播放


    }

    fun PlayVlcAction(path: String) {
        Log.d(TORRENT, "onStreamfinish===" + path)
        dataBinding.vlcplayer.onAttached(this);
        dataBinding.vlcplayer.playVideo(path);


    }
}