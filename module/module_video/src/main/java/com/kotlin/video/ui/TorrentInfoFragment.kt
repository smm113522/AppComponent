package com.kotlin.video.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.code.base.BaseNoModelFragment
import com.kotlin.code.utils.RouterPath
import com.kotlin.video.R
import com.kotlin.video.databinding.FragmentTorrentInfoBinding

@Route(path = RouterPath.path_torrent_info_fragment)
class TorrentInfoFragment: BaseNoModelFragment<FragmentTorrentInfoBinding>() {

    override fun onCreate(): Int = R.layout.fragment_torrent_info

    override fun initView() {

    }

    override fun initData() {

    }

}