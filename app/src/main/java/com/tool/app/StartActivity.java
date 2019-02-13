package com.tool.app;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tool.component.base.BaseActivity;
import com.tool.component.route.MyRoute;

import butterknife.BindView;
import butterknife.OnClick;


public class StartActivity extends BaseActivity {

    @BindView(R.id.bt_main)
    Button btMain;
    @BindView(R.id.bt_ceshi)
    Button btCeshi;

    @Override
    public int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.bt_main, R.id.bt_ceshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_main:
                Log.d("ddd", "main");
                // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build(MyRoute.meActivity).navigation();
                break;
            case R.id.bt_ceshi:
                Log.d("ddd", "ceshi");
                ARouter.getInstance().build(MyRoute.mainActivity).navigation(this, new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d("ARouter", "找到了");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d("ARouter", "找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d("ARouter", "跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d("ARouter", "被拦截了");
                    }
                });
                break;
        }
    }
}
