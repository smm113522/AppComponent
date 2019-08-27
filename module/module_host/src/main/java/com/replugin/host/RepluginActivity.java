package com.replugin.host;

import android.content.ComponentName;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.code.base.BaseActivity;
import com.qihoo360.replugin.RePlugin;

@Route(path = "/app/replugin")
public class RepluginActivity extends BaseActivity {



    @Override
    public int getLayout() {
        return R.layout.activity_replugin;
    }

    @Override
    public int getViewDataLayout() {
        return 0;
    }

    @Override
    public void initView() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("replugin",
                "com.kotlin.replugin.MainActivity"));
        RePlugin.startActivity(RepluginActivity.this, intent, "replugin", "com.kotlin.replugin.MainActivity");
    }
}
