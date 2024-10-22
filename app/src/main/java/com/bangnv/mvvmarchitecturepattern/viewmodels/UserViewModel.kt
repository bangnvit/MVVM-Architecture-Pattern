package com.bangnv.mvvmarchitecturepattern.viewmodels

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangnv.mvvmarchitecturepattern.models.offline.User
import com.bangnv.mvvmarchitecturepattern.models.response.UserResponse
import com.bangnv.mvvmarchitecturepattern.repositories.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchUserAPI() {
        _isLoading.postValue(true) // Show ProgressBar
        viewModelScope.launch {
            try {
                val response: Response<UserResponse> = userRepository.getUserResponse()
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    userResponse?.let {
                        val user = User(
                            ObservableField(it.username),
                            ObservableField(it.email)
                        )
                        _userData.postValue(user)
                    }
                } else {
                    Log.d("UserViewModel", "Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.d("UserViewModel", "API call failed: ${e.message}")
            } finally {
                _isLoading.postValue(false)  // Hide ProgressBar
            }
        }
    }

    // Function to update user data from EditText
    fun updateUserData(user: User) {
        _userData.postValue(user)
    }
}