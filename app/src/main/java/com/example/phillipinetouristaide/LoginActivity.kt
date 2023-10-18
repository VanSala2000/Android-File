package com.example.phillipinetouristaide

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: TextView
    private lateinit var dbh: SQLiteDB
    private lateinit var rememberme: CheckBox

    private lateinit var sessionManager: SessionManager

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        inputEmail = findViewById(R.id.inputuserEmail)
        password = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnLogin)
        rememberme = findViewById(R.id.rememberMe)
        btnSignUp = findViewById(R.id.txtSignUp)
        dbh = SQLiteDB(this)

        //Button for Signing In of user
        btnLogin.setOnClickListener {
            val inpEmail = inputEmail.text.toString()
            val inputPassword = password.text.toString()

            if (TextUtils.isEmpty(inpEmail) || TextUtils.isEmpty(inputPassword)) {
                Toast.makeText(this, "Type your username and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val authUserPass = dbh.authUserPass(inpEmail, inputPassword)
                if (authUserPass) {
                    sessionManager.createLoginSession(inpEmail,inputPassword)
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Invalid username and password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Code for not getting back to LoginActivity when the user is still in Logged In
        rememberme.isChecked = sessionManager.getRememberMe()
        rememberme.setOnCheckedChangeListener { _, isChecked ->
            sessionManager.setRememberMe(isChecked)
        }


        //Intent for RegisterActivity
        btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
