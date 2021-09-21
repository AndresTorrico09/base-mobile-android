package com.example.myapplication.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCarsBinding
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.presentation.cars.CarsActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCars.setOnClickListener {
            startActivity(
                Intent(this, CarsActivity::class.java)
            )
        }
    }
}