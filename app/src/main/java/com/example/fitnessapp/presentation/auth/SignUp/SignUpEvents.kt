package com.example.fitnessapp.presentation.auth.SignUp

import com.example.fitnessapp.presentation.auth.SignIn.SignInEvents

sealed class SignUpEvents {
    class OnNameFieldInput(val name: String): SignUpEvents()
    class OnPasswordFieldInput(val password: String): SignUpEvents()
    class SignUp(val name: String, val password: String): SignUpEvents()
}