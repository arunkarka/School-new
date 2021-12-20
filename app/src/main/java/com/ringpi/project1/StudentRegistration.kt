package com.ringpi.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StudentRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)
        val refirstname=findViewById<EditText>(R.id.studentFirstName)
        val relastname=findViewById<EditText>(R.id.studentLastName)
        val rollnumber=findViewById<EditText>(R.id.studentRollNum)
        val reclass=findViewById<Spinner>(R.id.class_spinner)
        val resection=findViewById<Spinner>(R.id.spinner_section)
        val reemail=findViewById<EditText>(R.id.studentEmail)
        val repassword=findViewById<EditText>(R.id.studentPassword)
        val registerstudent =findViewById<Button>(R.id.studentRegDone)

        registerstudent.setOnClickListener{
            val firstname=refirstname.text.toString()
            val lastname=relastname.text.toString()
            val rollNo=rollnumber.text.toString()
            val email=reemail.text.toString()
            val password=repassword.text.toString()
            val class1 =reclass.selectedItem.toString()
            val section = resection.selectedItem.toString()

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()


                    val usermap = HashMap<String, Any>()
                    usermap.put("first-name", firstname)
                    usermap.put("last_name", lastname)
                    usermap.put("class", class1)
                    usermap.put("section",section)
                    usermap.put("rollNo",rollNo)
                    usermap.put("designation", "Student")




                    mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(email).set(usermap).addOnSuccessListener {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)


                    }.addOnFailureListener {
                        Toast.makeText(this, "error please try later", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener {


                }



        }

    }
}


