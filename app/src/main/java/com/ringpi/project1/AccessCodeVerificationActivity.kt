package com.ringpi.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class AccessCodeVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_code_verification)
        val accesscode=findViewById<EditText>(R.id.code)
        val next =findViewById<Button>(R.id.accessDone)

next.setOnClickListener {
    val len = 8
    if (accesscode.text.length < len) {
        accesscode.error = "Incorrect Access Code"
    }
    if (accesscode.text.isEmpty()) {
        accesscode.error = "Enter the Access Code"
    }


    var myDB = FirebaseFirestore.getInstance()

    val docRef1: DocumentReference =
        myDB.collection("school-profiles/zphs-indira-nagar/about").document("accesscode")
    docRef1.get().addOnCompleteListener {
            task ->
        if (task.isSuccessful) {
            val document = task.result
            if (document != null) {
                val parent: String? = document.getString("parentcode")
                val teacher: String? = document.getString("teachercode")
                val student: String? = document.getString("studentcode")
                val check=accesscode.text.toString()

                if (check == parent) {
                    val intent = Intent(this, ParentRegistration::class.java)
                    startActivity(intent)
                }
                if (check == teacher) {
                    val intent = Intent(this, TeacherRegistration::class.java)
                    startActivity(intent)
                }
                if (check== student) {
                    val intent = Intent(this, StudentRegistration::class.java)
                    startActivity(intent)
                }



            } else {
                Log.d("LOGGER", "No such document")
            }
        } else {
            Log.d("LOGGER", "get failed with ", task.exception)
        }
    }
}



      val login = findViewById<TextView>(R.id.textview6)
        login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

}