/*
 * Copyright (C) 2019 MrBinWin (https://github.com/MrBinWin/),
 *                         Dmitry Kuznetsov <mrbinwin@gmail.com>
 *
 * This file is part of MyMovies.
 *
 * MyMovies is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyMovies is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MyMovies.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kotlin.libtorrent4j;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;

/*
 * Download torrent metadata file by torrent id
 *
 */
public class TorrentFileLoader extends AsyncTaskLoader<File> {

    private static final String POSTFIX = ".torrent";

    public TorrentFileLoader(@NonNull Context context) {
        super(context);
    }


    @Nullable
    @Override
    public File loadInBackground() {
        File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
        File file = new File(sdCardDir, "test1.torrent");
        return file;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

    }
}
