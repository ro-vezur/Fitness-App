package com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme.responsiveLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    updateInput: (String) -> Unit,
    validationResult: ValidationResult,
    header: String = "Password",
    placeholderText: String = "Enter Password",
    elevation: Dp = 0.dp,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isVisible by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.titleMedium
        )

        BasicTextField(
            modifier = Modifier
                .padding(top = MaterialTheme.responsiveLayout.spacingLarge, start = MaterialTheme.responsiveLayout.spacingSmall)
                .height(MaterialTheme.responsiveLayout.buttonHeight)
                .fillMaxWidth()
                .shadow(elevation, MaterialTheme.responsiveLayout.cardShape)
                .border(
                    width = MaterialTheme.responsiveLayout.outlineWidthSmall,
                    color = when {
                        validationResult is ValidationResult.Error -> MaterialTheme.colorScheme.error
                        text.isNotEmpty() -> MaterialTheme.colorScheme.primary
                        else -> Color.Transparent
                    },
                    shape = MaterialTheme.responsiveLayout.cardShape
                ),
            value = text,
            onValueChange = updateInput,
            singleLine = true,
            visualTransformation = if(isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            cursorBrush = SolidColor(if(text.isEmpty()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                innerTextField = innerTextField,
                shape = MaterialTheme.responsiveLayout.cardShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedTextColor = MaterialTheme.colorScheme.secondary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    selectionColors = TextSelectionColors(
                        handleColor = MaterialTheme.colorScheme.primary,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ),
                value = text,
                enabled = true,
                singleLine = true,
                visualTransformation = if(isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                interactionSource = interactionSource,
                contentPadding = PaddingValues(horizontal = MaterialTheme.responsiveLayout.spacingMedium),
                placeholder = {
                    Text(
                        text = placeholderText,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier
                            .padding(end = MaterialTheme.responsiveLayout.spacingSmall),
                        onClick = {
                            isVisible = !isVisible
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(MaterialTheme.responsiveLayout.iconMedium),
                            imageVector = if(isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
            )
        }
    }
}