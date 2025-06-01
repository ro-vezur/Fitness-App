package com.example.fitnessapp.presentation.mainScreens

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fitnessapp.presentation.navHost.ScreenRoutes

fun NavGraphBuilder.mainScreensNavGraph(
    navController: NavController,
    showBottomNavigationBar: (Boolean) -> Unit,
) {
    navigation<ScreenRoutes.MainScreensNavGraph>(
        startDestination = ScreenRoutes.MainScreensNavGraph.startRoute
    ) {

        composable<ScreenRoutes.MainScreensNavGraph.Home> {
            showBottomNavigationBar(true)
        }

        composable<ScreenRoutes.MainScreensNavGraph.Search> {
            showBottomNavigationBar(true)
        }

        composable<ScreenRoutes.MainScreensNavGraph.Workouts> {
            showBottomNavigationBar(true)
        }

        composable<ScreenRoutes.MainScreensNavGraph.Report> {
            showBottomNavigationBar(true)
        }

        composable<ScreenRoutes.MainScreensNavGraph.Profile> {
            showBottomNavigationBar(true)
        }
    }
}