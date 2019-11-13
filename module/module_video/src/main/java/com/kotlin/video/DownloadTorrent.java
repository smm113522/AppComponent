//package com.kotlin.video;
//
//import com.frostwire.jlibtorrent.AlertListener;
//import com.frostwire.jlibtorrent.LibTorrent;
//import com.frostwire.jlibtorrent.SessionManager;
//import com.frostwire.jlibtorrent.TorrentInfo;
//import com.frostwire.jlibtorrent.alerts.AddTorrentAlert;
//import com.frostwire.jlibtorrent.alerts.Alert;
//import com.frostwire.jlibtorrent.alerts.AlertType;
//import com.frostwire.jlibtorrent.alerts.BlockFinishedAlert;
//
//import java.io.File;
//import java.util.concurrent.CountDownLatch;
//
///**
// * @author gubatron
// * @author aldenml
// */
//public final class DownloadTorrent {
//
//    public static void begin(String path) throws Throwable {
//
//        // comment this line for a real application
////        String[] args = new String[]{"/Users/aldenml/Downloads/Honey_Larochelle_Hijack_FrostClick_FrostWire_MP3_May_06_2016.torrent"};
//
//        File torrentFile = new File(path);
//
//        System.out.println("Using libtorrent version: " + LibTorrent.version());
//
//        final SessionManager s = new SessionManager();
//
//        final CountDownLatch signal = new CountDownLatch(1);
//
//        s.addListener(new AlertListener() {
//            @Override
//            public int[] types() {
//                return null;
//            }
//
//            @Override
//            public void alert(Alert<?> alert) {
//                AlertType type = alert.type();
//                System.out.println("type =" + type.toString());
//                switch (type) {
//                    case ADD_TORRENT:
//                        System.out.println("Torrent added");
//                        ((AddTorrentAlert) alert).handle().resume();
////                        ((AddTorrentAlert) alert).handle().queuePositionDown();
//                        break;
//                    case BLOCK_FINISHED:
//                        BlockFinishedAlert a = (BlockFinishedAlert) alert;
//                        int p = (int) (a.handle().status().progress() * 100);
//                        System.out.println("Progress: " + p + " for torrent name: " + a.torrentName());
//                        System.out.println(s.stats().totalDownload());
//                        break;
//                    case TORRENT_FINISHED:
//                        System.out.println("Torrent finished");
////                        signal.countDown();
//                        break;
//                    case LISTEN_SUCCEEDED:
//                        System.out.println("Torrent LISTEN_SUCCEEDED");
////                        signal.countDown();
//                        break;
//                }
//            }
//        });
//
//        s.start();
//
//        TorrentInfo ti = new TorrentInfo(torrentFile);
//        System.out.println(ti.toString());
//        s.download(ti, torrentFile.getParentFile());
//        s.start();
//        signal.await();
//
////        s.stop();
//
//    }
//}
