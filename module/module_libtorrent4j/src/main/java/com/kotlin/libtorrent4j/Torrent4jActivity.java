package com.kotlin.libtorrent4j;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kotlin.code.base.BaseNoModelActivity;
import com.kotlin.code.utils.LogUtils;
import com.kotlin.libtorrent4j.databinding.ActivityTorrent4jBinding;

import java.io.File;

@Route(path = "/4j_torrent/activity")
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

        final LoaderManager loaderManager = LoaderManager.getInstance(this);
        torrentFileLoaderCallbacks = new TorrentFileLoaderCallbacks();

        dataBinding.tbDemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaderManager.initLoader(1, null, torrentFileLoaderCallbacks);
//                loaderManager.restartLoader()
//                loaderManager.markForRedelivery();
            }
        });
    }

    @Override
    protected void initData() {

    }
    private TorrentFileLoaderCallbacks torrentFileLoaderCallbacks;

    public class TorrentFileLoaderCallbacks implements LoaderManager.LoaderCallbacks<File> {

        @NonNull
        @Override
        public Loader<File> onCreateLoader(int id, @Nullable Bundle bundle) {
            TorrentFileLoader torrentFileLoader = new TorrentFileLoader(Torrent4jActivity.this);
            torrentFileLoader.forceLoad();
            return torrentFileLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<File> loader, File file) {
            LogUtils.error("sucess");
        }

        @Override
        public void onLoaderReset(@NonNull Loader<File> loader) {

        }
    }

}
