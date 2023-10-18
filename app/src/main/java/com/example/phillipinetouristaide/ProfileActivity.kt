package com.example.phillipinetouristaide

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var db: SQLiteDB
    private lateinit var txtUsername: EditText
    private lateinit var txtEmail: EditText

    private lateinit var sessionManager: SessionManager

    private lateinit var newpassword: EditText
    private lateinit var oldpassword: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var btnSubmit: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sessionManager = SessionManager(this)

        oldpassword = findViewById(R.id.inputOldPassword)
        newpassword = findViewById(R.id.inputNewPassword)
        confirmPassword = findViewById(R.id.inputConfirmPassword)
        btnSubmit = findViewById(R.id.btnSubmit)

        //This block of code is for displaying user email in edit text
        db = SQLiteDB(this)
        txtEmail= findViewById(R.id.txtEmail)
        txtEmail.isEnabled = false
        val getEmail = sessionManager.getEmail()
        txtEmail.setText(getEmail)

        //This block of code is for changing user password
        btnSubmit.setOnClickListener{
            val newpass = newpassword.text.toString()
            val oldpass = oldpassword.text.toString()
            val confirmpass = confirmPassword.text.toString()
            val email = sessionManager.getEmail().toString()
            val authenticateOldPassword = sessionManager.getPassword()

            if(TextUtils.isEmpty(oldpass) || TextUtils.isEmpty(newpass)
                || TextUtils.isEmpty(confirmpass)){
                Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show()
            }else{
                if(oldpass != authenticateOldPassword){
                    Toast.makeText(this, "Old password does not match.", Toast.LENGTH_SHORT).show()
                }else{
                    if (newpass == confirmpass) {
                        val saveData = db.updatePassword(email, newpass)

                        if (saveData > 0) {
                            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "Failed to change your password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        //this block of code is for Drawer Function
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



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}