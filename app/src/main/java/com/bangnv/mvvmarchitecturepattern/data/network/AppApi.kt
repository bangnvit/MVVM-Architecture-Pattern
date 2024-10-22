package com.bangnv.mvvmarchitecturepattern.data.network

import com.bangnv.mvvmarchitecturepattern.data.network.NetworkEndPoint.GET_ONE_USER
import com.bangnv.mvvmarchitecturepattern.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface AppApi {

//    @GET("users/{id}")
//    suspend fun getUserById(@Path("id") userId: Int): Response<UserResponse>

    @GET(GET_ONE_USER)
    // Because creating api with free link so can not access by id...
    suspend fun getUserResponse(): Response<UserResponse>
}