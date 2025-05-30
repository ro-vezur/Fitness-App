package com.example.fitnessapp.presentation.auth

sealed class AuthResult(val errorMessage: String? = null) {
    object Unknown: AuthResult()
    object Loading: AuthResult()
    object Success: AuthResult()
    class Error(errorMessage: String): AuthResult(errorMessage)
}