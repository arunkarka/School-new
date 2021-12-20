package com.ringpi.project1.ui.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.firebase.firestore.FirebaseFirestore
import com.ringpi.project1.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var myDB = FirebaseFirestore.getInstance()


        var iii: Intent = intent
                    var info : String? = iii.getStringExtra("url")

                    val myWebView: WebView = findViewById(R.id.webviewb)
                    val webSettings: WebSettings = myWebView.getSettings()
                    webSettings.javaScriptEnabled = true
                    myWebView.webViewClient = WebViewClient()
                    myWebView.loadUrl(info.toString())


          }
    }
