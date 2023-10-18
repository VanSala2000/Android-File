package com.example.phillipinetouristaide

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var recyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Categories>

    lateinit var titleList:Array<String>
    lateinit var imageList:Array<Int>
    lateinit var descList: Array<String>
    lateinit var descText:Array<String>
    lateinit var detailImageList: Array<ImageInfo>

    lateinit var txt1:Array<String>
    lateinit var txt11:Array<String>
    lateinit var txt2:Array<String>
    lateinit var txt22:Array<String>
    lateinit var txt3:Array<String>
    lateinit var txt33:Array<String>
    lateinit var txt4:Array<String>
    lateinit var txt44:Array<String>
    lateinit var txt5:Array<String>
    lateinit var txt55:Array<String>
    lateinit var txt6:Array<String>
    lateinit var txt66:Array<String>
    lateinit var txt7:Array<String>
    lateinit var txt77:Array<String>
    lateinit var txt8:Array<String>
    lateinit var txt88:Array<String>
    lateinit var txt9:Array<String>
    lateinit var txt99:Array<String>
    lateinit var txt10:Array<String>
    lateinit var txt1010:Array<String>

    private lateinit var adapter: MyAdapter
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<Categories>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        //Array for data in recyclerview
        imageList = arrayOf(
            R.drawable.bataneslaoagvigan,
            R.drawable.bicol,
            R.drawable.boracay,
            R.drawable.calabarzon,
            R.drawable.cebubohol,
            R.drawable.cordillera,
            R.drawable.davaocamiguin,
            R.drawable.iloilonegros,
            R.drawable.leytesamar,
            R.drawable.metromanila,
            R.drawable.palawan,
            R.drawable.siargao)

        titleList = arrayOf(
            "BATANES, LAOAG & VIGAN",
            "BICOL",
            "BORACAY",
            "CALABARZON",
            "CEBU & BOHOL",
            "CORDILLERAS",
            "DAVAO & CAMIGUIN",
            "ILOILO & NEGROS",
            "LEYTE & SAMAR",
            "METRO MANILA",
            "PALAWAN",
            "SIARGAO")


        descList = arrayOf(
            getString(R.string.batanesLaoagVigan),
            getString(R.string.bicol),
            getString(R.string.boracay),
            getString(R.string.calabarzon),
            getString(R.string.cebuBohol),
            getString(R.string.cordilleras),
            getString(R.string.davaoCamiguin),
            getString(R.string.iloiloNegros),
            getString(R.string.leyteSamar),
            getString(R.string.metroManila),
            getString(R.string.palawan),
            getString(R.string.siargao))

        descText = arrayOf(
            getString(R.string.batanesLaoagViganDESC),
            getString(R.string.bicolDESC),
            getString(R.string.boracayDESC),
            getString(R.string.calabarzonDESC),
            getString(R.string.cebuBoholDESC),
            getString(R.string.cordillerasDESC),
            getString(R.string.davaoCamiguinDESC),
            getString(R.string.iloiloNegrosDESC),
            getString(R.string.leyteSamarDESC),
            getString(R.string.metroManilaDESC),
            getString(R.string.palawanDESC),
            getString(R.string.siargaoDESC))

        txt1 = arrayOf(
            getString(R.string.blv1),
            getString(R.string.bi1),
            getString(R.string.bo1),
            getString(R.string.ca1),
            getString(R.string.cb1),
            getString(R.string.co1),
            getString(R.string.dc1),
            getString(R.string.in1),
            getString(R.string.ls1),
            getString(R.string.mm1),
            getString(R.string.pa1),
            getString(R.string.si1))

        txt11 = arrayOf(
            getString(R.string.blv11),
            getString(R.string.bi11),
            getString(R.string.bo11),
            getString(R.string.ca11),
            getString(R.string.cb11),
            getString(R.string.co11),
            getString(R.string.dc11),
            getString(R.string.in11),
            getString(R.string.ls11),
            getString(R.string.mm11),
            getString(R.string.pa11),
            getString(R.string.si11))

        txt2 = arrayOf(
            getString(R.string.blv2),
            getString(R.string.bi2),
            getString(R.string.bo2),
            getString(R.string.ca2),
            getString(R.string.cb2),
            getString(R.string.co2),
            getString(R.string.dc2),
            getString(R.string.in2),
            getString(R.string.ls2),
            getString(R.string.mm2),
            getString(R.string.pa2),
            getString(R.string.si2))

        txt22 = arrayOf(
            getString(R.string.blv22),
            getString(R.string.bi22),
            getString(R.string.bo22),
            getString(R.string.ca22),
            getString(R.string.cb22),
            getString(R.string.co22),
            getString(R.string.dc22),
            getString(R.string.in22),
            getString(R.string.ls22),
            getString(R.string.mm22),
            getString(R.string.pa22),
            getString(R.string.si22))

        txt3 = arrayOf(
            getString(R.string.blv3),
            getString(R.string.bi3),
            getString(R.string.bo3),
            getString(R.string.ca3),
            getString(R.string.cb3),
            getString(R.string.co3),
            getString(R.string.dc3),
            getString(R.string.in3),
            getString(R.string.ls3),
            getString(R.string.mm3),
            getString(R.string.pa3),
            getString(R.string.si3))

        txt33 = arrayOf(
            getString(R.string.blv33),
            getString(R.string.bi33),
            getString(R.string.bo33),
            getString(R.string.ca33),
            getString(R.string.cb33),
            getString(R.string.co33),
            getString(R.string.dc33),
            getString(R.string.in33),
            getString(R.string.ls33),
            getString(R.string.mm33),
            getString(R.string.pa33),
            getString(R.string.si33))

        txt4 = arrayOf(
            getString(R.string.blv4),
            getString(R.string.bi4),
            getString(R.string.bo4),
            getString(R.string.ca4),
            getString(R.string.cb4),
            getString(R.string.co4),
            getString(R.string.dc4),
            getString(R.string.in4),
            getString(R.string.ls4),
            getString(R.string.mm4),
            getString(R.string.pa4),
            getString(R.string.si4))

        txt44 = arrayOf(
            getString(R.string.blv44),
            getString(R.string.bi44),
            getString(R.string.bo44),
            getString(R.string.ca44),
            getString(R.string.cb44),
            getString(R.string.co44),
            getString(R.string.dc44),
            getString(R.string.in44),
            getString(R.string.ls44),
            getString(R.string.mm44),
            getString(R.string.pa44),
            getString(R.string.si44))

        txt5 = arrayOf(
            getString(R.string.blv5),
            getString(R.string.bi5),
            getString(R.string.bo5),
            getString(R.string.ca5),
            getString(R.string.cb5),
            getString(R.string.co5),
            getString(R.string.dc5),
            getString(R.string.in5),
            getString(R.string.ls5),
            getString(R.string.mm5),
            getString(R.string.pa5),
            getString(R.string.si5))

        txt55 = arrayOf(
            getString(R.string.blv55),
            getString(R.string.bi55),
            getString(R.string.bo55),
            getString(R.string.ca55),
            getString(R.string.cb55),
            getString(R.string.co55),
            getString(R.string.dc55),
            getString(R.string.in55),
            getString(R.string.ls55),
            getString(R.string.mm55),
            getString(R.string.pa55),
            getString(R.string.si55))

        txt6 = arrayOf(
            getString(R.string.blv6),
            getString(R.string.bi6),
            getString(R.string.bo6),
            getString(R.string.ca6),
            getString(R.string.cb6),
            getString(R.string.co6),
            getString(R.string.dc6),
            getString(R.string.in6),
            getString(R.string.ls6),
            getString(R.string.mm6),
            getString(R.string.pa6),
            getString(R.string.si6))

        txt66 = arrayOf(
            getString(R.string.blv66),
            getString(R.string.bi66),
            getString(R.string.bo66),
            getString(R.string.ca66),
            getString(R.string.cb66),
            getString(R.string.co66),
            getString(R.string.dc66),
            getString(R.string.in66),
            getString(R.string.ls66),
            getString(R.string.mm66),
            getString(R.string.pa66),
            getString(R.string.si66))

        txt7 = arrayOf(
            getString(R.string.blv7),
            getString(R.string.bi7),
            getString(R.string.bo7),
            getString(R.string.ca7),
            getString(R.string.cb7),
            getString(R.string.co7),
            getString(R.string.dc7),
            getString(R.string.in7),
            getString(R.string.ls7),
            getString(R.string.mm7),
            getString(R.string.pa7),
            getString(R.string.si7))

        txt77 = arrayOf(
            getString(R.string.blv77),
            getString(R.string.bi77),
            getString(R.string.bo77),
            getString(R.string.ca77),
            getString(R.string.cb77),
            getString(R.string.co77),
            getString(R.string.dc77),
            getString(R.string.in77),
            getString(R.string.ls77),
            getString(R.string.mm77),
            getString(R.string.pa77),
            getString(R.string.si77))

        txt8 = arrayOf(
            getString(R.string.blv8),
            getString(R.string.bi8),
            getString(R.string.bo8),
            getString(R.string.ca8),
            getString(R.string.cb8),
            getString(R.string.co8),
            getString(R.string.dc8),
            getString(R.string.in8),
            getString(R.string.ls8),
            getString(R.string.mm8),
            getString(R.string.pa8),
            getString(R.string.si8))

        txt88 = arrayOf(
            getString(R.string.blv88),
            getString(R.string.bi88),
            getString(R.string.bo88),
            getString(R.string.ca88),
            getString(R.string.cb88),
            getString(R.string.co88),
            getString(R.string.dc88),
            getString(R.string.in88),
            getString(R.string.ls88),
            getString(R.string.mm88),
            getString(R.string.pa88),
            getString(R.string.si88))

        txt9 = arrayOf(
            getString(R.string.blv9),
            getString(R.string.bi9),
            getString(R.string.bo9),
            getString(R.string.ca9),
            getString(R.string.cb9),
            getString(R.string.co9),
            getString(R.string.dc9),
            getString(R.string.in9),
            getString(R.string.ls9),
            getString(R.string.mm9),
            getString(R.string.pa9),
            getString(R.string.si9))

        txt99 = arrayOf(
            getString(R.string.blv99),
            getString(R.string.bi99),
            getString(R.string.bo99),
            getString(R.string.ca99),
            getString(R.string.cb99),
            getString(R.string.co99),
            getString(R.string.dc99),
            getString(R.string.in99),
            getString(R.string.ls99),
            getString(R.string.mm99),
            getString(R.string.pa99),
            getString(R.string.si99))

        txt10 = arrayOf(
            getString(R.string.blv10),
            getString(R.string.bi10),
            getString(R.string.bo10),
            getString(R.string.ca10),
            getString(R.string.cb10),
            getString(R.string.co10),
            getString(R.string.dc10),
            getString(R.string.in10),
            getString(R.string.ls10),
            getString(R.string.mm10),
            getString(R.string.pa10),
            getString(R.string.si10))

        txt1010 = arrayOf(
            getString(R.string.blv1010),
            getString(R.string.bi1010),
            getString(R.string.bo1010),
            getString(R.string.ca1010),
            getString(R.string.cb1010),
            getString(R.string.co1010),
            getString(R.string.dc1010),
            getString(R.string.in1010),
            getString(R.string.ls1010),
            getString(R.string.mm1010),
            getString(R.string.pa1010),
            getString(R.string.si1010))

        detailImageList = arrayOf(
            ImageInfo(R.drawable.bataneslaoagvigan,R.drawable.bataneslaoagvigan1,R.drawable.bataneslaoagvigan2),
            ImageInfo(R.drawable.bicol,R.drawable.bicol1,R.drawable.bicol2),
            ImageInfo(R.drawable.boracay,R.drawable.boracay1,R.drawable.boracay2),
            ImageInfo(R.drawable.calabarzon,R.drawable.calabarzon1,R.drawable.calabarzon2),
            ImageInfo(R.drawable.cebubohol,R.drawable.cebubohol1,R.drawable.cebubohol2),
            ImageInfo(R.drawable.cordillera,R.drawable.cordillera1,R.drawable.cordillera2),
            ImageInfo(R.drawable.davaocamiguin,R.drawable.davaocamiguin1,R.drawable.davaocamiguin2),
            ImageInfo(R.drawable.iloilonegros,R.drawable.iloilonegros1,R.drawable.iloilonegros2),
            ImageInfo(R.drawable.leytesamar,R.drawable.leytesamar1,R.drawable.leytesamar2),
            ImageInfo(R.drawable.metromanila,R.drawable.metromanila1,R.drawable.metromanila2),
            ImageInfo(R.drawable.palawan,R.drawable.palawan1,R.drawable.palawan2),
            ImageInfo(R.drawable.siargao,R.drawable.siargao1,R.drawable.siargao2))

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Categories>()
        searchList = arrayListOf<Categories>()
        getUserdata()

        //Function for searching items in recyclerview
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    newArrayList.forEach{
                        if (it.dataTitle.lowercase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(newArrayList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

        //Function when the user click the item on recyclerview
        adapter = MyAdapter(searchList)
        recyclerView.adapter = adapter
        adapter.onItemClick = {
            val intent = Intent(this, CategoriesActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //Function for passing the data in Categories Class
    private fun getUserdata() {
        for (i in imageList.indices){
            val dataClass = Categories(imageList[i], titleList[i], descList[i], descText[i], txt1[i], txt11[i], txt2[i], txt22[i], txt3[i], txt33[i], txt4[i], txt44[i], txt5[i], txt55[i], txt6[i], txt66[i], txt7[i], txt77[i], txt8[i], txt88[i], txt9[i], txt99[i], txt10[i], txt1010[i], detailImageList[i])
            newArrayList.add(dataClass)
        }
        searchList.addAll(newArrayList)
        recyclerView.adapter = MyAdapter(searchList)
    }
}