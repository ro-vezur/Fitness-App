package com.example.fitnessapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.example.fitnessapp.ui.theme.dimensions.Dimensions
import com.example.fitnessapp.ui.theme.dimensions.provideResponsiveDimensions

@get:Composable
val MaterialTheme.responsiveLayout : Dimensions
     get() = provideResponsiveDimensions()