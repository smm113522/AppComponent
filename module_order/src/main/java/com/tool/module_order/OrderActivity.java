package com.tool.module_order;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.style.base.BaseActivity;

@Route(path = "/order/activity")
public class OrderActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {

    }
}
