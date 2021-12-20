package com.ringpi.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class ResultDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_display)
        val name=findViewById<TextView>(R.id.name)
        val section=findViewById<TextView>(R.id.sec)
        val maths=findViewById<TextView>(R.id.maths_marks)
        val social=findViewById<TextView>(R.id.social_marks)
        val english=findViewById<TextView>(R.id.english_marks)
        val telugu=findViewById<TextView>(R.id.fl_marks)
        val hindi=findViewById<TextView>(R.id.sl_marks)
        val science=findViewById<TextView>(R.id.phy_marks)
        val total=findViewById<TextView>(R.id.total)

        var iii: Intent = getIntent()
        var coll : String? = iii.getStringExtra("coll")

        var ii: Intent = getIntent()
        var doc : String? = ii.getStringExtra("doc")

        var myDB1 = FirebaseFirestore.getInstance()

        val docRef2: DocumentReference =
            myDB1.collection(coll.toString()).document(doc.toString())
        docRef2.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {

                    var sname=document.getString("name").toString()
                    name.text = sname

                    var sec=document.getString("class").toString()
                    section.text=sec
                    var mathematics=document.getString("Maths").toString()
                    maths.text=mathematics

                    var sciences=document.getString("Science").toString()
                    science.text=sciences
                    var eng=document.getString("English").toString()
                    english.text=eng
                    var hindilang=document.getString("Hindi").toString()
                    hindi.text=hindilang
                    var telugulang=document.getString("Telugu").toString()
                    telugu.text=telugulang
                    var socialstudies=document.getString("Social").toString()
                    social.text=socialstudies
                    var finalgrade=document.getString("Total").toString()
                    total.text=finalgrade














                }
            }
        }



    }





}
