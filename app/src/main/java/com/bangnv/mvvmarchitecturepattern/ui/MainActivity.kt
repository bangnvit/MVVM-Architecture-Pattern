package com.bangnv.mvvmarchitecturepattern.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bangnv.mvvmarchitecturepattern.R
import com.bangnv.mvvmarchitecturepattern.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    // List of IDs for the Fragments managed by the BottomNavigationView
    private val bottomNavFragments = listOf(
        R.id.nav_first,
        R.id.nav_second,
        R.id.nav_setting
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initializeBinding()
        setupNavigation()
        setupNavigationListener()
    }

    // Initialize ViewBinding and set the root content view
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Setup navigation for the BottomNavigationView with NavController
    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)
    }

    private fun setupNavigationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in bottomNavFragments) {
                binding.navView.visibility = View.VISIBLE // Show BottomNavigationView
            } else {
                binding.navView.visibility = View.GONE // Hide BottomNavigationView
            }
        }
    }
}
