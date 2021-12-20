package com.ringpi.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class ContactUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        val school_name=findViewById<TextView>(R.id.school_title)
        val sch_address=findViewById<TextView>(R.id.school_address)
        val hmname=findViewById<TextView>(R.id.hm_name)
        val hmnumber=findViewById<TextView>(R.id.principal_number)
        val hm_mail=findViewById<TextView>(R.id.principal_email)
        val officeheadname=findViewById<TextView>(R.id.oh_name)
        val officeheadnumber=findViewById<TextView>(R.id.oh_number)
        val office_mail=findViewById<TextView>(R.id.oh_email)
        val  loading =findViewById<ProgressBar>(R.id.spin_kit)
        var myDB = FirebaseFirestore.getInstance()
        loading.setVisibility(View.VISIBLE)
        val docRef1: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/about").document("info")
        docRef1.get().addOnCompleteListener {
                task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val school: String? = document.getString("school-name")
                    val address: String? = document.getString("address")
                    val headmaster_name: String? = document.getString("hm-name")
                    val headmaster_ph: String? = document.getString("hm-mobileno")
                    val hm_email=document.getString("hm-email")
                    val officehead_name: String? = document.getString("officeheadname")
                    val officehead_ph: String? = document.getString("officehead-mobileno")
                    val off_email=document.getString("officehead-email")
                    hm_mail.text=hm_email
                    office_mail.text=off_email
                    school_name.text=school
                    sch_address.text=address
                    hmname.text=headmaster_name
                    hmnumber.text=headmaster_ph
                    officeheadname.text=officehead_name
                    officeheadnumber.text=officehead_ph
                    loading.setVisibility(View.GONE)


                } else {
                    Log.d("LOGGER", "No such document")

                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }


    }
}