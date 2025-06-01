package com.example.fitnessapp.presentation.components

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme.dimensions.LocalDimensions

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    loadingText: String,
) {
    val localDimensions = LocalDimensions.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(0.15f))
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight(0.25f)
                .clip(localDimensions.cardShape)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(localDimensions.circularProgressIndicatorSize),
                strokeWidth = localDimensions.circularProgressIndicatorStrokeWidth,
                trackColor = MaterialTheme.colorScheme.surface,
                strokeCap = StrokeCap.Round
            )

            Text(
                text = loadingText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}