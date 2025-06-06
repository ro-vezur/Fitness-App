package com.example.fitnessapp.presentation.mainScreens.HomeScreen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.data.dto.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(

): ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}