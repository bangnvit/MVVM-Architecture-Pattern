package com.bangnv.mvvmarchitecturepattern.models

import androidx.databinding.ObservableField

data class User(
    val username: ObservableField<String> = ObservableField(),
    val email: ObservableField<String> = ObservableField()
)
