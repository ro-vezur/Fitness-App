package com.example.fitnessapp.presentation.auth.SignIn

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.extensions.modifier.MultipleClickCutter
import com.example.fitnessapp.extensions.modifier.get
import com.example.fitnessapp.presentation.auth.AuthUiComponents.Validator.BasicValidatingInputTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.Validator.PasswordTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.Validator.ValidationResult
import com.example.fitnessapp.presentation.auth.AuthResult
import com.example.fitnessapp.presentation.components.LoadingScreen
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import com.example.fitnessapp.ui.theme.BASE_BUTTON_HEIGHT
import com.example.fitnessapp.ui.theme.BASE_BUTTON_SHAPE
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.blur

@Composable
fun SignInScreen(
    navController: NavController,
    uiState: SignInUiState,
    executeEvent: (SignInEvents) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    BackHandler {
        if(uiState.signInResult != AuthResult.Loading) { navController.navigateUp() }
    }

    SideEffect {
        Log.d("RECOMPOSE", "SignInScreen recomposed")
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .let { if (uiState.signInResult == AuthResult.Loading) it.blur(4.dp) else it }
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 35.dp, start = 20.dp),
                colors = IconButtonDefaults.iconButtonColors(),
                onClick = { MultipleClickCutter.get().executeEvent { navController.navigateUp() } }
            ) {
                Icon(
                    modifier = Modifier
                        .size(34.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(Modifier.fillMaxHeight(0.12f))

                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.headlineLarge
                )

                BasicValidatingInputTextField(
                    modifier = Modifier
                        .padding(top = 75.dp),
                    text =  uiState.nameInput ,
                    updateInput = { input ->
                        executeEvent(SignInEvents.OnNameFieldInput(input))
                    },
                    validationResult = ValidationResult.Unknown,
                    header = "Name",
                    placeholderText = "Enter Name",
                    elevation = 2.dp
                )

                PasswordTextField(
                    modifier = Modifier
                        .padding(top = 25.dp),
                    text =  uiState.passwordInput,
                    updateInput = { input ->
                        executeEvent(SignInEvents.OnPasswordFieldInput(input))
                    },
                    validationResult = ValidationResult.Unknown,
                    elevation = 2.dp
                )

                ForgotPasswordText(
                    onClick = {
                        Log.d("TAG", "click")
                    }
                )

                SignInButton(
                    onClick = {
                        executeEvent(SignInEvents.SignIn)
                        focusManager.clearFocus()
                    }
                )

                Text(
                    modifier = Modifier
                        .padding(top = 25.dp),
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
        LoadingScreen(
            modifier = Modifier
                .fillMaxWidth(0.85f),
            loadingText = "Sign In"
        )
    }
}

@Composable
private fun SignInButton(
    onClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = Modifier
            .padding(top = 45.dp)
            .fillMaxWidth()
            .height(BASE_BUTTON_HEIGHT),
        shape = BASE_BUTTON_SHAPE,
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

@Composable
private fun ForgotPasswordText(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { onClick() }
                .padding(6.dp),
            text = "Forgot password?",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}