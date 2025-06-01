package com.example.fitnessapp.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fitnessapp.presentation.auth.SignIn.SignInScreen
import com.example.fitnessapp.presentation.auth.SignIn.SignInViewModel
import com.example.fitnessapp.presentation.auth.SignUp.SignUpScreen
import com.example.fitnessapp.presentation.auth.SignUp.SignUpViewModel
import com.example.fitnessapp.presentation.auth.forgotPassword.ForgotPasswordNavHost
import com.example.fitnessapp.presentation.auth.getStarted.GetStartedScreen
import com.example.fitnessapp.presentation.navHost.ScreenRoutes


fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    showBottomNavigationBar: (Boolean) -> Unit,
) {
    navigation<ScreenRoutes.AuthNavGraph>(startDestination = ScreenRoutes.AuthNavGraph.GetStartedRoute) {
        showBottomNavigationBar(false)

        composable<ScreenRoutes.AuthNavGraph.GetStartedRoute> {
            GetStartedScreen(
                navController = navController
            )
        }

        composable<ScreenRoutes.AuthNavGraph.SignUpRoute> {
            val signUpViewModel: SignUpViewModel = hiltViewModel()
            val uiState by signUpViewModel.uiState.collectAsStateWithLifecycle()

            SignUpScreen(
                navController = navController,
                uiState = uiState,
                executeEvent = signUpViewModel::executeEvent
            )
        }

        composable<ScreenRoutes.AuthNavGraph.SignInRoute> {
            val signInViewModel: SignInViewModel = hiltViewModel()
            val uiState by signInViewModel.uiState.collectAsStateWithLifecycle()

            SignInScreen(
                navController = navController,
                uiState = uiState ,
                executeEvent = signInViewModel::executeEvent
            )
        }

        composable<ScreenRoutes.AuthNavGraph.ForgotPasswordNavHostRoute> {
            ForgotPasswordNavHost(
                authNavController = navController
            )
        }
    }
}