package com.ringpi.project1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var detail:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val user = FirebaseAuth.getInstance().currentUser
        val email: String = user?.email.toString()
        detail = email



        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }







    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId

        if (id==R.id.action_logout){

            MaterialAlertDialogBuilder(this)
                .setTitle("Logout Confirmation")
                .setMessage("Are you sure you want to Logout?")
                .setNegativeButton("NO") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton("YES") { dialog, which ->
                    // Respond to positive button press
                    val firebaseAuth=FirebaseAuth.getInstance().signOut()
                    Toast.makeText(this, "Successfully Logged out", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                .show()

        }

        if(id==R.id.action_hmview) {
            val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

            val user = FirebaseAuth.getInstance().currentUser
            val email: String = user?.email.toString()
            detail = email


            val docRef: DocumentReference =
                mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(detail)
            docRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        val designation = document.getString("designation").toString()
                        if (designation == "HeadMaster") {


                            val intent = Intent(this, HMactivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Only HeadMaster Can Access This",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }

            }
        }
        /*if (id==R.id.action_examlink){
            val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

            val user = FirebaseAuth.getInstance().currentUser
            val email: String = user?.email.toString()
            detail = email

            val docRef: DocumentReference =
                mFirestore.collection("school-profiles/zphs-indira-nagar/user-info").document(detail)
            docRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null) {
                        val designation = document.getString("designation").toString()
                        if (designation == "Teacher"){


                            val intent = Intent(this, TeachersExamZoneActivity::class.java)
                            startActivity(intent)
                        }else if(designation == "HeadMaster") {

                            val intent = Intent(this, TeachersExamZoneActivity::class.java)
                            startActivity(intent)

                        } else
                            {
                            Toast.makeText(
                                this,
                                "Only Faculty Can Access This",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }

            }

        }*/
        return super.onOptionsItemSelected(item)
    }





    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}