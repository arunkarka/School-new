package com.ringpi.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class CheckInActivity : AppCompatActivity() {
    lateinit var dell: String
    private lateinit var mExtendFab: ExtendedFloatingActionButton

    lateinit var detail: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        val spinner = findViewById<Spinner>(R.id.spinner1)






        mExtendFab = findViewById(R.id.extended_fab1)
        mExtendFab.setOnClickListener { view ->
            val class_details = spinner.selectedItem.toString()

            MaterialAlertDialogBuilder(this)
                .setTitle(resources.getString(R.string.checkin_dialog_title))
                .setMessage(resources.getString(R.string.supporting_text_checkin)+class_details+"?")
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                    // Respond to positive button press
                    val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                    val user = FirebaseAuth.getInstance().currentUser
                    val email: String = user?.email.toString()
                    detail = email
                    val docRef: DocumentReference =
                        mFirestore.collection("school-profiles/zphs-indira-nagar/user-info")
                            .document(detail)
                    docRef.get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val document = task.result
                            if (document != null) {

                                val namef: String = document.getString("name").toString()

                                var faculty = namef

                                val subjectname: String = document.getString("subject").toString()


                                var sub = subjectname


                              val docRef: DocumentReference =
                                    mFirestore.collection("school-profiles/zphs-indira-nagar/session-list")
                                        .document(class_details)
                                docRef.get().addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val document = task.result
                                        if (document != null) {
                                            val eee: String =
                                                document.getString("currentTeacher").toString()
                                            val emp = "none"

                                            val dddd = eee
                                            if (dddd == emp) {

                                                val usermap = HashMap<String, Any>()
                                                usermap.put("currentTeacher", faculty)
                                                usermap.put("subject", sub)


                                                val ggFirestore: FirebaseFirestore =
                                                    FirebaseFirestore.getInstance()
                                                ggFirestore.collection("school-profiles/zphs-indira-nagar/session-list")
                                                    .document(class_details).update(usermap)
                                                    .addOnSuccessListener {
                                                        Toast.makeText(
                                                            this,
                                                            "Checked In",
                                                            Toast.LENGTH_LONG
                                                        ).show()

                                                        val userinfomap = HashMap<String, Any>()
                                                        userinfomap.put("current-class",class_details)
                                                        val ggfFirestore: FirebaseFirestore =
                                                            FirebaseFirestore.getInstance()
                                                        ggfFirestore.collection("school-profiles/zphs-indira-nagar/user-info")
                                                            .document(detail).update(userinfomap)


                                                        val intent =Intent(this,CheckOutActivity::class.java)
                                                        startActivity(intent)
                                                    }.addOnFailureListener {
                                                        Toast.makeText(
                                                            this,
                                                            "error please try later",
                                                            Toast.LENGTH_LONG
                                                        )
                                                            .show()
                                                    }
                                            } else {
                                                Toast.makeText(
                                                    this,
                                                    "$eee Is already logged into the class",
                                                    Toast.LENGTH_LONG
                                                ).show()

                                            }

                                        }



                                    }
                                }



                            }
                        }
                    }

                }
                .show()

        }
    }
}

