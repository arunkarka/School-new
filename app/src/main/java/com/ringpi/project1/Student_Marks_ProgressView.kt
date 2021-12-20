package com.ringpi.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class Student_Marks_ProgressView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student__marks__progress_view)
        val fa1=findViewById<Button>(R.id.Button_for_f1)
        val fa2=findViewById<Button>(R.id.Button_for_f2)
        val fa3=findViewById<Button>(R.id.Button_for_f3)
        val fa4=findViewById<Button>(R.id.Button_for_f4)
        val sa1=findViewById<Button>(R.id.Button_for_s1)
        val sa2=findViewById<Button>(R.id.Button_for_s2)


        val ccsa1=findViewById<Button>(R.id.Button_for_cs2)
        val ccsa2=findViewById<Button>(R.id.Button_for_cc4)

        fa1.isEnabled=false
        fa2.isEnabled=false
        fa3.isEnabled=false
        fa4.isEnabled=false
        sa1.isEnabled=false
        sa2.isEnabled=false
        ccsa1.isEnabled=false
        ccsa2.isEnabled=false
        var myDB1 = FirebaseFirestore.getInstance()

        val docRef2: DocumentReference =
            myDB1.collection("school-profiles/zphs-indira-nagar/exam-status-info").document("examstatus")
        docRef2.get().addOnCompleteListener {
                task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val fa1status: Boolean? = document.getBoolean("fa1")
                    val fa2status: Boolean? = document.getBoolean("fa2")
                    val fa3status: Boolean? = document.getBoolean("fa3")
                    val fa4status: Boolean? = document.getBoolean("fa4")
                    val sa1status: Boolean? = document.getBoolean("sa1")
                    val sa2status: Boolean? = document.getBoolean("sa2")




                    if (fa1status==true) {
                        fa1.isEnabled=true
                    }
                    if (fa2status==true)
                    {
                        fa2.isEnabled=true
                    }
                    if (fa3status==true)
                    {
                        fa3.isEnabled=true
                    }
                    if (fa4status==true)
                    {
                        fa4.isEnabled=true
                    }
                    if (sa1status==true)
                    {
                        sa1.isEnabled=true
                        ccsa1.isEnabled=true
                    }
                    if (sa2status==true)
                    {
                        sa2.isEnabled=true
                        ccsa2.isEnabled=true
                    }




                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }
        var iii: Intent = getIntent()
        var info : String = iii.getStringExtra("sid").toString()



        var myDB = FirebaseFirestore.getInstance()

        val docRef1: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/sids").document(info)
        docRef1.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val classname=document.getString("class").toString()
                    fa1.setOnClickListener {
                        val exam="fa1"
                        result(info,exam,classname)

                    }
                    fa2.setOnClickListener {
                        val exam="fa2"
                        result(info,exam,classname)
                    }
                    fa3.setOnClickListener {
                        val exam="fa3"
                        result(info,exam,classname)
                    }
                    fa4.setOnClickListener {
                        val exam="fa4"
                        result(info,exam,classname)
                    }
                    sa1.setOnClickListener {
                        val exam="sa1"
                        result(info,exam,classname)
                    }
                    sa2.setOnClickListener {
                        val exam="sa2"
                        result(info,exam,classname)
                    }
                    ccsa1.setOnClickListener {
                        val exam="ccsa1"
                        result(info,exam,classname)
                    }
                    ccsa2.setOnClickListener {
                        val exam="ccsa2"
                        result(info,exam,classname)
                    }




                }
            }
        }










    }

    private fun result(info: String, exam: String, classname: String) {

        val collectionpath="school-profiles/zphs-indira-nagar/results/$exam/$classname"
        val doc=info
        val iii=Intent(this,ResultDisplayActivity::class.java)
        iii.putExtra("coll",collectionpath)
        iii.putExtra("doc",doc)
        startActivity(iii)



    }

    }
