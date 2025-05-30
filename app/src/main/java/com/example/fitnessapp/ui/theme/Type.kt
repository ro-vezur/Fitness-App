package com.example.fitnessapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    displayMedium = TextStyle(fontSize = 32.sp),
    displaySmall = TextStyle(fontSize = 28.sp),
    headlineLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineMedium = TextStyle(fontSize = 24.sp),
    headlineSmall = TextStyle(fontSize = 22.sp),
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = TextStyle(fontSize = 18.sp),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    bodyMedium = TextStyle(fontSize = 14.sp),
    bodySmall = TextStyle(fontSize = 12.sp),
    labelLarge = TextStyle(fontSize = 12.sp),
    labelMedium = TextStyle(fontSize = 10.sp),
    labelSmall = TextStyle(fontSize = 8.sp),
)