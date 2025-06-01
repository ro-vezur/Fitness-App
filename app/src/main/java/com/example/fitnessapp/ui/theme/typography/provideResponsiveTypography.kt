package com.example.fitnessapp.ui.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitnessapp.ui.theme.WindowSize
import com.example.fitnessapp.ui.theme.currentWindowSize

@Composable
fun provideResponsiveTypography(): Typography {
    val currentWindowSize = currentWindowSize()

    return when(currentWindowSize) {
        WindowSize.Phone -> PhoneTypography
        WindowSize.Tablet -> TabletTypography
        WindowSize.Expanded -> ExpandedTypography
    }
}