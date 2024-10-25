package com.bangnv.mvvmarchitecturepattern.utils

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Extension function to handle WindowInsets for edge-to-edge experience
fun View.applyWindowInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

        // Keep left + right + bottom padding from XML;
        // top = systemBars.top = height of the notification bar, which varies(thay đổi) by screen resolution and device model.
        v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
        insets
    }
}
