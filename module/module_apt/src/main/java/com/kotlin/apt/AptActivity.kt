package com.kotlin.apt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.code.utils.RouterPath

class AptActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apt)

    }

    @MyRoute(paths = RouterPath.path_pdf_activity)
    fun ButtonCS(view : View){



    }


}