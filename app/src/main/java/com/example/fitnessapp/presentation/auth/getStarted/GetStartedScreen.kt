package com.example.fitnessapp.presentation.auth.getStarted

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import com.example.fitnessapp.ui.theme.BASE_BUTTON_HEIGHT
import com.example.fitnessapp.ui.theme.BASE_BUTTON_SHAPE

@Composable
fun GetStartedScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.fillMaxHeight(0.15f))

        Text(
            text = "Let get started!",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = "This fitness app for you",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(Modifier.weight(2f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(BASE_BUTTON_HEIGHT),
            shape = BASE_BUTTON_SHAPE,
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

        Spacer(Modifier.weight(0.25f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(BASE_BUTTON_HEIGHT),
            shape = BASE_BUTTON_SHAPE,
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