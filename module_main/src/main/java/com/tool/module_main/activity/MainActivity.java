package com.tool.module_main.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.component.base.BaseActivity;
import com.tool.module_main.R;
import com.tool.module_main.R2;

import butterknife.BindView;

@Route(path = "/main/activity")
public class MainActivity extends BaseActivity {

    @BindView(R2.id.main)
    TextView main;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        main.setText("首页添加新的文字");
    }

}
