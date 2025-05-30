package com.example.fitnessapp.presentation.auth.AuthUiComponents.Validator

import android.R.attr.text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme.BASE_BUTTON_HEIGHT
import com.example.fitnessapp.ui.theme.BASE_BUTTON_SHAPE

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    updateInput: (String) -> Unit,
    validationResult: ValidationResult,
    elevation: Dp = 0.dp,
) {
    var isVisible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Password",
            style = MaterialTheme.typography.titleLarge
        )

        TextField(
            modifier = Modifier
                .padding(top = 16.dp,start = 5.dp)
                .height(BASE_BUTTON_HEIGHT)
                .fillMaxWidth()
                .shadow(elevation,BASE_BUTTON_SHAPE)
                .border(
                    width = 1.dp,
                    color = when {
                        validationResult is ValidationResult.Error -> MaterialTheme.colorScheme.error
                        text.isNotEmpty() -> MaterialTheme.colorScheme.primary
                        else -> Color.Transparent
                    },
                    shape = BASE_BUTTON_SHAPE
                ),
            shape = BASE_BUTTON_SHAPE,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
                selectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.onPrimary
                )
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            value = text,
            onValueChange = updateInput,
            placeholder = {
                Text(
                    text = "Enter Password",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(end = 5.dp),
                    onClick = {
                        isVisible = !isVisible
                    }
                ) {
                    Icon(
                        imageVector = if(isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            visualTransformation = if(isVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}