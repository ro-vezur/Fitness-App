package com.example.fitnessapp.presentation.auth.forgotPassword.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields.CodeTextField
import com.example.fitnessapp.ui.theme.BASE_SCREEN_WIDTH_RATIO
import com.example.fitnessapp.ui.theme.dimensions.LocalDimensions

@Composable
fun EnterCodeScreen(
    email: String,
    code: String,
    onCodeInputChange: (String) -> Unit,
    verifyCode: () -> Unit,
) {
    val localDimensions = LocalDimensions.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(BASE_SCREEN_WIDTH_RATIO),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.weight(1f))

        Text(
            text = "Enter Code",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            modifier = Modifier
                .padding(top = localDimensions.spacingLarge),
            text = buildAnnotatedString {
                append("We've sent a code to your email,")
                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    start = 0,
                    end = length
                )

                val secondPartStartIndex = length

                append("\n$email")
                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    start = secondPartStartIndex,
                    end = length
                )
            },
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
            )

        Spacer(Modifier.weight(1f))

        CodeTextField(
            modifier = Modifier,
            code = code,
            onCodeInputChange = onCodeInputChange
        )

        Spacer(Modifier.weight(0.5f))

        VerifyCodeButton(
            onClick = {
                verifyCode()
            }
        )
    }
}

@Composable
private fun VerifyCodeButton(
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
            text = "Verify",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}