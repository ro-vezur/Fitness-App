package com.example.fitnessapp.presentation.auth.SignUp

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.BasicValidatingInputTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.PasswordTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.ValidationResult
import com.example.fitnessapp.presentation.auth.AuthResult
import com.example.fitnessapp.presentation.components.LoadingScreen
import com.example.fitnessapp.presentation.components.buttons.TurnBackButton
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun SignUpScreen(
    navController: NavController,
    uiState: SignUpUiState,
    executeEvent: (SignUpEvents) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    BackHandler {
        if(uiState.signUpResult != AuthResult.Loading) {
            navController.navigateUp()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .let { if (uiState.signUpResult == AuthResult.Loading) it.blur(4.dp) else it }
        ) {
            TurnBackButton(
                modifier = Modifier
                    .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge, start = MaterialTheme.responsiveLayout.spacingLarge)
                    .align(Alignment.TopStart),
                onClick = {
                    navController.navigateUp()
                }
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = MaterialTheme.responsiveLayout.screenWidthPadding)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.fillMaxHeight(0.12f))

                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(Modifier.fillMaxHeight(0.1f))

                BasicValidatingInputTextField(
                    text =  uiState.nameInput ,
                    updateInput = { input ->
                        executeEvent(SignUpEvents.OnNameFieldInput(input))
                    },
                    validationResult = ValidationResult.Unknown,
                    header = "Name",
                    placeholderText = "Enter Name",
                    elevation = MaterialTheme.responsiveLayout.elevation
                )

                PasswordTextField(
                    modifier = Modifier
                        .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge),
                    text =  uiState.passwordInput ,
                    updateInput = { input ->
                        executeEvent(SignUpEvents.OnPasswordFieldInput(input))
                    },
                    validationResult = ValidationResult.Unknown,
                    elevation = MaterialTheme.responsiveLayout.elevation
                )

                Spacer(modifier = Modifier.weight(0.2f))

                Button(
                    modifier = Modifier
                        .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge)
                        .fillMaxWidth()
                        .height(MaterialTheme.responsiveLayout.buttonHeight),
                    shape = MaterialTheme.responsiveLayout.cardShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        executeEvent(SignUpEvents.SignUp(uiState.nameInput, uiState.passwordInput))
                        focusManager.clearFocus()
                    }
                ) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge),
                    text = buildAnnotatedString {
                        append("Already have account? ")
                        addStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                            ),
                            start = 0,
                            end = length
                        )
                        val startIndex = length

                        append("Sign In")
                        addStyle(
                            style = SpanStyle(
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                            ),
                            start = startIndex,
                            end = length
                        )
                        val endIndex = length

                        addLink(
                            LinkAnnotation.Clickable(
                                "Sign In",
                                linkInteractionListener = {
                                    navController.navigate(ScreenRoutes.AuthNavGraph.SignInRoute) {
                                        popUpTo(ScreenRoutes.AuthNavGraph.GetStartedRoute)
                                    }
                                }
                            ),
                            start = startIndex,
                            end = endIndex
                        )
                    }
                )

                Spacer(Modifier.weight(1f))
            }
        }

        if(uiState.signUpResult == AuthResult.Loading) {
            LoadingScreen(loadingText = "Sign Up")
        }
    }
}