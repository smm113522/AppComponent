package com.kotlin.player;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dueeeke.videocontroller.StandardVideoController;
import com.kotlin.code.base.BaseNoModelActivity;
import com.kotlin.player.databinding.ActivityPlayerNormalBinding;



@Route(path = "/player/activity")
public class PlayerActivity extends BaseNoModelActivity<ActivityPlayerNormalBinding> {

    //        PlayAction("http://dlhls.cdn.zhanqi.tv/zqlive/35180_KUDhx.m3u8")
//        PlayAction("http://220.161.87.62:8800/hls/0/index.m3u8")//直播
//        PlayAction("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4")

    @Autowired
    public String url;

    @Override
    protected int onCreate() {
        return R.layout.activity_player_normal;
    }

    @Override
    protected void initView() {

        ARouter.getInstance().inject(this);
        dataBinding.player.setUrl(url); //设置视频地址

        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent("标题", false);
        dataBinding.player.setVideoController(controller); //设置控制器

        dataBinding.player.start(); //开始播放，不调用则不自动播放

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataBinding.player.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataBinding.player.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBinding.player.release();
    }

    @Override
    protected void initData() {

    }
}
