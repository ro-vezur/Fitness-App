package com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields

import android.R.attr.singleLine
import android.R.attr.text
import android.R.attr.textStyle
import android.util.Log
import android.util.Log.i
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.CODE_LENGTH
import com.example.fitnessapp.ui.theme.dimensions.LocalDimensions

@Composable
fun CodeTextField(
    modifier: Modifier = Modifier,
    code: String,
    onCodeInputChange: (String) -> Unit,
    codeLength: Int = CODE_LENGTH,
    placeholderDigit: String = "0"
) {
    val localDimensions = LocalDimensions.current

    BasicTextField(
        modifier = modifier
            .height(localDimensions.buttonHeight)
            .fillMaxWidth(),
        value = code,
        onValueChange = { input ->
            if(input.length <= codeLength) {
                Log.d("code input",input)
                onCodeInputChange(input)
            }

        },
        singleLine = true,
        cursorBrush = SolidColor(Color.Transparent),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for(index in 0..codeLength-1) {
                val digit = when {
                    code.isBlank() -> ""
                    code.length <= index -> ""
                    else -> code[index].toString()
                }
                DigitCard(
                    digit = digit,
                    placeholderDigit = placeholderDigit
                )
            }
        }
    }
}

@Composable
fun DigitCard(
    digit: String,
    placeholderDigit: String
) {
    val localDimensions = LocalDimensions.current

    Box(
        modifier = Modifier
            .size(localDimensions.digitBoxSize)
            .clip(localDimensions.cardShape)
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = localDimensions.outlineWidthSmall,
                color = if (digit.isBlank()) Color.Transparent else MaterialTheme.colorScheme.primary,
                shape = localDimensions.cardShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit.ifBlank { placeholderDigit },
            style = MaterialTheme.typography.titleLarge,
            color = if(digit.isBlank()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
        )
    }
}