package com.replugin.host;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.code.base.BaseActivity;
import com.code.utils.RouterPath;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;

/**
 * 插件化部分测试。。可以从后台进行添加
 */

@Route(path = RouterPath.path_replugin_activity)
public class RepluginActivity extends BaseActivity implements View.OnClickListener {

    private Button mPushBt;
    private Button mJiazaiBt;
    private Button mPerformanceBt;

    @Override
    public void initView() {

        mPushBt = (Button) findViewById(R.id.bt_push);
        mPushBt.setOnClickListener(this);
        mJiazaiBt = (Button) findViewById(R.id.bt_jiazai);
        mJiazaiBt.setOnClickListener(this);
        mPerformanceBt = (Button) findViewById(R.id.bt_performance);
        mPerformanceBt.setOnClickListener(this);

        PluginInfo pi = RePlugin.getPluginInfo("replugin");
        if (pi != null) {
            // Fetch "New Version" plugin
            PluginInfo newPi = pi.getPendingUpdate();
            int v = pi.getVersion();

//            int v1 = pi.getParentInfo().getVersion();
            if (newPi != null) {

                new AlertDialog.Builder(RepluginActivity.this)
                        .setTitle("发现新版版" + pi.getFrameworkVersion())
                        .setCancelable(false)
                        .setMessage("需要重新启动")
                        .setNegativeButton(
                                "确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        //与正常页面跳转一样可传递序列化数据,在Launch页面内获得
                                        intent.putExtra("REBOOT", "reboot");
                                        startActivity(intent);

                                    }
                                }).show();

                Toast.makeText(getApplicationContext(), "" + newPi.toString(), Toast.LENGTH_SHORT).show();
            } else {
                // No update

                Toast.makeText(getApplicationContext(), "没有更新数据", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_push) {// TODO 19/08/27

            final ProgressDialog pd = ProgressDialog.show(RepluginActivity.this, "Installing...", "Please wait...", true, true);
            // FIXME: 仅用于安装流程演示 2017/7/24
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
//                    simulateInstallExternalPlugin();
                    // Fetch "Current Version" plugin.
                    PluginInfo pi = RePlugin.getPluginInfo("replugin");
                    if (pi != null) {
                        // Fetch "New Version" plugin

                        PluginInfo newPi = pi.getPendingUpdate();
                        if (newPi != null) {

                            new AlertDialog.Builder(RepluginActivity.this)
                                    .setTitle("发现新版版" + pi.getFrameworkVersion())
                                    .setCancelable(false)
                                    .setMessage("需要重新启动")
                                    .setNegativeButton(
                                            "确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    //与正常页面跳转一样可传递序列化数据,在Launch页面内获得
                                                    intent.putExtra("REBOOT", "reboot");
                                                    startActivity(intent);

                                                }
                                            }).show();

                            Toast.makeText(getApplicationContext(), "" + newPi.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            // No update
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("replugin",
                                    "com.kotlin.replugin.MainActivity"));
                            RePlugin.startActivity(RepluginActivity.this, intent, "replugin", "com.kotlin.replugin.MainActivity");
                            Toast.makeText(getApplicationContext(), "没有更新数据", Toast.LENGTH_SHORT).show();
                        }

                    }

                    pd.dismiss();
                }
            }, 1000);

        } else if (i == R.id.bt_jiazai) {// TODO 19/08/27
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("flutter_app",
                    "com.example.flutter_app.MainActivity"));
            RePlugin.startActivity(RepluginActivity.this, intent, "flutter_app", "com.example.flutter_app.MainActivity");


        } else if (i == R.id.bt_performance) {// TODO 19/08/27
            startActivity(new Intent(RepluginActivity.this, PluginFragmentActivity.class));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_replugin;
    }
}
