package com.ringpi.project1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



class CheckOutActivity : AppCompatActivity() {

    private lateinit var mExtendFab: ExtendedFloatingActionButton
    lateinit var detail: String

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        mExtendFab = findViewById(R.id.extended_fab2)
        mExtendFab.text = "Check Out"


        val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        val email: String = user?.email.toString()
        detail = email


        mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(detail).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        val currentclass: String = document.getString("current-class").toString()
                        if (currentclass == "none") {
                            val intent = Intent(this, CheckInActivity::class.java)
                            startActivity(intent)
                        }


                        val textViewCheckout=findViewById<TextView>(R.id.textViewCheckout)
                        textViewCheckout.text =
                            getString(R.string.checkout_display_text)
                        val classtext =findViewById<TextView>(R.id.classtext)
                        classtext.text=currentclass
                        mExtendFab.setOnClickListener { view ->
                            MaterialAlertDialogBuilder(this)
                                .setTitle(resources.getString(R.string.checkout_dialog_title))
                                .setMessage(resources.getString(R.string.supporting_text_checkout) + currentclass + "?")
                                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                                    // Respond to negative button press
                                }
                                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                                    // Respond to positive button press


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
                                                    val intent =
                                                        Intent(this, HomeActivity::class.java)
                                                    startActivity(intent)
                                                }


                                        }.addOnFailureListener {
                                            Toast.makeText(
                                                this,
                                                "error please try later",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }


                                }
                                .show()
                        }

                    }
                }


            }


        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //Handle the back pressed
                val intent=Intent(this@CheckOutActivity,HomeActivity::class.java)
                startActivity(intent)
            }
        }
        this.onBackPressedDispatcher.addCallback(this, callback)

    }



}