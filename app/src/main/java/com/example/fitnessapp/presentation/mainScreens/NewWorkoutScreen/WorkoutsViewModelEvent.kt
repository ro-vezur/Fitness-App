package com.example.fitnessapp.presentation.mainScreens.NewWorkoutScreen

import com.example.fitnessapp.data.dto.workout.Workout

sealed class WorkoutsViewModelEvent {
    class SetWorkout(val selectedDateMillis: Long): WorkoutsViewModelEvent()
}