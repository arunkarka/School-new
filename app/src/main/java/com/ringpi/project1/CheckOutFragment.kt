package com.ringpi.project1

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class CheckOutFragment : Fragment() {


    private lateinit var mExtendFab: ExtendedFloatingActionButton
    lateinit var detail: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.check_out_fragment, container, false)
        val view: View = inflater.inflate(R.layout.check_out_fragment, container, false)
        val  loading =view.findViewById<ProgressBar>(R.id.spin_kit_cout)
        mExtendFab = view.findViewById(R.id.extended_fab2)
        mExtendFab.text = "Check Out"

        loading.setVisibility(View.GONE)
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
                            (activity as CheckInOutActivity?)?.replaceFragment(CheckInFragment(),R.id.container)
                            //val intent = Intent(this, CheckInActivity::class.java)
                           // startActivity(intent)
                        }


                        val classtext=view.findViewById<TextView>(R.id.classtext)
                        classtext.text=currentclass
                        mExtendFab.setOnClickListener { view ->

                            MaterialAlertDialogBuilder(requireActivity())
                                .setTitle(resources.getString(R.string.checkout_dialog_title))
                                .setMessage(resources.getString(R.string.supporting_text_checkout) + currentclass + "?")
                                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                                    // Respond to negative button press
                                }
                                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                                    // Respond to positive button press

                                    loading.setVisibility(View.VISIBLE)
                                    requireActivity().getWindow().setFlags(
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                                    );

                                    val cm =
                                        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        val n = cm.activeNetwork
                                        if (n != null) {
                                            val nc = cm.getNetworkCapabilities(n)
                                            //It will check for both wifi and cellular network


                                        } else {
                                            Toast.makeText(
                                                requireActivity().baseContext,
                                                "Connection Lost",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            startActivity(
                                                Intent(
                                                    requireActivity().baseContext,
                                                    ErrorActivity::class.java
                                                )
                                            )

                                        }

                                    }

                                    //
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
                                                        requireActivity().baseContext,
                                                        "Checked Out",
                                                        Toast.LENGTH_LONG
                                                    )
                                                        .show()
                                                    loading.setVisibility(View.GONE)
                                                    requireActivity().getWindow()
                                                        .clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                                    (activity as CheckInOutActivity?)?.replaceFragment(
                                                        CheckInFragment(),
                                                        R.id.container
                                                    )
                                                    // val intent =
                                                    //     Intent(this, HomeActivity::class.java)
                                                    // startActivity(intent)
                                                }


                                        }.addOnFailureListener {

                                            startActivity(
                                                Intent(
                                                    requireActivity().baseContext,
                                                    ErrorActivity::class.java
                                                )
                                            )
                                            Toast.makeText(
                                                requireActivity().baseContext,
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


        return view
        return root
    }


}