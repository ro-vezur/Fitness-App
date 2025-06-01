package com.example.fitnessapp.presentation.components.bottomNavigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fitnessapp.presentation.navHost.ScreenRoutes

enum class BottomNavigationBarItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: ScreenRoutes,
) {
    HOME("Home", Icons.Outlined.Home, Icons.Filled.Home, ScreenRoutes.MainScreensNavGraph.Home),
    SEARCH("Search", Icons.Outlined.Search, Icons.Filled.Search, ScreenRoutes.MainScreensNavGraph.Search),
    WORKOUTS("Workouts", Icons.Outlined.CalendarMonth, Icons.Filled.CalendarMonth, ScreenRoutes.MainScreensNavGraph.Workouts),
    PROFILE("Profile", Icons.Outlined.Person, Icons.Filled.Person, ScreenRoutes.MainScreensNavGraph.Profile)
}