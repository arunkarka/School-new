package com.ringpi.project1

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

   lateinit var emailEt: EditText
   lateinit var passwordEt: EditText

    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        fun YourTask(){
            val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
            val user = FirebaseAuth.getInstance().currentUser
            val email: String = user?.email.toString()
             var detail = email

            mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(detail).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null) {
                            val currentclass: String = document.getString("current-class").toString()
                            if (currentclass == "none") {
                                // startActivity(Intent(this, CheckInFragment::class.java))

                            }







                            //
                            val fff: String = "none"
                            val dmap = HashMap<String, Any>()
                            dmap.put("currentTeacher", fff)
                            dmap.put("subject", fff)


                            mFirestore.collection("school-profiles/zphs-indira-nagar/session-list")
                                .document(currentclass).update(dmap).addOnSuccessListener {


                                    val ddmap = HashMap<String, Any>()
                                    ddmap.put("current-class", fff)

                                    mFirestore.collection("school-profiles/zphs-indira-nagar/user-info")
                                        .document(detail).update(ddmap)
                                        .addOnCompleteListener {
                                            Toast.makeText(
                                                this,
                                                "Checked Out",
                                                Toast.LENGTH_LONG
                                            )
                                                .show()

                                            // val intent =
                                            //     Intent(this, HomeActivity::class.java)
                                            // startActivity(intent)
                                        }


                                }.addOnFailureListener {

                                    startActivity(
                                        Intent(
                                            this,
                                            ErrorActivity::class.java
                                        )
                                    )
                                    Toast.makeText(
                                        this,
                                        "error please try later",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }





                        }
                    }


                }
        }


        if (auth.currentUser != null) {



            val curent_time = Date(System.currentTimeMillis())
            val cal = Calendar.getInstance()
            cal.time = curent_time
            val hour = cal[Calendar.HOUR]
            val min = cal[Calendar.MINUTE]
            val sec = cal[Calendar.SECOND]

            val `when` = (( 19* 3600 + 35*60)
                    - (hour * 3600 + min * 60 + sec)).toLong()
// interval should be based on miliseconds so
            // interval should be based on miliseconds so
            val interval = (24 * 60 * 60 * 1000).toLong()

            Timer().scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    YourTask()
                }
            }, `when`,interval)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.email1)
        passwordEt = findViewById(R.id.password1)

        loginBtn = findViewById(R.id.login1)
        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            var email: String = emailEt.text.toString().trim()
            var password: String = passwordEt.text.toString()
            auth = FirebaseAuth.getInstance()


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

            if (auth.currentUser == null){
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please fill all the fields",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, OnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG)
                                    .show()
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                            }
                        })
                }
        }else{
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        val regText1 = findViewById<TextView>(R.id.signUpText)
        regText1.setOnClickListener{
            val intent1 = Intent(this, AccessCodeVerificationActivity::class.java)
            startActivity(intent1)
        }





    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}