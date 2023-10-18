package com.example.phillipinetouristaide

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnSignIn: TextView
    private lateinit var db: SQLiteDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.inEmail)
        password = findViewById(R.id.inPassword)
        confirmPassword = findViewById(R.id.inConfirmPass)
        btnRegister = findViewById(R.id.btnRegister)
        btnSignIn = findViewById(R.id.txtSignIn)
        db = SQLiteDB(this)

        //Button for Signing Up of user
        btnRegister.setOnClickListener{
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val cpText = confirmPassword.text.toString()
            val isPasswordValid = isPasswordValid(passwordText)
            val emailExist = db.isEmailExists(emailText)

            if(TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(cpText)){
                Toast.makeText(this,"Input username, email, password & confirm your password ", Toast.LENGTH_SHORT).show()
            }else{
                if (Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    if(isPasswordValid) {
                        if (passwordText == cpText) {
                            if(!emailExist) {
                                val saveData = db.insertUser(emailText, passwordText)
                                if (saveData) {
                                    Toast.makeText(
                                        this,
                                        "You are now registered to this application",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                }else{
                                    Toast.makeText(this, "Failed to register", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Password is invalid. It must have at least 6 numbers or letters.", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Enter valid Email address", Toast.LENGTH_SHORT).show();
                }
            }
        }

        //Intent for LoginActivity
        btnSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    //Function for authenticating the password
    fun isPasswordValid(password: String): Boolean {
        // Define a regular expression for at least 6 alphanumeric characters
        val regex = "^(?=.*[a-zA-Z0-9]).{6,}\$"

        // Check if the password matches the regex
        return password.matches(regex.toRegex())
    }
}