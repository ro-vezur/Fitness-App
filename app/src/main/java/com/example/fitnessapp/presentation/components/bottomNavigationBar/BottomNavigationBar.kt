package com.example.fitnessapp.presentation.components.bottomNavigationBar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val interactionSource = remember { MutableInteractionSource() }
    var currentNavigationBarRoute: ScreenRoutes by remember { mutableStateOf(ScreenRoutes.MainScreensNavGraph.startRoute) }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            val currentRoute = destination.route

            ScreenRoutes.fromRoute(currentRoute)?.let { routeObject ->
                currentNavigationBarRoute = routeObject
            }

        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Row(
        modifier = Modifier
            .height(MaterialTheme.responsiveLayout.bottomNavigationBarHeight)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavigationBarItem.entries.forEach { item ->
            val isSelected = currentNavigationBarRoute == item.route

            Column(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        if (!isSelected) {
                            currentNavigationBarRoute = item.route
                            navController.navigate(item.route)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.responsiveLayout.spacingSmall)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.responsiveLayout.spacingMedium)
                        .size(MaterialTheme.responsiveLayout.iconLarge),
                    imageVector = if(isSelected) item.selectedIcon else item.unselectedIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}