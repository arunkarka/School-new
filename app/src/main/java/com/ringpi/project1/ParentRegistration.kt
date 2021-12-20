package com.ringpi.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ParentRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_registration)
       val refirstname=findViewById<EditText>(R.id.ParentFirstName)
        val relastname=findViewById<EditText>(R.id.ParentLastName)
        val reph_number=findViewById<EditText>(R.id.ParentContNum)
        val reemail=findViewById<EditText>(R.id.ParentEmail)
        val repassword=findViewById<EditText>(R.id.ParentPassword)
        val registerparent =findViewById<Button>(R.id.ParentRegDone)

        registerparent.setOnClickListener{
            val firstname=refirstname.text.toString()
            val lastname=relastname.text.toString()
            val phone=reph_number.text.toString()
            val email=reemail.text.toString()
            val password=repassword.text.toString()


            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()


                    val usermap = HashMap<String, Any>()
                    usermap.put("first-name", firstname)
                    usermap.put("last_name", lastname)
                    usermap.put("mobile", phone)
                    usermap.put("designation", "Parent")




                    mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(email).set(usermap).addOnSuccessListener {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this,LoginActivity::class.java)
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