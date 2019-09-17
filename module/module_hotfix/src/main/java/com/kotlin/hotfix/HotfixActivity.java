package com.kotlin.hotfix;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.code.base.BaseActivity;
import com.code.utils.RouterPath;

@Route(path = RouterPath.path_hotfix_activity)
public class HotfixActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_hotfix;
    }

    @Override
    public void initView() {

    }
}
