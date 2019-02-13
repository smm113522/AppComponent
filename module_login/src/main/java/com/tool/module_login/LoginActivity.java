package com.tool.module_login;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.component.base.BaseActivity;

@Route(path = "/login/activity")
public class LoginActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }
}
