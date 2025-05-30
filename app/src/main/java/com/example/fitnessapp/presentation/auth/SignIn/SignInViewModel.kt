package com.example.fitnessapp.presentation.auth.SignIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.presentation.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SignInViewModel @Inject constructor(

): ViewModel() {
    private val _uiState: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = SignInUiState()
    )

    fun executeEvent(event: SignInEvents) {
        when(event) {
            is SignInEvents.OnNameFieldInput -> onNameFieldInput(event.name)
            is SignInEvents.OnPasswordFieldInput -> onPasswordFieldInput(event.name)
            is SignInEvents.SignIn -> signIn()
        }
    }

    private fun onNameFieldInput(input: String) = viewModelScope.launch {
        _uiState.update { it.copy(nameInput = input) }
    }

    private fun onPasswordFieldInput(input: String) = viewModelScope.launch {
        _uiState.update { it.copy(passwordInput = input) }
    }

    private fun signIn() = viewModelScope.launch {

        _uiState.update { it.copy(signInResult = AuthResult.Loading) }

        delay(2000)

        _uiState.update { it.copy(signInResult = AuthResult.Success) }
    }

}