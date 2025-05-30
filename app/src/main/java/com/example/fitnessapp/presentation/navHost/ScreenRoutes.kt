package com.example.fitnessapp.presentation.navHost

import kotlinx.serialization.Serializable

@Serializable
object ScreenRoutes {
    @Serializable
    object SplashScreenRoute

    @Serializable
    object AuthNavGraph {
        @Serializable
        object GetStartedRoute

        @Serializable
        object SelectRoleRoute

        @Serializable
        object SignUpRoute

        @Serializable
        object SignInRoute
    }

    @Serializable
    object MainScreensNavGraph {

    }
}