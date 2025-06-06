package com.example.fitnessapp.presentation.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.extensions.modifier.MultipleClickCutter
import com.example.fitnessapp.extensions.modifier.get
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun TurnBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
) {

    IconButton(
        modifier = modifier,
        colors = colors,
        onClick = {
            MultipleClickCutter.get().executeEvent { onClick() }
        }
    ) {
        Icon(
            modifier = Modifier
                .size(MaterialTheme.responsiveLayout.iconLarge),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
        )
    }
}