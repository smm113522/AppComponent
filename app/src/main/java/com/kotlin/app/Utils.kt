package com.kotlin.app

import android.widget.Toast

object Utils {

    fun tomast(text: String) {
        Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT).show()
    }
}