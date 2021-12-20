package com.ringpi.project1

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.time.ExperimentalTime

class CheckInOutActivity : AppCompatActivity() {
    lateinit var detail:String
    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in_out)






        val user = FirebaseAuth.getInstance().currentUser
        val email: String = user?.email.toString()
        detail = email
        val  loading =findViewById<ProgressBar>(R.id.spin_kit1)
        loading.setVisibility(View.VISIBLE)

        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val n = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                //It will check for both wifi and cellular network


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





        val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val docRef: DocumentReference =
            mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(detail)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val currentclass = document.getString("current-class").toString()
                    if (currentclass == "none") {
                        loading.setVisibility(View.GONE)
                        val fatherView=CheckInFragment()
                        addFragment(fatherView,R.id.container)



                    }else{
                        loading.setVisibility(View.GONE)
                        val fatherView=CheckOutFragment()
                        addFragment(fatherView,R.id.container)
                       //replaceFragment(CheckOutFragment(),R.id.cout)
                    }

                }

            }

        }

    }
}

