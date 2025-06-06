package com.example.fitnessapp.presentation.auth.getStarted

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun GetStartedScreen(
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.responsiveLayout.screenWidthPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Text(
            text = "Let get started!",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            modifier = Modifier
                .padding(top = MaterialTheme.responsiveLayout.spacingLarge),
            text = "This fitness app for you",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.weight(2f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.responsiveLayout.buttonHeight),
            shape = MaterialTheme.responsiveLayout.cardShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = {
               navController.navigate(ScreenRoutes.AuthNavGraph.SignUpRoute) {
                   launchSingleTop = true
               }
            }
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Button(
            modifier = Modifier
                .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge)
                .fillMaxWidth()
                .height(MaterialTheme.responsiveLayout.buttonHeight),
            shape = MaterialTheme.responsiveLayout.cardShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            onClick = {
                navController.navigate(ScreenRoutes.AuthNavGraph.SignInRoute) {
                    launchSingleTop = true
                }
            }
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(Modifier.weight(1.5f))
    }
}