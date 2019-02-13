package com.tool.module_me;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.component.base.BaseActivity;

@Route(path = "/me/activity")
public class MeActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_me;
    }

    @Override
    public void initView() {

    }
}
