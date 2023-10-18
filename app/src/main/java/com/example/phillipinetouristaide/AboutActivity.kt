package com.example.phillipinetouristaide

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class AboutActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        sessionManager = SessionManager(this)

        //This block of code is for drawer function
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navigation : NavigationView = findViewById(R.id.navigaton)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Philippine Tourist Aide"
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent = Intent(applicationContext, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_information -> {
                    val intent = Intent(applicationContext, AboutActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    sessionManager.logoutUser()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            true
        }


        //This code is for linking the site when clicking the text in TextView
        val linktosite = findViewById<TextView>(R.id.linktosite)

        linktosite.setOnClickListener {
            val url = "https://www.itsmorefuninthephilippines.co.uk/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}