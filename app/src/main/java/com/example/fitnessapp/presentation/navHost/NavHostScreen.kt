package com.example.fitnessapp.presentation.navHost

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.presentation.auth.authNavGraph
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.composable
import com.example.fitnessapp.presentation.components.bottomNavigationBar.BottomNavigationBar
import com.example.fitnessapp.presentation.mainScreens.mainScreensNavGraph
import kotlinx.coroutines.delay
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun NavHostScreen(

) {
    val navHostController = rememberNavController()
    var showBottomNavigationBar by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)

    //    navHostController.navigate(ScreenRoutes.MainScreensNavGraph)
    }

    Scaffold(
        bottomBar = {
            if(showBottomNavigationBar) {
                BottomNavigationBar(
                    navController = navHostController
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            navController = navHostController,
            startDestination = ScreenRoutes.MainScreensNavGraph,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(durationMillis = 200)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(durationMillis = 200)
                )
            }
        ) {
            composable<ScreenRoutes.SplashScreenRoute> {
                showBottomNavigationBar = false
            }

            authNavGraph(
                navController = navHostController,
                showBottomNavigationBar = { showBottomNavigationBar = it }
            )

            mainScreensNavGraph(
                navController = navHostController,
                showBottomNavigationBar = { showBottomNavigationBar = it }
            )
        }
    }
}

@Preview
@Composable
private fun MainNavHostPreview() {
    FitnessAppTheme {
        NavHostScreen()
    }
}