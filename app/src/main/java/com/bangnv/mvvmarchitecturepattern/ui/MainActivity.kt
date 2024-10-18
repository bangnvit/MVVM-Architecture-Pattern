package com.bangnv.mvvmarchitecturepattern.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bangnv.mvvmarchitecturepattern.R
import com.bangnv.mvvmarchitecturepattern.databinding.ActivityMainBinding
import com.bangnv.mvvmarchitecturepattern.models.User
import com.bangnv.mvvmarchitecturepattern.utils.GlobalFunction
import com.bangnv.mvvmarchitecturepattern.viewmodels.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable Edge to Edge mode, content will expand to full screen
        enableEdgeToEdge()
        // Initialize binding from layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Apply window insets to root view
        applyWindowInsets()



        val navView: BottomNavigationView = binding.navView

        // Sử dụng NavHostFragment để lấy NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        // Thiết lập BottomNavigationView với NavController
        navView.setupWithNavController(navController)
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}