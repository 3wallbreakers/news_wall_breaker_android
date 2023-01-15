package com.example.newswallbreaker

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.webkit.WebSettings.FORCE_DARK_OFF
import android.webkit.WebSettings.FORCE_DARK_ON
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature

class BrowserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
        val mSettings = myWebView.getSettings()
        mSettings.setBuiltInZoomControls(true);

        //allow dark mode
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    WebSettingsCompat.setForceDark(myWebView.settings, WebSettingsCompat.FORCE_DARK_ON)
                }
                Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                    WebSettingsCompat.setForceDark(myWebView.settings, WebSettingsCompat.FORCE_DARK_OFF)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handleShareIntent()
    }

    private fun handleShareIntent() {
        if (intent?.action == Intent.ACTION_SEND) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                val urlString = "https://3wallbreakers.deta.dev/news_wall_breaker/?url=" + it
                val myWebView: WebView = findViewById(R.id.webview)
                myWebView.loadUrl(urlString)
            }
        }
    }


}