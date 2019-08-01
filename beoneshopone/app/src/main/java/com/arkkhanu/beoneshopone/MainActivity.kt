package com.arkkhanu.beoneshopone

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebView = findViewById<WebView>(R.id.webView)
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        val webSetting = mWebView!!.settings
        webSetting.javaScriptEnabled = true
        webSetting.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSetting.setAppCacheEnabled(true)
        webSetting.domStorageEnabled = true
        webSetting.useWideViewPort = true
        mWebView!!.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        mWebView!!.loadUrl("https://www.beoneshopone.com/")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }



    }

    override fun onStart() {
        super.onStart()
        checking()
    }

    fun checking(){
        if(isConnectedToNetwork()){
//            Toast.makeText(this,"Good Internet Access", Toast.LENGTH_SHORT).show()
        }else{
            startActivity(Intent(this,ConnectivityGone::class.java))
            finish()
        }
    }

    fun isConnectedToNetwork(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

}
