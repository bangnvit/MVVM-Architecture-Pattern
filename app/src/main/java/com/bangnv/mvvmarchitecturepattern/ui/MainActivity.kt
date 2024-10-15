package com.bangnv.mvvmarchitecturepattern.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.bangnv.mvvmarchitecturepattern.databinding.ActivityMainBinding
import com.bangnv.mvvmarchitecturepattern.models.User
import com.bangnv.mvvmarchitecturepattern.utils.GlobalFunction
import com.bangnv.mvvmarchitecturepattern.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()

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

        // Assign viewModel to binding
        binding.viewModel = userViewModel
        binding.lifecycleOwner = this

        // Get user data and update the UI
        userViewModel.userData.observe(this, Observer { user ->
            binding.tvName.text = user.username
            binding.tvEmail.text = user.email
            Toast.makeText(this, "User updated: ${user.username}", Toast.LENGTH_SHORT).show()
        })

        // Update data
        binding.btnUpdateValue.setOnClickListener {
            val username = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            userViewModel.updateUserData(User(username, email))

            GlobalFunction.hideSoftKeyboard(this)
            binding.edtName.clearFocus()
            binding.edtEmail.clearFocus()
        }

        // Get default value
        binding.btnGetDefault.setOnClickListener {
            userViewModel.fetchDefaultUserData()

            GlobalFunction.hideSoftKeyboard(this)
            binding.edtName.clearFocus()
            binding.edtEmail.clearFocus()
            binding.edtName.setText("")
            binding.edtEmail.setText("")
        }

    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}