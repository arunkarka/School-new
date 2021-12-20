package com.ringpi.project1

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        val btn=findViewById<Button>(R.id.retry)
        btn.setOnClickListener {
            val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val n = cm.activeNetwork
                if (n != null) {
                    val nc = cm.getNetworkCapabilities(n)
                    //It will check for both wifi and cellular network

                    startActivity(Intent(this, CheckInOutActivity::class.java))

                }
                else {
                    Toast.makeText(
                        this,
                        "Connection Lost",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this, ErrorActivity::class.java))


                }

            }
        }
    }
}