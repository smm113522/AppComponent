package com.tool.module_main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tool.component.base.BaseActivity;
import com.tool.component.route.MyRoute;
import com.tool.module_main.OneFragment;
import com.tool.module_main.R;
import com.tool.module_main.R2;
import com.tool.module_main.view.BottomTabBar;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/main/activity")
public class MainActivity extends BaseActivity {

    @BindView(R2.id.bottom_bar)
    BottomTabBar mBottomBar;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Fragment me = (Fragment) ARouter.getInstance().build(MyRoute.meFragment).navigation();
        Fragment order = (Fragment) ARouter.getInstance().build(MyRoute.orderFragment).navigation();
        mBottomBar.init(getSupportFragmentManager(), 750, 1334)
//                .setImgSize(50, 50)
//                .setFontSize(28)
//                .setTabPadding(10, 6, 4)
                .setChangeColor(Color.parseColor("#FF1493"),Color.parseColor("#282828"))
                .addTabItem("首页", R.mipmap.ic_common_tab_index_select, R.mipmap.ic_common_tab_index_unselect, OneFragment.class)
                .addTabItem("订单", R.mipmap.ic_common_tab_index_select, R.mipmap.ic_common_tab_index_unselect, order.getClass())
                .addTabItem("我的", R.mipmap.ic_common_tab_index_select, R.mipmap.ic_common_tab_index_unselect, me.getClass())
                .isShowDivider(false)
                .setDividerColor(Color.parseColor("#FFFFFFFF"))
                .setTabBarBackgroundColor(Color.parseColor("#FFB6C1"))
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        if (position == 1)
                            mBottomBar.setSpot(1, false);
                    }
                })
                .setSpot(1, true)
                .setSpot(2, true);
    }

}
