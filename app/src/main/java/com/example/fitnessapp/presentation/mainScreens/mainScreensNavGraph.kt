package com.example.fitnessapp.presentation.mainScreens

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fitnessapp.presentation.mainScreens.HomeScreen.HomeScreen
import com.example.fitnessapp.presentation.mainScreens.HomeScreen.HomeUiState
import com.example.fitnessapp.presentation.mainScreens.HomeScreen.HomeViewModel
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import androidx.compose.runtime.getValue
import com.example.fitnessapp.presentation.mainScreens.NewWorkoutScreen.WorkoutsScreen
import com.example.fitnessapp.presentation.mainScreens.NewWorkoutScreen.WorkoutsViewModel

fun NavGraphBuilder.mainScreensNavGraph(
    navController: NavController,
    showBottomNavigationBar: (Boolean) -> Unit,
) {
    navigation<ScreenRoutes.MainScreensNavGraph>(
        startDestination = ScreenRoutes.MainScreensNavGraph.startRoute
    ) {

        composable<ScreenRoutes.MainScreensNavGraph.Home> {
            showBottomNavigationBar(true)

            val homeViewModel: HomeViewModel = hiltViewModel()
            val uiState: HomeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

            HomeScreen(
                uiState = uiState
            )
        }

        composable<ScreenRoutes.MainScreensNavGraph.Search> {
            showBottomNavigationBar(true)
        }

        composable<ScreenRoutes.MainScreensNavGraph.Workouts> {
            showBottomNavigationBar(true)

            val workoutsViewModel: WorkoutsViewModel = hiltViewModel()
            val uiState by workoutsViewModel.uiState.collectAsStateWithLifecycle()

            WorkoutsScreen(
                uiState = uiState,
                executeEvent = workoutsViewModel::executeEvent
            )
        }

        composable<ScreenRoutes.MainScreensNavGraph.Report> {
            showBottomNavigationBar(true)
        }

        composable<ScreenRoutes.MainScreensNavGraph.Profile> {
            showBottomNavigationBar(true)
        }
    }
}