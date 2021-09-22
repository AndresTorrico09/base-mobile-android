package com.example.myapplication.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.presentation.cars.CarsActivity
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpDrawerNavigation()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnCars.setOnClickListener {
            startActivity(
                Intent(this, CarsActivity::class.java)
            )
        }
    }

    private fun setUpDrawerNavigation() {
        navView = binding.navView
        drawerLayout = binding.mainDrawer
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.black)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarToggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.account -> {
                    Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.mycart -> {
                    Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favourite -> Toast.makeText(this, "Favourites", Toast.LENGTH_SHORT)
                .show()
            else -> null
        } ?: return super.onOptionsItemSelected(item)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            this.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}