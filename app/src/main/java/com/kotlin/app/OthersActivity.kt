package com.kotlin.app

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.app.databinding.ActivityOtherBinding


/**
 * kotlin 基础
 */

class OthersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityOtherBinding = DataBindingUtil.setContentView<ActivityOtherBinding>(this,R.layout.activity_other)


    }

}