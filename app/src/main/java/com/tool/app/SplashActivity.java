package com.tool.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tool.component.route.MyRoute;
import com.tool.style.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = "/splash/activity")
public class SplashActivity extends BaseActivity {

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    ARouter.getInstance().build(MyRoute.mainActivity).navigation();
                    finish();
                    break;
            }
        }

    };

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.bt_ignore)
    Button btIgnore;
    @BindView(R.id.tv_logo)
    TextView tvLogo;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    handler.postAtTime(runnable,3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessageAtTime(1, 3000);
        }
    };

    @OnClick(R.id.bt_ignore)
    public void onViewClicked() {
        handler.removeCallbacks(runnable);
        handler.sendEmptyMessage(1);
    }
}
