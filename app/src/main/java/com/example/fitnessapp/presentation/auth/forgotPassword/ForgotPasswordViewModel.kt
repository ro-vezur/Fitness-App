package com.example.fitnessapp.presentation.auth.forgotPassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(

): ViewModel() {

    var email: String by mutableStateOf("")
        private set

    var code: String by mutableStateOf("")
        private set

    var newPassword by mutableStateOf("")
        private set

    var passwordConfirm by mutableStateOf("")
        private set

    fun updateEmailInput(input: String) = viewModelScope.launch {
        email = input
    }

    fun updateCodeInput(input: String) = viewModelScope.launch {
        code = input
    }

    fun updateNewPasswordInput(input: String) = viewModelScope.launch {
        newPassword = input
    }

    fun updatePasswordConfirmInput(input: String) = viewModelScope.launch {
        passwordConfirm = input
    }
}