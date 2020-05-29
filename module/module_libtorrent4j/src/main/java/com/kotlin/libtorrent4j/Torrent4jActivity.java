package com.kotlin.libtorrent4j;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kotlin.code.base.BaseNoModelActivity;
import com.kotlin.code.utils.RouterPath;
import com.kotlin.libtorrent4j.databinding.ActivityTorrent4jBinding;

@Route(path = RouterPath.path_4j_torrent_activity)
public class Torrent4jActivity extends BaseNoModelActivity<ActivityTorrent4jBinding> {

    @Override
    protected int onCreate() {
        return R.layout.activity_torrent_4j;
    }

    @Override
    protected void initView() {
        dataBinding.tbDemo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GetMagnet2.GetMagnet2();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        dataBinding.tbDemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
