package com.kotlin.video.libtorrentj

import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.os.*
import com.kotlin.video.libtorrentj.bean.Torrent
import com.kotlin.video.libtorrentj.bean.TorrentMetaInfo
import com.kotlin.video.libtorrentj.download.TorrentEngine
import com.kotlin.video.libtorrentj.exception.DecodeException
import org.libtorrent4j.Priority
import java.io.File
import java.util.*
import java.util.concurrent.Executors

class TorrentjService : Service() {

    private val binder: IBinder = LocalBinder()


    class LocalBinder : Binder() {
        fun getService(): TorrentjService? {
            return TorrentjService()
        }

    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    var isAlreadyRunning = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var source = intent?.getStringExtra("source")

        var sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
        var path = sdCardDir.absolutePath + "/2"
        var file = File(path)
        if (!file.exists()){
            file.mkdir()
        }
        var torrentMetaInfo = TorrentMetaInfo(source)
        println(torrentMetaInfo)

        var priorities: ArrayList<Priority?>? = null
        if (torrentMetaInfo.fileCount !== 0) {
            priorities = ArrayList(Collections.nCopies(torrentMetaInfo.fileCount, Priority.DEFAULT))
        }

        var torrent = Torrent(torrentMetaInfo.sha1Hash, "test1", priorities, file.absolutePath, System.currentTimeMillis())

        torrent.setSource(source);
        torrent.setSequentialDownload(true);//是否顺序下载
        torrent.setPaused(true);//是否立即下载

        torrent.setFilePriorities(Collections.nCopies(torrentMetaInfo.fileCount, Priority.DEFAULT))

        if (!isAlreadyRunning) {
            isAlreadyRunning = true

            var sharedPreferences = SettingsManager.getPreferences(applicationContext)
            sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)

            TorrentEngine.getInstance().setContext(applicationContext)
            TorrentEngine.getInstance().setCallback(torrentEngineCallback)
            TorrentEngine.getInstance().settings = SettingsManager.readEngineSettings(applicationContext)
            TorrentEngine.getInstance().start()
        }
//        donwLoad(torrent)
        TorrentEngine.getInstance().download(torrent)

        val tasksCount = TorrentEngine.getInstance().tasksCount()

        return START_STICKY

    }

    var onSharedPreferenceChangeListener = object : SharedPreferences.OnSharedPreferenceChangeListener {
        override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {

        }

    }

    var torrentEngineCallback = object : TorrentEngineCallback {
        override fun onTorrentMetadataLoaded(id: String?, err: Exception?) {
            print("onTorrentMetadataLoaded")
        }

        override fun onEngineStarted() {
            print("onEngineStarted")
        }

        override fun onTorrentError(id: String?, errorMsg: String?) {
            print("onTorrentError" + errorMsg)
        }

        override fun onNatError(errorMsg: String?) {
            print("onNatError" + errorMsg)
        }

        override fun onIpFilterParsed(success: Boolean) {
            print("onIpFilterParsed")
        }

        override fun onTorrentResumed(id: String?) {
            print("onTorrentResumed")
        }

        override fun onTorrentStateChanged(id: String?) {
            print("onTorrentStateChanged")
        }

        override fun onTorrentMoved(id: String?, success: Boolean) {
            print("onTorrentMoved")
        }

        override fun onTorrentFinished(id: String?) {
            print("完成onTorrentFinished")
        }

        override fun onTorrentRemoved(id: String?) {
            print("onTorrentRemoved")
        }

        override fun onTorrentPaused(id: String?) {
            print("onTorrentPaused")
        }

        override fun onSessionError(errorMsg: String?) {
            print("onSessionError" + errorMsg)
        }

        override fun onRestoreSessionError(id: String?) {
            print("onRestoreSessionError")
        }

        override fun onTorrentAdded(id: String?) {
            print("onTorrentAdded")
        }

        override fun onMagnetLoaded(hash: String?, bencode: ByteArray?) {
            var info: TorrentMetaInfo? = null
            try {
                info = TorrentMetaInfo(bencode)
            } catch (e: DecodeException) {
                e.printStackTrace()
            }
            print("onMagnetLoaded" + info)
        }

    }

    val mainHandler = Handler(Looper.getMainLooper())

    fun donwLoad(torrent: Torrent) {
        val executor =
                Executors.newSingleThreadExecutor()
        executor.execute(Runnable {
            TorrentEngine.getInstance().download(torrent)
//
//            var request = Request.Builder()
//                    .url("")
//                    .build();
//            var call = OkHttpClient().newCall(request);
//            call.enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    mainHandler.post {
//
//
//                    }
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    // 保存文件到本地
//
//                }
//
//            });

        })

    }

}