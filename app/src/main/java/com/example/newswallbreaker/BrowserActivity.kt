package com.example.newswallbreaker

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

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
        myWebView.getSettings().setBuiltInZoomControls(true);
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