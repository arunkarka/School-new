package com.ringpi.project1.ui.home

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import com.ringpi.project1.*


class HomeFragment : Fragment() {


    var viewPager: ViewPager? = null
    var adapter: CustomSwipeAdapter? = null
    lateinit var detail:String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val checkinout=view.findViewById<CardView>(R.id.cincout)
        val dev_mail=view.findViewById<TextView>(R.id.devmail)
        val contact=view.findViewById<CardView>(R.id.contactus)
        val results =view.findViewById<CardView>(R.id.results)
        val exams=view.findViewById<CardView>(R.id.exam)
        val
        viewPager = view.findViewById<View>(R.id.view_pager) as ViewPager
        adapter = CustomSwipeAdapter(this.activity)
        viewPager!!.adapter = adapter

        val user = FirebaseAuth.getInstance().currentUser
        val email: String = user?.email.toString()
        detail = email
        checkinout.setOnClickListener {
            startActivity(Intent(requireActivity().baseContext, CheckInOutActivity::class.java))
        }
        contact.setOnClickListener {
            startActivity(Intent(requireActivity().baseContext, ContactUsActivity::class.java))
        }

        results.setOnClickListener {
            Toast.makeText(
                requireActivity().baseContext,
                "Coming Soon",
                Toast.LENGTH_LONG
            ).show()
           // startActivity(Intent(requireActivity().baseContext, ResultsHomeActivity::class.java))
        }
        exams.setOnClickListener {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val n = cm.activeNetwork
                if (n != null) {
                    val nc = cm.getNetworkCapabilities(n)
                    //It will check for both wifi and cellular network
                    Toast.makeText(
                        requireActivity().baseContext,
                        "Coming Soon",
                        Toast.LENGTH_LONG
                    ).show()

                }
                else {
                    Toast.makeText(
                        requireActivity().baseContext,
                        "nope",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }



        dev_mail?.setOnClickListener {
        val intent = Intent(Intent.ACTION_SEND)
        val recipients = arrayOf("ringpisolve@gmail.com")
        intent.putExtra(Intent.EXTRA_EMAIL, recipients)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject text here...")
        intent.putExtra(Intent.EXTRA_TEXT, "Body of the content here...")
        intent.putExtra(Intent.EXTRA_CC, "mailcc@gmail.com")
        intent.type = "text/html"
        intent.setPackage("com.google.android.gm")
        startActivity(Intent.createChooser(intent, "Send mail"))
     }
        return view
    }




}



