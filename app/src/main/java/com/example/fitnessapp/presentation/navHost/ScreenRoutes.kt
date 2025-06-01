package com.example.fitnessapp.presentation.navHost

import android.util.Log
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoutes() {

    @Serializable
    object SplashScreenRoute : ScreenRoutes()

    @Serializable
    object AuthNavGraph {
        @Serializable
        object GetStartedRoute :  ScreenRoutes()

        @Serializable
        object SelectRoleRoute :  ScreenRoutes()

        @Serializable
        object SignUpRoute :  ScreenRoutes()

        @Serializable
        object SignInRoute :  ScreenRoutes()

        @Serializable
        object ForgotPasswordNavHostRoute {
            @Serializable
            object EnterEmailRoute :  ScreenRoutes()

            @Serializable
            object EnterCodeRoute :  ScreenRoutes()

            @Serializable
            object ResetPasswordRoute :  ScreenRoutes()
        }
    }

    @Serializable
    data object MainScreensNavGraph {
        @Serializable
        data object Home :  ScreenRoutes()

        @Serializable
        data object Search :  ScreenRoutes()

        @Serializable
        data object Workouts :  ScreenRoutes()

        @Serializable
        data object Report :  ScreenRoutes()

        @Serializable
        data object Profile :  ScreenRoutes()

        val startRoute: ScreenRoutes = Home
    }

    companion object {
        private val routeMap: List<ScreenRoutes?> = listOf(
            SplashScreenRoute,
            AuthNavGraph.GetStartedRoute,
            AuthNavGraph.SelectRoleRoute,
            AuthNavGraph.SignUpRoute,
            AuthNavGraph.SignInRoute,
            AuthNavGraph.ForgotPasswordNavHostRoute.EnterEmailRoute,
            AuthNavGraph.ForgotPasswordNavHostRoute.EnterCodeRoute,
            AuthNavGraph.ForgotPasswordNavHostRoute.ResetPasswordRoute,
            MainScreensNavGraph.Home,
            MainScreensNavGraph.Search,
            MainScreensNavGraph.Workouts,
            MainScreensNavGraph.Report,
            MainScreensNavGraph.Profile
        )

        fun fromRoute(route: String?): ScreenRoutes? = routeMap.find { route.toString().contains(it.toString()) }
    }
}