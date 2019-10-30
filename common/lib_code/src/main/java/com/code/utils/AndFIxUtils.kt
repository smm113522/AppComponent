package com.code.utils

import android.content.Context
import android.os.Environment
import com.alipay.euler.andfix.patch.PatchManager
import java.io.File

object AndFIxUtils {

    fun init(context:Context){

        val patchManager = PatchManager(context)
        patchManager.init("1.0")//current version

        patchManager.loadPatch()

        val file = File(Environment.getExternalStorageDirectory(), "kotlin.apatch")

        if (file.exists()) {
            patchManager.addPatch(file.absolutePath)
        }

    }
}