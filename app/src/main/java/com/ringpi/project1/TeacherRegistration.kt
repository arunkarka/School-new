package com.ringpi.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TeacherRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_registration)
        val refirstname=findViewById<EditText>(R.id.teacherFirstName)
        val relastname=findViewById<EditText>(R.id.teacherLastName)
        val reph_number=findViewById<EditText>(R.id.teacherNum)
        val resubject=findViewById<Spinner>(R.id.spinner_subject)
        val reemail=findViewById<EditText>(R.id.teacherEmail)
        val repassword=findViewById<EditText>(R.id.teacherPassword)
        val register =findViewById<Button>(R.id.teacherRegDone)


        val rootRef = FirebaseFirestore.getInstance()
        val subjectsRef = rootRef.collection("school-profiles/zphs-indira-nagar/general-info")
        // val spinner = findViewById<View>(R.id.spinner1) as Spinner
        val subjects: MutableList<String?> = ArrayList()
        val adapter = ArrayAdapter(
            applicationContext, android.R.layout.simple_spinner_item, subjects
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        resubject.adapter = adapter
        subjectsRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val subject = document.getString("subject")
                    subjects.add(subject)
                }
                adapter.notifyDataSetChanged()
            }
        }



        register.setOnClickListener {
            val firstname=refirstname.text.toString()
            val lastname=relastname.text.toString()
            val phone=reph_number.text.toString()
            val email=reemail.text.toString()
            val password=repassword.text.toString()
            val subject=resubject.selectedItem.toString()

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()


                    val usermap = HashMap<String, Any>()
                    usermap.put("name", firstname)
                    usermap.put("last_name", lastname)
                    usermap.put("current-class", "none")
                    usermap.put("subject", subject)
                    usermap.put("mobile", phone)
                    usermap.put("designation", "Teacher")




                    mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(email).set(usermap).addOnSuccessListener {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent =Intent(this,LoginActivity::class.java)
                        startActivity(intent)


                    }.addOnFailureListener {
                        Toast.makeText(this, "error please try later", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener {

                    it.printStackTrace()
                }



        }


    }
}