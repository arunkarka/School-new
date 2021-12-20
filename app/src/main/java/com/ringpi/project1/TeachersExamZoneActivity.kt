package com.ringpi.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class TeachersExamZoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teachers_exam_zone)
        val examurl = findViewById<EditText>(R.id.examlink)
        val exam_desc = findViewById<EditText>(R.id.exam_desc)
        val subjectspinner = findViewById<Spinner>(R.id.spinner_subject)
        var db = FirebaseFirestore.getInstance()


        val submit =findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            val select=subjectspinner.selectedItem.toString()
            val documentReference: DocumentReference =
                db.collection("school-profiles/zphs-indira-nagar/exam-links").document(select)

            documentReference.update(
                "url",
                examurl.text.toString(),
                "description",
                exam_desc.text.toString()
            ).addOnSuccessListener {
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
            }.addOnFailureListener {
                Toast.makeText(this, "Error Please try again ", Toast.LENGTH_LONG).show();
            }


        }
    }
}