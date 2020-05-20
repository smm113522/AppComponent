package com.kotlin.html

import android.graphics.Bitmap
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.code.base.BaseActivity
import com.code.utils.RouterPath
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebParentLayout
import com.just.agentweb.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*


@Route(path = RouterPath.path_web_activity)
class WebActivity : BaseActivity() {

    @Autowired
    @JvmField var url: String? = null
    @Autowired
    @JvmField var title: String? = null

    protected var mAgentWeb: AgentWeb? = null

    override fun getLayoutId(): Int = R.layout.activity_web

    override fun initView() {
        ARouter.getInstance().inject(this);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(content,LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .interceptUnkownUrl()
                .setWebViewClient(mWebViewClient)
                .setWebChromeClient(mWebChromeClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page,-1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)

                .createAgentWeb().ready()
                .go(url)
    }

    var mWebViewClient = object : WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)

        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }

    }
    var mWebChromeClient = object : WebChromeClient(){

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)

        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(mAgentWeb!!.handleKeyEvent(keyCode,event)){
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onResume() {
        mAgentWeb!!.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onPause() {
        mAgentWeb!!.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mAgentWeb!!.webLifeCycle.onDestroy()
        super.onDestroy()
    }


}