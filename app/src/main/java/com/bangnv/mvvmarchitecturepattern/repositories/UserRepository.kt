package com.bangnv.mvvmarchitecturepattern.repositories

import com.bangnv.mvvmarchitecturepattern.data.network.RetrofitClient
import com.bangnv.mvvmarchitecturepattern.models.response.UserResponse
import retrofit2.Response


class UserRepository {
    suspend fun getUserResponse(): Response<UserResponse> {
        return RetrofitClient.instance.getUserResponse()
    }
}
