package com.example.fitnessapp.presentation.auth.AuthUiComponents.InputFields

sealed class ValidationResult(message: String? = null) {
    data object Unknown: ValidationResult()
    data object Success: ValidationResult()
    class Error(errorMessage: String): ValidationResult(errorMessage)
}