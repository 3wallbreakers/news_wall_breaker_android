package com.example.newswallbreaker

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        handleIntent()
    }


    fun handleIntent() {

        when {
            intent?.action == Intent.ACTION_SEND -> {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let {

                    val toast = Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT)
                    toast.show()

                    val urlString = "https://archive.ph/submit/?url=" + it
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setPackage("com.android.chrome")
                    try {
                        startActivity(intent)
                    } catch (ex: ActivityNotFoundException) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null)
                        startActivity(intent)
                    }
                }

            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }

    }
}