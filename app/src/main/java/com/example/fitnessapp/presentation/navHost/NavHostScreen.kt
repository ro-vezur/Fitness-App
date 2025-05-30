package com.example.fitnessapp.presentation.navHost

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
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

@Composable
fun NavHostScreen(

) {
    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    NavHost(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        navController = navHostController,
        startDestination = ScreenRoutes.AuthNavGraph
    ) {
        authNavGraph(
            navController = navHostController
        )
    }
}

@Preview
@Composable
private fun MainNavHostPreview() {
    FitnessAppTheme {
        NavHostScreen()
    }
}