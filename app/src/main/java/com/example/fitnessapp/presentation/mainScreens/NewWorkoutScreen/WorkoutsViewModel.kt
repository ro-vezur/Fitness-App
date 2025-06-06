package com.example.fitnessapp.presentation.mainScreens.NewWorkoutScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.data.dto.User
import com.example.fitnessapp.data.dto.workout.Workout
import com.example.fitnessapp.helper.TimeHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsViewModel @Inject constructor(

): ViewModel() {
    private val user = User()

    private val _uiState: MutableStateFlow<WorkoutsUiState> = MutableStateFlow(WorkoutsUiState())
    val uiState: StateFlow<WorkoutsUiState> = _uiState.asStateFlow()

    init {

    }

    fun executeEvent(event: WorkoutsViewModelEvent) = viewModelScope.launch {
        when(event) {
            is WorkoutsViewModelEvent.SetWorkout -> setWorkout(event.selectedDateMillis)
        }
    }

    private fun setWorkout(selectedDateMillis: Long) = viewModelScope.launch {
        val workoutInSelectedDate: Workout? = user.userWorkouts.find {
            TimeHelper.getDateFromMillis(it.date, TimeHelper.DateFormats.MMM_D_YYYY) == TimeHelper.getDateFromMillis(selectedDateMillis, TimeHelper.DateFormats.MMM_D_YYYY)
        }

        _uiState.update { state ->
            state.copy(selectedWorkout = workoutInSelectedDate)
        }
    }
}