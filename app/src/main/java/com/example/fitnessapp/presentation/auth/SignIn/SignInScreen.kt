package com.example.fitnessapp.presentation.auth.SignIn

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.presentation.auth.AuthResult
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.BasicValidatingInputTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.PasswordTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.ValidationResult
import com.example.fitnessapp.presentation.components.LoadingScreen
import com.example.fitnessapp.presentation.components.buttons.TurnBackButton
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import com.example.fitnessapp.ui.theme.responsiveLayout

@Composable
fun SignInScreen(
    navController: NavController,
    uiState: SignInUiState,
    executeEvent: (SignInEvents) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val interactionSource = remember { MutableInteractionSource() }

    BackHandler {
        if(uiState.signInResult != AuthResult.Loading) { navController.navigateUp() }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .let { if (uiState.signInResult == AuthResult.Loading) it.blur(4.dp) else it }
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
                    text = "Sign In",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(Modifier.fillMaxHeight(0.1f))

                BasicValidatingInputTextField(
                    modifier = Modifier,
                    text =  uiState.nameInput ,
                    updateInput = { input ->
                        executeEvent(SignInEvents.OnNameFieldInput(input))
                    },
                    validationResult = ValidationResult.Unknown,
                    header = "Name",
                    placeholderText = "Enter Name",
                    elevation = MaterialTheme.responsiveLayout.elevation
                )

                PasswordTextField(
                    modifier = Modifier
                        .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge),
                    text =  uiState.passwordInput,
                    updateInput = { input ->
                        executeEvent(SignInEvents.OnPasswordFieldInput(input))
                    },
                    validationResult = ValidationResult.Unknown,
                    elevation = MaterialTheme.responsiveLayout.elevation
                )

                ForgotPasswordText(
                    onClick = {
                        navController.navigate(ScreenRoutes.AuthNavGraph.ForgotPasswordNavHostRoute)
                    }
                )

                Spacer(modifier = Modifier.weight(0.25f))

                SignInButton(
                    onClick = {
                        executeEvent(SignInEvents.SignIn)
                        focusManager.clearFocus()
                    }
                )

                Text(
                    modifier = Modifier
                        .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge),
                    text = buildAnnotatedString {
                        append("Don't have account? ")
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

                        append("Sign up")
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
                                "Sign up",
                                linkInteractionListener = {
                                    navController.navigate(ScreenRoutes.AuthNavGraph.SignUpRoute) {
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

        SignUpLoadingScreen(
            signInResult = uiState.signInResult )
    }
}

@Composable
private fun SignUpLoadingScreen(
    signInResult: AuthResult
) {
    if(signInResult == AuthResult.Loading) {
        LoadingScreen(loadingText = "Sign In")
    }
}

@Composable
private fun ForgotPasswordText(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(top = MaterialTheme.responsiveLayout.spacingSmall)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        Text(
            modifier = Modifier
                .clip(MaterialTheme.responsiveLayout.cardShape)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { onClick() }
                .padding(MaterialTheme.responsiveLayout.spacingSmall),
            text = "Forgot password?",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SignInButton(
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.responsiveLayout.buttonHeight),
        shape = MaterialTheme.responsiveLayout.cardShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        interactionSource = interactionSource,
        onClick = { onClick() }
    ) {
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}