package com.example.fitnessapp.presentation.auth.SignUp

import com.example.fitnessapp.presentation.auth.AuthResult

data class SignUpUiState(
    val nameInput: String = "",
    val passwordInput: String = "",
    val signUpResult: AuthResult = AuthResult.Unknown,
)