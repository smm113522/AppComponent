package com.tool.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tool.component.base.BaseActivity;
import com.tool.component.route.MyRoute;
import com.tool.component.utils.PermissionUtils;

import java.util.Arrays;

import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.OnClick;


public class StartActivity extends BaseActivity {

    @BindView(R.id.bt_main)
    Button btMain;
    @BindView(R.id.bt_ceshi)
    Button btCeshi;

    private final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private final int REQUEST_CODE_PERMISSIONS=2;
    @Override
    public int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void initView() {
        PermissionUtils.checkMorePermissions(getActivity(), PERMISSIONS, new PermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
                Log.d("baidu","tongguo-------");
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {
                showExplainDialog(permission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.requestMorePermissions(getActivity(), PERMISSIONS, REQUEST_CODE_PERMISSIONS);
                    }
                });
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                PermissionUtils.requestMorePermissions(getActivity(), PERMISSIONS, REQUEST_CODE_PERMISSIONS);
            }
        });
    }
    /**
     * 解释权限的dialog
     *
     */
    private void showExplainDialog(String[] permission, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(getActivity())
                .setTitle("申请权限")
                .setMessage("我们需要" + Arrays.toString(permission)+"权限")
                .setPositiveButton("确定", onClickListener)
                .show();
    }

    @OnClick({R.id.bt_main, R.id.bt_ceshi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_main:
                Log.d("ddd", "main");
                // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build(MyRoute.splash).navigation();
                break;
            case R.id.bt_ceshi:
                Log.d("ddd", "ceshi");
                ARouter.getInstance().build(MyRoute.mainActivity).navigation(this, new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d("ARouter", "找到了");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d("ARouter", "找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d("ARouter", "跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d("ARouter", "被拦截了");
                    }
                });
                break;
        }
    }
}
