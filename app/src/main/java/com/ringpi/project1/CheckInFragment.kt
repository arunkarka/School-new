package com.ringpi.project1


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class CheckInFragment : Fragment() {

    lateinit var dell: String
    lateinit var total: String
    private lateinit var mExtendFab: ExtendedFloatingActionButton

    lateinit var detail: String
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.check_in_fragment, container, false)
        val view: View = inflater.inflate(R.layout.check_in_fragment, container, false)
        val spinner = view.findViewById<Spinner>(R.id.spinner1)
        val spinner_sec=view.findViewById<Spinner>(R.id.spinner2)
        val  loading =view.findViewById<ProgressBar>(R.id.spin_kit_cin)


        loading.setVisibility(View.GONE)




        mExtendFab = view.findViewById(R.id.extended_fab1)
        mExtendFab.setOnClickListener { view ->

            var class_details = spinner.selectedItem.toString()
            var section_details = spinner_sec.selectedItem.toString()
            total=class_details+section_details
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle(resources.getString(R.string.checkin_dialog_title))
                .setMessage(resources.getString(R.string.supporting_text_checkin) + class_details+section_details + "?")
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                    // Respond to positive button press
                    loading.setVisibility(View.VISIBLE)
                    requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        val n = cm.activeNetwork
                        if (n != null) {
                            val nc = cm.getNetworkCapabilities(n)
                            //It will check for both wifi and cellular network


                        }
                        else {
                            Toast.makeText(
                                requireActivity().baseContext,
                                "nope",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(requireActivity().baseContext, ErrorActivity::class.java))

                        }

                    }

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


                                val doc: DocumentReference =
                                    mFirestore.collection("school-profiles/zphs-indira-nagar/session-list")
                                        .document(total)
                                doc.get().addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val document = task.result
                                        if (document != null) {
                                            val eee: String =
                                                document.getString("currentTeacher").toString()
                                            val emp = "none"

                                            val dddd = eee
                                            if (eee=="none") {

                                                val usermap = HashMap<String, Any>()
                                                usermap.put("currentTeacher", faculty)
                                                usermap.put("subject", sub)


                                                val ggFirestore: FirebaseFirestore =
                                                    FirebaseFirestore.getInstance()
                                                ggFirestore.collection("school-profiles/zphs-indira-nagar/session-list")
                                                    .document(total).update(usermap)
                                                    .addOnSuccessListener {
                                                        Toast.makeText(
                                                            requireActivity().baseContext,
                                                            "Checked In",
                                                            Toast.LENGTH_LONG
                                                        ).show()

                                                        val userinfomap = HashMap<String, Any>()
                                                        userinfomap.put(
                                                            "current-class",
                                                            total
                                                        )
                                                        val ggfFirestore: FirebaseFirestore =
                                                            FirebaseFirestore.getInstance()
                                                        ggfFirestore.collection("school-profiles/zphs-indira-nagar/user-info")
                                                            .document(detail).update(userinfomap)
                                                        loading.setVisibility(View.GONE)

                                                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                        (activity as CheckInOutActivity?)?.replaceFragment(CheckOutFragment(),R.id.container)
                                                        //val intent = Intent(requireActivity().baseContext ,CheckOutActivity::class.java)
                                                        //startActivity(intent)
                                                    }.addOnFailureListener {

                                                        startActivity(Intent(requireActivity().baseContext, ErrorActivity::class.java))
                                                        Toast.makeText(
                                                            requireActivity().baseContext,
                                                            "error please try later",
                                                            Toast.LENGTH_LONG
                                                        )
                                                            .show()
                                                    }





                                            }
                                           else {
                                                Toast.makeText(
                                                    requireActivity().baseContext,
                                                    "$eee Is already logged into the class",
                                                    Toast.LENGTH_LONG
                                                ).show()


                                            }

                                        }



                                    }
                                }



                            }
                        }
                    }.addOnFailureListener {
                        startActivity(Intent(requireActivity().baseContext, ErrorActivity::class.java))

                    }

                }
                .show()

        }

        return view
        return root
    }




}