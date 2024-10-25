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

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchUserAPI() {
        _isLoading.postValue(true) // Show ProgressBar
        viewModelScope.launch {
            try {
                val response: Response<UserResponse> = userRepository.getUserResponse()
                if (response.isSuccessful) {
                    response.body()?.let {
                        val user = convertResponseToUser(it) // Convert UserResponse to User
                        _userData.postValue(user)
                    }
                } else {
                    handleError(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                handleError(e.message)
            } finally {
                _isLoading.postValue(false)  // Hide ProgressBar
            }
        }
    }

    // Convert UserResponse to User
    private fun convertResponseToUser(userResponse: UserResponse): User {
        return User(
            ObservableField(userResponse.username),
            ObservableField(userResponse.email)
        )
    }

    private fun handleError(message: String?) {
        Log.d("UserViewModel", "Error: $message")
        _errorMessage.postValue(message ?: "Unknown error occurred")
    }

    // Function to update user data from EditText
    fun updateUserData(user: User) {
        _userData.postValue(user)
    }
}