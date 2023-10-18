package com.example.phillipinetouristaide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class LogoScreen : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_logo_screen)

        sessionManager = SessionManager(this)

        //Logic for session when the user hit the logout button in navigation or not
        if(sessionManager.isLoggedIn()){
            //SplashScreen for logo
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },4000)
        }else{
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            },4000)
        }
    }
}