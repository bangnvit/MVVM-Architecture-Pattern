package com.bangnv.mvvmarchitecturepattern.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "user_id") val id: Int,
    @Json(name = "user_name") val username: String,
    val email: String // Same the name from JSON
)
