package com.example.footballapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.footballapp.R
import com.example.footballapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNavView()
    }

    private fun setUpBottomNavView() {
        val navController = findNavController(R.id.mainNavHostFragment)
        binding.bottomNavView.setupWithNavController(navController)
    }
}