package com.example.newsfeedsample

import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsfeedsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawer = binding.drawerLayout
        navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer)
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.politics_menu_item -> NewsFragment.category.value = "politics"
                R.id.sports_menu_item -> NewsFragment.category.value = "sports"
                R.id.lifestyle_menu_item -> NewsFragment.category.value = "lifestyle"
                R.id.technology_menu_item -> NewsFragment.category.value = "technology"
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val controller = findNavController(R.id.fragmentContainerView)
        return  controller.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}