package com.example.newswallbreaker

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
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

//        val unencodedHtml = """<html><body>Open a page with paywall in a browser > Select ... > Share... > Choose NewsWallBreaker </body></html>""";
//        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
//        myWebView.loadData(encodedHtml, "text/html", "base64")

//        myWebView.loadUrl("https://www.google.com")
//        myWebView.loadUrl("https://archive.ph/submit/?url=https://www.wsj.com/articles/silicon-valley-investors-give-startups-survival-advice-for-downturn-11653822000")

    }

    override fun onResume() {
        super.onResume()
        handleIntent()
    }


    fun handleIntent() {

        when {
            intent?.action == Intent.ACTION_SEND -> {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {

//                    val toast = Toast.makeText(applicationContext, "v2: " + it, Toast.LENGTH_SHORT)
//                    toast.show()

                    val urlString = "https://archive.ph/submit/?url=" + it
                    val myWebView: WebView = findViewById(R.id.webview)
                    myWebView.loadUrl(urlString)

//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    intent.setPackage("com.android.chrome")
//                    try {
//                        startActivity(intent)
//                    } catch (ex: ActivityNotFoundException) {
//                        // Chrome browser presumably not installed so allow user to choose instead
//                        intent.setPackage(null)
//                        startActivity(intent)
//                    }
                }

            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }

    }
}