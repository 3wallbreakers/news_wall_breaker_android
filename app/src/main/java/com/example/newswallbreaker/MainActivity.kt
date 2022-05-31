package com.example.newswallbreaker

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myWebView: WebView = findViewById(R.id.webview)
        val settings: WebSettings = myWebView.getSettings()
        settings.domStorageEnabled = true

        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
        myWebView.getSettings().setBuiltInZoomControls(true);

        val urlString = "https://3wallbreakers.notion.site/News-Wall-Breaker-on-Android-91e63abff59146b9aec89299f0504e41"
        myWebView.loadUrl(urlString)
    }

}