package com.kotlin.video.libtorrentj

import android.os.Environment
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.base.BaseNoModelActivity
import com.kotlin.code.utils.AssetFile
import com.kotlin.code.utils.RouterPath
import com.kotlin.video.R
import com.kotlin.video.databinding.ActivityToorentJBinding
import com.kotlin.video.libtorrentj.bean.TorrentMetaInfo
import java.io.File

@Route(path = RouterPath.path_4jTorrent_activity)
class TorrentjActivity : BaseNoModelActivity<ActivityToorentJBinding>() {


    override fun onCreate(): Int {
        return R.layout.activity_toorent_j
    }

    override fun initView() {
        dataBinding.btCopy.setOnClickListener {

            var sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            var file = File(sdCardDir,"test1.torrent")

            AssetFile(this.context).fromAsset("test1.torrent").copyAssetsFileToAppFiles(file)
            var path = file.absolutePath
            var torrentMetaInfo = TorrentMetaInfo(file.absolutePath)
            println(torrentMetaInfo)


        }
        dataBinding.btDownload.setOnClickListener {

        }
    }

    override fun initData() {

    }
}