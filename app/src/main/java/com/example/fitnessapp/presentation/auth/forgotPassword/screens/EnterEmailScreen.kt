package com.example.fitnessapp.presentation.auth.forgotPassword.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.BasicValidatingInputTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.ValidationResult
import com.example.fitnessapp.ui.theme.BASE_SCREEN_WIDTH_RATIO
import com.example.fitnessapp.ui.theme.dimensions.LocalDimensions

@Composable
fun EnterEmailScreen(
    emailInput: String,
    onEmailInputChange: (String) -> Unit,
    sendCode: () -> Unit,
) {
    val localDimensions = LocalDimensions.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(BASE_SCREEN_WIDTH_RATIO),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))

        Text(
            text = "Forgot Password",
            style = MaterialTheme.typography.headlineLarge,
        )

        Text(
            modifier = Modifier
                .padding(top = localDimensions.spacingLarge),
            text = "Enter your registered email to receive the verification code to reset your password",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(1f))

        BasicValidatingInputTextField(
            modifier = Modifier,
            text = emailInput ,
            updateInput = { input ->
                onEmailInputChange(input)
            },
            validationResult = ValidationResult.Unknown,
            header = "Email",
            placeholderText = "Enter Email",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            elevation = localDimensions.elevation
        )

        Spacer(Modifier.weight(0.5f))

        ResetPasswordButton(
            onClick = { sendCode() }
        )
    }
}

@Composable
private fun ResetPasswordButton(
    onClick: () -> Unit
) {
    val localDimensions = LocalDimensions.current
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(localDimensions.buttonHeight),
        shape = localDimensions.cardShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        interactionSource = interactionSource,
        onClick = { onClick() }
    ) {
        Text(
            text = "Send a Code",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}