package com.code.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code.R;
import com.code.base.BaseApp;


public class ToastUtils {

    public static void showToast(String text) {
        showToast(BaseApp.Companion.getInstance(), text);
    }

    public static void showToast(Context ctx, String text) {
        showToast(ctx, Toast.LENGTH_SHORT, text);
    }

    public static void showToast(Context ctx, int duration, String text) {
        Toast toast = Toast.makeText(ctx, text, duration);
        View mNextView = toast.getView();
        if (mNextView != null)
            toast.show();
    }

    public static synchronized void showToast(String text, boolean toastBool) {
        Toast toast = Toast.makeText(BaseApp.Companion.getInstance(), text, Toast.LENGTH_SHORT);
        View view = LinearLayoutCompat.inflate(BaseApp.Companion.getInstance(), R.layout.toast_view_center, null);

        ImageView imageView = view.findViewById(R.id.imv_toast);
        if (toastBool) {
            imageView.setBackgroundResource(R.mipmap.sucess);
        } else {
            imageView.setBackgroundResource(R.mipmap.error_img);
        }

        TextView textView = view.findViewById(R.id.itv_toast_txt);
        textView.setText(text);

        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 在UI线程运行弹出
     */
    public static void showToastOnUiThread(final Activity ctx, final String text) {
        if (ctx != null) {
            ctx.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    showToast(ctx, text);
                }
            });
        }
    }
}
