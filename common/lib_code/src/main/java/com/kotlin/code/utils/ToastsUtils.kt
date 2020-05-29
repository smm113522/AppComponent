package com.kotlin.code.utils

import android.app.Activity
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.kotlin.code.R
import com.kotlin.code.base.BaseApp

object ToastsUtils {
    var toast:Toast? = null
    @Synchronized
    fun showToast(text: String, toastBool: Boolean) {
        if (toast == null) {
            toast = Toast.makeText(BaseApp.getInstance(), text, Toast.LENGTH_SHORT)
        }
//        toast?.cancel()

        val view = LinearLayoutCompat.inflate(BaseApp.getInstance(), R.layout.toast_view_center, null)

        val imageView = view.findViewById<ImageView>(R.id.imv_toast)
        if (toastBool) {
            imageView.setBackgroundResource(R.mipmap.sucess)
        } else {
            imageView.setBackgroundResource(R.mipmap.error_img)
        }

        val textView = view.findViewById<TextView>(R.id.itv_toast_txt)
        textView.setText(text)

        toast?.view = view
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.show()
    }

    /**
     * 在UI线程运行弹出
     */
    fun showToastOnUiThread(ctx: Activity?, text: String) {
        ctx?.runOnUiThread { showToast(text, true) }
    }
}