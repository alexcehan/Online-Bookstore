package com.hfad.online_bookstore

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.SearchView

import androidx.core.content.ContextCompat


import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.top_toolbar)

        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()

        toolbar.setupWithNavController(navController, appBarConfiguration)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setupWithNavController(navController)






    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)


    }





    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

//        when (item.itemId) {
//            R.id.search_icon -> {
//                // Inflate the custom layout for the SearchView
//                val searchViewContainer = item.actionView as FrameLayout
//                val searchView = layoutInflater.inflate(R.layout.search_view, searchViewContainer, false)
//                searchViewContainer.addView(searchView)
//
//                // Set up the SearchView and the TextView
//                val searchViewWidget = searchView.findViewById<SearchView>(R.id.search_view)
//                val searchEditText = searchViewWidget.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
//                searchEditText.setTextColor(ContextCompat.getColor(this, android.R.color.black))
//
//                val searchIcon = searchViewWidget.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
//                val clearIcon = searchViewWidget.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
//                searchIcon.setColorFilter(Color.BLACK)
//                clearIcon.setColorFilter(Color.BLACK)
//
//                // ...
//
//                return true
//            }
//            else -> return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//        }


    }


}