package com.lizij.jetpackdemo.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.lizij.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val url = intent.getStringExtra("url")
        if (!TextUtils.isEmpty(url)) {
            mWebview.webViewClient = WebViewClient()
            mWebview.webChromeClient = WebChromeClient()
            mWebview.loadUrl(url)
        }
    }

    override fun onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
