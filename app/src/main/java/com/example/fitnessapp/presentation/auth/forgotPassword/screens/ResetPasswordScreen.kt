package com.example.fitnessapp.presentation.auth.forgotPassword.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.PasswordTextField
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.ValidationResult
import com.example.fitnessapp.ui.theme.BASE_SCREEN_WIDTH_RATIO
import com.example.fitnessapp.ui.theme.dimensions.LocalDimensions

@Composable
fun ResetPasswordScreen(
    newPasswordInput: String,
    confirmPasswordInput: String,
    onNewPasswordInputChange: (String) -> Unit,
    onConfirmPasswordInputChange: (String) -> Unit,
    resetPassword: () -> Unit,
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
            text = "Reset Password",
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(Modifier.weight(1f))

        PasswordTextField(
            modifier = Modifier,
            text = newPasswordInput ,
            updateInput = { input ->
                onNewPasswordInputChange(input)
            },
            validationResult = ValidationResult.Unknown,
            elevation = localDimensions.elevation
        )

        PasswordTextField(
            modifier = Modifier
                .padding(top = localDimensions.spacingExtraLarge),
            text = confirmPasswordInput ,
            updateInput = { input ->
                onConfirmPasswordInputChange(input)
            },
            validationResult = ValidationResult.Unknown,
            header = "Confirm Password",
            placeholderText = "Confirm Your Password",
            elevation = localDimensions.elevation
        )

        Spacer(Modifier.weight(0.5f))

        ResetPasswordButton(
            onClick = { resetPassword() }
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
            .padding(top = localDimensions.spacingExtraLarge)
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
            text = "Reset Password",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}