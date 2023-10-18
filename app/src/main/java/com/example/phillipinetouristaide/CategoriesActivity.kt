package com.example.phillipinetouristaide

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.smarteist.autoimageslider.SliderView


class CategoriesActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

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

        //This is for menu for navigation
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


        //This block of code is for fetching the data for Categories class
        val getData = intent.getParcelableExtra<Categories>("android")
        if (getData != null) {
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailDesc: TextView = findViewById(R.id.detailDesc)
            val ttlTodo: TextView = findViewById(R.id.ttlTodo)
            val txt1: TextView = findViewById(R.id.txt1)
            val txt11: TextView = findViewById(R.id.txt11)
            val txt2: TextView = findViewById(R.id.txt2)
            val txt22: TextView = findViewById(R.id.txt22)
            val txt3: TextView = findViewById(R.id.txt3)
            val txt33: TextView = findViewById(R.id.txt33)
            val txt4: TextView = findViewById(R.id.txt4)
            val txt44: TextView = findViewById(R.id.txt44)
            val txt5: TextView = findViewById(R.id.txt5)
            val txt55: TextView = findViewById(R.id.txt55)
            val txt6: TextView = findViewById(R.id.txt6)
            val txt66: TextView = findViewById(R.id.txt66)
            val txt7: TextView = findViewById(R.id.txt7)
            val txt77: TextView = findViewById(R.id.txt77)
            val txt8: TextView = findViewById(R.id.txt8)
            val txt88: TextView = findViewById(R.id.txt88)
            val txt9: TextView = findViewById(R.id.txt9)
            val txt99: TextView = findViewById(R.id.txt99)
            val txt10: TextView = findViewById(R.id.txt10)
            val txt1010: TextView = findViewById(R.id.txt1010)

            //Displays the pictures into ImageAutoSlider and TextView
            detailTitle.text = getData.dataTitle
            ttlTodo.text = getData.dataText
            txt1.text = getData.data1
            txt11.text = getData.data11
            txt2.text = getData.data2
            txt22.text = getData.data22
            txt3.text = getData.data3
            txt33.text = getData.data33
            txt4.text = getData.data4
            txt44.text = getData.data44
            txt5.text = getData.data5
            txt55.text = getData.data55
            txt6.text = getData.data6
            txt66.text = getData.data66
            txt7.text = getData.data7
            txt77.text = getData.data77
            txt8.text = getData.data8
            txt88.text = getData.data88
            txt9.text = getData.data9
            txt99.text = getData.data99
            txt10.text = getData.data10
            txt1010.text = getData.data1010
            detailDesc.text = getData.dataDesc

            val imageInfo = getData.dataDetailImage
            if (imageInfo != null) {
                val imageSlider = findViewById<SliderView>(R.id.sliderImage)
                val adapter = SliderAdapter(imageInfo)
                imageSlider.setSliderAdapter(adapter)
            }
        }
    }

    //Function for toggle the items in menu for navigation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}