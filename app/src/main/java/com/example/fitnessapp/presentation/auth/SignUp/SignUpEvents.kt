package com.example.fitnessapp.presentation.auth.SignUp

sealed class SignUpEvents {
    class OnNameFieldInput(val name: String): SignUpEvents()
    class OnPasswordFieldInput(val password: String): SignUpEvents()
    class SignUp(val name: String, val password: String): SignUpEvents()
}