package com.example.fitnessapp.presentation.auth.SignIn

import androidx.compose.runtime.Immutable
import com.example.fitnessapp.presentation.auth.AuthResult

@Immutable
data class SignInUiState(
    val nameInput: String = "",
    val passwordInput: String = "",
    val signInResult: AuthResult = AuthResult.Unknown
)
