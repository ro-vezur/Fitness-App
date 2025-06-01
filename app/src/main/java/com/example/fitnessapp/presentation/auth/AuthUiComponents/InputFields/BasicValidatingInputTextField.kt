package com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme.dimensions.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicValidatingInputTextField(
    modifier: Modifier = Modifier,
    text: String,
    updateInput: (String) -> Unit,
    validationResult: ValidationResult,
    header: String,
    placeholderText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    elevation: Dp = 0.dp,
) {
    val localDimensions = LocalDimensions.current
    val interactionSource = remember { MutableInteractionSource() }

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
                .padding(top = localDimensions.spacingLarge, start = localDimensions.spacingSmall)
                .height(localDimensions.buttonHeight)
                .fillMaxWidth()
                .shadow(elevation, localDimensions.cardShape)
                .border(
                    width = localDimensions.outlineWidthSmall,
                    color = when {
                        validationResult is ValidationResult.Error -> MaterialTheme.colorScheme.error
                        text.isNotEmpty() -> MaterialTheme.colorScheme.primary
                        else -> Color.Transparent
                    },
                    shape = localDimensions.cardShape
                ),
            value = text,
            onValueChange = updateInput,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            cursorBrush = SolidColor(if(text.isEmpty()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                innerTextField = innerTextField,
                interactionSource = interactionSource,
                shape = localDimensions.cardShape,
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
                        backgroundColor = MaterialTheme.colorScheme.onPrimary
                    )
                ),
                value = text,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                contentPadding = PaddingValues(horizontal = localDimensions.spacingMedium),
                placeholder = {
                    Text(
                        text = placeholderText,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
        }
    }
}