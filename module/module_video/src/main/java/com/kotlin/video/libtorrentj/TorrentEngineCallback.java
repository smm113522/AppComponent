/*
 * Copyright (C) 2016-2018 Yaroslav Pronin <proninyaroslav@mail.ru>
 *
 * This file is part of LibreTorrent.
 *
 * LibreTorrent is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LibreTorrent is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LibreTorrent.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kotlin.video.libtorrentj;

public interface TorrentEngineCallback
{
    void onTorrentAdded(String id);

    void onTorrentStateChanged(String id);

    void onTorrentFinished(String id);

    void onTorrentRemoved(String id);

    void onTorrentPaused(String id);

    void onTorrentResumed(String id);

    void onEngineStarted();

    void onTorrentMoved(String id, boolean success);

    void onIpFilterParsed(boolean success);

    void onMagnetLoaded(String hash, byte[] bencode);

    void onTorrentMetadataLoaded(String id, Exception err);

    void onRestoreSessionError(String id);

    void onTorrentError(String id, String errorMsg);

    void onSessionError(String errorMsg);

    void onNatError(String errorMsg);
}
