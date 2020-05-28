package com.kotlin.video.libtorrentj

import android.content.Intent
import android.content.SharedPreferences
import android.os.Environment
import android.os.Handler
import android.os.Looper
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.base.BaseNoModelActivity
import com.kotlin.code.utils.AssetFile
import com.kotlin.code.utils.RouterPath
import com.kotlin.video.R
import com.kotlin.video.databinding.ActivityToorentJBinding
import com.kotlin.video.libtorrentj.bean.Torrent
import com.kotlin.video.libtorrentj.bean.TorrentMetaInfo
import com.kotlin.video.libtorrentj.download.TorrentEngine
import com.kotlin.video.libtorrentj.exception.DecodeException
import okhttp3.*
import org.libtorrent4j.Priority
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.Executors

@Route(path = RouterPath.path_4jTorrent_activity)
class TorrentjActivity : BaseNoModelActivity<ActivityToorentJBinding>() {


    override fun onCreate(): Int {
        return R.layout.activity_toorent_j
    }

    override fun initView() {
        dataBinding.btCopy.setOnClickListener {

            var sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            var file = File(sdCardDir, "test1.torrent")

            AssetFile(this.context).fromAsset("test1.torrent").copyAssetsFileToAppFiles(file)
            var source = file.absolutePath

            var intent = Intent(this,TorrentjService::class.java)
            intent.putExtra("source",source)
            startService(intent)

//            var torrentMetaInfo = TorrentMetaInfo(source)
//            println(torrentMetaInfo)
//
//            var priorities: ArrayList<Priority?>? = null
//            if (torrentMetaInfo.fileCount !== 0) {
//                priorities = ArrayList(Collections.nCopies(torrentMetaInfo.fileCount, Priority.DEFAULT))
//            }
//
//            var torrent = Torrent(torrentMetaInfo.sha1Hash, "test1", priorities, sdCardDir.absolutePath, System.currentTimeMillis())
//
//            torrent.setSource(source);
//            torrent.setSequentialDownload(true);//是否顺序下载
//            torrent.setPaused(true);//是否立即下载
//
//            torrent.setFilePriorities(Collections.nCopies(torrentMetaInfo.fileCount, Priority.DEFAULT))
//
//            var sharedPreferences = SettingsManager.getPreferences(applicationContext)
//            sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
//
//            TorrentEngine.getInstance().setContext(context)
//            TorrentEngine.getInstance().setCallback(torrentEngineCallback)
//            TorrentEngine.getInstance().settings = SettingsManager.readEngineSettings(context)
//            TorrentEngine.getInstance().start()
//
//           donwLoad(torrent)

        }
        dataBinding.btDownload.setOnClickListener {
            var txt = dataBinding.etUrl


        }
    }

    override fun initData() {

    }

    var onSharedPreferenceChangeListener = object : SharedPreferences.OnSharedPreferenceChangeListener {
        override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {

        }

    }

    var torrentEngineCallback = object : TorrentEngineCallback {
        override fun onTorrentMetadataLoaded(id: String?, err: Exception?) {
            print("")
        }

        override fun onEngineStarted() {
            print("")
        }

        override fun onTorrentError(id: String?, errorMsg: String?) {
            print("")
        }

        override fun onNatError(errorMsg: String?) {
            print("")
        }

        override fun onIpFilterParsed(success: Boolean) {
            print("")
        }

        override fun onTorrentResumed(id: String?) {
            print("")
        }

        override fun onTorrentStateChanged(id: String?) {
            print("")
        }

        override fun onTorrentMoved(id: String?, success: Boolean) {
            print("")
        }

        override fun onTorrentFinished(id: String?) {
            print("")
        }

        override fun onTorrentRemoved(id: String?) {
            print("")
        }

        override fun onTorrentPaused(id: String?) {
            print("")
        }

        override fun onSessionError(errorMsg: String?) {
            print("")
        }

        override fun onRestoreSessionError(id: String?) {
            print("")
        }

        override fun onTorrentAdded(id: String?) {
            print("")
        }

        override fun onMagnetLoaded(hash: String?, bencode: ByteArray?) {
            var info: TorrentMetaInfo? = null
            try {
                info = TorrentMetaInfo(bencode)
            } catch (e: DecodeException) {
                e.printStackTrace()
            }
            print("")
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