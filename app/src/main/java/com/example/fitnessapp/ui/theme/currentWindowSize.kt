package com.example.fitnessapp.ui.theme

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo

enum class WindowSize { Phone, Tablet, Expanded }

@Composable
fun currentWindowSize(): WindowSize {
    val widthDp = LocalConfiguration.current.screenWidthDp

    return when {
        widthDp < 600 -> WindowSize.Phone
        widthDp < 840 -> WindowSize.Tablet
        else -> WindowSize.Expanded
    }
}