package com.bangnv.mvvmarchitecturepattern.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangnv.mvvmarchitecturepattern.models.User
import com.bangnv.mvvmarchitecturepattern.repositories.UserRepository


class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    init {
        // Get default value from repository when ViewModel is initialized
        fetchDefaultUserData()
    }

    // Function to get default data
    fun fetchDefaultUserData() {
        val user = userRepository.getUserData()
        _userData.postValue(user)
    }

    // Function to update user data from EditText
    fun updateUserData(user: User) {
        _userData.postValue(user)
    }
}