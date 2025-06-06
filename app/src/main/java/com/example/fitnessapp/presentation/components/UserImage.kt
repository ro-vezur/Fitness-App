package com.example.fitnessapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun UserImage(
    modifier: Modifier = Modifier,
    userImageUrl: String?,
    size: Dp = MaterialTheme.responsiveLayout.imageMedium
) {
    Box(
        modifier = modifier
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        if (userImageUrl != null) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                model = userImageUrl,
                contentDescription = "user image url"
            )
        } else {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                painter = painterResource(R.drawable.empty_profile),
                contentDescription = "empty user image"
            )
        }
    }
}