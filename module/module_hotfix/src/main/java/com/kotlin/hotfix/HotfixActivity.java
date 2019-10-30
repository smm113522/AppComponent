package com.kotlin.hotfix;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.code.base.BaseActivity;
import com.code.utils.RouterPath;

/**
 *  阿里巴巴 热修复技术
 *  https://www.bilibili.com/video/av32291420/?p=192
 *  https://github.com/alibaba/AndFix
 */

@Route(path = RouterPath.path_hotfix_activity)
public class HotfixActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_hotfix;
    }

    @Override
    public void initView() {
        TextView tv_hello_fix = findViewById(R.id.tv_hello_fix);
        tv_hello_fix.setText("测试");
        /// 下面是修复内容

        tv_hello_fix.setText("好好修复了");

    }
}
