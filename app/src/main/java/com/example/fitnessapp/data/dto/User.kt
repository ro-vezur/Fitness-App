package com.example.fitnessapp.data.dto

import com.example.fitnessapp.data.dto.workout.Workout

data class User(
    val id: String = "",
    val name: String = "User Name",
    val email: String = "user.name@gmail.com",
    val imageUrl: String? = null,
    val userWorkouts: List<Workout> = listOf(
        Workout.workoutFakeData
    )
)
