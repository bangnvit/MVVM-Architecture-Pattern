package com.bangnv.mvvmarchitecturepattern.repositories

import com.bangnv.mvvmarchitecturepattern.models.User


class UserRepository {
    fun getUserData(): User {
        // Fetch weather data from a remote server or local storage
        return User("Bang", "Bang123@xmail.com")
    }
}