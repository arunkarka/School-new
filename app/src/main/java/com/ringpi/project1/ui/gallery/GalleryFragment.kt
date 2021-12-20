package com.ringpi.project1.ui.gallery


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ringpi.project1.R


class GalleryFragment : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val view: View = inflater.inflate(R.layout.fragment_gallery, container, false)

        val title1=view.findViewById<TextView>(R.id.title1)
        val desc1=view.findViewById<TextView>(R.id.note1)
        val testbutton1=view.findViewById<Button>(R.id.Button1)
        var url1:String

        val title2=view.findViewById<TextView>(R.id.title2)
        val desc2=view.findViewById<TextView>(R.id.note2)
        val testbutton2=view.findViewById<Button>(R.id.Button2)
        var url2:String

        val title3=view.findViewById<TextView>(R.id.title3)
        val desc3=view.findViewById<TextView>(R.id.note3)
        val testbutton3=view.findViewById<Button>(R.id.Button3)
        var url3:String

        val title4=view.findViewById<TextView>(R.id.title4)
        val desc4=view.findViewById<TextView>(R.id.note4)
        val testbutton4=view.findViewById<Button>(R.id.Button4)
        var url4:String

        val title5=view.findViewById<TextView>(R.id.title5)
        val desc5=view.findViewById<TextView>(R.id.note5)
        val testbutton5=view.findViewById<Button>(R.id.Button5)
        var url5:String

        val title6=view.findViewById<TextView>(R.id.title6)
        val desc6=view.findViewById<TextView>(R.id.note6)
        val testbutton6=view.findViewById<Button>(R.id.Button6)
        var url6:String

        val title7=view.findViewById<TextView>(R.id.title7)
        val desc7=view.findViewById<TextView>(R.id.note7)
        val testbutton7=view.findViewById<Button>(R.id.Button7)
        var url7:String



            var myDB = FirebaseFirestore.getInstance()

            val docRef1: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/exam-links").document("Mathematics")
        docRef1.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val t1: String? = document.getString("title")
                    val d1: String? = document.getString("description")
                     url1= document.getString("url").toString()
                    title1.text=t1
                    desc1.text=d1

                    testbutton1.setOnClickListener {
                        val iii = Intent(requireActivity().baseContext, RegisterActivity::class.java)
                        iii.putExtra("url",url1)
                        requireActivity().startActivity(iii)
                    }


                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }


        val docRef2: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/exam-links").document("English")
        docRef2.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val t2: String? = document.getString("title")
                    val d2: String? = document.getString("description")
                    url2 = document.getString("url").toString()
                    title2.text=t2
                    desc2.text=d2

                    testbutton2.setOnClickListener {
                        val iii = Intent(requireActivity().baseContext, RegisterActivity::class.java)
                        iii.putExtra("url",url2)
                        requireActivity().startActivity(iii)
                    }


                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }


        val docRef3: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/exam-links").document("Telugu")
        docRef3.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val t3: String? = document.getString("title")
                    val d3: String? = document.getString("description")
                    url3= document.getString("url").toString()
                    title3.text=t3
                    desc3.text=d3
                    testbutton3.setOnClickListener {
                        val iii = Intent(requireActivity().baseContext, RegisterActivity::class.java)
                        iii.putExtra("url",url3)
                        requireActivity().startActivity(iii)
                    }




                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }



        val docRef4: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/exam-links").document("Hindi")
        docRef4.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val t4: String? = document.getString("title")
                    val d4: String? = document.getString("description")
                    url4= document.getString("url").toString()
                    title4.text=t4
                    desc4.text=d4

                    testbutton4.setOnClickListener {
                        val iii = Intent(requireActivity().baseContext, RegisterActivity::class.java)
                        iii.putExtra("url",url4)
                        requireActivity().startActivity(iii)
                    }


                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }


        val docRef5: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/exam-links").document("Social Studies")
        docRef5.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val t5: String? = document.getString("title")
                    val d5: String? = document.getString("description")
                    url5= document.getString("url").toString()
                    title5.text=t5
                    desc5.text=d5
                    testbutton5.setOnClickListener {
                        val iii = Intent(requireActivity().baseContext, RegisterActivity::class.java)
                        iii.putExtra("url",url5)
                        requireActivity().startActivity(iii)
                    }




                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }


        val docRef6: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/exam-links").document("Physical Science")
        docRef6.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val t6: String? = document.getString("title")
                    val d6: String? = document.getString("description")
                    url6= document.getString("url").toString()
                    title6.text=t6
                    desc6.text=d6
                    testbutton6.setOnClickListener {
                        val iii = Intent(requireActivity().baseContext, RegisterActivity::class.java)
                        iii.putExtra("url",url6)
                        requireActivity().startActivity(iii)
                    }



                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }


        val docRef7: DocumentReference =
            myDB.collection("school-profiles/zphs-indira-nagar/exam-links").document("Biology")
        docRef7.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    val t7: String? = document.getString("title")
                    val d7: String? = document.getString("description")
                    url7= document.getString("url").toString()
                    title7.text=t7
                    desc7.text=d7

                    testbutton7.setOnClickListener {
                        val iii = Intent(requireActivity().baseContext, RegisterActivity::class.java)
                        iii.putExtra("url",url7)
                        requireActivity().startActivity(iii)
                    }


                } else {
                    Log.d("LOGGER", "No such document")
                }
            } else {
                Log.d("LOGGER", "get failed with ", task.exception)
            }
        }










        return view
        return root
    }
}