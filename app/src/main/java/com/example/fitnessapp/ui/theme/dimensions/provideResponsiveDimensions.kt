package com.example.fitnessapp.ui.theme.dimensions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme.WindowSize
import com.example.fitnessapp.ui.theme.currentWindowSize

@Composable
fun provideResponsiveDimensions(): Dimensions {
    val currentWindowSize = currentWindowSize()

    return when(currentWindowSize) {
        WindowSize.Phone -> PhoneDimensions
        WindowSize.Tablet -> TabletDimensions
        WindowSize.Expanded -> ExpandedDimensions
    }
}

val PhoneDimensions = Dimensions(
    screenWidthPadding = 26.dp,

    spacingExtraSmall = 3.dp,
    spacingSmall = 6.dp,
    spacingMedium = 12.dp,
    spacingLarge = 20.dp,
    spacingExtraLarge = 30.dp,

    iconSmall = 20.dp,
    iconMedium = 24.dp,
    iconLarge = 30.dp,

    imageSmall = 45.dp,
    imageMedium = 65.dp,
    imageLarge = 90.dp,

    buttonHeight = 60.dp,
    digitBoxSize = 65.dp,
    dailyActivityCardHeight = 95.dp,
    datePickerHeight = 50.dp,
    exerciseCardHeight = 70.dp,

    cardShape = RoundedCornerShape(12.dp),

    outlineWidthSmall = 1.dp,
    outlineWidthLarge = 2.dp,

    elevation = 2.dp,

    circularProgressIndicatorSize = 55.dp,
    circularProgressIndicatorStrokeWidth = 7.dp,

    bottomNavigationBarHeight = 80.dp,
)

val TabletDimensions = Dimensions(
    screenWidthPadding = 45.dp,

    spacingExtraSmall = 4.dp,
    spacingSmall = 10.dp,
    spacingMedium = 16.dp,
    spacingLarge = 24.dp,
    spacingExtraLarge = 34.dp,

    iconSmall = 24.dp,
    iconMedium = 32.dp,
    iconLarge = 38.dp,

    imageSmall = 65.dp,
    imageMedium = 85.dp,
    imageLarge = 105.dp,

    buttonHeight = 80.dp,
    digitBoxSize = 100.dp,
    dailyActivityCardHeight = 130.dp,
    datePickerHeight = 65.dp,
    exerciseCardHeight = 90.dp,

    cardShape = RoundedCornerShape(16.dp),

    outlineWidthSmall = 2.dp,
    outlineWidthLarge = 3.dp,

    elevation = 4.dp,

    circularProgressIndicatorSize = 75.dp,
    circularProgressIndicatorStrokeWidth = 9.dp,

    bottomNavigationBarHeight = 100.dp,
)

val ExpandedDimensions = Dimensions(
    screenWidthPadding = 26.dp,

    spacingExtraSmall = 6.dp,
    spacingSmall = 12.dp,
    spacingMedium = 24.dp,
    spacingLarge = 32.dp,
    spacingExtraLarge = 48.dp,

    iconSmall = 24.dp,
    iconMedium = 32.dp,
    iconLarge = 40.dp,

    imageSmall = 45.dp,
    imageMedium = 65.dp,
    imageLarge = 85.dp,

    buttonHeight = 85.dp,
    digitBoxSize = 115.dp,
    dailyActivityCardHeight = 95.dp,
    datePickerHeight = 70.dp,
    exerciseCardHeight = 70.dp,

    cardShape = RoundedCornerShape(20.dp),

    outlineWidthSmall = 4.dp,
    outlineWidthLarge = 5.dp,

    elevation = 6.dp,

    circularProgressIndicatorSize = 90.dp,
    circularProgressIndicatorStrokeWidth = 11.dp,

    bottomNavigationBarHeight = 120.dp,
)

val LocalDimensions = staticCompositionLocalOf { PhoneDimensions }