package com.kotlin.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


/**
 * fragment 中间跳转 页面
 *
 *
 *  ARouter.getInstance().build("/native/activity")
 *       .withString("url","/goods/fragment").navigation();
 *
 */
@Route(path = "/other/activity")
class OthersActivity : AppCompatActivity() {


    @Autowired
    @JvmField
    var url: String? = null

    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.activity_other)

        var aRouter = ARouter.getInstance().build(url)

        bundle = intent.extras
        if (bundle != null) {
            aRouter.with(bundle)
        }

        val fragment: Fragment = aRouter.navigation() as Fragment

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()

    }

}