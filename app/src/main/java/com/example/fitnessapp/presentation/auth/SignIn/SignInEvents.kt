package com.example.fitnessapp.presentation.auth.SignIn

sealed class SignInEvents {
    class OnNameFieldInput(val name: String): SignInEvents()
    class OnPasswordFieldInput(val name: String): SignInEvents()
    object SignIn: SignInEvents()
}