package com.example.fitnessapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun LoadingScreen(
    loadingText: String,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(0.15f))
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.responsiveLayout.screenWidthPadding)
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .clip(MaterialTheme.responsiveLayout.cardShape)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(MaterialTheme.responsiveLayout.circularProgressIndicatorSize),
                strokeWidth = MaterialTheme.responsiveLayout.circularProgressIndicatorStrokeWidth,
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