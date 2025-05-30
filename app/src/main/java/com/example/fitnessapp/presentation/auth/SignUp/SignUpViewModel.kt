package com.example.fitnessapp.presentation.auth.SignUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.presentation.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

): ViewModel() {
    private val _uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun executeEvent(event: SignUpEvents) {
        when(event) {
            is SignUpEvents.OnNameFieldInput -> onNameFieldInput(event.name)
            is SignUpEvents.OnPasswordFieldInput -> onPasswordFieldInput(event.password)
            is SignUpEvents.SignUp -> signUp(event.name,event.password)
        }
    }

    private fun onNameFieldInput(input: String) = viewModelScope.launch {
        _uiState.update { it.copy(nameInput = input) }
    }

    private fun onPasswordFieldInput(input: String) = viewModelScope.launch {
        _uiState.update { it.copy(passwordInput = input) }
    }

    private fun signUp(name: String, password: String) = viewModelScope.launch {

        _uiState.update {
            it.copy(signUpResult = AuthResult.Loading)
        }

        delay(2000)

        _uiState.update {
            it.copy(signUpResult = AuthResult.Success)
        }
    }

}