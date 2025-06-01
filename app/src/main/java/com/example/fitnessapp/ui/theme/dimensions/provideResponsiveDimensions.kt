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
    spacingExtraSmall = 2.dp,
    spacingSmall = 6.dp,
    spacingMedium = 12.dp,
    spacingLarge = 16.dp,
    spacingExtraLarge = 26.dp,

    iconSmall = 16.dp,
    iconMedium = 24.dp,
    iconLarge = 30.dp,

    buttonHeight = 60.dp,
    digitBoxSize = 65.dp,
    cardShape = RoundedCornerShape(12.dp),

    outlineWidthSmall = 1.dp,
    outlineWidthLarge = 2.dp,

    elevation = 2.dp,

    circularProgressIndicatorSize = 55.dp,
    circularProgressIndicatorStrokeWidth = 7.dp,

    bottomNavigationBarHeight = 80.dp,
)

val TabletDimensions = Dimensions(
    spacingExtraSmall = 4.dp,
    spacingSmall = 10.dp,
    spacingMedium = 16.dp,
    spacingLarge = 24.dp,
    spacingExtraLarge = 34.dp,

    iconSmall = 24.dp,
    iconMedium = 32.dp,
    iconLarge = 38.dp,

    buttonHeight = 80.dp,
    digitBoxSize = 100.dp,
    cardShape = RoundedCornerShape(16.dp),

    outlineWidthSmall = 2.dp,
    outlineWidthLarge = 3.dp,

    elevation = 4.dp,

    circularProgressIndicatorSize = 75.dp,
    circularProgressIndicatorStrokeWidth = 9.dp,

    bottomNavigationBarHeight = 100.dp,
)

val ExpandedDimensions = Dimensions(
    spacingExtraSmall = 6.dp,
    spacingSmall = 12.dp,
    spacingMedium = 24.dp,
    spacingLarge = 32.dp,
    spacingExtraLarge = 48.dp,

    iconSmall = 24.dp,
    iconMedium = 32.dp,
    iconLarge = 40.dp,

    buttonHeight = 85.dp,
    digitBoxSize = 115.dp,
    cardShape = RoundedCornerShape(20.dp),

    outlineWidthSmall = 4.dp,
    outlineWidthLarge = 5.dp,

    elevation = 6.dp,

    circularProgressIndicatorSize = 90.dp,
    circularProgressIndicatorStrokeWidth = 11.dp,

    bottomNavigationBarHeight = 120.dp,
)

val LocalDimensions = staticCompositionLocalOf { PhoneDimensions }