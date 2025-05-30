package com.example.fitnessapp.presentation.auth.SignIn

import com.example.fitnessapp.presentation.auth.SignUp.SignUpEvents

sealed class SignInEvents {
    class OnNameFieldInput(val name: String): SignInEvents()
    class OnPasswordFieldInput(val name: String): SignInEvents()
    object SignIn: SignInEvents()
}