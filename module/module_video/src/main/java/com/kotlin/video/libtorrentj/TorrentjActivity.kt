package com.kotlin.video.libtorrentj

import android.content.Intent
import android.content.SharedPreferences
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.code.base.BaseNoModelActivity
import com.kotlin.code.utils.AssetFile
import com.kotlin.code.utils.RouterPath
import com.kotlin.code.utils.ToastsUtils
import com.kotlin.video.R
import com.kotlin.video.databinding.ActivityToorentJBinding
import com.kotlin.video.libtorrentj.bean.Torrent
import com.kotlin.video.libtorrentj.bean.TorrentMetaInfo
import com.kotlin.video.libtorrentj.download.TorrentEngine
import com.kotlin.video.libtorrentj.exception.DecodeException
import org.libtorrent4j.AlertListener
import org.libtorrent4j.SessionManager
import org.libtorrent4j.TorrentInfo
import org.libtorrent4j.alerts.AddTorrentAlert
import org.libtorrent4j.alerts.Alert
import org.libtorrent4j.alerts.AlertType
import org.libtorrent4j.alerts.BlockFinishedAlert
import java.io.File
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


@Route(path = RouterPath.path_4jTorrent_activity)
class TorrentjActivity : BaseNoModelActivity<ActivityToorentJBinding>() {

    @JvmField
    @Autowired
    var localPath = ""

    override fun onCreate(): Int {
        return R.layout.activity_toorent_j
    }

    override fun initView() {
        ARouter.getInstance().inject(this)

        if (!TextUtils.isEmpty(localPath)) {
            dataBinding.tvPath.setText(localPath)
        }

        dataBinding.btCopy.setOnClickListener {

            var sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            var file = File(sdCardDir, "test1.torrent")

            AssetFile(this.context).fromAsset("test1.torrent").copyAssetsFileToAppFiles(file)

            var source = file.absolutePath

            dataBinding.tvPath.setText(source)

            var torrentMetaInfo = TorrentMetaInfo(source)

            var intent = Intent(this, TorrentjService::class.java)
            intent.putExtra("source", source)
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
//            PieceMap(source)
        }
        dataBinding.btInfo.setOnClickListener {
            ARouter.getInstance().build(RouterPath.path_fragment_activity)
                    .withString("fragmentPath", RouterPath.path_torrent_info_fragment)
                    .navigation();
        }

        dataBinding.btDownload.setOnClickListener {
            var txt = dataBinding.etUrl.text.toString().trim()
            if (TextUtils.isEmpty(txt)) {
                ToastsUtils.showToast("请输入地址", false)
                return@setOnClickListener
            }
        }

        dataBinding.btInfo1.setOnClickListener {

        }


    }

    fun PieceMap(path: String) {

        val torrentFile = File(path)

        val s = SessionManager()

        val signal = CountDownLatch(1)

        s.addListener(object : AlertListener {
            override fun types(): IntArray {
                return IntArray(1)
            }

            override fun alert(alert: Alert<*>) {
                val type = alert.type()
                when (type) {
                    AlertType.ADD_TORRENT -> {
                        println("Torrent added")
                        (alert as AddTorrentAlert).handle().resume()
                    }
                    AlertType.BLOCK_FINISHED -> {
                        val a = alert as BlockFinishedAlert
                        val p = (a.handle().status().progress() * 100).toInt()
                        println("Progress: " + p + " for torrent name: " + a.torrentName())
                        println(s.stats().totalDownload())
                    }
                    AlertType.TORRENT_FINISHED -> {
                        println("Torrent finished")
                        signal.countDown()
                    }
                }
            }
        })

        s.start()

        val ti = TorrentInfo(torrentFile)
        s.download(ti, torrentFile.parentFile)

        signal.await()

        s.stop()
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