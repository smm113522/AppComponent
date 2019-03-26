package com.tool.module_me;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.style.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/me/activity")
public class MeActivity extends BaseActivity {

    @BindView(R2.id.tv_me)
    TextView tvMe;

    @Override
    public int getLayout() {
        return R.layout.activity_me;
    }

    @Override
    public void initView() {
        tvMe.setText("me 自定义文字");
    }

}
