package com.tool.app;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.component.base.BaseActivity;


@Route(path = "/splash/activity")
public class SplashActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }
}
