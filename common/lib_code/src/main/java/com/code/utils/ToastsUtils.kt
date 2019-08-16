package com.code.utils

import android.app.Activity
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.code.R
import com.code.base.BaseApp

object ToastsUtils {

    @Synchronized
    fun showToast(text: String, toastBool: Boolean) {
        val toast = Toast.makeText(BaseApp.getInstance(), text, Toast.LENGTH_SHORT)
        val view = LinearLayoutCompat.inflate(BaseApp.getInstance(), R.layout.toast_view_center, null)

        val imageView = view.findViewById<ImageView>(R.id.imv_toast)
        if (toastBool) {
            imageView.setBackgroundResource(R.mipmap.sucess)
        } else {
            imageView.setBackgroundResource(R.mipmap.error_img)
        }

        val textView = view.findViewById<TextView>(R.id.itv_toast_txt)
        textView.setText(text)

        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    /**
     * 在UI线程运行弹出
     */
    fun showToastOnUiThread(ctx: Activity?, text: String) {
        ctx?.runOnUiThread { showToast(text, true) }
    }
}