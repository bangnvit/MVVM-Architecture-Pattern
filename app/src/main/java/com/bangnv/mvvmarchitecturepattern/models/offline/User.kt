package com.bangnv.mvvmarchitecturepattern.models.offline

import androidx.databinding.ObservableField
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val username: ObservableField<String> = ObservableField(),
    val email: ObservableField<String> = ObservableField()
)